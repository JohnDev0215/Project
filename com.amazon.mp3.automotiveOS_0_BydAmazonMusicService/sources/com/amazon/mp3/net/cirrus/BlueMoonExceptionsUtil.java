package com.amazon.mp3.net.cirrus;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.BlueMoonExceptionActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.BaseAccountValidationWebView;
import com.amazon.mp3.library.activity.LibraryDeviceAuthorizationWebViewActivity;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.EmailUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.music.media.playback.ControlSource;

/* JADX INFO: loaded from: classes3.dex */
public class BlueMoonExceptionsUtil {
    private static final BaseActivity BASE_ACTIVITY_NULL = null;
    private static BaseActivity sBaseActivity = null;
    private static volatile boolean sBlueMoonExceptionDialogShown = false;
    private static int sCirrusExceptionDialogId = -1;

    public enum DetectType {
        Detect,
        Dont_Detect,
        Update_Settings_Only,
        Detect_And_Update_Settings
    }

    private static String returnNullString(Object object) {
        return object != null ? "non-null" : "null";
    }

    protected BlueMoonExceptionsUtil() {
    }

    private static void setBlueMoonExceptionDialogShown(boolean shown, int dialogId) {
        sBlueMoonExceptionDialogShown = shown;
        setCirrusExceptionDialogId(dialogId);
    }

    public static boolean isBlueMoonExceptionDialogShown() {
        return sBlueMoonExceptionDialogShown;
    }

    private static void setCirrusExceptionDialogId(int dialogId) {
        sCirrusExceptionDialogId = dialogId;
    }

    private static int getCirrusExceptionDialogId() {
        return sCirrusExceptionDialogId;
    }

    public static boolean processBlueMoonException(Context context) {
        if (!isBlueMoonException()) {
            return false;
        }
        context.startActivity(BlueMoonExceptionActivity.getStartIntent(context));
        return true;
    }

    public static boolean isBlueMoonException() {
        return CirrusExceptions.AccountNotVerifiedException.hasBeenDetected() || CirrusExceptions.DeviceException.hasBeenDetected() || CirrusExceptions.DeviceNotAuthorizedException.hasBeenDetected() || CirrusExceptions.DependencyFailureException.hasBeenDetected() || CirrusExceptions.DependencyTimeoutException.hasBeenDetected();
    }

    public static boolean detectBlueMoonException(CirrusExceptions.CirrusException ex, DetectType detectType) {
        boolean z = true;
        if (ex instanceof CirrusExceptions.AccountNotVerifiedException) {
            if (detectType == DetectType.Detect || detectType == DetectType.Detect_And_Update_Settings) {
                CirrusExceptions.AccountNotVerifiedException.setDetected(true);
                if (detectType == DetectType.Detect_And_Update_Settings) {
                    DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
                }
            } else if (detectType == DetectType.Update_Settings_Only) {
                DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
            }
        } else if (ex instanceof CirrusExceptions.DeviceException) {
            if (detectType == DetectType.Detect || detectType == DetectType.Detect_And_Update_Settings) {
                CirrusExceptions.DeviceException.setDetected(true);
                DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
            } else if (detectType == DetectType.Update_Settings_Only) {
                DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
            }
        } else if (!(ex instanceof CirrusExceptions.DeviceNotAuthorizedException)) {
            z = false;
        } else if (detectType == DetectType.Detect || detectType == DetectType.Detect_And_Update_Settings) {
            CirrusExceptions.DeviceNotAuthorizedException.setDetected(true);
            DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
        } else if (detectType == DetectType.Update_Settings_Only) {
            DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
        }
        determineBroadcastDisplayDialogEvent();
        return z;
    }

