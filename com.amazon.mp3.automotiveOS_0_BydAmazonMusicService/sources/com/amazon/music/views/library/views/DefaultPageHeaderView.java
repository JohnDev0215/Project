package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultPageHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0010\u001a\u0004\b \u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\u0010\u001a\u0004\b(\u0010!R\u001b\u0010*\u001a\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0010\u001a\u0004\b+\u0010\u000eR\u001b\u0010-\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u0010\u001a\u0004\b.\u0010!R\u001b\u00100\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\u0010\u001a\u0004\b1\u0010!R\u001b\u00103\u001a\u00020\u001f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\u0010\u001a\u0004\b4\u0010!¨\u00066"}, d2 = {"Lcom/amazon/music/views/library/views/DefaultPageHeaderView;", "Lcom/amazon/music/views/library/views/PageHeaderView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isSonicRushEnabled", "", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;ZLcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "actionIconContainer", "Landroid/widget/LinearLayout;", "getActionIconContainer", "()Landroid/widget/LinearLayout;", "actionIconContainer$delegate", "Lkotlin/Lazy;", "actionIcons", "Landroid/widget/RelativeLayout;", "getActionIcons", "()Landroid/widget/RelativeLayout;", "actionIcons$delegate", "headlineTextView", "Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "getHeadlineTextView", "()Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "headlineTextView$delegate", "()Z", "setSonicRushEnabled", "(Z)V", "labelTextView", "Landroid/widget/TextView;", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView$delegate", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setPageType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "primaryTextView", "getPrimaryTextView", "primaryTextView$delegate", "rightActionIconContainer", "getRightActionIconContainer", "rightActionIconContainer$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "tertiaryTextView", "getTertiaryTextView", "tertiaryTextView$delegate", "textView4", "getTextView4", "textView4$delegate", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultPageHeaderView extends PageHeaderView {

    /* JADX INFO: renamed from: actionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy actionIconContainer;

    /* JADX INFO: renamed from: actionIcons$delegate, reason: from kotlin metadata */
    private final Lazy actionIcons;

    /* JADX INFO: renamed from: headlineTextView$delegate, reason: from kotlin metadata */
    private final Lazy headlineTextView;
    private boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private PageType pageType;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: rightActionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy rightActionIconContainer;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX INFO: renamed from: textView4$delegate, reason: from kotlin metadata */
    private final Lazy textView4;

    @Override // com.amazon.music.views.library.views.PageHeaderView
    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public PageType getPageType() {
        return this.pageType;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultPageHeaderView(Context context, StyleSheet styleSheet, boolean z, PageType pageType) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.isSonicRushEnabled = z;
        this.pageType = pageType;
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.headlineTextView = LazyKt__LazyJVMKt.lazy(new Function0<PageHeaderHeadlineTextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$headlineTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageHeaderHeadlineTextView invoke() {
                return (PageHeaderHeadlineTextView) this.this$0.findViewById(R$id.Title);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.Subtitle);
            }
        });
        this.textView4 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$textView4$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.text4);
            }
        });
        this.rightActionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$rightActionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.right_action_icon);
            }
        });
        this.actionIcons = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$actionIcons$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RelativeLayout invoke() {
                return (RelativeLayout) this.this$0.findViewById(R$id.action_icons);
            }
        });
        this.actionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DefaultPageHeaderView$actionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.action_icon_container);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_default_page_header_layout, this);
        super.setSonicRushEnabled(getIsSonicRushEnabled());
        super.setPageType(getPageType());
        init();
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public PageHeaderHeadlineTextView getHeadlineTextView() {
        Object value = this.headlineTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headlineTextView>(...)");
        return (PageHeaderHeadlineTextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getTertiaryTextView() {
        Object value = this.tertiaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tertiaryTextView>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public TextView getTextView4() {
        Object value = this.textView4.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textView4>(...)");
        return (TextView) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public LinearLayout getRightActionIconContainer() {
        Object value = this.rightActionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rightActionIconContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public RelativeLayout getActionIcons() {
        Object value = this.actionIcons.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIcons>(...)");
        return (RelativeLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    public LinearLayout getActionIconContainer() {
        Object value = this.actionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIconContainer>(...)");
        return (LinearLayout) value;
    }
}
