package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int distance(android.view.View r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14) {
        /*
            r5 = this;
            int r5 = r5.mSide
            r0 = 5
            r1 = 3
            r2 = 0
            r3 = 1
            r4 = 8388611(0x800003, float:1.1754948E-38)
            if (r5 != r4) goto L19
            int r5 = androidx.core.view.ViewCompat.getLayoutDirection(r6)
            if (r5 != r3) goto L12
            goto L13
        L12:
            r3 = r2
        L13:
            if (r3 == 0) goto L17
        L15:
            r5 = r0
            goto L29
        L17:
            r5 = r1
            goto L29
        L19:
            r4 = 8388613(0x800005, float:1.175495E-38)
            if (r5 != r4) goto L29
            int r5 = androidx.core.view.ViewCompat.getLayoutDirection(r6)
            if (r5 != r3) goto L25
            goto L26
        L25:
            r3 = r2
        L26:
            if (r3 == 0) goto L15
            goto L17
        L29:
            if (r5 == r1) goto L51
            if (r5 == r0) goto L48
            r6 = 48
            if (r5 == r6) goto L3f
            r6 = 80
            if (r5 == r6) goto L36
            goto L59
        L36:
            int r8 = r8 - r12
            int r9 = r9 - r7
            int r5 = java.lang.Math.abs(r9)
            int r2 = r5 + r8
            goto L59
        L3f:
            int r14 = r14 - r8
            int r9 = r9 - r7
            int r5 = java.lang.Math.abs(r9)
            int r2 = r5 + r14
            goto L59
        L48:
            int r7 = r7 - r11
            int r10 = r10 - r8
            int r5 = java.lang.Math.abs(r10)
            int r2 = r5 + r7
            goto L59
        L51:
            int r13 = r13 - r7
            int r10 = r10 - r8
            int r5 = java.lang.Math.abs(r10)
            int r2 = r5 + r13
        L59:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.SidePropagation.distance(android.view.View, int, int, int, int, int, int, int, int):int");
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        int i = this.mSide;
        return (i == 3 || i == 5 || i == 8388611 || i == 8388613) ? viewGroup.getWidth() : viewGroup.getHeight();
    }

    @Override // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        int iCenterX;
        int iCenterY;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null && transitionValues2 == null) {
            return 0L;
        }
        Rect epicenter = transition.getEpicenter();
        if (transitionValues2 == null || getViewVisibility(transitionValues3) == 0) {
            i = -1;
        } else {
            transitionValues3 = transitionValues2;
            i = 1;
        }
        int viewX = getViewX(transitionValues3);
        int viewY = getViewY(transitionValues3);
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        int iRound = iArr[0] + Math.round(viewGroup.getTranslationX());
        int iRound2 = Math.round(viewGroup.getTranslationY()) + iArr[1];
        int width = viewGroup.getWidth() + iRound;
        int height = viewGroup.getHeight() + iRound2;
        if (epicenter != null) {
            iCenterX = epicenter.centerX();
            iCenterY = epicenter.centerY();
        } else {
            iCenterX = (iRound + width) / 2;
            iCenterY = (iRound2 + height) / 2;
        }
        float fDistance = distance(viewGroup, viewX, viewY, iCenterX, iCenterY, iRound, iRound2, width, height) / getMaxDistance(viewGroup);
        long duration = transition.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return Math.round(((duration * ((long) i)) / this.mPropagationSpeed) * fDistance);
    }

    public void setPropagationSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    public void setSide(int i) {
        this.mSide = i;
    }
}
