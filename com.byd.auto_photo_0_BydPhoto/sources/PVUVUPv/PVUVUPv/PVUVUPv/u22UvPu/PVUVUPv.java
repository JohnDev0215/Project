package PVUVUPv.PVUVUPv.PVUVUPv.u22UvPu;

import android.acquisitionservice.BYDAcquisitionClient;
import android.content.Context;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.MemorySafeContext;
import com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient;
import com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClientManager;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink_rse.acquisitionservice.DiLinkAcquisitionClient;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_RUI, DiLink.VERSION_NAME_RSE_5_0})
@ServiceImpl(service = IDiLinkAcquisitionClientManager.class, singleton = true)
public class PVUVUPv implements IDiLinkAcquisitionClientManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<BYDAcquisitionClient, DiLinkAcquisitionClient> f83a = new ConcurrentHashMap<>();

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClientManager
    public IDiLinkAcquisitionClient create(Context context, String str) {
        BYDAcquisitionClient bYDAcquisitionClient = BYDAcquisitionClient.getInstance(MemorySafeContext.get(context), str);
        if (bYDAcquisitionClient != null) {
            return this.f83a.computeIfAbsent(bYDAcquisitionClient, a.f84a);
        }
        Log.w("DiLinkAcquisitionClient", "create failed: " + str);
        return null;
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClientManager
    public IDiLinkAcquisitionClient create(Context context, String str, String str2) {
        BYDAcquisitionClient bYDAcquisitionClient = BYDAcquisitionClient.getInstance(MemorySafeContext.get(context), str, str2);
        if (bYDAcquisitionClient != null) {
            return this.f83a.computeIfAbsent(bYDAcquisitionClient, a.f84a);
        }
        Log.w("DiLinkAcquisitionClient", "create failed: " + str + " " + str2);
        return null;
    }
}
