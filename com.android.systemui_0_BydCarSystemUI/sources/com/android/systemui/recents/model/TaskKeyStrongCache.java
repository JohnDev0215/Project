package com.android.systemui.recents.model;

import android.util.ArrayMap;
import com.android.systemui.shared.recents.model.Task;
import com.android.systemui.shared.recents.model.TaskKeyCache;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class TaskKeyStrongCache<V> extends TaskKeyCache<V> {
    private static final String TAG = "TaskKeyCache";
    private final ArrayMap<Integer, V> mCache = new ArrayMap<>();

    public final void copyEntries(TaskKeyStrongCache<V> other) {
        for (int size = other.mKeys.size() - 1; size >= 0; size--) {
            Task.TaskKey taskKeyValueAt = other.mKeys.valueAt(size);
            put(taskKeyValueAt, other.mCache.get(Integer.valueOf(taskKeyValueAt.id)));
        }
    }

    public void dump(String prefix, PrintWriter writer) {
        String str = prefix + "  ";
        writer.print(prefix);
        writer.print(TAG);
        writer.print(" numEntries=");
        writer.print(this.mKeys.size());
        writer.println();
        int size = this.mKeys.size();
        for (int i = 0; i < size; i++) {
            writer.print(str);
            writer.println(this.mKeys.get(this.mKeys.keyAt(i)));
        }
    }

    @Override // com.android.systemui.shared.recents.model.TaskKeyCache
    protected V getCacheEntry(int id) {
        return this.mCache.get(Integer.valueOf(id));
    }

    @Override // com.android.systemui.shared.recents.model.TaskKeyCache
    protected void putCacheEntry(int id, V value) {
        this.mCache.put(Integer.valueOf(id), value);
    }

    @Override // com.android.systemui.shared.recents.model.TaskKeyCache
    protected void removeCacheEntry(int id) {
        this.mCache.remove(Integer.valueOf(id));
    }

    @Override // com.android.systemui.shared.recents.model.TaskKeyCache
    protected void evictAllCache() {
        this.mCache.clear();
    }
}
