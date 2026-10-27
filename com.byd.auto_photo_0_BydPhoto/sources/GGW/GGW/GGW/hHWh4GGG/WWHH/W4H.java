package GGW.GGW.GGW.hHWh4GGG.WWHH;

import android.content.Context;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkStorageManager;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkStorageManager.class, singleton = true)
public class W4H implements IDiLinkStorageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final StorageManager f63a;

    public W4H(Context context) {
        this.f63a = (StorageManager) context.getSystemService("storage");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean format(String str) {
        return this.f63a.format(str);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public List<IDiLinkVolumeInfo> getVolumes() {
        List volumes = this.f63a.getVolumes();
        if (volumes == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = volumes.iterator();
        while (it.hasNext()) {
            arrayList.add(new b((VolumeInfo) it.next()));
        }
        return arrayList;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean partitionPublic(String str) {
        return this.f63a.partitionPublic(str);
    }
}
