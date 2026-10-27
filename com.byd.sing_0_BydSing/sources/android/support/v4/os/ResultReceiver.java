package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.a;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f580b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Handler f581c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    android.support.v4.os.a f582d;

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

    class b extends a.AbstractBinderC0030a {
        b() {
        }

        @Override // android.support.v4.os.a
        public void p0(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f581c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.f(i, bundle);
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f584b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f585c;

        c(int i, Bundle bundle) {
            this.f584b = i;
            this.f585c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.f(this.f584b, this.f585c);
        }
    }

    ResultReceiver(Parcel parcel) {
        this.f582d = a.AbstractBinderC0030a.q0(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected void f(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f582d == null) {
                this.f582d = new b();
            }
            parcel.writeStrongBinder(this.f582d.asBinder());
        }
    }
}
