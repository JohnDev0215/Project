package defpackage;

import J.N;
import android.graphics.drawable.Drawable;
import defpackage.di1;
import defpackage.ij0;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.webapps.WebApkUpdateManager;
import org.chromium.components.page_info.PageInfoController;
import org.chromium.components.page_info.d;
import org.chromium.components.signin.a;
import org.chromium.content.browser.input.SelectPopup;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class aq3 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ aq3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        lb2<ij0.a> lb2Var;
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((WebApkUpdateManager) obj2).e(((Integer) obj).intValue());
                break;
            case 1:
                ((jn2) obj2).g.p(kn2.b, (Drawable) obj);
                break;
            case 2:
                fv1 fv1Var = (fv1) obj2;
                int iD = iv1.d(true, fv1.a(fv1Var.b));
                if (iD >= 0) {
                    fv1Var.b(iD);
                }
                break;
            case 3:
                PropertyModel propertyModel = (PropertyModel) obj2;
                ((di1.a) propertyModel.j(di1.a)).c(((Integer) obj).intValue(), propertyModel);
                break;
            case 4:
                ij0 ij0Var = (ij0) obj2;
                if (((Integer) obj).intValue() != 1 && (lb2Var = ij0Var.s) != null) {
                    lb2Var.onResult(new ij0.a());
                }
                break;
            case 5:
                w11 w11Var = (w11) obj2;
                ij0.a aVar = (ij0.a) obj;
                ArrayList arrayList = w11.f;
                w11Var.getClass();
                int i2 = aVar.a;
                ij0 ij0Var2 = aVar.c;
                if (i2 == 1) {
                    w11Var.f(ij0Var2.a, aVar.b);
                    break;
                } else if (i2 == 2) {
                    w11Var.b(aVar.d, ij0Var2);
                    break;
                }
                break;
            case 6:
                d dVar = (d) obj2;
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (dVar.k != null) {
                    ((PageInfoController) dVar.g).f(zBooleanValue ? 12 : 11);
                    long j = dVar.k.a;
                    if (j != 0) {
                        N.MTF7msU_(j, zBooleanValue);
                    }
                }
                break;
            case 7:
                boolean zBooleanValue2 = ((Boolean) obj).booleanValue();
                Runnable runnable = ((f22) obj2).a;
                if (runnable != null) {
                    runnable.run();
                }
                if (zBooleanValue2 && !k22.a("PaintPreview.Player.Zoomed")) {
                    n3.X0("PaintPreview.Player.Zoomed");
                    k22.a.put("PaintPreview.Player.Zoomed", Long.valueOf(System.currentTimeMillis()));
                    break;
                }
                break;
            case 8:
                Map map = (Map) obj2;
                Map.Entry entry = (Map.Entry) obj;
                String str = (String) entry.getKey();
                lz1 lz1Var = (lz1) entry.getValue();
                map.put(str, lz1Var == null ? null : new qr3(lz1Var.b, lz1Var.c));
                break;
            case 9:
                a aVar2 = (a) obj2;
                aVar2.d.set((List) obj);
                aVar2.l();
                break;
            default:
                ((SelectPopup) obj2).d((int[]) obj);
                break;
        }
    }
}
