package android.support.v4.os;

import a.d.a.c.a;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f177b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f178c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.d.a.c.a f179d;

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

    public class b extends a.AbstractBinderC0007a {
        public b() {
        }

        @Override // a.d.a.c.a
        public void H(int i2, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f178c;
            if (handler != null) {
                handler.post(resultReceiver.new c(i2, bundle));
            } else {
                resultReceiver.j(i2, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f181b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Bundle f182c;

        public c(int i2, Bundle bundle) {
            this.f181b = i2;
            this.f182c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.j(this.f181b, this.f182c);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f179d = a.AbstractBinderC0007a.K(parcel.readStrongBinder());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void j(int i2, Bundle bundle) {
    }

    public void k(int i2, Bundle bundle) {
        if (this.f177b) {
            Handler handler = this.f178c;
            if (handler != null) {
                handler.post(new c(i2, bundle));
                return;
            } else {
                j(i2, bundle);
                return;
            }
        }
        a.d.a.c.a aVar = this.f179d;
        if (aVar != null) {
            try {
                aVar.H(i2, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        synchronized (this) {
            if (this.f179d == null) {
                this.f179d = new b();
            }
            parcel.writeStrongBinder(this.f179d.asBinder());
        }
    }
}
