package jViVV55i.jViVV55i.jViVV55i.jjjJj5.IIJjVVVV;

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
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkStorageManager.class, singleton = true)
public class ViiVJJIi implements IDiLinkStorageManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final StorageManager f3070a;

    public ViiVJJIi(Context context) {
        this.f3070a = (StorageManager) context.getSystemService("storage");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public boolean format(String str) {
        return this.f3070a.format(str);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkStorageManager
    public List<IDiLinkVolumeInfo> getVolumes() {
        List volumes = this.f3070a.getVolumes();
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
        return this.f3070a.partitionPublic(str);
    }
}
