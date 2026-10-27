package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistDetailHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 U2\u00020\u0001:\u0001UB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u00109\u001a\u00020:H\u0002J\u0018\u0010;\u001a\u00020:2\u0006\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020\u0007H\u0002J\u0016\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0007J\u0016\u0010B\u001a\u00020:2\u0006\u0010C\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0007J\u000e\u0010D\u001a\u00020:2\u0006\u0010?\u001a\u00020@J\u000e\u0010E\u001a\u00020:2\u0006\u0010?\u001a\u00020@J\u000e\u0010F\u001a\u00020:2\u0006\u0010?\u001a\u00020@J\u000e\u0010G\u001a\u00020:2\u0006\u0010?\u001a\u00020@J\u000e\u0010H\u001a\u00020:2\u0006\u0010I\u001a\u00020\u0007J\u0016\u0010J\u001a\u00020:2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0007J\u0010\u0010K\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007H\u0002J\u0016\u0010K\u001a\u00020:2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0007J\u000e\u0010M\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007J\u0010\u0010N\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007H\u0002J\u0010\u0010O\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007H\u0002J\u000e\u0010P\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007J\u0016\u0010Q\u001a\u00020:2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0007J\u000e\u0010R\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007J\u0016\u0010S\u001a\u00020:2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010=\u001a\u00020\u0007J\u0010\u0010T\u001a\u00020:2\u0006\u0010L\u001a\u00020\u0007H\u0002J\u0016\u0010T\u001a\u00020:2\u0006\u0010L\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0007R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0017\u001a\u00020\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001d\u0010\fR\u001b\u0010\u001f\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b \u0010\fR\u001b\u0010\"\u001a\u00020#8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u000e\u001a\u0004\b$\u0010%R\u001b\u0010'\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u000e\u001a\u0004\b(\u0010\fR\u001b\u0010*\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u000e\u001a\u0004\b+\u0010\fR\u001b\u0010-\u001a\u00020\u00188VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u000e\u001a\u0004\b.\u0010\u001aR\u001b\u00100\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u000e\u001a\u0004\b1\u0010\fR\u001b\u00103\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u000e\u001a\u0004\b4\u0010\fR\u001b\u00106\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010\u000e\u001a\u0004\b7\u0010\f¨\u0006V"}, d2 = {"Lcom/amazon/music/views/library/views/ArtistDetailHeaderView;", "Lcom/amazon/music/views/library/views/PageHeaderView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isSonicRushEnabled", "", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Z)V", "followIcon", "Lcom/amazon/ui/foundations/views/BaseButton;", "getFollowIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "followIcon$delegate", "Lkotlin/Lazy;", "headlineTextView", "Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "getHeadlineTextView", "()Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "headlineTextView$delegate", "()Z", "setSonicRushEnabled", "(Z)V", "labelTextView", "Landroid/widget/TextView;", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView$delegate", "largeFollowButton", "getLargeFollowButton", "largeFollowButton$delegate", "largeStationButton", "getLargeStationButton", "largeStationButton$delegate", "maximumHeadlineFontStyleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getMaximumHeadlineFontStyleKey", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "maximumHeadlineFontStyleKey$delegate", "overflowIcon", "getOverflowIcon", "overflowIcon$delegate", "playIcon", "getPlayIcon", "playIcon$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "shareIcon", "getShareIcon", "shareIcon$delegate", "shuffleIcon", "getShuffleIcon", "shuffleIcon$delegate", "stationIcon", "getStationIcon", "stationIcon$delegate", "setConstraints", "", "setEnability", "button", Constants.ENABLE_DISABLE, "setFollowIconClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "withLargeButton", "setIsFollowed", "isFollowed", "setOverflowOnClickListener", "setPlayIconClickListener", "setShareIconClickListener", "setShuffleIconClickListener", "setStationEligibility", "enableStations", "setStationIconClickListener", "showFollowIcon", "show", "showImageSpace", "showLargeFollowButton", "showLargeStationButton", "showOverflowMenu", "showPlayIcon", "showShareIcon", "showShuffleIcon", "showStationIcon", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ArtistDetailHeaderView extends PageHeaderView {

    /* JADX INFO: renamed from: followIcon$delegate, reason: from kotlin metadata */
    private final Lazy followIcon;

    /* JADX INFO: renamed from: headlineTextView$delegate, reason: from kotlin metadata */
    private final Lazy headlineTextView;
    private boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: largeFollowButton$delegate, reason: from kotlin metadata */
    private final Lazy largeFollowButton;

    /* JADX INFO: renamed from: largeStationButton$delegate, reason: from kotlin metadata */
    private final Lazy largeStationButton;

    /* JADX INFO: renamed from: maximumHeadlineFontStyleKey$delegate, reason: from kotlin metadata */
    private final Lazy maximumHeadlineFontStyleKey;

    /* JADX INFO: renamed from: overflowIcon$delegate, reason: from kotlin metadata */
    private final Lazy overflowIcon;

    /* JADX INFO: renamed from: playIcon$delegate, reason: from kotlin metadata */
    private final Lazy playIcon;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;

    /* JADX INFO: renamed from: shareIcon$delegate, reason: from kotlin metadata */
    private final Lazy shareIcon;

    /* JADX INFO: renamed from: shuffleIcon$delegate, reason: from kotlin metadata */
    private final Lazy shuffleIcon;

    /* JADX INFO: renamed from: stationIcon$delegate, reason: from kotlin metadata */
    private final Lazy stationIcon;

    @Override // com.amazon.music.views.library.views.PageHeaderView
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArtistDetailHeaderView(Context context, StyleSheet styleSheet, boolean z) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.isSonicRushEnabled = z;
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.headlineTextView = LazyKt__LazyJVMKt.lazy(new Function0<PageHeaderHeadlineTextView>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$headlineTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageHeaderHeadlineTextView invoke() {
                return (PageHeaderHeadlineTextView) this.this$0.findViewById(R$id.header_text);
            }
        });
        this.maximumHeadlineFontStyleKey = LazyKt__LazyJVMKt.lazy(new Function0<FontStyleKey>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$maximumHeadlineFontStyleKey$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FontStyleKey invoke() {
                return FontStyleKey.HEADLINE_1;
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.follower_count_text);
            }
        });
        this.playIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$playIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.play_icon);
            }
        });
        this.shuffleIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$shuffleIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.shuffle_icon);
            }
        });
        this.stationIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$stationIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.station_icon);
            }
        });
        this.overflowIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$overflowIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.overflow_icon);
            }
        });
        this.largeFollowButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$largeFollowButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.large_follow_button);
            }
        });
        this.largeStationButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$largeStationButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.large_station_button);
            }
        });
        this.followIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$followIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.follow_icon);
            }
        });
        this.shareIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.ArtistDetailHeaderView$shareIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.share_icon);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_artist_detail_header_layout, this);
        setConstraints();
        init();
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
    public FontStyleKey getMaximumHeadlineFontStyleKey() {
        return (FontStyleKey) this.maximumHeadlineFontStyleKey.getValue();
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getPlayIcon() {
        Object value = this.playIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getShuffleIcon() {
        Object value = this.shuffleIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-shuffleIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getStationIcon() {
        Object value = this.stationIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-stationIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getOverflowIcon() {
        Object value = this.overflowIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overflowIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getLargeFollowButton() {
        Object value = this.largeFollowButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-largeFollowButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getLargeStationButton() {
        Object value = this.largeStationButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-largeStationButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getFollowIcon() {
        Object value = this.followIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-followIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getShareIcon() {
        Object value = this.shareIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-shareIcon>(...)");
        return (BaseButton) value;
    }

    public final void showImageSpace(boolean show) {
        Integer spacerInPixels;
        if (!show || (spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.EPIC)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        setPadding(0, iIntValue + iIntValue, 0, 0);
    }

    public final void showPlayIcon(boolean show, boolean isEnabled) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder accentIconButtonBuilder = getIsSonicRushEnabled() ? getAccentIconButtonBuilder() : getIconButtonBuilder();
        if (show) {
            Drawable playDrawable = getPlayDrawable();
            if (playDrawable != null && accentIconButtonBuilder != null && (styleBuilderWithIcon = accentIconButtonBuilder.withIcon(playDrawable)) != null) {
                styleBuilderWithIcon.applyStyle(getPlayIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
            PageHeaderView.setMargins$default(this, getPlayIcon(), 0, 0, null, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, 110, null);
            setEnability(getPlayIcon(), isEnabled);
        }
        getPlayIcon().setVisibility(show ? 0 : 8);
    }

    public final void showShuffleIcon(boolean show, boolean isEnabled) {
        BaseButton.StyleBuilder iconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (show) {
            Drawable shuffleDrawable = getShuffleDrawable();
            if (shuffleDrawable != null && (iconButtonBuilder = getIconButtonBuilder()) != null && (styleBuilderWithIcon = iconButtonBuilder.withIcon(shuffleDrawable)) != null) {
                styleBuilderWithIcon.applyStyle(getShuffleIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            if (!getIsSonicRushEnabled()) {
                PageHeaderView.setMargins$default(this, getShuffleIcon(), 0, 0, Integer.valueOf(iIntValue), null, null, null, 118, null);
            }
            setEnability(getShuffleIcon(), isEnabled);
        }
        getShuffleIcon().setVisibility(show ? 0 : 8);
    }

    public final void showStationIcon(boolean show, boolean withLargeButton) {
        if (withLargeButton) {
            showLargeStationButton(show);
        } else {
            showStationIcon(show);
        }
    }

    private final void showStationIcon(boolean show) {
        BaseButton.StyleBuilder iconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (show) {
            Drawable stationDrawable = getStationDrawable();
            if (stationDrawable != null && (iconButtonBuilder = getIconButtonBuilder()) != null && (styleBuilderWithIcon = iconButtonBuilder.withIcon(stationDrawable)) != null) {
                styleBuilderWithIcon.applyStyle(getStationIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            PageHeaderView.setMargins$default(this, getStationIcon(), 0, 0, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, null, 118, null);
        }
        getStationIcon().setVisibility(show ? 0 : 8);
    }

    public final void showOverflowMenu(boolean show) {
        if (show) {
            BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
            if (iconButtonBuilder != null) {
                Drawable overflowDrawable = getOverflowDrawable();
                if (overflowDrawable != null) {
                    iconButtonBuilder.withIcon(overflowDrawable);
                }
                iconButtonBuilder.applyStyle(getOverflowIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            PageHeaderView.setMargins$default(this, getOverflowIcon(), 0, 0, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, null, 118, null);
        }
        getOverflowIcon().setVisibility(show ? 0 : 8);
    }

    private final void showLargeFollowButton(boolean show) {
        if (show) {
            BaseButton.StyleBuilder largeButtonBuilder = getLargeButtonBuilder();
            if (largeButtonBuilder != null) {
                Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favorite_svg);
                if (drawable != null) {
                    largeButtonBuilder.withIcon(drawable);
                }
                Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favoriteon_svg);
                if (drawable2 != null) {
                    largeButtonBuilder.withActiveIcon(drawable2);
                }
                String string = getResources().getString(R$string.dmusic_follow_text);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.dmusic_follow_text)");
                largeButtonBuilder.withText(string);
                String string2 = getResources().getString(R$string.dmusic_following_text);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…ng.dmusic_following_text)");
                largeButtonBuilder.withActiveText(string2);
                largeButtonBuilder.applyStyle(getLargeFollowButton());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.MEDIUM);
            PageHeaderView.setMargins$default(this, getLargeFollowButton(), 0, 0, null, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, 110, null);
        }
        getLargeFollowButton().setVisibility(show ? 0 : 8);
    }

    private final void showLargeStationButton(boolean show) {
        BaseButton.StyleBuilder largeButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (show) {
            Drawable stationDrawable = getStationDrawable();
            if (stationDrawable != null && (largeButtonBuilder = getLargeButtonBuilder()) != null && (styleBuilderWithIcon = largeButtonBuilder.withIcon(stationDrawable)) != null) {
                String string = getResources().getString(R$string.dmusic_detail_page_start_station);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…etail_page_start_station)");
                BaseButton.StyleBuilder styleBuilderWithText = styleBuilderWithIcon.withText(string);
                if (styleBuilderWithText != null) {
                    styleBuilderWithText.applyStyle(getLargeStationButton());
                }
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
            PageHeaderView.setMargins$default(this, getLargeStationButton(), 0, 0, null, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, 110, null);
        }
        getLargeStationButton().setVisibility(show ? 0 : 8);
    }

    public final void showFollowIcon(boolean show, boolean withLargeButton) {
        if (withLargeButton) {
            showLargeFollowButton(show);
        } else {
            showFollowIcon(show);
        }
    }

    private final void showFollowIcon(boolean show) {
        BaseButton.StyleBuilder iconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithActiveIcon;
        if (show) {
            Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favorite_svg);
            Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_favoriteon_svg);
            if (drawable != null && drawable2 != null && (iconButtonBuilder = getIconButtonBuilder()) != null && (styleBuilderWithIcon = iconButtonBuilder.withIcon(drawable)) != null && (styleBuilderWithActiveIcon = styleBuilderWithIcon.withActiveIcon(drawable2)) != null) {
                styleBuilderWithActiveIcon.applyStyle(getFollowIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.MEDIUM);
            PageHeaderView.setMargins$default(this, getFollowIcon(), 0, 0, null, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, 110, null);
        }
        getFollowIcon().setVisibility(show ? 0 : 8);
    }

    public final void showShareIcon(boolean show) {
        BaseButton.StyleBuilder iconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (show) {
            Drawable shareDrawable = getShareDrawable();
            if (shareDrawable != null && (iconButtonBuilder = getIconButtonBuilder()) != null && (styleBuilderWithIcon = iconButtonBuilder.withIcon(shareDrawable)) != null) {
                styleBuilderWithIcon.applyStyle(getShareIcon());
            }
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
            PageHeaderView.setMargins$default(this, getShareIcon(), 0, 0, Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()), null, null, null, 118, null);
        }
        BaseButton shareIcon = getShareIcon();
        if (shareIcon == null) {
            return;
        }
        shareIcon.setVisibility(show ? 0 : 8);
    }

    public final void setPlayIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getPlayIcon().setOnClickListener(onClickListener);
    }

    public final void setShuffleIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getShuffleIcon().setOnClickListener(onClickListener);
    }

    public final void setStationIconClickListener(View.OnClickListener onClickListener, boolean withLargeButton) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        if (withLargeButton) {
            getLargeStationButton().setOnClickListener(onClickListener);
        } else {
            getStationIcon().setOnClickListener(onClickListener);
        }
    }

    public final void setFollowIconClickListener(View.OnClickListener onClickListener, boolean withLargeButton) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        if (withLargeButton) {
            getLargeFollowButton().setOnClickListener(onClickListener);
        } else {
            getFollowIcon().setOnClickListener(onClickListener);
        }
    }

    public final void setShareIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getShareIcon().setOnClickListener(onClickListener);
    }

    public final void setOverflowOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getOverflowIcon().setOnClickListener(onClickListener);
    }

    public final void setIsFollowed(boolean isFollowed, boolean withLargeButton) {
        String string;
        if (isFollowed) {
            string = getResources().getString(R$string.dmusic_following_text);
        } else {
            string = getResources().getString(R$string.dmusic_follow_text);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (isFollowed) resource…tring.dmusic_follow_text)");
        if (withLargeButton) {
            getLargeFollowButton().setActive(isFollowed);
            getLargeFollowButton().setContentDescription(string);
        } else {
            getFollowIcon().setActive(isFollowed);
            getFollowIcon().setContentDescription(string);
        }
    }

    public final void setStationEligibility(boolean enableStations) {
        getStationIcon().setEnabled(enableStations);
    }

    private final void setEnability(BaseButton button, boolean isEnabled) {
        if (isEnabled) {
            return;
        }
        button.setLayerType(2, null);
        button.setAlpha(0.3f);
    }

    private final void setConstraints() {
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.artist_detail_header_layout);
        constraintSet.clone(constraintLayout);
        if (getIsSonicRushEnabled()) {
            int i = R$id.play_icon;
            constraintSet.connect(i, 7, 0, 7);
            constraintSet.connect(i, 3, R$id.follower_count_text, 4);
            int i2 = R$id.follow_icon;
            constraintSet.connect(i2, 6, 0, 6);
            constraintSet.connect(i2, 3, i, 3);
            int i3 = R$id.shuffle_icon;
            constraintSet.connect(i3, 6, i2, 7);
            constraintSet.connect(i3, 3, i2, 3);
        } else {
            int i4 = R$id.play_icon;
            constraintSet.connect(i4, 6, 0, 6);
            constraintSet.connect(i4, 3, R$id.follower_count_text, 4);
            int i5 = R$id.shuffle_icon;
            constraintSet.connect(i5, 6, i4, 7);
            constraintSet.connect(i5, 3, i4, 3);
            int i6 = R$id.follow_icon;
            constraintSet.connect(i6, 6, 0, 6);
            constraintSet.connect(i6, 3, R$id.large_station_button, 4);
        }
        constraintSet.applyTo(constraintLayout);
    }
}
