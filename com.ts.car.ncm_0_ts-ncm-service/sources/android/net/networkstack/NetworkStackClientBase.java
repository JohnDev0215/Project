package android.net.networkstack;

import android.net.IIpMemoryStoreCallbacks;
import android.net.INetworkMonitorCallbacks;
import android.net.INetworkStackConnector;
import android.net.Network;
import android.net.dhcp.DhcpServingParamsParcel;
import android.net.dhcp.IDhcpServerCallbacks;
import android.net.ip.IIpClientCallbacks;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public abstract class NetworkStackClientBase {
    private INetworkStackConnector mConnector;
    private final ArrayList<Consumer<INetworkStackConnector>> mPendingNetStackRequests = new ArrayList<>();

    public void makeDhcpServer(final String ifName, final DhcpServingParamsParcel params, final IDhcpServerCallbacks cb) {
        requestConnector(new Consumer() { // from class: android.net.networkstack.-$$Lambda$NetworkStackClientBase$KFueb5ux_Y29dHAgr1C312iTfY8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkStackClientBase.lambda$makeDhcpServer$0(ifName, params, cb, (INetworkStackConnector) obj);
            }
        });
    }

    static /* synthetic */ void lambda$makeDhcpServer$0(String ifName, DhcpServingParamsParcel params, IDhcpServerCallbacks cb, INetworkStackConnector connector) {
        try {
            connector.makeDhcpServer(ifName, params, cb);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not create DhcpServer", e);
        }
    }

    public void makeIpClient(final String ifName, final IIpClientCallbacks cb) {
        requestConnector(new Consumer() { // from class: android.net.networkstack.-$$Lambda$NetworkStackClientBase$OwDc2jxNNxij2DwZJOxHrSIkT4w
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkStackClientBase.lambda$makeIpClient$1(ifName, cb, (INetworkStackConnector) obj);
            }
        });
    }

    static /* synthetic */ void lambda$makeIpClient$1(String ifName, IIpClientCallbacks cb, INetworkStackConnector connector) {
        try {
            connector.makeIpClient(ifName, cb);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not create IpClient", e);
        }
    }

    public void makeNetworkMonitor(final Network network, final String name, final INetworkMonitorCallbacks cb) {
        requestConnector(new Consumer() { // from class: android.net.networkstack.-$$Lambda$NetworkStackClientBase$xmhbZP7V8GFtCN9z63d8LazFkUM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkStackClientBase.lambda$makeNetworkMonitor$2(network, name, cb, (INetworkStackConnector) obj);
            }
        });
    }

    static /* synthetic */ void lambda$makeNetworkMonitor$2(Network network, String name, INetworkMonitorCallbacks cb, INetworkStackConnector connector) {
        try {
            connector.makeNetworkMonitor(network, name, cb);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not create NetworkMonitor", e);
        }
    }

    public void fetchIpMemoryStore(final IIpMemoryStoreCallbacks cb) {
        requestConnector(new Consumer() { // from class: android.net.networkstack.-$$Lambda$NetworkStackClientBase$okdj3YJsErzDSIpQV-9KsxdCYmM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NetworkStackClientBase.lambda$fetchIpMemoryStore$3(cb, (INetworkStackConnector) obj);
            }
        });
    }

    static /* synthetic */ void lambda$fetchIpMemoryStore$3(IIpMemoryStoreCallbacks cb, INetworkStackConnector connector) {
        try {
            connector.fetchIpMemoryStore(cb);
        } catch (RemoteException e) {
            throw new IllegalStateException("Could not fetch IpMemoryStore", e);
        }
    }

    protected void requestConnector(Consumer<INetworkStackConnector> request) {
        synchronized (this.mPendingNetStackRequests) {
            INetworkStackConnector connector = this.mConnector;
            if (connector == null) {
                this.mPendingNetStackRequests.add(request);
            } else {
                request.accept(connector);
            }
        }
    }

    protected void onNetworkStackConnected(INetworkStackConnector connector) {
        ArrayList<Consumer<INetworkStackConnector>> requests;
        while (true) {
            synchronized (this.mPendingNetStackRequests) {
                requests = new ArrayList<>(this.mPendingNetStackRequests);
                this.mPendingNetStackRequests.clear();
            }
            for (Consumer<INetworkStackConnector> consumer : requests) {
                consumer.accept(connector);
            }
            synchronized (this.mPendingNetStackRequests) {
                if (this.mPendingNetStackRequests.size() == 0) {
                    this.mConnector = connector;
                    return;
                }
            }
        }
    }

    protected int getQueueLength() {
        int size;
        synchronized (this.mPendingNetStackRequests) {
            size = this.mPendingNetStackRequests.size();
        }
        return size;
    }
}
