package com.amazon.mp3.lyrics.item;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Lyrics {
    private String mAsin;
    private String[] mCopyrights;
    private ExplicitStatus mExplicitStatus;
    private String mExternalId;
    private long mFetchDate;
    private long mLastUpdated;
    private long mLastViewed;
    private List<LyricsLine> mLines = new ArrayList();
    private LyricsSharingConfig mLyricsSharingConfig;
    private String mMarketplace;
    private long mMatchHash;
    private String mSource;
    private int mViewCount;
    private String[] mWriter;

    public List<LyricsLine> getLines() {
        return this.mLines;
    }

    public void setLines(List<LyricsLine> lines) {
        this.mLines = lines;
    }

    public String[] getCopyrights() {
        return this.mCopyrights;
    }

    public void setCopyrights(String[] copyrights) {
        this.mCopyrights = copyrights;
    }

    public String[] getWriter() {
        return this.mWriter;
    }

    public void setWriter(String[] writer) {
        this.mWriter = writer;
    }

    public void setExternalId(String externalId) {
        this.mExternalId = externalId;
    }

    public void setExplicitStatus(ExplicitStatus explicitStatus) {
        this.mExplicitStatus = explicitStatus;
    }

    public int getViewCount() {
        return this.mViewCount;
    }

    public void setViewCount(int viewCount) {
        this.mViewCount = viewCount;
    }

    public String getSource() {
        return this.mSource;
    }

    public void setSource(String source) {
        this.mSource = source;
    }

    public String getAsin() {
        return this.mAsin;
    }

    public void setAsin(String asin) {
        this.mAsin = asin;
    }

    public String getMarketplace() {
        return this.mMarketplace;
    }

    public void setMarketplace(String marketplace) {
        this.mMarketplace = marketplace;
    }

    public void setLyricsSharingConfig(LyricsSharingConfig lyricsSharingConfig) {
        this.mLyricsSharingConfig = lyricsSharingConfig;
    }

    public Boolean isLyricsSharable() {
        LyricsSharingConfig lyricsSharingConfig = this.mLyricsSharingConfig;
        if (lyricsSharingConfig == null) {
            return null;
        }
        return Boolean.valueOf(lyricsSharingConfig.getIsLyricsSharable());
    }

    public void clear() {
        this.mMatchHash = 0L;
        this.mAsin = null;
        this.mCopyrights = null;
        this.mExplicitStatus = null;
        this.mExternalId = null;
        this.mLastUpdated = 0L;
        this.mLastViewed = 0L;
        this.mLines.clear();
        this.mMarketplace = null;
        this.mSource = null;
        this.mViewCount = 0;
        this.mWriter = null;
        this.mFetchDate = 0L;
        this.mLyricsSharingConfig = null;
    }
}
