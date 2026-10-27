package g.a.a;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class l0 extends InputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v f3902c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3903d = true;
    public InputStream s;

    public l0(v vVar) {
        this.f3902c = vVar;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        o oVar;
        if (this.s == null) {
            if (!this.f3903d || (oVar = (o) this.f3902c.b()) == null) {
                return -1;
            }
            this.f3903d = false;
            this.s = oVar.b();
        }
        while (true) {
            int i2 = this.s.read();
            if (i2 >= 0) {
                return i2;
            }
            o oVar2 = (o) this.f3902c.b();
            if (oVar2 == null) {
                this.s = null;
                return -1;
            }
            this.s = oVar2.b();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) throws IOException {
        o oVar;
        int i4 = 0;
        if (this.s == null) {
            if (!this.f3903d || (oVar = (o) this.f3902c.b()) == null) {
                return -1;
            }
            this.f3903d = false;
            this.s = oVar.b();
        }
        while (true) {
            int i5 = this.s.read(bArr, i2 + i4, i3 - i4);
            if (i5 >= 0) {
                i4 += i5;
                if (i4 == i3) {
                    return i4;
                }
            } else {
                o oVar2 = (o) this.f3902c.b();
                if (oVar2 == null) {
                    this.s = null;
                    if (i4 < 1) {
                        return -1;
                    }
                    return i4;
                }
                this.s = oVar2.b();
            }
        }
    }
}
