package org.apache.tools.mail;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.Manifest;

/* JADX INFO: loaded from: classes.dex */
public class MailMessage {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 25;
    private static final int OK_DATA = 354;
    private static final int OK_DOT = 250;
    private static final int OK_FROM = 250;
    private static final int OK_HELO = 250;
    private static final int OK_QUIT = 221;
    private static final int OK_RCPT_1 = 250;
    private static final int OK_RCPT_2 = 251;
    private static final int OK_READY = 220;
    private Vector cc;
    private String from;
    private Vector headersKeys;
    private Vector headersValues;
    private String host;
    private SmtpResponseReader in;
    private MailPrintStream out;
    private int port;
    private Vector replyto;
    private Socket socket;
    private Vector to;

    public MailMessage() throws IOException {
        this(DEFAULT_HOST, 25);
    }

    public MailMessage(String host) throws IOException {
        this(host, 25);
    }

    public MailMessage(String host, int port) throws IOException {
        this.port = 25;
        this.port = port;
        this.host = host;
        this.replyto = new Vector();
        this.to = new Vector();
        this.cc = new Vector();
        this.headersKeys = new Vector();
        this.headersValues = new Vector();
        connect();
        sendHelo();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void from(String from) throws IOException {
        sendFrom(from);
        this.from = from;
    }

    public void replyto(String rto) {
        this.replyto.addElement(rto);
    }

    public void to(String to) throws IOException {
        sendRcpt(to);
        this.to.addElement(to);
    }

    public void cc(String cc) throws IOException {
        sendRcpt(cc);
        this.cc.addElement(cc);
    }

    public void bcc(String bcc) throws IOException {
        sendRcpt(bcc);
    }

    public void setSubject(String subj) {
        setHeader("Subject", subj);
    }

    public void setHeader(String name, String value) {
        this.headersKeys.add(name);
        this.headersValues.add(value);
    }

    public PrintStream getPrintStream() throws IOException {
        setFromHeader();
        setReplyToHeader();
        setToHeader();
        setCcHeader();
        setHeader("X-Mailer", "org.apache.tools.mail.MailMessage (ant.apache.org)");
        sendData();
        flushHeaders();
        return this.out;
    }

    void setFromHeader() {
        setHeader(Manifest.ATTRIBUTE_FROM, this.from);
    }

    void setReplyToHeader() {
        if (!this.replyto.isEmpty()) {
            setHeader("Reply-To", vectorToList(this.replyto));
        }
    }

    void setToHeader() {
        if (!this.to.isEmpty()) {
            setHeader("To", vectorToList(this.to));
        }
    }

    void setCcHeader() {
        if (!this.cc.isEmpty()) {
            setHeader("Cc", vectorToList(this.cc));
        }
    }

    String vectorToList(Vector v) {
        StringBuffer buf = new StringBuffer();
        Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            buf.append(e.nextElement());
            if (e.hasMoreElements()) {
                buf.append(", ");
            }
        }
        return buf.toString();
    }

    void flushHeaders() throws IOException {
        for (int i = 0; i < this.headersKeys.size(); i++) {
            String name = (String) this.headersKeys.elementAt(i);
            String value = (String) this.headersValues.elementAt(i);
            MailPrintStream mailPrintStream = this.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append(": ");
            stringBuffer.append(value);
            mailPrintStream.println(stringBuffer.toString());
        }
        this.out.println();
        this.out.flush();
    }

    public void sendAndClose() throws IOException {
        try {
            sendDot();
            sendQuit();
        } finally {
            disconnect();
        }
    }

    static String sanitizeAddress(String s) {
        int paramDepth = 0;
        int start = 0;
        int end = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                paramDepth++;
                if (start == 0) {
                    end = i;
                }
            } else if (c == ')') {
                paramDepth--;
                if (end == 0) {
                    start = i + 1;
                }
            } else if (paramDepth == 0 && c == '<') {
                start = i + 1;
            } else if (paramDepth == 0 && c == '>') {
                end = i;
            }
        }
        if (end == 0) {
            end = len;
        }
        return s.substring(start, end);
    }

    void connect() throws IOException {
        this.socket = new Socket(this.host, this.port);
        this.out = new MailPrintStream(new BufferedOutputStream(this.socket.getOutputStream()));
        this.in = new SmtpResponseReader(this.socket.getInputStream());
        getReady();
    }

    void getReady() throws IOException {
        String response = this.in.getResponse();
        int[] ok = {220};
        if (!isResponseOK(response, ok)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Didn't get introduction from server: ");
            stringBuffer.append(response);
            throw new IOException(stringBuffer.toString());
        }
    }

    void sendHelo() throws IOException {
        String local = InetAddress.getLocalHost().getHostName();
        int[] ok = {250};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("HELO ");
        stringBuffer.append(local);
        send(stringBuffer.toString(), ok);
    }

    void sendFrom(String from) throws IOException {
        int[] ok = {250};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("MAIL FROM: <");
        stringBuffer.append(sanitizeAddress(from));
        stringBuffer.append(">");
        send(stringBuffer.toString(), ok);
    }

    void sendRcpt(String rcpt) throws IOException {
        int[] ok = {250, 251};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("RCPT TO: <");
        stringBuffer.append(sanitizeAddress(rcpt));
        stringBuffer.append(">");
        send(stringBuffer.toString(), ok);
    }

    void sendData() throws IOException {
        int[] ok = {354};
        send("DATA", ok);
    }

    void sendDot() throws IOException {
        int[] ok = {250};
        send("\r\n.", ok);
    }

    void sendQuit() throws IOException {
        int[] ok = {221};
        try {
            send("QUIT", ok);
        } catch (IOException e) {
            throw new ErrorInQuitException(e);
        }
    }

    void send(String msg, int[] ok) throws IOException {
        MailPrintStream mailPrintStream = this.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(msg);
        stringBuffer.append(Manifest.EOL);
        mailPrintStream.rawPrint(stringBuffer.toString());
        String response = this.in.getResponse();
        if (!isResponseOK(response, ok)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unexpected reply to command: ");
            stringBuffer2.append(msg);
            stringBuffer2.append(": ");
            stringBuffer2.append(response);
            throw new IOException(stringBuffer2.toString());
        }
    }

    boolean isResponseOK(String response, int[] ok) {
        for (int i : ok) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("");
            stringBuffer.append(i);
            if (response.startsWith(stringBuffer.toString())) {
                return true;
            }
        }
        return false;
    }

    void disconnect() throws IOException {
        MailPrintStream mailPrintStream = this.out;
        if (mailPrintStream != null) {
            mailPrintStream.close();
        }
        SmtpResponseReader smtpResponseReader = this.in;
        if (smtpResponseReader != null) {
            try {
                smtpResponseReader.close();
            } catch (IOException e) {
            }
        }
        Socket socket = this.socket;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e2) {
            }
        }
    }
}
