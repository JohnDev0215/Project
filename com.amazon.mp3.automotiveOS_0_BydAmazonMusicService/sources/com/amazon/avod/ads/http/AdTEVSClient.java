package com.amazon.avod.ads.http;

import com.amazon.avod.ads.api.Beacon;
import com.amazon.avod.ads.api.TEVSClientDecorator;
import com.amazon.avod.ads.api.livetracking.LiveAdTracking;
import com.amazon.avod.ads.api.livetracking.LiveBeacons;
import com.amazon.avod.ads.config.AdsConfig;
import com.amazon.avod.http.Parser;
import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes2.dex */
public class AdTEVSClient {
    private final ServiceClient mServiceClient;
    private TEVSClientDecorator mTEVSClientDecorator;
    private final ResponseParser<LiveAdTracking> mResponseParser = new ResponseParser<>(LiveAdTracking.class);
    private final Set<String> mSupportedLiveAdTrackingEvents = AdsConfig.getInstance().getLiveSupportedAdTrackingEvents();

    public AdTEVSClient(@Nonnull ServiceClient serviceClient, @Nullable TEVSClientDecorator tEVSClientDecorator) {
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
        this.mTEVSClientDecorator = tEVSClientDecorator;
    }

    public void decorateTEVSRequest(@Nullable TEVSClientDecorator tEVSClientDecorator) {
        this.mTEVSClientDecorator = tEVSClientDecorator;
    }

    private LiveAdTracking getTrackingEvents(String str) throws MalformedURLException, BoltException, RequestBuildException {
        URL url = new URL(str);
        HashMap map = new HashMap();
        map.put("platform", "android");
        TEVSClientDecorator tEVSClientDecorator = this.mTEVSClientDecorator;
        if (tEVSClientDecorator != null) {
            map.put("advertisingId", String.valueOf(tEVSClientDecorator.getAdvertisingId()));
            map.put("optOut", String.valueOf(this.mTEVSClientDecorator.getUserOptOutSetting()));
            map.put("consumptionId", String.valueOf(this.mTEVSClientDecorator.getConsumptionId()));
            DLog.devf("adid = %s, UserOptOut = %b, consumptionId = %s", this.mTEVSClientDecorator.getAdvertisingId(), this.mTEVSClientDecorator.getUserOptOutSetting(), this.mTEVSClientDecorator.getConsumptionId());
        }
        return (LiveAdTracking) request(PlaybackHttpRequestBuilder.newBuilder().setOverriddenEndpoint(url.getProtocol() + "://" + url.getHost()).setUrlPath(url.getPath()).setUrlParamString(url.getQuery()).setUrlParamMap(map).suppressAtvUrlParams().setHttpMethod(Request.HttpMethod.GET).setAuthentication(null).setResponseParser(this.mResponseParser));
    }

    public List<Beacon> getBeaconList(String str, AdHttpClient adHttpClient) throws MalformedURLException, RequestBuildException, BoltException {
        try {
            DLog.devf("url = %s", str);
            return LiveBeacons.getLiveTimeTranslatedBeacons(getTrackingEvents(str), adHttpClient, this.mSupportedLiveAdTrackingEvents);
        } catch (RequestBuildException e) {
            DLog.exceptionf(e, "Invalid Request", new Object[0]);
            throw e;
        } catch (BoltException e2) {
            DLog.exceptionf(e2, "Server response exception", new Object[0]);
            throw e2;
        } catch (MalformedURLException e3) {
            DLog.exceptionf(e3, "Malformed Url", new Object[0]);
            throw e3;
        }
    }

    private <T> T request(PlaybackHttpRequestBuilder<T> playbackHttpRequestBuilder) throws BoltException, RequestBuildException {
        try {
            Request<T> requestBuild = playbackHttpRequestBuilder.build();
            DLog.devf("TEVSRequest=%s", requestBuild.getUrl().toString());
            Response<T> responseExecuteSync = this.mServiceClient.executeSync(requestBuild);
            if (responseExecuteSync.hasException()) {
                DLog.exceptionf(responseExecuteSync.getException(), "Response Exception", new Object[0]);
                throw responseExecuteSync.getException();
            }
            return responseExecuteSync.getValue();
        } catch (RequestBuildException e) {
            DLog.exceptionf(e, "Request Builder Exception", new Object[0]);
            throw e;
        }
    }

    public static class ResponseParser<T> implements Parser<T> {
        private final Class<T> mTClass;

        public ResponseParser(@Nonnull Class<T> cls) {
            this.mTClass = (Class) Preconditions.checkNotNull(cls);
        }

        @Override // com.amazon.avod.http.Parser
        public T parse(@Nonnull Request<T> request, @Nonnull Headers headers, @Nonnull byte[] bArr) throws Exception {
            return (T) JacksonCache.OBJECT_MAPPER.readValue(bArr, this.mTClass);
        }
    }
}
