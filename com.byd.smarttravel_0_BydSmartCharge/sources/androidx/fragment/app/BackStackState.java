package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.k.a.h;
import b.k.a.j;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<String> f372c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f373d;
    public final int[] e;
    public final int f;
    public final int g;
    public final String h;
    public final int i;
    public final int j;
    public final CharSequence k;
    public final int l;
    public final CharSequence m;
    public final ArrayList<String> n;
    public final ArrayList<String> o;
    public final boolean p;

    public static class a implements Parcelable.Creator<BackStackState> {
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

    public BackStackState(b.k.a.a aVar) {
        int size = aVar.f1252a.size();
        this.f371b = new int[size * 5];
        if (!aVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f372c = new ArrayList<>(size);
        this.f373d = new int[size];
        this.e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            j.a aVar2 = aVar.f1252a.get(i);
            int i3 = i2 + 1;
            this.f371b[i2] = aVar2.f1256a;
            ArrayList<String> arrayList = this.f372c;
            Fragment fragment = aVar2.f1257b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f371b;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1258c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1259d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            iArr[i6] = aVar2.f;
            this.f373d[i] = aVar2.g.ordinal();
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

    public b.k.a.a f(h hVar) {
        b.k.a.a aVar = new b.k.a.a(hVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f371b.length) {
            j.a aVar2 = new j.a();
            int i3 = i + 1;
            aVar2.f1256a = this.f371b[i];
            if (h.f1223d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f371b[i3]);
            }
            String str = this.f372c.get(i2);
            if (str != null) {
                aVar2.f1257b = hVar.k.get(str);
            } else {
                aVar2.f1257b = null;
            }
            aVar2.g = Lifecycle.State.values()[this.f373d[i2]];
            aVar2.h = Lifecycle.State.values()[this.e[i2]];
            int[] iArr = this.f371b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f1258c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.f1259d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.f1253b = i5;
            aVar.f1254c = i7;
            aVar.f1255d = i9;
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
        aVar.h(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f371b);
        parcel.writeStringList(this.f372c);
        parcel.writeIntArray(this.f373d);
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

    public BackStackState(Parcel parcel) {
        this.f371b = parcel.createIntArray();
        this.f372c = parcel.createStringArrayList();
        this.f373d = parcel.createIntArray();
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
}
