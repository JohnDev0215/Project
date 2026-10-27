package defpackage;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: WebappDataStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ds3 {
    public final String a;
    public final SharedPreferences b;

    /* JADX INFO: compiled from: WebappDataStorage.java */
    public interface a<T> {
    }

    public ds3(String str) {
        this.a = str;
        this.b = xz.a.getSharedPreferences("webapp_" + str, 0);
    }

    public final void a() {
        SharedPreferences sharedPreferences = this.b;
        String string = sharedPreferences.getString("pending_update_file_path", null);
        if (string == null) {
            return;
        }
        sharedPreferences.edit().remove("pending_update_file_path").apply();
        PostTask.c(d73.g, new v6(string, 1));
    }

    public final void b(boolean z) {
        SharedPreferences sharedPreferences = this.b;
        String string = sharedPreferences.getString("webapk_package_name", null);
        if ((string == null || string.startsWith("org.chromium.webapk")) ? false : true) {
            return;
        }
        sharedPreferences.edit().putBoolean("should_force_update", z).apply();
    }

    public final void c(oj ojVar) {
        boolean z;
        if (ojVar == null) {
            return;
        }
        ls3 ls3VarC = ls3.c(ojVar);
        SharedPreferences sharedPreferences = this.b;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        boolean z2 = true;
        if (sharedPreferences.getString("url", "").equals("")) {
            editorEdit.putString("url", ls3VarC.f().b);
            z = true;
        } else {
            z = false;
        }
        if (sharedPreferences.getString("scope", "").equals("")) {
            editorEdit.putString("scope", ls3VarC.q());
            z = true;
        }
        if (sharedPreferences.getInt("version", 0) != 3) {
            editorEdit.putInt("version", 3);
            if (ls3VarC.j()) {
                editorEdit.putString("webapk_package_name", ls3VarC.u());
                editorEdit.putString("webapk_manifest_url", ls3VarC.o());
                editorEdit.putString("webapk_manifest_id", ls3VarC.m());
                editorEdit.putInt("webapk_version_code", ls3VarC.e().n);
                PackageInfo packageInfoC = zt1.c(0, ls3VarC.u());
                editorEdit.putLong("webapk_install_timestamp", packageInfoC == null ? 0L : packageInfoC.firstInstallTime);
            } else {
                editorEdit.putString("name", ls3VarC.p());
                editorEdit.putString("short_name", ls3VarC.r());
                editorEdit.putString("icon", ls3VarC.g().b());
                editorEdit.putInt("display_mode", ls3VarC.d());
                editorEdit.putInt("orientation", ls3VarC.f().h);
                editorEdit.putLong("theme_color", ls3VarC.t());
                editorEdit.putLong("background_color", ls3VarC.b());
                editorEdit.putBoolean("is_icon_generated", ls3VarC.l());
                editorEdit.putBoolean("is_icon_adaptive", ls3VarC.k());
                editorEdit.putInt(BYDAutoMultimediaDevice.PARAM_SOURCE, ls3VarC.f().i);
            }
        } else {
            z2 = z;
        }
        if (z2) {
            editorEdit.apply();
        }
    }
}
