package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LeftRightItem;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.present.ConnectedWifiInfoPresenter;
import com.byd.systemsettings.wifi2.util.ResUtils;

/* JADX INFO: loaded from: classes.dex */
public class WifiInfoPager extends a<ConnectedWifiInfoPresenter> {
    private Button btnConnect;
    private Button btnRemove;
    private ViewGroup llBottom;
    private volatile WifiScanResult mInfo;
    private LeftRightItem tvName;
    private LeftRightItem tvRssi;
    private LeftRightItem tvSecurity;
    private LeftRightItem tvState;

    public WifiInfoPager(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_wifi_info;
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
        this.tvName = (LeftRightItem) this.mRootView.findViewById(R.id.item_title_wifi_name_pager_wifi_info);
        this.tvState = (LeftRightItem) this.mRootView.findViewById(R.id.item_title_wifi_state_pager_wifi_info);
        this.tvRssi = (LeftRightItem) this.mRootView.findViewById(R.id.item_title_wifi_rssi_pager_wifi_info);
        this.tvSecurity = (LeftRightItem) this.mRootView.findViewById(R.id.item_title_wifi_security_pager_wifi_info);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_wifi_info);
        this.btnConnect = (Button) this.mRootView.findViewById(R.id.btn_connect_pager_wifi_info);
        this.btnRemove = (Button) this.mRootView.findViewById(R.id.btn_remove_pager_wifi_info);
        this.btnConnect.setOnClickListener(this);
        this.btnRemove.setOnClickListener(this);
        this.tvName.setLogCipher(true);
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_connect_pager_wifi_info) {
            if (id == R.id.btn_remove_pager_wifi_info && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, this.mInfo, null);
                return;
            }
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.mInfo, null);
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
        if (bydData == null || !(bydData instanceof WifiScanResult)) {
            return;
        }
        this.mInfo = (WifiScanResult) bydData;
        b.b.m.c.a.a(this.TAG, "---updateData---mInfo = " + this.mInfo);
        Context context = this.tvName.getContext();
        if (this.mInfo == null) {
            this.tvName.setRightText("");
            this.tvState.setRightText("");
            this.tvRssi.setRightText("");
            this.tvSecurity.setRightText("");
            this.btnConnect.setVisibility(4);
            this.btnRemove.setVisibility(8);
            return;
        }
        String string = context.getString(R.string.wifi_info_not_connect_wireless);
        this.tvName.setRightText(this.mInfo.getName());
        this.tvState.setRightText(string);
        this.tvRssi.setRightText(ResUtils.getRssiText(this.ref.get(), this.mInfo.getRssiLevel()));
        String security = this.mInfo.getSecurity();
        LeftRightItem leftRightItem = this.tvSecurity;
        if (TextUtils.isEmpty(security)) {
            security = context.getString(R.string.security_open_wireless);
        }
        leftRightItem.setRightText(security);
        this.btnRemove.setVisibility(this.mInfo.isSaved() ? 0 : 8);
        this.btnConnect.setVisibility(0);
    }

    @Override // b.b.m.e.h
    public ConnectedWifiInfoPresenter newP() {
        return new ConnectedWifiInfoPresenter();
    }
}
