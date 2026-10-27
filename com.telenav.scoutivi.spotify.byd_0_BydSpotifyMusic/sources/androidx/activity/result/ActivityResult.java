package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class ActivityResult implements Parcelable {
    public static final Parcelable.Creator<ActivityResult> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f328e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Intent f329g;

    class a implements Parcelable.Creator<ActivityResult> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResult createFromParcel(Parcel parcel) {
            return new ActivityResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ActivityResult[] newArray(int i7) {
            return new ActivityResult[i7];
        }
    }

    public ActivityResult(int i7, Intent intent) {
        this.f328e = i7;
        this.f329g = intent;
    }

    public static String h(int i7) {
        return i7 != -1 ? i7 != 0 ? String.valueOf(i7) : "RESULT_CANCELED" : "RESULT_OK";
    }

    public Intent b() {
        return this.f329g;
    }

    public int c() {
        return this.f328e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + h(this.f328e) + ", data=" + this.f329g + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.f328e);
        parcel.writeInt(this.f329g == null ? 0 : 1);
        Intent intent = this.f329g;
        if (intent != null) {
            intent.writeToParcel(parcel, i7);
        }
    }

    ActivityResult(Parcel parcel) {
        this.f328e = parcel.readInt();
        this.f329g = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }
}
