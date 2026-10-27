package com.amazon.avod.content.smoothstream.manifest.validation;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImagePeriodView {
    private final TreeMap<Long, String> mPeriodStartTimeToPeriodIdMap = Maps.newTreeMap();

    public void putPeriod(long j, @Nonnull String str) {
        Preconditions.checkNotNull(str, "periodId");
        this.mPeriodStartTimeToPeriodIdMap.put(Long.valueOf(j), str);
    }

    @Nullable
    public String lookupPeriodIdWithTimestamp(long j) {
        Map.Entry<Long, String> entryFloorEntry = this.mPeriodStartTimeToPeriodIdMap.floorEntry(Long.valueOf(j));
        if (entryFloorEntry == null) {
            return null;
        }
        return entryFloorEntry.getValue();
    }
}
