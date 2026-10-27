package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;

/* JADX INFO: loaded from: classes.dex */
class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i2, chainHead)) {
                    applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003e A[PHI: r8 r15
  0x003e: PHI (r8v3 boolean) = (r8v1 boolean), (r8v32 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
  0x003e: PHI (r15v3 boolean) = (r15v1 boolean), (r15v35 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0040 A[PHI: r8 r15
  0x0040: PHI (r8v30 boolean) = (r8v1 boolean), (r8v32 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]
  0x0040: PHI (r15v33 boolean) = (r15v1 boolean), (r15v35 boolean) binds: [B:24:0x003c, B:15:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0137  */
    /* JADX WARN: Type inference failed for: r35v0, types: [androidx.constraintlayout.solver.LinearSystem] */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30, types: [androidx.constraintlayout.solver.SolverVariable] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v41 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.constraintlayout.solver.widgets.ConstraintWidget] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer r34, androidx.constraintlayout.solver.LinearSystem r35, int r36, int r37, androidx.constraintlayout.solver.widgets.ChainHead r38) {
        /*
            Method dump skipped, instruction units count: 1212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.Chain.applyChainConstraints(androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer, androidx.constraintlayout.solver.LinearSystem, int, int, androidx.constraintlayout.solver.widgets.ChainHead):void");
    }
}
