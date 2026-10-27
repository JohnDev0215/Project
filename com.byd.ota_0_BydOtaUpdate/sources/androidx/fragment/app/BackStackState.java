package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.j.a.g;
import b.j.a.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();
    public final int E3;
    public final String F3;
    public final int G3;
    public final int H3;
    public final CharSequence I3;
    public final int J3;
    public final CharSequence K3;
    public final ArrayList<String> L3;
    public final ArrayList<String> M3;
    public final boolean N3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f491c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList<String> f492d;
    public final int[] s;
    public final int[] x;
    public final int y;

    public static class a implements Parcelable.Creator<BackStackState> {
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

    public BackStackState(b.j.a.a aVar) {
        int size = aVar.f1904a.size();
        this.f491c = new int[size * 5];
        if (!aVar.f1911h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f492d = new ArrayList<>(size);
        this.s = new int[size];
        this.x = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            i.a aVar2 = aVar.f1904a.get(i2);
            int i4 = i3 + 1;
            this.f491c[i3] = aVar2.f1913a;
            ArrayList<String> arrayList = this.f492d;
            Fragment fragment = aVar2.f1914b;
            arrayList.add(fragment != null ? fragment.E3 : null);
            int[] iArr = this.f491c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1915c;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.f1916d;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.f1917e;
            iArr[i7] = aVar2.f1918f;
            this.s[i2] = aVar2.f1919g.ordinal();
            this.x[i2] = aVar2.f1920h.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.y = aVar.f1909f;
        this.E3 = aVar.f1910g;
        this.F3 = aVar.j;
        this.G3 = aVar.u;
        this.H3 = aVar.k;
        this.I3 = aVar.l;
        this.J3 = aVar.m;
        this.K3 = aVar.n;
        this.L3 = aVar.o;
        this.M3 = aVar.p;
        this.N3 = aVar.q;
    }

    public b.j.a.a a(g gVar) {
        b.j.a.a aVar = new b.j.a.a(gVar);
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.f491c.length) {
            i.a aVar2 = new i.a();
            int i4 = i2 + 1;
            aVar2.f1913a = this.f491c[i2];
            if (g.s) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i3 + " base fragment #" + this.f491c[i4]);
            }
            String str = this.f492d.get(i3);
            if (str != null) {
                aVar2.f1914b = gVar.I3.get(str);
            } else {
                aVar2.f1914b = null;
            }
            aVar2.f1919g = Lifecycle.State.values()[this.s[i3]];
            aVar2.f1920h = Lifecycle.State.values()[this.x[i3]];
            int[] iArr = this.f491c;
            int i5 = i4 + 1;
            int i6 = iArr[i4];
            aVar2.f1915c = i6;
            int i7 = i5 + 1;
            int i8 = iArr[i5];
            aVar2.f1916d = i8;
            int i9 = i7 + 1;
            int i10 = iArr[i7];
            aVar2.f1917e = i10;
            int i11 = iArr[i9];
            aVar2.f1918f = i11;
            aVar.f1905b = i6;
            aVar.f1906c = i8;
            aVar.f1907d = i10;
            aVar.f1908e = i11;
            aVar.c(aVar2);
            i3++;
            i2 = i9 + 1;
        }
        aVar.f1909f = this.y;
        aVar.f1910g = this.E3;
        aVar.j = this.F3;
        aVar.u = this.G3;
        aVar.f1911h = true;
        aVar.k = this.H3;
        aVar.l = this.I3;
        aVar.m = this.J3;
        aVar.n = this.K3;
        aVar.o = this.L3;
        aVar.p = this.M3;
        aVar.q = this.N3;
        aVar.h(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f491c);
        parcel.writeStringList(this.f492d);
        parcel.writeIntArray(this.s);
        parcel.writeIntArray(this.x);
        parcel.writeInt(this.y);
        parcel.writeInt(this.E3);
        parcel.writeString(this.F3);
        parcel.writeInt(this.G3);
        parcel.writeInt(this.H3);
        TextUtils.writeToParcel(this.I3, parcel, 0);
        parcel.writeInt(this.J3);
        TextUtils.writeToParcel(this.K3, parcel, 0);
        parcel.writeStringList(this.L3);
        parcel.writeStringList(this.M3);
        parcel.writeInt(this.N3 ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.f491c = parcel.createIntArray();
        this.f492d = parcel.createStringArrayList();
        this.s = parcel.createIntArray();
        this.x = parcel.createIntArray();
        this.y = parcel.readInt();
        this.E3 = parcel.readInt();
        this.F3 = parcel.readString();
        this.G3 = parcel.readInt();
        this.H3 = parcel.readInt();
        this.I3 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.J3 = parcel.readInt();
        this.K3 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.L3 = parcel.createStringArrayList();
        this.M3 = parcel.createStringArrayList();
        this.N3 = parcel.readInt() != 0;
    }
}
