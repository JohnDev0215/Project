package com.bumptech.glide.k;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: GifHeaderParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ByteBuffer f2905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f2906c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f2904a = new byte[256];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2907d = 0;

    private boolean b() {
        return this.f2906c.f2900b != 0;
    }

    private int d() {
        try {
            return this.f2905b.get() & 255;
        } catch (Exception unused) {
            this.f2906c.f2900b = 1;
            return 0;
        }
    }

    private void e() {
        this.f2906c.f2902d.f2894a = n();
        this.f2906c.f2902d.f2895b = n();
        this.f2906c.f2902d.f2896c = n();
        this.f2906c.f2902d.f2897d = n();
        int iD = d();
        boolean z = (iD & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iD & 7) + 1);
        b bVar = this.f2906c.f2902d;
        bVar.f2898e = (iD & 64) != 0;
        if (z) {
            bVar.k = g(iPow);
        } else {
            bVar.k = null;
        }
        this.f2906c.f2902d.j = this.f2905b.position();
        r();
        if (b()) {
            return;
        }
        c cVar = this.f2906c;
        cVar.f2901c++;
        cVar.f2903e.add(cVar.f2902d);
    }

    private void f() {
        int iD = d();
        this.f2907d = iD;
        if (iD <= 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                i2 = this.f2907d;
                if (i >= i2) {
                    return;
                }
                i2 -= i;
                this.f2905b.get(this.f2904a, i, i2);
                i += i2;
            } catch (Exception e2) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    Log.d("GifHeaderParser", "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.f2907d, e2);
                }
                this.f2906c.f2900b = 1;
                return;
            }
        }
    }

    private int[] g(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.f2905b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i2 + 1;
                iArr[i2] = ((bArr[i3] & 255) << 16) | (-16777216) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                i3 = i6;
                i2 = i7;
            }
        } catch (BufferUnderflowException e2) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e2);
            }
            this.f2906c.f2900b = 1;
        }
        return iArr;
    }

    private void h() {
        i(Integer.MAX_VALUE);
    }

    private void i(int i) {
        boolean z = false;
        while (!z && !b() && this.f2906c.f2901c <= i) {
            int iD = d();
            if (iD == 33) {
                int iD2 = d();
                if (iD2 == 1) {
                    q();
                } else if (iD2 == 249) {
                    this.f2906c.f2902d = new b();
                    j();
                } else if (iD2 == 254) {
                    q();
                } else if (iD2 != 255) {
                    q();
                } else {
                    f();
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < 11; i2++) {
                        sb.append((char) this.f2904a[i2]);
                    }
                    if (sb.toString().equals("NETSCAPE2.0")) {
                        m();
                    } else {
                        q();
                    }
                }
            } else if (iD == 44) {
                c cVar = this.f2906c;
                if (cVar.f2902d == null) {
                    cVar.f2902d = new b();
                }
                e();
            } else if (iD != 59) {
                this.f2906c.f2900b = 1;
            } else {
                z = true;
            }
        }
    }

    private void j() {
        d();
        int iD = d();
        b bVar = this.f2906c.f2902d;
        int i = (iD & 28) >> 2;
        bVar.g = i;
        if (i == 0) {
            bVar.g = 1;
        }
        bVar.f = (iD & 1) != 0;
        int iN = n();
        if (iN < 2) {
            iN = 10;
        }
        b bVar2 = this.f2906c.f2902d;
        bVar2.i = iN * 10;
        bVar2.h = d();
        d();
    }

    private void k() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char) d());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.f2906c.f2900b = 1;
            return;
        }
        l();
        if (!this.f2906c.h || b()) {
            return;
        }
        c cVar = this.f2906c;
        cVar.f2899a = g(cVar.i);
        c cVar2 = this.f2906c;
        cVar2.l = cVar2.f2899a[cVar2.j];
    }

    private void l() {
        this.f2906c.f = n();
        this.f2906c.g = n();
        int iD = d();
        c cVar = this.f2906c;
        cVar.h = (iD & 128) != 0;
        cVar.i = (int) Math.pow(2.0d, (iD & 7) + 1);
        this.f2906c.j = d();
        this.f2906c.k = d();
    }

    private void m() {
        do {
            f();
            byte[] bArr = this.f2904a;
            if (bArr[0] == 1) {
                this.f2906c.m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.f2907d <= 0) {
                return;
            }
        } while (!b());
    }

    private int n() {
        return this.f2905b.getShort();
    }

    private void o() {
        this.f2905b = null;
        Arrays.fill(this.f2904a, (byte) 0);
        this.f2906c = new c();
        this.f2907d = 0;
    }

    private void q() {
        int iD;
        do {
            iD = d();
            this.f2905b.position(Math.min(this.f2905b.position() + iD, this.f2905b.limit()));
        } while (iD > 0);
    }

    private void r() {
        d();
        q();
    }

    public void a() {
        this.f2905b = null;
        this.f2906c = null;
    }

    public c c() {
        if (this.f2905b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (b()) {
            return this.f2906c;
        }
        k();
        if (!b()) {
            h();
            c cVar = this.f2906c;
            if (cVar.f2901c < 0) {
                cVar.f2900b = 1;
            }
        }
        return this.f2906c;
    }

    public d p(ByteBuffer byteBuffer) {
        o();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f2905b = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f2905b.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }
}
