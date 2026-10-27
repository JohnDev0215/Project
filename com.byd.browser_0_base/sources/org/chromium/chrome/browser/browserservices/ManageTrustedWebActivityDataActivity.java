package org.chromium.chrome.browser.browserservices;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.fg3;
import defpackage.fq3;
import defpackage.gg3;
import defpackage.n3;
import defpackage.pp;
import defpackage.s50;
import defpackage.sp;
import defpackage.z01;
import defpackage.zs;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ManageTrustedWebActivityDataActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String strD;
        super.onCreate(bundle);
        String string = getIntent().getData().toString();
        boolean booleanExtra = getIntent().getBooleanExtra("org.chromium.webapk.is_webapk", false);
        Integer numValueOf = null;
        if (booleanExtra) {
            strD = getCallingPackage();
        } else {
            CustomTabsSessionToken customTabsSessionTokenB = CustomTabsSessionToken.b(getIntent());
            if (customTabsSessionTokenB == null) {
                strD = null;
            } else {
                ((s50) pp.d()).getClass();
                strD = n3.W0().d(customTabsSessionTokenB);
            }
        }
        if (strD == null) {
            Log.e("cr_TwaDataActivity", "Package name for incoming intent couldn't be resolved. Was a CustomTabSession created and added to the intent?");
            finish();
        } else {
            sp spVarA = sp.a();
            Objects.requireNonNull(spVarA);
            spVarA.f(new gg3(0));
            if (booleanExtra) {
                zs.c();
                if (fq3.a(this, strD, string)) {
                    try {
                        numValueOf = Integer.valueOf(getPackageManager().getApplicationInfo(strD, 0).uid);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                    if (numValueOf != null) {
                        fg3.b(this, string);
                    }
                }
            } else {
                try {
                    numValueOf = Integer.valueOf(getPackageManager().getApplicationInfo(strD, 0).uid);
                } catch (PackageManager.NameNotFoundException unused2) {
                }
                if (numValueOf != null) {
                    z01 z01Var = new z01();
                    String strB = z01.b(numValueOf.intValue());
                    Set<String> setEmptySet = Collections.emptySet();
                    SharedPreferences sharedPreferences = z01Var.a;
                    Set<String> stringSet = sharedPreferences.getStringSet(strB, setEmptySet);
                    Set<String> stringSet2 = sharedPreferences.getStringSet(z01.c(numValueOf.intValue()), Collections.emptySet());
                    if (!stringSet.isEmpty() && !stringSet2.isEmpty()) {
                        fg3.a(this, stringSet2, stringSet);
                    }
                }
            }
        }
        finish();
    }
}
