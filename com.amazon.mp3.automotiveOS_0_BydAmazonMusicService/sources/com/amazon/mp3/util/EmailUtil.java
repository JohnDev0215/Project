package com.amazon.mp3.util;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import com.amazon.music.externalstorage.StorageUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public class EmailUtil {
    private static final String DEBUG_SUPPORT_EMAIL;
    private static final String DEFAULT_SUPPORT_EMAIL;
    private static final String TAG;

    static {
        boolean z = AmazonApplication.BETA;
        DEFAULT_SUPPORT_EMAIL = z ? "android-beta-feedback@amazon.com" : "digital-music-android-feedback@amazon.com";
        DEBUG_SUPPORT_EMAIL = z ? "android-beta-feedback@amazon.com" : "mobileamp@amazon.com";
        TAG = EmailUtil.class.getSimpleName();
    }

    public static void reportError(Context context, String subjectPrefix) {
        if (TextUtils.isEmpty(subjectPrefix)) {
            subjectPrefix = context.getString(R.string.dmusic_setting_contact_us_email_subject);
        }
        createAndSendEmail(context, new String[]{DEBUG_SUPPORT_EMAIL}, subjectPrefix);
    }

    public static void contactCustomerSupport(Context context) {
        createAndSendEmail(context, getSupportEmail(context), context.getString(R.string.dmusic_setting_contact_us_email_subject));
    }

    private static void createAndSendEmail(Context context, String[] emailAddresses, String subjectPrefix) {
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM-dd HH:mm", locale);
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        sendEmailIntent(context, createEmailIntent(context, emailAddresses, subjectPrefix + " | " + Build.MODEL + " | " + simpleDateFormat.format(date) + " | " + simpleDateFormat2.format(date) + " UTC", context.getString(R.string.dmusic_email_body_issue_desc) + "\n\n" + getDebugInformation(context)));
    }

    private static Intent createEmailIntent(Context context, String[] emailAddresses, String subject, String body) {
        Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.EMAIL", emailAddresses);
        ArrayList<File> arrayListCreateLogs = LogFiles.createLogs(context, true);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(arrayListCreateLogs.size());
        Iterator<File> it = arrayListCreateLogs.iterator();
        while (it.hasNext()) {
            arrayList.add(FileProvider.getUriForFile(context, "com.amazon.mp3.logs.fileprovider", it.next()));
        }
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.TEXT", body);
        Iterator<ResolveInfo> it2 = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (it2.hasNext()) {
            String str = it2.next().activityInfo.packageName;
            Iterator<? extends Parcelable> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                context.grantUriPermission(str, (Uri) it3.next(), 1);
            }
        }
        return intent;
    }

    private static void sendEmailIntent(Context context, Intent emailIntent) {
        String str;
        try {
            Intent intentCreateChooser = Intent.createChooser(emailIntent, context.getString(R.string.dmusic_setting_contact_us_email_chooser_title));
            intentCreateChooser.addFlags(268435456);
            context.startActivity(intentCreateChooser);
        } catch (Exception unused) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            String[] stringArrayExtra = emailIntent.getStringArrayExtra("android.intent.extra.EMAIL");
            if (stringArrayExtra != null && stringArrayExtra.length > 0) {
                str = stringArrayExtra[0];
            } else {
                str = DEFAULT_SUPPORT_EMAIL;
            }
            builder.setTitle(R.string.dmusic_setting_contact_us_title);
            builder.setMessage(context.getString(R.string.dmusic_setting_contact_us_no_email_desc, str));
            builder.setNegativeButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
            builder.show();
        }
    }

    private static String[] getSupportEmail(Context context) {
        if (AmazonApplication.BETA) {
            return new String[]{"android-beta-feedback@amazon.com"};
        }
        String string = AmazonApplication.getConfiguration(context).getString("android_support_email", DEFAULT_SUPPORT_EMAIL);
        return Log.getNonCriticalLoggingEnabled() ? new String[]{string, DEBUG_SUPPORT_EMAIL} : new String[]{string};
    }

    public static String getDebugInformation(Context context) {
        ActivityManager activityManager;
        ApplicationAttributes applicationAttributes = ApplicationAttributes.getInstance(context);
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        StorageLocationPreference storageLocationPreference = new StorageLocationPreference(context);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        StorageLocationFileManager storageLocationFileManagerCreate = StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC);
        Locale locale = Locale.US;
        int i = Build.VERSION.SDK_INT;
        String str = String.format(locale, "Model: %s (%s)%nOS version: %s (%s)%nCarrier: %s%nApp version: %s (%s)%nScreen: %s%nAccount Id: %s%nDevice Id: %s%ngetDeviceStorageConfiguration() %s%ncanDeviceWriteToSdCard() %s%ngetPreferredStorageLocation() %s%n", Build.MODEL, context.getResources().getConfiguration().locale.toString(), Build.VERSION.RELEASE, Integer.valueOf(i), ConnectivityUtil.getCarrierName(), applicationAttributes.getVersion(), Integer.valueOf(applicationAttributes.getVersionCode()), ScreenUtil.describeScreen(), accountCredentialStorage.getAccountId(), accountCredentialStorage.getDeviceId(), storageLocationFileManagerCreate.getStorageConfiguration(), Boolean.valueOf(StorageUtil.canDeviceWriteToSdCard(context, Environment.DIRECTORY_MUSIC)), storageLocationPreference.getPreferredStorageLocation());
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (SettingsUtil.getCirrusDownloadPreference(context)) {
            sb.append("Automatic Downloads: Enabled\n");
        }
        if (!ConnectivityUtil.isWifiRequiredForDownloads(context)) {
            sb.append("Download only on WiFi: Disabled\n");
        }
        String streamingNetworkPreference = SettingsUtil.getStreamingNetworkPreference(context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO);
        boolean zIsPermissionGranted = ContextKt.isPermissionGranted(context, "android.permission.READ_EXTERNAL_STORAGE");
        sb.append("READ_EXTERNAL_STORAGE Permission State = ");
        sb.append(zIsPermissionGranted ? "[GRANTED]" : "[DENIED]");
        sb.append('\n');
        boolean zIsPermissionGranted2 = ContextKt.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        sb.append("WRITE_EXTERNAL_STORAGE Permission State = ");
        sb.append(zIsPermissionGranted2 ? "[GRANTED]" : "[DENIED]");
        sb.append('\n');
        boolean zSecondaryStoragePresent = AmazonApplication.getCapabilities().secondaryStoragePresent(context);
        sb.append("Secondary Storage Present State = ");
        sb.append(zSecondaryStoragePresent ? "[MOUNTED]" : "[UNMOUNTED]");
        sb.append('\n');
        if (!streamingNetworkPreference.equals("ALL_NETWORKS")) {
            sb.append("Streaming Preference: ");
            sb.append(streamingNetworkPreference);
            sb.append("\n");
        }
        String multiBitrateStreamingPreferenceForWifi = SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(context);
        if (!multiBitrateStreamingPreferenceForWifi.equals("ALL")) {
            sb.append("Streaming Quality for Wifi: ");
            sb.append(multiBitrateStreamingPreferenceForWifi);
            sb.append("\n");
        }
        String multiBitrateStreamingPreferenceForOTA = SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(context);
        if (!multiBitrateStreamingPreferenceForOTA.equals("ALL")) {
            sb.append("Streaming Quality for OTA: ");
            sb.append(multiBitrateStreamingPreferenceForOTA);
            sb.append("\n");
        }
        if (powerManager.isPowerSaveMode()) {
            sb.append("Battery Saver: Enabled\n");
        }
        if (i >= 28 && (activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)) != null && activityManager.isBackgroundRestricted()) {
            sb.append("Background Restrictions: Enabled\n");
        }
        if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            sb.append("Storage Permission: Not Granted\n");
        }
        sb.append(getBackgroundDataSetting(context));
        sb.append(getEqualizerSettingData(context));
        sb.append("FEATURE FLAG UPLOAD_LOG_AFTER_CRASH: ");
        sb.append(AmazonApplication.getCapabilities().shouldUploadLogAfterCrash());
        Log.info(TAG, sb.toString());
        LogFiles.logDebugInfo(context);
        return sb.toString();
    }

    private static String getBackgroundDataSetting(final Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return "";
        }
        if (!connectivityManager.isActiveNetworkMetered()) {
            return "Device is not on a mobile network. Cannot check the restrict background data setting.\n";
        }
        int restrictBackgroundStatus = connectivityManager.getRestrictBackgroundStatus();
        return (restrictBackgroundStatus == 1 || restrictBackgroundStatus == 2 || restrictBackgroundStatus != 3) ? "" : "Background Data is restricted. Data will not work once the app is backgrounded.\n";
    }

    private static String getEqualizerSettingData(final Context context) {
        StringBuilder sb = new StringBuilder("Equalizer enable status = ");
        boolean z = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("setting_pref_key_equalizer_enable", false);
        boolean zIsAudioEffectsPageSupported = AudioEffectController.isAudioEffectsPageSupported();
        String string = context.getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0).getString("equalizer_user_settings", "");
        sb.append("eq arcus status : ");
        sb.append(zIsAudioEffectsPageSupported);
        sb.append("\t");
        sb.append("eq device status : ");
        sb.append(z);
        sb.append("\t");
        sb.append("eq user pref setting : ");
        sb.append(string);
        sb.append("\n");
        return sb.toString();
    }
}
