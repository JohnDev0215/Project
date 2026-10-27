package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.ImageSize;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: CollectionDetailHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u0081\u00012\u00020\u0001:\u0006\u0081\u0001\u0082\u0001\u0083\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0002J\b\u0010c\u001a\u00020`H\u0016J\u0012\u0010d\u001a\u00020\u00072\b\u0010e\u001a\u0004\u0018\u00010bH\u0016J\u0018\u0010f\u001a\u00020\u00162\u0006\u0010]\u001a\u00020^2\u0006\u00101\u001a\u000202H\u0002J\u0010\u0010g\u001a\u00020\u00162\u0006\u0010]\u001a\u00020^H\u0002J\u0010\u0010h\u001a\u00020\u00162\u0006\u0010]\u001a\u00020^H\u0002J\u0010\u0010i\u001a\u00020\u00162\u0006\u0010J\u001a\u00020\u0016H\u0002J\b\u0010j\u001a\u00020kH\u0002J\u000f\u0010l\u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0002\u0010mJ\b\u0010n\u001a\u00020^H\u0002J\b\u0010o\u001a\u00020`H\u0016J\u0012\u0010p\u001a\u00020`2\b\u0010q\u001a\u0004\u0018\u00010rH\u0014J\b\u0010s\u001a\u00020`H\u0014J\u0012\u0010t\u001a\u00020\u00072\b\u0010u\u001a\u0004\u0018\u00010bH\u0016J\u000e\u0010v\u001a\u00020`2\u0006\u0010w\u001a\u00020xJ\u000e\u0010y\u001a\u00020`2\u0006\u0010z\u001a\u00020\u0007J\u0010\u0010{\u001a\u00020`2\u0006\u0010|\u001a\u00020\u0016H\u0002J\u0010\u0010}\u001a\u00020`2\u0006\u0010~\u001a\u00020IH\u0002J\u0010\u0010\u007f\u001a\u00020`2\u0006\u0010~\u001a\u00020IH\u0002J\u0011\u0010\u0080\u0001\u001a\u00020`2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002R\u001b\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\u00020\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010 \u001a\u00020\u001f2\u0006\u0010\u0015\u001a\u00020\u001f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u000f\u001a\u0004\b$\u0010\rR\u000e\u0010&\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020)8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u000f\u001a\u0004\b*\u0010+R\u0011\u0010-\u001a\u00020.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u00101\u001a\u0002028BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u00105\"\u0004\b6\u00107R\u001b\u00108\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u000f\u001a\u0004\b:\u0010;R\u001b\u0010=\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\u000f\u001a\u0004\b>\u0010;R\u001e\u0010@\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\bA\u0010\u0019R\u000e\u0010B\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010C\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u000f\u001a\u0004\bD\u0010\rR\u000e\u0010F\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010J\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\bK\u0010\u0019R\u001b\u0010L\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\u000f\u001a\u0004\bM\u0010;R\u001b\u0010O\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\u000f\u001a\u0004\bP\u0010\rR\u001b\u0010R\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\u000f\u001a\u0004\bS\u0010;R\u001a\u0010\b\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u00105\"\u0004\bV\u00107R\u001b\u0010W\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bY\u0010\u000f\u001a\u0004\bX\u0010;R\u001b\u0010Z\u001a\u0002098VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010\u000f\u001a\u0004\b[\u0010;R\u000e\u0010]\u001a\u00020^X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0084\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/CollectionDetailHeaderView;", "Lcom/amazon/music/views/library/views/PageHeaderView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isSonicRushEnabled", "", "shouldShowDownloadTextButton", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;ZZ)V", "actionIconContainer", "Landroid/widget/LinearLayout;", "getActionIconContainer", "()Landroid/widget/LinearLayout;", "actionIconContainer$delegate", "Lkotlin/Lazy;", "actionIcons", "Landroid/widget/RelativeLayout;", "getActionIcons", "()Landroid/widget/RelativeLayout;", "actionIcons$delegate", "value", "", "artworkHeight", "setArtworkHeight", "(I)V", "artworkImageView", "Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "getArtworkImageView", "()Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "artworkImageView$delegate", "", "artworkRatio", "setArtworkRatio", "(F)V", "badgeIconContainer", "getBadgeIconContainer", "badgeIconContainer$delegate", "baseOverlappedHeight", "baseY", "headlineTextView", "Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "getHeadlineTextView", "()Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "headlineTextView$delegate", "imageLoaderCallback", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "getImageLoaderCallback", "()Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "imageShape", "Lcom/amazon/music/views/library/views/CollectionDetailHeaderView$SHAPE;", "getImageShape", "()Lcom/amazon/music/views/library/views/CollectionDetailHeaderView$SHAPE;", "()Z", "setSonicRushEnabled", "(Z)V", "labelBadgeView", "Landroid/widget/TextView;", "getLabelBadgeView", "()Landroid/widget/TextView;", "labelBadgeView$delegate", "labelTextView", "getLabelTextView", "labelTextView$delegate", "maxArtworkHeight", "setMaxArtworkHeight", "maxOverlappedHeight", "metadataContainer", "getMetadataContainer", "metadataContainer$delegate", "minArtworkHeight", "minOverlappedHeight", "originalBitmap", "Landroid/graphics/Bitmap;", "overlappedHeight", "setOverlappedHeight", "primaryTextView", "getPrimaryTextView", "primaryTextView$delegate", "rightActionIconContainer", "getRightActionIconContainer", "rightActionIconContainer$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "getShouldShowDownloadTextButton", "setShouldShowDownloadTextButton", "tertiaryTextView", "getTertiaryTextView", "tertiaryTextView$delegate", "textView4", "getTextView4", "textView4$delegate", "widgetSize", "Lcom/amazon/music/views/library/views/CollectionDetailHeaderView$SIZE;", "calculateOverlappedHeight", "", "motionEvent", "Landroid/view/MotionEvent;", "clear", "dispatchTouchEvent", "ev", "getArtworkHeight", "getArtworkHeightForRectangle", "getArtworkHeightForSquare", "getArtworkSizeForOverlappedHeight", "getGradientStyle", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "getMaxAllowedArtworkHeightForAspectRatio", "()Ljava/lang/Integer;", "getWidgetSize", "init", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDetachedFromWindow", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "setCuratedByLink", "curatedByLink", "Landroid/text/SpannableString;", "setIs16x9Tile", "is16x9Tile", "setTextGravity", "gravity", "setupArtworkGradient", "bitmap", "updateArtworkGradientEffect", "updateArtworkSize", "Companion", "SHAPE", "SIZE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CollectionDetailHeaderView extends PageHeaderView {

    /* JADX INFO: renamed from: actionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy actionIconContainer;

    /* JADX INFO: renamed from: actionIcons$delegate, reason: from kotlin metadata */
    private final Lazy actionIcons;
    private int artworkHeight;

    /* JADX INFO: renamed from: artworkImageView$delegate, reason: from kotlin metadata */
    private final Lazy artworkImageView;
    private float artworkRatio;

    /* JADX INFO: renamed from: badgeIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgeIconContainer;
    private int baseOverlappedHeight;
    private int baseY;

    /* JADX INFO: renamed from: headlineTextView$delegate, reason: from kotlin metadata */
    private final Lazy headlineTextView;
    private final ImageLoader.ImageLoaderCallback imageLoaderCallback;
    private SHAPE imageShape;
    private boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: labelBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy labelBadgeView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private int maxArtworkHeight;
    private int maxOverlappedHeight;

    /* JADX INFO: renamed from: metadataContainer$delegate, reason: from kotlin metadata */
    private final Lazy metadataContainer;
    private int minArtworkHeight;
    private int minOverlappedHeight;
    private Bitmap originalBitmap;
    private int overlappedHeight;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: rightActionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy rightActionIconContainer;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private boolean shouldShowDownloadTextButton;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX INFO: renamed from: textView4$delegate, reason: from kotlin metadata */
    private final Lazy textView4;
    private SIZE widgetSize;

    /* JADX INFO: compiled from: CollectionDetailHeaderView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/views/library/views/CollectionDetailHeaderView$SHAPE;", "", "(Ljava/lang/String;I)V", "SQUARE", "RECTANGLE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SHAPE {
        SQUARE,
        RECTANGLE
    }

    /* JADX INFO: compiled from: CollectionDetailHeaderView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/views/CollectionDetailHeaderView$SIZE;", "", "(Ljava/lang/String;I)V", "SMALL", "MEDIUM", "LARGE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SIZE {
        SMALL,
        MEDIUM,
        LARGE
    }

    /* JADX INFO: compiled from: CollectionDetailHeaderView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[SHAPE.values().length];
            iArr[SHAPE.SQUARE.ordinal()] = 1;
            iArr[SHAPE.RECTANGLE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[SIZE.values().length];
            iArr2[SIZE.SMALL.ordinal()] = 1;
            iArr2[SIZE.MEDIUM.ordinal()] = 2;
            iArr2[SIZE.LARGE.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ScreenSizeKey.values().length];
            iArr3[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr3[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr3[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            iArr3[ScreenSizeKey.LARGE.ordinal()] = 4;
            iArr3[ScreenSizeKey.XLARGE.ordinal()] = 5;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public /* synthetic */ CollectionDetailHeaderView(Context context, StyleSheet styleSheet, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public boolean getShouldShowDownloadTextButton() {
        return this.shouldShowDownloadTextButton;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void setShouldShowDownloadTextButton(boolean z) {
        this.shouldShowDownloadTextButton = z;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionDetailHeaderView(Context context, StyleSheet styleSheet, boolean z, boolean z2) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.isSonicRushEnabled = z;
        this.shouldShowDownloadTextButton = z2;
        this.labelBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$labelBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_badge);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.headlineTextView = LazyKt__LazyJVMKt.lazy(new Function0<PageHeaderHeadlineTextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$headlineTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageHeaderHeadlineTextView invoke() {
                return (PageHeaderHeadlineTextView) this.this$0.findViewById(R$id.Title);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.Subtitle);
            }
        });
        this.textView4 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$textView4$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.text4);
            }
        });
        this.badgeIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$badgeIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.badge_container);
            }
        });
        this.actionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$actionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.action_icon_container);
            }
        });
        this.rightActionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$rightActionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.right_action_icon);
            }
        });
        this.actionIcons = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$actionIcons$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RelativeLayout invoke() {
                return (RelativeLayout) this.this$0.findViewById(R$id.action_icons);
            }
        });
        this.artworkImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$artworkImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.collection_artwork);
            }
        });
        this.imageLoaderCallback = new CollectionDetailHeaderView$imageLoaderCallback$1(this);
        this.metadataContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$metadataContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.metadata_container);
            }
        });
        this.artworkRatio = 1.0f;
        SIZE size = SIZE.SMALL;
        SHAPE shape = SHAPE.SQUARE;
        this.minArtworkHeight = getArtworkHeight(size, shape);
        this.maxArtworkHeight = getArtworkHeight(SIZE.LARGE, shape);
        this.widgetSize = size;
        this.imageShape = shape;
        this.artworkHeight = this.minArtworkHeight;
        init();
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getLabelBadgeView() {
        Object value = this.labelBadgeView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelBadgeView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public PageHeaderHeadlineTextView getHeadlineTextView() {
        Object value = this.headlineTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headlineTextView>(...)");
        return (PageHeaderHeadlineTextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getTertiaryTextView() {
        Object value = this.tertiaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tertiaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getTextView4() {
        Object value = this.textView4.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textView4>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public LinearLayout getBadgeIconContainer() {
        Object value = this.badgeIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgeIconContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public LinearLayout getActionIconContainer() {
        Object value = this.actionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIconContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public LinearLayout getRightActionIconContainer() {
        Object value = this.rightActionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rightActionIconContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public RelativeLayout getActionIcons() {
        Object value = this.actionIcons.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIcons>(...)");
        return (RelativeLayout) value;
    }

    public final TransparentGradientImageView getArtworkImageView() {
        Object value = this.artworkImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkImageView>(...)");
        return (TransparentGradientImageView) value;
    }

    public final ImageLoader.ImageLoaderCallback getImageLoaderCallback() {
        return this.imageLoaderCallback;
    }

    private final LinearLayout getMetadataContainer() {
        Object value = this.metadataContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-metadataContainer>(...)");
        return (LinearLayout) value;
    }

    private final void setArtworkRatio(float f) {
        this.artworkRatio = f;
        this.minArtworkHeight = getArtworkHeight(this.widgetSize, getImageShape());
        setMaxArtworkHeight(getArtworkHeight(SIZE.LARGE, getImageShape()));
    }

    private final void setMaxArtworkHeight(int i) {
        this.maxArtworkHeight = ((Number) RangesKt___RangesKt.coerceIn(Integer.valueOf(i), (Integer) 0, getMaxAllowedArtworkHeightForAspectRatio())).intValue();
    }

    private final SHAPE getImageShape() {
        return (this.artworkRatio > 1.7777778f ? 1 : (this.artworkRatio == 1.7777778f ? 0 : -1)) == 0 ? SHAPE.RECTANGLE : SHAPE.SQUARE;
    }

    private final void setArtworkHeight(int i) {
        this.artworkHeight = i;
        updateArtworkSize(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setOverlappedHeight(int i) {
        if (getIsSonicRushEnabled()) {
            return;
        }
        this.overlappedHeight = i;
        PageHeaderView.setMargins$default(this, getMetadataContainer(), 0, 0, null, Integer.valueOf(-this.overlappedHeight), null, null, 110, null);
        setArtworkHeight(getArtworkSizeForOverlappedHeight(i));
        Bitmap bitmap = this.originalBitmap;
        if (bitmap == null) {
            return;
        }
        updateArtworkGradientEffect(bitmap);
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void init() {
        int i;
        SIZE widgetSize = getWidgetSize();
        this.widgetSize = widgetSize;
        this.minArtworkHeight = getArtworkHeight(widgetSize, getImageShape());
        if (!getIsSonicRushEnabled() || isTabletDevice()) {
            i = isTabletDevice() ? R$layout.dmmviewlibrary_collection_detail_header_layout_large : R$layout.dmmviewlibrary_collection_detail_header_layout;
        } else {
            i = R$layout.dmmviewlibrary_collection_detail_header_layout_sr;
        }
        LayoutInflater.from(getContext()).inflate(i, this);
        super.init();
        getArtworkImageView().initStyleSheet(getStyleSheet());
        if (this.widgetSize != SIZE.SMALL || getIsSonicRushEnabled()) {
            GridSize gridSize = getStyleSheet().getGridSize();
            if (gridSize != null) {
                PageHeaderView.setMargins$default(this, getMetadataContainer(), 0, 0, Integer.valueOf(gridSize.getColumnSpace()), null, null, null, 116, null);
            }
            setTextGravity(8388611);
            setArtworkHeight(getArtworkSizeForOverlappedHeight(this.overlappedHeight));
            return;
        }
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.EPIC);
        if (spacerInPixels != null) {
            int iIntValue = spacerInPixels.intValue();
            setOverlappedHeight(iIntValue);
            this.maxOverlappedHeight = iIntValue;
            this.minOverlappedHeight = -iIntValue;
        }
        setTextGravity(1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev == null || this.widgetSize != SIZE.SMALL) {
            return super.dispatchTouchEvent(ev);
        }
        int action = ev.getAction();
        if (action == 0) {
            clearAnimation();
            this.baseY = (int) ev.getRawY();
            this.baseOverlappedHeight = this.overlappedHeight;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            return super.dispatchTouchEvent(ev);
        }
        if (action == 1) {
            this.baseOverlappedHeight = this.overlappedHeight;
            Animation animation = new Animation() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView$dispatchTouchEvent$animation$1
                @Override // android.view.animation.Animation
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    this.this$0.setOverlappedHeight((int) ((interpolatedTime * (r4.maxOverlappedHeight - this.this$0.baseOverlappedHeight)) + this.this$0.baseOverlappedHeight));
                }
            };
            animation.setDuration(500L);
            animation.setInterpolator(new DecelerateInterpolator());
            startAnimation(animation);
            ViewParent parent2 = getParent();
            if (parent2 != null) {
                parent2.requestDisallowInterceptTouchEvent(false);
            }
            return super.dispatchTouchEvent(ev);
        }
        if (action == 2) {
            if (getParent() != null && (getParent() instanceof RecyclerView)) {
                ViewParent parent3 = getParent();
                if (parent3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
                }
                if (((RecyclerView) parent3).canScrollVertically(-1)) {
                    ViewParent parent4 = getParent();
                    if (parent4 != null) {
                        parent4.requestDisallowInterceptTouchEvent(false);
                    }
                    return super.dispatchTouchEvent(ev);
                }
                calculateOverlappedHeight(ev);
                return true;
            }
            calculateOverlappedHeight(ev);
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.widgetSize == SIZE.SMALL) {
            if (event != null && event.getAction() == 0) {
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView, android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        init();
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void clear() {
        getArtworkImageView().animate().cancel();
        clearAnimation();
        Drawable drawable = getResources().getDrawable(R$drawable.placeholder_sm, null);
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;
        if (bitmapDrawable != null) {
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "it.bitmap");
            updateArtworkGradientEffect(bitmap);
        }
        super.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getArtworkImageView().animate().cancel();
        clearAnimation();
    }

    private final void calculateOverlappedHeight(MotionEvent motionEvent) {
        ViewParent parent;
        int rawY = this.baseOverlappedHeight - (((int) motionEvent.getRawY()) - this.baseY);
        if (rawY >= this.maxOverlappedHeight + 20 && (parent = getParent()) != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        setOverlappedHeight(RangesKt___RangesKt.coerceIn(rawY, this.minOverlappedHeight, this.maxOverlappedHeight));
    }

    public final void setIs16x9Tile(boolean is16x9Tile) {
        setArtworkRatio(is16x9Tile ? 1.7777778f : 1.0f);
        setArtworkHeight(this.minArtworkHeight);
    }

    private final int getArtworkSizeForOverlappedHeight(int overlappedHeight) {
        int i = this.maxOverlappedHeight;
        float f = i - (-i);
        if (f == 0.0f) {
            return this.minArtworkHeight;
        }
        int i2 = this.maxArtworkHeight;
        return ((int) (((i - overlappedHeight) / f) * (i2 - r3))) + this.minArtworkHeight;
    }

    private final void updateArtworkSize(int artworkHeight) {
        ImageSize imageSize = getStyleSheet().getImageSize(artworkHeight);
        if (imageSize == null) {
            return;
        }
        Integer cornerSize = getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM);
        if (cornerSize != null) {
            getArtworkImageView().setCornerRadius(cornerSize.intValue());
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = null;
        if (this.widgetSize != SIZE.SMALL || getIsSonicRushEnabled()) {
            ViewGroup.LayoutParams layoutParams = getArtworkImageView().getLayoutParams();
            if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                marginLayoutParams = (ConstraintLayout.LayoutParams) layoutParams;
            }
        } else {
            ViewGroup.LayoutParams layoutParams2 = getArtworkImageView().getLayoutParams();
            if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                marginLayoutParams = (RelativeLayout.LayoutParams) layoutParams2;
            }
        }
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.width = (int) (imageSize.getSize() * this.artworkRatio);
        marginLayoutParams.height = imageSize.getSize();
        getArtworkImageView().requestLayout();
    }

    private final void setTextGravity(int gravity) {
        getMetadataContainer().setGravity(gravity);
        ((LinearLayout) findViewById(R$id.text_container)).setGravity(gravity);
        getLabelTextView().setGravity(gravity);
        getHeadlineTextView().setGravity(gravity);
        getPrimaryTextView().setGravity(gravity);
        getSecondaryTextView().setGravity(gravity);
        getTertiaryTextView().setGravity(gravity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateArtworkGradientEffect(final Bitmap bitmap) {
        if (this.artworkHeight == 0) {
            getArtworkImageView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.views.library.views.CollectionDetailHeaderView.updateArtworkGradientEffect.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    CollectionDetailHeaderView.this.getArtworkImageView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    CollectionDetailHeaderView.this.setupArtworkGradient(bitmap);
                }
            });
        } else {
            setupArtworkGradient(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupArtworkGradient(Bitmap bitmap) {
        GradientStyle gradientStyle = getGradientStyle();
        float[] floatArray = CollectionsKt___CollectionsKt.toFloatArray(gradientStyle.getPositions());
        int[] intArray = CollectionsKt___CollectionsKt.toIntArray(gradientStyle.getColors());
        TransparentGradientImageView artworkImageView = getArtworkImageView();
        int i = this.artworkHeight;
        artworkImageView.applyTransparentGradientToBottom(bitmap, floatArray, intArray, i * this.artworkRatio, i, true);
        getArtworkImageView().invalidate();
    }

    private final GradientStyle getGradientStyle() {
        List listListOf;
        SIZE size = this.widgetSize;
        SIZE size2 = SIZE.SMALL;
        Float fValueOf = Float.valueOf(0.0f);
        Float fValueOf2 = Float.valueOf(1.0f);
        if (size != size2 || getIsSonicRushEnabled()) {
            return new GradientStyle(CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{-1, -1}), CollectionsKt__CollectionsKt.listOf((Object[]) new Float[]{fValueOf, fValueOf2}), false);
        }
        float f = this.overlappedHeight / this.artworkHeight;
        float fIntValue = (this.overlappedHeight + (getStyleSheet().getSpacerInPixels(SpacerKey.EPIC) == null ? 0 : r1.intValue())) / this.artworkHeight;
        if (f >= 0.0f) {
            listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{-1, -1, 0, 0});
        } else {
            listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{-1, -1, 0});
        }
        return new GradientStyle(listListOf, f >= 0.0f ? CollectionsKt__CollectionsKt.listOf((Object[]) new Float[]{fValueOf, Float.valueOf(1.0f - fIntValue), Float.valueOf(1.0f - f), fValueOf2}) : CollectionsKt__CollectionsKt.listOf((Object[]) new Float[]{fValueOf, Float.valueOf(1.0f - fIntValue), fValueOf2}), false);
    }

    private final int getArtworkHeight(SIZE widgetSize, SHAPE imageShape) {
        int i = WhenMappings.$EnumSwitchMapping$0[imageShape.ordinal()];
        if (i == 1) {
            return getArtworkHeightForSquare(widgetSize);
        }
        if (i == 2) {
            return getArtworkHeightForRectangle(widgetSize);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final int getArtworkHeightForSquare(SIZE widgetSize) {
        int i = WhenMappings.$EnumSwitchMapping$1[widgetSize.ordinal()];
        if (i == 1) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_small_square);
        }
        if (i == 2) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_medium_square);
        }
        if (i == 3) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_large_square);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final int getArtworkHeightForRectangle(SIZE widgetSize) {
        int i = WhenMappings.$EnumSwitchMapping$1[widgetSize.ordinal()];
        if (i == 1) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_small_rectangle);
        }
        if (i == 2) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_medium_rectangle);
        }
        if (i == 3) {
            return getResources().getDimensionPixelSize(R$dimen.collection_detail_header_artwork_size_large_rectangle);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final SIZE getWidgetSize() {
        int i = WhenMappings.$EnumSwitchMapping$2[getStyleSheet().getWidthScreenSizeKey().ordinal()];
        if (i == 1) {
            return SIZE.SMALL;
        }
        if (i == 2 || i == 3) {
            return getIsSonicRushEnabled() ? SIZE.MEDIUM : SIZE.SMALL;
        }
        if (i == 4 || i == 5) {
            return SIZE.MEDIUM;
        }
        return SIZE.LARGE;
    }

    private final Integer getMaxAllowedArtworkHeightForAspectRatio() {
        Object parent = getArtworkImageView().getParent();
        if ((parent instanceof View ? (View) parent : null) == null) {
            return null;
        }
        return Integer.valueOf((int) (r0.getWidth() / this.artworkRatio));
    }

    public final void setCuratedByLink(SpannableString curatedByLink) {
        Intrinsics.checkNotNullParameter(curatedByLink, "curatedByLink");
        getPrimaryTextView().setText(curatedByLink);
        getPrimaryTextView().setMovementMethod(LinkMovementMethod.getInstance());
        getPrimaryTextView().setHighlightColor(0);
    }
}
