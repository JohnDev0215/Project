package android.hardware.bydauto.time.tsimpl;

import android.hardware.IBYDAutoEvent;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.time.AbsBYDAutoTimeListener;
import android.util.ArraySet;

/* JADX INFO: loaded from: classes.dex */
public class TimeListenerHelper {
    private ArraySet<AbsBYDAutoTimeListener> mListenerArraySet = new ArraySet<>();

    public synchronized boolean registerListener(AbsBYDAutoTimeListener listener) {
        if (listener != null) {
            return this.mListenerArraySet.add(listener);
        }
        return false;
    }

    public synchronized boolean unregisterListener(AbsBYDAutoTimeListener listener) {
        if (listener != null) {
            return this.mListenerArraySet.remove(listener);
        }
        return false;
    }

    public synchronized int getListenerSize() {
        return this.mListenerArraySet.size();
    }

    public void notifyTimeChanged(int[] time) {
        ArraySet<AbsBYDAutoTimeListener> listenerArraySet = new ArraySet<>();
        synchronized (this) {
            listenerArraySet.addAll(this.mListenerArraySet);
        }
        for (AbsBYDAutoTimeListener listener : listenerArraySet) {
            listener.onTimeChanged(time);
        }
    }

    public void notifyTimeFormatChanged(int value) {
        ArraySet<AbsBYDAutoTimeListener> listenerArraySet = new ArraySet<>();
        synchronized (this) {
            listenerArraySet.addAll(this.mListenerArraySet);
        }
        for (AbsBYDAutoTimeListener listener : listenerArraySet) {
            listener.onTimeFormatChanged(value);
        }
    }

    public void notifyError(int errCode, String errMessage) {
        ArraySet<AbsBYDAutoTimeListener> listenerArraySet = new ArraySet<>();
        synchronized (this) {
            listenerArraySet.addAll(this.mListenerArraySet);
        }
        for (AbsBYDAutoTimeListener listener : listenerArraySet) {
            listener.onError(errCode, errMessage);
        }
    }

    public final void notifyDataChanged(IBYDAutoEvent event) {
        ArraySet<AbsBYDAutoTimeListener> listenerArraySet = new ArraySet<>();
        synchronized (this) {
            listenerArraySet.addAll(this.mListenerArraySet);
        }
        for (AbsBYDAutoTimeListener listener : listenerArraySet) {
            listener.onDataChanged(event);
        }
    }

    public void notifyDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        ArraySet<AbsBYDAutoTimeListener> listenerArraySet = new ArraySet<>();
        synchronized (this) {
            listenerArraySet.addAll(this.mListenerArraySet);
        }
        for (AbsBYDAutoTimeListener listener : listenerArraySet) {
            listener.onDataEventChanged(eventType, eventValue);
        }
    }
}
