package x3;

import android.os.Parcel;
import android.util.SparseIntArray;
import u.C2465e;

/* JADX INFO: renamed from: x3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2675c extends AbstractC2674b {
    public final SparseIntArray d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f23772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f23773f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f23774g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f23775h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f23776i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f23777j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f23778k;

    public C2675c(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new C2465e(0), new C2465e(0), new C2465e(0));
    }

    @Override // x3.AbstractC2674b
    public final C2675c a() {
        Parcel parcel = this.f23772e;
        int iDataPosition = parcel.dataPosition();
        int i7 = this.f23777j;
        if (i7 == this.f23773f) {
            i7 = this.f23774g;
        }
        return new C2675c(parcel, iDataPosition, i7, android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), this.f23775h, "  "), this.f23770a, this.f23771b, this.c);
    }

    @Override // x3.AbstractC2674b
    public final boolean e(int i7) {
        while (this.f23777j < this.f23774g) {
            int i10 = this.f23778k;
            if (i10 == i7) {
                return true;
            }
            if (String.valueOf(i10).compareTo(String.valueOf(i7)) > 0) {
                return false;
            }
            int i11 = this.f23777j;
            Parcel parcel = this.f23772e;
            parcel.setDataPosition(i11);
            int i12 = parcel.readInt();
            this.f23778k = parcel.readInt();
            this.f23777j += i12;
        }
        return this.f23778k == i7;
    }

    @Override // x3.AbstractC2674b
    public final void i(int i7) {
        int i10 = this.f23776i;
        SparseIntArray sparseIntArray = this.d;
        Parcel parcel = this.f23772e;
        if (i10 >= 0) {
            int i11 = sparseIntArray.get(i10);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i11);
            parcel.writeInt(iDataPosition - i11);
            parcel.setDataPosition(iDataPosition);
        }
        this.f23776i = i7;
        sparseIntArray.put(i7, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i7);
    }

    public C2675c(Parcel parcel, int i7, int i10, String str, C2465e c2465e, C2465e c2465e2, C2465e c2465e3) {
        super(c2465e, c2465e2, c2465e3);
        this.d = new SparseIntArray();
        this.f23776i = -1;
        this.f23778k = -1;
        this.f23772e = parcel;
        this.f23773f = i7;
        this.f23774g = i10;
        this.f23777j = i7;
        this.f23775h = str;
    }
}
