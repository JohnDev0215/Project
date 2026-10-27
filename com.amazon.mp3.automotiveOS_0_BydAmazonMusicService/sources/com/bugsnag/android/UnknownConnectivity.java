package com.bugsnag.android;

import androidx.core.os.EnvironmentCompat;

/* JADX INFO: compiled from: ConnectivityCompat.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/bugsnag/android/UnknownConnectivity;", "Lcom/bugsnag/android/Connectivity;", "", "registerForNetworkChanges", "", "hasNetworkConnection", "", "retrieveNetworkAccessState", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class UnknownConnectivity implements Connectivity {
    public static final UnknownConnectivity INSTANCE = new UnknownConnectivity();

    @Override // com.bugsnag.android.Connectivity
    public boolean hasNetworkConnection() {
        return true;
    }

    @Override // com.bugsnag.android.Connectivity
    public void registerForNetworkChanges() {
    }

    @Override // com.bugsnag.android.Connectivity
    public String retrieveNetworkAccessState() {
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private UnknownConnectivity() {
    }
}
