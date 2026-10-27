package com.amazon.music.search;

import androidx.annotation.Nullable;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.model.LiveSportsCompetition;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class Competition implements SportsItem {
    private final String mBlockRef;
    private final LiveSportsCompetition mCompetition;
    private final Map<String, String> mContentInfo;

    public enum AdditionalFields implements com.amazon.music.search.AdditionalFields {
        Image(ContextMappingConstants.IMAGE);

        private final String mSoccerField;

        AdditionalFields(String str) {
            this.mSoccerField = str;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCatalogField() {
            throw new UnsupportedOperationException("There are no catalog music competitions.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getLibraryField() {
            throw new UnsupportedOperationException("There are no customer created competitions.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getSoccerField() {
            return this.mSoccerField;
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getPodcastCatalogField() {
            throw new UnsupportedOperationException("There are no podcast catalog competitions.");
        }

        @Override // com.amazon.music.search.AdditionalFields
        public String getCommunityField() {
            throw new UnsupportedOperationException("There are no community competitions.");
        }
    }

    Competition(Hit hit, Map<String, String> map) {
        this.mCompetition = (LiveSportsCompetition) hit.getDocument();
        this.mContentInfo = map;
        this.mBlockRef = hit.getBlockRef();
    }

    public String getTitle() {
        return this.mCompetition.getName();
    }

    @Nullable
    public String getImage() {
        if (this.mCompetition.getLiveSportsImages() == null || !this.mCompetition.getLiveSportsImages().containsKey("PROGRAM_COVER") || this.mCompetition.getLiveSportsImages().get("PROGRAM_COVER").isEmpty()) {
            return null;
        }
        return this.mCompetition.getLiveSportsImages().get("PROGRAM_COVER").get(0).getUrl();
    }

    public String getLink() {
        return this.mCompetition.getCompetitionLink();
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
