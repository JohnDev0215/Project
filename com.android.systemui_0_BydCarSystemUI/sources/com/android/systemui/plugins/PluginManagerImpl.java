package com.android.systemui.plugins;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Toast;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.PluginInstanceManager;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import com.android.systemui.util.SystemUIException;
import com.byd.mediacenter.api.media.MediaControl;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import dalvik.system.PathClassLoader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class PluginManagerImpl extends BroadcastReceiver implements PluginManager {
    static final String DISABLE_PLUGIN = "com.android.systemui.action.DISABLE_PLUGIN";
    private static PluginManager sInstance;
    private final boolean isDebuggable;
    private final Map<String, ClassLoader> mClassLoaders;
    private final Context mContext;
    private final PluginInstanceManagerFactory mFactory;
    private boolean mHasOneShot;
    private boolean mListening;
    private Looper mLooper;
    private final ArraySet<String> mOneShotPackages;
    private ClassLoaderFilter mParentClassLoader;
    private final ArrayMap<PluginListener<?>, PluginInstanceManager> mPluginMap;
    private final PluginPrefs mPluginPrefs;
    private boolean mWtfsSet;

    static /* synthetic */ void lambda$new$0() {
    }

    public PluginManagerImpl(Context context) {
        this(context, new PluginInstanceManagerFactory(), Build.IS_DEBUGGABLE, Thread.getDefaultUncaughtExceptionHandler());
    }

    PluginManagerImpl(Context context, PluginInstanceManagerFactory factory, boolean debuggable, Thread.UncaughtExceptionHandler defaultHandler) {
        this.mPluginMap = new ArrayMap<>();
        this.mClassLoaders = new ArrayMap();
        this.mOneShotPackages = new ArraySet<>();
        this.mContext = context;
        this.mFactory = factory;
        this.mLooper = (Looper) Dependency.get(Dependency.BG_LOOPER);
        this.isDebuggable = debuggable;
        this.mPluginPrefs = new PluginPrefs(context);
        Thread.setDefaultUncaughtExceptionHandler(new PluginExceptionHandler(defaultHandler));
        if (debuggable) {
            new Handler(this.mLooper).post(new Runnable() { // from class: com.android.systemui.plugins.-$$Lambda$PluginManagerImpl$7nBWfDWJobhWwYx5_W_NTtShb6o
                @Override // java.lang.Runnable
                public final void run() {
                    PluginManagerImpl.lambda$new$0();
                }
            });
        }
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> T getOneShotPlugin(Class<T> cls) {
        ProvidesInterface providesInterface = (ProvidesInterface) cls.getDeclaredAnnotation(ProvidesInterface.class);
        if (providesInterface == null) {
            throw new SystemUIException(cls + " doesn't provide an interface");
        }
        if (TextUtils.isEmpty(providesInterface.action())) {
            throw new SystemUIException(cls + " doesn't provide an action");
        }
        return (T) getOneShotPlugin(providesInterface.action(), cls);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> T getOneShotPlugin(String action, Class<?> cls) {
        if (!this.isDebuggable) {
            return null;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new SystemUIException("Must be called from UI thread");
        }
        PluginInstanceManager pluginInstanceManagerCreatePluginInstanceManager = this.mFactory.createPluginInstanceManager(this.mContext, action, null, false, this.mLooper, cls, this);
        this.mPluginPrefs.addAction(action);
        PluginInstanceManager.PluginInfo<T> plugin = pluginInstanceManagerCreatePluginInstanceManager.getPlugin();
        if (plugin == null) {
            return null;
        }
        this.mOneShotPackages.add(plugin.mPackage);
        this.mHasOneShot = true;
        startListening();
        return plugin.mPlugin;
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> void addPluginListener(PluginListener<T> listener, Class<?> cls) {
        addPluginListener((PluginListener) listener, cls, false);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> void addPluginListener(PluginListener<T> listener, Class<?> cls, boolean allowMultiple) {
        addPluginListener(PluginManager.getAction(cls), listener, cls, allowMultiple);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> void addPluginListener(String action, PluginListener<T> listener, Class<?> cls) {
        addPluginListener(action, listener, cls, false);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T extends Plugin> void addPluginListener(String action, PluginListener<T> listener, Class cls, boolean allowMultiple) {
        if (this.isDebuggable) {
            this.mPluginPrefs.addAction(action);
            PluginInstanceManager pluginInstanceManagerCreatePluginInstanceManager = this.mFactory.createPluginInstanceManager(this.mContext, action, listener, allowMultiple, this.mLooper, cls, this);
            pluginInstanceManagerCreatePluginInstanceManager.loadAll();
            this.mPluginMap.put(listener, pluginInstanceManagerCreatePluginInstanceManager);
            startListening();
        }
    }

    @Override // com.android.systemui.plugins.PluginManager
    public void removePluginListener(PluginListener<?> listener) {
        if (this.isDebuggable && this.mPluginMap.containsKey(listener)) {
            this.mPluginMap.remove(listener).destroy();
            if (this.mPluginMap.size() == 0) {
                stopListening();
            }
        }
    }

    private void startListening() {
        if (this.mListening) {
            return;
        }
        this.mListening = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction(PluginManager.PLUGIN_CHANGED);
        intentFilter.addAction(DISABLE_PLUGIN);
        intentFilter.addDataScheme(MediaControl.PARAM_PACKAGE);
        this.mContext.registerReceiver(this, intentFilter);
        this.mContext.registerReceiver(this, new IntentFilter("android.intent.action.USER_UNLOCKED"));
    }

    private void stopListening() {
        if (!this.mListening || this.mHasOneShot) {
            return;
        }
        this.mListening = false;
        this.mContext.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String string;
        if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction())) {
            Iterator<PluginInstanceManager> it = this.mPluginMap.values().iterator();
            while (it.hasNext()) {
                it.next().loadAll();
            }
            return;
        }
        if (DISABLE_PLUGIN.equals(intent.getAction())) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(intent.getData().toString().substring(10));
            this.mContext.getPackageManager().setComponentEnabledSetting(componentNameUnflattenFromString, 2, 1);
            ((NotificationManager) this.mContext.getSystemService(NotificationManager.class)).cancel(componentNameUnflattenFromString.getClassName(), 6);
            return;
        }
        String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
        if (this.mOneShotPackages.contains(encodedSchemeSpecificPart)) {
            int identifier = this.mContext.getResources().getIdentifier("tuner", AttrFactory.DRAWABLE, this.mContext.getPackageName());
            int identifier2 = Resources.getSystem().getIdentifier("system_notification_accent_color", "color", "android");
            try {
                PackageManager packageManager = this.mContext.getPackageManager();
                string = packageManager.getApplicationInfo(encodedSchemeSpecificPart, 0).loadLabel(packageManager).toString();
            } catch (PackageManager.NameNotFoundException unused) {
                string = encodedSchemeSpecificPart;
            }
            Notification.Builder contentText = new Notification.Builder(this.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setSmallIcon(identifier).setWhen(0L).setShowWhen(false).setPriority(2).setVisibility(1).setColor(this.mContext.getColor(identifier2)).setContentTitle("Plugin \"" + string + "\" has updated").setContentText("Restart SysUI for changes to take effect.");
            contentText.addAction(new Notification.Action.Builder((Icon) null, "Restart SysUI", PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.systemui.action.RESTART").setData(Uri.parse("package://" + encodedSchemeSpecificPart)), 0)).build());
            ((NotificationManager) this.mContext.getSystemService(NotificationManager.class)).notifyAsUser(encodedSchemeSpecificPart, 6, contentText.build(), UserHandle.ALL);
        }
        if (clearClassLoader(encodedSchemeSpecificPart)) {
            Toast.makeText(this.mContext, "Reloading " + encodedSchemeSpecificPart, 1).show();
        }
        if (!"android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            Iterator<PluginInstanceManager> it2 = this.mPluginMap.values().iterator();
            while (it2.hasNext()) {
                it2.next().onPackageChange(encodedSchemeSpecificPart);
            }
        } else {
            Iterator<PluginInstanceManager> it3 = this.mPluginMap.values().iterator();
            while (it3.hasNext()) {
                it3.next().onPackageRemoved(encodedSchemeSpecificPart);
            }
        }
    }

    public ClassLoader getClassLoader(String sourceDir, String pkg) {
        if (this.mClassLoaders.containsKey(pkg)) {
            return this.mClassLoaders.get(pkg);
        }
        PathClassLoader pathClassLoader = new PathClassLoader(sourceDir, getParentClassLoader());
        this.mClassLoaders.put(pkg, pathClassLoader);
        return pathClassLoader;
    }

    private boolean clearClassLoader(String pkg) {
        return this.mClassLoaders.remove(pkg) != null;
    }

    ClassLoader getParentClassLoader() {
        if (this.mParentClassLoader == null) {
            this.mParentClassLoader = new ClassLoaderFilter(getClass().getClassLoader(), "com.android.systemui.plugin");
        }
        return this.mParentClassLoader;
    }

    public Context getContext(ApplicationInfo info, String pkg) throws PackageManager.NameNotFoundException {
        return new PluginInstanceManager.PluginContextWrapper(this.mContext.createApplicationContext(info, 0), getClassLoader(info.sourceDir, pkg));
    }

    @Override // com.android.systemui.plugins.PluginManager
    public <T> boolean dependsOn(Plugin p, Class<T> cls) {
        for (int i = 0; i < this.mPluginMap.size(); i++) {
            if (this.mPluginMap.valueAt(i).dependsOn(p, cls)) {
                return true;
            }
        }
        return false;
    }

    public void handleWtfs() {
        if (this.mWtfsSet) {
            return;
        }
        this.mWtfsSet = true;
        Log.setWtfHandler(new Log.TerribleFailureHandler() { // from class: com.android.systemui.plugins.-$$Lambda$PluginManagerImpl$NVkpiyw681j54HQ8fZsDhuKpFk8
            public final void onTerribleFailure(String str, Log.TerribleFailure terribleFailure, boolean z) {
                this.f$0.lambda$handleWtfs$1$PluginManagerImpl(str, terribleFailure, z);
            }
        });
    }

    public /* synthetic */ void lambda$handleWtfs$1$PluginManagerImpl(String str, Log.TerribleFailure terribleFailure, boolean z) {
        throw new CrashWhilePluginActiveException(terribleFailure);
    }

    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println(String.format("  plugin map (%d):", Integer.valueOf(this.mPluginMap.size())));
        for (PluginListener<?> pluginListener : this.mPluginMap.keySet()) {
            pw.println(String.format("    %s -> %s", pluginListener, this.mPluginMap.get(pluginListener)));
        }
    }

    public static class PluginInstanceManagerFactory {
        public <T extends Plugin> PluginInstanceManager createPluginInstanceManager(Context context, String action, PluginListener<T> listener, boolean allowMultiple, Looper looper, Class<?> cls, PluginManagerImpl manager) {
            return new PluginInstanceManager(context, action, listener, allowMultiple, looper, new VersionInfo().addClass(cls), manager);
        }
    }

    private static class ClassLoaderFilter extends ClassLoader {
        private final ClassLoader mBase;
        private final String mPackage;

        public ClassLoaderFilter(ClassLoader base, String pkg) {
            super(ClassLoader.getSystemClassLoader());
            this.mBase = base;
            this.mPackage = pkg;
        }

        @Override // java.lang.ClassLoader
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (!name.startsWith(this.mPackage)) {
                super.loadClass(name, resolve);
            }
            return this.mBase.loadClass(name);
        }
    }

    private class PluginExceptionHandler implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler mHandler;

        private PluginExceptionHandler(Thread.UncaughtExceptionHandler handler) {
            this.mHandler = handler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable throwable) {
            if (SystemProperties.getBoolean("plugin.debugging", false)) {
                this.mHandler.uncaughtException(thread, throwable);
                return;
            }
            boolean zCheckStack = checkStack(throwable);
            if (!zCheckStack) {
                Iterator it = PluginManagerImpl.this.mPluginMap.values().iterator();
                while (it.hasNext()) {
                    zCheckStack |= ((PluginInstanceManager) it.next()).disableAll();
                }
            }
            if (zCheckStack) {
                throwable = PluginManagerImpl.this.new CrashWhilePluginActiveException(throwable);
            }
            this.mHandler.uncaughtException(thread, throwable);
        }

        private boolean checkStack(Throwable throwable) {
            if (throwable == null) {
                return false;
            }
            boolean zCheckAndDisable = false;
            for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
                Iterator it = PluginManagerImpl.this.mPluginMap.values().iterator();
                while (it.hasNext()) {
                    zCheckAndDisable |= ((PluginInstanceManager) it.next()).checkAndDisable(stackTraceElement.getClassName());
                }
            }
            return checkStack(throwable.getCause()) | zCheckAndDisable;
        }
    }

    private class CrashWhilePluginActiveException extends RuntimeException {
        public CrashWhilePluginActiveException(Throwable throwable) {
            super(throwable);
        }
    }
}
