package com.amazon.alexa.voice.superbowl.plugins.system;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.superbowl.plugins.SystemRepository;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseSystemRepository implements SystemRepository {
    private final List<SystemRepository.OnChangedListener> onChangedListenerList = new ArrayList();

    @Override // com.amazon.alexa.voice.superbowl.plugins.SystemRepository
    public final void addOnChangedListener(SystemRepository.OnChangedListener onChangedListener) {
        Preconditions.notNull(onChangedListener, "listener == null");
        synchronized (this.onChangedListenerList) {
            this.onChangedListenerList.add(onChangedListener);
        }
    }

    protected final void notifyEndpointChanged() {
        synchronized (this.onChangedListenerList) {
            for (int size = this.onChangedListenerList.size() - 1; size >= 0; size--) {
                this.onChangedListenerList.get(size).onEndpointChanged(this);
            }
        }
    }

    protected final void notifyLocaleChanged() {
        synchronized (this.onChangedListenerList) {
            for (int size = this.onChangedListenerList.size() - 1; size >= 0; size--) {
                this.onChangedListenerList.get(size).onLocaleChanged(this);
            }
        }
    }
}
