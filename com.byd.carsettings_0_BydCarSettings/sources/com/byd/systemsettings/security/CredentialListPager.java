package com.byd.systemsettings.security;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.CertListAdapter;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CredentialListPager extends a<CredentialListPresenter> {
    public static final int ARG1_CERT_LIST = 305;
    private List<b.b.g.m.d.a> certList;
    private Map<String, List<b.b.g.m.d.a>> certMap;
    private int curIndex;
    private ImageView ivBack;
    private CertListAdapter mAdapter;
    private CustomViewDialog mCustomViewDialog;
    private RecyclerView rcv;
    private SegmentedButtonGroup rg;
    private View rlDataShow;
    private View rlNoDataShow;
    private String title;
    private TextView tvTitle;

    public CredentialListPager(Context context) {
        super(context);
        this.certMap = new HashMap();
        this.curIndex = 0;
    }

    private void onReceivedCertList(Message message) {
        this.certMap = (Map) message.obj;
        b.b.m.c.a.a(this.TAG, "---onReceivedCertList---certMap = " + this.certMap);
        showCertListByIndex(this.curIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCertListByIndex(int i) {
        List<b.b.g.m.d.a> list;
        b.b.m.c.a.a(this.TAG, "---showCertListByIndex---index: " + i);
        this.curIndex = i;
        this.certList.clear();
        if (i == 0) {
            List<b.b.g.m.d.a> list2 = this.certMap.get("root_cert");
            if (list2 != null) {
                this.certList.addAll(list2);
            }
        } else if (i == 1 && (list = this.certMap.get("user_cert")) != null) {
            this.certList.addAll(list);
        }
        this.mAdapter.notifyDataSetChanged();
        if (this.certList.size() <= 0) {
            this.rlDataShow.setVisibility(8);
            this.rlNoDataShow.setVisibility(0);
        } else {
            this.rlDataShow.setVisibility(0);
            this.rlNoDataShow.setVisibility(8);
            onLoadGetdata();
        }
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_credential_list;
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
        ((CredentialListPresenter) mo7getP()).init(context);
        ArrayList arrayList = new ArrayList();
        this.certList = arrayList;
        this.mAdapter = new CertListAdapter(arrayList, context);
        this.curIndex = 0;
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.security.CredentialListPager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CredentialListPager.this.mCallback != null) {
                    CredentialListPager.this.mCallback.onBackPress();
                }
                CredentialListPager.this.mCustomViewDialog.dismiss();
            }
        });
        this.rg.setOnPositionChangedListener(new SegmentedButtonGroup.f() { // from class: com.byd.systemsettings.security.CredentialListPager.2
            @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
            public void onPositionChanged(View view, View view2, int i) {
                CredentialListPager.this.showCertListByIndex(i);
            }
        });
        this.rcv.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemBtnCheckedCallbackV2(new BydAdapter.b() { // from class: com.byd.systemsettings.security.CredentialListPager.3
            @Override // com.byd.mvp.ui.adapter.BydAdapter.b
            public void onItemBtnChecked(Object obj, boolean z, BtnType btnType) {
                if (obj instanceof b.b.g.m.d.a) {
                    ((CredentialListPresenter) CredentialListPager.this.mo7getP()).setUserSelectable((b.b.g.m.d.a) obj, z);
                }
            }
        });
        ((CredentialListPresenter) mo7getP()).getInstalledBydCert(33, 305);
        onLoading();
        this.tvTitle.setText(context.getString(R.string.trusted_credential_app_manage));
        this.rg.setClickTime(50);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        initLoadingView(R.id.ll_status_in_loading);
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_credential_list);
        this.rg = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_credential_type_dialog_credential_list);
        this.rlDataShow = this.mRootView.findViewById(R.id.rl_data_show_dialog_credential_list);
        this.rlNoDataShow = this.mRootView.findViewById(R.id.rl_no_data_show_dialog_credential_list);
        this.rcv = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dialog_credential_list);
        this.ivBack = (ImageView) this.mRootView.findViewById(R.id.iv_back_dialog_credential_list);
        this.rcv.setLayoutManager(new LinearLayoutManager(context, 1, false));
        m.b(this.rcv);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        if (message.arg1 != 305) {
            return;
        }
        onReceivedCertList(message);
    }

    public void setTitle(String str) {
        this.title = str;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public CredentialListPresenter newP() {
        return new CredentialListPresenter();
    }

    public CredentialListPager(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.certMap = new HashMap();
        this.curIndex = 0;
        this.mCustomViewDialog = customViewDialog;
        customViewDialog.w();
    }
}
