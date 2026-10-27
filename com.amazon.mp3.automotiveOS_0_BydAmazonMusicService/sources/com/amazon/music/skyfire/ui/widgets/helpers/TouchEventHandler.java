package com.amazon.music.skyfire.ui.widgets.helpers;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchEventHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/helpers/TouchEventHandler;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "gestureDetector", "Landroid/view/GestureDetector;", "wasFlungUp", "", "wasSingleTapped", "onSwipeDown", "", "onSwipeLeft", "onSwipeRight", "onSwipeUp", "onTouch", "view", "Landroid/view/View;", "motionEvent", "Landroid/view/MotionEvent;", "reset", "GestureListener", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class TouchEventHandler implements View.OnTouchListener {
    private final GestureDetector gestureDetector;
    private boolean wasFlungUp;
    private boolean wasSingleTapped;

    public void onSwipeDown() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public void onSwipeUp() {
    }

    public TouchEventHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.gestureDetector = new GestureDetector(context, new GestureListener(this));
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        if (motionEvent.getActionMasked() == 0) {
            reset();
        }
        return this.gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: renamed from: wasSingleTapped, reason: from getter */
    public boolean getWasSingleTapped() {
        return this.wasSingleTapped;
    }

    /* JADX INFO: renamed from: wasFlungUp, reason: from getter */
    public boolean getWasFlungUp() {
        return this.wasFlungUp;
    }

    private final void reset() {
        this.wasSingleTapped = false;
        this.wasFlungUp = false;
    }

    /* JADX INFO: compiled from: TouchEventHandler.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J(\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/helpers/TouchEventHandler$GestureListener;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "(Lcom/amazon/music/skyfire/ui/widgets/helpers/TouchEventHandler;)V", "onDown", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onFling", "startEvent", "triggeringEvent", "velocityX", "", "velocityY", "onSingleTapUp", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ TouchEventHandler this$0;

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            return true;
        }

        public GestureListener(TouchEventHandler this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent event) {
            Intrinsics.checkNotNullParameter(event, "event");
            this.this$0.wasSingleTapped = true;
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent startEvent, MotionEvent triggeringEvent, float velocityX, float velocityY) {
            Intrinsics.checkNotNullParameter(startEvent, "startEvent");
            Intrinsics.checkNotNullParameter(triggeringEvent, "triggeringEvent");
            if (triggeringEvent.getRawY() < startEvent.getRawY()) {
                this.this$0.wasFlungUp = true;
            }
            return true;
        }
    }
}
