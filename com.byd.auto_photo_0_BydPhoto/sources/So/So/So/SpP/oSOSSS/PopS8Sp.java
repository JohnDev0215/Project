package So.So.So.SpP.oSOSSS;

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

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkStorageManager.class, singleton = true)
public class PopS8Sp implements IDiLinkStorageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final StorageManager f151a;

    public PopS8Sp(Context context) {
        this.f151a = (StorageManager) context.getSystemService("storage");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean format(String str) {
        this.f151a.format(str);
        return true;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public List<IDiLinkVolumeInfo> getVolumes() {
        List volumes = this.f151a.getVolumes();
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
        this.f151a.partitionPublic(str);
        return true;
    }
}
