package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.o;
import androidx.lifecycle.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f1353b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<String> f1354c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f1355d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int[] f1356e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f1357f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final String f1358g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final int f1359h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int f1360i;
    final CharSequence j;
    final int k;
    final CharSequence l;
    final ArrayList<String> m;
    final ArrayList<String> n;
    final boolean o;

    static class a implements Parcelable.Creator<BackStackState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BackStackState createFromParcel(Parcel parcel) {
            return new BackStackState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BackStackState[] newArray(int i2) {
            return new BackStackState[i2];
        }
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.f1484c.size();
        this.f1353b = new int[size * 5];
        if (!aVar.f1490i) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1354c = new ArrayList<>(size);
        this.f1355d = new int[size];
        this.f1356e = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            o.a aVar2 = aVar.f1484c.get(i2);
            int i4 = i3 + 1;
            this.f1353b[i3] = aVar2.f1491a;
            ArrayList<String> arrayList = this.f1354c;
            Fragment fragment = aVar2.f1492b;
            arrayList.add(fragment != null ? fragment.f1366g : null);
            int[] iArr = this.f1353b;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1493c;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1494d;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.f1495e;
            iArr[i7] = aVar2.f1496f;
            this.f1355d[i2] = aVar2.f1497g.ordinal();
            this.f1356e[i2] = aVar2.f1498h.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.f1357f = aVar.f1489h;
        this.f1358g = aVar.k;
        this.f1359h = aVar.v;
        this.f1360i = aVar.l;
        this.j = aVar.m;
        this.k = aVar.n;
        this.l = aVar.o;
        this.m = aVar.p;
        this.n = aVar.q;
        this.o = aVar.r;
    }

    public androidx.fragment.app.a a(j jVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(jVar);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f1353b.length) {
            o.a aVar2 = new o.a();
            int i4 = i2 + 1;
            aVar2.f1491a = this.f1353b[i2];
            if (j.q0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i3 + " base fragment #" + this.f1353b[i4]);
            }
            String str = this.f1354c.get(i3);
            if (str != null) {
                aVar2.f1492b = jVar.W(str);
            } else {
                aVar2.f1492b = null;
            }
            aVar2.f1497g = d.b.values()[this.f1355d[i3]];
            aVar2.f1498h = d.b.values()[this.f1356e[i3]];
            int[] iArr = this.f1353b;
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            aVar2.f1493c = i6;
            int i7 = i5 + 1;
            int i8 = iArr[i5];
            aVar2.f1494d = i8;
            int i9 = i7 + 1;
            int i10 = iArr[i7];
            aVar2.f1495e = i10;
            int i11 = iArr[i9];
            aVar2.f1496f = i11;
            aVar.f1485d = i6;
            aVar.f1486e = i8;
            aVar.f1487f = i10;
            aVar.f1488g = i11;
            aVar.e(aVar2);
            i3++;
            i2 = i9 + 1;
        }
        aVar.f1489h = this.f1357f;
        aVar.k = this.f1358g;
        aVar.v = this.f1359h;
        aVar.f1490i = true;
        aVar.l = this.f1360i;
        aVar.m = this.j;
        aVar.n = this.k;
        aVar.o = this.l;
        aVar.p = this.m;
        aVar.q = this.n;
        aVar.r = this.o;
        aVar.v(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f1353b);
        parcel.writeStringList(this.f1354c);
        parcel.writeIntArray(this.f1355d);
        parcel.writeIntArray(this.f1356e);
        parcel.writeInt(this.f1357f);
        parcel.writeString(this.f1358g);
        parcel.writeInt(this.f1359h);
        parcel.writeInt(this.f1360i);
        TextUtils.writeToParcel(this.j, parcel, 0);
        parcel.writeInt(this.k);
        TextUtils.writeToParcel(this.l, parcel, 0);
        parcel.writeStringList(this.m);
        parcel.writeStringList(this.n);
        parcel.writeInt(this.o ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1353b = parcel.createIntArray();
        this.f1354c = parcel.createStringArrayList();
        this.f1355d = parcel.createIntArray();
        this.f1356e = parcel.createIntArray();
        this.f1357f = parcel.readInt();
        this.f1358g = parcel.readString();
        this.f1359h = parcel.readInt();
        this.f1360i = parcel.readInt();
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = parcel.readInt();
        this.l = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.m = parcel.createStringArrayList();
        this.n = parcel.createStringArrayList();
        this.o = parcel.readInt() != 0;
    }
}
