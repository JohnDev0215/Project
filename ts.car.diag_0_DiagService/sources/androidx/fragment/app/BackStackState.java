package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.r;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int[] f611c;
    final ArrayList<String> d;
    final int[] e;
    final int[] f;
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

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<BackStackState> {
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
        int size = aVar.f711a.size();
        this.f611c = new int[size * 5];
        if (!aVar.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.d = new ArrayList<>(size);
        this.e = new int[size];
        this.f = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            r.a aVar2 = aVar.f711a.get(i);
            int i3 = i2 + 1;
            this.f611c[i2] = aVar2.f714a;
            ArrayList<String> arrayList = this.d;
            Fragment fragment = aVar2.f715b;
            arrayList.add(fragment != null ? fragment.h : null);
            int[] iArr = this.f611c;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f716c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            iArr[i6] = aVar2.f;
            this.e[i] = aVar2.g.ordinal();
            this.f[i] = aVar2.h.ordinal();
            i++;
            i2 = i6 + 1;
        }
        this.g = aVar.f;
        this.h = aVar.h;
        this.i = aVar.s;
        this.j = aVar.i;
        this.k = aVar.j;
        this.l = aVar.k;
        this.m = aVar.l;
        this.n = aVar.m;
        this.o = aVar.n;
        this.p = aVar.o;
    }

    public androidx.fragment.app.a a(FragmentManager fragmentManager) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(fragmentManager);
        int i = 0;
        int i2 = 0;
        while (i < this.f611c.length) {
            r.a aVar2 = new r.a();
            int i3 = i + 1;
            aVar2.f714a = this.f611c[i];
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f611c[i3]);
            }
            String str = this.d.get(i2);
            if (str != null) {
                aVar2.f715b = fragmentManager.f0(str);
            } else {
                aVar2.f715b = null;
            }
            aVar2.g = Lifecycle.State.values()[this.e[i2]];
            aVar2.h = Lifecycle.State.values()[this.f[i2]];
            int[] iArr = this.f611c;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f716c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.f712b = i5;
            aVar.f713c = i7;
            aVar.d = i9;
            aVar.e = i10;
            aVar.e(aVar2);
            i2++;
            i = i8 + 1;
        }
        aVar.f = this.g;
        aVar.h = this.h;
        aVar.s = this.i;
        aVar.g = true;
        aVar.i = this.j;
        aVar.j = this.k;
        aVar.k = this.l;
        aVar.l = this.m;
        aVar.m = this.n;
        aVar.n = this.o;
        aVar.o = this.p;
        aVar.m(1);
        return aVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f611c);
        parcel.writeStringList(this.d);
        parcel.writeIntArray(this.e);
        parcel.writeIntArray(this.f);
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
        this.f611c = parcel.createIntArray();
        this.d = parcel.createStringArrayList();
        this.e = parcel.createIntArray();
        this.f = parcel.createIntArray();
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
