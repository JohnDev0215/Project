package com.amazon.music.search;

import androidx.annotation.NonNull;
import com.amazon.music.platform.model.ParentalControls;
import com.amazon.tenzing.textsearch.v1_1.AssetQuality;
import com.amazon.tenzing.textsearch.v1_1.ContentRestrictions;
import com.amazon.tenzing.textsearch.v1_1.DocumentSpecification;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
final class SpecBuilder {
    public static final String[] DEFAULT_FIELDS_LIST = {"__default", "parentalControls.hasExplicitLanguage", "contentTier"};
    private List<String> assetQualities;
    private boolean isExplicitFilterEnabled;
    private final List<AdditionalFields> mAdditionals;
    private Map<String, AdditionalFields[]> mDocumentTypes;
    private final List<String> mFields;
    private List<String> mFilters;
    private int mPageSize;
    private String mPageToken;
    private EnumSet<ResultType> mResultTypes;
    private String mSearchType;
    private SearchContentTier searchContentTier;

    public SpecBuilder() {
        this.mAdditionals = new LinkedList();
        this.mResultTypes = EnumSet.noneOf(ResultType.class);
        this.mDocumentTypes = Collections.emptyMap();
        this.mSearchType = "";
        this.mPageSize = 10;
        this.mFields = new LinkedList();
        this.isExplicitFilterEnabled = false;
        this.assetQualities = new ArrayList();
        this.searchContentTier = null;
        this.mPageToken = null;
        this.mFilters = new ArrayList();
    }

    public SpecBuilder(String[] strArr) {
        this.mAdditionals = new LinkedList();
        this.mResultTypes = EnumSet.noneOf(ResultType.class);
        this.mDocumentTypes = Collections.emptyMap();
        this.mSearchType = "";
        this.mPageSize = 10;
        LinkedList linkedList = new LinkedList();
        this.mFields = linkedList;
        this.isExplicitFilterEnabled = false;
        this.assetQualities = new ArrayList();
        this.searchContentTier = null;
        this.mPageToken = null;
        this.mFilters = new ArrayList();
        Collections.addAll(linkedList, strArr);
    }

    static String getFieldName(AdditionalFields additionalFields, ResultType resultType) {
        if (ResultType.Catalog == resultType) {
            return additionalFields.getCatalogField();
        }
        if (ResultType.Library == resultType) {
            return additionalFields.getLibraryField();
        }
        if (ResultType.Sports == resultType) {
            return additionalFields.getSoccerField();
        }
        if (ResultType.Podcast_Catalog == resultType) {
            return additionalFields.getPodcastCatalogField();
        }
        if (ResultType.Community == resultType) {
            return additionalFields.getCommunityField();
        }
        throw new UnsupportedOperationException("Unsupported ResultType: " + resultType);
    }

    public SpecBuilder withPageSize(int i) {
        this.mPageSize = i;
        return this;
    }

    public SpecBuilder withSearchType(String str) {
        this.mSearchType = str;
        return this;
    }

    public SpecBuilder withResultTypes(EnumSet<ResultType> enumSet) {
        this.mResultTypes = enumSet;
        return this;
    }

    public SpecBuilder withDocumentTypes(Map<String, AdditionalFields[]> map) {
        this.mDocumentTypes = map;
        return this;
    }

    public SpecBuilder withExplicitLanguageFilter(boolean z) {
        if (z) {
            this.mFilters.add("explicitLanguageFilter");
        }
        return this;
    }

    public SpecBuilder withChildFriendlyContentFilter(boolean z) {
        if (z) {
            this.mFilters.add("childFriendlyContentFilter");
        }
        return this;
    }

    public SpecBuilder withAdditionalFields(AdditionalFields... additionalFieldsArr) {
        this.mAdditionals.clear();
        Collections.addAll(this.mAdditionals, additionalFieldsArr);
        return this;
    }

    public SpecBuilder withSearchContentTier(@NonNull SearchContentTier searchContentTier) {
        this.searchContentTier = searchContentTier;
        return this;
    }

