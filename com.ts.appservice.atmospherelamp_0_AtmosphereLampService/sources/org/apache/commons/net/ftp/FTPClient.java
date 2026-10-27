package org.apache.commons.net.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.MLSxEntryParser;
import org.apache.commons.net.io.CRLFLineReader;
import org.apache.commons.net.io.CopyStreamAdapter;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.commons.net.io.SocketInputStream;
import org.apache.commons.net.io.SocketOutputStream;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
import org.apache.commons.net.io.Util;

/* JADX INFO: loaded from: classes3.dex */
public class FTPClient extends FTP implements Configurable {
    public static final int ACTIVE_LOCAL_DATA_CONNECTION_MODE = 0;
    public static final int ACTIVE_REMOTE_DATA_CONNECTION_MODE = 1;
    public static final String FTP_SYSTEM_TYPE = "org.apache.commons.net.ftp.systemType";
    public static final String FTP_SYSTEM_TYPE_DEFAULT = "org.apache.commons.net.ftp.systemType.default";
    private static final Pattern PARMS_PAT = Pattern.compile("(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})");
    public static final int PASSIVE_LOCAL_DATA_CONNECTION_MODE = 2;
    public static final int PASSIVE_REMOTE_DATA_CONNECTION_MODE = 3;
    public static final String SYSTEM_TYPE_PROPERTIES = "/systemType.properties";
    private InetAddress activeExternalHost;
    private int activeMaxPort;
    private int activeMinPort;
    private int bufferSize;
    private FTPClientConfig configuration;
    private long controlKeepAliveTimeout;
    private CopyStreamListener copyStreamListener;
    private int[] cslDebug;
    private int dataConnectionMode;
    private int dataTimeout;
    private FTPFileEntryParser entryParser;
    private String entryParserKey;
    private HashMap<String, Set<String>> featuresMap;
    private int fileFormat;
    private int fileStructure;
    private int fileTransferMode;
    private int fileType;
    private boolean listHiddenFiles;
    private FTPFileEntryParserFactory parserFactory;
    private String passiveHost;
    private InetAddress passiveLocalHost;
    private int passivePort;
    private final Random random;
    private int receiveDataSocketBufferSize;
    private boolean remoteVerificationEnabled;
    private InetAddress reportActiveExternalHost;
    private long restartOffset;
    private int sendDataSocketBufferSize;
    private String systemName;
    private boolean useEPSVwithIPv4;
    private int controlKeepAliveReplyTimeout = 1000;
    private HostnameResolver passiveNatWorkaroundStrategy = new NatServerResolverImpl(this);
    private boolean autodetectEncoding = false;

    public interface HostnameResolver {
        String resolve(String str) throws UnknownHostException;
    }

    private static class PropertiesSingleton {
        static final Properties PROPERTIES;

        private PropertiesSingleton() {
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0018 -> B:20:0x0026). Please report as a decompilation issue!!! */
        static {
            InputStream resourceAsStream = FTPClient.class.getResourceAsStream(FTPClient.SYSTEM_TYPE_PROPERTIES);
            Properties p = null;
            if (resourceAsStream != null) {
                p = new Properties();
                try {
                    try {
                        p.load(resourceAsStream);
                        resourceAsStream.close();
                    } catch (IOException e) {
                        resourceAsStream.close();
                    } catch (Throwable th) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException e2) {
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            }
            PROPERTIES = p;
        }
    }

    private static Properties getOverrideProperties() {
        return PropertiesSingleton.PROPERTIES;
    }

    public FTPClient() {
        initDefaults();
        this.dataTimeout = -1;
        this.remoteVerificationEnabled = true;
        this.parserFactory = new DefaultFTPFileEntryParserFactory();
        this.configuration = null;
        this.listHiddenFiles = false;
        this.useEPSVwithIPv4 = false;
        this.random = new Random();
        this.passiveLocalHost = null;
    }

    private void initDefaults() {
        this.dataConnectionMode = 0;
        this.passiveHost = null;
        this.passivePort = -1;
        this.activeExternalHost = null;
        this.reportActiveExternalHost = null;
        this.activeMinPort = 0;
        this.activeMaxPort = 0;
        this.fileType = 0;
        this.fileStructure = 7;
        this.fileFormat = 4;
        this.fileTransferMode = 10;
        this.restartOffset = 0L;
        this.systemName = null;
        this.entryParser = null;
        this.entryParserKey = "";
        this.featuresMap = null;
    }

    static String parsePathname(String reply) {
        String param = reply.substring(4);
        if (param.startsWith("\"")) {
            StringBuilder sb = new StringBuilder();
            boolean quoteSeen = false;
            for (int i = 1; i < param.length(); i++) {
                char ch = param.charAt(i);
                if (ch == '\"') {
                    if (quoteSeen) {
                        sb.append(ch);
                        quoteSeen = false;
                    } else {
                        quoteSeen = true;
                    }
                } else {
                    if (quoteSeen) {
                        return sb.toString();
                    }
                    sb.append(ch);
                }
            }
            if (quoteSeen) {
                return sb.toString();
            }
        }
        return param;
    }

