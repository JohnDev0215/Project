package defpackage;

import android.net.Uri;

/* JADX INFO: compiled from: PickerBitmap.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b12 implements Comparable<b12> {
    public final Uri e;
    public final long f;
    public final int g;

    public b12(Uri uri, long j, int i) {
        this.e = uri;
        this.f = j;
        this.g = i;
    }

    @Override // java.lang.Comparable
    public final int compareTo(b12 b12Var) {
        return Long.compare(b12Var.f, this.f);
    }
}
