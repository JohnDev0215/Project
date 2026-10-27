package ts.car.service.ipod.data;

/* JADX INFO: loaded from: classes.dex */
public class IpodMediaItemInfo {
    private int mIndex;
    private String mInitial;
    private String mKoName;
    private String mName;
    private String mPid;

    public IpodMediaItemInfo(String name, String pid) {
        this.mName = name;
        this.mPid = pid;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

    public String getInitial() {
        return this.mInitial;
    }

    public void setInitial(String initial) {
        this.mInitial = initial;
    }

    public String getKoName() {
        return this.mKoName;
    }

    public void setKoName(String koname) {
        this.mKoName = koname;
    }

    public String toString() {
        return "IpodMediaItemInfo{Name='" + this.mName + "', Pid='" + this.mPid + "', Index=" + this.mIndex + ", Initial='" + this.mInitial + "', KoName='" + this.mKoName + "'}";
    }
}
