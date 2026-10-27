package a1;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f74d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f75e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f76f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f77g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f78h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f79i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f80j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f81k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new s.a(), new s.a(), new s.a());
    }

    @Override // a1.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f75e.writeInt(-1);
        } else {
            this.f75e.writeInt(bArr.length);
            this.f75e.writeByteArray(bArr);
        }
    }

    @Override // a1.a
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f75e, 0);
    }

    @Override // a1.a
    public void E(int i3) {
        this.f75e.writeInt(i3);
    }

    @Override // a1.a
    public void G(Parcelable parcelable) {
        this.f75e.writeParcelable(parcelable, 0);
    }

    @Override // a1.a
    public void I(String str) {
        this.f75e.writeString(str);
    }

    @Override // a1.a
    public void a() {
        int i3 = this.f79i;
        if (i3 >= 0) {
            int i4 = this.f74d.get(i3);
            int iDataPosition = this.f75e.dataPosition();
            this.f75e.setDataPosition(i4);
            this.f75e.writeInt(iDataPosition - i4);
            this.f75e.setDataPosition(iDataPosition);
        }
    }

    @Override // a1.a
    public a b() {
        Parcel parcel = this.f75e;
        int iDataPosition = parcel.dataPosition();
        int i3 = this.f80j;
        if (i3 == this.f76f) {
            i3 = this.f77g;
        }
        return new b(parcel, iDataPosition, i3, this.f78h + "  ", this.f71a, this.f72b, this.f73c);
    }

    @Override // a1.a
    public boolean g() {
        return this.f75e.readInt() != 0;
    }

    @Override // a1.a
    public byte[] i() {
        int i3 = this.f75e.readInt();
        if (i3 < 0) {
            return null;
        }
        byte[] bArr = new byte[i3];
        this.f75e.readByteArray(bArr);
        return bArr;
    }

    @Override // a1.a
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f75e);
    }

    @Override // a1.a
    public boolean m(int i3) {
        while (this.f80j < this.f77g) {
            int i4 = this.f81k;
            if (i4 == i3) {
                return true;
            }
            if (String.valueOf(i4).compareTo(String.valueOf(i3)) > 0) {
                return false;
            }
            this.f75e.setDataPosition(this.f80j);
            int i5 = this.f75e.readInt();
            this.f81k = this.f75e.readInt();
            this.f80j += i5;
        }
        return this.f81k == i3;
    }

    @Override // a1.a
    public int o() {
        return this.f75e.readInt();
    }

    @Override // a1.a
    public <T extends Parcelable> T q() {
        return (T) this.f75e.readParcelable(b.class.getClassLoader());
    }

    @Override // a1.a
    public String s() {
        return this.f75e.readString();
    }

    @Override // a1.a
    public void w(int i3) {
        a();
        this.f79i = i3;
        this.f74d.put(i3, this.f75e.dataPosition());
        E(0);
        E(i3);
    }

    @Override // a1.a
    public void y(boolean z3) {
        this.f75e.writeInt(z3 ? 1 : 0);
    }

    public b(Parcel parcel, int i3, int i4, String str, s.a<String, Method> aVar, s.a<String, Method> aVar2, s.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f74d = new SparseIntArray();
        this.f79i = -1;
        this.f80j = 0;
        this.f81k = -1;
        this.f75e = parcel;
        this.f76f = i3;
        this.f77g = i4;
        this.f80j = i3;
        this.f78h = str;
    }
}
