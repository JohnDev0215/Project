package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes6.dex */
public class Uploader {
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;

    public Uploader(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard synchronizationGuard, Clock clock, Clock clock2, ClientHealthMetricsStore clientHealthMetricsStore) {
        this.context = context;
        this.backendRegistry = backendRegistry;
        this.eventStore = eventStore;
        this.workScheduler = workScheduler;
        this.executor = executor;
        this.guard = synchronizationGuard;
        this.clock = clock;
        this.uptimeClock = clock2;
        this.clientHealthMetricsStore = clientHealthMetricsStore;
    }

    boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void upload(final TransportContext transportContext, final int i, final Runnable runnable) {
        this.executor.execute(new Runnable() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$upload$1(transportContext, i, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$upload$1(final TransportContext transportContext, final int i, Runnable runnable) {
        try {
            try {
                SynchronizationGuard synchronizationGuard = this.guard;
                final EventStore eventStore = this.eventStore;
                Objects.requireNonNull(eventStore);
                synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda1
                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return Integer.valueOf(eventStore.cleanUp());
                    }
                });
                if (!isNetworkAvailable()) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda2
                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return this.f$0.lambda$upload$0(transportContext, i);
                        }
                    });
                } else {
                    logAndUpdateState(transportContext, i);
                }
            } catch (SynchronizationException unused) {
                this.workScheduler.schedule(transportContext, i + 1);
            }
        } finally {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$upload$0(TransportContext transportContext, int i) {
        this.workScheduler.schedule(transportContext, i + 1);
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public BackendResponse logAndUpdateState(final TransportContext transportContext, int i) {
        BackendResponse backendResponseSend;
        TransportBackend transportBackend = this.backendRegistry.get(transportContext.getBackendName());
        long jMax = 0;
        BackendResponse backendResponseOk = BackendResponse.ok(0L);
        while (true) {
            final long j = jMax;
            while (((Boolean) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda3
                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    return this.f$0.lambda$logAndUpdateState$2(transportContext);
                }
            })).booleanValue()) {
                final Iterable iterable = (Iterable) this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda4
                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return this.f$0.lambda$logAndUpdateState$3(transportContext);
                    }
                });
                if (!iterable.iterator().hasNext()) {
                    return backendResponseOk;
                }
                if (transportBackend == null) {
                    Logging.d("Uploader", "Unknown backend for %s, deleting event batch for it...", transportContext);
                    backendResponseSend = BackendResponse.fatalError();
                } else {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((PersistedEvent) it.next()).getEvent());
                    }
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        arrayList.add(createMetricsEvent(transportBackend));
                    }
                    backendResponseSend = transportBackend.send(BackendRequest.builder().setEvents(arrayList).setExtras(transportContext.getExtras()).build());
                }
                backendResponseOk = backendResponseSend;
                if (backendResponseOk.getStatus() == BackendResponse.Status.TRANSIENT_ERROR) {
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda5
                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return this.f$0.lambda$logAndUpdateState$4(iterable, transportContext, j);
                        }
                    });
                    this.workScheduler.schedule(transportContext, i + 1, true);
                    return backendResponseOk;
                }
                this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda6
                    @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                    public final Object execute() {
                        return this.f$0.lambda$logAndUpdateState$5(iterable);
                    }
                });
                if (backendResponseOk.getStatus() == BackendResponse.Status.OK) {
                    jMax = Math.max(j, backendResponseOk.getNextRequestWaitMillis());
                    if (transportContext.shouldUploadClientHealthMetrics()) {
                        this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda7
                            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                            public final Object execute() {
                                return this.f$0.lambda$logAndUpdateState$6();
                            }
                        });
                    }
                } else if (backendResponseOk.getStatus() == BackendResponse.Status.INVALID_PAYLOAD) {
                    final HashMap map = new HashMap();
                    Iterator it2 = iterable.iterator();
                    while (it2.hasNext()) {
                        String transportName = ((PersistedEvent) it2.next()).getEvent().getTransportName();
                        if (!map.containsKey(transportName)) {
                            map.put(transportName, 1);
                        } else {
                            map.put(transportName, Integer.valueOf(((Integer) map.get(transportName)).intValue() + 1));
                        }
                    }
                    this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda8
                        @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                        public final Object execute() {
                            return this.f$0.lambda$logAndUpdateState$7(map);
                        }
                    });
                }
            }
            this.guard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda9
                @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
                public final Object execute() {
                    return this.f$0.lambda$logAndUpdateState$8(transportContext, j);
                }
            });
            return backendResponseOk;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$logAndUpdateState$2(TransportContext transportContext) {
        return Boolean.valueOf(this.eventStore.hasPendingEventsFor(transportContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Iterable lambda$logAndUpdateState$3(TransportContext transportContext) {
        return this.eventStore.loadBatch(transportContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$4(Iterable iterable, TransportContext transportContext, long j) {
        this.eventStore.recordFailure(iterable);
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$5(Iterable iterable) {
        this.eventStore.recordSuccess(iterable);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$6() {
        this.clientHealthMetricsStore.resetClientMetrics();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$7(Map map) {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            this.clientHealthMetricsStore.recordLogEventDropped(((Integer) r0.getValue()).intValue(), LogEventDropped.Reason.INVALID_PAYLOD, (String) ((Map.Entry) it.next()).getKey());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$logAndUpdateState$8(TransportContext transportContext, long j) {
        this.eventStore.recordNextCallTime(transportContext, this.clock.getTime() + j);
        return null;
    }

    @VisibleForTesting
    public EventInternal createMetricsEvent(TransportBackend transportBackend) {
        SynchronizationGuard synchronizationGuard = this.guard;
        final ClientHealthMetricsStore clientHealthMetricsStore = this.clientHealthMetricsStore;
        Objects.requireNonNull(clientHealthMetricsStore);
        return transportBackend.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName("GDT_CLIENT_METRICS").setEncodedPayload(new EncodedPayload(Encoding.of("proto"), ((ClientMetrics) synchronizationGuard.runCriticalSection(new SynchronizationGuard.CriticalSection() { // from class: com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader$$ExternalSyntheticLambda10
            @Override // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection
            public final Object execute() {
                return clientHealthMetricsStore.loadClientMetrics();
            }
        })).toByteArray())).build());
    }
}
