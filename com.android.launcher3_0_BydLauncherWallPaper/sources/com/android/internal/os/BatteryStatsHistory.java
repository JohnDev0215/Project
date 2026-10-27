package com.android.internal.os;

import android.os.BatteryStats;
import android.os.Parcel;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.ArraySet;
import android.util.AtomicFile;
import android.util.Slog;
import com.android.internal.util.ParseUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BatteryStatsHistory {
    private static final boolean DEBUG = false;
    public static final String FILE_SUFFIX = ".bin";
    public static final String HISTORY_DIR = "battery-history";
    private static final int MIN_FREE_SPACE = 104857600;
    private static final String TAG = "BatteryStatsHistory";
    private AtomicFile mActiveFile;
    private int mCurrentFileIndex;
    private Parcel mCurrentParcel;
    private int mCurrentParcelEnd;
    private final List<Integer> mFileNumbers;
    private final Parcel mHistoryBuffer;
    private final File mHistoryDir;
    private List<Parcel> mHistoryParcels;
    private int mParcelIndex;
    private int mRecordCount;
    private final BatteryStatsImpl mStats;

    public BatteryStatsHistory(BatteryStatsImpl batteryStatsImpl, File file, Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.mFileNumbers = arrayList;
        this.mHistoryParcels = null;
        this.mRecordCount = 0;
        this.mParcelIndex = 0;
        this.mStats = batteryStatsImpl;
        this.mHistoryBuffer = parcel;
        File file2 = new File(file, HISTORY_DIR);
        this.mHistoryDir = file2;
        file2.mkdirs();
        if (!file2.exists()) {
            Slog.wtf(TAG, "HistoryDir does not exist:" + file2.getPath());
        }
        final ArraySet arraySet = new ArraySet();
        file2.listFiles(new FilenameFilter() { // from class: com.android.internal.os.BatteryStatsHistory.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file3, String str) {
                int iLastIndexOf = str.lastIndexOf(BatteryStatsHistory.FILE_SUFFIX);
                if (iLastIndexOf <= 0) {
                    return false;
                }
                Integer numValueOf = Integer.valueOf(ParseUtils.parseInt(str.substring(0, iLastIndexOf), -1));
                if (numValueOf.intValue() == -1) {
                    return false;
                }
                arraySet.add(numValueOf);
                return true;
            }
        });
        if (!arraySet.isEmpty()) {
            arrayList.addAll(arraySet);
            Collections.sort(arrayList);
            setActiveFile(((Integer) arrayList.get(arrayList.size() - 1)).intValue());
        } else {
            arrayList.add(0);
            setActiveFile(0);
        }
    }

    public BatteryStatsHistory(BatteryStatsImpl batteryStatsImpl, Parcel parcel) {
        this.mFileNumbers = new ArrayList();
        this.mHistoryParcels = null;
        this.mRecordCount = 0;
        this.mParcelIndex = 0;
        this.mStats = batteryStatsImpl;
        this.mHistoryDir = null;
        this.mHistoryBuffer = parcel;
    }

    private void setActiveFile(int i) {
        this.mActiveFile = getFile(i);
    }

    private AtomicFile getFile(int i) {
        return new AtomicFile(new File(this.mHistoryDir, i + FILE_SUFFIX));
    }

    public void startNextFile() {
        if (this.mFileNumbers.isEmpty()) {
            Slog.wtf(TAG, "mFileNumbers should never be empty");
            return;
        }
        int iIntValue = this.mFileNumbers.get(r0.size() - 1).intValue() + 1;
        this.mFileNumbers.add(Integer.valueOf(iIntValue));
        setActiveFile(iIntValue);
        if (!hasFreeDiskSpace()) {
            getFile(this.mFileNumbers.remove(0).intValue()).delete();
        }
        while (this.mFileNumbers.size() > this.mStats.mConstants.MAX_HISTORY_FILES) {
            getFile(this.mFileNumbers.get(0).intValue()).delete();
            this.mFileNumbers.remove(0);
        }
    }

    public void resetAllFiles() {
        Iterator<Integer> it = this.mFileNumbers.iterator();
        while (it.hasNext()) {
            getFile(it.next().intValue()).delete();
        }
        this.mFileNumbers.clear();
        this.mFileNumbers.add(0);
        setActiveFile(0);
    }

    public boolean startIteratingHistory() {
        this.mRecordCount = 0;
        this.mCurrentFileIndex = 0;
        this.mCurrentParcel = null;
        this.mCurrentParcelEnd = 0;
        this.mParcelIndex = 0;
        return true;
    }

    public void finishIteratingHistory() {
        Parcel parcel = this.mHistoryBuffer;
        parcel.setDataPosition(parcel.dataSize());
    }

    public Parcel getNextParcel(BatteryStats.HistoryItem historyItem) {
        if (this.mRecordCount == 0) {
            historyItem.clear();
        }
        this.mRecordCount++;
        Parcel parcel = this.mCurrentParcel;
        if (parcel != null) {
            if (parcel.dataPosition() < this.mCurrentParcelEnd) {
                return this.mCurrentParcel;
            }
            Parcel parcel2 = this.mHistoryBuffer;
            Parcel parcel3 = this.mCurrentParcel;
            if (parcel2 == parcel3) {
                return null;
            }
            List<Parcel> list = this.mHistoryParcels;
            if (list == null || !list.contains(parcel3)) {
                this.mCurrentParcel.recycle();
            }
        }
        while (this.mCurrentFileIndex < this.mFileNumbers.size() - 1) {
            this.mCurrentParcel = null;
            this.mCurrentParcelEnd = 0;
            Parcel parcelObtain = Parcel.obtain();
            List<Integer> list2 = this.mFileNumbers;
            int i = this.mCurrentFileIndex;
            this.mCurrentFileIndex = i + 1;
            if (readFileToParcel(parcelObtain, getFile(list2.get(i).intValue()))) {
                int i2 = parcelObtain.readInt();
                int iDataPosition = parcelObtain.dataPosition();
                int i3 = i2 + iDataPosition;
                this.mCurrentParcelEnd = i3;
                this.mCurrentParcel = parcelObtain;
                if (iDataPosition < i3) {
                    return parcelObtain;
                }
            } else {
                parcelObtain.recycle();
            }
        }
        if (this.mHistoryParcels != null) {
            while (this.mParcelIndex < this.mHistoryParcels.size()) {
                List<Parcel> list3 = this.mHistoryParcels;
                int i4 = this.mParcelIndex;
                this.mParcelIndex = i4 + 1;
                Parcel parcel4 = list3.get(i4);
                if (skipHead(parcel4)) {
                    int i5 = parcel4.readInt();
                    int iDataPosition2 = parcel4.dataPosition();
                    int i6 = i5 + iDataPosition2;
                    this.mCurrentParcelEnd = i6;
                    this.mCurrentParcel = parcel4;
                    if (iDataPosition2 < i6) {
                        return parcel4;
                    }
                }
            }
        }
        if (this.mHistoryBuffer.dataSize() <= 0) {
            return null;
        }
        this.mHistoryBuffer.setDataPosition(0);
        Parcel parcel5 = this.mHistoryBuffer;
        this.mCurrentParcel = parcel5;
        this.mCurrentParcelEnd = parcel5.dataSize();
        return this.mCurrentParcel;
    }

    public boolean readFileToParcel(Parcel parcel, AtomicFile atomicFile) {
        try {
            SystemClock.uptimeMillis();
            byte[] fully = atomicFile.readFully();
            parcel.unmarshall(fully, 0, fully.length);
            parcel.setDataPosition(0);
            return skipHead(parcel);
        } catch (Exception e) {
            Slog.e(TAG, "Error reading file " + atomicFile.getBaseFile().getPath(), e);
            return false;
        }
    }

    private boolean skipHead(Parcel parcel) {
        parcel.setDataPosition(0);
        if (parcel.readInt() != 188) {
            return false;
        }
        parcel.readLong();
        return true;
    }

    public void writeToParcel(Parcel parcel) {
        SystemClock.uptimeMillis();
        parcel.writeInt(this.mFileNumbers.size() - 1);
        for (int i = 0; i < this.mFileNumbers.size() - 1; i++) {
            AtomicFile file = getFile(this.mFileNumbers.get(i).intValue());
            byte[] fully = new byte[0];
            try {
                fully = file.readFully();
            } catch (Exception e) {
                Slog.e(TAG, "Error reading file " + file.getBaseFile().getPath(), e);
            }
            parcel.writeByteArray(fully);
        }
    }

    public void readFromParcel(Parcel parcel) {
        SystemClock.uptimeMillis();
        this.mHistoryParcels = new ArrayList();
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            if (bArrCreateByteArray.length != 0) {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.unmarshall(bArrCreateByteArray, 0, bArrCreateByteArray.length);
                parcelObtain.setDataPosition(0);
                this.mHistoryParcels.add(parcelObtain);
            }
        }
    }

    private boolean hasFreeDiskSpace() {
        return new StatFs(this.mHistoryDir.getAbsolutePath()).getAvailableBytes() > 104857600;
    }

    public List<Integer> getFilesNumbers() {
        return this.mFileNumbers;
    }

    public AtomicFile getActiveFile() {
        return this.mActiveFile;
    }

    public int getHistoryUsedSize() {
        int length = 0;
        for (int i = 0; i < this.mFileNumbers.size() - 1; i++) {
            length = (int) (((long) length) + getFile(this.mFileNumbers.get(i).intValue()).getBaseFile().length());
        }
        int iDataSize = length + this.mHistoryBuffer.dataSize();
        if (this.mHistoryParcels != null) {
            for (int i2 = 0; i2 < this.mHistoryParcels.size(); i2++) {
                iDataSize += this.mHistoryParcels.get(i2).dataSize();
            }
        }
        return iDataSize;
    }
}
