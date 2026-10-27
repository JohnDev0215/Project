package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.carrier.CarrierIdentifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class CarrierRestrictionRules implements Parcelable {
    public static final int CARRIER_RESTRICTION_DEFAULT_ALLOWED = 1;
    public static final int CARRIER_RESTRICTION_DEFAULT_NOT_ALLOWED = 0;
    public static final Parcelable.Creator<CarrierRestrictionRules> CREATOR = new Parcelable.Creator<CarrierRestrictionRules>() { // from class: android.telephony.CarrierRestrictionRules.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarrierRestrictionRules createFromParcel(Parcel parcel) {
            return new CarrierRestrictionRules(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarrierRestrictionRules[] newArray(int i) {
            return new CarrierRestrictionRules[i];
        }
    };
    public static final int MULTISIM_POLICY_NONE = 0;
    public static final int MULTISIM_POLICY_ONE_VALID_SIM_MUST_BE_PRESENT = 1;
    private static final char WILD_CHARACTER = '?';
    private List<CarrierIdentifier> mAllowedCarriers;
    private int mCarrierRestrictionDefault;
    private List<CarrierIdentifier> mExcludedCarriers;
    private int mMultiSimPolicy;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarrierRestrictionDefault {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MultiSimPolicy {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CarrierRestrictionRules() {
        this.mAllowedCarriers = new ArrayList();
        this.mExcludedCarriers = new ArrayList();
        this.mCarrierRestrictionDefault = 0;
        this.mMultiSimPolicy = 0;
    }

    private CarrierRestrictionRules(Parcel parcel) {
        this.mAllowedCarriers = new ArrayList();
        this.mExcludedCarriers = new ArrayList();
        parcel.readTypedList(this.mAllowedCarriers, CarrierIdentifier.CREATOR);
        parcel.readTypedList(this.mExcludedCarriers, CarrierIdentifier.CREATOR);
        this.mCarrierRestrictionDefault = parcel.readInt();
        this.mMultiSimPolicy = parcel.readInt();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isAllCarriersAllowed() {
        return this.mAllowedCarriers.isEmpty() && this.mExcludedCarriers.isEmpty() && this.mCarrierRestrictionDefault == 1;
    }

    public List<CarrierIdentifier> getAllowedCarriers() {
        return this.mAllowedCarriers;
    }

    public List<CarrierIdentifier> getExcludedCarriers() {
        return this.mExcludedCarriers;
    }

    public int getDefaultCarrierRestriction() {
        return this.mCarrierRestrictionDefault;
    }

    public int getMultiSimPolicy() {
        return this.mMultiSimPolicy;
    }

    public List<Boolean> areCarrierIdentifiersAllowed(List<CarrierIdentifier> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            boolean zIsCarrierIdInList = isCarrierIdInList(list.get(i), this.mAllowedCarriers);
            boolean zIsCarrierIdInList2 = isCarrierIdInList(list.get(i), this.mExcludedCarriers);
            if (this.mCarrierRestrictionDefault == 0) {
                arrayList.add(Boolean.valueOf(zIsCarrierIdInList && !zIsCarrierIdInList2));
            } else {
                if (zIsCarrierIdInList2 && !zIsCarrierIdInList) {
                    z = false;
                }
                arrayList.add(Boolean.valueOf(z));
            }
            i++;
        }
        if (this.mMultiSimPolicy == 1) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((Boolean) it.next()).booleanValue()) {
                    arrayList.replaceAll(new UnaryOperator() { // from class: android.telephony.-$$Lambda$CarrierRestrictionRules$XeBzxZcOJGyFPu6CCh0Khud2FHI
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return CarrierRestrictionRules.lambda$areCarrierIdentifiersAllowed$0((Boolean) obj);
                        }
                    });
                    break;
                }
            }
        }
        return arrayList;
    }

    static /* synthetic */ Boolean lambda$areCarrierIdentifiersAllowed$0(Boolean bool) {
        return true;
    }

    private static boolean isCarrierIdInList(CarrierIdentifier carrierIdentifier, List<CarrierIdentifier> list) {
        for (CarrierIdentifier carrierIdentifier2 : list) {
            if (patternMatch(carrierIdentifier.getMcc(), carrierIdentifier2.getMcc()) && patternMatch(carrierIdentifier.getMnc(), carrierIdentifier2.getMnc())) {
                String strConvertNullToEmpty = convertNullToEmpty(carrierIdentifier2.getSpn());
                String strConvertNullToEmpty2 = convertNullToEmpty(carrierIdentifier.getSpn());
                if (strConvertNullToEmpty.isEmpty() || patternMatch(strConvertNullToEmpty2, strConvertNullToEmpty)) {
                    String strConvertNullToEmpty3 = convertNullToEmpty(carrierIdentifier2.getImsi());
                    String strConvertNullToEmpty4 = convertNullToEmpty(carrierIdentifier.getImsi());
                    if (patternMatch(strConvertNullToEmpty4.substring(0, Math.min(strConvertNullToEmpty4.length(), strConvertNullToEmpty3.length())), strConvertNullToEmpty3)) {
                        String strConvertNullToEmpty5 = convertNullToEmpty(carrierIdentifier2.getGid1());
                        String strConvertNullToEmpty6 = convertNullToEmpty(carrierIdentifier.getGid1());
                        if (patternMatch(strConvertNullToEmpty6.substring(0, Math.min(strConvertNullToEmpty6.length(), strConvertNullToEmpty5.length())), strConvertNullToEmpty5)) {
                            String strConvertNullToEmpty7 = convertNullToEmpty(carrierIdentifier2.getGid2());
                            String strConvertNullToEmpty8 = convertNullToEmpty(carrierIdentifier.getGid2());
                            if (patternMatch(strConvertNullToEmpty8.substring(0, Math.min(strConvertNullToEmpty8.length(), strConvertNullToEmpty7.length())), strConvertNullToEmpty7)) {
                                return true;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return false;
    }

    private static String convertNullToEmpty(String str) {
        return Objects.toString(str, "");
    }

    private static boolean patternMatch(String str, String str2) {
        if (str.length() != str2.length()) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        for (int i = 0; i < lowerCase2.length(); i++) {
            if (lowerCase2.charAt(i) != lowerCase.charAt(i) && lowerCase2.charAt(i) != '?') {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mAllowedCarriers);
        parcel.writeTypedList(this.mExcludedCarriers);
        parcel.writeInt(this.mCarrierRestrictionDefault);
        parcel.writeInt(this.mMultiSimPolicy);
    }

    public String toString() {
        return "CarrierRestrictionRules(allowed:" + this.mAllowedCarriers + ", excluded:" + this.mExcludedCarriers + ", default:" + this.mCarrierRestrictionDefault + ", multisim policy:" + this.mMultiSimPolicy + ")";
    }

    public static final class Builder {
        private final CarrierRestrictionRules mRules = new CarrierRestrictionRules();

        public CarrierRestrictionRules build() {
            return this.mRules;
        }

        public Builder setAllCarriersAllowed() {
            this.mRules.mAllowedCarriers.clear();
            this.mRules.mExcludedCarriers.clear();
            this.mRules.mCarrierRestrictionDefault = 1;
            return this;
        }

        public Builder setAllowedCarriers(List<CarrierIdentifier> list) {
            this.mRules.mAllowedCarriers = new ArrayList(list);
            return this;
        }

        public Builder setExcludedCarriers(List<CarrierIdentifier> list) {
            this.mRules.mExcludedCarriers = new ArrayList(list);
            return this;
        }

        public Builder setDefaultCarrierRestriction(int i) {
            this.mRules.mCarrierRestrictionDefault = i;
            return this;
        }

        public Builder setMultiSimPolicy(int i) {
            this.mRules.mMultiSimPolicy = i;
            return this;
        }
    }
}
