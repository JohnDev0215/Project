package com.byd.hvac.application;

import android.app.Application;
import b.a.a.f.f1;
import b.a.a.f.g1;
import b.a.a.h.b;
import b.a.a.h.h;
import b.a.a.h.i;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class HvacApplication extends Application {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f983a = HvacApplication.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        BydLogHelper.logW(this.f983a, "onCreate  异步 start  = ");
        int iE = f1.l0().E();
        h.b().d("KEY_AC_TYPE", iE);
        int iJ = g1.e().j();
        h.b().d("KEY_SUPPORT_FRAGRANCE", iJ);
        int iQ1 = f1.l0().q1(2);
        h.b().d("KEY_SUPPORT_GREEN", iQ1);
        int iC1 = f1.l0().c1();
        h.b().d("KEY_SUPPORT_SEAT_TYPE", iC1);
        BydLogHelper.logW(this.f983a, "onCreate  异步 acType= " + iE + " -- supportFragrance= " + iJ + " -- supportGreen= " + iQ1 + " -- seatType= " + iC1);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        b.b().c(this);
        f1.l0().r1(getApplicationContext());
        g1.e().u(getApplicationContext());
        if (h.b().a("KEY_AC_TYPE")) {
            i.b(new Runnable() { // from class: b.a.a.a.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f61a.b();
                }
            });
            return;
        }
        BydLogHelper.logW(this.f983a, "onCreate  同步 start  = ");
        int iE = f1.l0().E();
        h.b().d("KEY_AC_TYPE", iE);
        int iJ = g1.e().j();
        h.b().d("KEY_SUPPORT_FRAGRANCE", iJ);
        int iQ1 = f1.l0().q1(2);
        h.b().d("KEY_SUPPORT_GREEN", iQ1);
        int iC1 = f1.l0().c1();
        h.b().d("KEY_SUPPORT_SEAT_TYPE", iC1);
        BydLogHelper.logW(this.f983a, "onCreate  同步 acType= " + iE + " -- supportFragrance= " + iJ + " -- supportGreen= " + iQ1 + " -- seatType= " + iC1);
    }
}
