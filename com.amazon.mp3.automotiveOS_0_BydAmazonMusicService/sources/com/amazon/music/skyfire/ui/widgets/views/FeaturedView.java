package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.StyleSheetKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.styles.OutlineStyle;
import com.amazon.ui.foundations.views.BaseImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturedView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\f\u0010\u0018\u001a\u00020\u000e*\u00020\u0010H\u0014R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/FeaturedView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "barker", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBarker", "()Lcom/amazon/ui/foundations/views/BaseImage;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "getBannerWidth", "", "screenSize", "Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;", "resetBarkerProperties", "", "screenSizeToAspectRatio", "", "setImage", "bitmap", "Landroid/graphics/Bitmap;", "toColumnCount", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class FeaturedView extends ConstraintLayout {
    private final StyleSheet styleSheet;

    public abstract BaseImage getBarker();

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
    }

    public final void setImage(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        BaseImage barker = getBarker();
        barker.setImage(new BitmapDrawable(barker.getContext().getResources(), bitmap));
        barker.setVisibility(0);
        barker.getImage().setVisibility(0);
        resetBarkerProperties();
    }

    private final void resetBarkerProperties() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        ScreenSizeKey widthScreenSizeKey = styleSheet.getWidthScreenSizeKey(context);
        int bannerWidth = getBannerWidth(widthScreenSizeKey);
        float fScreenSizeToAspectRatio = screenSizeToAspectRatio(widthScreenSizeKey);
        int color = ContextCompat.getColor(getContext(), R.color.primary_glass_2);
        BaseImage barker = getBarker();
        barker.setScalingFactor(fScreenSizeToAspectRatio);
        barker.setImageStyle(new ImageStyle(-1, new OutlineStyle(color, 1), null, 4, null));
        barker.setImageSize(new ImageSize(bannerWidth, null, 2, null));
        barker.getImage().setScaleType(ImageView.ScaleType.CENTER_CROP);
        Integer cornerSize = getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize == null) {
            return;
        }
        barker.applyCornerRadius(cornerSize.intValue());
    }

    private final int getBannerWidth(ScreenSizeKey screenSize) {
        GridSize gridSize;
        Context context = getContext();
        if (context == null || (gridSize = this.styleSheet.getGridSize(context)) == null) {
            return 0;
        }
        int columnCount = toColumnCount(screenSize);
        return (StyleSheetKt.columnWidth(this.styleSheet, context) * columnCount) + (gridSize.getColumnSpace() * (columnCount - 1));
    }

    protected int toColumnCount(ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "<this>");
        if (screenSizeKey.compareTo(ScreenSizeKey.XLARGE3) >= 0) {
            return 8;
        }
        return screenSizeKey.compareTo(ScreenSizeKey.XLARGE2) >= 0 ? 10 : 12;
    }

    protected float screenSizeToAspectRatio(ScreenSizeKey screenSize) {
        Intrinsics.checkNotNullParameter(screenSize, "screenSize");
        return screenSize.compareTo(ScreenSizeKey.LARGE) >= 0 ? 4.0f : 1.7777778f;
    }
}
