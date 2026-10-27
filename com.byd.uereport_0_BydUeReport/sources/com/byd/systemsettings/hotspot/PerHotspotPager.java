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
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.EnableState;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.BydRadioGroup;
import com.byd.mvp.ui.widget.NoMenuEditText;
import com.byd.mvp.ui.widget.TintImageView;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.R;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.systemsettings.hotspot.model.HotspotPagerType;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.hotspot.view.pager.ApConnectedListPager;
import com.byd.systemsettings.hotspot.view.pager.ApFlowLimitedPager;
import com.byd.systemsettings.hotspot.view.pager.ApFreqBandPager;
import com.byd.systemsettings.hotspot.view.pager.ApPwdSettingPager;
import com.byd.systemsettings.hotspot.view.pager.ApSecurityTypePager;
import com.byd.systemsettings.hotspot.view.pager.NoApConnectionPager;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.SystemSettingsModule;
import com.byd.systemsettings.system.utils.TextLengthUtil;
import com.byd.systemsettings.ui.cache.DialogStatesCache;
import com.byd.systemsettings.ui.cache.model.DialogState;
import com.byd.systemsettings.ui.cache.model.HotspotDialogState;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.byd.systemsettings.ui.widget.CheckLinearItem;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import k4.c;
import m4.e;
import o2.b;
import p2.g;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import z3.a;
import z3.d;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class PerHotspotPager extends a<PerHotspotPresenter> implements BydRadioGroup.a, c {
    private static final String TAG_W = "PerHotspotPager";
    private static final int UNIT_INDEX_GB = 1;
    private static final int UNIT_INDEX_MB = 0;
    private AndroidAutoManager androidAutoManager;
    private BydSwitch bydSwitch;
    private CarPlayManager carPlayManager;
    private volatile int connectedCount;
    private volatile HotspotPagerType curPagerType;
    private NoMenuEditText etName;
    private FrameLayout flRight;
    private View hintBottom;
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
    private b mIJumpService;
    private View mLeftView;
    private Object mObj0;
    private Object mObj1;
    private int mSecurityType;
    private PagerBtnType mType;
    private ConcurrentHashMap<HotspotPagerType, a> pagerMap;
    private int preItemIndex;
    private volatile HotspotPagerType prePagerType;
    private BydRadioGroup rg;
    private View rlName;
    private FrameLayout titleParent;
    private TextView tvName;

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

    public class OnPageBtnClickedCallback implements w3.a {
        private final WeakReference<PerHotspotPager> ref;

        public OnPageBtnClickedCallback(PerHotspotPager perHotspotPager) {
            this.ref = new WeakReference<>(perHotspotPager);
        }

        @Override // w3.a
        public /* bridge */ /* synthetic */ void onBtnClicked(PagerBtnType pagerBtnType) {
            super.onBtnClicked(pagerBtnType);
        }

        @Override // w3.a
        public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
            super.onBtnClicked(cls, pagerBtnType);
        }

        @Override // w3.a
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
        this.preItemIndex = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectCPAA(boolean z7, boolean z8) {
        AndroidAutoDevice connectedAndroidAutoDevice;
        CarPlayDevice connectedCarPlayDevice;
        if (z7 && (connectedCarPlayDevice = this.carPlayManager.getConnectedCarPlayDevice()) != null) {
            this.carPlayManager.disconnectCarPlay(connectedCarPlayDevice.getBtAddr());
        }
        if (!z8 || (connectedAndroidAutoDevice = this.androidAutoManager.getConnectedAndroidAutoDevice()) == null) {
            return;
        }
        String btAddr = connectedAndroidAutoDevice.getBtAddr();
        if (TextUtils.isEmpty(btAddr)) {
            btAddr = connectedAndroidAutoDevice.getUsbSerialNumber();
        }
        this.androidAutoManager.disconnectAndroidAuto(btAddr);
    }

    private void doBack() {
        d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableApNameEdit(boolean z7) {
        x3.a.a(TAG_W, "---enableApNameEdit---enable: " + z7);
        if (!z7) {
            this.rlName.setVisibility(8);
            this.tvName.setVisibility(0);
            delayToCloseImeOnOtherPage(this.etName);
        } else if (((PerHotspotPresenter) getP()).isHotspotEnable()) {
            this.rlName.setVisibility(0);
            this.tvName.setVisibility(8);
            this.etName.setFocusable(true);
            this.etName.setClickable(true);
            this.etName.setText(this.tvName.getText());
            this.etName.requestFocus();
            NoMenuEditText noMenuEditText = this.etName;
            noMenuEditText.setSelection(noMenuEditText.getText().length());
            e.j(this.etName);
        }
    }

    private String getCipherTypeText(int i8) {
        if (getContext() == null) {
            return "";
        }
        x3.a.a(TAG_W, "--getCipherTypeText--securityType = " + i8);
        Resources resources = getContext().getResources();
        return i8 == 2 ? resources.getString(R.string.wpa3_cipher_security_hotspot_right) : resources.getString(R.string.cipher_security_hotspot_right);
    }

    private String getFreqBandText(int i8) {
        if (getContext() == null) {
            return "";
        }
        Resources resources = getContext().getResources();
        return i8 == 1 ? resources.getString(R.string.g2_4_freq_band_hotspot_right) : resources.getString(R.string.g5_freq_band_hotspot_right);
    }

    private String getLimitText(int i8) {
        return i8 + "";
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
        boolean zIsCarPlayConnected = this.carPlayManager.isCarPlayConnected();
        boolean zIsAndroidAutoWifiConnected = this.androidAutoManager.isAndroidAutoWifiConnected();
        boolean z7 = zIsCarPlayConnected || zIsAndroidAutoWifiConnected;
        if (z7) {
            showConfigChangeWarningDialog(zIsAndroidAutoWifiConnected ? "Android Auto" : "Apple CarPlay", zIsCarPlayConnected, zIsAndroidAutoWifiConnected);
        }
        return z7;
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
            public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                ((PerHotspotPresenter) PerHotspotPager.this.getP()).setHotspotEnable(z7);
                PerHotspotPager.this.bydSwitch.setInvalid(true);
            }
        });
        this.bydSwitch.setOnInterceptTouchListener(new BydSwitch.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.2
            @Override // com.byd.base.view.BydSwitch.a
            public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z7) {
                boolean z8 = true;
                x3.a.a(PerHotspotPager.TAG_W, "---OnBydSwitchTouch---isChecked = " + z7);
                PerHotspotPager.this.bydSwitch.playSoundEffect(0);
                if (motionEvent.getAction() != 1) {
                    return;
                }
                if (z7) {
                    PerHotspotPager.this.bydSwitch.setChecked(true);
                    return;
                }
                boolean zIsCarPlayConnected = PerHotspotPager.this.carPlayManager.isCarPlayConnected();
                boolean zIsAndroidAutoWifiConnected = PerHotspotPager.this.androidAutoManager.isAndroidAutoWifiConnected();
                if (!zIsCarPlayConnected && !zIsAndroidAutoWifiConnected) {
                    z8 = false;
                }
                if (z8) {
                    PerHotspotPager.this.showWarningDialog(zIsAndroidAutoWifiConnected ? "Android Auto" : "Apple CarPlay", zIsCarPlayConnected, zIsAndroidAutoWifiConnected);
                } else {
                    PerHotspotPager.this.bydSwitch.setChecked(false);
                }
            }
        });
        this.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                if (i8 == 6) {
                    PerHotspotPager.this.mClazz = null;
                    String strTrim = PerHotspotPager.this.etName.getText().toString().trim();
                    String hotspotName = ((PerHotspotPresenter) PerHotspotPager.this.getP()).getHotspotName();
                    if (PerHotspotPager.this.isCPAAConnected()) {
                        return false;
                    }
                    if (TextUtils.isEmpty(strTrim) || strTrim.equals(hotspotName)) {
                        strTrim = hotspotName;
                    } else {
                        ((PerHotspotPresenter) PerHotspotPager.this.getP()).setHotspotName(strTrim);
                        PerHotspotPager.this.restartHotspot();
                    }
                    PerHotspotPager.this.tvName.setText(strTrim);
                    PerHotspotPager.this.enableApNameEdit(false);
                }
                return false;
            }
        });
        NoMenuEditText noMenuEditText = this.etName;
        noMenuEditText.addTextChangedListener(new BydTextWatcher(noMenuEditText, BydTextWatcher.getNameRegex(noMenuEditText.getContext())) { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.4
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher
            public void onInputFiltered(String str) {
                q2.d.b(PerHotspotPager.this.getContext(), R.string.hint_unsupport_input);
            }
        });
        this.etName.setOnBackEventListener(new NoMenuEditText.b() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.5
            @Override // com.byd.mvp.ui.widget.NoMenuEditText.b
            public void onBackPress() {
                PerHotspotPager.this.enableApNameEdit(false);
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                x3.a.a(PerHotspotPager.TAG_W, "---etName.onFocusChange---hasFocus: " + z7);
                if (z7) {
                    return;
                }
                PerHotspotPager.this.enableApNameEdit(false);
            }
        });
    }

    private void onFlowLimitedPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i8 = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 2) {
            if (i8 != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue != ((PerHotspotPresenter) getP()).getHotspotFlowLimit()) {
                ((PerHotspotPresenter) getP()).setHotspotFlowLimit(iIntValue);
                restartHotspot();
            }
            this.itemFlowLimit.setRightText(iIntValue + "");
        }
    }

    private void onFreqBandPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i8 = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 2) {
            if (i8 != 3) {
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
        int hotspotApBand = ((PerHotspotPresenter) getP()).getHotspotApBand();
        x3.a.a(TAG_W, "---onFreqBandPagerClicked--BOTTOM_LEFT--freqBand: " + iIntValue + ", band: " + hotspotApBand);
        if (iIntValue != hotspotApBand) {
            ((PerHotspotPresenter) getP()).setHotspotApBand(iIntValue);
            restartHotspot();
        }
    }

    private void onPwdPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i8 = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 == 1) {
            if (obj instanceof Boolean) {
                x3.a.a(TAG_W, "---onPwdPagerClicked--IMAGE_BTN--");
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                this.itemPwd.setRightTextType(zBooleanValue);
                ((PerHotspotPresenter) getP()).getDefHotspotParams().setPwdHide(zBooleanValue);
                return;
            }
            return;
        }
        if (i8 != 2) {
            if (i8 != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            String str = (String) obj;
            if (!str.equals(((PerHotspotPresenter) getP()).getHotspotPwd())) {
                ((PerHotspotPresenter) getP()).setHotspotPwd(str);
                restartHotspot();
            }
            this.itemPwd.setRightText(TextLengthUtil.getHotspotPwd(str));
        }
    }

    private void onSecurityPagerClicked(PagerBtnType pagerBtnType, Object obj, Object obj2) {
        int i8 = AnonymousClass9.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 2) {
            if (i8 != 3) {
                return;
            }
            doBack();
        } else {
            if (isCPAAConnected()) {
                return;
            }
            int iIntValue = ((Integer) obj).intValue();
            this.mSecurityType = iIntValue;
            if (iIntValue != ((PerHotspotPresenter) getP()).getApSecurityType()) {
                ((PerHotspotPresenter) getP()).setApSecurityType(this.mSecurityType);
                this.itemPwd.setVisibility(this.mSecurityType == 0 ? 8 : 0);
                restartHotspot();
            }
            this.itemSecurity.setRightText(getCipherTypeText(this.mSecurityType));
        }
    }

    private void onShowConnectedCountView(int i8) {
        this.connectedCount = i8;
        if (i8 <= 0) {
            showPageOnRight(HotspotPagerType.NO_CONNECTION, null);
            this.itemConnectedCount.setRightText("0");
            return;
        }
        this.itemConnectedCount.setRightText(i8 + "");
        showPageOnRight(HotspotPagerType.CONNECTED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartHotspot() {
        this.rg.b();
        ((PerHotspotPresenter) getP()).restartHotspot();
        q2.d.b(getContext(), R.string.modify_completed);
    }

    private void showConfigChangeWarningDialog(String str, final boolean z7, final boolean z8) {
        this.mIJumpService.jump2OffHotspotWarningDialog(2, this.ref.get().getResources().getString(R.string.carplay_re_start_hotspot, str), new b.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.8
            @Override // o2.b.a
            public void onOkResult(Object obj) {
                g.a().d(new Runnable() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.8.1
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
                PerHotspotPager.this.disconnectCPAA(z7, z8);
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
        x3.a.a(TAG_W, "---showPageOnRight---type = " + hotspotPagerType);
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
    public void showWarningDialog(String str, final boolean z7, final boolean z8) {
        this.mIJumpService.jump2OffHotspotWarningDialog(1, this.ref.get().getResources().getString(R.string.carplay_close_hotspot, str), new b.a() { // from class: com.byd.systemsettings.hotspot.PerHotspotPager.7
            @Override // o2.b.a
            public void onOkResult(Object obj) {
                PerHotspotPager.this.bydSwitch.setEverTouch(true);
                PerHotspotPager.this.bydSwitch.setChecked(false);
                PerHotspotPager.this.disconnectCPAA(z7, z8);
            }
        });
    }

    private void updateUIOnHotspotStateChanged(boolean z7) {
        this.bydSwitch.setInvalid(false);
        this.bydSwitch.b(z7, false);
        this.itemFreqBand.setEnabled(z7);
        x3.a.a(TAG_W, "---onHotspotStateChanged---open: " + z7);
        if (getContext() == null) {
            return;
        }
        Resources resources = getContext().getResources();
        if (z7) {
            this.llOpen.setVisibility(0);
            this.hintBottom.setVisibility(0);
            this.llClose.setVisibility(8);
            this.tvName.setTextColor(resources.getColor(R.color.text_color_main));
            this.ivRight.b(1);
            this.rg.b();
            return;
        }
        this.llOpen.setVisibility(8);
        this.hintBottom.setVisibility(0);
        this.llClose.setVisibility(0);
        this.rlName.setVisibility(8);
        this.tvName.setVisibility(0);
        this.tvName.setTextColor(resources.getColor(R.color.text_color_main_gray));
        this.ivRight.b(0);
        e.b(this.etName);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_hotspot;
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
    public boolean initHandler() {
        return true;
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_586);
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.mContext = context;
        if (this.pagerMap == null) {
            this.pagerMap = new ConcurrentHashMap<>();
        }
        ((PerHotspotPresenter) getP()).init(context, 33);
        this.mLeftView = this.mRootView.findViewById(R.id.rl_left_hotspot);
        this.titleParent = (FrameLayout) this.mRootView.findViewById(R.id.fl_title_hotspot);
        this.llOpen = this.mRootView.findViewById(R.id.ll_hotspot_state_open_hotspot);
        this.llClose = this.mRootView.findViewById(R.id.ll_hotspot_state_close_hotspot);
        this.flRight = (FrameLayout) this.mRootView.findViewById(R.id.fl_right_hotspot);
        this.hintBottom = this.mRootView.findViewById(R.id.ll_bottom_hint_text_open_hotspot);
        BydRadioGroup bydRadioGroup = (BydRadioGroup) this.mRootView.findViewById(R.id.rg_hotshpot_left);
        this.rg = bydRadioGroup;
        bydRadioGroup.setOnCheckedChangeListener(this);
        CheckLinearItem checkLinearItem = (CheckLinearItem) this.rg.findViewById(R.id.item_pwd_hotspot_left);
        this.itemPwd = checkLinearItem;
        checkLinearItem.setPwdRight(true);
        this.itemPwd.setShowOnLog(false);
        this.itemSecurity = (CheckLinearItem) this.rg.findViewById(R.id.item_security_hotspot_left);
        this.itemFreqBand = (CheckLinearItem) this.rg.findViewById(R.id.item_freq_band_hotspot_left);
        this.itemFlowLimit = (CheckLinearItem) this.rg.findViewById(R.id.item_flow_limited_hotspot_left);
        this.itemConnectedCount = (CheckLinearItem) this.mRootView.findViewById(R.id.item_connected_count_hotspot_left);
        this.itemFlowLimit.setVisibility(8);
        this.itemConnectedCount.setCheckable(false);
        this.carPlayManager = CarPlayManager.getInstance(context);
        this.androidAutoManager = AndroidAutoManager.getInstance(context);
        if (AppConfig.a() == CarModel$Brand.DENZA) {
            this.mLeftView.setBackgroundResource(R.drawable.bg_dialog_bt_left_tenz_land);
        }
        this.mIJumpService = o2.c.b().c();
    }

    @Override // z3.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    @Override // com.byd.mvp.ui.widget.BydRadioGroup.a
    public void onCheckedChanged(BydRadioGroup bydRadioGroup, int i8) {
        HotspotParams defHotspotParams = ((PerHotspotPresenter) getP()).getDefHotspotParams();
        int i9 = 0;
        if (i8 == R.id.item_pwd_hotspot_left) {
            defHotspotParams.setPwdHide(this.itemPwd.isRightTextHideType());
            showPageOnRight(HotspotPagerType.INPUT_PWD, defHotspotParams);
            e.b(this.etName);
            enableApNameEdit(false);
        } else if (i8 == R.id.item_security_hotspot_left) {
            defHotspotParams.setApSecurityType(this.mSecurityType);
            showPageOnRight(HotspotPagerType.SECURITY, defHotspotParams);
            e.b(this.etName);
            enableApNameEdit(false);
            i9 = 1;
        } else if (i8 == R.id.item_freq_band_hotspot_left) {
            defHotspotParams.setApBand(((PerHotspotPresenter) getP()).getHotspotApBand());
            showPageOnRight(HotspotPagerType.FREQ_BAND, defHotspotParams);
            e.b(this.etName);
            enableApNameEdit(false);
            i9 = 2;
        } else {
            i9 = -1;
        }
        this.preItemIndex = i9;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (m4.c.c(view, 200L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_back_hotspot) {
            doBack();
            return;
        }
        if (id == R.id.iv_right_local_device_hotspot) {
            x3.a.a(TAG_W, "---onClick---iv_right.");
            enableApNameEdit(((PerHotspotPresenter) getP()).isHotspotEnable() && this.bydSwitch.isChecked());
        } else if (id == R.id.iv_close_edit_hotspot) {
            this.etName.setText("");
            this.etName.setHint(((PerHotspotPresenter) getP()).getHotspotName());
        }
    }

    public void onConnectedClientsChanged(List<WifiClient> list) {
        x3.a.a(TAG_W, "---onNumClientsChanged----clients = " + list);
        onShowConnectedCountView(list.size());
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        super.onDestroy();
        SystemDialog.sendBroadcastHotspotDialogShow(SystemSettingsModule.getAppContext(), false);
        e.b(this.etName);
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        int i8 = message.arg1;
        if (i8 == 1) {
            onHotspotStateChanged((EnableState) message.obj);
        } else {
            if (i8 != 16) {
                return;
            }
            onConnectedClientsChanged((List) message.obj);
        }
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
        e.b(this.etName);
    }

    public void onHotspotStateChanged(EnableState enableState) {
        x3.a.a(TAG_W, "---onHotspotStateChanged---state = " + enableState);
        if (enableState == EnableState.DISABLE || enableState == EnableState.ENABLE) {
            int i8 = AnonymousClass9.$SwitchMap$com$byd$bt$model$EnableState[enableState.ordinal()];
            if (i8 != 1) {
                if (i8 == 2 || i8 == 3) {
                    updateUIOnHotspotStateChanged(false);
                    return;
                }
                return;
            }
            this.itemPwd.setRightText(TextLengthUtil.getHotspotPwd(((PerHotspotPresenter) getP()).getHotspotPwd()));
            x3.a.a(TAG_W, "---onHotspotStateChanged---params = " + ((PerHotspotPresenter) getP()).getDefHotspotParams());
            this.itemPwd.setRightTextType(true);
            updateUIOnHotspotStateChanged(true);
        }
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        x3.a.a(TAG_W, "---onKeyboardStateChanged---showing: " + z7);
        if (z7) {
            return;
        }
        NoMenuEditText noMenuEditText = this.etName;
        if (noMenuEditText != null && noMenuEditText.hasFocus()) {
            this.etName.clearFocus();
        }
        View.OnClickListener pager = getPager(this.curPagerType);
        if (pager instanceof c) {
            ((c) pager).onKeyboardStateChanged(z7);
        }
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
        super.onLtrRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        super.onLtrRudderR(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings_r, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    public void onPagerBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        x3.a.a(TAG_W, "---onPagerBtnClicked---clazz = " + cls.getName() + ", type = " + pagerBtnType);
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

    @Override // z3.e
    public void onRestoreInstanceState() {
        super.onRestoreInstanceState();
        HotspotDialogState hotspotDialogState = (HotspotDialogState) DialogStatesCache.get(DialogState.HOTSPOT_DIALOG_STATE);
        x3.a.a(TAG_W, "---onRestoreInstanceState---state: " + hotspotDialogState);
        if (hotspotDialogState == null) {
            return;
        }
        HotspotPagerType curPagerType = hotspotDialogState.getCurPagerType();
        if (curPagerType != null) {
            this.rg.b();
            int curItemIndex = hotspotDialogState.getCurItemIndex();
            if (curItemIndex >= 0 && curItemIndex < this.rg.getChildCount()) {
                BydRadioGroup bydRadioGroup = this.rg;
                bydRadioGroup.a(bydRadioGroup.getChildAt(curItemIndex).getId());
            }
            BydData curBydData = hotspotDialogState.getCurBydData();
            x3.a.a(TAG_W, "---onRestoreInstanceState---data: " + curBydData);
            showPageOnRight(curPagerType, curBydData);
        }
        DialogStatesCache.remove(DialogState.HOTSPOT_DIALOG_STATE);
    }

    @Override // z3.a
    public void onResume() {
        super.onResume();
        boolean zIsHotspotEnable = ((PerHotspotPresenter) getP()).isHotspotEnable();
        this.bydSwitch.b(zIsHotspotEnable, false);
        String hotspotName = ((PerHotspotPresenter) getP()).getHotspotName();
        this.tvName.setText(hotspotName);
        this.etName.setHint(hotspotName);
        this.itemPwd.setRightText(TextLengthUtil.getHotspotPwd(((PerHotspotPresenter) getP()).getHotspotPwd()));
        this.itemPwd.setRightTextSingleLine();
        this.itemPwd.setRightTextType(true);
        int apSecurityType = ((PerHotspotPresenter) getP()).getApSecurityType();
        this.mSecurityType = apSecurityType;
        this.itemPwd.setVisibility(apSecurityType == 0 ? 8 : 0);
        this.itemSecurity.setRightText(getCipherTypeText(this.mSecurityType));
        boolean zIsWifiUnsupport5GHzBand = BydSystemSettings.Singleton.get().isWifiUnsupport5GHzBand();
        x3.a.a(this.TAG, "---onResume---unsupport5GHz: " + zIsWifiUnsupport5GHzBand);
        if (zIsWifiUnsupport5GHzBand) {
            this.itemFreqBand.setRightText(getFreqBandText(1));
        } else {
            this.itemFreqBand.setRightText(getFreqBandText(((PerHotspotPresenter) getP()).getHotspotApBand()));
        }
        this.itemFreqBand.setRightTextSingleLine();
        this.itemFlowLimit.setRightText(getLimitText(((PerHotspotPresenter) getP()).getHotspotFlowLimit()));
        int hotspotConnectedCount = ((PerHotspotPresenter) getP()).getHotspotConnectedCount();
        x3.a.a(TAG_W, "---onResume---isOpen = " + zIsHotspotEnable + ", connectedCount: " + hotspotConnectedCount);
        onShowConnectedCountView(hotspotConnectedCount);
        updateUIOnHotspotStateChanged(zIsHotspotEnable);
        if (zIsHotspotEnable || ServiceDialogHolder.isDoingonConfigurationChanged()) {
            return;
        }
        ((PerHotspotPresenter) getP()).setHotspotEnable(true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_hotspot_settings_rtl, (ViewGroup) null));
        loadTitleOnLayoutLocked();
        setViewGroupRtl(this.titleParent, false);
    }

    @Override // z3.e
    public void onSaveInstanceState() {
        super.onSaveInstanceState();
        HotspotDialogState hotspotDialogState = new HotspotDialogState(this.preItemIndex, this.curPagerType, ((PerHotspotPresenter) getP()).getDefHotspotParams());
        x3.a.a(TAG_W, "---onSaveInstanceState---state: " + hotspotDialogState);
        DialogStatesCache.put(DialogState.HOTSPOT_DIALOG_STATE, hotspotDialogState);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.g
    public PerHotspotPresenter newP() {
        return new PerHotspotPresenter();
    }
}
