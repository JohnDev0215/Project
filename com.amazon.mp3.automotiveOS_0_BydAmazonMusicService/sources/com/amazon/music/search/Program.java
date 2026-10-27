package com.amazon.music.search;

import androidx.annotation.Nullable;
import com.amazon.music.ContentAccessType;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.model.LiveSportsProgram;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class Program implements SportsItem {
    private final String mBlockRef;
    private Set<ContentAccessType> mContentAccess = null;
    private final Map<String, String> mContentInfo;
    private final LiveSportsProgram mProgram;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image(ContextMappingConstants.IMAGE);

        private final String mSoccerField;

        AdditionalFields(String str) {
            this.mSoccerField = str;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCatalogField() {
            throw new UnsupportedOperationException("There are no catalog music programs.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getLibraryField() {
            throw new UnsupportedOperationException("There are no customer created programs.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getSoccerField() {
            return this.mSoccerField;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog music programs.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community music programs.");
        }
    }

    Program(Hit hit, Map<String, String> map) {
        this.mProgram = (LiveSportsProgram) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getTitle() {
        return this.mProgram.getTitle();
    }

    @Nullable
    public String getImage() {
        if (this.mProgram.getLiveSportsImages() == null || !this.mProgram.getLiveSportsImages().containsKey("PROGRAM_COVER") || this.mProgram.getLiveSportsImages().get("PROGRAM_COVER").isEmpty()) {
            return null;
        }
        return this.mProgram.getLiveSportsImages().get("PROGRAM_COVER").get(0).getUrl();
    }

    public String getLink() {
        return this.mProgram.getProgramLink();
    }

    public Date getStartTime() {
        return this.mProgram.getStartTime();
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
