package defpackage;

import J.N;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import androidx.core.app.NotificationCompat$Builder;
import defpackage.ag3;
import defpackage.kp1;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browserservices.ui.trustedwebactivity.DisclosureAcceptanceBroadcastReceiver;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: DisclosureNotification.java */
/* JADX INFO: loaded from: classes.dex */
public final class qa0 implements k.a<v62>, kr2 {
    public final Context e;
    public final Resources f;
    public final ag3 g;
    public final so1 h;
    public String i;

    public qa0(Context context, Resources resources, so1 so1Var, ag3 ag3Var, q2 q2Var) {
        this.e = context;
        this.f = resources;
        this.h = so1Var;
        this.g = ag3Var;
        ag3Var.a(this);
        q2Var.f(this);
    }

    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k<v62> kVar, v62 v62Var) {
        v62 v62Var2 = v62Var;
        PropertyModel.p pVar = ag3.c;
        if (v62Var2 != pVar) {
            return;
        }
        int iH = this.g.h(pVar);
        if (iH != 0) {
            if (iH != 1) {
                return;
            }
            b();
        } else {
            String str = this.i;
            so1 so1Var = this.h;
            so1Var.d(13, str);
            so1Var.d(14, this.i);
            this.i = null;
        }
    }

    public final void b() {
        int i;
        String str;
        int i2;
        int i3;
        PropertyModel.r<String> rVar = ag3.d;
        ag3 ag3Var = this.g;
        this.i = (String) ag3Var.j(rVar);
        boolean zK = ag3Var.k(ag3.e);
        String str2 = (String) ag3Var.j(ag3.g);
        String str3 = this.i;
        if (zK) {
            i = 2;
            str = "twa_disclosure_initial";
            i2 = 13;
            i3 = 23;
        } else {
            i = -2;
            str = "twa_disclosure_subsequent";
            i2 = 14;
            i3 = 24;
        }
        o8 o8Var = new o8(i3, i2, str3);
        int i4 = R$string.twa_running_in_chrome;
        Resources resources = this.f;
        String string = resources.getString(i4);
        String string2 = resources.getString(R$string.twa_running_in_chrome_v2, N.MeroQv$e(str3));
        int i5 = DisclosureAcceptanceBroadcastReceiver.c;
        Intent intent = new Intent();
        Context context = this.e;
        intent.setClass(context, DisclosureAcceptanceBroadcastReceiver.class);
        intent.putExtra("TWADisclosureResp.tag_extra", str3);
        intent.putExtra("TWADisclosureResp.id_extra", i2);
        intent.putExtra("TWADisclosureResp.package_extra", str2);
        o8 o8VarB = o8.b(context, 0, intent, 134217728, false);
        qq qqVarD = n3.D(str, o8Var);
        qqVarD.p(R$drawable.ic_chrome);
        qqVarD.k(string);
        qqVarD.j(string2);
        qqVarD.c(o8VarB);
        qqVarD.b(0, resources.getString(R$string.got_it), o8VarB, 15);
        qq qqVar = qqVarD;
        qqVar.a.j = false;
        qqVar.h(false);
        NotificationCompat$Builder notificationCompat$Builder = qqVar.a;
        Notification notification = notificationCompat$Builder.A;
        notification.sound = null;
        notification.audioStreamType = -1;
        notification.audioAttributes = NotificationCompat$Builder.a.a(NotificationCompat$Builder.a.d(NotificationCompat$Builder.a.c(NotificationCompat$Builder.a.b(), 4), 5));
        lo1 lo1Var = new lo1();
        lo1Var.d = NotificationCompat$Builder.b(string2);
        if (notificationCompat$Builder.k != lo1Var) {
            notificationCompat$Builder.k = lo1Var;
            lo1Var.f(notificationCompat$Builder);
        }
        qqVar.o(!zK);
        notificationCompat$Builder.i = i;
        lp1 lp1VarF = qqVar.f();
        this.h.c(lp1VarF);
        kp1.a.a.b(zK ? 23 : 24, lp1VarF.a);
        ((ag3.a) ag3Var.j(ag3.f)).b();
    }

    @Override // defpackage.kr2
    public final void j() {
        if (this.g.h(ag3.c) == 1) {
            b();
        }
    }

    @Override // defpackage.kr2
    public final void l() {
        String str = this.i;
        so1 so1Var = this.h;
        so1Var.d(13, str);
        so1Var.d(14, this.i);
        this.i = null;
    }
}
