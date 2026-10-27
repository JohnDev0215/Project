package com.android.launcher3.home.bezier;

/* JADX INFO: loaded from: classes4.dex */
public interface SwipeListener {
    void onConsumerAttachedToWrapper(SmartSwipeWrapper wrapper, SwipeConsumer consumer);

    void onConsumerDetachedFromWrapper(SmartSwipeWrapper wrapper, SwipeConsumer consumer);

    void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction);

    void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction);

    void onSwipeProcess(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction, boolean settling, float progress);

    void onSwipeRelease(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction, float progress, float xVelocity, float yVelocity);

    void onSwipeStart(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction);

    void onSwipeStateChanged(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int state, int direction, float progress);
}
