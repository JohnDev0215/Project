package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.SlotNumber;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.TableRowItemView;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptivePrimaryTextContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010i\u001a\u00020h\u0012\u0006\u0010k\u001a\u00020j¢\u0006\u0004\bl\u0010mJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\u001a\u0010\n\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J*\u0010\u0012\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\"\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0017J\u0018\u0010\u001e\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001d\u001a\u00020\u0013J\u0017\u0010!\u001a\u00020\u00022\b\u0010 \u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b!\u0010\"J\u001a\u0010'\u001a\u00020\u00022\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010&\u001a\u0004\u0018\u00010%J\u0006\u0010(\u001a\u00020\u0002J\u0018\u0010-\u001a\u00020\u00022\u0006\u0010*\u001a\u00020)2\b\b\u0002\u0010,\u001a\u00020+J\u0018\u00100\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00132\b\u0010/\u001a\u0004\u0018\u00010.R.\u00102\u001a\u0004\u0018\u0001012\b\u0010 \u001a\u0004\u0018\u0001018\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001d\u0010<\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001d\u0010A\u001a\u0004\u0018\u00010=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b>\u00109\u001a\u0004\b?\u0010@R\u001d\u0010F\u001a\u0004\u0018\u00010B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u00109\u001a\u0004\bD\u0010ER\u001d\u0010J\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bG\u00109\u001a\u0004\bH\u0010IR\u001d\u0010M\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u00109\u001a\u0004\bL\u0010;R\u001d\u0010P\u001a\u0004\u0018\u00010B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u00109\u001a\u0004\bO\u0010ER\u001b\u0010S\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u00109\u001a\u0004\bR\u0010IR\u001b\u0010V\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u00109\u001a\u0004\bU\u0010;R\u001d\u0010Y\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bW\u00109\u001a\u0004\bX\u0010IR\u001d\u0010^\u001a\u0004\u0018\u00010Z8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u00109\u001a\u0004\b\\\u0010]R\u0018\u0010_\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010$\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010aR\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010bR*\u0010c\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00138\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bc\u0010d\u001a\u0004\bc\u0010e\"\u0004\bf\u0010g¨\u0006n"}, d2 = {"Lcom/amazon/music/views/library/views/DescriptivePrimaryTextContainer;", "Landroid/widget/LinearLayout;", "", "initViewStyling", "setPrimaryTextMargins", "setDownloadingBadgeContainerMargins", "Landroid/widget/TextView;", "textView", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "fontStyleKey", "setFontStyle", "Landroid/view/View;", "view", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "spacerKey", "", "width", "height", "setViewTopMargin", "", "shouldDisplaySecondContainer", "enabled", "setEnabled", "", "primary", "second1", "second2", "setText", "commentaryText", "isShown", "showCommentary", "", "value", "showPopularityBar", "(Ljava/lang/Double;)V", "Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "size", "Lcom/amazon/music/views/library/models/SlotNumber;", "slotNumber", "updateView", "cleanViews", "Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "badge", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "state", "setDownloadIndicator", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconBuilder", "showExplicitIcon", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "primaryTextView$delegate", "Lkotlin/Lazy;", "getPrimaryTextView", "()Landroid/widget/TextView;", "primaryTextView", "Lcom/amazon/ui/foundations/views/BaseButton;", "explicitIcon$delegate", "getExplicitIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "explicitIcon", "Landroid/widget/FrameLayout;", "primaryDownloadingBadgeContainer$delegate", "getPrimaryDownloadingBadgeContainer", "()Landroid/widget/FrameLayout;", "primaryDownloadingBadgeContainer", "secondaryContainer$delegate", "getSecondaryContainer", "()Landroid/widget/LinearLayout;", "secondaryContainer", "secondaryTextView$delegate", "getSecondaryTextView", "secondaryTextView", "secondaryDownloadingBadgeContainer$delegate", "getSecondaryDownloadingBadgeContainer", "secondaryDownloadingBadgeContainer", "commentaryContainer$delegate", "getCommentaryContainer", "commentaryContainer", "commentaryTextView$delegate", "getCommentaryTextView", "commentaryTextView", "badgeContainer$delegate", "getBadgeContainer", "badgeContainer", "Lcom/amazon/music/views/library/views/TrackPopularityBar;", "popularityBar$delegate", "getPopularityBar", "()Lcom/amazon/music/views/library/views/TrackPopularityBar;", "popularityBar", "downloadingBadge", "Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "Lcom/amazon/music/views/library/views/TableRowItemView$SIZE;", "Lcom/amazon/music/views/library/models/SlotNumber;", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DescriptivePrimaryTextContainer extends LinearLayout {

    /* JADX INFO: renamed from: badgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgeContainer;

    /* JADX INFO: renamed from: commentaryContainer$delegate, reason: from kotlin metadata */
    private final Lazy commentaryContainer;

    /* JADX INFO: renamed from: commentaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy commentaryTextView;
    private DownloadIndicatorView downloadingBadge;

    /* JADX INFO: renamed from: explicitIcon$delegate, reason: from kotlin metadata */
    private final Lazy explicitIcon;
    private boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: popularityBar$delegate, reason: from kotlin metadata */
    private final Lazy popularityBar;

    /* JADX INFO: renamed from: primaryDownloadingBadgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy primaryDownloadingBadgeContainer;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: secondaryContainer$delegate, reason: from kotlin metadata */
    private final Lazy secondaryContainer;

    /* JADX INFO: renamed from: secondaryDownloadingBadgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy secondaryDownloadingBadgeContainer;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private TableRowItemView.SIZE size;
    private SlotNumber slotNumber;
    private StyleSheet styleSheet;

    /* JADX INFO: compiled from: DescriptivePrimaryTextContainer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TableRowItemView.SIZE.values().length];
            iArr[TableRowItemView.SIZE.SMALL.ordinal()] = 1;
            iArr[TableRowItemView.SIZE.MEDIUM.ordinal()] = 2;
            iArr[TableRowItemView.SIZE.LARGE.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptivePrimaryTextContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.explicitIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$explicitIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.explicit_icon);
            }
        });
        this.primaryDownloadingBadgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$primaryDownloadingBadgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.primary_downloading_badge_container);
            }
        });
        this.secondaryContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$secondaryContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.secondary_container);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.secondaryDownloadingBadgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$secondaryDownloadingBadgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.secondary_downloading_badge_container);
            }
        });
        this.commentaryContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$commentaryContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.commentary_container);
            }
        });
        this.commentaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$commentaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.commentary_text);
            }
        });
        this.badgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$badgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.badge_container);
            }
        });
        this.popularityBar = LazyKt__LazyJVMKt.lazy(new Function0<TrackPopularityBar>() { // from class: com.amazon.music.views.library.views.DescriptivePrimaryTextContainer$popularityBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TrackPopularityBar invoke() {
                return (TrackPopularityBar) this.this$0.findViewById(R$id.popularity_bar);
            }
        });
        LayoutInflater.from(getContext()).inflate(R$layout.dmmviewlibrary_descriptive_text_container, this);
        setOrientation(1);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final void setStyleSheet(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
        if (styleSheet != null) {
            initViewStyling();
        }
    }

    private final TextView getPrimaryTextView() {
        return (TextView) this.primaryTextView.getValue();
    }

    private final BaseButton getExplicitIcon() {
        return (BaseButton) this.explicitIcon.getValue();
    }

    private final FrameLayout getPrimaryDownloadingBadgeContainer() {
        return (FrameLayout) this.primaryDownloadingBadgeContainer.getValue();
    }

    private final LinearLayout getSecondaryContainer() {
        return (LinearLayout) this.secondaryContainer.getValue();
    }

    private final TextView getSecondaryTextView() {
        return (TextView) this.secondaryTextView.getValue();
    }

    private final FrameLayout getSecondaryDownloadingBadgeContainer() {
        return (FrameLayout) this.secondaryDownloadingBadgeContainer.getValue();
    }

    private final LinearLayout getCommentaryContainer() {
        Object value = this.commentaryContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-commentaryContainer>(...)");
        return (LinearLayout) value;
    }

    private final TextView getCommentaryTextView() {
        Object value = this.commentaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-commentaryTextView>(...)");
        return (TextView) value;
    }

    private final LinearLayout getBadgeContainer() {
        return (LinearLayout) this.badgeContainer.getValue();
    }

    private final TrackPopularityBar getPopularityBar() {
        return (TrackPopularityBar) this.popularityBar.getValue();
    }

    public final void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
        TrackPopularityBar popularityBar = getPopularityBar();
        if (popularityBar == null) {
            return;
        }
        popularityBar.setSonicRushEnabled(z);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        Float alpha;
        super.setEnabled(enabled);
        TextView primaryTextView = getPrimaryTextView();
        if (primaryTextView != null) {
            primaryTextView.setEnabled(enabled);
        }
        TextView secondaryTextView = getSecondaryTextView();
        if (secondaryTextView != null) {
            secondaryTextView.setEnabled(enabled);
        }
        BaseButton explicitIcon = getExplicitIcon();
        if (explicitIcon != null) {
            explicitIcon.setEnabled(enabled);
        }
        DownloadIndicatorView downloadIndicatorView = this.downloadingBadge;
        if (downloadIndicatorView != null) {
            downloadIndicatorView.setEnabled(enabled);
        }
        LinearLayout badgeContainer = getBadgeContainer();
        if (badgeContainer != null) {
            int childCount = badgeContainer.getChildCount();
            int i = 0;
            while (i < childCount) {
                int i2 = i + 1;
                LinearLayout badgeContainer2 = getBadgeContainer();
                View childAt = badgeContainer2 == null ? null : badgeContainer2.getChildAt(i);
                if (childAt != null) {
                    childAt.setEnabled(enabled);
                }
                i = i2;
            }
        }
        if (!enabled) {
            StyleSheet styleSheet = this.styleSheet;
            if (styleSheet == null || (alpha = styleSheet.getAlpha(AlphaKey.GLASS_3)) == null) {
                return;
            }
            float fFloatValue = alpha.floatValue();
            TrackPopularityBar popularityBar = getPopularityBar();
            if (popularityBar == null) {
                return;
            }
            popularityBar.setAlpha(fFloatValue);
            return;
        }
        TrackPopularityBar popularityBar2 = getPopularityBar();
        if (popularityBar2 == null) {
            return;
        }
        popularityBar2.setAlpha(1.0f);
    }

    public final void setText(String primary, String second1, String second2) {
        Intrinsics.checkNotNullParameter(primary, "primary");
        TextView primaryTextView = getPrimaryTextView();
        if (primaryTextView != null) {
            primaryTextView.setText(primary);
        }
        TableRowItemView.SIZE size = this.size;
        if (size == TableRowItemView.SIZE.XLARGE || second1 == null) {
            return;
        }
        if (size != TableRowItemView.SIZE.LARGE && !TextUtils.isEmpty(second2)) {
            second1 = second1 + " · " + ((Object) second2);
        }
        TextView secondaryTextView = getSecondaryTextView();
        if (secondaryTextView == null) {
            return;
        }
        secondaryTextView.setText(second1);
    }

    public final void showCommentary(String commentaryText, boolean isShown) {
        if (commentaryText == null) {
            return;
        }
        getCommentaryTextView().setText(commentaryText);
        getCommentaryContainer().setVisibility(isShown ? 0 : 8);
    }

    public final void showPopularityBar(Double value) {
        TrackPopularityBar popularity;
        TrackPopularityBar popularityBar;
        if (value == null) {
            TrackPopularityBar popularityBar2 = getPopularityBar();
            if (popularityBar2 == null) {
                return;
            }
            popularityBar2.setVisibility(8);
            return;
        }
        TrackPopularityBar popularityBar3 = getPopularityBar();
        if (popularityBar3 == null || (popularity = popularityBar3.setPopularity(value.doubleValue())) == null || popularity.show() || (popularityBar = getPopularityBar()) == null) {
            return;
        }
        popularityBar.setVisibility(8);
    }

    public final void updateView(TableRowItemView.SIZE size, SlotNumber slotNumber) {
        this.size = size;
        this.slotNumber = slotNumber;
        LinearLayout secondaryContainer = getSecondaryContainer();
        if (secondaryContainer == null) {
            return;
        }
        secondaryContainer.setVisibility(shouldDisplaySecondContainer() ? 0 : 8);
    }

    public final void cleanViews() {
        LinearLayout secondaryContainer = getSecondaryContainer();
        if (secondaryContainer != null) {
            secondaryContainer.setVisibility(8);
        }
        TrackPopularityBar popularityBar = getPopularityBar();
        if (popularityBar != null) {
            popularityBar.setVisibility(8);
        }
        BaseButton explicitIcon = getExplicitIcon();
        if (explicitIcon != null) {
            explicitIcon.setVisibility(8);
        }
        FrameLayout primaryDownloadingBadgeContainer = getPrimaryDownloadingBadgeContainer();
        if (primaryDownloadingBadgeContainer != null) {
            primaryDownloadingBadgeContainer.setVisibility(8);
        }
        FrameLayout secondaryDownloadingBadgeContainer = getSecondaryDownloadingBadgeContainer();
        if (secondaryDownloadingBadgeContainer != null) {
            secondaryDownloadingBadgeContainer.setVisibility(8);
        }
        LinearLayout badgeContainer = getBadgeContainer();
        if (badgeContainer != null) {
            badgeContainer.removeAllViews();
        }
        FrameLayout primaryDownloadingBadgeContainer2 = getPrimaryDownloadingBadgeContainer();
        if (primaryDownloadingBadgeContainer2 != null) {
            primaryDownloadingBadgeContainer2.removeAllViews();
        }
        FrameLayout secondaryDownloadingBadgeContainer2 = getSecondaryDownloadingBadgeContainer();
        if (secondaryDownloadingBadgeContainer2 != null) {
            secondaryDownloadingBadgeContainer2.removeAllViews();
        }
        LinearLayout commentaryContainer = getCommentaryContainer();
        if (commentaryContainer == null) {
            return;
        }
        commentaryContainer.setVisibility(8);
    }

    public final void setDownloadIndicator(DownloadIndicatorView badge, DownloadStateModel.DownloadState state) {
        FrameLayout secondaryDownloadingBadgeContainer;
        FrameLayout primaryDownloadingBadgeContainer;
        Intrinsics.checkNotNullParameter(badge, "badge");
        Intrinsics.checkNotNullParameter(state, "state");
        int i = state != DownloadStateModel.DownloadState.NO_STATE ? 0 : 8;
        if (!shouldDisplaySecondContainer()) {
            FrameLayout primaryDownloadingBadgeContainer2 = getPrimaryDownloadingBadgeContainer();
            if (primaryDownloadingBadgeContainer2 != null) {
                primaryDownloadingBadgeContainer2.setVisibility(i);
            }
            if (getPrimaryDownloadingBadgeContainer() != null && (primaryDownloadingBadgeContainer = getPrimaryDownloadingBadgeContainer()) != null) {
                primaryDownloadingBadgeContainer.removeView(badge);
            }
            FrameLayout primaryDownloadingBadgeContainer3 = getPrimaryDownloadingBadgeContainer();
            if (primaryDownloadingBadgeContainer3 != null) {
                primaryDownloadingBadgeContainer3.addView(badge);
            }
            FrameLayout secondaryDownloadingBadgeContainer2 = getSecondaryDownloadingBadgeContainer();
            if (secondaryDownloadingBadgeContainer2 != null) {
                secondaryDownloadingBadgeContainer2.setVisibility(8);
            }
        } else {
            FrameLayout secondaryDownloadingBadgeContainer3 = getSecondaryDownloadingBadgeContainer();
            if (secondaryDownloadingBadgeContainer3 != null) {
                secondaryDownloadingBadgeContainer3.setVisibility(i);
            }
            if (getSecondaryDownloadingBadgeContainer() != null && (secondaryDownloadingBadgeContainer = getSecondaryDownloadingBadgeContainer()) != null) {
                secondaryDownloadingBadgeContainer.removeView(badge);
            }
            FrameLayout secondaryDownloadingBadgeContainer4 = getSecondaryDownloadingBadgeContainer();
            if (secondaryDownloadingBadgeContainer4 != null) {
                secondaryDownloadingBadgeContainer4.addView(badge);
            }
            FrameLayout primaryDownloadingBadgeContainer4 = getPrimaryDownloadingBadgeContainer();
            if (primaryDownloadingBadgeContainer4 != null) {
                primaryDownloadingBadgeContainer4.setVisibility(8);
            }
        }
        badge.setEnabled(isEnabled());
        this.downloadingBadge = badge;
    }

    public final void showExplicitIcon(boolean isShown, BaseButton.StyleBuilder iconBuilder) {
        if (isShown) {
            BaseButton explicitIcon = getExplicitIcon();
            if (explicitIcon != null && iconBuilder != null) {
                iconBuilder.applyStyle(explicitIcon);
            }
            BaseButton explicitIcon2 = getExplicitIcon();
            if (explicitIcon2 == null) {
                return;
            }
            explicitIcon2.setVisibility(0);
            return;
        }
        BaseButton explicitIcon3 = getExplicitIcon();
        if (explicitIcon3 == null) {
            return;
        }
        explicitIcon3.setVisibility(8);
    }

    private final void initViewStyling() {
        Integer spacerInPixels;
        setViewTopMargin(getSecondaryContainer(), SpacerKey.NANO, -2, -2);
        LinearLayout badgeContainer = getBadgeContainer();
        SpacerKey spacerKey = SpacerKey.MICRO;
        setViewTopMargin(badgeContainer, spacerKey, -1, -2);
        TrackPopularityBar popularityBar = getPopularityBar();
        ViewGroup.LayoutParams layoutParams = popularityBar == null ? null : popularityBar.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        setViewTopMargin(getPopularityBar(), SpacerKey.MINI, -2, layoutParams2 == null ? 0 : layoutParams2.height);
        setFontStyle(getPrimaryTextView(), FontStyleKey.PRIMARY);
        TextView secondaryTextView = getSecondaryTextView();
        FontStyleKey fontStyleKey = FontStyleKey.SECONDARY;
        setFontStyle(secondaryTextView, fontStyleKey);
        setFontStyle(getCommentaryTextView(), fontStyleKey);
        setPrimaryTextMargins();
        setDownloadingBadgeContainerMargins();
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(spacerKey)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        BaseButton explicitIcon = getExplicitIcon();
        if (explicitIcon == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(explicitIcon, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(iIntValue), (240 & 128) != 0 ? null : null);
    }

    private final void setPrimaryTextMargins() {
        Integer spacerInPixels;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        TextView primaryTextView = getPrimaryTextView();
        ViewGroup.LayoutParams layoutParams = primaryTextView == null ? null : primaryTextView.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = iIntValue;
        }
        TextView primaryTextView2 = getPrimaryTextView();
        if (primaryTextView2 == null) {
            return;
        }
        primaryTextView2.requestLayout();
    }

    private final void setDownloadingBadgeContainerMargins() {
        Integer spacerInPixels;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        FrameLayout secondaryDownloadingBadgeContainer = getSecondaryDownloadingBadgeContainer();
        ViewGroup.LayoutParams layoutParams = secondaryDownloadingBadgeContainer == null ? null : secondaryDownloadingBadgeContainer.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.rightMargin = iIntValue;
        }
        FrameLayout secondaryDownloadingBadgeContainer2 = getSecondaryDownloadingBadgeContainer();
        if (secondaryDownloadingBadgeContainer2 == null) {
            return;
        }
        secondaryDownloadingBadgeContainer2.requestLayout();
    }

    private final void setFontStyle(TextView textView, FontStyleKey fontStyleKey) {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || textView == null) {
            return;
        }
        TableRowItemView.Companion.applyFontStyle$default(TableRowItemView.INSTANCE, styleSheet, textView, fontStyleKey, null, 8, null);
    }

    private final void setViewTopMargin(View view, SpacerKey spacerKey, int width, int height) {
        Integer spacerInPixels;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(spacerKey)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        if (view == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    private final boolean shouldDisplaySecondContainer() {
        TableRowItemView.SIZE size = this.size;
        int i = size == null ? -1 : WhenMappings.$EnumSwitchMapping$0[size.ordinal()];
        if (i == 1 || i == 2) {
            SlotNumber slotNumber = this.slotNumber;
            if (slotNumber != SlotNumber.TWO && slotNumber != SlotNumber.THREE) {
                return false;
            }
        } else if (i != 3 || this.slotNumber != SlotNumber.THREE) {
            return false;
        }
        return true;
    }
}
