package org.apache.tools.mail;

import java.io.OutputStream;
import java.io.PrintStream;

/* JADX INFO: compiled from: MailMessage.java */
/* JADX INFO: loaded from: classes.dex */
class MailPrintStream extends PrintStream {
    private int lastChar;

    public MailPrintStream(OutputStream out) {
        super(out, true);
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) {
        if (b == 10 && this.lastChar != 13) {
            rawWrite(13);
            rawWrite(b);
        } else if (b == 46 && this.lastChar == 10) {
            rawWrite(46);
            rawWrite(b);
        } else {
            rawWrite(b);
        }
        this.lastChar = b;
    }

    @Override // java.io.PrintStream, java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int off, int len) {
        for (int i = 0; i < len; i++) {
            write(buf[off + i]);
        }
    }

    void rawWrite(int b) {
        super.write(b);
    }

    void rawPrint(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            rawWrite(s.charAt(i));
        }
    }
}
