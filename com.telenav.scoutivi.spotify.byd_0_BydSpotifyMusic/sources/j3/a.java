package j3;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import i3.s;
import i3.y;

/* JADX INFO: compiled from: MainModule.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    s a(Application application, y yVar) {
        try {
            ApplicationInfo applicationInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(), 128);
            return new s(application, applicationInfo.metaData.getString("com.vivid.music.packagename"), applicationInfo.metaData.getString("com.vivid.music.classname"), yVar.a());
        } catch (Exception e7) {
            throw new RuntimeException(e7);
        }
    }
}
