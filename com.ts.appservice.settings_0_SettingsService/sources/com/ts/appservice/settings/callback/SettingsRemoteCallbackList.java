package com.ts.appservice.settings.callback;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class SettingsRemoteCallbackList<E extends IInterface> extends RemoteCallbackList<E> {
    private OnCallbackListListener mListener;

    public interface OnCallbackListListener {
        void onCallbackDied(int i) throws RemoteException;
    }

    @Override // android.os.RemoteCallbackList
    public void onCallbackDied(E callback, Object cookie) {
        onCallbackDied(callback);
    }

    @Override // android.os.RemoteCallbackList
    public void onCallbackDied(E callback) {
        super.onCallbackDied(callback);
        OnCallbackListListener onCallbackListListener = this.mListener;
        if (onCallbackListListener != null) {
            try {
                onCallbackListListener.onCallbackDied(getRegisteredCallbackCount());
            } catch (RemoteException except) {
                except.printStackTrace();
            }
        }
    }

    public void setOnCallbackListListener(OnCallbackListListener listener) {
        this.mListener = listener;
    }
}
