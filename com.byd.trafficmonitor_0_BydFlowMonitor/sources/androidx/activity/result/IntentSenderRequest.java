package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class IntentSenderRequest implements Parcelable {
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final IntentSender f236b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Intent f237c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f238d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f239e;

    public class a implements Parcelable.Creator<IntentSenderRequest> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest createFromParcel(Parcel parcel) {
            return new IntentSenderRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest[] newArray(int i2) {
            return new IntentSenderRequest[i2];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public IntentSender f240a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Intent f241b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f242c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f243d;

        public b(IntentSender intentSender) {
            this.f240a = intentSender;
        }

        public IntentSenderRequest a() {
            return new IntentSenderRequest(this.f240a, this.f241b, this.f242c, this.f243d);
        }

        public b b(Intent intent) {
            this.f241b = intent;
            return this;
        }

        public b c(int i2, int i3) {
            this.f243d = i2;
            this.f242c = i3;
            return this;
        }
    }

    public IntentSenderRequest(IntentSender intentSender, Intent intent, int i2, int i3) {
        this.f236b = intentSender;
        this.f237c = intent;
        this.f238d = i2;
        this.f239e = i3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Intent j() {
        return this.f237c;
    }

    public int k() {
        return this.f238d;
    }

    public int l() {
        return this.f239e;
    }

    public IntentSender m() {
        return this.f236b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f236b, i2);
        parcel.writeParcelable(this.f237c, i2);
        parcel.writeInt(this.f238d);
        parcel.writeInt(this.f239e);
    }

    public IntentSenderRequest(Parcel parcel) {
        this.f236b = (IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader());
        this.f237c = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.f238d = parcel.readInt();
        this.f239e = parcel.readInt();
    }
}
