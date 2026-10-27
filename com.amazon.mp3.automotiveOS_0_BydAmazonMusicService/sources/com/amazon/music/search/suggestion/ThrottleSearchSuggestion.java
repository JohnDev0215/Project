package com.amazon.music.search.suggestion;

import com.amazon.music.search.suggestion.SearchSuggestionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ThrottleSearchSuggestion {
    private static final ScheduledThreadPoolExecutor THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1);
    private OnSuggestionReadyListener onSuggestionReadyListener;
    final long queryDelayMilliSeconds;
    private ScheduledFuture scheduledFuture;
    private final SearchSuggestion searchSuggestion;

    ThrottleSearchSuggestion(SearchSuggestion searchSuggestion, long j) {
        Validate.notNull(searchSuggestion, "SearchSuggestion cannot be null", new Object[0]);
        this.searchSuggestion = searchSuggestion;
        this.queryDelayMilliSeconds = j;
    }

    public void query(String str) throws SearchSuggestionException {
        ScheduledFuture scheduledFuture = this.scheduledFuture;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            this.scheduledFuture.cancel(false);
        }
        OnSuggestionReadyListener onSuggestionReadyListener = this.onSuggestionReadyListener;
        if (onSuggestionReadyListener == null) {
            throw new SearchSuggestionException(SearchSuggestionException.Reason.LISTENER_NOT_REGISTERED);
        }
        this.scheduledFuture = THREAD_POOL_EXECUTOR.schedule(new ThrottleQueryTask(this.searchSuggestion, str, onSuggestionReadyListener), this.queryDelayMilliSeconds, TimeUnit.MICROSECONDS);
    }

    public void setListener(OnSuggestionReadyListener onSuggestionReadyListener) {
        this.onSuggestionReadyListener = onSuggestionReadyListener;
    }

    public static Builder builder(SearchSuggestionService searchSuggestionService, int i) {
        Validate.notNull(searchSuggestionService, "SearchSuggestionService cannot be null", new Object[0]);
        Validate.isTrue(i >= 1, "Max results cannot be less than 1", new Object[0]);
        return new Builder(searchSuggestionService, i);
    }

    public static Builder builder(SearchSuggestionService searchSuggestionService) {
        return builder(searchSuggestionService, 10);
    }

    public static final class Builder {
        private final int maxResults;
        private long queryDelayMilliSeconds;
        private final SearchSuggestionService service;

        private Builder(SearchSuggestionService searchSuggestionService, int i) {
            this.queryDelayMilliSeconds = 200L;
            Validate.notNull(searchSuggestionService, "SearchSuggestionService cannot be null", new Object[0]);
            Validate.isTrue(i >= 1, "Max results cannot be less than 1", new Object[0]);
            this.service = searchSuggestionService;
            this.maxResults = i;
        }

        int getMaxResults() {
            return this.maxResults;
        }

        public ThrottleSearchSuggestion build() {
            return new ThrottleSearchSuggestion(new SearchSuggestion(this.service, this.maxResults), this.queryDelayMilliSeconds);
        }
    }
}
