package com.amazon.music.recommendation;

import com.amazon.hermes.CoralCall;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsResponse;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsResponse;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequest;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsResponse;
import com.amazon.musicensembleservice.GetBrowseRecommendationsBySourceAsinsCall;
import com.amazon.musicensembleservice.GetBrowseRecommendationsCall;
import com.amazon.musicensembleservice.GetSimilarityRecommendationsCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultRecommendationService implements RecommendationService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public DefaultRecommendationService(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override // com.amazon.music.recommendation.RecommendationService
    public GetBrowseRecommendationsResponse getBrowseRecommendation(GetBrowseRecommendationsRequest getBrowseRecommendationsRequest) throws VolleyError {
        GetBrowseRecommendationsCall getBrowseRecommendationsCall = new GetBrowseRecommendationsCall(this.configuration.getRecommendationUrl(), getBrowseRecommendationsRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getBrowseRecommendationsCall);
        return getBrowseRecommendationsCall.execute(MAPPER);
    }

    @Override // com.amazon.music.recommendation.RecommendationService
    public GetSimilarityRecommendationsResponse getSimilarityRecommendation(GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest) throws VolleyError {
        GetSimilarityRecommendationsCall getSimilarityRecommendationsCall = new GetSimilarityRecommendationsCall(this.configuration.getRecommendationUrl(), getSimilarityRecommendationsRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getSimilarityRecommendationsCall);
        return getSimilarityRecommendationsCall.execute(MAPPER);
    }

    @Override // com.amazon.music.recommendation.RecommendationService
    public GetBrowseRecommendationsBySourceAsinsResponse getBrowseRecommendationsBySourceAsins(GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest) throws VolleyError {
        GetBrowseRecommendationsBySourceAsinsCall getBrowseRecommendationsBySourceAsinsCall = new GetBrowseRecommendationsBySourceAsinsCall(this.configuration.getRecommendationUrl(), getBrowseRecommendationsBySourceAsinsRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getBrowseRecommendationsBySourceAsinsCall);
        return getBrowseRecommendationsBySourceAsinsCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
