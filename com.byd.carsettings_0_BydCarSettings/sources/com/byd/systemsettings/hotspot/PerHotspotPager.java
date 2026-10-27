package com.byd.systemsettings.hotspot;

import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiClient;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import b.b.a.f.h;
import b.b.f.c;
import b.b.g.p.d;
import b.b.g.r.x;
import b.b.g.r.z;
import b.b.m.e.a;
import b.b.m.e.d;
import b.b.m.f.d.b;
import b.b.m.f.d.d;
import b.b.m.g.g;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.EnableState;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydRadioGroup;
import com.byd.carsettings.ui.widget.CheckLinearItem;
import com.byd.carsettings.ui.widget.NoMenuEditText;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.TintImageView;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.hotspot.model.HotspotPagerType;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.hotspot.view.pager.ApConnectedListPager;
import com.byd.systemsettings.hotspot.view.pager.ApFlowLimitedPager;
import com.byd.systemsettings.hotspot.view.pager.ApFreqBandPager;
import com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager;
import com.byd.systemsettings.hotspot.view.pager.ApSecurityTypePager;
import com.byd.systemsettings.hotspot.view.pager.NoApConnectionPager;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes.dex */
public class PerHotspotPager extends a<PerHotspotPresenter> implements BydRadioGroup.a {
    public static final long CLICK_INTERVAL = 2000;
    private static final String TAG_W = "PerHotspotPager";
    private static final int UNIT_INDEX_GB = 1;
    private static final int UNIT_INDEX_MB = 0;
    private b.b.f.a androidAutoManager;
    private BydSwitch bydSwitch;
    private c carPlayManager;
    private volatile int connectedCount;
    private volatile HotspotPagerType curPagerType;
    private NoMenuEditText etName;
    private FrameLayout flRight;
    private CheckLinearItem itemConnectedCount;
    private CheckLinearItem itemFlowLimit;
    private CheckLinearItem itemFreqBand;
    private CheckLinearItem itemPwd;
    private CheckLinearItem itemSecurity;
    private TintImageView ivRight;
    private View llClose;
    private View llOpen;
    private OnPageBtnClickedCallback mBtnCallback;
    private Class<?> mClazz;
    private Context mContext;
    private View mLeftView;
    private Object mObj0;
    private Object mObj1;
    private int mSecurityType;
    private PagerBtnType mType;
    private ConcurrentHashMap<HotspotPagerType, a> pagerMap;
    private volatile HotspotPagerType prePagerType;
    private BydRadioGroup rg;
    private View rlName;
    private FrameLayout titleParent;
    private TextView tvName;
    private b warningDialog;

    /* JADX INFO: renamed from: com.byd.systemsettings.hotspot.PerHotspotPager$9, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass9 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$EnableState;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$mvp$callback$PagerBtnType;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType;

        static {
            int[] iArr = new int[PagerBtnType.values().length];
            $SwitchMap$com$byd$mvp$callback$PagerBtnType = iArr;
            try {
                iArr[PagerBtnType.IMAGE_BTN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[HotspotPagerType.values().length];
            $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType = iArr2;
            try {
                iArr2[HotspotPagerType.NO_CONNECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[HotspotPagerType.INPUT_PWD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[HotspotPagerType.SECURITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[HotspotPagerType.FREQ_BAND.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[HotspotPagerType.FLOW_LIMITED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[HotspotPagerType.CONNECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[EnableState.values().length];
            $SwitchMap$com$byd$bt$model$EnableState = iArr3;
            try {
                iArr3[EnableState.ENABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$byd$bt$model$EnableState[EnableState.DISABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$byd$bt$model$EnableState[EnableState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public class OnPageBtnClickedCallback implements b.b.m.b.a {
        private final WeakReference<PerHotspotPager> ref;

        public OnPageBtnClickedCallback(PerHotspotPager perHotspotPager) {
            this.ref = new WeakReference<>(perHotspotPager);
        }

        @Override // b.b.m.b.a
        public /* bridge */ /* synthetic */ void onBtnClicked(PagerBtnType pagerBtnType) {
            super.onBtnClicked(pagerBtnType);
        }

