package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import defpackage.S0;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
final class b extends a {
    private final SparseIntArray d;
    private final Parcel e;
    private final int f;
    private final int g;
    private final String h;
    private int i;
    private int j;
    private int k;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new S0(), new S0(), new S0());
    }

    @Override // androidx.versionedparcelable.a
    public final void B(int i) {
        this.e.writeInt(i);
    }

    @Override // androidx.versionedparcelable.a
    public final void D(Parcelable parcelable) {
        this.e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public final void F(String str) {
        this.e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public final void a() {
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

    @Override // androidx.versionedparcelable.a
    protected final a b() {
        Parcel parcel = this.e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new b(parcel, iDataPosition, i, this.h + "  ", this.a, this.b, this.c);
    }

    @Override // androidx.versionedparcelable.a
    public final boolean f() {
        return this.e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public final byte[] h() {
        Parcel parcel = this.e;
        int i = parcel.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        parcel.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected final CharSequence j() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.e);
    }

    @Override // androidx.versionedparcelable.a
    public final boolean l(int i) {
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

    @Override // androidx.versionedparcelable.a
    public final int m() {
        return this.e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public final Parcelable o() {
        return this.e.readParcelable(b.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public final String q() {
        return this.e.readString();
    }

    @Override // androidx.versionedparcelable.a
    public final void u(int i) {
        a();
        this.i = i;
        this.d.put(i, this.e.dataPosition());
        B(0);
        B(i);
    }

    @Override // androidx.versionedparcelable.a
    public final void w(boolean z) {
        this.e.writeInt(z ? 1 : 0);
    }

    @Override // androidx.versionedparcelable.a
    public final void x(byte[] bArr) {
        Parcel parcel = this.e;
        if (bArr == null) {
            parcel.writeInt(-1);
        } else {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected final void z(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.e, 0);
    }

    private b(Parcel parcel, int i, int i2, String str, S0 s0, S0 s02, S0 s03) {
        super(s0, s02, s03);
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
