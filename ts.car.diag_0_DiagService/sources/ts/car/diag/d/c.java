package ts.car.diag.d;

import android.content.Context;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class c {
    public static List<File> a(Context context) {
        List volumes = ((StorageManager) context.getSystemService(StorageManager.class)).getVolumes();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < volumes.size(); i++) {
            VolumeInfo volumeInfo = (VolumeInfo) volumes.get(i);
            q.h("StorageUtil", "getUpanPath: disk " + volumeInfo.toString());
            DiskInfo diskInfo = volumeInfo.disk;
            if (diskInfo != null) {
                boolean zIsUsb = diskInfo.isUsb();
                File path = volumeInfo.getPath();
                q.h("StorageUtil", "volumeInfo: getPath " + path);
                if (path != null) {
                    q.h("StorageUtil", "getUpanPath: isUsb " + zIsUsb + " path " + path.getPath());
                    if (zIsUsb) {
                        arrayList.add(path);
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }
}
