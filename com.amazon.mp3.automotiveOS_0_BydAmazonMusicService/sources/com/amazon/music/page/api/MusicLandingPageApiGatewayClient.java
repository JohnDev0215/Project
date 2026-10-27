package com.amazon.music.page.api;

import android.text.TextUtils;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.page.api.model.AutomotiveServicePageRequest;
import com.amazon.music.page.api.model.BasePageRequest;
import com.amazon.music.page.api.model.PageRequest;
import com.amazon.music.page.api.model.PageResponse;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.android.volley.Cache;
import com.android.volley.CacheStateManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MusicLandingPageApiGatewayClient {
    protected static final Long CACHE_TTL = 1800000L;
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) MusicLandingPageApiGatewayClient.class);
    private final String CATEGORY;
    private final String CATEGORY_CLIENT;
    private final String HD;
    private final String HD_CLIENT;
    private final AuthenticationProvider authenticationProvider;
    private final Cache cache;
    private final CacheStateManager cacheStateManager;
    private final HashMap<String, CategoryLandingPageApiGatewayClient> categoryClients;
    private final ClientConfiguration config;
    private final ApiClientFactory factory;
    private final HashMap<String, GenreLandingPageApiGatewayClient> genreClients;
    private final HashMap<String, HdLandingPageApiGatewayClient> hdClients;
    private String pathUri;
    private String rootPageUri;
    private String widgetId;

    public MusicLandingPageApiGatewayClient(AuthenticationProvider authenticationProvider, Cache cache, CacheStateManager cacheStateManager) {
        ClientConfiguration clientConfigurationWithEnableGzip = new ClientConfiguration().withEnableGzip(true);
        this.config = clientConfigurationWithEnableGzip;
        this.factory = new ApiClientFactory().clientConfiguration(clientConfigurationWithEnableGzip);
        this.genreClients = new HashMap<>();
        this.hdClients = new HashMap<>();
        this.categoryClients = new HashMap<>();
        this.HD = "hd";
        this.CATEGORY = "category";
        this.HD_CLIENT = "hdClient";
        this.CATEGORY_CLIENT = "categoryClient";
        this.authenticationProvider = authenticationProvider;
        this.cache = cache;
        this.cacheStateManager = cacheStateManager;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public PageResponse get(BasePageRequest basePageRequest, String str) {
        String cacheKey = getCacheKey(basePageRequest, str);
        PageResponse pageResponseFetchFromCache = fetchFromCache(cacheKey);
        if (pageResponseFetchFromCache != null) {
            LOG.debug("Get page response from API gateway cache for page request " + basePageRequest.getUri());
            return pageResponseFetchFromCache;
        }
        PageResponse pageResponseGenreLandingPage = null;
        parseUri(basePageRequest.getUri());
        String str2 = this.rootPageUri;
        str2.hashCode();
        byte b = -1;
        switch (str2.hashCode()) {
            case -1865828127:
                if (str2.equals("playlists")) {
                    b = 0;
                }
                break;
            case -1312261771:
                if (str2.equals("carmode-home")) {
                    b = 1;
                }
                break;
            case -1273784917:
                if (str2.equals("previews")) {
                    b = 2;
                }
                break;
            case -816678056:
                if (str2.equals("videos")) {
                    b = 3;
                }
                break;
            case -393940263:
                if (str2.equals("popular")) {
                    b = 4;
                }
                break;
            case 1681:
                if (str2.equals("3d")) {
                    b = 5;
                }
                break;
            case 108960:
                if (str2.equals("new")) {
                    b = 6;
                }
                break;
            case 115761:
                if (str2.equals("uhd")) {
                    b = 7;
                }
                break;
            case 3143097:
                if (str2.equals("find")) {
                    b = 8;
                }
                break;
            case 93145598:
                if (str2.equals("atmos")) {
                    b = 9;
                }
                break;
            case 98240899:
                if (str2.equals(ParserUtil.GENRE_SEGMENT_NAME)) {
                    b = 10;
                }
                break;
            case 108221854:
                if (str2.equals("ra360")) {
                    b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                }
                break;
            case 554217335:
                if (str2.equals("carmode")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                }
                break;
            case 1513279335:
                if (str2.equals("carmode-detail")) {
                    b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                }
                break;
            case 1961482735:
                if (str2.equals("carmode-podcast-episode-list")) {
                    b = MqttWireMessage.MESSAGE_TYPE_DISCONNECT;
                }
                break;
        }
        switch (b) {
            case 0:
                try {
                    pageResponseGenreLandingPage = playlistsLandingPage((PageRequest) basePageRequest);
                } catch (Exception e) {
                    LOG.error("Failed to get response from PlaylistsLanding Page ApiGateway : {}", e.toString());
                }
                break;
            case 1:
            case 12:
                try {
                    pageResponseGenreLandingPage = carModePage((AutomotiveServicePageRequest) basePageRequest);
                } catch (Exception e2) {
                    LOG.error("Failed to get response from DMAutomotive service ApiGateway ", (Throwable) e2);
                    throw e2;
                }
                break;
            case 2:
                try {
                    pageResponseGenreLandingPage = previewsLandingPage((PageRequest) basePageRequest);
                } catch (Exception e3) {
                    LOG.error("Failed to get response from Previews Page ApiGateway : {}", e3.toString());
                }
                break;
            case 3:
                try {
                    pageResponseGenreLandingPage = musicVideosLandingPage((PageRequest) basePageRequest);
                } catch (Exception e4) {
                    LOG.error("Failed to get response from musicVideosLanding Page ApiGateway : {}", e4.toString());
                }
                break;
            case 4:
                try {
                    pageResponseGenreLandingPage = chartsLandingPage((PageRequest) basePageRequest);
                } catch (Exception e5) {
                    LOG.error("Failed to get response from ChartsLanding Page ApiGateway : {}", e5.toString());
                }
                break;
            case 5:
                try {
                    pageResponseGenreLandingPage = threeDLandingPage((PageRequest) basePageRequest);
                } catch (Exception e6) {
                    LOG.error("Failed to get response from 3DLanding Page ApiGateway : {}", e6.toString());
                }
                break;
            case 6:
                try {
                    pageResponseGenreLandingPage = newReleasesLandingPage((PageRequest) basePageRequest);
                } catch (Exception e7) {
                    LOG.error("Failed to get response from NewReleasesLanding Page ApiGateway : {}", e7.toString());
                }
                break;
            case 7:
                try {
                    pageResponseGenreLandingPage = uhdLandingPage((PageRequest) basePageRequest);
                } catch (Exception e8) {
                    LOG.error("Failed to get response from UHDLanding Page ApiGateway : {}", e8.toString());
                }
                break;
            case 8:
                try {
                    pageResponseGenreLandingPage = findLandingPage((PageRequest) basePageRequest);
                } catch (Exception e9) {
                    LOG.error("Failed to get response from FindLanding Page ApiGateway : {}", e9.toString());
                }
                break;
            case 9:
                try {
                    pageResponseGenreLandingPage = atmosLandingPage((PageRequest) basePageRequest);
                } catch (Exception e10) {
                    LOG.error("Failed to get response from atmosLanding Page ApiGateway : {}", e10.toString());
                }
                break;
            case 10:
                try {
                    pageResponseGenreLandingPage = genreLandingPage((PageRequest) basePageRequest);
                } catch (Exception e11) {
                    LOG.error("Failed to get response from GenreLanding Page ApiGateway : {}", e11.toString());
                }
                break;
            case 11:
                try {
                    pageResponseGenreLandingPage = ra360LandingPage((PageRequest) basePageRequest);
                } catch (Exception e12) {
                    LOG.error("Failed to get response from Ra360Landing Page ApiGateway : {}", e12.toString());
                }
                break;
            case 13:
                try {
                    pageResponseGenreLandingPage = carModeDetailPage((AutomotiveServicePageRequest) basePageRequest);
                } catch (Exception e13) {
                    LOG.error("Failed to get response from DMAutomotive service ApiGateway ", (Throwable) e13);
                    throw e13;
                }
                break;
            case 14:
                try {
                    pageResponseGenreLandingPage = carModePodcastEpisodeListPage((AutomotiveServicePageRequest) basePageRequest);
                } catch (Exception e14) {
                    LOG.error("Failed to get response from DMAutomotive service ApiGateway ", (Throwable) e14);
                    throw e14;
                }
                break;
            default:
                LOG.error("Unsupported page requested : {}", basePageRequest.getUri());
                throw new IllegalArgumentException("Unsupported page requested: " + basePageRequest.getUri());
        }
        cacheResponse(cacheKey, pageResponseGenreLandingPage);
        return pageResponseGenreLandingPage;
    }

    public boolean isPageExpired(BasePageRequest basePageRequest, String str) {
        return this.cacheStateManager.hasCacheExpired(getCacheKey(basePageRequest, str));
    }

    public boolean invalidateCache(BasePageRequest basePageRequest, String str) {
        return this.cacheStateManager.invalidateCacheEntry(getCacheKey(basePageRequest, str));
    }

    public String getEndpoint(String str, String str2, String str3, String str4) {
        return String.format("https://%s.%s.api.%s.music.amazon.dev/%s", getRegionEndpoint(str), str4, str2, str3);
    }

    public String getDiscoverEndpoint(String str, String str2, String str3, String str4) {
        return String.format("https://%s.%s.api.%s.discover.music.amazon.dev/%s", getRegionEndpoint(str), str3, str4, str2);
    }

    public String getAutomotiveEndpoint(String str, String str2, String str3) {
        return String.format("https://%s.%s.api.automotive.music.amazon.dev/%s", getRegionEndpoint(str), str2, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getRegionEndpoint(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 2224(0x8b0, float:3.116E-42)
            r2 = 1
            if (r0 == r1) goto L26
            r1 = 2239(0x8bf, float:3.138E-42)
            if (r0 == r1) goto L1c
            r1 = 2483(0x9b3, float:3.48E-42)
            if (r0 == r1) goto L12
            goto L30
        L12:
            java.lang.String r0 = "NA"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L30
            r4 = 2
            goto L31
        L1c:
            java.lang.String r0 = "FE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L30
            r4 = r2
            goto L31
        L26:
            java.lang.String r0 = "EU"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L30
            r4 = 0
            goto L31
        L30:
            r4 = -1
        L31:
            if (r4 == 0) goto L3d
            if (r4 == r2) goto L39
            java.lang.String r4 = "us-east-1"
            return r4
        L39:
            java.lang.String r4 = "us-west-2"
            return r4
        L3d:
            java.lang.String r4 = "eu-west-1"
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.page.api.MusicLandingPageApiGatewayClient.getRegionEndpoint(java.lang.String):java.lang.String");
    }

    private PageResponse fetchFromCache(String str) {
        IOException e;
        PageResponse pageResponse;
        ValidatingObjectInputStream validatingObjectInputStream;
        Cache.Entry entry = this.cache.get(str);
        PageResponse pageResponse2 = null;
        if (entry == null || entry.data == null || entry.isExpired()) {
            return null;
        }
        try {
            validatingObjectInputStream = new ValidatingObjectInputStream(new ByteArrayInputStream(entry.data));
        } catch (IOException e2) {
            e = e2;
            pageResponse = null;
        } catch (ClassNotFoundException unused) {
        }
        try {
            validatingObjectInputStream.accept("*");
            pageResponse = (PageResponse) validatingObjectInputStream.readObject();
            try {
                validatingObjectInputStream.close();
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                LOG.error("Failed to deserialize cached response for key: " + str);
            } catch (ClassNotFoundException unused2) {
                pageResponse2 = pageResponse;
                LOG.error("Failed to cast cached response for key: " + str);
                return pageResponse2;
            }
            return pageResponse;
        } catch (Throwable th) {
            try {
                validatingObjectInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void cacheResponse(String str, PageResponse pageResponse) {
        if (pageResponse == null) {
            return;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.ttl = System.currentTimeMillis() + cacheTtl(pageResponse);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(pageResponse);
                    objectOutputStream.flush();
                    entry.data = byteArrayOutputStream.toByteArray();
                    this.cache.put(str, entry);
                    objectOutputStream.close();
                    byteArrayOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to cache response for key: " + str);
        }
    }

    protected long cacheTtl(PageResponse pageResponse) {
        return (pageResponse.maxCacheTtl() != null ? pageResponse.maxCacheTtl() : CACHE_TTL).longValue();
    }

    PageResponse findLandingPage(PageRequest pageRequest) {
        return findClient(pageRequest).pageGet(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), pageRequest.getDeviceType(), pageRequest.getDeviceId(), pageRequest.getLocale(), pageRequest.getSessionId(), TextUtils.join(",", pageRequest.getFeatureFlags()), pageRequest.getNextToken(), this.widgetId, pageRequest.getCustomerId());
    }

    private FindLandingPageApiGatewayClient findClient(PageRequest pageRequest) {
        return (FindLandingPageApiGatewayClient) this.factory.endpoint(getEndpoint(pageRequest.getRegion(), this.rootPageUri, "v6", pageRequest.getStage())).build(FindLandingPageApiGatewayClient.class);
    }

    PageResponse genreLandingPage(PageRequest pageRequest) {
        return genreClient(pageRequest).pageGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), pageRequest.getLocale(), pageRequest.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), this.authenticationProvider.getDeviceType(), pageRequest.getNextToken(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), this.widgetId, pageRequest.getCustomerId(), pageRequest.getMusicRequestIdentityContextToken(), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), TextUtils.join(",", pageRequest.getFeatureFlags()));
    }

    private GenreLandingPageApiGatewayClient genreClient(PageRequest pageRequest) {
        String endpoint = getEndpoint(pageRequest.getRegion(), this.rootPageUri, "v3sub1", pageRequest.getStage());
        if (this.genreClients.get(this.pathUri) != null) {
            return this.genreClients.get(this.pathUri);
        }
        GenreLandingPageApiGatewayClient genreLandingPageApiGatewayClient = (GenreLandingPageApiGatewayClient) this.factory.endpoint(endpoint + "/" + this.pathUri).build(GenreLandingPageApiGatewayClient.class);
        this.genreClients.put(this.pathUri, genreLandingPageApiGatewayClient);
        return genreLandingPageApiGatewayClient;
    }

    PageResponse uhdLandingPage(PageRequest pageRequest) {
        return uhdClient(pageRequest).pageGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), pageRequest.getDeviceId(), pageRequest.getLocale(), this.authenticationProvider.getDeviceType(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), pageRequest.getNextToken(), this.pathUri, pageRequest.getCustomerId(), pageRequest.getMusicRequestIdentityContextToken(), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()));
    }

    private UhdLandingPageApiGatewayClient uhdClient(PageRequest pageRequest) {
        return (UhdLandingPageApiGatewayClient) this.factory.endpoint(getEndpoint(pageRequest.getRegion(), this.rootPageUri, "v1", pageRequest.getStage())).build(UhdLandingPageApiGatewayClient.class);
    }

    PageResponse threeDLandingPage(PageRequest pageRequest) {
        return threeDClient(pageRequest).pageGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), pageRequest.getDeviceId(), pageRequest.getLocale(), this.authenticationProvider.getDeviceType(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), pageRequest.getNextToken(), this.pathUri, pageRequest.getCustomerId(), pageRequest.getMusicRequestIdentityContextToken(), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()));
    }

    private ThreeDLandingPageApiGatewayClient threeDClient(PageRequest pageRequest) {
        return (ThreeDLandingPageApiGatewayClient) this.factory.endpoint(getEndpoint(pageRequest.getRegion(), this.rootPageUri, "v1", pageRequest.getStage())).build(ThreeDLandingPageApiGatewayClient.class);
    }

    PageResponse musicVideosLandingPage(PageRequest pageRequest) {
        return musicVideosClient(pageRequest).apiV1Get(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), this.authenticationProvider.getDeviceType(), pageRequest.getMusicTerritory(), pageRequest.getCustomerId(), pageRequest.getRequestedContent(), pageRequest.getDeviceType(), pageRequest.getDeviceId(), pageRequest.getLocale(), pageRequest.getNextToken(), TextUtils.join(",", pageRequest.getFeatureFlags()), this.widgetId, String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()));
    }

    private MusicVideosLandingPageAPIClient musicVideosClient(PageRequest pageRequest) {
        return (MusicVideosLandingPageAPIClient) this.factory.endpoint(getEndpoint(pageRequest.getRegion(), this.rootPageUri, "", pageRequest.getStage())).build(MusicVideosLandingPageAPIClient.class);
    }

    PageResponse atmosLandingPage(PageRequest pageRequest) {
        return hdClient(pageRequest).atmosGet(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), pageRequest.getDeviceType(), pageRequest.getDeviceId(), pageRequest.getLocale(), pageRequest.getSessionId(), TextUtils.join(",", pageRequest.getFeatureFlags()), pageRequest.getNextToken(), this.pathUri, pageRequest.getCustomerId(), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), pageRequest.getBrowseId());
    }

    PageResponse ra360LandingPage(PageRequest pageRequest) {
        return hdClient(pageRequest).ra360Get(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), pageRequest.getDeviceType(), pageRequest.getDeviceId(), pageRequest.getLocale(), pageRequest.getSessionId(), TextUtils.join(",", pageRequest.getFeatureFlags()), pageRequest.getNextToken(), this.pathUri, pageRequest.getCustomerId(), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), pageRequest.getBrowseId());
    }

    private HdLandingPageApiGatewayClient hdClient(PageRequest pageRequest) {
        String discoverEndpoint = getDiscoverEndpoint(pageRequest.getRegion(), "v1", pageRequest.getStage(), "hd");
        if (this.hdClients.get("hdClient") != null) {
            return this.hdClients.get("hdClient");
        }
        HdLandingPageApiGatewayClient hdLandingPageApiGatewayClient = (HdLandingPageApiGatewayClient) this.factory.endpoint(discoverEndpoint).build(HdLandingPageApiGatewayClient.class);
        this.hdClients.put("hdClient", hdLandingPageApiGatewayClient);
        return hdLandingPageApiGatewayClient;
    }

    PageResponse newReleasesLandingPage(PageRequest pageRequest) {
        CategoryLandingPageApiGatewayClient categoryLandingPageApiGatewayClientCategoriesClient = categoriesClient(pageRequest);
        String browseId = this.pathUri;
        if (browseId == null) {
            browseId = pageRequest.getBrowseId();
        }
        String str = browseId;
        pageRequest.setWidgetId(this.widgetId);
        if (pageRequest.isCategoryPostEnabled()) {
            return categoryLandingPageApiGatewayClientCategoriesClient.newPost(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), pageRequest, this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId());
        }
        return categoryLandingPageApiGatewayClientCategoriesClient.newGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), pageRequest.getLocale(), pageRequest.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), this.authenticationProvider.getDeviceType(), pageRequest.getNextToken(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), this.widgetId, pageRequest.getCustomerId(), str, TextUtils.join(",", pageRequest.getFeatureFlags()), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), pageRequest.getSessionId(), pageRequest.getMusicRequestIdentityContextToken());
    }

    PageResponse chartsLandingPage(PageRequest pageRequest) {
        String browseId = this.pathUri;
        if (browseId == null) {
            browseId = pageRequest.getBrowseId();
        }
        String str = browseId;
        CategoryLandingPageApiGatewayClient categoryLandingPageApiGatewayClientCategoriesClient = categoriesClient(pageRequest);
        pageRequest.setWidgetId(this.widgetId);
        if (pageRequest.isCategoryPostEnabled()) {
            return categoryLandingPageApiGatewayClientCategoriesClient.popularPost(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), pageRequest, this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId());
        }
        return categoryLandingPageApiGatewayClientCategoriesClient.popularGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), pageRequest.getLocale(), pageRequest.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), this.authenticationProvider.getDeviceType(), pageRequest.getNextToken(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), this.widgetId, pageRequest.getCustomerId(), str, TextUtils.join(",", pageRequest.getFeatureFlags()), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), pageRequest.getSessionId(), pageRequest.getMusicRequestIdentityContextToken());
    }

    PageResponse playlistsLandingPage(PageRequest pageRequest) {
        String browseId = this.pathUri;
        if (browseId == null) {
            browseId = pageRequest.getBrowseId();
        }
        String str = browseId;
        CategoryLandingPageApiGatewayClient categoryLandingPageApiGatewayClientCategoriesClient = categoriesClient(pageRequest);
        pageRequest.setWidgetId(this.widgetId);
        if (pageRequest.isCategoryPostEnabled()) {
            return categoryLandingPageApiGatewayClientCategoriesClient.playlistsPost(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), pageRequest, this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId());
        }
        return categoryLandingPageApiGatewayClientCategoriesClient.playlistsGet(pageRequest.getDeviceType(), this.authenticationProvider.getDeviceId(), pageRequest.getLocale(), pageRequest.getDeviceId(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), this.authenticationProvider.getDeviceType(), pageRequest.getNextToken(), pageRequest.getMusicTerritory(), pageRequest.getRequestedContent(), this.widgetId, pageRequest.getCustomerId(), str, TextUtils.join(",", pageRequest.getFeatureFlags()), String.valueOf(pageRequest.isExplicitLanguageFilterEnabled()), pageRequest.getSessionId(), pageRequest.getMusicRequestIdentityContextToken());
    }

    PageResponse previewsLandingPage(PageRequest pageRequest) {
        CategoryLandingPageApiGatewayClient categoryLandingPageApiGatewayClientCategoriesClient = categoriesClient(pageRequest);
        pageRequest.setWidgetId(this.widgetId);
        return categoryLandingPageApiGatewayClientCategoriesClient.previewsPost(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), pageRequest, this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId());
    }

    private CategoryLandingPageApiGatewayClient categoriesClient(PageRequest pageRequest) {
        String discoverEndpoint = getDiscoverEndpoint(pageRequest.getRegion(), "v1", pageRequest.getStage(), "category");
        if (this.categoryClients.get("categoryClient") != null) {
            return this.categoryClients.get("categoryClient");
        }
        CategoryLandingPageApiGatewayClient categoryLandingPageApiGatewayClient = (CategoryLandingPageApiGatewayClient) this.factory.endpoint(discoverEndpoint).build(CategoryLandingPageApiGatewayClient.class);
        this.categoryClients.put("categoryClient", categoryLandingPageApiGatewayClient);
        return categoryLandingPageApiGatewayClient;
    }

    private AutomotiveServiceApiGatewayClient automotiveServiceClient(AutomotiveServicePageRequest automotiveServicePageRequest) {
        return (AutomotiveServiceApiGatewayClient) this.factory.endpoint(getAutomotiveEndpoint(automotiveServicePageRequest.getRegion(), automotiveServicePageRequest.getStage(), "v1")).build(AutomotiveServiceApiGatewayClient.class);
    }

    PageResponse carModePage(AutomotiveServicePageRequest automotiveServicePageRequest) {
        return automotiveServiceClient(automotiveServicePageRequest).v1CarModeGet(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), automotiveServicePageRequest.getAppVersion(), automotiveServicePageRequest.getPlatform(), automotiveServicePageRequest.getOsVersion(), automotiveServicePageRequest.getMusicTerritory(), automotiveServicePageRequest.getMarketPlaceId(), automotiveServicePageRequest.getRequestedContent(), String.valueOf(automotiveServicePageRequest.isExplicitLanguageFilterEnabled()), automotiveServicePageRequest.getDeviceType(), automotiveServicePageRequest.getDeviceId(), automotiveServicePageRequest.getDeviceModel(), automotiveServicePageRequest.getLocale(), automotiveServicePageRequest.getCustomerId(), automotiveServicePageRequest.getPresetIds(), automotiveServicePageRequest.getDownloadedIds(), automotiveServicePageRequest.getRecentMusicIds(), automotiveServicePageRequest.getRecentlyCreatedLibraryPlaylistIds(), TextUtils.join(",", automotiveServicePageRequest.getFeatureFlags()));
    }

    PageResponse carModeDetailPage(AutomotiveServicePageRequest automotiveServicePageRequest) {
        return automotiveServiceClient(automotiveServicePageRequest).v1CarModeDetailsGet(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), automotiveServicePageRequest.getAppVersion(), automotiveServicePageRequest.getPlatform(), automotiveServicePageRequest.getOsVersion(), automotiveServicePageRequest.getMusicTerritory(), automotiveServicePageRequest.getMarketPlaceId(), automotiveServicePageRequest.getRequestedContent(), String.valueOf(automotiveServicePageRequest.isExplicitLanguageFilterEnabled()), automotiveServicePageRequest.getDeviceType(), automotiveServicePageRequest.getDeviceId(), automotiveServicePageRequest.getDeviceModel(), automotiveServicePageRequest.getLocale(), automotiveServicePageRequest.getCustomerId(), automotiveServicePageRequest.getWidgetId(), automotiveServicePageRequest.getPresetIds(), automotiveServicePageRequest.getDownloadedIds(), automotiveServicePageRequest.getRecentMusicIds(), automotiveServicePageRequest.getRecentlyCreatedLibraryPlaylistIds());
    }

    PageResponse carModePodcastEpisodeListPage(AutomotiveServicePageRequest automotiveServicePageRequest) {
        return automotiveServiceClient(automotiveServicePageRequest).CarModePodcastEpisodeListGet(this.authenticationProvider.getDeviceId(), this.authenticationProvider.getDeviceType(), this.authenticationProvider.getToken(), this.authenticationProvider.getCustomerId(), automotiveServicePageRequest.getAppVersion(), automotiveServicePageRequest.getPlatform(), automotiveServicePageRequest.getOsVersion(), automotiveServicePageRequest.getMusicTerritory(), automotiveServicePageRequest.getMarketPlaceId(), automotiveServicePageRequest.getRequestedContent(), String.valueOf(automotiveServicePageRequest.isExplicitLanguageFilterEnabled()), automotiveServicePageRequest.getDeviceType(), automotiveServicePageRequest.getDeviceId(), automotiveServicePageRequest.getDeviceModel(), automotiveServicePageRequest.getLocale(), automotiveServicePageRequest.getCustomerId(), automotiveServicePageRequest.getPodcastShowId());
    }

    private void parseUri(String str) {
        String[] strArrSplit = str.split("/");
        int i = "uri:".equalsIgnoreCase(strArrSplit[0]) ? 2 : 0;
        String str2 = strArrSplit[i];
        this.rootPageUri = str2;
        if (str2.equals("videos")) {
            int i2 = i + 1;
            this.widgetId = strArrSplit.length > i2 ? strArrSplit[i2] : null;
        } else {
            int i3 = i + 1;
            this.pathUri = strArrSplit.length > i3 ? strArrSplit[i3] : null;
            int i4 = i + 2;
            this.widgetId = strArrSplit.length > i4 ? strArrSplit[i4] : null;
        }
    }

    static String getCacheKey(BasePageRequest basePageRequest, String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(basePageRequest.getUri(), basePageRequest.getLocale(), basePageRequest.getMusicTerritory(), basePageRequest.getRequestedContent(), basePageRequest.getBrowseId(), basePageRequest.getCustomerId(), basePageRequest.getNextToken(), String.valueOf(basePageRequest.getCount())));
        if (str != null) {
            arrayList.add(str);
        }
        if (basePageRequest instanceof AutomotiveServicePageRequest) {
            AutomotiveServicePageRequest automotiveServicePageRequest = (AutomotiveServicePageRequest) basePageRequest;
            arrayList.addAll(Arrays.asList(automotiveServicePageRequest.getPresetIds(), automotiveServicePageRequest.getDownloadedIds(), automotiveServicePageRequest.getRecentMusicIds(), automotiveServicePageRequest.getRecentlyCreatedLibraryPlaylistIds(), automotiveServicePageRequest.getWidgetId(), automotiveServicePageRequest.getPodcastShowId()));
        }
        String strJoin = TextUtils.join(";", arrayList);
        strJoin.hashCode();
        return String.valueOf(strJoin.hashCode());
    }
}
