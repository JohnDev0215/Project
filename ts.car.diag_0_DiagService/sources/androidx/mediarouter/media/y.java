package androidx.mediarouter.media;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.x;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(17)
final class y {

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final DisplayManager f1027c;
        private final Handler d;
        private Method e;
        private boolean f;

        public a(Context context, Handler handler) {
            if (Build.VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            this.f1027c = (DisplayManager) context.getSystemService("display");
            this.d = handler;
            try {
                this.e = DisplayManager.class.getMethod("scanWifiDisplays", new Class[0]);
            } catch (NoSuchMethodException unused) {
            }
        }

        public void a(int i) {
            if ((i & 2) == 0) {
                if (this.f) {
                    this.f = false;
                    this.d.removeCallbacks(this);
                    return;
                }
                return;
            }
            if (this.f) {
                return;
            }
            if (this.e == null) {
                Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays because the DisplayManager.scanWifiDisplays() method is not available on this device.");
            } else {
                this.f = true;
                this.d.post(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f) {
                try {
                    this.e.invoke(this.f1027c, new Object[0]);
                } catch (IllegalAccessException e) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", e);
                } catch (InvocationTargetException e2) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot scan for wifi displays.", e2);
                }
                this.d.postDelayed(this, 15000L);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b extends x.a {
        void a(Object obj);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    static class c<T extends b> extends x.b<T> {
        public c(T t) {
            super(t);
        }

        @Override // android.media.MediaRouter.Callback
        public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            ((b) this.f1023a).a(routeInfo);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Method f1028a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1029b;

        public d() {
            if (Build.VERSION.SDK_INT != 17) {
                throw new UnsupportedOperationException();
            }
            try {
                this.f1029b = MediaRouter.RouteInfo.class.getField("STATUS_CONNECTING").getInt(null);
                this.f1028a = MediaRouter.RouteInfo.class.getMethod("getStatusCode", new Class[0]);
            } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException unused) {
            }
        }

        public boolean a(Object obj) {
            MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) obj;
            Method method = this.f1028a;
            if (method == null) {
                return false;
            }
            try {
                return ((Integer) method.invoke(routeInfo, new Object[0])).intValue() == this.f1029b;
            } catch (IllegalAccessException | InvocationTargetException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class e {
        public static Display a(Object obj) {
            try {
                return ((MediaRouter.RouteInfo) obj).getPresentationDisplay();
            } catch (NoSuchMethodError e) {
                Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
                return null;
            }
        }

        public static boolean b(Object obj) {
            return ((MediaRouter.RouteInfo) obj).isEnabled();
        }
    }

    public static Object a(b bVar) {
        return new c(bVar);
    }
}
