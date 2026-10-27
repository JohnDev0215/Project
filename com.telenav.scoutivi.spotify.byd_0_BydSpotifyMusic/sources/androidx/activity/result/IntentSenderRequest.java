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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final IntentSender f354e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Intent f355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f357i;

    class a implements Parcelable.Creator<IntentSenderRequest> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest createFromParcel(Parcel parcel) {
            return new IntentSenderRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest[] newArray(int i7) {
            return new IntentSenderRequest[i7];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IntentSender f358a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Intent f359b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f360c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f361d;

        public b(IntentSender intentSender) {
            this.f358a = intentSender;
        }

        public IntentSenderRequest a() {
            return new IntentSenderRequest(this.f358a, this.f359b, this.f360c, this.f361d);
        }

        public b b(Intent intent) {
            this.f359b = intent;
            return this;
        }

        public b c(int i7, int i8) {
            this.f361d = i7;
            this.f360c = i8;
            return this;
        }
    }

    IntentSenderRequest(IntentSender intentSender, Intent intent, int i7, int i8) {
        this.f354e = intentSender;
        this.f355g = intent;
        this.f356h = i7;
        this.f357i = i8;
    }

    public Intent b() {
        return this.f355g;
    }

    public int c() {
        return this.f356h;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int h() {
        return this.f357i;
    }

    public IntentSender j() {
        return this.f354e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.f354e, i7);
        parcel.writeParcelable(this.f355g, i7);
        parcel.writeInt(this.f356h);
        parcel.writeInt(this.f357i);
    }

    IntentSenderRequest(Parcel parcel) {
        this.f354e = (IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader());
        this.f355g = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.f356h = parcel.readInt();
        this.f357i = parcel.readInt();
    }
}
