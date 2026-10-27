package com.amazon.music.views.library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.amazon.music.views.library.R$anim;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import kotlin.Metadata;

/* JADX INFO: compiled from: OverlayLoadingView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001d\b\u0016\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/views/OverlayLoadingView;", "Landroid/widget/RelativeLayout;", "", "startSpinnerAnimation", "stopSpinnerAnimation", "", "visibility", "onWindowVisibilityChanged", "Landroid/widget/ImageView;", "spinner", "Landroid/widget/ImageView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class OverlayLoadingView extends RelativeLayout {
    private ImageView spinner;

    public OverlayLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View.inflate(getContext(), R$layout.dmmviewlibrary_overlay_loading_layout, this);
        this.spinner = (ImageView) findViewById(R$id.overlay_loading_view_spinner);
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

    private final void startSpinnerAnimation() {
        ImageView imageView = this.spinner;
        if (imageView == null) {
            return;
        }
        imageView.startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.dmmviewlibrary_overlay_loading_spinner));
    }

    private final void stopSpinnerAnimation() {
        ImageView imageView = this.spinner;
        Animation animation = imageView == null ? null : imageView.getAnimation();
        if (animation == null) {
            return;
        }
        animation.cancel();
    }
}
