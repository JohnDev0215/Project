package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconState;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompositeOverflowButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006R\u001b\u0010\u000e\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0011\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\rR\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0015R\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0015¨\u0006!"}, d2 = {"Lcom/amazon/mp3/view/CompositeOverflowButton;", "Landroid/widget/FrameLayout;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initViewStyling", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconState;", "stageOverflowMenuSecondaryIconState", "updateSecondaryIconTo", "Lcom/amazon/ui/foundations/views/BaseButton;", "primaryButton$delegate", "Lkotlin/Lazy;", "getPrimaryButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "primaryButton", "secondaryButton$delegate", "getSecondaryButton", "secondaryButton", "Landroid/graphics/drawable/Drawable;", "overflowRepeatOneDrawable$delegate", "getOverflowRepeatOneDrawable", "()Landroid/graphics/drawable/Drawable;", "overflowRepeatOneDrawable", "overflowRepeatAllDrawable$delegate", "getOverflowRepeatAllDrawable", "overflowRepeatAllDrawable", "overflowShuffleDrawable$delegate", "getOverflowShuffleDrawable", "overflowShuffleDrawable", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CompositeOverflowButton extends FrameLayout {

    /* JADX INFO: renamed from: overflowRepeatAllDrawable$delegate, reason: from kotlin metadata */
    private final Lazy overflowRepeatAllDrawable;

    /* JADX INFO: renamed from: overflowRepeatOneDrawable$delegate, reason: from kotlin metadata */
    private final Lazy overflowRepeatOneDrawable;

    /* JADX INFO: renamed from: overflowShuffleDrawable$delegate, reason: from kotlin metadata */
    private final Lazy overflowShuffleDrawable;

    /* JADX INFO: renamed from: primaryButton$delegate, reason: from kotlin metadata */
    private final Lazy primaryButton;

    /* JADX INFO: renamed from: secondaryButton$delegate, reason: from kotlin metadata */
    private final Lazy secondaryButton;

    /* JADX INFO: compiled from: CompositeOverflowButton.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StageOverflowMenuSecondaryIconState.values().length];
            iArr[StageOverflowMenuSecondaryIconState.REPEAT_ONE.ordinal()] = 1;
            iArr[StageOverflowMenuSecondaryIconState.REPEAT_ALL.ordinal()] = 2;
            iArr[StageOverflowMenuSecondaryIconState.SHUFFLE.ordinal()] = 3;
            iArr[StageOverflowMenuSecondaryIconState.NONE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompositeOverflowButton(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(getContext(), R.layout.composite_overflow_button_view, this);
        this.primaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.CompositeOverflowButton$primaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.primary_button);
            }
        });
        this.secondaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.CompositeOverflowButton$secondaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.secondary_button);
            }
        });
        this.overflowRepeatOneDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.CompositeOverflowButton$overflowRepeatOneDrawable$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.ic_playback_repeat_one);
            }
        });
        this.overflowRepeatAllDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.CompositeOverflowButton$overflowRepeatAllDrawable$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.ic_playback_repeat_all);
            }
        });
        this.overflowShuffleDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.CompositeOverflowButton$overflowShuffleDrawable$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.ic_playback_shuffle);
            }
        });
    }

    public final BaseButton getPrimaryButton() {
        Object value = this.primaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryButton>(...)");
        return (BaseButton) value;
    }

    public final BaseButton getSecondaryButton() {
        Object value = this.secondaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryButton>(...)");
        return (BaseButton) value;
    }

    private final Drawable getOverflowRepeatOneDrawable() {
        return (Drawable) this.overflowRepeatOneDrawable.getValue();
    }

    private final Drawable getOverflowRepeatAllDrawable() {
        return (Drawable) this.overflowRepeatAllDrawable.getValue();
    }

    private final Drawable getOverflowShuffleDrawable() {
        return (Drawable) this.overflowShuffleDrawable.getValue();
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (NowPlayingUtil.isSmallScreen(getContext())) {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.GLASS);
        } else {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        }
        Context context = getContext();
        if (context != null && (drawable2 = context.getDrawable(R.drawable.ic_action_more)) != null) {
            BaseButton primaryButton = getPrimaryButton();
            if (iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable2)) != null) {
                styleBuilderWithIcon2.applyStyle(primaryButton);
            }
        }
        BaseButton.StyleBuilder iconBuilder2 = styleSheet.getIconBuilder(IconSizeKey.MICRO, IconStyleKey.SOLID);
        Context context2 = getContext();
        if (context2 == null || (drawable = context2.getDrawable(R.drawable.ic_playback_repeat)) == null) {
            return;
        }
        BaseButton secondaryButton = getSecondaryButton();
        if (iconBuilder2 == null || (styleBuilderWithIcon = iconBuilder2.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(secondaryButton);
    }

    public final void updateSecondaryIconTo(StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState) {
        Intrinsics.checkNotNullParameter(stageOverflowMenuSecondaryIconState, "stageOverflowMenuSecondaryIconState");
        int i = WhenMappings.$EnumSwitchMapping$0[stageOverflowMenuSecondaryIconState.ordinal()];
        if (i == 1) {
            getSecondaryButton().setVisibility(0);
            getSecondaryButton().setIcon(getOverflowRepeatOneDrawable());
            return;
        }
        if (i == 2) {
            getSecondaryButton().setVisibility(0);
            getSecondaryButton().setIcon(getOverflowRepeatAllDrawable());
        } else if (i == 3) {
            getSecondaryButton().setVisibility(0);
            getSecondaryButton().setIcon(getOverflowShuffleDrawable());
        } else {
            if (i != 4) {
                return;
            }
            getSecondaryButton().setVisibility(8);
        }
    }
}
