package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.Metrics;
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

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i);
            if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT || constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer) {
        this.constraintWidgetContainer = constraintWidgetContainer;
    }

    private void measureChildren(ConstraintWidgetContainer constraintWidgetContainer) {
        int size = constraintWidgetContainer.mChildren.size();
        Measurer measurer = constraintWidgetContainer.getMeasurer();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i);
            if (!(constraintWidget instanceof Guideline) && (!constraintWidget.horizontalRun.dimension.resolved || !constraintWidget.verticalRun.dimension.resolved)) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(0);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.getDimensionBehaviour(1);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour3 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (!(dimensionBehaviour == dimensionBehaviour3 && constraintWidget.mMatchConstraintDefaultWidth != 1 && dimensionBehaviour2 == dimensionBehaviour3 && constraintWidget.mMatchConstraintDefaultHeight != 1)) {
                    measure(measurer, constraintWidget, false);
                }
            }
        }
        measurer.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer, String str, int i, int i2) {
        int minWidth = constraintWidgetContainer.getMinWidth();
        int minHeight = constraintWidgetContainer.getMinHeight();
        constraintWidgetContainer.setMinWidth(0);
        constraintWidgetContainer.setMinHeight(0);
        constraintWidgetContainer.setWidth(i);
        constraintWidgetContainer.setHeight(i2);
        constraintWidgetContainer.setMinWidth(minWidth);
        constraintWidgetContainer.setMinHeight(minHeight);
        this.constraintWidgetContainer.layout();
    }

    public void solverMeasure(ConstraintWidgetContainer constraintWidgetContainer, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        boolean zDirectMeasure;
        int i10;
        int i11;
        boolean z;
        boolean z2;
        int i12;
        int i13;
        Measurer measurer;
        int i14;
        boolean z3;
        boolean zDirectMeasureWithOrientation;
        int i15;
        Metrics metrics;
        Measurer measurer2 = constraintWidgetContainer.getMeasurer();
        int size = constraintWidgetContainer.mChildren.size();
        int width = constraintWidgetContainer.getWidth();
        int height = constraintWidgetContainer.getHeight();
        boolean zEnabled = Optimizer.enabled(i, 128);
        boolean z4 = zEnabled || Optimizer.enabled(i, 64);
        if (z4) {
            for (int i16 = 0; i16 < size; i16++) {
                ConstraintWidget constraintWidget = constraintWidgetContainer.mChildren.get(i16);
                boolean z5 = (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((constraintWidget.isInHorizontalChain() && z5) || ((constraintWidget.isInVerticalChain() && z5) || (constraintWidget instanceof VirtualLayout) || constraintWidget.isInHorizontalChain() || constraintWidget.isInVerticalChain())) {
                    z4 = false;
                    break;
                }
            }
        }
        if (z4 && (metrics = LinearSystem.sMetrics) != null) {
            metrics.measures++;
        }
        int i17 = 2;
        if (z4 & ((i4 == 1073741824 && i6 == 1073741824) || zEnabled)) {
            int iMin = Math.min(constraintWidgetContainer.getMaxWidth(), i5);
            int iMin2 = Math.min(constraintWidgetContainer.getMaxHeight(), i7);
            if (i4 == 1073741824 && constraintWidgetContainer.getWidth() != iMin) {
                constraintWidgetContainer.setWidth(iMin);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i6 == 1073741824 && constraintWidgetContainer.getHeight() != iMin2) {
                constraintWidgetContainer.setHeight(iMin2);
                constraintWidgetContainer.invalidateGraph();
            }
            if (i4 == 1073741824 && i6 == 1073741824) {
                zDirectMeasure = constraintWidgetContainer.directMeasure(zEnabled);
                i10 = 2;
            } else {
                boolean zDirectMeasureSetup = constraintWidgetContainer.directMeasureSetup(zEnabled);
                if (i4 == 1073741824) {
                    zDirectMeasureWithOrientation = zDirectMeasureSetup & constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 0);
                    i15 = 1;
                } else {
                    zDirectMeasureWithOrientation = zDirectMeasureSetup;
                    i15 = 0;
                }
                if (i6 == 1073741824) {
                    boolean zDirectMeasureWithOrientation2 = constraintWidgetContainer.directMeasureWithOrientation(zEnabled, 1) & zDirectMeasureWithOrientation;
                    i10 = i15 + 1;
                    zDirectMeasure = zDirectMeasureWithOrientation2;
                } else {
                    i10 = i15;
                    zDirectMeasure = zDirectMeasureWithOrientation;
                }
            }
            if (zDirectMeasure) {
                constraintWidgetContainer.updateFromRuns(i4 == 1073741824, i6 == 1073741824);
            }
        } else {
            constraintWidgetContainer.horizontalRun.clear();
            constraintWidgetContainer.verticalRun.clear();
            for (ConstraintWidget constraintWidget2 : constraintWidgetContainer.getChildren()) {
                constraintWidget2.horizontalRun.clear();
                constraintWidget2.verticalRun.clear();
            }
            zDirectMeasure = false;
            i10 = 0;
        }
        if (zDirectMeasure && i10 == 2) {
            return;
        }
        if (size > 0) {
            measureChildren(constraintWidgetContainer);
        }
        int optimizationLevel = constraintWidgetContainer.getOptimizationLevel();
        int size2 = this.mVariableDimensionsWidgets.size();
        if (size > 0) {
            solveLinearSystem(constraintWidgetContainer, "First pass", width, height);
        }
        if (size2 > 0) {
            boolean z6 = constraintWidgetContainer.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z7 = constraintWidgetContainer.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int iMax = Math.max(constraintWidgetContainer.getWidth(), this.constraintWidgetContainer.getMinWidth());
            int iMax2 = Math.max(constraintWidgetContainer.getHeight(), this.constraintWidgetContainer.getMinHeight());
            int i18 = 0;
            boolean zMeasure = false;
            while (i18 < size2) {
                ConstraintWidget constraintWidget3 = this.mVariableDimensionsWidgets.get(i18);
                if (constraintWidget3 instanceof VirtualLayout) {
                    int width2 = constraintWidget3.getWidth();
                    int height2 = constraintWidget3.getHeight();
                    i14 = optimizationLevel;
                    boolean zMeasure2 = zMeasure | measure(measurer2, constraintWidget3, true);
                    int width3 = constraintWidget3.getWidth();
                    int height3 = constraintWidget3.getHeight();
                    if (width3 != width2) {
                        constraintWidget3.setWidth(width3);
                        if (z6 && constraintWidget3.getRight() > iMax) {
                            iMax = Math.max(iMax, constraintWidget3.getRight() + constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                        }
                        z3 = true;
                    } else {
                        z3 = zMeasure2;
                    }
                    if (height3 != height2) {
                        constraintWidget3.setHeight(height3);
                        if (z7 && constraintWidget3.getBottom() > iMax2) {
                            iMax2 = Math.max(iMax2, constraintWidget3.getBottom() + constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                        }
                        z3 = true;
                    }
                    zMeasure = z3 | ((VirtualLayout) constraintWidget3).needSolverPass();
                } else {
                    i14 = optimizationLevel;
                }
                i18++;
                optimizationLevel = i14;
                i17 = 2;
            }
            int i19 = optimizationLevel;
            int i20 = 0;
            for (int i21 = i17; i20 < i21; i21 = 2) {
                int i22 = 0;
                while (i22 < size2) {
                    ConstraintWidget constraintWidget4 = this.mVariableDimensionsWidgets.get(i22);
                    if (((constraintWidget4 instanceof Helper) && !(constraintWidget4 instanceof VirtualLayout)) || (constraintWidget4 instanceof Guideline) || constraintWidget4.getVisibility() == 8 || ((constraintWidget4.horizontalRun.dimension.resolved && constraintWidget4.verticalRun.dimension.resolved) || (constraintWidget4 instanceof VirtualLayout))) {
                        i13 = i20;
                        i12 = size2;
                        measurer = measurer2;
                    } else {
                        int width4 = constraintWidget4.getWidth();
                        int height4 = constraintWidget4.getHeight();
                        i12 = size2;
                        int baselineDistance = constraintWidget4.getBaselineDistance();
                        i13 = i20;
                        zMeasure |= measure(measurer2, constraintWidget4, true);
                        int width5 = constraintWidget4.getWidth();
                        measurer = measurer2;
                        int height5 = constraintWidget4.getHeight();
                        if (width5 != width4) {
                            constraintWidget4.setWidth(width5);
                            if (z6 && constraintWidget4.getRight() > iMax) {
                                iMax = Math.max(iMax, constraintWidget4.getRight() + constraintWidget4.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                            }
                            zMeasure = true;
                        }
                        if (height5 != height4) {
                            constraintWidget4.setHeight(height5);
                            if (z7 && constraintWidget4.getBottom() > iMax2) {
                                iMax2 = Math.max(iMax2, constraintWidget4.getBottom() + constraintWidget4.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                            }
                            zMeasure = true;
                        }
                        if (constraintWidget4.hasBaseline() && baselineDistance != constraintWidget4.getBaselineDistance()) {
                            zMeasure = true;
                        }
                    }
                    i22++;
                    size2 = i12;
                    measurer2 = measurer;
                    i20 = i13;
                }
                int i23 = i20;
                int i24 = size2;
                Measurer measurer3 = measurer2;
                if (zMeasure) {
                    solveLinearSystem(constraintWidgetContainer, "intermediate pass", width, height);
                    zMeasure = false;
                }
                i20 = i23 + 1;
                size2 = i24;
                measurer2 = measurer3;
            }
            if (zMeasure) {
                solveLinearSystem(constraintWidgetContainer, "2nd pass", width, height);
                if (constraintWidgetContainer.getWidth() < iMax) {
                    constraintWidgetContainer.setWidth(iMax);
                    z = true;
                } else {
                    z = false;
                }
                if (constraintWidgetContainer.getHeight() < iMax2) {
                    constraintWidgetContainer.setHeight(iMax2);
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2) {
                    solveLinearSystem(constraintWidgetContainer, "3rd pass", width, height);
                }
            }
            i11 = i19;
        } else {
            i11 = optimizationLevel;
        }
        constraintWidgetContainer.setOptimizationLevel(i11);
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        Measure measure = this.mMeasure;
        measure.measuredNeedsSolverPass = false;
        measure.useDeprecated = z;
        boolean z2 = measure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z3 = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        boolean z5 = z3 && constraintWidget.mDimensionRatio > 0.0f;
        if (z4 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z5 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, this.mMeasure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        Measure measure2 = this.mMeasure;
        measure2.useDeprecated = false;
        return measure2.measuredNeedsSolverPass;
    }
}
