package android.hardware.bydauto.fragrance;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.fragrance.tsimpl.FragranceManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoFragranceDevice extends AbsBYDAutoDevice {
    public static final int FRAGRANCE_COMMAND_BUSY = -2147482647;
    public static final int FRAGRANCE_COMMAND_FAILED = -2147482648;
    public static final int FRAGRANCE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int FRAGRANCE_COMMAND_SUCCESS = 0;
    public static final int FRAGRANCE_COMMAND_TIMEOUT = -2147482646;
    public static final int FRAGRANCE_CONCENTRATION_CLOSE = 4;
    public static final int FRAGRANCE_CONCENTRATION_INVALID = 0;
    public static final int FRAGRANCE_CONCENTRATION_MID = 2;
    public static final int FRAGRANCE_CONCENTRATION_THICK = 3;
    public static final int FRAGRANCE_CONCENTRATION_THIN = 1;
    public static final int FRAGRANCE_ONLINE_STATE_NULL = 0;
    public static final int FRAGRANCE_ONLINE_STATE_OFF = 2;
    public static final int FRAGRANCE_ONLINE_STATE_ON = 1;
    public static final int FRAGRANCE_TYPE_1 = 1;
    public static final int FRAGRANCE_TYPE_2 = 2;
    public static final int FRAGRANCE_TYPE_3 = 3;
    public static final int PM2P5_ONLINE_STATE_NULL = 0;
    public static final int PM2P5_ONLINE_STATE_ON = 1;
    private static BYDAutoFragranceDevice sDevice;
    private final FragranceManagerImpl mFragranceManagerImpl;

    protected BYDAutoFragranceDevice(Context con) {
        super(con.getApplicationContext());
        this.mFragranceManagerImpl = FragranceManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoFragranceDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoFragranceDevice(con);
        }
        return sDevice;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mFragranceManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mFragranceManagerImpl.set(eventTypes, eventValue);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    public int getFragranceConcentration(int type) {
        return this.mFragranceManagerImpl.getFragranceConcentration(type);
    }

    public int getCurFragranceType() {
        return this.mFragranceManagerImpl.getCurFragranceType();
    }

    public int getFragranceResidue(int type) {
        return this.mFragranceManagerImpl.getFragranceResidue(type);
    }

    public int setFragranceConcentration(int value) {
        return this.mFragranceManagerImpl.setFragranceConcentration(value);
    }

    public int setFragranceType(int type) {
        return this.mFragranceManagerImpl.setFragranceType(type);
    }

    public int getFragranceSystemState() {
        return this.mFragranceManagerImpl.getFragranceSystemState();
    }

    public void registerListener(AbsBYDAutoFragranceListener listener) {
        this.mFragranceManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoFragranceListener listener, int[] featureIds) {
        this.mFragranceManagerImpl.registerListener(listener, featureIds);
    }

    public void unregisterListener(AbsBYDAutoFragranceListener listener) {
        this.mFragranceManagerImpl.unregisterListener(listener);
    }

    public int getFragranceOnlineState() {
        return this.mFragranceManagerImpl.getFragranceOnlineState();
    }

    public int getFragranceName(int type) {
        return this.mFragranceManagerImpl.getFragranceName(type);
    }

    public int getFragranceInstallState(int type) {
        return this.mFragranceManagerImpl.getFragranceInstallState(type);
    }

    public int getFragranceLessAllowanceState(int type) {
        return this.mFragranceManagerImpl.getFragranceLessAllowanceState(type);
    }
}
