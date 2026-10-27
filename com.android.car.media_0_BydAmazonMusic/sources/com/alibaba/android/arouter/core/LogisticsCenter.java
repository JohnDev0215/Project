package com.alibaba.android.arouter.core;

import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptorGroup;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.ClassUtils;
import com.alibaba.android.arouter.utils.Consts;
import com.alibaba.android.arouter.utils.MapUtils;
import com.alibaba.android.arouter.utils.PackageUtils;
import com.alibaba.android.arouter.utils.TextUtils;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import spi.c;
import spi.zc;

/* JADX INFO: loaded from: classes.dex */
public class LogisticsCenter {
    public static ThreadPoolExecutor executor;
    private static Context mContext;
    private static boolean registerByPlugin;

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[RouteType.values().length];
            a = iArr;
            try {
                iArr[RouteType.PROVIDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[RouteType.FRAGMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    public static synchronized void addRouteGroupDynamic(String str, IRouteGroup iRouteGroup) {
        if (zc.a.containsKey(str)) {
            ((IRouteGroup) ((Class) zc.a.get(str)).getConstructor(new Class[0]).newInstance(new Object[0])).loadInto(zc.b);
            zc.a.remove(str);
        }
        if (iRouteGroup != null) {
            iRouteGroup.loadInto(zc.b);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta>] */
    public static Postcard buildProvider(String str) {
        RouteMeta routeMeta = (RouteMeta) zc.d.get(str);
        if (routeMeta == null) {
            return null;
        }
        return new Postcard(routeMeta.getPath(), routeMeta.getGroup());
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public static synchronized void completion(Postcard postcard) {
        if (postcard == null) {
            throw new NoRouteFoundException("ARouter::No postcard!");
        }
        RouteMeta routeMeta = zc.b.get(postcard.getPath());
        if (routeMeta != null) {
            postcard.setDestination(routeMeta.getDestination());
            postcard.setType(routeMeta.getType());
            postcard.setPriority(routeMeta.getPriority());
            postcard.setExtra(routeMeta.getExtra());
            Uri uri = postcard.getUri();
            if (uri != null) {
                Map<String, String> mapSplitQueryParameters = TextUtils.splitQueryParameters(uri);
                Map<String, Integer> paramsType = routeMeta.getParamsType();
                if (MapUtils.isNotEmpty(paramsType)) {
                    for (Map.Entry<String, Integer> entry : paramsType.entrySet()) {
                        setValue(postcard, entry.getValue(), entry.getKey(), mapSplitQueryParameters.get(entry.getKey()));
                    }
                    postcard.getExtras().putStringArray(ARouter.AUTO_INJECT, (String[]) paramsType.keySet().toArray(new String[0]));
                }
                postcard.withString(ARouter.RAW_URI, uri.toString());
            }
            int i = a.a[routeMeta.getType().ordinal()];
            if (i == 1) {
                Class<?> destination = routeMeta.getDestination();
                IProvider iProvider = zc.c.get(destination);
                if (iProvider == null) {
                    try {
                        iProvider = (IProvider) destination.getConstructor(new Class[0]).newInstance(new Object[0]);
                        iProvider.init(mContext);
                        zc.c.put(destination, iProvider);
                    } catch (Exception e) {
                        ARouter.logger.error("ARouter::", "Init provider failed!", e);
                        throw new HandlerException("Init provider failed!");
                    }
                }
                postcard.setProvider(iProvider);
                postcard.greenChannel();
            } else if (i == 2) {
                postcard.greenChannel();
            }
        } else {
            if (!zc.a.containsKey(postcard.getGroup())) {
                throw new NoRouteFoundException("ARouter::There is no route match the path [" + postcard.getPath() + "], in group [" + postcard.getGroup() + "]");
            }
            try {
                if (ARouter.debuggable()) {
                    ARouter.logger.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] starts loading, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                }
                addRouteGroupDynamic(postcard.getGroup(), null);
                if (ARouter.debuggable()) {
                    ARouter.logger.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] has already been loaded, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                }
                completion(postcard);
            } catch (Exception e2) {
                throw new HandlerException("ARouter::Fatal exception when loading group meta. [" + e2.getMessage() + "]");
            }
        }
    }

    /* JADX WARN: Type inference failed for: r5v11, types: [java.util.Map<java.lang.Integer, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IInterceptor>>, java.util.TreeMap] */
    /* JADX WARN: Type inference failed for: r5v14, types: [java.util.HashMap, java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta>] */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    public static synchronized void init(Context context, ThreadPoolExecutor threadPoolExecutor) {
        Set<String> fileNameByPackageName;
        mContext = context;
        executor = threadPoolExecutor;
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            loadRouterMap();
            if (registerByPlugin) {
                ARouter.logger.info("ARouter::", "Load router map by arouter-auto-register plugin.");
            } else {
                if (ARouter.debuggable() || PackageUtils.isNewVersion(context)) {
                    ARouter.logger.info("ARouter::", "Run with debug mode or new install, rebuild router map.");
                    fileNameByPackageName = ClassUtils.getFileNameByPackageName(mContext, Consts.ROUTE_ROOT_PAKCAGE);
                    if (!fileNameByPackageName.isEmpty()) {
                        context.getSharedPreferences(Consts.AROUTER_SP_CACHE_KEY, 0).edit().putStringSet(Consts.AROUTER_SP_KEY_MAP, fileNameByPackageName).apply();
                    }
                    PackageUtils.updateVersion(context);
                } else {
                    ARouter.logger.info("ARouter::", "Load router map from cache.");
                    fileNameByPackageName = new HashSet<>(context.getSharedPreferences(Consts.AROUTER_SP_CACHE_KEY, 0).getStringSet(Consts.AROUTER_SP_KEY_MAP, new HashSet()));
                }
                ARouter.logger.info("ARouter::", "Find router map finished, map size = " + fileNameByPackageName.size() + ", cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms.");
                jCurrentTimeMillis = System.currentTimeMillis();
                for (String str : fileNameByPackageName) {
                    if (str.startsWith("com.alibaba.android.arouter.routes.ARouter$$Root")) {
                        ((IRouteRoot) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0])).loadInto(zc.a);
                    } else if (str.startsWith("com.alibaba.android.arouter.routes.ARouter$$Interceptors")) {
                        ((IInterceptorGroup) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0])).loadInto(zc.e);
                    } else if (str.startsWith("com.alibaba.android.arouter.routes.ARouter$$Providers")) {
                        ((IProviderGroup) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0])).loadInto(zc.d);
                    }
                }
            }
            ARouter.logger.info("ARouter::", "Load root element finished, cost " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms.");
            if (zc.a.size() == 0) {
                ARouter.logger.error("ARouter::", "No mapping files were found, check your configuration please!");
            }
            if (ARouter.debuggable()) {
                ARouter.logger.debug("ARouter::", String.format(Locale.getDefault(), "LogisticsCenter has already been loaded, GroupIndex[%d], InterceptorIndex[%d], ProviderIndex[%d]", Integer.valueOf(zc.a.size()), Integer.valueOf(zc.e.size()), Integer.valueOf(zc.d.size())));
            }
        } catch (Exception e) {
            throw new HandlerException("ARouter::ARouter init logistics center exception! [" + e.getMessage() + "]");
        }
    }

    private static void loadRouterMap() {
        registerByPlugin = false;
    }

    private static void markRegisteredByPlugin() {
        if (registerByPlugin) {
            return;
        }
        registerByPlugin = true;
    }

    private static void register(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Object objNewInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (objNewInstance instanceof IRouteRoot) {
                registerRouteRoot((IRouteRoot) objNewInstance);
            } else if (objNewInstance instanceof IProviderGroup) {
                registerProvider((IProviderGroup) objNewInstance);
            } else if (objNewInstance instanceof IInterceptorGroup) {
                registerInterceptor((IInterceptorGroup) objNewInstance);
            } else {
                ARouter.logger.info("ARouter::", "register failed, class name: " + str + " should implements one of IRouteRoot/IProviderGroup/IInterceptorGroup.");
            }
        } catch (Exception e) {
            ARouter.logger.error("ARouter::", "register class error:" + str, e);
        }
    }

    private static void registerInterceptor(IInterceptorGroup iInterceptorGroup) {
        markRegisteredByPlugin();
        if (iInterceptorGroup != null) {
            iInterceptorGroup.loadInto(zc.e);
        }
    }

    private static void registerProvider(IProviderGroup iProviderGroup) {
        markRegisteredByPlugin();
        if (iProviderGroup != null) {
            iProviderGroup.loadInto(zc.d);
        }
    }

    private static void registerRouteRoot(IRouteRoot iRouteRoot) {
        markRegisteredByPlugin();
        if (iRouteRoot != null) {
            iRouteRoot.loadInto(zc.a);
        }
    }

    private static void setValue(Postcard postcard, Integer num, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (num == null) {
                postcard.withString(str, str2);
            } else if (num.intValue() == TypeKind.BOOLEAN.ordinal()) {
                postcard.withBoolean(str, Boolean.parseBoolean(str2));
            } else if (num.intValue() == TypeKind.BYTE.ordinal()) {
                postcard.withByte(str, Byte.parseByte(str2));
            } else if (num.intValue() == TypeKind.SHORT.ordinal()) {
                postcard.withShort(str, Short.parseShort(str2));
            } else if (num.intValue() == TypeKind.INT.ordinal()) {
                postcard.withInt(str, Integer.parseInt(str2));
            } else if (num.intValue() == TypeKind.LONG.ordinal()) {
                postcard.withLong(str, Long.parseLong(str2));
            } else if (num.intValue() == TypeKind.FLOAT.ordinal()) {
                postcard.withFloat(str, Float.parseFloat(str2));
            } else if (num.intValue() == TypeKind.DOUBLE.ordinal()) {
                postcard.withDouble(str, Double.parseDouble(str2));
            } else if (num.intValue() == TypeKind.STRING.ordinal()) {
                postcard.withString(str, str2);
            } else if (num.intValue() != TypeKind.PARCELABLE.ordinal()) {
                if (num.intValue() == TypeKind.OBJECT.ordinal()) {
                    postcard.withString(str, str2);
                } else {
                    postcard.withString(str, str2);
                }
            }
        } catch (Throwable th) {
            ILogger iLogger = ARouter.logger;
            StringBuilder sbK = c.k("LogisticsCenter setValue failed! ");
            sbK.append(th.getMessage());
            iLogger.warning("ARouter::", sbK.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashMap, java.util.Map<java.lang.Class, com.alibaba.android.arouter.facade.template.IProvider>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashMap, java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta>] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<com.alibaba.android.arouter.facade.template.IInterceptor>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.Map<java.lang.Integer, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IInterceptor>>, java.util.TreeMap] */
    public static void suspend() {
        zc.b.clear();
        zc.a.clear();
        zc.c.clear();
        zc.d.clear();
        zc.f.clear();
        zc.e.clear();
    }
}
