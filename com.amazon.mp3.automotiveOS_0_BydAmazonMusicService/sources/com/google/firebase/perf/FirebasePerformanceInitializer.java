package com.google.firebase.perf;

import com.google.firebase.perf.application.AppStateMonitor;

/* JADX INFO: loaded from: classes6.dex */
public final class FirebasePerformanceInitializer implements AppStateMonitor.AppColdStartCallback {
    @Override // com.google.firebase.perf.application.AppStateMonitor.AppColdStartCallback
    public void onAppColdStart() {
        FirebasePerformance.getInstance();
    }
}
