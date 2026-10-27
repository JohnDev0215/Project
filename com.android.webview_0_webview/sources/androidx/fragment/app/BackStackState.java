package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.B3;
import defpackage.C3;
import defpackage.D3;
import defpackage.K3;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new D3();
    public final CharSequence A;
    public final ArrayList B;
    public final ArrayList C;
    public final boolean D;
    public final int[] s;
    public final int t;
    public final int u;
    public final String v;
    public final int w;
    public final int x;
    public final CharSequence y;
    public final int z;

    public BackStackState(C3 c3) {
        int size = c3.b.size();
        this.s = new int[size * 6];
        if (!c3.i) {
            throw new IllegalStateException("Not on back stack");
        }
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            B3 b3 = (B3) c3.b.get(i2);
            int[] iArr = this.s;
            int i3 = i + 1;
            iArr[i] = b3.a;
            int i4 = i3 + 1;
            K3 k3 = b3.b;
            iArr[i3] = k3 != null ? k3.v : -1;
            int[] iArr2 = this.s;
            int i5 = i4 + 1;
            iArr2[i4] = b3.c;
            int i6 = i5 + 1;
            iArr2[i5] = b3.d;
            int i7 = i6 + 1;
            iArr2[i6] = b3.e;
            i = i7 + 1;
            iArr2[i7] = b3.f;
        }
        this.t = c3.g;
        this.u = c3.h;
        this.v = c3.j;
        this.w = c3.l;
        this.x = c3.m;
        this.y = c3.n;
        this.z = c3.o;
        this.A = c3.p;
        this.B = c3.q;
        this.C = c3.r;
        this.D = c3.s;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeString(this.v);
        parcel.writeInt(this.w);
        parcel.writeInt(this.x);
        TextUtils.writeToParcel(this.y, parcel, 0);
        parcel.writeInt(this.z);
        TextUtils.writeToParcel(this.A, parcel, 0);
        parcel.writeStringList(this.B);
        parcel.writeStringList(this.C);
        parcel.writeInt(this.D ? 1 : 0);
    }

    public BackStackState(Parcel parcel) {
        this.s = parcel.createIntArray();
        this.t = parcel.readInt();
        this.u = parcel.readInt();
        this.v = parcel.readString();
        this.w = parcel.readInt();
        this.x = parcel.readInt();
        this.y = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.z = parcel.readInt();
        this.A = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.B = parcel.createStringArrayList();
        this.C = parcel.createStringArrayList();
        this.D = parcel.readInt() != 0;
    }
}
