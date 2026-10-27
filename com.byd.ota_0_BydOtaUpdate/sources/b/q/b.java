package b.q;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f2187d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f2188e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2189f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2190g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f2191h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2192i;
    public int j;
    public int k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new b.d.a(), new b.d.a(), new b.d.a());
    }

    @Override // b.q.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f2188e.writeInt(-1);
        } else {
            this.f2188e.writeInt(bArr.length);
            this.f2188e.writeByteArray(bArr);
        }
    }

    @Override // b.q.a
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f2188e, 0);
    }

    @Override // b.q.a
    public void E(int i2) {
        this.f2188e.writeInt(i2);
    }

    @Override // b.q.a
    public void G(Parcelable parcelable) {
        this.f2188e.writeParcelable(parcelable, 0);
    }

    @Override // b.q.a
    public void I(String str) {
        this.f2188e.writeString(str);
    }

    @Override // b.q.a
    public void a() {
        int i2 = this.f2192i;
        if (i2 >= 0) {
            int i3 = this.f2187d.get(i2);
            int iDataPosition = this.f2188e.dataPosition();
            this.f2188e.setDataPosition(i3);
            this.f2188e.writeInt(iDataPosition - i3);
            this.f2188e.setDataPosition(iDataPosition);
        }
    }

    @Override // b.q.a
    public a b() {
        Parcel parcel = this.f2188e;
        int iDataPosition = parcel.dataPosition();
        int i2 = this.j;
        if (i2 == this.f2189f) {
            i2 = this.f2190g;
        }
        return new b(parcel, iDataPosition, i2, this.f2191h + "  ", this.f2184a, this.f2185b, this.f2186c);
    }

    @Override // b.q.a
    public boolean g() {
        return this.f2188e.readInt() != 0;
    }

    @Override // b.q.a
    public byte[] i() {
        int i2 = this.f2188e.readInt();
        if (i2 < 0) {
            return null;
        }
        byte[] bArr = new byte[i2];
        this.f2188e.readByteArray(bArr);
        return bArr;
    }

    @Override // b.q.a
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f2188e);
    }

    @Override // b.q.a
    public boolean m(int i2) {
        while (this.j < this.f2190g) {
            int i3 = this.k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.f2188e.setDataPosition(this.j);
            int i4 = this.f2188e.readInt();
            this.k = this.f2188e.readInt();
            this.j += i4;
        }
        return this.k == i2;
    }

    @Override // b.q.a
    public int o() {
        return this.f2188e.readInt();
    }

    @Override // b.q.a
    public <T extends Parcelable> T q() {
        return (T) this.f2188e.readParcelable(b.class.getClassLoader());
    }

    @Override // b.q.a
    public String s() {
        return this.f2188e.readString();
    }

    @Override // b.q.a
    public void w(int i2) {
        a();
        this.f2192i = i2;
        this.f2187d.put(i2, this.f2188e.dataPosition());
        E(0);
        E(i2);
    }

    @Override // b.q.a
    public void y(boolean z) {
        this.f2188e.writeInt(z ? 1 : 0);
    }

    public b(Parcel parcel, int i2, int i3, String str, b.d.a<String, Method> aVar, b.d.a<String, Method> aVar2, b.d.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f2187d = new SparseIntArray();
        this.f2192i = -1;
        this.j = 0;
        this.k = -1;
        this.f2188e = parcel;
        this.f2189f = i2;
        this.f2190g = i3;
        this.j = i2;
        this.f2191h = str;
    }
}
