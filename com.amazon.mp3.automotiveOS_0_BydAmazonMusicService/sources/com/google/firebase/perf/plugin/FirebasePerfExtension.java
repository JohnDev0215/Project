package com.google.firebase.perf.plugin;

import java.util.Optional;

/* JADX INFO: loaded from: classes6.dex */
public class FirebasePerfExtension {
    private Boolean instrumentationEnabled = null;

    public void setInstrumentationEnabled(boolean z) {
        this.instrumentationEnabled = Boolean.valueOf(z);
    }

    Optional<Boolean> isInstrumentationEnabled() {
        return Optional.ofNullable(this.instrumentationEnabled);
    }

    public String toString() {
        return "FirebasePerfExtension(instrumentationEnabled: " + this.instrumentationEnabled + ")";
    }
}
