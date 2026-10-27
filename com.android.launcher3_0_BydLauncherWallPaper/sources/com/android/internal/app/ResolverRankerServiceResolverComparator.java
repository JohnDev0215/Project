package com.android.internal.app;

import android.app.usage.UsageStats;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.metrics.LogMaker;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.resolver.IResolverRankerResult;
import android.service.resolver.IResolverRankerService;
import android.service.resolver.ResolverRankerService;
import android.service.resolver.ResolverTarget;
import android.util.Log;
import com.android.internal.app.AbstractResolverComparator;
import com.android.internal.app.ResolverActivity;
import com.android.internal.logging.MetricsLogger;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
class ResolverRankerServiceResolverComparator extends AbstractResolverComparator {
    private static final int CONNECTION_COST_TIMEOUT_MILLIS = 200;
    private static final boolean DEBUG = false;
    private static final float RECENCY_MULTIPLIER = 2.0f;
    private static final long RECENCY_TIME_PERIOD = 43200000;
    private static final String TAG = "RRSResolverComparator";
    private static final long USAGE_STATS_PERIOD = 604800000;
    private String mAction;
    private final Collator mCollator;
    private CountDownLatch mConnectSignal;
    private ResolverRankerServiceConnection mConnection;
    private Context mContext;
    private final long mCurrentTime;
    private final Object mLock;
    private IResolverRankerService mRanker;
    private ComponentName mRankerServiceName;
    private final String mReferrerPackage;
    private ComponentName mResolvedRankerName;
    private final long mSinceTime;
    private final Map<String, UsageStats> mStats;
    private ArrayList<ResolverTarget> mTargets;
    private final LinkedHashMap<ComponentName, ResolverTarget> mTargetsDict;

