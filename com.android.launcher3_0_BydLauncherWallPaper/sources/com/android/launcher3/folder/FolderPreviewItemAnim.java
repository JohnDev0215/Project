package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.FloatArrayEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.util.Property;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
class FolderPreviewItemAnim {
    public final float[] finalState;
    private final ObjectAnimator mAnimator;
    private final PreviewItemManager mItemManager;
    private final PreviewItemDrawingParams mParams;
    private static final Property<FolderPreviewItemAnim, float[]> PARAMS = new Property<FolderPreviewItemAnim, float[]>(float[].class, "params") { // from class: com.android.launcher3.folder.FolderPreviewItemAnim.1
        @Override // android.util.Property
        public float[] get(FolderPreviewItemAnim anim) {
            FolderPreviewItemAnim.sTempParamsArray[0] = anim.mParams.scale;
            FolderPreviewItemAnim.sTempParamsArray[1] = anim.mParams.transX;
            FolderPreviewItemAnim.sTempParamsArray[2] = anim.mParams.transY;
            return FolderPreviewItemAnim.sTempParamsArray;
        }

        @Override // android.util.Property
        public void set(FolderPreviewItemAnim anim, float[] value) {
            anim.setParams(value);
        }
    };
    private static PreviewItemDrawingParams sTmpParams = new PreviewItemDrawingParams(0.0f, 0.0f, 0.0f, 0.0f);
    private static final float[] sTempParamsArray = new float[3];

    FolderPreviewItemAnim(PreviewItemManager itemManager, final PreviewItemDrawingParams params, int index0, int items0, int index1, int items1, int duration, final Runnable onCompleteRunnable) {
        this.mItemManager = itemManager;
        this.mParams = params;
        itemManager.computePreviewItemDrawingParams(index1, items1, sTmpParams);
        float[] fArr = {sTmpParams.scale, sTmpParams.transX, sTmpParams.transY};
        this.finalState = fArr;
        itemManager.computePreviewItemDrawingParams(index0, items0, sTmpParams);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this, (Property<FolderPreviewItemAnim, V>) PARAMS, (TypeEvaluator) new FloatArrayEvaluator(), (Object[]) new float[][]{new float[]{sTmpParams.scale, sTmpParams.transX, sTmpParams.transY}, fArr});
        this.mAnimator = objectAnimatorOfObject;
        objectAnimatorOfObject.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.FolderPreviewItemAnim.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Runnable runnable = onCompleteRunnable;
                if (runnable != null) {
                    runnable.run();
                }
                params.anim = null;
            }
        });
        objectAnimatorOfObject.setDuration(duration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParams(float[] values) {
        this.mParams.scale = values[0];
        this.mParams.transX = values[1];
        this.mParams.transY = values[2];
        this.mItemManager.onParamsChanged();
    }

    public void start() {
        this.mAnimator.start();
    }

    public void cancel() {
        this.mAnimator.cancel();
    }

    public boolean hasEqualFinalState(FolderPreviewItemAnim anim) {
        return Arrays.equals(this.finalState, anim.finalState);
    }
}
