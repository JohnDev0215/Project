package a.t;

import android.os.Parcel;
import android.util.SparseIntArray;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f1361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f1362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1363f;
    public final int g;
    public final String h;
    public int i;
    public int j;
    public int k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new a.e.a(), new a.e.a(), new a.e.a());
    }

    public b(Parcel parcel, int i, int i2, String str, a.e.a<String, Method> aVar, a.e.a<String, Method> aVar2, a.e.a<String, Class> aVar3) {
        super(aVar, aVar2, aVar3);
        this.f1361d = new SparseIntArray();
        this.i = -1;
        this.j = 0;
        this.k = -1;
        this.f1362e = parcel;
        this.f1363f = i;
        this.g = i2;
        this.j = i;
        this.h = str;
    }

    @Override // a.t.a
    public void a() {
        int i = this.i;
        if (i >= 0) {
            int i2 = this.f1361d.get(i);
            int iDataPosition = this.f1362e.dataPosition();
            this.f1362e.setDataPosition(i2);
            this.f1362e.writeInt(iDataPosition - i2);
            this.f1362e.setDataPosition(iDataPosition);
        }
    }

    @Override // a.t.a
    public a b() {
        Parcel parcel = this.f1362e;
        int iDataPosition = parcel.dataPosition();
        int i = this.j;
        if (i == this.f1363f) {
            i = this.g;
        }
        return new b(parcel, iDataPosition, i, this.h + "  ", this.f1358a, this.f1359b, this.f1360c);
    }

    @Override // a.t.a
    public boolean h(int i) {
        while (this.j < this.g) {
            int i2 = this.k;
            if (i2 == i) {
                return true;
            }
            if (String.valueOf(i2).compareTo(String.valueOf(i)) > 0) {
                return false;
            }
            this.f1362e.setDataPosition(this.j);
            int i3 = this.f1362e.readInt();
            this.k = this.f1362e.readInt();
            this.j += i3;
        }
        return this.k == i;
    }

    @Override // a.t.a
    public void l(int i) {
        a();
        this.i = i;
        this.f1361d.put(i, this.f1362e.dataPosition());
        this.f1362e.writeInt(0);
        this.f1362e.writeInt(i);
    }
}
