package com.amazon.mp3.casting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingBaseButton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012B\u0019\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0011\u0010\u0015J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/casting/CastingBaseButton;", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/mp3/casting/CastingButton;", "", "connected", "", "updateButton", "isShown", "show", "Landroid/view/View$OnClickListener;", "onClickListener", "setOnCastingButtonClickListener", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CastingBaseButton extends BaseButton implements CastingButton {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingBaseButton(Context context) {
        super(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingBaseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void updateButton(boolean connected) {
        setActive(connected);
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void show(boolean isShown) {
        setVisibility(isShown ? 0 : 8);
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void setOnCastingButtonClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }

    public final void initStyleSheet(StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.ic_casting_inactive);
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R.drawable.ic_casting_active);
        ButtonStyle iconStyle = AmazonApplication.getCapabilities().isYAImmerseEnabled() ? styleSheet.getIconStyle(IconStyleKey.ACCENT_NORMAL) : styleSheet.getIconStyle(IconStyleKey.ACCENT);
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled() && NowPlayingUtil.isSmallScreen(getContext())) {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.GLASS);
        } else {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        }
        if (drawable != null && iconBuilder != null) {
            iconBuilder.withIcon(drawable);
        }
        if (drawable2 != null && iconBuilder != null) {
            iconBuilder.withActiveIcon(drawable2);
        }
        if (iconStyle != null && iconBuilder != null) {
            iconBuilder.withActiveStyle(iconStyle);
        }
        if (iconBuilder == null) {
            return;
        }
        iconBuilder.applyStyle(this);
    }
}
