package com.android.car.developeroptions.wifi.dpp;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.net.wifi.EasyConnectStatusCallback;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig;
import com.android.car.developeroptions.wifi.qrcode.QrCamera;
import com.android.car.developeroptions.wifi.qrcode.QrDecorateView;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppQrCodeScannerFragment extends WifiDppQrCodeBaseFragment implements TextureView.SurfaceTextureListener, QrCamera.ScannerCallback, WifiManager.ActionListener, WifiTracker.WifiListener {
    private QrCamera mCamera;
    private QrDecorateView mDecorateView;
    private WifiConfiguration mEnrolleeWifiConfiguration;
    private TextView mErrorMessage;
    private ProgressBar mProgressBar;
    OnScanWifiDppSuccessListener mScanWifiDppSuccessListener;
    private String mSsid;
    private TextureView mTextureView;
    private WifiQrCode mWifiQrCode;
    private WifiTracker mWifiTracker;
    private int mLatestStatusCode = 0;
    private final Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeScannerFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setVisibility(4);
                return;
            }
            if (i == 2) {
                String str = (String) message.obj;
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setVisibility(0);
                WifiDppQrCodeScannerFragment.this.mErrorMessage.setText(str);
                WifiDppQrCodeScannerFragment.this.mErrorMessage.sendAccessibilityEvent(32);
                removeMessages(1);
                sendEmptyMessageDelayed(1, 10000L);
                if (message.arg1 == 1) {
                    WifiDppQrCodeScannerFragment.this.mProgressBar.setVisibility(4);
                    WifiDppQrCodeScannerFragment.this.mDecorateView.setFocused(false);
                    WifiDppQrCodeScannerFragment.this.restartCamera();
                    return;
                }
                return;
            }
            if (i == 3) {
                OnScanWifiDppSuccessListener onScanWifiDppSuccessListener = WifiDppQrCodeScannerFragment.this.mScanWifiDppSuccessListener;
                if (onScanWifiDppSuccessListener == null) {
                    return;
                }
                onScanWifiDppSuccessListener.onScanWifiDppSuccess((WifiQrCode) message.obj);
                if (!WifiDppQrCodeScannerFragment.this.mIsConfiguratorMode) {
                    WifiDppQrCodeScannerFragment.this.mProgressBar.setVisibility(0);
                    WifiDppQrCodeScannerFragment.this.startWifiDppEnrolleeInitiator((WifiQrCode) message.obj);
                    WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
                    WifiDppQrCodeScannerFragment.this.mSummary.sendAccessibilityEvent(32);
                }
                WifiDppQrCodeScannerFragment.this.notifyUserForQrCodeRecognition();
                return;
            }
            if (i != 4) {
                return;
            }
            WifiManager wifiManager = (WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class);
            boolean z = false;
            for (WifiConfiguration wifiConfiguration : ((WifiNetworkConfig) message.obj).getWifiConfigurations()) {
                int iAddNetwork = wifiManager.addNetwork(wifiConfiguration);
                if (iAddNetwork != -1) {
                    wifiManager.enableNetwork(iAddNetwork, false);
                    if (WifiDppQrCodeScannerFragment.this.isReachableWifiNetwork(wifiConfiguration)) {
                        WifiDppQrCodeScannerFragment.this.mEnrolleeWifiConfiguration = wifiConfiguration;
                        wifiManager.connect(iAddNetwork, WifiDppQrCodeScannerFragment.this);
                        z = true;
                    }
                }
            }
            if (!z) {
                WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(R.string.wifi_dpp_check_connection_try_again);
            } else {
                WifiDppQrCodeScannerFragment.this.notifyUserForQrCodeRecognition();
            }
        }
    };
    private boolean mIsConfiguratorMode = true;

    public interface OnScanWifiDppSuccessListener {
        void onScanWifiDppSuccess(WifiQrCode wifiQrCode);
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onAccessPointsChanged() {
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onConnectedChanged() {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onWifiStateChanged(int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyUserForQrCodeRecognition() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
        }
        this.mDecorateView.setFocused(true);
        this.mErrorMessage.setVisibility(4);
        WifiDppUtils.triggerVibrationForQrCodeRecognition(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isReachableWifiNetwork(WifiConfiguration wifiConfiguration) {
        for (AccessPoint accessPoint : this.mWifiTracker.getAccessPoints()) {
            if (accessPoint.matches(wifiConfiguration) && accessPoint.isReachable()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mIsConfiguratorMode = bundle.getBoolean("key_is_configurator_mode");
            this.mLatestStatusCode = bundle.getInt("key_latest_error_code");
            this.mEnrolleeWifiConfiguration = (WifiConfiguration) bundle.getParcelable("key_wifi_configuration");
        }
        final WifiDppInitiatorViewModel wifiDppInitiatorViewModel = (WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class);
        wifiDppInitiatorViewModel.getEnrolleeSuccessNetworkId().observe(this, new Observer() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppQrCodeScannerFragment$LuftBpX04WsCJPMbrndaK4SKPyw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$0$WifiDppQrCodeScannerFragment(wifiDppInitiatorViewModel, (Integer) obj);
            }
        });
        wifiDppInitiatorViewModel.getStatusCode().observe(this, new Observer() { // from class: com.android.car.developeroptions.wifi.dpp.-$$Lambda$WifiDppQrCodeScannerFragment$82Tnw4uvj2Ka1PIQn6Lw3GQlW9g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$1$WifiDppQrCodeScannerFragment(wifiDppInitiatorViewModel, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$WifiDppQrCodeScannerFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (wifiDppInitiatorViewModel.isGoingInitiator()) {
            return;
        }
        new EasyConnectEnrolleeStatusCallback().onEnrolleeSuccess(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$1$WifiDppQrCodeScannerFragment(WifiDppInitiatorViewModel wifiDppInitiatorViewModel, Integer num) {
        if (wifiDppInitiatorViewModel.isGoingInitiator()) {
            return;
        }
        int iIntValue = num.intValue();
        Log.d("WifiDppQrCodeScannerFragment", "Easy connect enrollee callback onFailure " + iIntValue);
        new EasyConnectEnrolleeStatusCallback().onFailure(iIntValue);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onPause() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
        }
        super.onPause();
    }

    @Override // com.android.car.developeroptions.core.InstrumentedFragment, com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isGoingInitiator()) {
            return;
        }
        restartCamera();
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return this.mIsConfiguratorMode ? 1595 : 1596;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mWifiTracker = WifiTrackerFactory.create(getActivity(), this, getSettingsLifecycle(), false, true);
        if (this.mIsConfiguratorMode) {
            getActivity().setTitle(R.string.wifi_dpp_add_device_to_network);
        } else {
            getActivity().setTitle(R.string.wifi_dpp_scan_qr_code);
        }
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
        this.mScanWifiDppSuccessListener = (OnScanWifiDppSuccessListener) context;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        this.mScanWifiDppSuccessListener = null;
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.wifi_dpp_qrcode_scanner_fragment, viewGroup, false);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        TextureView textureView = (TextureView) view.findViewById(R.id.preview_view);
        this.mTextureView = textureView;
        textureView.setSurfaceTextureListener(this);
        this.mDecorateView = (QrDecorateView) view.findViewById(R.id.decorate_view);
        setHeaderIconImageResource(R.drawable.ic_scan_24dp);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.indeterminate_bar);
        this.mProgressBar = progressBar;
        progressBar.setVisibility(isGoingInitiator() ? 0 : 4);
        if (this.mIsConfiguratorMode) {
            this.mTitle.setText(R.string.wifi_dpp_add_device_to_network);
            WifiNetworkConfig wifiNetworkConfig = ((WifiNetworkConfig.Retriever) getActivity()).getWifiNetworkConfig();
            if (!WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
                throw new IllegalStateException("Invalid Wi-Fi network for configuring");
            }
            this.mSummary.setText(getString(R.string.wifi_dpp_center_qr_code, wifiNetworkConfig.getSsid()));
        } else {
            this.mTitle.setText(R.string.wifi_dpp_scan_qr_code);
            updateEnrolleeSummary();
        }
        this.mErrorMessage = (TextView) view.findViewById(R.id.error_message);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.removeItem(1);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        initCamera(surfaceTexture);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        destroyCamera();
        return true;
    }

    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    public Size getViewSize() {
        return new Size(this.mTextureView.getWidth(), this.mTextureView.getHeight());
    }

    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    public Rect getFramePosition(Size size, int i) {
        return new Rect(0, 0, size.getHeight(), size.getHeight());
    }

    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    public void setTransform(Matrix matrix) {
        this.mTextureView.setTransform(matrix);
    }

    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    public boolean isValid(String str) {
        try {
            WifiQrCode wifiQrCode = new WifiQrCode(str);
            this.mWifiQrCode = wifiQrCode;
            String scheme = wifiQrCode.getScheme();
            if (!this.mIsConfiguratorMode && "WIFI".equals(scheme)) {
                String ssid = this.mWifiQrCode.getWifiNetworkConfig().getSsid();
                if (!TextUtils.isEmpty(this.mSsid) && !this.mSsid.equals(ssid)) {
                    showErrorMessage(R.string.wifi_dpp_could_not_detect_valid_qr_code);
                    return false;
                }
            }
            if (!this.mIsConfiguratorMode || !"WIFI".equals(scheme)) {
                return true;
            }
            showErrorMessage(R.string.wifi_dpp_could_not_detect_valid_qr_code);
            return false;
        } catch (IllegalArgumentException unused) {
            showErrorMessage(R.string.wifi_dpp_could_not_detect_valid_qr_code);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleSuccessfulResult(java.lang.String r4) {
        /*
            r3 = this;
            com.android.car.developeroptions.wifi.dpp.WifiQrCode r4 = r3.mWifiQrCode
            java.lang.String r4 = r4.getScheme()
            int r0 = r4.hashCode()
            r1 = 67908(0x10944, float:9.516E-41)
            r2 = 1
            if (r0 == r1) goto L20
            r1 = 2664213(0x28a715, float:3.733358E-39)
            if (r0 == r1) goto L16
            goto L2a
        L16:
            java.lang.String r0 = "WIFI"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L2a
            r4 = r2
            goto L2b
        L20:
            java.lang.String r0 = "DPP"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L2a
            r4 = 0
            goto L2b
        L2a:
            r4 = -1
        L2b:
            if (r4 == 0) goto L34
            if (r4 == r2) goto L30
            goto L37
        L30:
            r3.handleZxingWifiFormat()
            goto L37
        L34:
            r3.handleWifiDpp()
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeScannerFragment.handleSuccessfulResult(java.lang.String):void");
    }

    private void handleWifiDpp() {
        Message messageObtainMessage = this.mHandler.obtainMessage(3);
        messageObtainMessage.obj = new WifiQrCode(this.mWifiQrCode.getQrCode());
        this.mHandler.sendMessageDelayed(messageObtainMessage, 1000L);
    }

    private void handleZxingWifiFormat() {
        Message messageObtainMessage = this.mHandler.obtainMessage(4);
        messageObtainMessage.obj = new WifiQrCode(this.mWifiQrCode.getQrCode()).getWifiNetworkConfig();
        this.mHandler.sendMessageDelayed(messageObtainMessage, 1000L);
    }

    @Override // com.android.car.developeroptions.wifi.qrcode.QrCamera.ScannerCallback
    public void handleCameraFailure() {
        destroyCamera();
    }

    private void initCamera(SurfaceTexture surfaceTexture) {
        if (this.mCamera == null) {
            this.mCamera = new QrCamera(getContext(), this);
            if (isGoingInitiator()) {
                QrDecorateView qrDecorateView = this.mDecorateView;
                if (qrDecorateView != null) {
                    qrDecorateView.setFocused(true);
                    return;
                }
                return;
            }
            this.mCamera.start(surfaceTexture);
        }
    }

    private void destroyCamera() {
        QrCamera qrCamera = this.mCamera;
        if (qrCamera != null) {
            qrCamera.stop();
            this.mCamera = null;
        }
    }

    private void showErrorMessage(int i) {
        this.mHandler.obtainMessage(2, getString(i)).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorMessageAndRestartCamera(int i) {
        Message messageObtainMessage = this.mHandler.obtainMessage(2, getString(i));
        messageObtainMessage.arg1 = 1;
        messageObtainMessage.sendToTarget();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_is_configurator_mode", this.mIsConfiguratorMode);
        bundle.putInt("key_latest_error_code", this.mLatestStatusCode);
        bundle.putParcelable("key_wifi_configuration", this.mEnrolleeWifiConfiguration);
        super.onSaveInstanceState(bundle);
    }

    private class EasyConnectEnrolleeStatusCallback extends EasyConnectStatusCallback {
        public void onConfiguratorSuccess(int i) {
        }

        public void onProgress(int i) {
        }

        private EasyConnectEnrolleeStatusCallback() {
        }

        public void onEnrolleeSuccess(int i) {
            WifiManager wifiManager = (WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class);
            for (WifiConfiguration wifiConfiguration : wifiManager.getPrivilegedConfiguredNetworks()) {
                if (wifiConfiguration.networkId == i) {
                    WifiDppQrCodeScannerFragment.this.mLatestStatusCode = 1;
                    WifiDppQrCodeScannerFragment.this.mEnrolleeWifiConfiguration = wifiConfiguration;
                    wifiManager.connect(wifiConfiguration, WifiDppQrCodeScannerFragment.this);
                    return;
                }
            }
            Log.e("WifiDppQrCodeScannerFragment", "Invalid networkId " + i);
            WifiDppQrCodeScannerFragment.this.mLatestStatusCode = -7;
            WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
            WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(R.string.wifi_dpp_check_connection_try_again);
        }

        public void onFailure(int i) {
            Log.d("WifiDppQrCodeScannerFragment", "EasyConnectEnrolleeStatusCallback.onFailure " + i);
            int i2 = R.string.wifi_dpp_failure_authentication_or_configuration;
            switch (i) {
                case -9:
                    throw new IllegalStateException("EASY_CONNECT_EVENT_FAILURE_INVALID_NETWORK should be a configurator only error");
                case -8:
                    throw new IllegalStateException("EASY_CONNECT_EVENT_FAILURE_NOT_SUPPORTED should be a configurator only error");
                case -7:
                    i2 = R.string.wifi_dpp_failure_generic;
                    break;
                case -6:
                    i2 = R.string.wifi_dpp_failure_timeout;
                    break;
                case -5:
                    if (i != WifiDppQrCodeScannerFragment.this.mLatestStatusCode) {
                        WifiDppQrCodeScannerFragment.this.mLatestStatusCode = i;
                        ((WifiManager) WifiDppQrCodeScannerFragment.this.getContext().getSystemService(WifiManager.class)).stopEasyConnectSession();
                        WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment = WifiDppQrCodeScannerFragment.this;
                        wifiDppQrCodeScannerFragment.startWifiDppEnrolleeInitiator(wifiDppQrCodeScannerFragment.mWifiQrCode);
                        return;
                    }
                    throw new IllegalStateException("stopEasyConnectSession and try again forEASY_CONNECT_EVENT_FAILURE_BUSY but still failed");
                case -4:
                case -2:
                    break;
                case -3:
                    i2 = R.string.wifi_dpp_failure_not_compatible;
                    break;
                case -1:
                    i2 = R.string.wifi_dpp_could_not_detect_valid_qr_code;
                    break;
                default:
                    throw new IllegalStateException("Unexpected Wi-Fi DPP error");
            }
            WifiDppQrCodeScannerFragment.this.mLatestStatusCode = i;
            WifiDppQrCodeScannerFragment.this.updateEnrolleeSummary();
            WifiDppQrCodeScannerFragment.this.showErrorMessageAndRestartCamera(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWifiDppEnrolleeInitiator(WifiQrCode wifiQrCode) {
        ((WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class)).startEasyConnectAsEnrolleeInitiator(wifiQrCode.getQrCode());
    }

    public void onSuccess() {
        Intent intent = new Intent();
        intent.putExtra("wifi_configuration", this.mEnrolleeWifiConfiguration);
        FragmentActivity activity = getActivity();
        activity.setResult(-1, intent);
        activity.finish();
    }

    public void onFailure(int i) {
        Log.d("WifiDppQrCodeScannerFragment", "Wi-Fi connect onFailure reason - " + i);
        showErrorMessageAndRestartCamera(R.string.wifi_dpp_check_connection_try_again);
    }

    private boolean isGoingInitiator() {
        return ((WifiDppInitiatorViewModel) ViewModelProviders.of(this).get(WifiDppInitiatorViewModel.class)).isGoingInitiator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartCamera() {
        if (this.mCamera == null) {
            Log.d("WifiDppQrCodeScannerFragment", "mCamera is not available for restarting camera");
            return;
        }
        SurfaceTexture surfaceTexture = this.mTextureView.getSurfaceTexture();
        if (surfaceTexture == null) {
            throw new IllegalStateException("SurfaceTexture is not ready for restarting camera");
        }
        this.mCamera.start(surfaceTexture);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEnrolleeSummary() {
        if (isGoingInitiator()) {
            this.mSummary.setText(R.string.wifi_dpp_connecting);
        } else {
            this.mSummary.setText(TextUtils.isEmpty(this.mSsid) ? getString(R.string.wifi_dpp_scan_qr_code_join_unknown_network, this.mSsid) : getString(R.string.wifi_dpp_scan_qr_code_join_network, this.mSsid));
        }
    }

    protected boolean isDecodeTaskAlive() {
        QrCamera qrCamera = this.mCamera;
        return qrCamera != null && qrCamera.isDecodeTaskAlive();
    }
}
