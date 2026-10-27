package com.bugsnag.android;

/* JADX INFO: compiled from: ConnectivityCompat.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0007\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/bugsnag/android/Connectivity;", "", "", "registerForNetworkChanges", "", "hasNetworkConnection", "", "retrieveNetworkAccessState", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public interface Connectivity {
    boolean hasNetworkConnection();

    void registerForNetworkChanges();

    String retrieveNetworkAccessState();
}
