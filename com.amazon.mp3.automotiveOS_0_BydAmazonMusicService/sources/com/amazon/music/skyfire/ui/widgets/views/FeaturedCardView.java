package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedCardView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedCardView;", "Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "barker", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBarker", "()Lcom/amazon/ui/foundations/views/BaseImage;", "barkerView", "horizontalTile", "Lcom/amazon/music/views/library/views/HorizontalTileView;", "getHorizontalTile", "()Lcom/amazon/music/views/library/views/HorizontalTileView;", "horizontalTileContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getHorizontalTileContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturedCardView extends FeaturedView {
    private final BaseImage barkerView;
    private final HorizontalTileView horizontalTile;
    private final ConstraintLayout horizontalTileContainer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedCardView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        View.inflate(context, R.layout.dmsf_featured_card_view, this);
        View viewFindViewById = findViewById(R.id.barker);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.barker)");
        this.barkerView = (BaseImage) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.horizontal_tile_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.horizontal_tile_container)");
        ConstraintLayout constraintLayout = (ConstraintLayout) viewFindViewById2;
        this.horizontalTileContainer = constraintLayout;
        HorizontalTileView horizontalTileView = new HorizontalTileView(context, styleSheet);
        this.horizontalTile = horizontalTileView;
        ViewGroupKt.plusAssign(constraintLayout, horizontalTileView);
    }

    public final HorizontalTileView getHorizontalTile() {
        return this.horizontalTile;
    }

    public final ConstraintLayout getHorizontalTileContainer() {
        return this.horizontalTileContainer;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.views.FeaturedView
    /* JADX INFO: renamed from: getBarker, reason: from getter */
    public BaseImage getBarkerView() {
        return this.barkerView;
    }
}
