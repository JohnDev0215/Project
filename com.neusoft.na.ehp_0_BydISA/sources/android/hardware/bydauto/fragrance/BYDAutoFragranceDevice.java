package android.hardware.bydauto.fragrance;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.fragrance.tsimpl.FragranceManagerImpl;
import org.java_websocket.extensions.ExtensionRequestData;

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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return ExtensionRequestData.EMPTY_VALUE;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return ExtensionRequestData.EMPTY_VALUE;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    protected BYDAutoFragranceDevice(Context context) {
        super(context.getApplicationContext());
        this.mFragranceManagerImpl = FragranceManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoFragranceDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoFragranceDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mFragranceManagerImpl.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mFragranceManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int getFragranceConcentration(int i) {
        return this.mFragranceManagerImpl.getFragranceConcentration(i);
    }

    public int getCurFragranceType() {
        return this.mFragranceManagerImpl.getCurFragranceType();
    }

    public int getFragranceResidue(int i) {
        return this.mFragranceManagerImpl.getFragranceResidue(i);
    }

    public int setFragranceConcentration(int i) {
        return this.mFragranceManagerImpl.setFragranceConcentration(i);
    }

    public int setFragranceType(int i) {
        return this.mFragranceManagerImpl.setFragranceType(i);
    }

    public int getFragranceSystemState() {
        return this.mFragranceManagerImpl.getFragranceSystemState();
    }

    public void registerListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        this.mFragranceManagerImpl.registerListener(absBYDAutoFragranceListener);
    }

    public void registerListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener, int[] iArr) {
        this.mFragranceManagerImpl.registerListener(absBYDAutoFragranceListener, iArr);
    }

    public void unregisterListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        this.mFragranceManagerImpl.unregisterListener(absBYDAutoFragranceListener);
    }

    public int getFragranceOnlineState() {
        return this.mFragranceManagerImpl.getFragranceOnlineState();
    }

    public int getFragranceName(int i) {
        return this.mFragranceManagerImpl.getFragranceName(i);
    }

    public int getFragranceInstallState(int i) {
        return this.mFragranceManagerImpl.getFragranceInstallState(i);
    }

    public int getFragranceLessAllowanceState(int i) {
        return this.mFragranceManagerImpl.getFragranceLessAllowanceState(i);
    }
}
