package androidx.core.widget;

import android.content.ClipData;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class i<T extends View> {
    public abstract boolean a(@NonNull T t, @NonNull ClipData clipData, int i, int i2);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final void b(@Nullable InputConnection inputConnection, @Nullable EditorInfo editorInfo) {
        throw null;
    }
}
