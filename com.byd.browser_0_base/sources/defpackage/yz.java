package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.ui.permissions.AndroidPermissionDelegate;

/* JADX INFO: compiled from: ContextualNotificationPermissionRequesterImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class yz extends vd1 {

    /* JADX INFO: compiled from: ContextualNotificationPermissionRequesterImpl.java */
    public static class a {
        public static final yz a = new yz();
    }

    public static to1 J() {
        Activity activity = ApplicationStatus.c;
        if (!(activity instanceof ChromeTabbedActivity)) {
            return null;
        }
        g3 g3Var = ((ChromeTabbedActivity) activity).O;
        if (g3Var != null) {
            return (to1) to1.g.e(g3Var.r);
        }
        aj3<to1> aj3Var = to1.g;
        return null;
    }

    @Override // defpackage.vd1
    public final void E() {
        to1 to1VarJ;
        sk skVar = bq.a;
        if (N.M6bsIDpc("NotificationPermissionVariant", "enable_contextual_permission_requests", false) && (to1VarJ = J()) != null) {
            to1VarJ.a();
        }
    }

    @Override // defpackage.vd1
    public final boolean e() {
        to1 to1VarJ = J();
        if (to1VarJ == null) {
            return true;
        }
        boolean zA = new ro1(xz.a).a();
        if (!yj.a()) {
            return zA;
        }
        AndroidPermissionDelegate androidPermissionDelegate = to1VarJ.e;
        if (androidPermissionDelegate.hasPermission("android.permission.POST_NOTIFICATIONS")) {
            return true;
        }
        sk skVar = bq.a;
        return N.M6bsIDpc("NotificationPermissionVariant", "permission_request_allow_site_notification_requests", true) && androidPermissionDelegate.canRequestPermission("android.permission.POST_NOTIFICATIONS");
    }
}
