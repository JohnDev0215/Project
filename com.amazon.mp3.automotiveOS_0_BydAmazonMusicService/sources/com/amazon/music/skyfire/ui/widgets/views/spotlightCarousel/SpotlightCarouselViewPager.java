package com.amazon.music.skyfire.ui.widgets.views.spotlightCarousel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpotlightCarouselViewPager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/spotlightCarousel/SpotlightCarouselViewPager;", "Landroidx/viewpager/widget/ViewPager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "isPagingEnabled", "", "()Z", "setPagingEnabled", "(Z)V", "init", "", "onInterceptTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onTouchEvent", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpotlightCarouselViewPager extends ViewPager {
    private boolean isPagingEnabled;

    /* JADX INFO: renamed from: isPagingEnabled, reason: from getter */
    public final boolean getIsPagingEnabled() {
        return this.isPagingEnabled;
    }

    public final void setPagingEnabled(boolean z) {
        this.isPagingEnabled = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpotlightCarouselViewPager(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpotlightCarouselViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        new CarouselSlideScroller(context).applyScroller(this);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }
}
