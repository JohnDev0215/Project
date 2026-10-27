package com.amazon.mp3.net.dmls;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.capability.PlatformNameProvider;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.UserAgent;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.dmls.model.ContentType;
import com.amazon.mp3.net.dmls.model.CustomerInfo;
import com.amazon.mp3.net.dmls.model.DrmType;
import com.amazon.mp3.net.dmls.model.Entitlement;
import com.amazon.mp3.net.dmls.model.Format;
import com.amazon.mp3.net.dmls.model.Protocol;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultDMLSApi implements DMLSApi {
    private static final String TAG = "DefaultDMLSApi";
    private static DefaultDMLSApi sInstance;
    private int mRetry = 0;

    static DefaultDMLSApi getInstance() {
        if (sInstance == null) {
            sInstance = new DefaultDMLSApi();
        }
        return sInstance;
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public ContentResponse retrieveGetStreamUrlResponse(ContentId contentId) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(contentId);
        ContentResponse contentResponse = retrieveGetStreamUrlResponses(arrayList).get(0);
        DMLSExceptions.validateContentResponse(contentResponse);
        return contentResponse;
    }

    public List<ContentResponse> retrieveGetStreamUrlResponses(final List<ContentId> contentIds) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        return retrieveGetStreamUrlResponses(createGetStreamUrlsRequest(contentIds), LocatorRequest.GetStreamingUrls, StreamingBitrate.fromEntryValueForDMLS(SettingsUtil.getMultiBitrateStreamingPreferenceForDMLS(AmazonApplication.getContext())));
    }

    protected JSONObject createGetStreamUrlsRequest(final List<ContentId> contentIds) throws JSONException {
        return createGetStreamUrlsBaseRequest(contentIds).put("bitRate", SettingsUtil.getMultiBitrateStreamingPreferenceForDMLS(AmazonApplication.getContext()));
    }

    protected JSONObject createGetStreamUrlsBaseRequest(final List<ContentId> contentIds) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("contentIdList", createContentIdJSONArray(contentIds)).put("deviceToken", createDeviceToken()).put("appMetadata", createAppMetadata()).put("clientMetadata", createClientMetadata());
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            jSONObjectPut.put("tryAsinSubstitution", true);
        }
        return jSONObjectPut;
    }

    protected JSONObject createDeviceToken() throws JSONException {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get();
        return new JSONObject().put("deviceTypeId", accountCredentialStorage.getDeviceType()).put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
    }

    protected JSONArray createContentIdJSONArray(List<ContentId> contentIds) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (ContentId contentId : contentIds) {
            jSONArray.put(new JSONObject().put("identifier", contentId.getId()).put("identifierType", contentId.getType()).put("uniqueId", "foo"));
        }
        return jSONArray;
    }

    protected JSONObject createAppMetadata() throws JSONException {
        return new JSONObject().put("appId", "MP3").put(Splash.PARAMS_APP_VERSION, ApplicationAttributes.getInstance(AmazonApplication.getContext()).getVersion()).put("userAgent", UserAgent.getUserAgent()).put("https", "true");
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public IStreamingConcurrencyStatusResponse updateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        if (AmazonApplication.onMainThread()) {
            Log.error(TAG, "updateStreamingStatus call on main thread! This should be fixed PRONTO!");
        }
        try {
            return StreamingConcurrencyStatusResponse.createFromJSON(LocatorRequest.UpdateStreamingStatus.execute(createStreamingConcurrencyRequestParameters(contentId, status, contentDuration, currentPlayTime, null)));
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.warning(TAG, "HTTP Client unable to complete request: %s", e.toString());
            throw e;
        } catch (DMLSExceptions.StatusCodeException e2) {
            Log.warning(TAG, "StatusCode Exception during request: %s", e2.toString());
            throw e2;
        } catch (DMLSExceptions.DMLSException e3) {
            Log.warning(TAG, "DMLS Exception during request: %s", e3.toString());
            throw e3;
        } catch (JSONException e4) {
            Log.warning(TAG, "JSON Exception during request: %s", e4.toString());
            throw e4;
        }
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public IStreamingConcurrencyStatusResponse terminateAndUpdateStreamingStatus(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime, String oldDeviceName) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        if (AmazonApplication.onMainThread()) {
            Log.error(TAG, "terminateAndUpdateStreamingStatus call on main thread! This should be fixed PRONTO!");
        }
        try {
            return StreamingConcurrencyStatusResponse.createFromJSON(LocatorRequest.TerminateAndUpdateStreamingStatus.execute(createStreamingConcurrencyRequestParameters(contentId, status, contentDuration, currentPlayTime, oldDeviceName)));
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.warning(TAG, "HTTP Client unable to complete request: %s", e.toString());
            throw e;
        } catch (DMLSExceptions.DMLSException e2) {
            Log.warning(TAG, "DMLS Exception during request: %s", e2.toString());
            throw e2;
        } catch (JSONException e3) {
            Log.warning(TAG, "JSON Exception during request: %s", e3.toString());
            throw e3;
        }
    }

    protected JSONObject createClientMetadata() throws JSONException {
        return new JSONObject().put("clientId", PlatformNameProvider.getPlatformNameForDMLSRequests());
    }

    protected JSONObject createStreamingConcurrencyRequestParameters(ContentId contentId, StreamingStatus status, long contentDuration, long currentPlayTime, String oldDeviceName) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("contentID", contentId.toJSON()).put("streamingStatus", status.toString()).put("contentDuration", contentDuration).put("currentPlayTime", currentPlayTime).put("deviceToken", createDeviceToken()).put("clientMetadata", createClientMetadata());
        if (oldDeviceName != null) {
            jSONObjectPut.put("oldDeviceName", oldDeviceName);
        }
        return jSONObjectPut;
    }

    private List<ContentResponse> retrieveGetStreamUrlResponses(final JSONObject requestParams, final LocatorRequest request, final StreamingBitrate bitrate) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        ArrayList arrayList = new ArrayList();
        try {
            this.mRetry = 0;
            JSONArray jSONArray = request.execute(requestParams).getJSONArray("contentResponseList");
            boolean zEquals = LocatorRequest.GetStreamingURLsWithFirstChunkV2.equals(request);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(ContentResponse.fromJSON(bitrate, jSONArray.getJSONObject(i), false, zEquals));
            }
            return arrayList;
        } catch (AbstractHttpClient.HttpClientException e) {
            String str = TAG;
            Log.warning(str, "HTTP Client unable to complete request", e);
            if (!ConnectivityUtil.hasAnyInternetConnection()) {
                throw new AbstractHttpClient.HttpClientException();
            }
            int i2 = this.mRetry;
            this.mRetry = i2 + 1;
            if (i2 < 3) {
                try {
                    Thread.sleep(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                } catch (InterruptedException unused) {
                    Log.error(TAG, "HttpClientException: interrupted while retrying, failing...");
                }
                Log.info(TAG, "HttpClientException: retrying...");
                return retrieveGetStreamUrlResponses(requestParams, request, bitrate);
            }
            Log.error(str, "HttpClientException: reached max of retries, failing...");
            throw e;
        } catch (JSONException e2) {
            Log.warning(TAG, "JSON Exception during request", e2);
            throw e2;
        }
    }

    protected JSONObject createGetStreamingURLsWithFirstChunkV2(final List<ContentId> contentIds, final BitRateSelection bitRateSelection, final boolean includeSpatial) throws JSONException {
        return createGetStreamUrlsBaseRequest(contentIds).put("protocol", Protocol.DASH).put("drmType", DrmType.WIDEVINE).put("format", Format.ENCRYPTED_OPUS_FLAC).put("customerInfo", createCustomerInfo()).put("customerId", AccountDetailUtil.get().getCustomerId()).put("contentTypeList", createContentTypeList(bitRateSelection, includeSpatial));
    }

    @Override // com.amazon.mp3.net.dmls.DMLSApi
    public List<ContentResponse> retrieveStreamingURLsWithFirstChunkV2(final List<ContentId> contentIds, final BitRateSelection bitRateSelection, final boolean includeSpatial) throws JSONException, AbstractHttpClient.HttpClientException, DMLSExceptions.DMLSException {
        return retrieveGetStreamUrlResponses(createGetStreamingURLsWithFirstChunkV2(contentIds, bitRateSelection, includeSpatial), LocatorRequest.GetStreamingURLsWithFirstChunkV2, StreamingBitrate.fromBitRateSelection(bitRateSelection));
    }

    private List<Entitlement> createEntitlementList() {
        ArrayList arrayList = new ArrayList();
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        if (userSubscription.isNightwingOnly()) {
            arrayList.add(Entitlement.NIGHTWING);
        }
        if (userSubscription.isPrime()) {
            arrayList.add(Entitlement.ROBIN);
        }
        if (userSubscription.isHawkfireAllDevices()) {
            arrayList.add(Entitlement.HAWKFIRE);
        }
        if (userSubscription.isKatana()) {
            arrayList.add(Entitlement.KATANA);
        }
        if (userSubscription.isSonicRush()) {
            arrayList.add(Entitlement.SONIC_RUSH);
        }
        return arrayList;
    }

    private JSONObject createCustomerInfo() throws JSONException {
        return CustomerInfo.builder().withEntitlementList(createEntitlementList()).withMarketplaceId(AccountDetailUtil.get().getHomeMarketPlaceId()).withTerritoryId(AccountDetailUtil.getMusicTerritoryOfResidence()).build().toJSONObject();
    }

    private JSONArray createContentTypeList(final BitRateSelection bitRateSelection, final boolean includeSpatial) {
        JSONArray jSONArray = new JSONArray();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[bitRateSelection.ordinal()];
        if (i == 1) {
            jSONArray.put(ContentType.HI_FI).put(ContentType.HI_RES);
        } else if (i == 2) {
            jSONArray.put(ContentType.HI_FI).put(ContentType.HI_RES).put(ContentType.STD_RES);
        } else {
            jSONArray.put(ContentType.STD_RES);
        }
        if (includeSpatial) {
            jSONArray.put(ContentType.THREE_DIMENSIONAL).put(ContentType.REAL_AUDIO_360_MHM1);
        }
        return jSONArray;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.net.dmls.DefaultDMLSApi$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection;

        static {
            int[] iArr = new int[BitRateSelection.values().length];
            $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection = iArr;
            try {
                iArr[BitRateSelection.KATANA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$config$BitRateSelection[BitRateSelection.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
