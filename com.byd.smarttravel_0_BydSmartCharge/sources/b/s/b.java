package b.s;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f1569d;
    public final Parcel e;
    public final int f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new b.e.a(), new b.e.a(), new b.e.a());
    }

    @Override // b.s.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.e.writeInt(-1);
        } else {
            this.e.writeInt(bArr.length);
            this.e.writeByteArray(bArr);
        }
    }

    @Override // b.s.a
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.e, 0);
    }

    @Override // b.s.a
    public void E(int i) {
        this.e.writeInt(i);
    }

    @Override // b.s.a
    public void G(Parcelable parcelable) {
        this.e.writeParcelable(parcelable, 0);
    }

    @Override // b.s.a
    public void I(String str) {
        this.e.writeString(str);
    }

    @Override // b.s.a
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.f1569d.get(i);
            int iDataPosition = this.e.dataPosition();
            this.e.setDataPosition(i2);
            this.e.writeInt(iDataPosition - i2);
            this.e.setDataPosition(iDataPosition);
        }
    }

    @Override // b.s.a
    public a b() {
        Parcel parcel = this.e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f) {
            i = this.g;
        }
        return new b(parcel, iDataPosition, i, this.h + "  ", this.f1566a, this.f1567b, this.f1568c);
    }

    @Override // b.s.a
    public boolean g() {
        return this.e.readInt() != 0;
    }

    @Override // b.s.a
    public byte[] i() {
        int i = this.e.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.e.readByteArray(bArr);
        return bArr;
    }

    @Override // b.s.a
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.e);
    }

    @Override // b.s.a
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

    @Override // b.s.a
    public int o() {
        return this.e.readInt();
    }

    @Override // b.s.a
    public <T extends Parcelable> T q() {
        return (T) this.e.readParcelable(b.class.getClassLoader());
    }

    @Override // b.s.a
    public String s() {
        return this.e.readString();
    }

    @Override // b.s.a
    public void w(int i) {
        a();
        this.i = i;
        this.f1569d.put(i, this.e.dataPosition());
        E(0);
        E(i);
    }

    @Override // b.s.a
    public void y(boolean z) {
        this.e.writeInt(z ? 1 : 0);
    }

    public b(Parcel parcel, int i, int i2, String str, b.e.a<String, Method> aVar, b.e.a<String, Method> aVar2, b.e.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1569d = new SparseIntArray();
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
