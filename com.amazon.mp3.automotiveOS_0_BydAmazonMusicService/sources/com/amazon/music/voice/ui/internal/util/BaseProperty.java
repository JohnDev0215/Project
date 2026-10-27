package com.amazon.music.voice.ui.internal.util;

import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseProperty {
    private final List<OnChangedListener> changedListenerList = new ArrayList();

    public interface OnChangedListener {
        void onPropertyChanged();
    }

    protected BaseProperty() {
    }

    protected final void notifyChanged() {
        synchronized (this.changedListenerList) {
            for (int size = this.changedListenerList.size() - 1; size >= 0; size--) {
                this.changedListenerList.get(size).onPropertyChanged();
            }
        }
    }

    public final void addOnChangedListener(OnChangedListener onChangedListener) {
        Preconditions.notNull(onChangedListener, "listener == null");
        synchronized (this.changedListenerList) {
            this.changedListenerList.add(onChangedListener);
        }
    }
}
