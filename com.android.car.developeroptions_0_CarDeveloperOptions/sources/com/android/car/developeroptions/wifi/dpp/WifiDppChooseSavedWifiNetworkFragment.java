package com.android.car.developeroptions.wifi.dpp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppChooseSavedWifiNetworkFragment extends WifiDppQrCodeBaseFragment {
    private Button mButtonLeft;
    private Button mButtonRight;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1595;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        WifiNetworkListFragment wifiNetworkListFragment = new WifiNetworkListFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            wifiNetworkListFragment.setArguments(arguments);
        }
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.wifi_network_list_container, wifiNetworkListFragment, "wifi_network_list_fragment");
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.wifi_dpp_choose_saved_wifi_network_fragment, viewGroup, false);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setHeaderIconImageResource(R.drawable.ic_wifi_signal_4);
        this.mTitle.setText(R.string.wifi_dpp_choose_network);
        this.mSummary.setText(R.string.wifi_dpp_choose_network_to_connect_device);
        Button button = (Button) view.findViewById(R.id.button_left);
        this.mButtonLeft = button;
        button.setText(R.string.cancel);
        this.mButtonLeft.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppChooseSavedWifiNetworkFragment$JLevgsWti74CuaBRw4DqVDCNvY0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$WifiDppChooseSavedWifiNetworkFragment(view2);
            }
        });
        Button button2 = (Button) view.findViewById(R.id.button_right);
        this.mButtonRight = button2;
        button2.setVisibility(8);
        if (bundle == null) {
            this.mTitleSummaryContainer.sendAccessibilityEvent(32);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onViewCreated$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onViewCreated$0$WifiDppChooseSavedWifiNetworkFragment(View view) {
        Intent intent = getActivity().getIntent();
        String action = intent != null ? intent.getAction() : null;
        if ("android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_SCANNER".equals(action) || "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR".equals(action)) {
            getFragmentManager().popBackStack();
        } else {
            getActivity().finish();
        }
    }
}
