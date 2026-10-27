package com.amazon.avod.playback.player;

import com.amazon.avod.playback.player.actions.Action;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackActionQueue {
    private final BlockingDeque<Action> mActionsQueue = new LinkedBlockingDeque();

    public void enqueueAction(@Nonnull Action action) {
        Preconditions.checkNotNull(action, "newAction");
        DLog.logf("Enqueue action: %s", action.getActionType());
        this.mActionsQueue.add(action);
    }

    public void enqueueActionToHead(@Nonnull Action action) {
        Preconditions.checkNotNull(action, "newAction");
        DLog.logf("Enqueue action to the head: %s", action.getActionType());
        this.mActionsQueue.addFirst(action);
    }

    public Action getNextAction() {
        Action actionPoll = this.mActionsQueue.poll();
        while (actionPoll != null) {
            Action actionPeek = this.mActionsQueue.peek();
            if (actionPeek == null || actionPeek.getActionType() != actionPoll.getActionType()) {
                break;
            }
            DLog.logf("Skipping action due to duplication: %s", actionPoll.getActionType());
            actionPoll = this.mActionsQueue.poll();
        }
        return actionPoll;
    }

    public void waitForNextAction() {
        try {
            this.mActionsQueue.addFirst(this.mActionsQueue.takeFirst());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            DLog.exceptionf(e, "Interrupted while waiting for the next action", new Object[0]);
        }
    }
}
