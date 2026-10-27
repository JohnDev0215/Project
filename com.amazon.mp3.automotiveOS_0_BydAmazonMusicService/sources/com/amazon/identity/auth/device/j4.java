package com.amazon.identity.auth.device;

import amazon.os.Build;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.framework.InvalidEnumValueException;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.utils.BuildConfiguration;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j4 extends o5 {
    public static final String d = o5.class.getName();
    public static final v5 e = new v5();
    public final t5 c;

    public j4(Context context) {
        this.c = t5.a(context.getApplicationContext());
    }

    public static final BuildConfiguration i() {
        String str = Build.TYPE;
        try {
            return BuildConfiguration.fromString(str);
        } catch (InvalidEnumValueException unused) {
            Log.e(ga.a(d), "Unable to determine the build type : " + str);
            return BuildConfiguration.User;
        }
    }

    @Override // com.amazon.identity.auth.device.o5
    public String a(y5 y5Var) {
        synchronized (fb.class) {
        }
        ga.a(d);
        return null;
    }

    @Override // com.amazon.identity.auth.device.o5
    public long b() {
        if (!qe.f(this.c)) {
            return s9.a().c;
        }
        long j = 0;
        if (!qe.b()) {
            try {
                j = Long.parseLong(e.a("ro.build.version.number"));
            } catch (NumberFormatException unused) {
            }
            Log.i(ga.a(d), "Amazon Platform is of version: " + j);
            return j;
        }
        Log.i(ga.a(d), "Amazon Platform is of version: " + Build.VERSION.SERIAL);
        if (Build.VERSION.SERIAL != null) {
            return Integer.parseInt(r0);
        }
        return 0L;
    }

    @Override // com.amazon.identity.auth.device.o5
    public String c() {
        synchronized (fb.class) {
        }
        Log.i(ga.a(d), "This should be a 3p device or 1p client side, cannot get DeviceSecret");
        return null;
    }

    @Override // com.amazon.identity.auth.device.o5
    public String d() {
        String strA = ra.a(this.c);
        if (!((TextUtils.isEmpty(strA) || strA.toLowerCase(Locale.US).equals(EnvironmentCompat.MEDIA_UNKNOWN)) ? false : true)) {
            ga.a(d);
            u4 u4VarA = u4.a(this.c);
            u4VarA.c();
            strA = u4VarA.b.b("dcp.third.party.device.state", "serial.number");
            if (strA == null) {
                Log.e(ga.a(u4.e), "Cannot generate the dsn", new Throwable());
            }
        }
        return strA;
    }

    @Override // com.amazon.identity.auth.device.o5
    public String f() {
        Log.i(ga.a(d), "Amazon Device Info will try get central device type");
        return oa.a(this.c, DeviceAttribute.CentralDeviceType);
    }

    @Override // com.amazon.identity.auth.device.o5
    public boolean h() {
        synchronized (fb.class) {
        }
        Log.i(ga.a(d), "This should be a 1p device, DHA is not supported");
        return false;
    }

    @Override // com.amazon.identity.auth.device.o5
    public String a() {
        String strB = MAPApplicationInformationQueryer.a(this.c).b(this.c.getPackageName());
        return strB == null ? d() : strB;
    }
}
