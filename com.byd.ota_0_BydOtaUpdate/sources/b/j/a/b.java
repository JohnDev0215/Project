package b.j.a;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentContainer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    @Deprecated
    public Fragment d(Context context, String str, Bundle bundle) {
        return Fragment.L(context, str, bundle);
    }

    public abstract View e(int i2);

    public abstract boolean f();
}
