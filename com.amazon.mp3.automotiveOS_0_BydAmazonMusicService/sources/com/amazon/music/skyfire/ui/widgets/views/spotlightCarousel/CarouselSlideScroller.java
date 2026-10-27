package com.amazon.music.skyfire.ui.widgets.views.spotlightCarousel;

import android.content.Context;
import android.view.animation.PathInterpolator;
import android.widget.Scroller;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselSlideScroller.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ(\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J0\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/spotlightCarousel/CarouselSlideScroller;", "Landroid/widget/Scroller;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applyScroller", "", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "startScroll", "startX", "", "startY", "dx", "dy", "duration", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselSlideScroller extends Scroller {
    private static final int DURATION = 300;
    private static final PathInterpolator STANDARD_ENTRANCE_INTERPOLATOR = new PathInterpolator(0.25f, 0.1f, 0.25f, 1.0f);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarouselSlideScroller(Context context) {
        super(context, STANDARD_ENTRANCE_INTERPOLATOR);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, 300);
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, 300);
    }

    public final void applyScroller(ViewPager viewPager) {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(viewPager, this);
        } catch (IllegalAccessException | NoSuchFieldException unused) {
        }
    }
}
