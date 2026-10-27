package p;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class uk implements Parcelable {
    public static final Parcelable.Creator<uk> CREATOR = new tk(0);
    public final int[] c;
    public final ArrayList e;
    public final int[] k;
    public final int[] l;
    public final int m;
    public final String n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f196p;
    public final CharSequence q;
    public final int r;
    public final CharSequence s;
    public final ArrayList t;
    public final ArrayList u;
    public final boolean v;

    public uk(sk skVar) {
        int size = skVar.a.size();
        this.c = new int[size * 6];
        if (!skVar.g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.e = new ArrayList(size);
        this.k = new int[size];
        this.l = new int[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            vx0 vx0Var = (vx0) skVar.a.get(i2);
            int i3 = i + 1;
            this.c[i] = vx0Var.a;
            ArrayList arrayList = this.e;
            ww0 ww0Var = vx0Var.b;
            arrayList.add(ww0Var != null ? ww0Var.m : null);
            int[] iArr = this.c;
            iArr[i3] = vx0Var.c ? 1 : 0;
            iArr[i + 2] = vx0Var.d;
            iArr[i + 3] = vx0Var.e;
            int i4 = i + 5;
            iArr[i + 4] = vx0Var.f;
            i += 6;
            iArr[i4] = vx0Var.g;
            this.k[i2] = vx0Var.h.ordinal();
            this.l[i2] = vx0Var.i.ordinal();
        }
        this.m = skVar.f;
        this.n = skVar.h;
        this.o = skVar.r;
        this.f196p = skVar.i;
        this.q = skVar.j;
        this.r = skVar.k;
        this.s = skVar.l;
        this.t = skVar.m;
        this.u = skVar.n;
        this.v = skVar.o;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.c);
        parcel.writeStringList(this.e);
        parcel.writeIntArray(this.k);
        parcel.writeIntArray(this.l);
        parcel.writeInt(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.f196p);
        TextUtils.writeToParcel(this.q, parcel, 0);
        parcel.writeInt(this.r);
        TextUtils.writeToParcel(this.s, parcel, 0);
        parcel.writeStringList(this.t);
        parcel.writeStringList(this.u);
        parcel.writeInt(this.v ? 1 : 0);
    }

    public uk(Parcel parcel) {
        this.c = parcel.createIntArray();
        this.e = parcel.createStringArrayList();
        this.k = parcel.createIntArray();
        this.l = parcel.createIntArray();
        this.m = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readInt();
        this.f196p = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.q = (CharSequence) creator.createFromParcel(parcel);
        this.r = parcel.readInt();
        this.s = (CharSequence) creator.createFromParcel(parcel);
        this.t = parcel.createStringArrayList();
        this.u = parcel.createStringArrayList();
        this.v = parcel.readInt() != 0;
    }
}
