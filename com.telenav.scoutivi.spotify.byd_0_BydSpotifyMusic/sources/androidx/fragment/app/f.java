package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: compiled from: FragmentContainer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    @Deprecated
    public Fragment e(Context context, String str, Bundle bundle) {
        return Fragment.b0(context, str, bundle);
    }

    public abstract View f(int i7);

    public abstract boolean h();
}
