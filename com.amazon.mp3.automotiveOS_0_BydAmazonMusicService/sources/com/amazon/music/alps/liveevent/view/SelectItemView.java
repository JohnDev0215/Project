package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/SelectItemView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "titleTextView", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView$delegate", "Lkotlin/Lazy;", "underlineView", "Landroid/view/View;", "getUnderlineView", "()Landroid/view/View;", "underlineView$delegate", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectItemView extends LinearLayout {
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: renamed from: underlineView$delegate, reason: from kotlin metadata */
    private final Lazy underlineView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectItemView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.SelectItemView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.select_item_title);
            }
        });
        this.underlineView = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.alps.liveevent.view.SelectItemView$underlineView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R$id.select_item_underline);
            }
        });
        View.inflate(context, R$layout.select_item_view_layout, this);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    public final View getUnderlineView() {
        Object value = this.underlineView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-underlineView>(...)");
        return (View) value;
    }
}
