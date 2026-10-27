package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.enums.DeviceTypeEnum;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.DividerStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.Divider;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingListItemHorizontalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010p\u001a\u00020o\u0012\u0006\u0010*\u001a\u00020)¢\u0006\u0004\bq\u0010rJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002JG\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJG\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u0012\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000e\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0016J\u000e\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001bJ\u000e\u0010!\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001bJ\u0010\u0010$\u001a\u00020\u00022\b\u0010#\u001a\u0004\u0018\u00010\"J\u000e\u0010%\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010'\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u001bJ\u000e\u0010(\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0016R\u0017\u0010*\u001a\u00020)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u001b\u00103\u001a\u00020.8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102R\u001b\u00107\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00100\u001a\u0004\b5\u00106R\u001b\u0010:\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00100\u001a\u0004\b9\u00106R\u001b\u0010=\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u00100\u001a\u0004\b<\u00106R\u001b\u0010@\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b>\u00100\u001a\u0004\b?\u00106R\u001b\u0010C\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u00100\u001a\u0004\bB\u00106R\u001b\u0010G\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u00100\u001a\u0004\bE\u0010FR\u001b\u0010L\u001a\u00020H8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bI\u00100\u001a\u0004\bJ\u0010KR\u001b\u0010P\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u00100\u001a\u0004\bN\u0010OR\u001b\u0010S\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u00100\u001a\u0004\bR\u0010OR\u001b\u0010X\u001a\u00020T8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bU\u00100\u001a\u0004\bV\u0010WR\u001b\u0010]\u001a\u00020Y8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u00100\u001a\u0004\b[\u0010\\R\u001b\u0010b\u001a\u00020^8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u00100\u001a\u0004\b`\u0010aR\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010gR\u0016\u0010h\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010gR\u0016\u0010i\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010gR\u0016\u0010j\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010gR\u0016\u0010k\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010gR\u0016\u0010l\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010gR\u0014\u0010m\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bm\u0010n¨\u0006s"}, d2 = {"Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "initViewMargins", "initViewStyling", "Landroid/widget/TextView;", "textView", "", "left", "top", "right", "bottom", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton", "setIconMargins", "(Lcom/amazon/ui/foundations/views/BaseButton;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setDisconnectButtonMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "width", "updateViewWidth", "", "show", "updateViewVisibility", "isCasting", "setIsCasting", "", "title", "setHeader", "setLabel", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "Lcom/amazon/music/views/library/models/enums/DeviceTypeEnum;", Splash.PARAMS_DEVICE_TYPE, "setUpDeviceType", "setDisconnectIcon", "detailsText", "setDetailsText", "showSpinner", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout$delegate", "Lkotlin/Lazy;", "getArtworkFrameLayout", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout", "headerTitleView$delegate", "getHeaderTitleView", "()Landroid/widget/TextView;", "headerTitleView", "labelTextView$delegate", "getLabelTextView", "labelTextView", "titleTextView$delegate", "getTitleTextView", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "detailTextView$delegate", "getDetailTextView", "detailTextView", "tileContainer$delegate", "getTileContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "tileContainer", "Landroid/widget/LinearLayout;", "textContainer$delegate", "getTextContainer", "()Landroid/widget/LinearLayout;", "textContainer", "deviceTypeIcon$delegate", "getDeviceTypeIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "deviceTypeIcon", "disconnectButton$delegate", "getDisconnectButton", "disconnectButton", "Lcom/amazon/ui/foundations/views/Divider;", "divider$delegate", "getDivider", "()Lcom/amazon/ui/foundations/views/Divider;", "divider", "Landroid/widget/FrameLayout;", "bottomView$delegate", "getBottomView", "()Landroid/widget/FrameLayout;", "bottomView", "Landroid/widget/ProgressBar;", "loadingView$delegate", "getLoadingView", "()Landroid/widget/ProgressBar;", "loadingView", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "disconnectButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "nanoSpace", "Ljava/lang/Integer;", "microSpace", "miniSpace", "smallSpace", "baseSpace", "mediumSpace", "buttonSize", "I", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CastingListItemHorizontalTileView extends ConstraintLayout {

    /* JADX INFO: renamed from: artworkFrameLayout$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameLayout;
    private final Integer baseSpace;

    /* JADX INFO: renamed from: bottomView$delegate, reason: from kotlin metadata */
    private final Lazy bottomView;
    private final int buttonSize;

    /* JADX INFO: renamed from: detailTextView$delegate, reason: from kotlin metadata */
    private final Lazy detailTextView;

    /* JADX INFO: renamed from: deviceTypeIcon$delegate, reason: from kotlin metadata */
    private final Lazy deviceTypeIcon;

    /* JADX INFO: renamed from: disconnectButton$delegate, reason: from kotlin metadata */
    private final Lazy disconnectButton;
    private BaseButton.StyleBuilder disconnectButtonBuilder;

    /* JADX INFO: renamed from: divider$delegate, reason: from kotlin metadata */
    private final Lazy divider;

    /* JADX INFO: renamed from: headerTitleView$delegate, reason: from kotlin metadata */
    private final Lazy headerTitleView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: loadingView$delegate, reason: from kotlin metadata */
    private final Lazy loadingView;
    private final Integer mediumSpace;
    private final Integer microSpace;
    private final Integer miniSpace;
    private final Integer nanoSpace;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: renamed from: tileContainer$delegate, reason: from kotlin metadata */
    private final Lazy tileContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: compiled from: CastingListItemHorizontalTileView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceTypeEnum.values().length];
            iArr[DeviceTypeEnum.PHONE.ordinal()] = 1;
            iArr[DeviceTypeEnum.COMPUTER.ordinal()] = 2;
            iArr[DeviceTypeEnum.ECHO.ordinal()] = 3;
            iArr[DeviceTypeEnum.TV.ordinal()] = 4;
            iArr[DeviceTypeEnum.TROUBLESHOOT.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingListItemHorizontalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameLayout = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$artworkFrameLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.artwork_frame_view);
            }
        });
        this.headerTitleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$headerTitleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.header_title);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.detailTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$detailTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.recommendation_text);
            }
        });
        this.tileContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$tileContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.horizontal_tile_view);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_container);
            }
        });
        this.deviceTypeIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$deviceTypeIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.device_type_icon);
            }
        });
        this.disconnectButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$disconnectButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.disconnect_button);
            }
        });
        this.divider = LazyKt__LazyJVMKt.lazy(new Function0<Divider>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$divider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Divider invoke() {
                return (Divider) this.this$0.findViewById(R$id.divider);
            }
        });
        this.bottomView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$bottomView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.bottom_view);
            }
        });
        this.loadingView = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.music.views.library.views.CastingListItemHorizontalTileView$loadingView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R$id.loading_spinner);
            }
        });
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.miniSpace = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.mediumSpace = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.SMALL);
        Integer height = iconSize == null ? null : iconSize.getHeight();
        this.buttonSize = height == null ? (int) getResources().getDimension(R$dimen.spacer_40) : height.intValue();
        View.inflate(context, R$layout.dmmviewlibrary_casting_list_item_horizontal_tile_view, this);
        initViewStyling();
        initViewMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameLayout() {
        Object value = this.artworkFrameLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameLayout>(...)");
        return (ArtworkFrameView) value;
    }

    private final TextView getHeaderTitleView() {
        Object value = this.headerTitleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headerTitleView>(...)");
        return (TextView) value;
    }

    private final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getDetailTextView() {
        Object value = this.detailTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-detailTextView>(...)");
        return (TextView) value;
    }

    private final ConstraintLayout getTileContainer() {
        Object value = this.tileContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tileContainer>(...)");
        return (ConstraintLayout) value;
    }

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final BaseButton getDeviceTypeIcon() {
        Object value = this.deviceTypeIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-deviceTypeIcon>(...)");
        return (BaseButton) value;
    }

    public final BaseButton getDisconnectButton() {
        Object value = this.disconnectButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-disconnectButton>(...)");
        return (BaseButton) value;
    }

    private final Divider getDivider() {
        Object value = this.divider.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-divider>(...)");
        return (Divider) value;
    }

    private final FrameLayout getBottomView() {
        Object value = this.bottomView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-bottomView>(...)");
        return (FrameLayout) value;
    }

    private final ProgressBar getLoadingView() {
        Object value = this.loadingView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-loadingView>(...)");
        return (ProgressBar) value;
    }

    private final void initViewMargins() {
        updateViewWidth(-1);
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTextContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : this.baseSpace, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : this.smallSpace, (240 & 128) != 0 ? null : 0);
        setTextMargins(getLabelTextView(), 0, 0, 0, this.nanoSpace);
        setTextMargins(getDetailTextView(), 0, 0, 0, this.nanoSpace);
        setIconMargins(getDeviceTypeIcon(), 0, this.miniSpace, 0, this.miniSpace);
        Integer num = this.nanoSpace;
        Integer numValueOf = null;
        if (num != null) {
            int iIntValue = num.intValue();
            Integer num2 = this.microSpace;
            if (num2 != null) {
                numValueOf = Integer.valueOf(num2.intValue() + iIntValue);
            }
        }
        setDisconnectButtonMargins(0, 0, numValueOf, 0);
    }

    public final void updateViewWidth(int width) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, width, -2, Boolean.TRUE, 0, 0, 0, 0);
        setForeground(AppCompatResources.getDrawable(getContext(), R$drawable.ripple));
    }

    public final void updateViewVisibility(boolean show) {
        int iIntValue;
        if (show) {
            getArtworkFrameLayout().setVisibility(0);
            getTextContainer().setVisibility(0);
        } else {
            getArtworkFrameLayout().setVisibility(8);
            getTextContainer().setVisibility(8);
        }
        if (show) {
            int i = this.buttonSize;
            Integer num = this.baseSpace;
            Intrinsics.checkNotNull(num);
            iIntValue = i + num.intValue();
        } else {
            iIntValue = this.buttonSize;
        }
        Integer num2 = this.smallSpace;
        if (num2 == null) {
            return;
        }
        num2.intValue();
        getTileContainer().setPadding(this.buttonSize, iIntValue, 0, 0);
        FrameLayout bottomView = getBottomView();
        Integer num3 = this.mediumSpace;
        Intrinsics.checkNotNull(num3);
        bottomView.setPadding(0, num3.intValue(), 0, 0);
    }

    private final void initViewStyling() {
        Divider.StyleBuilder styleBuilderWithLength;
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            fontUtil.applyFontStyle(getTitleTextView(), fontStyle2);
            fontUtil.applyFontStyle(getHeaderTitleView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle3);
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle4 != null) {
            FontUtil.INSTANCE.applyFontStyle(getDetailTextView(), fontStyle4);
        }
        this.disconnectButtonBuilder = this.styleSheet.getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.OUTLINE);
        Divider.StyleBuilder dividerBuilder = this.styleSheet.getDividerBuilder(DividerStyleKey.DEFAULT);
        if (dividerBuilder == null || (styleBuilderWithLength = dividerBuilder.withLength(getResources().getDisplayMetrics().widthPixels)) == null) {
            return;
        }
        styleBuilderWithLength.applyStyle(getDivider());
    }

    public final void setIsCasting(boolean isCasting) {
        FontStyle fontStyleCopy$default;
        int color = isCasting ? getResources().getColor(R$color.blue_accent) : getResources().getColor(R$color.white);
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, color, null, 11, null)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getHeaderTitleView(), fontStyleCopy$default);
        }
        if (isCasting) {
            BaseButton.updateState$default(getDeviceTypeIcon(), this.styleSheet.getIconStyle(IconStyleKey.ACCENT), null, null, 6, null);
        } else {
            BaseButton.updateState$default(getDeviceTypeIcon(), this.styleSheet.getIconStyle(IconStyleKey.PRIMARY), null, null, 6, null);
        }
    }

    public final void setHeader(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getHeaderTitleView().setText(title);
        getHeaderTitleView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setLabel(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getLabelTextView().setText(title);
        getLabelTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
    }

    public final void setUpDeviceType(DeviceTypeEnum deviceType) {
        int i;
        BaseButton.StyleBuilder iconBuilder;
        int i2 = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
        if (i2 == 1) {
            i = R$drawable.ic_navigation_alexa;
        } else if (i2 == 2) {
            i = R$drawable.ic_vol_type_tv_light;
        } else if (i2 == 3) {
            i = R$drawable.ic_navigation_alexa;
        } else if (i2 == 4) {
            i = R$drawable.ic_vol_type_tv_light;
        } else if (i2 == 5) {
            i = R$drawable.ic_alerts_help;
        } else {
            i = R$drawable.ic_navigation_alexa;
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), i);
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (drawable != null && (iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY)) != null) {
            styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        }
        if (styleBuilderWithIcon != null) {
            styleBuilderWithIcon.applyStyle(getDeviceTypeIcon());
        }
        getDeviceTypeIcon().setVisibility(0);
        if (deviceType == DeviceTypeEnum.TROUBLESHOOT) {
            String string = getResources().getString(R$string.casting_device_list_troubleshooting_missing_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…bleshooting_missing_text)");
            setHeader(string);
        }
    }

    public final void setDisconnectIcon(boolean show) {
        BaseButton.StyleBuilder styleBuilder = this.disconnectButtonBuilder;
        if (styleBuilder != null) {
            String string = getResources().getString(R$string.casting_tile_button_text);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…casting_tile_button_text)");
            BaseButton.StyleBuilder styleBuilderWithText = styleBuilder.withText(string);
            if (styleBuilderWithText != null) {
                styleBuilderWithText.applyStyle(getDisconnectButton());
            }
        }
        getDisconnectButton().setVisibility(show ? 0 : 8);
    }

    public final void setDetailsText(String detailsText) {
        Intrinsics.checkNotNullParameter(detailsText, "detailsText");
        getDetailTextView().setText(detailsText);
        getDetailTextView().setVisibility(!TextUtils.isEmpty(detailsText) ? 0 : 8);
    }

    public final void showSpinner(boolean show) {
        getLoadingView().setVisibility(show ? 0 : 8);
        getDisconnectButton().setVisibility(show ? 8 : 0);
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setIconMargins(BaseButton baseButton, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(baseButton, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setDisconnectButtonMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getDisconnectButton(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
