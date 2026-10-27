package com.google.android.projection.transport;

import android.content.Context;
import android.os.Parcelable;
import com.google.android.projection.logging.Log;
import com.google.android.projection.protocol.Transport;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TransportFactory {
    static final FactoryHandle DO_NOTHING_HANDLE = new FactoryHandle() { // from class: com.google.android.projection.transport.TransportFactory.1
        @Override // com.google.android.projection.transport.TransportFactory.FactoryHandle
        public void interrupt() throws Exception {
        }
    };
    private static final int READ_TIMEOUT_IN_MILLIS = 5000;
    private static final int RECEIVE_SOCKET_BUFFER_SIZE = 65536;
    private static final String TAG = "AHU.TRANSPORT_FACTORY";
    private static final boolean TCP_NO_DELAY_ON = true;
    private List<TransportFactoryDelegate> delegates;
    private boolean mIsInitialized;

    public interface Callback {
        void onConnected(int i, Transport transport);

        void onDisconnected(int i);
    }

    public interface FactoryHandle {
        void interrupt() throws Exception;
    }

    public static class TcpConnectionSocketOptions {
        public int readTimeoutInMillis = 5000;
        public int readSocketBufferSize = 65536;
        public boolean enableSendNoDelay = TransportFactory.TCP_NO_DELAY_ON;
    }

    interface TransportFactoryDelegate {
        void shutdown();

        FactoryHandle tryOpen(Parcelable parcelable, Callback callback) throws IOException;
    }

    private TransportFactory() {
        this.mIsInitialized = TCP_NO_DELAY_ON;
    }

    public static class Builder {
        private Context mContext;
        private ServerSocketProvider mServerSocketProvider;
        private int mTcpListenPort;
        private TcpConnectionSocketOptions mConnectionSocketOptions = new TcpConnectionSocketOptions();
        private int mFactoryId = 0;

        public Builder setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public Builder setTcpListenPort(int tcpListenPort) {
            this.mTcpListenPort = tcpListenPort;
            return this;
        }

        public Builder setServerSocketProvider(ServerSocketProvider serverSocketProvider) {
            this.mServerSocketProvider = serverSocketProvider;
            return this;
        }

        public Builder setTcpConnectionSocketOptions(TcpConnectionSocketOptions options) {
            this.mConnectionSocketOptions = options;
            return this;
        }

        public Builder setFactoryId(int id) {
            this.mFactoryId = id;
            return this;
        }

        public TransportFactory build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Context was not provided");
            }
            TransportFactory factory = new TransportFactory();
            if (this.mTcpListenPort > 0) {
                factory.delegates = Arrays.asList(new UsbHostDelegate(this.mContext, this.mFactoryId), new TcpDelegate(this.mServerSocketProvider, this.mFactoryId, this.mConnectionSocketOptions));
            } else {
                factory.delegates = Arrays.asList(new UsbHostDelegate(this.mContext, this.mFactoryId));
            }
            return factory;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void shutdown() {
        if (this.mIsInitialized) {
            Log.d("AHU.TRANSPORT_FACTORY", "Shutting down...");
            this.mIsInitialized = false;
            for (TransportFactoryDelegate delegate : this.delegates) {
                delegate.shutdown();
            }
            Log.d("AHU.TRANSPORT_FACTORY", "Shutdown completed.");
        }
    }

    public FactoryHandle open(Parcelable device, Callback callback) throws IOException {
        Log.d("AHU.TRANSPORT_FACTORY", "Starting...");
        for (TransportFactoryDelegate delegate : this.delegates) {
            FactoryHandle handle = delegate.tryOpen(device, callback);
            if (handle != null) {
                Log.d("AHU.TRANSPORT_FACTORY", "Start sequence finished.");
                return handle;
            }
        }
        Log.w("AHU.TRANSPORT_FACTORY", "Unable to handle " + device);
        return null;
    }
}
