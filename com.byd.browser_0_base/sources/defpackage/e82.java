package defpackage;

import org.chromium.chrome.browser.share.qrcode.scan_tab.CameraPreview;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: QrCodeScanViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e82 implements j.a<PropertyModel, c82, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        CameraPreview cameraPreview;
        PropertyModel propertyModel = (PropertyModel) kVar;
        c82 c82Var = (c82) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.n nVar = f82.a;
        if (nVar == v62Var) {
            Boolean boolValueOf = Boolean.valueOf(propertyModel.k(nVar));
            if (c82Var.d && boolValueOf.booleanValue()) {
                return;
            }
            c82Var.d = boolValueOf.booleanValue();
            c82Var.a();
            return;
        }
        PropertyModel.n nVar2 = f82.c;
        if (nVar2 != v62Var) {
            PropertyModel.n nVar3 = f82.b;
            if (nVar3 == v62Var) {
                Boolean boolValueOf2 = Boolean.valueOf(propertyModel.k(nVar3));
                c82Var.getClass();
                c82Var.e = boolValueOf2.booleanValue();
                c82Var.a();
                return;
            }
            return;
        }
        Boolean boolValueOf3 = Boolean.valueOf(propertyModel.k(nVar2));
        c82Var.getClass();
        boolean zBooleanValue = boolValueOf3.booleanValue();
        c82Var.f = zBooleanValue;
        if (zBooleanValue || (cameraPreview = c82Var.g) == null) {
            c82Var.a();
        } else {
            cameraPreview.d();
        }
    }
}
