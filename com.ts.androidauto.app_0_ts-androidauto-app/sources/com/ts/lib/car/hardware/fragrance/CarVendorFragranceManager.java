package com.ts.lib.car.hardware.fragrance;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorFragranceManager {
    public static final int ID_CUR_FRAGRANCE_TYPE = 557858816;
    public static final int ID_FRAGRANCE_CONCENTRATIONR = 557858819;
    public static final int ID_FRAGRANCE_CONCENTRATIONS = 557858820;
    public static final int ID_FRAGRANCE_LESS_ALLOWANCE = 557858823;
    public static final int ID_FRAGRANCE_LESS_ALLOWANCE_THREE_R = 557858849;
    public static final int ID_FRAGRANCE_LESS_ALLOWANCE_TWO_R = 557858848;
    public static final int ID_FRAGRANCE_NAME_ONE_AAR = 557858824;
    public static final int ID_FRAGRANCE_NAME_ONE_ABR = 557858825;
    public static final int ID_FRAGRANCE_NAME_ONE_BAR = 557858826;
    public static final int ID_FRAGRANCE_NAME_ONE_BBR = 557858827;
    public static final int ID_FRAGRANCE_NAME_ONE_CAR = 557858828;
    public static final int ID_FRAGRANCE_NAME_ONE_CBR = 557858829;
    public static final int ID_FRAGRANCE_NAME_ONE_DAR = 557858830;
    public static final int ID_FRAGRANCE_NAME_ONE_DBR = 557858831;
    public static final int ID_FRAGRANCE_NAME_THREE_AAR = 557858840;
    public static final int ID_FRAGRANCE_NAME_THREE_ABR = 557858841;
    public static final int ID_FRAGRANCE_NAME_THREE_BAR = 557858842;
    public static final int ID_FRAGRANCE_NAME_THREE_BBR = 557858843;
    public static final int ID_FRAGRANCE_NAME_THREE_CAR = 557858844;
    public static final int ID_FRAGRANCE_NAME_THREE_CBR = 557858845;
    public static final int ID_FRAGRANCE_NAME_THREE_DAR = 557858846;
    public static final int ID_FRAGRANCE_NAME_THREE_DBR = 557858847;
    public static final int ID_FRAGRANCE_NAME_TWO_AAR = 557858832;
    public static final int ID_FRAGRANCE_NAME_TWO_ABR = 557858833;
    public static final int ID_FRAGRANCE_NAME_TWO_BAR = 557858834;
    public static final int ID_FRAGRANCE_NAME_TWO_BBR = 557858835;
    public static final int ID_FRAGRANCE_NAME_TWO_CAR = 557858836;
    public static final int ID_FRAGRANCE_NAME_TWO_CBR = 557858837;
    public static final int ID_FRAGRANCE_NAME_TWO_DAR = 557858838;
    public static final int ID_FRAGRANCE_NAME_TWO_DBR = 557858839;
    public static final int ID_FRAGRANCE_RESIDUE_ONE = 557858818;
    public static final int ID_FRAGRANCE_RESIDUE_THREE = 557858822;
    public static final int ID_FRAGRANCE_RESIDUE_TWO = 557858821;
    public static final int ID_FRAGRANCE_TYPES = 557858817;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_ONE = 557856028;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_THREE = 557856030;
    public static final int ID_SUPPORT_FRAGRANCE_TYPE_TWO = 557856029;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.fragrance";
    public static final String TAG = "CAR.VENDOR.FRAGRANCE";

    public interface OnCarFragranceEventListener {
        void onFragranceErrorEvent(int i, int i2);

        void onFragranceEventChange(FragranceEventChangeValue fragranceEventChangeValue);
    }

    void addCarFragranceEventListener(OnCarFragranceEventListener onCarFragranceEventListener) throws CarNotConnectedException;

    int getCurFragranceType() throws CarNotConnectedException;

    int getFragranceConcentrationR() throws CarNotConnectedException;

    int getFragranceLessAllowance() throws CarNotConnectedException;

    int getFragranceLessAllowanceThreeR() throws CarNotConnectedException;

    int getFragranceLessAllowanceTwoR() throws CarNotConnectedException;

    int getFragranceNameOneAaR() throws CarNotConnectedException;

    int getFragranceNameOneAbR() throws CarNotConnectedException;

    int getFragranceNameOneBaR() throws CarNotConnectedException;

    int getFragranceNameOneBbR() throws CarNotConnectedException;

    int getFragranceNameOneCaR() throws CarNotConnectedException;

    int getFragranceNameOneCbR() throws CarNotConnectedException;

    int getFragranceNameOneDaR() throws CarNotConnectedException;

    int getFragranceNameOneDbR() throws CarNotConnectedException;

    int getFragranceNameThreeAaR() throws CarNotConnectedException;

    int getFragranceNameThreeAbR() throws CarNotConnectedException;

    int getFragranceNameThreeBaR() throws CarNotConnectedException;

    int getFragranceNameThreeBbR() throws CarNotConnectedException;

    int getFragranceNameThreeCaR() throws CarNotConnectedException;

    int getFragranceNameThreeCbR() throws CarNotConnectedException;

    int getFragranceNameThreeDaR() throws CarNotConnectedException;

    int getFragranceNameThreeDbR() throws CarNotConnectedException;

    int getFragranceNameTwoAaR() throws CarNotConnectedException;

    int getFragranceNameTwoAbR() throws CarNotConnectedException;

    int getFragranceNameTwoBaR() throws CarNotConnectedException;

    int getFragranceNameTwoBbR() throws CarNotConnectedException;

    int getFragranceNameTwoCaR() throws CarNotConnectedException;

    int getFragranceNameTwoCbR() throws CarNotConnectedException;

    int getFragranceNameTwoDaR() throws CarNotConnectedException;

    int getFragranceNameTwoDbR() throws CarNotConnectedException;

    int getFragranceResidueOne() throws CarNotConnectedException;

    int getFragranceResidueThree() throws CarNotConnectedException;

    int getFragranceResidueTwo() throws CarNotConnectedException;

    int getSupportFragranceTypeOne() throws CarNotConnectedException;

    int getSupportFragranceTypeThree() throws CarNotConnectedException;

    int getSupportFragranceTypeTwo() throws CarNotConnectedException;

    void removeCarFragranceEventListener(OnCarFragranceEventListener onCarFragranceEventListener) throws CarNotConnectedException;

    void setFragranceConcentrationS(int i) throws CarNotConnectedException;

    void setFragranceTypeS(int i) throws CarNotConnectedException;
}
