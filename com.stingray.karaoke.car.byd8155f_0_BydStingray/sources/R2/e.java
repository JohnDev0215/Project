package R2;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e extends b {
    public static final Parcelable.Creator<e> CREATOR = new I1.g(24);
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f7362m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f7363n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f7364p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f7365q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final long f7366r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final List f7367s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f7368t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f7369u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f7370v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f7371w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f7372x;

    public e(long j3, boolean z6, boolean z10, boolean z11, boolean z12, long j10, long j11, List list, boolean z13, long j12, int i7, int i10, int i11) {
        this.l = j3;
        this.f7362m = z6;
        this.f7363n = z10;
        this.o = z11;
        this.f7364p = z12;
        this.f7365q = j10;
        this.f7366r = j11;
        this.f7367s = Collections.unmodifiableList(list);
        this.f7368t = z13;
        this.f7369u = j12;
        this.f7370v = i7;
        this.f7371w = i10;
        this.f7372x = i11;
    }

    @Override // R2.b
    public final String toString() {
        StringBuilder sb = new StringBuilder("SCTE-35 SpliceInsertCommand { programSplicePts=");
        sb.append(this.f7365q);
        sb.append(", programSplicePlaybackPositionUs= ");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f7366r, " }", sb);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.l);
        parcel.writeByte(this.f7362m ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7363n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7364p ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f7365q);
        parcel.writeLong(this.f7366r);
        List list = this.f7367s;
        int size = list.size();
        parcel.writeInt(size);
        for (int i10 = 0; i10 < size; i10++) {
            d dVar = (d) list.get(i10);
            parcel.writeInt(dVar.f7360a);
            parcel.writeLong(dVar.f7361b);
            parcel.writeLong(dVar.c);
        }
        parcel.writeByte(this.f7368t ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f7369u);
        parcel.writeInt(this.f7370v);
        parcel.writeInt(this.f7371w);
        parcel.writeInt(this.f7372x);
    }

    public e(Parcel parcel) {
        this.l = parcel.readLong();
        this.f7362m = parcel.readByte() == 1;
        this.f7363n = parcel.readByte() == 1;
        this.o = parcel.readByte() == 1;
        this.f7364p = parcel.readByte() == 1;
        this.f7365q = parcel.readLong();
        this.f7366r = parcel.readLong();
        int i7 = parcel.readInt();
        ArrayList arrayList = new ArrayList(i7);
        for (int i10 = 0; i10 < i7; i10++) {
            arrayList.add(new d(parcel.readInt(), parcel.readLong(), parcel.readLong()));
        }
        this.f7367s = Collections.unmodifiableList(arrayList);
        this.f7368t = parcel.readByte() == 1;
        this.f7369u = parcel.readLong();
        this.f7370v = parcel.readInt();
        this.f7371w = parcel.readInt();
        this.f7372x = parcel.readInt();
    }
}
