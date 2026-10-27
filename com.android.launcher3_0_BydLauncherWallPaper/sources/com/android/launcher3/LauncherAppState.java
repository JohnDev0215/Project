package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.PackageInstallerCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.util.SecureSettingsObserver;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppState {
    public static final String ACTION_FORCE_ROLOAD = "force-reload-launcher";
    private static final MainThreadInitializedObject<LauncherAppState> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.-$$Lambda$LauncherAppState$XapVNkb8llyjxoSqMEkM5geTzyQ
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return LauncherAppState.lambda$static$0(context);
        }
    });
    private final Context mContext;
    private final IconCache mIconCache;
    private final InvariantDeviceProfile mInvariantDeviceProfile;
    private final LauncherModel mModel;
    private final SecureSettingsObserver mNotificationDotsObserver;
    private final WidgetPreviewLoader mWidgetCache;

    static /* synthetic */ LauncherAppState lambda$static$0(Context context) {
        return new LauncherAppState(context);
    }

    public static LauncherAppState getInstance(final Context context) {
        return INSTANCE.lambda$get$0$MainThreadInitializedObject(context);
    }

    public static LauncherAppState getInstanceNoCreate() {
        return INSTANCE.getNoCreate();
    }

    public Context getContext() {
        return this.mContext;
    }

    private LauncherAppState(final Context context) {
        if (getLocalProvider(context) == null) {
            throw new RuntimeException("Initializing LauncherAppState in the absence of LauncherProvider");
        }
        Log.v(Launcher.TAG, "LauncherAppState initiated");
        Preconditions.assertUIThread();
        this.mContext = context;
        InvariantDeviceProfile invariantDeviceProfileLambda$get$0$MainThreadInitializedObject = InvariantDeviceProfile.INSTANCE.lambda$get$0$MainThreadInitializedObject(context);
        this.mInvariantDeviceProfile = invariantDeviceProfileLambda$get$0$MainThreadInitializedObject;
        IconCache iconCache = new IconCache(context, invariantDeviceProfileLambda$get$0$MainThreadInitializedObject);
        this.mIconCache = iconCache;
        this.mWidgetCache = new WidgetPreviewLoader(context, iconCache);
        LauncherModel launcherModel = new LauncherModel(this, iconCache, AppFilter.newInstance(context));
        this.mModel = launcherModel;
        LauncherAppsCompat.getInstance(context).addOnAppsChangedCallback(launcherModel);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_ADDED);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_REMOVED);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_AVAILABLE);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_UNLOCKED);
        context.registerReceiver(launcherModel, intentFilter);
        UserManagerCompat.getInstance(context).enableAndResetCache();
        invariantDeviceProfileLambda$get$0$MainThreadInitializedObject.addOnChangeListener(new InvariantDeviceProfile.OnIDPChangeListener() { // from class: com.android.launcher3.-$$Lambda$LauncherAppState$Vlw2ZDkS72USPce64gXSd79-QSc
            @Override // com.android.launcher3.InvariantDeviceProfile.OnIDPChangeListener
            public final void onIdpChanged(int i, InvariantDeviceProfile invariantDeviceProfile) {
                this.f$0.onIdpChanged(i, invariantDeviceProfile);
            }
        });
        new Handler().post(new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherAppState$0ZgeEwMJ254CT3KgMu6ByFrWNMk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1$LauncherAppState(context);
            }
        });
        if (!context.getResources().getBoolean(R.bool.notification_dots_enabled)) {
            this.mNotificationDotsObserver = null;
            return;
        }
        SecureSettingsObserver secureSettingsObserverNewNotificationSettingsObserver = SecureSettingsObserver.newNotificationSettingsObserver(context, new SecureSettingsObserver.OnChangeListener() { // from class: com.android.launcher3.-$$Lambda$wrdpBUVHMgFegB-VNcPbz9R0l9I
            @Override // com.android.launcher3.util.SecureSettingsObserver.OnChangeListener
            public final void onSettingsChanged(boolean z) {
                this.f$0.onNotificationSettingsChanged(z);
            }
        });
        this.mNotificationDotsObserver = secureSettingsObserverNewNotificationSettingsObserver;
        secureSettingsObserverNewNotificationSettingsObserver.register();
        secureSettingsObserverNewNotificationSettingsObserver.dispatchOnChange();
    }

    public /* synthetic */ void lambda$new$1$LauncherAppState(Context context) {
        this.mInvariantDeviceProfile.verifyConfigChangedInBackground(context);
    }

    protected void onNotificationSettingsChanged(boolean areNotificationDotsEnabled) {
        if (areNotificationDotsEnabled) {
            NotificationListener.requestRebind(new ComponentName(this.mContext, (Class<?>) NotificationListener.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onIdpChanged(int changeFlags, InvariantDeviceProfile idp) {
        if (changeFlags == 0) {
            return;
        }
        if ((changeFlags & 2) != 0) {
            LauncherIcons.clearPool();
            this.mIconCache.updateIconParams(idp.fillResIconDpi, idp.iconBitmapSize);
            this.mWidgetCache.refresh();
        }
        this.mModel.forceReload();
    }

    public void onTerminate() {
        this.mContext.unregisterReceiver(this.mModel);
        LauncherAppsCompat.getInstance(this.mContext).removeOnAppsChangedCallback(this.mModel);
        PackageInstallerCompat.getInstance(this.mContext).onStop();
        SecureSettingsObserver secureSettingsObserver = this.mNotificationDotsObserver;
        if (secureSettingsObserver != null) {
            secureSettingsObserver.unregister();
        }
    }

    LauncherModel setLauncher(Launcher launcher) {
        getLocalProvider(this.mContext).setLauncherProviderChangeListener(launcher);
        this.mModel.initialize(launcher);
        return this.mModel;
    }

    public IconCache getIconCache() {
        return this.mIconCache;
    }

    public LauncherModel getModel() {
        return this.mModel;
    }

    public WidgetPreviewLoader getWidgetCache() {
        return this.mWidgetCache;
    }

    public InvariantDeviceProfile getInvariantDeviceProfile() {
        return this.mInvariantDeviceProfile;
    }

    public static InvariantDeviceProfile getIDP(Context context) {
        return InvariantDeviceProfile.INSTANCE.lambda$get$0$MainThreadInitializedObject(context);
    }

    private static LauncherProvider getLocalProvider(Context context) {
        ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(LauncherProvider.AUTHORITY);
        try {
            LauncherProvider launcherProvider = (LauncherProvider) contentProviderClientAcquireContentProviderClient.getLocalContentProvider();
            if (contentProviderClientAcquireContentProviderClient != null) {
                contentProviderClientAcquireContentProviderClient.close();
            }
            return launcherProvider;
        } catch (Throwable th) {
            if (contentProviderClientAcquireContentProviderClient != null) {
                try {
                    contentProviderClientAcquireContentProviderClient.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
