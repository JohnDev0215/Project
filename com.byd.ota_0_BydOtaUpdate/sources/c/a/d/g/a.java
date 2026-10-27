package c.a.d.g;

import android.hardware.bydauto.ota.AbsMessageCallback;
import android.os.Bundle;
import c.a.b.d.e;
import c.a.b.d.n;
import c.a.g.l.o;
import java.util.Calendar;

/* JADX INFO: compiled from: CloudServiceListener.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AbsMessageCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2481a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c.a.g.c f2482b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.a f2483c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.d.a f2484d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.g.d f2485e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.b.c.a f2486f;

    public a(c.a.g.c cVar, c.a.a.a aVar, c.a.d.a aVar2, c.a.g.d dVar) {
        e.c(f2481a, "init CloudServiceListener");
        this.f2482b = cVar;
        this.f2483c = aVar;
        this.f2484d = aVar2;
        this.f2485e = dVar;
        this.f2486f = c.a.b.c.a.k();
    }

    public final void a(Bundle bundle) {
        String string = bundle.getString("uuid");
        e.c(f2481a, "uuid = " + string);
        this.f2486f.f0(string);
    }

    @Override // android.hardware.bydauto.ota.AbsMessageCallback
    public void onCloudServiceListener(int i2, Bundle bundle) {
        super.onCloudServiceListener(i2, bundle);
        String str = f2481a;
        e.c(str, "onCloudServiceListener->type = " + i2);
        if (i2 == 1) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e2) {
                e.a(f2481a, "processFailed " + e2);
            }
            int i3 = bundle.getInt("overtime");
            int i4 = bundle.getInt("silence");
            int i5 = bundle.getInt("soc");
            int i6 = bundle.getInt("voltage");
            String str2 = f2481a;
            e.c(str2, "onCloudServiceListener->overtime = " + i3 + ",silence = " + i4 + ",soc = " + i5 + ",voltage = " + i6);
            this.f2486f.S(i3);
            this.f2486f.b0(String.valueOf(i5));
            this.f2486f.d0(String.valueOf(i6));
            StringBuilder sb = new StringBuilder();
            sb.append("onCloudServiceListener->getSubmittingReportState = ");
            sb.append(this.f2484d.p());
            e.c(str2, sb.toString());
            if (this.f2484d.p() || this.f2483c.b()) {
                e.c(str2, "break factory update!");
                return;
            }
            if (i4 == 0) {
                a(bundle);
                e.a(str2, "remotePresenter =" + this.f2482b);
                if (this.f2482b != null) {
                    e.a(str2, "remotePresenter.stopDownload");
                    this.f2482b.f();
                }
                this.f2483c.e(5);
                return;
            }
            if (i4 == 1) {
                a(bundle);
                e.a(str2, "remotePresenter =" + this.f2482b);
                if (this.f2482b != null) {
                    e.a(str2, "remotePresenter.stopDownload");
                    this.f2482b.f();
                }
                this.f2483c.e(4);
                return;
            }
            return;
        }
        if (i2 == 2) {
            e.c(str, "onCloudServiceListener->deleteType = " + bundle.getInt("deleteType"));
            this.f2485e.f(false);
            o.a();
            c.a.b.d.d.g(c.a.b.b.a.f2322c);
            return;
        }
        if (i2 != 3) {
            return;
        }
        int i7 = bundle.getInt("updateType");
        e.c(str, "onCloudServiceListener->updateType = " + i7);
        if (i7 == 1) {
            if (!this.f2486f.w()) {
                e.c(str, "break mobile phone update,because update flag is false!");
                return;
            }
            e.c(str, "onCloudServiceListener->getSubmittingReportState = " + this.f2484d.p());
            if (this.f2484d.p() || this.f2483c.b()) {
                e.c(str, "break mobile phone update!");
                return;
            } else {
                a(bundle);
                this.f2483c.e(9);
                return;
            }
        }
        if (i7 != 2) {
            if (i7 == 3) {
                a(bundle);
                this.f2484d.i(0L);
                this.f2482b.c().j(2);
                return;
            }
            return;
        }
        if (!this.f2486f.w()) {
            e.c(str, "break mobile phone update,because update flag is false!");
            return;
        }
        long j = bundle.getLong("timestamp") * 1000;
        e.c(str, "onCloudServiceListener->timestamp = " + j);
        if (j <= 0) {
            e.c(str, "break mobile phone update,because timestamp <= 0!");
            return;
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        e.c(str, "curTime = " + n.d("yyyy-MM-dd HH:mm:ss", timeInMillis) + ", timestamp = " + n.d("yyyy-MM-dd HH:mm:ss", j));
        if (timeInMillis >= j) {
            e.c(str, "break mobile phone update,because curTime >= timestamp!");
            return;
        }
        a(bundle);
        this.f2484d.i(j);
        this.f2482b.c().i(1, n.d("yyyy-MM-dd HH:mm", j));
    }
}
