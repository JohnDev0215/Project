package m1;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcelParcel.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f6343d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f6344e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6345f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6346g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f6347h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6348i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6349j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6350k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new v.a(), new v.a(), new v.a());
    }

    @Override // m1.a
    public void A(byte[] bArr) {
        if (bArr == null) {
            this.f6344e.writeInt(-1);
        } else {
            this.f6344e.writeInt(bArr.length);
            this.f6344e.writeByteArray(bArr);
        }
    }

    @Override // m1.a
    public void C(CharSequence charSequence) {
        TextUtils.writeToParcel(charSequence, this.f6344e, 0);
    }

    @Override // m1.a
    public void E(int i8) {
        this.f6344e.writeInt(i8);
    }

    @Override // m1.a
    public void G(Parcelable parcelable) {
        this.f6344e.writeParcelable(parcelable, 0);
    }

    @Override // m1.a
    public void I(String str) {
        this.f6344e.writeString(str);
    }

    @Override // m1.a
    public void a() {
        int i8 = this.f6348i;
        if (i8 >= 0) {
            int i9 = this.f6343d.get(i8);
            int iDataPosition = this.f6344e.dataPosition();
            this.f6344e.setDataPosition(i9);
            this.f6344e.writeInt(iDataPosition - i9);
            this.f6344e.setDataPosition(iDataPosition);
        }
    }

    @Override // m1.a
    public a b() {
        Parcel parcel = this.f6344e;
        int iDataPosition = parcel.dataPosition();
        int i8 = this.f6349j;
        if (i8 == this.f6345f) {
            i8 = this.f6346g;
        }
        return new b(parcel, iDataPosition, i8, this.f6347h + "  ", this.f6340a, this.f6341b, this.f6342c);
    }

    @Override // m1.a
    public boolean g() {
        return this.f6344e.readInt() != 0;
    }

    @Override // m1.a
    public byte[] i() {
        int i8 = this.f6344e.readInt();
        if (i8 < 0) {
            return null;
        }
        byte[] bArr = new byte[i8];
        this.f6344e.readByteArray(bArr);
        return bArr;
    }

    @Override // m1.a
    public CharSequence k() {
        return (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this.f6344e);
    }

    @Override // m1.a
    public boolean m(int i8) {
        while (this.f6349j < this.f6346g) {
            int i9 = this.f6350k;
            if (i9 == i8) {
                return true;
            }
            if (String.valueOf(i9).compareTo(String.valueOf(i8)) > 0) {
                return false;
            }
            this.f6344e.setDataPosition(this.f6349j);
            int i10 = this.f6344e.readInt();
            this.f6350k = this.f6344e.readInt();
            this.f6349j += i10;
        }
        return this.f6350k == i8;
    }

    @Override // m1.a
    public int o() {
        return this.f6344e.readInt();
    }

    @Override // m1.a
    public <T extends Parcelable> T q() {
        return (T) this.f6344e.readParcelable(b.class.getClassLoader());
    }

    @Override // m1.a
    public String s() {
        return this.f6344e.readString();
    }

    @Override // m1.a
    public void w(int i8) {
        a();
        this.f6348i = i8;
        this.f6343d.put(i8, this.f6344e.dataPosition());
        E(0);
        E(i8);
    }

    @Override // m1.a
    public void y(boolean z7) {
        this.f6344e.writeInt(z7 ? 1 : 0);
    }

    public b(Parcel parcel, int i8, int i9, String str, v.a<String, Method> aVar, v.a<String, Method> aVar2, v.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f6343d = new SparseIntArray();
        this.f6348i = -1;
        this.f6349j = 0;
        this.f6350k = -1;
        this.f6344e = parcel;
        this.f6345f = i8;
        this.f6346g = i9;
        this.f6349j = i8;
        this.f6347h = str;
    }
}
