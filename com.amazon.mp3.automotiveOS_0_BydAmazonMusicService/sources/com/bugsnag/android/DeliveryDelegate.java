package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.TaskType;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class DeliveryDelegate extends BaseObservable {

    @VisibleForTesting
    static long DELIVERY_TIMEOUT = 3000;
    final BackgroundTaskService backgroundTaskService;
    private final CallbackState callbackState;
    private final EventStore eventStore;
    private final ImmutableConfig immutableConfig;
    final Logger logger;
    private final Notifier notifier;

    DeliveryDelegate(Logger logger, EventStore eventStore, ImmutableConfig immutableConfig, CallbackState callbackState, Notifier notifier, BackgroundTaskService backgroundTaskService) {
        this.logger = logger;
        this.eventStore = eventStore;
        this.immutableConfig = immutableConfig;
        this.callbackState = callbackState;
        this.notifier = notifier;
        this.backgroundTaskService = backgroundTaskService;
    }

    void deliver(@NonNull Event event) {
        this.logger.d("DeliveryDelegate#deliver() - event being stored/delivered by Client");
        Session session = event.getSession();
        if (session != null) {
            if (event.isUnhandled()) {
                event.setSession(session.incrementUnhandledAndCopy());
                updateState(StateEvent.NotifyUnhandled.INSTANCE);
            } else {
                event.setSession(session.incrementHandledAndCopy());
                updateState(StateEvent.NotifyHandled.INSTANCE);
            }
        }
        if (event.getImpl().getOriginalUnhandled()) {
            boolean zEquals = "unhandledPromiseRejection".equals(event.getImpl().getSeverityReasonType());
            if (event.getImpl().isAnr(event) || zEquals) {
                cacheEvent(event, true);
                return;
            } else if (this.immutableConfig.getAttemptDeliveryOnCrash()) {
                cacheAndSendSynchronously(event);
                return;
            } else {
                cacheEvent(event, false);
                return;
            }
        }
        if (this.callbackState.runOnSendTasks(event, this.logger)) {
            deliverPayloadAsync(event, new EventPayload(event.getApiKey(), event, this.notifier, this.immutableConfig));
        }
    }

    private void deliverPayloadAsync(@NonNull final Event event, final EventPayload eventPayload) throws Throwable {
        try {
            this.backgroundTaskService.submitTask(TaskType.ERROR_REQUEST, new Runnable() { // from class: com.bugsnag.android.DeliveryDelegate.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    DeliveryDelegate.this.deliverPayloadInternal(eventPayload, event);
                }
            });
        } catch (RejectedExecutionException unused) {
            cacheEvent(event, false);
            this.logger.w("Exceeded max queue count, saving to disk to send later");
        }
    }

    @VisibleForTesting
    DeliveryStatus deliverPayloadInternal(@NonNull EventPayload eventPayload, @NonNull Event event) throws Throwable {
        this.logger.d("DeliveryDelegate#deliverPayloadInternal() - attempting event delivery");
        DeliveryStatus deliveryStatusDeliver = this.immutableConfig.getDelivery().deliver(eventPayload, this.immutableConfig.getErrorApiDeliveryParams(eventPayload));
        int i = AnonymousClass2.$SwitchMap$com$bugsnag$android$DeliveryStatus[deliveryStatusDeliver.ordinal()];
        if (i == 1) {
            this.logger.i("Sent 1 new event to Bugsnag");
        } else if (i == 2) {
            this.logger.w("Could not send event(s) to Bugsnag, saving to disk to send later");
            cacheEvent(event, false);
        } else if (i == 3) {
            this.logger.w("Problem sending event to Bugsnag");
        }
        return deliveryStatusDeliver;
    }

    /* JADX INFO: renamed from: com.bugsnag.android.DeliveryDelegate$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$bugsnag$android$DeliveryStatus;

        static {
            int[] iArr = new int[DeliveryStatus.values().length];
            $SwitchMap$com$bugsnag$android$DeliveryStatus = iArr;
            try {
                iArr[DeliveryStatus.DELIVERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$bugsnag$android$DeliveryStatus[DeliveryStatus.UNDELIVERED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$bugsnag$android$DeliveryStatus[DeliveryStatus.FAILURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void cacheAndSendSynchronously(@NonNull Event event) {
        long jCurrentTimeMillis = System.currentTimeMillis() + DELIVERY_TIMEOUT;
        Future<String> futureWriteAndDeliver = this.eventStore.writeAndDeliver(event);
        long jCurrentTimeMillis2 = jCurrentTimeMillis - System.currentTimeMillis();
        if (futureWriteAndDeliver == null || jCurrentTimeMillis2 <= 0) {
            return;
        }
        try {
            futureWriteAndDeliver.get(jCurrentTimeMillis2, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            this.logger.w("failed to immediately deliver event", e);
        }
        if (futureWriteAndDeliver.isDone()) {
            return;
        }
        futureWriteAndDeliver.cancel(true);
    }

    private void cacheEvent(@NonNull Event event, boolean z) throws Throwable {
        this.eventStore.write(event);
        if (z) {
            this.eventStore.flushAsync();
        }
    }
}
