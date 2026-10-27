package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.components.infobars.R$dimen;
import org.chromium.components.infobars.R$drawable;
import org.chromium.components.infobars.R$id;

/* JADX INFO: compiled from: InfoBarWrapper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yz0 extends FrameLayout {
    public final xz0 e;

    public yz0(Context context, xz0 xz0Var) {
        super(context);
        this.e = xz0Var;
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.infobar_peeking_height);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.infobar_shadow_height);
        setMinimumHeight(dimensionPixelSize + dimensionPixelSize2);
        setBackgroundResource(R$drawable.infobar_wrapper_bg);
        setPadding(0, dimensionPixelSize2, 0, 0);
        ColorDrawable colorDrawable = (ColorDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(R$id.infobar_wrapper_bg_fill);
        colorDrawable.mutate();
        colorDrawable.setColor(yp.c(getContext(), R$dimen.infobar_elevation));
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 48));
    }
}
