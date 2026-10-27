package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@18.0.1 */
/* JADX INFO: loaded from: classes6.dex */
public final class DuplicateTaskCompletionException extends IllegalStateException {
    private DuplicateTaskCompletionException(String str, @Nullable Throwable th) {
        super(str, th);
    }

    @NonNull
    public static IllegalStateException of(@NonNull Task<?> task) {
        if (!task.isComplete()) {
            return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
        }
        Exception exception = task.getException();
        String strConcat = exception != null ? "failure" : task.isSuccessful() ? "result ".concat(String.valueOf(task.getResult())) : task.isCanceled() ? "cancellation" : "unknown issue";
        return new DuplicateTaskCompletionException(strConcat.length() != 0 ? "Complete with: ".concat(strConcat) : new String("Complete with: "), exception);
    }
}
