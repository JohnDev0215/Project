package com.amazon.music.views.library.views;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.DisplayState;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.providers.NestedContentView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TableRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u008b\u00012\u00020\u00012\u00020\u0002:\u0004\u008b\u0001\u008c\u0001B\u001b\u0012\b\u0010\u0088\u0001\u001a\u00030\u0087\u0001\u0012\u0006\u0010H\u001a\u00020G¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002JG\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016JB\u0010\"\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u00032\b\u0010 \u001a\u0004\u0018\u00010\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0003H&J\u0010\u0010%\u001a\u00020\r2\u0006\u0010$\u001a\u00020#H&J\u0010\u0010'\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0018H&J\b\u0010(\u001a\u00020\rH\u0004J\u0010\u0010*\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u0018H\u0016J\u0012\u0010-\u001a\u00020\r2\b\u0010,\u001a\u0004\u0018\u00010+H\u0014J\u0010\u00100\u001a\u00020\r2\u0006\u0010/\u001a\u00020.H\u0016J\u000e\u00101\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u00104\u001a\u00020\r2\u0006\u00103\u001a\u000202J\u000e\u00105\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u00106\u001a\u00020\r2\u0006\u00103\u001a\u000202J\u0010\u00107\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u000e\u00108\u001a\u00020\r2\u0006\u00103\u001a\u000202J\u0014\u0010:\u001a\u0004\u0018\u0001092\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0004J\b\u0010;\u001a\u00020\rH\u0004J\u0010\u0010<\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0010\u0010=\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0006\u0010>\u001a\u00020\rJ\u000e\u0010@\u001a\u00020\r2\u0006\u0010?\u001a\u00020\u0018J\u000e\u0010B\u001a\u00020\r2\u0006\u0010A\u001a\u00020\tJ\u0006\u0010C\u001a\u00020\rJ\b\u0010D\u001a\u00020\rH\u0016J\u0006\u0010E\u001a\u00020\rJ\u0006\u0010F\u001a\u00020\rR\u001a\u0010H\u001a\u00020G8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bH\u0010I\u001a\u0004\bJ\u0010KR\u001b\u0010Q\u001a\u00020L8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR$\u0010R\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR*\u0010/\u001a\u00020.2\u0006\u0010X\u001a\u00020.8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u0017\u0010_\u001a\u00020^8\u0006¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\u001c\u0010c\u001a\u0004\u0018\u0001098\u0004X\u0084\u0004¢\u0006\f\n\u0004\bc\u0010d\u001a\u0004\be\u0010fR\u001c\u0010g\u001a\u0004\u0018\u00010\u00058\u0004X\u0084\u0004¢\u0006\f\n\u0004\bg\u0010h\u001a\u0004\bi\u0010jR\u001a\u0010l\u001a\u00020k8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bl\u0010m\u001a\u0004\bn\u0010oR\u001d\u0010r\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bp\u0010N\u001a\u0004\bq\u0010jR\u001b\u0010w\u001a\u00020s8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bt\u0010N\u001a\u0004\bu\u0010vR\u001d\u0010|\u001a\u0004\u0018\u00010x8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\by\u0010N\u001a\u0004\bz\u0010{R\u0014\u0010~\u001a\u0002098&X¦\u0004¢\u0006\u0006\u001a\u0004\b}\u0010fR\u0015\u0010\u0080\u0001\u001a\u0002098&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010fR\u0018\u0010\u0084\u0001\u001a\u00030\u0081\u00018&X¦\u0004¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0086\u0001\u001a\u0002098&X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u0010f¨\u0006\u008d\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/TableRowItemView;", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "Lcom/amazon/music/views/library/providers/NestedContentView;", "", "badgeText", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getBadgeBuilder", "Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "getWidgetSizeClass", "", "backgroundColor", "Landroid/graphics/drawable/StateListDrawable;", "getBackgroundDrawable", "", "topAlignPrimaryTextContainer", "centerAlignPrimaryTextContainter", "Landroid/view/View;", "view", "left", "top", "right", "bottom", "setContentMargins", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "", "show", "showRowButton", "init", "rowNumber", ContextMappingConstants.PRIMARY_TEXT, "secondaryText1", "secondaryText2", "date", "time", "setText", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "state", "initDownloadIndicator", "isShown", "initExplicitIcon", "initMissingSDCardIcon", "enabled", "setEnabled", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Lcom/amazon/music/views/library/models/DisplayState;", "displayState", "onDisplayStateUpdated", "showOverflowButton", "Landroid/view/View$OnClickListener;", "onClickListener", "setOverflowOnClickListener", "showDownloadButton", "setDownloadButtonOnClickListener", "showSDCardIcon", "setMissingSDCardIconClickListener", "Lcom/amazon/ui/foundations/views/BaseButton;", "getBadge", "initRowButton", "initStereoAudioQualityBadge", "initSpatialAudioQualityBadge", "initLyricsBadge", "hasBadges", "updateTextConstraints", "upsellBadgeResource", "showUpsellBadge", "hideUpsellBadge", "disableRowButtonIcon", "updateRowIconForSuggestions", "resetRowIconForFormerSuggestions", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "rootView$delegate", "Lkotlin/Lazy;", "getRootView$DMMViewLibrary_release", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "rootView", "sizeClass", "Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "getSizeClass", "()Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "setSizeClass", "(Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;)V", "value", "Lcom/amazon/music/views/library/models/DisplayState;", "getDisplayState", "()Lcom/amazon/music/views/library/models/DisplayState;", "setDisplayState", "(Lcom/amazon/music/views/library/models/DisplayState;)V", "Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "downloadIndicatorView", "Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "getDownloadIndicatorView", "()Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "lyricsBadge", "Lcom/amazon/ui/foundations/views/BaseButton;", "getLyricsBadge", "()Lcom/amazon/ui/foundations/views/BaseButton;", "explicitBadgeBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getExplicitBadgeBuilder", "()Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "actionIconSizeKey", "Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "getActionIconSizeKey", "()Lcom/amazon/music/views/library/styles/keys/IconSizeKey;", "iconButtonBuilder$delegate", "getIconButtonBuilder", "iconButtonBuilder", "Landroid/widget/LinearLayout;", "badgingContainer$delegate", "getBadgingContainer", "()Landroid/widget/LinearLayout;", "badgingContainer", "Landroid/widget/ImageView;", "upsellBadge$delegate", "getUpsellBadge", "()Landroid/widget/ImageView;", "upsellBadge", "getOverflowIcon", "overflowIcon", "getDownloadButtonIcon", "downloadButtonIcon", "Landroid/widget/FrameLayout;", "getDownloadButtonContainer", "()Landroid/widget/FrameLayout;", "downloadButtonContainer", "getMissingSDCardIcon", "missingSDCardIcon", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "SIZE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class TableRowItemView extends LibraryAddableView implements NestedContentView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final IconSizeKey actionIconSizeKey;

    /* JADX INFO: renamed from: badgingContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgingContainer;
    private DisplayState displayState;
    private final DownloadIndicatorView downloadIndicatorView;
    private final BaseButton.StyleBuilder explicitBadgeBuilder;

    /* JADX INFO: renamed from: iconButtonBuilder$delegate, reason: from kotlin metadata */
    private final Lazy iconButtonBuilder;
    private final BaseButton lyricsBadge;

    /* JADX INFO: renamed from: rootView$delegate, reason: from kotlin metadata */
    private final Lazy rootView;
    private SIZE sizeClass;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: upsellBadge$delegate, reason: from kotlin metadata */
    private final Lazy upsellBadge;

    /* JADX INFO: compiled from: TableRowItemView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "", "(Ljava/lang/String;I)V", "SMALL", "MEDIUM", "LARGE", "XLARGE", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum SIZE {
        SMALL,
        MEDIUM,
        LARGE,
        XLARGE
    }

    /* JADX INFO: compiled from: TableRowItemView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DisplayState.values().length];
            iArr[DisplayState.DISABLED.ordinal()] = 1;
            iArr[DisplayState.DEEPLINK.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ScreenSizeKey.values().length];
            iArr2[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr2[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr2[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            iArr2[ScreenSizeKey.LARGE.ordinal()] = 4;
            iArr2[ScreenSizeKey.XLARGE.ordinal()] = 5;
            iArr2[ScreenSizeKey.XLARGE2.ordinal()] = 6;
            iArr2[ScreenSizeKey.XLARGE3.ordinal()] = 7;
            iArr2[ScreenSizeKey.XLARGE4.ordinal()] = 8;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public abstract /* synthetic */ View getContentView();

    public abstract FrameLayout getDownloadButtonContainer();

    public abstract BaseButton getDownloadButtonIcon();

    public abstract BaseButton getMissingSDCardIcon();

    public abstract BaseButton getOverflowIcon();

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public abstract /* synthetic */ BaseButton getRowButtonClickedImageView();

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public abstract /* synthetic */ View getRowButtonContainerView();

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public abstract /* synthetic */ BaseButton getRowButtonView();

    public abstract void initDownloadIndicator(DownloadStateModel.DownloadState state);

    public abstract void initExplicitIcon(boolean isShown);

    public abstract void setText(String rowNumber, String primaryText, String secondaryText1, String secondaryText2, String date, String time);

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TableRowItemView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.rootView = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.TableRowItemView$rootView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.layout_root);
            }
        });
        this.displayState = DisplayState.NORMAL;
        this.downloadIndicatorView = new DownloadIndicatorView(context, styleSheet);
        this.lyricsBadge = getBadge(context.getResources().getString(R$string.dmusic_lyrics_listview_badge));
        this.explicitBadgeBuilder = getBadgeBuilder("E");
        this.actionIconSizeKey = IconSizeKey.SMALL;
        this.iconButtonBuilder = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton.StyleBuilder>() { // from class: com.amazon.music.views.library.views.TableRowItemView$iconButtonBuilder$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton.StyleBuilder invoke() {
                return this.this$0.getStyleSheet().getIconBuilder(this.this$0.getActionIconSizeKey(), IconStyleKey.PRIMARY);
            }
        });
        this.badgingContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.TableRowItemView$badgingContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                LinearLayout linearLayout = (LinearLayout) this.this$0.findViewById(R$id.badge_container);
                if (linearLayout != null) {
                    TableRowItemView tableRowItemView = this.this$0;
                    TableRowItemView.setContentMargins$default(tableRowItemView, linearLayout, null, tableRowItemView.getStyleSheet().getSpacerInPixels(SpacerKey.MICRO), null, null, 26, null);
                }
                return linearLayout;
            }
        });
        this.upsellBadge = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.TableRowItemView$upsellBadge$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.upsell_badge);
            }
        });
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
    }

    public final ConstraintLayout getRootView$DMMViewLibrary_release() {
        Object value = this.rootView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rootView>(...)");
        return (ConstraintLayout) value;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView
    public void showRowButton(boolean show) {
        super.showRowButton(show && getDownloadButtonContainer().getVisibility() != 0);
    }

    /* JADX INFO: compiled from: TableRowItemView.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/views/TableRowItemView$Companion;", "", "()V", "TEXTDIVIDER", "", "applyFontStyle", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "textView", "Landroid/widget/TextView;", "fontStyleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "textSize", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/widget/TextView;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Ljava/lang/Integer;)V", "getDisabledColorForFontStyle", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;)Ljava/lang/Integer;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: TableRowItemView.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FontStyleKey.values().length];
                iArr[FontStyleKey.PRIMARY.ordinal()] = 1;
                iArr[FontStyleKey.SECONDARY.ordinal()] = 2;
                iArr[FontStyleKey.INDEX.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Integer getDisabledColorForFontStyle(StyleSheet styleSheet, FontStyleKey fontStyleKey) {
            int i = fontStyleKey == null ? -1 : WhenMappings.$EnumSwitchMapping$0[fontStyleKey.ordinal()];
            if (i == 1) {
                return styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
            }
            if (i == 2) {
                return styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2);
            }
            if (i != 3) {
                return null;
            }
            return styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2);
        }

        public static /* synthetic */ void applyFontStyle$default(Companion companion, StyleSheet styleSheet, TextView textView, FontStyleKey fontStyleKey, Integer num, int i, Object obj) {
            if ((i & 8) != 0) {
                num = null;
            }
            companion.applyFontStyle(styleSheet, textView, fontStyleKey, num);
        }

        public final void applyFontStyle(StyleSheet styleSheet, TextView textView, FontStyleKey fontStyleKey, Integer textSize) {
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            Intrinsics.checkNotNullParameter(textView, "textView");
            Intrinsics.checkNotNullParameter(fontStyleKey, "fontStyleKey");
            FontStyle fontStyle = styleSheet.getFontStyle(fontStyleKey);
            if (fontStyle == null) {
                return;
            }
            if (textSize != null) {
                fontStyle = FontStyle.copy$default(fontStyle, new FontSize(textSize.intValue(), null, 2, null), null, 0, null, 14, null);
            }
            Integer disabledColorForFontStyle = TableRowItemView.INSTANCE.getDisabledColorForFontStyle(styleSheet, fontStyleKey);
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
            textView.setTextColor(ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, fontStyle.getColor(), null, null, disabledColorForFontStyle, 6, null));
        }
    }

    public final SIZE getSizeClass() {
        return this.sizeClass;
    }

    public final void setSizeClass(SIZE size) {
        this.sizeClass = size;
    }

    public final DisplayState getDisplayState() {
        return this.displayState;
    }

    public final void setDisplayState(DisplayState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.displayState = value;
        onDisplayStateUpdated(value);
    }

    public final DownloadIndicatorView getDownloadIndicatorView() {
        return this.downloadIndicatorView;
    }

    protected final BaseButton getLyricsBadge() {
        return this.lyricsBadge;
    }

    protected final BaseButton.StyleBuilder getExplicitBadgeBuilder() {
        return this.explicitBadgeBuilder;
    }

    protected final IconSizeKey getActionIconSizeKey() {
        return this.actionIconSizeKey;
    }

    private final BaseButton.StyleBuilder getIconButtonBuilder() {
        return (BaseButton.StyleBuilder) this.iconButtonBuilder.getValue();
    }

    private final LinearLayout getBadgingContainer() {
        Object value = this.badgingContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgingContainer>(...)");
        return (LinearLayout) value;
    }

    private final ImageView getUpsellBadge() {
        return (ImageView) this.upsellBadge.getValue();
    }

    public void init() {
        this.sizeClass = getWidgetSizeClass();
    }

    protected final void initMissingSDCardIcon() {
        Drawable drawable;
        ButtonSize iconSize = this.styleSheet.getIconSize(this.actionIconSizeKey);
        if (iconSize == null || (drawable = AppCompatResources.getDrawable(getContext(), R$drawable.sd_card_missing)) == null) {
            return;
        }
        new BaseButton.StyleBuilder().withSize(iconSize).withIcon(drawable).applyStyle(getMissingSDCardIcon());
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getRowButtonClickedImageView().setEnabled(enabled);
        getRowButtonView().setEnabled(enabled);
        getOverflowIcon().setEnabled(enabled);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        init();
    }

    public void onDisplayStateUpdated(DisplayState displayState) {
        Intrinsics.checkNotNullParameter(displayState, "displayState");
        int i = WhenMappings.$EnumSwitchMapping$0[displayState.ordinal()];
        boolean z = false;
        if (i != 1) {
            if (i == 2) {
                Integer color = this.styleSheet.getColor(ColorKey.COLOR_ACCENT, AlphaKey.GLASS_1);
                if (color != null) {
                    setBackground(getBackgroundDrawable(color.intValue()));
                }
            } else {
                setBackground(getBackgroundDrawable(0));
            }
            z = true;
        } else {
            setBackground(getBackgroundDrawable(0));
        }
        setEnabled(z);
    }

    public final void showOverflowButton(boolean show) {
        BaseButton.StyleBuilder iconButtonBuilder;
        if (show && (iconButtonBuilder = getIconButtonBuilder()) != null) {
            Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_more);
            if (drawable != null) {
                iconButtonBuilder.withIcon(drawable);
            }
            iconButtonBuilder.applyStyle(getOverflowIcon());
        }
        getOverflowIcon().setVisibility(show ? 0 : 8);
    }

    public final void setOverflowOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getOverflowIcon().setOnClickListener(onClickListener);
    }

    public final void showDownloadButton(boolean show) {
        if (show) {
            showRowButton(false);
            BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
            if (iconButtonBuilder != null) {
                Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_download);
                if (drawable != null) {
                    iconButtonBuilder.withIcon(drawable);
                }
                iconButtonBuilder.applyStyle(getDownloadButtonIcon());
            }
            getDownloadButtonIcon().setVisibility(0);
            getDownloadButtonContainer().setVisibility(0);
            return;
        }
        getDownloadButtonContainer().setVisibility(8);
        getDownloadButtonIcon().setVisibility(8);
    }

    public final void setDownloadButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getDownloadButtonIcon().setOnClickListener(onClickListener);
    }

    public void showSDCardIcon(boolean show) {
        getMissingSDCardIcon().setVisibility(show ? 0 : 8);
    }

    public final void setMissingSDCardIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getMissingSDCardIcon().setOnClickListener(onClickListener);
    }

    protected final BaseButton getBadge(String badgeText) {
        BaseButton.StyleBuilder badgeBuilder = getBadgeBuilder(badgeText);
        if (badgeBuilder == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return badgeBuilder.build(context);
    }

    protected final void initRowButton() {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder iconBuilder2;
        BaseButton.StyleBuilder iconBuilder3;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_add);
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        setRowButtonIconBuilder((drawable == null || (iconBuilder = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) == null) ? null : iconBuilder.withIcon(drawable));
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_cloud_done);
        setRowButtonClickedIconBuilder((drawable2 == null || (iconBuilder2 = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) == null) ? null : iconBuilder2.withIcon(drawable2));
        Drawable drawable3 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favorite_svg);
        setLikesButtonIconBuilder((drawable3 == null || (iconBuilder3 = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) == null) ? null : iconBuilder3.withIcon(drawable3));
        Drawable drawable4 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favoriteon_svg);
        if (drawable4 != null) {
            drawable4.setColorFilter(getResources().getColor(R$color.cyan_accent), PorterDuff.Mode.MULTIPLY);
            BaseButton.StyleBuilder iconBuilder4 = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY);
            if (iconBuilder4 != null) {
                styleBuilderWithIcon = iconBuilder4.withIcon(drawable4);
            }
        }
        setLikesButtonClickedIconBuilder(styleBuilderWithIcon);
        initRowButtonStyling();
    }

    private final BaseButton.StyleBuilder getBadgeBuilder(String badgeText) {
        BaseButton.StyleBuilder tagBuilder;
        if (badgeText == null || (tagBuilder = this.styleSheet.getTagBuilder(TagSizeKey.SMALL, TagStyleKey.GLASS_PRIMARY)) == null) {
            return null;
        }
        return tagBuilder.withText(badgeText);
    }

    private final SIZE getWidgetSizeClass() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        switch (WhenMappings.$EnumSwitchMapping$1[styleSheet.getWidthScreenSizeKey(context).ordinal()]) {
            case 1:
            case 2:
            case 3:
                return SIZE.SMALL;
            case 4:
            case 5:
                return SIZE.MEDIUM;
            case 6:
                return SIZE.LARGE;
            case 7:
            case 8:
                return SIZE.XLARGE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final StateListDrawable getBackgroundDrawable(int backgroundColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
        if (color != null) {
            stateListDrawable.addState(new int[]{R.attr.state_pressed}, new ColorDrawable(color.intValue()));
        }
        stateListDrawable.addState(new int[0], new ColorDrawable(backgroundColor));
        return stateListDrawable;
    }

    public final void initStereoAudioQualityBadge(String badgeText) {
        if (TextUtils.isEmpty(badgeText)) {
            getBadgingContainer().removeAllViews();
            return;
        }
        BaseButton badge = getBadge(badgeText);
        if (badge != null) {
            setContentMargins$default(this, badge, null, null, getStyleSheet().getSpacerInPixels(SpacerKey.MICRO), null, 22, null);
        }
        getBadgingContainer().addView(badge);
        if (badge != null) {
            badge.setEnabled(isEnabled());
        }
        if (badge == null) {
            return;
        }
        badge.setId(R$id.AudioQualityBadge);
    }

    public final void initSpatialAudioQualityBadge(String badgeText) {
        if (TextUtils.isEmpty(badgeText)) {
            return;
        }
        BaseButton badge = getBadge(badgeText);
        if (badge != null) {
            StyleSheet styleSheet = getStyleSheet();
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            BadgeCreationHelper.setSpatialBadgeText$default(new BadgeCreationHelper(styleSheet, context), badgeText, badge, null, 4, null);
            setContentMargins$default(this, badge, null, null, getStyleSheet().getSpacerInPixels(SpacerKey.MICRO), null, 22, null);
        }
        getBadgingContainer().addView(badge);
        if (badge != null) {
            badge.setEnabled(isEnabled());
        }
        if (badge == null) {
            return;
        }
        badge.setId(R$id.ImmersiveBadge);
    }

    public final void initLyricsBadge() {
        BaseButton baseButton = this.lyricsBadge;
        if (baseButton != null) {
            setContentMargins$default(this, baseButton, null, null, getStyleSheet().getSpacerInPixels(SpacerKey.MICRO), null, 22, null);
        }
        getBadgingContainer().addView(baseButton);
        if (baseButton != null) {
            baseButton.setEnabled(isEnabled());
        }
        if (baseButton == null) {
            return;
        }
        baseButton.setId(R$id.LyricsView);
    }

    public final void updateTextConstraints(boolean hasBadges) {
        if (hasBadges) {
            topAlignPrimaryTextContainer();
        } else {
            centerAlignPrimaryTextContainter();
        }
    }

    private final void topAlignPrimaryTextContainer() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.layout_root);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(R$id.primary_text_container, 4);
        constraintSet.applyTo(constraintLayout);
    }

    private final void centerAlignPrimaryTextContainter() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.layout_root);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R$id.primary_text_container, 4, 0, 4, 0);
        constraintSet.applyTo(constraintLayout);
    }

    static /* synthetic */ void setContentMargins$default(TableRowItemView tableRowItemView, View view, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setContentMargins");
        }
        tableRowItemView.setContentMargins(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4);
    }

    private final void setContentMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
            view.setLayoutParams(layoutParams);
        }
        if (left != null) {
            layoutParams.leftMargin = left.intValue();
        }
        if (right != null) {
            layoutParams.rightMargin = right.intValue();
        }
        if (top != null) {
            layoutParams.topMargin = top.intValue();
        }
        if (bottom == null) {
            return;
        }
        layoutParams.bottomMargin = bottom.intValue();
    }

    public final void showUpsellBadge(int upsellBadgeResource) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), upsellBadgeResource);
        if (drawable == null) {
            return;
        }
        ImageView upsellBadge = getUpsellBadge();
        if (upsellBadge != null) {
            upsellBadge.setImageDrawable(drawable);
        }
        ImageView upsellBadge2 = getUpsellBadge();
        if (upsellBadge2 != null) {
            upsellBadge2.setVisibility(0);
        }
        getRowButtonContainerView().setVisibility(8);
        getRowButtonView().setVisibility(8);
        getOverflowIcon().setVisibility(8);
    }

    public final void hideUpsellBadge() {
        ImageView upsellBadge = getUpsellBadge();
        if (upsellBadge != null) {
            upsellBadge.setVisibility(8);
        }
        getOverflowIcon().setVisibility(0);
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView
    public void disableRowButtonIcon() {
        getRowButtonView().setVisibility(4);
        getRowButtonContainerView().setVisibility(4);
    }

    public final void updateRowIconForSuggestions() {
        BaseButton.StyleBuilder iconBuilder;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_add_inline);
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (drawable != null && (iconBuilder = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) != null) {
            styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        }
        setRowButtonIconBuilder(styleBuilderWithIcon);
        restyleRowButtonIcon();
    }

    public final void resetRowIconForFormerSuggestions() {
        BaseButton.StyleBuilder iconBuilder;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_add);
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (drawable != null && (iconBuilder = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) != null) {
            styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        }
        setRowButtonIconBuilder(styleBuilderWithIcon);
        restyleRowButtonIcon();
    }
}
