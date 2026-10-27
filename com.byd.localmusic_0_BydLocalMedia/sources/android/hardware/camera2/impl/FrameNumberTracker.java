package android.hardware.camera2.impl;

import android.hardware.camera2.CaptureResult;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes2.dex */
public class FrameNumberTracker {
    private static final String TAG = "FrameNumberTracker";
    private long[] mCompletedFrameNumber = new long[3];
    private final LinkedList<Long>[] mSkippedOtherFrameNumbers = new LinkedList[3];
    private final LinkedList<Long>[] mSkippedFrameNumbers = new LinkedList[3];
    private final TreeMap<Long, Integer> mFutureErrorMap = new TreeMap<>();
    private final HashMap<Long, List<CaptureResult>> mPartialResults = new HashMap<>();

    public FrameNumberTracker() {
        for (int i = 0; i < 3; i++) {
            this.mCompletedFrameNumber[i] = -1;
            this.mSkippedOtherFrameNumbers[i] = new LinkedList<>();
            this.mSkippedFrameNumbers[i] = new LinkedList<>();
        }
    }

    private void update() {
        Iterator<Map.Entry<Long, Integer>> it = this.mFutureErrorMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Integer> next = it.next();
            Long key = next.getKey();
            int iIntValue = next.getValue().intValue();
            Boolean bool = false;
            long jLongValue = key.longValue();
            long[] jArr = this.mCompletedFrameNumber;
            if (jLongValue == jArr[iIntValue] + 1) {
                jArr[iIntValue] = key.longValue();
                bool = true;
            } else if (this.mSkippedFrameNumbers[iIntValue].isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= 3) {
                        break;
                    }
                    int i2 = (iIntValue + i) % 3;
                    if (!this.mSkippedOtherFrameNumbers[i2].isEmpty() && key == this.mSkippedOtherFrameNumbers[i2].element()) {
                        this.mCompletedFrameNumber[iIntValue] = key.longValue();
                        this.mSkippedOtherFrameNumbers[i2].remove();
                        bool = true;
                        break;
                    }
                    i++;
                }
            } else if (key == this.mSkippedFrameNumbers[iIntValue].element()) {
                this.mCompletedFrameNumber[iIntValue] = key.longValue();
                this.mSkippedFrameNumbers[iIntValue].remove();
                bool = true;
            }
            if (bool.booleanValue()) {
                it.remove();
            }
        }
    }

    public void updateTracker(long j, boolean z, int i) {
        if (z) {
            this.mFutureErrorMap.put(Long.valueOf(j), Integer.valueOf(i));
        } else {
            try {
                updateCompletedFrameNumber(j, i);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        update();
    }

    public void updateTracker(long j, CaptureResult captureResult, boolean z, int i) {
        if (!z) {
            updateTracker(j, false, i);
            return;
        }
        if (captureResult == null) {
            return;
        }
        List<CaptureResult> arrayList = this.mPartialResults.get(Long.valueOf(j));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mPartialResults.put(Long.valueOf(j), arrayList);
        }
        arrayList.add(captureResult);
    }

    public List<CaptureResult> popPartialResults(long j) {
        return this.mPartialResults.remove(Long.valueOf(j));
    }

    public long getCompletedFrameNumber() {
        return this.mCompletedFrameNumber[0];
    }

    public long getCompletedReprocessFrameNumber() {
        return this.mCompletedFrameNumber[1];
    }

    public long getCompletedZslStillFrameNumber() {
        return this.mCompletedFrameNumber[2];
    }

    private void updateCompletedFrameNumber(long j, int i) throws IllegalArgumentException {
        LinkedList<Long> linkedList;
        LinkedList<Long> linkedList2;
        long[] jArr = this.mCompletedFrameNumber;
        if (j <= jArr[i]) {
            throw new IllegalArgumentException("frame number " + j + " is a repeat");
        }
        int i2 = (i + 1) % 3;
        int i3 = (i + 2) % 3;
        long jMax = Math.max(jArr[i2], jArr[i3]);
        if (j < jMax) {
            if (!this.mSkippedFrameNumbers[i].isEmpty()) {
                if (j < this.mSkippedFrameNumbers[i].element().longValue()) {
                    throw new IllegalArgumentException("frame number " + j + " is a repeat");
                }
                if (j > this.mSkippedFrameNumbers[i].element().longValue()) {
                    throw new IllegalArgumentException("frame number " + j + " comes out of order. Expecting " + this.mSkippedFrameNumbers[i].element());
                }
                this.mSkippedFrameNumbers[i].remove();
            } else {
                int iIndexOf = this.mSkippedOtherFrameNumbers[i2].indexOf(Long.valueOf(j));
                int iIndexOf2 = this.mSkippedOtherFrameNumbers[i3].indexOf(Long.valueOf(j));
                boolean z = iIndexOf != -1;
                if (!(z ^ (iIndexOf2 != -1))) {
                    throw new IllegalArgumentException("frame number " + j + " is a repeat or invalid");
                }
                if (z) {
                    linkedList = this.mSkippedOtherFrameNumbers[i2];
                    linkedList2 = this.mSkippedFrameNumbers[i3];
                } else {
                    LinkedList<Long> linkedList3 = this.mSkippedOtherFrameNumbers[i3];
                    LinkedList<Long> linkedList4 = this.mSkippedFrameNumbers[i2];
                    linkedList = linkedList3;
                    iIndexOf = iIndexOf2;
                    linkedList2 = linkedList4;
                }
                for (int i4 = 0; i4 < iIndexOf; i4++) {
                    linkedList2.add(linkedList.removeFirst());
                }
                linkedList.remove();
            }
        } else {
            long jMax2 = Math.max(jMax, this.mCompletedFrameNumber[i]);
            while (true) {
                jMax2++;
                if (jMax2 >= j) {
                    break;
                } else {
                    this.mSkippedOtherFrameNumbers[i].add(Long.valueOf(jMax2));
                }
            }
        }
        this.mCompletedFrameNumber[i] = j;
    }
}
