package android.app;

import android.app.IWindowToken;
import android.content.res.Configuration;
import android.view.WindowManagerGlobal;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class WindowTokenClient extends IWindowToken.Stub {
    private WeakReference<WindowContext> mContextRef = null;
    private final ResourcesManager mResourcesManager = ResourcesManager.getInstance();

    void attachContext(WindowContext windowContext) {
        if (this.mContextRef != null) {
            throw new IllegalStateException("Context is already attached.");
        }
        this.mContextRef = new WeakReference<>(windowContext);
        ContextImpl impl = ContextImpl.getImpl(windowContext);
        impl.setResources(impl.createWindowContextResources());
    }

    @Override // android.app.IWindowToken
    public void onConfigurationChanged(Configuration configuration, int i) {
        WindowContext windowContext = this.mContextRef.get();
        if (windowContext == null) {
            return;
        }
        boolean z = i != windowContext.getDisplayId();
        boolean z2 = windowContext.getResources().getConfiguration().diff(configuration) != 0;
        if (z || z2) {
            this.mResourcesManager.updateResourcesForActivity(this, configuration, i, z);
        }
        if (z) {
            windowContext.updateDisplay(i);
        }
    }

    @Override // android.app.IWindowToken
    public void onWindowTokenRemoved() {
        WindowContext windowContext = this.mContextRef.get();
        if (windowContext != null) {
            windowContext.destroy();
            this.mContextRef.clear();
        }
        WindowManagerGlobal.getInstance().closeAll(this, this.mContextRef.getClass().getName(), "WindowContext");
    }
}
