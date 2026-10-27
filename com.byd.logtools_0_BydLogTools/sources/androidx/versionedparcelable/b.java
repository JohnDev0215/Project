package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SparseIntArray f1965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Parcel f1966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f1967f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f1968g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f1969h;
    private int i;
    private int j;
    private int k;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), HttpUrl.FRAGMENT_ENCODE_SET, new b.d.a(), new b.d.a(), new b.d.a());
    }

    @Override // androidx.versionedparcelable.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f1966e.writeInt(-1);
        } else {
            this.f1966e.writeInt(bArr.length);
            this.f1966e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f1966e, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void E(int i) {
        this.f1966e.writeInt(i);
    }

    @Override // androidx.versionedparcelable.a
    public void G(Parcelable parcelable) {
        this.f1966e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void I(String str) {
        this.f1966e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.f1965d.get(i);
            int iDataPosition = this.f1966e.dataPosition();
            this.f1966e.setDataPosition(i2);
            this.f1966e.writeInt(iDataPosition - i2);
            this.f1966e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f1966e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f1967f) {
            i = this.f1968g;
        }
        return new b(parcel, iDataPosition, i, this.f1969h + "  ", this.f1962a, this.f1963b, this.f1964c);
    }

    @Override // androidx.versionedparcelable.a
    public boolean g() {
        return this.f1966e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] i() {
        int i = this.f1966e.readInt();
        if (i < 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        this.f1966e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f1966e);
    }

    @Override // androidx.versionedparcelable.a
    public boolean m(int i) {
        while (this.j < this.f1968g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.f1966e.setDataPosition(this.j);
            int i3 = this.f1966e.readInt();
            this.k = this.f1966e.readInt();
            this.j += i3;
        }
        return this.k == i;
    }

    @Override // androidx.versionedparcelable.a
    public int o() {
        return this.f1966e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T q() {
        return (T) this.f1966e.readParcelable(b.class.getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String s() {
        return this.f1966e.readString();
    }

    @Override // androidx.versionedparcelable.a
    public void w(int i) {
        a();
        this.i = i;
        this.f1965d.put(i, this.f1966e.dataPosition());
        E(0);
        E(i);
    }

    @Override // androidx.versionedparcelable.a
    public void y(boolean z) {
        this.f1966e.writeInt(z ? 1 : 0);
    }

    private b(Parcel parcel, int i, int i2, String str, b.d.a<String, Method> aVar, b.d.a<String, Method> aVar2, b.d.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1965d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.f1966e = parcel;
        this.f1967f = i;
        this.f1968g = i2;
        this.j = i;
        this.f1969h = str;
    }
}
