package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.f81;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.browser.download.home.view.SelectionView;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.offline_items_collection.OfflineItemVisuals;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: GenericViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class gs0 extends yq1 {
    public static final /* synthetic */ int L = 0;
    public final TextView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final TextView f39J;
    public int K;

    public gs0(View view) {
        super(view);
        this.I = (TextView) view.findViewById(R$id.title);
        this.f39J = (TextView) view.findViewById(R$id.caption);
        this.A.setForegroundScaleTypeCompat(ImageView.ScaleType.CENTER);
    }

    @Override // defpackage.yq1, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        f81.f fVar = (f81.f) f81Var;
        this.I.setText(fVar.e.f);
        this.f39J.setText(vh3.b(fVar.e));
        int iIntValue = z7.l(fVar.e).intValue();
        int i = iIntValue != 0 ? iIntValue != 1 ? iIntValue != 2 ? iIntValue != 3 ? iIntValue != 4 ? iIntValue != 5 ? R$drawable.ic_drive_file_24dp : R$drawable.ic_drive_document_24dp : R$drawable.ic_drive_image_24dp : R$drawable.ic_music_note_24dp : R$drawable.ic_videocam_24dp : R$drawable.ic_globe_24dp : R$drawable.ic_file_download_24dp;
        int i2 = this.K;
        AsyncImageView asyncImageView = this.A;
        if (i != i2) {
            this.K = i;
            Drawable drawableE = uh3.e(this.e.getContext(), i, R$color.default_icon_color_tint_list);
            asyncImageView.setUnavailableDrawable(drawableE);
            asyncImageView.setWaitingDrawable(drawableE);
        }
        SelectionView selectionView = this.z;
        selectionView.setVisibility(selectionView.isSelected() ? 0 : 4);
        asyncImageView.setVisibility(selectionView.isSelected() ? 4 : 0);
        x(asyncImageView.getDrawable() != null);
    }

    @Override // defpackage.yq1
    public final Drawable w(OfflineItemVisuals offlineItemVisuals) {
        boolean z = (offlineItemVisuals == null || offlineItemVisuals.a == null) ? false : true;
        x(z);
        if (!z) {
            return null;
        }
        qc2 qc2Var = new qc2(this.e.getResources(), offlineItemVisuals.a);
        qc2Var.k = true;
        qc2Var.j = true;
        qc2Var.g = Math.min(qc2Var.m, qc2Var.l) / 2;
        qc2Var.d.setShader(qc2Var.e);
        qc2Var.invalidateSelf();
        return qc2Var;
    }

    public final void x(boolean z) {
        AsyncImageView asyncImageView = this.A;
        if (z) {
            asyncImageView.setBackground(null);
            return;
        }
        if (asyncImageView.getBackground() == null) {
            View view = this.e;
            Resources resources = view.getResources();
            Drawable drawableA = AppCompatResources.a(view.getContext(), R$drawable.list_item_icon_modern_bg);
            drawableA.setLevel(resources.getInteger(R$integer.list_item_level_default));
            asyncImageView.setBackground(drawableA);
        }
    }
}
