package com.amazon.mp3.config;

import android.content.Context;
import android.os.RemoteException;
import com.amazon.appmanager.lib.PreloadManager;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.Log;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class MdipAssociateTagStore {
    private static final String TAG = "MdipAssociateTagStore";
    private final Context mContext;

    MdipAssociateTagStore(final Context context) {
        this.mContext = context;
    }

    public String loadOrDefault() {
        String preloadAssociateTagWithoutRegionId_Minerva;
        if (Feature.preload_associate_tag_without_region_id.isEnabled()) {
            try {
                preloadAssociateTagWithoutRegionId_Minerva = PreloadManager.getDefault().getPreloadAssociateTagWithoutRegionId_Minerva(this.mContext);
            } catch (RemoteException | IOException | RuntimeException e) {
                Log.error(TAG, "Exception while trying to get associate tag from MDIP", e);
                preloadAssociateTagWithoutRegionId_Minerva = null;
            }
            if (preloadAssociateTagWithoutRegionId_Minerva != null) {
                return preloadAssociateTagWithoutRegionId_Minerva + '-' + AssociateTag.getInstance(this.mContext).getAssociateTagRegionInformation();
            }
        }
        return null;
    }
}
