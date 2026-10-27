package com.bugsnag.android;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.BackgroundTaskService;
import com.bugsnag.android.internal.DateUtils;
import com.bugsnag.android.internal.ImmutableConfig;
import com.bugsnag.android.internal.TaskType;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes5.dex */
class SessionTracker extends BaseObservable {
    final BackgroundTaskService backgroundTaskService;
    private final CallbackState callbackState;
    private final Client client;
    private final ImmutableConfig configuration;
    private volatile Session currentSession;
    private final Deque<String> foregroundActivities;
    private final ForegroundDetector foregroundDetector;
    private final AtomicLong lastEnteredForegroundMs;
    private final AtomicLong lastExitedForegroundMs;
    final Logger logger;
    final SessionStore sessionStore;
    private final long timeoutMs;

    SessionTracker(ImmutableConfig immutableConfig, CallbackState callbackState, Client client, SessionStore sessionStore, Logger logger, BackgroundTaskService backgroundTaskService) {
        this(immutableConfig, callbackState, client, 30000L, sessionStore, logger, backgroundTaskService);
    }

    SessionTracker(ImmutableConfig immutableConfig, CallbackState callbackState, Client client, long j, SessionStore sessionStore, Logger logger, BackgroundTaskService backgroundTaskService) {
        this.foregroundActivities = new ArrayDeque();
        this.lastExitedForegroundMs = new AtomicLong(0L);
        this.lastEnteredForegroundMs = new AtomicLong(0L);
        this.currentSession = null;
        this.configuration = immutableConfig;
        this.callbackState = callbackState;
        this.client = client;
        this.timeoutMs = j;
        this.sessionStore = sessionStore;
        this.foregroundDetector = new ForegroundDetector(client.getAppContext());
        this.backgroundTaskService = backgroundTaskService;
        this.logger = logger;
        notifyNdkInForeground();
    }

    @Nullable
    @VisibleForTesting
    Session startNewSession(@NonNull Date date, @Nullable User user, boolean z) {
        if (this.client.getConfig().shouldDiscardSession(z)) {
            return null;
        }
        Session session = new Session(UUID.randomUUID().toString(), date, user, z, this.client.getNotifier(), this.logger);
        if (trackSessionIfNeeded(session)) {
            return session;
        }
        return null;
    }

    Session startSession(boolean z) {
        if (this.client.getConfig().shouldDiscardSession(z)) {
            return null;
        }
        return startNewSession(new Date(), this.client.getUser(), z);
    }

    void pauseSession() {
        Session session = this.currentSession;
        if (session != null) {
            session.isPaused.set(true);
            updateState(StateEvent.PauseSession.INSTANCE);
        }
    }

    boolean resumeSession() {
        Session sessionStartSession = this.currentSession;
        boolean zCompareAndSet = false;
        if (sessionStartSession == null) {
            sessionStartSession = startSession(false);
        } else {
            zCompareAndSet = sessionStartSession.isPaused.compareAndSet(true, false);
        }
        if (sessionStartSession != null) {
            notifySessionStartObserver(sessionStartSession);
        }
        return zCompareAndSet;
    }

    private void notifySessionStartObserver(Session session) {
        updateState(new StateEvent.StartSession(session.getId(), DateUtils.toIso8601(session.getStartedAt()), session.getHandledCount(), session.getUnhandledCount()));
    }

    @Nullable
    Session registerExistingSession(@Nullable Date date, @Nullable String str, @Nullable User user, int i, int i2) {
        Session session = null;
        if (this.client.getConfig().shouldDiscardSession(false)) {
            return null;
        }
        if (date != null && str != null) {
            session = new Session(str, date, user, i, i2, this.client.getNotifier(), this.logger);
            notifySessionStartObserver(session);
        } else {
            updateState(StateEvent.PauseSession.INSTANCE);
        }
        this.currentSession = session;
        return session;
    }

    private boolean trackSessionIfNeeded(Session session) throws Throwable {
        this.logger.d("SessionTracker#trackSessionIfNeeded() - session captured by Client");
        session.setApp(this.client.getAppDataCollector().generateApp());
        session.setDevice(this.client.getDeviceDataCollector().generateDevice());
        if (!this.callbackState.runOnSessionTasks(session, this.logger) || !session.isTracked().compareAndSet(false, true)) {
            return false;
        }
        this.currentSession = session;
        notifySessionStartObserver(session);
        flushInMemorySession(session);
        flushAsync();
        return true;
    }

    @Nullable
    Session getCurrentSession() {
        Session session = this.currentSession;
        if (session == null || session.isPaused.get()) {
            return null;
        }
        return session;
    }

    void flushAsync() {
        try {
            this.backgroundTaskService.submitTask(TaskType.SESSION_REQUEST, new Runnable() { // from class: com.bugsnag.android.SessionTracker.1
                @Override // java.lang.Runnable
                public void run() {
                    SessionTracker.this.flushStoredSessions();
                }
            });
        } catch (RejectedExecutionException e) {
            this.logger.w("Failed to flush session reports", e);
        }
    }

