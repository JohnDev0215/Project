package com.amazon.music.uicasting;

import androidx.mediarouter.media.MediaRouter;
import com.amazon.musicplayqueueservice.client.common.CastingDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class UiConverter {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) UiConverter.class);

    public static UiDeviceListResult resultToUiResult(List list) {
        if (list == null || list.isEmpty()) {
            LOG.warn("Received null result");
            return new UiDeviceListResult(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        Object obj = list.get(0);
        if (obj instanceof CastingDevice) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new CastingTileConverter().convert((CastingDevice) it.next()));
            }
        } else if (obj instanceof MediaRouter.RouteInfo) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayList.add(new CastingTileConverter().convert((MediaRouter.RouteInfo) it2.next()));
            }
        }
        arrayList.removeAll(Collections.singleton(null));
        return new UiDeviceListResult(arrayList);
    }
}
