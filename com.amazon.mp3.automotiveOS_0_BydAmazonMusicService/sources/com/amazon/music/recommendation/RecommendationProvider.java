package com.amazon.music.recommendation;

import com.amazon.music.storeservice.model.BaseRecommendations;
import com.android.volley.VolleyError;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
interface RecommendationProvider {
    List<BaseRecommendations> getRecommendations(RecommendationRequest recommendationRequest) throws VolleyError;

    List<BaseRecommendations> getRecommendationsPage(RecommendationPageRequest recommendationPageRequest) throws VolleyError;
}
