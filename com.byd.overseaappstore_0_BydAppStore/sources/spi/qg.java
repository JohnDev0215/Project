package spi;

import android.os.SystemProperties;

/* JADX INFO: loaded from: classes.dex */
public final class qg {
    public og a;

    public static class a {
        public static qg a = new qg(null);

        private a() {
        }
    }

    private qg() {
    }

    public /* synthetic */ qg(e eVar) {
        this();
    }

    public static String a() {
        String str;
        try {
            str = SystemProperties.get("persist.sys.energytype");
        } catch (Exception e) {
            ct.b("libVehicle getPowerTypeProp error :%s", e.getMessage());
            str = "-1";
        }
        ct.a("libVehicle getPowerTypeProp powerTypeProp:%s", str);
        return str;
    }
}
