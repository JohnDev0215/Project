package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionTypeProvider {

    public static final class NativeObserver implements Observer {
        private long nThis;

        private NativeObserver() {
        }

        @Override // com.spotify.connectivity.ConnectionTypeProvider.Observer
        public native void connectionTypeChanged(ConnectionType connectionType, ConnectionType connectionType2);
    }

    public interface Observer {
        void connectionTypeChanged(ConnectionType connectionType, ConnectionType connectionType2);
    }

    void addObserver(Observer observer);

    ConnectionType getConnectionType();

    void removeObserver(Observer observer);
}
