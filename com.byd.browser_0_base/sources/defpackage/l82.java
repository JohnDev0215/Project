package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: compiled from: QrCodeShareViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l82 implements j.a<PropertyModel, k82, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        k82 k82Var = (k82) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.r<Bitmap> rVar = m82.a;
        if (rVar == v62Var) {
            Bitmap bitmap = (Bitmap) propertyModel.j(rVar);
            int i = R$id.qrcode;
            View view = k82Var.b;
            ((ChromeImageView) view.findViewById(i)).setImageDrawable(new BitmapDrawable(k82Var.a.getResources(), bitmap));
            view.findViewById(R$id.download).setEnabled(true);
            view.findViewById(R$id.settings).setEnabled(true);
            return;
        }
        PropertyModel.r<String> rVar2 = m82.b;
        if (rVar2 == v62Var) {
            String str = (String) propertyModel.j(rVar2);
            int i2 = R$id.error_message;
            View view2 = k82Var.b;
            TextView textView = (TextView) view2.findViewById(i2);
            textView.setText(str);
            textView.setVisibility(0);
            view2.findViewById(R$id.download).setEnabled(false);
            view2.findViewById(R$id.settings).setEnabled(false);
            return;
        }
        PropertyModel.n nVar = m82.c;
        if (nVar == v62Var) {
            Boolean boolValueOf = Boolean.valueOf(propertyModel.k(nVar));
            if (k82Var.c && boolValueOf.booleanValue()) {
                return;
            }
            k82Var.c = boolValueOf.booleanValue();
            k82Var.a();
            return;
        }
        PropertyModel.n nVar2 = m82.d;
        if (nVar2 == v62Var) {
            Boolean boolValueOf2 = Boolean.valueOf(propertyModel.k(nVar2));
            k82Var.getClass();
            k82Var.d = boolValueOf2.booleanValue();
            k82Var.a();
            return;
        }
        PropertyModel.n nVar3 = m82.e;
        if (nVar3 == v62Var) {
            Boolean boolValueOf3 = Boolean.valueOf(propertyModel.k(nVar3));
            k82Var.getClass();
            boolean zBooleanValue = boolValueOf3.booleanValue();
            k82Var.e = zBooleanValue;
            if (zBooleanValue) {
                k82Var.a();
            }
        }
    }
}
