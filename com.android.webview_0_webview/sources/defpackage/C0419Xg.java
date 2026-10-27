package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.zzipv;

/* JADX INFO: renamed from: Xg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0419Xg extends AbstractC0672dh {
    public final zzipv j;

    public C0419Xg(Context context, zzipv zzipvVar) {
        super(context, "BarcodeNativeHandle", "barcode");
        this.j = zzipvVar;
        e();
    }

    @Override // defpackage.AbstractC0672dh
    public final Object b(C1061kg c1061kg, Context context) throws C0726eg {
        InterfaceC0616ch c0560bh;
        IBinder iBinderB = c1061kg.b("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        Object c0455Zg = null;
        if (iBinderB == null) {
            c0560bh = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinderB.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            c0560bh = iInterfaceQueryLocalInterface instanceof InterfaceC0616ch ? (InterfaceC0616ch) iInterfaceQueryLocalInterface : new C0560bh(iBinderB);
        }
        if (c0560bh == null) {
            return null;
        }
        BinderC0615cg binderC0615cg = new BinderC0615cg(context);
        zzipv zzipvVar = this.j;
        C0560bh c0560bh2 = (C0560bh) c0560bh;
        Parcel parcelF = c0560bh2.f();
        AbstractC0383Vg.c(parcelF, binderC0615cg);
        AbstractC0383Vg.d(parcelF, zzipvVar);
        Parcel parcelC = c0560bh2.c(1, parcelF);
        IBinder strongBinder = parcelC.readStrongBinder();
        if (strongBinder != null) {
            IInterface iInterfaceQueryLocalInterface2 = strongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            c0455Zg = iInterfaceQueryLocalInterface2 instanceof InterfaceC0504ah ? (InterfaceC0504ah) iInterfaceQueryLocalInterface2 : new C0455Zg(strongBinder);
        }
        parcelC.recycle();
        return c0455Zg;
    }

    @Override // defpackage.AbstractC0672dh
    public final void c() {
        if (a()) {
            C0455Zg c0455Zg = (C0455Zg) ((InterfaceC0504ah) e());
            c0455Zg.d(3, c0455Zg.f());
        }
    }
}
