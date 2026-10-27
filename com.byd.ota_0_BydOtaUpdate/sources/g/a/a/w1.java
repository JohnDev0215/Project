package g.a.a;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public abstract class w1 extends InputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InputStream f4107c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4108d;

    public w1(InputStream inputStream, int i2) {
        this.f4107c = inputStream;
        this.f4108d = i2;
    }

    public int c() {
        return this.f4108d;
    }

    public void d(boolean z) {
        InputStream inputStream = this.f4107c;
        if (inputStream instanceof t1) {
            ((t1) inputStream).v(z);
        }
    }
}
