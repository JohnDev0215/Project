package com.amazon.music.social;

import com.amazon.hermes.CoralCall;
import com.amazon.musicrelationshipservice.model.AddEdgesCall;
import com.amazon.musicrelationshipservice.model.EdgesRequest;
import com.amazon.musicrelationshipservice.model.EdgesResponse;
import com.amazon.musicrelationshipservice.model.RemoveEdgesCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
public class MusicRelationshipService implements SocialService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration configuration;
    private final ServiceEnvironment environment;
    private RetryPolicy mRetryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public MusicRelationshipService(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        this.configuration = configuration;
        this.environment = serviceEnvironment;
    }

    @Override // com.amazon.music.social.SocialService
    public EdgesResponse addEdges(EdgesRequest edgesRequest) throws VolleyError {
        AddEdgesCall addEdgesCall = new AddEdgesCall(SocialUrls.create(this.configuration, this.environment), edgesRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(addEdgesCall);
        return addEdgesCall.execute(MAPPER);
    }

    @Override // com.amazon.music.social.SocialService
    public EdgesResponse removeEdges(EdgesRequest edgesRequest) throws VolleyError {
        RemoveEdgesCall removeEdgesCall = new RemoveEdgesCall(SocialUrls.create(this.configuration, this.environment), edgesRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(removeEdgesCall);
        return removeEdgesCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.mRetryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
