package defpackage;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.base.ThreadUtils;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: renamed from: bF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0539bF implements InterfaceC0110Gc, InterfaceC0128Hc, InterfaceC1558ti, XE {
    public final AbstractC0146Ic a;
    public InterfaceC1503si b = AbstractC1668vi.d;
    public boolean c;
    public LocationRequest d;

    public C0539bF(Context context) {
        Ts.d("LocationProvider", "Google Play Services", new Object[0]);
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        C0971j1 c0971j1 = new C0971j1();
        C0971j1 c0971j12 = new C0971j1();
        C1223nc c1223nc = C1223nc.c;
        AbstractC1552tc abstractC1552tc = AbstractC1230nj.c;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Looper mainLooper = context.getMainLooper();
        String packageName = context.getPackageName();
        String name = context.getClass().getName();
        C0020Bc c0020Bc = AbstractC1668vi.c;
        Cif.g(c0020Bc, "Api must not be null");
        c0971j12.put(c0020Bc, null);
        Objects.requireNonNull(c0020Bc.a);
        List listEmptyList = Collections.emptyList();
        hashSet2.addAll(listEmptyList);
        hashSet.addAll(listEmptyList);
        Cif.g(this, "Listener must not be null");
        arrayList.add(this);
        Cif.g(this, "Listener must not be null");
        arrayList2.add(this);
        Cif.b(!c0971j12.isEmpty(), "must call addApi() to add at least one API");
        C0381Ve c0381Ve = new C0381Ve(null, hashSet, c0971j1, 0, null, packageName, name, c0971j12.containsKey(AbstractC1230nj.e) ? (C0618cj) c0971j12.get(AbstractC1230nj.e) : C0618cj.j, false);
        Map map = c0381Ve.d;
        C0971j1 c0971j13 = new C0971j1();
        C0971j1 c0971j14 = new C0971j1();
        ArrayList arrayList3 = new ArrayList();
        for (C0020Bc c0020Bc2 : c0971j12.keySet()) {
            Object obj = c0971j12.get(c0020Bc2);
            boolean z = map.get(c0020Bc2) != null;
            c0971j13.put(c0020Bc2, Boolean.valueOf(z));
            C0004Ae c0004Ae = new C0004Ae(c0020Bc2, z);
            arrayList3.add(c0004Ae);
            Cif.i(c0020Bc2.a != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
            Object objA = c0020Bc2.a.a(context, mainLooper, c0381Ve, obj, c0004Ae, c0004Ae);
            c0971j14.put(c0020Bc2.a(), objA);
            Objects.requireNonNull((BaseGmsClient) objA);
        }
        C0165Jd c0165Jd = new C0165Jd(context, new ReentrantLock(), mainLooper, c0381Ve, c1223nc, abstractC1552tc, c0971j13, arrayList, arrayList2, c0971j14, -1, C0165Jd.l(c0971j14.values(), true), arrayList3);
        synchronized (AbstractC0146Ic.a) {
            AbstractC0146Ic.a.add(c0165Jd);
        }
        this.a = c0165Jd;
    }

    @Override // defpackage.XE
    public void a(boolean z) {
        ThreadUtils.b();
        if (this.a.i()) {
            this.a.e();
        }
        this.c = z;
        this.a.d();
    }

    @Override // defpackage.InterfaceC0397Wc
    public void d(int i) {
    }

    @Override // defpackage.InterfaceC0397Wc
    public void f(Bundle bundle) {
        LocationManager locationManager;
        Location locationV;
        ThreadUtils.b();
        LocationRequest locationRequest = new LocationRequest();
        this.d = locationRequest;
        if (this.c) {
            locationRequest.e(100);
            locationRequest.b(500L);
        } else {
            Objects.requireNonNull(Mx.a());
            Context context = Is.a;
            if (Build.VERSION.SDK_INT < 28 ? Settings.Secure.getInt(context.getContentResolver(), "location_mode", 0) == 1 : !((locationManager = (LocationManager) context.getSystemService("location")) == null || !C1405qt.c(locationManager) || !locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network"))) {
                this.d.e(100);
            } else {
                this.d.e(102);
            }
            this.d.b(1000L);
        }
        InterfaceC1503si interfaceC1503si = this.b;
        AbstractC0146Ic abstractC0146Ic = this.a;
        Objects.requireNonNull((C0295Qi) interfaceC1503si);
        Cif.b(abstractC0146Ic != null, "GoogleApiClient parameter is required.");
        C0188Ki c0188Ki = (C0188Ki) abstractC0146Ic.g(AbstractC1668vi.a);
        Cif.i(c0188Ki != null, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        try {
            locationV = c0188Ki.v();
        } catch (Exception unused) {
            locationV = null;
        }
        if (locationV != null) {
            LocationProviderAdapter.b(locationV);
        }
        try {
            InterfaceC1503si interfaceC1503si2 = this.b;
            AbstractC0146Ic abstractC0146Ic2 = this.a;
            LocationRequest locationRequest2 = this.d;
            Looper looperD = ThreadUtils.d();
            Objects.requireNonNull((C0295Qi) interfaceC1503si2);
            abstractC0146Ic2.f(new C0313Ri(abstractC0146Ic2, locationRequest2, this, looperD));
        } catch (IllegalStateException | SecurityException e) {
            Ts.a("LocationProvider", " mLocationProviderApi.requestLocationUpdates() " + e, new Object[0]);
            LocationProviderAdapter.a("Failed to request location updates: " + e.toString());
        }
    }

    @Override // defpackage.InterfaceC0947id
    public void i(ConnectionResult connectionResult) {
        StringBuilder sbF = Z7.f("Failed to connect to Google Play Services: ");
        sbF.append(connectionResult.toString());
        LocationProviderAdapter.a(sbF.toString());
    }

    @Override // defpackage.XE
    public void stop() {
        ThreadUtils.b();
        if (this.a.i()) {
            InterfaceC1503si interfaceC1503si = this.b;
            AbstractC0146Ic abstractC0146Ic = this.a;
            Objects.requireNonNull((C0295Qi) interfaceC1503si);
            abstractC0146Ic.f(new C1723wi(abstractC0146Ic, this));
            this.a.e();
        }
    }
}
