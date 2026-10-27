package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.os.a;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f153b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Handler f154c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    android.support.v4.os.a f155d;

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

    class b extends a.AbstractBinderC0009a {
        b() {
        }

        @Override // android.support.v4.os.a
        public void Y(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f154c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.a(i, bundle);
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f157b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f158c;

        c(int i, Bundle bundle) {
            this.f157b = i;
            this.f158c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f157b, this.f158c);
        }
    }

    ResultReceiver(Parcel parcel) {
        this.f155d = a.AbstractBinderC0009a.c0(parcel.readStrongBinder());
    }

    protected void a(int i, Bundle bundle) {
    }

    public void c(int i, Bundle bundle) {
        if (this.f153b) {
            Handler handler = this.f154c;
            if (handler != null) {
                handler.post(new c(i, bundle));
                return;
            } else {
                a(i, bundle);
                return;
            }
        }
        android.support.v4.os.a aVar = this.f155d;
        if (aVar != null) {
            try {
                aVar.Y(i, bundle);
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
            if (this.f155d == null) {
                this.f155d = new b();
            }
            parcel.writeStrongBinder(this.f155d.asBinder());
        }
    }
}