    void flushStoredSessions() {
        Iterator<File> it = this.sessionStore.findStoredFiles().iterator();
        while (it.hasNext()) {
            flushStoredSession(it.next());
        }
    }

    void flushStoredSession(File file) {
        this.logger.d("SessionTracker#flushStoredSession() - attempting delivery");
        Session session = new Session(file, this.client.getNotifier(), this.logger);
        if (!session.isV2Payload()) {
            session.setApp(this.client.getAppDataCollector().generateApp());
            session.setDevice(this.client.getDeviceDataCollector().generateDevice());
        }
        int i = AnonymousClass3.$SwitchMap$com$bugsnag$android$DeliveryStatus[deliverSessionPayload(session).ordinal()];
        if (i == 1) {
            this.sessionStore.deleteStoredFiles(Collections.singletonList(file));
            this.logger.d("Sent 1 new session to Bugsnag");
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.logger.w("Deleting invalid session tracking payload");
            this.sessionStore.deleteStoredFiles(Collections.singletonList(file));
            return;
        }
        if (this.sessionStore.isTooOld(file)) {
            this.logger.w("Discarding historical session (from {" + this.sessionStore.getCreationDate(file) + "}) after failed delivery");
            this.sessionStore.deleteStoredFiles(Collections.singletonList(file));
            return;
        }
        this.sessionStore.cancelQueuedFiles(Collections.singletonList(file));
        this.logger.w("Leaving session payload for future delivery");
    }

    /* JADX INFO: renamed from: com.bugsnag.android.SessionTracker$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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

    private void flushInMemorySession(final Session session) throws Throwable {
        try {
            this.backgroundTaskService.submitTask(TaskType.SESSION_REQUEST, new Runnable() { // from class: com.bugsnag.android.SessionTracker.2
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    SessionTracker.this.deliverInMemorySession(session);
                }
            });
        } catch (RejectedExecutionException unused) {
            this.sessionStore.write(session);
        }
    }

    void deliverInMemorySession(Session session) throws Throwable {
        try {
            this.logger.d("SessionTracker#trackSessionIfNeeded() - attempting initial delivery");
            int i = AnonymousClass3.$SwitchMap$com$bugsnag$android$DeliveryStatus[deliverSessionPayload(session).ordinal()];
            if (i == 1) {
                this.logger.d("Sent 1 new session to Bugsnag");
            } else if (i == 2) {
                this.logger.w("Storing session payload for future delivery");
                this.sessionStore.write(session);
            } else if (i == 3) {
                this.logger.w("Dropping invalid session tracking payload");
            }
        } catch (Exception e) {
            this.logger.w("Session tracking payload failed", e);
        }
    }

    DeliveryStatus deliverSessionPayload(Session session) {
        return this.configuration.getDelivery().deliver(session, this.configuration.getSessionApiDeliveryParams());
    }

    void onActivityStarted(String str) {
        updateForegroundTracker(str, true, SystemClock.elapsedRealtime());
    }

    void onActivityStopped(String str) {
        updateForegroundTracker(str, false, SystemClock.elapsedRealtime());
    }

    void updateForegroundTracker(String str, boolean z, long j) {
        if (z) {
            long j2 = j - this.lastExitedForegroundMs.get();
            synchronized (this.foregroundActivities) {
                if (this.foregroundActivities.isEmpty()) {
                    this.lastEnteredForegroundMs.set(j);
                    if (j2 >= this.timeoutMs && this.configuration.getAutoTrackSessions()) {
                        startNewSession(new Date(), this.client.getUser(), true);
                    }
                }
                this.foregroundActivities.add(str);
            }
        } else {
            synchronized (this.foregroundActivities) {
                this.foregroundActivities.removeLastOccurrence(str);
                if (this.foregroundActivities.isEmpty()) {
                    this.lastExitedForegroundMs.set(j);
                }
            }
        }
        this.client.getContextState().setAutomaticContext(getContextActivity());
        notifyNdkInForeground();
    }

    private void notifyNdkInForeground() {
        Boolean boolIsInForeground = isInForeground();
        updateState(new StateEvent.UpdateInForeground(boolIsInForeground != null ? boolIsInForeground.booleanValue() : false, getContextActivity()));
    }

    @Nullable
    Boolean isInForeground() {
        return this.foregroundDetector.isInForeground();
    }

    long getLastEnteredForegroundMs() {
        return this.lastEnteredForegroundMs.get();
    }

    @Nullable
    String getContextActivity() {
        String strPeekLast;
        synchronized (this.foregroundActivities) {
            strPeekLast = this.foregroundActivities.peekLast();
        }
        return strPeekLast;
    }
}
