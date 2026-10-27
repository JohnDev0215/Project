package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    @NonNull
    @Deprecated
    public Fragment f(@NonNull Context context, @NonNull String str, @Nullable Bundle bundle) {
        return Fragment.O(context, str, bundle);
    }

    @Nullable
    public abstract View g(@IdRes int i);

    public abstract boolean i();
}
