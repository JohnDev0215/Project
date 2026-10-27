package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f68c = false;
    final Handler d = null;
    IResultReceiver e;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<ResultReceiver> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResultReceiver[] newArray(int i) {
            return new ResultReceiver[i];
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends IResultReceiver.Stub {
        b() {
        }

        @Override // android.support.v4.os.IResultReceiver
        public void k0(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.d;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.a(i, bundle);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f70c;
        final Bundle d;

        c(int i, Bundle bundle) {
            this.f70c = i;
            this.d = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f70c, this.d);
        }
    }

    ResultReceiver(Parcel parcel) {
        this.e = IResultReceiver.Stub.n0(parcel.readStrongBinder());
    }

    protected void a(int i, Bundle bundle) {
    }

    public void b(int i, Bundle bundle) {
        if (this.f68c) {
            Handler handler = this.d;
            if (handler != null) {
                handler.post(new c(i, bundle));
                return;
            } else {
                a(i, bundle);
                return;
            }
        }
        IResultReceiver iResultReceiver = this.e;
        if (iResultReceiver != null) {
            try {
                iResultReceiver.k0(i, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.e == null) {
                this.e = new b();
            }
            parcel.writeStrongBinder(this.e.asBinder());
        }
    }
}
