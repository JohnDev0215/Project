package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import defpackage.hb;
import defpackage.ob;
import defpackage.qb;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();
    public final int[] b;
    public final ArrayList<String> c;
    public final int[] d;
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

    public BackStackState(hb hbVar) {
        int size = hbVar.a.size();
        this.b = new int[size * 5];
        if (!hbVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.c = new ArrayList<>(size);
        this.d = new int[size];
        this.e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            qb.a aVar = hbVar.a.get(i);
            int i3 = i2 + 1;
            this.b[i2] = aVar.a;
            ArrayList<String> arrayList = this.c;
            Fragment fragment = aVar.b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.b;
            int i4 = i3 + 1;
            iArr[i3] = aVar.c;
            int i5 = i4 + 1;
            iArr[i4] = aVar.d;
            int i6 = i5 + 1;
            iArr[i5] = aVar.e;
            iArr[i6] = aVar.f;
            this.d[i] = aVar.g.ordinal();
            this.e[i] = aVar.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.f = hbVar.f;
        this.g = hbVar.g;
        this.h = hbVar.j;
        this.i = hbVar.u;
        this.j = hbVar.k;
        this.k = hbVar.l;
        this.l = hbVar.m;
        this.m = hbVar.n;
        this.n = hbVar.o;
        this.o = hbVar.p;
        this.p = hbVar.q;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public hb f(ob obVar) {
        hb hbVar = new hb(obVar);
        int i = 0;
        int i2 = 0;
        while (i < this.b.length) {
            qb.a aVar = new qb.a();
            int i3 = i + 1;
            aVar.a = this.b[i];
            if (ob.d) {
                Log.v("FragmentManager", "Instantiate " + hbVar + " op #" + i2 + " base fragment #" + this.b[i3]);
            }
            String str = this.c.get(i2);
            if (str != null) {
                aVar.b = obVar.k.get(str);
            } else {
                aVar.b = null;
            }
            aVar.g = Lifecycle.State.values()[this.d[i2]];
            aVar.h = Lifecycle.State.values()[this.e[i2]];
            int[] iArr = this.b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar.c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar.e = i9;
            int i10 = iArr[i8];
            aVar.f = i10;
            hbVar.b = i5;
            hbVar.c = i7;
            hbVar.d = i9;
            hbVar.e = i10;
            hbVar.c(aVar);
            i2++;
            i = i8 + 1;
        }
        hbVar.f = this.f;
        hbVar.g = this.g;
        hbVar.j = this.h;
        hbVar.u = this.i;
        hbVar.h = true;
        hbVar.k = this.j;
        hbVar.l = this.k;
        hbVar.m = this.l;
        hbVar.n = this.m;
        hbVar.o = this.n;
        hbVar.p = this.o;
        hbVar.q = this.p;
        hbVar.h(1);
        return hbVar;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.b);
        parcel.writeStringList(this.c);
        parcel.writeIntArray(this.d);
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
        this.b = parcel.createIntArray();
        this.c = parcel.createStringArrayList();
        this.d = parcel.createIntArray();
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
