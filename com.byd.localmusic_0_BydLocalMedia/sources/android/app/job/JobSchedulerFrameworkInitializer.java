package android.app.job;

import android.annotation.SystemApi;
import android.app.JobSchedulerImpl;
import android.app.SystemServiceRegistry;
import android.app.job.IJobScheduler;
import android.content.Context;
import android.os.DeviceIdleManager;
import android.os.IBinder;
import android.os.IDeviceIdleController;
import android.os.PowerWhitelistManager;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public class JobSchedulerFrameworkInitializer {
    private JobSchedulerFrameworkInitializer() {
    }

    public static void registerServiceWrappers() {
        SystemServiceRegistry.registerStaticService(Context.JOB_SCHEDULER_SERVICE, JobScheduler.class, new SystemServiceRegistry.StaticServiceProducerWithBinder() { // from class: android.app.job.-$$Lambda$JobSchedulerFrameworkInitializer$RHUxgww0pZFMmfQWKgaRAx0YFqA
            @Override // android.app.SystemServiceRegistry.StaticServiceProducerWithBinder
            public final Object createService(IBinder iBinder) {
                return JobSchedulerFrameworkInitializer.lambda$registerServiceWrappers$0(iBinder);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.DEVICE_IDLE_CONTROLLER, DeviceIdleManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithBinder() { // from class: android.app.job.-$$Lambda$JobSchedulerFrameworkInitializer$PtYe8PQc1PVJQXRnpm3iSxcWTR0
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithBinder
            public final Object createService(Context context, IBinder iBinder) {
                return JobSchedulerFrameworkInitializer.lambda$registerServiceWrappers$1(context, iBinder);
            }
        });
        SystemServiceRegistry.registerContextAwareService(Context.POWER_WHITELIST_MANAGER, PowerWhitelistManager.class, new SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder() { // from class: android.app.job.-$$Lambda$FpGlzN9oJcl8o5soW-gU-DyTvXM
            @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
            public final Object createService(Context context) {
                return new PowerWhitelistManager(context);
            }
        });
    }

    static /* synthetic */ JobScheduler lambda$registerServiceWrappers$0(IBinder iBinder) {
        return new JobSchedulerImpl(IJobScheduler.Stub.asInterface(iBinder));
    }

    static /* synthetic */ DeviceIdleManager lambda$registerServiceWrappers$1(Context context, IBinder iBinder) {
        return new DeviceIdleManager(context, IDeviceIdleController.Stub.asInterface(iBinder));
    }
}
