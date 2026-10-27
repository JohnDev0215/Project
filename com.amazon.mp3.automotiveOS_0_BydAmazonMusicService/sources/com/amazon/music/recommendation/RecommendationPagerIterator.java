package com.amazon.music.recommendation;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.android.volley.VolleyError;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class RecommendationPagerIterator<T> implements PagerIterator<List<T>> {
    private Map<String, String> contentInfo;
    private boolean hasNext;
    private int nextPageToken;
    private final RecommendationProvider provider;
    private final RecommendationRequest request;
    private final RecommendationType type;
    private String widgetId;

    public RecommendationPagerIterator(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationType recommendationType) {
        this.nextPageToken = -1;
        this.hasNext = true;
        this.provider = recommendationProvider;
        this.request = recommendationRequest;
        this.type = recommendationType;
    }

    public RecommendationPagerIterator(RecommendationProvider recommendationProvider, RecommendationRequest recommendationRequest, RecommendationType recommendationType, RecommendationRequestPageOptions recommendationRequestPageOptions) {
        this(recommendationProvider, recommendationRequest, recommendationType);
        this.widgetId = recommendationRequestPageOptions.getWidgetId();
        this.nextPageToken = recommendationRequestPageOptions.getNextPageToken();
    }

    @Override // com.amazon.music.pager.PagerIterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // com.amazon.music.pager.PagerIterator
    public List<T> next() throws ExecutionException, NoSuchElementException {
        try {
            BaseRecommendations baseRecommendations = null;
            Iterator<BaseRecommendations> it = this.provider.getRecommendationsPage(new RecommendationPageRequest(this.request, this.nextPageToken, this.widgetId)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BaseRecommendations next = it.next();
                if (this.type == RecommendationType.fromString(next.getRecommendationType())) {
                    baseRecommendations = next;
                    break;
                }
            }
            if (baseRecommendations == null) {
                this.hasNext = false;
                return Collections.emptyList();
            }
            prepareNextRequest(baseRecommendations);
            return RecommendationUtils.fromRecommendations(baseRecommendations);
        } catch (VolleyError e) {
            throw new ExecutionException(new RecommendationException(e));
        }
    }

    private void prepareNextRequest(BaseRecommendations baseRecommendations) {
        Integer nextResultsToken = baseRecommendations.getNextResultsToken();
        if (nextResultsToken == null || nextResultsToken.intValue() == this.nextPageToken) {
            this.hasNext = false;
            return;
        }
        this.nextPageToken = nextResultsToken.intValue();
        this.widgetId = baseRecommendations.getWidgetId();
        Iterator<RecommendationInfo> it = RecommendationUtils.fromResponse(Collections.singletonList(baseRecommendations)).getRecommendationInfos().iterator();
        while (it.hasNext()) {
            Map<String, String> contentInfoMap = it.next().getContentInfoMap();
            if (contentInfoMap != null) {
                this.contentInfo = contentInfoMap;
            }
        }
    }
}
