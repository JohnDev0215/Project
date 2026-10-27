package GGW.GGW.GGW.WWHH.GGW;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.content.om.OverlayManager;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.content.om.IDiLinkOverlayInfo;
import com.byd.dilink.content.om.IDiLinkOverlayManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkOverlayManager.class, singleton = true)
public class Gg4HHGg44 implements IDiLinkOverlayManager {
    private List<IDiLinkOverlayInfo> a(List<OverlayInfo> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<OverlayInfo> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new GGW(it.next()));
        }
        return arrayList;
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayManager
    public Map<String, List<IDiLinkOverlayInfo>> getAllOverlays(int i) {
        Map allOverlays = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")).getAllOverlays(i);
        if (allOverlays == null) {
            Log.d("DiLinkOverlayManager", "allOverlays为空");
            return null;
        }
        HashMap map = new HashMap();
        for (Map.Entry entry : allOverlays.entrySet()) {
            map.put((String) entry.getKey(), a((List) entry.getValue()));
        }
        return map;
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayManager
    public IDiLinkOverlayInfo getOverlayInfo(Context context, String str, UserHandle userHandle) {
        OverlayInfo overlayInfo = ((OverlayManager) context.getSystemService(OverlayManager.class)).getOverlayInfo(str, userHandle);
        if (overlayInfo == null) {
            return null;
        }
        return new GGW(overlayInfo);
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayManager
    public List<IDiLinkOverlayInfo> getOverlayInfosForTarget(Context context, String str, UserHandle userHandle) {
        return a(((OverlayManager) context.getSystemService(OverlayManager.class)).getOverlayInfosForTarget(str, userHandle));
    }

    @Override // com.byd.dilink.content.om.IDiLinkOverlayManager
    public void setEnabled(Context context, String str, boolean z, UserHandle userHandle) {
        ((OverlayManager) context.getSystemService(OverlayManager.class)).setEnabled(str, z, userHandle);
    }
}
