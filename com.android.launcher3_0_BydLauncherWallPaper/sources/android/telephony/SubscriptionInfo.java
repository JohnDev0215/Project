package android.telephony;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionInfo implements Parcelable {
    public static final Parcelable.Creator<SubscriptionInfo> CREATOR = new Parcelable.Creator<SubscriptionInfo>() { // from class: android.telephony.SubscriptionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriptionInfo createFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            String string = parcel.readString();
            int i2 = parcel.readInt();
            CharSequence charSequenceCreateFromParcel = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            CharSequence charSequenceCreateFromParcel2 = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            int i3 = parcel.readInt();
            int i4 = parcel.readInt();
            String string2 = parcel.readString();
            int i5 = parcel.readInt();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            Bitmap bitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
            boolean z = parcel.readBoolean();
            UiccAccessRule[] uiccAccessRuleArr = (UiccAccessRule[]) parcel.createTypedArray(UiccAccessRule.CREATOR);
            String string6 = parcel.readString();
            int i6 = parcel.readInt();
            boolean z2 = parcel.readBoolean();
            String string7 = parcel.readString();
            boolean z3 = parcel.readBoolean();
            int i7 = parcel.readInt();
            int i8 = parcel.readInt();
            int i9 = parcel.readInt();
            String[] strArrCreateStringArray = parcel.createStringArray();
            String[] strArrCreateStringArray2 = parcel.createStringArray();
            SubscriptionInfo subscriptionInfo = new SubscriptionInfo(i, string, i2, charSequenceCreateFromParcel, charSequenceCreateFromParcel2, i3, i4, string2, i5, bitmap, string3, string4, string5, z, uiccAccessRuleArr, string6, i6, z2, string7, z3, i7, i8, i9, parcel.readString(), (UiccAccessRule[]) parcel.createTypedArray(UiccAccessRule.CREATOR), parcel.readBoolean());
            subscriptionInfo.setAssociatedPlmns(strArrCreateStringArray, strArrCreateStringArray2);
            return subscriptionInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriptionInfo[] newArray(int i) {
            return new SubscriptionInfo[i];
        }
    };
    private static final int TEXT_SIZE = 16;
    private boolean mAreUiccApplicationsEnabled;
    private int mCardId;
    private String mCardString;
    private UiccAccessRule[] mCarrierConfigAccessRules;
    private int mCarrierId;
    private CharSequence mCarrierName;
    private String mCountryIso;
    private int mDataRoaming;
    private CharSequence mDisplayName;
    private String[] mEhplmns;
    private String mGroupOwner;
    private ParcelUuid mGroupUUID;
    private String[] mHplmns;
    private String mIccId;
    private Bitmap mIconBitmap;
    private int mIconTint;
    private int mId;
    private boolean mIsEmbedded;
    private boolean mIsGroupDisabled;
    private boolean mIsOpportunistic;
    private String mMcc;
    private String mMnc;
    private int mNameSource;
    private UiccAccessRule[] mNativeAccessRules;
    private String mNumber;
    private int mProfileClass;
    private int mSimSlotIndex;
    private int mSubscriptionType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SubscriptionInfo(SubscriptionInfo subscriptionInfo) {
        int i = subscriptionInfo.mId;
        String str = subscriptionInfo.mIccId;
        int i2 = subscriptionInfo.mSimSlotIndex;
        CharSequence charSequence = subscriptionInfo.mDisplayName;
        CharSequence charSequence2 = subscriptionInfo.mCarrierName;
        int i3 = subscriptionInfo.mNameSource;
        int i4 = subscriptionInfo.mIconTint;
        String str2 = subscriptionInfo.mNumber;
        int i5 = subscriptionInfo.mDataRoaming;
        Bitmap bitmap = subscriptionInfo.mIconBitmap;
        String str3 = subscriptionInfo.mMcc;
        String str4 = subscriptionInfo.mMnc;
        String str5 = subscriptionInfo.mCountryIso;
        boolean z = subscriptionInfo.mIsEmbedded;
        UiccAccessRule[] uiccAccessRuleArr = subscriptionInfo.mNativeAccessRules;
        String str6 = subscriptionInfo.mCardString;
        int i6 = subscriptionInfo.mCardId;
        boolean z2 = subscriptionInfo.mIsOpportunistic;
        ParcelUuid parcelUuid = subscriptionInfo.mGroupUUID;
        this(i, str, i2, charSequence, charSequence2, i3, i4, str2, i5, bitmap, str3, str4, str5, z, uiccAccessRuleArr, str6, i6, z2, parcelUuid == null ? null : parcelUuid.toString(), subscriptionInfo.mIsGroupDisabled, subscriptionInfo.mCarrierId, subscriptionInfo.mProfileClass, subscriptionInfo.mSubscriptionType, subscriptionInfo.mGroupOwner, subscriptionInfo.mCarrierConfigAccessRules, subscriptionInfo.mAreUiccApplicationsEnabled);
    }

    public SubscriptionInfo(int i, String str, int i2, CharSequence charSequence, CharSequence charSequence2, int i3, int i4, String str2, int i5, Bitmap bitmap, String str3, String str4, String str5, boolean z, UiccAccessRule[] uiccAccessRuleArr, String str6) {
        this(i, str, i2, charSequence, charSequence2, i3, i4, str2, i5, bitmap, str3, str4, str5, z, uiccAccessRuleArr, str6, -1, false, null, false, -1, -1, 0, null, null, true);
    }

    public SubscriptionInfo(int i, String str, int i2, CharSequence charSequence, CharSequence charSequence2, int i3, int i4, String str2, int i5, Bitmap bitmap, String str3, String str4, String str5, boolean z, UiccAccessRule[] uiccAccessRuleArr, String str6, boolean z2, String str7, int i6, int i7) {
        this(i, str, i2, charSequence, charSequence2, i3, i4, str2, i5, bitmap, str3, str4, str5, z, uiccAccessRuleArr, str6, -1, z2, str7, false, i6, i7, 0, null, null, true);
    }

    public SubscriptionInfo(int i, String str, int i2, CharSequence charSequence, CharSequence charSequence2, int i3, int i4, String str2, int i5, Bitmap bitmap, String str3, String str4, String str5, boolean z, UiccAccessRule[] uiccAccessRuleArr, String str6, int i6, boolean z2, String str7, boolean z3, int i7, int i8, int i9, String str8, UiccAccessRule[] uiccAccessRuleArr2, boolean z4) {
        this.mIsGroupDisabled = false;
        this.mAreUiccApplicationsEnabled = true;
        this.mId = i;
        this.mIccId = str;
        this.mSimSlotIndex = i2;
        this.mDisplayName = charSequence;
        this.mCarrierName = charSequence2;
        this.mNameSource = i3;
        this.mIconTint = i4;
        this.mNumber = str2;
        this.mDataRoaming = i5;
        this.mIconBitmap = bitmap;
        this.mMcc = str3;
        this.mMnc = str4;
        this.mCountryIso = str5;
        this.mIsEmbedded = z;
        this.mNativeAccessRules = uiccAccessRuleArr;
        this.mCardString = str6;
        this.mCardId = i6;
        this.mIsOpportunistic = z2;
        this.mGroupUUID = str7 == null ? null : ParcelUuid.fromString(str7);
        this.mIsGroupDisabled = z3;
        this.mCarrierId = i7;
        this.mProfileClass = i8;
        this.mSubscriptionType = i9;
        this.mGroupOwner = str8;
        this.mCarrierConfigAccessRules = uiccAccessRuleArr2;
        this.mAreUiccApplicationsEnabled = z4;
    }

    public int getSubscriptionId() {
        return this.mId;
    }

    public String getIccId() {
        return this.mIccId;
    }

    public void clearIccId() {
        this.mIccId = "";
    }

    public int getSimSlotIndex() {
        return this.mSimSlotIndex;
    }

    public int getCarrierId() {
        return this.mCarrierId;
    }

    public CharSequence getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(CharSequence charSequence) {
        this.mDisplayName = charSequence;
    }

    public CharSequence getCarrierName() {
        return this.mCarrierName;
    }

    public void setCarrierName(CharSequence charSequence) {
        this.mCarrierName = charSequence;
    }

    public int getNameSource() {
        return this.mNameSource;
    }

    public void setAssociatedPlmns(String[] strArr, String[] strArr2) {
        this.mEhplmns = strArr;
        this.mHplmns = strArr2;
    }

    public Bitmap createIconBitmap(Context context) {
        int width = this.mIconBitmap.getWidth();
        int height = this.mIconBitmap.getHeight();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(displayMetrics, width, height, this.mIconBitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(this.mIconTint, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(this.mIconBitmap, 0.0f, 0.0f, paint);
        paint.setColorFilter(null);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create("sans-serif", 0));
        paint.setColor(-1);
        paint.setTextSize(displayMetrics.density * 16.0f);
        String str = String.format("%d", Integer.valueOf(this.mSimSlotIndex + 1));
        paint.getTextBounds(str, 0, 1, new Rect());
        canvas.drawText(str, (width / 2.0f) - r9.centerX(), (height / 2.0f) - r9.centerY(), paint);
        return bitmapCreateBitmap;
    }

    public int getIconTint() {
        return this.mIconTint;
    }

    public void setIconTint(int i) {
        this.mIconTint = i;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public void clearNumber() {
        this.mNumber = "";
    }

    public int getDataRoaming() {
        return this.mDataRoaming;
    }

    @Deprecated
    public int getMcc() {
        try {
            String str = this.mMcc;
            if (str == null) {
                return 0;
            }
            return Integer.valueOf(str).intValue();
        } catch (NumberFormatException unused) {
            Log.w(SubscriptionInfo.class.getSimpleName(), "MCC string is not a number");
            return 0;
        }
    }

    @Deprecated
    public int getMnc() {
        try {
            String str = this.mMnc;
            if (str == null) {
                return 0;
            }
            return Integer.valueOf(str).intValue();
        } catch (NumberFormatException unused) {
            Log.w(SubscriptionInfo.class.getSimpleName(), "MNC string is not a number");
            return 0;
        }
    }

    public String getMccString() {
        return this.mMcc;
    }

    public String getMncString() {
        return this.mMnc;
    }

    public String getCountryIso() {
        return this.mCountryIso;
    }

    public boolean isEmbedded() {
        return this.mIsEmbedded;
    }

    public boolean isOpportunistic() {
        return this.mIsOpportunistic;
    }

    public ParcelUuid getGroupUuid() {
        return this.mGroupUUID;
    }

    public void clearGroupUuid() {
        this.mGroupUUID = null;
    }

    public List<String> getEhplmns() {
        String[] strArr = this.mEhplmns;
        return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
    }

    public List<String> getHplmns() {
        String[] strArr = this.mHplmns;
        return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
    }

    public String getGroupOwner() {
        return this.mGroupOwner;
    }

    @SystemApi
    public int getProfileClass() {
        return this.mProfileClass;
    }

    public int getSubscriptionType() {
        return this.mSubscriptionType;
    }

    @Deprecated
    public boolean canManageSubscription(Context context) {
        return canManageSubscription(context, context.getPackageName());
    }

    @Deprecated
    public boolean canManageSubscription(Context context, String str) {
        List<UiccAccessRule> allAccessRules = getAllAccessRules();
        if (allAccessRules == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 134217728);
            Iterator<UiccAccessRule> it = allAccessRules.iterator();
            while (it.hasNext()) {
                if (it.next().getCarrierPrivilegeStatus(packageInfo) == 1) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("SubscriptionInfo", "canManageSubscription: Unknown package: " + str, e);
            return false;
        }
    }

    @SystemApi
    public List<UiccAccessRule> getAccessRules() {
        UiccAccessRule[] uiccAccessRuleArr = this.mNativeAccessRules;
        if (uiccAccessRuleArr == null) {
            return null;
        }
        return Arrays.asList(uiccAccessRuleArr);
    }

    public List<UiccAccessRule> getAllAccessRules() {
        ArrayList arrayList = new ArrayList();
        if (this.mNativeAccessRules != null) {
            arrayList.addAll(getAccessRules());
        }
        UiccAccessRule[] uiccAccessRuleArr = this.mCarrierConfigAccessRules;
        if (uiccAccessRuleArr != null) {
            arrayList.addAll(Arrays.asList(uiccAccessRuleArr));
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    public String getCardString() {
        return this.mCardString;
    }

    public void clearCardString() {
        this.mCardString = "";
    }

    public int getCardId() {
        return this.mCardId;
    }

    public void setGroupDisabled(boolean z) {
        this.mIsGroupDisabled = z;
    }

    @SystemApi
    public boolean isGroupDisabled() {
        return this.mIsGroupDisabled;
    }

    @SystemApi
    public boolean areUiccApplicationsEnabled() {
        return this.mAreUiccApplicationsEnabled;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeString(this.mIccId);
        parcel.writeInt(this.mSimSlotIndex);
        TextUtils.writeToParcel(this.mDisplayName, parcel, 0);
        TextUtils.writeToParcel(this.mCarrierName, parcel, 0);
        parcel.writeInt(this.mNameSource);
        parcel.writeInt(this.mIconTint);
        parcel.writeString(this.mNumber);
        parcel.writeInt(this.mDataRoaming);
        parcel.writeString(this.mMcc);
        parcel.writeString(this.mMnc);
        parcel.writeString(this.mCountryIso);
        parcel.writeParcelable(this.mIconBitmap, i);
        parcel.writeBoolean(this.mIsEmbedded);
        parcel.writeTypedArray(this.mNativeAccessRules, i);
        parcel.writeString(this.mCardString);
        parcel.writeInt(this.mCardId);
        parcel.writeBoolean(this.mIsOpportunistic);
        ParcelUuid parcelUuid = this.mGroupUUID;
        parcel.writeString(parcelUuid == null ? null : parcelUuid.toString());
        parcel.writeBoolean(this.mIsGroupDisabled);
        parcel.writeInt(this.mCarrierId);
        parcel.writeInt(this.mProfileClass);
        parcel.writeInt(this.mSubscriptionType);
        parcel.writeStringArray(this.mEhplmns);
        parcel.writeStringArray(this.mHplmns);
        parcel.writeString(this.mGroupOwner);
        parcel.writeTypedArray(this.mCarrierConfigAccessRules, i);
        parcel.writeBoolean(this.mAreUiccApplicationsEnabled);
    }

    public static String givePrintableIccid(String str) {
        if (str != null) {
            return (str.length() <= 9 || TelephonyUtils.IS_DEBUGGABLE) ? str : str.substring(0, 9) + com.android.telephony.Rlog.pii(false, (Object) str.substring(9));
        }
        return null;
    }

    public String toString() {
        return "{id=" + this.mId + " iccId=" + givePrintableIccid(this.mIccId) + " simSlotIndex=" + this.mSimSlotIndex + " carrierId=" + this.mCarrierId + " displayName=" + ((Object) this.mDisplayName) + " carrierName=" + ((Object) this.mCarrierName) + " nameSource=" + this.mNameSource + " iconTint=" + this.mIconTint + " number=" + com.android.telephony.Rlog.pii(TelephonyUtils.IS_DEBUGGABLE, this.mNumber) + " dataRoaming=" + this.mDataRoaming + " iconBitmap=" + this.mIconBitmap + " mcc=" + this.mMcc + " mnc=" + this.mMnc + " countryIso=" + this.mCountryIso + " isEmbedded=" + this.mIsEmbedded + " nativeAccessRules=" + Arrays.toString(this.mNativeAccessRules) + " cardString=" + givePrintableIccid(this.mCardString) + " cardId=" + this.mCardId + " isOpportunistic=" + this.mIsOpportunistic + " groupUUID=" + this.mGroupUUID + " isGroupDisabled=" + this.mIsGroupDisabled + " profileClass=" + this.mProfileClass + " ehplmns=" + Arrays.toString(this.mEhplmns) + " hplmns=" + Arrays.toString(this.mHplmns) + " subscriptionType=" + this.mSubscriptionType + " groupOwner=" + this.mGroupOwner + " carrierConfigAccessRules=" + Arrays.toString(this.mCarrierConfigAccessRules) + " areUiccApplicationsEnabled=" + this.mAreUiccApplicationsEnabled + "}";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mId), Integer.valueOf(this.mSimSlotIndex), Integer.valueOf(this.mNameSource), Integer.valueOf(this.mIconTint), Integer.valueOf(this.mDataRoaming), Boolean.valueOf(this.mIsEmbedded), Boolean.valueOf(this.mIsOpportunistic), this.mGroupUUID, this.mIccId, this.mNumber, this.mMcc, this.mMnc, this.mCountryIso, this.mCardString, Integer.valueOf(this.mCardId), this.mDisplayName, this.mCarrierName, this.mNativeAccessRules, Boolean.valueOf(this.mIsGroupDisabled), Integer.valueOf(this.mCarrierId), Integer.valueOf(this.mProfileClass), this.mGroupOwner, Boolean.valueOf(this.mAreUiccApplicationsEnabled));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        try {
            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) obj;
            return this.mId == subscriptionInfo.mId && this.mSimSlotIndex == subscriptionInfo.mSimSlotIndex && this.mNameSource == subscriptionInfo.mNameSource && this.mIconTint == subscriptionInfo.mIconTint && this.mDataRoaming == subscriptionInfo.mDataRoaming && this.mIsEmbedded == subscriptionInfo.mIsEmbedded && this.mIsOpportunistic == subscriptionInfo.mIsOpportunistic && this.mIsGroupDisabled == subscriptionInfo.mIsGroupDisabled && this.mAreUiccApplicationsEnabled == subscriptionInfo.mAreUiccApplicationsEnabled && this.mCarrierId == subscriptionInfo.mCarrierId && Objects.equals(this.mGroupUUID, subscriptionInfo.mGroupUUID) && Objects.equals(this.mIccId, subscriptionInfo.mIccId) && Objects.equals(this.mNumber, subscriptionInfo.mNumber) && Objects.equals(this.mMcc, subscriptionInfo.mMcc) && Objects.equals(this.mMnc, subscriptionInfo.mMnc) && Objects.equals(this.mCountryIso, subscriptionInfo.mCountryIso) && Objects.equals(this.mCardString, subscriptionInfo.mCardString) && Objects.equals(Integer.valueOf(this.mCardId), Integer.valueOf(subscriptionInfo.mCardId)) && Objects.equals(this.mGroupOwner, subscriptionInfo.mGroupOwner) && TextUtils.equals(this.mDisplayName, subscriptionInfo.mDisplayName) && TextUtils.equals(this.mCarrierName, subscriptionInfo.mCarrierName) && Arrays.equals(this.mNativeAccessRules, subscriptionInfo.mNativeAccessRules) && this.mProfileClass == subscriptionInfo.mProfileClass && Arrays.equals(this.mEhplmns, subscriptionInfo.mEhplmns) && Arrays.equals(this.mHplmns, subscriptionInfo.mHplmns);
        } catch (ClassCastException unused) {
            return false;
        }
    }
}
