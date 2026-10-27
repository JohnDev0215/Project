package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.feed.FeedPlaceholderLayout;

/* JADX INFO: compiled from: FeedPlaceholderCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hl0 {
    public final ViewGroup a;
    public FeedPlaceholderLayout b;

    public hl0(Context context, ViewGroup viewGroup) {
        this.a = viewGroup;
        FeedPlaceholderLayout feedPlaceholderLayout = (FeedPlaceholderLayout) LayoutInflater.from(context).inflate(R$layout.feed_placeholder_layout, (ViewGroup) null, false);
        this.b = feedPlaceholderLayout;
        feedPlaceholderLayout.setBlankHeaderHeight(context.getResources().getDimensionPixelSize(R$dimen.snippets_article_header_menu_size));
        viewGroup.addView(this.b);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.b.getLayoutParams();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.content_suggestions_card_modern_padding);
        marginLayoutParams.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
        this.b.requestLayout();
    }
}
