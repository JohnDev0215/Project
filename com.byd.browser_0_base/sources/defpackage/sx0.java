package defpackage;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.f81;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: InProgressImageViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class sx0 extends ux0 {
    public final ImageView E;

    public sx0(View view) {
        super(view, true);
        ImageView imageView = (ImageView) view.findViewById(R$id.placeholder);
        this.E = imageView;
        imageView.setImageDrawable(v9.b(AppCompatResources.a(view.getContext(), R$drawable.async_image_view_waiting)));
    }

    @Override // defpackage.ux0, defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        super.u(propertyModel, f81Var);
        this.E.setContentDescription(((f81.f) f81Var).e.f);
    }
}
