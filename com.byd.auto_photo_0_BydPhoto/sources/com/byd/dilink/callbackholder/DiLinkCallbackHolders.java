package com.byd.dilink.callbackholder;

import android.os.RemoteException;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class DiLinkCallbackHolders<Wrapper, Inner> {
    private static final String TAG = "DiLinkCallbackHolders";
    private ConcurrentHashMap<Wrapper, Inner> mCallbacks = new ConcurrentHashMap<>();

    private boolean hasNoCallback(Wrapper wrapper) {
        return !this.mCallbacks.containsKey(wrapper);
    }

    public boolean addCallback(Wrapper wrapper, IDiLinkCallbackHandler<Inner> iDiLinkCallbackHandler) {
        if (wrapper == null) {
            return false;
        }
        if (this.mCallbacks.get(wrapper) == null) {
            this.mCallbacks.put(wrapper, createInnerCallback(wrapper));
        }
        iDiLinkCallbackHandler.onResult(this.mCallbacks.get(wrapper));
        return true;
    }

    public boolean addCallbackIgnoreRemoteException(Wrapper wrapper, IDiLinkCallbackHandler<Inner> iDiLinkCallbackHandler) {
        try {
            return addCallback(wrapper, iDiLinkCallbackHandler);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Inner createInnerCallback(Wrapper wrapper);

    public boolean removeCallback(Wrapper wrapper, IDiLinkCallbackHandler<Inner> iDiLinkCallbackHandler) {
        if (wrapper == null || hasNoCallback(wrapper)) {
            Log.e(TAG, "removeCallback: callback is null or needs to perform a addCallback first");
            return false;
        }
        iDiLinkCallbackHandler.onResult(this.mCallbacks.remove(wrapper));
        return true;
    }

    public boolean removeCallbackIgnoreRemoteException(Wrapper wrapper, IDiLinkCallbackHandler<Inner> iDiLinkCallbackHandler) {
        try {
            return removeCallback(wrapper, iDiLinkCallbackHandler);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
