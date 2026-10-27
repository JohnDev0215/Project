package org.apache.commons.net.imap;

import com.engine.tbox.utils.AESUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;
import org.apache.commons.net.util.NetConstants;

/* JADX INFO: loaded from: classes.dex */
public class IMAP extends SocketClient {
    public static final int DEFAULT_PORT = 143;
    public static final IMAPChunkListener TRUE_CHUNK_LISTENER = new IMAPChunkListener() { // from class: org.apache.commons.net.imap.IMAP.1
        @Override // org.apache.commons.net.imap.IMAP.IMAPChunkListener
        public boolean chunkReceived(IMAP imap) {
            return true;
        }
    };
    protected static final String __DEFAULT_ENCODING = "ISO-8859-1";
    protected BufferedWriter __writer;
    protected BufferedReader _reader;
    private volatile IMAPChunkListener chunkListener;
    private final char[] initialID = {'A', 'A', 'A', 'A'};
    private int replyCode;
    private final List<String> replyLines;
    private IMAPState state;

    public interface IMAPChunkListener {
        boolean chunkReceived(IMAP imap);
    }

    public enum IMAPState {
        DISCONNECTED_STATE,
        NOT_AUTH_STATE,
        AUTH_STATE,
        LOGOUT_STATE
    }

    public IMAP() {
        setDefaultPort(DEFAULT_PORT);
        this.state = IMAPState.DISCONNECTED_STATE;
        this._reader = null;
        this.__writer = null;
        this.replyLines = new ArrayList();
        createCommandSupport();
    }

    private void getReply() throws IOException {
        getReply(true);
    }

    private void getReply(boolean wantTag) throws IOException {
        IMAPChunkListener il;
        this.replyLines.clear();
        String line = this._reader.readLine();
        if (line == null) {
            throw new EOFException("Connection closed without indication.");
        }
        this.replyLines.add(line);
        if (wantTag) {
            while (IMAPReply.isUntagged(line)) {
                int literalCount = IMAPReply.literalCount(line);
                boolean isMultiLine = literalCount >= 0;
                while (literalCount >= 0) {
                    String line2 = this._reader.readLine();
                    if (line2 == null) {
                        throw new EOFException("Connection closed without indication.");
                    }
                    this.replyLines.add(line2);
                    literalCount -= line2.length() + 2;
                }
                if (isMultiLine && (il = this.chunkListener) != null) {
                    boolean clear = il.chunkReceived(this);
                    if (clear) {
                        fireReplyReceived(3, getReplyString());
                        this.replyLines.clear();
                    }
                }
                line = this._reader.readLine();
                if (line == null) {
                    throw new EOFException("Connection closed without indication.");
                }
                this.replyLines.add(line);
            }
            this.replyCode = IMAPReply.getReplyCode(line);
        } else {
            this.replyCode = IMAPReply.getUntaggedReplyCode(line);
        }
        fireReplyReceived(this.replyCode, getReplyString());
    }

    @Override // org.apache.commons.net.SocketClient
    protected void fireReplyReceived(int replyCode, String ignored) {
        if (getCommandSupport().getListenerCount() > 0) {
            getCommandSupport().fireReplyReceived(replyCode, getReplyString());
        }
    }

    @Override // org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        this._reader = new CRLFLineReader(new InputStreamReader(this._input_, "ISO-8859-1"));
        this.__writer = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
        int tmo = getSoTimeout();
        if (tmo <= 0) {
            setSoTimeout(this.connectTimeout);
        }
        getReply(false);
        if (tmo <= 0) {
            setSoTimeout(tmo);
        }
        setState(IMAPState.NOT_AUTH_STATE);
    }

    protected void setState(IMAPState state) {
        this.state = state;
    }

    public IMAPState getState() {
        return this.state;
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        super.disconnect();
        this._reader = null;
        this.__writer = null;
        this.replyLines.clear();
        setState(IMAPState.DISCONNECTED_STATE);
    }

    private int sendCommandWithID(String commandID, String command, String args) throws IOException {
        StringBuilder __commandBuffer = new StringBuilder();
        if (commandID != null) {
            __commandBuffer.append(commandID);
            __commandBuffer.append(' ');
        }
        __commandBuffer.append(command);
        if (args != null) {
            __commandBuffer.append(' ');
            __commandBuffer.append(args);
        }
        __commandBuffer.append(SocketClient.NETASCII_EOL);
        String message = __commandBuffer.toString();
        this.__writer.write(message);
        this.__writer.flush();
        fireCommandSent(command, message);
        getReply();
        return this.replyCode;
    }

    public int sendCommand(String command, String args) throws IOException {
        return sendCommandWithID(generateCommandID(), command, args);
    }

    public int sendCommand(String command) throws IOException {
        return sendCommand(command, (String) null);
    }

    public int sendCommand(IMAPCommand command, String args) throws IOException {
        return sendCommand(command.getIMAPCommand(), args);
    }

    public boolean doCommand(IMAPCommand command, String args) throws IOException {
        return IMAPReply.isSuccess(sendCommand(command, args));
    }

    public int sendCommand(IMAPCommand command) throws IOException {
        return sendCommand(command, (String) null);
    }

    public boolean doCommand(IMAPCommand command) throws IOException {
        return IMAPReply.isSuccess(sendCommand(command));
    }

    public int sendData(String command) throws IOException {
        return sendCommandWithID(null, command, null);
    }

    public String[] getReplyStrings() {
        return (String[]) this.replyLines.toArray(NetConstants.EMPTY_STRING_ARRAY);
    }

    public String getReplyString() {
        StringBuilder buffer = new StringBuilder(AESUtils.ECB_256_LENGTH);
        for (String s : this.replyLines) {
            buffer.append(s);
            buffer.append(SocketClient.NETASCII_EOL);
        }
        return buffer.toString();
    }

    public void setChunkListener(IMAPChunkListener listener) {
        this.chunkListener = listener;
    }

    protected String generateCommandID() {
        String res = new String(this.initialID);
        boolean carry = true;
        for (int i = this.initialID.length - 1; carry && i >= 0; i--) {
            char[] cArr = this.initialID;
            if (cArr[i] == 'Z') {
                cArr[i] = 'A';
            } else {
                cArr[i] = (char) (cArr[i] + 1);
                carry = false;
            }
        }
        return res;
    }

    static String quoteMailboxName(String input) {
        if (input == null) {
            return null;
        }
        if (input.isEmpty()) {
            return "\"\"";
        }
        if ((input.length() <= 1 || !input.startsWith("\"") || !input.endsWith("\"")) && input.contains(" ")) {
            return "\"" + input.replaceAll("([\\\\\"])", "\\\\$1") + "\"";
        }
        return input;
    }
}
