package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextToastView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0002R\u001b\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/views/library/views/TextToastView;", "Lcom/amazon/music/views/library/views/ToastView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "textView$delegate", "Lkotlin/Lazy;", "initPadding", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class TextToastView extends ToastView {

    /* JADX INFO: renamed from: textView$delegate, reason: from kotlin metadata */
    private final Lazy textView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextToastView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.textView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.TextToastView$textView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.text_view);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_text_toast_view_layout, this);
        initPadding();
        initTextStyle();
    }

    @Override // com.amazon.music.views.library.views.ToastView
    public TextView getTextView() {
        Object value = this.textView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textView>(...)");
        return (TextView) value;
    }

    private final void initPadding() {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        setPadding(iIntValue, getPaddingTop(), iIntValue, getPaddingBottom());
    }
}
