package com.amazon.avod.media.events.dao;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.db.ADatabaseInstance;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventsDatabase extends ADatabaseInstance {
    public static final MediaEventTable MEDIA_EVENT_TABLE = new MediaEventTable();
    public static final MediaReportTable MEDIA_REPORT_TABLE = new MediaReportTable();
    private boolean mAreConfigsCached;
    private String mMediaEventsDatabaseFilePath;
    private int mMinDiskSpaceMegabytesRequiredForAloysius;

    private static final class SingletonHolder {
        static final MediaEventsDatabase INSTANCE = new MediaEventsDatabase();

        private SingletonHolder() {
        }
    }

    public static MediaEventsDatabase getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private void cacheConfigs() {
        if (this.mAreConfigsCached) {
            return;
        }
        this.mMinDiskSpaceMegabytesRequiredForAloysius = AloysiusConfig.getInstance().getMinDiskSpaceMegabytesRequiredForAloysius();
        this.mMediaEventsDatabaseFilePath = getFilePath(StorageHelper.getInstance());
        this.mAreConfigsCached = true;
    }

    public boolean hasSufficientDiskSpace() {
        int availableSpaceOnDiskMegabytes = getAvailableSpaceOnDiskMegabytes();
        DLog.devf("MediaEventsDatabase availableMBForAloysius %d", Integer.valueOf(availableSpaceOnDiskMegabytes));
        return availableSpaceOnDiskMegabytes >= this.mMinDiskSpaceMegabytesRequiredForAloysius;
    }

    public int getAvailableSpaceOnDiskMegabytes() {
        cacheConfigs();
        try {
            return (int) DataUnit.BYTES.toMegaBytes(DiskUtils.getDiskSpace(this.mMediaEventsDatabaseFilePath));
        } catch (Exception e) {
            DLog.exceptionf(e, "Exception trying to determine available space on disk", new Object[0]);
            return this.mMinDiskSpaceMegabytesRequiredForAloysius;
        }
    }

    private MediaEventsDatabase() {
        super("mediaEvents", 2, ADatabaseInstance.Scope.USER_PRIVATE);
        this.mAreConfigsCached = false;
        addTable(MEDIA_EVENT_TABLE);
        addTable(MEDIA_REPORT_TABLE);
    }
}
