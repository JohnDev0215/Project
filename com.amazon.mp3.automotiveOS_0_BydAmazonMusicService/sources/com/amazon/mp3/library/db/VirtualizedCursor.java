package com.amazon.mp3.library.db;

import android.database.AbstractWindowedCursor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class VirtualizedCursor extends AbstractWindowedCursor {
    private boolean mAllRowsCached;
    private OnRowsCachedListener mListener;

    public interface OnRowsCachedListener {
        void onAllRowsCached();
    }

    public abstract boolean rowCached(int position);

    public boolean allRowsCached() {
        return this.mAllRowsCached;
    }

    public final void setOnRowsCachedListener(OnRowsCachedListener listener) {
        this.mListener = listener;
    }

    protected final void onAllRowsCached() {
        if (this.mAllRowsCached) {
            return;
        }
        this.mAllRowsCached = true;
        OnRowsCachedListener onRowsCachedListener = this.mListener;
        if (onRowsCachedListener != null) {
            onRowsCachedListener.onAllRowsCached();
        }
    }
}
