package com.amazon.music.social;

import com.amazon.musicrelationshipservice.model.EdgesRequest;
import com.amazon.musicrelationshipservice.model.EdgesResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface SocialService {
    EdgesResponse addEdges(EdgesRequest edgesRequest) throws VolleyError;

    EdgesResponse removeEdges(EdgesRequest edgesRequest) throws VolleyError;
}
