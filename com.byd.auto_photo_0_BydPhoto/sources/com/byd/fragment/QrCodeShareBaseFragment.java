package com.byd.fragment;

import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import b.a.d;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.dilink.app.UiGenerationConstants;
import com.byd.share.a;
import com.byd.share.b;
import com.byd.share.c;
import com.byd.util.e;
import com.byd.util.s;
import com.byd.util.w;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class QrCodeShareBaseFragment extends BaseDialogFragment implements View.OnClickListener {
    private static final String ACTION_AP_CHANGE = "android.net.wifi.WIFI_AP_STATE_CHANGED";
    private static final int MSG_CHECK_AP_STATE = 2;
    private static final int MSG_CHECK_CONNECT = 1;
    private static final int PERMISSION_REQUEST = 10;
    private ApReceiver apReceiver;
    protected TextView btnRefresh;
    protected TextView btnToHotpot;
    private View containerQRCode;
    private View containerWifiDisConnect;
    private View containerWifiInfo;
    protected boolean isIviDev;
    private ImageView ivQrCode;
    private MHandler mHandler;
    private int orientation;
    private ProgressBar progressBar;
    private List<FileBean> shareFileBeans;
    private String shareId;
    private boolean toHotpot;
    private TextView tvWifiName;
    private TextView tvWifiPsw;

    private class ApReceiver extends BroadcastReceiver {
        private ApReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            MHandler mHandler;
            MHandler mHandler2;
            Boolean bool;
            if (QrCodeShareBaseFragment.ACTION_AP_CHANGE.equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("wifi_state", 0);
                w.c("AP state change : " + intExtra);
                QrCodeShareBaseFragment.this.mHandler.removeMessages(2);
                switch (intExtra) {
                    case 10:
                        str = "热点正在关闭";
                        w.c(str);
                        return;
                    case 11:
                        w.c("热点已关闭");
                        mHandler = QrCodeShareBaseFragment.this.mHandler;
                        mHandler2 = QrCodeShareBaseFragment.this.mHandler;
                        bool = Boolean.FALSE;
                        break;
                    case 12:
                        str = "热点正在打开";
                        w.c(str);
                        return;
                    case 13:
                        w.c("热点已打开");
                        mHandler = QrCodeShareBaseFragment.this.mHandler;
                        mHandler2 = QrCodeShareBaseFragment.this.mHandler;
                        bool = Boolean.TRUE;
                        break;
                    default:
                        return;
                }
                mHandler.sendMessageDelayed(mHandler2.obtainMessage(2, bool), 500L);
            }
        }
    }

    private static class MHandler extends d<QrCodeShareBaseFragment> {
        public MHandler(QrCodeShareBaseFragment qrCodeShareBaseFragment) {
            super(qrCodeShareBaseFragment);
        }

        @Override // b.a.d
        public void handleMessage(QrCodeShareBaseFragment qrCodeShareBaseFragment, Message message) {
            int i = message.what;
            if (i == 1) {
                qrCodeShareBaseFragment.progressBar.setVisibility(8);
                qrCodeShareBaseFragment.checkWifiConnect();
            } else {
                if (i != 2) {
                    return;
                }
                qrCodeShareBaseFragment.apStateChange(((Boolean) message.obj).booleanValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void apStateChange(boolean z) {
        checkWifiConnect();
        if (z || !c.w(getActivity()).B()) {
            return;
        }
        c.w(getActivity()).F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkWifiConnect() {
        if (!isWifiConnect()) {
            showDisconnectView();
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (this.isIviDev) {
            boolean zE = s.e(activity);
            if (!zE && s.b(activity) != 1) {
                showDisconnectView();
                return;
            }
            this.containerWifiDisConnect.setVisibility(8);
            this.containerQRCode.setVisibility(0);
            this.containerWifiInfo.setVisibility(8);
            this.btnToHotpot.setVisibility(zE ? 0 : 4);
        } else {
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(getActivity().getApplicationContext());
            String strC = s.c(bYDAutoSettingDevice);
            String strD = s.d(bYDAutoSettingDevice);
            if (strC == null || strD == null) {
                showDisconnectView();
                return;
            }
            this.containerWifiInfo.setVisibility(0);
            this.btnToHotpot.setVisibility(8);
            this.tvWifiName.setText(strC);
            this.tvWifiPsw.setText(strD);
        }
        this.containerWifiDisConnect.setVisibility(8);
        this.containerQRCode.setVisibility(0);
        if (this.shareId == null) {
            this.shareId = c.w(activity).t();
            c.w(activity).D(this.shareId, this.shareFileBeans);
        }
        if (c.w(activity).B()) {
            showQrCode();
            return;
        }
        c.w(activity).A();
        if (c.w(activity).z()) {
            c.w(activity).E();
        }
        showQrCode();
    }

    private int getDialogWidthWithConfig(Configuration configuration) {
        Resources resources;
        int i;
        if (configuration.orientation == 1) {
            resources = getResources();
            i = R.dimen.qr_code_dialog_width_port;
        } else {
            resources = getResources();
            i = R.dimen.qr_code_dialog_width_land;
        }
        return resources.getDimensionPixelSize(i);
    }

    private boolean hasHotspotReceiver() {
        PackageManager packageManager = getActivity().getPackageManager();
        Intent intent = new Intent();
        intent.setPackage("com.byd.systemsettings");
        intent.setAction("com.byd.systemsettings.action.OPEN_PER_HOTSPOT_SETTING");
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.isEmpty()) {
            return false;
        }
        w.c("==>" + listQueryBroadcastReceivers.get(0));
        return true;
    }

    private boolean isWifiConnect() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return false;
        }
        if (s.b(activity) == 1) {
            return true;
        }
        return this.isIviDev ? s.e(activity) : a.s(activity).A();
    }

    private void showDisconnectView() {
        TextView textView;
        int i;
        this.containerWifiDisConnect.setVisibility(0);
        this.containerQRCode.setVisibility(8);
        if (this.isIviDev) {
            textView = this.btnRefresh;
            i = R.string.show_hotpot_info;
        } else {
            textView = this.btnRefresh;
            i = R.string.refresh;
        }
        textView.setText(i);
    }

    private void showQrCode() {
        if (this.shareId != null) {
            String strU = c.w(getActivity()).u(this.shareId);
            w.c("====>ip:" + strU);
            if (strU == null) {
                w.c("create url fail");
            } else {
                this.ivQrCode.setImageBitmap(b.a(strU, ViewCompat.MEASURED_STATE_MASK));
            }
        }
    }

    private void toHotpotActivity() {
        ComponentName componentName;
        String str;
        if (this.toHotpot) {
            return;
        }
        String strW = e.w();
        if (strW == null) {
            str = "toHotpotActivity platform is null";
        } else {
            Intent intent = new Intent();
            intent.addCategory("android.intent.category.DEFAULT");
            if (!hasHotspotReceiver()) {
                String str2 = null;
                ComponentName componentName2 = null;
                if (strW.startsWith("Di1") || strW.startsWith("Di2") || strW.contains(UiGenerationConstants.UI_3_0)) {
                    str2 = "android.intent.action.HOTSPOT_SETTINGS";
                    componentName = null;
                } else if (strW.startsWith("Di4.0") || strW.contains("3.5UI")) {
                    componentName = new ComponentName("com.byd.systemsettings", "com.byd.systemsettings.hotspot.PerHotspotActivity");
                } else {
                    if (strW.startsWith("Di5.0")) {
                        componentName2 = new ComponentName("com.byd.systemsettings", "com.byd.systemsettings.hotspot.PerHotspotActivity");
                    } else if (strW.startsWith("DiR")) {
                        componentName2 = new ComponentName("com.byd.carsettings", "com.byd.systemsettings.hotspot2.PerHotspotActivity2");
                    }
                    componentName = componentName2;
                    str2 = "com.android.settings.WIFI_TETHER_SETTINGS";
                }
                if (str2 != null) {
                    intent.setAction(str2);
                }
                if (componentName != null) {
                    intent.setComponent(componentName);
                }
                try {
                    this.toHotpot = true;
                    startActivity(intent);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.toHotpot = false;
                    w.c("to hotpot exception : " + e.getMessage());
                    return;
                }
            }
            intent.setPackage("com.byd.systemsettings");
            intent.setAction("com.byd.systemsettings.action.OPEN_PER_HOTSPOT_SETTING");
            intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
            intent.setComponent(new ComponentName("com.byd.systemsettings", "com.byd.systemsettings.hotspot2.PerHotspotReceiver"));
            getActivity().sendBroadcast(intent);
            str = "send broadcast : " + intent;
        }
        w.c(str);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 4;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getDialogHeight() {
        return getResources().getDimensionPixelSize(R.dimen.qr_code_dialog_height);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getDialogWidth() {
        return getDialogWidthWithConfig(getResources().getConfiguration());
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_qr_code_share;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        return getResources().getString(R.string.title_share_qr_code);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void initUiData() {
        super.initUiData();
        checkWifiConnect();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_refresh) {
            if (id != R.id.btn_to_hotpot) {
                return;
            }
            toHotpotActivity();
        } else {
            if (this.isIviDev) {
                toHotpotActivity();
                return;
            }
            if (this.progressBar.getVisibility() != 0) {
                this.progressBar.setVisibility(0);
            }
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 1500L);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = this.orientation;
        int i2 = configuration.orientation;
        if (i != i2) {
            this.orientation = i2;
            getDialog().getWindow().setLayout(getDialogWidthWithConfig(configuration), getDialogHeight());
            onOrientationChange(configuration);
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mHandler = new MHandler(this);
        int iP = a.s(getActivity()).p();
        boolean z = true;
        if (iP != 1 && iP != 0) {
            z = false;
        }
        this.isIviDev = z;
        if (z) {
            this.apReceiver = new ApReceiver();
            getActivity().registerReceiver(this.apReceiver, new IntentFilter(ACTION_AP_CHANGE));
        }
        super.onCreate(bundle);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.shareId = null;
        super.onDestroy();
        MHandler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            this.mHandler.release();
            this.mHandler = null;
        }
        if (this.apReceiver != null) {
            getActivity().unregisterReceiver(this.apReceiver);
            this.apReceiver = null;
        }
    }

    protected void onOrientationChange(Configuration configuration) {
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        w.c("QrCodeFragment onResume : " + this.toHotpot);
        if (this.toHotpot) {
            this.toHotpot = false;
            checkWifiConnect();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        this.orientation = getResources().getConfiguration().orientation;
        this.containerQRCode = view.findViewById(R.id.container_qr_code);
        this.ivQrCode = (ImageView) view.findViewById(R.id.iv_qr_code);
        this.tvWifiName = (TextView) view.findViewById(R.id.tv_wifi_name);
        this.tvWifiPsw = (TextView) view.findViewById(R.id.tv_wifi_psw);
        this.containerWifiDisConnect = view.findViewById(R.id.container_wifi_disconnect);
        this.containerWifiInfo = view.findViewById(R.id.container_wifi_info);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_check_net);
        TextView textView = (TextView) view.findViewById(R.id.btn_refresh);
        this.btnRefresh = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.btn_to_hotpot);
        this.btnToHotpot = textView2;
        textView2.setOnClickListener(this);
    }

    public void setShareFileBeans(List<FileBean> list) {
        this.shareFileBeans = list;
    }
}
