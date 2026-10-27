package com.amazon.music.recents;

import com.amazon.hermes.CoralCall;
import com.amazon.nimblymusicservice.GetRecentActivityCall;
import com.amazon.nimblymusicservice.GetRecentActivityRequest;
import com.amazon.nimblymusicservice.GetRecentActivityResponse;
import com.amazon.nimblymusicservice.GetRecentTrackActivityCall;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequest;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponse;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackActivityCall;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequest;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
public class HttpRecentActivityService implements RecentActivityService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration configuration;
    private final ServiceEnvironment environment;
    private RetryPolicy mRetryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public HttpRecentActivityService(Configuration configuration, ServiceEnvironment serviceEnvironment) {
        this.configuration = configuration;
        this.environment = serviceEnvironment;
    }

    @Override // com.amazon.music.recents.RecentActivityService
    public GetRecentActivityResponse getRecentActivity(GetRecentActivityRequest getRecentActivityRequest) throws VolleyError {
        GetRecentActivityCall getRecentActivityCall = new GetRecentActivityCall(RecentServiceUrls.create(this.configuration, this.environment), getRecentActivityRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getRecentActivityCall);
        return getRecentActivityCall.execute(MAPPER);
    }

    @Override // com.amazon.music.recents.RecentActivityService
    public GetRecentTrackActivityResponse getRecentTrackActivity(GetRecentTrackActivityRequest getRecentTrackActivityRequest) throws VolleyError {
        GetRecentTrackActivityCall getRecentTrackActivityCall = new GetRecentTrackActivityCall(RecentServiceUrls.create(this.configuration, this.environment), getRecentTrackActivityRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getRecentTrackActivityCall);
        return getRecentTrackActivityCall.execute(MAPPER);
    }

    @Override // com.amazon.music.recents.RecentActivityService
    public GetRecentUnfilteredTrackResponse getRecentUnfilteredTrackActivity(GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest) throws VolleyError {
        GetRecentUnfilteredTrackActivityCall getRecentUnfilteredTrackActivityCall = new GetRecentUnfilteredTrackActivityCall(RecentServiceUrls.create(this.configuration, this.environment), getRecentUnfilteredTrackRequest, this.configuration.getRequestInterceptor());
        setRetryPolicy(getRecentUnfilteredTrackActivityCall);
        return getRecentUnfilteredTrackActivityCall.execute(MAPPER);
    }

    private void setRetryPolicy(CoralCall coralCall) {
        RetryPolicy retryPolicy = this.mRetryPolicy;
        if (retryPolicy != null) {
            coralCall.setRetryPolicy(retryPolicy);
        }
    }
}
