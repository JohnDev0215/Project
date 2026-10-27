package android.net.ipmemorystore;

import android.net.ipmemorystore.IOnStatusAndCountListener;

/* JADX INFO: loaded from: classes.dex */
public interface OnDeleteStatusListener {
    void onComplete(Status status, int i);

    static IOnStatusAndCountListener toAIDL(OnDeleteStatusListener listener) {
        return new IOnStatusAndCountListener.Stub() { // from class: android.net.ipmemorystore.OnDeleteStatusListener.1
            @Override // android.net.ipmemorystore.IOnStatusAndCountListener
            public void onComplete(StatusParcelable statusParcelable, int deletedRecords) {
                OnDeleteStatusListener onDeleteStatusListener = OnDeleteStatusListener.this;
                if (onDeleteStatusListener != null) {
                    onDeleteStatusListener.onComplete(new Status(statusParcelable), deletedRecords);
                }
            }

            @Override // android.net.ipmemorystore.IOnStatusAndCountListener
            public int getInterfaceVersion() {
                return 7;
            }

            @Override // android.net.ipmemorystore.IOnStatusAndCountListener
            public String getInterfaceHash() {
                return "31826566143ef882d67fac9f24566f73df4907b4";
            }
        };
    }
}
