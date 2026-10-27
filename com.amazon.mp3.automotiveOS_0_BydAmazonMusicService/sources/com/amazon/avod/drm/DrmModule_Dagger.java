package com.amazon.avod.drm;

import com.amazon.avod.drm.db.AndroidDrmContentRegistry;
import com.amazon.avod.drm.db.DrmContentRegistry;
import com.amazon.avod.drm.playready.DrmSystemImpl;
import com.amazon.avod.media.framework.MediaComponent;

/* JADX INFO: loaded from: classes2.dex */
public class DrmModule_Dagger {
    BaseDrmSystem provideBaseDrmSystem(DrmSystemImpl drmSystemImpl) {
        return drmSystemImpl;
    }

    DrmContentRegistry provideDrmContentRegistry(AndroidDrmContentRegistry androidDrmContentRegistry) {
        return androidDrmContentRegistry;
    }

    MediaComponent provideLicenseCleaner(DrmLicenseCleaner drmLicenseCleaner) {
        return drmLicenseCleaner;
    }
}
