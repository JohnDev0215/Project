package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Inet6Address;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.util.Base64;

/* JADX INFO: loaded from: classes3.dex */
public class FTPHTTPClient extends FTPClient {
    private static final byte[] CRLF = {13, 10};
    private final Base64 base64;
    private final Charset charset;
    private final String proxyHost;
    private final String proxyPassword;
    private final int proxyPort;
    private final String proxyUsername;
    private String tunnelHost;

    public FTPHTTPClient(String proxyHost, int proxyPort, String proxyUser, String proxyPass, Charset encoding) {
        this.base64 = new Base64();
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUsername = proxyUser;
        this.proxyPassword = proxyPass;
        this.tunnelHost = null;
        this.charset = encoding;
    }

    public FTPHTTPClient(String proxyHost, int proxyPort, String proxyUser, String proxyPass) {
        this(proxyHost, proxyPort, proxyUser, proxyPass, Charset.forName("UTF-8"));
    }

    public FTPHTTPClient(String proxyHost, int proxyPort) {
        this(proxyHost, proxyPort, null, null);
    }

    public FTPHTTPClient(String proxyHost, int proxyPort, Charset encoding) {
        this(proxyHost, proxyPort, null, null, encoding);
    }

    @Override // org.apache.commons.net.ftp.FTPClient
    @Deprecated
    protected Socket _openDataConnection_(int command, String arg) throws IOException {
        return super._openDataConnection_(command, arg);
    }

    @Override // org.apache.commons.net.ftp.FTPClient
    protected Socket _openDataConnection_(String command, String arg) throws IOException {
        String passiveHost;
        if (getDataConnectionMode() != 2) {
            throw new IllegalStateException("Only passive connection mode supported");
        }
        boolean isInet6Address = getRemoteAddress() instanceof Inet6Address;
        boolean attemptEPSV = isUseEPSVwithIPv4() || isInet6Address;
        if (attemptEPSV && epsv() == 229) {
            _parseExtendedPassiveModeReply(this._replyLines.get(0));
            passiveHost = this.tunnelHost;
        } else {
            if (isInet6Address || pasv() != 227) {
                return null;
            }
            _parsePassiveModeReply(this._replyLines.get(0));
            passiveHost = getPassiveHost();
        }
        Socket socket = this._socketFactory_.createSocket(this.proxyHost, this.proxyPort);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        tunnelHandshake(passiveHost, getPassivePort(), is, os);
        if (getRestartOffset() > 0 && !restart(getRestartOffset())) {
            socket.close();
            return null;
        }
        if (!FTPReply.isPositivePreliminary(sendCommand(command, arg))) {
            socket.close();
            return null;
        }
        return socket;
    }

    @Override // org.apache.commons.net.SocketClient
    public void connect(String host, int port) throws IOException {
        this._socket_ = this._socketFactory_.createSocket(this.proxyHost, this.proxyPort);
        this._input_ = this._socket_.getInputStream();
        this._output_ = this._socket_.getOutputStream();
        try {
            Reader socketIsReader = tunnelHandshake(host, port, this._input_, this._output_);
            super._connectAction_(socketIsReader);
        } catch (Exception e) {
            IOException ioe = new IOException("Could not connect to " + host + " using port " + port);
            ioe.initCause(e);
            throw ioe;
        }
    }

    private BufferedReader tunnelHandshake(String host, int port, InputStream input, OutputStream output) throws IOException {
        String connectString = "CONNECT " + host + ":" + port + " HTTP/1.1";
        String hostString = "Host: " + host + ":" + port;
        this.tunnelHost = host;
        output.write(connectString.getBytes(this.charset));
        output.write(CRLF);
        output.write(hostString.getBytes(this.charset));
        output.write(CRLF);
        if (this.proxyUsername != null && this.proxyPassword != null) {
            String auth = this.proxyUsername + ":" + this.proxyPassword;
            String header = "Proxy-Authorization: Basic " + this.base64.encodeToString(auth.getBytes(this.charset));
            output.write(header.getBytes(this.charset));
        }
        output.write(CRLF);
        List<String> response = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, getCharset()));
        for (String line = reader.readLine(); line != null && line.length() > 0; line = reader.readLine()) {
            response.add(line);
        }
        int size = response.size();
        if (size == 0) {
            throw new IOException("No response from proxy");
        }
        String resp = response.get(0);
        if (resp.startsWith("HTTP/") && resp.length() >= 12) {
            String code = resp.substring(9, 12);
            if (!"200".equals(code)) {
                StringBuilder msg = new StringBuilder();
                msg.append("HTTPTunnelConnector: connection failed\r\n");
                msg.append("Response received from the proxy:\r\n");
                for (String line2 : response) {
                    msg.append(line2);
                    msg.append(SocketClient.NETASCII_EOL);
                }
                throw new IOException(msg.toString());
            }
            return reader;
        }
        throw new IOException("Invalid response from proxy: " + resp);
    }
}
