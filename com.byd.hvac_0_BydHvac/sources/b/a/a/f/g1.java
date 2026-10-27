package b.a.a.f;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.SystemProperties;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f118b = g1.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoStudy f119c;
    public BYDAutoAcDevice d;
    public BYDAutoSettingDevice e;
    public a.a.a f;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static g1 f120a = new g1();
    }

    public static g1 e() {
        return a.f120a;
    }

    public int a() {
        BYDAutoAcDevice bYDAutoAcDevice = this.d;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.f118b, "getAcType mAutoAcDevice == null !!! ");
            return -1;
        }
        int acType = bYDAutoAcDevice.getAcType();
        BydLogHelper.logW(this.f118b, "getAcType acType = " + acType);
        return acType;
    }

    public int b() {
        BYDAutoAcDevice bYDAutoAcDevice = this.d;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.f118b, "getAcWindModeNum mAutoAcDevice == null !!! ");
            return -1;
        }
        int acWindModeNum = bYDAutoAcDevice.getAcWindModeNum();
        BydLogHelper.logW(this.f118b, "getAcWindModeNum acWindModeNum = " + acWindModeNum);
        return acWindModeNum;
    }

    public int c() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getAutoPurificationSupport mUiStyleConfig == null !!! ");
            return -1;
        }
        int autoPurificationSupport = bYDAutoStudy.getAutoPurificationSupport();
        BydLogHelper.logW(this.f118b, "getAutoPurificationSupport autoPurificationSupport = " + autoPurificationSupport);
        return autoPurificationSupport;
    }

    public int d() {
        BYDAutoAcDevice bYDAutoAcDevice = this.d;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.f118b, "getDefrostRearConfig mAutoAcDevice == null !!! ");
            return -1;
        }
        int defrostRearConfig = bYDAutoAcDevice.getDefrostRearConfig();
        BydLogHelper.logW(this.f118b, "getDefrostRearConfig defrostRearConfig = " + defrostRearConfig);
        return defrostRearConfig;
    }

    public int f() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getLeftPlatformConfigs mUiStyleConfig == null !!! ");
            return -1;
        }
        int leftPlatformConfigs = bYDAutoStudy.getLeftPlatformConfigs();
        BydLogHelper.logW(this.f118b, "getLeftPlatformConfigs leftPlatformConfigs = " + leftPlatformConfigs);
        return leftPlatformConfigs;
    }

    public int g() {
        BYDAutoAcDevice bYDAutoAcDevice = this.d;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(this.f118b, "getRearAcMaxWindLevel mAutoAcDevice == null !!! ");
            return -1;
        }
        int rearAcMaxWindLevel = bYDAutoAcDevice.getRearAcMaxWindLevel();
        BydLogHelper.logW(this.f118b, "getRearAcMaxWindLevel rearAcMaxWindLevel = " + rearAcMaxWindLevel);
        return rearAcMaxWindLevel;
    }

    public int h() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportAcAutoKeyCanClose mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportAcAutoKeyCanClose = bYDAutoStudy.getSupportAcAutoKeyCanClose();
        BydLogHelper.logW(this.f118b, "getSupportAcAutoKeyCanClose supportAcAutoKeyCanClose = " + supportAcAutoKeyCanClose);
        return supportAcAutoKeyCanClose;
    }

    public boolean i() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportFRElectricAirOutlet getSupportFRElectricAirOutlet == null !!! ");
            return false;
        }
        int supportFRElectricAirOutlet = bYDAutoStudy.getSupportFRElectricAirOutlet();
        BydLogHelper.logW(this.f118b, "getSupportFRElectricAirOutlet config = " + supportFRElectricAirOutlet);
        return supportFRElectricAirOutlet > 1 && supportFRElectricAirOutlet <= 8;
    }

    public int j() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportFragrance mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportFragrance = bYDAutoStudy.getSupportFragrance();
        BydLogHelper.logW(this.f118b, "getSupportFragrance supportFragrance = " + supportFragrance);
        return supportFragrance;
    }

    public int k() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportNegativeIon mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportNegativeIon = bYDAutoStudy.getSupportNegativeIon();
        BydLogHelper.logW(this.f118b, "getSupportNegativeIon supportNegativeIon = " + supportNegativeIon);
        return supportNegativeIon;
    }

    public int l() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportPM25Mode mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportPM25Mode = bYDAutoStudy.getSupportPM25Mode();
        BydLogHelper.logW(this.f118b, "getSupportPM25Mode supportPM25Mode = " + supportPM25Mode);
        return supportPM25Mode;
    }

    public int m() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAc mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAc = bYDAutoStudy.getSupportRearAc();
        BydLogHelper.logW(this.f118b, "getSupportRearAc supportRearAc = " + supportRearAc);
        return supportRearAc;
    }

    public int n() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcAutoControl mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcAutoControl = bYDAutoStudy.getSupportRearAcAutoControl();
        BydLogHelper.logW(this.f118b, "getSupportRearAcAutoControl supportRearAcAutoControl = " + supportRearAcAutoControl);
        return supportRearAcAutoControl;
    }

    public int o() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcKeyLock mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcKeyLock = bYDAutoStudy.getSupportRearAcKeyLock();
        BydLogHelper.logW(this.f118b, "getSupportRearAcKeyLock supportRearAcSwitch = " + supportRearAcKeyLock);
        return supportRearAcKeyLock;
    }

    public int p() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcSwitch mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcSwitch = bYDAutoStudy.getSupportRearAcSwitch();
        BydLogHelper.logW(this.f118b, "getSupportRearAcSwitch supportRearAcSwitch = " + supportRearAcSwitch);
        return supportRearAcSwitch;
    }

    public int q() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcTempertureControl mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcTempertureControl = bYDAutoStudy.getSupportRearAcTempertureControl();
        BydLogHelper.logW(this.f118b, "getSupportRearAcTempertureControl supportRearAcTempertureControl = " + supportRearAcTempertureControl);
        return supportRearAcTempertureControl;
    }

    public int r() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcWindLevelControl mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcWindLevelControl = bYDAutoStudy.getSupportRearAcWindLevelControl();
        BydLogHelper.logW(this.f118b, "getSupportRearAcWindLevelControl supportRearAcTempertureControl = " + supportRearAcWindLevelControl);
        return supportRearAcWindLevelControl;
    }

    public int s() {
        BYDAutoStudy bYDAutoStudy = this.f119c;
        if (bYDAutoStudy == null) {
            BydLogHelper.logW(this.f118b, "getSupportRearAcWindModeControl mUiStyleConfig == null !!! ");
            return -1;
        }
        int supportRearAcWindModeControl = bYDAutoStudy.getSupportRearAcWindModeControl();
        BydLogHelper.logW(this.f118b, "getSupportRearAcWindModeControl supportRearAcWindModeControl = " + supportRearAcWindModeControl);
        return supportRearAcWindModeControl;
    }

    public int t() {
        String str = SystemProperties.get(BYDAutoSysPropertiesDevice.UI_STYLE);
        BydLogHelper.logW(this.f118b, "getUiStyle uiStyle = " + str);
        if (str != null && str != "") {
            return Integer.parseInt(str);
        }
        BydLogHelper.logW(this.f118b, "getUiStyle uiStyle is null");
        return -1;
    }

    public void u(Context context) {
        BydLogHelper.logW(this.f118b, "UiStyleManager init start  = ");
        f117a = context;
        this.f119c = BYDAutoStudy.getInstance(context);
        this.d = BYDAutoAcDevice.getInstance(context);
        this.e = BYDAutoSettingDevice.getInstance(context);
        this.f = a.a.a.a(context);
        BydLogHelper.logW(this.f118b, "UiStyleManager init end  = ");
    }
}
