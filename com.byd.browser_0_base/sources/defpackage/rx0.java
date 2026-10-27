package defpackage;

import android.view.View;
import android.widget.TextView;
import defpackage.f81;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: InProgressGenericViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class rx0 extends ux0 {
    public static final /* synthetic */ int F = 0;
    public final TextView E;

    public rx0(View view) {
        super(view, false);
        this.E = (TextView) view.findViewById(R$id.title);
    }

    @Override // defpackage.ux0, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        this.E.setText(((f81.f) f81Var).e.f);
    }
}
