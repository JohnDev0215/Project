package com.android.internal.app;

import android.app.prediction.AppPredictor;
import android.app.prediction.AppTarget;
import android.app.prediction.AppTargetEvent;
import android.app.prediction.AppTargetId;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Message;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.internal.app.AbstractResolverComparator;
import com.android.internal.app.ResolverActivity;
import com.android.internal.config.sysui.SystemUiDeviceConfigFlags;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
class AppPredictionServiceResolverComparator extends AbstractResolverComparator {
    private static final String TAG = "APSResolverComparator";
    private final AppPredictor mAppPredictor;
    private boolean mAppendDirectShareEnabled;
    private final Context mContext;
    private final Intent mIntent;
    private final String mReferrerPackage;
    private ResolverRankerServiceResolverComparator mResolverRankerService;
    private final Map<ComponentName, Integer> mTargetRanks;
    private final Map<ComponentName, Integer> mTargetScores;
    private final UserHandle mUser;

    AppPredictionServiceResolverComparator(Context context, Intent intent, String str, AppPredictor appPredictor, UserHandle userHandle) {
        super(context, intent);
        this.mTargetRanks = new HashMap();
        this.mTargetScores = new HashMap();
        this.mAppendDirectShareEnabled = DeviceConfig.getBoolean(DeviceConfig.NAMESPACE_SYSTEMUI, SystemUiDeviceConfigFlags.APPEND_DIRECT_SHARE_ENABLED, true);
        this.mContext = context;
        this.mIntent = intent;
        this.mAppPredictor = appPredictor;
        this.mUser = userHandle;
        this.mReferrerPackage = str;
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.compare(resolveInfo, resolveInfo2);
        }
        Integer num = this.mTargetRanks.get(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        Integer num2 = this.mTargetRanks.get(new ComponentName(resolveInfo2.activityInfo.packageName, resolveInfo2.activityInfo.name));
        if (num == null && num2 == null) {
            return 0;
        }
        if (num == null) {
            return -1;
        }
        if (num2 == null) {
            return 1;
        }
        return num.intValue() - num2.intValue();
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void doCompute(final List<ResolverActivity.ResolvedComponentInfo> list) {
        if (list.isEmpty()) {
            this.mHandler.sendEmptyMessage(0);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ResolverActivity.ResolvedComponentInfo resolvedComponentInfo : list) {
            arrayList.add(new AppTarget.Builder(new AppTargetId(resolvedComponentInfo.name.flattenToString()), resolvedComponentInfo.name.getPackageName(), this.mUser).setClassName(resolvedComponentInfo.name.getClassName()).build());
        }
        this.mAppPredictor.sortTargets(arrayList, Executors.newSingleThreadExecutor(), new Consumer() { // from class: com.android.internal.app.-$$Lambda$AppPredictionServiceResolverComparator$PQ-i16vesHTtkDyBgU_HkS0uF1A
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$doCompute$1$AppPredictionServiceResolverComparator(list, (List) obj);
            }
        });
    }

    public /* synthetic */ void lambda$doCompute$1$AppPredictionServiceResolverComparator(List list, List list2) {
        if (list2.isEmpty()) {
            Log.i(TAG, "AppPredictionService disabled. Using resolver.");
            ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = new ResolverRankerServiceResolverComparator(this.mContext, this.mIntent, this.mReferrerPackage, new AbstractResolverComparator.AfterCompute() { // from class: com.android.internal.app.-$$Lambda$AppPredictionServiceResolverComparator$25gj8kU_BfxuxUXCZ0QzLVhZs9Y
                @Override // com.android.internal.app.AbstractResolverComparator.AfterCompute
                public final void afterCompute() {
                    this.f$0.lambda$doCompute$0$AppPredictionServiceResolverComparator();
                }
            });
            this.mResolverRankerService = resolverRankerServiceResolverComparator;
            resolverRankerServiceResolverComparator.compute(list);
            return;
        }
        Log.i(TAG, "AppPredictionService response received");
        handleResult(list2);
    }

    public /* synthetic */ void lambda$doCompute$0$AppPredictionServiceResolverComparator() {
        this.mHandler.sendEmptyMessage(0);
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void handleResultMessage(Message message) {
        if (message.what == 0 && message.obj != null) {
            handleSortedAppTargets((List) message.obj);
        } else if (message.obj == null && this.mResolverRankerService == null) {
            Log.e(TAG, "Unexpected null result");
        }
    }

    private void handleResult(List<AppTarget> list) {
        if (this.mHandler.hasMessages(1)) {
            handleSortedAppTargets(list);
            this.mHandler.removeMessages(1);
            afterCompute();
        }
    }

    private void handleSortedAppTargets(List<AppTarget> list) {
        if (checkAppTargetRankValid(list)) {
            list.forEach(new Consumer() { // from class: com.android.internal.app.-$$Lambda$AppPredictionServiceResolverComparator$2eH8BdPmQNaGw6ZzFc7QgsKf5CY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$handleSortedAppTargets$2$AppPredictionServiceResolverComparator((AppTarget) obj);
                }
            });
        }
        for (int i = 0; i < list.size(); i++) {
            ComponentName componentName = new ComponentName(list.get(i).getPackageName(), list.get(i).getClassName());
            this.mTargetRanks.put(componentName, Integer.valueOf(i));
            Log.i(TAG, "handleSortedAppTargets, sortedAppTargets #" + i + ": " + componentName);
        }
    }

    public /* synthetic */ void lambda$handleSortedAppTargets$2$AppPredictionServiceResolverComparator(AppTarget appTarget) {
        this.mTargetScores.put(new ComponentName(appTarget.getPackageName(), appTarget.getClassName()), Integer.valueOf(appTarget.getRank()));
    }

    private boolean checkAppTargetRankValid(List<AppTarget> list) {
        Iterator<AppTarget> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getRank() != 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    float getScore(ComponentName componentName) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.getScore(componentName);
        }
        if (this.mAppendDirectShareEnabled && !this.mTargetScores.isEmpty()) {
            return this.mTargetScores.get(componentName).intValue();
        }
        if (this.mTargetRanks.get(componentName) == null) {
            Log.w(TAG, "Score requested for unknown component. Did you call compute yet?");
            return 0.0f;
        }
        return 1.0f - (r3.intValue() / (((this.mTargetRanks.size() - 1) * this.mTargetRanks.size()) / 2));
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    List<ComponentName> getTopComponentNames(int i) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            return resolverRankerServiceResolverComparator.getTopComponentNames(i);
        }
        return (List) this.mTargetRanks.entrySet().stream().sorted(Map.Entry.comparingByValue()).limit(i).map($$Lambda$vbUiOaY5W_R3oio_uxyQbdJwl0.INSTANCE).collect(Collectors.toList());
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void updateModel(ComponentName componentName) {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            resolverRankerServiceResolverComparator.updateModel(componentName);
        } else {
            this.mAppPredictor.notifyAppTargetEvent(new AppTargetEvent.Builder(new AppTarget.Builder(new AppTargetId(componentName.toString()), componentName.getPackageName(), this.mUser).setClassName(componentName.getClassName()).build(), 1).build());
        }
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void destroy() {
        ResolverRankerServiceResolverComparator resolverRankerServiceResolverComparator = this.mResolverRankerService;
        if (resolverRankerServiceResolverComparator != null) {
            resolverRankerServiceResolverComparator.destroy();
            this.mResolverRankerService = null;
        }
    }
}
