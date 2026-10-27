package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.f81;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PrefetchGroupedItemViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class q32 extends yq1 {
    public final TextView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final TextView f92J;

    public q32(View view) {
        super(view);
        this.I = (TextView) view.findViewById(R$id.title);
        this.f92J = (TextView) view.findViewById(R$id.timestamp);
    }

    @Override // defpackage.yq1, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        f81.f fVar = (f81.f) f81Var;
        this.I.setText(fVar.e.f);
        this.f92J.setText(vh3.d(fVar.d));
        OfflineItem offlineItem = fVar.e;
        ImageView imageView = (ImageView) this.e.findViewById(R$id.media_button);
        int i = offlineItem.h;
        imageView.setImageResource((i == 1 || i == 2) ? R$drawable.ic_play_circle_filled_24dp : 0);
    }
}
