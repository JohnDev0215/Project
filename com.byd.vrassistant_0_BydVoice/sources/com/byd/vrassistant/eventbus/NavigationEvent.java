package com.byd.vrassistant.eventbus;

/* JADX INFO: loaded from: classes.dex */
public class NavigationEvent {
    private boolean isReadyForBinding;
    private int type;

    public NavigationEvent(boolean z, int i) {
        this.isReadyForBinding = z;
        this.type = i;
    }

    public boolean isReadyForBinding() {
        return this.isReadyForBinding;
    }

    public void setReadyForBinding(boolean z) {
        this.isReadyForBinding = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
