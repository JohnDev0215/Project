package com.amazon.music.play.queue;

import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataRequest;
import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import com.amazon.musicplayqueueservice.client.common.RateEntityRequest;
import com.amazon.musicplayqueueservice.client.common.RateEntityResponse;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.GetTrackAndQueueMetadataCall;
import com.amazon.musicplayqueueservice.model.client.external.voiceenabled.RateEntityCall;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultMusicPlayQueueService implements MusicPlayQueueService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final MusicPlayQueueServiceConfig config;

    public DefaultMusicPlayQueueService(MusicPlayQueueServiceConfig musicPlayQueueServiceConfig) {
        this.config = musicPlayQueueServiceConfig;
    }

    @Override // com.amazon.music.play.queue.MusicPlayQueueService
    public GetTrackAndQueueMetadataResponse getTrackAndQueueMetadata(String str) throws VolleyError {
        Validate.notNull(str);
        GetTrackAndQueueMetadataRequest getTrackAndQueueMetadataRequest = new GetTrackAndQueueMetadataRequest();
        getTrackAndQueueMetadataRequest.setTrackId(str);
        return executeGetTrackAndQueueMetadataCall(new GetTrackAndQueueMetadataCall(this.config.getMusicPlayQueueServiceUrl(), getTrackAndQueueMetadataRequest, this.config.getRequestInterceptor()));
    }

    GetTrackAndQueueMetadataResponse executeGetTrackAndQueueMetadataCall(GetTrackAndQueueMetadataCall getTrackAndQueueMetadataCall) throws VolleyError {
        return getTrackAndQueueMetadataCall.execute(MAPPER);
    }

    @Override // com.amazon.music.play.queue.MusicPlayQueueService
    public RateEntityResponse rateEntity(String str, String str2, int i, long j) throws VolleyError {
        Validate.matchesPattern(str, "THUMBS_UP|THUMBS_DOWN|NEUTRAL");
        Validate.notBlank(str2);
        Validate.isTrue(i >= 0);
        Validate.isTrue(j >= 0);
        RateEntityRequest rateEntityRequest = new RateEntityRequest();
        rateEntityRequest.setRating(str);
        rateEntityRequest.setIdentifier(str2);
        rateEntityRequest.setDurationPlayedInMilliseconds(i);
        rateEntityRequest.setUtcTimeStamp(j);
        return executeRateEntityCall(new RateEntityCall(this.config.getMusicPlayQueueServiceUrl(), rateEntityRequest, this.config.getRequestInterceptor()));
    }

    RateEntityResponse executeRateEntityCall(RateEntityCall rateEntityCall) throws VolleyError {
        return rateEntityCall.execute(MAPPER);
    }
}
