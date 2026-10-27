package com.amazon.mp3.view.stage;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageOverlayButtonsLayout.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\b\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006R\u001b\u0010\r\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u0012\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/view/stage/StageOverlayButtonsLayout;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "initViewStyling", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "initViewStylingForButton", "lyricsOverlayButton$delegate", "Lkotlin/Lazy;", "getLyricsOverlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "lyricsOverlayButton", "Landroid/widget/LinearLayout;", "overlayButtonsLinearLayout$delegate", "getOverlayButtonsLinearLayout", "()Landroid/widget/LinearLayout;", "overlayButtonsLinearLayout", "Lcom/amazon/music/views/library/styles/StyleSheet;", "", "SMALL_SCREEN_THRESHOLD_HEIGHT_PIXELS", "F", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageOverlayButtonsLayout extends ConstraintLayout {
    private final float SMALL_SCREEN_THRESHOLD_HEIGHT_PIXELS;

    /* JADX INFO: renamed from: lyricsOverlayButton$delegate, reason: from kotlin metadata */
    private final Lazy lyricsOverlayButton;

    /* JADX INFO: renamed from: overlayButtonsLinearLayout$delegate, reason: from kotlin metadata */
    private final Lazy overlayButtonsLinearLayout;
    private StyleSheet styleSheet;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageOverlayButtonsLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageOverlayButtonsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.lyricsOverlayButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageOverlayButtonsLayout$lyricsOverlayButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.lyricsOverlayButton);
            }
        });
        this.overlayButtonsLinearLayout = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.view.stage.StageOverlayButtonsLayout$overlayButtonsLinearLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.overlay_button_layout);
            }
        });
        this.SMALL_SCREEN_THRESHOLD_HEIGHT_PIXELS = 1280.0f;
        View.inflate(context, R.layout.stage_overlay_buttons_layout, this);
    }

    public /* synthetic */ StageOverlayButtonsLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final BaseButton getLyricsOverlayButton() {
        Object value = this.lyricsOverlayButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-lyricsOverlayButton>(...)");
        return (BaseButton) value;
    }

    public final LinearLayout getOverlayButtonsLinearLayout() {
        Object value = this.overlayButtonsLinearLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overlayButtonsLinearLayout>(...)");
        return (LinearLayout) value;
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Resources resources;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        if (!ScreenUtil.isLandscape() || getContext().getResources().getDisplayMetrics().widthPixels >= this.SMALL_SCREEN_THRESHOLD_HEIGHT_PIXELS) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.overlay_button_layout);
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.width = (int) getContext().getResources().getDimension(R.dimen.stage_overlay_button_layout_width);
            linearLayout.setLayoutParams(layoutParams);
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.SMALL_NOT_ALL_CAPS, ButtonStyleKey.GLASS);
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.SECONDARY_GLASS);
        BaseButton lyricsOverlayButton = getLyricsOverlayButton();
        Context context = getContext();
        String string = null;
        if (context != null && (resources = context.getResources()) != null) {
            string = resources.getString(R.string.non_active_lyrics_pill_description);
        }
        lyricsOverlayButton.setContentDescription(string);
        if (buttonBuilder == null) {
            return;
        }
        String string2 = getResources().getString(R.string.dmusic_stage_lyrics_overlay);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…sic_stage_lyrics_overlay)");
        buttonBuilder.withText(string2);
        if (iconStyle != null) {
            buttonBuilder.withActiveStyle(iconStyle);
        }
        buttonBuilder.applyStyle(getLyricsOverlayButton());
    }

    public final void initViewStylingForButton(BaseButton button) {
        Intrinsics.checkNotNullParameter(button, "button");
        StyleSheet styleSheet = this.styleSheet;
        StyleSheet styleSheet2 = null;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.SMALL_NOT_ALL_CAPS, ButtonStyleKey.GLASS);
        StyleSheet styleSheet3 = this.styleSheet;
        if (styleSheet3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
        } else {
            styleSheet2 = styleSheet3;
        }
        ButtonStyle iconStyle = styleSheet2.getIconStyle(IconStyleKey.SECONDARY_GLASS);
        if (buttonBuilder == null) {
            return;
        }
        if (iconStyle != null) {
            buttonBuilder.withActiveStyle(iconStyle);
        }
        buttonBuilder.applyStyle(button);
    }
}
