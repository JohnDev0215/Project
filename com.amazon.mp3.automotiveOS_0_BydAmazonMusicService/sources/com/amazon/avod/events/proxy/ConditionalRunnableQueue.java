package com.amazon.avod.events.proxy;

import com.google.common.collect.Lists;
import java.util.Queue;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ConditionalRunnableQueue {
    private boolean mIsConditionMet = false;
    private boolean mIsShutDown = false;
    private final Queue<Runnable> mActions = Lists.newLinkedList();

    public void setConditionMet() {
        synchronized (this.mActions) {
            if (!this.mIsConditionMet && !this.mIsShutDown) {
                this.mIsConditionMet = true;
                while (!this.mActions.isEmpty()) {
                    this.mActions.poll().run();
                }
            }
        }
    }

    public void addAction(Runnable runnable) {
        synchronized (this.mActions) {
            if (this.mIsShutDown) {
                return;
            }
            if (this.mIsConditionMet) {
                runnable.run();
            } else {
                this.mActions.add(runnable);
            }
        }
    }
}
