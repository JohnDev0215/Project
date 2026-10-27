package com.amazon.avod.playback.player.actions;

/* JADX INFO: loaded from: classes2.dex */
public class ShutdownAction extends Action {
    final boolean mShouldRetainReusableComponents;

    public ShutdownAction(boolean z) {
        this.mShouldRetainReusableComponents = z;
    }

    public boolean shouldRetainReusableComponents() {
        return this.mShouldRetainReusableComponents;
    }

    @Override // com.amazon.avod.playback.player.actions.Action
    public ActionType getActionType() {
        return ActionType.Shutdown;
    }
}
