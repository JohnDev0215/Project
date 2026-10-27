package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.vision.text.internal.client.zzn;

/* JADX INFO: renamed from: wk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1725wk extends AbstractC0672dh {
    public final zzn j;

    public C1725wk(Context context, zzn zznVar) {
        super(context, "TextNativeHandle", "ocr");
        this.j = zznVar;
        e();
    }

    @Override // defpackage.AbstractC0672dh
    public final Object b(C1061kg c1061kg, Context context) throws C0726eg {
        IInterface c1560tk;
        IBinder iBinderB = c1061kg.b("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        Object c1505sk = null;
        if (iBinderB == null) {
            c1560tk = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = iBinderB.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            c1560tk = iInterfaceQueryLocalInterface instanceof InterfaceC1450rk ? (InterfaceC1450rk) iInterfaceQueryLocalInterface : new C1560tk(iBinderB);
        }
        if (c1560tk == null) {
            return null;
        }
        BinderC0615cg binderC0615cg = new BinderC0615cg(context);
        zzn zznVar = this.j;
        C1560tk c1560tk2 = (C1560tk) c1560tk;
        Parcel parcelF = c1560tk2.f();
        AbstractC0383Vg.c(parcelF, binderC0615cg);
        AbstractC0383Vg.d(parcelF, zznVar);
        Parcel parcelC = c1560tk2.c(1, parcelF);
        IBinder strongBinder = parcelC.readStrongBinder();
        if (strongBinder != null) {
            IInterface iInterfaceQueryLocalInterface2 = strongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            c1505sk = iInterfaceQueryLocalInterface2 instanceof InterfaceC1341pk ? (InterfaceC1341pk) iInterfaceQueryLocalInterface2 : new C1505sk(strongBinder);
        }
        parcelC.recycle();
        return c1505sk;
    }

    @Override // defpackage.AbstractC0672dh
    public final void c() {
        C1505sk c1505sk = (C1505sk) ((InterfaceC1341pk) e());
        c1505sk.d(2, c1505sk.f());
    }
}
