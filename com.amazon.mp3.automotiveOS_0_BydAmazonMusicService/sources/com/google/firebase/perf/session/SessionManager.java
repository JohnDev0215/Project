package com.google.firebase.perf.session;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.perf.application.AppStateMonitor;
import com.google.firebase.perf.application.AppStateUpdateHandler;
import com.google.firebase.perf.session.gauges.GaugeManager;
import com.google.firebase.perf.v1.ApplicationProcessState;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes6.dex */
@Keep
public class SessionManager extends AppStateUpdateHandler {

    @SuppressLint({"StaticFieldLeak"})
    private static final SessionManager instance = new SessionManager();
    private final AppStateMonitor appStateMonitor;
    private final Set<WeakReference<SessionAwareObject>> clients;
    private final GaugeManager gaugeManager;
    private PerfSession perfSession;
    private Future syncInitFuture;

    public static SessionManager getInstance() {
        return instance;
    }

    public final PerfSession perfSession() {
        return this.perfSession;
    }

    private SessionManager() {
        this(GaugeManager.getInstance(), PerfSession.create(), AppStateMonitor.getInstance());
    }

    @VisibleForTesting
    public SessionManager(GaugeManager gaugeManager, PerfSession perfSession, AppStateMonitor appStateMonitor) {
        this.clients = new HashSet();
        this.gaugeManager = gaugeManager;
        this.perfSession = perfSession;
        this.appStateMonitor = appStateMonitor;
        registerForAppState();
    }

    public void setApplicationContext(final Context context) {
        final PerfSession perfSession = this.perfSession;
        this.syncInitFuture = Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.google.firebase.perf.session.SessionManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setApplicationContext$0(context, perfSession);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setApplicationContext$0(Context context, PerfSession perfSession) {
        this.gaugeManager.initializeGaugeMetadataManager(context);
        if (perfSession.isGaugeAndEventCollectionEnabled()) {
            this.gaugeManager.logGaugeMetadata(perfSession.sessionId(), ApplicationProcessState.FOREGROUND);
        }
    }

    @Override // com.google.firebase.perf.application.AppStateUpdateHandler, com.google.firebase.perf.application.AppStateMonitor.AppStateCallback
    public void onUpdateAppState(ApplicationProcessState applicationProcessState) {
        super.onUpdateAppState(applicationProcessState);
        if (this.appStateMonitor.isColdStart()) {
            return;
        }
        if (applicationProcessState == ApplicationProcessState.FOREGROUND) {
            updatePerfSession(applicationProcessState);
        } else {
            if (updatePerfSessionIfExpired()) {
                return;
            }
            startOrStopCollectingGauges(applicationProcessState);
        }
    }

    public boolean updatePerfSessionIfExpired() {
        if (!this.perfSession.isExpired()) {
            return false;
        }
        updatePerfSession(this.appStateMonitor.getAppState());
        return true;
    }

    public void updatePerfSession(ApplicationProcessState applicationProcessState) {
        synchronized (this.clients) {
            this.perfSession = PerfSession.create();
            Iterator<WeakReference<SessionAwareObject>> it = this.clients.iterator();
            while (it.hasNext()) {
                SessionAwareObject sessionAwareObject = it.next().get();
                if (sessionAwareObject != null) {
                    sessionAwareObject.updateSession(this.perfSession);
                } else {
                    it.remove();
                }
            }
        }
        logGaugeMetadataIfCollectionEnabled(applicationProcessState);
        startOrStopCollectingGauges(applicationProcessState);
    }

    public void initializeGaugeCollection() {
        ApplicationProcessState applicationProcessState = ApplicationProcessState.FOREGROUND;
        logGaugeMetadataIfCollectionEnabled(applicationProcessState);
        startOrStopCollectingGauges(applicationProcessState);
    }

    public void registerForSessionUpdates(WeakReference<SessionAwareObject> weakReference) {
        synchronized (this.clients) {
            this.clients.add(weakReference);
        }
    }

    public void unregisterForSessionUpdates(WeakReference<SessionAwareObject> weakReference) {
        synchronized (this.clients) {
            this.clients.remove(weakReference);
        }
    }

    private void logGaugeMetadataIfCollectionEnabled(ApplicationProcessState applicationProcessState) {
        if (this.perfSession.isGaugeAndEventCollectionEnabled()) {
            this.gaugeManager.logGaugeMetadata(this.perfSession.sessionId(), applicationProcessState);
        }
    }

    private void startOrStopCollectingGauges(ApplicationProcessState applicationProcessState) {
        if (this.perfSession.isGaugeAndEventCollectionEnabled()) {
            this.gaugeManager.startCollectingGauges(this.perfSession, applicationProcessState);
        } else {
            this.gaugeManager.stopCollectingGauges();
        }
    }

    @VisibleForTesting
    public void setPerfSession(PerfSession perfSession) {
        this.perfSession = perfSession;
    }

    @VisibleForTesting
    public Future getSyncInitFuture() {
        return this.syncInitFuture;
    }
}
