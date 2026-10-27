package com.facebook.yoga;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class YogaNode implements YogaProps {

    public interface Inputs {
        void freeze(YogaNode yogaNode, @Nullable YogaNode yogaNode2);
    }

    public abstract void addChildAt(YogaNode yogaNode, int i);

    public abstract void calculateLayout(float f, float f2);

    public abstract void dirty();

    @Override // com.facebook.yoga.YogaProps
    public abstract float getAspectRatio();

    public abstract int getChildCount();

    public abstract float getFlex();

    @Override // com.facebook.yoga.YogaProps
    public abstract float getFlexGrow();

    @Override // com.facebook.yoga.YogaProps
    public abstract float getFlexShrink();

    public abstract YogaValue getHeight();

    public abstract YogaDirection getLayoutDirection();

    public abstract float getLayoutHeight();

    public abstract float getLayoutPadding(YogaEdge yogaEdge);

    public abstract float getLayoutWidth();

    public abstract float getLayoutX();

    public abstract float getLayoutY();

    public abstract YogaValue getWidth();

    public abstract boolean hasNewLayout();

    public abstract boolean isDirty();

    public abstract boolean isMeasureDefined();

    public abstract void markLayoutSeen();

    public abstract YogaNode removeChildAt(int i);

    public abstract void reset();

    public abstract void setAlignContent(YogaAlign yogaAlign);

    public abstract void setAlignItems(YogaAlign yogaAlign);

    public abstract void setAlignSelf(YogaAlign yogaAlign);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setAspectRatio(float f);

    public abstract void setBaselineFunction(YogaBaselineFunction yogaBaselineFunction);

    public abstract void setBorder(YogaEdge yogaEdge, float f);

    public abstract void setData(Object obj);

    public abstract void setDirection(YogaDirection yogaDirection);

    public abstract void setDisplay(YogaDisplay yogaDisplay);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setFlex(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setFlexBasis(float f);

    public abstract void setFlexBasisAuto();

    @Override // com.facebook.yoga.YogaProps
    public abstract void setFlexBasisPercent(float f);

    public abstract void setFlexDirection(YogaFlexDirection yogaFlexDirection);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setFlexGrow(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setFlexShrink(float f);

    public abstract void setGap(YogaGutter yogaGutter, float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setHeight(float f);

    public abstract void setHeightAuto();

    @Override // com.facebook.yoga.YogaProps
    public abstract void setHeightPercent(float f);

    public abstract void setJustifyContent(YogaJustify yogaJustify);

    public abstract void setMargin(YogaEdge yogaEdge, float f);

    public abstract void setMarginAuto(YogaEdge yogaEdge);

    public abstract void setMarginPercent(YogaEdge yogaEdge, float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMaxHeight(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMaxHeightPercent(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMaxWidth(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMaxWidthPercent(float f);

    public abstract void setMeasureFunction(YogaMeasureFunction yogaMeasureFunction);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMinHeight(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMinHeightPercent(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMinWidth(float f);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setMinWidthPercent(float f);

    public abstract void setOverflow(YogaOverflow yogaOverflow);

    public abstract void setPadding(YogaEdge yogaEdge, float f);

    public abstract void setPaddingPercent(YogaEdge yogaEdge, float f);

    public abstract void setPosition(YogaEdge yogaEdge, float f);

    public abstract void setPositionPercent(YogaEdge yogaEdge, float f);

    public abstract void setPositionType(YogaPositionType yogaPositionType);

    @Override // com.facebook.yoga.YogaProps
    public abstract void setWidth(float f);

    public abstract void setWidthAuto();

    @Override // com.facebook.yoga.YogaProps
    public abstract void setWidthPercent(float f);

    public abstract void setWrap(YogaWrap yogaWrap);
}
