package defpackage;

import android.view.View;
import android.widget.TextView;
import defpackage.f81;
import org.chromium.chrome.R$id;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: VideoViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class jm3 extends yq1 {
    public final TextView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final TextView f42J;

    public jm3(View view) {
        super(view);
        this.I = (TextView) view.findViewById(R$id.title);
        this.f42J = (TextView) view.findViewById(R$id.caption);
    }

    @Override // defpackage.yq1, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        OfflineItem offlineItem = ((f81.f) f81Var).e;
        this.I.setText(offlineItem.f);
        this.f42J.setText(vh3.b(offlineItem));
        this.A.setContentDescription(offlineItem.f);
    }
}
