package com.android.car.settings.applications.specialaccess;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.android.settingslib.applications.ApplicationsState;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppEntryListManager {
    private final ApplicationsState mApplicationsState;
    private final BackgroundHandler mBackgroundHandler;
    private Callback mCallback;
    private ExtraInfoBridge mExtraInfoBridge;
    private AppFilterProvider mFilterProvider;
    private boolean mHasReceivedExtraInfo;
    private boolean mHasReceivedLoadEntries;
    private ApplicationsState.Session mSession;
    private final ApplicationsState.Callbacks mSessionCallbacks = new ApplicationsState.Callbacks() { // from class: com.android.car.settings.applications.specialaccess.AppEntryListManager.1
        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onRunningStateChanged(boolean running) {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageListChanged() {
            AppEntryListManager.this.forceUpdate();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> apps) {
            if (AppEntryListManager.this.mCallback != null) {
                AppEntryListManager.this.mCallback.onAppEntryListChanged(apps);
            }
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageIconChanged() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageSizeChanged(String packageName) {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onAllSizesComputed() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onLauncherInfoChanged() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onLoadEntriesCompleted() {
            AppEntryListManager.this.mHasReceivedLoadEntries = true;
            AppEntryListManager.this.forceUpdate();
        }
    };
    private final MainHandler mMainHandler = new MainHandler(new WeakReference(this));

    public interface AppFilterProvider {
        ApplicationsState.AppFilter getAppFilter();
    }

    public interface Callback {
        void onAppEntryListChanged(List<ApplicationsState.AppEntry> list);
    }

    public interface ExtraInfoBridge {
        void loadExtraInfo(List<ApplicationsState.AppEntry> list);
    }

    public AppEntryListManager(Context context) {
        this.mApplicationsState = ApplicationsState.getInstance((Application) context.getApplicationContext());
        this.mBackgroundHandler = new BackgroundHandler(new WeakReference(this), this.mApplicationsState.getBackgroundLooper());
    }

    public void init(ExtraInfoBridge extraInfoBridge, AppFilterProvider filterProvider, Callback callback) {
        if (this.mSession != null) {
            destroy();
        }
        this.mExtraInfoBridge = extraInfoBridge;
        this.mFilterProvider = filterProvider;
        this.mCallback = callback;
        this.mSession = this.mApplicationsState.newSession(this.mSessionCallbacks);
    }

    public void start() {
        this.mSession.onResume();
    }

    public void stop() {
        this.mSession.onPause();
        clearHandlers();
    }

    public void destroy() {
        this.mSession.onDestroy();
        clearHandlers();
        this.mExtraInfoBridge = null;
        this.mFilterProvider = null;
        this.mCallback = null;
    }

    public void forceUpdate() {
        this.mBackgroundHandler.sendEmptyMessage(1);
    }

    public void forceUpdate(ApplicationsState.AppEntry entry) {
        this.mBackgroundHandler.obtainMessage(2, entry).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rebuild() {
        if (!this.mHasReceivedLoadEntries || !this.mHasReceivedExtraInfo) {
            return;
        }
        ApplicationsState.Session session = this.mSession;
        AppFilterProvider appFilterProvider = this.mFilterProvider;
        session.rebuild(appFilterProvider != null ? appFilterProvider.getAppFilter() : ApplicationsState.FILTER_EVERYTHING, ApplicationsState.ALPHA_COMPARATOR, false);
    }

    private void clearHandlers() {
        this.mBackgroundHandler.removeMessages(1);
        this.mBackgroundHandler.removeMessages(2);
        this.mMainHandler.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInfo(List<ApplicationsState.AppEntry> entries) {
        ExtraInfoBridge extraInfoBridge = this.mExtraInfoBridge;
        if (extraInfoBridge != null) {
            extraInfoBridge.loadExtraInfo(entries);
        }
        for (ApplicationsState.AppEntry entry : entries) {
            this.mApplicationsState.ensureIcon(entry);
        }
    }

    private static class BackgroundHandler extends Handler {
        private static final int MSG_LOAD_ALL = 1;
        private static final int MSG_LOAD_PKG = 2;
        private final WeakReference<AppEntryListManager> mOuter;

        BackgroundHandler(WeakReference<AppEntryListManager> outer, Looper looper) {
            super(looper);
            this.mOuter = outer;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AppEntryListManager outer = this.mOuter.get();
            if (outer == null) {
                return;
            }
            int i = msg.what;
            if (i == 1) {
                outer.loadInfo(outer.mSession.getAllApps());
                outer.mMainHandler.sendEmptyMessage(1);
            } else if (i == 2) {
                ApplicationsState.AppEntry entry = (ApplicationsState.AppEntry) msg.obj;
                outer.loadInfo(Collections.singletonList(entry));
                outer.mMainHandler.sendEmptyMessage(1);
            }
        }
    }

    private static class MainHandler extends Handler {
        private static final int MSG_INFO_UPDATED = 1;
        private final WeakReference<AppEntryListManager> mOuter;

        MainHandler(WeakReference<AppEntryListManager> outer) {
            this.mOuter = outer;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AppEntryListManager outer = this.mOuter.get();
            if (outer != null && msg.what == 1) {
                outer.mHasReceivedExtraInfo = true;
                outer.rebuild();
            }
        }
    }
}
