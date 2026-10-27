package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import b.b.a.h.i;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LeftRightItem;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.BydWifiManager;
import com.byd.systemsettings.wifi2.WifiSettingsPager;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.ConnectedWifiInfo;
import com.byd.systemsettings.wifi2.present.ConnectedWifiInfoPresenter;
import com.byd.systemsettings.wifi2.util.ResUtils;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedWifiInfoPager extends a<ConnectedWifiInfoPresenter> {
    private ViewGroup llBottom;
    private volatile ConnectedWifiInfo mInfo;
    private LeftRightItem tvIp;
    private LeftRightItem tvName;
    private LeftRightItem tvRssi;
    private LeftRightItem tvSecurity;
    private LeftRightItem tvSpeed;
    private LeftRightItem tvState;

    public ConnectedWifiInfoPager(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_wifi_info_connected;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ g mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ Object mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.tvName = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_name_pager_connected_wifi_info);
        this.tvState = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_state_pager_connected_wifi_info);
        this.tvRssi = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_rssi_pager_connected_wifi_info);
        this.tvSpeed = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_speed_pager_connected_wifi_info);
        this.tvSecurity = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_security_pager_connected_wifi_info);
        LeftRightItem leftRightItem = (LeftRightItem) this.mRootView.findViewById(R.id.item_wifi_ip_pager_connected_wifi_info);
        this.tvIp = leftRightItem;
        leftRightItem.setShowOnLog(false);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_info_connected);
        this.mRootView.findViewById(R.id.btn_remove_pager_connected_wifi_info).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_pager_connected_wifi_info).setOnClickListener(this);
        this.tvName.setLogCipher(true);
        this.tvIp.setLogCipher(true);
    }

    @Override // b.b.m.e.f
    public void loadData() {
        b.b.m.c.a.a(this.TAG, "mInfo = " + this.mInfo);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_cancel_pager_connected_wifi_info) {
            if (id == R.id.btn_remove_pager_connected_wifi_info && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.mInfo, null);
                return;
            }
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, this.mInfo, null);
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderL(boolean z) {
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        b.b.m.c.a.a(this.TAG, "onLtrRudderR land = " + z);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        if (bydData == null || !(bydData instanceof ConnectedWifiInfo)) {
            return;
        }
        this.mInfo = (ConnectedWifiInfo) bydData;
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
                this.mInfo.setSecurity(i.a(getContext(), WifiSettingsPager.CUR_CONNECT_WIFI_SECURITY));
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

    @Override // b.b.m.e.h
    public ConnectedWifiInfoPresenter newP() {
        return new ConnectedWifiInfoPresenter();
    }
}
