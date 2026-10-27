package androidx.appcompat.app;

import android.content.Context;
import android.view.Window;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SkinAppCompatDelegateImpl extends AppCompatDelegateImpl {
    public static final WeakHashMap q0 = new WeakHashMap();

    public SkinAppCompatDelegateImpl(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback, context);
    }

    @Override // androidx.appcompat.app.AppCompatDelegateImpl, androidx.appcompat.app.AppCompatDelegate
    public final void l() {
    }
}
