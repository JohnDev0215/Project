package com.amazon.mp3.library.fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B#\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/fragment/LyricsDynamicBackgroundSubscriber;", "Lcom/amazon/mp3/dynamicbackground/DynamicBackgroundImagePublisher$DynamicBackgroundSubscriber;", "Landroid/graphics/Bitmap;", "bitmap", "", "onBackgroundImageUpdate", "onPauseBackgroundImageUpdate", "onResumeBackgroundImageUpdate", "Ljava/lang/ref/WeakReference;", "Landroid/widget/ImageView;", "backgroundImageView", "Ljava/lang/ref/WeakReference;", "getBackgroundImageView", "()Ljava/lang/ref/WeakReference;", "Landroid/content/res/Resources;", "resources", "getResources", "<init>", "(Ljava/lang/ref/WeakReference;Ljava/lang/ref/WeakReference;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsDynamicBackgroundSubscriber implements DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber {
    private final WeakReference<ImageView> backgroundImageView;
    private final WeakReference<Resources> resources;

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onPauseBackgroundImageUpdate() {
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onResumeBackgroundImageUpdate() {
    }

    public LyricsDynamicBackgroundSubscriber(WeakReference<ImageView> backgroundImageView, WeakReference<Resources> resources) {
        Intrinsics.checkNotNullParameter(backgroundImageView, "backgroundImageView");
        Intrinsics.checkNotNullParameter(resources, "resources");
        this.backgroundImageView = backgroundImageView;
        this.resources = resources;
    }

    public final WeakReference<Resources> getResources() {
        return this.resources;
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onBackgroundImageUpdate(Bitmap bitmap) {
        Resources resources;
        ImageView imageView = this.backgroundImageView.get();
        if (imageView == null || (resources = getResources().get()) == null || bitmap == null) {
            return;
        }
        imageView.setImageDrawable(new BitmapDrawable(resources, bitmap));
    }
}
