package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.NoTargetDeviceBottomSheetContent;
import org.chromium.chrome.browser.share.send_tab_to_self.TargetDeviceInfo;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SendTabToSelfCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ki2 {
    public final Context a;
    public final WindowAndroid b;
    public final String c;
    public final String d;
    public final ji e;
    public final Profile f;

    /* JADX INFO: compiled from: SendTabToSelfCoordinator.java */
    public static class b extends bh0 {
        public final ji e;

        public b(ji jiVar, String str, f52 f52Var, Profile profile) {
            this.e = jiVar;
            jiVar.g(this);
            Optional optionalW = n3.W(str, profile);
            if (optionalW.isPresent() && ((Integer) optionalW.get()).intValue() != 1) {
                jiVar.e(this);
                f52Var.run();
            }
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void c(int i) {
            this.e.e(this);
        }
    }

    public ki2(Activity activity, WindowAndroid windowAndroid, String str, String str2, ji jiVar, Profile profile) {
        this.a = activity;
        this.b = windowAndroid;
        this.c = str;
        this.d = str2;
        this.e = jiVar;
        this.f = profile;
    }

    public final void a() {
        String str = this.c;
        Profile profile = this.f;
        Optional optionalW = n3.W(str, profile);
        boolean zIsPresent = optionalW.isPresent();
        Context context = this.a;
        ji jiVar = this.e;
        if (!zIsPresent) {
            N.MgBRzKvC(3);
            jiVar.d(new NoTargetDeviceBottomSheetContent(context), true);
            return;
        }
        int iIntValue = ((Integer) optionalW.get()).intValue();
        if (iIntValue == 0) {
            N.MgBRzKvC(2);
            List listAsList = Arrays.asList((TargetDeviceInfo[]) N.MVujpkId(profile));
            ji jiVar2 = this.e;
            jiVar2.d(new l90(this.a, this.c, this.d, jiVar2, listAsList, this.f), true);
            return;
        }
        if (iIntValue != 1) {
            if (iIntValue != 2) {
                return;
            }
            N.MgBRzKvC(3);
            jiVar.d(new NoTargetDeviceBottomSheetContent(context), true);
            return;
        }
        N.MgBRzKvC(4);
        new C0215v0(this.b, jiVar, new a(new eu1(18, this), profile), new C0217w0(1));
    }

    /* JADX INFO: compiled from: SendTabToSelfCoordinator.java */
    public static class a implements c1 {
        public final Runnable a;
        public final Profile b;

        public a(eu1 eu1Var, Profile profile) {
            this.a = eu1Var;
            this.b = profile;
        }

        @Override // defpackage.c1
        public final void a(String str, dr drVar) {
            z7.m().getClass();
            z7.D(this.b).c(org.chromium.components.signin.b.c(str), new ji2(this));
        }

        @Override // defpackage.c1
        public final int b() {
            return 1;
        }

        @Override // defpackage.c1
        public final void destroy() {
        }
    }
}
