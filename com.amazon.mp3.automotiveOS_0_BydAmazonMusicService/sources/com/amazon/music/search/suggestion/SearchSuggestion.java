package com.amazon.music.search.suggestion;

import com.amazon.tenzing.textsearch.v1_1.SuggestRequest;
import com.amazon.tenzing.textsearch.v1_1.SuggestResponse;
import com.amazon.tenzing.textsearch.v1_1.Suggestion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class SearchSuggestion {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchSuggestion.class.getSimpleName());
    private static final String TAG = "SearchSuggestion";
    private final int maxResults;
    private final SearchSuggestionService searchSuggestionService;

    public SearchSuggestion(SearchSuggestionService searchSuggestionService, int i) {
        Validate.notNull(searchSuggestionService, "SearchSuggestionService cannot be null", new Object[0]);
        Validate.isTrue(i >= 1, "Max results cannot be less than 1", new Object[0]);
        this.searchSuggestionService = searchSuggestionService;
        this.maxResults = i;
    }

    public List<String> query(String str) throws SearchSuggestionException {
        if (str == null || StringUtils.isBlank(str)) {
            LOGGER.error("The query passed is null or empty. Returning null");
            return null;
        }
        SuggestRequest suggestRequest = new SuggestRequest();
        suggestRequest.setQuery(str);
        suggestRequest.setSize(Integer.valueOf(this.maxResults));
        try {
            SuggestResponse suggestResponseQuery = this.searchSuggestionService.query(suggestRequest);
            if (suggestResponseQuery == null) {
                LOGGER.debug(TAG, "Received null response");
                return null;
            }
            List<Suggestion> suggestions = suggestResponseQuery.getSuggestions();
            if (suggestions != null && !suggestions.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<Suggestion> it = suggestions.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getTerm());
                }
                return arrayList;
            }
            LOGGER.debug(TAG, "Received null/empty suggestions in response");
            return null;
        } catch (Exception e) {
            LOGGER.error(TAG, "Error while executing search suggestion call", e);
            throw new SearchSuggestionException(e);
        }
    }
}
