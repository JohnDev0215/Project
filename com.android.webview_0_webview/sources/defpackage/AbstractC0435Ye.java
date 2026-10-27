package defpackage;

import android.content.ServiceConnection;

/* JADX INFO: renamed from: Ye, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0435Ye {
    public static final Object a = new Object();
    public static AbstractC0435Ye b;

    public static int b() {
        return 129;
    }

    public boolean a(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        boolean z2;
        C0417Xe c0417Xe = new C0417Xe(str, str2, i, z);
        C0023Bf c0023Bf = (C0023Bf) this;
        Cif.g(serviceConnection, "ServiceConnection must not be null");
        synchronized (c0023Bf.c) {
            ServiceConnectionC0005Af serviceConnectionC0005Af = (ServiceConnectionC0005Af) c0023Bf.c.get(c0417Xe);
            if (serviceConnectionC0005Af == null) {
                serviceConnectionC0005Af = new ServiceConnectionC0005Af(c0023Bf, c0417Xe);
                C0023Bf c0023Bf2 = serviceConnectionC0005Af.g;
                C0149If c0149If = c0023Bf2.f;
                serviceConnectionC0005Af.e.a(c0023Bf2.d);
                serviceConnectionC0005Af.a.put(serviceConnection, serviceConnection);
                serviceConnectionC0005Af.a(str3);
                c0023Bf.c.put(c0417Xe, serviceConnectionC0005Af);
            } else {
                c0023Bf.e.removeMessages(0, c0417Xe);
                if (serviceConnectionC0005Af.a.containsKey(serviceConnection)) {
                    String strValueOf = String.valueOf(c0417Xe);
                    StringBuilder sb = new StringBuilder(strValueOf.length() + 81);
                    sb.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
                    sb.append(strValueOf);
                    throw new IllegalStateException(sb.toString());
                }
                C0023Bf c0023Bf3 = serviceConnectionC0005Af.g;
                C0149If c0149If2 = c0023Bf3.f;
                serviceConnectionC0005Af.e.a(c0023Bf3.d);
                serviceConnectionC0005Af.a.put(serviceConnection, serviceConnection);
                int i2 = serviceConnectionC0005Af.b;
                if (i2 == 1) {
                    serviceConnection.onServiceConnected(serviceConnectionC0005Af.f, serviceConnectionC0005Af.d);
                } else if (i2 == 2) {
                    serviceConnectionC0005Af.a(str3);
                }
            }
            z2 = serviceConnectionC0005Af.c;
        }
        return z2;
    }

    public void c(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        C0417Xe c0417Xe = new C0417Xe(str, str2, i, z);
        C0023Bf c0023Bf = (C0023Bf) this;
        Cif.g(serviceConnection, "ServiceConnection must not be null");
        synchronized (c0023Bf.c) {
            ServiceConnectionC0005Af serviceConnectionC0005Af = (ServiceConnectionC0005Af) c0023Bf.c.get(c0417Xe);
            if (serviceConnectionC0005Af == null) {
                String strValueOf = String.valueOf(c0417Xe);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 50);
                sb.append("Nonexistent connection status for service config: ");
                sb.append(strValueOf);
                throw new IllegalStateException(sb.toString());
            }
            if (!serviceConnectionC0005Af.a.containsKey(serviceConnection)) {
                String strValueOf2 = String.valueOf(c0417Xe);
                StringBuilder sb2 = new StringBuilder(strValueOf2.length() + 76);
                sb2.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
                sb2.append(strValueOf2);
                throw new IllegalStateException(sb2.toString());
            }
            C0149If c0149If = serviceConnectionC0005Af.g.f;
            serviceConnectionC0005Af.a.remove(serviceConnection);
            if (serviceConnectionC0005Af.a.isEmpty()) {
                c0023Bf.e.sendMessageDelayed(c0023Bf.e.obtainMessage(0, c0417Xe), c0023Bf.g);
            }
        }
    }
}
