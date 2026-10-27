package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectivityPolicyProvider {

    public static final class NativeObserver implements Observer {
        private long nThis;

        private NativeObserver() {
        }

        @Override // com.spotify.connectivity.ConnectivityPolicyProvider.Observer
        public native void connectivityPolicyChanged(ConnectivityPolicy connectivityPolicy, ConnectivityPolicy connectivityPolicy2);
    }

    public interface Observer {
        void connectivityPolicyChanged(ConnectivityPolicy connectivityPolicy, ConnectivityPolicy connectivityPolicy2);
    }

    void addObserver(Observer observer);

    ConnectivityPolicy getConnectivityPolicy();

    void removeObserver(Observer observer);
}
