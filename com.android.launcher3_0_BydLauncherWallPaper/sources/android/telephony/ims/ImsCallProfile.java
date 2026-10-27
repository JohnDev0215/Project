package android.telephony.ims;

import android.annotation.SystemApi;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.VideoProfile;
import android.telephony.emergency.EmergencyNumber;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class ImsCallProfile implements Parcelable {
    public static final int CALL_RESTRICT_CAUSE_DISABLED = 2;
    public static final int CALL_RESTRICT_CAUSE_HD = 3;
    public static final int CALL_RESTRICT_CAUSE_NONE = 0;
    public static final int CALL_RESTRICT_CAUSE_RAT = 1;
    public static final int CALL_TYPE_VIDEO_N_VOICE = 3;
    public static final int CALL_TYPE_VOICE = 2;
    public static final int CALL_TYPE_VOICE_N_VIDEO = 1;
    public static final int CALL_TYPE_VS = 8;
    public static final int CALL_TYPE_VS_RX = 10;
    public static final int CALL_TYPE_VS_TX = 9;
    public static final int CALL_TYPE_VT = 4;
    public static final int CALL_TYPE_VT_NODIR = 7;
    public static final int CALL_TYPE_VT_RX = 6;
    public static final int CALL_TYPE_VT_TX = 5;
    public static final Parcelable.Creator<ImsCallProfile> CREATOR = new Parcelable.Creator<ImsCallProfile>() { // from class: android.telephony.ims.ImsCallProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImsCallProfile createFromParcel(Parcel parcel) {
            return new ImsCallProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImsCallProfile[] newArray(int i) {
            return new ImsCallProfile[i];
        }
    };
    public static final int DIALSTRING_NORMAL = 0;
    public static final int DIALSTRING_SS_CONF = 1;
    public static final int DIALSTRING_USSD = 2;
    public static final String EXTRA_ADDITIONAL_CALL_INFO = "AdditionalCallInfo";
    public static final String EXTRA_ADDITIONAL_SIP_INVITE_FIELDS = "android.telephony.ims.extra.ADDITIONAL_SIP_INVITE_FIELDS";
    public static final String EXTRA_CALL_DISCONNECT_CAUSE = "android.telephony.ims.extra.CALL_DISCONNECT_CAUSE";
    public static final String EXTRA_CALL_MODE_CHANGEABLE = "call_mode_changeable";
    public static final String EXTRA_CALL_NETWORK_TYPE = "android.telephony.ims.extra.CALL_NETWORK_TYPE";

    @Deprecated
    public static final String EXTRA_CALL_RAT_TYPE = "CallRadioTech";

    @Deprecated
    public static final String EXTRA_CALL_RAT_TYPE_ALT = "callRadioTech";
    public static final String EXTRA_CHILD_NUMBER = "ChildNum";
    public static final String EXTRA_CNA = "cna";
    public static final String EXTRA_CNAP = "cnap";
    public static final String EXTRA_CODEC = "Codec";
    public static final String EXTRA_CONFERENCE = "conference";
    public static final String EXTRA_CONFERENCE_AVAIL = "conference_avail";
    public static final String EXTRA_DIALSTRING = "dialstring";
    public static final String EXTRA_DISPLAY_TEXT = "DisplayText";
    public static final String EXTRA_EMERGENCY_CALL = "e_call";
    public static final String EXTRA_FORWARDED_NUMBER = "android.telephony.ims.extra.FORWARDED_NUMBER";
    public static final String EXTRA_IS_CALL_PULL = "CallPull";
    public static final String EXTRA_OEM_EXTRAS = "android.telephony.ims.extra.OEM_EXTRAS";
    public static final String EXTRA_OI = "oi";
    public static final String EXTRA_OIR = "oir";
    public static final String EXTRA_REMOTE_URI = "remote_uri";
    public static final String EXTRA_RETRY_CALL_FAIL_NETWORKTYPE = "android.telephony.ims.extra.RETRY_CALL_FAIL_NETWORKTYPE";
    public static final String EXTRA_RETRY_CALL_FAIL_REASON = "android.telephony.ims.extra.RETRY_CALL_FAIL_REASON";
    public static final String EXTRA_USSD = "ussd";
    public static final String EXTRA_VMS = "vms";
    public static final int OIR_DEFAULT = 0;
    public static final int OIR_PRESENTATION_NOT_RESTRICTED = 2;
    public static final int OIR_PRESENTATION_PAYPHONE = 4;
    public static final int OIR_PRESENTATION_RESTRICTED = 1;
    public static final int OIR_PRESENTATION_UNKNOWN = 3;
    public static final int SERVICE_TYPE_EMERGENCY = 2;
    public static final int SERVICE_TYPE_NONE = 0;
    public static final int SERVICE_TYPE_NORMAL = 1;
    private static final String TAG = "ImsCallProfile";
    public static final int VERIFICATION_STATUS_FAILED = 2;
    public static final int VERIFICATION_STATUS_NOT_VERIFIED = 0;
    public static final int VERIFICATION_STATUS_PASSED = 1;
    public Bundle mCallExtras;
    public int mCallType;
    private int mCallerNumberVerificationStatus;
    private int mEmergencyCallRouting;
    private boolean mEmergencyCallTesting;
    private int mEmergencyServiceCategories;
    private List<String> mEmergencyUrns;
    private boolean mHasKnownUserIntentEmergency;
    public ImsStreamMediaProfile mMediaProfile;
    public int mRestrictCause;
    public int mServiceType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallRestrictCause {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VerificationStatus {
    }

    public static int OIRToPresentation(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 4 ? 3 : 4;
        }
        return 1;
    }

    public static int getVideoStateFromCallType(int i) {
        if (i == 2) {
            return 0;
        }
        if (i == 4) {
            return 3;
        }
        if (i != 5) {
            return i != 6 ? 0 : 2;
        }
        return 1;
    }

    private static boolean isVideoStateSet(int i, int i2) {
        return (i & i2) == i2;
    }

    public static int presentationToOIR(int i) {
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 1;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 4;
            if (i != 4) {
                return 0;
            }
        }
        return i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ImsCallProfile(Parcel parcel) {
        this.mRestrictCause = 0;
        this.mEmergencyServiceCategories = 0;
        this.mEmergencyUrns = new ArrayList();
        this.mEmergencyCallRouting = 0;
        this.mEmergencyCallTesting = false;
        this.mHasKnownUserIntentEmergency = false;
        readFromParcel(parcel);
    }

    public ImsCallProfile() {
        this.mRestrictCause = 0;
        this.mEmergencyServiceCategories = 0;
        this.mEmergencyUrns = new ArrayList();
        this.mEmergencyCallRouting = 0;
        this.mEmergencyCallTesting = false;
        this.mHasKnownUserIntentEmergency = false;
        this.mServiceType = 1;
        this.mCallType = 1;
        this.mCallExtras = new Bundle();
        this.mMediaProfile = new ImsStreamMediaProfile();
    }

    public ImsCallProfile(int i, int i2) {
        this.mRestrictCause = 0;
        this.mEmergencyServiceCategories = 0;
        this.mEmergencyUrns = new ArrayList();
        this.mEmergencyCallRouting = 0;
        this.mEmergencyCallTesting = false;
        this.mHasKnownUserIntentEmergency = false;
        this.mServiceType = i;
        this.mCallType = i2;
        this.mCallExtras = new Bundle();
        this.mMediaProfile = new ImsStreamMediaProfile();
    }

    public ImsCallProfile(int i, int i2, Bundle bundle, ImsStreamMediaProfile imsStreamMediaProfile) {
        this.mRestrictCause = 0;
        this.mEmergencyServiceCategories = 0;
        this.mEmergencyUrns = new ArrayList();
        this.mEmergencyCallRouting = 0;
        this.mEmergencyCallTesting = false;
        this.mHasKnownUserIntentEmergency = false;
        this.mServiceType = i;
        this.mCallType = i2;
        this.mCallExtras = bundle;
        this.mMediaProfile = imsStreamMediaProfile;
    }

    public String getCallExtra(String str) {
        return getCallExtra(str, "");
    }

    public String getCallExtra(String str, String str2) {
        Bundle bundle = this.mCallExtras;
        return bundle == null ? str2 : bundle.getString(str, str2);
    }

    public boolean getCallExtraBoolean(String str) {
        return getCallExtraBoolean(str, false);
    }

    public boolean getCallExtraBoolean(String str, boolean z) {
        Bundle bundle = this.mCallExtras;
        return bundle == null ? z : bundle.getBoolean(str, z);
    }

    public int getCallExtraInt(String str) {
        return getCallExtraInt(str, -1);
    }

    public int getCallExtraInt(String str, int i) {
        Bundle bundle = this.mCallExtras;
        return bundle == null ? i : bundle.getInt(str, i);
    }

    public void setCallExtra(String str, String str2) {
        Bundle bundle = this.mCallExtras;
        if (bundle != null) {
            bundle.putString(str, str2);
        }
    }

    public void setCallExtraBoolean(String str, boolean z) {
        Bundle bundle = this.mCallExtras;
        if (bundle != null) {
            bundle.putBoolean(str, z);
        }
    }

    public void setCallExtraInt(String str, int i) {
        Bundle bundle = this.mCallExtras;
        if (bundle != null) {
            bundle.putInt(str, i);
        }
    }

    public void setCallRestrictCause(int i) {
        this.mRestrictCause = i;
    }

    public void updateCallType(ImsCallProfile imsCallProfile) {
        this.mCallType = imsCallProfile.mCallType;
    }

    public void updateCallExtras(ImsCallProfile imsCallProfile) {
        this.mCallExtras.clear();
        this.mCallExtras = (Bundle) imsCallProfile.mCallExtras.clone();
    }

    public void updateMediaProfile(ImsCallProfile imsCallProfile) {
        this.mMediaProfile = imsCallProfile.mMediaProfile;
    }

    public void setCallerNumberVerificationStatus(int i) {
        this.mCallerNumberVerificationStatus = i;
    }

    public int getCallerNumberVerificationStatus() {
        return this.mCallerNumberVerificationStatus;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("{ serviceType=").append(this.mServiceType).append(", callType=").append(this.mCallType).append(", restrictCause=").append(this.mRestrictCause).append(", mediaProfile=");
        ImsStreamMediaProfile imsStreamMediaProfile = this.mMediaProfile;
        return sbAppend.append(imsStreamMediaProfile != null ? imsStreamMediaProfile.toString() : "null").append(", emergencyServiceCategories=").append(this.mEmergencyServiceCategories).append(", emergencyUrns=").append(this.mEmergencyUrns).append(", emergencyCallRouting=").append(this.mEmergencyCallRouting).append(", emergencyCallTesting=").append(this.mEmergencyCallTesting).append(", hasKnownUserIntentEmergency=").append(this.mHasKnownUserIntentEmergency).append(", mRestrictCause=").append(this.mRestrictCause).append(", mCallerNumberVerstat= ").append(this.mCallerNumberVerificationStatus).append(" }").toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundleMaybeCleanseExtras = maybeCleanseExtras(this.mCallExtras);
        parcel.writeInt(this.mServiceType);
        parcel.writeInt(this.mCallType);
        parcel.writeBundle(bundleMaybeCleanseExtras);
        parcel.writeParcelable(this.mMediaProfile, 0);
        parcel.writeInt(this.mEmergencyServiceCategories);
        parcel.writeStringList(this.mEmergencyUrns);
        parcel.writeInt(this.mEmergencyCallRouting);
        parcel.writeBoolean(this.mEmergencyCallTesting);
        parcel.writeBoolean(this.mHasKnownUserIntentEmergency);
        parcel.writeInt(this.mRestrictCause);
        parcel.writeInt(this.mCallerNumberVerificationStatus);
    }

    private void readFromParcel(Parcel parcel) {
        this.mServiceType = parcel.readInt();
        this.mCallType = parcel.readInt();
        this.mCallExtras = parcel.readBundle();
        this.mMediaProfile = (ImsStreamMediaProfile) parcel.readParcelable(ImsStreamMediaProfile.class.getClassLoader());
        this.mEmergencyServiceCategories = parcel.readInt();
        this.mEmergencyUrns = parcel.createStringArrayList();
        this.mEmergencyCallRouting = parcel.readInt();
        this.mEmergencyCallTesting = parcel.readBoolean();
        this.mHasKnownUserIntentEmergency = parcel.readBoolean();
        this.mRestrictCause = parcel.readInt();
        this.mCallerNumberVerificationStatus = parcel.readInt();
    }

    public int getServiceType() {
        return this.mServiceType;
    }

    public int getCallType() {
        return this.mCallType;
    }

    public int getRestrictCause() {
        return this.mRestrictCause;
    }

    public Bundle getCallExtras() {
        return this.mCallExtras;
    }

    public Bundle getProprietaryCallExtras() {
        Bundle bundle = this.mCallExtras;
        if (bundle == null) {
            return new Bundle();
        }
        Bundle bundle2 = bundle.getBundle(EXTRA_OEM_EXTRAS);
        if (bundle2 == null) {
            return new Bundle();
        }
        return new Bundle(bundle2);
    }

    public ImsStreamMediaProfile getMediaProfile() {
        return this.mMediaProfile;
    }

    public static int getVideoStateFromImsCallProfile(ImsCallProfile imsCallProfile) {
        int videoStateFromCallType = getVideoStateFromCallType(imsCallProfile.mCallType);
        return (!imsCallProfile.isVideoPaused() || VideoProfile.isAudioOnly(videoStateFromCallType)) ? videoStateFromCallType & (-5) : videoStateFromCallType | 4;
    }

    public static int getCallTypeFromVideoState(int i) {
        boolean zIsVideoStateSet = isVideoStateSet(i, 1);
        boolean zIsVideoStateSet2 = isVideoStateSet(i, 2);
        if (isVideoStateSet(i, 4)) {
            return 7;
        }
        if (zIsVideoStateSet && !zIsVideoStateSet2) {
            return 5;
        }
        if (zIsVideoStateSet || !zIsVideoStateSet2) {
            return (zIsVideoStateSet && zIsVideoStateSet2) ? 4 : 2;
        }
        return 6;
    }

    public static int presentationToOir(int i) {
        return presentationToOIR(i);
    }

    public boolean isVideoPaused() {
        return this.mMediaProfile.mVideoDirection == 0;
    }

    public boolean isVideoCall() {
        return VideoProfile.isVideo(getVideoStateFromCallType(this.mCallType));
    }

    private Bundle maybeCleanseExtras(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int size = bundle.size();
        Bundle bundleFilterValues = TelephonyUtils.filterValues(bundle);
        int size2 = bundleFilterValues.size();
        if (size != size2) {
            Log.i(TAG, "maybeCleanseExtras: " + (size - size2) + " extra values were removed - only primitive types and system parcelables are permitted.");
        }
        return bundleFilterValues;
    }

    public void setEmergencyCallInfo(EmergencyNumber emergencyNumber, boolean z) {
        setEmergencyServiceCategories(emergencyNumber.getEmergencyServiceCategoryBitmaskInternalDial());
        setEmergencyUrns(emergencyNumber.getEmergencyUrns());
        setEmergencyCallRouting(emergencyNumber.getEmergencyCallRouting());
        setEmergencyCallTesting(emergencyNumber.getEmergencyNumberSourceBitmask() == 32);
        setHasKnownUserIntentEmergency(z);
    }

    public void setEmergencyServiceCategories(int i) {
        this.mEmergencyServiceCategories = i;
    }

    public void setEmergencyUrns(List<String> list) {
        this.mEmergencyUrns = list;
    }

    public void setEmergencyCallRouting(int i) {
        this.mEmergencyCallRouting = i;
    }

    public void setEmergencyCallTesting(boolean z) {
        this.mEmergencyCallTesting = z;
    }

    public void setHasKnownUserIntentEmergency(boolean z) {
        this.mHasKnownUserIntentEmergency = z;
    }

    public int getEmergencyServiceCategories() {
        return this.mEmergencyServiceCategories;
    }

    public List<String> getEmergencyUrns() {
        return this.mEmergencyUrns;
    }

    public int getEmergencyCallRouting() {
        return this.mEmergencyCallRouting;
    }

    public boolean isEmergencyCallTesting() {
        return this.mEmergencyCallTesting;
    }

    public boolean hasKnownUserIntentEmergency() {
        return this.mHasKnownUserIntentEmergency;
    }
}
