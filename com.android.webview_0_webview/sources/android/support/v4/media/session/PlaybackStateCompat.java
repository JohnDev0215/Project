package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.AbstractC1025k;
import defpackage.C1137m;
import defpackage.C1192n;
import defpackage.Z7;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1137m();
    public List A;
    public final long B;
    public final Bundle C;
    public final int s;
    public final long t;
    public final long u;
    public final float v;
    public final long w;
    public final int x;
    public final CharSequence y;
    public final long z;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public final class CustomAction implements Parcelable {
        public static final Parcelable.Creator CREATOR = new C1192n();
        public final String s;
        public final CharSequence t;
        public final int u;
        public final Bundle v;

        public CustomAction(Parcel parcel) {
            this.s = parcel.readString();
            this.t = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.u = parcel.readInt();
            this.v = parcel.readBundle(AbstractC1025k.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder sbF = Z7.f("Action:mName='");
            sbF.append((Object) this.t);
            sbF.append(", mIcon=");
            sbF.append(this.u);
            sbF.append(", mExtras=");
            sbF.append(this.v);
            return sbF.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.s);
            TextUtils.writeToParcel(this.t, parcel, i);
            parcel.writeInt(this.u);
            parcel.writeBundle(this.v);
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.s = parcel.readInt();
        this.t = parcel.readLong();
        this.v = parcel.readFloat();
        this.z = parcel.readLong();
        this.u = parcel.readLong();
        this.w = parcel.readLong();
        this.y = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.A = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.B = parcel.readLong();
        this.C = parcel.readBundle(AbstractC1025k.class.getClassLoader());
        this.x = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "PlaybackState {state=" + this.s + ", position=" + this.t + ", buffered position=" + this.u + ", speed=" + this.v + ", updated=" + this.z + ", actions=" + this.w + ", error code=" + this.x + ", error message=" + this.y + ", custom actions=" + this.A + ", active item id=" + this.B + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.s);
        parcel.writeLong(this.t);
        parcel.writeFloat(this.v);
        parcel.writeLong(this.z);
        parcel.writeLong(this.u);
        parcel.writeLong(this.w);
        TextUtils.writeToParcel(this.y, parcel, i);
        parcel.writeTypedList(this.A);
        parcel.writeLong(this.B);
        parcel.writeBundle(this.C);
        parcel.writeInt(this.x);
    }
}
