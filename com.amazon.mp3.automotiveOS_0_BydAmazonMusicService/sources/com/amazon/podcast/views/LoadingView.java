package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$anim;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;

/* JADX INFO: loaded from: classes5.dex */
public final class LoadingView extends RelativeLayout {
    private ImageView spinner;

    public LoadingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.refinement_loading_view, this);
        ImageView imageView = (ImageView) findViewById(R$id.refinement_loading_view_spinner);
        this.spinner = imageView;
        imageView.setColorFilter(Podcast.getRuntimeStyleSheet().getAccentColor());
        startSpinnerAnimation();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == 0) {
            startSpinnerAnimation();
        } else {
            stopSpinnerAnimation();
        }
        super.onWindowVisibilityChanged(visibility);
    }

    private void startSpinnerAnimation() {
        this.spinner.startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.podcast_loading_spinner));
    }

    private void stopSpinnerAnimation() {
        Animation animation = this.spinner.getAnimation();
        if (animation == null) {
            return;
        }
        animation.cancel();
    }
}
