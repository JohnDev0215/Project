package android.car.cluster.renderer;

import android.annotation.SystemApi;
import android.content.Context;
import com.android.internal.annotations.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
@Deprecated
public abstract class InstrumentClusterRenderer {
    private final Object mLock = new Object();

    @GuardedBy({"mLock"})
    private NavigationRenderer mNavigationRenderer;

    protected abstract NavigationRenderer createNavigationRenderer();

    public NavigationRenderer getNavigationRenderer() {
        NavigationRenderer navigationRenderer;
        synchronized (this.mLock) {
            navigationRenderer = this.mNavigationRenderer;
        }
        return navigationRenderer;
    }

    public final void initialize() {
        synchronized (this.mLock) {
            this.mNavigationRenderer = createNavigationRenderer();
        }
    }

    public abstract void onCreate(Context context);

    public abstract void onStart();

    public abstract void onStop();
}
