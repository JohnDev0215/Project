package com.amazon.mp3.playback.service.exception;

import android.content.Context;
import android.provider.Settings;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringId;
import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackErrorInformation {
    private static final String NO_TEXT = null;
    private static final String TAG = "PlaybackErrorInformation";
    private int mButtonRunnableId;
    private String mButtonUriAction;
    private int mCancelRunnableId;
    private String mDialogBody;
    private String mDialogHeader;
    private String mNegativeButtonText;
    private String mNotificationDetail;
    private String mNotificationHeader;
    private String mPositiveButtonText;
    private String mToastMessage;

    public String getToastMessage() {
        return this.mToastMessage;
    }

    public String getDialogHeader() {
        return this.mDialogHeader;
    }

    public String getDialogBody() {
        return this.mDialogBody;
    }

    public String getPositiveButtonText() {
        return this.mPositiveButtonText;
    }

    public String getNegativeButtonText() {
        return this.mNegativeButtonText;
    }

    public int getButtonRunnableId() {
        return this.mButtonRunnableId;
    }

    public int getCancelRunnableId() {
        return this.mCancelRunnableId;
    }

    public String getButtonUriAction() {
        return this.mButtonUriAction;
    }

    public PlaybackErrorInformation(Context c, String toastMessage, int notificationHeaderId, int notificationDetailId, int dialogHeaderId, int dialogBodyId, int positiveButtonTextId, int buttonRunnableId, int negativeButtonTextId, String buttonRunnableUriAction, int cancelRunnableId) {
        this(c, toastMessage, notificationHeaderId, notificationDetailId, dialogHeaderId, StringId.getOrConvertStringId(dialogBodyId), positiveButtonTextId, buttonRunnableId, negativeButtonTextId, buttonRunnableUriAction, cancelRunnableId);
    }

    public PlaybackErrorInformation(Context c, String toastMessage, int notificationHeaderId, int notificationDetailId, int dialogHeaderId, String dialogBodyMsg, int positiveButtonTextId, int buttonRunnableId, int negativeButtonTextId, String buttonRunnableUriAction, int cancelRunnableId) {
        this.mToastMessage = toastMessage;
        this.mNotificationHeader = getString(c, notificationHeaderId);
        this.mNotificationDetail = getString(c, notificationDetailId);
        this.mDialogHeader = getString(c, dialogHeaderId);
        this.mPositiveButtonText = getString(c, positiveButtonTextId);
        this.mButtonRunnableId = buttonRunnableId;
        this.mNegativeButtonText = getString(c, negativeButtonTextId);
        this.mButtonUriAction = buttonRunnableUriAction;
        this.mCancelRunnableId = cancelRunnableId;
        this.mDialogBody = dialogBodyMsg;
    }

    public PlaybackErrorInformation(Context c, String toastMessage, int notificationHeaderId, int notificationDetailId, int dialogHeaderId, int dialogBodyId, int positiveButtonTextId, int buttonRunnableId, int negativeButtonTextId) {
        this(c, toastMessage, notificationHeaderId, notificationDetailId, dialogHeaderId, StringId.getOrConvertStringId(dialogBodyId), positiveButtonTextId, buttonRunnableId, negativeButtonTextId, (String) null, -1);
    }

    private static String getString(Context c, int id) {
        if (id <= 0) {
            return null;
        }
        return c.getString(id);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.service.exception.PlaybackErrorInformation$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError;

        static {
            int[] iArr = new int[PlaybackException.PlaybackError.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError = iArr;
            try {
                iArr[PlaybackException.PlaybackError.DeviceRooted.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.NetworkFailure.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.BufferUnderrun.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.FileNotFound.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingServiceError.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidFile.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.SignedOut.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InsufficientStorage.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.Payment.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.RegistrationError.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.TOU.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ConnectionLimit.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.Cirrus.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidParameter.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlayback.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.UnknownFailure.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericStreaming.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlaybackRemote.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingPlaybackLocal.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DrmFileNotFound.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.LocalFileNotFound.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingRestriction.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StreamingVideoRestriction.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.PrimeStreamingConcurrency.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericDrm.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericHLSPlayer.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericProgressivePlayer.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericLocalPlayer.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.GenericChromecastPlayer.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ChromecastPlayerHttp.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.ContentNotEligible.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.CustomerNotEligible.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.DMLS.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.SecondaryStorageNotFound.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StationNotFound.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StationCannotStart.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StationCannotContinue.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.StationOutOfTracks.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[PlaybackException.PlaybackError.InvalidStationSeed.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
        }
    }

    public static PlaybackErrorInformation fromPlaybackError(Context c, PlaybackException.PlaybackError error) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$music$media$playback$PlaybackException$PlaybackError[error.ordinal()]) {
            case 1:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_device_rooted_toast), R.string.dmusic_device_rooted_notification_header, R.string.dmusic_device_rooted_notification_detail, R.string.dmusic_device_rooted_dialog_header, R.string.dmusic_device_rooted_dialog_detail, -1, -1, -1);
            case 2:
            case 3:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_network_error_toast), R.string.dmusic_playback_error_network_error_notification_header, R.string.dmusic_playback_error_network_error_notification_detail, R.string.dmusic_playback_error_network_error_dialog_header, ConnectivityUtil.isWifiOnlyDevice(c) ? R.string.dmusic_no_connection_desc_wifi_only : R.string.dmusic_playback_error_network_error_dialog_detail, -1, -1, -1);
            case 4:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_file_not_found_toast), R.string.dmusic_playback_error_file_not_found_notification_header, R.string.dmusic_playback_error_file_not_found_notification_detail, R.string.dmusic_playback_error_file_not_found_dialog_header, R.string.dmusic_playback_error_file_not_found_dialog_detail, R.string.dmusic_playback_error_file_not_found_button_text, 1, -1);
            case 5:
            case 6:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_processing_file_toast), R.string.dmusic_playback_error_processing_file_notification_header, R.string.dmusic_playback_error_processing_file_notification_detail, R.string.dmusic_playback_error_processing_file_dialog_header, R.string.dmusic_playback_error_processing_file_dialog_detail, -1, -1, -1);
            case 7:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_signed_out_toast), R.string.dmusic_playback_error_signed_out_notification_header, R.string.dmusic_playback_error_signed_out_notification_detail, R.string.dmusic_playback_error_signed_out_dialog_header, R.string.dmusic_playback_error_signed_out_dialog_detail, R.string.dmusic_playback_error_signed_out_button_text, 2, -1);
            case 8:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_over_quota_toast), R.string.dmusic_playback_error_over_quota_notification_header, R.string.dmusic_playback_error_over_quota_notification_detail, R.string.dmusic_playback_error_over_quota_dialog_header, R.string.dmusic_playback_error_over_quota_dialog_detail, -1, -1, -1);
            case 9:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_cirrus_error_invalid_payment_method_title), R.string.dmusic_playback_error_invalid_payment_method_notification_header, R.string.dmusic_playback_error_invalid_payment_method_notification_detail, R.string.dmusic_cirrus_error_invalid_payment_method_title, R.string.dmusic_invalid_payment_method_exception_message, R.string.dmusic_cirrus_error_invalid_payment_method_button_label, 5, -1, AmazonApplication.getConfiguration(c).getString("invalid_payment_method_exception_uri"), -1);
            case 10:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_cirrus_error_device_registration_title), R.string.dmusic_playback_error_device_registration_notification_header, R.string.dmusic_playback_error_device_registration_notification_detail, R.string.dmusic_cirrus_error_device_registration_title, R.string.dmusic_device_registration_exception_message, -1, -1, -1);
            case 11:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_tou_vcac_toast), R.string.dmusic_playback_error_tou_vcac_notification_header, R.string.dmusic_playback_error_tou_vcac_notification_detail, R.string.dmusic_playback_error_tou_vcac_dialog_header, R.string.dmusic_playback_error_tou_vcac_dialog_detail, R.string.dmusic_playback_error_tou_vcac_button_text, 3, -1);
            case 12:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_connection_limit_toast), R.string.dmusic_playback_error_connection_limit_notification_header, R.string.dmusic_playback_error_connection_limit_notification_detail, R.string.dmusic_playback_error_connection_limit_dialog_header, R.string.dmusic_playback_error_connection_limit_dialog_detail, -1, -1, -1);
            case 13:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_server_toast), R.string.dmusic_playback_error_server_notification_header, R.string.dmusic_playback_error_server_notification_detail, R.string.dmusic_playback_error_server_dialog_header, R.string.dmusic_playback_error_server_dialog_detail, R.string.dmusic_playback_error_server_button_text, 3, -1);
            case 14:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_invalid_parameter_toast), R.string.dmusic_playback_error_invalid_parameter_notification_header, R.string.dmusic_playback_error_invalid_parameter_notification_detail, R.string.dmusic_playback_error_invalid_parameter_dialog_header, R.string.dmusic_playback_error_invalid_parameter_dialog_detail, R.string.dmusic_playback_error_invalid_parameter_button_text, 3, -1);
            case 15:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_buydrm_player_dialog_detail, -1, -1, -1);
            case 16:
            case 17:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_buydrm_player_dialog_detail, -1, -1, -1);
            case 18:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_error_remote_media_player_dialog_detail, -1, -1, -1);
            case 19:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_error_local_media_player_dialog_detail, -1, -1, -1);
            case 20:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_file_not_found_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_file_not_found_dialog_header, R.string.dmusic_playback_file_not_found_drm_player_dialog_detail, -1, -1, -1);
            case 21:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_file_not_found_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_file_not_found_dialog_header, R.string.dmusic_playback_file_not_found_local_player_dialog_detail, -1, -1, -1);
            case 22:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_streaming_network_toast), R.string.dmusic_playback_error_streaming_network_notification_header, R.string.dmusic_playback_error_streaming_network_notification_detail, R.string.dmusic_playback_error_streaming_network_dialog_header, R.string.dmusic_playback_error_streaming_network_dialog_detail, R.string.dmusic_playback_error_streaming_network_button_text, 4, R.string.dmusic_playback_error_streaming_network_negative_button_text);
            case 23:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_streaming_network_toast), R.string.dmusic_video_playback_error_streaming_network_notification_header, R.string.dmusic_playback_error_streaming_network_notification_detail, R.string.dmusic_video_playback_error_streaming_network_dialog_header, R.string.dmusic_video_playback_error_streaming_network_dialog_detail, R.string.dmusic_playback_error_streaming_network_button_text, 4, R.string.dmusic_playback_error_streaming_network_negative_button_text, (String) null, 10);
            case 24:
                PlaybackErrorInformation playbackErrorInformation = new PlaybackErrorInformation(c, c.getString(R.string.dmusic_prime_streaming_concurrency_toast), R.string.dmusic_prime_streaming_concurrency_notification_title, R.string.dmusic_prime_streaming_concurrency_notification_message, R.string.dmusic_prime_streaming_concurrency_dlg_title, -1, R.string.dmusic_prime_streaming_concurrency_dlg_continue, 6, R.string.dmusic_prime_streaming_concurrency_dlg_cancel, (String) null, 7);
                playbackErrorInformation.mDialogBody = ((PrimeStreamingConcurrencyException) error.getException()).getDialogMessage();
                return playbackErrorInformation;
            case 25:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_buydrm_player_dialog_detail, -1, -1, -1);
            case 26:
                PlaybackErrorInformation playbackErrorInformation2 = new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_with_expiry_troubleshooting_hls_player_dialog_detail, -1, -1, -1);
                if (Settings.Global.getInt(c.getContentResolver(), "auto_time", 0) == 1) {
                    return playbackErrorInformation2;
                }
                Log.error(TAG, "Automatic date and time is not enabled");
                return playbackErrorInformation2;
            case 27:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_progressive_player_dialog_detail, -1, -1, -1);
            case 28:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_local_player_dialog_detail, -1, -1, -1);
            case 29:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_generic_error_chromecast_player_dialog_detail, -1, -1, -1);
            case 30:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_media_player_toast), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_error_chromecast_player_http_dialog_detail, -1, -1, -1);
            case 31:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_customer_not_eligible_toast), R.string.dmusic_playback_error_customer_not_eligible_notification_header, R.string.dmusic_playback_error_customer_not_eligible_notification_detail, R.string.dmusic_playback_error_customer_not_eligible_dialog_header, c.getString(R.string.dmusic_playback_error_customer_not_eligible_dialog_detail), -1, -1, -1, NO_TEXT, 1);
            case 32:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_customer_not_eligible_toast), R.string.dmusic_playback_error_customer_not_eligible_notification_header, R.string.dmusic_playback_error_customer_not_eligible_notification_detail, R.string.dmusic_playback_error_customer_not_eligible_dialog_header, c.getString(R.string.dmusic_playback_error_customer_not_eligible_dialog_detail), -1, -1, -1, NO_TEXT, 8);
            case 33:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_dmls_unknown_toast), R.string.dmusic_playback_error_dmls_unknown_notification_header, R.string.dmusic_playback_error_dmls_unknown_notification_detail, R.string.dmusic_playback_error_dmls_unknown_dialog_header, R.string.dmusic_playback_error_dmls_unknown_dialog_detail, -1, -1, -1);
            case 34:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_external_storage_not_found_toast), R.string.dmusic_external_storage_not_found_notification_header, R.string.dmusic_external_storage_not_found_notification_detail, R.string.dmusic_external_storage_not_found_dialog_header, R.string.dmusic_external_storage_not_found_dialog_detail, -1, -1, -1);
            case 35:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_prime_stations_playback_station_not_found), -1, -1, -1, -1, -1, -1, -1);
            case 36:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_prime_stations_playback_service_down_at_startup), -1, -1, -1, -1, -1, -1, -1);
            case 37:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_prime_stations_playback_service_down), -1, -1, -1, -1, -1, -1, -1);
            case 38:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_prime_stations_playback_out_of_tracks), -1, -1, -1, -1, -1, -1, -1);
            case 39:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_invalid_station_seed), R.string.dmusic_playback_error_media_player_notification_header, R.string.dmusic_playback_error_media_player_notification_detail, R.string.dmusic_playback_error_media_player_dialog_header, R.string.dmusic_playback_error_invalid_station_seed, -1, -1, -1, NO_TEXT, 9);
            default:
                return new PlaybackErrorInformation(c, c.getString(R.string.dmusic_playback_error_could_not_stream_track), -1, -1, -1, -1, -1, -1, -1);
        }
    }

    public String toString() {
        return "PlaybackErrorInformation{mToastMessage='" + this.mToastMessage + "', mNotificationHeader='" + this.mNotificationHeader + "', mNotificationDetail='" + this.mNotificationDetail + "', mDialogHeader='" + this.mDialogHeader + "', mDialogBody='" + this.mDialogBody + "'}";
    }
}
