package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SingleWindowKeyboardVisibilityDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class vn2 extends p2 {
    public vn2(WeakReference<Activity> weakReference) {
        super(weakReference);
    }

    @Override // defpackage.t41
    public boolean f(Context context, View view) {
        if (xz.a(context) == null && view != null && (view.getContext() instanceof Activity)) {
        }
        View rootView = view.getRootView();
        return rootView != null && b(rootView) > 0;
    }
}
