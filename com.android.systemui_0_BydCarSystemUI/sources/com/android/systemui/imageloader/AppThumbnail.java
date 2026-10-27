package com.android.systemui.imageloader;

import com.android.systemui.shared.recents.model.Task;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AppThumbnail {
    private Task.TaskKey mTaskKey;

    public AppThumbnail(Task.TaskKey taskKey) {
        this.mTaskKey = taskKey;
    }

    public Task.TaskKey getTaskKey() {
        return this.mTaskKey;
    }

    public void setTaskKey(Task.TaskKey taskKey) {
        this.mTaskKey = taskKey;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return Objects.equals(this.mTaskKey, ((AppThumbnail) o).mTaskKey);
    }

    public int hashCode() {
        return Objects.hash(this.mTaskKey);
    }
}
