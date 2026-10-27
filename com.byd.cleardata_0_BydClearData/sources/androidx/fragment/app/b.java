package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.n;
import androidx.lifecycle.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f1282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<String> f1283c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f1284d;
    final int[] e;
    final int f;
    final int g;
    final String h;
    final int i;
    final int j;
    final CharSequence k;
    final int l;
    final CharSequence m;
    final ArrayList<String> n;
    final ArrayList<String> o;
    final boolean p;

    static class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    public b(Parcel parcel) {
        this.f1282b = parcel.createIntArray();
        this.f1283c = parcel.createStringArrayList();
        this.f1284d = parcel.createIntArray();
        this.e = parcel.createIntArray();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.l = parcel.readInt();
        this.m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.n = parcel.createStringArrayList();
        this.o = parcel.createStringArrayList();
        this.p = parcel.readInt() != 0;
    }

    public b(androidx.fragment.app.a aVar) {
        int size = aVar.f1331a.size();
        this.f1282b = new int[size * 5];
        if (!aVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f1283c = new ArrayList<>(size);
        this.f1284d = new int[size];
        this.e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            n.a aVar2 = aVar.f1331a.get(i);
            int i3 = i2 + 1;
            this.f1282b[i2] = aVar2.f1335a;
            ArrayList<String> arrayList = this.f1283c;
            Fragment fragment = aVar2.f1336b;
            arrayList.add(fragment != null ? fragment.g : null);
            int[] iArr = this.f1282b;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1337c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1338d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            iArr[i6] = aVar2.f;
            this.f1284d[i] = aVar2.g.ordinal();
            this.e[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.j;
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

    public androidx.fragment.app.a u(j jVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(jVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f1282b.length) {
            n.a aVar2 = new n.a();
            int i3 = i + 1;
            aVar2.f1335a = this.f1282b[i];
            if (j.f1292d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f1282b[i3]);
            }
            String str = this.f1283c.get(i2);
            aVar2.f1336b = str != null ? jVar.k.get(str) : null;
            aVar2.g = d.b.values()[this.f1284d[i2]];
            aVar2.h = d.b.values()[this.e[i2]];
            int[] iArr = this.f1282b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f1337c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.f1338d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.f1332b = i5;
            aVar.f1333c = i7;
            aVar.f1334d = i9;
            aVar.e = i10;
            aVar.c(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.f = this.f;
        aVar.g = this.g;
        aVar.j = this.h;
        aVar.u = this.i;
        aVar.h = true;
        aVar.k = this.j;
        aVar.l = this.k;
        aVar.m = this.l;
        aVar.n = this.m;
        aVar.o = this.n;
        aVar.p = this.o;
        aVar.q = this.p;
        aVar.j(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f1282b);
        parcel.writeStringList(this.f1283c);
        parcel.writeIntArray(this.f1284d);
        parcel.writeIntArray(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        TextUtils.writeToParcel(this.k, parcel, 0);
        parcel.writeInt(this.l);
        TextUtils.writeToParcel(this.m, parcel, 0);
        parcel.writeStringList(this.n);
        parcel.writeStringList(this.o);
        parcel.writeInt(this.p ? 1 : 0);
    }
}
