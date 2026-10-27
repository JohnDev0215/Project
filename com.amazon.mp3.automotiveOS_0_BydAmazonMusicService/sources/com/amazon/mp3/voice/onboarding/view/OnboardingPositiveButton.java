package com.amazon.mp3.voice.onboarding.view;

import android.content.Context;
import android.util.AttributeSet;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnboardingPositiveButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/view/OnboardingPositiveButton;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "text", "", "initButton", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class OnboardingPositiveButton extends BaseButton {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OnboardingPositiveButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    public final void initButton(StyleSheet styleSheet, String text) {
        BaseButton.StyleBuilder styleBuilderWithText;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(text, "text");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder == null || (styleBuilderWithText = buttonBuilder.withText(text)) == null) {
            return;
        }
        styleBuilderWithText.applyStyle(this);
    }
}
