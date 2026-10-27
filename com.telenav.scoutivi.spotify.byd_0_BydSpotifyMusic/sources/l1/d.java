package l1;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: GifHeaderParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ByteBuffer f7919b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f7920c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f7918a = new byte[256];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f7921d = 0;

    private boolean b() {
        return this.f7920c.f7906b != 0;
    }

    private int d() {
        try {
            return this.f7919b.get() & 255;
        } catch (Exception unused) {
            this.f7920c.f7906b = 1;
            return 0;
        }
    }

    private void e() {
        this.f7920c.f7908d.f7894a = n();
        this.f7920c.f7908d.f7895b = n();
        this.f7920c.f7908d.f7896c = n();
        this.f7920c.f7908d.f7897d = n();
        int iD = d();
        boolean z6 = (iD & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iD & 7) + 1);
        b bVar = this.f7920c.f7908d;
        bVar.f7898e = (iD & 64) != 0;
        if (z6) {
            bVar.f7904k = g(iPow);
        } else {
            bVar.f7904k = null;
        }
        this.f7920c.f7908d.f7903j = this.f7919b.position();
        r();
        if (b()) {
            return;
        }
        c cVar = this.f7920c;
        cVar.f7907c++;
        cVar.f7909e.add(cVar.f7908d);
    }

    private void f() {
        int iD = d();
        this.f7921d = iD;
        if (iD <= 0) {
            return;
        }
        int i7 = 0;
        int i8 = 0;
        while (true) {
            try {
                i8 = this.f7921d;
                if (i7 >= i8) {
                    return;
                }
                i8 -= i7;
                this.f7919b.get(this.f7918a, i7, i8);
                i7 += i8;
            } catch (Exception e7) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    Log.d("GifHeaderParser", "Error Reading Block n: " + i7 + " count: " + i8 + " blockSize: " + this.f7921d, e7);
                }
                this.f7920c.f7906b = 1;
                return;
            }
        }
    }

    private int[] g(int i7) {
        byte[] bArr = new byte[i7 * 3];
        int[] iArr = null;
        try {
            this.f7919b.get(bArr);
            iArr = new int[256];
            int i8 = 0;
            int i9 = 0;
            while (i8 < i7) {
                int i10 = i9 + 1;
                int i11 = i10 + 1;
                int i12 = i11 + 1;
                int i13 = i8 + 1;
                iArr[i8] = ((bArr[i9] & 255) << 16) | (-16777216) | ((bArr[i10] & 255) << 8) | (bArr[i11] & 255);
                i9 = i12;
                i8 = i13;
            }
        } catch (BufferUnderflowException e7) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e7);
            }
            this.f7920c.f7906b = 1;
        }
        return iArr;
    }

    private void h() {
        i(Integer.MAX_VALUE);
    }

    private void i(int i7) {
        boolean z6 = false;
        while (!z6 && !b() && this.f7920c.f7907c <= i7) {
            int iD = d();
            if (iD == 33) {
                int iD2 = d();
                if (iD2 == 1) {
                    q();
                } else if (iD2 == 249) {
                    this.f7920c.f7908d = new b();
                    j();
                } else if (iD2 == 254) {
                    q();
                } else if (iD2 != 255) {
                    q();
                } else {
                    f();
                    StringBuilder sb = new StringBuilder();
                    for (int i8 = 0; i8 < 11; i8++) {
                        sb.append((char) this.f7918a[i8]);
                    }
                    if (sb.toString().equals("NETSCAPE2.0")) {
                        m();
                    } else {
                        q();
                    }
                }
            } else if (iD == 44) {
                c cVar = this.f7920c;
                if (cVar.f7908d == null) {
                    cVar.f7908d = new b();
                }
                e();
            } else if (iD != 59) {
                this.f7920c.f7906b = 1;
            } else {
                z6 = true;
            }
        }
    }

    private void j() {
        d();
        int iD = d();
        b bVar = this.f7920c.f7908d;
        int i7 = (iD & 28) >> 2;
        bVar.f7900g = i7;
        if (i7 == 0) {
            bVar.f7900g = 1;
        }
        bVar.f7899f = (iD & 1) != 0;
        int iN = n();
        if (iN < 2) {
            iN = 10;
        }
        b bVar2 = this.f7920c.f7908d;
        bVar2.f7902i = iN * 10;
        bVar2.f7901h = d();
        d();
    }

    private void k() {
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < 6; i7++) {
            sb.append((char) d());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.f7920c.f7906b = 1;
            return;
        }
        l();
        if (!this.f7920c.f7912h || b()) {
            return;
        }
        c cVar = this.f7920c;
        cVar.f7905a = g(cVar.f7913i);
        c cVar2 = this.f7920c;
        cVar2.f7916l = cVar2.f7905a[cVar2.f7914j];
    }

    private void l() {
        this.f7920c.f7910f = n();
        this.f7920c.f7911g = n();
        int iD = d();
        c cVar = this.f7920c;
        cVar.f7912h = (iD & 128) != 0;
        cVar.f7913i = (int) Math.pow(2.0d, (iD & 7) + 1);
        this.f7920c.f7914j = d();
        this.f7920c.f7915k = d();
    }

    private void m() {
        do {
            f();
            byte[] bArr = this.f7918a;
            if (bArr[0] == 1) {
                this.f7920c.f7917m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.f7921d <= 0) {
                return;
            }
        } while (!b());
    }

    private int n() {
        return this.f7919b.getShort();
    }

    private void o() {
        this.f7919b = null;
        Arrays.fill(this.f7918a, (byte) 0);
        this.f7920c = new c();
        this.f7921d = 0;
    }

    private void q() {
        int iD;
        do {
            iD = d();
            this.f7919b.position(Math.min(this.f7919b.position() + iD, this.f7919b.limit()));
        } while (iD > 0);
    }

    private void r() {
        d();
        q();
    }

    public void a() {
        this.f7919b = null;
        this.f7920c = null;
    }

    public c c() {
        if (this.f7919b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (b()) {
            return this.f7920c;
        }
        k();
        if (!b()) {
            h();
            c cVar = this.f7920c;
            if (cVar.f7907c < 0) {
                cVar.f7906b = 1;
            }
        }
        return this.f7920c;
    }

    public d p(ByteBuffer byteBuffer) {
        o();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f7919b = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f7919b.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }
}
