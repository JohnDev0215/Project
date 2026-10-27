package com.byd.music.utils.binding;

/* JADX INFO: loaded from: classes5.dex */
public class ActionObserver<T> {
    private Listener listener;

    public interface Listener<T> {
        void onDoing(T t);
    }

    public void observe(Listener<T> listener) {
        this.listener = listener;
    }

    public void active() {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onDoing(null);
        }
    }

    public void setValue(T t) {
        Listener listener = this.listener;
        if (listener != null) {
            listener.onDoing(t);
        }
    }
}
