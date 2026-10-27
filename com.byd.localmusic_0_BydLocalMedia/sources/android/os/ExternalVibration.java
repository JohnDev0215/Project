package android.os;

import android.media.AudioAttributes;
import android.os.IBinder;
import android.os.IExternalVibrationController;
import android.os.Parcelable;
import android.os.VibrationAttributes;
import android.util.Slog;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class ExternalVibration implements Parcelable {
    public static final Parcelable.Creator<ExternalVibration> CREATOR = new Parcelable.Creator<ExternalVibration>() { // from class: android.os.ExternalVibration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExternalVibration createFromParcel(Parcel parcel) {
            return new ExternalVibration(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExternalVibration[] newArray(int i) {
            return new ExternalVibration[i];
        }
    };
    private static final String TAG = "ExternalVibration";
    private AudioAttributes mAttrs;
    private IExternalVibrationController mController;
    private String mPkg;
    private IBinder mToken;
    private int mUid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExternalVibration(int i, String str, AudioAttributes audioAttributes, IExternalVibrationController iExternalVibrationController) {
        this.mUid = i;
        this.mPkg = (String) Preconditions.checkNotNull(str);
        this.mAttrs = (AudioAttributes) Preconditions.checkNotNull(audioAttributes);
        this.mController = (IExternalVibrationController) Preconditions.checkNotNull(iExternalVibrationController);
        this.mToken = new Binder();
    }

    private ExternalVibration(Parcel parcel) {
        this.mUid = parcel.readInt();
        this.mPkg = parcel.readString();
        this.mAttrs = readAudioAttributes(parcel);
        this.mController = IExternalVibrationController.Stub.asInterface(parcel.readStrongBinder());
        this.mToken = parcel.readStrongBinder();
    }

    private AudioAttributes readAudioAttributes(Parcel parcel) {
        int i = parcel.readInt();
        int i2 = parcel.readInt();
        int i3 = parcel.readInt();
        return new AudioAttributes.Builder().setUsage(i).setContentType(i2).setCapturePreset(i3).setFlags(parcel.readInt()).build();
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPackage() {
        return this.mPkg;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAttrs;
    }

    public VibrationAttributes getVibrationAttributes() {
        return new VibrationAttributes.Builder(this.mAttrs, null).build();
    }

    public boolean mute() {
        try {
            this.mController.mute();
            return true;
        } catch (RemoteException e) {
            Slog.wtf(TAG, "Failed to mute vibration stream: " + this, e);
            return false;
        }
    }

    public boolean unmute() {
        try {
            this.mController.unmute();
            return true;
        } catch (RemoteException e) {
            Slog.wtf(TAG, "Failed to unmute vibration stream: " + this, e);
            return false;
        }
    }

    public void linkToDeath(IBinder.DeathRecipient deathRecipient) {
        try {
            this.mToken.linkToDeath(deathRecipient, 0);
        } catch (RemoteException unused) {
        }
    }

    public void unlinkToDeath(IBinder.DeathRecipient deathRecipient) {
        this.mToken.unlinkToDeath(deathRecipient, 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ExternalVibration)) {
            return false;
        }
        return this.mToken.equals(((ExternalVibration) obj).mToken);
    }

    public String toString() {
        return "ExternalVibration{uid=" + this.mUid + ", pkg=" + this.mPkg + ", attrs=" + this.mAttrs + ", controller=" + this.mController + "token=" + this.mController + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mUid);
        parcel.writeString(this.mPkg);
        writeAudioAttributes(this.mAttrs, parcel, i);
        parcel.writeStrongBinder(this.mController.asBinder());
        parcel.writeStrongBinder(this.mToken);
    }

    private static void writeAudioAttributes(AudioAttributes audioAttributes, Parcel parcel, int i) {
        parcel.writeInt(audioAttributes.getUsage());
        parcel.writeInt(audioAttributes.getContentType());
        parcel.writeInt(audioAttributes.getCapturePreset());
        parcel.writeInt(audioAttributes.getAllFlags());
    }
}
