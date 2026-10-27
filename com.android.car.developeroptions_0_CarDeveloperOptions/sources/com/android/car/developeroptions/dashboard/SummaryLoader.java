package com.android.car.developeroptions.dashboard;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.drawer.DashboardCategory;
import com.android.settingslib.drawer.Tile;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SummaryLoader {
    public static final String SUMMARY_PROVIDER_FACTORY = "SUMMARY_PROVIDER_FACTORY";
    private final Activity mActivity;
    private final String mCategoryKey;
    private final DashboardFeatureProvider mDashboardFeatureProvider;
    private boolean mListening;
    private SummaryConsumer mSummaryConsumer;
    private final Worker mWorker;
    private boolean mWorkerListening;
    private final HandlerThread mWorkerThread;
    private final ArrayMap<SummaryProvider, ComponentName> mSummaryProviderMap = new ArrayMap<>();
    private final ArrayMap<String, CharSequence> mSummaryTextMap = new ArrayMap<>();
    private ArraySet<BroadcastReceiver> mReceivers = new ArraySet<>();

    public interface SummaryConsumer {
        void notifySummaryChanged(Tile tile);
    }

    public interface SummaryProvider {
        void setListening(boolean z);
    }

    public interface SummaryProviderFactory {
        SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader);
    }

    public SummaryLoader(Activity activity, String str) {
        this.mDashboardFeatureProvider = FeatureFactory.getFactory(activity).getDashboardFeatureProvider(activity);
        this.mCategoryKey = str;
        HandlerThread handlerThread = new HandlerThread("SummaryLoader", 10);
        this.mWorkerThread = handlerThread;
        handlerThread.start();
        this.mWorker = new Worker(this.mWorkerThread.getLooper());
        this.mActivity = activity;
    }

    public void release() {
        this.mWorkerThread.quitSafely();
        setListeningW(false);
    }

    public void setSummaryConsumer(SummaryConsumer summaryConsumer) {
        this.mSummaryConsumer = summaryConsumer;
    }

    public void setSummary(SummaryProvider summaryProvider, final CharSequence charSequence) {
        final ComponentName componentName = this.mSummaryProviderMap.get(summaryProvider);
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$SummaryLoader$sBuW7-b-m_KuqcglcHokZ3QvzC8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setSummary$0$SummaryLoader(componentName, charSequence);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setSummary$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setSummary$0$SummaryLoader(ComponentName componentName, CharSequence charSequence) {
        Tile tileFromCategory = getTileFromCategory(this.mDashboardFeatureProvider.getTilesForCategory(this.mCategoryKey), componentName);
        if (tileFromCategory == null) {
            return;
        }
        updateSummaryIfNeeded(this.mActivity.getApplicationContext(), tileFromCategory, charSequence);
    }

    void updateSummaryIfNeeded(Context context, Tile tile, CharSequence charSequence) {
        if (TextUtils.equals(tile.getSummary(context), charSequence)) {
            return;
        }
        this.mSummaryTextMap.put(this.mDashboardFeatureProvider.getDashboardKeyForTile(tile), charSequence);
        tile.overrideSummary(charSequence);
        SummaryConsumer summaryConsumer = this.mSummaryConsumer;
        if (summaryConsumer != null) {
            summaryConsumer.notifySummaryChanged(tile);
        }
    }

    public void setListening(boolean z) {
        if (this.mListening == z) {
            return;
        }
        this.mListening = z;
        for (int i = 0; i < this.mReceivers.size(); i++) {
            this.mActivity.unregisterReceiver(this.mReceivers.valueAt(i));
        }
        this.mReceivers.clear();
        this.mWorker.removeMessages(3);
        if (!z) {
            this.mWorker.obtainMessage(3, 0).sendToTarget();
        } else {
            if (this.mSummaryProviderMap.isEmpty()) {
                if (this.mWorker.hasMessages(1)) {
                    return;
                }
                this.mWorker.sendEmptyMessage(1);
                return;
            }
            this.mWorker.obtainMessage(3, 1).sendToTarget();
        }
    }

    private SummaryProvider getSummaryProvider(Tile tile) {
        if (!this.mActivity.getPackageName().equals(tile.getPackageName())) {
            return null;
        }
        Bundle metaData = tile.getMetaData();
        Intent intent = tile.getIntent();
        if (metaData == null) {
            Log.d("SummaryLoader", "No metadata specified for " + intent.getComponent());
            return null;
        }
        String string = metaData.getString("com.android.car.developeroptions.FRAGMENT_CLASS");
        if (string == null) {
            Log.d("SummaryLoader", "No fragment specified for " + intent.getComponent());
            return null;
        }
        try {
            return ((SummaryProviderFactory) Class.forName(string).getField(SUMMARY_PROVIDER_FACTORY).get(null)).createSummaryProvider(this.mActivity, this);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setListeningW(boolean z) {
        if (this.mWorkerListening == z) {
            return;
        }
        this.mWorkerListening = z;
        Iterator<SummaryProvider> it = this.mSummaryProviderMap.keySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().setListening(z);
            } catch (Exception e) {
                Log.d("SummaryLoader", "Problem in setListening", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void makeProviderW(Tile tile) {
        SummaryProvider summaryProvider = getSummaryProvider(tile);
        if (summaryProvider != null) {
            this.mSummaryProviderMap.put(summaryProvider, tile.getIntent().getComponent());
        }
    }

    private Tile getTileFromCategory(DashboardCategory dashboardCategory, ComponentName componentName) {
        if (dashboardCategory != null && dashboardCategory.getTilesCount() != 0) {
            List<Tile> tiles = dashboardCategory.getTiles();
            int size = tiles.size();
            for (int i = 0; i < size; i++) {
                Tile tile = tiles.get(i);
                if (componentName.equals(tile.getIntent().getComponent())) {
                    return tile;
                }
            }
        }
        return null;
    }

    private class Worker extends Handler {
        public Worker(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    SummaryLoader.this.makeProviderW((Tile) message.obj);
                    return;
                } else {
                    if (i != 3) {
                        return;
                    }
                    Object obj = message.obj;
                    SummaryLoader.this.setListeningW(obj != null && obj.equals(1));
                    return;
                }
            }
            DashboardCategory tilesForCategory = SummaryLoader.this.mDashboardFeatureProvider.getTilesForCategory(SummaryLoader.this.mCategoryKey);
            if (tilesForCategory == null || tilesForCategory.getTilesCount() == 0) {
                return;
            }
            Iterator<Tile> it = tilesForCategory.getTiles().iterator();
            while (it.hasNext()) {
                SummaryLoader.this.makeProviderW(it.next());
            }
            SummaryLoader.this.setListeningW(true);
        }
    }
}
