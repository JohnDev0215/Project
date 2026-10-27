package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SparseIntArray f2045d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Parcel f2046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f2047f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2048g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f2049h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f2050i;
    private int j;
    private int k;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new b.d.a(), new b.d.a(), new b.d.a());
    }

    @Override // androidx.versionedparcelable.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f2046e.writeInt(-1);
        } else {
            this.f2046e.writeInt(bArr.length);
            this.f2046e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f2046e, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void E(int i2) {
        this.f2046e.writeInt(i2);
    }

    @Override // androidx.versionedparcelable.a
    public void G(Parcelable parcelable) {
        this.f2046e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void I(String str) {
        this.f2046e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i2 = this.f2050i;
        if (i2 >= 0) {
            int i3 = this.f2045d.get(i2);
            int iDataPosition = this.f2046e.dataPosition();
            this.f2046e.setDataPosition(i3);
            this.f2046e.writeInt(iDataPosition - i3);
            this.f2046e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f2046e;
        int iDataPosition = parcel.dataPosition();
        int i2 = this.j;
        if (i2 == this.f2047f) {
            i2 = this.f2048g;
        }
        return new b(parcel, iDataPosition, i2, this.f2049h + "  ", this.f2042a, this.f2043b, this.f2044c);
    }

    @Override // androidx.versionedparcelable.a
    public boolean g() {
        return this.f2046e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] i() {
        int i2 = this.f2046e.readInt();
        if (i2 < 0) {
            return null;
        }
        byte[] bArr = new byte[i2];
        this.f2046e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f2046e);
    }

    @Override // androidx.versionedparcelable.a
    public boolean m(int i2) {
        while (this.j < this.f2048g) {
            int i3 = this.k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.f2046e.setDataPosition(this.j);
            int i4 = this.f2046e.readInt();
            this.k = this.f2046e.readInt();
            this.j += i4;
        }
        return this.k == i2;
    }

    @Override // androidx.versionedparcelable.a
    public int o() {
        return this.f2046e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T q() {
        return (T) this.f2046e.readParcelable(b.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String s() {
        return this.f2046e.readString();
    }

    @Override // androidx.versionedparcelable.a
    public void w(int i2) {
        a();
        this.f2050i = i2;
        this.f2045d.put(i2, this.f2046e.dataPosition());
        E(0);
        E(i2);
    }

    @Override // androidx.versionedparcelable.a
    public void y(boolean z) {
        this.f2046e.writeInt(z ? 1 : 0);
    }

    private b(Parcel parcel, int i2, int i3, String str, b.d.a<String, Method> aVar, b.d.a<String, Method> aVar2, b.d.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f2045d = new SparseIntArray();
        this.f2050i = -1;
        this.j = 0;
        this.k = -1;
        this.f2046e = parcel;
        this.f2047f = i2;
        this.f2048g = i3;
        this.j = i2;
        this.f2049h = str;
    }
}
