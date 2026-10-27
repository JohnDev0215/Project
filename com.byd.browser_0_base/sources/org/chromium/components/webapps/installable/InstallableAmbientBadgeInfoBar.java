package org.chromium.components.webapps.installable;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.browser_ui.widget.text.AccessibleTextView;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;
import org.chromium.components.infobars.R$dimen;
import org.chromium.components.infobars.R$id;
import org.chromium.components.infobars.R$style;
import org.chromium.components.webapps.WebappsIconUtils;

/* JADX INFO: loaded from: classes3.dex */
public class InstallableAmbientBadgeInfoBar extends InfoBar implements View.OnClickListener {
    public final String o;
    public boolean p;

    public InstallableAmbientBadgeInfoBar(String str, Bitmap bitmap, int i) {
        super(i, 0, null, bitmap);
        this.o = str;
    }

    @CalledByNative
    public static InfoBar show(int i, Bitmap bitmap, String str, String str2, boolean z) {
        if (z) {
            bitmap = WebappsIconUtils.generateAdaptiveIconBitmap(bitmap);
        }
        return new InstallableAmbientBadgeInfoBar(str, bitmap, i);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void o(InfoBarCompactLayout infoBarCompactLayout) {
        AccessibleTextView accessibleTextView = new AccessibleTextView(this.k);
        Resources resources = infoBarCompactLayout.getResources();
        accessibleTextView.setText(this.o);
        accessibleTextView.setTextAppearance(accessibleTextView.getContext(), R$style.TextAppearance_TextLarge_Link);
        accessibleTextView.setGravity(16);
        accessibleTextView.setOnClickListener(this);
        ImageView imageView = (ImageView) infoBarCompactLayout.findViewById(R$id.infobar_icon);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.infobar_small_icon_margin);
        imageView.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
        imageView.setOnClickListener(this);
        imageView.setImportantForAccessibility(2);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.infobar_compact_message_vertical_padding);
        accessibleTextView.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
        infoBarCompactLayout.a(accessibleTextView);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        long j = this.n;
        if (j == 0 || this.p) {
            return;
        }
        N.MzHO1MxZ(j, this);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void t() {
        this.p = true;
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final boolean u() {
        return true;
    }
}
