package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;

/* JADX INFO: renamed from: Xe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0417Xe {
    public static final Uri f = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    public final String a;
    public final String b;
    public final ComponentName c;
    public final int d;
    public final boolean e;

    public C0417Xe(String str, String str2, int i, boolean z) {
        Cif.e(str);
        this.a = str;
        Cif.e(str2);
        this.b = str2;
        this.c = null;
        this.d = i;
        this.e = z;
    }

    public final Intent a(Context context) {
        Bundle bundleCall;
        if (this.a == null) {
            return new Intent().setComponent(null);
        }
        if (this.e) {
            Bundle bundle = new Bundle();
            bundle.putString("serviceActionBundleKey", this.a);
            try {
                bundleCall = context.getContentResolver().call(f, "serviceIntentCall", (String) null, bundle);
            } catch (IllegalArgumentException e) {
                String strValueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(strValueOf.length() + 34);
                sb.append("Dynamic intent resolution failed: ");
                sb.append(strValueOf);
                Log.w("ConnectionStatusConfig", sb.toString());
                bundleCall = null;
            }
            intent = bundleCall != null ? (Intent) bundleCall.getParcelable("serviceResponseIntentKey") : null;
            if (intent == null) {
                String strValueOf2 = String.valueOf(this.a);
                Log.w("ConnectionStatusConfig", strValueOf2.length() != 0 ? "Dynamic lookup for intent failed for action: ".concat(strValueOf2) : new String("Dynamic lookup for intent failed for action: "));
            }
        }
        return intent == null ? new Intent(this.a).setPackage(this.b) : intent;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0417Xe)) {
            return false;
        }
        C0417Xe c0417Xe = (C0417Xe) obj;
        return AbstractC0893hf.a(this.a, c0417Xe.a) && AbstractC0893hf.a(this.b, c0417Xe.b) && AbstractC0893hf.a(null, null) && this.d == c0417Xe.d && this.e == c0417Xe.e;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.a, this.b, null, Integer.valueOf(this.d), Boolean.valueOf(this.e)});
    }

    public final String toString() {
        String str = this.a;
        if (str != null) {
            return str;
        }
        throw null;
    }
}
