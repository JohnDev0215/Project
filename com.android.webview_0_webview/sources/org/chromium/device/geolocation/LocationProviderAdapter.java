package org.chromium.device.geolocation;

import J.N;
import android.location.Location;
import defpackage.Ts;
import defpackage.XE;
import defpackage.YE;
import defpackage.ZE;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LocationProviderAdapter {
    public XE a;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public LocationProviderAdapter() {
        /*
            r3 = this;
            r3.<init>()
            XE r0 = org.chromium.device.geolocation.LocationProviderFactory.a
            if (r0 == 0) goto L8
            goto L30
        L8:
            boolean r0 = org.chromium.device.geolocation.LocationProviderFactory.b
            if (r0 == 0) goto L27
            android.content.Context r0 = defpackage.Is.a
            nc r1 = defpackage.C1223nc.c
            int r2 = defpackage.AbstractC1278oc.a
            int r0 = r1.d(r0, r2)
            if (r0 != 0) goto L1a
            r0 = 1
            goto L1b
        L1a:
            r0 = 0
        L1b:
            if (r0 == 0) goto L27
            bF r0 = new bF
            android.content.Context r1 = defpackage.Is.a
            r0.<init>(r1)
            org.chromium.device.geolocation.LocationProviderFactory.a = r0
            goto L2e
        L27:
            aF r0 = new aF
            r0.<init>()
            org.chromium.device.geolocation.LocationProviderFactory.a = r0
        L2e:
            XE r0 = org.chromium.device.geolocation.LocationProviderFactory.a
        L30:
            r3.a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.device.geolocation.LocationProviderAdapter.<init>():void");
    }

    public static void a(String str) {
        Ts.a("LocationProvider", "newErrorAvailable %s", str);
        N.M8Iz7Ptw(str);
    }

    public static void b(Location location) {
        N.MvJnRjJi(location.getLatitude(), location.getLongitude(), location.getTime() / 1000.0d, location.hasAltitude(), location.getAltitude(), location.hasAccuracy(), location.getAccuracy(), location.hasBearing(), location.getBearing(), location.hasSpeed(), location.getSpeed());
    }

    public static LocationProviderAdapter create() {
        return new LocationProviderAdapter();
    }

    public void start(boolean z) {
        ThreadUtils.f(new FutureTask(new YE(this, z), null));
    }

    public void stop() {
        ThreadUtils.f(new FutureTask(new ZE(this), null));
    }
}
