package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class KeyframeAnimation<T> extends BaseKeyframeAnimation<T, T> {
    public KeyframeAnimation(List<? extends Keyframe<T>> list) {
        super(list);
    }
}
