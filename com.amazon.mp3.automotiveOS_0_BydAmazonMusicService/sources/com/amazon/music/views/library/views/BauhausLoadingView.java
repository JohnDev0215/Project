package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausLoadingView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausLoadingView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "loadingTextView", "Landroid/widget/TextView;", "getLoadingTextView", "()Landroid/widget/TextView;", "loadingTextView$delegate", "Lkotlin/Lazy;", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "progressBar$delegate", "initStyle", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "setText", "loadingTextId", "loadingText", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausLoadingView extends LinearLayout {

    /* JADX INFO: renamed from: loadingTextView$delegate, reason: from kotlin metadata */
    private final Lazy loadingTextView;

    /* JADX INFO: renamed from: progressBar$delegate, reason: from kotlin metadata */
    private final Lazy progressBar;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BauhausLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final TextView getLoadingTextView() {
        Object value = this.loadingTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-loadingTextView>(...)");
        return (TextView) value;
    }

    private final ProgressBar getProgressBar() {
        Object value = this.progressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-progressBar>(...)");
        return (ProgressBar) value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BauhausLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.loadingTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BauhausLoadingView$loadingTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.loading_text);
            }
        });
        this.progressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.music.views.library.views.BauhausLoadingView$progressBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R$id.loading_spinner);
            }
        });
        View.inflate(getContext(), R$layout.dmmviewlibrary_loading_view, this);
    }

    public /* synthetic */ BauhausLoadingView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void initStyle(StyleSheet styleSheet) {
        Integer width;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLoadingTextView(), fontStyle);
        }
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        TextView loadingTextView = getLoadingTextView();
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        layoutParamUtils.setLayoutMargins(loadingTextView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : Integer.valueOf(spacerInPixels == null ? 0 : spacerInPixels.intValue()));
        ButtonSize buttonSize = styleSheet.getButtonSize(ButtonSizeKey.LARGE);
        if (buttonSize == null || (width = buttonSize.getWidth()) == null) {
            return;
        }
        int iIntValue = width.intValue();
        getProgressBar().getLayoutParams().width = iIntValue;
        getProgressBar().getLayoutParams().height = iIntValue;
    }

    public final void setText(String loadingText) {
        Intrinsics.checkNotNullParameter(loadingText, "loadingText");
        getLoadingTextView().setText(loadingText);
        getLoadingTextView().setVisibility(TextUtils.isEmpty(loadingText) ? 8 : 0);
    }

    public final void setText(int loadingTextId) {
        String string = getResources().getString(loadingTextId);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(loadingTextId)");
        setText(string);
    }
}
