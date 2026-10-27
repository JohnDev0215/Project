package spi;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.InitException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.thread.DefaultPoolExecutor;
import com.alibaba.android.arouter.utils.DefaultLogger;
import com.alibaba.android.arouter.utils.TextUtils;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: _ARouter.java */
/* JADX INFO: loaded from: classes.dex */
public final class hd {
    public static ILogger a = new DefaultLogger("ARouter::");
    public static volatile boolean b = false;
    public static volatile boolean c = false;
    public static volatile boolean d = false;
    public static volatile hd e = null;
    public static volatile boolean f = false;
    public static volatile ThreadPoolExecutor g = DefaultPoolExecutor.getInstance();
    public static Handler h;
    public static Application i;
    public static InterceptorService j;

    /* JADX INFO: compiled from: _ARouter.java */
    public class a implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ Context b;
        public final /* synthetic */ Intent c;
        public final /* synthetic */ Postcard d;
        public final /* synthetic */ NavigationCallback e;

        public a(int i, Context context, Intent intent, Postcard postcard, NavigationCallback navigationCallback) {
            this.a = i;
            this.b = context;
            this.c = intent;
            this.d = postcard;
            this.e = navigationCallback;
        }

        @Override // java.lang.Runnable
        public final void run() {
            hd hdVar = hd.this;
            int i = this.a;
            Context context = this.b;
            Intent intent = this.c;
            Postcard postcard = this.d;
            NavigationCallback navigationCallback = this.e;
            ILogger iLogger = hd.a;
            Objects.requireNonNull(hdVar);
            if (i < 0) {
                ContextCompat.startActivity(context, intent, postcard.getOptionsBundle());
            } else if (context instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) context, intent, i, postcard.getOptionsBundle());
            } else {
                hd.a.warning("ARouter::", "Must use [navigation(activity, ...)] to support [startActivityForResult]");
            }
            if (-1 != postcard.getEnterAnim() && -1 != postcard.getExitAnim() && (context instanceof Activity)) {
                ((Activity) context).overridePendingTransition(postcard.getEnterAnim(), postcard.getExitAnim());
            }
            if (navigationCallback != null) {
                navigationCallback.onArrival(postcard);
            }
        }
    }

    /* JADX INFO: compiled from: _ARouter.java */
    public static /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[RouteType.values().length];
            a = iArr;
            try {
                iArr[RouteType.ACTIVITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[RouteType.PROVIDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[RouteType.BOARDCAST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[RouteType.CONTENT_PROVIDER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[RouteType.FRAGMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[RouteType.METHOD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[RouteType.SERVICE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private hd() {
    }

    public static synchronized void c() {
        if (c) {
            f = false;
            LogisticsCenter.suspend();
            a.info("ARouter::", "ARouter destroy success!");
        } else {
            a.error("ARouter::", "Destroy can be used in debug mode only!");
        }
    }

    public static hd e() {
        if (!f) {
            throw new InitException("ARouterCore::Init::Invoke init(context) first!");
        }
        if (e == null) {
            synchronized (hd.class) {
                if (e == null) {
                    e = new hd();
                }
            }
        }
        return e;
    }

    public final Object a(Postcard postcard, int i2, NavigationCallback navigationCallback) {
        Context context = postcard.getContext();
        int i3 = b.a[postcard.getType().ordinal()];
        if (i3 == 1) {
            Intent intent = new Intent(context, postcard.getDestination());
            intent.putExtras(postcard.getExtras());
            int flags = postcard.getFlags();
            if (flags != 0) {
                intent.setFlags(flags);
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            String action = postcard.getAction();
            if (!TextUtils.isEmpty(action)) {
                intent.setAction(action);
            }
            f(new a(i2, context, intent, postcard, navigationCallback));
            return null;
        }
        if (i3 == 2) {
            return postcard.getProvider();
        }
        if (i3 == 3 || i3 == 4 || i3 == 5) {
            try {
                Object objNewInstance = postcard.getDestination().getConstructor(new Class[0]).newInstance(new Object[0]);
                if (objNewInstance instanceof Fragment) {
                    ((Fragment) objNewInstance).setArguments(postcard.getExtras());
                } else if (objNewInstance instanceof androidx.fragment.app.Fragment) {
                    ((androidx.fragment.app.Fragment) objNewInstance).setArguments(postcard.getExtras());
                }
                return objNewInstance;
            } catch (Exception e2) {
                ILogger iLogger = a;
                StringBuilder sbK = c.k("Fetch fragment instance error, ");
                sbK.append(TextUtils.formatStackTrace(e2.getStackTrace()));
                iLogger.error("ARouter::", sbK.toString());
            }
        }
        return null;
    }

    public final Postcard b(String str, String str2, Boolean bool) {
        PathReplaceService pathReplaceService;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new HandlerException("ARouter::Parameter is invalid!");
        }
        if (!bool.booleanValue() && (pathReplaceService = (PathReplaceService) ARouter.getInstance().navigation(PathReplaceService.class)) != null) {
            str = pathReplaceService.forString(str);
        }
        return new Postcard(str, str2);
    }

    public final String d(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/")) {
            throw new HandlerException("ARouter::Extract the default group failed, the path must be start with '/' and contain more than 2 '/'!");
        }
        try {
            String strSubstring = str.substring(1, str.indexOf("/", 1));
            if (TextUtils.isEmpty(strSubstring)) {
                throw new HandlerException("ARouter::Extract the default group failed! There's nothing between 2 '/'!");
            }
            return strSubstring;
        } catch (Exception e2) {
            ILogger iLogger = a;
            StringBuilder sbK = c.k("Failed to extract default group! ");
            sbK.append(e2.getMessage());
            iLogger.warning("ARouter::", sbK.toString());
            return null;
        }
    }

    public final void f(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            h.post(runnable);
        } else {
            runnable.run();
        }
    }
}
