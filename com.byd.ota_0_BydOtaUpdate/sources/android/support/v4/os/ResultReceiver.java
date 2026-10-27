package android.support.v4.os;

import a.d.a.b.a;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f56c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f57d = null;
    public a.d.a.b.a s;

    public class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResultReceiver[] newArray(int i2) {
            return new ResultReceiver[i2];
        }
    }

    public class b extends a.AbstractBinderC0002a {
        public b() {
        }

        @Override // a.d.a.b.a
        public void z(int i2, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f57d;
            if (handler != null) {
                handler.post(resultReceiver.new c(i2, bundle));
            } else {
                resultReceiver.a(i2, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f59c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Bundle f60d;

        public c(int i2, Bundle bundle) {
            this.f59c = i2;
            this.f60d = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f59c, this.f60d);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.s = a.AbstractBinderC0002a.B(parcel.readStrongBinder());
    }

    public void a(int i2, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        synchronized (this) {
            if (this.s == null) {
                this.s = new b();
            }
            parcel.writeStrongBinder(this.s.asBinder());
        }
    }
}
