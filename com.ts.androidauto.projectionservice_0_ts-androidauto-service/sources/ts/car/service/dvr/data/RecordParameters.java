package ts.car.service.dvr.data;

/* JADX INFO: loaded from: classes2.dex */
public final class RecordParameters {
    private boolean mAudioEnable;
    private boolean mAutoRecord;
    private int mBitRate;
    private int mFrameRate;
    private int mResolution;
    private int mSegmentPeriod;

    public RecordParameters() {
    }

    public RecordParameters(int resolution, int segmentation, int bitRate, boolean audioEnable, int frameRate, boolean autoRecord) {
        this.mResolution = resolution;
        this.mSegmentPeriod = segmentation;
        this.mBitRate = bitRate;
        this.mAudioEnable = audioEnable;
        this.mFrameRate = frameRate;
        this.mAutoRecord = autoRecord;
    }

    public int getResolution() {
        return this.mResolution;
    }

    public void setResolution(int resolution) {
        this.mResolution = resolution;
    }

    public int getSegmentPeriod() {
        return this.mSegmentPeriod;
    }

    public void setSegmentPeriod(int segmentPeriod) {
        this.mSegmentPeriod = segmentPeriod;
    }

    public int getBitRate() {
        return this.mBitRate;
    }

    public void setBitRate(int bitRate) {
        this.mBitRate = bitRate;
    }

    public boolean isAudioEnable() {
        return this.mAudioEnable;
    }

    public void setAudioEnable(boolean audioEnable) {
        this.mAudioEnable = audioEnable;
    }

    public int getFrameRate() {
        return this.mFrameRate;
    }

    public void setFrameRate(int frameRate) {
        this.mFrameRate = frameRate;
    }

    public boolean isAutoRecord() {
        return this.mAutoRecord;
    }

    public void setAutoRecord(boolean autoRecord) {
        this.mAutoRecord = autoRecord;
    }
}