    public SpecBuilder withExplicitFilterEnabled(boolean z) {
        this.isExplicitFilterEnabled = z;
        return this;
    }

    public SpecBuilder withAssetQualities(List<String> list) {
        this.assetQualities = list;
        return this;
    }

    public SpecBuilder withPageToken(String str) {
        this.mPageToken = str;
        return this;
    }

    static String getResultLabel(String str, ResultType resultType) {
        return String.format(resultType.getResultLabelFormat(), str);
    }

    private String getSearchType(ResultType resultType) {
        return getSearchType(this.mSearchType, resultType);
    }

    private String getSearchType(String str, ResultType resultType) {
        return getResultLabel(str, resultType);
    }

    public List<DocumentSpecification> buildDocumentSpecifications(ResultType resultType) {
        LinkedList linkedList = new LinkedList();
        if (!this.mDocumentTypes.isEmpty()) {
            for (String str : this.mDocumentTypes.keySet()) {
                if (this.mResultTypes.contains(resultType)) {
                    AdditionalFields[] additionalFieldsArr = this.mDocumentTypes.get(str);
                    ArrayList arrayList = new ArrayList(this.mFields.size() + (additionalFieldsArr != null ? additionalFieldsArr.length : 0));
                    arrayList.addAll(this.mFields);
                    if (additionalFieldsArr != null) {
                        for (AdditionalFields additionalFields : additionalFieldsArr) {
                            String fieldName = getFieldName(additionalFields, resultType);
                            if (StringUtils.isNotEmpty(fieldName)) {
                                arrayList.add(fieldName);
                            }
                        }
                    }
                    DocumentSpecification.Builder builderWithFields = DocumentSpecification.builder().withType(getSearchType(str, resultType)).withFields(arrayList);
                    if (!this.mFilters.isEmpty()) {
                        builderWithFields.withFilters(this.mFilters);
                    }
                    linkedList.add(builderWithFields.build());
                }
            }
        } else if (this.mResultTypes.contains(resultType)) {
            ArrayList arrayList2 = new ArrayList(this.mFields.size() + this.mAdditionals.size());
            arrayList2.addAll(this.mFields);
            Iterator<AdditionalFields> it = this.mAdditionals.iterator();
            while (it.hasNext()) {
                String fieldName2 = getFieldName(it.next(), resultType);
                if (StringUtils.isNotEmpty(fieldName2)) {
                    arrayList2.add(fieldName2);
                }
            }
            DocumentSpecification.Builder builderWithFields2 = DocumentSpecification.builder().withType(getSearchType(resultType)).withFields(arrayList2);
            if (!this.mFilters.isEmpty()) {
                builderWithFields2.withFilters(this.mFilters);
            }
            linkedList.add(builderWithFields2.build());
        }
        return linkedList;
    }

    public ResultSpecification build(ResultType resultType) {
        if (!this.mResultTypes.contains(resultType)) {
            return null;
        }
        ContentRestrictions.Builder builder = ContentRestrictions.builder();
        ParentalControls parentalControlsBuild = ParentalControls.builder().withHasExplicitLanguage(Boolean.valueOf(!this.isExplicitFilterEnabled)).build();
        AssetQuality assetQualityBuild = AssetQuality.builder().withQuality(this.assetQualities).build();
        builder.withAllowedParentalControls(parentalControlsBuild);
        builder.withAssetQuality(assetQualityBuild);
        if (!resultType.isLibrary()) {
            SearchContentTier searchContentTier = this.searchContentTier;
            if (searchContentTier == null) {
                throw new RuntimeException("searchContentTier should not be null");
            }
            builder.withContentTier(searchContentTier.toString());
        }
        return ResultSpecification.builder().withLabel(getResultLabel(this.mSearchType, resultType)).withMaxResults(Integer.valueOf(this.mPageSize)).withPageToken(this.mPageToken).withContentRestrictions(builder.build()).withDocumentSpecs(buildDocumentSpecifications(resultType)).build();
    }
}
