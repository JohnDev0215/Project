package android.support.v4.os;

import a.d.a.b.a;
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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f165a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f166b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a.d.a.b.a f167c;

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

    public class b extends a.AbstractBinderC0007a {
        public b() {
        }

        @Override // a.d.a.b.a
        public void a0(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.f166b;
            if (handler != null) {
                handler.post(resultReceiver.new c(i, bundle));
            } else {
                resultReceiver.a(i, bundle);
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f169a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Bundle f170b;

        public c(int i, Bundle bundle) {
            this.f169a = i;
            this.f170b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.a(this.f169a, this.f170b);
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.f167c = a.AbstractBinderC0007a.d0(parcel.readStrongBinder());
    }

    public void a(int i, Bundle bundle) {
    }

    public void b(int i, Bundle bundle) {
        if (this.f165a) {
            Handler handler = this.f166b;
            if (handler != null) {
                handler.post(new c(i, bundle));
                return;
            } else {
                a(i, bundle);
                return;
            }
        }
        a.d.a.b.a aVar = this.f167c;
        if (aVar != null) {
            try {
                aVar.a0(i, bundle);
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
            if (this.f167c == null) {
                this.f167c = new b();
            }
            parcel.writeStrongBinder(this.f167c.asBinder());
        }
    }
}
