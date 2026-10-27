package com.amazon.music.views.library.views;

import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.music.views.library.utils.AnimationUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayV2BaseView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"com/amazon/music/views/library/views/FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1", "Ljava/lang/Runnable;", "imageIndex", "", "getImageIndex", "()I", "setImageIndex", "(I)V", "run", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1 implements Runnable {
    final /* synthetic */ ImageView $background;
    final /* synthetic */ int $backgroundHeight;
    final /* synthetic */ int $backgroundWidth;
    final /* synthetic */ FrameLayout $foreGroundLayout;
    final /* synthetic */ ImageView $foreground;
    final /* synthetic */ List<String> $imageUrls;
    final /* synthetic */ int $pos;
    private int imageIndex;
    final /* synthetic */ FeaturePlayV2BaseView this$0;

    FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1(int i, ImageView imageView, FeaturePlayV2BaseView featurePlayV2BaseView, ImageView imageView2, List<String> list, int i2, int i3, FrameLayout frameLayout) {
        this.$pos = i;
        this.$foreground = imageView;
        this.this$0 = featurePlayV2BaseView;
        this.$background = imageView2;
        this.$imageUrls = list;
        this.$backgroundHeight = i2;
        this.$backgroundWidth = i3;
        this.$foreGroundLayout = frameLayout;
        this.imageIndex = i;
    }

    public final int getImageIndex() {
        return this.imageIndex;
    }

    public final void setImageIndex(int i) {
        this.imageIndex = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        final ImageView imageView = this.$foreground;
        final FeaturePlayV2BaseView featurePlayV2BaseView = this.this$0;
        final ImageView imageView2 = this.$background;
        final List<String> list = this.$imageUrls;
        final int i = this.$backgroundHeight;
        final int i2 = this.$backgroundWidth;
        final FrameLayout frameLayout = this.$foreGroundLayout;
        imageView.post(new Runnable() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1.m2410run$lambda1(featurePlayV2BaseView, imageView2, imageView, list, this, i, i2, frameLayout);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: run$lambda-1, reason: not valid java name */
    public static final void m2410run$lambda1(FeaturePlayV2BaseView this$0, final ImageView background, final ImageView foreground, List imageUrls, FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1 this$1, int i, int i2, FrameLayout foreGroundLayout) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(background, "$background");
        Intrinsics.checkNotNullParameter(foreground, "$foreground");
        Intrinsics.checkNotNullParameter(imageUrls, "$imageUrls");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(foreGroundLayout, "$foreGroundLayout");
        this$0.bindImagesForNormalScreen(background, foreground, (String) imageUrls.get(this$1.imageIndex), i, i2);
        AnimationUtil animationUtil = AnimationUtil.INSTANCE;
        animationUtil.fadeInAnimation(foreground, 1000L);
        animationUtil.fadeInAnimation(background, 1000L);
        animationUtil.panInAnimation(foreGroundLayout, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1.m2411run$lambda1$lambda0(foreground, background);
            }
        }, 4000L);
        this$1.imageIndex = (this$1.imageIndex + 2) % imageUrls.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: run$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2411run$lambda1$lambda0(ImageView foreground, ImageView background) {
        Intrinsics.checkNotNullParameter(foreground, "$foreground");
        Intrinsics.checkNotNullParameter(background, "$background");
        AnimationUtil animationUtil = AnimationUtil.INSTANCE;
        AnimationUtil.fadeOutImage$default(animationUtil, foreground, 1000L, null, 4, null);
        AnimationUtil.fadeOutImage$default(animationUtil, background, 1000L, null, 4, null);
    }
}
