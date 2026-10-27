package com.android.car.ui.uxr;

import android.view.View;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
class DrawableStateUtil implements DrawableStateView {
    private int[] mStateToAdd;
    private int[] mStateToRemove;
    private final View mView;

    DrawableStateUtil(View view) {
        this.mView = view;
    }

    public int[] onCreateDrawableState(int extraSpace, Function<Integer, int[]> callSuper) {
        int[] result;
        int[] result2 = this.mStateToAdd;
        if (result2 == null) {
            result = callSuper.apply(Integer.valueOf(extraSpace));
        } else {
            result = mergeDrawableStates(callSuper.apply(Integer.valueOf(result2.length + extraSpace)), this.mStateToAdd);
        }
        int[] iArr = this.mStateToRemove;
        if (iArr != null && iArr.length != 0) {
            return Arrays.stream(result).filter(new IntPredicate() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateUtil$-DzHlEN4E11L4xr95KL91TTfySE
                @Override // java.util.function.IntPredicate
                public final boolean test(int i) {
                    return this.f$0.lambda$onCreateDrawableState$1$DrawableStateUtil(i);
                }
            }).toArray();
        }
        return result;
    }

    public /* synthetic */ boolean lambda$onCreateDrawableState$1$DrawableStateUtil(final int state) {
        return Arrays.stream(this.mStateToRemove).noneMatch(new IntPredicate() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateUtil$pTyZDeDfGPctjaUiy17aiq6YrHA
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return DrawableStateUtil.lambda$onCreateDrawableState$0(state, i);
            }
        });
    }

    static /* synthetic */ boolean lambda$onCreateDrawableState$0(int state, int toRemove) {
        return state == toRemove;
    }

    @Override // com.android.car.ui.uxr.DrawableStateView
    public void setExtraDrawableState(int[] stateToAdd, int[] stateToRemove) {
        this.mStateToAdd = stateToAdd;
        this.mStateToRemove = stateToRemove;
        this.mView.refreshDrawableState();
    }

    private static int[] mergeDrawableStates(int[] baseState, int[] additionalState) {
        int i = baseState.length - 1;
        while (i >= 0 && baseState[i] == 0) {
            i--;
        }
        System.arraycopy(additionalState, 0, baseState, i + 1, additionalState.length);
        return baseState;
    }
}
