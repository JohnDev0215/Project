package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;

/* JADX INFO: compiled from: DividerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class lb0 extends View {
    public lb0(Context context, int i) {
        super(context, null, 0, i);
        setBackground(AppCompatResources.a(getContext(), R$drawable.rectangle_surface_1));
        setVisibility(8);
    }

    public final void a(ViewGroup viewGroup, FrameLayout.LayoutParams layoutParams) {
        layoutParams.gravity = 80;
        layoutParams.width = -1;
        layoutParams.height = getResources().getDimensionPixelSize(R$dimen.divider_height);
        viewGroup.addView(this, layoutParams);
    }

    public final void b(boolean z) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (z) {
            layoutParams.height = getResources().getDimensionPixelSize(R$dimen.thick_divider_height);
        } else {
            layoutParams.height = getResources().getDimensionPixelSize(R$dimen.divider_height);
        }
        ao3.e(this, "DividerView.setIsThickDivider");
    }
}
