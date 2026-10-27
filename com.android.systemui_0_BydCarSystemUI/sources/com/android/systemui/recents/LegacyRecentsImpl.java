package com.android.systemui.recents;

import android.content.Context;
import android.content.res.Resources;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.recents.misc.SystemServicesProxy;
import com.android.systemui.recents.model.RecentsTaskLoader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class LegacyRecentsImpl implements RecentsImplementation {
    public static final Set<String> RECENTS_ACTIVITIES;
    private static RecentsConfiguration sConfiguration;
    private static SystemServicesProxy sSystemServicesProxy;
    private static RecentsTaskLoader sTaskLoader;
    private Context mContext;
    private RecentsImpl mImpl;
    private SysUiServiceProvider mSysUiServiceProvider;

    @Override // com.android.systemui.recents.RecentsImplementation
    public void cancelPreloadRecentApps() {
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void hideRecentApps(boolean triggeredFromAltTab, boolean triggeredFromHomeKey) {
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void showRecentApps(boolean triggeredFromAltTab) {
    }

    static {
        HashSet hashSet = new HashSet();
        RECENTS_ACTIVITIES = hashSet;
        hashSet.add(RecentsImpl.RECENTS_ACTIVITY);
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void onStart(Context context, SysUiServiceProvider sysUiServiceProvider) {
        this.mContext = context;
        this.mSysUiServiceProvider = sysUiServiceProvider;
        sSystemServicesProxy = SystemServicesProxy.getInstance(context);
        sConfiguration = new RecentsConfiguration(this.mContext);
        Resources resources = this.mContext.getResources();
        sTaskLoader = new RecentsTaskLoader(this.mContext, resources.getInteger(R.integer.config_recents_max_thumbnail_count), resources.getInteger(R.integer.config_recents_max_icon_count), resources.getInteger(R.integer.recents_svelte_level));
        this.mImpl = new RecentsImpl(this.mContext);
    }

    public static RecentsTaskLoader getTaskLoader() {
        return sTaskLoader;
    }

    public static SystemServicesProxy getSystemServices() {
        return sSystemServicesProxy;
    }

    public static RecentsConfiguration getConfiguration() {
        return sConfiguration;
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void toggleRecentApps() {
        this.mImpl.toggleRecents(0);
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void preloadRecentApps() {
        if (sSystemServicesProxy.isSystemUser(sSystemServicesProxy.getCurrentUser())) {
            this.mImpl.preloadRecents();
        }
    }

    private <T> T getComponent(Class<T> cls) {
        return (T) this.mSysUiServiceProvider.getComponent(cls);
    }

    @Override // com.android.systemui.recents.RecentsImplementation
    public void dump(PrintWriter pw) {
        pw.println("Recents");
    }
}
