package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaValue;

/* JADX INFO: loaded from: classes6.dex */
public interface ReactShadowNode<T extends ReactShadowNode> {
    void addChildAt(T t, int i);

    void addNativeChildAt(T t, int i);

    void calculateLayout();

    void calculateLayout(float f, float f2);

    Iterable<? extends ReactShadowNode> calculateLayoutOnChildren();

    void dirty();

    boolean dispatchUpdates(float f, float f2, UIViewOperationQueue uIViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer);

    void dispose();

    T getChildAt(int i);

    int getChildCount();

    float getFlex();

    Integer getHeightMeasureSpec();

    float getLayoutHeight();

    @Nullable
    T getLayoutParent();

    float getLayoutWidth();

    float getLayoutX();

    float getLayoutY();

    int getNativeChildCount();

    NativeKind getNativeKind();

    int getNativeOffsetForChild(T t);

    @Nullable
    T getNativeParent();

    @Nullable
    T getParent();

    int getReactTag();

    int getRootTag();

    int getScreenHeight();

    int getScreenWidth();

    int getScreenX();

    int getScreenY();

    YogaValue getStyleHeight();

    YogaValue getStyleWidth();

    ThemedReactContext getThemedContext();

    int getTotalNativeChildren();

    String getViewClass();

    Integer getWidthMeasureSpec();

    boolean hasUpdates();

    int indexOf(T t);

    int indexOfNativeChild(T t);

    boolean isDescendantOf(T t);

    boolean isLayoutOnly();

    boolean isVirtual();

    void markUpdateSeen();

    void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer);

    void removeAllNativeChildren();

    void removeAndDisposeAllChildren();

    T removeChildAt(int i);

    T removeNativeChildAt(int i);

    void setColumnGap(float f);

    void setFlex(float f);

    void setFlexBasis(float f);

    void setFlexBasisPercent(float f);

    void setFlexGrow(float f);

    void setFlexShrink(float f);

    void setGap(float f);

    void setIsLayoutOnly(boolean z);

    void setLayoutDirection(YogaDirection yogaDirection);

    void setLayoutParent(@Nullable T t);

    void setLocalData(Object obj);

    void setMarginAuto(int i);

    void setMeasureSpecs(int i, int i2);

    void setReactTag(int i);

    void setRootTag(int i);

    void setRowGap(float f);

    void setStyleAspectRatio(float f);

    void setStyleHeight(float f);

    void setStyleHeightPercent(float f);

    void setStyleMaxHeight(float f);

    void setStyleMaxHeightPercent(float f);

    void setStyleMaxWidth(float f);

    void setStyleMaxWidthPercent(float f);

    void setStyleMinHeight(float f);

    void setStyleMinHeightPercent(float f);

    void setStyleMinWidth(float f);

    void setStyleMinWidthPercent(float f);

    void setStyleWidth(float f);

    void setStyleWidthPercent(float f);

    void setThemedContext(ThemedReactContext themedReactContext);

    void setViewClassName(String str);

    boolean shouldNotifyOnLayout();

    void updateProperties(ReactStylesDiffMap reactStylesDiffMap);
}
