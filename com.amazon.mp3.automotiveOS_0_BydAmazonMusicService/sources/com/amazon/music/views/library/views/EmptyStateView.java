package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptyStateView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)J\u0010\u0010*\u001a\u00020\u00162\b\u0010+\u001a\u0004\u0018\u00010,J\u000e\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020&J\u000e\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u00020,J\u000e\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u000203R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\u0010¨\u00064"}, d2 = {"Lcom/amazon/music/views/library/views/EmptyStateView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "getButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "button$delegate", "Lkotlin/Lazy;", "messageTextView", "Landroid/widget/TextView;", "getMessageTextView", "()Landroid/widget/TextView;", "messageTextView$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "initMessageViewStyling", "", "initTopMargin", "view", "Landroid/view/View;", "topMargin", "", "setButton", "buttonType", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "setButtonStyle", "buttonSize", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonStyle", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "setButtonTopMargin", "spacerKey", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "setEmptyStateButtonClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setMessage", "message", "", "setPadding", "verticalSpacerKey", "setTitle", "title", "setTitleFont", "fontStyle", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmptyStateView extends FrameLayout {

    /* JADX INFO: renamed from: button$delegate, reason: from kotlin metadata */
    private final Lazy button;

    /* JADX INFO: renamed from: messageTextView$delegate, reason: from kotlin metadata */
    private final Lazy messageTextView;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmptyStateView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.EmptyStateView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.messageTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.EmptyStateView$messageTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.message);
            }
        });
        this.button = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.EmptyStateView$button$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.empty_view_button);
            }
        });
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_empty_state_view, this);
        initMessageViewStyling();
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

    private final BaseButton getButton() {
        Object value = this.button.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-button>(...)");
        return (BaseButton) value;
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
    }

    public final void setMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            getMessageTextView().setVisibility(8);
        } else {
            getMessageTextView().setVisibility(0);
            getMessageTextView().setText(message);
        }
    }

    public final void setButton(EmptyStateButtonType buttonType) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        getButton().setText(buttonType.getButtonString());
        getButton().setVisibility(0);
    }

    public final void setEmptyStateButtonClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getButton().setOnClickListener(onClickListener);
    }

    private final void initMessageViewStyling() {
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MINI);
        initTopMargin(getMessageTextView(), spacerInPixels == null ? 0 : spacerInPixels.intValue());
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getMessageTextView(), fontStyle);
    }

    private final void initTopMargin(View view, int topMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.topMargin = topMargin;
        view.requestLayout();
    }

    public final void setButtonTopMargin(SpacerKey spacerKey) {
        Intrinsics.checkNotNullParameter(spacerKey, "spacerKey");
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(spacerKey);
        initTopMargin(getButton(), spacerInPixels == null ? 0 : spacerInPixels.intValue());
    }

    public final void setButtonStyle(ButtonSizeKey buttonSize, ButtonStyleKey buttonStyle) {
        Intrinsics.checkNotNullParameter(buttonSize, "buttonSize");
        Intrinsics.checkNotNullParameter(buttonStyle, "buttonStyle");
        BaseButton.StyleBuilder buttonBuilder = this.styleSheet.getButtonBuilder(buttonSize, buttonStyle);
        if (buttonBuilder == null) {
            return;
        }
        buttonBuilder.applyStyle(getButton());
    }

    public final void setTitleFont(FontStyleKey fontStyle) {
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(fontStyle);
        if (fontStyle2 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
    }

    public final void setPadding(SpacerKey verticalSpacerKey) {
        Intrinsics.checkNotNullParameter(verticalSpacerKey, "verticalSpacerKey");
        GridSize gridSize = this.styleSheet.getGridSize();
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        GridSize gridSize2 = this.styleSheet.getGridSize();
        int columnSpace = marginSpace + (gridSize2 == null ? 0 : gridSize2.getColumnSpace());
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(verticalSpacerKey);
        int iIntValue = spacerInPixels != null ? spacerInPixels.intValue() : 0;
        setPadding(columnSpace, iIntValue, columnSpace, iIntValue);
    }
}
