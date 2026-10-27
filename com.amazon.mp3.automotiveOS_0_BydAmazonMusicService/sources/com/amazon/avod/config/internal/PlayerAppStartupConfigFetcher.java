package com.amazon.avod.config.internal;

import com.amazon.avod.config.internal.PlayerAppStartupConfigResponse;
import com.amazon.avod.http.PlaybackHttpRequestBuilder;
import com.amazon.avod.http.RequestBuildException;
import com.amazon.avod.http.RequestPriority;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.json.ServiceResponseParser;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.IETFUtils;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PlayerAppStartupConfigFetcher {
    private final ServiceResponseParser<PlayerAppStartupConfigResponse> mParser;
    private final ServiceClient mServiceClient;

    PlayerAppStartupConfigFetcher() {
        this(ServiceClient.getInstance());
    }

    PlayerAppStartupConfigFetcher(@Nonnull ServiceClient serviceClient) {
        this.mParser = new ServiceResponseParser<PlayerAppStartupConfigResponse>(new PlayerAppStartupConfigResponse.Parser()) { // from class: com.amazon.avod.config.internal.PlayerAppStartupConfigFetcher.1
            @Override // com.amazon.avod.json.ServiceResponseParser
            @Nonnull
            public String getSaveFilename(@Nonnull Request<PlayerAppStartupConfigResponse> request) {
                return "playerAppStartupConfig";
            }
        };
        this.mServiceClient = (ServiceClient) Preconditions.checkNotNull(serviceClient, "serviceClient");
    }

    @Nonnull
    public PlayerAppStartupConfigResponse fetch() throws BoltException, RequestBuildException {
        ImmutableSet immutableSetBuild = ImmutableSet.builder().add(Locale.getDefault()).build();
        Response responseExecuteSync = this.mServiceClient.executeSync(PlaybackHttpRequestBuilder.newBuilder().setUrlPath("/cdp/usage/v3/GetAppStartupConfig").setUrlParamMap(ImmutableMap.builder().put("version", "1").put("supportedLocales", IETFUtils.toAmazonLocaleStringCommaSeparated(immutableSetBuild)).putAll(ImmutableMap.of("customer", "true", "device", "true")).build()).suppressAcceptLanguageHeader().setResponseParser(this.mParser).setAuthentication(ImmutableMap.of()).setRequestPriority(RequestPriority.CRITICAL).build());
        if (responseExecuteSync.hasException()) {
            throw responseExecuteSync.getException();
        }
        DLog.logf("Successfully parsed player app startup config.");
        return (PlayerAppStartupConfigResponse) responseExecuteSync.getValue();
    }
}
