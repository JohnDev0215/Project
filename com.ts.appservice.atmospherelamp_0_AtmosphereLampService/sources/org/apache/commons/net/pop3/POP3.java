package org.apache.commons.net.pop3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

/* JADX INFO: loaded from: classes3.dex */
public class POP3 extends SocketClient {
    public static final int AUTHORIZATION_STATE = 0;
    public static final int DEFAULT_PORT = 110;
    public static final int DISCONNECTED_STATE = -1;
    public static final int TRANSACTION_STATE = 1;
    public static final int UPDATE_STATE = 2;
    static final Charset _DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    static final String _ERROR = "-ERR";
    static final String _OK = "+OK";
    static final String _OK_INT = "+ ";
    protected ProtocolCommandSupport _commandSupport_;
    String lastReplyLine;
    private int popState;
    BufferedReader reader;
    int replyCode;
    List<String> replyLines;
    BufferedWriter writer;

    public POP3() {
        setDefaultPort(110);
        this.popState = -1;
        this.reader = null;
        this.writer = null;
        this.replyLines = new ArrayList();
        this._commandSupport_ = new ProtocolCommandSupport(this);
    }

    private void getReply() throws IOException {
        this.replyLines.clear();
        String line = this.reader.readLine();
        if (line == null) {
            throw new EOFException("Connection closed without indication.");
        }
        if (line.startsWith(_OK)) {
            this.replyCode = 0;
        } else if (line.startsWith(_ERROR)) {
            this.replyCode = 1;
        } else if (line.startsWith(_OK_INT)) {
            this.replyCode = 2;
        } else {
            throw new MalformedServerReplyException("Received invalid POP3 protocol response from server." + line);
        }
        this.replyLines.add(line);
        this.lastReplyLine = line;
        fireReplyReceived(this.replyCode, getReplyString());
    }

    @Override // org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        this.reader = new CRLFLineReader(new InputStreamReader(this._input_, _DEFAULT_ENCODING));
        this.writer = new BufferedWriter(new OutputStreamWriter(this._output_, _DEFAULT_ENCODING));
        getReply();
        setState(0);
    }

    public void setState(int state) {
        this.popState = state;
    }

    public int getState() {
        return this.popState;
    }

    public void getAdditionalReply() throws IOException {
        String line = this.reader.readLine();
        while (line != null) {
            this.replyLines.add(line);
            if (!line.equals(".")) {
                line = this.reader.readLine();
            } else {
                return;
            }
        }
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this.reader = null;
        this.writer = null;
        this.lastReplyLine = null;
        this.replyLines.clear();
        setState(-1);
    }

    public int sendCommand(String command, String args) throws IOException {
        if (this.writer == null) {
            throw new IllegalStateException("Socket is not connected");
        }
        StringBuilder __commandBuffer = new StringBuilder();
        __commandBuffer.append(command);
        if (args != null) {
            __commandBuffer.append(' ');
            __commandBuffer.append(args);
        }
        __commandBuffer.append(SocketClient.NETASCII_EOL);
        String message = __commandBuffer.toString();
        this.writer.write(message);
        this.writer.flush();
        fireCommandSent(command, message);
        getReply();
        return this.replyCode;
    }

    public int sendCommand(String command) throws IOException {
        return sendCommand(command, (String) null);
    }

    public int sendCommand(int command, String args) throws IOException {
        return sendCommand(POP3Command.commands[command], args);
    }

    public int sendCommand(int command) throws IOException {
        return sendCommand(POP3Command.commands[command], (String) null);
    }

    public String[] getReplyStrings() {
        List<String> list = this.replyLines;
        return (String[]) list.toArray(new String[list.size()]);
    }

    public String getReplyString() {
        StringBuilder buffer = new StringBuilder(256);
        for (String entry : this.replyLines) {
            buffer.append(entry);
            buffer.append(SocketClient.NETASCII_EOL);
        }
        return buffer.toString();
    }

    public void removeProtocolCommandistener(ProtocolCommandListener listener) {
        removeProtocolCommandListener(listener);
    }

    @Override // org.apache.commons.net.SocketClient
    protected ProtocolCommandSupport getCommandSupport() {
        return this._commandSupport_;
    }
}
