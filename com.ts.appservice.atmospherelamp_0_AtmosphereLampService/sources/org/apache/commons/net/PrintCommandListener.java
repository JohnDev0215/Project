package org.apache.commons.net;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public class PrintCommandListener implements ProtocolCommandListener {
    private final boolean directionMarker;
    private final char eolMarker;
    private final boolean nologin;
    private final PrintWriter writer;

    public PrintCommandListener(PrintStream stream) {
        this(new PrintWriter(stream));
    }

    public PrintCommandListener(PrintStream stream, boolean suppressLogin) {
        this(new PrintWriter(stream), suppressLogin);
    }

    public PrintCommandListener(PrintStream stream, boolean suppressLogin, char eolMarker) {
        this(new PrintWriter(stream), suppressLogin, eolMarker);
    }

    public PrintCommandListener(PrintStream stream, boolean suppressLogin, char eolMarker, boolean showDirection) {
        this(new PrintWriter(stream), suppressLogin, eolMarker, showDirection);
    }

    public PrintCommandListener(PrintWriter writer) {
        this(writer, false);
    }

    public PrintCommandListener(PrintWriter writer, boolean suppressLogin) {
        this(writer, suppressLogin, (char) 0);
    }

    public PrintCommandListener(PrintWriter writer, boolean suppressLogin, char eolMarker) {
        this(writer, suppressLogin, eolMarker, false);
    }

    public PrintCommandListener(PrintWriter writer, boolean suppressLogin, char eolMarker, boolean showDirection) {
        this.writer = writer;
        this.nologin = suppressLogin;
        this.eolMarker = eolMarker;
        this.directionMarker = showDirection;
    }

    @Override // org.apache.commons.net.ProtocolCommandListener
    public void protocolCommandSent(ProtocolCommandEvent event) {
        if (this.directionMarker) {
            this.writer.print("> ");
        }
        if (this.nologin) {
            String cmd = event.getCommand();
            if ("PASS".equalsIgnoreCase(cmd) || "USER".equalsIgnoreCase(cmd)) {
                this.writer.print(cmd);
                this.writer.println(" *******");
            } else if ("LOGIN".equalsIgnoreCase(cmd)) {
                String msg = event.getMessage();
                this.writer.print(msg.substring(0, msg.indexOf("LOGIN") + "LOGIN".length()));
                this.writer.println(" *******");
            } else {
                this.writer.print(getPrintableString(event.getMessage()));
            }
        } else {
            this.writer.print(getPrintableString(event.getMessage()));
        }
        this.writer.flush();
    }

    private String getPrintableString(String msg) {
        int pos;
        if (this.eolMarker != 0 && (pos = msg.indexOf(SocketClient.NETASCII_EOL)) > 0) {
            return msg.substring(0, pos) + this.eolMarker + msg.substring(pos);
        }
        return msg;
    }

    @Override // org.apache.commons.net.ProtocolCommandListener
    public void protocolReplyReceived(ProtocolCommandEvent event) {
        if (this.directionMarker) {
            this.writer.print("< ");
        }
        this.writer.print(event.getMessage());
        this.writer.flush();
    }
}
