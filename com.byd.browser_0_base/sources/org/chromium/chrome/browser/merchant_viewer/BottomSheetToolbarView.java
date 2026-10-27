package org.chromium.chrome.browser.merchant_viewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.FadingShadowView;

/* JADX INFO: loaded from: classes2.dex */
public class BottomSheetToolbarView {
    public final int a;
    public final View b;

    public BottomSheetToolbarView(Context context) {
        this.a = context.getResources().getDimensionPixelSize(R$dimen.sheet_tab_toolbar_height);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.sheet_tab_toolbar, (ViewGroup) null);
        this.b = viewInflate;
        ((FadingShadowView) viewInflate.findViewById(R$id.shadow)).a(context.getColor(R$color.toolbar_shadow_color));
    }
}
