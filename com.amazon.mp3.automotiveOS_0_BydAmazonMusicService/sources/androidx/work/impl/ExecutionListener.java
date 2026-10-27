package androidx.work.impl;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface ExecutionListener {
    void onExecuted(@NonNull String workSpecId, boolean needsReschedule);
}
