package com.amazon.identity.auth.device.token;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.r8;
import com.amazon.identity.auth.device.token.IInternalListener;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class RemoteInternalListenerWrapper extends r8 implements Parcelable {
    public static final Parcelable.Creator<RemoteInternalListenerWrapper> CREATOR = new a();
    private static final String TAG = "RemoteInternalListenerWrapper";
    private final IInternalListener mIInternalListener;

    /* JADX INFO: compiled from: DCP */
    public static class a implements Parcelable.Creator<RemoteInternalListenerWrapper> {
        @Override // android.os.Parcelable.Creator
        public RemoteInternalListenerWrapper createFromParcel(Parcel parcel) {
            return new RemoteInternalListenerWrapper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public RemoteInternalListenerWrapper[] newArray(int i) {
            return new RemoteInternalListenerWrapper[i];
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b extends IInternalListener.Stub {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final r8 f595a;

        public b(r8 r8Var) {
            this.f595a = r8Var;
        }

        @Override // com.amazon.identity.auth.device.token.IInternalListener
        public void finish(Bundle bundle) {
            r8 r8Var = this.f595a;
            if (r8Var != null) {
                r8Var.finish(bundle);
            }
        }
    }

    public RemoteInternalListenerWrapper(IInternalListener iInternalListener) {
        this.mIInternalListener = iInternalListener;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.identity.auth.device.r8
    public void finish(Bundle bundle) {
        IInternalListener iInternalListener = this.mIInternalListener;
        if (iInternalListener != null) {
            try {
                iInternalListener.finish(bundle);
            } catch (RemoteException e) {
                Log.e(ga.a(TAG), "finish callback failed", e);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        IInternalListener iInternalListener = this.mIInternalListener;
        if (iInternalListener != null) {
            parcel.writeStrongBinder(iInternalListener.asBinder());
        }
    }

    public RemoteInternalListenerWrapper(r8 r8Var) {
        this(new b(r8Var));
    }

    public RemoteInternalListenerWrapper(Parcel parcel) {
        this(IInternalListener.Stub.asInterface(parcel.readStrongBinder()));
    }
}
