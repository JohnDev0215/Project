package com.amazon.mp3.auto.carmode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeEmbeddedDialog.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u001cJ\u0006\u0010#\u001a\u00020\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\n¨\u0006$"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "messageTextView", "Landroid/widget/TextView;", "getMessageTextView", "()Landroid/widget/TextView;", "messageTextView$delegate", "Lkotlin/Lazy;", "positiveButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getPositiveButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "positiveButton$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "hide", "", "initStyling", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "setMessage", "message", "", "setPositiveButton", "text", "clickListener", "Landroid/view/View$OnClickListener;", "setTitle", "title", "show", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeEmbeddedDialog extends LinearLayout {

    /* JADX INFO: renamed from: messageTextView$delegate, reason: from kotlin metadata */
    private final Lazy messageTextView;

    /* JADX INFO: renamed from: positiveButton$delegate, reason: from kotlin metadata */
    private final Lazy positiveButton;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeEmbeddedDialog(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_embedded_dialog_title);
            }
        });
        this.messageTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog$messageTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_embedded_dialog_message);
            }
        });
        this.positiveButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog$positiveButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_embedded_dialog_positive_button);
            }
        });
        View.inflate(context, R.layout.car_mode_embedded_dialog, this);
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

    private final BaseButton getPositiveButton() {
        Object value = this.positiveButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-positiveButton>(...)");
        return (BaseButton) value;
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
    }

    public final void setMessage(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        getMessageTextView().setText(message);
    }

    public final void setPositiveButton(String text, View.OnClickListener clickListener) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        getPositiveButton().setText(text);
        getPositiveButton().setOnClickListener(clickListener);
    }

    public final void initStyling(StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        FontStyle fontStyle;
        FontStyle fontStyle2;
        if (styleSheet != null && (fontStyle2 = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
        }
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getMessageTextView(), fontStyle);
        }
        if (styleSheet == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID)) == null) {
            return;
        }
        buttonBuilder.applyStyle(getPositiveButton());
    }

    public final void hide() {
        setVisibility(8);
    }

    public final void show() {
        setVisibility(0);
    }
}
