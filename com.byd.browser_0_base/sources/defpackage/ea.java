package defpackage;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.omnibox.suggestions.base.BaseSuggestionView;
import org.chromium.chrome.browser.omnibox.suggestions.carousel.BaseCarouselSuggestionView;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ea implements za1 {
    public final /* synthetic */ int a;

    public /* synthetic */ ea(int i) {
        this.a = i;
    }

    @Override // defpackage.za1
    public final View a(ViewGroup viewGroup) {
        switch (this.a) {
            case 0:
                m mVar = new m(new ya1());
                mVar.H(0, new bd(0), new ag(8));
                return new BaseCarouselSuggestionView(viewGroup.getContext(), mVar);
            case 1:
                return new BaseSuggestionView(viewGroup.getContext(), R$layout.omnibox_basic_suggestion);
            case 2:
                return new BaseSuggestionView(viewGroup.getContext(), R$layout.omnibox_entity_suggestion);
            case 3:
                return new BaseSuggestionView(viewGroup.getContext(), R$layout.omnibox_basic_suggestion);
            case 4:
                Resources resources = viewGroup.getResources();
                int dimensionPixelSize = resources.getDimensionPixelSize(pr1.e(viewGroup.getContext()) ? R$dimen.omnibox_suggestion_icon_area_size_modern : R$dimen.omnibox_suggestion_icon_area_size) - ((resources.getDimensionPixelSize(R$dimen.omnibox_action_chip_spacing) * 3) / 2);
                View view = new View(viewGroup.getContext());
                view.setMinimumWidth(dimensionPixelSize);
                return view;
            case 5:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.touch_to_fill_credit_card_header_item, viewGroup, false);
            case 6:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.touch_to_fill_credit_card_footer_item, viewGroup, false);
            case 7:
                return LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.account_selection_account_item, viewGroup, false);
            case 8:
                return LayoutInflater.from(viewGroup.getContext()).inflate(org.chromium.chrome.browser.ui.fast_checkout.R$layout.fast_checkout_credit_card_item, viewGroup, false);
            default:
                return LayoutInflater.from(viewGroup.getContext()).inflate(org.chromium.chrome.browser.ui.fast_checkout.R$layout.fast_checkout_autofill_profile_item, viewGroup, false);
        }
    }
}
