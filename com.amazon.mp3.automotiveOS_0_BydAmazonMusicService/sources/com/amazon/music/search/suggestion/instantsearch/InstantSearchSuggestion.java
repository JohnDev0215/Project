package com.amazon.music.search.suggestion.instantsearch;

import com.amazon.music.search.suggestion.SearchSuggestionException;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.InstantSearchResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class InstantSearchSuggestion {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstantSearchSuggestion.class.getSimpleName());
    private final InstantSearchSuggestionService instantSearchService;
    private final int maxResults;

    public InstantSearchSuggestion(InstantSearchSuggestionService instantSearchSuggestionService, int i) {
        Validate.notNull(instantSearchSuggestionService, "InstantSearchSuggestionService cannot be null", new Object[0]);
        Validate.isTrue(i >= 1, "Max results cannot be less than 1", new Object[0]);
        this.instantSearchService = instantSearchSuggestionService;
        this.maxResults = i;
    }

    public InstantSearchQueryResult query(InstantSearchQueryRequest instantSearchQueryRequest) throws SearchSuggestionException {
        if (instantSearchQueryRequest == null || StringUtils.isBlank(instantSearchQueryRequest.getQuery())) {
            LOGGER.error("The query passed is null or empty. Returning null");
            return null;
        }
        try {
            InstantSearchResponse instantSearchResponseQuery = this.instantSearchService.query(InstantSearchRequest.builder().withQuery(instantSearchQueryRequest.getQuery()).withMaxResults(Integer.valueOf(this.maxResults)).withActivityId(instantSearchQueryRequest.getActivityId()).withContentTier(instantSearchQueryRequest.getContentTier()).withTextSpec(instantSearchQueryRequest.getTextSpec()).withEntitySpec(instantSearchQueryRequest.getEntitySpec()).withQueryId(instantSearchQueryRequest.getQueryId()).build());
            if (instantSearchResponseQuery == null) {
                LOGGER.debug("Received null response");
                return null;
            }
            return InstantSearchQueryResult.builder().withResults(instantSearchResponseQuery.getResults()).withQueryId(instantSearchResponseQuery.getQueryId()).build();
        } catch (Exception e) {
            LOGGER.error("Error while executing search suggestion call", (Throwable) e);
            throw new SearchSuggestionException(e);
        }
    }
}
