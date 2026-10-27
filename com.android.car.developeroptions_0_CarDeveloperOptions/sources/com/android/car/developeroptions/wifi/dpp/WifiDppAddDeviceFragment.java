package com.android.car.developeroptions.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.net.wifi.EasyConnectStatusCallback;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppAddDeviceFragment extends WifiDppQrCodeBaseFragment {
    private Button mButtonLeft;
    private Button mButtonRight;
    private Button mChooseDifferentNetwork;
    OnClickChooseDifferentNetworkListener mClickChooseDifferentNetworkListener;
    private int mLatestStatusCode = 0;
    private ProgressBar mProgressBar;
    private ImageView mWifiApPictureView;

    public interface OnClickChooseDifferentNetworkListener {
        void onClickChooseDifferentNetwork();
    }

    private boolean hasRetryButton(int i) {
        return (i == -3 || i == -1) ? false : true;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1595;
    }

    private class EasyConnectConfiguratorStatusCallback extends EasyConnectStatusCallback {
        public void onEnrolleeSuccess(int i) {
        }

        public void onProgress(int i) {
        }

        private EasyConnectConfiguratorStatusCallback() {
        }

        public void onConfiguratorSuccess(int i) {
            WifiDppAddDeviceFragment.this.showSuccessUi(false);
        }

        public void onFailure(int i) {
            Log.d("WifiDppAddDeviceFragment", "EasyConnectConfiguratorStatusCallback.onFailure " + i);
            WifiDppAddDeviceFragment.this.showErrorUi(i, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuccessUi(boolean z) {
        setHeaderIconImageResource(R.drawable.ic_devices_check_circle_green);
        this.mTitle.setText(R.string.wifi_dpp_wifi_shared_with_device);
        this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        this.mSummary.setVisibility(4);
        this.mWifiApPictureView.setImageResource(R.drawable.wifi_dpp_success);
        this.mChooseDifferentNetwork.setVisibility(4);
        this.mButtonLeft.setText(R.string.wifi_dpp_add_another_device);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$ZxqPPi4SWGuGE8qtyzlMzfOW3Q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showSuccessUi$0$WifiDppAddDeviceFragment(view);
            }
        });
        this.mButtonRight.setText(R.string.done);
        this.mButtonRight.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$a6uKIW5nLiGSnOYdKHUsf5yiZpg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showSuccessUi$1$WifiDppAddDeviceFragment(view);
            }
        });
        if (z) {
            return;
        }
        this.mLatestStatusCode = 1;
        changeFocusAndAnnounceChange(this.mButtonRight, this.mTitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showSuccessUi$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showSuccessUi$0$WifiDppAddDeviceFragment(View view) {
        getFragmentManager().popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showSuccessUi$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showSuccessUi$1$WifiDppAddDeviceFragment(View view) {
        FragmentActivity activity = getActivity();
        activity.setResult(-1);
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorUi(int i, boolean z) {
        switch (i) {
            case -9:
                throw new IllegalStateException("Wi-Fi DPP configurator used a non-PSK/non-SAEnetwork to handshake");
            case -8:
                this.mSummary.setText(getString(R.string.wifi_dpp_failure_not_supported, getSsid()));
                break;
            case -7:
                this.mSummary.setText(R.string.wifi_dpp_failure_generic);
                break;
            case -6:
                this.mSummary.setText(R.string.wifi_dpp_failure_timeout);
                break;
            case -5:
                if (z) {
                    return;
                }
                if (i == this.mLatestStatusCode) {
                    throw new IllegalStateException("Tried restarting EasyConnectSession but stillreceiving EASY_CONNECT_EVENT_FAILURE_BUSY");
                }
                this.mLatestStatusCode = i;
                ((WifiManager) getContext().getSystemService(WifiManager.class)).stopEasyConnectSession();
                startWifiDppConfiguratorInitiator();
                return;
            case -4:
                this.mSummary.setText(R.string.wifi_dpp_failure_authentication_or_configuration);
                break;
            case -3:
                this.mSummary.setText(R.string.wifi_dpp_failure_not_compatible);
                break;
            case -2:
                this.mSummary.setText(R.string.wifi_dpp_failure_authentication_or_configuration);
                break;
            case -1:
                this.mSummary.setText(R.string.wifi_dpp_could_not_detect_valid_qr_code);
                break;
            default:
                throw new IllegalStateException("Unexpected Wi-Fi DPP error");
        }
        this.mTitle.setText(R.string.wifi_dpp_could_not_add_device);
        this.mWifiApPictureView.setImageResource(R.drawable.wifi_dpp_error);
        this.mChooseDifferentNetwork.setVisibility(4);
        if (hasRetryButton(i)) {
            this.mButtonRight.setText(R.string.retry);
        } else {
            this.mButtonRight.setText(R.string.done);
            this.mButtonRight.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$O56SGNzHFXXu-IrKmRtuRConTSg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$showErrorUi$2$WifiDppAddDeviceFragment(view);
                }
            });
            this.mButtonLeft.setVisibility(4);
        }
        if (isGoingInitiator()) {
            this.mSummary.setText(R.string.wifi_dpp_sharing_wifi_with_this_device);
        }
        this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        this.mButtonRight.setVisibility(isGoingInitiator() ? 4 : 0);
        if (z) {
            return;
        }
        this.mLatestStatusCode = i;
        changeFocusAndAnnounceChange(this.mButtonRight, this.mSummary);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showErrorUi$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showErrorUi$2$WifiDppAddDeviceFragment(View view) {
        getActivity().finish();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mLatestStatusCode = bundle.getInt("key_latest_error_code");
        }
        final WifiDppInitiatorViewModel wifiDppInitiatorViewModel = (WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class);
        wifiDppInitiatorViewModel.getStatusCode().observe(this, new Observer() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$mTTSOC73_kdr_9Mu_Fo6Q0gTK_A
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$3$WifiDppAddDeviceFragment(wifiDppInitiatorViewModel, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$3$WifiDppAddDeviceFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (wifiDppInitiatorViewModel.isGoingInitiator()) {
            return;
        }
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            new EasyConnectConfiguratorStatusCallback().onConfiguratorSuccess(iIntValue);
        } else {
            new EasyConnectConfiguratorStatusCallback().onFailure(iIntValue);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.wifi_dpp_add_device_fragment, viewGroup, false);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHeaderIconImageResource(R.drawable.ic_devices_other_opaque_black);
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.indeterminate_bar);
        String information = ((WifiDppConfiguratorActivity) getActivity()).getWifiDppQrCode().getInformation();
        if (TextUtils.isEmpty(information)) {
            this.mTitle.setText(R.string.wifi_dpp_device_found);
        } else {
            this.mTitle.setText(information);
        }
        updateSummary();
        this.mWifiApPictureView = (ImageView) view.findViewById(R.id.wifi_ap_picture_view);
        Button button = (Button) view.findViewById(R.id.choose_different_network);
        this.mChooseDifferentNetwork = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$RTMaKV69HbkfhIGlUjEZl8vlf0Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$4$WifiDppAddDeviceFragment(view2);
            }
        });
        Button button2 = (Button) view.findViewById(R.id.button_left);
        this.mButtonLeft = button2;
        button2.setText(R.string.cancel);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$UaurXwBQycXlV9mAL0frzeSKbLE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$5$WifiDppAddDeviceFragment(view2);
            }
        });
        Button button3 = (Button) view.findViewById(R.id.button_right);
        this.mButtonRight = button3;
        button3.setText(R.string.wifi_dpp_share_wifi);
        this.mButtonRight.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppAddDeviceFragment$JmBx-qCBzvWr8OTJF3YToOeyS-k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$6$WifiDppAddDeviceFragment(view2);
            }
        });
        if (bundle != null) {
            int i = this.mLatestStatusCode;
            if (i == 1) {
                showSuccessUi(true);
                return;
            } else if (i == 0) {
                this.mProgressBar.setVisibility(isGoingInitiator() ? 0 : 4);
                this.mButtonRight.setVisibility(isGoingInitiator() ? 4 : 0);
                return;
            } else {
                showErrorUi(i, true);
                return;
            }
        }
        changeFocusAndAnnounceChange(this.mButtonRight, this.mTitleSummaryContainer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onViewCreated$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onViewCreated$4$WifiDppAddDeviceFragment(View view) {
        this.mClickChooseDifferentNetworkListener.onClickChooseDifferentNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onViewCreated$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onViewCreated$5$WifiDppAddDeviceFragment(View view) {
        getActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onViewCreated$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onViewCreated$6$WifiDppAddDeviceFragment(View view) {
        this.mProgressBar.setVisibility(0);
        this.mButtonRight.setVisibility(4);
        startWifiDppConfiguratorInitiator();
        updateSummary();
        this.mTitleSummaryContainer.sendAccessibilityEvent(8);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("key_latest_error_code", this.mLatestStatusCode);
        super.onSaveInstanceState(bundle);
    }

    private String getSsid() {
        WifiNetworkConfig wifiNetworkConfig = ((WifiDppConfiguratorActivity) getActivity()).getWifiNetworkConfig();
        if (!WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            throw new IllegalStateException("Invalid Wi-Fi network for configuring");
        }
        return wifiNetworkConfig.getSsid();
    }

    private void startWifiDppConfiguratorInitiator() {
        ((WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class)).startEasyConnectAsConfiguratorInitiator(((WifiDppConfiguratorActivity) getActivity()).getWifiDppQrCode().getQrCode(), ((WifiDppConfiguratorActivity) getActivity()).getWifiNetworkConfig().getNetworkId());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mClickChooseDifferentNetworkListener = (OnClickChooseDifferentNetworkListener) context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mClickChooseDifferentNetworkListener = null;
        super.onDetach();
    }

    private boolean isGoingInitiator() {
        return ((WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class)).isGoingInitiator();
    }

    private void updateSummary() {
        if (isGoingInitiator()) {
            this.mSummary.setText(R.string.wifi_dpp_sharing_wifi_with_this_device);
        } else {
            this.mSummary.setText(getString(R.string.wifi_dpp_add_device_to_wifi, getSsid()));
        }
    }

    private void changeFocusAndAnnounceChange(View view, View view2) {
        view.sendAccessibilityEvent(8);
        view2.sendAccessibilityEvent(32);
    }
}
