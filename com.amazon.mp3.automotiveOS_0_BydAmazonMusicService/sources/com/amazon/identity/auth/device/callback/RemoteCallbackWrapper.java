package com.amazon.identity.auth.device.callback;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.IRemoteCallback;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.va;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class RemoteCallbackWrapper implements Callback, Parcelable {
    private static final int MAX_RETRIES = 1;
    private final IRemoteCallback mCallback;
    public static final Parcelable.Creator<RemoteCallbackWrapper> CREATOR = new a();
    private static final String TAG = RemoteCallbackWrapper.class.getName();

    /* JADX INFO: compiled from: DCP */
    public static class a implements Parcelable.Creator<RemoteCallbackWrapper> {
        @Override // android.os.Parcelable.Creator
        public RemoteCallbackWrapper createFromParcel(Parcel parcel) {
            return new RemoteCallbackWrapper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public RemoteCallbackWrapper[] newArray(int i) {
            return new RemoteCallbackWrapper[i];
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b extends IRemoteCallback.Stub {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Callback f267a;

        /* JADX INFO: compiled from: DCP */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bundle f268a;

            public a(Bundle bundle) {
                this.f268a = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f267a.onSuccess(this.f268a);
            }
        }

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.callback.RemoteCallbackWrapper$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class RunnableC0055b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bundle f269a;

            public RunnableC0055b(Bundle bundle) {
                this.f269a = bundle;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f267a.onError(this.f269a);
            }
        }

        public b(Callback callback) {
            this.f267a = callback;
        }

        @Override // com.amazon.identity.auth.device.callback.IRemoteCallback
        public void onError(Bundle bundle) throws RemoteException {
            if (this.f267a != null) {
                va.a(new RunnableC0055b(bundle));
            }
        }

        @Override // com.amazon.identity.auth.device.callback.IRemoteCallback
        public void onSuccess(Bundle bundle) throws RemoteException {
            if (this.f267a != null) {
                va.a(new a(bundle));
            }
        }
    }

    public RemoteCallbackWrapper(Callback callback) {
        this(new b(callback));
    }

    public static IRemoteCallback toRemoteCallback(Callback callback) {
        return new b(callback);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        boolean z = false;
        int i = 0;
        while (!z && i <= 1) {
            i++;
            try {
                IRemoteCallback iRemoteCallback = this.mCallback;
                if (iRemoteCallback != null) {
                    iRemoteCallback.onError(bundle);
                } else {
                    Log.e(ga.a(TAG), "Not calling onError because mCallback is null");
                }
                z = true;
            } catch (RemoteException e) {
                Log.e(ga.a(TAG), "onError callback failed", e);
            } catch (Exception e2) {
                Log.e(ga.a(TAG), "Exception onError", e2);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        boolean z = false;
        int i = 0;
        while (!z && i <= 1) {
            i++;
            try {
                IRemoteCallback iRemoteCallback = this.mCallback;
                if (iRemoteCallback != null) {
                    iRemoteCallback.onSuccess(bundle);
                } else {
                    Log.e(ga.a(TAG), "Not calling onSuccess because mCallback is null");
                }
                z = true;
            } catch (RemoteException e) {
                Log.e(ga.a(TAG), "onSuccess callback failed", e);
            } catch (Exception e2) {
                Log.e(ga.a(TAG), "Exception onSuccess", e2);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        IRemoteCallback iRemoteCallback = this.mCallback;
        if (iRemoteCallback != null) {
            parcel.writeStrongBinder(iRemoteCallback.asBinder());
        }
    }

    public RemoteCallbackWrapper(Parcel parcel) {
        this(IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
    }

    public RemoteCallbackWrapper(IRemoteCallback iRemoteCallback) {
        this.mCallback = iRemoteCallback;
    }
}
