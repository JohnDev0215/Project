package com.byd.automultipletheme.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewGroupKt;
import com.blankj.utilcode.util.ScreenUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.IntentString;
import java.util.Locale;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommonTabLayout.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 .2\u00020\u0001:\u0001.B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001c\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\tH\u0016J$\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0002J\u0012\u0010'\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\b\u0010)\u001a\u00020\u001dH\u0003J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\tJ\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020-H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u001b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006/"}, d2 = {"Lcom/byd/automultipletheme/widget/CommonTabLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "floatView", "Landroid/widget/ImageView;", "indicatorColor", "Ljava/lang/Integer;", "indicatorHeight", "parentLayout", IntentString.DATA_POSITION, "switchLayout", "Landroid/widget/LinearLayout;", "tabIndicatorGravity", "tabIndicatorMode", "tabIndicatorWidth", "tabItemSelectListener", "Lcom/byd/automultipletheme/widget/TabItemSelectListener;", "tabSpace", "tabTextSelectColor", "tabTextUnselectColor", "addTabItemSelectListener", "", "addView", "child", "Landroid/view/View;", "params", "Landroid/view/ViewGroup$LayoutParams;", "index", "addViewFromItem", "tabItem", "Lcom/byd/automultipletheme/widget/CommonTabItem;", "addViewInternal", "createFloatView", "moveToNext", "setCurrentTab", "switchTabView", "tabView", "Lcom/byd/automultipletheme/widget/CommonTabView;", "Companion", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CommonTabLayout extends FrameLayout {
    public static final int HORIZONTAL = 0;
    private ImageView floatView;
    private Integer indicatorColor;
    private int indicatorHeight;
    private FrameLayout parentLayout;
    private int position;
    private LinearLayout switchLayout;
    private int tabIndicatorGravity;
    private int tabIndicatorMode;
    private int tabIndicatorWidth;
    private TabItemSelectListener tabItemSelectListener;
    private int tabSpace;
    private Integer tabTextSelectColor;
    private Integer tabTextUnselectColor;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CommonTabLayout(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CommonTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.tabIndicatorGravity = 3;
        FrameLayout frameLayout = new FrameLayout(context);
        this.parentLayout = frameLayout;
        super.addView(frameLayout, 0, new FrameLayout.LayoutParams(-2, -2));
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CommonTabLayout);
        this.indicatorColor = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(0, ContextCompat.getColor(context, R.color.item_selected)));
        this.indicatorHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, context.getResources().getDimensionPixelSize(R.dimen.dp_2));
        this.tabTextSelectColor = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(4, ContextCompat.getColor(context, R.color.item_selected)));
        this.tabTextUnselectColor = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(5, ContextCompat.getColor(context, R.color.item_unselected)));
        this.tabIndicatorGravity = typedArrayObtainStyledAttributes.getInt(1, 3);
        this.tabIndicatorMode = typedArrayObtainStyledAttributes.getInt(3, 0);
        createFloatView();
        this.switchLayout = new LinearLayout(context);
        this.parentLayout.addView(this.switchLayout, new FrameLayout.LayoutParams(-2, -2, 17));
        this.switchLayout.setOrientation(this.tabIndicatorMode != 0 ? 1 : 0);
    }

    @Override // android.view.ViewGroup
    public void addView(View child) {
        addViewInternal(child);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        addViewInternal(child);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        addViewInternal(child);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        addViewInternal(child);
    }

    private final void addViewInternal(View child) {
        if (child instanceof CommonTabItem) {
            addViewFromItem((CommonTabItem) child);
            return;
        }
        throw new IllegalStateException("CommonTabLayout just use CommonTabItem");
    }

    private final void addViewFromItem(final CommonTabItem tabItem) {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        if (this.tabIndicatorMode == 0) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_128);
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_96);
            this.tabSpace = getContext().getResources().getDimensionPixelSize(R.dimen.dp_80);
        } else {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_208);
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_64);
            this.tabSpace = getContext().getResources().getDimensionPixelSize(R.dimen.dp_4);
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        final CommonTabView commonTabView = new CommonTabView(context, this.tabIndicatorMode);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2);
        if (this.switchLayout.getChildCount() == 0) {
            if (this.tabIndicatorMode == 0) {
                layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_28), this.tabSpace, 0);
            } else if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_12), getContext().getResources().getDimensionPixelSize(R.dimen.dp_28), 0);
            } else {
                layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_12), 0, 0);
            }
        } else if (this.tabIndicatorMode != 0) {
            if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_3), getContext().getResources().getDimensionPixelSize(R.dimen.dp_28), 0);
            } else {
                layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_3), 0, 0);
            }
        } else {
            layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_28), 0, 0);
        }
        commonTabView.setLayoutParams(layoutParams);
        commonTabView.setText(tabItem.getText());
        Integer num = this.tabTextSelectColor;
        Intrinsics.checkNotNull(num);
        Integer num2 = this.tabTextUnselectColor;
        Intrinsics.checkNotNull(num2);
        commonTabView.setTextColor(new ColorStateList(new int[][]{new int[]{android.R.attr.state_enabled}, new int[0]}, new int[]{num.intValue(), num2.intValue()}));
        commonTabView.setIcon(tabItem.getIcon());
        commonTabView.setIconSize(tabItem.getIconSize());
        commonTabView.setBackground(tabItem.getBackground());
        this.switchLayout.addView(commonTabView);
        if (this.switchLayout.getChildCount() == 1) {
            switchTabView(commonTabView);
            TabItemSelectListener tabItemSelectListener = this.tabItemSelectListener;
            if (tabItemSelectListener != null) {
                tabItemSelectListener.onItemSelected(this.position, tabItem);
            }
        }
        commonTabView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.automultipletheme.widget.CommonTabLayout$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CommonTabLayout.m106addViewFromItem$lambda0(this.f$0, commonTabView, tabItem, view);
            }
        });
        requestLayout();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addViewFromItem$lambda-0, reason: not valid java name */
    public static final void m106addViewFromItem$lambda0(CommonTabLayout this$0, CommonTabView tabView, CommonTabItem tabItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tabView, "$tabView");
        Intrinsics.checkNotNullParameter(tabItem, "$tabItem");
        if (Intrinsics.areEqual(ViewGroupKt.get(this$0.switchLayout, this$0.position), tabView)) {
            return;
        }
        this$0.switchTabView(tabView);
        TabItemSelectListener tabItemSelectListener = this$0.tabItemSelectListener;
        if (tabItemSelectListener != null) {
            tabItemSelectListener.onItemSelected(this$0.position, tabItem);
        }
        this$0.moveToNext();
    }

    private final void createFloatView() {
        int dimensionPixelSize;
        int dimensionPixelSize2;
        if (this.tabIndicatorMode == 0) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_128);
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_96);
        } else if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1 || (AutoUtils.INSTANCE.isRightRudder() && AutoUtils.INSTANCE.getModelType() != 3)) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_230);
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_64);
        } else {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_208);
            dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.dp_64);
        }
        this.floatView = new ImageView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2);
        if (this.tabIndicatorMode != 0) {
            layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_12), 0, 0);
        } else {
            layoutParams.setMargins(0, getContext().getResources().getDimensionPixelSize(R.dimen.dp_28), 0, 0);
        }
        ImageView imageView = this.floatView;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("floatView");
            imageView = null;
        }
        imageView.setLayoutParams(layoutParams);
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            ImageView imageView3 = this.floatView;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("floatView");
                imageView3 = null;
            }
            imageView3.setImageResource(R.mipmap.img_menu_bg_select_denza);
        } else if (AutoUtils.INSTANCE.isRightRudder() && ScreenUtils.isLandscape()) {
            ImageView imageView4 = this.floatView;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("floatView");
                imageView4 = null;
            }
            imageView4.setImageResource(R.mipmap.img_menu_bg_select_right);
        } else {
            ImageView imageView5 = this.floatView;
            if (imageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("floatView");
                imageView5 = null;
            }
            imageView5.setImageResource(R.mipmap.img_menu_bg_select);
        }
        FrameLayout frameLayout = this.parentLayout;
        ImageView imageView6 = this.floatView;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("floatView");
        } else {
            imageView2 = imageView6;
        }
        frameLayout.addView(imageView2);
    }

    private final void moveToNext() {
        int dimensionPixelSize;
        if (this.tabIndicatorMode == 0) {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_128);
        } else {
            dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_64);
        }
        this.tabIndicatorWidth = dimensionPixelSize;
        float f = (dimensionPixelSize * this.position) + (r1 * this.tabSpace);
        String str = this.tabIndicatorMode == 0 ? "translationX" : "translationY";
        ImageView imageView = this.floatView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("floatView");
            imageView = null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, str, f);
        objectAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimatorOfFloat.setDuration(0L);
        objectAnimatorOfFloat.start();
    }

    private final void switchTabView(CommonTabView tabView) {
        int childCount = this.switchLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.switchLayout.getChildAt(i);
            Objects.requireNonNull(childAt, "null cannot be cast to non-null type com.byd.automultipletheme.widget.CommonTabView");
            CommonTabView commonTabView = (CommonTabView) childAt;
            if (Intrinsics.areEqual(commonTabView, tabView)) {
                this.position = i;
                tabView.setViewEnable(true);
            } else {
                commonTabView.setViewEnable(false);
            }
        }
    }

    public final void setCurrentTab(int index) {
        int childCount = this.switchLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.switchLayout.getChildAt(i);
            Objects.requireNonNull(childAt, "null cannot be cast to non-null type com.byd.automultipletheme.widget.CommonTabView");
            CommonTabView commonTabView = (CommonTabView) childAt;
            if (i == index) {
                this.position = i;
                commonTabView.setViewEnable(true);
            } else {
                commonTabView.setViewEnable(false);
            }
        }
        moveToNext();
    }

    public final void addTabItemSelectListener(TabItemSelectListener tabItemSelectListener) {
        Intrinsics.checkNotNullParameter(tabItemSelectListener, "tabItemSelectListener");
        this.tabItemSelectListener = tabItemSelectListener;
    }
}
