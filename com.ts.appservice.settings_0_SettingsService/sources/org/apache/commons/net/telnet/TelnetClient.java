package org.apache.commons.net.telnet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class TelnetClient extends Telnet {
    private static final int DEFAULT_MAX_SUBNEGOTIATION_LENGTH = 512;
    private InputStream input;
    private TelnetInputListener inputListener;
    final int maxSubnegotiationLength;
    private OutputStream output;
    protected boolean readerThread;

    public TelnetClient() {
        this("VT100", 512);
    }

    public TelnetClient(String termtype) {
        this(termtype, 512);
    }

    public TelnetClient(int maxSubnegotiationLength) {
        this("VT100", maxSubnegotiationLength);
    }

    public TelnetClient(String termtype, int maxSubnegotiationLength) {
        super(termtype);
        this.readerThread = true;
        this.input = null;
        this.output = null;
        this.maxSubnegotiationLength = maxSubnegotiationLength;
    }

    void flushOutputStream() throws IOException {
        if (this._output_ == null) {
            throw new IOException("Stream closed");
        }
        this._output_.flush();
    }

    void closeOutputStream() throws IOException {
        if (this._output_ == null) {
            return;
        }
        try {
            this._output_.close();
        } finally {
            this._output_ = null;
        }
    }

    @Override // org.apache.commons.net.telnet.Telnet, org.apache.commons.net.SocketClient
    protected void _connectAction_() throws IOException {
        super._connectAction_();
        TelnetInputStream tmp = new TelnetInputStream(this._input_, this, this.readerThread);
        if (this.readerThread) {
            tmp._start();
        }
        this.input = new BufferedInputStream(tmp);
        this.output = new TelnetOutputStream(this);
    }

    @Override // org.apache.commons.net.SocketClient
    public void disconnect() throws IOException {
        try {
            if (this.input != null) {
                this.input.close();
            }
            if (this.output != null) {
                this.output.close();
            }
        } finally {
            this.output = null;
            this.input = null;
            super.disconnect();
        }
    }

    public OutputStream getOutputStream() {
        return this.output;
    }

    public InputStream getInputStream() {
        return this.input;
    }

    public boolean getLocalOptionState(int option) {
        return stateIsWill(option) && requestedWill(option);
    }

    public boolean getRemoteOptionState(int option) {
        return stateIsDo(option) && requestedDo(option);
    }

    public boolean sendAYT(long timeout) throws InterruptedException, IOException, IllegalArgumentException {
        return _sendAYT(timeout);
    }

    public void sendSubnegotiation(int[] message) throws IOException, IllegalArgumentException {
        if (message.length < 1) {
            throw new IllegalArgumentException("zero length message");
        }
        _sendSubnegotiation(message);
    }

    public void sendCommand(byte command) throws IOException, IllegalArgumentException {
        _sendCommand(command);
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void addOptionHandler(TelnetOptionHandler opthand) throws IOException, InvalidTelnetOptionException {
        super.addOptionHandler(opthand);
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void deleteOptionHandler(int optcode) throws IOException, InvalidTelnetOptionException {
        super.deleteOptionHandler(optcode);
    }

    public void registerSpyStream(OutputStream spystream) {
        super._registerSpyStream(spystream);
    }

    public void stopSpyStream() {
        super._stopSpyStream();
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void registerNotifHandler(TelnetNotificationHandler notifhand) {
        super.registerNotifHandler(notifhand);
    }

    @Override // org.apache.commons.net.telnet.Telnet
    public void unregisterNotifHandler() {
        super.unregisterNotifHandler();
    }

    public void setReaderThread(boolean flag) {
        this.readerThread = flag;
    }

    public boolean getReaderThread() {
        return this.readerThread;
    }

    public synchronized void registerInputListener(TelnetInputListener listener) {
        this.inputListener = listener;
    }

    public synchronized void unregisterInputListener() {
        this.inputListener = null;
    }

    void notifyInputListener() {
        TelnetInputListener listener;
        synchronized (this) {
            listener = this.inputListener;
        }
        if (listener != null) {
            listener.telnetInputAvailable();
        }
    }
}
