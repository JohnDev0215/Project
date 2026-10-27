package org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class ApplicationStatusManager {
    private static final String TOOLBAR_CALLBACK_INTERNAL_WRAPPER_CLASS = "androidx.appcompat.internal.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static final String TOOLBAR_CALLBACK_WRAPPER_CLASS = "androidx.appcompat.app.ToolbarActionBar$ToolbarCallbackWrapper";
    private static ObserverList<WindowFocusChangedListener> sWindowFocusListeners = new ObserverList<>();

    public interface WindowFocusChangedListener {
        void onWindowFocusChanged(Activity activity, boolean z);
    }

    private static class WindowCallbackProxy implements InvocationHandler {
        private final Activity mActivity;
        private final Window.Callback mCallback;

        public WindowCallbackProxy(Activity activity, Window.Callback callback) {
            this.mCallback = callback;
            this.mActivity = activity;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (method.getName().equals("onWindowFocusChanged") && objArr.length == 1) {
                Object obj2 = objArr[0];
                if (obj2 instanceof Boolean) {
                    onWindowFocusChanged(((Boolean) obj2).booleanValue());
                    return null;
                }
            }
            try {
                return method.invoke(this.mCallback, objArr);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof AbstractMethodError) {
                    throw e.getCause();
                }
                throw e;
            }
        }

        public void onWindowFocusChanged(boolean z) {
            this.mCallback.onWindowFocusChanged(z);
            Iterator it = ApplicationStatusManager.sWindowFocusListeners.iterator();
            while (it.hasNext()) {
                ((WindowFocusChangedListener) it.next()).onWindowFocusChanged(this.mActivity, z);
            }
        }
    }

    public static void init(Application application) {
        ApplicationStatus.initialize(application);
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: org.chromium.base.ApplicationStatusManager.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                ApplicationStatusManager.setWindowFocusChangedCallback(activity);
            }
        });
    }

    public static void registerWindowFocusChangedListener(WindowFocusChangedListener windowFocusChangedListener) {
        sWindowFocusListeners.addObserver(windowFocusChangedListener);
    }

    public static void unregisterWindowFocusChangedListener(WindowFocusChangedListener windowFocusChangedListener) {
        sWindowFocusListeners.removeObserver(windowFocusChangedListener);
    }

    public static void informActivityStarted(Activity activity) {
        setWindowFocusChangedCallback(activity);
        ApplicationStatus.informActivityStarted(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setWindowFocusChangedCallback(Activity activity) {
        activity.getWindow().setCallback((Window.Callback) Proxy.newProxyInstance(Window.Callback.class.getClassLoader(), new Class[]{Window.Callback.class}, new WindowCallbackProxy(activity, activity.getWindow().getCallback())));
    }
}
