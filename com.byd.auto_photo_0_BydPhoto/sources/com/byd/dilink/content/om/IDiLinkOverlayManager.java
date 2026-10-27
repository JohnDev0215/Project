package com.byd.dilink.content.om;

import android.content.Context;
import android.os.UserHandle;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkOverlayManager {
    Map<String, List<IDiLinkOverlayInfo>> getAllOverlays(int i);

    IDiLinkOverlayInfo getOverlayInfo(Context context, String str, UserHandle userHandle);

    List<IDiLinkOverlayInfo> getOverlayInfosForTarget(Context context, String str, UserHandle userHandle);

    void setEnabled(Context context, String str, boolean z, UserHandle userHandle);
}
