package com.loostone.libbottom.util.permission;

import OooO00o.OooO00o.OooO0O0.OooO0o0.e.a;
import OooO00o.OooO00o.OooO0O0.b;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.loostone.libtuning.R;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionActivity extends Activity {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public static final OooO00o f3800OooO00o = new OooO00o(null);
    public static a OooO0O0;
    public String[] OooO0OO;

    public final boolean OooO0O0() {
        String[] strArr = this.OooO0OO;
        h.b(strArr);
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            i++;
            if (androidx.core.content.a.a(this, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b.f41a.b("PermissionActivity", " onCreate");
        try {
            if (!getIntent().hasExtra("permissions")) {
                finish();
            } else {
                this.OooO0OO = getIntent().getStringArrayExtra("permissions");
                OooO00o();
            }
        } catch (Exception e) {
            b.f41a.d("PermissionActivity", "PermissionActivity onCreate error");
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z;
        h.d(strArr, "permissions");
        h.d(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 100) {
            int length = iArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = true;
                    break;
                }
                int i3 = iArr[i2];
                i2++;
                if (i3 != 0) {
                    z = false;
                    break;
                }
            }
            if (z) {
                a aVar = OooO0O0;
                if (aVar != null) {
                    h.b(aVar);
                    aVar.a();
                }
                b.f41a.b("PermissionActivity", "request permission is all granted");
            } else {
                a aVar2 = OooO0O0;
                if (aVar2 != null) {
                    h.b(aVar2);
                    aVar2.b(null);
                }
                Toast.makeText(this, R.string.permission_request_failed, 0).show();
            }
            finish();
        }
    }

    public final void OooO00o() {
        if (!OooO0O0()) {
            String[] strArr = this.OooO0OO;
            h.b(strArr);
            androidx.core.app.a.l(this, strArr, 100);
        } else {
            b.f41a.b("PermissionActivity", " permission is all granted");
            a aVar = OooO0O0;
            if (aVar != null) {
                h.b(aVar);
                aVar.a();
            }
            finish();
        }
    }

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }

        public final void OooO00o(String[] strArr, a aVar) {
            h.d(strArr, "permissions");
            PermissionActivity.OooO0O0 = aVar;
            if (OooO00o(strArr)) {
                if (aVar == null) {
                    return;
                }
                aVar.a();
            } else {
                OooO00o.OooO00o.OooO0O0.a aVar2 = OooO00o.OooO00o.OooO0O0.a.f39a;
                Intent intent = new Intent(aVar2.a(), (Class<?>) PermissionActivity.class);
                intent.putExtra("permissions", strArr);
                intent.addFlags(268435456);
                aVar2.a().startActivity(intent);
                b.f41a.b("PermissionActivity", " startActivity:request");
            }
        }

        public final boolean OooO00o(String[] strArr) {
            h.d(strArr, "permissions");
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                i++;
                if (androidx.core.content.a.a(OooO00o.OooO00o.OooO0O0.a.f39a.a(), str) != 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
