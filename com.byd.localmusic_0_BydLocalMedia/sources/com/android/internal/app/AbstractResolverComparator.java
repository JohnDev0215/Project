package com.android.internal.app;

import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.internal.app.ResolverActivity;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractResolverComparator implements Comparator<ResolverActivity.ResolvedComponentInfo> {
    private static final boolean DEBUG = true;
    private static final int NUM_OF_TOP_ANNOTATIONS_TO_USE = 3;
    static final int RANKER_RESULT_TIMEOUT = 1;
    static final int RANKER_SERVICE_RESULT = 0;
    private static final String TAG = "AbstractResolverComp";
    private static final int WATCHDOG_TIMEOUT_MILLIS = 500;
    protected AfterCompute mAfterCompute;
    protected String[] mAnnotations;
    private final Comparator<ResolveInfo> mAzComparator;
    protected String mContentType;
    protected final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.internal.app.AbstractResolverComparator.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 1) {
                    Log.d(AbstractResolverComparator.TAG, "RANKER_RESULT_TIMEOUT; unbinding services");
                    AbstractResolverComparator.this.mHandler.removeMessages(0);
                    AbstractResolverComparator.this.afterCompute();
                    return;
                }
                super.handleMessage(message);
                return;
            }
            Log.d(AbstractResolverComparator.TAG, "RANKER_SERVICE_RESULT");
            if (AbstractResolverComparator.this.mHandler.hasMessages(1)) {
                AbstractResolverComparator.this.handleResultMessage(message);
                AbstractResolverComparator.this.mHandler.removeMessages(1);
                AbstractResolverComparator.this.afterCompute();
            }
        }
    };
    private final boolean mHttp;
    protected final PackageManager mPm;
    protected final UsageStatsManager mUsm;

    interface AfterCompute {
        void afterCompute();
    }

    abstract int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2);

    abstract void doCompute(List<ResolverActivity.ResolvedComponentInfo> list);

    abstract float getScore(ComponentName componentName);

    abstract List<ComponentName> getTopComponentNames(int i);

    abstract void handleResultMessage(Message message);

    void updateModel(ComponentName componentName) {
    }

    public AbstractResolverComparator(Context context, Intent intent) {
        String scheme = intent.getScheme();
        this.mHttp = IntentFilter.SCHEME_HTTP.equals(scheme) || IntentFilter.SCHEME_HTTPS.equals(scheme);
        this.mContentType = intent.getType();
        getContentAnnotations(intent);
        this.mPm = context.getPackageManager();
        this.mUsm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        this.mAzComparator = new AzInfoComparator(context);
    }

    private void getContentAnnotations(Intent intent) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(Intent.EXTRA_CONTENT_ANNOTATIONS);
        if (stringArrayListExtra != null) {
            int size = stringArrayListExtra.size();
            if (size > 3) {
                size = 3;
            }
            this.mAnnotations = new String[size];
            for (int i = 0; i < size; i++) {
                this.mAnnotations[i] = stringArrayListExtra.get(i);
            }
        }
    }

    void setCallBack(AfterCompute afterCompute) {
        this.mAfterCompute = afterCompute;
    }

    protected final void afterCompute() {
        AfterCompute afterCompute = this.mAfterCompute;
        if (afterCompute != null) {
            afterCompute.afterCompute();
        }
    }

    @Override // java.util.Comparator
    public final int compare(ResolverActivity.ResolvedComponentInfo resolvedComponentInfo, ResolverActivity.ResolvedComponentInfo resolvedComponentInfo2) {
        boolean zIsSpecificUriMatch;
        ResolveInfo resolveInfoAt = resolvedComponentInfo.getResolveInfoAt(0);
        ResolveInfo resolveInfoAt2 = resolvedComponentInfo2.getResolveInfoAt(0);
        if (resolveInfoAt.targetUserId != -2) {
            return resolveInfoAt2.targetUserId != -2 ? 0 : 1;
        }
        if (resolveInfoAt2.targetUserId != -2) {
            return -1;
        }
        if (this.mHttp && (zIsSpecificUriMatch = ResolverActivity.isSpecificUriMatch(resolveInfoAt.match)) != ResolverActivity.isSpecificUriMatch(resolveInfoAt2.match)) {
            return zIsSpecificUriMatch ? -1 : 1;
        }
        boolean zIsPinned = resolvedComponentInfo.isPinned();
        boolean zIsPinned2 = resolvedComponentInfo2.isPinned();
        if (zIsPinned && !zIsPinned2) {
            return -1;
        }
        if (!zIsPinned && zIsPinned2) {
            return 1;
        }
        if (zIsPinned && zIsPinned2) {
            return this.mAzComparator.compare(resolvedComponentInfo.getResolveInfoAt(0), resolvedComponentInfo2.getResolveInfoAt(0));
        }
        return compare(resolveInfoAt, resolveInfoAt2);
    }

    final void compute(List<ResolverActivity.ResolvedComponentInfo> list) {
        beforeCompute();
        doCompute(list);
    }

    final void updateChooserCounts(String str, int i, String str2) {
        UsageStatsManager usageStatsManager = this.mUsm;
        if (usageStatsManager != null) {
            usageStatsManager.reportChooserSelection(str, i, this.mContentType, this.mAnnotations, str2);
        }
    }

    void beforeCompute() {
        Log.d(TAG, "Setting watchdog timer for 500ms");
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.d(TAG, "Error: Handler is Null; Needs to be initialized.");
        } else {
            handler.sendEmptyMessageDelayed(1, 500L);
        }
    }

    void destroy() {
        this.mHandler.removeMessages(0);
        this.mHandler.removeMessages(1);
        afterCompute();
        this.mAfterCompute = null;
    }

    class AzInfoComparator implements Comparator<ResolveInfo> {
        Collator mCollator;

        AzInfoComparator(Context context) {
            this.mCollator = Collator.getInstance(context.getResources().getConfiguration().locale);
        }

        @Override // java.util.Comparator
        public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            if (resolveInfo == null) {
                return -1;
            }
            if (resolveInfo2 == null) {
                return 1;
            }
            return this.mCollator.compare(resolveInfo.activityInfo.packageName, resolveInfo2.activityInfo.packageName);
        }
    }
}
