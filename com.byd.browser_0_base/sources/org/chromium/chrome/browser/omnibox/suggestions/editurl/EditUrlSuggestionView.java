package org.chromium.chrome.browser.omnibox.suggestions.editurl;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.omnibox.suggestions.base.BaseSuggestionView;

/* JADX INFO: loaded from: classes2.dex */
public class EditUrlSuggestionView extends FrameLayout {
    public final BaseSuggestionView<View> e;
    public final View f;

    public EditUrlSuggestionView(Context context) {
        super(context, null);
        BaseSuggestionView<View> baseSuggestionView = new BaseSuggestionView<>(context, R$layout.omnibox_basic_suggestion);
        this.e = baseSuggestionView;
        FrameLayout.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.width = -1;
        layoutParamsGenerateDefaultLayoutParams.height = -2;
        addView(baseSuggestionView, layoutParamsGenerateDefaultLayoutParams);
        View view = new View(context, null, 0, R$style.HorizontalDivider);
        this.f = view;
        FrameLayout.LayoutParams layoutParamsGenerateDefaultLayoutParams2 = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams2.gravity = 80;
        layoutParamsGenerateDefaultLayoutParams2.width = -1;
        layoutParamsGenerateDefaultLayoutParams2.height = getResources().getDimensionPixelSize(R$dimen.divider_height);
        addView(view, layoutParamsGenerateDefaultLayoutParams2);
    }

    public BaseSuggestionView<View> getBaseSuggestionView() {
        return this.e;
    }

    public View getDivider() {
        return this.f;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.e.setSelected(z);
    }
}
