package com.android.systemui.plugins;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.VersionInfo;
import com.android.systemui.util.SystemUIException;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PluginInstanceManager<T extends Plugin> {
    private static final boolean DEBUG = false;
    public static final String PLUGIN_PERMISSION = "com.android.systemui.permission.PLUGIN";
    private static final String TAG = "PluginInstanceManager";
    private final boolean isDebuggable;
    private final String mAction;
    private final boolean mAllowMultiple;
    private final Context mContext;
    private final PluginListener<T> mListener;
    final PluginInstanceManager<T>.MainHandler mMainHandler;
    private final PluginManagerImpl mManager;
    final PluginInstanceManager<T>.PluginHandler mPluginHandler;
    private final PackageManager mPm;
    private final VersionInfo mVersion;

    PluginInstanceManager(Context context, String action, PluginListener<T> listener, boolean allowMultiple, Looper looper, VersionInfo version, PluginManagerImpl manager) {
        this(context, context.getPackageManager(), action, listener, allowMultiple, looper, version, manager, Build.IS_DEBUGGABLE);
    }

    PluginInstanceManager(Context context, PackageManager pm, String action, PluginListener<T> listener, boolean allowMultiple, Looper looper, VersionInfo version, PluginManagerImpl manager, boolean debuggable) {
        this.mMainHandler = new MainHandler(Looper.getMainLooper());
        this.mPluginHandler = new PluginHandler(looper);
        this.mManager = manager;
        this.mContext = context;
        this.mPm = pm;
        this.mAction = action;
        this.mListener = listener;
        this.mAllowMultiple = allowMultiple;
        this.mVersion = version;
        this.isDebuggable = debuggable;
    }

    public PluginInfo<T> getPlugin() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new SystemUIException("Must be called from UI thread");
        }
        this.mPluginHandler.handleQueryPlugins(null);
        if (((PluginHandler) this.mPluginHandler).mPlugins.size() <= 0) {
            return null;
        }
        this.mMainHandler.removeMessages(1);
        PluginInfo<T> pluginInfo = (PluginInfo) ((PluginHandler) this.mPluginHandler).mPlugins.get(0);
        PluginPrefs.setHasPlugins(this.mContext);
        pluginInfo.mPlugin.onCreate(this.mContext, ((PluginInfo) pluginInfo).mPluginContext);
        return pluginInfo;
    }

    public void loadAll() {
        this.mPluginHandler.sendEmptyMessage(1);
    }

    public void destroy() {
        Iterator it = new ArrayList(((PluginHandler) this.mPluginHandler).mPlugins).iterator();
        while (it.hasNext()) {
            this.mMainHandler.obtainMessage(2, ((PluginInfo) it.next()).mPlugin).sendToTarget();
        }
    }

    public void onPackageRemoved(String pkg) {
        this.mPluginHandler.obtainMessage(3, pkg).sendToTarget();
    }

    public void onPackageChange(String pkg) {
        this.mPluginHandler.obtainMessage(3, pkg).sendToTarget();
        this.mPluginHandler.obtainMessage(2, pkg).sendToTarget();
    }

    public boolean checkAndDisable(String className) {
        boolean z = false;
        for (PluginInfo pluginInfo : new ArrayList(((PluginHandler) this.mPluginHandler).mPlugins)) {
            if (className.startsWith(pluginInfo.mPackage)) {
                disable(pluginInfo);
                z = true;
            }
        }
        return z;
    }

    public boolean disableAll() {
        ArrayList arrayList = new ArrayList(((PluginHandler) this.mPluginHandler).mPlugins);
        for (int i = 0; i < arrayList.size(); i++) {
            disable((PluginInfo) arrayList.get(i));
        }
        return arrayList.size() != 0;
    }

    private void disable(PluginInfo info) {
        Log.w(TAG, "Disabling plugin " + info.mPackage + "/" + info.mClass);
        this.mPm.setComponentEnabledSetting(new ComponentName(info.mPackage, info.mClass), 2, 1);
    }

    public <T> boolean dependsOn(Plugin p, Class<T> cls) {
        for (PluginInfo pluginInfo : new ArrayList(((PluginHandler) this.mPluginHandler).mPlugins)) {
            if (pluginInfo.mPlugin.getClass().getName().equals(p.getClass().getName())) {
                return pluginInfo.mVersion != null && pluginInfo.mVersion.hasClass(cls);
            }
        }
        return false;
    }

    public String toString() {
        return String.format("%s@%s (action=%s)", getClass().getSimpleName(), Integer.valueOf(hashCode()), this.mAction);
    }

    private class MainHandler extends Handler {
        private static final int PLUGIN_CONNECTED = 1;
        private static final int PLUGIN_DISCONNECTED = 2;

        public MainHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                PluginPrefs.setHasPlugins(PluginInstanceManager.this.mContext);
                PluginInfo pluginInfo = (PluginInfo) msg.obj;
                PluginInstanceManager.this.mManager.handleWtfs();
                if (!(msg.obj instanceof PluginFragment)) {
                    ((Plugin) pluginInfo.mPlugin).onCreate(PluginInstanceManager.this.mContext, pluginInfo.mPluginContext);
                }
                PluginInstanceManager.this.mListener.onPluginConnected((Plugin) pluginInfo.mPlugin, pluginInfo.mPluginContext);
                return;
            }
            if (i == 2) {
                PluginInstanceManager.this.mListener.onPluginDisconnected((Plugin) msg.obj);
                if (msg.obj instanceof PluginFragment) {
                    return;
                }
                ((Plugin) msg.obj).onDestroy();
                return;
            }
            super.handleMessage(msg);
        }
    }

    private class PluginHandler extends Handler {
        private static final int QUERY_ALL = 1;
        private static final int QUERY_PKG = 2;
        private static final int REMOVE_PKG = 3;
        private final ArrayList<PluginInfo<T>> mPlugins;

        public PluginHandler(Looper looper) {
            super(looper);
            this.mPlugins = new ArrayList<>();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                for (int size = this.mPlugins.size() - 1; size >= 0; size--) {
                    PluginInfo<T> pluginInfo = this.mPlugins.get(size);
                    PluginInstanceManager.this.mListener.onPluginDisconnected(pluginInfo.mPlugin);
                    if (!(pluginInfo.mPlugin instanceof PluginFragment)) {
                        pluginInfo.mPlugin.onDestroy();
                    }
                }
                this.mPlugins.clear();
                handleQueryPlugins(null);
                return;
            }
            if (i == 2) {
                String str = (String) msg.obj;
                if (PluginInstanceManager.this.mAllowMultiple || this.mPlugins.size() == 0) {
                    handleQueryPlugins(str);
                    return;
                }
                return;
            }
            if (i == 3) {
                String str2 = (String) msg.obj;
                for (int size2 = this.mPlugins.size() - 1; size2 >= 0; size2--) {
                    PluginInfo<T> pluginInfo2 = this.mPlugins.get(size2);
                    if (pluginInfo2.mPackage.equals(str2)) {
                        PluginInstanceManager.this.mMainHandler.obtainMessage(2, pluginInfo2.mPlugin).sendToTarget();
                        this.mPlugins.remove(size2);
                    }
                }
                return;
            }
            super.handleMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleQueryPlugins(String pkgName) {
            Intent intent = new Intent(PluginInstanceManager.this.mAction);
            if (pkgName != null) {
                intent.setPackage(pkgName);
            }
            List<ResolveInfo> listQueryIntentServices = PluginInstanceManager.this.mPm.queryIntentServices(intent, 0);
            if (listQueryIntentServices.size() > 1 && !PluginInstanceManager.this.mAllowMultiple) {
                Log.w(PluginInstanceManager.TAG, "Multiple plugins found for " + PluginInstanceManager.this.mAction);
                return;
            }
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                PluginInfo<T> pluginInfoHandleLoadPlugin = handleLoadPlugin(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
                if (pluginInfoHandleLoadPlugin != null) {
                    PluginInstanceManager.this.mMainHandler.obtainMessage(1, pluginInfoHandleLoadPlugin).sendToTarget();
                    this.mPlugins.add(pluginInfoHandleLoadPlugin);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected PluginInfo<T> handleLoadPlugin(ComponentName component) {
            String string;
            if (!PluginInstanceManager.this.isDebuggable) {
                Log.d(PluginInstanceManager.TAG, "Somehow hit second debuggable check");
                return null;
            }
            String packageName = component.getPackageName();
            String className = component.getClassName();
            try {
                ApplicationInfo applicationInfo = PluginInstanceManager.this.mPm.getApplicationInfo(packageName, 0);
                if (PluginInstanceManager.this.mPm.checkPermission(PluginInstanceManager.PLUGIN_PERMISSION, packageName) == 0) {
                    ClassLoader classLoader = PluginInstanceManager.this.mManager.getClassLoader(applicationInfo.sourceDir, applicationInfo.packageName);
                    PluginContextWrapper pluginContextWrapper = new PluginContextWrapper(PluginInstanceManager.this.mContext.createApplicationContext(applicationInfo, 0), classLoader);
                    Class<?> cls = Class.forName(className, true, classLoader);
                    Plugin plugin = (Plugin) cls.newInstance();
                    try {
                        return new PluginInfo<>(packageName, className, plugin, pluginContextWrapper, checkVersion(cls, plugin, PluginInstanceManager.this.mVersion));
                    } catch (VersionInfo.InvalidVersionException e) {
                        Notification.Builder color = new Notification.Builder(PluginInstanceManager.this.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setStyle(new Notification.BigTextStyle()).setSmallIcon(PluginInstanceManager.this.mContext.getResources().getIdentifier("tuner", AttrFactory.DRAWABLE, PluginInstanceManager.this.mContext.getPackageName())).setWhen(0L).setShowWhen(false).setVisibility(1).setColor(PluginInstanceManager.this.mContext.getColor(Resources.getSystem().getIdentifier("system_notification_accent_color", "color", "android")));
                        try {
                            string = PluginInstanceManager.this.mPm.getServiceInfo(component, 0).loadLabel(PluginInstanceManager.this.mPm).toString();
                        } catch (PackageManager.NameNotFoundException unused) {
                            string = className;
                        }
                        if (!e.isTooNew()) {
                            color.setContentTitle("Plugin \"" + string + "\" is too old").setContentText("Contact plugin developer to get an updated version.\n" + e.getMessage());
                        } else {
                            color.setContentTitle("Plugin \"" + string + "\" is too new").setContentText("Check to see if an OTA is available.\n" + e.getMessage());
                        }
                        color.addAction(new Notification.Action.Builder((Icon) null, "Disable plugin", PendingIntent.getBroadcast(PluginInstanceManager.this.mContext, 0, new Intent("com.android.systemui.action.DISABLE_PLUGIN").setData(Uri.parse("package://" + component.flattenToString())), 0)).build());
                        ((NotificationManager) PluginInstanceManager.this.mContext.getSystemService(NotificationManager.class)).notifyAsUser(className, 6, color.build(), UserHandle.ALL);
                        Log.w(PluginInstanceManager.TAG, "Plugin has invalid interface version " + plugin.getVersion() + ", expected " + PluginInstanceManager.this.mVersion);
                        return null;
                    }
                }
                Log.d(PluginInstanceManager.TAG, "Plugin doesn't have permission: " + packageName);
                return null;
            } catch (Throwable th) {
                Log.w(PluginInstanceManager.TAG, "Couldn't load plugin: " + packageName, th);
                return null;
            }
        }

        private VersionInfo checkVersion(Class<?> pluginClass, T plugin, VersionInfo version) throws VersionInfo.InvalidVersionException {
            VersionInfo versionInfoAddClass = new VersionInfo().addClass(pluginClass);
            if (versionInfoAddClass.hasVersionInfo()) {
                version.checkVersion(versionInfoAddClass);
                return versionInfoAddClass;
            }
            if (plugin.getVersion() == version.getDefaultVersion()) {
                return null;
            }
            throw new VersionInfo.InvalidVersionException("Invalid legacy version", false);
        }
    }

    public static class PluginContextWrapper extends ContextWrapper {
        private final ClassLoader mClassLoader;
        private LayoutInflater mInflater;

        public PluginContextWrapper(Context base, ClassLoader classLoader) {
            super(base);
            this.mClassLoader = classLoader;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public ClassLoader getClassLoader() {
            return this.mClassLoader;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Object getSystemService(String name) {
            if ("layout_inflater".equals(name)) {
                if (this.mInflater == null) {
                    this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
                }
                return this.mInflater;
            }
            return getBaseContext().getSystemService(name);
        }
    }

    static class PluginInfo<T> {
        private String mClass;
        String mPackage;
        T mPlugin;
        private final Context mPluginContext;
        private final VersionInfo mVersion;

        public PluginInfo(String pkg, String cls, T plugin, Context pluginContext, VersionInfo info) {
            this.mPlugin = plugin;
            this.mClass = cls;
            this.mPackage = pkg;
            this.mPluginContext = pluginContext;
            this.mVersion = info;
        }
    }
}
