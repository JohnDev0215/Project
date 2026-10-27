package com.amazon.avod.media.service.playbackoptimizationservice;

import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.RequestPriority;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.json.JacksonCache;
import com.amazon.avod.json.ServiceResponseParser;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playbackresource.PlaybackSettingsTransformer;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class DefaultHeuristicsSettingsFetcher {
    private final HeuristicsConfigurationValidator mHeuristicsConfigurationValidator;
    private final ServiceClient mHttpServiceClient;
    private final PlaybackEventReporter mPlaybackEventReporter;
    private final PlaybackSettingsTransformer mPlaybackSettingsTransformer;
    private final ServiceResponseParser<PlaybackSettings> mSettingsResponseParser;

    DefaultHeuristicsSettingsFetcher(@Nonnull PlaybackEventReporter playbackEventReporter) {
        this(ServiceClient.getInstance(), new PlaybackSettingsTransformer(playbackEventReporter), new HeuristicsConfigurationValidator(), playbackEventReporter);
    }

    DefaultHeuristicsSettingsFetcher(@Nonnull ServiceClient serviceClient, @Nonnull PlaybackSettingsTransformer playbackSettingsTransformer, @Nonnull HeuristicsConfigurationValidator heuristicsConfigurationValidator, @Nonnull PlaybackEventReporter playbackEventReporter) {
        this.mSettingsResponseParser = new ServiceResponseParser<PlaybackSettings>(new PlaybackSettings.Parser(JacksonCache.OBJECT_MAPPER)) { // from class: com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsSettingsFetcher.1
            @Override // com.amazon.avod.json.ServiceResponseParser
            @Nonnull
            public String getSaveFilename(@Nonnull Request<PlaybackSettings> request) {
                return "GetDefaultSettings.json";
            }
        };
        this.mHttpServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "httpServiceClient");
        this.mPlaybackSettingsTransformer = (PlaybackSettingsTransformer) Preconditions.checkNotNull(playbackSettingsTransformer, "playbackSettingsTransformer");
        this.mHeuristicsConfigurationValidator = (HeuristicsConfigurationValidator) Preconditions.checkNotNull(heuristicsConfigurationValidator, "validator");
        this.mPlaybackEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "playbackEventReporter");
    }

    @Nullable
    public Map<String, String> getPlaybackSettings() {
        try {
            HashMap mapNewHashMap = Maps.newHashMap();
            mapNewHashMap.put(HttpHeader.ACCEPT, Optional.of("application/json"));
            HashMap mapNewHashMap2 = Maps.newHashMap();
            mapNewHashMap2.put("responseFormatVersion", "1.0.0");
            Response responseExecuteSync = this.mHttpServiceClient.executeSync(PlaybackHttpRequestBuilder.newBuilder().setResponseParser(this.mSettingsResponseParser).setUrlPath("/cdp/playback/GetDefaultSettings").setHttpMethod(Request.HttpMethod.GET).setHeaders(mapNewHashMap).setUrlParamMap(mapNewHashMap2).setRequestPriority(RequestPriority.CRITICAL).setAuthentication(Collections.emptyMap()).build());
            if (responseExecuteSync.hasException()) {
                throw responseExecuteSync.getException();
            }
            if (responseExecuteSync.getValue() == null) {
                return Collections.emptyMap();
            }
            Map<String, String> mapTransform = this.mPlaybackSettingsTransformer.transform((PlaybackSettings) responseExecuteSync.getValue());
            this.mHeuristicsConfigurationValidator.validate(mapTransform);
            return mapTransform;
        } catch (RequestBuildException e) {
            e = e;
            this.mPlaybackEventReporter.reportError("SettingsNetworkError", e.getMessage(), null);
            DLog.errorf("Sending QOS error for exception: %s", e);
            return null;
        } catch (InvalidConfigurationException e2) {
            String message = e2.getMessage();
            this.mPlaybackEventReporter.reportError("SettingsParseError", message, null);
            DLog.errorf("Sending QOS error for exception: %s", e2);
            QALog.newQALog(PlaybackQAEvent.PLAYBACK_HEURISTICS_SETTINGS_PARSE_ERROR).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.ERROR_MESSAGE, message).send();
            return null;
        } catch (BoltException e3) {
            e = e3;
            this.mPlaybackEventReporter.reportError("SettingsNetworkError", e.getMessage(), null);
            DLog.errorf("Sending QOS error for exception: %s", e);
            return null;
        }
    }
}
