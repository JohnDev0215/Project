package com.android.launcher3;

import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.android.launcher3.widget.DeferredAppWidgetHostView;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherAppWidgetHost extends AppWidgetHost {
    public static final int APPWIDGET_HOST_ID = 1024;
    private static final int FLAG_LISTENING = 1;
    private static final int FLAG_LISTEN_IF_RESUMED = 4;
    private static final int FLAG_RESUMED = 2;
    private final Context mContext;
    private int mFlags;
    private final ArrayList<ProviderChangedListener> mProviderChangeListeners;
    private final SparseArray<LauncherAppWidgetHostView> mViews;

    public interface ProviderChangedListener {
        void notifyWidgetProvidersChanged();
    }

    public LauncherAppWidgetHost(Context context) {
        super(context, 1024);
        this.mProviderChangeListeners = new ArrayList<>();
        this.mViews = new SparseArray<>();
        this.mFlags = 2;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.appwidget.AppWidgetHost
    public LauncherAppWidgetHostView onCreateView(Context context, int appWidgetId, AppWidgetProviderInfo appWidget) {
        LauncherAppWidgetHostView launcherAppWidgetHostView = new LauncherAppWidgetHostView(context);
        this.mViews.put(appWidgetId, launcherAppWidgetHostView);
        return launcherAppWidgetHostView;
    }

    @Override // android.appwidget.AppWidgetHost
    public void startListening() {
        this.mFlags |= 1;
        try {
            super.startListening();
        } catch (Exception e) {
            if (!Utilities.isBinderSizeError(e)) {
                throw new RuntimeException(e);
            }
        }
        for (int size = this.mViews.size() - 1; size >= 0; size--) {
            LauncherAppWidgetHostView launcherAppWidgetHostViewValueAt = this.mViews.valueAt(size);
            if (launcherAppWidgetHostViewValueAt instanceof DeferredAppWidgetHostView) {
                launcherAppWidgetHostViewValueAt.reInflate();
            }
        }
    }

    @Override // android.appwidget.AppWidgetHost
    public void stopListening() {
        this.mFlags &= -2;
        super.stopListening();
    }

    public void setResumed(boolean isResumed) {
        int i = this.mFlags;
        if (isResumed == ((i & 2) != 0)) {
            return;
        }
        if (isResumed) {
            int i2 = i | 2;
            this.mFlags = i2;
            if ((i2 & 4) == 0 || (i2 & 1) != 0) {
                return;
            }
            startListening();
            return;
        }
        this.mFlags = i & (-3);
    }

    public void setListenIfResumed(boolean listenIfResumed) {
        int i = this.mFlags;
        if (listenIfResumed == ((i & 4) != 0)) {
            return;
        }
        if (listenIfResumed) {
            int i2 = i | 4;
            this.mFlags = i2;
            if ((i2 & 2) != 0) {
                startListening();
                return;
            }
            return;
        }
        this.mFlags = i & (-5);
        stopListening();
    }

    @Override // android.appwidget.AppWidgetHost
    public int allocateAppWidgetId() {
        return super.allocateAppWidgetId();
    }

    public void addProviderChangeListener(ProviderChangedListener callback) {
        this.mProviderChangeListeners.add(callback);
    }

    public void removeProviderChangeListener(ProviderChangedListener callback) {
        this.mProviderChangeListeners.remove(callback);
    }

    @Override // android.appwidget.AppWidgetHost
    protected void onProvidersChanged() {
        if (this.mProviderChangeListeners.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.mProviderChangeListeners).iterator();
        while (it.hasNext()) {
            ((ProviderChangedListener) it.next()).notifyWidgetProvidersChanged();
        }
    }

    public AppWidgetHostView createView(Context context, int appWidgetId, LauncherAppWidgetProviderInfo appWidget) {
        if (appWidget.isCustomWidget()) {
            LauncherAppWidgetHostView launcherAppWidgetHostView = new LauncherAppWidgetHostView(context);
            ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(appWidget.initialLayout, launcherAppWidgetHostView);
            launcherAppWidgetHostView.setAppWidget(0, appWidget);
            return launcherAppWidgetHostView;
        }
        if ((this.mFlags & 1) == 0) {
            DeferredAppWidgetHostView deferredAppWidgetHostView = new DeferredAppWidgetHostView(context);
            deferredAppWidgetHostView.setAppWidget(appWidgetId, appWidget);
            this.mViews.put(appWidgetId, deferredAppWidgetHostView);
            return deferredAppWidgetHostView;
        }
        try {
            return super.createView(context, appWidgetId, (AppWidgetProviderInfo) appWidget);
        } catch (Exception e) {
            if (!Utilities.isBinderSizeError(e)) {
                throw new RuntimeException(e);
            }
            LauncherAppWidgetHostView launcherAppWidgetHostViewOnCreateView = this.mViews.get(appWidgetId);
            if (launcherAppWidgetHostViewOnCreateView == null) {
                launcherAppWidgetHostViewOnCreateView = this.onCreateView(this.mContext, appWidgetId, (AppWidgetProviderInfo) appWidget);
            }
            launcherAppWidgetHostViewOnCreateView.setAppWidget(appWidgetId, appWidget);
            launcherAppWidgetHostViewOnCreateView.switchToErrorView();
            return launcherAppWidgetHostViewOnCreateView;
        }
    }

    @Override // android.appwidget.AppWidgetHost
    protected void onProviderChanged(int appWidgetId, AppWidgetProviderInfo appWidget) {
        LauncherAppWidgetProviderInfo launcherAppWidgetProviderInfoFromProviderInfo = LauncherAppWidgetProviderInfo.fromProviderInfo(this.mContext, appWidget);
        super.onProviderChanged(appWidgetId, launcherAppWidgetProviderInfoFromProviderInfo);
        launcherAppWidgetProviderInfoFromProviderInfo.initSpans(this.mContext);
    }

    @Override // android.appwidget.AppWidgetHost
    public void deleteAppWidgetId(int appWidgetId) {
        super.deleteAppWidgetId(appWidgetId);
        this.mViews.remove(appWidgetId);
    }

    @Override // android.appwidget.AppWidgetHost
    public void clearViews() {
        super.clearViews();
        this.mViews.clear();
    }

    public void startBindFlow(BaseActivity activity, int appWidgetId, AppWidgetProviderInfo info, int requestCode) {
        activity.startActivityForResult(new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND).putExtra("appWidgetId", appWidgetId).putExtra("appWidgetProvider", info.provider).putExtra(AppWidgetManager.EXTRA_APPWIDGET_PROVIDER_PROFILE, info.getProfile()), requestCode);
    }

    public void startConfigActivity(BaseActivity activity, int widgetId, int requestCode) {
        try {
            startAppWidgetConfigureActivityForResult(activity, widgetId, 0, requestCode, null);
        } catch (ActivityNotFoundException | SecurityException unused) {
            Toast.makeText(activity, R.string.activity_not_found, 0).show();
            sendActionCancelled(activity, requestCode);
        }
    }

    private void sendActionCancelled(final BaseActivity activity, final int requestCode) {
        new Handler().post(new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherAppWidgetHost$S2mwoa2f5ZdyUVrAqNtI4ZBuQOI
            @Override // java.lang.Runnable
            public final void run() {
                activity.onActivityResult(requestCode, 0, null);
            }
        });
    }
}
