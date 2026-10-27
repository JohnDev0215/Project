package ts.car.iap.protocol.common;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MediaListInfo {
    private int mCategoryType;
    private int mEndIndex;
    private int mLevel;
    private ArrayList<MediaItemInfo> mMediaItemInfos = new ArrayList<>();
    private int mSize;
    private int mStartIndex;
    private int mSubCount;
    private int mTotalCount;

    public MediaListInfo() {
    }

    public MediaListInfo(int size, int total) {
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

    public void setSubCount(int subCount) {
        this.mSubCount = subCount;
    }

    public int getSubCount() {
        return this.mSubCount;
    }

    public ArrayList<MediaItemInfo> getMediaItemInfos() {
        return this.mMediaItemInfos;
    }

    public void setMediaItemInfos(ArrayList<MediaItemInfo> mediaItemInfos) {
        this.mMediaItemInfos = mediaItemInfos;
    }

    public String toString() {
        return "MediaItemInfo{Size=" + this.mSize + ", TotalCount=" + this.mTotalCount + ", StartIndex=" + this.mStartIndex + ", EndIndex=" + this.mEndIndex + ", Level=" + this.mLevel + ", CategoryType=" + this.mCategoryType + ", SubCount=" + this.mSubCount + "', MusicList.size=" + this.mMediaItemInfos.size() + ", MusicList={}}";
    }
}
