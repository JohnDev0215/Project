package org.apache.commons.net.smtp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

/* JADX INFO: loaded from: classes.dex */
public class SMTP extends SocketClient {
    private static final String DEFAULT_ENCODING = "ISO-8859-1";
    public static final int DEFAULT_PORT = 25;
    protected ProtocolCommandSupport _commandSupport_;
    protected final String encoding;
    private boolean newReplyString;
    BufferedReader reader;
    private int replyCode;
    private final ArrayList<String> replyLines;
    private String replyString;
    BufferedWriter writer;

    public SMTP() {
        this("ISO-8859-1");
    }

    public SMTP(String encoding) {
        setDefaultPort(25);
        this.replyLines = new ArrayList<>();
        this.newReplyString = false;
        this.replyString = null;
        this._commandSupport_ = new ProtocolCommandSupport(this);
        this.encoding = encoding;
    }

    private int __sendCommand(String command, String args, boolean includeSpace) throws IOException {
        StringBuilder __commandBuffer = new StringBuilder();
        __commandBuffer.append(command);
        if (args != null) {
            if (includeSpace) {
                __commandBuffer.append(' ');
            }
            __commandBuffer.append(args);
        }
        __commandBuffer.append(SocketClient.NETASCII_EOL);
        String message = __commandBuffer.toString();
        this.writer.write(message);
        this.writer.flush();
        fireCommandSent(command, message);
        __getReply();
        return this.replyCode;
    }

    private int __sendCommand(int command, String args, boolean includeSpace) throws IOException {
        return __sendCommand(SMTPCommand.getCommand(command), args, includeSpace);
    }

    private void __getReply() throws IOException {
        this.newReplyString = true;
        this.replyLines.clear();
        String line = this.reader.readLine();
        if (line == null) {
            throw new SMTPConnectionClosedException("Connection closed without indication.");
        }
        int length = line.length();
        if (length >= 3) {
            try {
                String code = line.substring(0, 3);
                this.replyCode = Integer.parseInt(code);
                this.replyLines.add(line);
                if (length > 3 && line.charAt(3) == '-') {
                    while (true) {
                        String line2 = this.reader.readLine();
                        if (line2 == null) {
                            throw new SMTPConnectionClosedException("Connection closed without indication.");
                        }
                        this.replyLines.add(line2);
                        if (line2.length() >= 4 && line2.charAt(3) != '-' && Character.isDigit(line2.charAt(0))) {
                            break;
                        }
                    }
                }
                fireReplyReceived(this.replyCode, getReplyString());
                if (this.replyCode == 421) {
                    throw new SMTPConnectionClosedException("SMTP response 421 received.  Server closed connection.");
                }
                return;
            } catch (NumberFormatException e) {
                throw new MalformedServerReplyException("Could not parse response code.\nServer Reply: " + line);
            }
        }
        throw new MalformedServerReplyException("Truncated server reply: " + line);
    }

    @Override // org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        this.reader = new CRLFLineReader(new InputStreamReader(this._input_, this.encoding));
        this.writer = new BufferedWriter(new OutputStreamWriter(this._output_, this.encoding));
        __getReply();
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this.reader = null;
        this.writer = null;
        this.replyString = null;
        this.replyLines.clear();
        this.newReplyString = false;
    }

    public int sendCommand(String command, String args) throws IOException {
        return __sendCommand(command, args, true);
    }

    public int sendCommand(int command, String args) throws IOException {
        return sendCommand(SMTPCommand.getCommand(command), args);
    }

    public int sendCommand(String command) throws IOException {
        return sendCommand(command, (String) null);
    }

    public int sendCommand(int command) throws IOException {
        return sendCommand(command, (String) null);
    }

    public int getReplyCode() {
        return this.replyCode;
    }

    public int getReply() throws IOException {
        __getReply();
        return this.replyCode;
    }

    public String[] getReplyStrings() {
        ArrayList<String> arrayList = this.replyLines;
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String getReplyString() {
        if (!this.newReplyString) {
            return this.replyString;
        }
        StringBuilder buffer = new StringBuilder();
        for (String line : this.replyLines) {
            buffer.append(line);
            buffer.append(SocketClient.NETASCII_EOL);
        }
        this.newReplyString = false;
        String string = buffer.toString();
        this.replyString = string;
        return string;
    }

    public int helo(String hostname) throws IOException {
        return sendCommand(0, hostname);
    }

    public int mail(String reversePath) throws IOException {
        return __sendCommand(1, reversePath, false);
    }

    public int rcpt(String forwardPath) throws IOException {
        return __sendCommand(2, forwardPath, false);
    }

    public int data() throws IOException {
        return sendCommand(3);
    }

    public int send(String reversePath) throws IOException {
        return sendCommand(4, reversePath);
    }

    public int soml(String reversePath) throws IOException {
        return sendCommand(5, reversePath);
    }

    public int saml(String reversePath) throws IOException {
        return sendCommand(6, reversePath);
    }

    public int rset() throws IOException {
        return sendCommand(7);
    }

    public int vrfy(String user) throws IOException {
        return sendCommand(8, user);
    }

    public int expn(String name) throws IOException {
        return sendCommand(9, name);
    }

    public int help() throws IOException {
        return sendCommand(10);
    }

    public int help(String command) throws IOException {
        return sendCommand(10, command);
    }

    public int noop() throws IOException {
        return sendCommand(11);
    }

    public int turn() throws IOException {
        return sendCommand(12);
    }

    public int quit() throws IOException {
        return sendCommand(13);
    }

    public void removeProtocolCommandistener(ProtocolCommandListener listener) {
        removeProtocolCommandListener(listener);
    }

    @Override // org.apache.commons.net.SocketClient
    protected ProtocolCommandSupport getCommandSupport() {
        return this._commandSupport_;
    }
}
