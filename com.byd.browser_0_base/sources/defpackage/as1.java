package defpackage;

import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: OnClickListenerViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class as1 implements j.a<PropertyModel, View, v62> {
    public final PropertyModel.l<View.OnClickListener> a = g1.a;

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        ((View) obj).setOnClickListener((View.OnClickListener) ((PropertyModel) kVar).j(this.a));
    }
}
