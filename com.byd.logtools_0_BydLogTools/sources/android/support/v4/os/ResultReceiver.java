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
    final boolean f54b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Handler f55c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    android.support.v4.os.a f56d;

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

    class b extends a.AbstractBinderC0003a {
        b() {
        }

        @Override // android.support.v4.os.a
        public void g(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f55c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.e(i, bundle);
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f58b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final Bundle f59c;

        c(int i, Bundle bundle) {
            this.f58b = i;
            this.f59c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.e(this.f58b, this.f59c);
        }
    }

    ResultReceiver(Parcel parcel) {
        this.f56d = a.AbstractBinderC0003a.h(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected void e(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f56d == null) {
                this.f56d = new b();
            }
            parcel.writeStrongBinder(this.f56d.asBinder());
        }
    }
}
