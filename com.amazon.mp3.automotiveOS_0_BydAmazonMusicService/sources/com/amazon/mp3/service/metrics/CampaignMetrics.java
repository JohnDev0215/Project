package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.ClientInfoMetricsHelper;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.external.ads.AmazonAds;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.mts.event.definition.store.Campaign;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Completable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public final class CampaignMetrics implements Campaign {
    static final Long NO_CLICK_TS = Long.valueOf("0");
    static final Long NO_INSTALL_BEGIN_TS = Long.valueOf("0");
    private static final String TAG = "CampaignMetrics";
    private static InstallReferrerClient mInstallReferrerClient = null;
    private static boolean sHasEmittedCampaignMetric = false;
    private final Map<String, String> mCampaignReferrerMap;
    private final Long mClickTS;
    private final Long mInstallBeginTS;
    private final String mRawReferrer;

    public static void report(final Context context) {
        Completable.fromAction(new Action0() { // from class: com.amazon.mp3.service.metrics.CampaignMetrics$$ExternalSyntheticLambda0
            @Override // rx.functions.Action0
            public final void call() {
                CampaignMetrics.lambda$report$0(context);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$report$0(Context context) {
        ClientInfoMetricsHelper clientInfoMetricsHelper = AmazonApplication.getClientInfoMetricsHelper();
        if (clientInfoMetricsHelper != null) {
            clientInfoMetricsHelper.sendClientInfoMetricForCampaignMetrics();
        }
        CampaignMetrics campaignMetricsLoadCampaignReferrer = loadCampaignReferrer(context);
        if (campaignMetricsLoadCampaignReferrer.mCampaignReferrerMap.size() > 0) {
            MetricsLogger.userClickedonCampaign(campaignMetricsLoadCampaignReferrer);
        }
    }

    private static final Map<String, String> parseReferrer(final String rawReferrer) {
        if (rawReferrer.length() == 0) {
            return Collections.EMPTY_MAP;
        }
        String str = TAG;
        Log.debug(str, "rawReferrer: " + rawReferrer);
        if (!isRawReferrerValid(rawReferrer)) {
            Log.warning(str, "An invalid referrer string: %s", rawReferrer);
            return Collections.EMPTY_MAP;
        }
        try {
            List<NameValuePair> list = URLEncodedUtils.parse(createUriWithRawReferrer(rawReferrer), ParserUtil.UTF_8);
            HashMap map = new HashMap(8);
            for (NameValuePair nameValuePair : list) {
                String value = nameValuePair.getValue();
                if (value != null && value.length() != 0) {
                    map.put(nameValuePair.getName(), value);
                }
            }
            return map;
        } catch (URISyntaxException unused) {
            Log.warning(TAG, "Failed to parse a referrer string: %s", rawReferrer);
            return Collections.EMPTY_MAP;
        }
    }

    private static URI createUriWithRawReferrer(final String rawReferrer) throws URISyntaxException {
        return new URI("http", "amazon.com", "/", rawReferrer, "");
    }

    protected CampaignMetrics(final String rawReferrer, final Long clickTS, final Long installBeginTS) {
        this.mCampaignReferrerMap = parseReferrer(rawReferrer);
        this.mRawReferrer = rawReferrer;
        this.mClickTS = clickTS;
        this.mInstallBeginTS = installBeginTS;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.store.Campaign
    public String getName() {
        String str = this.mCampaignReferrerMap.get("utm_campaign");
        return str == null ? "" : str;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.store.Campaign
    public String getSource() {
        String str = this.mCampaignReferrerMap.get("utm_source");
        return str == null ? "" : str;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.store.Campaign
    public String getMedium() {
        String str = this.mCampaignReferrerMap.get("utm_medium");
        return str == null ? "" : str;
    }

    @Override // com.amazon.music.metrics.mts.event.definition.store.Campaign
    public String getContent() {
        String str = this.mCampaignReferrerMap.get("utm_content");
        return str == null ? "" : str;
    }

    public String getRawReferrer() {
        return this.mRawReferrer;
    }

    public Long getClickTS() {
        return this.mClickTS;
    }

    public Long getInstallBeginTS() {
        return this.mInstallBeginTS;
    }

    @VisibleForTesting
    static File getBackupFile() {
        try {
            StorageInfo storageInfo = StorageInfo.getInstance();
            if (storageInfo.isExternalStorageAvailable()) {
                return new File(String.format(Locale.US, "%s" + File.separator + "%s", storageInfo.getExternalStorageDirectory().getAbsolutePath(), AmazonApplication.getExternalStorageAppDirectory()), ".prefs.bak");
            }
        } catch (StorageInfo.DeviceNotAvailableException unused) {
            Log.error(TAG, "External app storage not available while attempting to get backup file");
        }
        return new File("");
    }

    @VisibleForTesting
    static CampaignMetrics restoreReferrer(final Context context) throws Throwable {
        if (!ContextKt.isPermissionGranted(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Log.warning(TAG, "Failed to restore the referrer since storage permission is not granted");
            return new CampaignMetrics("", NO_CLICK_TS, NO_INSTALL_BEGIN_TS);
        }
        File backupFile = getBackupFile();
        if (!backupFile.exists()) {
            Log.warning(TAG, "Failed to restore a referrer. The backup file does not exist.");
            return new CampaignMetrics("", NO_CLICK_TS, NO_INSTALL_BEGIN_TS);
        }
        MReferrerDetails mReferrerDetailsLoadReferrerFromFile = loadReferrerFromFile(context, backupFile);
        String rawReferrer = mReferrerDetailsLoadReferrerFromFile.getRawReferrer();
        Long lValueOf = Long.valueOf(mReferrerDetailsLoadReferrerFromFile.getClickTS());
        Long lValueOf2 = Long.valueOf(mReferrerDetailsLoadReferrerFromFile.getInstallBeginTS());
        if (!isRawReferrerValid(rawReferrer)) {
            Log.warning(TAG, "Failed to parse the back up file.");
            FileUtil.deleteFile(backupFile);
            clearCampaignReferrerInformation(context);
            return new CampaignMetrics("", NO_CLICK_TS, NO_INSTALL_BEGIN_TS);
        }
        setCampaignReferrerInPrefs(context, rawReferrer, lValueOf, lValueOf2);
        return new CampaignMetrics(rawReferrer, lValueOf, lValueOf2);
    }

    @VisibleForTesting
    static MReferrerDetails loadReferrerFromFile(final Context context, final File backupFile) throws Throwable {
        try {
            MReferrerDetails mReferrerDetails = new MReferrerDetails();
            String strLoadFromFile = FileUtil.loadFromFile(backupFile);
            if (strLoadFromFile != null) {
                JSONObject jSONObject = new JSONObject(strLoadFromFile);
                mReferrerDetails.setRawReferrer(jSONObject.getString(context.getString(R.string.setting_key_raw_install_referrer)));
                mReferrerDetails.setClickTS(jSONObject.getLong(context.getString(R.string.setting_key_click_ts)));
                mReferrerDetails.setInstallBeginTS(jSONObject.getLong(context.getString(R.string.setting_key_install_begin_ts)));
                return mReferrerDetails;
            }
        } catch (JSONException e) {
            Log.error(TAG, "Unable to parse the referral loaded from a file", e);
        }
        return new MReferrerDetails();
    }

    @VisibleForTesting
    static void setCampaignReferrerInPrefs(final Context context, final String rawReferrer, final Long clickTS, final Long installBeginTS) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_raw_install_referrer), rawReferrer);
        editorEdit.putLong(context.getString(R.string.setting_key_click_ts), clickTS.longValue());
        editorEdit.putLong(context.getString(R.string.setting_key_install_begin_ts), installBeginTS.longValue());
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void clearCampaignReferrerInformation(final Context context) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        String string = prefs.getString(context.getString(R.string.setting_key_raw_install_referrer), null);
        String string2 = context.getString(R.string.setting_key_click_ts);
        Long l = NO_CLICK_TS;
        Long lValueOf = Long.valueOf(prefs.getLong(string2, l.longValue()));
        String string3 = context.getString(R.string.setting_key_install_begin_ts);
        Long l2 = NO_INSTALL_BEGIN_TS;
        Long lValueOf2 = Long.valueOf(prefs.getLong(string3, l2.longValue()));
        if (string == null && lValueOf == l && lValueOf2 == l2 && !getBackupFile().exists()) {
            setCampaignReferrerInPrefs(context, "", l, l2);
            backupReferrerInformation(context, "", l, l2);
        }
    }

    public static synchronized void fetchAndSaveCampaignReferrer(final Context context) {
        if (hasEmittedCampaignMetric()) {
            return;
        }
        if (mInstallReferrerClient == null) {
            mInstallReferrerClient = InstallReferrerClient.newBuilder(context).build();
        }
        try {
            mInstallReferrerClient.startConnection(new CustomInstallReferrerStateListener(context, mInstallReferrerClient));
        } catch (SecurityException e) {
            Log.error(TAG, "Failed to startConnection on InstallReferrerClient: ", e);
        }
    }

    public static synchronized CampaignMetrics loadCampaignReferrer(final Context context) {
        CampaignMetrics campaignMetricsRestoreReferrer;
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        String string = prefs.getString(context.getString(R.string.setting_key_raw_install_referrer), null);
        String string2 = context.getString(R.string.setting_key_click_ts);
        Long l = NO_CLICK_TS;
        Long lValueOf = Long.valueOf(prefs.getLong(string2, l.longValue()));
        String string3 = context.getString(R.string.setting_key_install_begin_ts);
        Long l2 = NO_INSTALL_BEGIN_TS;
        Long lValueOf2 = Long.valueOf(prefs.getLong(string3, l2.longValue()));
        boolean zExists = getBackupFile().exists();
        if (isRawReferrerValid(string)) {
            campaignMetricsRestoreReferrer = new CampaignMetrics(string, lValueOf, lValueOf2);
            if (!zExists) {
                backupReferrerInformation(context, campaignMetricsRestoreReferrer.mRawReferrer, campaignMetricsRestoreReferrer.mClickTS, campaignMetricsRestoreReferrer.mInstallBeginTS);
            }
        } else {
            campaignMetricsRestoreReferrer = zExists ? restoreReferrer(context) : new CampaignMetrics("", l, l2);
        }
        return campaignMetricsRestoreReferrer;
    }

    public static boolean isRawReferrerValid(final String rawReferrer) {
        if (rawReferrer == null || "".equals(rawReferrer)) {
            return false;
        }
        int i = 0;
        for (String str : rawReferrer.split("&")) {
            String[] strArrSplit = str.split("=");
            if (strArrSplit.length == 0 || strArrSplit.length > 2) {
                return false;
            }
            i++;
        }
        return i > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setHasEmittedCampaignMetric(boolean hasEmittedCampaignMetric) {
        sHasEmittedCampaignMetric = hasEmittedCampaignMetric;
    }

    private static boolean hasEmittedCampaignMetric() {
        return sHasEmittedCampaignMetric;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void backupReferrerInformation(final Context context, final String rawReferrer, final Long click_ts, final Long install_begin_ts) {
        Completable.fromAction(new Action0() { // from class: com.amazon.mp3.service.metrics.CampaignMetrics$$ExternalSyntheticLambda1
            @Override // rx.functions.Action0
            public final void call() {
                CampaignMetrics.storeReferrerInfoInFile(context, rawReferrer, click_ts, install_begin_ts);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public static synchronized void storeReferrerInfoInFile(final Context context, final String rawReferrer, final Long click_ts, final Long install_begin_ts) {
        if (!ContextKt.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Log.debug(TAG, "Cannot write referrer string to disc file since storage permission is not yet granted.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(context.getString(R.string.setting_key_raw_install_referrer), rawReferrer);
            jSONObject.put(context.getString(R.string.setting_key_click_ts), click_ts);
            jSONObject.put(context.getString(R.string.setting_key_install_begin_ts), install_begin_ts);
            File backupFile = getBackupFile();
            if (!FileUtil.saveToFile(jSONObject.toString(), backupFile)) {
                Log.error(TAG, "Failed to save referrer string to file on disc.");
                FileUtil.deleteFile(backupFile);
            } else {
                Log.info(TAG, "Successfully saved referrer string to file on disc.");
            }
        } catch (JSONException unused) {
            Log.error(TAG, "Failed to create a referrer json key for back up.");
        }
    }

    private static class CustomInstallReferrerStateListener implements InstallReferrerStateListener {
        private final InstallReferrerClient mClient;
        private final Context mContext;

        private CustomInstallReferrerStateListener(Context context, InstallReferrerClient client) {
            this.mContext = context;
            this.mClient = client;
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public void onInstallReferrerSetupFinished(int responseCode) {
            if (responseCode != 0) {
                if (responseCode == 1) {
                    Log.error(CampaignMetrics.TAG, "InstallReferrerClient service is currently unavailable");
                    return;
                }
                if (responseCode != 2) {
                    return;
                }
                Log.error(CampaignMetrics.TAG, "InstallReferrerClient service feature is not supported");
                SettingsUtil.setHasFetchedReferrerInformation(this.mContext, true);
                CampaignMetrics.report(this.mContext);
                CampaignMetrics.setHasEmittedCampaignMetric(true);
                CampaignMetrics.clearCampaignReferrerInformation(this.mContext);
                new AmazonAds(this.mContext).identifyCustomer();
                this.mClient.endConnection();
                return;
            }
            Log.info(CampaignMetrics.TAG, "InstallReferrerClient service successfully connected");
            try {
                ReferrerDetails installReferrer = this.mClient.getInstallReferrer();
                String installReferrer2 = installReferrer.getInstallReferrer();
                Long lValueOf = Long.valueOf(installReferrer.getInstallBeginTimestampSeconds());
                Long lValueOf2 = Long.valueOf(installReferrer.getReferrerClickTimestampSeconds());
                Log.info(CampaignMetrics.TAG, "Referrer information received: %s", installReferrer2);
                SharedPreferences prefs = SettingsUtil.getPrefs(this.mContext);
                String string = prefs.getString(this.mContext.getString(R.string.setting_key_raw_install_referrer), null);
                String string2 = this.mContext.getString(R.string.setting_key_click_ts);
                Long l = CampaignMetrics.NO_CLICK_TS;
                Long lValueOf3 = Long.valueOf(prefs.getLong(string2, l.longValue()));
                String string3 = this.mContext.getString(R.string.setting_key_install_begin_ts);
                Long l2 = CampaignMetrics.NO_INSTALL_BEGIN_TS;
                Long lValueOf4 = Long.valueOf(prefs.getLong(string3, l2.longValue()));
                if (string == null && lValueOf3 == l && lValueOf4 == l2 && !CampaignMetrics.getBackupFile().exists()) {
                    if (TextUtils.isEmpty(installReferrer2)) {
                        CampaignMetrics.setCampaignReferrerInPrefs(this.mContext, "", l, l2);
                    } else {
                        CampaignMetrics.setCampaignReferrerInPrefs(this.mContext, installReferrer2, lValueOf2, lValueOf);
                    }
                    CampaignMetrics.backupReferrerInformation(this.mContext, installReferrer2, lValueOf2, lValueOf);
                }
            } catch (RemoteException | IllegalStateException e) {
                Log.error(CampaignMetrics.TAG, "onInstallReferrerSetupFinished failed: ", e);
            }
            CampaignMetrics.report(this.mContext);
            CampaignMetrics.setHasEmittedCampaignMetric(true);
            new AmazonAds(this.mContext).identifyCustomer();
            SettingsUtil.setHasFetchedReferrerInformation(this.mContext, true);
            this.mClient.endConnection();
        }

        @Override // com.android.installreferrer.api.InstallReferrerStateListener
        public void onInstallReferrerServiceDisconnected() {
            Log.error(CampaignMetrics.TAG, "InstallReferrerClient service is disconnected");
        }
    }
}
