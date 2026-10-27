package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SwipeableViewPager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0007H\u0016R\"\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/prime/browse/pager/SwipeableViewPager;", "Landroidx/viewpager/widget/ViewPager;", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "", "onInterceptTouchEvent", "onTouchEvent", "Landroid/view/KeyEvent;", "executeKeyEvent", "swipeable", "Z", "getSwipeable", "()Z", "setSwipeable", "(Z)V", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SwipeableViewPager extends ViewPager {
    private boolean swipeable;

    public final boolean getSwipeable() {
        return this.swipeable;
    }

    public final void setSwipeable(boolean z) {
        this.swipeable = z;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.swipeable = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return this.swipeable && super.onInterceptTouchEvent(event);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return super.onTouchEvent(event) && this.swipeable;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public boolean executeKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return this.swipeable && super.executeKeyEvent(event);
    }
}
