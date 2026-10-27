package defpackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.f81;
import defpackage.t81;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CardHeaderViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class om extends i81 {
    public static final /* synthetic */ int z = 0;

    public om(View view) {
        super(view);
    }

    @Override // defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        f81.d dVar = (f81.d) f81Var;
        int i = R$id.domain;
        View view = this.e;
        ((TextView) view.findViewById(i)).setText((CharSequence) dVar.d.second);
        ImageView imageView = (ImageView) view.findViewById(R$id.favicon);
        int dimensionPixelSize = view.getContext().getResources().getDimensionPixelSize(R$dimen.default_favicon_size);
        if (imageView != null) {
            ((t81.a) propertyModel.j(t81.v)).a(dimensionPixelSize, dVar.e, new n62(this, 6, imageView));
        }
    }
}
