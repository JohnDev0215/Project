package spi;

import android.app.UiStyleConfig;
import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.text.TextUtils;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.qg;

/* JADX INFO: loaded from: classes.dex */
public final class av {

    @NotNull
    public static final av a = new av();

    @NotNull
    public static String b = "";

    private av() {
    }

    @NotNull
    public final String a() {
        String str;
        Objects.requireNonNull(qg.a.a);
        str = "47";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str2 = (String) cls.getMethod("get", String.class).invoke(cls, BYDAutoSysPropertiesDevice.COUNTRY_CODE);
            str = TextUtils.isEmpty(str2) ? "47" : str2;
            ct.a("libVehicle getCountryCode countryCode:%s", str2);
        } catch (Exception e) {
            StringBuilder sbN = e.n("libVehicle getCountryCode error use default:", str, " msg:");
            sbN.append(e.getMessage());
            ct.b(sbN.toString(), new Object[0]);
        }
        Intrinsics.checkNotNullExpressionValue(str, "getInstance().countryCode");
        return str;
    }

    @NotNull
    public final String b(@NotNull Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Objects.requireNonNull(qg.a.a);
        str = "Di4.0_3.5UI";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str2 = (String) cls.getMethod("get", String.class).invoke(cls, "ro.vehicle.type");
            str = TextUtils.isEmpty(str2) ? "Di4.0_3.5UI" : str2;
            ct.a("libVehicle getPlatform platform:%s", str2);
        } catch (Exception e) {
            StringBuilder sbN = e.n("libVehicle getPlatform error use default:", str, " msg:");
            sbN.append(e.getMessage());
            ct.b(sbN.toString(), new Object[0]);
        }
        Intrinsics.checkNotNullExpressionValue(str, "getInstance().getPlatform(context)");
        return str;
    }

    public final int c(@Nullable Context context) {
        Objects.requireNonNull(qg.a.a);
        int autoType = 225;
        try {
            autoType = BYDAutoBodyworkDevice.getInstance(context).getAutoType();
            ct.a("libVehicle getVehicleType vehicleType:%s", Integer.valueOf(autoType));
            return autoType;
        } catch (Exception e) {
            StringBuilder sbJ = d.j("libVehicle getVehicleType error use default:", autoType, " msg:");
            sbJ.append(e.getMessage());
            ct.b(sbJ.toString(), new Object[0]);
            return autoType;
        }
    }

    public final boolean d(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return UiStyleConfig.getInstance(context).isDenza();
    }

    public final boolean e() {
        Exception e;
        String str;
        boolean zEquals;
        Objects.requireNonNull(qg.a.a);
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class).invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position");
        } catch (Exception e2) {
            e = e2;
            str = "0";
        }
        try {
            zEquals = Objects.equals(str, "1");
            try {
                ct.a("libVehicle isRightRudder targetIsRightRudder:%s rudder:%s", Boolean.valueOf(zEquals), str);
                return zEquals;
            } catch (Exception e3) {
                e = e3;
                ct.b("libVehicle isRightRudder error:" + zEquals + " rudder:" + str + " msg:" + e.getMessage(), new Object[0]);
                return Objects.equals(str, "1");
            }
        } catch (Exception e4) {
            e = e4;
            zEquals = false;
            ct.b("libVehicle isRightRudder error:" + zEquals + " rudder:" + str + " msg:" + e.getMessage(), new Object[0]);
            return Objects.equals(str, "1");
        }
    }

    public final void f(@Nullable Context context, @NotNull final Function2<? super Boolean, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        if (context == null) {
            ct.b("registerGearBoxListener context is null", new Object[0]);
            return;
        }
        qg qgVar = qg.a.a;
        y0 y0Var = new y0() { // from class: spi.zu
            @Override // spi.y0
            public final void a(boolean z, int i) {
                Function2 block2 = block;
                Intrinsics.checkNotNullParameter(block2, "$block");
                ct.a("registerGearBoxListener pGear:" + z + " gearLevel:" + i, new Object[0]);
                block2.mo6invoke(Boolean.valueOf(z), Integer.valueOf(i));
            }
        };
        Objects.requireNonNull(qgVar);
        if (qgVar.a == null) {
            ct.a("libVehicle registerGearBoxListener listener is null,init it", new Object[0]);
            qgVar.a = new og();
        }
        qgVar.a.a.push(y0Var);
        ct.a("libVehicle registerGearBoxListener listener outSize:%d", Integer.valueOf(qgVar.a.a.size()));
        try {
            BYDAutoGearboxDevice.getInstance(context).registerListener((AbsBYDAutoGearboxListener) qgVar.a);
            ct.a("libVehicle registerGearBoxListener success", new Object[0]);
        } catch (Exception e) {
            ct.b("libVehicle registerGearBoxListener error :%s", e.getMessage());
        }
    }
}
