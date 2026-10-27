package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.commons.net.util.Base64;
import org.apache.commons.net.util.SSLContextUtils;
import org.apache.commons.net.util.SSLSocketUtils;
import org.apache.commons.net.util.TrustManagerUtils;

/* JADX INFO: loaded from: classes.dex */
public class FTPSClient extends FTPClient {
    private static final String CMD_ADAT = "ADAT";
    private static final String CMD_AUTH = "AUTH";
    private static final String CMD_CCC = "CCC";
    private static final String CMD_CONF = "CONF";
    private static final String CMD_ENC = "ENC";
    private static final String CMD_MIC = "MIC";
    private static final String CMD_PBSZ = "PBSZ";
    private static final String CMD_PROT = "PROT";
    public static final int DEFAULT_FTPS_DATA_PORT = 989;
    public static final int DEFAULT_FTPS_PORT = 990;
    private static final String DEFAULT_PROTOCOL = "TLS";

    @Deprecated
    public static String KEYSTORE_ALGORITHM;

    @Deprecated
    public static String PROVIDER;

    @Deprecated
    public static String STORE_TYPE;

    @Deprecated
    public static String TRUSTSTORE_ALGORITHM;
    private String auth;
    private SSLContext context;
    private HostnameVerifier hostnameVerifier;
    private boolean isClientMode;
    private boolean isCreation;
    private final boolean isImplicit;
    private boolean isNeedClientAuth;
    private boolean isWantClientAuth;
    private KeyManager keyManager;
    private Socket plainSocket;
    private final String protocol;
    private String[] protocols;
    private String[] suites;
    private boolean tlsEndpointChecking;
    private TrustManager trustManager;
    private static final String DEFAULT_PROT = "C";
    private static final String[] PROT_COMMAND_VALUE = {DEFAULT_PROT, "E", "S", "P"};

    public FTPSClient() {
        this("TLS", false);
    }

    public FTPSClient(boolean isImplicit) {
        this("TLS", isImplicit);
    }

    public FTPSClient(String protocol) {
        this(protocol, false);
    }

    public FTPSClient(String protocol, boolean isImplicit) {
        this.auth = "TLS";
        this.isCreation = true;
        this.isClientMode = true;
        this.trustManager = TrustManagerUtils.getValidateServerCertificateTrustManager();
        this.protocol = protocol;
        this.isImplicit = isImplicit;
        if (isImplicit) {
            setDefaultPort(DEFAULT_FTPS_PORT);
        }
    }

    public FTPSClient(boolean isImplicit, SSLContext context) {
        this("TLS", isImplicit);
        this.context = context;
    }

    public FTPSClient(SSLContext context) {
        this(false, context);
    }

    public void setAuthValue(String auth) {
        this.auth = auth;
    }

    public String getAuthValue() {
        return this.auth;
    }