        @Override // b.b.m.b.a
        public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
            super.onBtnClicked(cls, pagerBtnType);
        }

        @Override // b.b.m.b.a
        public void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
            WeakReference<PerHotspotPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onPagerBtnClicked(cls, pagerBtnType, obj, obj2);
        }
    }

    public PerHotspotPager(Context context) {
        super(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectCPAA(boolean z, boolean z2) {
        AndroidAutoDevice androidAutoDeviceR;
        CarPlayDevice carPlayDeviceU;
        if (z && (carPlayDeviceU = this.carPlayManager.u()) != null) {
            this.carPlayManager.q(carPlayDeviceU.getBtAddr());
        }
        if (!z2 || (androidAutoDeviceR = this.androidAutoManager.r()) == null) {
            return;
        }
        String btAddr = androidAutoDeviceR.getBtAddr();
        if (TextUtils.isEmpty(btAddr)) {
            btAddr = androidAutoDeviceR.getUsbSerialNumber();
        }
        this.androidAutoManager.o(btAddr);
    }

    private void doBack() {
        d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableApNameEdit(boolean z) {
        if (!z) {
            g.b(this.etName, 6);
            this.rlName.setVisibility(8);
            this.tvName.setVisibility(0);
            g.a(this.etName);
            return;
        }
        if (((PerHotspotPresenter) mo7getP()).isHotspotEnable()) {
            this.rlName.setVisibility(0);
            this.tvName.setVisibility(8);
            this.etName.setFocusable(true);
            this.etName.setClickable(true);
            this.etName.setText(this.tvName.getText());
            this.etName.requestFocus();
            NoMenuEditText noMenuEditText = this.etName;
            noMenuEditText.setSelection(noMenuEditText.getText().length());
            g.f(this.etName);
        }
    }

    private String getCipherTypeText(int i) {
        if (getContext() == null) {
            return "";
        }
        b.b.m.c.a.a(TAG_W, "--getCipherTypeText--securityType = " + i);
        Resources resources = getContext().getResources();
        return i == 2 ? resources.getString(R.string.wpa3_cipher_security_hotspot_right) : resources.getString(R.string.cipher_security_hotspot_right);
    }

    private String getFreqBandText(int i) {
        if (getContext() == null) {
            return "";
        }
        Resources resources = getContext().getResources();
        return i == 1 ? resources.getString(R.string.g2_4_freq_band_hotspot_right) : resources.getString(R.string.g5_freq_band_hotspot_right);
    }

    private String getLimitText(int i) {
        return i + "";
    }

    private a getPager(HotspotPagerType hotspotPagerType) {
        a noApConnectionPager = this.pagerMap.get(hotspotPagerType);
        if (noApConnectionPager != null) {
            return noApConnectionPager;
        }
        if (this.mBtnCallback == null) {
            this.mBtnCallback = new OnPageBtnClickedCallback(this);
        }
        switch (AnonymousClass9.$SwitchMap$com$byd$systemsettings$hotspot$model$HotspotPagerType[hotspotPagerType.ordinal()]) {
            case 1:
                noApConnectionPager = new NoApConnectionPager(getContext());
                break;
            case 2:
                noApConnectionPager = new ApPwdSettingPager(getContext());
                noApConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 3:
                noApConnectionPager = new ApSecurityTypePager(getContext());
                noApConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 4:
                noApConnectionPager = new ApFreqBandPager(getContext());
                noApConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 5:
                noApConnectionPager = new ApFlowLimitedPager(getContext());
                noApConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 6:
                noApConnectionPager = new ApConnectedListPager(getContext());
                noApConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
        }
        if (noApConnectionPager != null) {
            this.pagerMap.put(hotspotPagerType, noApConnectionPager);
        }
        return noApConnectionPager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCPAAConnected() {
        boolean zY = this.carPlayManager.y();
        boolean zX = this.androidAutoManager.x();
        boolean z = zY || zX;
        if (z) {
            showConfigChangeWarningDialog(zX ? "Android Auto" : "Apple CarPlay", zY, zX);
        }
        return z;
    }

    private void loadTitleOnLayoutLocked() {
        this.bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_title_hotspot);
        this.mRootView.findViewById(R.id.iv_back_hotspot).setOnClickListener(this);
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_local_device_hotspot_name_hotspot);
        this.etName = (NoMenuEditText) this.mRootView.findViewById(R.id.et_local_device_hotspot_name_hotspot);
        this.rlName = this.mRootView.findViewById(R.id.rl_title_hotspot_name_hotspot);
        TintImageView tintImageView = (TintImageView) this.mRootView.findViewById(R.id.iv_right_local_device_hotspot);
        this.ivRight = tintImageView;
        tintImageView.setOnClickListener(this);
        this.mRootView.findViewById(R.id.iv_close_edit_hotspot).setOnClickListener(this);
        this.bydSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ((PerHotspotPresenter) PerHotspotPager.this.mo7getP()).setHotspotEnable(z);
                PerHotspotPager.this.bydSwitch.setInvalid(true);
            }
        });
        this.bydSwitch.setOnInterceptTouchListener(new BydSwitch.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.2
            @Override // com.byd.base.view.BydSwitch.a
            public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
                boolean z2 = true;
                b.b.m.c.a.a(PerHotspotPager.TAG_W, "---OnBydSwitchTouch---isChecked = " + z);
                PerHotspotPager.this.bydSwitch.playSoundEffect(0);
                if (motionEvent.getAction() != 1) {
                    return;
                }
                if (z) {
                    PerHotspotPager.this.bydSwitch.setChecked(true);
                    return;
                }
                boolean zY = PerHotspotPager.this.carPlayManager.y();
                boolean zX = PerHotspotPager.this.androidAutoManager.x();
                if (!zY && !zX) {
                    z2 = false;
                }
                if (z2) {
                    PerHotspotPager.this.showWarningDialog(zX ? "Android Auto" : "Apple CarPlay", zY, zX);
                } else {
                    PerHotspotPager.this.bydSwitch.setChecked(false);
                }
            }
        });
        this.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    PerHotspotPager.this.mClazz = null;
                    String strTrim = PerHotspotPager.this.etName.getText().toString().trim();
                    String hotspotName = ((PerHotspotPresenter) PerHotspotPager.this.mo7getP()).getHotspotName();
                    if (PerHotspotPager.this.isCPAAConnected()) {
                        return false;
                    }
                    if (TextUtils.isEmpty(strTrim) || strTrim.equals(hotspotName)) {
                        strTrim = hotspotName;
                    } else {
                        ((PerHotspotPresenter) PerHotspotPager.this.mo7getP()).setHotspotName(strTrim);
                        PerHotspotPager.this.restartHotspot();
                    }
                    PerHotspotPager.this.tvName.setText(strTrim);
                    PerHotspotPager.this.enableApNameEdit(false);
                }
                return false;
            }
        });
        NoMenuEditText noMenuEditText = this.etName;
        noMenuEditText.addTextChangedListener(new b.b.g.q.h.a(noMenuEditText, b.b.g.q.h.a.getNameRegex(noMenuEditText.getContext())) { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.4
            @Override // b.b.g.q.h.a
            public void onInputFiltered(String str) {
                z.b(PerHotspotPager.this.getContext(), R.string.hint_unsupport_input);
            }
        });
        this.etName.setOnBackEventListener(new NoMenuEditText.b() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.5
            @Override // com.byd.carsettings.ui.widget.NoMenuEditText.b
            public void onBackPress() {
                PerHotspotPager.this.enableApNameEdit(false);
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    return;
                }
                PerHotspotPager.this.enableApNameEdit(false);
            }
        });
    }

    private void onFlowLimitedPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 2) {
            if (i != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue != ((PerHotspotPresenter) mo7getP()).getHotspotFlowLimit()) {
                ((PerHotspotPresenter) mo7getP()).setHotspotFlowLimit(iIntValue);
                restartHotspot();
            }
            this.itemFlowLimit.setRightText(iIntValue + "");
        }
    }

    private void onFreqBandPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 2) {
            if (i != 3) {
                return;
            }
            doBack();
            return;
        }
        if (isCPAAConnected()) {
            return;
        }
        onShowConnectedCountView(this.connectedCount);
        int iIntValue = ((Integer) obj).intValue();
        this.itemFreqBand.setRightText(getFreqBandText(iIntValue));
        int hotspotApBand = ((PerHotspotPresenter) mo7getP()).getHotspotApBand();
        b.b.m.c.a.a(TAG_W, "---onFreqBandPagerClicked--BOTTOM_LEFT--freqBand: " + iIntValue + ", band: " + hotspotApBand);
        if (iIntValue != hotspotApBand) {
            ((PerHotspotPresenter) mo7getP()).setHotspotApBand(iIntValue);
            restartHotspot();
        }
    }

    private void onPwdPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i == 1) {
            if (obj instanceof Boolean) {
                b.b.m.c.a.a(TAG_W, "---onPwdPagerClicked--IMAGE_BTN--");
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                this.itemPwd.setRightTextType(zBooleanValue);
                ((PerHotspotPresenter) mo7getP()).getDefHotspotParams().setPwdHide(zBooleanValue);
                return;
            }
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            String str = (String) obj;
            if (!str.equals(((PerHotspotPresenter) mo7getP()).getHotspotPwd())) {
                ((PerHotspotPresenter) mo7getP()).setHotspotPwd(str);
                restartHotspot();
            }
            this.itemPwd.setRightText(x.h(str));
        }
    }

    private void onSecurityPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 2) {
            if (i != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            int iIntValue = ((Integer) obj).intValue();
            this.mSecurityType = iIntValue;
            if (iIntValue != ((PerHotspotPresenter) mo7getP()).getApSecurityType()) {
                ((PerHotspotPresenter) mo7getP()).setApSecurityType(this.mSecurityType);
                this.itemPwd.setVisibility(this.mSecurityType == 0 ? 8 : 0);
                restartHotspot();
            }
            this.itemSecurity.setRightText(getCipherTypeText(this.mSecurityType));
        }
    }

    private void onShowConnectedCountView(int i) {
        this.connectedCount = i;
        if (i <= 0) {
            showPageOnRight(HotspotPagerType.NO_CONNECTION, null);
            this.itemConnectedCount.setRightText("0");
            return;
        }
        this.itemConnectedCount.setRightText(i + "");
        showPageOnRight(HotspotPagerType.CONNECTED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartHotspot() {
        this.rg.b();
        ((PerHotspotPresenter) mo7getP()).restartHotspot();
        z.b(getContext(), R.string.modify_completed);
    }

    private void showConfigChangeWarningDialog(String str, final boolean z, final boolean z2) {
        Resources resources = this.ref.get().getResources();
        this.warningDialog = b.b.i.d.A(this.ref.get(), resources.getString(R.string.cp_warning), resources.getString(R.string.carplay_re_start_hotspot, str), resources.getString(R.string.dilink_yes), resources.getString(R.string.dilink_no), new d.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.8
            @Override // b.b.m.f.d.d.a
            public void onOKClicked() {
                h.a().g(new Runnable() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PerHotspotPager.this.mClazz == null) {
                            PerHotspotPager.this.etName.onEditorAction(6);
                        } else {
                            PerHotspotPager perHotspotPager = PerHotspotPager.this;
                            perHotspotPager.onPagerBtnClicked(perHotspotPager.mClazz, PerHotspotPager.this.mType, PerHotspotPager.this.mObj0, PerHotspotPager.this.mObj1);
                        }
                    }
                }, 500L);
                PerHotspotPager.this.disconnectCPAA(z, z2);
            }
        });
    }

    private void showDefPager() {
        if (this.connectedCount <= 0) {
            showPageOnRight(HotspotPagerType.NO_CONNECTION, null);
        } else {
            showPageOnRight(HotspotPagerType.CONNECTED, null);
        }
    }

    private void showPageOnRight(HotspotPagerType hotspotPagerType, BydData bydData) {
        b.b.m.c.a.a(TAG_W, "---showPageOnRight---type = " + hotspotPagerType);
        this.flRight.setVisibility(0);
        a pager = getPager(hotspotPagerType);
        if (pager != null) {
            pager.updateData(bydData);
        }
        if (this.curPagerType != hotspotPagerType) {
            this.prePagerType = this.curPagerType;
            if (this.prePagerType != null) {
                this.pagerMap.get(this.prePagerType).onHide();
            }
            this.curPagerType = hotspotPagerType;
            this.flRight.removeAllViews();
            pager.loadData();
            this.flRight.addView(pager.getRootView());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWarningDialog(String str, final boolean z, final boolean z2) {
        Resources resources = this.ref.get().getResources();
        this.warningDialog = b.b.i.d.A(this.ref.get(), resources.getString(R.string.cp_warning), resources.getString(R.string.carplay_close_hotspot, str), resources.getString(R.string.dilink_yes), resources.getString(R.string.dilink_no), new d.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.7
            @Override // b.b.m.f.d.d.a
            public void onOKClicked() {
                PerHotspotPager.this.bydSwitch.setChecked(false);
                PerHotspotPager.this.disconnectCPAA(z, z2);
            }
        });
    }

    private void updateUIOnHotspotStateChanged(boolean z) {
        this.bydSwitch.setInvalid(false);
        this.bydSwitch.setChecked(z, false);
        this.itemFreqBand.setEnabled(z);
        b.b.m.c.a.a(TAG_W, "---onHotspotStateChanged---open: " + z);
        if (getContext() == null) {
            return;
        }
        Resources resources = getContext().getResources();
        if (z) {
            this.llOpen.setVisibility(0);
            this.llClose.setVisibility(8);
            this.tvName.setTextColor(resources.getColor(R.color.text_color_main));
            this.ivRight.b(1);
            this.rg.b();
            return;
        }
        this.llOpen.setVisibility(8);
        this.llClose.setVisibility(0);
        this.rlName.setVisibility(8);
        this.tvName.setVisibility(0);
        this.tvName.setTextColor(resources.getColor(R.color.text_color_main_gray));
        this.ivRight.b(0);
        g.a(this.etName);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.activity_hotspot;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ b.b.m.e.g mo7getP() {
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
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.mContext = context;
        if (this.pagerMap == null) {
            this.pagerMap = new ConcurrentHashMap<>();
        }
        ((PerHotspotPresenter) mo7getP()).init(context, 33);
        this.mLeftView = this.mRootView.findViewById(R.id.rl_left_hotspot);
        this.titleParent = (FrameLayout) this.mRootView.findViewById(R.id.fl_title_hotspot);
        this.llOpen = this.mRootView.findViewById(R.id.ll_hotspot_state_open_hotspot);
        this.llClose = this.mRootView.findViewById(R.id.ll_hotspot_state_close_hotspot);
        this.flRight = (FrameLayout) this.mRootView.findViewById(R.id.fl_right_hotspot);
        BydRadioGroup bydRadioGroup = (BydRadioGroup) this.mRootView.findViewById(R.id.rg_hotshpot_left);
        this.rg = bydRadioGroup;
        bydRadioGroup.setOnCheckedChangeListener(this);
        CheckLinearItem checkLinearItem = (CheckLinearItem) this.rg.findViewById(R.id.item_pwd_hotspot_left);
        this.itemPwd = checkLinearItem;
        checkLinearItem.setPwdRight(true);
        this.itemSecurity = (CheckLinearItem) this.rg.findViewById(R.id.item_security_hotspot_left);
        this.itemFreqBand = (CheckLinearItem) this.rg.findViewById(R.id.item_freq_band_hotspot_left);
        this.itemFlowLimit = (CheckLinearItem) this.rg.findViewById(R.id.item_flow_limited_hotspot_left);
        this.itemConnectedCount = (CheckLinearItem) this.mRootView.findViewById(R.id.item_connected_count_hotspot_left);
        this.itemFlowLimit.setVisibility(8);
        this.itemConnectedCount.setCheckable(false);
        this.carPlayManager = c.w(context);
        this.androidAutoManager = b.b.f.a.t(context);
        if (AppConfig.a() == CarModel$Brand.DENZA) {
            this.mLeftView.setBackgroundResource(R.drawable.bg_dialog_bt_left_tenz_land);
        }
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    @Override // com.byd.carsettings.ui.widget.BydRadioGroup.a
    public void onCheckedChanged(BydRadioGroup bydRadioGroup, int i) {
        HotspotParams defHotspotParams = ((PerHotspotPresenter) mo7getP()).getDefHotspotParams();
        if (i == R.id.item_freq_band_hotspot_left) {
            defHotspotParams.setApBand(((PerHotspotPresenter) mo7getP()).getHotspotApBand());
            showPageOnRight(HotspotPagerType.FREQ_BAND, defHotspotParams);
            g.a(this.etName);
            enableApNameEdit(false);
            return;
        }
        if (i == R.id.item_pwd_hotspot_left) {
            defHotspotParams.setPwdHide(this.itemPwd.l());
            showPageOnRight(HotspotPagerType.INPUT_PWD, defHotspotParams);
            g.a(this.etName);
            enableApNameEdit(false);
            return;
        }
        if (i != R.id.item_security_hotspot_left) {
            return;
        }
        defHotspotParams.setApSecurityType(this.mSecurityType);
        showPageOnRight(HotspotPagerType.SECURITY, defHotspotParams);
        g.a(this.etName);
        enableApNameEdit(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.c(view, 2000L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_back_hotspot) {
            doBack();
            return;
        }
        if (id == R.id.iv_close_edit_hotspot) {
            this.etName.setText("");
            this.etName.setHint(((PerHotspotPresenter) mo7getP()).getHotspotName());
        } else {
            if (id != R.id.iv_right_local_device_hotspot) {
                return;
            }
            enableApNameEdit(((PerHotspotPresenter) mo7getP()).isHotspotEnable() && this.bydSwitch.isChecked());
        }
    }

    public void onConnectedClientsChanged(List<WifiClient> list) {
        b.b.m.c.a.a(TAG_W, "---onNumClientsChanged----clients = " + list);
        onShowConnectedCountView(list.size());
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        b.b.i.d.v(BydApp.getContext(), false);
        b.b.m.f.d.d.c(getClass().getName());
        g.a(this.etName);
        b bVar = this.warningDialog;
        if (bVar != null) {
            bVar.c();
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
        if (i == 1) {
            onHotspotStateChanged((EnableState) message.obj);
        } else {
            if (i != 16) {
                return;
            }
            onConnectedClientsChanged((List) message.obj);
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onHide() {
        super.onHide();
        g.a(this.etName);
    }

    public void onHotspotStateChanged(EnableState enableState) {
        b.b.m.c.a.a(TAG_W, "---onHotspotStateChanged---state = " + enableState);
        if (enableState == EnableState.DISABLE || enableState == EnableState.ENABLE) {
            int i = AnonymousClass9.$SwitchMap$com$byd$bt$model$EnableState[enableState.ordinal()];
            if (i != 1) {
                if (i == 2 || i == 3) {
                    updateUIOnHotspotStateChanged(false);
                    return;
                }
                return;
            }
            this.itemPwd.setRightText(x.h(((PerHotspotPresenter) mo7getP()).getHotspotPwd()));
            b.b.m.c.a.a(TAG_W, "---onHotspotStateChanged---params = " + ((PerHotspotPresenter) mo7getP()).getDefHotspotParams());
            this.itemPwd.setRightTextType(true);
            updateUIOnHotspotStateChanged(true);
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderL(boolean z) {
        super.onLtrRudderL(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        super.onLtrRudderR(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings_r, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    public void onPagerBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        if (cls == null) {
            return;
        }
        b.b.m.c.a.a(TAG_W, "---onPagerBtnClicked---clazz = " + cls.getName() + ", type = " + pagerBtnType);
        this.mClazz = cls;
        this.mType = pagerBtnType;
        this.mObj0 = obj;
        this.mObj1 = obj2;
        String name = cls.getName();
        if (ApPwdSettingPager.class.getName().equals(name)) {
            onPwdPagerClicked(pagerBtnType, obj, obj2);
            return;
        }
        if (ApSecurityTypePager.class.getName().equals(name)) {
            onSecurityPagerClicked(pagerBtnType, obj, obj2);
        } else if (ApFreqBandPager.class.getName().equals(name)) {
            onFreqBandPagerClicked(pagerBtnType, obj, obj2);
        } else if (ApFlowLimitedPager.class.getName().equals(name)) {
            onFlowLimitedPagerClicked(pagerBtnType, obj, obj2);
        }
    }

    @Override // b.b.m.e.a
    public void onResume() {
        super.onResume();
        boolean zIsHotspotEnable = ((PerHotspotPresenter) mo7getP()).isHotspotEnable();
        this.bydSwitch.setChecked(zIsHotspotEnable, false);
        String hotspotName = ((PerHotspotPresenter) mo7getP()).getHotspotName();
        this.tvName.setText(hotspotName);
        this.etName.setHint(hotspotName);
        this.itemPwd.setRightText(x.h(((PerHotspotPresenter) mo7getP()).getHotspotPwd()));
        this.itemPwd.setRightTextSingleLine();
        this.itemPwd.setRightTextType(true);
        int apSecurityType = ((PerHotspotPresenter) mo7getP()).getApSecurityType();
        this.mSecurityType = apSecurityType;
        this.itemPwd.setVisibility(apSecurityType == 0 ? 8 : 0);
        this.itemSecurity.setRightText(getCipherTypeText(this.mSecurityType));
        boolean zF0 = d.g.a().f0();
        b.b.m.c.a.a(this.TAG, "---onResume---unsupport5GHz: " + zF0);
        if (zF0) {
            this.itemFreqBand.setRightText(getFreqBandText(1));
        } else {
            this.itemFreqBand.setRightText(getFreqBandText(((PerHotspotPresenter) mo7getP()).getHotspotApBand()));
        }
        this.itemFreqBand.setRightTextSingleLine();
        this.itemFlowLimit.setRightText(getLimitText(((PerHotspotPresenter) mo7getP()).getHotspotFlowLimit()));
        int hotspotConnectedCount = ((PerHotspotPresenter) mo7getP()).getHotspotConnectedCount();
        b.b.m.c.a.a(TAG_W, "---onResume---isOpen = " + zIsHotspotEnable + ", connectedCount: " + hotspotConnectedCount);
        onShowConnectedCountView(hotspotConnectedCount);
        updateUIOnHotspotStateChanged(zIsHotspotEnable);
        if (zIsHotspotEnable) {
            return;
        }
        ((PerHotspotPresenter) mo7getP()).setHotspotEnable(true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        super.onRtlRudderL(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings_rtl, (ViewGroup) null));
        loadTitleOnLayoutLocked();
        setViewGroupRtl(this.titleParent, false);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public PerHotspotPresenter newP() {
        return new PerHotspotPresenter();
    }
}
