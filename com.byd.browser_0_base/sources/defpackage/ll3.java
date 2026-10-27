package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public final class ll3 extends kl3 {
    public final SparseIntArray d;
    public final Parcel e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public ll3(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new wm2(), new wm2(), new wm2());
    }

    @Override // defpackage.kl3
    public final ll3 a() {
        Parcel parcel = this.e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new ll3(parcel, iDataPosition, i, gq2.i(new StringBuilder(), this.h, "  "), this.a, this.b, this.c);
    }

    @Override // defpackage.kl3
    public final boolean e() {
        return this.e.readInt() != 0;
    }

    @Override // defpackage.kl3
    public final byte[] f() {
        Parcel parcel = this.e;
        int i = parcel.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        parcel.readByteArray(bArr);
        return bArr;
    }

    @Override // defpackage.kl3
    public final CharSequence g() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.e);
    }

    @Override // defpackage.kl3
    public final boolean h(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            int i3 = this.j;
            Parcel parcel = this.e;
            parcel.setDataPosition(i3);
            int i4 = parcel.readInt();
            this.k = parcel.readInt();
            this.j += i4;
        }
        return this.k == i;
    }

    @Override // defpackage.kl3
    public final int i() {
        return this.e.readInt();
    }

    @Override // defpackage.kl3
    public final <T extends Parcelable> T k() {
        return (T) this.e.readParcelable(ll3.class.getClassLoader());
    }

    @Override // defpackage.kl3
    public final String m() {
        return this.e.readString();
    }

    @Override // defpackage.kl3
    public final void o(int i) {
        x();
        this.i = i;
        this.d.put(i, this.e.dataPosition());
        s(0);
        s(i);
    }

    @Override // defpackage.kl3
    public final void p(boolean z) {
        this.e.writeInt(z ? 1 : 0);
    }

    @Override // defpackage.kl3
    public final void q(byte[] bArr) {
        Parcel parcel = this.e;
        if (bArr == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
    }

    @Override // defpackage.kl3
    public final void r(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.e, 0);
    }

    @Override // defpackage.kl3
    public final void s(int i) {
        this.e.writeInt(i);
    }

    @Override // defpackage.kl3
    public final void u(Parcelable parcelable) {
        this.e.writeParcelable(parcelable, 0);
    }

    @Override // defpackage.kl3
    public final void v(String str) {
        this.e.writeString(str);
    }

    public final void x() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.d.get(i);
            Parcel parcel = this.e;
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i2);
            parcel.writeInt(iDataPosition - i2);
            parcel.setDataPosition(iDataPosition);
        }
    }

    public ll3(Parcel parcel, int i, int i2, String str, wm2<String, Method> wm2Var, wm2<String, Method> wm2Var2, wm2<String, Class<?>> wm2Var3) {
        super(wm2Var, wm2Var2, wm2Var3);
        this.d = new SparseIntArray();
        this.i = -1;
        this.k = -1;
        this.e = parcel;
        this.f = i;
        this.g = i2;
        this.j = i;
        this.h = str;
    }
}
