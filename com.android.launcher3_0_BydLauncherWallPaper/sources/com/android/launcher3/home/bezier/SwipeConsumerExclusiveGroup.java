package com.android.launcher3.home.bezier;

import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeConsumerExclusiveGroup {
    private SwipeConsumer curSwipeConsumer;
    private List<SwipeConsumer> list;
    private boolean lockOther;
    private SimpleSwipeListener singleListener;
    private boolean smooth;

    public SwipeConsumerExclusiveGroup() {
        this.list = new LinkedList();
        this.lockOther = false;
        this.singleListener = new SimpleSwipeListener() { // from class: com.android.launcher3.home.bezier.SwipeConsumerExclusiveGroup.1
            @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
            public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                SwipeConsumerExclusiveGroup.this.markAsCurrent(consumer);
            }

            @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
            public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                if (consumer == SwipeConsumerExclusiveGroup.this.curSwipeConsumer) {
                    SwipeConsumerExclusiveGroup.this.markNoCurrent();
                }
            }
        };
        this.smooth = true;
    }

    public SwipeConsumerExclusiveGroup(boolean smooth) {
        this.list = new LinkedList();
        this.lockOther = false;
        this.singleListener = new SimpleSwipeListener() { // from class: com.android.launcher3.home.bezier.SwipeConsumerExclusiveGroup.1
            @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
            public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                SwipeConsumerExclusiveGroup.this.markAsCurrent(consumer);
            }

            @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
            public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                if (consumer == SwipeConsumerExclusiveGroup.this.curSwipeConsumer) {
                    SwipeConsumerExclusiveGroup.this.markNoCurrent();
                }
            }
        };
        this.smooth = smooth;
    }

    public void markNoCurrent() {
        SwipeConsumer swipeConsumer = this.curSwipeConsumer;
        if (swipeConsumer != null) {
            swipeConsumer.close(this.smooth);
            this.curSwipeConsumer = null;
        }
        if (this.lockOther) {
            for (SwipeConsumer swipeConsumer2 : this.list) {
                if (swipeConsumer2.isAllDirectionsLocked()) {
                    swipeConsumer2.unlockAllDirections();
                }
            }
        }
    }

    public void markAsCurrent(SwipeConsumer consumer) {
        markAsCurrent(consumer, this.smooth);
    }

    public void markAsCurrent(SwipeConsumer current, boolean smoothResetOrigin) {
        if (this.curSwipeConsumer == current) {
            return;
        }
        this.curSwipeConsumer = current;
        for (SwipeConsumer swipeConsumer : this.list) {
            if (swipeConsumer != this.curSwipeConsumer) {
                if (this.lockOther && !swipeConsumer.isAllDirectionsLocked()) {
                    swipeConsumer.lockAllDirections();
                }
                swipeConsumer.close(smoothResetOrigin);
            }
        }
    }

    public void add(SwipeConsumer consumer) {
        if (this.list.contains(consumer)) {
            return;
        }
        this.list.add(consumer);
        consumer.addListener(this.singleListener);
    }

    public void remove(SwipeConsumer consumer) {
        if (consumer != null) {
            this.list.remove(consumer);
            consumer.removeListener(this.singleListener);
        }
    }

    public void clear() {
        while (!this.list.isEmpty()) {
            SwipeConsumer swipeConsumerRemove = this.list.remove(0);
            if (swipeConsumerRemove != null) {
                swipeConsumerRemove.removeListener(this.singleListener);
            }
        }
    }

    public boolean isLockOther() {
        return this.lockOther;
    }

    public void setLockOther(boolean lockOther) {
        this.lockOther = lockOther;
    }

    public SwipeConsumer getCurSwipeConsumer() {
        return this.curSwipeConsumer;
    }

    public boolean isSmooth() {
        return this.smooth;
    }

    public void setSmooth(boolean smooth) {
        this.smooth = smooth;
    }
}
