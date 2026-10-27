package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    @Deprecated
    public Fragment b(Context context, String str, Bundle bundle) {
        return Fragment.X(context, str, bundle);
    }

    public abstract View e(int i);

    public abstract boolean f();
}
