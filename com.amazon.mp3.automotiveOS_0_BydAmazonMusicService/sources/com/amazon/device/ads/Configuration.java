package com.amazon.device.ads;

import android.content.SharedPreferences;
import android.webkit.CookieManager;
import com.amazon.aps.shared.APSAnalytics;
import com.amazon.device.ads.Metrics;
import com.amazon.device.ads.PreferredMarketplaceRetriever;
import com.amazon.device.ads.ThreadUtils;
import com.amazon.device.ads.WebRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class Configuration {
    private static final String LOGTAG = "Configuration";
    private static Configuration instance = new Configuration();
    private boolean amazonAdvertisingPublisher;
    private String appDefinedMarketplace;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private boolean isAppDefinedMarketplaceSet;
    private final AtomicBoolean isFetching;
    private boolean isFirstParty;
    private Boolean lastTestModeValue;
    private final List<ConfigurationListener> listeners;
    private final MobileAdsLogger logger;
    private final HashMap<String, String> mShopDomainMapping;
    private final Metrics metrics;
    private final PermissionChecker permissionChecker;
    private PreferredMarketplaceRetriever pfmRetriever;
    private final Settings settings;
    private final SystemTime systemTime;
    private final ThreadUtils.ThreadRunner threadRunner;
    private final WebRequest.WebRequestFactory webRequestFactory;
    private final WebRequestUserId webRequestUserId;

    interface ConfigurationListener {
        void onConfigurationFailure();

        void onConfigurationReady();
    }

    public static class ConfigOption {
        public static final ConfigOption AAX_HOSTNAME;
        public static final ConfigOption AD_PREF_URL;
        public static final ConfigOption AD_RESOURCE_PATH;
        public static final ConfigOption ANALYTICS_PARAMS;
        public static final ConfigOption BASE_URL;
        public static final ConfigOption COOKIE_CONSENT;
        public static final ConfigOption DEBUG_PROPERTIES;
        public static final ConfigOption IDENTIFY_USER_INTERVAL;
        public static final ConfigOption IDENTIFY_USER_RETRY_INTERVAL;
        public static final ConfigOption IDENTIFY_USER_SESSION_INTERVAL;
        public static final ConfigOption MADS_HOSTNAME;
        public static final ConfigOption SEND_GEO;
        public static final ConfigOption SIS_DOMAIN;
        public static final ConfigOption SIS_URL;
        public static final ConfigOption TRUNCATE_LAT_LON;
        public static final ConfigOption VIEWABLE_INTERVAL;
        public static final ConfigOption VIEWABLE_JAVASCRIPT_URL;
        public static final ConfigOption VIEWABLE_JS_VERSION_CONFIG;
        public static final ConfigOption WHITELISTED_CUSTOMER;
        public static final ConfigOption[] configOptions;
        private final boolean allowEmpty;
        private final Class<?> dataType;
        private final String responseKey;
        private final String settingsName;

        static {
            ConfigOption configOption = new ConfigOption("config-aaxHostname", String.class, "aaxHostname");
            AAX_HOSTNAME = configOption;
            ConfigOption configOption2 = new ConfigOption("config-adResourcePath", String.class, "adResourcePath");
            AD_RESOURCE_PATH = configOption2;
            ConfigOption configOption3 = new ConfigOption("config-sisURL", String.class, "sisURL");
            SIS_URL = configOption3;
            ConfigOption configOption4 = new ConfigOption("config-adPrefURL", String.class, "adPrefURL");
            AD_PREF_URL = configOption4;
            ConfigOption configOption5 = new ConfigOption("config-madsHostname", String.class, "madsHostname", true);
            MADS_HOSTNAME = configOption5;
            ConfigOption configOption6 = new ConfigOption("config-sisDomain", String.class, "sisDomain");
            SIS_DOMAIN = configOption6;
            ConfigOption configOption7 = new ConfigOption("config-sendGeo", Boolean.class, "sendGeo");
            SEND_GEO = configOption7;
            ConfigOption configOption8 = new ConfigOption("config-truncateLatLon", Boolean.class, "truncateLatLon");
            TRUNCATE_LAT_LON = configOption8;
            ConfigOption configOption9 = new ConfigOption("config-whitelistedCustomer", Boolean.class, "whitelistedCustomer");
            WHITELISTED_CUSTOMER = configOption9;
            ConfigOption configOption10 = new ConfigOption("config-identifyUserInterval", Long.class, "identifyUserInterval");
            IDENTIFY_USER_INTERVAL = configOption10;
            ConfigOption configOption11 = new ConfigOption("config-identifyUserSessionIdInterval", Long.class, "identifyUserSessionIdInterval", true);
            IDENTIFY_USER_SESSION_INTERVAL = configOption11;
            ConfigOption configOption12 = new ConfigOption("config-identifyUserRetryInterval", Long.class, "identifyUserRetryInterval", true);
            IDENTIFY_USER_RETRY_INTERVAL = configOption12;
            ConfigOption configOption13 = new ConfigOption("config-viewableJavascriptCDNURL", String.class, "viewableJavascriptCDNURL");
            VIEWABLE_JAVASCRIPT_URL = configOption13;
            ConfigOption configOption14 = new ConfigOption("config-viewableJSVersionConfig", Integer.class, "viewableJSVersion");
            VIEWABLE_JS_VERSION_CONFIG = configOption14;
            ConfigOption configOption15 = new ConfigOption("config-viewableInterval", Long.class, "viewableInterval", true);
            VIEWABLE_INTERVAL = configOption15;
            ConfigOption configOption16 = new ConfigOption("config-debugProperties", JSONObject.class, "debugProperties", true);
            DEBUG_PROPERTIES = configOption16;
            ConfigOption configOption17 = new ConfigOption("config-baseURL", String.class, "baseURL", true);
            BASE_URL = configOption17;
            ConfigOption configOption18 = new ConfigOption("config-cookieConsent", JSONObject.class, "cookieConsent", true);
            COOKIE_CONSENT = configOption18;
            ConfigOption configOption19 = new ConfigOption("config-analyticsParams", JSONObject.class, "analyticsParams", true);
            ANALYTICS_PARAMS = configOption19;
            configOptions = new ConfigOption[]{configOption, configOption2, configOption3, configOption4, configOption5, configOption6, configOption7, configOption8, configOption9, configOption10, configOption11, configOption12, configOption13, configOption14, configOption16, configOption15, configOption17, configOption18, configOption19};
        }

        protected ConfigOption(String str, Class<?> cls, String str2) {
            this(str, cls, str2, false);
        }

        protected ConfigOption(String str, Class<?> cls, String str2, boolean z) {
            this.settingsName = str;
            this.responseKey = str2;
            this.dataType = cls;
            this.allowEmpty = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getSettingsName() {
            return this.settingsName;
        }

        String getResponseKey() {
            return this.responseKey;
        }

        Class<?> getDataType() {
            return this.dataType;
        }

        boolean getAllowEmpty() {
            return this.allowEmpty;
        }
    }

    protected Configuration() {
        this(new MobileAdsLoggerFactory(), new PermissionChecker(), new WebRequest.WebRequestFactory(), DebugProperties.getInstance(), Settings.getInstance(), MobileAdsInfoStore.getInstance(), new SystemTime(), Metrics.getInstance(), ThreadUtils.getThreadRunner(), new WebRequestUserId());
    }

    Configuration(MobileAdsLoggerFactory mobileAdsLoggerFactory, PermissionChecker permissionChecker, WebRequest.WebRequestFactory webRequestFactory, DebugProperties debugProperties, Settings settings, MobileAdsInfoStore mobileAdsInfoStore, SystemTime systemTime, Metrics metrics, ThreadUtils.ThreadRunner threadRunner, WebRequestUserId webRequestUserId) {
        this.appDefinedMarketplace = null;
        this.isAppDefinedMarketplaceSet = false;
        this.listeners = new ArrayList(5);
        this.isFetching = new AtomicBoolean(false);
        this.lastTestModeValue = null;
        this.isFirstParty = false;
        this.pfmRetriever = new PreferredMarketplaceRetriever.NullPreferredMarketplaceRetriever();
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.webRequestFactory = webRequestFactory;
        this.debugProperties = debugProperties;
        this.settings = settings;
        this.infoStore = mobileAdsInfoStore;
        this.systemTime = systemTime;
        this.metrics = metrics;
        this.threadRunner = threadRunner;
        this.webRequestUserId = webRequestUserId;
        this.amazonAdvertisingPublisher = true;
        this.mShopDomainMapping = setMShopDomainMapping();
    }

    public static final Configuration getInstance() {
        return instance;
    }

    public void setAppDefinedMarketplace(String str) {
        this.appDefinedMarketplace = str;
        this.isAppDefinedMarketplaceSet = true;
    }

    public void setIsFirstParty(boolean z) {
        this.isFirstParty = z;
    }

    private void fetchAmazonAdvertisingPublisherConsent(String str) {
        JSONObject jSONObjectFromString = JSONUtils.getJSONObjectFromString(str);
        if (jSONObjectFromString == null) {
            this.logger.e("Invalid privacy consent format");
            setAmazonAdvertisingPublisherConsent(false);
        } else {
            setAmazonAdvertisingPublisherConsent(JSONUtils.getBooleanFromJSON(jSONObjectFromString, JSONUtils.getStringFromJSON(getJSONObjectWithDefault(ConfigOption.COOKIE_CONSENT, new JSONObject()), "amznAdPubConsent", "amazonAdvertisingPublisher"), false));
        }
    }

    private void setAmazonAdvertisingPublisherConsent(boolean z) {
        this.amazonAdvertisingPublisher = z;
    }

    public boolean isAmazonAdvertisingPublisher() {
        return this.amazonAdvertisingPublisher;
    }

    public boolean hasValue(ConfigOption configOption) {
        return !StringUtils.isNullOrWhiteSpace(getString(configOption));
    }

    public String getString(ConfigOption configOption) {
        return this.settings.getString(configOption.getSettingsName(), null);
    }

    public boolean getBooleanWithDefault(ConfigOption configOption, boolean z) {
        return this.settings.getBoolean(configOption.getSettingsName(), z);
    }

    public long getLong(ConfigOption configOption) {
        return getLongWithDefault(configOption, 0L);
    }

    public long getLongWithDefault(ConfigOption configOption, long j) {
        return this.settings.getLong(configOption.getSettingsName(), j);
    }

    public JSONObject getJSONObjectWithDefault(ConfigOption configOption, JSONObject jSONObject) {
        return this.settings.getJSONObject(configOption.getSettingsName(), jSONObject);
    }

    public String getCookieConsent() {
        if (!JSONUtils.getBooleanFromJSON(getJSONObjectWithDefault(ConfigOption.COOKIE_CONSENT, new JSONObject()), "featureEnabled", true)) {
            setAmazonAdvertisingPublisherConsent(true);
            return null;
        }
        String currentMShopDomainBasedOnMarketPlaceCode = getCurrentMShopDomainBasedOnMarketPlaceCode();
        if (StringUtils.isNullOrEmpty(currentMShopDomainBasedOnMarketPlaceCode)) {
            currentMShopDomainBasedOnMarketPlaceCode = "amazon.com";
        }
        String cookie = CookieManager.getInstance().getCookie(currentMShopDomainBasedOnMarketPlaceCode);
        if (!StringUtils.isNullOrEmpty(cookie)) {
            for (String str : cookie.split(";")) {
                String[] strArrSplit = str.trim().split("=");
                if (strArrSplit.length > 1 && strArrSplit[0].toLowerCase().trim().equals(JSONUtils.getStringFromJSON(getJSONObjectWithDefault(ConfigOption.COOKIE_CONSENT, new JSONObject()), "cookieName", "privacy-consent"))) {
                    this.logger.i("Found consent string in webview Cookie Jar");
                    fetchAmazonAdvertisingPublisherConsent(WebUtils.getURLDecodedString(strArrSplit[1].trim()));
                    this.settings.putString("MSDK_CONSENT_STRING_" + currentMShopDomainBasedOnMarketPlaceCode, strArrSplit[1].trim());
                    return strArrSplit[1].trim();
                }
            }
        }
        String writtenString = this.settings.getWrittenString("MSDK_CONSENT_STRING_" + currentMShopDomainBasedOnMarketPlaceCode, null);
        if (!StringUtils.isNullOrEmpty(writtenString)) {
            this.logger.i("Last stored consent string based on domain");
            fetchAmazonAdvertisingPublisherConsent(WebUtils.getURLDecodedString(writtenString));
            return writtenString;
        }
        setAmazonAdvertisingPublisherConsent(true);
        return null;
    }

    String getCurrentMShopDomainBasedOnMarketPlaceCode() {
        if (ApplicationDefaultPreferences.getDefaultPreferences() == null) {
            ApplicationDefaultPreferences.initialize(this.infoStore.getApplicationContext());
        }
        SharedPreferences defaultPreferences = ApplicationDefaultPreferences.getDefaultPreferences();
        ConfigOption configOption = ConfigOption.COOKIE_CONSENT;
        String stringFromJSON = JSONUtils.getStringFromJSON(getJSONObjectWithDefault(configOption, new JSONObject()), "mShopDomainStorageKey", "MSHOP_CURRENT_COOKIE_DOMAIN");
        if (defaultPreferences != null && defaultPreferences.contains(stringFromJSON)) {
            return defaultPreferences.getString(stringFromJSON, null);
        }
        String stringFromJSON2 = JSONUtils.getStringFromJSON(getJSONObjectWithDefault(configOption, new JSONObject()), "mShopDomain", null);
        if (!StringUtils.isNullOrEmpty(stringFromJSON2)) {
            return stringFromJSON2;
        }
        String string = this.settings.getString("config-appDefinedMarketplace", null);
        return (StringUtils.isNullOrEmpty(string) || !this.mShopDomainMapping.containsKey(string.trim().toUpperCase())) ? "amazon.com" : this.mShopDomainMapping.get(string.trim().toUpperCase());
    }

    private HashMap<String, String> setMShopDomainMapping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("AE", "amazon.ae");
        map.put("AU", "amazon.com.au");
        map.put("BR", "amazon.com.br");
        map.put("CA", "amazon.ca");
        map.put("CN", "amazon.cn");
        map.put("DE", "amazon.de");
        map.put("EG", "amazon.eg");
        map.put("ES", "amazon.es");
        map.put("FR", "amazon.fr");
        map.put("IN", "amazon.in");
        map.put("IT", "amazon.it");
        map.put("JP", "amazon.co.jp");
        map.put("MX", "amazon.com.mx");
        map.put("NL", "amazon.nl");
        map.put("PL", "amazon.pl");
        map.put("SA", "amazon.sa");
        map.put("SE", "amazon.se");
        map.put("SG", "amazon.sg");
        map.put("TR", "amazon.com.tr");
        map.put("UK", "amazon.co.uk");
        map.put("US", "amazon.com");
        return map;
    }

    protected boolean shouldFetch() {
        if (hasAppDefinedMarketplaceChanged() || this.settings.getInt("configVersion", 0) != 4) {
            return true;
        }
        long j = this.settings.getLong("config-lastFetchTime", 0L);
        if (j == 0) {
            this.logger.d("No configuration found. A new configuration will be retrieved.");
            return true;
        }
        if (this.systemTime.currentTimeMillis() - j > this.settings.getLong("config-ttl", 86400000L)) {
            this.logger.d("The configuration has expired. A new configuration will be retrieved.");
            return true;
        }
        if (this.settings.getWrittenLong("amzn-ad-iu-last-checkin", 0L) - j > 0) {
            this.logger.d("A new user has been identified. A new configuration will be retrieved.");
            return true;
        }
        Boolean bool = this.lastTestModeValue;
        if (bool == null || bool.booleanValue() == this.settings.getBoolean("testingEnabled", false)) {
            return this.debugProperties.getDebugPropertyAsBoolean("debug.shouldFetchConfig", Boolean.FALSE).booleanValue();
        }
        this.logger.d("The testing mode has changed. A new configuration will be retrieved.");
        return true;
    }

    private boolean hasAppDefinedMarketplaceChanged() {
        String string = this.settings.getString("config-appDefinedMarketplace", null);
        if (this.isAppDefinedMarketplaceSet) {
            this.isAppDefinedMarketplaceSet = false;
            String str = this.appDefinedMarketplace;
            if (str != null && !str.equals(string)) {
                this.settings.putLongWithNoFlush("config-lastFetchTime", 0L);
                this.settings.putStringWithNoFlush("config-appDefinedMarketplace", this.appDefinedMarketplace);
                this.settings.flush();
                this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
                this.logger.d("New application-defined marketplace set. A new configuration will be retrieved.");
                return true;
            }
            if (string != null && this.appDefinedMarketplace == null) {
                this.settings.remove("config-appDefinedMarketplace");
                this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
                this.logger.d("Application-defined marketplace removed. A new configuration will be retrieved.");
                return true;
            }
        }
        return false;
    }

    protected boolean isFetching() {
        return this.isFetching.get();
    }

    protected void setIsFetching(boolean z) {
        this.isFetching.set(z);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener configurationListener) {
        queueConfigurationListener(configurationListener, true);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener configurationListener, boolean z) {
        if (isFetching()) {
            this.listeners.add(configurationListener);
        } else if (shouldFetch()) {
            this.listeners.add(configurationListener);
            if (z) {
                this.logger.d("Starting configuration fetching...");
                setIsFetching(true);
                beginFetch();
            }
        } else {
            configurationListener.onConfigurationReady();
        }
    }

    protected void beginFetch() {
        this.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.Configuration.1
            @Override // java.lang.Runnable
            public void run() {
                Configuration.this.fetchConfigurationOnBackgroundThread();
            }
        }, ThreadUtils.ExecutionStyle.SCHEDULE, ThreadUtils.ExecutionThread.BACKGROUND_THREAD);
    }

    protected synchronized void onFetchSuccess() {
        setIsFetching(false);
        for (ConfigurationListener configurationListener : getAndClearListeners()) {
            configurationListener.onConfigurationReady();
        }
    }

    protected synchronized void onFetchFailure() {
        this.metrics.getMetricsCollector().incrementMetric(Metrics.MetricType.AAX_CONFIG_DOWNLOAD_FAILED);
        setIsFetching(false);
        for (ConfigurationListener configurationListener : getAndClearListeners()) {
            configurationListener.onConfigurationFailure();
        }
    }

    protected synchronized ConfigurationListener[] getAndClearListeners() {
        ConfigurationListener[] configurationListenerArr;
        configurationListenerArr = (ConfigurationListener[]) this.listeners.toArray(new ConfigurationListener[this.listeners.size()]);
        this.listeners.clear();
        return configurationListenerArr;
    }

    protected ConfigOption[] getConfigOptions() {
        return ConfigOption.configOptions;
    }

    protected void setLastTestModeValue(boolean z) {
        this.lastTestModeValue = Boolean.valueOf(z);
    }

    protected void fetchConfigurationOnBackgroundThread() {
        this.logger.d("In configuration fetcher background thread.");
        if (!this.permissionChecker.hasInternetPermission(this.infoStore.getApplicationContext())) {
            this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
            onFetchFailure();
            return;
        }
        WebRequest webRequestCreateWebRequest = createWebRequest();
        if (webRequestCreateWebRequest == null) {
            onFetchFailure();
            return;
        }
        try {
            JSONObject asJSON = webRequestCreateWebRequest.makeCall().getResponseReader().readAsJSON();
            try {
                for (ConfigOption configOption : getConfigOptions()) {
                    if (asJSON.isNull(configOption.getResponseKey())) {
                        if (configOption.getAllowEmpty()) {
                            this.settings.removeWithNoFlush(configOption.getSettingsName());
                        } else {
                            throw new Exception("The configuration value for " + configOption.getResponseKey() + " must be present and not null.");
                        }
                    } else {
                        writeSettingFromConfigOption(configOption, asJSON);
                    }
                }
                ConfigOption configOption2 = ConfigOption.DEBUG_PROPERTIES;
                if (asJSON.isNull(configOption2.getResponseKey())) {
                    this.settings.removeWithNoFlush(configOption2.getSettingsName());
                    this.debugProperties.clearDebugProperties();
                } else {
                    this.debugProperties.overwriteDebugProperties(asJSON.getJSONObject(configOption2.getResponseKey()));
                }
                if (asJSON.isNull("ttl")) {
                    throw new Exception("The configuration value must be present and not null.");
                }
                long jConvertToMillisecondsFromSeconds = NumberUtils.convertToMillisecondsFromSeconds(asJSON.getInt("ttl"));
                try {
                    if (!asJSON.isNull("analyticsParams")) {
                        int integerFromJSON = JSONUtils.getIntegerFromJSON(getJSONObjectWithDefault(ConfigOption.ANALYTICS_PARAMS, new JSONObject()), "samplingRate", 1);
                        this.logger.d("Analytics Sampling Rate value:" + integerFromJSON);
                        APSAnalytics.setSamplingRate(integerFromJSON);
                    }
                } catch (Exception e) {
                    this.logger.e("Unexpected error during parsing anlytics sampling rate: %s", e.getMessage());
                }
                if (jConvertToMillisecondsFromSeconds > 86400000) {
                    jConvertToMillisecondsFromSeconds = 86400000;
                }
                this.settings.putLongWithNoFlush("config-ttl", jConvertToMillisecondsFromSeconds);
                this.settings.putLongWithNoFlush("config-lastFetchTime", this.systemTime.currentTimeMillis());
                this.settings.putIntWithNoFlush("configVersion", 4);
                this.settings.flush();
                this.logger.d("Configuration fetched and saved.");
                onFetchSuccess();
            } catch (JSONException e2) {
                this.logger.e("Unable to parse JSON response: %s", e2.getMessage());
                onFetchFailure();
            } catch (Exception e3) {
                this.logger.e("Unexpected error during parsing: %s", e3.getMessage());
                onFetchFailure();
            }
        } catch (WebRequest.WebRequestException unused) {
            onFetchFailure();
        }
    }

    private void writeSettingFromConfigOption(ConfigOption configOption, JSONObject jSONObject) throws Exception {
        if (configOption.getDataType().equals(String.class)) {
            String string = jSONObject.getString(configOption.getResponseKey());
            if (configOption.getAllowEmpty() || !StringUtils.isNullOrWhiteSpace(string)) {
                this.settings.putStringWithNoFlush(configOption.getSettingsName(), string);
                return;
            }
            throw new IllegalArgumentException("The configuration value must not be empty or contain only white spaces.");
        }
        if (configOption.getDataType().equals(Boolean.class)) {
            this.settings.putBooleanWithNoFlush(configOption.getSettingsName(), jSONObject.getBoolean(configOption.getResponseKey()));
            return;
        }
        if (configOption.getDataType().equals(Integer.class)) {
            this.settings.putIntWithNoFlush(configOption.getSettingsName(), jSONObject.getInt(configOption.getResponseKey()));
        } else if (configOption.getDataType().equals(Long.class)) {
            this.settings.putLongWithNoFlush(configOption.getSettingsName(), jSONObject.getLong(configOption.getResponseKey()));
        } else {
            if (configOption.getDataType().equals(JSONObject.class)) {
                this.settings.putJSONObjectWithNoFlush(configOption.getSettingsName(), jSONObject.getJSONObject(configOption.getResponseKey()));
                return;
            }
            throw new IllegalArgumentException("Undefined configuration option type.");
        }
    }

    protected WebRequest createWebRequest() {
        WebRequest webRequestCreateJSONGetWebRequest = this.webRequestFactory.createJSONGetWebRequest();
        webRequestCreateJSONGetWebRequest.setExternalLogTag(LOGTAG);
        webRequestCreateJSONGetWebRequest.enableLog(true);
        webRequestCreateJSONGetWebRequest.setHost(this.debugProperties.getDebugPropertyAsString("debug.aaxConfigHostname", "mads.amazon-adsystem.com"));
        webRequestCreateJSONGetWebRequest.setPath("/msdk/getConfig");
        webRequestCreateJSONGetWebRequest.setMetricsCollector(this.metrics.getMetricsCollector());
        webRequestCreateJSONGetWebRequest.setServiceCallLatencyMetric(Metrics.MetricType.AAX_CONFIG_DOWNLOAD_LATENCY);
        webRequestCreateJSONGetWebRequest.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean("debug.aaxConfigUseSecure", Boolean.TRUE).booleanValue());
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        DeviceInfo deviceInfo = this.infoStore.getDeviceInfo();
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("appId", registrationInfo.getAppKey());
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("dinfo", deviceInfo.getDInfoProperty().toString());
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("sdkVer", Version.getSDKVersion());
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("fp", Boolean.toString(this.isFirstParty));
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("mkt", this.settings.getString("config-appDefinedMarketplace", null));
        webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("pfm", getPreferredMarketplace());
        boolean z = this.settings.getBoolean("testingEnabled", false);
        setLastTestModeValue(z);
        if (z) {
            webRequestCreateJSONGetWebRequest.putUnencodedQueryParameter("testMode", "true");
        }
        webRequestCreateJSONGetWebRequest.setAdditionalQueryParamsString(this.debugProperties.getDebugPropertyAsString("debug.aaxConfigParams", null));
        return webRequestCreateJSONGetWebRequest;
    }

    public void setPreferredMarketplaceRetriever(PreferredMarketplaceRetriever preferredMarketplaceRetriever) {
        this.pfmRetriever = preferredMarketplaceRetriever;
    }

    private String getPreferredMarketplace() {
        return this.pfmRetriever.retrievePreferredMarketplace(MobileAdsInfoStore.getInstance().getApplicationContext());
    }
}