    public static void disableBlueMoonException() {
        if (isBlueMoonException()) {
            if (CirrusExceptions.AccountNotVerifiedException.hasBeenDetected()) {
                CirrusExceptions.AccountNotVerifiedException.setDetected(false);
                return;
            }
            if (CirrusExceptions.DeviceException.hasBeenDetected()) {
                CirrusExceptions.DeviceException.setDetected(false);
                return;
            }
            if (CirrusExceptions.DeviceNotAuthorizedException.hasBeenDetected()) {
                CirrusExceptions.DeviceNotAuthorizedException.setDetected(false);
            } else if (CirrusExceptions.DependencyFailureException.hasBeenDetected()) {
                CirrusExceptions.DependencyFailureException.setDetected(false);
            } else if (CirrusExceptions.DependencyTimeoutException.hasBeenDetected()) {
                CirrusExceptions.DependencyTimeoutException.setDetected(false);
            }
        }
    }

    public static void disableSelectedBlueMoonException(Context context) {
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "disableSelectedBlueMoonException: Disable BlueMoon exception if criteria is fulfill.");
        if (isBlueMoonException()) {
            if (DeviceAuthorizationManager.getInstance().isAuthorized()) {
                CirrusExceptions.DeviceException.setDetected(false);
                CirrusExceptions.DeviceNotAuthorizedException.setDetected(false);
            }
            if (WebViewUtil.isAccountValidationValid(context)) {
                CirrusExceptions.AccountNotVerifiedException.setDetected(false);
            }
        }
    }

    private static void setBaseActivity(BaseActivity baseActivity) {
        sBaseActivity = baseActivity;
    }

    public static BaseActivity getBaseActivity() {
        return sBaseActivity;
    }

    private static void determineBroadcastDisplayDialogEvent() {
        if (isBlueMoonException() && !isBlueMoonExceptionDialogShown()) {
            Intent intent = new Intent("com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.Display_BlueMoon_Dialog_Broadcast");
            BaseActivity baseActivity = getBaseActivity();
            if (baseActivity != null) {
                if (!WebViewUtil.isWebViewActivity(baseActivity)) {
                    Log.debug("BlueMoon_BlueMoonExceptionsUtil", "determineBroadcastDisplayDialogEvent: Broadcasting display dialog event");
                    baseActivity.sendBroadcast(intent);
                    return;
                } else {
                    Log.debug("BlueMoon_BlueMoonExceptionsUtil", "determineBroadcastDisplayDialogEvent: Cannot broadcast display dialog event, current activity is a webview");
                    return;
                }
            }
            return;
        }
        if (isBlueMoonException() && isBlueMoonExceptionDialogShown()) {
            Log.debug("BlueMoon_BlueMoonExceptionsUtil", "determineBroadcastDisplayDialogEvent: There is a BlueMoon exception and dialog is shown, need to disable the exception");
            disableBlueMoonException();
        }
    }

    public static void processBroadcastDisplayDialogEvent() {
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "processBroadcastDisplayDialogEvent: Processing broadcast display dialog event");
        BaseActivity baseActivity = getBaseActivity();
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "processBroadcastDisplayDialogEvent: activity is %s and dialog shown is %s", returnNullString(baseActivity), Boolean.valueOf(isBlueMoonExceptionDialogShown()));
        if (baseActivity == null || isBlueMoonExceptionDialogShown()) {
            return;
        }
        displayBlueMoonExceptionDialog(baseActivity);
    }

    private static boolean isActivityAllowToDisplayDialog(BaseActivity activity) {
        if (activity == null) {
            return false;
        }
        return activity.shouldDisplayBluemoonExceptions();
    }

    public static void displayBlueMoonExceptionDialog(BaseActivity activity) {
        String shortString = activity.getComponentName().toShortString();
        setBaseActivity(activity);
        if (WebViewUtil.isWebViewActivity(activity) || !isBlueMoonException()) {
            return;
        }
        if (isActivityAllowToDisplayDialog(activity)) {
            if (CirrusExceptions.AccountNotVerifiedException.hasBeenDetected()) {
                CirrusExceptions.AccountNotVerifiedException.setDetected(false);
                addFragmentDialog(activity, 30032, createAccountNotVerifiedExceptionDialog(activity));
                return;
            }
            if (CirrusExceptions.DeviceException.hasBeenDetected()) {
                CirrusExceptions.DeviceException.setDetected(false);
                addFragmentDialog(activity, 30033, createDeviceExceptionDialog(activity, 30033));
                return;
            }
            if (CirrusExceptions.DeviceNotAuthorizedException.hasBeenDetected()) {
                handleDeviceNotAuthorizedException(activity);
                return;
            }
            if (CirrusExceptions.DependencyFailureException.hasBeenDetected()) {
                CirrusExceptions.DependencyFailureException.setDetected(false);
                addFragmentDialog(activity, 30035, createDependencyExceptionDialog(activity, 30035));
                return;
            } else {
                if (CirrusExceptions.DependencyTimeoutException.hasBeenDetected()) {
                    CirrusExceptions.DependencyTimeoutException.setDetected(false);
                    addFragmentDialog(activity, 30036, createDependencyExceptionDialog(activity, 30036));
                    return;
                }
                return;
            }
        }
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "displayBlueMoonExceptionDialog: exception (%s) exist but did not display the dialog", shortString);
    }

    private static void handleDeviceNotAuthorizedException(final BaseActivity activity) {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... params) {
                CirrusExceptions.DeviceNotAuthorizedException.setDetected(false);
                return Boolean.valueOf(DeviceAuthorizationManager.getInstance().autoAuthorize(activity));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean authorized) {
                super.onPostExecute(authorized);
                if (authorized.booleanValue() || BlueMoonExceptionsUtil.sBaseActivity == null || BlueMoonExceptionsUtil.sBaseActivity.isFinishing()) {
                    return;
                }
                BlueMoonExceptionsUtil.addFragmentDialog(BlueMoonExceptionsUtil.sBaseActivity, 30034, BlueMoonExceptionsUtil.createDeviceExceptionDialog(BlueMoonExceptionsUtil.sBaseActivity, 30034));
            }
        }.execute(new Void[0]);
    }

    public static void dismissBlueMoonExceptionDialog(BaseActivity activity) {
        setBaseActivity(BASE_ACTIVITY_NULL);
        if (isBlueMoonExceptionDialogShown()) {
            int cirrusExceptionDialogId = getCirrusExceptionDialogId();
            reenableBlueMoonException(cirrusExceptionDialogId);
            removeFragmentDialog(activity, cirrusExceptionDialogId);
        }
    }

    private static void reenableBlueMoonException(int dialogId) {
        if (30032 == dialogId) {
            CirrusExceptions.AccountNotVerifiedException.setDetected(true);
        } else if (30033 == dialogId) {
            CirrusExceptions.DeviceException.setDetected(true);
        } else if (30034 == dialogId) {
            CirrusExceptions.DeviceNotAuthorizedException.setDetected(true);
        } else if (30035 == dialogId) {
            CirrusExceptions.DependencyFailureException.setDetected(true);
        } else if (30036 == dialogId) {
            CirrusExceptions.DependencyTimeoutException.setDetected(true);
        }
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "reenableBlueMoonException: re-enable exception (%s)", Boolean.valueOf(isBlueMoonException()));
    }

    private static Dialog createAccountNotVerifiedExceptionDialog(final BaseActivity activity) {
        stopPlaybackService(activity);
        return new BauhausDialogBuilder(activity).setCancelable(false).setTitle(R.string.dmusic_cirrus_error_account_not_verified_exception_title).setMessage(R.string.dmusic_cirrus_error_account_not_verified_exception_message).setPositiveButton(R.string.dmusic_button_continue, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, 30032);
                activity.startActivity(BaseAccountValidationWebView.getStartIntent(activity));
            }
        }).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, 30032);
                BlueMoonExceptionsUtil.startMusicHomeActivity(activity);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Dialog createDeviceExceptionDialog(final BaseActivity activity, final int dialogId) {
        if (dialogId != 30033 && dialogId != 30034) {
            return null;
        }
        final boolean zStopPlaybackService = stopPlaybackService(activity);
        DeviceAuthorizationManager.getInstance().setDeviceNotAuthorized();
        int i = R.string.dmusic_cirrus_error_device_exception_title;
        int i2 = R.string.dmusic_cirrus_error_device_exception_message;
        if (dialogId == 30034) {
            i = R.string.dmusic_cirrus_error_device_not_authorized_exception_title;
            i2 = R.string.dmusic_cirrus_error_device_not_authorized_exception_message;
        }
        return new BauhausDialogBuilder(activity).setCancelable(false).setTitle(i).setMessage(i2).setPositiveButton(R.string.dmusic_button_continue, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, dialogId);
                Intent startIntent = LibraryDeviceAuthorizationWebViewActivity.getStartIntent(activity);
                startIntent.putExtra("com.amazon.mp3.util.FROM_PLAYBACK", zStopPlaybackService);
                activity.startActivity(startIntent);
            }
        }).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, dialogId);
                if (dialogId == 30034) {
                    BlueMoonExceptionsUtil.startMusicHomeActivity(activity);
                } else {
                    EmailUtil.contactCustomerSupport(activity);
                    activity.finish();
                }
            }
        }).create();
    }

    private static Dialog createDependencyExceptionDialog(final BaseActivity activity, final int dialogId) {
        if (dialogId != 30035 && dialogId != 30036) {
            return null;
        }
        stopPlaybackService(activity);
        int i = R.string.dmusic_cirrus_dependency_failure_exception_title;
        int i2 = R.string.dmusic_cirrus_dependency_failure_exception_message;
        if (dialogId == 30036) {
            i = R.string.dmusic_cirrus_dependency_timeout_exception_title;
            i2 = R.string.dmusic_cirrus_dependency_timeout_exception_message;
        }
        return new BauhausDialogBuilder(activity).setCancelable(false).setTitle(i).setMessage(i2).setPositiveButton(R.string.dmusic_button_continue, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, dialogId);
                activity.finish();
            }
        }).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                BlueMoonExceptionsUtil.removeFragmentDialog(activity, dialogId);
                EmailUtil.contactCustomerSupport(activity);
                activity.finish();
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addFragmentDialog(BaseActivity activity, int dialogId, Dialog dialog) {
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "addFragmentDialog: is BlueMoon exception dialog currently display (%s)", Boolean.valueOf(isBlueMoonExceptionDialogShown()));
        if (isBlueMoonExceptionDialogShown()) {
            return;
        }
        setBlueMoonExceptionDialogShown(true, dialogId);
        activity.showFragmentDialog(dialogId, dialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void removeFragmentDialog(BaseActivity activity, int dialogId) {
        Log.debug("BlueMoon_BlueMoonExceptionsUtil", "removeFragmentDialog: dimiss the BlueMoon exception dialog");
        setBlueMoonExceptionDialogShown(false, -1);
        activity.removeFragmentDialog(dialogId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startMusicHomeActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) MusicHomeActivity.class));
    }

    private static boolean isCirrusStreaming(BaseActivity activity) {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        return controller.getPlayStatus().willPlay() && !NowPlayingUtil.isDownloaded(controller.getCurrentMediaItem());
    }

    private static boolean stopPlaybackService(BaseActivity activity) {
        if (!isCirrusStreaming(activity)) {
            return false;
        }
        Intent intent = new Intent("com.amazon.mp3.playerservicecommand.stop");
        intent.putExtra("com.amazon.mp3.playstate_mutation_reason", PlayStateMutationReason.ACCOUNT_NOT_VERIFIED);
        activity.sendBroadcast(intent);
        return true;
    }
}
