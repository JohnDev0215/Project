package com.amazon.music.explore.views.videoPreview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;

/* JADX INFO: loaded from: classes4.dex */
public class CoachMarkCircleView extends FrameLayout {
    private final double EXPANDED_LARGE_CIRCLE_RATIO;
    private final double LARGE_CIRCLE_RATIO;
    private final double MEDIUM_CIRCLE_RATIO;
    private final int PULSE_ANIMATION_DURATION;
    private final double SMALL_CIRCLE_RATIO;
    private final Context context;
    private ImageView largeCircle;
    private float screenWidth;

    public CoachMarkCircleView(Context context) {
        super(context);
        this.SMALL_CIRCLE_RATIO = 0.01449d;
        this.MEDIUM_CIRCLE_RATIO = 0.0628d;
        this.LARGE_CIRCLE_RATIO = 0.1256d;
        this.EXPANDED_LARGE_CIRCLE_RATIO = 0.13526d;
        this.PULSE_ANIMATION_DURATION = 1000;
        this.context = context;
        init();
    }

    public CoachMarkCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.SMALL_CIRCLE_RATIO = 0.01449d;
        this.MEDIUM_CIRCLE_RATIO = 0.0628d;
        this.LARGE_CIRCLE_RATIO = 0.1256d;
        this.EXPANDED_LARGE_CIRCLE_RATIO = 0.13526d;
        this.PULSE_ANIMATION_DURATION = 1000;
        this.context = context;
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.coach_mark_circle_layout, this);
        this.screenWidth = ScreenUtils.getScreenWidth(this.context);
        ImageView imageView = (ImageView) findViewById(R.id.coach_mark_large_circle);
        this.largeCircle = imageView;
        setCircleView(imageView, 0.13526d);
        setCircleView((ImageView) findViewById(R.id.coach_mark_medium_circle), 0.0628d);
        setCircleView((ImageView) findViewById(R.id.coach_mark_small_circle), 0.01449d);
        animateLargeCircle();
    }

    private void setCircleView(ImageView imageView, double d) {
        imageView.getLayoutParams().height = (int) (((double) this.screenWidth) * d);
        imageView.getLayoutParams().width = (int) (d * ((double) this.screenWidth));
    }

    private void animateLargeCircle() {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.largeCircle, PropertyValuesHolder.ofFloat("scaleX", 0.928582f), PropertyValuesHolder.ofFloat("scaleY", 0.928582f));
        objectAnimatorOfPropertyValuesHolder.setDuration(1000L);
        objectAnimatorOfPropertyValuesHolder.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimatorOfPropertyValuesHolder.setRepeatCount(-1);
        objectAnimatorOfPropertyValuesHolder.setRepeatMode(2);
        objectAnimatorOfPropertyValuesHolder.start();
    }
}
