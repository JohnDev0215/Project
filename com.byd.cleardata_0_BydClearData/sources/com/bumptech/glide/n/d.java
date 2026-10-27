package com.bumptech.glide.n;

import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ByteBuffer f2451b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f2452c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f2450a = new byte[256];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2453d = 0;

    private boolean b() {
        return this.f2452c.f2447b != 0;
    }

    private int d() {
        try {
            return this.f2451b.get() & 255;
        } catch (Exception unused) {
            this.f2452c.f2447b = 1;
            return 0;
        }
    }

    private void e() {
        this.f2452c.f2449d.f2442a = n();
        this.f2452c.f2449d.f2443b = n();
        this.f2452c.f2449d.f2444c = n();
        this.f2452c.f2449d.f2445d = n();
        int iD = d();
        boolean z = (iD & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iD & 7) + 1);
        b bVar = this.f2452c.f2449d;
        bVar.e = (iD & 64) != 0;
        if (z) {
            bVar.k = g(iPow);
        } else {
            bVar.k = null;
        }
        this.f2452c.f2449d.j = this.f2451b.position();
        r();
        if (b()) {
            return;
        }
        c cVar = this.f2452c;
        cVar.f2448c++;
        cVar.e.add(cVar.f2449d);
    }

    private void f() {
        int iD = d();
        this.f2453d = iD;
        if (iD <= 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                i2 = this.f2453d;
                if (i >= i2) {
                    return;
                }
                i2 -= i;
                this.f2451b.get(this.f2450a, i, i2);
                i += i2;
            } catch (Exception e) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    Log.d("GifHeaderParser", "Error Reading Block n: " + i + " count: " + i2 + " blockSize: " + this.f2453d, e);
                }
                this.f2452c.f2447b = 1;
                return;
            }
        }
    }

    private int[] g(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.f2451b.get(bArr);
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
        } catch (BufferUnderflowException e) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e);
            }
            this.f2452c.f2447b = 1;
        }
        return iArr;
    }

    private void h() {
        i(Integer.MAX_VALUE);
    }

    private void i(int i) {
        boolean z = false;
        while (!z && !b() && this.f2452c.f2448c <= i) {
            int iD = d();
            if (iD == 33) {
                int iD2 = d();
                if (iD2 != 1) {
                    if (iD2 == 249) {
                        this.f2452c.f2449d = new b();
                        j();
                    } else if (iD2 != 254 && iD2 == 255) {
                        f();
                        StringBuilder sb = new StringBuilder();
                        for (int i2 = 0; i2 < 11; i2++) {
                            sb.append((char) this.f2450a[i2]);
                        }
                        if (sb.toString().equals("NETSCAPE2.0")) {
                            m();
                        }
                    }
                }
                q();
            } else if (iD == 44) {
                c cVar = this.f2452c;
                if (cVar.f2449d == null) {
                    cVar.f2449d = new b();
                }
                e();
            } else if (iD != 59) {
                this.f2452c.f2447b = 1;
            } else {
                z = true;
            }
        }
    }

    private void j() {
        d();
        int iD = d();
        b bVar = this.f2452c.f2449d;
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
        b bVar2 = this.f2452c.f2449d;
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
            this.f2452c.f2447b = 1;
            return;
        }
        l();
        if (!this.f2452c.h || b()) {
            return;
        }
        c cVar = this.f2452c;
        cVar.f2446a = g(cVar.i);
        c cVar2 = this.f2452c;
        cVar2.l = cVar2.f2446a[cVar2.j];
    }

    private void l() {
        this.f2452c.f = n();
        this.f2452c.g = n();
        int iD = d();
        c cVar = this.f2452c;
        cVar.h = (iD & 128) != 0;
        cVar.i = (int) Math.pow(2.0d, (iD & 7) + 1);
        this.f2452c.j = d();
        this.f2452c.k = d();
    }

    private void m() {
        do {
            f();
            byte[] bArr = this.f2450a;
            if (bArr[0] == 1) {
                this.f2452c.m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.f2453d <= 0) {
                return;
            }
        } while (!b());
    }

    private int n() {
        return this.f2451b.getShort();
    }

    private void o() {
        this.f2451b = null;
        Arrays.fill(this.f2450a, (byte) 0);
        this.f2452c = new c();
        this.f2453d = 0;
    }

    private void q() {
        int iD;
        do {
            iD = d();
            this.f2451b.position(Math.min(this.f2451b.position() + iD, this.f2451b.limit()));
        } while (iD > 0);
    }

    private void r() {
        d();
        q();
    }

    public void a() {
        this.f2451b = null;
        this.f2452c = null;
    }

    public c c() {
        if (this.f2451b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (b()) {
            return this.f2452c;
        }
        k();
        if (!b()) {
            h();
            c cVar = this.f2452c;
            if (cVar.f2448c < 0) {
                cVar.f2447b = 1;
            }
        }
        return this.f2452c;
    }

    public d p(ByteBuffer byteBuffer) {
        o();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.f2451b = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.f2451b.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }
}
