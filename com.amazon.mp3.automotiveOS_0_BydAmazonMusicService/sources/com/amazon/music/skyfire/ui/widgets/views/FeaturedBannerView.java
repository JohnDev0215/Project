package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.view.View;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedBannerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedBannerView;", "Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "barker", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBarker", "()Lcom/amazon/ui/foundations/views/BaseImage;", "barkerView", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedBannerView extends FeaturedView {
    private final BaseImage barkerView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedBannerView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        View.inflate(context, R.layout.dmsf_featured_banner_view, this);
        View viewFindViewById = findViewById(R.id.barker);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.barker)");
        this.barkerView = (BaseImage) viewFindViewById;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.views.FeaturedView
    /* JADX INFO: renamed from: getBarker, reason: from getter */
    public BaseImage getBarkerView() {
        return this.barkerView;
    }
}
