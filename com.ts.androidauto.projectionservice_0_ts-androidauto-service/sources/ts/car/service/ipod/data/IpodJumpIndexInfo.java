package ts.car.service.ipod.data;

/* JADX INFO: loaded from: classes2.dex */
public class IpodJumpIndexInfo {
    private int mJumpIndex;
    private String mJumpSection;

    public IpodJumpIndexInfo() {
    }

    public IpodJumpIndexInfo(String jumpSection, int jumpIndex) {
        this.mJumpSection = jumpSection;
        this.mJumpIndex = jumpIndex;
    }

    public String getJumpSection() {
        return this.mJumpSection;
    }

    public void setJumpSection(String jumpSection) {
        this.mJumpSection = jumpSection;
    }

    public int getJumpIndex() {
        return this.mJumpIndex;
    }

    public void setJumpIndex(int jumpIndex) {
        this.mJumpIndex = jumpIndex;
    }

    public String toString() {
        return "IpodJumpIndexInfo{JumpSection='" + this.mJumpSection + "', JumpIndex=" + this.mJumpIndex + "'}";
    }
}
