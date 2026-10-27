package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: renamed from: rg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1446rg extends AbstractC0365Ug implements InterfaceC1337pg {
    public C1446rg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final InterfaceC0559bg i(InterfaceC0559bg interfaceC0559bg, String str, int i) {
        Parcel parcelF = f();
        AbstractC0383Vg.c(parcelF, interfaceC0559bg);
        parcelF.writeString(str);
        parcelF.writeInt(i);
        Parcel parcelC = c(2, parcelF);
        InterfaceC0559bg interfaceC0559bgV = AbstractBinderC0503ag.v(parcelC.readStrongBinder());
        parcelC.recycle();
        return interfaceC0559bgV;
    }

    public final InterfaceC0559bg u(InterfaceC0559bg interfaceC0559bg, String str, int i) {
        Parcel parcelF = f();
        AbstractC0383Vg.c(parcelF, interfaceC0559bg);
        parcelF.writeString(str);
        parcelF.writeInt(i);
        Parcel parcelC = c(4, parcelF);
        InterfaceC0559bg interfaceC0559bgV = AbstractBinderC0503ag.v(parcelC.readStrongBinder());
        parcelC.recycle();
        return interfaceC0559bgV;
    }
}
