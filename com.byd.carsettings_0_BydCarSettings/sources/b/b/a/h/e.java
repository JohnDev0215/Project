package b.b.a.h;

import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.SystemProperties;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: DmsUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static boolean a() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.isp", "-1");
        String str2 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.name", "-1");
        String str3 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.lens", "-1");
        BydLogHelper.logD("CockpitModeStudy", "cameraIsp = " + str + ", cameraName = " + str2 + ", cameraLens = " + str3);
        int autoType = BYDAutoBodyworkDevice.getInstance(b.b.a.a.a.b().a()).getAutoType();
        StringBuilder sb = new StringBuilder();
        sb.append("isHasDms carType : ");
        sb.append(autoType);
        BydLogHelper.logD("CockpitModeStudy", sb.toString());
        return ((autoType != 196 && autoType != 182 && autoType != 208 && autoType != 60 && autoType != 123 && autoType != 143) || "-1".equals(str) || "-1".equals(str2) || "-1".equals(str3)) ? false : true;
    }
}
