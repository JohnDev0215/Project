package com.amazon.music.recommendation;

import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsBySourceAsinsResponse;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsRequest;
import com.amazon.music.storeservice.model.GetBrowseRecommendationsResponse;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsRequest;
import com.amazon.music.storeservice.model.GetSimilarityRecommendationsResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface RecommendationService {
    GetBrowseRecommendationsResponse getBrowseRecommendation(GetBrowseRecommendationsRequest getBrowseRecommendationsRequest) throws VolleyError;

    GetBrowseRecommendationsBySourceAsinsResponse getBrowseRecommendationsBySourceAsins(GetBrowseRecommendationsBySourceAsinsRequest getBrowseRecommendationsBySourceAsinsRequest) throws VolleyError;

    GetSimilarityRecommendationsResponse getSimilarityRecommendation(GetSimilarityRecommendationsRequest getSimilarityRecommendationsRequest) throws VolleyError;
}
