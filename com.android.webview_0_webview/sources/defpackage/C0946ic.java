package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

/* JADX INFO: renamed from: ic, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0946ic {
    public static final Lock c = new ReentrantLock();
    public static C0946ic d;
    public final Lock a = new ReentrantLock();
    public final SharedPreferences b;

    public C0946ic(Context context) {
        this.b = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static C0946ic a(Context context) {
        Cif.f(context);
        c.lock();
        try {
            if (d == null) {
                d = new C0946ic(context.getApplicationContext());
            }
            return d;
        } finally {
            c.unlock();
        }
    }

    public GoogleSignInAccount b() {
        String strC = c("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(strC)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(strC).length() + 20);
        sb.append("googleSignInAccount");
        sb.append(":");
        sb.append(strC);
        String strC2 = c(sb.toString());
        if (strC2 == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.e(strC2);
        } catch (JSONException unused) {
            return null;
        }
    }

    public final String c(String str) {
        this.a.lock();
        try {
            return this.b.getString(str, null);
        } finally {
            this.a.unlock();
        }
    }
}