    @Override // org.apache.commons.net.ftp.FTPClient, org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        if (this.isImplicit) {
            applySocketAttributes();
            sslNegotiation();
        }
        super._connectAction_();
        if (!this.isImplicit) {
            execAUTH();
            sslNegotiation();
        }
    }

    protected void execAUTH() throws IOException {
        int replyCode = sendCommand(CMD_AUTH, this.auth);
        if (334 != replyCode && 234 != replyCode) {
            throw new SSLException(getReplyString());
        }
    }

    private void initSslContext() throws IOException {
        if (this.context == null) {
            this.context = SSLContextUtils.createSSLContext(this.protocol, getKeyManager(), getTrustManager());
        }
    }

    protected void sslNegotiation() throws IOException {
        HostnameVerifier hostnameVerifier;
        this.plainSocket = this._socket_;
        initSslContext();
        SSLSocket socket = createSSLSocket(this._socket_);
        socket.setEnableSessionCreation(this.isCreation);
        socket.setUseClientMode(this.isClientMode);
        if (this.isClientMode) {
            if (this.tlsEndpointChecking) {
                SSLSocketUtils.enableEndpointNameVerification(socket);
            }
        } else {
            socket.setNeedClientAuth(this.isNeedClientAuth);
            socket.setWantClientAuth(this.isWantClientAuth);
        }
        String[] strArr = this.protocols;
        if (strArr != null) {
            socket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = this.suites;
        if (strArr2 != null) {
            socket.setEnabledCipherSuites(strArr2);
        }
        socket.startHandshake();
        this._socket_ = socket;
        this._controlInput_ = new BufferedReader(new InputStreamReader(socket.getInputStream(), getControlEncoding()));
        this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), getControlEncoding()));
        if (this.isClientMode && (hostnameVerifier = this.hostnameVerifier) != null && !hostnameVerifier.verify(this._hostname_, socket.getSession())) {
            throw new SSLHandshakeException("Hostname doesn't match certificate");
        }
    }

    private KeyManager getKeyManager() {
        return this.keyManager;
    }

    public void setKeyManager(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    public void setEnabledSessionCreation(boolean isCreation) {
        this.isCreation = isCreation;
    }

    public boolean getEnableSessionCreation() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnableSessionCreation();
        }
        return false;
    }

    public void setNeedClientAuth(boolean isNeedClientAuth) {
        this.isNeedClientAuth = isNeedClientAuth;
    }

    public boolean getNeedClientAuth() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getNeedClientAuth();
        }
        return false;
    }

    public void setWantClientAuth(boolean isWantClientAuth) {
        this.isWantClientAuth = isWantClientAuth;
    }

    public boolean getWantClientAuth() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getWantClientAuth();
        }
        return false;
    }

    public void setUseClientMode(boolean isClientMode) {
        this.isClientMode = isClientMode;
    }

    public boolean getUseClientMode() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getUseClientMode();
        }
        return false;
    }

    public void setEnabledCipherSuites(String[] cipherSuites) {
        this.suites = (String[]) cipherSuites.clone();
    }

    public String[] getEnabledCipherSuites() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnabledCipherSuites();
        }
        return null;
    }

    public void setEnabledProtocols(String[] protocolVersions) {
        this.protocols = (String[]) protocolVersions.clone();
    }

    public String[] getEnabledProtocols() {
        if (this._socket_ instanceof SSLSocket) {
            return ((SSLSocket) this._socket_).getEnabledProtocols();
        }
        return null;
    }

    public void execPBSZ(long pbsz) throws IOException {
        if (pbsz < 0 || 4294967295L < pbsz) {
            throw new IllegalArgumentException();
        }
        int status = sendCommand(CMD_PBSZ, String.valueOf(pbsz));
        if (200 != status) {
            throw new SSLException(getReplyString());
        }
    }

    public long parsePBSZ(long pbsz) throws IOException {
        execPBSZ(pbsz);
        String remainder = extractPrefixedData("PBSZ=", getReplyString());
        if (remainder == null) {
            return pbsz;
        }
        long replysz = Long.parseLong(remainder);
        if (replysz >= pbsz) {
            return pbsz;
        }
        return replysz;
    }

    public void execPROT(String prot) throws IOException {
        if (prot == null) {
            prot = DEFAULT_PROT;
        }
        if (!checkPROTValue(prot)) {
            throw new IllegalArgumentException();
        }
        if (200 != sendCommand(CMD_PROT, prot)) {
            throw new SSLException(getReplyString());
        }
        if (DEFAULT_PROT.equals(prot)) {
            setSocketFactory(null);
            setServerSocketFactory(null);
        } else {
            setSocketFactory(new FTPSSocketFactory(this.context));
            setServerSocketFactory(new FTPSServerSocketFactory(this.context));
            initSslContext();
        }
    }

    private boolean checkPROTValue(String prot) {
        for (String element : PROT_COMMAND_VALUE) {
            if (element.equals(prot)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.commons.net.ftp.FTP
    public int sendCommand(String command, String args) throws IOException {
        int repCode = super.sendCommand(command, args);
        if (CMD_CCC.equals(command)) {
            if (200 == repCode) {
                this._socket_.close();
                this._socket_ = this.plainSocket;
                this._controlInput_ = new BufferedReader(new InputStreamReader(this._socket_.getInputStream(), getControlEncoding()));
                this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(this._socket_.getOutputStream(), getControlEncoding()));
            } else {
                throw new SSLException(getReplyString());
            }
        }
        return repCode;
    }

    @Override // org.apache.commons.net.ftp.FTPClient
    @Deprecated
    protected Socket _openDataConnection_(int command, String arg) throws IOException {
        return _openDataConnection_(FTPCommand.getCommand(command), arg);
    }

    @Override // org.apache.commons.net.ftp.FTPClient
    protected Socket _openDataConnection_(String command, String arg) throws IOException {
        Socket socket = super._openDataConnection_(command, arg);
        _prepareDataSocket_(socket);
        if (socket instanceof SSLSocket) {
            SSLSocket sslSocket = (SSLSocket) socket;
            sslSocket.setUseClientMode(this.isClientMode);
            sslSocket.setEnableSessionCreation(this.isCreation);
            if (!this.isClientMode) {
                sslSocket.setNeedClientAuth(this.isNeedClientAuth);
                sslSocket.setWantClientAuth(this.isWantClientAuth);
            }
            String[] strArr = this.suites;
            if (strArr != null) {
                sslSocket.setEnabledCipherSuites(strArr);
            }
            String[] strArr2 = this.protocols;
            if (strArr2 != null) {
                sslSocket.setEnabledProtocols(strArr2);
            }
            sslSocket.startHandshake();
        }
        return socket;
    }

    protected void _prepareDataSocket_(Socket socket) throws IOException {
    }

    public TrustManager getTrustManager() {
        return this.trustManager;
    }

    public void setTrustManager(TrustManager trustManager) {
        this.trustManager = trustManager;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.hostnameVerifier;
    }

    public void setHostnameVerifier(HostnameVerifier newHostnameVerifier) {
        this.hostnameVerifier = newHostnameVerifier;
    }

    public boolean isEndpointCheckingEnabled() {
        return this.tlsEndpointChecking;
    }

    public void setEndpointCheckingEnabled(boolean enable) {
        this.tlsEndpointChecking = enable;
    }

    @Override // org.apache.commons.net.ftp.FTPClient, org.apache.commons.net.ftp.FTP, org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        Socket socket = this.plainSocket;
        if (socket != null) {
            socket.close();
        }
        setSocketFactory(null);
        setServerSocketFactory(null);
    }

    public int execAUTH(String mechanism) throws IOException {
        return sendCommand(CMD_AUTH, mechanism);
    }

    public int execADAT(byte[] data) throws IOException {
        if (data != null) {
            return sendCommand(CMD_ADAT, Base64.encodeBase64StringUnChunked(data));
        }
        return sendCommand(CMD_ADAT);
    }

    public int execCCC() throws IOException {
        int repCode = sendCommand(CMD_CCC);
        return repCode;
    }

    public int execMIC(byte[] data) throws IOException {
        return data != null ? sendCommand(CMD_MIC, Base64.encodeBase64StringUnChunked(data)) : sendCommand(CMD_MIC, "");
    }

    public int execCONF(byte[] data) throws IOException {
        return data != null ? sendCommand(CMD_CONF, Base64.encodeBase64StringUnChunked(data)) : sendCommand(CMD_CONF, "");
    }

    public int execENC(byte[] data) throws IOException {
        return data != null ? sendCommand(CMD_ENC, Base64.encodeBase64StringUnChunked(data)) : sendCommand(CMD_ENC, "");
    }

    public byte[] parseADATReply(String reply) {
        if (reply == null) {
            return null;
        }
        return Base64.decodeBase64(extractPrefixedData("ADAT=", reply));
    }

    private String extractPrefixedData(String prefix, String reply) {
        int idx = reply.indexOf(prefix);
        if (idx == -1) {
            return null;
        }
        return reply.substring(prefix.length() + idx).trim();
    }

    private SSLSocket createSSLSocket(Socket socket) throws IOException {
        if (socket != null) {
            SSLSocketFactory f = this.context.getSocketFactory();
            return (SSLSocket) f.createSocket(socket, this._hostname_, socket.getPort(), false);
        }
        return null;
    }
}
