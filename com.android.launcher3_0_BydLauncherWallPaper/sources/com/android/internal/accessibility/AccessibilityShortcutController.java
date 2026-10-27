package com.android.internal.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.AudioSystem;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Slog;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import com.android.internal.R;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.accessibility.dialog.AccessibilityTarget;
import com.android.internal.accessibility.dialog.AccessibilityTargetHelper;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.function.pooled.PooledLambda;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class AccessibilityShortcutController {
    public static final String MAGNIFICATION_CONTROLLER_NAME = "com.android.server.accessibility.MagnificationController";
    private static final String TAG = "AccessibilityShortcutController";
    private static Map<ComponentName, ToggleableFrameworkFeatureInfo> sFrameworkShortcutFeaturesMap;
    private AlertDialog mAlertDialog;
    private final Context mContext;
    private boolean mEnabledOnLockScreen;
    public FrameworkObjectProvider mFrameworkObjectProvider = new FrameworkObjectProvider();
    private final Handler mHandler;
    private boolean mIsShortcutEnabled;
    private int mUserId;
    public static final ComponentName COLOR_INVERSION_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "ColorInversion");
    public static final ComponentName DALTONIZER_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "Daltonizer");
    public static final ComponentName MAGNIFICATION_COMPONENT_NAME = new ComponentName("com.android.server.accessibility", "Magnification");
    private static final AudioAttributes VIBRATION_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(11).build();

    @Retention(RetentionPolicy.SOURCE)
    private @interface DialogStaus {
        public static final int NOT_SHOWN = 0;
        public static final int SHOWN = 1;
    }

    public static Map<ComponentName, ToggleableFrameworkFeatureInfo> getFrameworkShortcutFeaturesMap() {
        if (sFrameworkShortcutFeaturesMap == null) {
            ArrayMap arrayMap = new ArrayMap(2);
            arrayMap.put(COLOR_INVERSION_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.ACCESSIBILITY_DISPLAY_INVERSION_ENABLED, "1", AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, R.string.color_inversion_feature_name));
            arrayMap.put(DALTONIZER_COMPONENT_NAME, new ToggleableFrameworkFeatureInfo(Settings.Secure.ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED, "1", AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, R.string.color_correction_feature_name));
            sFrameworkShortcutFeaturesMap = Collections.unmodifiableMap(arrayMap);
        }
        return sFrameworkShortcutFeaturesMap;
    }

    public AccessibilityShortcutController(Context context, Handler handler, int i) {
        this.mContext = context;
        this.mHandler = handler;
        this.mUserId = i;
        ContentObserver contentObserver = new ContentObserver(handler) { // from class: com.android.internal.accessibility.AccessibilityShortcutController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Collection<Uri> collection, int i2, int i3) {
                if (i3 == AccessibilityShortcutController.this.mUserId) {
                    AccessibilityShortcutController.this.onSettingsChanged();
                }
            }
        };
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE), false, contentObserver, -1);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN), false, contentObserver, -1);
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor(Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN), false, contentObserver, -1);
        setCurrentUser(this.mUserId);
    }

    public void setCurrentUser(int i) {
        this.mUserId = i;
        onSettingsChanged();
    }

    public boolean isAccessibilityShortcutAvailable(boolean z) {
        return this.mIsShortcutEnabled && (!z || this.mEnabledOnLockScreen);
    }

    public void onSettingsChanged() {
        boolean zHasShortcutTarget = hasShortcutTarget();
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.mEnabledOnLockScreen = Settings.Secure.getIntForUser(contentResolver, Settings.Secure.ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN, Settings.Secure.getIntForUser(contentResolver, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, this.mUserId), this.mUserId) == 1;
        this.mIsShortcutEnabled = zHasShortcutTarget;
    }

    public void performAccessibilityShortcut() {
        Slog.d(TAG, "Accessibility shortcut activated");
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int currentUser = ActivityManager.getCurrentUser();
        int intForUser = Settings.Secure.getIntForUser(contentResolver, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, currentUser);
        Vibrator vibrator = (Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(ArrayUtils.convertToLongArray(this.mContext.getResources().getIntArray(R.array.config_longPressVibePattern)), -1, VIBRATION_ATTRIBUTES);
        }
        if (intForUser == 0) {
            AlertDialog alertDialogCreateShortcutWarningDialog = createShortcutWarningDialog(currentUser);
            this.mAlertDialog = alertDialogCreateShortcutWarningDialog;
            if (alertDialogCreateShortcutWarningDialog == null) {
                return;
            }
            if (!performTtsPrompt(alertDialogCreateShortcutWarningDialog)) {
                playNotificationTone();
            }
            Window window = this.mAlertDialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.type = 2009;
            window.setAttributes(attributes);
            this.mAlertDialog.show();
            Settings.Secure.putIntForUser(contentResolver, Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 1, currentUser);
            return;
        }
        playNotificationTone();
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mAlertDialog = null;
        }
        showToast();
        this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).performAccessibilityShortcut();
    }

    private void showToast() {
        String shortcutFeatureDescription;
        AccessibilityServiceInfo infoForTargetService = getInfoForTargetService();
        if (infoForTargetService == null || (shortcutFeatureDescription = getShortcutFeatureDescription(false)) == null) {
            return;
        }
        boolean z = (infoForTargetService.flags & 256) != 0;
        boolean zIsServiceEnabled = isServiceEnabled(infoForTargetService);
        if (infoForTargetService.getResolveInfo().serviceInfo.applicationInfo.targetSdkVersion > 29 && z && zIsServiceEnabled) {
            return;
        }
        this.mFrameworkObjectProvider.makeToastFromText(this.mContext, String.format(this.mContext.getString(zIsServiceEnabled ? R.string.accessibility_shortcut_disabling_service : R.string.accessibility_shortcut_enabling_service), shortcutFeatureDescription), 1).show();
    }

    private AlertDialog createShortcutWarningDialog(final int i) {
        List<AccessibilityTarget> targets = AccessibilityTargetHelper.getTargets(this.mContext, 1);
        if (targets.size() == 0) {
            return null;
        }
        FrameworkObjectProvider frameworkObjectProvider = this.mFrameworkObjectProvider;
        return frameworkObjectProvider.getAlertDialogBuilder(frameworkObjectProvider.getSystemUiContext()).setTitle(getShortcutWarningTitle(targets)).setMessage(getShortcutWarningMessage(targets)).setCancelable(false).setNegativeButton(R.string.accessibility_shortcut_on, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.accessibility_shortcut_off, new DialogInterface.OnClickListener() { // from class: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$mGjWih2o7RzI-5ffKpEKm0yS2U4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                this.f$0.lambda$createShortcutWarningDialog$0$AccessibilityShortcutController(i, dialogInterface, i2);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$CSlXQCG1fgAUoemLosZu6MvIGEc
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$createShortcutWarningDialog$1$AccessibilityShortcutController(i, dialogInterface);
            }
        }).create();
    }

    public /* synthetic */ void lambda$createShortcutWarningDialog$0$AccessibilityShortcutController(int i, DialogInterface dialogInterface, int i2) {
        Settings.Secure.putStringForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE, "", i);
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, i);
    }

    public /* synthetic */ void lambda$createShortcutWarningDialog$1$AccessibilityShortcutController(int i, DialogInterface dialogInterface) {
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN, 0, i);
    }

    private String getShortcutWarningTitle(List<AccessibilityTarget> list) {
        if (list.size() == 1) {
            return this.mContext.getString(R.string.accessibility_shortcut_single_service_warning_title, list.get(0).getLabel());
        }
        return this.mContext.getString(R.string.accessibility_shortcut_multiple_service_warning_title);
    }

    private String getShortcutWarningMessage(List<AccessibilityTarget> list) {
        if (list.size() == 1) {
            return this.mContext.getString(R.string.accessibility_shortcut_single_service_warning, list.get(0).getLabel());
        }
        StringBuilder sb = new StringBuilder();
        Iterator<AccessibilityTarget> it = list.iterator();
        while (it.hasNext()) {
            sb.append(this.mContext.getString(R.string.accessibility_shortcut_multiple_service_list, it.next().getLabel()));
        }
        return this.mContext.getString(R.string.accessibility_shortcut_multiple_service_warning, sb.toString());
    }

    private AccessibilityServiceInfo getInfoForTargetService() {
        ComponentName shortcutTargetComponentName = getShortcutTargetComponentName();
        if (shortcutTargetComponentName == null) {
            return null;
        }
        return this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getInstalledServiceInfoWithComponentName(shortcutTargetComponentName);
    }

    private String getShortcutFeatureDescription(boolean z) {
        ComponentName shortcutTargetComponentName = getShortcutTargetComponentName();
        if (shortcutTargetComponentName == null) {
            return null;
        }
        ToggleableFrameworkFeatureInfo toggleableFrameworkFeatureInfo = getFrameworkShortcutFeaturesMap().get(shortcutTargetComponentName);
        if (toggleableFrameworkFeatureInfo != null) {
            return toggleableFrameworkFeatureInfo.getLabel(this.mContext);
        }
        AccessibilityServiceInfo installedServiceInfoWithComponentName = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getInstalledServiceInfoWithComponentName(shortcutTargetComponentName);
        if (installedServiceInfoWithComponentName == null) {
            return null;
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        String string = installedServiceInfoWithComponentName.getResolveInfo().loadLabel(packageManager).toString();
        CharSequence charSequenceLoadSummary = installedServiceInfoWithComponentName.loadSummary(packageManager);
        return (!z || TextUtils.isEmpty(charSequenceLoadSummary)) ? string : String.format("%s\n%s", string, charSequenceLoadSummary);
    }

    private boolean isServiceEnabled(AccessibilityServiceInfo accessibilityServiceInfo) {
        return this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getEnabledAccessibilityServiceList(-1).contains(accessibilityServiceInfo);
    }

    private boolean hasFeatureLeanback() {
        return this.mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNotificationTone() {
        int i = hasFeatureLeanback() ? 11 : 10;
        Ringtone ringtone = this.mFrameworkObjectProvider.getRingtone(this.mContext, Settings.System.DEFAULT_NOTIFICATION_URI);
        if (ringtone != null) {
            ringtone.setAudioAttributes(new AudioAttributes.Builder().setUsage(i).build());
            ringtone.play();
        }
    }

    private boolean performTtsPrompt(AlertDialog alertDialog) {
        String shortcutFeatureDescription = getShortcutFeatureDescription(false);
        AccessibilityServiceInfo infoForTargetService = getInfoForTargetService();
        if (TextUtils.isEmpty(shortcutFeatureDescription) || infoForTargetService == null || (infoForTargetService.flags & 1024) == 0) {
            return false;
        }
        final TtsPrompt ttsPrompt = new TtsPrompt(shortcutFeatureDescription);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$d7VqGIQyi3r_CrXZJKyex6Wr0Og
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ttsPrompt.dismiss();
            }
        });
        return true;
    }

    private boolean hasShortcutTarget() {
        String stringForUser = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), Settings.Secure.ACCESSIBILITY_SHORTCUT_TARGET_SERVICE, this.mUserId);
        if (stringForUser == null) {
            stringForUser = this.mContext.getString(R.string.config_defaultAccessibilityService);
        }
        return !TextUtils.isEmpty(stringForUser);
    }

    private ComponentName getShortcutTargetComponentName() {
        List<String> accessibilityShortcutTargets = this.mFrameworkObjectProvider.getAccessibilityManagerInstance(this.mContext).getAccessibilityShortcutTargets(1);
        if (accessibilityShortcutTargets.size() != 1) {
            return null;
        }
        return ComponentName.unflattenFromString(accessibilityShortcutTargets.get(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    class TtsPrompt implements TextToSpeech.OnInitListener {
        private static final int RETRY_MILLIS = 1000;
        private boolean mDismiss;
        private final CharSequence mText;
        private TextToSpeech mTts;
        private int mRetryCount = 3;
        private boolean mLanguageReady = false;

        TtsPrompt(String str) {
            this.mText = AccessibilityShortcutController.this.mContext.getString(R.string.accessibility_shortcut_spoken_feedback, str);
            this.mTts = AccessibilityShortcutController.this.mFrameworkObjectProvider.getTextToSpeech(AccessibilityShortcutController.this.mContext, this);
        }

        public void dismiss() {
            this.mDismiss = true;
            AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.accessibility.-$$Lambda$EPlwpRpkwCOLtivKMUD0L1VPCFg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((TextToSpeech) obj).shutdown();
                }
            }, this.mTts));
        }

        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public void onInit(int i) {
            if (i == 0) {
                AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage($$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU.INSTANCE, this));
            } else {
                Slog.d(AccessibilityShortcutController.TAG, "Tts init fail, status=" + Integer.toString(i));
                AccessibilityShortcutController.this.playNotificationTone();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void play() {
            if (this.mDismiss || this.mTts.speak(this.mText, 0, null, null) == 0) {
                return;
            }
            Slog.d(AccessibilityShortcutController.TAG, "Tts play fail");
            AccessibilityShortcutController.this.playNotificationTone();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void waitForTtsReady() {
            if (this.mDismiss) {
                return;
            }
            boolean z = false;
            if (!this.mLanguageReady) {
                int language = this.mTts.setLanguage(Locale.getDefault());
                this.mLanguageReady = (language == -1 || language == -2) ? false : true;
            }
            if (this.mLanguageReady) {
                Voice voice = this.mTts.getVoice();
                if (voice != null && voice.getFeatures() != null && !voice.getFeatures().contains(TextToSpeech.Engine.KEY_FEATURE_NOT_INSTALLED)) {
                    z = true;
                }
                if (z) {
                    AccessibilityShortcutController.this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.accessibility.-$$Lambda$AccessibilityShortcutController$TtsPrompt$nTJXhJ166ZZd_5nPL3DkmRf_KpA
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((AccessibilityShortcutController.TtsPrompt) obj).play();
                        }
                    }, this));
                    return;
                }
            }
            int i = this.mRetryCount;
            if (i == 0) {
                Slog.d(AccessibilityShortcutController.TAG, "Tts not ready to speak.");
                AccessibilityShortcutController.this.playNotificationTone();
            } else {
                this.mRetryCount = i - 1;
                AccessibilityShortcutController.this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage($$Lambda$AccessibilityShortcutController$TtsPrompt$rSvbPKgeiYtMTwvNC2DcnXNlUvU.INSTANCE, this), 1000L);
            }
        }
    }

    public static class ToggleableFrameworkFeatureInfo {
        private int mIconDrawableId;
        private final int mLabelStringResourceId;
        private final String mSettingKey;
        private final String mSettingOffValue;
        private final String mSettingOnValue;

        ToggleableFrameworkFeatureInfo(String str, String str2, String str3, int i) {
            this.mSettingKey = str;
            this.mSettingOnValue = str2;
            this.mSettingOffValue = str3;
            this.mLabelStringResourceId = i;
        }

        public String getSettingKey() {
            return this.mSettingKey;
        }

        public String getSettingOnValue() {
            return this.mSettingOnValue;
        }

        public String getSettingOffValue() {
            return this.mSettingOffValue;
        }

        public String getLabel(Context context) {
            return context.getString(this.mLabelStringResourceId);
        }
    }

    public static class FrameworkObjectProvider {
        public AccessibilityManager getAccessibilityManagerInstance(Context context) {
            return AccessibilityManager.getInstance(context);
        }

        public AlertDialog.Builder getAlertDialogBuilder(Context context) {
            return new AlertDialog.Builder(context);
        }

        public Toast makeToastFromText(Context context, CharSequence charSequence, int i) {
            return Toast.makeText(context, charSequence, i);
        }

        public Context getSystemUiContext() {
            return ActivityThread.currentActivityThread().getSystemUiContext();
        }

        public TextToSpeech getTextToSpeech(Context context, TextToSpeech.OnInitListener onInitListener) {
            return new TextToSpeech(context, onInitListener);
        }

        public Ringtone getRingtone(Context context, Uri uri) {
            return RingtoneManager.getRingtone(context, uri);
        }
    }
}
