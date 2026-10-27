package com.loostone.libtuning.channel.skyworth.old.record;

import OooO00o.OooO00o.OooO0O0.a;
import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.d;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class RecordMgr {
    private static final String TAG = "RecordMgr";
    private static RecordMgr mInstance;
    private FileOutputStream fo;
    private IAudioRecord mAudioRecord;
    private final int BUFFER_SIZE = 205824;
    private final int SAMPLE_RATE = 48000;
    private final int VOICE_RATE = 16000;
    private final int CHANNEL = 2;
    private final int AUDIO_FORMAT = 16;
    private final int NODE_SIZE_MAX = 204800;
    private byte[] mBuffer = new byte[205824];
    private byte[] jniBuffer = new byte[204800];
    private boolean mCreated = false;
    private int position = 0;

    private RecordMgr() {
    }

    private int compareVersion(String str, String str2) throws Exception {
        if (str == null || str2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = strArrSplit[i].compareTo(strArrSplit2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return strArrSplit.length - strArrSplit2.length;
    }

    public static synchronized RecordMgr getInstance() {
        if (mInstance == null) {
            mInstance = new RecordMgr();
        }
        return mInstance;
    }

    private boolean isCanUseNode() {
        if (new File("/dev/pm_kara_wet").exists()) {
            return true;
        }
        d.a aVarB = d.b("cat /sys/class/pmkaraoke/version", false, true);
        if (aVarB.f91a == 0 && !TextUtils.isEmpty(aVarB.f92b)) {
            try {
                return compareVersion(aVarB.f92b.trim(), "2.6.10") > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int resample(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        int i4 = i2 / i3;
        int i5 = 0;
        for (int i6 = 0; i6 < i / 2; i6++) {
            if (i6 % i4 == 0) {
                int i7 = i5 * 2;
                int i8 = i6 * 2;
                bArr2[i7] = bArr[i8];
                bArr2[i7 + 1] = bArr[i8 + 1];
                i5++;
            }
        }
        return i5 * 2;
    }

    public static int stereo2mono(byte[] bArr, int i) {
        int i2 = i / 4;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 4;
            int i5 = (((bArr[i4] + (bArr[i4 + 1] << 8)) + bArr[i4 + 2]) + (bArr[i4 + 3] << 8)) / 2;
            int i6 = i3 * 2;
            bArr[i6] = (byte) (i5 & 255);
            bArr[i6 + 1] = (byte) ((i5 >> 8) & 255);
        }
        return i2 * 2;
    }

    public synchronized void create() {
        b bVar = b.f41a;
        bVar.b(TAG, "RecordMgr create");
        if (this.mCreated) {
            return;
        }
        this.mCreated = true;
        if (isCanUseNode()) {
            bVar.b(TAG, "node mode");
            this.mAudioRecord = new NodeAudioRecord();
        } else {
            bVar.b(TAG, "android mode");
            this.mAudioRecord = new AndroidAudioRecord();
        }
        this.mAudioRecord.create(205824, 48000, 2, 16);
    }

    public synchronized void destroy() {
        IAudioRecord iAudioRecord = this.mAudioRecord;
        if (iAudioRecord != null) {
            iAudioRecord.destroy();
            this.mAudioRecord = null;
        }
        this.mCreated = false;
    }

    public void foClose() {
        FileOutputStream fileOutputStream = this.fo;
        if (fileOutputStream != null) {
            try {
                try {
                    if (fileOutputStream.getChannel().position() < this.position) {
                        Log.d(TAG, "foClose: --------> pos: " + this.fo.getChannel().position());
                        this.fo.flush();
                    } else {
                        this.fo.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                this.fo = null;
            }
        }
    }

    public void foOpen() {
        if (this.fo == null) {
            try {
                String str = a.f39a.a().getCacheDir() + InternalZipConstants.ZIP_FILE_SEPARATOR + System.currentTimeMillis() + ".pcm";
                Log.d(TAG, "foOpen: --------------> PATH: " + str);
                this.fo = new FileOutputStream(str);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int read(byte[] bArr, int i, int i2) {
        IAudioRecord iAudioRecord = this.mAudioRecord;
        if (iAudioRecord == null) {
            return 0;
        }
        int i3 = iAudioRecord.read(this.jniBuffer, 0, 204800);
        this.mAudioRecord.read2Cache();
        int iStereo2mono = stereo2mono(this.mBuffer, resample(this.jniBuffer, this.mBuffer, i3, 48000, 16000));
        if (bArr.length < iStereo2mono) {
            Log.e(TAG, "read: -----------------> data.length " + bArr.length + ", readsize: " + iStereo2mono + ", offset: " + i);
        }
        System.arraycopy(this.mBuffer, 0, bArr, i, iStereo2mono);
        return iStereo2mono;
    }
}