    public ResolverRankerServiceResolverComparator(Context context, Intent intent, String str, AbstractResolverComparator.AfterCompute afterCompute) {
        super(context, intent);
        this.mTargetsDict = new LinkedHashMap<>();
        this.mLock = new Object();
        this.mCollator = Collator.getInstance(context.getResources().getConfiguration().locale);
        this.mReferrerPackage = str;
        this.mContext = context;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.mCurrentTime = jCurrentTimeMillis;
        long j = jCurrentTimeMillis - 604800000;
        this.mSinceTime = j;
        this.mStats = this.mUsm.queryAndAggregateUsageStats(j, jCurrentTimeMillis);
        this.mAction = intent.getAction();
        this.mRankerServiceName = new ComponentName(this.mContext, getClass());
        setCallBack(afterCompute);
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    public void handleResultMessage(Message message) {
        if (message.what != 0) {
            return;
        }
        if (message.obj == null) {
            Log.e(TAG, "Receiving null prediction results.");
            return;
        }
        List list = (List) message.obj;
        if (list != null && this.mTargets != null && list.size() == this.mTargets.size()) {
            int size = this.mTargets.size();
            boolean z = false;
            for (int i = 0; i < size; i++) {
                float selectProbability = ((ResolverTarget) list.get(i)).getSelectProbability();
                if (selectProbability != this.mTargets.get(i).getSelectProbability()) {
                    this.mTargets.get(i).setSelectProbability(selectProbability);
                    z = true;
                }
            }
            if (z) {
                this.mRankerServiceName = this.mResolvedRankerName;
                return;
            }
            return;
        }
        Log.e(TAG, "Sizes of sent and received ResolverTargets diff.");
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    public void doCompute(List<ResolverActivity.ResolvedComponentInfo> list) {
        long j;
        long j2 = this.mCurrentTime - 43200000;
        float f = 1.0f;
        float f2 = 1.0f;
        float f3 = 1.0f;
        float f4 = 1.0f;
        for (ResolverActivity.ResolvedComponentInfo resolvedComponentInfo : list) {
            ResolverTarget resolverTarget = new ResolverTarget();
            this.mTargetsDict.put(resolvedComponentInfo.name, resolverTarget);
            UsageStats usageStats = this.mStats.get(resolvedComponentInfo.name.getPackageName());
            if (usageStats != null) {
                if (!resolvedComponentInfo.name.getPackageName().equals(this.mReferrerPackage) && !isPersistentProcess(resolvedComponentInfo)) {
                    float fMax = Math.max(usageStats.getLastTimeUsed() - j2, 0L);
                    resolverTarget.setRecencyScore(fMax);
                    if (fMax > f) {
                        f = fMax;
                    }
                }
                float totalTimeInForeground = usageStats.getTotalTimeInForeground();
                resolverTarget.setTimeSpentScore(totalTimeInForeground);
                if (totalTimeInForeground > f2) {
                    f2 = totalTimeInForeground;
                }
                float f5 = usageStats.mLaunchCount;
                resolverTarget.setLaunchScore(f5);
                if (f5 > f3) {
                    f3 = f5;
                }
                float fIntValue = 0.0f;
                if (usageStats.mChooserCounts != null && this.mAction != null && usageStats.mChooserCounts.get(this.mAction) != null) {
                    fIntValue = usageStats.mChooserCounts.get(this.mAction).getOrDefault(this.mContentType, 0).intValue();
                    if (this.mAnnotations != null) {
                        int i = 0;
                        while (i < this.mAnnotations.length) {
                            fIntValue += usageStats.mChooserCounts.get(this.mAction).getOrDefault(this.mAnnotations[i], 0).intValue();
                            i++;
                            j2 = j2;
                        }
                    }
                }
                j = j2;
                resolverTarget.setChooserScore(fIntValue);
                if (fIntValue > f4) {
                    f4 = fIntValue;
                }
            } else {
                j = j2;
            }
            j2 = j;
        }
        ArrayList<ResolverTarget> arrayList = new ArrayList<>(this.mTargetsDict.values());
        this.mTargets = arrayList;
        for (ResolverTarget resolverTarget2 : arrayList) {
            float recencyScore = resolverTarget2.getRecencyScore() / f;
            setFeatures(resolverTarget2, recencyScore * recencyScore * RECENCY_MULTIPLIER, resolverTarget2.getLaunchScore() / f3, resolverTarget2.getTimeSpentScore() / f2, resolverTarget2.getChooserScore() / f4);
            addDefaultSelectProbability(resolverTarget2);
        }
        predictSelectProbabilities(this.mTargets);
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    public int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
        int iCompare;
        if (this.mStats != null) {
            ResolverTarget resolverTarget = this.mTargetsDict.get(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            ResolverTarget resolverTarget2 = this.mTargetsDict.get(new ComponentName(resolveInfo2.activityInfo.packageName, resolveInfo2.activityInfo.name));
            if (resolverTarget != null && resolverTarget2 != null && (iCompare = Float.compare(resolverTarget2.getSelectProbability(), resolverTarget.getSelectProbability())) != 0) {
                return iCompare > 0 ? 1 : -1;
            }
        }
        CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(this.mPm);
        if (charSequenceLoadLabel == null) {
            charSequenceLoadLabel = resolveInfo.activityInfo.name;
        }
        CharSequence charSequenceLoadLabel2 = resolveInfo2.loadLabel(this.mPm);
        if (charSequenceLoadLabel2 == null) {
            charSequenceLoadLabel2 = resolveInfo2.activityInfo.name;
        }
        return this.mCollator.compare(charSequenceLoadLabel.toString().trim(), charSequenceLoadLabel2.toString().trim());
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    public float getScore(ComponentName componentName) {
        ResolverTarget resolverTarget = this.mTargetsDict.get(componentName);
        if (resolverTarget != null) {
            return resolverTarget.getSelectProbability();
        }
        return 0.0f;
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    List<ComponentName> getTopComponentNames(int i) {
        return (List) this.mTargetsDict.entrySet().stream().sorted(new Comparator() { // from class: com.android.internal.app.-$$Lambda$ResolverRankerServiceResolverComparator$3RX-rlVncHq5p8XgTs_ukiRFf70
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.lambda$getTopComponentNames$0$ResolverRankerServiceResolverComparator((Map.Entry) obj, (Map.Entry) obj2);
            }
        }).limit(i).map($$Lambda$lrglEmXJyu4r690vPrMCeVxMVk.INSTANCE).collect(Collectors.toList());
    }

    public /* synthetic */ int lambda$getTopComponentNames$0$ResolverRankerServiceResolverComparator(Map.Entry entry, Map.Entry entry2) {
        return -Float.compare(getScore((ComponentName) entry.getKey()), getScore((ComponentName) entry2.getKey()));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0062 A[Catch: all -> 0x0064, DONT_GENERATE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0018, B:10:0x001c, B:11:0x0027, B:13:0x002d, B:15:0x003b, B:16:0x003e, B:20:0x0062, B:19:0x004a), top: B:25:0x0003, inners: #1 }] */
    @Override // com.android.internal.app.AbstractResolverComparator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateModel(android.content.ComponentName r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            android.service.resolver.IResolverRankerService r1 = r5.mRanker     // Catch: java.lang.Throwable -> L64
            if (r1 == 0) goto L62
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            java.util.LinkedHashMap<android.content.ComponentName, android.service.resolver.ResolverTarget> r2 = r5.mTargetsDict     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            java.util.Set r2 = r2.keySet()     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            r1.<init>(r2)     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            int r1 = r1.indexOf(r6)     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            if (r1 < 0) goto L62
            java.util.ArrayList<android.service.resolver.ResolverTarget> r2 = r5.mTargets     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            if (r2 == 0) goto L62
            float r6 = r5.getScore(r6)     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            r2 = 0
            java.util.ArrayList<android.service.resolver.ResolverTarget> r3 = r5.mTargets     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            java.util.Iterator r3 = r3.iterator()     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
        L27:
            boolean r4 = r3.hasNext()     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            if (r4 == 0) goto L3e
            java.lang.Object r4 = r3.next()     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            android.service.resolver.ResolverTarget r4 = (android.service.resolver.ResolverTarget) r4     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            float r4 = r4.getSelectProbability()     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 <= 0) goto L27
            int r2 = r2 + 1
            goto L27
        L3e:
            r5.logMetrics(r2)     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            android.service.resolver.IResolverRankerService r6 = r5.mRanker     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            java.util.ArrayList<android.service.resolver.ResolverTarget> r5 = r5.mTargets     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            r6.train(r5, r1)     // Catch: android.os.RemoteException -> L49 java.lang.Throwable -> L64
            goto L62
        L49:
            r5 = move-exception
            java.lang.String r6 = "RRSResolverComparator"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L64
            r1.<init>()     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "Error in Train: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L64
            java.lang.StringBuilder r5 = r1.append(r5)     // Catch: java.lang.Throwable -> L64
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L64
            android.util.Log.e(r6, r5)     // Catch: java.lang.Throwable -> L64
        L62:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
            return
        L64:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.ResolverRankerServiceResolverComparator.updateModel(android.content.ComponentName):void");
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    public void destroy() {
        this.mHandler.removeMessages(0);
        this.mHandler.removeMessages(1);
        ResolverRankerServiceConnection resolverRankerServiceConnection = this.mConnection;
        if (resolverRankerServiceConnection != null) {
            this.mContext.unbindService(resolverRankerServiceConnection);
            this.mConnection.destroy();
        }
        afterCompute();
    }

    private void logMetrics(int i) {
        if (this.mRankerServiceName != null) {
            MetricsLogger metricsLogger = new MetricsLogger();
            LogMaker logMaker = new LogMaker(1085);
            logMaker.setComponentName(this.mRankerServiceName);
            logMaker.addTaggedData(1086, Integer.valueOf(this.mAnnotations == null ? 0 : 1));
            logMaker.addTaggedData(1087, Integer.valueOf(i));
            metricsLogger.write(logMaker);
        }
    }

    private void initRanker(Context context) {
        synchronized (this.mLock) {
            if (this.mConnection == null || this.mRanker == null) {
                Intent intentResolveRankerService = resolveRankerService();
                if (intentResolveRankerService == null) {
                    return;
                }
                this.mConnectSignal = new CountDownLatch(1);
                ResolverRankerServiceConnection resolverRankerServiceConnection = new ResolverRankerServiceConnection(this.mConnectSignal);
                this.mConnection = resolverRankerServiceConnection;
                context.bindServiceAsUser(intentResolveRankerService, resolverRankerServiceConnection, 1, UserHandle.SYSTEM);
            }
        }
    }

    private Intent resolveRankerService() {
        Intent intent = new Intent(ResolverRankerService.SERVICE_INTERFACE);
        for (ResolveInfo resolveInfo : this.mPm.queryIntentServices(intent, 0)) {
            if (resolveInfo != null && resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.applicationInfo != null) {
                ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.applicationInfo.packageName, resolveInfo.serviceInfo.name);
                try {
                    if (!"android.permission.BIND_RESOLVER_RANKER_SERVICE".equals(this.mPm.getServiceInfo(componentName, 0).permission)) {
                        Log.w(TAG, "ResolverRankerService " + componentName + " does not require permission android.permission.BIND_RESOLVER_RANKER_SERVICE - this service will not be queried for ResolverRankerServiceResolverComparator. add android:permission=\"android.permission.BIND_RESOLVER_RANKER_SERVICE\" to the <service> tag for " + componentName + " in the manifest.");
                    } else if (this.mPm.checkPermission("android.permission.PROVIDE_RESOLVER_RANKER_SERVICE", resolveInfo.serviceInfo.packageName) != 0) {
                        Log.w(TAG, "ResolverRankerService " + componentName + " does not hold permission android.permission.PROVIDE_RESOLVER_RANKER_SERVICE - this service will not be queried for ResolverRankerServiceResolverComparator.");
                    } else {
                        this.mResolvedRankerName = componentName;
                        intent.setComponent(componentName);
                        return intent;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e(TAG, "Could not look up service " + componentName + "; component name not found");
                }
            }
        }
        return null;
    }

    private class ResolverRankerServiceConnection implements ServiceConnection {
        private final CountDownLatch mConnectSignal;
        public final IResolverRankerResult resolverRankerResult = new IResolverRankerResult.Stub() { // from class: com.android.internal.app.ResolverRankerServiceResolverComparator.ResolverRankerServiceConnection.1
            @Override // android.service.resolver.IResolverRankerResult
            public void sendResult(List<ResolverTarget> list) throws RemoteException {
                synchronized (ResolverRankerServiceResolverComparator.this.mLock) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 0;
                    messageObtain.obj = list;
                    ResolverRankerServiceResolverComparator.this.mHandler.sendMessage(messageObtain);
                }
            }
        };

        public ResolverRankerServiceConnection(CountDownLatch countDownLatch) {
            this.mConnectSignal = countDownLatch;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (ResolverRankerServiceResolverComparator.this.mLock) {
                ResolverRankerServiceResolverComparator.this.mRanker = IResolverRankerService.Stub.asInterface(iBinder);
                this.mConnectSignal.countDown();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (ResolverRankerServiceResolverComparator.this.mLock) {
                destroy();
            }
        }

        public void destroy() {
            synchronized (ResolverRankerServiceResolverComparator.this.mLock) {
                ResolverRankerServiceResolverComparator.this.mRanker = null;
            }
        }
    }

    @Override // com.android.internal.app.AbstractResolverComparator
    void beforeCompute() {
        super.beforeCompute();
        this.mTargetsDict.clear();
        this.mTargets = null;
        this.mRankerServiceName = new ComponentName(this.mContext, getClass());
        this.mResolvedRankerName = null;
        initRanker(this.mContext);
    }

    private void predictSelectProbabilities(List<ResolverTarget> list) {
        if (this.mConnection != null) {
            try {
                this.mConnectSignal.await(200L, TimeUnit.MILLISECONDS);
                synchronized (this.mLock) {
                    IResolverRankerService iResolverRankerService = this.mRanker;
                    if (iResolverRankerService != null) {
                        iResolverRankerService.predict(list, this.mConnection.resolverRankerResult);
                        return;
                    }
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Error in Predict: " + e);
            } catch (InterruptedException unused) {
                Log.e(TAG, "Error in Wait for Service Connection.");
            }
        }
        afterCompute();
    }

    private void addDefaultSelectProbability(ResolverTarget resolverTarget) {
        resolverTarget.setSelectProbability((float) (1.0d / (Math.exp(1.6568f - ((((resolverTarget.getLaunchScore() * 2.5543f) + (resolverTarget.getTimeSpentScore() * 2.8412f)) + (resolverTarget.getRecencyScore() * 0.269f)) + (resolverTarget.getChooserScore() * 4.2222f))) + 1.0d)));
    }

    private void setFeatures(ResolverTarget resolverTarget, float f, float f2, float f3, float f4) {
        resolverTarget.setRecencyScore(f);
        resolverTarget.setLaunchScore(f2);
        resolverTarget.setTimeSpentScore(f3);
        resolverTarget.setChooserScore(f4);
    }

    static boolean isPersistentProcess(ResolverActivity.ResolvedComponentInfo resolvedComponentInfo) {
        return (resolvedComponentInfo == null || resolvedComponentInfo.getCount() <= 0 || (resolvedComponentInfo.getResolveInfoAt(0).activityInfo.applicationInfo.flags & 8) == 0) ? false : true;
    }
}
