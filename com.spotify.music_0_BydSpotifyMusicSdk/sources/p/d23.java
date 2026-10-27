package p;

import android.os.ParcelFileDescriptor;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class d23 extends r13 {
    public final int c;
    public final long d;
    public final int e;
    public final long f;
    public final int g;
    public final String h;
    public final long i;
    public final InputStream j;

    public d23(int i, String str, int i2, long j, int i3, long j2, int i4, String str2, long j3, ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream) {
        super(i, str);
        this.c = i2;
        this.d = j;
        this.e = i3;
        this.f = j2;
        this.g = i4;
        this.h = str2;
        this.i = j3;
        this.j = autoCloseInputStream;
    }
}
