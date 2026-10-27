package com.android.internal.os;

import android.os.Process;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ProcTimeInStateReader {
    private static final String TAG = "ProcTimeInStateReader";
    private long[] mFrequenciesKhz;
    private int[] mTimeInStateTimeFormat;
    private static final List<Integer> TIME_IN_STATE_LINE_FREQUENCY_FORMAT = Arrays.asList(8224, 10);
    private static final List<Integer> TIME_IN_STATE_LINE_TIME_FORMAT = Arrays.asList(32, 8202);
    private static final List<Integer> TIME_IN_STATE_HEADER_LINE_FORMAT = Collections.singletonList(10);

    public ProcTimeInStateReader(Path path) throws IOException {
        initializeTimeInStateFormat(path);
    }

    public long[] getUsageTimesMillis(Path path) {
        int length = this.mFrequenciesKhz.length;
        long[] jArr = new long[length];
        if (!Process.readProcFile(path.toString(), this.mTimeInStateTimeFormat, null, jArr, null)) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            jArr[i] = jArr[i] * 10;
        }
        return jArr;
    }

    public long[] getFrequenciesKhz() {
        return this.mFrequenciesKhz;
    }

    private void initializeTimeInStateFormat(Path path) throws IOException {
        byte[] allBytes = Files.readAllBytes(path);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < allBytes.length) {
            if (!Character.isDigit(allBytes[i])) {
                arrayList.addAll(TIME_IN_STATE_HEADER_LINE_FORMAT);
                arrayList2.addAll(TIME_IN_STATE_HEADER_LINE_FORMAT);
            } else {
                arrayList.addAll(TIME_IN_STATE_LINE_FREQUENCY_FORMAT);
                arrayList2.addAll(TIME_IN_STATE_LINE_TIME_FORMAT);
                i2++;
            }
            while (i < allBytes.length && allBytes[i] != 10) {
                i++;
            }
            i++;
        }
        if (i2 == 0) {
            throw new IOException("Empty time_in_state file");
        }
        long[] jArr = new long[i2];
        if (!Process.parseProcLine(allBytes, 0, allBytes.length, ArrayUtils.convertToIntArray(arrayList), null, jArr, null)) {
            throw new IOException("Failed to parse time_in_state file");
        }
        this.mTimeInStateTimeFormat = ArrayUtils.convertToIntArray(arrayList2);
        this.mFrequenciesKhz = jArr;
    }
}
