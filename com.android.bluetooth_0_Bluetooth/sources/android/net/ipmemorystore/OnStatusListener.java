package android.net.ipmemorystore;

import android.net.ipmemorystore.IOnStatusListener;

/* JADX INFO: loaded from: classes.dex */
public interface OnStatusListener {
    void onComplete(Status status);

    static IOnStatusListener toAIDL(OnStatusListener listener) {
        return new IOnStatusListener.Stub() { // from class: android.net.ipmemorystore.OnStatusListener.1
            @Override // android.net.ipmemorystore.IOnStatusListener
            public void onComplete(StatusParcelable statusParcelable) {
                OnStatusListener onStatusListener = OnStatusListener.this;
                if (onStatusListener != null) {
                    onStatusListener.onComplete(new Status(statusParcelable));
                }
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }
        };
    }
}
