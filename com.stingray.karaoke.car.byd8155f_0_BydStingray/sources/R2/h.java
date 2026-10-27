package R2;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f7375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7376b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f7377e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f7378f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f7379g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f7380h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7381i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f7382j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f7383k;

    public h(long j3, boolean z6, boolean z10, boolean z11, ArrayList arrayList, long j10, boolean z12, long j11, int i7, int i10, int i11) {
        this.f7375a = j3;
        this.f7376b = z6;
        this.c = z10;
        this.d = z11;
        this.f7378f = Collections.unmodifiableList(arrayList);
        this.f7377e = j10;
        this.f7379g = z12;
        this.f7380h = j11;
        this.f7381i = i7;
        this.f7382j = i10;
        this.f7383k = i11;
    }

    public h(Parcel parcel) {
        this.f7375a = parcel.readLong();
        this.f7376b = parcel.readByte() == 1;
        this.c = parcel.readByte() == 1;
        this.d = parcel.readByte() == 1;
        int i7 = parcel.readInt();
        ArrayList arrayList = new ArrayList(i7);
        for (int i10 = 0; i10 < i7; i10++) {
            arrayList.add(new g(parcel.readLong(), parcel.readInt()));
        }
        this.f7378f = Collections.unmodifiableList(arrayList);
        this.f7377e = parcel.readLong();
        this.f7379g = parcel.readByte() == 1;
        this.f7380h = parcel.readLong();
        this.f7381i = parcel.readInt();
        this.f7382j = parcel.readInt();
        this.f7383k = parcel.readInt();
    }
}
