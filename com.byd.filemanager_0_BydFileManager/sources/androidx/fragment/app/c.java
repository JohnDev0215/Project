package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: compiled from: FragmentContainer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    @Deprecated
    public Fragment b(Context context, String str, Bundle bundle) {
        return Fragment.W(context, str, bundle);
    }

    public abstract View e(int i);

    public abstract boolean f();
}
