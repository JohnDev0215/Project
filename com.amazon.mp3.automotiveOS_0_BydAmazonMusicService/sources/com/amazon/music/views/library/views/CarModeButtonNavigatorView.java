package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.base.ClearableContainer;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
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

/* JADX INFO: compiled from: CarModeButtonNavigatorView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u001fJ\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u001fH\u0002JG\u0010(\u001a\u00020\u001f2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010*2\b\b\u0002\u0010.\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u000102J=\u00103\u001a\u00020\u001f2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010*H\u0002¢\u0006\u0002\u00104J\u000e\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u000207R\u001b\u0010\b\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001b\u0010\u001c¨\u00068"}, d2 = {"Lcom/amazon/music/views/library/views/CarModeButtonNavigatorView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/models/base/ClearableContainer;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "headerContainer", "getHeaderContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "headerContainer$delegate", "Lkotlin/Lazy;", "itemDivider", "Landroid/view/View;", "getItemDivider", "()Landroid/view/View;", "itemDivider$delegate", "seeMoreButton", "Landroidx/appcompat/widget/AppCompatImageView;", "getSeeMoreButton", "()Landroidx/appcompat/widget/AppCompatImageView;", "seeMoreButton$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "titleTextView", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView$delegate", "clearModels", "", "hideDivider", "initMargins", "initViewMargins", "initViewStyling", "setEnabled", Constants.ENABLE_DISABLE, "", "setInitialMargins", "setMargins", "left", "", "top", "right", "bottom", "view", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/view/View;)V", "setMoreButtonOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setTextMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setTitle", "title", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeButtonNavigatorView extends ConstraintLayout implements ClearableContainer {

    /* JADX INFO: renamed from: headerContainer$delegate, reason: from kotlin metadata */
    private final Lazy headerContainer;

    /* JADX INFO: renamed from: itemDivider$delegate, reason: from kotlin metadata */
    private final Lazy itemDivider;

    /* JADX INFO: renamed from: seeMoreButton$delegate, reason: from kotlin metadata */
    private final Lazy seeMoreButton;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeButtonNavigatorView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CarModeButtonNavigatorView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.more_title);
            }
        });
        this.seeMoreButton = LazyKt__LazyJVMKt.lazy(new Function0<AppCompatImageView>() { // from class: com.amazon.music.views.library.views.CarModeButtonNavigatorView$seeMoreButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppCompatImageView invoke() {
                return (AppCompatImageView) this.this$0.findViewById(R$id.more_button);
            }
        });
        this.headerContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.CarModeButtonNavigatorView$headerContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.more_container);
            }
        });
        this.itemDivider = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.views.library.views.CarModeButtonNavigatorView$itemDivider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R$id.item_divider);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_carmode_button_layout, this);
        initViewStyling();
        setInitialMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
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

    private final View getItemDivider() {
        Object value = this.itemDivider.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-itemDivider>(...)");
        return (View) value;
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY_T2_BIG_SHARPGROTESK);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
    }

    private final void initMargins() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        setMargins$default(this, Integer.valueOf(gridSize != null ? gridSize.getMarginSpace() : 0), 0, 0, 0, null, 16, null);
    }

    private final void initViewMargins() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        setTextMargins(Integer.valueOf(gridSize.getColumnSpace() / 4), 0, Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace()), 0);
    }

    private final void setInitialMargins() {
        initMargins();
        initViewMargins();
    }

    @Override // com.amazon.music.views.library.models.base.ClearableContainer
    public void clearModels() {
        getTitleTextView().setVisibility(8);
        getSeeMoreButton().setVisibility(8);
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
        getTitleTextView().setVisibility(TextUtils.isEmpty(title) ? 8 : 0);
    }

    public final void setMoreButtonOnClickListener(View.OnClickListener onClickListener) {
        getHeaderContainer().setOnClickListener(onClickListener);
    }

    static /* synthetic */ void setMargins$default(CarModeButtonNavigatorView carModeButtonNavigatorView, Integer num, Integer num2, Integer num3, Integer num4, View view, int i, Object obj) {
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
            view2 = carModeButtonNavigatorView;
        }
        carModeButtonNavigatorView.setMargins(num, num2, num3, num4, view2);
    }

    private final void setMargins(Integer left, Integer top, Integer right, Integer bottom, View view) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setTextMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTitleTextView(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final void hideDivider() {
        getItemDivider().setVisibility(8);
    }
}
