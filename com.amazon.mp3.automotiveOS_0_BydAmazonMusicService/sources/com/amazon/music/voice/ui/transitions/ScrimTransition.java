package com.amazon.music.voice.ui.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Parcel;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.amazon.music.voice.R$id;
import com.amazon.music.voice.ui.internal.util.DisplayUtils;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ScrimTransition extends AnimatorTransition {
    @NonNull
    protected abstract Animator createAlphaAnimator(@NonNull View view);

    @NonNull
    protected abstract Animator createTranslationAnimator(@NonNull View view, int i);

    protected abstract int getBodyAnimationDuration(int i);

    @Nullable
    protected abstract View selectView(@Nullable View view, @Nullable View view2);

    protected ScrimTransition() {
    }

    protected ScrimTransition(Parcel parcel) {
        super(parcel);
    }

    @Override // com.amazon.music.voice.ui.transitions.AnimatorTransition
    @Nullable
    protected Animator getAnimator(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2) {
        View viewSelectView = selectView(view, view2);
        if (viewSelectView == null) {
            return null;
        }
        View viewFindViewById = viewSelectView.findViewById(R$id.iv_alexa);
        View viewFindViewById2 = viewSelectView.findViewById(R$id.body_container);
        View viewFindViewById3 = viewSelectView.findViewById(R$id.chrome_border);
        View viewFindViewById4 = viewSelectView.findViewById(R$id.chrome);
        Animator animatorCreateAlphaAnimator = createAlphaAnimator(viewFindViewById);
        Animator animatorCreateAlphaAnimator2 = createAlphaAnimator(viewFindViewById2);
        Animator animatorCreateAlphaAnimator3 = createAlphaAnimator(viewFindViewById3);
        Animator animatorCreateAlphaAnimator4 = createAlphaAnimator(viewFindViewById4);
        int height = viewFindViewById2.getHeight() + viewFindViewById.getHeight();
        int bodyAnimationDuration = getBodyAnimationDuration(DisplayUtils.transformPxToDpi(viewSelectView.getContext(), height));
        Animator animatorCreateTranslationAnimator = createTranslationAnimator(viewFindViewById, height);
        Animator animatorCreateTranslationAnimator2 = createTranslationAnimator(viewFindViewById2, height);
        long j = bodyAnimationDuration;
        animatorCreateAlphaAnimator.setDuration(j);
        animatorCreateTranslationAnimator.setDuration(j);
        animatorCreateAlphaAnimator2.setDuration(j);
        animatorCreateTranslationAnimator2.setDuration(j);
        animatorCreateAlphaAnimator3.setDuration(300L);
        animatorCreateAlphaAnimator4.setDuration(300L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorCreateAlphaAnimator, animatorCreateTranslationAnimator, animatorCreateAlphaAnimator2, animatorCreateTranslationAnimator2, animatorCreateAlphaAnimator3, animatorCreateAlphaAnimator4);
        animatorSet.setInterpolator(new FastOutSlowInInterpolator());
        return animatorSet;
    }
}
