package com.bugsnag.android;

import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.dag.ConfigModule;
import com.bugsnag.android.internal.dag.ContextModule;
import com.bugsnag.android.internal.dag.DependencyModule;
import com.bugsnag.android.internal.dag.SystemServiceModule;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: EventStorageModule.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001b\u001a\u00020\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lcom/bugsnag/android/EventStorageModule;", "Lcom/bugsnag/android/internal/dag/DependencyModule;", "contextModule", "Lcom/bugsnag/android/internal/dag/ContextModule;", "configModule", "Lcom/bugsnag/android/internal/dag/ConfigModule;", "dataCollectionModule", "Lcom/bugsnag/android/DataCollectionModule;", "bgTaskService", "Lcom/bugsnag/android/internal/BackgroundTaskService;", "trackerModule", "Lcom/bugsnag/android/TrackerModule;", "systemServiceModule", "Lcom/bugsnag/android/internal/dag/SystemServiceModule;", "notifier", "Lcom/bugsnag/android/Notifier;", "callbackState", "Lcom/bugsnag/android/CallbackState;", "(Lcom/bugsnag/android/internal/dag/ContextModule;Lcom/bugsnag/android/internal/dag/ConfigModule;Lcom/bugsnag/android/DataCollectionModule;Lcom/bugsnag/android/internal/BackgroundTaskService;Lcom/bugsnag/android/TrackerModule;Lcom/bugsnag/android/internal/dag/SystemServiceModule;Lcom/bugsnag/android/Notifier;Lcom/bugsnag/android/CallbackState;)V", "cfg", "Lcom/bugsnag/android/internal/ImmutableConfig;", "delegate", "Lcom/bugsnag/android/InternalReportDelegate;", "getDelegate", "()Lcom/bugsnag/android/InternalReportDelegate;", "delegate$delegate", "Lkotlin/Lazy;", "eventStore", "Lcom/bugsnag/android/EventStore;", "getEventStore", "()Lcom/bugsnag/android/EventStore;", "eventStore$delegate", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class EventStorageModule extends DependencyModule {
    private final ImmutableConfig cfg;

    /* JADX INFO: renamed from: delegate$delegate, reason: from kotlin metadata */
    private final Lazy delegate;

    /* JADX INFO: renamed from: eventStore$delegate, reason: from kotlin metadata */
    private final Lazy eventStore;

    /* JADX INFO: Access modifiers changed from: private */
    public final InternalReportDelegate getDelegate() {
        return (InternalReportDelegate) this.delegate.getValue();
    }

    public final EventStore getEventStore() {
        return (EventStore) this.eventStore.getValue();
    }

    public EventStorageModule(final ContextModule contextModule, ConfigModule configModule, final DataCollectionModule dataCollectionModule, final BackgroundTaskService backgroundTaskService, final TrackerModule trackerModule, final SystemServiceModule systemServiceModule, final Notifier notifier, final CallbackState callbackState) {
        this.cfg = configModule.getConfig();
        this.delegate = future(new Function0<InternalReportDelegate>() { // from class: com.bugsnag.android.EventStorageModule$delegate$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InternalReportDelegate invoke() {
                if (this.this$0.cfg.getTelemetry().contains(Telemetry.INTERNAL_ERRORS)) {
                    return new InternalReportDelegate(contextModule.getCtx(), this.this$0.cfg.getLogger(), this.this$0.cfg, systemServiceModule.getStorageManager(), dataCollectionModule.getAppDataCollector(), dataCollectionModule.getDeviceDataCollector(), trackerModule.getSessionTracker(), notifier, backgroundTaskService);
                }
                return null;
            }
        });
        this.eventStore = future(new Function0<EventStore>() { // from class: com.bugsnag.android.EventStorageModule$eventStore$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EventStore invoke() {
                return new EventStore(this.this$0.cfg, this.this$0.cfg.getLogger(), notifier, backgroundTaskService, this.this$0.getDelegate(), callbackState);
            }
        });
    }
}
