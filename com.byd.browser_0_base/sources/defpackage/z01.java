package defpackage;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: InstalledWebappDataRegister.java */
/* JADX INFO: loaded from: classes.dex */
public final class z01 {
    public final SharedPreferences a;

    public z01() {
        ku2 ku2VarT = ku2.t();
        try {
            this.a = xz.a.getSharedPreferences("trusted_web_activity_client_apps", 0);
            ku2VarT.close();
            PostTask.c(d73.f, new wv(20, this));
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static String a(int i) {
        return i + ".appName";
    }

    public static String b(int i) {
        return i + ".domain";
    }

    public static String c(int i) {
        return i + ".origin";
    }

    public static String d(int i) {
        return i + ".packageName";
    }

    public final HashSet e() {
        ku2 ku2VarT = ku2.t();
        try {
            HashSet hashSet = new HashSet(this.a.getStringSet("trusted_web_activity_uids", Collections.emptySet()));
            ku2VarT.close();
            return hashSet;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final void f(int i, String str, String str2, String str3, ft1 ft1Var) {
        HashSet hashSetE = e();
        hashSetE.add(String.valueOf(i));
        SharedPreferences sharedPreferences = this.a;
        sharedPreferences.edit().putStringSet("trusted_web_activity_uids", hashSetE).apply();
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString(a(i), str);
        editorEdit.putString(d(i), str2);
        String strB = b(i);
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet(strB, Collections.emptySet()));
        hashSet.add(str3);
        editorEdit.putStringSet(strB, hashSet);
        String strC = c(i);
        String string = ft1Var.toString();
        HashSet hashSet2 = new HashSet(sharedPreferences.getStringSet(strC, Collections.emptySet()));
        hashSet2.add(string);
        editorEdit.putStringSet(strC, hashSet2);
        editorEdit.apply();
    }
}
