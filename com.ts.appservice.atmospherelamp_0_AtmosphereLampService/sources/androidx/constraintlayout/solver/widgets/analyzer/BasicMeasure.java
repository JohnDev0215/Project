package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.Optimizer;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();
    private Measure mMeasure = new Measure();

    public static class Measure {
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public boolean useDeprecated;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public enum MeasureType {
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer layout) {
        this.mVariableDimensionsWidgets.clear();
        int childCount = layout.mChildren.size();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget widget = layout.mChildren.get(i);
            if (widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                this.mVariableDimensionsWidgets.add(widget);
            }
        }
        layout.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer layout) {
        int childCount = layout.mChildren.size();
        Measurer measurer = layout.getMeasurer();
        for (int i = 0; i < childCount; i++) {
            ConstraintWidget child = layout.mChildren.get(i);
            if (!(child instanceof Guideline) && (!child.horizontalRun.dimension.resolved || !child.verticalRun.dimension.resolved)) {
                ConstraintWidget.DimensionBehaviour widthBehavior = child.getDimensionBehaviour(0);
                boolean skip = true;
                ConstraintWidget.DimensionBehaviour heightBehavior = child.getDimensionBehaviour(1);
                if (widthBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultWidth == 1 || heightBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || child.mMatchConstraintDefaultHeight == 1) {
                    skip = false;
                }
                if (!skip) {
                    measure(measurer, child, false);
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer layout, String reason, int w, int h) {
        int minWidth = layout.getMinWidth();
        int minHeight = layout.getMinHeight();
        layout.setMinWidth(0);
        layout.setMinHeight(0);
        layout.setWidth(w);
        layout.setHeight(h);
        layout.setMinWidth(minWidth);
        layout.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public void solverMeasure(ConstraintWidgetContainer layout, int optimizationLevel, int paddingX, int paddingY, int widthMode, int widthSize, int heightMode, int heightSize, int lastMeasureWidth, int lastMeasureHeight) {
        int widthSize2;
        int heightSize2;
        int optimizations;
        int maxIterations;
        int sizeDependentWidgetsCount;
        int j;
        boolean containerWrapWidth;
        Measurer measurer;
        int heightSize3;
        int optimizations2;
        Measurer measurer2 = layout.getMeasurer();
        int childCount = layout.mChildren.size();
        int startingWidth = layout.getWidth();
        int startingHeight = layout.getHeight();
        boolean optimizeWrap = Optimizer.enabled(optimizationLevel, 128);
        boolean optimize = optimizeWrap || Optimizer.enabled(optimizationLevel, 64);
        if (optimize) {
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                ConstraintWidget child = layout.mChildren.get(i);
                boolean matchWidth = child.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean matchHeight = child.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                boolean ratio = matchWidth && matchHeight && child.getDimensionRatio() > 0.0f;
                if (child.isInHorizontalChain() && ratio) {
                    optimize = false;
                    break;
                }
                if (child.isInVerticalChain() && ratio) {
                    optimize = false;
                    break;
                } else if (child instanceof VirtualLayout) {
                    optimize = false;
                    break;
                } else if (child.isInHorizontalChain() || child.isInVerticalChain()) {
                    break;
                } else {
                    i++;
                }
            }
            optimize = false;
        }
        if (optimize && LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.measures++;
        }
        boolean allSolved = false;
        boolean optimize2 = ((widthMode == 1073741824 && heightMode == 1073741824) || optimizeWrap) & optimize;
        int computations = 0;
        if (optimize2) {
            widthSize2 = Math.min(layout.getMaxWidth(), widthSize);
            heightSize2 = Math.min(layout.getMaxHeight(), heightSize);
            if (widthMode == 1073741824 && layout.getWidth() != widthSize2) {
                layout.setWidth(widthSize2);
                layout.invalidateGraph();
            }
            if (heightMode == 1073741824 && layout.getHeight() != heightSize2) {
                layout.setHeight(heightSize2);
                layout.invalidateGraph();
            }
            if (widthMode == 1073741824 && heightMode == 1073741824) {
                allSolved = layout.directMeasure(optimizeWrap);
                computations = 2;
            } else {
                allSolved = layout.directMeasureSetup(optimizeWrap);
                if (widthMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 0);
                    computations = 0 + 1;
                }
                if (heightMode == 1073741824) {
                    allSolved &= layout.directMeasureWithOrientation(optimizeWrap, 1);
                    computations++;
                }
            }
            if (allSolved) {
                layout.updateFromRuns(widthMode == 1073741824, heightMode == 1073741824);
            }
        } else {
            layout.horizontalRun.clear();
            layout.verticalRun.clear();
            for (ConstraintWidget child2 : layout.getChildren()) {
                child2.horizontalRun.clear();
                child2.verticalRun.clear();
                allSolved = allSolved;
            }
            widthSize2 = widthSize;
            heightSize2 = heightSize;
        }
        if (!allSolved || computations != 2) {
            if (childCount > 0) {
                measureChildren(layout);
            }
            int optimizations3 = layout.getOptimizationLevel();
            int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
            if (childCount > 0) {
                solveLinearSystem(layout, "First pass", startingWidth, startingHeight);
            }
            if (sizeDependentWidgetsCount2 > 0) {
                boolean containerWrapWidth2 = layout.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                boolean containerWrapHeight = layout.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                int minWidth = Math.max(layout.getWidth(), this.constraintWidgetContainer.getMinWidth());
                int minHeight = Math.max(layout.getHeight(), this.constraintWidgetContainer.getMinHeight());
                int i2 = 0;
                int minWidth2 = minWidth;
                int minHeight2 = minHeight;
                boolean needSolverPass = false;
                while (i2 < sizeDependentWidgetsCount2) {
                    int computations2 = computations;
                    ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i2);
                    int widthSize3 = widthSize2;
                    if (!(widget instanceof VirtualLayout)) {
                        optimizations2 = optimizations3;
                        heightSize3 = heightSize2;
                    } else {
                        int preWidth = widget.getWidth();
                        heightSize3 = heightSize2;
                        int preHeight = widget.getHeight();
                        optimizations2 = optimizations3;
                        boolean needSolverPass2 = needSolverPass | measure(measurer2, widget, true);
                        int measuredWidth = widget.getWidth();
                        boolean needSolverPass3 = needSolverPass2;
                        int measuredHeight = widget.getHeight();
                        if (measuredWidth != preWidth) {
                            widget.setWidth(measuredWidth);
                            if (containerWrapWidth2 && widget.getRight() > minWidth2) {
                                int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                minWidth2 = Math.max(minWidth2, w);
                            }
                            needSolverPass3 = true;
                        }
                        if (measuredHeight != preHeight) {
                            widget.setHeight(measuredHeight);
                            if (containerWrapHeight && widget.getBottom() > minHeight2) {
                                int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                minHeight2 = Math.max(minHeight2, h);
                            }
                            needSolverPass3 = true;
                        }
                        VirtualLayout virtualLayout = (VirtualLayout) widget;
                        needSolverPass = needSolverPass3 | virtualLayout.needSolverPass();
                    }
                    i2++;
                    computations = computations2;
                    widthSize2 = widthSize3;
                    heightSize2 = heightSize3;
                    optimizations3 = optimizations2;
                }
                optimizations = optimizations3;
                int maxIterations2 = 2;
                int j2 = 0;
                while (j2 < maxIterations2) {
                    int i3 = 0;
                    while (i3 < sizeDependentWidgetsCount2) {
                        ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i3);
                        if (((widget2 instanceof Helper) && !(widget2 instanceof VirtualLayout)) || (widget2 instanceof Guideline)) {
                            maxIterations = maxIterations2;
                        } else {
                            maxIterations = maxIterations2;
                            if (widget2.getVisibility() != 8 && ((!widget2.horizontalRun.dimension.resolved || !widget2.verticalRun.dimension.resolved) && !(widget2 instanceof VirtualLayout))) {
                                int preWidth2 = widget2.getWidth();
                                int preHeight2 = widget2.getHeight();
                                sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                                int preBaselineDistance = widget2.getBaselineDistance();
                                j = j2;
                                boolean needSolverPass4 = needSolverPass | measure(measurer2, widget2, true);
                                int measuredWidth2 = widget2.getWidth();
                                boolean needSolverPass5 = needSolverPass4;
                                int measuredHeight2 = widget2.getHeight();
                                if (measuredWidth2 == preWidth2) {
                                    containerWrapWidth = containerWrapWidth2;
                                    measurer = measurer2;
                                } else {
                                    widget2.setWidth(measuredWidth2);
                                    if (containerWrapWidth2) {
                                        containerWrapWidth = containerWrapWidth2;
                                        if (widget2.getRight() <= minWidth2) {
                                            measurer = measurer2;
                                        } else {
                                            measurer = measurer2;
                                            int w2 = widget2.getRight() + widget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                            minWidth2 = Math.max(minWidth2, w2);
                                        }
                                    } else {
                                        containerWrapWidth = containerWrapWidth2;
                                        measurer = measurer2;
                                    }
                                    needSolverPass5 = true;
                                }
                                if (measuredHeight2 != preHeight2) {
                                    widget2.setHeight(measuredHeight2);
                                    if (containerWrapHeight && widget2.getBottom() > minHeight2) {
                                        int h2 = widget2.getBottom() + widget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                        minHeight2 = Math.max(minHeight2, h2);
                                    }
                                    needSolverPass5 = true;
                                }
                                if (!widget2.hasBaseline() || preBaselineDistance == widget2.getBaselineDistance()) {
                                    needSolverPass = needSolverPass5;
                                } else {
                                    needSolverPass = true;
                                }
                            }
                            i3++;
                            maxIterations2 = maxIterations;
                            sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                            j2 = j;
                            containerWrapWidth2 = containerWrapWidth;
                            measurer2 = measurer;
                        }
                        containerWrapWidth = containerWrapWidth2;
                        measurer = measurer2;
                        j = j2;
                        sizeDependentWidgetsCount = sizeDependentWidgetsCount2;
                        i3++;
                        maxIterations2 = maxIterations;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
                        j2 = j;
                        containerWrapWidth2 = containerWrapWidth;
                        measurer2 = measurer;
                    }
                    boolean containerWrapWidth3 = containerWrapWidth2;
                    Measurer measurer3 = measurer2;
                    int maxIterations3 = maxIterations2;
                    int j3 = j2;
                    int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                    if (needSolverPass) {
                        solveLinearSystem(layout, "intermediate pass", startingWidth, startingHeight);
                        needSolverPass = false;
                    }
                    j2 = j3 + 1;
                    maxIterations2 = maxIterations3;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                    containerWrapWidth2 = containerWrapWidth3;
                    measurer2 = measurer3;
                }
                if (needSolverPass) {
                    solveLinearSystem(layout, "2nd pass", startingWidth, startingHeight);
                    boolean needSolverPass6 = false;
                    if (layout.getWidth() < minWidth2) {
                        layout.setWidth(minWidth2);
                        needSolverPass6 = true;
                    }
                    if (layout.getHeight() < minHeight2) {
                        layout.setHeight(minHeight2);
                        needSolverPass6 = true;
                    }
                    if (needSolverPass6) {
                        solveLinearSystem(layout, "3rd pass", startingWidth, startingHeight);
                    }
                }
            } else {
                optimizations = optimizations3;
            }
            layout.setOptimizationLevel(optimizations);
        }
    }

    private boolean measure(Measurer measurer, ConstraintWidget widget, boolean useDeprecated) {
        this.mMeasure.horizontalBehavior = widget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = widget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = widget.getWidth();
        this.mMeasure.verticalDimension = widget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.useDeprecated = useDeprecated;
        boolean horizontalMatchConstraints = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalMatchConstraints = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean horizontalUseRatio = horizontalMatchConstraints && widget.mDimensionRatio > 0.0f;
        boolean verticalUseRatio = verticalMatchConstraints && widget.mDimensionRatio > 0.0f;
        if (horizontalUseRatio && widget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (verticalUseRatio && widget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(widget, this.mMeasure);
        widget.setWidth(this.mMeasure.measuredWidth);
        widget.setHeight(this.mMeasure.measuredHeight);
        widget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        widget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.useDeprecated = false;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
