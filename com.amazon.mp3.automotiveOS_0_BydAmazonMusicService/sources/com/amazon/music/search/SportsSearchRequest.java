package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.Competition;
import com.amazon.music.search.Program;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class SportsSearchRequest extends SingleSearchRequest<SportsItem> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, SportsItem, SportsSearchRequest, Object> {
        private final Map<String, AdditionalFields[]> mAdditionals;

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<SportsItem>) pagerCreator, str);
        }

        public Builder(String str) {
            super(str, ResultType.Sports, "sports");
            HashMap map = new HashMap();
            this.mAdditionals = map;
            map.put("program", null);
            map.put("competition", null);
            getSpecBuilder().withDocumentTypes(map);
        }

        static String getResultLabel() {
            return SpecBuilder.getResultLabel("sports", ResultType.Sports);
        }

        public Builder withAdditionalFields(Program.AdditionalFields... additionalFieldsArr) {
            this.mAdditionals.put("program", additionalFieldsArr);
            getSpecBuilder().withDocumentTypes(this.mAdditionals);
            return this;
        }

        public Builder withAdditionalFields(Competition.AdditionalFields... additionalFieldsArr) {
            this.mAdditionals.put("competition", additionalFieldsArr);
            getSpecBuilder().withDocumentTypes(this.mAdditionals);
            return this;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<SportsItem> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<SportsItem>() { // from class: com.amazon.music.search.SportsSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<SportsItem>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new SportsPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SportsSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<SportsItem> pagerCreator, String str) {
            return new SportsSearchRequest(list, pagerCreator, str);
        }
    }

    SportsSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<SportsItem> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
