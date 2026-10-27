package p;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class x03 extends r13 {
    public final int c;
    public final long d;
    public final String e;
    public final String f;
    public final int g;
    public final int h;
    public final int i;
    public final long j;
    public final int k;
    public final InputStream l;

    public x03(int i, String str, int i2, long j, String str2, String str3, int i3, int i4, int i5, long j2, int i6, ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream) {
        super(i, str);
        this.c = i2;
        this.d = j;
        this.e = str2;
        this.f = str3;
        this.g = i3;
        this.h = i4;
        this.i = i5;
        this.j = j2;
        this.k = i6;
        this.l = autoCloseInputStream;
    }
}
