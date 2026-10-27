package com.android.permissioncontroller.permission.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.DumpableLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.SmartUpdateMediatorLiveData;
import com.android.permissioncontroller.permission.data.UnusedAutoRevokedPackagesLiveData;
import com.android.permissioncontroller.permission.ui.ManagePermissionsActivity;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: AutoRevokePermissions.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0017R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/android/permissioncontroller/permission/service/AutoRevokeService;", "Landroid/app/job/JobService;", "()V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "jobStartTime", "", "getJobStartTime", "()J", "setJobStartTime", "(J)V", "onStartJob", "", "params", "Landroid/app/job/JobParameters;", "onStopJob", "showAutoRevokeNotification", "", "sessionId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class AutoRevokeService extends JobService {
    public static final String SHOW_AUTO_REVOKE = "showAutoRevoke";
    private Job job;
    private long jobStartTime = -1;

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final long getJobStartTime() {
        return this.jobStartTime;
    }

    public final void setJobStartTime(long j) {
        this.jobStartTime = j;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters params) throws Throwable {
        DumpableLog.i$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "onStartJob", null, 4, null);
        if (AutoRevokePermissions.SKIP_NEXT_RUN) {
            AutoRevokePermissions.SKIP_NEXT_RUN = false;
            Log.i("AutoRevokePermissions", "Skipping auto revoke first run when scheduled by system");
            jobFinished(params, false);
            return true;
        }
        this.jobStartTime = System.currentTimeMillis();
        this.job = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(params, null), 2, null);
        return true;
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.service.AutoRevokeService$onStartJob$1, reason: invalid class name */
    /* JADX INFO: compiled from: AutoRevokePermissions.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.service.AutoRevokeService$onStartJob$1", f = "AutoRevokePermissions.kt", i = {0, 0, 1, 1, 1}, l = {577, 579}, m = "invokeSuspend", n = {"$this$launch", "sessionId", "$this$launch", "sessionId", "revokedApps"}, s = {"L$0", "J$0", "L$0", "J$0", "L$1"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ JobParameters $params;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(JobParameters jobParameters, Continuation continuation) {
            super(2, continuation);
            this.$params = jobParameters;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass1 anonymousClass1 = AutoRevokeService.this.new AnonymousClass1(this.$params, completion);
            anonymousClass1.p$ = (CoroutineScope) obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v11, types: [kotlinx.coroutines.CoroutineScope] */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r10v0 */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v8 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Exception e;
            ?? r0;
            Object obj2;
            long j;
            ?? r02;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
            } catch (Exception e2) {
                ?? r10 = r1;
                e = e2;
                r0 = r10;
            }
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                long jNextLong = 0;
                while (jNextLong == 0) {
                    jNextLong = new Random().nextLong();
                }
                AutoRevokeService autoRevokeService = AutoRevokeService.this;
                this.L$0 = coroutineScope;
                this.J$0 = jNextLong;
                this.label = 1;
                Object objRevokePermissionsOnUnusedApps = AutoRevokePermissions.revokePermissionsOnUnusedApps(autoRevokeService, jNextLong, this);
                if (objRevokePermissionsOnUnusedApps == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long j2 = jNextLong;
                obj2 = objRevokePermissionsOnUnusedApps;
                j = j2;
                r1 = coroutineScope;
            } else {
                if (r1 != 1) {
                    if (r1 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j3 = this.J$0;
                    r0 = (CoroutineScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        r02 = r0;
                    } catch (Exception e3) {
                        e = e3;
                        DumpableLog.INSTANCE.e("AutoRevokePermissions", "Failed to auto-revoke permissions", e);
                    }
                    AutoRevokeService.this.jobFinished(this.$params, false);
                    return Unit.INSTANCE;
                }
                j = this.J$0;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj2 = obj;
                r1 = coroutineScope2;
            }
            List list = (List) obj2;
            if (true ^ list.isEmpty()) {
                AutoRevokeService autoRevokeService2 = AutoRevokeService.this;
                this.L$0 = r1;
                this.J$0 = j;
                this.L$1 = list;
                this.label = 2;
                if (autoRevokeService2.showAutoRevokeNotification(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                r02 = r1;
            }
            AutoRevokeService.this.jobFinished(this.$params, false);
            return Unit.INSTANCE;
        }
    }

    final /* synthetic */ Object showAutoRevokeNotification(long sessionId, Continuation<? super Unit> continuation) {
        Object systemService = getSystemService(NotificationManager.class);
        if (systemService == null) {
            Intrinsics.throwNpe();
        }
        NotificationManager notificationManager = (NotificationManager) systemService;
        NotificationChannel permissionReminderChannel = new NotificationChannel(Constants.PERMISSION_REMINDER_CHANNEL_ID, getString(R.string.permission_reminders), 2);
        notificationManager.createNotificationChannel(permissionReminderChannel);
        Intent clickIntent = new Intent(this, (Class<?>) ManagePermissionsActivity.class);
        clickIntent.setAction(Constants.ACTION_MANAGE_AUTO_REVOKE);
        clickIntent.putExtra(Constants.EXTRA_SESSION_ID, sessionId);
        clickIntent.setFlags(268435456);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, clickIntent, 1207959552);
        Notification.Builder b = new Notification.Builder(this, Constants.PERMISSION_REMINDER_CHANNEL_ID).setContentTitle(getString(R.string.auto_revoke_permission_notification_title)).setContentText(getString(R.string.auto_revoke_permission_notification_content)).setStyle(new Notification.BigTextStyle().bigText(getString(R.string.auto_revoke_permission_notification_content))).setSmallIcon(R.drawable.ic_settings_24dp).setColor(getColor(android.R.color.system_notification_accent_color)).setAutoCancel(true).setContentIntent(pendingIntent).extend((Notification.Extender) new Notification.TvExtender());
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        CharSequence settingsLabel = Utils.getSettingsLabelForNotifications(applicationContext.getPackageManager());
        if (settingsLabel != null) {
            Bundle extras = new Bundle();
            extras.putString("android.substName", settingsLabel.toString());
            b.addExtras(extras);
        }
        notificationManager.notify(AutoRevokeService.class.getSimpleName(), 1, b.build());
        Object initializedValue$default = SmartUpdateMediatorLiveData.getInitializedValue$default(UnusedAutoRevokedPackagesLiveData.INSTANCE, false, false, continuation, 3, null);
        return initializedValue$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? initializedValue$default : Unit.INSTANCE;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) throws Throwable {
        DumpableLog.w$default(DumpableLog.INSTANCE, "AutoRevokePermissions", "onStopJob after " + (System.currentTimeMillis() - this.jobStartTime) + "ms", null, 4, null);
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        return true;
    }
}
