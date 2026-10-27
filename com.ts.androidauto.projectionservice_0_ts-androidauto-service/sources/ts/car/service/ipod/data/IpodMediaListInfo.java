package ts.car.service.ipod.data;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class IpodMediaListInfo {
    private int mCategoryType;
    private int mEndIndex;
    private ArrayList<IpodMediaItemInfo> mIpodMediaItemInfos = new ArrayList<>();
    private int mLevel;
    private int mSize;
    private int mStartIndex;
    private int mSubCount;
    private int mTotalCount;

    public IpodMediaListInfo() {
    }

    public IpodMediaListInfo(int size, int total) {
        this.mSize = size;
        this.mTotalCount = total;
    }

    public int getSize() {
        return this.mSize;
    }

    public void setSize(int size) {
        this.mSize = size;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public void setTotalCount(int totalCount) {
        this.mTotalCount = totalCount;
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public void setStartIndex(int startIndex) {
        this.mStartIndex = startIndex;
    }

    public int getEndIndex() {
        return this.mEndIndex;
    }

    public void setEndIndex(int endIndex) {
        this.mEndIndex = endIndex;
    }

    public int getLevel() {
        return this.mLevel;
    }

    public void setLevel(int level) {
        this.mLevel = level;
    }

    public int getCategoryType() {
        return this.mCategoryType;
    }

    public void setCategoryType(int categoryType) {
        this.mCategoryType = categoryType;
    }

    public int getSubCount() {
        return this.mSubCount;
    }

    public void setSubCount(int subCount) {
        this.mSubCount = subCount;
    }

    public ArrayList<IpodMediaItemInfo> getIpodMediaItemInfos() {
        return this.mIpodMediaItemInfos;
    }

    public void setIpodMediaItemInfos(ArrayList<IpodMediaItemInfo> mediaItemInfos) {
        this.mIpodMediaItemInfos = mediaItemInfos;
    }

    public String toString() {
        return "IpodMediaListInfo{Size=" + this.mSize + ", TotalCount=" + this.mTotalCount + ", StartIndex=" + this.mStartIndex + ", EndIndex=" + this.mEndIndex + ", Level=" + this.mLevel + ", CategoryType=" + this.mCategoryType + ", SubCount=" + this.mSubCount + "', MusicList.size=" + this.mIpodMediaItemInfos.size() + ", MusicList={" + AndroidAutoConst.DEFAULT_SCREEN_NAME + "}}";
    }
}
