package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.base.ClearableContainer;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SimpleSectionWidget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u0004\u0018\u00010,J\b\u0010-\u001a\u00020*H\u0002J\b\u0010.\u001a\u00020*H\u0002J\u0006\u0010/\u001a\u00020*J\u0010\u00100\u001a\u00020*2\b\u00101\u001a\u0004\u0018\u00010,J;\u00102\u001a\u00020*2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001042\n\b\u0002\u00106\u001a\u0004\u0018\u0001042\n\b\u0002\u00107\u001a\u0004\u0018\u000104¢\u0006\u0002\u00108J\u000e\u00109\u001a\u00020*2\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010:\u001a\u00020*2\u0006\u0010;\u001a\u00020<H\u0016J\u0006\u0010=\u001a\u00020*JE\u0010>\u001a\u00020*2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001042\n\b\u0002\u00106\u001a\u0004\u0018\u0001042\n\b\u0002\u00107\u001a\u0004\u0018\u0001042\b\b\u0002\u0010?\u001a\u00020@¢\u0006\u0002\u0010AJ=\u0010B\u001a\u00020*2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001042\n\b\u0002\u00106\u001a\u0004\u0018\u0001042\n\b\u0002\u00107\u001a\u0004\u0018\u000104H\u0002¢\u0006\u0002\u00108J\u0010\u0010C\u001a\u00020*2\b\u0010D\u001a\u0004\u0018\u00010EJ;\u0010F\u001a\u00020*2\n\b\u0002\u00103\u001a\u0004\u0018\u0001042\n\b\u0002\u00105\u001a\u0004\u0018\u0001042\n\b\u0002\u00106\u001a\u0004\u0018\u0001042\n\b\u0002\u00107\u001a\u0004\u0018\u000104¢\u0006\u0002\u00108J\u000e\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020IJ\u000e\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020<R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\r\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\r\u001a\u0004\b&\u0010'¨\u0006L"}, d2 = {"Lcom/amazon/music/views/library/views/SimpleSectionWidget;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/models/base/ClearableContainer;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "contentContainer", "Landroid/widget/FrameLayout;", "getContentContainer", "()Landroid/widget/FrameLayout;", "contentContainer$delegate", "Lkotlin/Lazy;", "headerContainer", "getHeaderContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "headerContainer$delegate", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "seeMoreButton", "Landroidx/appcompat/widget/AppCompatImageView;", "getSeeMoreButton", "()Landroidx/appcompat/widget/AppCompatImageView;", "seeMoreButton$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "textViewContainer", "Landroid/widget/LinearLayout;", "getTextViewContainer", "()Landroid/widget/LinearLayout;", "textViewContainer$delegate", "titleTextView", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView$delegate", "clearModels", "", "getAdapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "initMargins", "initViewMargins", "initViewStyling", "setAdapter", "adapter", "setContentMargins", "left", "", "top", "right", "bottom", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setContentView", "setEnabled", Constants.ENABLE_DISABLE, "", "setInitialMargins", "setMargins", "view", "Landroid/view/View;", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/view/View;)V", "setMoreButtonMargins", "setMoreButtonOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setTextMargins", "setTitle", "title", "", "updateMoreButtonVisibility", "visibility", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SimpleSectionWidget extends ConstraintLayout implements ClearableContainer {

    /* JADX INFO: renamed from: contentContainer$delegate, reason: from kotlin metadata */
    private final Lazy contentContainer;

    /* JADX INFO: renamed from: headerContainer$delegate, reason: from kotlin metadata */
    private final Lazy headerContainer;
    private RecyclerView recyclerView;

    /* JADX INFO: renamed from: seeMoreButton$delegate, reason: from kotlin metadata */
    private final Lazy seeMoreButton;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: textViewContainer$delegate, reason: from kotlin metadata */
    private final Lazy textViewContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleSectionWidget(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.contentContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.SimpleSectionWidget$contentContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.section_content);
            }
        });
        this.textViewContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.SimpleSectionWidget$textViewContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.section_text_view);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SimpleSectionWidget$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.section_title);
            }
        });
        this.seeMoreButton = LazyKt__LazyJVMKt.lazy(new Function0<AppCompatImageView>() { // from class: com.amazon.music.views.library.views.SimpleSectionWidget$seeMoreButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppCompatImageView invoke() {
                return (AppCompatImageView) this.this$0.findViewById(R$id.section_more_button);
            }
        });
        this.headerContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.SimpleSectionWidget$headerContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.header_container);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_simple_section_layout, this);
        initViewStyling();
        setInitialMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final FrameLayout getContentContainer() {
        Object value = this.contentContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentContainer>(...)");
        return (FrameLayout) value;
    }

    private final LinearLayout getTextViewContainer() {
        Object value = this.textViewContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textViewContainer>(...)");
        return (LinearLayout) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final AppCompatImageView getSeeMoreButton() {
        Object value = this.seeMoreButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-seeMoreButton>(...)");
        return (AppCompatImageView) value;
    }

    private final ConstraintLayout getHeaderContainer() {
        Object value = this.headerContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headerContainer>(...)");
        return (ConstraintLayout) value;
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY_HEADLINE_4_BIG_SHARPGROTESK);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
    }

    private final void initMargins() {
        setMargins$default(this, 0, 0, 0, 0, null, 16, null);
    }

    private final void initViewMargins() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        setContentMargins(Integer.valueOf((gridSize.getColumnSpace() / 2) + gridSize.getMarginSpace()), 0, Integer.valueOf((gridSize.getColumnSpace() / 2) + gridSize.getMarginSpace()), 0);
        setTextMargins(Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace()), 0, Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace()), 0);
        setMoreButtonMargins(0, 0, Integer.valueOf(gridSize.getColumnSpace()), 0);
    }

    public final void setInitialMargins() {
        initMargins();
        initViewMargins();
    }

    public final void setAdapter(UniversalAdapter adapter) {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
        getContentContainer().setVisibility(adapter != null ? 0 : 8);
    }

    public final UniversalAdapter getAdapter() {
        RecyclerView recyclerView = this.recyclerView;
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        if (adapter instanceof UniversalAdapter) {
            return (UniversalAdapter) adapter;
        }
        return null;
    }

    @Override // com.amazon.music.views.library.models.base.ClearableContainer
    public void clearModels() {
        setAdapter(null);
        getTitleTextView().setVisibility(8);
        getSeeMoreButton().setVisibility(8);
    }

    public final void setContentView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getContentContainer().addView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            getTitleTextView().setAlpha(1.0f);
            return;
        }
        Float alpha = this.styleSheet.getAlpha(AlphaKey.GLASS_3);
        if (alpha != null) {
            getTitleTextView().setAlpha(alpha.floatValue());
        }
        getSeeMoreButton().setVisibility(8);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setMaxLines(2);
        getTitleTextView().setVisibility(TextUtils.isEmpty(title) ? 8 : 0);
    }

    public final void updateMoreButtonVisibility(boolean visibility) {
        if (visibility) {
            getSeeMoreButton().setVisibility(0);
            Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
            if (spacerInPixels == null) {
                return;
            }
            int iIntValue = spacerInPixels.intValue();
            setMoreButtonMargins$default(this, Integer.valueOf(iIntValue), null, null, null, 14, null);
            setTextMargins$default(this, null, null, Integer.valueOf(iIntValue), null, 11, null);
            return;
        }
        getSeeMoreButton().setVisibility(8);
    }

    public final void setMoreButtonOnClickListener(View.OnClickListener onClickListener) {
        getHeaderContainer().setOnClickListener(onClickListener);
    }

    public static /* synthetic */ void setMargins$default(SimpleSectionWidget simpleSectionWidget, Integer num, Integer num2, Integer num3, Integer num4, View view, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        View view2 = view;
        if ((i & 16) != 0) {
            view2 = simpleSectionWidget;
        }
        simpleSectionWidget.setMargins(num, num2, num3, num4, view2);
    }

    public final void setMargins(Integer left, Integer top, Integer right, Integer bottom, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final void setContentMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getContentContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setTextMargins$default(SimpleSectionWidget simpleSectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        simpleSectionWidget.setTextMargins(num, num2, num3, num4);
    }

    public final void setTextMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTextViewContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    static /* synthetic */ void setMoreButtonMargins$default(SimpleSectionWidget simpleSectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        simpleSectionWidget.setMoreButtonMargins(num, num2, num3, num4);
    }

    private final void setMoreButtonMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getSeeMoreButton(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
