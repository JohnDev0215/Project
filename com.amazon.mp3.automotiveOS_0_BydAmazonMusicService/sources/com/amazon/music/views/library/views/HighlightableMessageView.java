package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.HighlightableMessageModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.StringUIUtils;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighlightableMessageView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u000eJ\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$J'\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00192\b\u0010'\u001a\u0004\u0018\u00010\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u0010)R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/music/views/library/views/HighlightableMessageView;", "Lcom/amazon/music/views/library/views/MessageView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "getButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "button$delegate", "Lkotlin/Lazy;", "initMargin", "", "view", "Landroid/view/View;", "model", "Lcom/amazon/music/views/library/models/HighlightableMessageModel;", "initStyling", "initTopMargin", "topMargin", "", "setButton", "buttonString", "", "setButtonClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setButtonStyle", "buttonSize", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonStyle", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "setButtonTopMargin", "spacerKey", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "setMessage", "text", "stringToHighlight", "colorKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HighlightableMessageView extends MessageView {

    /* JADX INFO: renamed from: button$delegate, reason: from kotlin metadata */
    private final Lazy button;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HighlightableMessageView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.button = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HighlightableMessageView$button$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.message_view_button);
            }
        });
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_empty_state_view, this);
    }

    private final BaseButton getButton() {
        Object value = this.button.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-button>(...)");
        return (BaseButton) value;
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

    public final void initMargin(View view, HighlightableMessageModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams == null) {
            return;
        }
        marginLayoutParams.bottomMargin = iIntValue2;
        if (!model.getIsEmpty()) {
            iIntValue = 0;
        }
        marginLayoutParams.topMargin = iIntValue;
        marginLayoutParams.leftMargin = 0;
        marginLayoutParams.rightMargin = 0;
        view.requestLayout();
    }

    public final void initStyling() {
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        if (color != null) {
            getMMessageText().setTextColor(color.intValue());
        }
        getMMessageText().setMaxWidth(Integer.MAX_VALUE);
        getMMessageText().setGravity(17);
        getMMessageText().setTextAlignment(4);
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getMMessageText(), fontStyle);
        }
        getMMessageText().setPadding(0, 0, 0, 0);
        GridSize gridSize = this.styleSheet.getGridSize();
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        GridSize gridSize2 = this.styleSheet.getGridSize();
        int columnSpace = marginSpace + ((gridSize2 == null ? 0 : gridSize2.getColumnSpace()) * 3);
        setPadding(columnSpace, 0, columnSpace, 0);
    }

    public final void setButtonStyle(ButtonSizeKey buttonSize, ButtonStyleKey buttonStyle) {
        BaseButton.StyleBuilder buttonBuilder;
        Intrinsics.checkNotNullParameter(buttonSize, "buttonSize");
        Intrinsics.checkNotNullParameter(buttonStyle, "buttonStyle");
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (buttonBuilder = styleSheet.getButtonBuilder(buttonSize, buttonStyle)) == null) {
            return;
        }
        buttonBuilder.applyStyle(getButton());
    }

    public final void setMessage(String text, String stringToHighlight, Integer colorKey) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (stringToHighlight == null || colorKey == null) {
            getMMessageText().setText(text);
        }
        getMMessageText().setText(StringUIUtils.INSTANCE.highlightedMessage(text, stringToHighlight, colorKey));
    }

    public final void setButtonTopMargin(SpacerKey spacerKey) {
        Intrinsics.checkNotNullParameter(spacerKey, "spacerKey");
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(spacerKey);
        initTopMargin(getButton(), spacerInPixels == null ? 0 : spacerInPixels.intValue());
    }

    public final void setButton(String buttonString) {
        Intrinsics.checkNotNullParameter(buttonString, "buttonString");
        getButton().setText(buttonString);
        getButton().setVisibility(0);
    }

    public final void setButtonClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getButton().setOnClickListener(onClickListener);
    }
}
