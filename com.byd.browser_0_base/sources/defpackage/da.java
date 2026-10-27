package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.omnibox.suggestions.base.BaseSuggestionView;
import org.chromium.chrome.browser.omnibox.suggestions.dividerline.DividerLineView;
import org.chromium.chrome.browser.omnibox.suggestions.header.HeaderView;
import org.chromium.chrome.browser.omnibox.suggestions.tail.TailSuggestionView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class da implements za1 {
    public final /* synthetic */ int a;

    public /* synthetic */ da(int i) {
        this.a = i;
    }

    @Override // defpackage.za1
    public final View a(ViewGroup viewGroup) {
        switch (this.a) {
            case 0:
                return new BaseSuggestionView(viewGroup.getContext(), R$layout.omnibox_basic_suggestion);
            case 1:
                return new HeaderView(viewGroup.getContext());
            case 2:
                return new DividerLineView(viewGroup.getContext());
            case 3:
                return new BaseSuggestionView(viewGroup.getContext(), R$layout.omnibox_answer_suggestion);
            case 4:
                return new BaseSuggestionView(new TailSuggestionView(viewGroup.getContext()));
            case 5:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.touch_to_fill_credit_card_sheet_item, viewGroup, false);
            case 6:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.touch_to_fill_fill_button_modern, viewGroup, false);
            case 7:
                return LayoutInflater.from(viewGroup.getContext()).inflate(org.chromium.chrome.browser.ui.fast_checkout.R$layout.fast_checkout_footer_item, viewGroup, false);
            default:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.language_card, viewGroup, false);
        }
    }
}
