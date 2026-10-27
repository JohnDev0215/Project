package android.hardware.bydauto.dms;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.dms.tsimpl.DmsManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoDmsDevice extends AbsBYDAutoDevice {
    private static final int DMS_ATTENTION_OFF = 0;
    private static final int DMS_ATTENTION_ON = 1;
    private static final int DMS_FATIGUE_OFF = 0;
    private static final int DMS_FATIGUE_ON = 1;
    private static final int DMS_MODE_FACTORY = 2;
    private static final int DMS_MODE_SENSITIVE = 1;
    private static final int DMS_MODE_STANDARD = 0;
    private static final int DMS_OFF = 0;
    private static final int DMS_ON = 1;
    private static String TAG = "BYDAutoDmsDevice";
    private static BYDAutoDmsDevice sDevice;
    private DmsManagerImpl mDmsManagerImpl;

    BYDAutoDmsDevice(Context con) {
        super(con.getApplicationContext());
        this.mDmsManagerImpl = DmsManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoDmsDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoDmsDevice(con);
        }
        return sDevice;
    }

    public void queryDmsServiceStatus() {
        this.mDmsManagerImpl.queryDmsServiceStatus();
    }

    public void setDmsSwtichStatus(int state) {
        this.mDmsManagerImpl.setDmsSwtichStatus(state);
    }

    public void setPauseFatigueDetect() {
        this.mDmsManagerImpl.setPauseFatigueDetect();
    }

    public void setFatigueDetect(int state) {
        this.mDmsManagerImpl.setFatigueDetect(state);
    }

    public void setAttentionDetect(int state) {
        this.mDmsManagerImpl.setAttentionDetect(state);
    }

    public void getDmsSwitchChanged() {
        this.mDmsManagerImpl.getDmsSwitchChanged();
    }

    public void queryFatigueDetect() {
        this.mDmsManagerImpl.queryFatigueDetect();
    }

    public void queryAttentionDetect() {
        this.mDmsManagerImpl.queryAttentionDetect();
    }

    public void setDmsMode(int mode) {
        this.mDmsManagerImpl.setDmsMode(mode);
    }

    public void queryDmsMode() {
        this.mDmsManagerImpl.queryDmsMode();
    }

    public void registerListener(IDMSListener dmsListener) {
        this.mDmsManagerImpl.registerListener(dmsListener);
    }

    public void unregisterListener(IDMSListener dmsListener) {
        this.mDmsManagerImpl.unregisterListener(dmsListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }
}
