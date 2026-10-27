package com.amazon.music.search;

import com.amazon.music.search.ResultItem;
import com.amazon.music.search.SearchRequest;
import com.amazon.tenzing.textsearch.v1_1.FeaturesRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes4.dex */
public abstract class SingleSearchRequest<TResultItem extends ResultItem> extends SearchRequest<TResultItem> implements SeeMoreRequest<TResultItem> {

    public static abstract class Builder<TBuilder extends Builder, TResultItem extends ResultItem, TSearchRequest extends SingleSearchRequest<TResultItem>, TAdditionalFields extends AdditionalFields> extends SearchRequest.Builder<TBuilder, TResultItem, TSearchRequest> {
        private final SpecBuilder mBuilder;
        private final ResultType mResultType;

        public Builder(String str, ResultType resultType, String str2) {
            super(str);
            SpecBuilder specBuilderCreateSpecBuilder = createSpecBuilder();
            this.mBuilder = specBuilderCreateSpecBuilder;
            specBuilderCreateSpecBuilder.withResultTypes(EnumSet.of(resultType)).withSearchType(str2);
            enableSpellCorrections();
            this.mResultType = resultType;
        }

        protected SpecBuilder createSpecBuilder() {
            return new SpecBuilder(SpecBuilder.DEFAULT_FIELDS_LIST).withExplicitFilterEnabled(isExplicitFilterEnabled());
        }

        public SpecBuilder getSpecBuilder() {
            return this.mBuilder;
        }

        public TBuilder withAdditionalFields(TAdditionalFields... tadditionalfieldsArr) {
            this.mBuilder.withAdditionalFields(tadditionalfieldsArr);
            return this;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        public TBuilder withSpellCorrections(SpellCorrections spellCorrections) {
            super.withSpellCorrections(spellCorrections);
            return this;
        }

        public TBuilder withPageToken(String str) {
            this.mBuilder.withPageToken(str);
            return this;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        public TBuilder withChildFriendlyContentFilter(boolean z) {
            this.mBuilder.withChildFriendlyContentFilter(z);
            return this;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        public TBuilder withExplicitLanguageFilter(boolean z) {
            this.mBuilder.withExplicitLanguageFilter(z);
            return this;
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected List<ResultSpecification> buildSpecs(ResultType resultType) {
            return Arrays.asList(this.mBuilder.build(resultType));
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        public TSearchRequest build() {
            this.mBuilder.withPageSize(getPageSize());
            this.mBuilder.withSearchContentTier(getSearchContentTier());
            this.mBuilder.withAssetQualities(getAssetQualities());
            List<ResultSpecification> listBuildSpecs = buildSpecs(this.mResultType);
            LinkedList linkedList = new LinkedList();
            FeaturesRequest.Builder featureBuilder = getFeatureBuilder();
            if (!listBuildSpecs.isEmpty()) {
                linkedList.add(com.amazon.tenzing.textsearch.v1_1.SearchRequest.builder().withQuery(getTerm()).withFeatures(featureBuilder.build()).withResultSpecs(listBuildSpecs).withQueryMetadata(getQueryMetadata()).build());
            }
            return create(linkedList, getPagerCreator(), getTerm());
        }
    }

    SingleSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<TResultItem> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
