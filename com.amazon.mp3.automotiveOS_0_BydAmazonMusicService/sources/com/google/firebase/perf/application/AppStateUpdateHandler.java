package com.google.firebase.perf.application;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.perf.application.AppStateMonitor;
import com.google.firebase.perf.v1.ApplicationProcessState;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes6.dex */
public abstract class AppStateUpdateHandler implements AppStateMonitor.AppStateCallback {
    private final WeakReference<AppStateMonitor.AppStateCallback> appStateCallback;
    private final AppStateMonitor appStateMonitor;
    private ApplicationProcessState currentAppState;
    private boolean isRegisteredForAppState;

    protected AppStateUpdateHandler() {
        this(AppStateMonitor.getInstance());
    }

    protected AppStateUpdateHandler(@NonNull AppStateMonitor appStateMonitor) {
        this.isRegisteredForAppState = false;
        this.currentAppState = ApplicationProcessState.APPLICATION_PROCESS_STATE_UNKNOWN;
        this.appStateMonitor = appStateMonitor;
        this.appStateCallback = new WeakReference<>(this);
    }

    protected void registerForAppState() {
        if (this.isRegisteredForAppState) {
            return;
        }
        this.currentAppState = this.appStateMonitor.getAppState();
        this.appStateMonitor.registerForAppState(this.appStateCallback);
        this.isRegisteredForAppState = true;
    }

    protected void unregisterForAppState() {
        if (this.isRegisteredForAppState) {
            this.appStateMonitor.unregisterForAppState(this.appStateCallback);
            this.isRegisteredForAppState = false;
        }
    }

    protected void incrementTsnsCount(int i) {
        this.appStateMonitor.incrementTsnsCount(i);
    }

    @Override // com.google.firebase.perf.application.AppStateMonitor.AppStateCallback
    public void onUpdateAppState(ApplicationProcessState applicationProcessState) {
        ApplicationProcessState applicationProcessState2 = this.currentAppState;
        ApplicationProcessState applicationProcessState3 = ApplicationProcessState.APPLICATION_PROCESS_STATE_UNKNOWN;
        if (applicationProcessState2 == applicationProcessState3) {
            this.currentAppState = applicationProcessState;
        } else {
            if (applicationProcessState2 == applicationProcessState || applicationProcessState == applicationProcessState3) {
                return;
            }
            this.currentAppState = ApplicationProcessState.FOREGROUND_BACKGROUND;
        }
    }

    public ApplicationProcessState getAppState() {
        return this.currentAppState;
    }

    @VisibleForTesting
    public WeakReference<AppStateMonitor.AppStateCallback> getAppStateCallback() {
        return this.appStateCallback;
    }
}
