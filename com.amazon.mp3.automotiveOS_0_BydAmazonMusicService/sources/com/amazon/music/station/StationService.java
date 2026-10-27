package com.amazon.music.station;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.storeservice.model.GetStationRecommendationsRequest;
import com.amazon.music.storeservice.model.GetStationRecommendationsResponse;
import com.amazon.musicensembleservice.GetStationRecommendationsCall;
import com.amazon.musicensembleservice.GetStationSectionsCall;
import com.amazon.musicensembleservice.stations.GetStationSectionsRequest;
import com.amazon.musicensembleservice.stations.GetStationSectionsResponse;
import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksRequest;
import com.amazon.musicplayqueueservice.client.common.GetNextTracksResponse;
import com.amazon.musicplayqueueservice.client.common.RateEntityRequest;
import com.amazon.musicplayqueueservice.model.client.external.nonvoiceenabled.CreateQueueCall;
import com.amazon.musicplayqueueservice.model.client.external.nonvoiceenabled.GetNextTracksCall;
import com.amazon.musicplayqueueservice.model.client.external.nonvoiceenabled.RateEntityCall;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class StationService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private Configuration configuration;
    private RetryPolicy retryPolicy = new DefaultRetryPolicy(20000, 1, 1.0f);

    public StationService(@NonNull Configuration configuration) {
        this.configuration = (Configuration) Validate.notNull(configuration);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public CreateQueueResponse startStation(CreateQueueRequest createQueueRequest) throws VolleyError {
        return new CreateQueueCall(getUrl(this.configuration.getStationURL(), ""), createQueueRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    public GetNextTracksResponse getTracksForStation(GetNextTracksRequest getNextTracksRequest) throws VolleyError {
        return new GetNextTracksCall(getUrl(this.configuration.getStationURL(), ""), getNextTracksRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    public GetStationSectionsResponse getStationSections(GetStationSectionsRequest getStationSectionsRequest) throws VolleyError {
        URL url = getUrl(this.configuration.getstationBrowseURL(), "stations");
        String musicTerritory = getConfiguration().getMusicTerritory();
        if (!StringUtils.isEmpty(musicTerritory)) {
            getStationSectionsRequest.setMusicTerritory(musicTerritory);
        } else {
            Marketplace marketplace = getConfiguration().getMarketplace();
            if (marketplace != null) {
                getStationSectionsRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getStationSectionsRequest.setRequestedContent(getConfiguration().getContentSelector().value);
        getStationSectionsRequest.setDeviceId(getConfiguration().getDeviceId());
        getStationSectionsRequest.setDeviceType(getConfiguration().getDeviceType());
        getStationSectionsRequest.setLang(getConfiguration().getLanguage());
        GetStationSectionsCall getStationSectionsCall = new GetStationSectionsCall(url, getStationSectionsRequest, this.configuration.getRequestInterceptor());
        RetryPolicy retryPolicy = this.retryPolicy;
        if (retryPolicy != null) {
            getStationSectionsCall.setRetryPolicy(retryPolicy);
        }
        return getStationSectionsCall.execute(MAPPER);
    }

    public void rateEntity(RateEntityRequest rateEntityRequest) throws VolleyError {
        new RateEntityCall(getUrl(this.configuration.getStationURL(), ""), rateEntityRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    public GetStationRecommendationsResponse getStationRecommendations(GetStationRecommendationsRequest getStationRecommendationsRequest) throws VolleyError {
        URL url = getUrl(this.configuration.getstationBrowseURL(), "stations");
        String musicTerritory = getConfiguration().getMusicTerritory();
        if (!StringUtils.isEmpty(musicTerritory)) {
            getStationRecommendationsRequest.setMusicTerritory(musicTerritory);
        } else {
            Marketplace marketplace = getConfiguration().getMarketplace();
            if (marketplace != null) {
                getStationRecommendationsRequest.setMarketplaceId(marketplace.getObfuscatedId());
            }
        }
        getStationRecommendationsRequest.setRequestedContent(getConfiguration().getContentSelector().value);
        getStationRecommendationsRequest.setDeviceId(getConfiguration().getDeviceId());
        getStationRecommendationsRequest.setDeviceType(getConfiguration().getDeviceType());
        getStationRecommendationsRequest.setLang(getConfiguration().getLanguage());
        return new GetStationRecommendationsCall(url, getStationRecommendationsRequest, this.configuration.getRequestInterceptor()).execute(MAPPER);
    }

    private final URL getUrl(URL url, String str) {
        try {
            return new URL(Uri.parse(url.toString()).buildUpon().appendEncodedPath(str).build().toString());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
