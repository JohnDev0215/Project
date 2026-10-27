package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalIconLinkItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/VerticalIconLinkItemView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "icon$delegate", "Lkotlin/Lazy;", "link", "Landroid/widget/TextView;", "getLink", "()Landroid/widget/TextView;", "link$delegate", "init", "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalIconLinkItemView extends ConstraintLayout {

    /* JADX INFO: renamed from: icon$delegate, reason: from kotlin metadata */
    private final Lazy icon;

    /* JADX INFO: renamed from: link$delegate, reason: from kotlin metadata */
    private final Lazy link;

    public final ImageView getIcon() {
        Object value = this.icon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-icon>(...)");
        return (ImageView) value;
    }

    public final TextView getLink() {
        Object value = this.link.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-link>(...)");
        return (TextView) value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalIconLinkItemView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.icon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.alps.liveevent.view.VerticalIconLinkItemView$icon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.icon);
            }
        });
        this.link = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.VerticalIconLinkItemView$link$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.link);
            }
        });
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalIconLinkItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.icon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.alps.liveevent.view.VerticalIconLinkItemView$icon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.icon);
            }
        });
        this.link = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.VerticalIconLinkItemView$link$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.link);
            }
        });
        init(context);
    }

    private final void init(Context context) {
        View.inflate(context, R$layout.vertical_icon_link_view, this);
    }
}
