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
    final int[] f974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<String> f975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int[] f976d;
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
        int size = aVar.f1036a.size();
        this.f974b = new int[size * 5];
        if (!aVar.h) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f975c = new ArrayList<>(size);
        this.f976d = new int[size];
        this.e = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            j.a aVar2 = aVar.f1036a.get(i);
            int i3 = i2 + 1;
            this.f974b[i2] = aVar2.f1040a;
            ArrayList<String> arrayList = this.f975c;
            Fragment fragment = aVar2.f1041b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f974b;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.f1042c;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.f1043d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            iArr[i6] = aVar2.f;
            this.f976d[i] = aVar2.g.ordinal();
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

    public androidx.fragment.app.a f(h hVar) {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(hVar);
        int i = 0;
        int i2 = 0;
        while (i < this.f974b.length) {
            j.a aVar2 = new j.a();
            int i3 = i + 1;
            aVar2.f1040a = this.f974b[i];
            if (h.f1007d) {
                Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i2 + " base fragment #" + this.f974b[i3]);
            }
            String str = this.f975c.get(i2);
            if (str != null) {
                aVar2.f1041b = hVar.k.get(str);
            } else {
                aVar2.f1041b = null;
            }
            aVar2.g = Lifecycle.State.values()[this.f976d[i2]];
            aVar2.h = Lifecycle.State.values()[this.e[i2]];
            int[] iArr = this.f974b;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            aVar2.f1042c = i5;
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            aVar2.f1043d = i7;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            aVar2.e = i9;
            int i10 = iArr[i8];
            aVar2.f = i10;
            aVar.f1037b = i5;
            aVar.f1038c = i7;
            aVar.f1039d = i9;
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
        parcel.writeIntArray(this.f974b);
        parcel.writeStringList(this.f975c);
        parcel.writeIntArray(this.f976d);
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
        this.f974b = parcel.createIntArray();
        this.f975c = parcel.createStringArrayList();
        this.f976d = parcel.createIntArray();
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
