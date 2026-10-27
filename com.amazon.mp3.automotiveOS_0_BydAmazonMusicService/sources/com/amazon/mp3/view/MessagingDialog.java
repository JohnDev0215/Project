package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.TextViewCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessagingDialog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\"J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020%J&\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007J\u0010\u0010,\u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\"J&\u0010-\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0007J\u0010\u00100\u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\"R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0010\u0010\fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000e\u001a\u0004\b\u0018\u0010\u0015¨\u00061"}, d2 = {"Lcom/amazon/mp3/view/MessagingDialog;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "closeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCloseButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "closeButton$delegate", "Lkotlin/Lazy;", "mainButton", "getMainButton", "mainButton$delegate", "messageTextView", "Landroid/widget/TextView;", "getMessageTextView", "()Landroid/widget/TextView;", "messageTextView$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "initViewStyling", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isStage", "", "setButton", "text", "", "setButtonOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setCloseButtonOnClickListener", "setHeaderMaxLines", "numberOfLines", "minHeaderTextSize", "maxHeaderTextSize", "autoSizeStepGranularity", "setMessage", "setMessageMaxLines", "minMessageTextSize", "maxMessageTextSize", "setTitle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MessagingDialog extends ConstraintLayout {

    /* JADX INFO: renamed from: closeButton$delegate, reason: from kotlin metadata */
    private final Lazy closeButton;

    /* JADX INFO: renamed from: mainButton$delegate, reason: from kotlin metadata */
    private final Lazy mainButton;

    /* JADX INFO: renamed from: messageTextView$delegate, reason: from kotlin metadata */
    private final Lazy messageTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public MessagingDialog(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public MessagingDialog(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.MessagingDialog$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.bauhausNowPlayingNonBlockingHeader);
            }
        });
        this.messageTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.MessagingDialog$messageTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.bauhausNowPlayingNonBlockingTitle);
            }
        });
        this.mainButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.MessagingDialog$mainButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.bauhausNowPlayingNonBlockingButton);
            }
        });
        this.closeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.MessagingDialog$closeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.closeButton);
            }
        });
        View.inflate(context, (isStage() && ScreenUtil.isLandscape()) ? R.layout.messaging_dialog_land : R.layout.messaging_dialog, this);
    }

    public /* synthetic */ MessagingDialog(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getMessageTextView() {
        Object value = this.messageTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-messageTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getMainButton() {
        Object value = this.mainButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mainButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getCloseButton() {
        Object value = this.closeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-closeButton>(...)");
        return (BaseButton) value;
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        FontStyle fontStyle;
        FontStyle fontStyleCopy$default;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (isStage()) {
            ViewGroup.LayoutParams layoutParams = getMainButton().getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = -2;
            getMainButton().setLayoutParams(layoutParams2);
        } else {
            BaseButton mainButton = getMainButton();
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(mainButton);
            constraintSet.connect(R.id.bauhausNowPlayingNonBlockingButton, 6, 0, 6);
            constraintSet.applyTo(mainButton);
        }
        float dimension = getContext().getResources().getDimension(R.dimen.spacer_20);
        FontSizeKey fontSizeKey = isStage() ? FontSizeKey.BODY_1 : FontSizeKey.HEADLINE_4;
        FontStyleKey fontStyleKey = isStage() ? FontStyleKey.PRIMARY_BOLD : FontStyleKey.HEADLINE_4;
        FontSize fontSize = styleSheet.getFontSize(fontSizeKey);
        FontSize fontSizeCopy$default = fontSize != null ? FontSize.copy$default(fontSize, 0, Integer.valueOf((int) dimension), 1, null) : null;
        if (fontSizeCopy$default != null && (fontStyle = styleSheet.getFontStyle(fontStyleKey)) != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, fontSizeCopy$default, null, 0, null, 14, null)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyleCopy$default);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(isStage() ? FontStyleKey.TERTIARY : FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            if (isStage()) {
                Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
                if (color != null) {
                    FontUtil.INSTANCE.applyFontStyle(getMessageTextView(), FontStyle.copy$default(fontStyle2, null, null, color.intValue(), null, 11, null));
                }
            } else {
                FontUtil.INSTANCE.applyFontStyle(getMessageTextView(), fontStyle2);
            }
        }
        ButtonSize iconSize = styleSheet.getIconSize(isStage() ? IconSizeKey.TINY : IconSizeKey.SMALL);
        if (iconSize != null) {
            BaseButton.StyleBuilder styleBuilderWithSize = new BaseButton.StyleBuilder().withSize(iconSize);
            Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_action_cancel_inline);
            Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra….ic_action_cancel_inline)");
            styleBuilderWithSize.withIcon(drawable).applyStyle(getCloseButton());
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder == null) {
            return;
        }
        buttonBuilder.applyStyle(getMainButton());
    }

    public final void setTitle(String text) {
        getTitleTextView().setText(text);
        getTitleTextView().setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public final void setMessage(String text) {
        getMessageTextView().setText(text);
        getMessageTextView().setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public final void setHeaderMaxLines(int numberOfLines, int minHeaderTextSize, int maxHeaderTextSize, int autoSizeStepGranularity) {
        getTitleTextView().setMaxLines(numberOfLines);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(getTitleTextView(), minHeaderTextSize, maxHeaderTextSize, autoSizeStepGranularity, 2);
    }

    public final void setMessageMaxLines(int numberOfLines, int minMessageTextSize, int maxMessageTextSize, int autoSizeStepGranularity) {
        getMessageTextView().setMaxLines(numberOfLines);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(getMessageTextView(), minMessageTextSize, maxMessageTextSize, autoSizeStepGranularity, 2);
    }

    public final void setButton(String text) {
        if (text != null) {
            getMainButton().setText(text);
        }
        getMainButton().setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public final void setButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getMainButton().setOnClickListener(onClickListener);
    }

    public final void setCloseButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getCloseButton().setOnClickListener(onClickListener);
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
