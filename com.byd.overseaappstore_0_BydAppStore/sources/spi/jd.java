package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class jd {
    public ByteBuffer b;
    public id c;
    public final byte[] a = new byte[256];
    public int d = 0;

    public final boolean a() {
        return this.c.b != 0;
    }

    /* JADX WARN: Type inference failed for: r6v23, types: [java.util.ArrayList, java.util.List<spi.gd>] */
    @NonNull
    public final id b() {
        if (this.b == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (a()) {
            return this.c;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append((char) c());
        }
        if (sb.toString().startsWith("GIF")) {
            this.c.f = f();
            this.c.g = f();
            int iC = c();
            id idVar = this.c;
            idVar.h = (iC & 128) != 0;
            idVar.i = (int) Math.pow(2.0d, (iC & 7) + 1);
            this.c.j = c();
            id idVar2 = this.c;
            c();
            Objects.requireNonNull(idVar2);
            if (this.c.h && !a()) {
                id idVar3 = this.c;
                idVar3.a = e(idVar3.i);
                id idVar4 = this.c;
                idVar4.k = idVar4.a[idVar4.j];
            }
        } else {
            this.c.b = 1;
        }
        if (!a()) {
            boolean z = false;
            while (!z && !a() && this.c.c <= Integer.MAX_VALUE) {
                int iC2 = c();
                if (iC2 == 33) {
                    int iC3 = c();
                    if (iC3 != 1) {
                        if (iC3 == 249) {
                            this.c.d = new gd();
                            c();
                            int iC4 = c();
                            gd gdVar = this.c.d;
                            int i2 = (iC4 & 28) >> 2;
                            gdVar.g = i2;
                            if (i2 == 0) {
                                gdVar.g = 1;
                            }
                            gdVar.f = (iC4 & 1) != 0;
                            int iF = f();
                            if (iF < 2) {
                                iF = 10;
                            }
                            gd gdVar2 = this.c.d;
                            gdVar2.i = iF * 10;
                            gdVar2.h = c();
                            c();
                        } else if (iC3 != 254 && iC3 == 255) {
                            d();
                            StringBuilder sb2 = new StringBuilder();
                            for (int i3 = 0; i3 < 11; i3++) {
                                sb2.append((char) this.a[i3]);
                            }
                            if (sb2.toString().equals("NETSCAPE2.0")) {
                                do {
                                    d();
                                    byte[] bArr = this.a;
                                    if (bArr[0] == 1) {
                                        byte b = bArr[1];
                                        byte b2 = bArr[2];
                                        Objects.requireNonNull(this.c);
                                    }
                                    if (this.d > 0) {
                                    }
                                } while (!a());
                            }
                        }
                    }
                    g();
                } else if (iC2 == 44) {
                    id idVar5 = this.c;
                    if (idVar5.d == null) {
                        idVar5.d = new gd();
                    }
                    this.c.d.a = f();
                    this.c.d.b = f();
                    this.c.d.c = f();
                    this.c.d.d = f();
                    int iC5 = c();
                    boolean z2 = (iC5 & 128) != 0;
                    int iPow = (int) Math.pow(2.0d, (iC5 & 7) + 1);
                    gd gdVar3 = this.c.d;
                    gdVar3.e = (iC5 & 64) != 0;
                    if (z2) {
                        gdVar3.k = e(iPow);
                    } else {
                        gdVar3.k = null;
                    }
                    this.c.d.j = this.b.position();
                    c();
                    g();
                    if (!a()) {
                        id idVar6 = this.c;
                        idVar6.c++;
                        idVar6.e.add(idVar6.d);
                    }
                } else if (iC2 != 59) {
                    this.c.b = 1;
                } else {
                    z = true;
                }
            }
            id idVar7 = this.c;
            if (idVar7.c < 0) {
                idVar7.b = 1;
            }
        }
        return this.c;
    }

    public final int c() {
        try {
            return this.b.get() & 255;
        } catch (Exception unused) {
            this.c.b = 1;
            return 0;
        }
    }

    public final void d() {
        int iC = c();
        this.d = iC;
        if (iC <= 0) {
            return;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                i2 = this.d;
                if (i >= i2) {
                    return;
                }
                i2 -= i;
                this.b.get(this.a, i, i2);
                i += i2;
            } catch (Exception e) {
                if (Log.isLoggable("GifHeaderParser", 3)) {
                    StringBuilder sbL = e.l("Error Reading Block n: ", i, " count: ", i2, " blockSize: ");
                    sbL.append(this.d);
                    Log.d("GifHeaderParser", sbL.toString(), e);
                }
                this.c.b = 1;
                return;
            }
        }
    }

    @Nullable
    public final int[] e(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.b.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = i3 + 1;
                int i5 = i4 + 1;
                int i6 = i5 + 1;
                int i7 = i2 + 1;
                iArr[i2] = ((bArr[i3] & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                i3 = i6;
                i2 = i7;
            }
        } catch (BufferUnderflowException e) {
            if (Log.isLoggable("GifHeaderParser", 3)) {
                Log.d("GifHeaderParser", "Format Error Reading Color Table", e);
            }
            this.c.b = 1;
        }
        return iArr;
    }

    public final int f() {
        return this.b.getShort();
    }

    public final void g() {
        int iC;
        do {
            iC = c();
            this.b.position(Math.min(this.b.position() + iC, this.b.limit()));
        } while (iC > 0);
    }
}
