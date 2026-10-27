package org.chromium.chrome.browser.app.creator;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import defpackage.aq1;
import defpackage.c3;
import defpackage.fk2;
import defpackage.fu0;
import defpackage.g3;
import defpackage.gk2;
import defpackage.h10;
import defpackage.hu0;
import defpackage.i10;
import defpackage.ji;
import defpackage.n10;
import defpackage.r2;
import defpackage.z2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.SnackbarActivity;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.ui.base.c;
import org.chromium.ui.base.d;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class CreatorActivity extends SnackbarActivity {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public g3 f59J;
    public f K;
    public z2 L;
    public r2 M;
    public gk2 N;
    public gk2 O;
    public aq1<Profile> P;

    public static class a extends fk2 {
        public a(ji jiVar, r2 r2Var, z2 z2Var, aq1 aq1Var, aq1 aq1Var2, fk2.a aVar) {
            super(jiVar, r2Var, z2Var, aq1Var, aq1Var2, aVar, false);
        }

        @Override // defpackage.fk2, defpackage.ck2
        public final boolean a() {
            return false;
        }
    }

    @Override // org.chromium.chrome.browser.SnackbarActivity, org.chromium.chrome.browser.SynchronousInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("CREATOR_WEB_FEED_ID");
        String stringExtra = getIntent().getStringExtra("CREATOR_URL");
        boolean booleanExtra = getIntent().getBooleanExtra("CREATOR_FOLLOWING", false);
        int intExtra = getIntent().getIntExtra("CREATOR_ENTRY_POINT", 3);
        this.L = new z2();
        this.M = new r2(this);
        this.N = new gk2();
        this.O = new gk2();
        this.P = new aq1<>();
        this.P.r(Profile.d());
        super.onCreate(bundle);
        this.f59J = new g3((Context) this, false, (c) new d(new org.chromium.ui.base.a(this)));
        this.O.r(new a(this.K, this.M, this.L, new aq1(), this.P, new fk2.a()));
        CreatorCoordinator creatorCoordinator = new CreatorCoordinator(this, byteArrayExtra, this.I, this.f59J, stringExtra, intExtra, booleanExtra);
        f fVar = creatorCoordinator.m;
        this.K = fVar;
        this.N.r(new fk2(fVar, this.M, this.L, new aq1(), this.P, new fk2.a(), false));
        h10 h10Var = new h10();
        fu0 fu0VarA = hu0.a();
        gk2 gk2Var = this.N;
        PropertyModel propertyModel = creatorCoordinator.j;
        PropertyModel.r<byte[]> rVar = n10.a;
        if (propertyModel.j(rVar) == null) {
            N.M98OGcFd((String) propertyModel.j(n10.c), new i10(creatorCoordinator, h10Var, fu0VarA, gk2Var, 0));
        } else if (TextUtils.isEmpty((CharSequence) propertyModel.j(n10.b)) || TextUtils.isEmpty((CharSequence) propertyModel.j(n10.c))) {
            N.MBln86eW(new String((byte[]) propertyModel.j(rVar)), new c3(13, creatorCoordinator));
            creatorCoordinator.b();
        }
        setContentView(creatorCoordinator.e);
        C1((Toolbar) findViewById(R$id.action_bar));
        B1().t(true);
        B1().w();
        B1().u(R$string.back);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        this.O.destroy();
        this.N.destroy();
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
