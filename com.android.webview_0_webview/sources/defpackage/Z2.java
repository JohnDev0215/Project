package defpackage;

import java.io.Writer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Z2 extends Writer {
    public final String s;
    public StringBuilder t = new StringBuilder(128);

    public Z2(String str) {
        this.s = str;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        x();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        x();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                x();
            } else {
                this.t.append(c);
            }
        }
    }

    public final void x() {
        if (this.t.length() > 0) {
            this.t.toString();
            StringBuilder sb = this.t;
            sb.delete(0, sb.length());
        }
    }
}
