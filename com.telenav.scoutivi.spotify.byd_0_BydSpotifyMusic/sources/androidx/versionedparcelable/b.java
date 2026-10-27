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
    private final SparseIntArray f3207d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Parcel f3208e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f3211h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3212i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f3213j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f3214k;

    b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new n.a(), new n.a(), new n.a());
    }

    @Override // androidx.versionedparcelable.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f3208e.writeInt(-1);
        } else {
            this.f3208e.writeInt(bArr.length);
            this.f3208e.writeByteArray(bArr);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f3208e, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void E(int i7) {
        this.f3208e.writeInt(i7);
    }

    @Override // androidx.versionedparcelable.a
    public void G(Parcelable parcelable) {
        this.f3208e.writeParcelable(parcelable, 0);
    }

    @Override // androidx.versionedparcelable.a
    public void I(String str) {
        this.f3208e.writeString(str);
    }

    @Override // androidx.versionedparcelable.a
    public void a() {
        int i7 = this.f3212i;
        if (i7 >= 0) {
            int i8 = this.f3207d.get(i7);
            int iDataPosition = this.f3208e.dataPosition();
            this.f3208e.setDataPosition(i8);
            this.f3208e.writeInt(iDataPosition - i8);
            this.f3208e.setDataPosition(iDataPosition);
        }
    }

    @Override // androidx.versionedparcelable.a
    protected a b() {
        Parcel parcel = this.f3208e;
        int iDataPosition = parcel.dataPosition();
        int i7 = this.f3213j;
        if (i7 == this.f3209f) {
            i7 = this.f3210g;
        }
        return new b(parcel, iDataPosition, i7, this.f3211h + "  ", this.f3204a, this.f3205b, this.f3206c);
    }

    @Override // androidx.versionedparcelable.a
    public boolean g() {
        return this.f3208e.readInt() != 0;
    }

    @Override // androidx.versionedparcelable.a
    public byte[] i() {
        int i7 = this.f3208e.readInt();
        if (i7 < 0) {
            return null;
        }
        byte[] bArr = new byte[i7];
        this.f3208e.readByteArray(bArr);
        return bArr;
    }

    @Override // androidx.versionedparcelable.a
    protected CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f3208e);
    }

    @Override // androidx.versionedparcelable.a
    public boolean m(int i7) {
        while (this.f3213j < this.f3210g) {
            int i8 = this.f3214k;
            if (i8 == i7) {
                return true;
            }
            if (String.valueOf(i8).compareTo(String.valueOf(i7)) > 0) {
                return false;
            }
            this.f3208e.setDataPosition(this.f3213j);
            int i9 = this.f3208e.readInt();
            this.f3214k = this.f3208e.readInt();
            this.f3213j += i9;
        }
        return this.f3214k == i7;
    }

    @Override // androidx.versionedparcelable.a
    public int o() {
        return this.f3208e.readInt();
    }

    @Override // androidx.versionedparcelable.a
    public <T extends Parcelable> T q() {
        return (T) this.f3208e.readParcelable(getClass().getClassLoader());
    }

    @Override // androidx.versionedparcelable.a
    public String s() {
        return this.f3208e.readString();
    }

    @Override // androidx.versionedparcelable.a
    public void w(int i7) {
        a();
        this.f3212i = i7;
        this.f3207d.put(i7, this.f3208e.dataPosition());
        E(0);
        E(i7);
    }

    @Override // androidx.versionedparcelable.a
    public void y(boolean z6) {
        this.f3208e.writeInt(z6 ? 1 : 0);
    }

    private b(Parcel parcel, int i7, int i8, String str, n.a<String, Method> aVar, n.a<String, Method> aVar2, n.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f3207d = new SparseIntArray();
        this.f3212i = -1;
        this.f3214k = -1;
        this.f3208e = parcel;
        this.f3209f = i7;
        this.f3210g = i8;
        this.f3213j = i7;
        this.f3211h = str;
    }
}
