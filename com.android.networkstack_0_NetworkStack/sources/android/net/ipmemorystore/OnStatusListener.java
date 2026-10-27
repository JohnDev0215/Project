package android.net.ipmemorystore;

import android.net.ipmemorystore.IOnStatusListener;

/* JADX INFO: loaded from: classes.dex */
public interface OnStatusListener {
    void onComplete(Status status);

    static IOnStatusListener toAIDL(OnStatusListener onStatusListener) {
        return new IOnStatusListener.Stub() { // from class: android.net.ipmemorystore.OnStatusListener.1
            @Override // android.net.ipmemorystore.IOnStatusListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnStatusListener
            public void onComplete(StatusParcelable statusParcelable) {
                OnStatusListener onStatusListener2 = OnStatusListener.this;
                if (onStatusListener2 != null) {
                    onStatusListener2.onComplete(new Status(statusParcelable));
                }
            }
        };
    }
}
