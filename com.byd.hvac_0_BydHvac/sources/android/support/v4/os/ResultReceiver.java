package android.support.v4.os;

import a.c.a.a.a;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f54a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f55b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a.c.a.a.a f56c;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements Parcelable.Creator<ResultReceiver> {
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
    public class b extends a.AbstractBinderC0001a {
        public b() {
        }

        @Override // a.c.a.a.a
        public void a(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f55b;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.n(i, bundle);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f58a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f59b;

        public c(int i, Bundle bundle) {
            this.f58a = i;
            this.f59b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.n(this.f58a, this.f59b);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f56c = a.AbstractBinderC0001a.b(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void n(int i, Bundle bundle) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.f56c == null) {
                this.f56c = new b();
            }
            parcel.writeStrongBinder(this.f56c.asBinder());
        }
    }
}