    protected void _parsePassiveModeReply(String reply) throws MalformedServerReplyException {
        Matcher m = PARMS_PAT.matcher(reply);
        if (!m.find()) {
            throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + reply);
        }
        this.passiveHost = "0,0,0,0".equals(m.group(1)) ? this._socket_.getInetAddress().getHostAddress() : m.group(1).replace(',', '.');
        try {
            int oct1 = Integer.parseInt(m.group(2));
            int oct2 = Integer.parseInt(m.group(3));
            this.passivePort = (oct1 << 8) | oct2;
            HostnameResolver hostnameResolver = this.passiveNatWorkaroundStrategy;
            if (hostnameResolver != null) {
                try {
                    String newPassiveHost = hostnameResolver.resolve(this.passiveHost);
                    if (!this.passiveHost.equals(newPassiveHost)) {
                        fireReplyReceived(0, "[Replacing PASV mode reply address " + this.passiveHost + " with " + newPassiveHost + "]\n");
                        this.passiveHost = newPassiveHost;
                    }
                } catch (UnknownHostException e) {
                    throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + reply);
                }
            }
        } catch (NumberFormatException e2) {
            throw new MalformedServerReplyException("Could not parse passive port information.\nServer Reply: " + reply);
        }
    }

    protected void _parseExtendedPassiveModeReply(String reply) throws MalformedServerReplyException {
        String reply2 = reply.substring(reply.indexOf(40) + 1, reply.indexOf(41)).trim();
        char delim1 = reply2.charAt(0);
        char delim2 = reply2.charAt(1);
        char delim3 = reply2.charAt(2);
        char delim4 = reply2.charAt(reply2.length() - 1);
        if (delim1 != delim2 || delim2 != delim3 || delim3 != delim4) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + reply2);
        }
        try {
            int port = Integer.parseInt(reply2.substring(3, reply2.length() - 1));
            this.passiveHost = getRemoteAddress().getHostAddress();
            this.passivePort = port;
        } catch (NumberFormatException e) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + reply2);
        }
    }

    private boolean storeFile(FTPCmd command, String remote, InputStream local) throws IOException {
        return _storeFile(command.getCommand(), remote, local);
    }

    protected boolean _storeFile(String command, String remote, InputStream local) throws IOException {
        OutputStream output;
        CSL csl;
        Socket socket = _openDataConnection_(command, remote);
        if (socket == null) {
            return false;
        }
        if (this.fileType == 0) {
            output = new ToNetASCIIOutputStream(getBufferedOutputStream(socket.getOutputStream()));
        } else {
            OutputStream output2 = socket.getOutputStream();
            output = getBufferedOutputStream(output2);
        }
        long j = this.controlKeepAliveTimeout;
        if (j <= 0) {
            csl = null;
        } else {
            CSL csl2 = new CSL(this, j, this.controlKeepAliveReplyTimeout);
            csl = csl2;
        }
        try {
            try {
                Util.copyStream(local, output, getBufferSize(), -1L, mergeListeners(csl), false);
                output.close();
                socket.close();
                return completePendingCommand();
            } catch (IOException e) {
                Util.closeQuietly(output);
                Util.closeQuietly(socket);
                throw e;
            }
        } finally {
            if (csl != null) {
                this.cslDebug = csl.cleanUp();
            }
        }
    }

    private OutputStream storeFileStream(FTPCmd command, String remote) throws IOException {
        return _storeFileStream(command.getCommand(), remote);
    }

    protected OutputStream _storeFileStream(String command, String remote) throws IOException {
        OutputStream output;
        Socket socket = _openDataConnection_(command, remote);
        if (socket == null) {
            return null;
        }
        if (this.fileType == 0) {
            output = new ToNetASCIIOutputStream(getBufferedOutputStream(socket.getOutputStream()));
        } else {
            output = socket.getOutputStream();
        }
        return new SocketOutputStream(socket, output);
    }

    @Deprecated
    protected Socket _openDataConnection_(int command, String arg) throws IOException {
        return _openDataConnection_(FTPCommand.getCommand(command), arg);
    }

    protected Socket _openDataConnection_(FTPCmd command, String arg) throws IOException {
        return _openDataConnection_(command.getCommand(), arg);
    }

    protected Socket _openDataConnection_(String command, String arg) throws IOException {
        Socket socket;
        int i = this.dataConnectionMode;
        if (i != 0 && i != 2) {
            return null;
        }
        boolean isInet6Address = getRemoteAddress() instanceof Inet6Address;
        boolean z = true;
        if (this.dataConnectionMode == 0) {
            ServerSocket server = this._serverSocketFactory_.createServerSocket(getActivePort(), 1, getHostAddress());
            try {
                if (isInet6Address) {
                    if (!FTPReply.isPositiveCompletion(eprt(getReportHostAddress(), server.getLocalPort()))) {
                        if (server != null) {
                            server.close();
                        }
                        return null;
                    }
                } else if (!FTPReply.isPositiveCompletion(port(getReportHostAddress(), server.getLocalPort()))) {
                    if (server != null) {
                        server.close();
                    }
                    return null;
                }
                if (this.restartOffset > 0 && !restart(this.restartOffset)) {
                    if (server != null) {
                        server.close();
                    }
                    return null;
                }
                if (!FTPReply.isPositivePreliminary(sendCommand(command, arg))) {
                    if (server != null) {
                        server.close();
                    }
                    return null;
                }
                if (this.dataTimeout >= 0) {
                    server.setSoTimeout(this.dataTimeout);
                }
                socket = server.accept();
                if (this.dataTimeout >= 0) {
                    socket.setSoTimeout(this.dataTimeout);
                }
                if (this.receiveDataSocketBufferSize > 0) {
                    socket.setReceiveBufferSize(this.receiveDataSocketBufferSize);
                }
                if (this.sendDataSocketBufferSize > 0) {
                    socket.setSendBufferSize(this.sendDataSocketBufferSize);
                }
                if (server != null) {
                    server.close();
                }
            } finally {
            }
        } else {
            if (!isUseEPSVwithIPv4() && !isInet6Address) {
                z = false;
            }
            boolean attemptEPSV = z;
            if (attemptEPSV && epsv() == 229) {
                _parseExtendedPassiveModeReply(this._replyLines.get(0));
            } else {
                if (isInet6Address || pasv() != 227) {
                    return null;
                }
                _parsePassiveModeReply(this._replyLines.get(0));
            }
            Socket socket2 = this._socketFactory_.createSocket();
            int i2 = this.receiveDataSocketBufferSize;
            if (i2 > 0) {
                socket2.setReceiveBufferSize(i2);
            }
            int i3 = this.sendDataSocketBufferSize;
            if (i3 > 0) {
                socket2.setSendBufferSize(i3);
            }
            if (this.passiveLocalHost != null) {
                socket2.bind(new InetSocketAddress(this.passiveLocalHost, 0));
            }
            int i4 = this.dataTimeout;
            if (i4 >= 0) {
                socket2.setSoTimeout(i4);
            }
            socket2.connect(new InetSocketAddress(this.passiveHost, this.passivePort), this.connectTimeout);
            long j = this.restartOffset;
            if (j > 0 && !restart(j)) {
                socket2.close();
                return null;
            }
            if (!FTPReply.isPositivePreliminary(sendCommand(command, arg))) {
                socket2.close();
                return null;
            }
            socket = socket2;
        }
        boolean attemptEPSV2 = this.remoteVerificationEnabled;
        if (!attemptEPSV2 || verifyRemote(socket)) {
            return socket;
        }
        InetAddress socketHost = socket.getInetAddress();
        socket.close();
        throw new IOException("Host attempting data connection " + socketHost.getHostAddress() + " is not same as server " + getRemoteAddress().getHostAddress());
    }

    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        _connectAction_(null);
    }

    @Override // org.apache.commons.net.ftp.FTP
    protected void _connectAction_(Reader socketIsReader) throws IOException {
        super._connectAction_(socketIsReader);
        initDefaults();
        if (this.autodetectEncoding) {
            ArrayList<String> oldReplyLines = new ArrayList<>(this._replyLines);
            int oldReplyCode = this._replyCode;
            if (hasFeature("UTF8") || hasFeature("UTF-8")) {
                setControlEncoding("UTF-8");
                this._controlInput_ = new CRLFLineReader(new InputStreamReader(this._input_, getControlEncoding()));
                this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(this._output_, getControlEncoding()));
            }
            this._replyLines.clear();
            this._replyLines.addAll(oldReplyLines);
            this._replyCode = oldReplyCode;
            this._newReplyString = true;
        }
    }

    public void setDataTimeout(int timeout) {
        this.dataTimeout = timeout;
    }

    public void setParserFactory(FTPFileEntryParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    @Override // org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        initDefaults();
    }

    public void setRemoteVerificationEnabled(boolean enable) {
        this.remoteVerificationEnabled = enable;
    }

    public boolean isRemoteVerificationEnabled() {
        return this.remoteVerificationEnabled;
    }

    public boolean login(String username, String password) throws IOException {
        user(username);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (!FTPReply.isPositiveIntermediate(this._replyCode)) {
            return false;
        }
        return FTPReply.isPositiveCompletion(pass(password));
    }

    public boolean login(String username, String password, String account) throws IOException {
        user(username);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (!FTPReply.isPositiveIntermediate(this._replyCode)) {
            return false;
        }
        pass(password);
        if (FTPReply.isPositiveCompletion(this._replyCode)) {
            return true;
        }
        if (FTPReply.isPositiveIntermediate(this._replyCode)) {
            return FTPReply.isPositiveCompletion(acct(account));
        }
        return false;
    }

    public boolean logout() throws IOException {
        return FTPReply.isPositiveCompletion(quit());
    }

    public boolean changeWorkingDirectory(String pathname) throws IOException {
        return FTPReply.isPositiveCompletion(cwd(pathname));
    }

    public boolean changeToParentDirectory() throws IOException {
        return FTPReply.isPositiveCompletion(cdup());
    }

    public boolean structureMount(String pathname) throws IOException {
        return FTPReply.isPositiveCompletion(smnt(pathname));
    }

    public boolean reinitialize() throws IOException {
        rein();
        if (FTPReply.isPositiveCompletion(this._replyCode) || (FTPReply.isPositivePreliminary(this._replyCode) && FTPReply.isPositiveCompletion(getReply()))) {
            initDefaults();
            return true;
        }
        return false;
    }

    public void enterLocalActiveMode() {
        this.dataConnectionMode = 0;
        this.passiveHost = null;
        this.passivePort = -1;
    }

    public void enterLocalPassiveMode() {
        this.dataConnectionMode = 2;
        this.passiveHost = null;
        this.passivePort = -1;
    }

    public boolean enterRemoteActiveMode(InetAddress host, int port) throws IOException {
        if (FTPReply.isPositiveCompletion(port(host, port))) {
            this.dataConnectionMode = 1;
            this.passiveHost = null;
            this.passivePort = -1;
            return true;
        }
        return false;
    }

    public boolean enterRemotePassiveMode() throws IOException {
        if (pasv() != 227) {
            return false;
        }
        this.dataConnectionMode = 3;
        _parsePassiveModeReply(this._replyLines.get(0));
        return true;
    }

    public String getPassiveHost() {
        return this.passiveHost;
    }

    public int getPassivePort() {
        return this.passivePort;
    }

    public int getDataConnectionMode() {
        return this.dataConnectionMode;
    }

    private int getActivePort() {
        int i;
        int i2 = this.activeMinPort;
        if (i2 > 0 && (i = this.activeMaxPort) >= i2) {
            if (i == i2) {
                return i;
            }
            return this.random.nextInt((i - i2) + 1) + this.activeMinPort;
        }
        return 0;
    }

    private InetAddress getHostAddress() {
        InetAddress inetAddress = this.activeExternalHost;
        if (inetAddress != null) {
            return inetAddress;
        }
        return getLocalAddress();
    }

    private InetAddress getReportHostAddress() {
        InetAddress inetAddress = this.reportActiveExternalHost;
        if (inetAddress != null) {
            return inetAddress;
        }
        return getHostAddress();
    }

    public void setActivePortRange(int minPort, int maxPort) {
        this.activeMinPort = minPort;
        this.activeMaxPort = maxPort;
    }

    public void setActiveExternalIPAddress(String ipAddress) throws UnknownHostException {
        this.activeExternalHost = InetAddress.getByName(ipAddress);
    }

    public void setPassiveLocalIPAddress(String ipAddress) throws UnknownHostException {
        this.passiveLocalHost = InetAddress.getByName(ipAddress);
    }

    public void setPassiveLocalIPAddress(InetAddress inetAddress) {
        this.passiveLocalHost = inetAddress;
    }

    public InetAddress getPassiveLocalIPAddress() {
        return this.passiveLocalHost;
    }

    public void setReportActiveExternalIPAddress(String ipAddress) throws UnknownHostException {
        this.reportActiveExternalHost = InetAddress.getByName(ipAddress);
    }

    public boolean setFileType(int fileType) throws IOException {
        if (FTPReply.isPositiveCompletion(type(fileType))) {
            this.fileType = fileType;
            this.fileFormat = 4;
            return true;
        }
        return false;
    }

    public boolean setFileType(int fileType, int formatOrByteSize) throws IOException {
        if (FTPReply.isPositiveCompletion(type(fileType, formatOrByteSize))) {
            this.fileType = fileType;
            this.fileFormat = formatOrByteSize;
            return true;
        }
        return false;
    }

    public boolean setFileStructure(int structure) throws IOException {
        if (FTPReply.isPositiveCompletion(stru(structure))) {
            this.fileStructure = structure;
            return true;
        }
        return false;
    }

    public boolean setFileTransferMode(int mode) throws IOException {
        if (FTPReply.isPositiveCompletion(mode(mode))) {
            this.fileTransferMode = mode;
            return true;
        }
        return false;
    }

    public boolean remoteRetrieve(String fileName) throws IOException {
        int i = this.dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(retr(fileName));
        }
        return false;
    }

    public boolean remoteStore(String fileName) throws IOException {
        int i = this.dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stor(fileName));
        }
        return false;
    }

    public boolean remoteStoreUnique(String fileName) throws IOException {
        int i = this.dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou(fileName));
        }
        return false;
    }

    public boolean remoteStoreUnique() throws IOException {
        int i = this.dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(stou());
        }
        return false;
    }

    public boolean remoteAppend(String fileName) throws IOException {
        int i = this.dataConnectionMode;
        if (i == 1 || i == 3) {
            return FTPReply.isPositivePreliminary(appe(fileName));
        }
        return false;
    }

    public boolean completePendingCommand() throws IOException {
        return FTPReply.isPositiveCompletion(getReply());
    }

    public boolean retrieveFile(String remote, OutputStream local) throws IOException {
        return _retrieveFile(FTPCmd.RETR.getCommand(), remote, local);
    }

    protected boolean _retrieveFile(String command, String remote, OutputStream local) throws IOException {
        Socket socket = _openDataConnection_(command, remote);
        if (socket == null) {
            return false;
        }
        InputStream input = null;
        CSL csl = null;
        try {
            try {
                if (this.fileType == 0) {
                    input = new FromNetASCIIInputStream(getBufferedInputStream(socket.getInputStream()));
                } else {
                    input = getBufferedInputStream(socket.getInputStream());
                }
                if (this.controlKeepAliveTimeout > 0) {
                    csl = new CSL(this, this.controlKeepAliveTimeout, this.controlKeepAliveReplyTimeout);
                }
                Util.copyStream(input, local, getBufferSize(), -1L, mergeListeners(csl), false);
                Util.closeQuietly(input);
                return completePendingCommand();
            } catch (Throwable th) {
                Util.closeQuietly(input);
                throw th;
            }
        } finally {
            Util.closeQuietly(socket);
            if (csl != null) {
                this.cslDebug = csl.cleanUp();
            }
        }
    }

    public InputStream retrieveFileStream(String remote) throws IOException {
        return _retrieveFileStream(FTPCmd.RETR.getCommand(), remote);
    }

    protected InputStream _retrieveFileStream(String command, String remote) throws IOException {
        InputStream input;
        Socket socket = _openDataConnection_(command, remote);
        if (socket == null) {
            return null;
        }
        if (this.fileType == 0) {
            input = new FromNetASCIIInputStream(getBufferedInputStream(socket.getInputStream()));
        } else {
            input = socket.getInputStream();
        }
        return new SocketInputStream(socket, input);
    }

    public boolean storeFile(String remote, InputStream local) throws IOException {
        return storeFile(FTPCmd.STOR, remote, local);
    }

    public OutputStream storeFileStream(String remote) throws IOException {
        return storeFileStream(FTPCmd.STOR, remote);
    }

    public boolean appendFile(String remote, InputStream local) throws IOException {
        return storeFile(FTPCmd.APPE, remote, local);
    }

    public OutputStream appendFileStream(String remote) throws IOException {
        return storeFileStream(FTPCmd.APPE, remote);
    }

    public boolean storeUniqueFile(String remote, InputStream local) throws IOException {
        return storeFile(FTPCmd.STOU, remote, local);
    }

    public OutputStream storeUniqueFileStream(String remote) throws IOException {
        return storeFileStream(FTPCmd.STOU, remote);
    }

    public boolean storeUniqueFile(InputStream local) throws IOException {
        return storeFile(FTPCmd.STOU, null, local);
    }

    public OutputStream storeUniqueFileStream() throws IOException {
        return storeFileStream(FTPCmd.STOU, null);
    }

    public boolean allocate(int bytes) throws IOException {
        return FTPReply.isPositiveCompletion(allo(bytes));
    }

    public boolean allocate(long bytes) throws IOException {
        return FTPReply.isPositiveCompletion(allo(bytes));
    }

    public boolean features() throws IOException {
        return FTPReply.isPositiveCompletion(feat());
    }

    public String[] featureValues(String feature) throws IOException {
        Set<String> entries;
        if (initFeatureMap() && (entries = this.featuresMap.get(feature.toUpperCase(Locale.ENGLISH))) != null) {
            return (String[]) entries.toArray(new String[entries.size()]);
        }
        return null;
    }

    public String featureValue(String feature) throws IOException {
        String[] values = featureValues(feature);
        if (values != null) {
            return values[0];
        }
        return null;
    }

    public boolean hasFeature(String feature) throws IOException {
        if (!initFeatureMap()) {
            return false;
        }
        return this.featuresMap.containsKey(feature.toUpperCase(Locale.ENGLISH));
    }

    public boolean hasFeature(String feature, String value) throws IOException {
        Set<String> entries;
        if (initFeatureMap() && (entries = this.featuresMap.get(feature.toUpperCase(Locale.ENGLISH))) != null) {
            return entries.contains(value);
        }
        return false;
    }

    private boolean initFeatureMap() throws IOException {
        String key;
        if (this.featuresMap == null) {
            int replyCode = feat();
            if (replyCode == 530) {
                return false;
            }
            boolean success = FTPReply.isPositiveCompletion(replyCode);
            this.featuresMap = new HashMap<>();
            if (!success) {
                return false;
            }
            for (String l : getReplyStrings()) {
                if (l.startsWith(" ")) {
                    String value = "";
                    int varsep = l.indexOf(32, 1);
                    if (varsep > 0) {
                        key = l.substring(1, varsep);
                        value = l.substring(varsep + 1);
                    } else {
                        key = l.substring(1);
                    }
                    String key2 = key.toUpperCase(Locale.ENGLISH);
                    Set<String> entries = this.featuresMap.get(key2);
                    if (entries == null) {
                        entries = new HashSet();
                        this.featuresMap.put(key2, entries);
                    }
                    entries.add(value);
                }
            }
        }
        return true;
    }

    public boolean allocate(int bytes, int recordSize) throws IOException {
        return FTPReply.isPositiveCompletion(allo(bytes, recordSize));
    }

    public boolean allocate(long bytes, int recordSize) throws IOException {
        return FTPReply.isPositiveCompletion(allo(bytes, recordSize));
    }

    public boolean doCommand(String command, String params) throws IOException {
        return FTPReply.isPositiveCompletion(sendCommand(command, params));
    }

    public String[] doCommandAsStrings(String command, String params) throws IOException {
        boolean success = FTPReply.isPositiveCompletion(sendCommand(command, params));
        if (success) {
            return getReplyStrings();
        }
        return null;
    }

    public FTPFile mlistFile(String pathname) throws IOException {
        boolean success = FTPReply.isPositiveCompletion(sendCommand(FTPCmd.MLST, pathname));
        if (success) {
            String reply = getReplyStrings()[1];
            if (reply.charAt(0) != ' ') {
                reply = " " + reply;
            }
            if (reply.length() < 3) {
                throw new MalformedServerReplyException("Invalid server reply (MLST): '" + reply + "'");
            }
            String entry = reply.replaceAll("^\\s+", "");
            return MLSxEntryParser.parseEntry(entry);
        }
        return null;
    }

    public FTPFile[] mlistDir() throws IOException {
        return mlistDir(null);
    }

    public FTPFile[] mlistDir(String pathname) throws IOException {
        FTPListParseEngine engine = initiateMListParsing(pathname);
        return engine.getFiles();
    }

    public FTPFile[] mlistDir(String pathname, FTPFileFilter filter) throws IOException {
        FTPListParseEngine engine = initiateMListParsing(pathname);
        return engine.getFiles(filter);
    }

    protected boolean restart(long offset) throws IOException {
        this.restartOffset = 0L;
        return FTPReply.isPositiveIntermediate(rest(Long.toString(offset)));
    }

    public void setRestartOffset(long offset) {
        if (offset >= 0) {
            this.restartOffset = offset;
        }
    }

    public long getRestartOffset() {
        return this.restartOffset;
    }

    public boolean rename(String from, String to) throws IOException {
        if (!FTPReply.isPositiveIntermediate(rnfr(from))) {
            return false;
        }
        return FTPReply.isPositiveCompletion(rnto(to));
    }

    public boolean abort() throws IOException {
        return FTPReply.isPositiveCompletion(abor());
    }

    public boolean deleteFile(String pathname) throws IOException {
        return FTPReply.isPositiveCompletion(dele(pathname));
    }

    public boolean removeDirectory(String pathname) throws IOException {
        return FTPReply.isPositiveCompletion(rmd(pathname));
    }

    public boolean makeDirectory(String pathname) throws IOException {
        return FTPReply.isPositiveCompletion(mkd(pathname));
    }

    public String printWorkingDirectory() throws IOException {
        if (pwd() != 257) {
            return null;
        }
        return parsePathname(this._replyLines.get(this._replyLines.size() - 1));
    }

    public boolean sendSiteCommand(String arguments) throws IOException {
        return FTPReply.isPositiveCompletion(site(arguments));
    }

    public String getSystemType() throws IOException {
        if (this.systemName == null) {
            if (FTPReply.isPositiveCompletion(syst())) {
                this.systemName = this._replyLines.get(this._replyLines.size() - 1).substring(4);
            } else {
                String systDefault = System.getProperty(FTP_SYSTEM_TYPE_DEFAULT);
                if (systDefault != null) {
                    this.systemName = systDefault;
                } else {
                    throw new IOException("Unable to determine system type - response: " + getReplyString());
                }
            }
        }
        return this.systemName;
    }

    public String listHelp() throws IOException {
        if (FTPReply.isPositiveCompletion(help())) {
            return getReplyString();
        }
        return null;
    }

    public String listHelp(String command) throws IOException {
        if (FTPReply.isPositiveCompletion(help(command))) {
            return getReplyString();
        }
        return null;
    }

    public boolean sendNoOp() throws IOException {
        return FTPReply.isPositiveCompletion(noop());
    }

    public String[] listNames(String pathname) throws IOException {
        ArrayList<String> results = new ArrayList<>();
        Socket socket = _openDataConnection_(FTPCmd.NLST, getListArguments(pathname));
        if (socket != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), getControlEncoding()));
                while (true) {
                    try {
                        String line = reader.readLine();
                        if (line == null) {
                            break;
                        }
                        results.add(line);
                    } finally {
                    }
                }
                reader.close();
                if (socket != null) {
                    socket.close();
                }
                if (!completePendingCommand()) {
                    return null;
                }
                String[] names = new String[results.size()];
                return (String[]) results.toArray(names);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (socket != null) {
            socket.close();
        }
        return null;
    }

    public String[] listNames() throws IOException {
        return listNames(null);
    }

    public FTPFile[] listFiles(String pathname) throws IOException {
        FTPListParseEngine engine = initiateListParsing((String) null, pathname);
        return engine.getFiles();
    }

    public FTPFile[] listFiles() throws IOException {
        return listFiles((String) null);
    }

    public FTPFile[] listFiles(String pathname, FTPFileFilter filter) throws IOException {
        FTPListParseEngine engine = initiateListParsing((String) null, pathname);
        return engine.getFiles(filter);
    }

    public FTPFile[] listDirectories() throws IOException {
        return listDirectories((String) null);
    }

    public FTPFile[] listDirectories(String parent) throws IOException {
        return listFiles(parent, FTPFileFilters.DIRECTORIES);
    }

    public FTPListParseEngine initiateListParsing() throws IOException {
        return initiateListParsing((String) null);
    }

    public FTPListParseEngine initiateListParsing(String pathname) throws IOException {
        return initiateListParsing((String) null, pathname);
    }

    public FTPListParseEngine initiateListParsing(String parserKey, String pathname) throws IOException {
        createParser(parserKey);
        return initiateListParsing(this.entryParser, pathname);
    }

    void createParser(String parserKey) throws IOException {
        String newType;
        if (this.entryParser == null || (parserKey != null && !this.entryParserKey.equals(parserKey))) {
            if (parserKey != null) {
                this.entryParser = this.parserFactory.createFileEntryParser(parserKey);
                this.entryParserKey = parserKey;
                return;
            }
            FTPClientConfig fTPClientConfig = this.configuration;
            if (fTPClientConfig != null && fTPClientConfig.getServerSystemKey().length() > 0) {
                this.entryParser = this.parserFactory.createFileEntryParser(this.configuration);
                this.entryParserKey = this.configuration.getServerSystemKey();
                return;
            }
            String systemType = System.getProperty(FTP_SYSTEM_TYPE);
            if (systemType == null) {
                systemType = getSystemType();
                Properties override = getOverrideProperties();
                if (override != null && (newType = override.getProperty(systemType)) != null) {
                    systemType = newType;
                }
            }
            if (this.configuration != null) {
                this.entryParser = this.parserFactory.createFileEntryParser(new FTPClientConfig(systemType, this.configuration));
            } else {
                this.entryParser = this.parserFactory.createFileEntryParser(systemType);
            }
            this.entryParserKey = systemType;
        }
    }

    private FTPListParseEngine initiateListParsing(FTPFileEntryParser parser, String pathname) throws IOException {
        Socket socket = _openDataConnection_(FTPCmd.LIST, getListArguments(pathname));
        FTPListParseEngine engine = new FTPListParseEngine(parser, this.configuration);
        if (socket == null) {
            return engine;
        }
        try {
            engine.readServerList(socket.getInputStream(), getControlEncoding());
            Util.closeQuietly(socket);
            completePendingCommand();
            return engine;
        } catch (Throwable th) {
            Util.closeQuietly(socket);
            throw th;
        }
    }

    public FTPListParseEngine initiateMListParsing() throws IOException {
        return initiateMListParsing(null);
    }

    public FTPListParseEngine initiateMListParsing(String pathname) throws IOException {
        Socket socket = _openDataConnection_(FTPCmd.MLSD, pathname);
        FTPListParseEngine engine = new FTPListParseEngine(MLSxEntryParser.getInstance(), this.configuration);
        if (socket == null) {
            return engine;
        }
        try {
            engine.readServerList(socket.getInputStream(), getControlEncoding());
            return engine;
        } finally {
            Util.closeQuietly(socket);
            completePendingCommand();
        }
    }

    protected String getListArguments(String pathname) {
        if (getListHiddenFiles()) {
            if (pathname != null) {
                StringBuilder sb = new StringBuilder(pathname.length() + 3);
                sb.append("-a ");
                sb.append(pathname);
                return sb.toString();
            }
            return "-a";
        }
        return pathname;
    }

    public String getStatus() throws IOException {
        if (FTPReply.isPositiveCompletion(stat())) {
            return getReplyString();
        }
        return null;
    }

    public String getStatus(String pathname) throws IOException {
        if (FTPReply.isPositiveCompletion(stat(pathname))) {
            return getReplyString();
        }
        return null;
    }

    public String getSize(String pathname) throws IOException {
        if (FTPReply.isPositiveCompletion(size(pathname))) {
            return getReplyStrings()[0].substring(4);
        }
        return null;
    }

    public String getModificationTime(String pathname) throws IOException {
        if (FTPReply.isPositiveCompletion(mdtm(pathname))) {
            return getReplyStrings()[0].substring(4);
        }
        return null;
    }

    public FTPFile mdtmFile(String pathname) throws IOException {
        if (FTPReply.isPositiveCompletion(mdtm(pathname))) {
            String reply = getReplyStrings()[0].substring(4);
            FTPFile file = new FTPFile();
            file.setName(pathname);
            file.setRawListing(reply);
            file.setTimestamp(MLSxEntryParser.parseGMTdateTime(reply));
            return file;
        }
        return null;
    }

    public boolean setModificationTime(String pathname, String timeval) throws IOException {
        return FTPReply.isPositiveCompletion(mfmt(pathname, timeval));
    }

    public void setBufferSize(int bufSize) {
        this.bufferSize = bufSize;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setSendDataSocketBufferSize(int bufSize) {
        this.sendDataSocketBufferSize = bufSize;
    }

    public int getSendDataSocketBufferSize() {
        return this.sendDataSocketBufferSize;
    }

    public void setReceieveDataSocketBufferSize(int bufSize) {
        this.receiveDataSocketBufferSize = bufSize;
    }

    public int getReceiveDataSocketBufferSize() {
        return this.receiveDataSocketBufferSize;
    }

    @Override // org.apache.commons.net.ftp.Configurable
    public void configure(FTPClientConfig config) {
        this.configuration = config;
    }

    public void setListHiddenFiles(boolean listHiddenFiles) {
        this.listHiddenFiles = listHiddenFiles;
    }

    public boolean getListHiddenFiles() {
        return this.listHiddenFiles;
    }

    public boolean isUseEPSVwithIPv4() {
        return this.useEPSVwithIPv4;
    }

    public void setUseEPSVwithIPv4(boolean selected) {
        this.useEPSVwithIPv4 = selected;
    }

    public void setCopyStreamListener(CopyStreamListener listener) {
        this.copyStreamListener = listener;
    }

    public CopyStreamListener getCopyStreamListener() {
        return this.copyStreamListener;
    }

    public void setControlKeepAliveTimeout(long controlIdle) {
        this.controlKeepAliveTimeout = 1000 * controlIdle;
    }

    public long getControlKeepAliveTimeout() {
        return this.controlKeepAliveTimeout / 1000;
    }

    @Deprecated
    public int[] getCslDebug() {
        return this.cslDebug;
    }

    public void setControlKeepAliveReplyTimeout(int timeout) {
        this.controlKeepAliveReplyTimeout = timeout;
    }

    public int getControlKeepAliveReplyTimeout() {
        return this.controlKeepAliveReplyTimeout;
    }

    @Deprecated
    public void setPassiveNatWorkaround(boolean enabled) {
        if (enabled) {
            this.passiveNatWorkaroundStrategy = new NatServerResolverImpl(this);
        } else {
            this.passiveNatWorkaroundStrategy = null;
        }
    }

    public void setPassiveNatWorkaroundStrategy(HostnameResolver resolver) {
        this.passiveNatWorkaroundStrategy = resolver;
    }

    public static class NatServerResolverImpl implements HostnameResolver {
        private final FTPClient client;

        public NatServerResolverImpl(FTPClient client) {
            this.client = client;
        }

        @Override // org.apache.commons.net.ftp.FTPClient.HostnameResolver
        public String resolve(String hostname) throws UnknownHostException {
            InetAddress host = InetAddress.getByName(hostname);
            if (!host.isSiteLocalAddress()) {
                return hostname;
            }
            InetAddress remote = this.client.getRemoteAddress();
            if (remote.isSiteLocalAddress()) {
                return hostname;
            }
            String newHostname = remote.getHostAddress();
            return newHostname;
        }
    }

    private OutputStream getBufferedOutputStream(OutputStream outputStream) {
        if (this.bufferSize > 0) {
            return new BufferedOutputStream(outputStream, this.bufferSize);
        }
        return new BufferedOutputStream(outputStream);
    }

    private InputStream getBufferedInputStream(InputStream inputStream) {
        if (this.bufferSize > 0) {
            return new BufferedInputStream(inputStream, this.bufferSize);
        }
        return new BufferedInputStream(inputStream);
    }

    private static class CSL implements CopyStreamListener {
        private int acksAcked;
        private final int currentSoTimeout;
        private final long idle;
        private int ioErrors;
        private int notAcked;
        private final FTPClient parent;
        private long time = System.currentTimeMillis();

        CSL(FTPClient parent, long idleTime, int maxWait) throws SocketException {
            this.idle = idleTime;
            this.parent = parent;
            this.currentSoTimeout = parent.getSoTimeout();
            parent.setSoTimeout(maxWait);
        }

        @Override // org.apache.commons.net.io.CopyStreamListener
        public void bytesTransferred(CopyStreamEvent event) {
            bytesTransferred(event.getTotalBytesTransferred(), event.getBytesTransferred(), event.getStreamSize());
        }

        @Override // org.apache.commons.net.io.CopyStreamListener
        public void bytesTransferred(long totalBytesTransferred, int bytesTransferred, long streamSize) {
            long now = System.currentTimeMillis();
            if (now - this.time > this.idle) {
                try {
                    this.parent.__noop();
                    this.acksAcked++;
                } catch (SocketTimeoutException e) {
                    this.notAcked++;
                } catch (IOException e2) {
                    this.ioErrors++;
                }
                this.time = now;
            }
        }

        int[] cleanUp() throws IOException {
            int remain = this.notAcked;
            while (this.notAcked > 0) {
                try {
                    this.parent.getReply();
                    this.notAcked--;
                } catch (SocketTimeoutException e) {
                } catch (Throwable th) {
                    this.parent.setSoTimeout(this.currentSoTimeout);
                    throw th;
                }
            }
            this.parent.setSoTimeout(this.currentSoTimeout);
            return new int[]{this.acksAcked, remain, this.notAcked, this.ioErrors};
        }
    }

    private CopyStreamListener mergeListeners(CopyStreamListener local) {
        if (local == null) {
            return this.copyStreamListener;
        }
        if (this.copyStreamListener == null) {
            return local;
        }
        CopyStreamAdapter merged = new CopyStreamAdapter();
        merged.addCopyStreamListener(local);
        merged.addCopyStreamListener(this.copyStreamListener);
        return merged;
    }

    public void setAutodetectUTF8(boolean autodetect) {
        this.autodetectEncoding = autodetect;
    }

    public boolean getAutodetectUTF8() {
        return this.autodetectEncoding;
    }

    FTPFileEntryParser getEntryParser() {
        return this.entryParser;
    }

    @Deprecated
    public String getSystemName() throws IOException {
        if (this.systemName == null && FTPReply.isPositiveCompletion(syst())) {
            this.systemName = this._replyLines.get(this._replyLines.size() - 1).substring(4);
        }
        return this.systemName;
    }
}
