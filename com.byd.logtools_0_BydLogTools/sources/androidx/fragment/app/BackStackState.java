package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.j;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f1458b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<String> f1459c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f1460d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int[] f1461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f1462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f1463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final String f1464h;
    final int i;
    final int j;
    final CharSequence k;
    final int l;
    final CharSequence m;
    final ArrayList<String> n;
    final ArrayList<String> o;
    final boolean p;

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
        public BackStackState[] newArray(int i) {
            return new BackStackState[i];
        }
    }

    public BackStackState(androidx.fragment.app.a aVar) {
        int size = aVar.f1551a.size();
        this.f1458b = new int[size * 5];
        if (!aVar.f1558h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1459c = new ArrayList<>(size);
        this.f1460d = new int[size];
        this.f1461e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            j.a aVar2 = aVar.f1551a.get(i);
            int i3 = i2 + 1;
            this.f1458b[i2] = aVar2.f1559a;
            ArrayList<String> arrayList = this.f1459c;
            Fragment fragment = aVar2.f1560b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f1458b;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1561c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1562d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1563e;
            iArr[i6] = aVar2.f1564f;
            this.f1460d[i] = aVar2.f1565g.ordinal();
            this.f1461e[i] = aVar2.f1566h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f1462f = aVar.f1556f;
        this.f1463g = aVar.f1557g;
        this.f1464h = aVar.j;
        this.i = aVar.u;
        this.j = aVar.k;
        this.k = aVar.l;
        this.l = aVar.m;
        this.m = aVar.n;
        this.n = aVar.o;
        this.o = aVar.p;
        this.p = aVar.q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public androidx.fragment.app.a e(h hVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(hVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1458b.length) {
            j.a aVar2 = new j.a();
            int i3 = i + 1;
            aVar2.f1559a = this.f1458b[i];
            if (h.f1512d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f1458b[i3]);
            }
            String str = this.f1459c.get(i2);
            if (str != null) {
                aVar2.f1560b = hVar.k.get(str);
            } else {
                aVar2.f1560b = null;
            }
            aVar2.f1565g = Lifecycle.State.values()[this.f1460d[i2]];
            aVar2.f1566h = Lifecycle.State.values()[this.f1461e[i2]];
            int[] iArr = this.f1458b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f1561c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.f1562d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.f1563e = i9;
            int i10 = iArr[i8];
            aVar2.f1564f = i10;
            aVar.f1552b = i5;
            aVar.f1553c = i7;
            aVar.f1554d = i9;
            aVar.f1555e = i10;
            aVar.c(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.f1556f = this.f1462f;
        aVar.f1557g = this.f1463g;
        aVar.j = this.f1464h;
        aVar.u = this.i;
        aVar.f1558h = true;
        aVar.k = this.j;
        aVar.l = this.k;
        aVar.m = this.l;
        aVar.n = this.m;
        aVar.o = this.n;
        aVar.p = this.o;
        aVar.q = this.p;
        aVar.h(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1458b);
        parcel.writeStringList(this.f1459c);
        parcel.writeIntArray(this.f1460d);
        parcel.writeIntArray(this.f1461e);
        parcel.writeInt(this.f1462f);
        parcel.writeInt(this.f1463g);
        parcel.writeString(this.f1464h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeInt(this.l);
        TextUtils.writeToParcel(this.m, parcel, 0);
        parcel.writeStringList(this.n);
        parcel.writeStringList(this.o);
        parcel.writeInt(this.p ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f1458b = parcel.createIntArray();
        this.f1459c = parcel.createStringArrayList();
        this.f1460d = parcel.createIntArray();
        this.f1461e = parcel.createIntArray();
        this.f1462f = parcel.readInt();
        this.f1463g = parcel.readInt();
        this.f1464h = parcel.readString();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.readInt();
        this.m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.n = parcel.createStringArrayList();
        this.o = parcel.createStringArrayList();
        this.p = parcel.readInt() != 0;
    }
}
