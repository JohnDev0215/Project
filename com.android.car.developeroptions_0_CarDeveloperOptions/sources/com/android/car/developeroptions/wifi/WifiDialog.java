package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.wifi.AccessPoint;

/* JADX INFO: loaded from: classes.dex */
public class WifiDialog extends AlertDialog implements WifiConfigUiBase, DialogInterface.OnClickListener {
    private final AccessPoint mAccessPoint;
    private WifiConfigController mController;
    private boolean mHideSubmitButton;
    private final WifiDialogListener mListener;
    private final int mMode;
    private View mView;

    public interface WifiDialogListener {
        default void onForget(WifiDialog wifiDialog) {
        }

        default void onScan(WifiDialog wifiDialog, String str) {
        }

        default void onSubmit(WifiDialog wifiDialog) {
        }
    }

    public static WifiDialog createModal(Context context, WifiDialogListener wifiDialogListener, AccessPoint accessPoint, int i) {
        return new WifiDialog(context, wifiDialogListener, accessPoint, i, 0, i == 0);
    }

    WifiDialog(Context context, WifiDialogListener wifiDialogListener, AccessPoint accessPoint, int i, int i2, boolean z) {
        super(context, i2);
        this.mMode = i;
        this.mListener = wifiDialogListener;
        this.mAccessPoint = accessPoint;
        this.mHideSubmitButton = z;
    }

    public WifiConfigController getController() {
        return this.mController;
    }

    @Override // androidx.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        View viewInflate = getLayoutInflater().inflate(R.layout.wifi_dialog, (ViewGroup) null);
        this.mView = viewInflate;
        setView(viewInflate);
        this.mController = new WifiConfigController(this, this.mView, this.mAccessPoint, this.mMode);
        super.onCreate(bundle);
        if (this.mHideSubmitButton) {
            this.mController.hideSubmitButton();
        } else {
            this.mController.enableSubmitIfAppropriate();
        }
        if (this.mAccessPoint == null) {
            this.mController.hideForgetButton();
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiDialog$5tY2qdTHPMtU1-6CF8M1pZ8A24c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onStart$0$WifiDialog(view);
            }
        };
        ImageButton imageButton = (ImageButton) findViewById(R.id.ssid_scanner_button);
        imageButton.setOnClickListener(onClickListener);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.password_scanner_button);
        imageButton2.setOnClickListener(onClickListener);
        if (this.mHideSubmitButton) {
            imageButton.setVisibility(8);
            imageButton2.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onStart$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onStart$0$WifiDialog(View view) {
        String ssidStr;
        if (this.mListener == null) {
            return;
        }
        AccessPoint accessPoint = this.mAccessPoint;
        if (accessPoint == null) {
            ssidStr = ((TextView) findViewById(R.id.ssid)).getText().toString();
        } else {
            ssidStr = accessPoint.getSsidStr();
        }
        this.mListener.onScan(this, ssidStr);
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mController.updatePassword();
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void dispatchSubmit() {
        WifiDialogListener wifiDialogListener = this.mListener;
        if (wifiDialogListener != null) {
            wifiDialogListener.onSubmit(this);
        }
        dismiss();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        WifiDialogListener wifiDialogListener = this.mListener;
        if (wifiDialogListener != null) {
            if (i != -3) {
                if (i != -1) {
                    return;
                }
                wifiDialogListener.onSubmit(this);
            } else if (WifiUtils.isNetworkLockedDown(getContext(), this.mAccessPoint.getConfig())) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), RestrictedLockUtilsInternal.getDeviceOwner(getContext()));
            } else {
                this.mListener.onForget(this);
            }
        }
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public Button getSubmitButton() {
        return getButton(-1);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public Button getForgetButton() {
        return getButton(-3);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setSubmitButton(CharSequence charSequence) {
        setButton(-1, charSequence, this);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setForgetButton(CharSequence charSequence) {
        setButton(-3, charSequence, this);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setCancelButton(CharSequence charSequence) {
        setButton(-2, charSequence, this);
    }
}
