package defpackage;

import android.view.View;
import android.widget.TextView;
import defpackage.f81;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AudioViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class o9 extends yq1 {
    public final TextView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final TextView f52J;
    public final TextView K;

    public o9(View view) {
        super(view);
        this.I = (TextView) view.findViewById(R$id.title);
        this.f52J = (TextView) view.findViewById(R$id.caption);
        this.K = (TextView) view.findViewById(R$id.timestamp);
    }

    @Override // defpackage.yq1, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        f81.f fVar = (f81.f) f81Var;
        this.I.setText(fVar.e.f);
        this.f52J.setText(vh3.c(fVar.e));
        this.K.setText(vh3.d(fVar.d));
    }
}
