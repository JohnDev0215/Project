package com.amazon.mp3.skiplimit.impl;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.skiplimit.SkipLimitController;
import com.amazon.mp3.skiplimit.SkipLimitRefreshSource;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.mp3.util.Log;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HardCodedRefreshStrategy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/HardCodedRefreshStrategy;", "Lcom/amazon/mp3/skiplimit/SkipLimitRefreshSource;", "skipLimitController", "Lcom/amazon/mp3/skiplimit/SkipLimitController;", "context", "Landroid/content/Context;", "(Lcom/amazon/mp3/skiplimit/SkipLimitController;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getSkipLimitController", "()Lcom/amazon/mp3/skiplimit/SkipLimitController;", "clearSession", "", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "terminate", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HardCodedRefreshStrategy implements SkipLimitRefreshSource {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final int DEFAULT_COUNT_PER_INTERVAL = 6;
    public static final String HAS_START_REFRESH_KEY = "skip_limit_refresh_key";
    private static final int INTERVAL_IN_MINS;
    public static final int JOB_ID = 617;
    public static final String LAST_SKIP_REFRESH_TIMESTAMP = "skip_limit_updated_timestamp";
    public static final int MS_IN_MINUTE = 60000;
    public static final int REFRESH_INTERVAL_DEBUG = 5;
    public static final int REFRESH_INTERVAL_PROD = 60;
    public static final String REFRESH_REASON = "hard coded refresh period reached";
    public static final String SKIP_LIMIT_COUNT_KEY = "skip_limit_count_key";
    private static final String TAG;
    private static final Lazy<JobInfo> jobInfo$delegate;
    private final Context context;
    private final SkipLimitController skipLimitController;

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void clearSession() {
    }

    public HardCodedRefreshStrategy(SkipLimitController skipLimitController, Context context) {
        Intrinsics.checkNotNullParameter(skipLimitController, "skipLimitController");
        Intrinsics.checkNotNullParameter(context, "context");
        this.skipLimitController = skipLimitController;
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public SkipLimitController getSkipLimitController() {
        return this.skipLimitController;
    }

    /* JADX INFO: compiled from: HardCodedRefreshStrategy.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u0004J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0004H\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020\u001eJ\u0006\u0010#\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n \u0012*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/amazon/mp3/skiplimit/impl/HardCodedRefreshStrategy$Companion;", "", "()V", "DEFAULT_COUNT_PER_INTERVAL", "", "HAS_START_REFRESH_KEY", "", "INTERVAL_IN_MINS", "getINTERVAL_IN_MINS", "()I", "JOB_ID", "LAST_SKIP_REFRESH_TIMESTAMP", "MS_IN_MINUTE", "REFRESH_INTERVAL_DEBUG", "REFRESH_INTERVAL_PROD", "REFRESH_REASON", "SKIP_LIMIT_COUNT_KEY", "TAG", "kotlin.jvm.PlatformType", "jobInfo", "Landroid/app/job/JobInfo;", "getJobInfo", "()Landroid/app/job/JobInfo;", "jobInfo$delegate", "Lkotlin/Lazy;", "getCurrentSkipLimitCount", "getRefreshFrequency", "", "getSkipRefreshInterval", "onDecrementSkipCount", "", "startRecurringRefresh", "forceStart", "", "tryRefreshHardCodeLimitCount", "tryRefreshHardCodeLimitCountInBackground", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getSkipRefreshInterval() {
            return 60;
        }

        private Companion() {
        }

        public final int getINTERVAL_IN_MINS() {
            return HardCodedRefreshStrategy.INTERVAL_IN_MINS;
        }

        private final JobInfo getJobInfo() {
            Object value = HardCodedRefreshStrategy.jobInfo$delegate.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-jobInfo>(...)");
            return (JobInfo) value;
        }

        public final void startRecurringRefresh(boolean forceStart) {
            try {
                Object systemService = AmazonApplication.getContext().getSystemService("jobscheduler");
                if (systemService != null) {
                    JobScheduler jobScheduler = (JobScheduler) systemService;
                    List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
                    Intrinsics.checkNotNullExpressionValue(allPendingJobs, "jobScheduler.allPendingJobs");
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allPendingJobs, 10));
                    Iterator<T> it = allPendingJobs.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Integer.valueOf(((JobInfo) it.next()).getId()));
                    }
                    if (!arrayList.contains(Integer.valueOf(HardCodedRefreshStrategy.JOB_ID)) || forceStart) {
                        jobScheduler.schedule(getJobInfo());
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext());
                        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "sharedPreferences");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Intrinsics.checkNotNullExpressionValue(editor, "editor");
                        editor.putBoolean(HardCodedRefreshStrategy.HAS_START_REFRESH_KEY, true);
                        editor.apply();
                        return;
                    }
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.app.job.JobScheduler");
            } catch (NullPointerException e) {
                Log.error(HardCodedRefreshStrategy.TAG, "OEM crashes for job scheduler: ", e);
            } catch (Exception e2) {
                Log.error(HardCodedRefreshStrategy.TAG, "startRecurringRefresh job scheduler failed: ", e2);
            }
        }

        public final long getRefreshFrequency() {
            return ((long) getINTERVAL_IN_MINS()) * 60000;
        }

        public final void tryRefreshHardCodeLimitCount() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext());
            if (System.currentTimeMillis() - sharedPreferences.getLong(HardCodedRefreshStrategy.LAST_SKIP_REFRESH_TIMESTAMP, 0L) >= getRefreshFrequency()) {
                Intrinsics.checkNotNullExpressionValue(sharedPreferences, "sharedPreferences");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor, "editor");
                editor.putInt(HardCodedRefreshStrategy.SKIP_LIMIT_COUNT_KEY, 6);
                editor.apply();
                SharedPreferences.Editor editor2 = sharedPreferences.edit();
                Intrinsics.checkNotNullExpressionValue(editor2, "editor");
                editor2.putLong(HardCodedRefreshStrategy.LAST_SKIP_REFRESH_TIMESTAMP, System.currentTimeMillis());
                editor2.apply();
            }
            if (sharedPreferences.getBoolean(HardCodedRefreshStrategy.HAS_START_REFRESH_KEY, false)) {
                startRecurringRefresh(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: tryRefreshHardCodeLimitCountInBackground$lambda-4, reason: not valid java name */
        public static final void m1632tryRefreshHardCodeLimitCountInBackground$lambda4() {
            HardCodedRefreshStrategy.INSTANCE.tryRefreshHardCodeLimitCount();
        }

        public final void tryRefreshHardCodeLimitCountInBackground() {
            AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy$Companion$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HardCodedRefreshStrategy.Companion.m1632tryRefreshHardCodeLimitCountInBackground$lambda4();
                }
            });
        }

        public final int getCurrentSkipLimitCount() {
            return PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext()).getInt(HardCodedRefreshStrategy.SKIP_LIMIT_COUNT_KEY, 0);
        }

        public final void onDecrementSkipCount() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AmazonApplication.getContext());
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "sharedPreferences");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Intrinsics.checkNotNullExpressionValue(editor, "editor");
            editor.putInt(HardCodedRefreshStrategy.SKIP_LIMIT_COUNT_KEY, HardCodedRefreshStrategy.INSTANCE.getCurrentSkipLimitCount() - 1);
            editor.apply();
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        TAG = HardCodedRefreshStrategy.class.getSimpleName();
        INTERVAL_IN_MINS = companion.getSkipRefreshInterval();
        jobInfo$delegate = LazyKt__LazyJVMKt.lazy(new Function0<JobInfo>() { // from class: com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy$Companion$jobInfo$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final JobInfo invoke() {
                JobInfo.Builder builder = new JobInfo.Builder(HardCodedRefreshStrategy.JOB_ID, new ComponentName(AmazonApplication.getContext(), (Class<?>) RefreshSkipCountService.class));
                builder.setMinimumLatency(((long) HardCodedRefreshStrategy.INSTANCE.getINTERVAL_IN_MINS()) * 60000);
                builder.setPersisted(true);
                return builder.build();
            }
        });
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void start() {
        synchronized (this) {
            Log.info(TAG, "Starting HardCodedRefreshStrategy, refresh 6 skips every " + INTERVAL_IN_MINS + " minutes.");
            getSkipLimitController().onUpdateCount(INSTANCE.getCurrentSkipLimitCount(), REFRESH_REASON, getContext());
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.skiplimit.SkipLimitRefreshSource
    public void terminate() {
        Log.info(TAG, "Stopping HardCodedRefreshStrategy, refresh 6 skipsevery " + INTERVAL_IN_MINS + " minutes.");
        synchronized (this) {
            Object systemService = AmazonApplication.getContext().getSystemService("jobscheduler");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.job.JobScheduler");
            }
            ((JobScheduler) systemService).cancel(JOB_ID);
            Unit unit = Unit.INSTANCE;
        }
    }
}
