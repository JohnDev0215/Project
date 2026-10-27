package com.android.car.developeroptions.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig;
import com.android.car.developeroptions.wifi.qrcode.QrCodeGenerator;
import com.google.zxing.WriterException;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppQrCodeGeneratorFragment extends WifiDppQrCodeBaseFragment {
    private TextView mPasswordView;
    private String mQrCode;
    private ImageView mQrCodeView;

    public interface OnQrCodeGeneratorFragmentAddButtonClickedListener {
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1595;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getWifiNetworkConfigFromHostActivity().isHotspot()) {
            getActivity().setTitle(R.string.wifi_dpp_share_hotspot);
        } else {
            getActivity().setTitle(R.string.wifi_dpp_share_wifi);
        }
        setHasOptionsMenu(true);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem menuItemFindItem = menu.findItem(1);
        if (menuItemFindItem != null) {
            menuItemFindItem.setShowAsAction(0);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.wifi_dpp_qrcode_generator_fragment, viewGroup, false);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mQrCodeView = (ImageView) view.findViewById(R.id.qrcode_view);
        setHeaderIconImageResource(R.drawable.ic_qrcode_24dp);
        WifiNetworkConfig wifiNetworkConfigFromHostActivity = getWifiNetworkConfigFromHostActivity();
        if (wifiNetworkConfigFromHostActivity.isHotspot()) {
            this.mTitle.setText(R.string.wifi_dpp_share_hotspot);
        } else {
            this.mTitle.setText(R.string.wifi_dpp_share_wifi);
        }
        String preSharedKey = wifiNetworkConfigFromHostActivity.getPreSharedKey();
        this.mPasswordView = (TextView) view.findViewById(R.id.password);
        if (TextUtils.isEmpty(preSharedKey)) {
            this.mSummary.setText(getString(R.string.wifi_dpp_scan_open_network_qr_code_with_another_device, wifiNetworkConfigFromHostActivity.getSsid()));
            this.mPasswordView.setVisibility(8);
        } else {
            this.mSummary.setText(getString(R.string.wifi_dpp_scan_qr_code_with_another_device, wifiNetworkConfigFromHostActivity.getSsid()));
            if (wifiNetworkConfigFromHostActivity.isHotspot()) {
                this.mPasswordView.setText(getString(R.string.wifi_dpp_hotspot_password, preSharedKey));
            } else {
                this.mPasswordView.setText(getString(R.string.wifi_dpp_wifi_password, preSharedKey));
            }
        }
        this.mQrCode = wifiNetworkConfigFromHostActivity.getQrCode();
        setQrCode();
    }

    private void setQrCode() {
        try {
            this.mQrCodeView.setImageBitmap(QrCodeGenerator.encodeQrCode(this.mQrCode, getContext().getResources().getDimensionPixelSize(R.dimen.qrcode_size)));
        } catch (WriterException e) {
            Log.e("WifiDppQrCodeGeneratorFragment", "Error generatting QR code bitmap " + e);
        }
    }

    WifiNetworkConfig getWifiNetworkConfigFromHostActivity() {
        WifiNetworkConfig wifiNetworkConfig = ((WifiNetworkConfig.Retriever) getActivity()).getWifiNetworkConfig();
        if (WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            return wifiNetworkConfig;
        }
        throw new IllegalStateException("Invalid Wi-Fi network for configuring");
    }
}
