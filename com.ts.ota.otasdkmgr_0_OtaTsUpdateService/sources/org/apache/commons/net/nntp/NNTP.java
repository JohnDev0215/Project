package org.apache.commons.net.nntp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

/* JADX INFO: loaded from: classes.dex */
public class NNTP extends SocketClient {
    private static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    public static final int DEFAULT_PORT = 119;
    protected ProtocolCommandSupport _commandSupport_;
    boolean _isAllowedToPost;
    protected BufferedReader _reader_;
    protected BufferedWriter _writer_;
    private int replyCode;
    private String replyString;

    public NNTP() {
        setDefaultPort(DEFAULT_PORT);
        this.replyString = null;
        this._reader_ = null;
        this._writer_ = null;
        this._isAllowedToPost = false;
        this._commandSupport_ = new ProtocolCommandSupport(this);
    }

    @Override // org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        this._reader_ = new CRLFLineReader(new InputStreamReader(this._input_, DEFAULT_ENCODING));
        this._writer_ = new BufferedWriter(new OutputStreamWriter(this._output_, DEFAULT_ENCODING));
        getReply();
        this._isAllowedToPost = this.replyCode == 200;
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._reader_ = null;
        this._writer_ = null;
        this.replyString = null;
        this._isAllowedToPost = false;
    }

    public boolean isAllowedToPost() {
        return this._isAllowedToPost;
    }

    public int sendCommand(String command, String args) throws IOException {
        StringBuilder __commandBuffer = new StringBuilder();
        __commandBuffer.append(command);
        if (args != null) {
            __commandBuffer.append(' ');
            __commandBuffer.append(args);
        }
        __commandBuffer.append(SocketClient.NETASCII_EOL);
        BufferedWriter bufferedWriter = this._writer_;
        String message = __commandBuffer.toString();
        bufferedWriter.write(message);
        this._writer_.flush();
        fireCommandSent(command, message);
        return getReply();
    }

    public int sendCommand(int command, String args) throws IOException {
        return sendCommand(NNTPCommand.getCommand(command), args);
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
        String line = this._reader_.readLine();
        this.replyString = line;
        if (line == null) {
            throw new NNTPConnectionClosedException("Connection closed without indication.");
        }
        if (line.length() < 3) {
            throw new MalformedServerReplyException("Truncated server reply: " + this.replyString);
        }
        try {
            int i = Integer.parseInt(this.replyString.substring(0, 3));
            this.replyCode = i;
            fireReplyReceived(i, this.replyString + SocketClient.NETASCII_EOL);
            int i2 = this.replyCode;
            if (i2 == 400) {
                throw new NNTPConnectionClosedException("NNTP response 400 received.  Server closed connection.");
            }
            return i2;
        } catch (NumberFormatException e) {
            throw new MalformedServerReplyException("Could not parse response code.\nServer Reply: " + this.replyString);
        }
    }

    public String getReplyString() {
        return this.replyString;
    }

    public int article(String messageId) throws IOException {
        return sendCommand(0, messageId);
    }

    public int article(long articleNumber) throws IOException {
        return sendCommand(0, Long.toString(articleNumber));
    }

    public int article() throws IOException {
        return sendCommand(0);
    }

    public int body(String messageId) throws IOException {
        return sendCommand(1, messageId);
    }

    public int body(long articleNumber) throws IOException {
        return sendCommand(1, Long.toString(articleNumber));
    }

    public int body() throws IOException {
        return sendCommand(1);
    }

    public int head(String messageId) throws IOException {
        return sendCommand(3, messageId);
    }

    public int head(long articleNumber) throws IOException {
        return sendCommand(3, Long.toString(articleNumber));
    }

    public int head() throws IOException {
        return sendCommand(3);
    }

    public int stat(String messageId) throws IOException {
        return sendCommand(14, messageId);
    }

    public int stat(long articleNumber) throws IOException {
        return sendCommand(14, Long.toString(articleNumber));
    }

    public int stat() throws IOException {
        return sendCommand(14);
    }

    public int group(String newsgroup) throws IOException {
        return sendCommand(2, newsgroup);
    }

    public int help() throws IOException {
        return sendCommand(4);
    }

    public int ihave(String messageId) throws IOException {
        return sendCommand(5, messageId);
    }

    public int last() throws IOException {
        return sendCommand(6);
    }

    public int list() throws IOException {
        return sendCommand(7);
    }

    public int next() throws IOException {
        return sendCommand(10);
    }

    public int newgroups(String date, String time, boolean GMT, String distributions) throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(date);
        buffer.append(' ');
        buffer.append(time);
        if (GMT) {
            buffer.append(' ');
            buffer.append("GMT");
        }
        if (distributions != null) {
            buffer.append(" <");
            buffer.append(distributions);
            buffer.append('>');
        }
        return sendCommand(8, buffer.toString());
    }

    public int newnews(String newsgroups, String date, String time, boolean GMT, String distributions) throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(newsgroups);
        buffer.append(' ');
        buffer.append(date);
        buffer.append(' ');
        buffer.append(time);
        if (GMT) {
            buffer.append(' ');
            buffer.append("GMT");
        }
        if (distributions != null) {
            buffer.append(" <");
            buffer.append(distributions);
            buffer.append('>');
        }
        return sendCommand(9, buffer.toString());
    }

    public int post() throws IOException {
        return sendCommand(11);
    }

    public int quit() throws IOException {
        return sendCommand(12);
    }

    public int authinfoUser(String username) throws IOException {
        String userParameter = "USER " + username;
        return sendCommand(15, userParameter);
    }

    public int authinfoPass(String password) throws IOException {
        String passParameter = "PASS " + password;
        return sendCommand(15, passParameter);
    }

    public int xover(String selectedArticles) throws IOException {
        return sendCommand(16, selectedArticles);
    }

    public int xhdr(String header, String selectedArticles) throws IOException {
        return sendCommand(17, header + " " + selectedArticles);
    }

    public int listActive(String wildmat) throws IOException {
        return sendCommand(7, "ACTIVE " + wildmat);
    }

    @Deprecated
    public int article(int a) throws IOException {
        return article(a);
    }

    @Deprecated
    public int body(int a) throws IOException {
        return body(a);
    }

    @Deprecated
    public int head(int a) throws IOException {
        return head(a);
    }

    @Deprecated
    public int stat(int a) throws IOException {
        return stat(a);
    }

    @Override // org.apache.commons.net.SocketClient
    protected ProtocolCommandSupport getCommandSupport() {
        return this._commandSupport_;
    }
}
