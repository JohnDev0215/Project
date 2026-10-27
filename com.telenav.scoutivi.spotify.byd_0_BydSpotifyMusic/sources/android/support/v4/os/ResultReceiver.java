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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f283e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final Handler f284g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    android.support.v4.os.a f285h;

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
        public ResultReceiver[] newArray(int i7) {
            return new ResultReceiver[i7];
        }
    }

    class b extends a.AbstractBinderC0009a {
        b() {
        }

        @Override // android.support.v4.os.a
        public void Q(int i7, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f284g;
            if (handler != null) {
                handler.post(resultReceiver.new c(i7, bundle));
            } else {
                resultReceiver.b(i7, bundle);
            }
        }
    }

    class c implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final int f287e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final Bundle f288g;

        c(int i7, Bundle bundle) {
            this.f287e = i7;
            this.f288g = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.b(this.f287e, this.f288g);
        }
    }

    public ResultReceiver(Handler handler) {
        this.f283e = true;
        this.f284g = handler;
    }

    protected void b(int i7, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        synchronized (this) {
            if (this.f285h == null) {
                this.f285h = new b();
            }
            parcel.writeStrongBinder(this.f285h.asBinder());
        }
    }

    ResultReceiver(Parcel parcel) {
        this.f283e = false;
        this.f284g = null;
        this.f285h = a.AbstractBinderC0009a.T(parcel.readStrongBinder());
    }
}
