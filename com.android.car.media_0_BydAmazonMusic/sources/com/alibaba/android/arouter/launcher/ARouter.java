package com.alibaba.android.arouter.launcher;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.alibaba.android.arouter.core.InstrumentationHook;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.InitException;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.facade.service.InterceptorService;
import com.alibaba.android.arouter.facade.service.PathReplaceService;
import com.alibaba.android.arouter.facade.service.PretreatmentService;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.utils.TextUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import spi.c;
import spi.fd;
import spi.gd;
import spi.hd;

/* JADX INFO: loaded from: classes.dex */
public final class ARouter {
    public static final String AUTO_INJECT = "wmHzgD4lOj5o4241";
    public static final String RAW_URI = "NTeRQWvye18AkPd6G";
    private static volatile boolean hasInit = false;
    private static volatile ARouter instance;
    public static ILogger logger;

    private ARouter() {
    }

    @Deprecated
    public static void attachBaseContext() {
        ILogger iLogger = hd.a;
        Log.i("ARouter::", "ARouter start attachBaseContext");
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            declaredField.set(objInvoke, new InstrumentationHook());
            Log.i("ARouter::", "ARouter hook instrumentation success!");
        } catch (Exception e) {
            StringBuilder sbK = c.k("ARouter hook instrumentation failed! [");
            sbK.append(e.getMessage());
            sbK.append("]");
            Log.e("ARouter::", sbK.toString());
        }
    }

    @Deprecated
    public static boolean canAutoInject() {
        return hd.d;
    }

    public static boolean debuggable() {
        return hd.c;
    }

    @Deprecated
    public static synchronized void enableAutoInject() {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.d = true;
        }
    }

    public static ARouter getInstance() {
        if (!hasInit) {
            throw new InitException("ARouter::Init::Invoke init(context) first!");
        }
        if (instance == null) {
            synchronized (ARouter.class) {
                if (instance == null) {
                    instance = new ARouter();
                }
            }
        }
        return instance;
    }

    public static void init(Application application) {
        if (hasInit) {
            return;
        }
        ILogger iLogger = hd.a;
        logger = iLogger;
        iLogger.info("ARouter::", "ARouter init start.");
        synchronized (hd.class) {
            hd.i = application;
            LogisticsCenter.init(application, hd.g);
            hd.a.info("ARouter::", "ARouter init success!");
            hd.f = true;
            hd.h = new Handler(Looper.getMainLooper());
        }
        hasInit = true;
        if (hasInit) {
            hd.j = (InterceptorService) getInstance().build("/arouter/service/interceptor").navigation();
        }
        hd.a.info("ARouter::", "ARouter init over.");
    }

    public static boolean isMonitorMode() {
        return hd.b;
    }

    public static synchronized void monitorMode() {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.b = true;
            hd.a.info("ARouter::", "ARouter monitorMode on");
        }
    }

    public static synchronized void openDebug() {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.c = true;
            hd.a.info("ARouter::", "ARouter openDebug");
        }
    }

    public static synchronized void openLog() {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.a.showLog(true);
            hd.a.info("ARouter::", "ARouter openLog");
        }
    }

    public static synchronized void printStackTrace() {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.a.showStackTrace(true);
            hd.a.info("ARouter::", "ARouter printStackTrace");
        }
    }

    public static synchronized void setExecutor(ThreadPoolExecutor threadPoolExecutor) {
        ILogger iLogger = hd.a;
        synchronized (hd.class) {
            hd.g = threadPoolExecutor;
        }
    }

    public static void setLogger(ILogger iLogger) {
        ILogger iLogger2 = hd.a;
        if (iLogger != null) {
            hd.a = iLogger;
        }
    }

    public boolean addRouteGroup(IRouteGroup iRouteGroup) {
        hd hdVarE = hd.e();
        Objects.requireNonNull(hdVarE);
        if (iRouteGroup == null) {
            return false;
        }
        String str = null;
        try {
            HashMap map = new HashMap();
            iRouteGroup.loadInto(map);
            for (Map.Entry entry : map.entrySet()) {
                String strD = hdVarE.d((String) entry.getKey());
                RouteMeta routeMeta = (RouteMeta) entry.getValue();
                if (str == null) {
                    str = strD;
                }
                if (str != null && str.equals(strD) && str.equals(routeMeta.getGroup())) {
                }
                return false;
            }
            LogisticsCenter.addRouteGroupDynamic(str, iRouteGroup);
            hd.a.info("ARouter::", "Add route group [" + str + "] finish, " + map.size() + " new route meta.");
            return true;
        } catch (Exception e) {
            hd.a.error("ARouter::", "Add route group dynamic exception!", e);
            return false;
        }
    }

    public Postcard build(String str) {
        hd hdVarE = hd.e();
        Objects.requireNonNull(hdVarE);
        if (TextUtils.isEmpty(str)) {
            throw new HandlerException("ARouter::Parameter is invalid!");
        }
        PathReplaceService pathReplaceService = (PathReplaceService) getInstance().navigation(PathReplaceService.class);
        if (pathReplaceService != null) {
            str = pathReplaceService.forString(str);
        }
        return hdVarE.b(str, hdVarE.d(str), Boolean.TRUE);
    }

    public synchronized void destroy() {
        hd.c();
        hasInit = false;
    }

    public void inject(Object obj) {
        ILogger iLogger = hd.a;
        AutowiredService autowiredService = (AutowiredService) getInstance().build("/arouter/service/autowired").navigation();
        if (autowiredService != null) {
            autowiredService.autowire(obj);
        }
    }

    public <T> T navigation(Class<? extends T> cls) {
        Objects.requireNonNull(hd.e());
        try {
            Postcard postcardBuildProvider = LogisticsCenter.buildProvider(cls.getName());
            if (postcardBuildProvider == null) {
                postcardBuildProvider = LogisticsCenter.buildProvider(cls.getSimpleName());
            }
            if (postcardBuildProvider != null) {
                postcardBuildProvider.setContext(hd.i);
                LogisticsCenter.completion(postcardBuildProvider);
                return (T) postcardBuildProvider.getProvider();
            }
        } catch (NoRouteFoundException e) {
            hd.a.warning("ARouter::", e.getMessage());
        }
        return null;
    }

    @Deprecated
    public Postcard build(String str, String str2) {
        return hd.e().b(str, str2, Boolean.FALSE);
    }

    public Postcard build(Uri uri) {
        hd hdVarE = hd.e();
        Objects.requireNonNull(hdVarE);
        if (uri != null && !TextUtils.isEmpty(uri.toString())) {
            PathReplaceService pathReplaceService = (PathReplaceService) getInstance().navigation(PathReplaceService.class);
            if (pathReplaceService != null) {
                uri = pathReplaceService.forUri(uri);
            }
            return new Postcard(uri.getPath(), hdVarE.d(uri.getPath()), uri, null);
        }
        throw new HandlerException("ARouter::Parameter invalid!");
    }

    public Object navigation(Context context, Postcard postcard, int i, NavigationCallback navigationCallback) {
        hd hdVarE = hd.e();
        Objects.requireNonNull(hdVarE);
        PretreatmentService pretreatmentService = (PretreatmentService) getInstance().navigation(PretreatmentService.class);
        if (pretreatmentService == null || pretreatmentService.onPretreatment(context, postcard)) {
            postcard.setContext(context == null ? hd.i : context);
            try {
                LogisticsCenter.completion(postcard);
                if (navigationCallback != null) {
                    navigationCallback.onFound(postcard);
                }
                if (!postcard.isGreenChannel()) {
                    hd.j.doInterceptions(postcard, new gd(hdVarE, i, navigationCallback, postcard));
                } else {
                    return hdVarE.a(postcard, i, navigationCallback);
                }
            } catch (NoRouteFoundException e) {
                hd.a.warning("ARouter::", e.getMessage());
                if (hd.c) {
                    hdVarE.f(new fd(hdVarE, postcard));
                }
                if (navigationCallback != null) {
                    navigationCallback.onLost(postcard);
                } else {
                    DegradeService degradeService = (DegradeService) getInstance().navigation(DegradeService.class);
                    if (degradeService != null) {
                        degradeService.onLost(context, postcard);
                    }
                }
            }
        }
        return null;
    }
}
