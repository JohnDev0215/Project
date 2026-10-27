package defpackage;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.File;

/* JADX INFO: compiled from: ZipHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class dg {
    public static void a(String str) {
        for (File file : new File(str).listFiles()) {
            if (file.exists()) {
                File file2 = new File(file.getPath());
                BydLogHelper.logD("PlayService", "deleteAudiomdlog ：" + file.getPath());
                if (file2.delete()) {
                    BydLogHelper.logD("PlayService", "deleteAudiomdlog success");
                }
            } else {
                BydLogHelper.logD("PlayService", "audio mdlog file not exist");
            }
        }
    }

    public static void b(File file, File file2, String str) throws Exception {
    }
}
