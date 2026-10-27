package com.byd.music.view.musicPlayingSlideView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class LineScaleWaveIndicator extends IndicatorController {
    public static final float SCALE = 1.0f;
    float[] scaleYFloats = {1.0f, 1.0f, 1.0f, 1.0f};

    @Override // com.byd.music.view.musicPlayingSlideView.IndicatorController
    public void draw(Canvas canvas, Paint paint, float[] fArr) {
        RectF rectF;
        float width = getWidth() / 12;
        float height = getHeight();
        int i = 0;
        while (i < 4) {
            canvas.save();
            int i2 = i + 1;
            canvas.translate((i2 * 2 * width) + (i * width), height);
            canvas.scale(1.0f, this.scaleYFloats[i]);
            if (fArr[i] == 1.0f) {
                rectF = new RectF((-getWidth()) / 8, -getHeight(), 0.0f, 0.0f);
            } else if (fArr[i] == 2.0f) {
                rectF = new RectF((-getWidth()) / 8, ((-getHeight()) * 2) / 3, 0.0f, 0.0f);
            } else if (fArr[i] == 3.0f) {
                rectF = new RectF((-getWidth()) / 8, (-getHeight()) / 3, 0.0f, 0.0f);
            } else {
                rectF = new RectF((-getWidth()) / 8, -getHeight(), 0.0f, 0.0f);
            }
            canvas.drawRoundRect(rectF, 6.0f, 6.0f, paint);
            canvas.restore();
            i = i2;
        }
    }

    @Override // com.byd.music.view.musicPlayingSlideView.IndicatorController
    public List<Animator> createAnimation(float[] fArr) {
        ArrayList arrayList = new ArrayList();
        for (final int i = 0; i < 4; i++) {
            ValueAnimator valueAnimatorOfFloat = fArr[i] == 1.0f ? ValueAnimator.ofFloat(1.0f, 0.333f, 1.0f) : fArr[i] == 2.0f ? ValueAnimator.ofFloat(1.0f, 0.5f, 1.5f, 1.0f) : fArr[i] == 3.0f ? ValueAnimator.ofFloat(1.0f, 3.0f, 1.0f) : ValueAnimator.ofFloat(1.0f, 0.3f, 1.0f);
            valueAnimatorOfFloat.setDuration(1200L);
            valueAnimatorOfFloat.setRepeatCount(-1);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.music.view.musicPlayingSlideView.-$$Lambda$LineScaleWaveIndicator$KKTiK9SCo7z7BjfWYF9SKePV-qA
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.lambda$createAnimation$0$LineScaleWaveIndicator(i, valueAnimator);
                }
            });
            valueAnimatorOfFloat.start();
            arrayList.add(valueAnimatorOfFloat);
        }
        return arrayList;
    }

    public /* synthetic */ void lambda$createAnimation$0$LineScaleWaveIndicator(int i, ValueAnimator valueAnimator) {
        this.scaleYFloats[i] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        postInvalidate();
    }
}
