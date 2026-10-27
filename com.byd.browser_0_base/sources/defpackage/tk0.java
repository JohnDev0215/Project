package defpackage;

import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.chromium.components.browser_ui.settings.R$dimen;
import org.chromium.components.browser_ui.settings.R$drawable;

/* JADX INFO: compiled from: FaviconViewUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tk0 {
    public static void a(Resources resources, ImageView imageView) {
        imageView.setBackgroundResource(R$drawable.list_item_icon_modern_bg);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        int i = R$dimen.list_item_start_icon_width;
        layoutParams.height = resources.getDimensionPixelSize(i);
        layoutParams.width = resources.getDimensionPixelSize(i);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
    }
}
