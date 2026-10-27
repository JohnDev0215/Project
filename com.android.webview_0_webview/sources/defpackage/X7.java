package defpackage;

import android.os.Parcel;
import android.util.SparseIntArray;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class X7 extends W7 {
    public final Parcel b;
    public final int c;
    public final int d;
    public final String e;
    public int g;
    public final SparseIntArray a = new SparseIntArray();
    public int f = -1;

    public X7(Parcel parcel, int i, int i2, String str) {
        this.g = 0;
        this.b = parcel;
        this.c = i;
        this.d = i2;
        this.g = i;
        this.e = str;
    }

    @Override // defpackage.W7
    public void a() {
        int i = this.f;
        if (i >= 0) {
            int i2 = this.a.get(i);
            int iDataPosition = this.b.dataPosition();
            this.b.setDataPosition(i2);
            this.b.writeInt(iDataPosition - i2);
            this.b.setDataPosition(iDataPosition);
        }
    }

    @Override // defpackage.W7
    public W7 b() {
        Parcel parcel = this.b;
        int iDataPosition = parcel.dataPosition();
        int i = this.g;
        if (i == this.c) {
            i = this.d;
        }
        return new X7(parcel, iDataPosition, i, this.e + "  ");
    }

    @Override // defpackage.W7
    public boolean d(int i) {
        int iDataPosition;
        while (true) {
            int i2 = this.g;
            if (i2 >= this.d) {
                iDataPosition = -1;
                break;
            }
            this.b.setDataPosition(i2);
            int i3 = this.b.readInt();
            int i4 = this.b.readInt();
            this.g += i3;
            if (i4 == i) {
                iDataPosition = this.b.dataPosition();
                break;
            }
        }
        if (iDataPosition == -1) {
            return false;
        }
        this.b.setDataPosition(iDataPosition);
        return true;
    }

    @Override // defpackage.W7
    public void h(int i) {
        a();
        this.f = i;
        this.a.put(i, this.b.dataPosition());
        this.b.writeInt(0);
        this.b.writeInt(i);
    }
}
