package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import g.a;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f109b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f110c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a f111d;

    public class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResultReceiver[] newArray(int i3) {
            return new ResultReceiver[i3];
        }
    }

    public class b extends a.AbstractBinderC0012a {
        public b() {
        }

        @Override // g.a
        public void D(int i3, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f110c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i3, bundle));
            } else {
                resultReceiver.f(i3, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f113b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f114c;

        public c(int i3, Bundle bundle) {
            this.f113b = i3;
            this.f114c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.f(this.f113b, this.f114c);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f111d = a.AbstractBinderC0012a.F(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(int i3, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        synchronized (this) {
            if (this.f111d == null) {
                this.f111d = new b();
            }
            parcel.writeStrongBinder(this.f111d.asBinder());
        }
    }
}
