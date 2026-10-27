package com.amazon.mp3.casting;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.metrics.mts.event.types.TerminationReason;

/* JADX INFO: loaded from: classes3.dex */
public class CastingAlertDialogActivity extends DialogActivity {
    private String mDeviceName;
    private int mMessageId;
    private int mNegativeButtonId;
    private int mPositiveButtonId;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private final DialogInterface.OnClickListener okOnClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.casting.CastingAlertDialogActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialogInterface, final int i) {
            String stringExtra = CastingAlertDialogActivity.this.getIntent().getStringExtra("casting_content_name");
            if (stringExtra != null) {
                switch (stringExtra) {
                    case "casting_content_type_disconnected":
                        CastingUIMetricsLogger.recordDeviceDisconnectedDialogOkClick();
                        break;
                    case "casting_content_type_aom":
                        CastingUIMetricsLogger.recordAlexaUnavailableDialogOkClick();
                        break;
                    case "casting_content_type_unavailable":
                        CastingUIMetricsLogger.recordContentUnavailableDialogOkClick(CastingUtil.getDeviceCategory());
                        break;
                    case "casting_content_type_unsupported":
                        CastingUIMetricsLogger.recordUnsupportedContentDialogOkClick();
                        break;
                }
            }
            CastingAlertDialogActivity.this.finish();
        }
    };
    private final DialogInterface.OnClickListener disconnectOnClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.casting.CastingAlertDialogActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialogInterface, final int i) {
            CastingSessionManager.getInstance().disconnect(TerminationReason.USER_DISCONNECT);
            String stringExtra = CastingAlertDialogActivity.this.getIntent().getStringExtra("casting_content_name");
            if (stringExtra != null) {
                if (stringExtra.equals("casting_content_type_aom")) {
                    CastingUIMetricsLogger.recordAlexaUnavailableDialogDisconnectClick();
                } else if (stringExtra.equals("casting_content_type_unavailable")) {
                    CastingUIMetricsLogger.recordContentUnavailableDialogDisconnectClick();
                }
            }
            CastingAlertDialogActivity.this.finish();
        }
    };

    public static Intent getCastingAlertIntentForUnsupportedContent(final Context context) {
        CastingDevice castingDevice = CastingUtil.isCasting() ? CastingSessionManager.getInstance().getCastingDevice() : null;
        Intent intent = new Intent(context, (Class<?>) CastingAlertDialogActivity.class);
        intent.putExtra("casting_content_name", "casting_content_type_unsupported");
        if (AnonymousClass3.$SwitchMap$com$amazon$music$casting$CastingCategory[CastingUtil.getDeviceCategory().ordinal()] == 1) {
            intent.putExtra("casting_extra_message_id", R.string.casting_unsupported_content);
            intent.putExtra("casting_extra_device_name", castingDevice.getName());
            intent.putExtra("casting_extra_negative_button_id", R.string.ok);
        } else {
            intent.putExtra("casting_extra_message_id", R.string.casting_unsupported_content_on_device);
            intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        }
        intent.setFlags(268435456);
        return intent;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.casting.CastingAlertDialogActivity$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$casting$CastingCategory;

        static {
            int[] iArr = new int[CastingCategory.values().length];
            $SwitchMap$com$amazon$music$casting$CastingCategory = iArr;
            try {
                iArr[CastingCategory.ALEXA_1P.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$casting$CastingCategory[CastingCategory.CHROMECAST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static Intent getCastingAlertIntentForContentUnavailable(final Context context) {
        CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
        Intent intent = new Intent(context, (Class<?>) CastingAlertDialogActivity.class);
        intent.putExtra("casting_content_name", "casting_content_type_unavailable");
        int i = AnonymousClass3.$SwitchMap$com$amazon$music$casting$CastingCategory[CastingUtil.getDeviceCategory().ordinal()];
        if (i == 1) {
            intent.putExtra("casting_extra_message_id", R.string.casting_content_not_available_alexa);
            intent.putExtra("casting_extra_positive_button_id", R.string.casting_disconnect_from_text);
            intent.putExtra("casting_extra_positive_button_listener_key", "casting_disconnect_listener_key");
            intent.putExtra("casting_extra_device_name", castingDevice.getName());
            intent.putExtra("casting_extra_negative_button_id", R.string.cancel);
        } else if (i == 2) {
            intent.putExtra("casting_extra_message_id", R.string.casting_content_not_available_chromecast);
            intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        } else {
            intent.putExtra("casting_extra_message_id", R.string.casting_content_not_available);
            intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        }
        intent.setFlags(268435456);
        return intent;
    }

    public static Intent getCastingAlertIntentForAoM(final Context context) {
        CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
        Intent intent = new Intent(context, (Class<?>) CastingAlertDialogActivity.class);
        intent.putExtra("casting_content_name", "casting_content_type_aom");
        if (AnonymousClass3.$SwitchMap$com$amazon$music$casting$CastingCategory[CastingUtil.getDeviceCategory().ordinal()] == 1) {
            intent.putExtra("casting_extra_message_id", R.string.casting_aom_not_available_alexa);
            intent.putExtra("casting_extra_positive_button_id", R.string.casting_disconnect_from_text);
            intent.putExtra("casting_extra_positive_button_listener_key", "casting_disconnect_listener_key");
            intent.putExtra("casting_extra_device_name", castingDevice.getName());
            intent.putExtra("casting_extra_negative_button_id", R.string.cancel);
        } else {
            intent.putExtra("casting_extra_message_id", R.string.casting_aom_not_available);
            intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        }
        return intent;
    }

    public static Intent getCastingAlertIntentForOfflineDevice(final Context context) {
        Intent intent = new Intent(context, (Class<?>) CastingAlertDialogActivity.class);
        intent.putExtra("casting_extra_message_id", R.string.casting_device_disconnected_text);
        intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        intent.putExtra("casting_content_name", "casting_content_type_disconnected");
        intent.setFlags(268435456);
        return intent;
    }

    public static Intent getCastingAlertIntentForUnknownError(final Context context) {
        Intent intent = new Intent(context, (Class<?>) CastingAlertDialogActivity.class);
        intent.putExtra("casting_extra_message_id", R.string.alexa_generic_reason);
        intent.putExtra("casting_extra_positive_button_id", R.string.ok);
        intent.setFlags(268435456);
        return intent;
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        Intent intent = getIntent();
        this.mMessageId = intent.getIntExtra("casting_extra_message_id", -1);
        this.mPositiveButtonId = intent.getIntExtra("casting_extra_positive_button_id", -1);
        this.mNegativeButtonId = intent.getIntExtra("casting_extra_negative_button_id", -1);
        this.mDeviceName = intent.getStringExtra("casting_extra_device_name");
        String stringExtra = intent.getStringExtra("casting_extra_positive_button_listener_key");
        if (stringExtra != null) {
            if (stringExtra.equals("casting_disconnect_listener_key")) {
                this.mPositiveButtonListener = this.disconnectOnClickListener;
                return;
            } else {
                this.mPositiveButtonListener = this.okOnClickListener;
                return;
            }
        }
        this.mPositiveButtonListener = this.okOnClickListener;
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        if (this.mMessageId != -1) {
            String str = this.mDeviceName;
            if (str == null || str.length() <= 0) {
                bauhausDialogBuilder.setMessage(this.mMessageId);
            } else {
                bauhausDialogBuilder.setMessage(getString(this.mMessageId, this.mDeviceName));
            }
        }
        if (this.mPositiveButtonId != -1) {
            String str2 = this.mDeviceName;
            if (str2 == null || str2.length() <= 0) {
                bauhausDialogBuilder.setPositiveButton(this.mPositiveButtonId, this.mPositiveButtonListener);
            } else {
                bauhausDialogBuilder.setPositiveButton(getString(this.mPositiveButtonId, this.mDeviceName), this.mPositiveButtonListener);
            }
        }
        int i = this.mNegativeButtonId;
        if (i != -1) {
            bauhausDialogBuilder.setNegativeButton(i, this.okOnClickListener);
        }
        return bauhausDialogBuilder.create();
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String stringExtra = getIntent().getStringExtra("casting_content_name");
        if (stringExtra != null) {
            switch (stringExtra) {
                case "casting_content_type_disconnected":
                    CastingUIMetricsLogger.recordDeviceDisconnectedDialogContentView();
                    break;
                case "casting_content_type_aom":
                    CastingUIMetricsLogger.recordAlexaUnavailableDialogContentView();
                    break;
                case "casting_content_type_unavailable":
                    CastingUIMetricsLogger.recordContentUnavailableDialogContentView(CastingUtil.getDeviceCategory());
                    break;
                case "casting_content_type_unsupported":
                    CastingUIMetricsLogger.recordUnsupportedContentDialogContentView();
                    break;
            }
        }
    }
}
