package android.net.ipmemorystore;

import android.net.ipmemorystore.IOnL2KeyResponseListener;

/* JADX INFO: loaded from: classes.dex */
public interface OnL2KeyResponseListener {
    void onL2KeyResponse(Status status, String str);

    static IOnL2KeyResponseListener toAIDL(OnL2KeyResponseListener listener) {
        return new IOnL2KeyResponseListener.Stub() { // from class: android.net.ipmemorystore.OnL2KeyResponseListener.1
            @Override // android.net.ipmemorystore.IOnL2KeyResponseListener
            public void onL2KeyResponse(StatusParcelable statusParcelable, String l2Key) {
                OnL2KeyResponseListener onL2KeyResponseListener = OnL2KeyResponseListener.this;
                if (onL2KeyResponseListener != null) {
                    onL2KeyResponseListener.onL2KeyResponse(new Status(statusParcelable), l2Key);
                }
            }

            @Override // android.net.ipmemorystore.IOnL2KeyResponseListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnL2KeyResponseListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }
        };
    }
}
