package android.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import android.view.WindowManagerImpl;
import java.lang.ref.Reference;

/* JADX INFO: loaded from: classes.dex */
public class WindowContext extends ContextWrapper {
    private boolean mOwnsToken;
    private final WindowTokenClient mToken;
    private final WindowManagerImpl mWindowManager;
    private final IWindowManager mWms;

    public WindowContext(Context context, int i, Bundle bundle) {
        super(null);
        IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
        this.mWms = windowManagerService;
        WindowTokenClient windowTokenClient = new WindowTokenClient();
        this.mToken = windowTokenClient;
        ContextImpl contextImplCreateBaseWindowContext = createBaseWindowContext(context, windowTokenClient);
        attachBaseContext(contextImplCreateBaseWindowContext);
        contextImplCreateBaseWindowContext.setOuterContext(this);
        windowTokenClient.attachContext(this);
        WindowManagerImpl windowManagerImpl = new WindowManagerImpl(this);
        this.mWindowManager = windowManagerImpl;
        windowManagerImpl.setDefaultToken(windowTokenClient);
        try {
            int iAddWindowTokenWithOptions = windowManagerService.addWindowTokenWithOptions(windowTokenClient, i, getDisplayId(), bundle, getPackageName());
            if (iAddWindowTokenWithOptions == -12) {
                throw new UnsupportedOperationException("createWindowContext failed! Too many unused window contexts. Please see Context#createWindowContext documentation for detail.");
            }
            this.mOwnsToken = iAddWindowTokenWithOptions == 0;
            Reference.reachabilityFence(this);
        } catch (RemoteException e) {
            this.mOwnsToken = false;
            throw e.rethrowFromSystemServer();
        }
    }

    private static ContextImpl createBaseWindowContext(Context context, IBinder iBinder) {
        return ContextImpl.getImpl(context).createBaseWindowContext(iBinder);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (Context.WINDOW_SERVICE.equals(str)) {
            return this.mWindowManager;
        }
        return super.getSystemService(str);
    }

    protected void finalize() throws Throwable {
        release();
        super.finalize();
    }

    public void release() {
        if (this.mOwnsToken) {
            try {
                this.mWms.removeWindowToken(this.mToken, getDisplayId());
                this.mOwnsToken = false;
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        destroy();
    }

    void destroy() {
        ((ContextImpl) getBaseContext()).scheduleFinalCleanup(getClass().getName(), "WindowContext");
        Reference.reachabilityFence(this);
    }
}
