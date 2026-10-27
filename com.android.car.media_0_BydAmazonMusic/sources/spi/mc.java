package spi;

import android.car.drivingstate.XCarUxRestrictions;
import com.android.car.apps.common.UxrTextView;
import spi.e2;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class mc implements e2.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ mc(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // spi.e2.a
    public final void a(XCarUxRestrictions xCarUxRestrictions) {
        switch (this.a) {
            case 0:
                UxrTextView uxrTextView = (UxrTextView) this.b;
                uxrTextView.b = xCarUxRestrictions;
                uxrTextView.setTransformationMethod(new UxrTextView.b());
                break;
            default:
                com.android.car.ui.toolbar.b bVar = (com.android.car.ui.toolbar.b) this.b;
                boolean zA = bVar.a();
                bVar.g = xCarUxRestrictions;
                if (bVar.a() != zA) {
                    bVar.f();
                }
                break;
        }
    }
}
