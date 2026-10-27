package com.amazon.mp3.view.stage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageLandscapeControlsView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\nH\u0002J\u000e\u00104\u001a\u0002012\u0006\u0010.\u001a\u00020/J\u0016\u00105\u001a\u0002012\u0006\u00106\u001a\u00020\n2\u0006\u00107\u001a\u00020\nR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u0004\u0018\u00010\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0017\u0010\u0014R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001a\u0010\u0014R\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001d\u0010\u0014R\u001d\u0010\u001f\u001a\u0004\u0018\u00010\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0010\u001a\u0004\b \u0010\u000eR\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u0004\u0018\u00010\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0010\u001a\u0004\b%\u0010\u000eR\u001d\u0010'\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u0010\u001a\u0004\b(\u0010\u0014R\u001d\u0010*\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0010\u001a\u0004\b+\u0010\u0014R\u0010\u0010-\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/amazon/mp3/view/stage/StageLandscapeControlsView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "isTabletLandscape", "", "nextButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getNextButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "nextButton$delegate", "Lkotlin/Lazy;", "nextDisabledDrawable", "Landroid/graphics/drawable/Drawable;", "getNextDisabledDrawable", "()Landroid/graphics/drawable/Drawable;", "nextDisabledDrawable$delegate", "nextDrawable", "getNextDrawable", "nextDrawable$delegate", "pauseDrawable", "getPauseDrawable", "pauseDrawable$delegate", "playDrawable", "getPlayDrawable", "playDrawable$delegate", "playPauseButton", "getPlayPauseButton", "playPauseButton$delegate", "playPauseButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "prevButton", "getPrevButton", "prevButton$delegate", "prevDisabledDrawable", "getPrevDisabledDrawable", "prevDisabledDrawable$delegate", "prevDrawable", "getPrevDrawable", "prevDrawable$delegate", "prevNextButtonBuilder", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "applyPlayPauseButtonDrawable", "", "baseButton", "playStatus", "initViewStyling", "updateControls", "nextButtonEnabled", "prevButtonEnabled", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StageLandscapeControlsView extends ConstraintLayout {
    private boolean isTabletLandscape;

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

    /* JADX INFO: renamed from: playPauseButton$delegate, reason: from kotlin metadata */
    private final Lazy playPauseButton;
    private BaseButton.StyleBuilder playPauseButtonBuilder;

    /* JADX INFO: renamed from: prevButton$delegate, reason: from kotlin metadata */
    private final Lazy prevButton;

    /* JADX INFO: renamed from: prevDisabledDrawable$delegate, reason: from kotlin metadata */
    private final Lazy prevDisabledDrawable;

    /* JADX INFO: renamed from: prevDrawable$delegate, reason: from kotlin metadata */
    private final Lazy prevDrawable;
    private BaseButton.StyleBuilder prevNextButtonBuilder;
    private StyleSheet styleSheet;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageLandscapeControlsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ StageLandscapeControlsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageLandscapeControlsView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.prevDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$prevDrawable$2
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
        this.nextDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$nextDrawable$2
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
        this.playDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$playDrawable$2
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
        this.pauseDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$pauseDrawable$2
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
        this.nextDisabledDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$nextDisabledDrawable$2
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
        this.prevDisabledDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$prevDisabledDrawable$2
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
        this.playPauseButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$playPauseButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerPlayButton);
            }
        });
        this.nextButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$nextButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerNextButton);
            }
        });
        this.prevButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageLandscapeControlsView$prevButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.PersistentPlayerPrevButton);
            }
        });
        View.inflate(context, R.layout.stage_landscape_controls_view, this);
    }

    private final Drawable getPrevDrawable() {
        return (Drawable) this.prevDrawable.getValue();
    }

    private final Drawable getNextDrawable() {
        return (Drawable) this.nextDrawable.getValue();
    }

    private final Drawable getPlayDrawable() {
        return (Drawable) this.playDrawable.getValue();
    }

    private final Drawable getPauseDrawable() {
        return (Drawable) this.pauseDrawable.getValue();
    }

    private final Drawable getNextDisabledDrawable() {
        return (Drawable) this.nextDisabledDrawable.getValue();
    }

    private final Drawable getPrevDisabledDrawable() {
        return (Drawable) this.prevDisabledDrawable.getValue();
    }

    public final BaseButton getPlayPauseButton() {
        return (BaseButton) this.playPauseButton.getValue();
    }

    public final BaseButton getNextButton() {
        return (BaseButton) this.nextButton.getValue();
    }

    public final BaseButton getPrevButton() {
        return (BaseButton) this.prevButton.getValue();
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        BaseButton nextButton;
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton prevButton;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        int iIntValue = 0;
        boolean z = styleSheet.getLandscapeType(context) == LandscapeType.TABLET_LANDSCAPE;
        this.isTabletLandscape = z;
        if (z) {
            this.prevNextButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY);
            this.playPauseButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
        } else {
            this.prevNextButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.PRIMARY);
            this.playPauseButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        }
        if (this.isTabletLandscape) {
            Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.EPIC);
            if (spacerInPixels != null) {
                iIntValue = spacerInPixels.intValue();
            }
        } else {
            Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
            if (spacerInPixels2 != null) {
                iIntValue = spacerInPixels2.intValue();
            }
        }
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        BaseButton prevButton2 = getPrevButton();
        if (prevButton2 != null) {
            layoutParamUtils.setLayoutMargins(prevButton2, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(iIntValue), (240 & 128) != 0 ? null : null);
            BaseButton nextButton2 = getNextButton();
            if (nextButton2 != null) {
                layoutParamUtils.setLayoutMargins(nextButton2, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : Integer.valueOf(iIntValue), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
                Drawable prevDrawable = getPrevDrawable();
                if (prevDrawable != null && (prevButton = getPrevButton()) != null && (styleBuilder2 = this.prevNextButtonBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(prevDrawable)) != null) {
                    styleBuilderWithIcon2.applyStyle(prevButton);
                }
                Drawable nextDrawable = getNextDrawable();
                if (nextDrawable != null && (nextButton = getNextButton()) != null && (styleBuilder = this.prevNextButtonBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(nextDrawable)) != null) {
                    styleBuilderWithIcon.applyStyle(nextButton);
                }
                BaseButton playPauseButton = getPlayPauseButton();
                if (playPauseButton == null) {
                    return;
                }
                applyPlayPauseButtonDrawable(playPauseButton, true);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.View");
    }

    private final void applyPlayPauseButtonDrawable(BaseButton baseButton, boolean playStatus) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (playStatus) {
            Drawable pauseDrawable = getPauseDrawable();
            if (pauseDrawable == null || (styleBuilder2 = this.playPauseButtonBuilder) == null || (styleBuilderWithIcon2 = styleBuilder2.withIcon(pauseDrawable)) == null) {
                return;
            }
            styleBuilderWithIcon2.applyStyle(baseButton);
            return;
        }
        Drawable playDrawable = getPlayDrawable();
        if (playDrawable == null || (styleBuilder = this.playPauseButtonBuilder) == null || (styleBuilderWithIcon = styleBuilder.withIcon(playDrawable)) == null) {
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
        if (nextDrawable != null && (nextButton = getNextButton()) != null && (styleBuilder2 = this.prevNextButtonBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(nextDrawable)) != null) {
            styleBuilderWithIcon2.applyStyle(nextButton);
        }
        Drawable prevDrawable = prevButtonEnabled ? getPrevDrawable() : getPrevDisabledDrawable();
        if (prevDrawable == null || (prevButton = getPrevButton()) == null || (styleBuilder = this.prevNextButtonBuilder) == null || (styleBuilderWithIcon = styleBuilder.withIcon(prevDrawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(prevButton);
    }
}
