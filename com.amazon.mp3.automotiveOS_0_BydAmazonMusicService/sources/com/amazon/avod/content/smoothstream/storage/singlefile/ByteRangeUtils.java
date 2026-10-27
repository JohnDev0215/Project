package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ByteRangeUtils {
    @Nonnull
    public static List<ByteRange> getUnDownloadedByteRanges(@Nonnull List<ByteRange> list, long j, long j2) {
        Preconditions.checkNotNull(list, "sortedDownloadedByteRanges");
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        if (list.isEmpty()) {
            splitRange(0L, j - 1, j2, arrayListNewArrayList);
            return arrayListNewArrayList;
        }
        long end = 0;
        for (ByteRange byteRange : list) {
            if (end < byteRange.getStart()) {
                splitRange(end, byteRange.getStart() - 1, j2, arrayListNewArrayList);
            }
            end = byteRange.getEnd() + 1;
        }
        ByteRange byteRange2 = list.get(list.size() - 1);
        long j3 = j - 1;
        if (byteRange2.getEnd() < j3) {
            splitRange(byteRange2.getEnd() + 1, j3, j2, arrayListNewArrayList);
        }
        return arrayListNewArrayList;
    }

    private static void splitRange(long j, long j2, long j3, List<ByteRange> list) {
        while (j2 >= j) {
            if (j2 - j > j3) {
                long j4 = j + j3;
                list.add(new ByteRange(j, j4 - 1));
                j = j4;
            } else {
                list.add(new ByteRange(j, j2));
                j = j2 + 1;
            }
        }
    }
}
