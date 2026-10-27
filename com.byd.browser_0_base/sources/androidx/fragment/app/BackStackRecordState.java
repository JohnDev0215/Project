package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = new a();
    public final int[] e;
    public final ArrayList<String> f;
    public final int[] g;
    public final int[] h;
    public final int i;
    public final String j;
    public final int k;
    public final int l;
    public final CharSequence m;
    public final int n;
    public final CharSequence o;
    public final ArrayList<String> p;
    public final ArrayList<String> q;
    public final boolean r;

    public class a implements Parcelable.Creator<BackStackRecordState> {
        @Override // android.os.Parcelable.Creator
        public final BackStackRecordState createFromParcel(Parcel parcel) {
            return new BackStackRecordState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final BackStackRecordState[] newArray(int i) {
            return new BackStackRecordState[i];
        }
    }

    public BackStackRecordState(androidx.fragment.app.a aVar) {
        int size = aVar.a.size();
        this.e = new int[size * 6];
        if (!aVar.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f = new ArrayList<>(size);
        this.g = new int[size];
        this.h = new int[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            i.a aVar2 = aVar.a.get(i);
            int i3 = i2 + 1;
            this.e[i2] = aVar2.a;
            ArrayList<String> arrayList = this.f;
            Fragment fragment = aVar2.b;
            arrayList.add(fragment != null ? fragment.i : null);
            int[] iArr = this.e;
            int i4 = i3 + 1;
            iArr[i3] = aVar2.c ? 1 : 0;
            int i5 = i4 + 1;
            iArr[i4] = aVar2.d;
            int i6 = i5 + 1;
            iArr[i5] = aVar2.e;
            int i7 = i6 + 1;
            iArr[i6] = aVar2.f;
            iArr[i7] = aVar2.g;
            this.g[i] = aVar2.h.ordinal();
            this.h[i] = aVar2.i.ordinal();
            i++;
            i2 = i7 + 1;
        }
        this.i = aVar.f;
        this.j = aVar.i;
        this.k = aVar.t;
        this.l = aVar.j;
        this.m = aVar.k;
        this.n = aVar.l;
        this.o = aVar.m;
        this.p = aVar.n;
        this.q = aVar.o;
        this.r = aVar.p;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.e);
        parcel.writeStringList(this.f);
        parcel.writeIntArray(this.g);
        parcel.writeIntArray(this.h);
        parcel.writeInt(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        TextUtils.writeToParcel(this.m, parcel, 0);
        parcel.writeInt(this.n);
        TextUtils.writeToParcel(this.o, parcel, 0);
        parcel.writeStringList(this.p);
        parcel.writeStringList(this.q);
        parcel.writeInt(this.r ? 1 : 0);
    }

    public BackStackRecordState(Parcel parcel) {
        this.e = parcel.createIntArray();
        this.f = parcel.createStringArrayList();
        this.g = parcel.createIntArray();
        this.h = parcel.createIntArray();
        this.i = parcel.readInt();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.n = parcel.readInt();
        this.o = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.p = parcel.createStringArrayList();
        this.q = parcel.createStringArrayList();
        this.r = parcel.readInt() != 0;
    }
}
