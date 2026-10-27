package b.z;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f3410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f3411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3415i;
    public int j;
    public int k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new b.e.a(), new b.e.a(), new b.e.a());
    }

    @Override // b.z.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f3411e.writeInt(-1);
        } else {
            this.f3411e.writeInt(bArr.length);
            this.f3411e.writeByteArray(bArr);
        }
    }

    @Override // b.z.a
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f3411e, 0);
    }

    @Override // b.z.a
    public void E(int i2) {
        this.f3411e.writeInt(i2);
    }

    @Override // b.z.a
    public void G(Parcelable parcelable) {
        this.f3411e.writeParcelable(parcelable, 0);
    }

    @Override // b.z.a
    public void I(String str) {
        this.f3411e.writeString(str);
    }

    @Override // b.z.a
    public void a() {
        int i2 = this.f3415i;
        if (i2 >= 0) {
            int i3 = this.f3410d.get(i2);
            int iDataPosition = this.f3411e.dataPosition();
            this.f3411e.setDataPosition(i3);
            this.f3411e.writeInt(iDataPosition - i3);
            this.f3411e.setDataPosition(iDataPosition);
        }
    }

    @Override // b.z.a
    public a b() {
        Parcel parcel = this.f3411e;
        int iDataPosition = parcel.dataPosition();
        int i2 = this.j;
        if (i2 == this.f3412f) {
            i2 = this.f3413g;
        }
        return new b(parcel, iDataPosition, i2, this.f3414h + "  ", this.f3407a, this.f3408b, this.f3409c);
    }

    @Override // b.z.a
    public boolean g() {
        return this.f3411e.readInt() != 0;
    }

    @Override // b.z.a
    public byte[] i() {
        int i2 = this.f3411e.readInt();
        if (i2 < 0) {
            return null;
        }
        byte[] bArr = new byte[i2];
        this.f3411e.readByteArray(bArr);
        return bArr;
    }

    @Override // b.z.a
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f3411e);
    }

    @Override // b.z.a
    public boolean m(int i2) {
        while (this.j < this.f3413g) {
            int i3 = this.k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            this.f3411e.setDataPosition(this.j);
            int i4 = this.f3411e.readInt();
            this.k = this.f3411e.readInt();
            this.j += i4;
        }
        return this.k == i2;
    }

    @Override // b.z.a
    public int o() {
        return this.f3411e.readInt();
    }

    @Override // b.z.a
    public <T extends Parcelable> T q() {
        return (T) this.f3411e.readParcelable(b.class.getClassLoader());
    }

    @Override // b.z.a
    public String s() {
        return this.f3411e.readString();
    }

    @Override // b.z.a
    public void w(int i2) {
        a();
        this.f3415i = i2;
        this.f3410d.put(i2, this.f3411e.dataPosition());
        E(0);
        E(i2);
    }

    @Override // b.z.a
    public void y(boolean z) {
        this.f3411e.writeInt(z ? 1 : 0);
    }

    public b(Parcel parcel, int i2, int i3, String str, b.e.a<String, Method> aVar, b.e.a<String, Method> aVar2, b.e.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f3410d = new SparseIntArray();
        this.f3415i = -1;
        this.j = 0;
        this.k = -1;
        this.f3411e = parcel;
        this.f3412f = i2;
        this.f3413g = i3;
        this.j = i2;
        this.f3414h = str;
    }
}
