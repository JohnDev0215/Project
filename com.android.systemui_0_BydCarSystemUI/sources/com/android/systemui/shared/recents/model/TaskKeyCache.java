package com.android.systemui.shared.recents.model;

import android.util.Log;
import android.util.SparseArray;
import com.android.systemui.shared.recents.model.Task;

/* JADX INFO: loaded from: classes.dex */
public abstract class TaskKeyCache<V> {
    protected static final String TAG = "TaskKeyCache";
    protected final SparseArray<Task.TaskKey> mKeys = new SparseArray<>();

    protected abstract void evictAllCache();

    protected abstract V getCacheEntry(int id);

    protected abstract void putCacheEntry(int id, V value);

    protected abstract void removeCacheEntry(int id);

    public final synchronized V get(Task.TaskKey key) {
        return getCacheEntry(key.id);
    }

    public final synchronized V getAndInvalidateIfModified(Task.TaskKey key) {
        Task.TaskKey taskKey = this.mKeys.get(key.id);
        if (taskKey != null && (taskKey.windowingMode != key.windowingMode || taskKey.lastActiveTime != key.lastActiveTime)) {
            remove(key);
            return null;
        }
        return getCacheEntry(key.id);
    }

    public final synchronized void put(Task.TaskKey key, V value) {
        if (key == null || value == null) {
            Log.e(TAG, "Unexpected null key or value: " + key + ", " + value);
        } else {
            this.mKeys.put(key.id, key);
            putCacheEntry(key.id, value);
        }
    }

    public final synchronized void remove(Task.TaskKey key) {
        removeCacheEntry(key.id);
        this.mKeys.remove(key.id);
    }

    public final synchronized void evictAll() {
        evictAllCache();
        this.mKeys.clear();
    }
}
