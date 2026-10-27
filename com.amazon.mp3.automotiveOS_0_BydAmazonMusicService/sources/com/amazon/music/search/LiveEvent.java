package com.amazon.music.search;

import androidx.annotation.Nullable;
import com.amazon.music.platform.model.Art;
import com.amazon.music.platform.model.ArtURL;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class LiveEvent implements ResultItem {
    public static final String[] DEFAULT_FIELDS = {"id", "startDate", "title"};
    private final String mBlockRef;
    private final Map<String, String> mContentInfo;
    private final com.amazon.music.platform.model.LiveEvent mLiveEvent;

    LiveEvent(Hit hit, Map<String, String> map) {
        this.mLiveEvent = (com.amazon.music.platform.model.LiveEvent) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getId() {
        return this.mLiveEvent.getId();
    }

    public Date getStartDate() {
        return this.mLiveEvent.getStartDate();
    }

    public String getTitle() {
        return this.mLiveEvent.getTitle();
    }

    @Nullable
    public String getImage() {
        Art artOriginal = this.mLiveEvent.getArtOriginal();
        if (artOriginal instanceof ArtURL) {
            return ((ArtURL) artOriginal).getArtUrl();
        }
        return null;
    }

    @Override // com.amazon.music.search.ResultItem
    public Map<String, String> getContentInfo() {
        return this.mContentInfo;
    }

    @Override // com.amazon.music.search.ResultItem
    public String getBlockRef() {
        return this.mBlockRef;
    }
}
