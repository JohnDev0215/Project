package org.chromium.weblayer_private;

import J.N;
import android.R;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import defpackage.BinderC0998jS;
import defpackage.C1164mQ;
import defpackage.C1659vR;
import defpackage.InterfaceC1769xR;
import defpackage.Is;
import defpackage.M1;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class DownloadCallbackProxy {
    public long a;
    public String b;
    public InterfaceC1769xR c;

    public DownloadCallbackProxy(String str, long j) {
        this.b = str;
        this.a = N.McPAi5G9(this, j);
    }

    private void allowDownload(String str, String str2, String str3, long j) {
        if (WebLayerFactoryImpl.c() < 81) {
            N.MYOOvdZV(j, true);
            return;
        }
        if (this.c == null) {
            N.MYOOvdZV(j, false);
            return;
        }
        C1164mQ c1164mQ = new C1164mQ(this, j);
        ((C1659vR) this.c).c(str, str2, str3, new BinderC0998jS(c1164mQ));
    }

    private DownloadImpl createDownload(long j, int i) {
        return new DownloadImpl(this.b, this.c, j, i);
    }

    private void downloadCompleted(DownloadImpl downloadImpl) {
        InterfaceC1769xR interfaceC1769xR = this.c;
        if (interfaceC1769xR != null) {
            ((C1659vR) interfaceC1769xR).i(downloadImpl.c);
        }
        if (downloadImpl.g == null) {
            return;
        }
        downloadImpl.x();
    }

    private void downloadFailed(DownloadImpl downloadImpl) {
        InterfaceC1769xR interfaceC1769xR = this.c;
        if (interfaceC1769xR != null) {
            ((C1659vR) interfaceC1769xR).u(downloadImpl.c);
        }
        if (downloadImpl.g == null) {
            return;
        }
        downloadImpl.x();
    }

    private void downloadProgressChanged(DownloadImpl downloadImpl) {
        InterfaceC1769xR interfaceC1769xR = this.c;
        if (interfaceC1769xR != null) {
            ((C1659vR) interfaceC1769xR).v(downloadImpl.c);
        }
        if (downloadImpl.g == null) {
            return;
        }
        downloadImpl.x();
    }

    private void downloadStarted(DownloadImpl downloadImpl) {
        InterfaceC1769xR interfaceC1769xR = this.c;
        if (interfaceC1769xR != null) {
            ((C1659vR) interfaceC1769xR).w(downloadImpl.c);
        }
        if (downloadImpl.e) {
            return;
        }
        if (!DownloadImpl.h) {
            if (Build.VERSION.SDK_INT >= 26) {
                ((NotificationManager) Is.a.getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel("org.chromium.weblayer.downloads.channel", "Downloads", 3));
            }
            DownloadImpl.h = true;
        }
        DownloadImpl.i.put(Integer.valueOf(downloadImpl.f), downloadImpl);
        Intent intentC = downloadImpl.c();
        intentC.setAction("org.chromium.weblayer.downloads.DELETE");
        intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_ID", downloadImpl.f);
        intentC.putExtra("org.chromium.weblayer.downloads.NOTIFICATION_PROFILE", downloadImpl.a);
        PendingIntent broadcast = PendingIntent.getBroadcast(Is.a, downloadImpl.f, intentC, 0);
        M1 m1 = new M1(Is.a, "org.chromium.weblayer.downloads.channel");
        m1.t.icon = R.drawable.stat_sys_download;
        m1.d(2, true);
        m1.t.deleteIntent = broadcast;
        m1.g = 0;
        downloadImpl.g = m1;
        downloadImpl.x();
    }

    private boolean interceptDownload(String str, String str2, String str3, String str4, long j) {
        InterfaceC1769xR interfaceC1769xR = this.c;
        if (interfaceC1769xR == null) {
            return true;
        }
        return ((C1659vR) interfaceC1769xR).x(str, str2, str3, str4, j);
    }
}
