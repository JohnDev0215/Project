package p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class tk implements Parcelable.Creator {
    public final /* synthetic */ int a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new uk(parcel);
            case 1:
                return new vk(parcel);
            case 2:
                lx0 lx0Var = new lx0();
                lx0Var.c = parcel.readString();
                lx0Var.e = parcel.readInt();
                return lx0Var;
            case 3:
                px0 px0Var = new px0();
                px0Var.m = null;
                px0Var.n = new ArrayList();
                px0Var.o = new ArrayList();
                px0Var.c = parcel.createStringArrayList();
                px0Var.e = parcel.createStringArrayList();
                px0Var.k = (uk[]) parcel.createTypedArray(uk.CREATOR);
                px0Var.l = parcel.readInt();
                px0Var.m = parcel.readString();
                px0Var.n = parcel.createStringArrayList();
                px0Var.o = parcel.createTypedArrayList(vk.CREATOR);
                px0Var.f169p = parcel.createTypedArrayList(lx0.CREATOR);
                return px0Var;
            default:
                return new sx0(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new uk[i];
            case 1:
                return new vk[i];
            case 2:
                return new lx0[i];
            case 3:
                return new px0[i];
            default:
                return new sx0[i];
        }
    }
}
