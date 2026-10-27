package com.amazon.music.recommendation;

import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequest;
import com.android.volley.VolleyError;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseRecommendationsBySourceAsinsProvider implements RecommendationProvider {
    private final Locale locale;
    private final Marketplace marketplace;
    private final String musicTerritory;
    private final RecommendationService service;

    private boolean isInitialRequest(int i, String str) {
        return i < 0 && str == null;
    }

    public BrowseRecommendationsBySourceAsinsProvider(RecommendationService recommendationService, Locale locale, Marketplace marketplace, String str) {
        this.service = recommendationService;
        this.locale = locale;
        this.marketplace = marketplace;
        this.musicTerritory = str;
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendations(RecommendationRequest recommendationRequest) throws VolleyError {
        return this.service.getBrowseRecommendationsBySourceAsins(createRequest((BrowseRecommendationsBySourceAsinsRequest) recommendationRequest)).getRecommendations();
    }

    @Override // com.amazon.music.recommendation.RecommendationProvider
    public List<BaseRecommendations> getRecommendationsPage(RecommendationPageRequest recommendationPageRequest) throws VolleyError {
        GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequestCreateRequest = createRequest((BrowseRecommendationsBySourceAsinsRequest) recommendationPageRequest.getRequest());
        int nextPageToken = recommendationPageRequest.getNextPageToken();
        String widgetId = recommendationPageRequest.getWidgetId();
        if (!isInitialRequest(nextPageToken, widgetId)) {
            getBrowseRecommendationsBySourceAsinsRequestCreateRequest.setWidgetIdTokenMap(Collections.singletonMap(widgetId, Integer.valueOf(nextPageToken)));
        }
        return this.service.getBrowseRecommendationsBySourceAsins(getBrowseRecommendationsBySourceAsinsRequestCreateRequest).getRecommendations();
    }

    private GetBrowseRecommendationsBySourceAsinsRequest createRequest(BrowseRecommendationsBySourceAsinsRequest browseRecommendationsBySourceAsinsRequest) {
        GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest = new GetBrowseRecommendationsBySourceAsinsRequest();
        if (!StringUtils.isEmpty(this.musicTerritory)) {
            getBrowseRecommendationsBySourceAsinsRequest.setMusicTerritory(this.musicTerritory);
        } else {
            Marketplace marketplace = this.marketplace;
            if (marketplace != null) {
                getBrowseRecommendationsBySourceAsinsRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getBrowseRecommendationsBySourceAsinsRequest.setMinResultsPerWidget(0);
        getBrowseRecommendationsBySourceAsinsRequest.setMaxResultsPerWidget(Integer.valueOf(browseRecommendationsBySourceAsinsRequest.getMaxCount()));
        getBrowseRecommendationsBySourceAsinsRequest.setLang(this.locale.getLanguage());
        getBrowseRecommendationsBySourceAsinsRequest.setAsins(browseRecommendationsBySourceAsinsRequest.getSourceAsins());
        getBrowseRecommendationsBySourceAsinsRequest.setRequestedContent(browseRecommendationsBySourceAsinsRequest.getRequestedContent());
        getBrowseRecommendationsBySourceAsinsRequest.setDeviceType(browseRecommendationsBySourceAsinsRequest.getDeviceType());
        getBrowseRecommendationsBySourceAsinsRequest.setDeviceId(browseRecommendationsBySourceAsinsRequest.getDeviceId());
        getBrowseRecommendationsBySourceAsinsRequest.setAllowedParentalControls(Configuration.getParentalControls(browseRecommendationsBySourceAsinsRequest.isExplicitFilterEnabled()));
        getBrowseRecommendationsBySourceAsinsRequest.setMusicRequestIdentityContextToken(browseRecommendationsBySourceAsinsRequest.getMusicRequestIdentityContextToken());
        return getBrowseRecommendationsBySourceAsinsRequest;
    }
}
