package android.net.ipmemorystore;

import android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener;

/* JADX INFO: loaded from: classes.dex */
public interface OnNetworkAttributesRetrievedListener {
    void onNetworkAttributesRetrieved(Status status, String str, NetworkAttributes networkAttributes);

    static IOnNetworkAttributesRetrievedListener toAIDL(OnNetworkAttributesRetrievedListener onNetworkAttributesRetrievedListener) {
        return new IOnNetworkAttributesRetrievedListener.Stub() { // from class: android.net.ipmemorystore.OnNetworkAttributesRetrievedListener.1
            @Override // android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }

            @Override // android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener
            public void onNetworkAttributesRetrieved(StatusParcelable statusParcelable, String str, NetworkAttributesParcelable networkAttributesParcelable) {
                OnNetworkAttributesRetrievedListener onNetworkAttributesRetrievedListener2 = OnNetworkAttributesRetrievedListener.this;
                if (onNetworkAttributesRetrievedListener2 != null) {
                    onNetworkAttributesRetrievedListener2.onNetworkAttributesRetrieved(new Status(statusParcelable), str, networkAttributesParcelable == null ? null : new NetworkAttributes(networkAttributesParcelable));
                }
            }
        };
    }
}
