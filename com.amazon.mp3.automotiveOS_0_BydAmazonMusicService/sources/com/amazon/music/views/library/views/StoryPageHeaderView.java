package com.amazon.music.views.library.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StoryPageHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010)\u001a\u00020*H\u0014J\u000e\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020*2\u0006\u0010/\u001a\u000200H\u0007J\u001f\u00101\u001a\u00020*2\b\u00102\u001a\u0004\u0018\u00010-2\b\u00103\u001a\u0004\u0018\u00010-¢\u0006\u0002\u00104R\u001b\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0018\u0010\nR\u001b\u0010\u001a\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001b\u0010\u0015R\u001b\u0010\u001d\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001e\u0010\u0015R\u001b\u0010 \u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\f\u001a\u0004\b!\u0010\u0015R\u001b\u0010#\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b$\u0010\nR\u001b\u0010&\u001a\u00020\u00138VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\f\u001a\u0004\b'\u0010\u0015¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/views/StoryPageHeaderView;", "Lcom/amazon/music/views/library/views/PageHeaderView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "actionIconContainer", "Landroid/widget/LinearLayout;", "getActionIconContainer", "()Landroid/widget/LinearLayout;", "actionIconContainer$delegate", "Lkotlin/Lazy;", "headlineTextView", "Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "getHeadlineTextView", "()Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "headlineTextView$delegate", "labelTextView", "Landroid/widget/TextView;", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView$delegate", "pageIndicator", "getPageIndicator", "pageIndicator$delegate", "primaryTextView", "getPrimaryTextView", "primaryTextView$delegate", "secondaryTextView", "getSecondaryTextView", "secondaryTextView$delegate", "tertiaryTextView", "getTertiaryTextView", "tertiaryTextView$delegate", "textContainer", "getTextContainer", "textContainer$delegate", "textView4", "getTextView4", "textView4$delegate", "initMargins", "", "setPagination", "count", "", "setTextContainerOnTouchListener", "textContainerOnTouchListener", "Landroid/view/View$OnTouchListener;", "updatePagination", "prev", "cur", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StoryPageHeaderView extends PageHeaderView {

    /* JADX INFO: renamed from: actionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy actionIconContainer;

    /* JADX INFO: renamed from: headlineTextView$delegate, reason: from kotlin metadata */
    private final Lazy headlineTextView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: pageIndicator$delegate, reason: from kotlin metadata */
    private final Lazy pageIndicator;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: renamed from: textView4$delegate, reason: from kotlin metadata */
    private final Lazy textView4;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StoryPageHeaderView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.headlineTextView = LazyKt__LazyJVMKt.lazy(new Function0<PageHeaderHeadlineTextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$headlineTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageHeaderHeadlineTextView invoke() {
                return (PageHeaderHeadlineTextView) this.this$0.findViewById(R$id.Title);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_container);
            }
        });
        this.pageIndicator = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$pageIndicator$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.page_indicator);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.Subtitle);
            }
        });
        this.textView4 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$textView4$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.text4);
            }
        });
        this.actionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.StoryPageHeaderView$actionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.action_icon_container);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_story_page_header_layout, this);
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

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getPageIndicator() {
        Object value = this.pageIndicator.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-pageIndicator>(...)");
        return (LinearLayout) value;
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
    public LinearLayout getActionIconContainer() {
        Object value = this.actionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIconContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.PageHeaderView
    protected void initMargins() {
        super.initMargins();
        TextView labelTextView = getLabelTextView();
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.EPIC);
        Intrinsics.checkNotNull(spacerInPixels);
        PageHeaderView.setMargins$default(this, labelTextView, 0, 0, null, Integer.valueOf(spacerInPixels.intValue() * 3), null, null, 110, null);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void setTextContainerOnTouchListener(View.OnTouchListener textContainerOnTouchListener) {
        Intrinsics.checkNotNullParameter(textContainerOnTouchListener, "textContainerOnTouchListener");
        getTextContainer().setOnTouchListener(textContainerOnTouchListener);
    }

    public final void setPagination(int count) {
        if (getPageIndicator().getChildCount() > 0 || count <= 1) {
            return;
        }
        int i = 0;
        while (i < count) {
            int i2 = i + 1;
            ImageView imageView = new ImageView(getContext());
            if (i == 0) {
                imageView.setImageDrawable(AppCompatResources.getDrawable(getContext(), R$drawable.video_preview_selected_dot));
            } else {
                imageView.setImageDrawable(AppCompatResources.getDrawable(getContext(), R$drawable.video_preview_default_dot));
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int dimension = (int) getResources().getDimension(R$dimen.story_detail_header_pagination_dot_half_margin);
            layoutParams.setMargins(dimension, 0, dimension, 0);
            getPageIndicator().addView(imageView, layoutParams);
            i = i2;
        }
    }

    public final void updatePagination(Integer prev, Integer cur) {
        if (prev == null || cur == null || getPageIndicator() == null || getPageIndicator().getChildCount() <= 0) {
            return;
        }
        View childAt = getPageIndicator().getChildAt(prev.intValue());
        ImageView imageView = childAt instanceof ImageView ? (ImageView) childAt : null;
        if (imageView != null) {
            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R$drawable.video_preview_default_dot));
        }
        View childAt2 = getPageIndicator().getChildAt(cur.intValue());
        ImageView imageView2 = childAt2 instanceof ImageView ? (ImageView) childAt2 : null;
        if (imageView2 == null) {
            return;
        }
        imageView2.setImageDrawable(ContextCompat.getDrawable(getContext(), R$drawable.video_preview_selected_dot));
    }
}
