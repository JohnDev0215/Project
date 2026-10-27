package com.amazon.mp3.web.webtargetbuilder;

import android.net.Uri;
import com.amazon.mp3.account.AccountInfo;
import com.amazon.mp3.account.DeviceAttributes;
import com.amazon.mp3.account.MarketplaceCategory;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.web.webtarget.BasicWebTarget;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class BaseWebTargetBuilder implements WebTargetBuilder {
    private static final List<String> RESERVED_QUERY_PARAMETER_NAMES;
    protected final AccountInfo accountInfo;
    protected final DeviceAttributes deviceAttributes;
    protected final WebTargetEndpoints endpoints;
    protected Map<String, String> paramMap;
    protected final String renderingType;
    protected Uri.Builder uriBuilder;

    static {
        ArrayList arrayList = new ArrayList();
        RESERVED_QUERY_PARAMETER_NAMES = arrayList;
        arrayList.add(ParserUtil.TAG_QUERY_PARAM_NAME);
    }

    protected BaseWebTargetBuilder(final AccountInfo accountInfo, final WebTargetEndpoints endpoints, final DeviceAttributes deviceAttributes) {
        this.accountInfo = accountInfo;
        this.endpoints = endpoints;
        this.deviceAttributes = deviceAttributes;
        this.renderingType = BrowseFactory.isTabletDevice() ? "tablet" : "mobile";
        Uri uri = Uri.parse(getDomainForMarketplaceType());
        this.paramMap = new HashMap();
        this.uriBuilder = uri.buildUpon();
        addToParamMap(getRequiredParams());
    }

    private String getDomainForMarketplaceType() {
        MarketplaceCategory marketplaceCategory = this.accountInfo.getMarketplaceCategory();
        if (marketplaceCategory == MarketplaceCategory.ROW_PRIME_ONLY) {
            return this.endpoints.getRowPrimeOnlyWebEndpoint();
        }
        if (marketplaceCategory == MarketplaceCategory.ROW) {
            return this.endpoints.getApexRowMusicWebEndpoint();
        }
        return this.endpoints.getDefaultWebEndpoint();
    }

    private Map<String, String> getRequiredParams() {
        HashMap map = new HashMap();
        map.put(Splash.PARAMS_DEVICE_TYPE, this.deviceAttributes.getDeviceType());
        map.put(Splash.PARAMS_DEVICE_ID, this.deviceAttributes.getDeviceId());
        map.put(Splash.PARAMS_APP_VERSION, this.deviceAttributes.getAppVersion());
        map.put(ParserUtil.TAG_QUERY_PARAM_NAME, this.deviceAttributes.getPreloadAssociateTagOrDefault());
        map.put("renderingType", this.renderingType);
        return map;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTargetBuilder withQueryParamsToForward(final Map<String, String> queryParameters) {
        removeReservedQueryParameters(queryParameters);
        addToParamMap(queryParameters);
        return this;
    }

    private void removeReservedQueryParameters(final Map<String, String> parameterMap) {
        if (parameterMap.get("persist_tag") != null) {
            String str = parameterMap.get("persist_tag");
            Objects.requireNonNull(str);
            if (str.equalsIgnoreCase("true")) {
                return;
            }
        }
        for (String str2 : RESERVED_QUERY_PARAMETER_NAMES) {
            if (parameterMap.get(str2) != null) {
                parameterMap.remove(str2);
            }
        }
    }

    private void addToParamMap(final Map<String, String> queryParameters) {
        this.paramMap.putAll(queryParameters);
    }

    protected Uri.Builder addQueryParameters(final Map<String, String> queryParameters) {
        for (String str : queryParameters.keySet()) {
            this.uriBuilder.appendQueryParameter(str, queryParameters.get(str));
        }
        return this.uriBuilder;
    }

    @Override // com.amazon.mp3.web.webtargetbuilder.WebTargetBuilder
    public WebTarget build() {
        Uri.Builder builderAddQueryParameters = addQueryParameters(this.paramMap);
        this.uriBuilder = builderAddQueryParameters;
        return new BasicWebTarget(builderAddQueryParameters.build().toString());
    }
}
