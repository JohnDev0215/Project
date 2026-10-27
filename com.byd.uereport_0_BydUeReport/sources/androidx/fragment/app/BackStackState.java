package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import v0.h;
import v0.j;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f1360d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList<String> f1361e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f1362f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f1363g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1364h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1365i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f1366j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1367k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f1368l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f1369m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1370n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final CharSequence f1371o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList<String> f1372p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList<String> f1373q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f1374r;

    public static class a implements Parcelable.Creator<BackStackState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i8) {
            return new BackStackState[i8];
        }
    }

    public BackStackState(v0.a aVar) {
        int size = aVar.f8422a.size();
        this.f1360d = new int[size * 5];
        if (!aVar.f8429h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1361e = new ArrayList<>(size);
        this.f1362f = new int[size];
        this.f1363g = new int[size];
        int i8 = 0;
        int i9 = 0;
        while (i8 < size) {
            j.a aVar2 = aVar.f8422a.get(i8);
            int i10 = i9 + 1;
            this.f1360d[i9] = aVar2.f8440a;
            ArrayList<String> arrayList = this.f1361e;
            Fragment fragment = aVar2.f8441b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1360d;
            int i11 = i10 + 1;
            iArr[i10] = aVar2.f8442c;
            int i12 = i11 + 1;
            iArr[i11] = aVar2.f8443d;
            int i13 = i12 + 1;
            iArr[i12] = aVar2.f8444e;
            iArr[i13] = aVar2.f8445f;
            this.f1362f[i8] = aVar2.f8446g.ordinal();
            this.f1363g[i8] = aVar2.f8447h.ordinal();
            i8++;
            i9 = i13 + 1;
        }
        this.f1364h = aVar.f8427f;
        this.f1365i = aVar.f8428g;
        this.f1366j = aVar.f8431j;
        this.f1367k = aVar.f8356u;
        this.f1368l = aVar.f8432k;
        this.f1369m = aVar.f8433l;
        this.f1370n = aVar.f8434m;
        this.f1371o = aVar.f8435n;
        this.f1372p = aVar.f8436o;
        this.f1373q = aVar.f8437p;
        this.f1374r = aVar.f8438q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public v0.a f(h hVar) {
        v0.a aVar = new v0.a(hVar);
        int i8 = 0;
        int i9 = 0;
        while (i8 < this.f1360d.length) {
            j.a aVar2 = new j.a();
            int i10 = i8 + 1;
            aVar2.f8440a = this.f1360d[i8];
            if (h.f8366f) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i9 + " base fragment #" + this.f1360d[i10]);
            }
            String str = this.f1361e.get(i9);
            if (str != null) {
                aVar2.f8441b = hVar.f8373m.get(str);
            } else {
                aVar2.f8441b = null;
            }
            aVar2.f8446g = Lifecycle.State.values()[this.f1362f[i9]];
            aVar2.f8447h = Lifecycle.State.values()[this.f1363g[i9]];
            int[] iArr = this.f1360d;
            int i11 = i10 + 1;
            int i12 = iArr[i10];
            aVar2.f8442c = i12;
            int i13 = i11 + 1;
            int i14 = iArr[i11];
            aVar2.f8443d = i14;
            int i15 = i13 + 1;
            int i16 = iArr[i13];
            aVar2.f8444e = i16;
            int i17 = iArr[i15];
            aVar2.f8445f = i17;
            aVar.f8423b = i12;
            aVar.f8424c = i14;
            aVar.f8425d = i16;
            aVar.f8426e = i17;
            aVar.d(aVar2);
            i9++;
            i8 = i15 + 1;
        }
        aVar.f8427f = this.f1364h;
        aVar.f8428g = this.f1365i;
        aVar.f8431j = this.f1366j;
        aVar.f8356u = this.f1367k;
        aVar.f8429h = true;
        aVar.f8432k = this.f1368l;
        aVar.f8433l = this.f1369m;
        aVar.f8434m = this.f1370n;
        aVar.f8435n = this.f1371o;
        aVar.f8436o = this.f1372p;
        aVar.f8437p = this.f1373q;
        aVar.f8438q = this.f1374r;
        aVar.n(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeIntArray(this.f1360d);
        parcel.writeStringList(this.f1361e);
        parcel.writeIntArray(this.f1362f);
        parcel.writeIntArray(this.f1363g);
        parcel.writeInt(this.f1364h);
        parcel.writeInt(this.f1365i);
        parcel.writeString(this.f1366j);
        parcel.writeInt(this.f1367k);
        parcel.writeInt(this.f1368l);
        TextUtils.writeToParcel(this.f1369m, parcel, 0);
        parcel.writeInt(this.f1370n);
        TextUtils.writeToParcel(this.f1371o, parcel, 0);
        parcel.writeStringList(this.f1372p);
        parcel.writeStringList(this.f1373q);
        parcel.writeInt(this.f1374r ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1360d = parcel.createIntArray();
        this.f1361e = parcel.createStringArrayList();
        this.f1362f = parcel.createIntArray();
        this.f1363g = parcel.createIntArray();
        this.f1364h = parcel.readInt();
        this.f1365i = parcel.readInt();
        this.f1366j = parcel.readString();
        this.f1367k = parcel.readInt();
        this.f1368l = parcel.readInt();
        this.f1369m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1370n = parcel.readInt();
        this.f1371o = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f1372p = parcel.createStringArrayList();
        this.f1373q = parcel.createStringArrayList();
        this.f1374r = parcel.readInt() != 0;
    }
}
