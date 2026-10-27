package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    private int chainStyle;
    public ArrayList<WidgetRun> widgets;

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.widgets = new ArrayList<>();
        this.orientation = i;
        build();
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget previousChainMember = this.widget;
        do {
            constraintWidget = previousChainMember;
            previousChainMember = previousChainMember.getPreviousChainMember(this.orientation);
        } while (previousChainMember != null);
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        for (WidgetRun widgetRun : this.widgets) {
            int i = this.orientation;
            if (i == 0) {
                widgetRun.widget.horizontalChainRun = this;
            } else if (i == 1) {
                widgetRun.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5 A[PHI: r0 r1
  0x00a5: PHI (r0v13 int) = (r0v10 int), (r0v19 int) binds: [B:29:0x00a3, B:19:0x006d] A[DONT_GENERATE, DONT_INLINE]
  0x00a5: PHI (r1v3 androidx.constraintlayout.core.widgets.analyzer.DependencyNode) = 
  (r1v2 androidx.constraintlayout.core.widgets.analyzer.DependencyNode)
  (r1v7 androidx.constraintlayout.core.widgets.analyzer.DependencyNode)
 binds: [B:29:0x00a3, B:19:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void apply() {
        /*
            r5 = this;
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r0 = r5.widgets
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L16
            java.lang.Object r1 = r0.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            r1.apply()
            goto L6
        L16:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r0 = r5.widgets
            int r0 = r0.size()
            r1 = 1
            if (r0 >= r1) goto L20
            return
        L20:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r2 = r5.widgets
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r2 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r2
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.widget
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r5.widgets
            int r0 = r0 - r1
            java.lang.Object r0 = r4.get(r0)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r0 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r0
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.widget
            int r4 = r5.orientation
            if (r4 != 0) goto L70
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r2.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mRight
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r5.getTarget(r1, r3)
            int r1 = r1.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r5.getFirstVisibleWidget()
            if (r4 == 0) goto L52
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r4.mLeft
            int r1 = r1.getMargin()
        L52:
            if (r2 == 0) goto L59
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r5.start
            r5.addTarget(r4, r2, r1)
        L59:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r5.getTarget(r0, r3)
            int r0 = r0.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r5.getLastVisibleWidget()
            if (r2 == 0) goto L6d
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r2.mRight
            int r0 = r0.getMargin()
        L6d:
            if (r1 == 0) goto Lab
            goto La5
        L70:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mBottom
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r5.getTarget(r2, r1)
            int r2 = r2.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r5.getFirstVisibleWidget()
            if (r4 == 0) goto L88
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r4.mTop
            int r2 = r2.getMargin()
        L88:
            if (r3 == 0) goto L8f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r5.start
            r5.addTarget(r4, r3, r2)
        L8f:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r5.getTarget(r0, r1)
            int r0 = r0.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r5.getLastVisibleWidget()
            if (r2 == 0) goto La3
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r2.mBottom
            int r0 = r0.getMargin()
        La3:
            if (r1 == 0) goto Lab
        La5:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r5.end
            int r0 = -r0
            r5.addTarget(r2, r1, r0)
        Lab:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r5.start
            r0.updateDelegate = r5
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r0 = r5.end
            r0.updateDelegate = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.apply():void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.widgets.size();
        long wrapDimension = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            wrapDimension = ((long) widgetRun.end.margin) + widgetRun.getWrapDimension() + wrapDimension + ((long) widgetRun.start.margin);
        }
        return wrapDimension;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        for (WidgetRun widgetRun : this.widgets) {
            sb.append("<");
            sb.append(widgetRun);
            sb.append("> ");
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e9  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            Method dump skipped, instruction units count: 1008
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }
}
