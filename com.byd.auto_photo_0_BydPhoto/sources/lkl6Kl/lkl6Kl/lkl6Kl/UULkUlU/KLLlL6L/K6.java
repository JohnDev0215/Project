package lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU.KLLlL6L;

import android.content.Context;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkStorageManager;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkStorageManager.class, singleton = true)
public class K6 implements IDiLinkStorageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final StorageManager f3117a;

    public K6(Context context) {
        this.f3117a = (StorageManager) context.getSystemService("storage");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean format(String str) {
        return this.f3117a.format(str);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public List<IDiLinkVolumeInfo> getVolumes() {
        List volumes = this.f3117a.getVolumes();
        if (volumes == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = volumes.iterator();
        while (it.hasNext()) {
            arrayList.add(new a((VolumeInfo) it.next()));
        }
        return arrayList;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean partitionPublic(String str) {
        return this.f3117a.partitionPublic(str);
    }
}
