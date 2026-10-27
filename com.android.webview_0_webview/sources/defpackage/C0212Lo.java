package defpackage;

import J.N;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: Lo, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0212Lo {
    public final SharedPreferences a;

    public C0212Lo(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    public static void f(SharedPreferences sharedPreferences, SharedPreferences sharedPreferences2) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        SharedPreferences.Editor editorEdit2 = sharedPreferences2.edit();
        for (String str : sharedPreferences.getAll().keySet()) {
            if (str.startsWith("AwGeolocationPermissions%")) {
                editorEdit2.putBoolean(str, sharedPreferences.getBoolean(str, false)).apply();
                editorEdit.remove(str).apply();
            }
        }
    }

    public void a(String str) {
        String strD = d(str);
        if (strD != null) {
            this.a.edit().putBoolean(strD, true).apply();
        }
    }

    public void b() {
        SharedPreferences.Editor editorEdit = null;
        for (String str : this.a.getAll().keySet()) {
            if (str.startsWith("AwGeolocationPermissions%")) {
                if (editorEdit == null) {
                    editorEdit = this.a.edit();
                }
                editorEdit.remove(str);
            }
        }
        if (editorEdit != null) {
            editorEdit.apply();
        }
    }

    public void c(String str, final Callback callback) {
        final boolean z = this.a.getBoolean(d(str), false);
        PostTask.a(AbstractC0538bE.a, new Runnable(callback, z) { // from class: Io
            public final Callback s;
            public final boolean t;

            {
                this.s = callback;
                this.t = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a(Boolean.valueOf(this.t));
            }
        }, 0L);
    }

    public final String d(String str) {
        String strMpCt7siL = N.MpCt7siL(str);
        if (strMpCt7siL.isEmpty()) {
            return null;
        }
        return Z7.c("AwGeolocationPermissions%", strMpCt7siL);
    }

    public void e(final Callback callback) {
        final HashSet hashSet = new HashSet();
        for (String str : this.a.getAll().keySet()) {
            if (str.startsWith("AwGeolocationPermissions%")) {
                hashSet.add(str.substring(25));
            }
        }
        PostTask.a(AbstractC0538bE.a, new Runnable(callback, hashSet) { // from class: Jo
            public final Callback s;
            public final Set t;

            {
                this.s = callback;
                this.t = hashSet;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a(this.t);
            }
        }, 0L);
    }
}
