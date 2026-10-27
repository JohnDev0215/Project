package com.android.car.developeroptions.wifi;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;

/* JADX INFO: loaded from: classes.dex */
public class AddNetworkFragment extends InstrumentedFragment implements WifiConfigUiBase, View.OnClickListener {
    static final int CANCEL_BUTTON_ID = 16908314;
    static final int SUBMIT_BUTTON_ID = 16908313;
    private Button mCancelBtn;
    private Button mSubmitBtn;
    private WifiConfigController mUIController;

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public Button getForgetButton() {
        return null;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1556;
    }

    public int getMode() {
        return 1;
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setForgetButton(CharSequence charSequence) {
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.wifi_add_network_view, viewGroup, false);
        Button button = (Button) viewInflate.findViewById(android.R.id.button3);
        if (button != null) {
            button.setVisibility(8);
        }
        this.mSubmitBtn = (Button) viewInflate.findViewById(16908313);
        this.mCancelBtn = (Button) viewInflate.findViewById(16908314);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.ssid_scanner_button);
        ImageButton imageButton2 = (ImageButton) viewInflate.findViewById(R.id.password_scanner_button);
        this.mSubmitBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        this.mUIController = new WifiConfigController(this, viewInflate, null, getMode());
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        this.mUIController.updatePassword();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String string;
        switch (view.getId()) {
            case 16908313:
                handleSubmitAction();
                return;
            case 16908314:
                handleCancelAction();
                return;
            case R.id.password_scanner_button /* 2131362574 */:
                string = null;
                break;
            case R.id.ssid_scanner_button /* 2131362803 */:
                string = ((TextView) getView().findViewById(R.id.ssid)).getText().toString();
                break;
            default:
                return;
        }
        startActivityForResult(WifiDppUtils.getEnrolleeQrCodeScannerIntent(string), 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            successfullyFinish((WifiConfiguration) intent.getParcelableExtra("wifi_configuration"));
        }
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void dispatchSubmit() {
        handleSubmitAction();
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setTitle(int i) {
        getActivity().setTitle(i);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setTitle(CharSequence charSequence) {
        getActivity().setTitle(charSequence);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setSubmitButton(CharSequence charSequence) {
        this.mSubmitBtn.setText(charSequence);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public void setCancelButton(CharSequence charSequence) {
        this.mCancelBtn.setText(charSequence);
    }

    @Override // com.android.car.developeroptions.wifi.WifiConfigUiBase
    public Button getSubmitButton() {
        return this.mSubmitBtn;
    }

    void handleSubmitAction() {
        successfullyFinish(this.mUIController.getConfig());
    }

    private void successfullyFinish(WifiConfiguration wifiConfiguration) {
        Intent intent = new Intent();
        FragmentActivity activity = getActivity();
        intent.putExtra("wifi_config_key", wifiConfiguration);
        activity.setResult(-1, intent);
        activity.finish();
    }

    void handleCancelAction() {
        FragmentActivity activity = getActivity();
        activity.setResult(0);
        activity.finish();
    }
}
