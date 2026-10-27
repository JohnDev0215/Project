package com.amazon.mp3.view.stage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StagePortraitPlaybackControlsView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\n2\u0006\u00101\u001a\u000202H\u0002J\u000e\u00103\u001a\u00020/2\u0006\u0010,\u001a\u00020-J\u0016\u00104\u001a\u00020/2\u0006\u00105\u001a\u0002022\u0006\u00106\u001a\u000202R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000f\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0015\u0010\u0012R\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u001a\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u000e\u001a\u0004\b\u001b\u0010\u0012R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000e\u001a\u0004\b \u0010\fR\u001d\u0010\"\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u000e\u001a\u0004\b#\u0010\fR\u001d\u0010%\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000e\u001a\u0004\b&\u0010\u0012R\u001d\u0010(\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u000e\u001a\u0004\b)\u0010\u0012R\u0010\u0010+\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/amazon/mp3/view/stage/StagePortraitPlaybackControlsView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "nextButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getNextButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "nextButton$delegate", "Lkotlin/Lazy;", "nextDisabledDrawable", "Landroid/graphics/drawable/Drawable;", "getNextDisabledDrawable", "()Landroid/graphics/drawable/Drawable;", "nextDisabledDrawable$delegate", "nextDrawable", "getNextDrawable", "nextDrawable$delegate", "pauseDrawable", "getPauseDrawable", "pauseDrawable$delegate", "playDrawable", "getPlayDrawable", "playDrawable$delegate", "playIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "playPauseButton", "getPlayPauseButton", "playPauseButton$delegate", "prevButton", "getPrevButton", "prevButton$delegate", "prevDisabledDrawable", "getPrevDisabledDrawable", "prevDisabledDrawable$delegate", "prevDrawable", "getPrevDrawable", "prevDrawable$delegate", "previousNextIconBuilder", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "applyPlayPauseButtonDrawable", "", "baseButton", "playStatus", "", "initViewStyling", "updateControls", "nextButtonEnabled", "prevButtonEnabled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StagePortraitPlaybackControlsView extends ConstraintLayout {

    /* JADX INFO: renamed from: nextButton$delegate, reason: from kotlin metadata */
    private final Lazy nextButton;

    /* JADX INFO: renamed from: nextDisabledDrawable$delegate, reason: from kotlin metadata */
    private final Lazy nextDisabledDrawable;

    /* JADX INFO: renamed from: nextDrawable$delegate, reason: from kotlin metadata */
    private final Lazy nextDrawable;

    /* JADX INFO: renamed from: pauseDrawable$delegate, reason: from kotlin metadata */
    private final Lazy pauseDrawable;

    /* JADX INFO: renamed from: playDrawable$delegate, reason: from kotlin metadata */
    private final Lazy playDrawable;
    private BaseButton.StyleBuilder playIconBuilder;

    /* JADX INFO: renamed from: playPauseButton$delegate, reason: from kotlin metadata */
    private final Lazy playPauseButton;

    /* JADX INFO: renamed from: prevButton$delegate, reason: from kotlin metadata */
    private final Lazy prevButton;

    /* JADX INFO: renamed from: prevDisabledDrawable$delegate, reason: from kotlin metadata */
    private final Lazy prevDisabledDrawable;

    /* JADX INFO: renamed from: prevDrawable$delegate, reason: from kotlin metadata */
    private final Lazy prevDrawable;
    private BaseButton.StyleBuilder previousNextIconBuilder;
    private StyleSheet styleSheet;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StagePortraitPlaybackControlsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ StagePortraitPlaybackControlsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StagePortraitPlaybackControlsView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.prevDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$prevDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_playback_previous);
            }
        });
        this.nextDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$nextDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_playback_next);
            }
        });
        this.nextDisabledDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$nextDisabledDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_playback_next_disabled);
            }
        });
        this.prevDisabledDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$prevDisabledDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_playback_previous_disabled);
            }
        });
        this.playDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$playDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_play);
            }
        });
        this.pauseDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$pauseDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_play);
            }
        });
        this.playPauseButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$playPauseButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerPlayButton);
            }
        });
        this.nextButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$nextButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerNextButton);
            }
        });
        this.prevButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StagePortraitPlaybackControlsView$prevButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerPrevButton);
            }
        });
        View.inflate(context, R.layout.stage_portrait_playback_controls_view, this);
    }

    private final Drawable getPrevDrawable() {
        return (Drawable) this.prevDrawable.getValue();
    }

    private final Drawable getNextDrawable() {
        return (Drawable) this.nextDrawable.getValue();
    }

    private final Drawable getNextDisabledDrawable() {
        return (Drawable) this.nextDisabledDrawable.getValue();
    }

    private final Drawable getPrevDisabledDrawable() {
        return (Drawable) this.prevDisabledDrawable.getValue();
    }

    private final Drawable getPlayDrawable() {
        return (Drawable) this.playDrawable.getValue();
    }

    private final Drawable getPauseDrawable() {
        return (Drawable) this.pauseDrawable.getValue();
    }

    private final BaseButton getPlayPauseButton() {
        return (BaseButton) this.playPauseButton.getValue();
    }

    private final BaseButton getNextButton() {
        return (BaseButton) this.nextButton.getValue();
    }

    private final BaseButton getPrevButton() {
        return (BaseButton) this.prevButton.getValue();
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder iconBuilder2;
        BaseButton nextButton;
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton prevButton;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        if (NowPlayingUtil.isSmallScreen(getContext())) {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
        } else {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        }
        this.playIconBuilder = iconBuilder;
        if (NowPlayingUtil.isSmallScreen(getContext())) {
            iconBuilder2 = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY);
        } else {
            iconBuilder2 = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
        }
        this.previousNextIconBuilder = iconBuilder2;
        Drawable prevDrawable = getPrevDrawable();
        if (prevDrawable != null && (prevButton = getPrevButton()) != null && (styleBuilder2 = this.previousNextIconBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(prevDrawable)) != null) {
            styleBuilderWithIcon2.applyStyle(prevButton);
        }
        Drawable nextDrawable = getNextDrawable();
        if (nextDrawable != null && (nextButton = getNextButton()) != null && (styleBuilder = this.previousNextIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(nextDrawable)) != null) {
            styleBuilderWithIcon.applyStyle(nextButton);
        }
        BaseButton playPauseButton = getPlayPauseButton();
        if (playPauseButton == null) {
            return;
        }
        applyPlayPauseButtonDrawable(playPauseButton, true);
    }

    private final void applyPlayPauseButtonDrawable(BaseButton baseButton, boolean playStatus) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (playStatus) {
            Drawable pauseDrawable = getPauseDrawable();
            if (pauseDrawable == null || (styleBuilder2 = this.playIconBuilder) == null || (styleBuilderWithIcon2 = styleBuilder2.withIcon(pauseDrawable)) == null) {
                return;
            }
            styleBuilderWithIcon2.applyStyle(baseButton);
            return;
        }
        Drawable playDrawable = getPlayDrawable();
        if (playDrawable == null || (styleBuilder = this.playIconBuilder) == null || (styleBuilderWithIcon = styleBuilder.withIcon(playDrawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(baseButton);
    }

    public final void updateControls(boolean nextButtonEnabled, boolean prevButtonEnabled) {
        BaseButton prevButton;
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton nextButton;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Drawable nextDrawable = nextButtonEnabled ? getNextDrawable() : getNextDisabledDrawable();
        if (nextDrawable != null && (nextButton = getNextButton()) != null && (styleBuilder2 = this.previousNextIconBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(nextDrawable)) != null) {
            styleBuilderWithIcon2.applyStyle(nextButton);
        }
        Drawable prevDrawable = prevButtonEnabled ? getPrevDrawable() : getPrevDisabledDrawable();
        if (prevDrawable == null || (prevButton = getPrevButton()) == null || (styleBuilder = this.previousNextIconBuilder) == null || (styleBuilderWithIcon = styleBuilder.withIcon(prevDrawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(prevButton);
    }
}
