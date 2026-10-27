package com.amazon.mp3.config;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.service.metrics.InstallSource;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OtaUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.external.AssociateTagVendor;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.metrics.util.StringUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class AssociateTag implements AssociateTagVendor {
    private static final Map<String, String> COUNTRY_CODE_TO_CUSTOM_MARKET_CODE;
    private static final Set<String> EXCLUDED_COUNTRIES_FOR_MARKET_CODE;
    private static final Map<Marketplace, String> MARKETPLACE_TO_ASSOCIATE_REGION;
    private static final Map<Marketplace, String> MARKETPLACE_TO_MARKETPLACE_ID;
    private static final String TAG = "AssociateTag";
    private static volatile AssociateTag sInstance;
    private final Context mContext;
    private final DeeplinkAssociateTagStore mDeeplinkAssociateTagStore;
    private final MdipAssociateTagStore mMdipAssociateTagStore;
    private final PreloadedFileAssociateTagStore mPreloadedFileAssociateTagStore;

    static {
        HashMap map = new HashMap();
        MARKETPLACE_TO_ASSOCIATE_REGION = map;
        Marketplace marketplace = Marketplace.USA;
        map.put(marketplace, "20");
        map.put(Marketplace.UK, "21");
        Marketplace marketplace2 = Marketplace.GERMANY;
        map.put(marketplace2, "21");
        map.put(Marketplace.FRANCE, "21");
        map.put(Marketplace.SPAIN, "21");
        map.put(Marketplace.ITALY, "21");
        Marketplace marketplace3 = Marketplace.JAPAN;
        map.put(marketplace3, "22");
        Marketplace marketplace4 = Marketplace.ROW_NA;
        map.put(marketplace4, "20");
        Marketplace marketplace5 = Marketplace.ROE_EU;
        map.put(marketplace5, "21");
        Marketplace marketplace6 = Marketplace.ROW_FE;
        map.put(marketplace6, "22");
        HashMap map2 = new HashMap();
        MARKETPLACE_TO_MARKETPLACE_ID = map2;
        map2.put(marketplace4, marketplace.getCountryCode());
        map2.put(marketplace5, marketplace2.getCountryCode());
        map2.put(marketplace6, marketplace3.getCountryCode());
        HashMap map3 = new HashMap();
        COUNTRY_CODE_TO_CUSTOM_MARKET_CODE = map3;
        map3.put(MusicTerritory.BELGIUM.value, "fr-");
        map3.put(MusicTerritory.INDIA.value, "");
        map3.put(MusicTerritory.AUSTRALIA.value, "au-");
        HashSet hashSet = new HashSet();
        EXCLUDED_COUNTRIES_FOR_MARKET_CODE = hashSet;
        hashSet.add(MusicTerritory.MEXICO.value);
        hashSet.add(MusicTerritory.BRAZIL.value);
        hashSet.add(MusicTerritory.CANADA.value);
    }

    private AssociateTag(Context context) {
        this.mContext = context.getApplicationContext();
        this.mDeeplinkAssociateTagStore = new DeeplinkAssociateTagStore(context);
        this.mPreloadedFileAssociateTagStore = new PreloadedFileAssociateTagStore(context);
        this.mMdipAssociateTagStore = new MdipAssociateTagStore(context);
    }

    public static AssociateTag getInstance(Context context) {
        AssociateTag associateTag = sInstance;
        if (associateTag == null) {
            synchronized (AssociateTag.class) {
                associateTag = sInstance;
                if (associateTag == null) {
                    associateTag = new AssociateTag(context);
                    sInstance = associateTag;
                }
            }
        }
        return associateTag;
    }

    @Override // com.amazon.music.inappmessaging.external.AssociateTagVendor
    public String getAssociateTag() {
        if (associateTagFromDeeplinkExists()) {
            return getAssociateTagFromDeeplink();
        }
        if (associateTagFromMdipExists()) {
            return getAssociateTagFromMdip();
        }
        if (associateTagFromPreloadedFileExists()) {
            return getAssociateTagFromPreloadedFile();
        }
        if (appIsPreloaded()) {
            return getAssociateTagFromRemoteConfigOrDefault();
        }
        if (associateTagFromCampaignExists()) {
            return getAssociateTagFromCampaign();
        }
        return getAssociateTagFromRemoteConfigOrDefault();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004a A[PHI: r1
  0x004a: PHI (r1v1 java.lang.String) = (r1v0 java.lang.String), (r1v5 java.lang.String) binds: [B:3:0x0016, B:11:0x0030] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getAssociateTagRegionInformation() {
        /*
            r6 = this;
            android.content.Context r0 = r6.mContext
            com.amazon.mp3.account.details.AccountDetailUtil r0 = com.amazon.mp3.account.details.AccountDetailUtil.get(r0)
            com.amazon.music.marketplace.Marketplace r0 = r0.getHomeMarketPlace()
            java.lang.String r1 = r0.getCountryCode()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 45
            java.lang.String r4 = ""
            if (r2 == 0) goto L4a
            java.lang.String r1 = com.amazon.mp3.account.details.AccountDetailUtil.getMusicTerritoryOfResidence()
            if (r1 != 0) goto L1f
            return r4
        L1f:
            java.util.Map<java.lang.String, java.lang.String> r2 = com.amazon.mp3.config.AssociateTag.COUNTRY_CODE_TO_CUSTOM_MARKET_CODE
            java.lang.Object r2 = r2.get(r1)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L2a
            goto L4b
        L2a:
            java.util.Set<java.lang.String> r2 = com.amazon.mp3.config.AssociateTag.EXCLUDED_COUNTRIES_FOR_MARKET_CODE
            boolean r2 = r2.contains(r1)
            if (r2 != 0) goto L4a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.util.Map<com.amazon.music.marketplace.Marketplace, java.lang.String> r5 = com.amazon.mp3.config.AssociateTag.MARKETPLACE_TO_MARKETPLACE_ID
            java.lang.Object r5 = r5.get(r0)
            java.lang.String r5 = (java.lang.String) r5
            r2.append(r5)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            goto L4b
        L4a:
            r2 = r4
        L4b:
            java.util.Map<com.amazon.music.marketplace.Marketplace, java.lang.String> r5 = com.amazon.mp3.config.AssociateTag.MARKETPLACE_TO_ASSOCIATE_REGION
            java.lang.Object r0 = r5.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L57
            java.lang.String r0 = "20"
        L57:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r1)
            r5.append(r3)
            r5.append(r2)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "[^a-z0-9-]"
            java.lang.String r0 = r0.replaceAll(r1, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.config.AssociateTag.getAssociateTagRegionInformation():java.lang.String");
    }

    public void setAssociateTagFromDeeplink(Uri uri) {
        String queryParameter;
        if (uri == null || (queryParameter = uri.getQueryParameter(ParserUtil.TAG_QUERY_PARAM_NAME)) == null) {
            return;
        }
        Log.verbose(TAG, String.format("Storing associate tag from deeplink: %s", queryParameter));
        this.mDeeplinkAssociateTagStore.save(queryParameter);
    }

    private boolean associateTagFromDeeplinkExists() {
        return !StringUtil.isNullOrEmpty(this.mDeeplinkAssociateTagStore.loadOrDefault());
    }

    private String getAssociateTagFromDeeplink() {
        return this.mDeeplinkAssociateTagStore.loadOrDefault();
    }

    private boolean associateTagFromMdipExists() {
        return !StringUtil.isNullOrEmpty(this.mMdipAssociateTagStore.loadOrDefault());
    }

    private String getAssociateTagFromMdip() {
        return this.mMdipAssociateTagStore.loadOrDefault();
    }

    private boolean associateTagFromPreloadedFileExists() {
        return !StringUtil.isNullOrEmpty(getAssociateTagFromPreloadedFile());
    }

    protected String getAssociateTagFromPreloadedFile() {
        return this.mPreloadedFileAssociateTagStore.loadOrDefault();
    }

    private boolean appIsPreloaded() {
        return InstallSource.isAppPreloaded(this.mContext);
    }

    private String getAssociateTagFromRemoteConfigOrDefault() {
        if (!ConnectivityUtil.isWifiConnected() && OtaUtil.isEnabled()) {
            return AmazonApplication.getConfiguration(this.mContext).getString("associate_tag_ota", "android-ota-default");
        }
        return AmazonApplication.getConfiguration(this.mContext).getString("associate_tag_wifi", "android-wifi-default");
    }

    private boolean associateTagFromCampaignExists() {
        return !StringUtil.isNullOrEmpty(CampaignMetrics.loadCampaignReferrer(this.mContext).getSource());
    }

    private String getAssociateTagFromCampaign() {
        return CampaignMetrics.loadCampaignReferrer(this.mContext).getSource();
    }
}
