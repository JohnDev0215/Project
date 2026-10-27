package org.chromium.chrome.browser.password_check;

import android.util.Pair;
import defpackage.p81;
import defpackage.v62;
import defpackage.xa1;
import defpackage.yw1;
import org.chromium.chrome.browser.password_check.PasswordCheckCoordinator;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordCheckProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {
    public static final PropertyModel.l<p81<xa1>> a;
    public static final PropertyModel.r<PasswordCheckDialogFragment.a> b;
    public static final PropertyModel.r<String> c;
    public static final PropertyModel.r<CompromisedCredential> d;
    public static final PropertyModel.r<PasswordCheckDialogFragment.a> e;
    public static final v62[] f;

    /* JADX INFO: compiled from: PasswordCheckProperties.java */
    public static class a {
        public static final PropertyModel.l<CompromisedCredential> a;
        public static final PropertyModel.l<PasswordCheckCoordinator.a> b;
        public static final PropertyModel.h c;
        public static final PropertyModel.r<yw1.a> d;
        public static final v62[] e;

        static {
            PropertyModel.l<CompromisedCredential> lVar = new PropertyModel.l<>("compromised_credential");
            a = lVar;
            PropertyModel.l<PasswordCheckCoordinator.a> lVar2 = new PropertyModel.l<>("credential_handler");
            b = lVar2;
            PropertyModel.h hVar = new PropertyModel.h("has_change_button");
            c = hVar;
            PropertyModel.r<yw1.a> rVar = new PropertyModel.r<>("favicon", false);
            d = rVar;
            e = new v62[]{lVar, lVar2, hVar, rVar};
        }
    }

    /* JADX INFO: compiled from: PasswordCheckProperties.java */
    public static class b {
        public static final PropertyModel.r<Pair<Integer, Integer>> a;
        public static final PropertyModel.p b;
        public static final PropertyModel.r<Long> c;
        public static final PropertyModel.r<Integer> d;
        public static final PropertyModel.l<Runnable> e;
        public static final PropertyModel.l<Runnable> f;
        public static final PropertyModel.n g;
        public static final v62[] h;
        public static final Pair<Integer, Integer> i;

        static {
            PropertyModel.r<Pair<Integer, Integer>> rVar = new PropertyModel.r<>("check_progress", false);
            a = rVar;
            PropertyModel.p pVar = new PropertyModel.p("check_status");
            b = pVar;
            PropertyModel.r<Long> rVar2 = new PropertyModel.r<>("check_timestamp", false);
            c = rVar2;
            PropertyModel.r<Integer> rVar3 = new PropertyModel.r<>("compromised_credentials_count", false);
            d = rVar3;
            PropertyModel.l<Runnable> lVar = new PropertyModel.l<>("launch_account_checkup_action");
            e = lVar;
            PropertyModel.l<Runnable> lVar2 = new PropertyModel.l<>("restart_button_action");
            f = lVar2;
            PropertyModel.n nVar = new PropertyModel.n("show_check_subtitle");
            g = nVar;
            h = new v62[]{rVar, pVar, rVar2, rVar3, lVar, lVar2, nVar};
            i = new Pair<>(-1, -1);
        }
    }

    static {
        PropertyModel.l<p81<xa1>> lVar = new PropertyModel.l<>("items");
        a = lVar;
        PropertyModel.r<PasswordCheckDialogFragment.a> rVar = new PropertyModel.r<>("deletion_confirmation_handler", false);
        b = rVar;
        PropertyModel.r<String> rVar2 = new PropertyModel.r<>("deletion_origin", false);
        c = rVar2;
        PropertyModel.r<CompromisedCredential> rVar3 = new PropertyModel.r<>("view_credential", false);
        d = rVar3;
        PropertyModel.r<PasswordCheckDialogFragment.a> rVar4 = new PropertyModel.r<>("view_dialog_handler", false);
        e = rVar4;
        f = new v62[]{lVar, rVar, rVar2, rVar3, rVar4};
    }
}
