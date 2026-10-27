package com.neusoft.na.mots;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.controller.domain.Domains;
import com.neusoft.na.common.Tool;
import com.neusoft.na.ocservice.MotsService;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;

/* JADX INFO: compiled from: ConditionMonitoring.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 82\u00020\u0001:\u00018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u00100\u001a\u000201J\u000e\u0010!\u001a\u0002012\u0006\u00102\u001a\u00020\u0013J\b\u00103\u001a\u000201H\u0002J\b\u00104\u001a\u000201H\u0002J\u0006\u00105\u001a\u000201J\b\u00106\u001a\u000201H\u0002J\u0006\u00107\u001a\u000201R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/neusoft/na/mots/ConditionMonitoring;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "BL", "", "CL", "Guard", Domains.HMI, "OC", "TAG", "blPid", "", "clPid", "getContext", "()Landroid/content/Context;", "hmiPid", "isMonitoring", "", "()Z", "setMonitoring", "(Z)V", "lastBlActive", "lastClActive", "lastHmiActive", "lastOcActive", "mActivityManager", "Landroid/app/ActivityManager;", "getMActivityManager", "()Landroid/app/ActivityManager;", "setMActivityManager", "(Landroid/app/ActivityManager;)V", "mainWindowActive", "monitorRunnable", "Ljava/lang/Runnable;", "monitorThread", "Ljava/lang/Thread;", "getMonitorThread", "()Ljava/lang/Thread;", "setMonitorThread", "(Ljava/lang/Thread;)V", "ocPid", "resetOccurred", "service_process_guard", "service_process_motsbl", "service_process_onecore", "service_process_telematics", "killActivity", "", "active", "reSet", "sendPreloadingBC", "startMonitoring", "startPreloading", "stopMonitoring", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ConditionMonitoring {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static ConditionMonitoring instance;
    private String BL;
    private String CL;
    private String Guard;
    private String HMI;
    private String OC;
    private final String TAG;
    private int blPid;
    private int clPid;
    private final Context context;
    private int hmiPid;
    private boolean isMonitoring;
    private boolean lastBlActive;
    private boolean lastClActive;
    private boolean lastHmiActive;
    private boolean lastOcActive;
    private ActivityManager mActivityManager;
    private boolean mainWindowActive;
    private final Runnable monitorRunnable;
    private Thread monitorThread;
    private int ocPid;
    private boolean resetOccurred;
    private final String service_process_guard;
    private final String service_process_motsbl;
    private final String service_process_onecore;
    private final String service_process_telematics;

    /* JADX INFO: compiled from: ConditionMonitoring.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/neusoft/na/mots/ConditionMonitoring$Companion;", "Lmu/KLogging;", "()V", "instance", "Lcom/neusoft/na/mots/ConditionMonitoring;", "getInstance", "context", "Landroid/content/Context;", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConditionMonitoring getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ConditionMonitoring.instance == null) {
                ConditionMonitoring.instance = new ConditionMonitoring(context);
            }
            return ConditionMonitoring.instance;
        }
    }

    public ConditionMonitoring(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "ConditionMonitoring";
        this.BL = "";
        this.OC = "";
        this.CL = "";
        this.HMI = "";
        this.Guard = "";
        this.service_process_motsbl = ":Service";
        this.service_process_onecore = ":OneCore";
        this.service_process_telematics = ":Telematics";
        this.service_process_guard = "Guard";
        this.blPid = -1;
        this.ocPid = -1;
        this.clPid = -1;
        this.hmiPid = -1;
        Log.i("ConditionMonitoring", "init");
        this.BL = "com.neusoft.na.navigation:Service";
        this.OC = "com.neusoft.na.navigation:OneCore";
        this.CL = "com.neusoft.na.navigation:Telematics";
        this.HMI = "com.neusoft.na.navigation";
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        this.mActivityManager = (ActivityManager) systemService;
        this.monitorRunnable = new Runnable() { // from class: com.neusoft.na.mots.ConditionMonitoring$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                ConditionMonitoring.monitorRunnable$lambda$0(this.f$0);
            }
        };
    }

    public final Context getContext() {
        return this.context;
    }

    public final ActivityManager getMActivityManager() {
        return this.mActivityManager;
    }

    public final void setMActivityManager(ActivityManager activityManager) {
        this.mActivityManager = activityManager;
    }

    /* JADX INFO: renamed from: isMonitoring, reason: from getter */
    public final boolean getIsMonitoring() {
        return this.isMonitoring;
    }

    public final void setMonitoring(boolean z) {
        this.isMonitoring = z;
    }

    public final Thread getMonitorThread() {
        return this.monitorThread;
    }

    public final void setMonitorThread(Thread thread) {
        this.monitorThread = thread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void monitorRunnable$lambda$0(ConditionMonitoring this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(this$0.TAG, "monitorThread:start");
        while (this$0.isMonitoring) {
            Thread.sleep(5000L);
            if (!this$0.isMonitoring) {
                Log.i(this$0.TAG, "break monitor loop");
                return;
            }
            new ArrayList();
            ActivityManager activityManager = this$0.mActivityManager;
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
            Intrinsics.checkNotNull(runningAppProcesses, "null cannot be cast to non-null type kotlin.collections.List<android.app.ActivityManager.RunningAppProcessInfo>");
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Intrinsics.areEqual(runningAppProcessInfo.processName, this$0.HMI)) {
                    if (this$0.hmiPid == -1) {
                        this$0.hmiPid = runningAppProcessInfo.pid;
                    }
                    if (this$0.hmiPid != runningAppProcessInfo.pid) {
                        Log.e(this$0.TAG, "reSet hmiPid: Change " + this$0.hmiPid + " --> " + runningAppProcessInfo.pid);
                        z = true;
                    }
                    Log.i(this$0.TAG, "monitor name [" + runningAppProcessInfo.processName + "]:processId : [" + runningAppProcessInfo.pid + "] :Cpu:[" + Tool.getPidCPURateDesc(String.valueOf(runningAppProcessInfo.pid)) + ']');
                    z2 = true;
                }
                if (Intrinsics.areEqual(runningAppProcessInfo.processName, this$0.BL)) {
                    if (this$0.blPid == -1) {
                        this$0.blPid = runningAppProcessInfo.pid;
                    }
                    if (this$0.blPid != runningAppProcessInfo.pid) {
                        Log.e(this$0.TAG, "reSet blPid: Change " + this$0.blPid + " --> " + runningAppProcessInfo.pid);
                        z = true;
                    }
                    Log.i(this$0.TAG, "monitor name [" + runningAppProcessInfo.processName + "]:processId : [" + runningAppProcessInfo.pid + "] :Cpu:[" + Tool.getPidCPURateDesc(String.valueOf(runningAppProcessInfo.pid)) + ']');
                    z3 = true;
                }
                if (Intrinsics.areEqual(runningAppProcessInfo.processName, this$0.OC)) {
                    if (this$0.ocPid == -1) {
                        this$0.ocPid = runningAppProcessInfo.pid;
                    }
                    if (this$0.ocPid != runningAppProcessInfo.pid) {
                        Log.e(this$0.TAG, "reSet ocPid: Change " + this$0.ocPid + " --> " + runningAppProcessInfo.pid);
                        z = true;
                    }
                    Log.i(this$0.TAG, "monitor name [" + runningAppProcessInfo.processName + "]:processId : [" + runningAppProcessInfo.pid + "] :Cpu:[" + Tool.getPidCPURateDesc(String.valueOf(runningAppProcessInfo.pid)) + ']');
                    z4 = true;
                }
                if (Intrinsics.areEqual(runningAppProcessInfo.processName, this$0.CL)) {
                    if (this$0.clPid == -1) {
                        this$0.clPid = runningAppProcessInfo.pid;
                    }
                    if (this$0.clPid != runningAppProcessInfo.pid) {
                        Log.e(this$0.TAG, "reSet clPid: Change " + this$0.clPid + " --> " + runningAppProcessInfo.pid);
                        z = true;
                    }
                    Log.i(this$0.TAG, "monitor name [" + runningAppProcessInfo.processName + "]:processId : [" + runningAppProcessInfo.pid + "] :Cpu:[" + Tool.getPidCPURateDesc(String.valueOf(runningAppProcessInfo.pid)) + ']');
                    z5 = true;
                }
            }
            if (!z) {
                if (this$0.lastHmiActive && !z2) {
                    Log.e(this$0.TAG, "lastHmiActive && !hmiActive");
                    z = true;
                } else {
                    this$0.lastHmiActive = z2;
                }
                if (this$0.lastBlActive && !z3) {
                    Log.e(this$0.TAG, "lastBlActive && !blActive");
                    z = true;
                } else {
                    this$0.lastBlActive = z3;
                }
                if (this$0.lastOcActive && !z4) {
                    Log.e(this$0.TAG, "lastOcActive && !ocActive");
                    z = true;
                } else {
                    this$0.lastOcActive = z4;
                }
                if (this$0.lastClActive && !z5) {
                    Log.e(this$0.TAG, "lastClActive && !clActive");
                    z = true;
                } else {
                    this$0.lastClActive = z5;
                }
            }
            if (!this$0.isMonitoring) {
                this$0.stopMonitoring();
                return;
            } else if (z) {
                this$0.reSet();
            }
        }
    }

    public final void startMonitoring() {
        Log.i(this.TAG, "startMonitoring");
        if (this.resetOccurred) {
            Log.i(this.TAG, "resetOccurred" + this.resetOccurred);
            Process.killProcess(Process.myPid());
        } else {
            if (this.isMonitoring) {
                return;
            }
            this.isMonitoring = true;
            Thread thread = new Thread(this.monitorRunnable);
            this.monitorThread = thread;
            thread.start();
        }
    }

    public final void stopMonitoring() {
        Log.i(this.TAG, "stopMonitoring");
        this.isMonitoring = false;
        this.blPid = -1;
        this.ocPid = -1;
        this.clPid = -1;
        this.hmiPid = -1;
        this.resetOccurred = false;
        this.lastHmiActive = false;
        this.lastBlActive = false;
        this.lastOcActive = false;
        this.lastClActive = false;
    }

    private final void reSet() throws InterruptedException {
        Log.e(this.TAG, "reSet");
        stopMonitoring();
        this.resetOccurred = true;
        if (this.mainWindowActive) {
            killActivity();
        } else {
            startPreloading();
        }
    }

    private final void startPreloading() throws InterruptedException {
        Log.i(this.TAG, "startPreloading");
        Object systemService = this.context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid != Process.myPid() && (Intrinsics.areEqual(runningAppProcessInfo.processName, this.BL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.OC) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.CL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.HMI))) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        Thread.sleep(300L);
        sendPreloadingBC();
    }

    private final void sendPreloadingBC() {
        Log.i(this.TAG, "sendPreloadingBC");
        if (!CheckBoardKt.isGreatwallV61()) {
            this.context.startForegroundService(new Intent(this.context, (Class<?>) MotsMainService.class));
            this.context.startForegroundService(new Intent(this.context, (Class<?>) MotsService.class));
        } else {
            this.context.startService(new Intent(this.context, (Class<?>) MotsMainService.class));
            this.context.startService(new Intent(this.context, (Class<?>) MotsService.class));
        }
    }

    public final void killActivity() throws InterruptedException {
        Log.i(this.TAG, "[killActivity]");
        INSTANCE.getLogger().debug("killActivity");
        Object systemService = this.context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) systemService).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid != Process.myPid() && (Intrinsics.areEqual(runningAppProcessInfo.processName, this.BL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.OC) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.CL) || Intrinsics.areEqual(runningAppProcessInfo.processName, this.HMI))) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        Thread.sleep(300L);
        if (CheckBoardKt.isByd8155f()) {
            Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(268435456);
            }
            this.context.startActivity(launchIntentForPackage);
            PendingIntent activity = PendingIntent.getActivity(this.context, 0, launchIntentForPackage, 268435456);
            Object systemService2 = this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.app.AlarmManager");
            ((AlarmManager) systemService2).set(1, System.currentTimeMillis() + ((long) 500), activity);
        } else {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.neusoft.na.navigation", "com.neusoft.na.mots.MainActivity"));
            intent.setFlags(268435456);
            this.context.startActivity(intent);
        }
        Thread.sleep(100L);
        Process.killProcess(Process.myPid());
    }

    public final void mainWindowActive(boolean active) {
        this.mainWindowActive = active;
    }
}
