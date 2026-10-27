package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import com.byd.systemsettings.ui.widget.LeftRightItem;
import com.byd.systemsettings.wifi2.BydWifiManager;
import com.byd.systemsettings.wifi2.WifiSettingsPager;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.ConnectedWifiInfo;
import com.byd.systemsettings.wifi2.present.ConnectedWifiInfoPresenter;
import com.byd.systemsettings.wifi2.util.ResUtils;
import com.byd.systemsettings.wifi2.util.SharedPrefUtils;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class ConnectedWifiInfoPager extends a<ConnectedWifiInfoPresenter> {
    private volatile long UPDATE_INTERVAL;
    private FrameLayout flWifiInfo;
    private LinearLayout llBottom;
    private volatile ConnectedWifiInfo mInfo;
    private volatile long preTime;
    private LeftRightItem tvIp;
    private LeftRightItem tvName;
    private LeftRightItem tvRssi;
    private LeftRightItem tvSecurity;
    private LeftRightItem tvSpeed;
    private LeftRightItem tvState;

    public ConnectedWifiInfoPager(Context context) {
        super(context);
        this.preTime = 0L;
        this.UPDATE_INTERVAL = 8000L;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_wifi_info_connected;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.flWifiInfo = (FrameLayout) this.mRootView.findViewById(R.id.fl_pager_connected_wifi_info);
        this.tvName = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_name_pager_connected_wifi_info);
        this.tvState = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_state_pager_connected_wifi_info);
        this.tvRssi = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_rssi_pager_connected_wifi_info);
        this.tvSpeed = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_speed_pager_connected_wifi_info);
        this.tvSecurity = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_security_pager_connected_wifi_info);
        LeftRightItem leftRightItem = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_ip_pager_connected_wifi_info);
        this.tvIp = leftRightItem;
        leftRightItem.setShowOnLog(false);
        this.llBottom = (LinearLayout) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_info_connected);
        this.mRootView.findViewById(R.id.btn_remove_pager_connected_wifi_info).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_pager_connected_wifi_info).setOnClickListener(this);
        this.tvName.setLogCipher(true);
        this.tvIp.setLogCipher(true);
        boolean zHasTextOverView = hasTextOverView(this.llBottom);
        x3.a.a(this.TAG, "----initViews---hasOver: " + zHasTextOverView);
        if (zHasTextOverView) {
            changeLayoutToVertical(this.llBottom);
            int dimension = (((int) context.getResources().getDimension(R.dimen.dp_560)) - ((int) context.getResources().getDimension(R.dimen.dp_72))) - ((int) context.getResources().getDimension(R.dimen.dp_164));
            x3.a.a(this.TAG, "----initViews---hasOver--dialogH: " + this.llBottom.getHeight() + ", height: " + dimension);
            ViewGroup.LayoutParams layoutParams = this.flWifiInfo.getLayoutParams();
            layoutParams.height = dimension;
            this.flWifiInfo.setLayoutParams(layoutParams);
        }
    }

    @Override // z3.e
    public void loadData() {
        x3.a.a(this.TAG, "mInfo = " + this.mInfo);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        int id = view.getId();
        if (id == R.id.btn_remove_pager_connected_wifi_info) {
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.mInfo, null);
                return;
            }
            return;
        }
        if (id != R.id.btn_cancel_pager_connected_wifi_info || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, this.mInfo, null);
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        x3.a.a(this.TAG, "onLtrRudderR land = " + z7);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        if (bydData == null || !(bydData instanceof ConnectedWifiInfo)) {
            return;
        }
        this.mInfo = (ConnectedWifiInfo) bydData;
        x3.a.a(this.TAG, "--updateData_connected---data: " + bydData);
        Context context = this.tvName.getContext();
        if (this.mInfo == null) {
            this.tvName.setRightText("");
            this.tvState.setRightText("");
            this.tvRssi.setRightText("");
            this.tvSpeed.setRightText("");
            this.tvSpeed.setRightText("");
            this.tvSecurity.setRightText("");
            this.tvIp.setRightText("");
            return;
        }
        String string = context.getString(R.string.connected_wireless);
        this.tvName.setRightText(this.mInfo.getName());
        this.tvState.setRightText(string);
        if (CipherType.OPEN.equals(this.mInfo.getCipherType())) {
            this.tvSecurity.setRightText(context.getString(R.string.security_open_wireless));
        } else {
            if (TextUtils.isEmpty(this.mInfo.getSecurity())) {
                this.mInfo.setSecurity(SharedPrefUtils.getSP(getContext(), WifiSettingsPager.CUR_CONNECT_WIFI_SECURITY));
            }
            this.tvSecurity.setRightText(this.mInfo.getSecurity());
        }
        this.tvIp.setRightText(this.mInfo.getIpAddr());
        WifiInfo info = this.mInfo.getInfo();
        if (info != null) {
            this.tvRssi.setRightText(ResUtils.getRssiText(this.ref.get(), BydWifiManager.Singleton.get().getRssiLevel(info.getRssi())));
            this.tvSpeed.setRightText(info.getLinkSpeed() + "Mbps");
        }
    }

    @Override // z3.g
    public ConnectedWifiInfoPresenter newP() {
        return new ConnectedWifiInfoPresenter();
    }
}
