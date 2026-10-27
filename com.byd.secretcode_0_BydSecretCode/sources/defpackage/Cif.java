package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: if, reason: invalid class name */
/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class Cif extends hf {
    public final SparseIntArray d;
    public final Parcel e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public Cif(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new a4(), new a4(), new a4());
    }

    @Override // defpackage.hf
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.e.writeInt(-1);
        } else {
            this.e.writeInt(bArr.length);
            this.e.writeByteArray(bArr);
        }
    }

    @Override // defpackage.hf
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.e, 0);
    }

    @Override // defpackage.hf
    public void E(int i) {
        this.e.writeInt(i);
    }

    @Override // defpackage.hf
    public void G(Parcelable parcelable) {
        this.e.writeParcelable(parcelable, 0);
    }

    @Override // defpackage.hf
    public void I(String str) {
        this.e.writeString(str);
    }

    @Override // defpackage.hf
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.d.get(i);
            int iDataPosition = this.e.dataPosition();
            this.e.setDataPosition(i2);
            this.e.writeInt(iDataPosition - i2);
            this.e.setDataPosition(iDataPosition);
        }
    }

    @Override // defpackage.hf
    public hf b() {
        Parcel parcel = this.e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new Cif(parcel, iDataPosition, i, this.h + "  ", this.a, this.b, this.c);
    }

    @Override // defpackage.hf
    public boolean g() {
        return this.e.readInt() != 0;
    }

    @Override // defpackage.hf
    public byte[] i() {
        int i = this.e.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.e.readByteArray(bArr);
        return bArr;
    }

    @Override // defpackage.hf
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.e);
    }

    @Override // defpackage.hf
    public boolean m(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.e.setDataPosition(this.j);
            int i3 = this.e.readInt();
            this.k = this.e.readInt();
            this.j += i3;
        }
        return this.k == i;
    }

    @Override // defpackage.hf
    public int o() {
        return this.e.readInt();
    }

    @Override // defpackage.hf
    public <T extends Parcelable> T q() {
        return (T) this.e.readParcelable(Cif.class.getClassLoader());
    }

    @Override // defpackage.hf
    public String s() {
        return this.e.readString();
    }

    @Override // defpackage.hf
    public void w(int i) {
        a();
        this.i = i;
        this.d.put(i, this.e.dataPosition());
        E(0);
        E(i);
    }

    @Override // defpackage.hf
    public void y(boolean z) {
        this.e.writeInt(z ? 1 : 0);
    }

    public Cif(Parcel parcel, int i, int i2, String str, a4<String, Method> a4Var, a4<String, Method> a4Var2, a4<String, Class> a4Var3) {
        super(a4Var, a4Var2, a4Var3);
        this.d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.e = parcel;
        this.f = i;
        this.g = i2;
        this.j = i;
        this.h = str;
    }
}
