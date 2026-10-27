package com.byd.viewsup.animation;

import android.view.View;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.byd.viewsup.R;

/* JADX INFO: loaded from: classes5.dex */
public class BydAnimationHelper {
    private static final float DAMPING_VALUE = 0.25f;
    public static final int SHAKE_TO_BOTTOM = 2;
    public static final int SHAKE_TO_LEFT = -1;
    public static final int SHAKE_TO_RIGHT = 0;
    public static final int SHAKE_TO_TOP = 1;
    private static final float STIFFNESS_VALUE = 250.0f;

    public static SpringAnimation getViewShakeAnimation(View view, int i) {
        if (view == null) {
            return null;
        }
        float dimension = view.getContext().getResources().getDimension(R.dimen.byd_spring_anim_positive_start_velocity);
        float dimension2 = view.getContext().getResources().getDimension(R.dimen.byd_spring_anim_negative_start_velocity);
        if (i == -1) {
            SpringAnimation springAnimation = new SpringAnimation(view, SpringAnimation.TRANSLATION_X, 0.0f);
            springAnimation.getSpring().setStiffness(STIFFNESS_VALUE);
            springAnimation.getSpring().setDampingRatio(0.25f);
            springAnimation.setStartVelocity(dimension2);
            return springAnimation;
        }
        if (i == 0) {
            SpringAnimation springAnimation2 = new SpringAnimation(view, SpringAnimation.TRANSLATION_X, 0.0f);
            springAnimation2.getSpring().setStiffness(STIFFNESS_VALUE);
            springAnimation2.getSpring().setDampingRatio(0.25f);
            springAnimation2.setStartVelocity(dimension);
            return springAnimation2;
        }
        if (i == 1) {
            SpringAnimation springAnimation3 = new SpringAnimation(view, SpringAnimation.TRANSLATION_Y, 0.0f);
            springAnimation3.getSpring().setStiffness(STIFFNESS_VALUE);
            springAnimation3.getSpring().setDampingRatio(0.25f);
            springAnimation3.setStartVelocity(dimension2);
            return springAnimation3;
        }
        if (i == 2) {
            SpringAnimation springAnimation4 = new SpringAnimation(view, SpringAnimation.TRANSLATION_Y, 0.0f);
            springAnimation4.getSpring().setStiffness(STIFFNESS_VALUE);
            springAnimation4.getSpring().setDampingRatio(0.25f);
            springAnimation4.setStartVelocity(dimension);
            return springAnimation4;
        }
        return new SpringAnimation(view, SpringAnimation.TRANSLATION_X, 0.0f);
    }
}
