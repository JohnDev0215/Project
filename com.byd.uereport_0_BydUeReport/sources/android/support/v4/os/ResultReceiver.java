package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import j.a;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f216d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f217e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public j.a f218f;

    public class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResultReceiver[] newArray(int i8) {
            return new ResultReceiver[i8];
        }
    }

    public class b extends a.AbstractBinderC0063a {
        public b() {
        }

        @Override // j.a
        public void w0(int i8, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f217e;
            if (handler != null) {
                handler.post(resultReceiver.new c(i8, bundle));
            } else {
                resultReceiver.f(i8, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f220d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Bundle f221e;

        public c(int i8, Bundle bundle) {
            this.f220d = i8;
            this.f221e = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.f(this.f220d, this.f221e);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f218f = a.AbstractBinderC0063a.A0(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(int i8, Bundle bundle) {
    }

    public void g(int i8, Bundle bundle) {
        if (this.f216d) {
            Handler handler = this.f217e;
            if (handler != null) {
                handler.post(new c(i8, bundle));
                return;
            } else {
                f(i8, bundle);
                return;
            }
        }
        j.a aVar = this.f218f;
        if (aVar != null) {
            try {
                aVar.w0(i8, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        synchronized (this) {
            if (this.f218f == null) {
                this.f218f = new b();
            }
            parcel.writeStrongBinder(this.f218f.asBinder());
        }
    }
}
