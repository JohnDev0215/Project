package android.support.v4.os;

import a.b.a.b.a;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f137b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f138c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.b.a.b.a f139d;

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

    public class b extends a.AbstractBinderC0006a {
        public b() {
        }

        @Override // a.b.a.b.a
        public void y(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f138c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.a(i, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f141b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f142c;

        public c(int i, Bundle bundle) {
            this.f141b = i;
            this.f142c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f141b, this.f142c);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f139d = a.AbstractBinderC0006a.B(parcel.readStrongBinder());
    }

    public void a(int i, Bundle bundle) {
    }

    public void b(int i, Bundle bundle) {
        if (this.f137b) {
            Handler handler = this.f138c;
            if (handler != null) {
                handler.post(new c(i, bundle));
                return;
            } else {
                a(i, bundle);
                return;
            }
        }
        a.b.a.b.a aVar = this.f139d;
        if (aVar != null) {
            try {
                aVar.y(i, bundle);
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
            if (this.f139d == null) {
                this.f139d = new b();
            }
            parcel.writeStrongBinder(this.f139d.asBinder());
        }
    }
}
