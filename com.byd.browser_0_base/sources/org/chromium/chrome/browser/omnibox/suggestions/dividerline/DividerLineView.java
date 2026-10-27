package org.chromium.chrome.browser.omnibox.suggestions.dividerline;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;

/* JADX INFO: loaded from: classes2.dex */
public class DividerLineView extends FrameLayout {
    public final View e;

    public DividerLineView(Context context) {
        super(context, null);
        setClickable(false);
        setFocusable(false);
        View view = new View(context, null, 0, R$style.HorizontalDivider);
        this.e = view;
        FrameLayout.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.gravity = 48;
        layoutParamsGenerateDefaultLayoutParams.width = -1;
        layoutParamsGenerateDefaultLayoutParams.height = getResources().getDimensionPixelSize(R$dimen.divider_height);
        addView(view, layoutParamsGenerateDefaultLayoutParams);
        int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.omnibox_suggestion_list_divider_line_padding);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(this, 0, 0, 0, dimensionPixelOffset);
    }

    public View getDivider() {
        return this.e;
    }
}
