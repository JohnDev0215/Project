package com.byd.systemsettings.btsetting;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.BTPowerLevel;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.BtPagerType;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.ConnectedBtDevice;
import com.byd.bt.model.PagerBtDevice;
import com.byd.bt.model.PhoneType;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.widget.NoMenuEditText;
import com.byd.mvp.ui.widget.TintImageView;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.systemsettings.btsetting.adapter.BondBtListAdapter;
import com.byd.systemsettings.btsetting.adapter.DiscoverBtListAdapter;
import com.byd.systemsettings.btsetting.model.BondFailedParams;
import com.byd.systemsettings.btsetting.model.BtBatteryParams;
import com.byd.systemsettings.btsetting.model.CarPlayParams;
import com.byd.systemsettings.btsetting.model.ProfileParams;
import com.byd.systemsettings.btsetting.model.RequestPairParams;
import com.byd.systemsettings.btsetting.view.pager.BtConnectFailedPager;
import com.byd.systemsettings.btsetting.view.pager.BtConnectedPager;
import com.byd.systemsettings.btsetting.view.pager.BtConnectingPager;
import com.byd.systemsettings.btsetting.view.pager.BtPaireCodePager;
import com.byd.systemsettings.btsetting.view.pager.BtPaireFailedPager;
import com.byd.systemsettings.btsetting.view.pager.BtPairingPager;
import com.byd.systemsettings.btsetting.view.pager.NoBtConnectionPager;
import com.byd.systemsettings.cpaa.AndroidAutoManager;
import com.byd.systemsettings.cpaa.callback.CpaaConnectStateCallback;
import com.byd.systemsettings.system.BydSystemProperties;
import com.byd.systemsettings.ui.cache.DialogStatesCache;
import com.byd.systemsettings.ui.cache.model.BtDialogState;
import com.byd.systemsettings.ui.cache.model.DialogState;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.bydux.api.common.tools.util.ListUtils;
import j7.l;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import k4.c;
import m4.e;
import o2.b;
import org.greenrobot.eventbus.ThreadMode;
import p2.g;
import q2.d;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtSettingsPager extends a<BtSettingsPresenter> implements c {
    private static final String INDEX_CLICKED = "index_clicked";
    private static final int INVALID = -1;
    private static final String KEY_UNPAIR_MAC = "key_unpair_mac";
    private static final int MAX_DEF_PAIRED_SHOW = 2;
    private static final String TAG_W = "BtSettingsPager";
    private static final long TIMEOUT_NOTIFY = 500;
    private BondBtListAdapter bondAdapter;
    private List<BondDevice> bondDevices;
    private ConcurrentHashMap<String, BondDevice> bondDevicesMap;
    private ConcurrentHashMap<String, BondDevice> connectedMap;
    private volatile boolean connecting;
    private volatile BluetoothDevice connectingDevice;
    private BydData curBydData;
    private volatile BtPagerType curPagerType;
    private AndroidAutoDevice currentAndroidAutoDevice;
    private ConcurrentHashMap<String, BTDevice> deviceMap;
    private DiscoverBtListAdapter discoverAdapter;
    private List<BTDevice> discoverDevices;
    private final Runnable enableHfpRunnable;
    private NoMenuEditText etName;
    private FrameLayout flRight;
    private volatile int indexClicked;
    private volatile boolean isBtNameEditable;
    private volatile boolean isOpen;
    private volatile boolean isScanning;
    private volatile boolean isShowMore;
    private ImageView ivRefresh;
    private TintImageView ivRight;
    private ImageView ivShowMore;
    private View llClose;
    private View llDiscoverDevices;
    private View llOpen;
    private View llPairedDevices;
    private View llShowMore;
    private OnPageBtnClickedCallback mBtnCallback;
    private View mLeftView;
    private BondDevice mUnpairDevice;
    private BluetoothDevice newConnectedDevice;
    private boolean newDeviceSupportCPAA;
    private ConcurrentHashMap<BtPagerType, a> pagerMap;
    private volatile Pair<String, String> pairOob;
    private volatile boolean pairing;
    private volatile BtPagerType prePagerType;
    private volatile long prePairFailed;
    private RecyclerView rcvBond;
    private RecyclerView rcvDiscover;
    private volatile boolean rcvNoRespond;
    private View rlName;
    private BydSwitch switchBleState;
    private FrameLayout titleParent;
    private TextView tvName;
    private boolean unpairDialogShowing;

    /* JADX INFO: renamed from: com.byd.systemsettings.btsetting.BtSettingsPager$26, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass26 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$BtPagerType;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$mvp$callback$PagerBtnType;

        static {
            int[] iArr = new int[PagerBtnType.values().length];
            $SwitchMap$com$byd$mvp$callback$PagerBtnType = iArr;
            try {
                iArr[PagerBtnType.BOTTOM_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[BtPagerType.values().length];
            $SwitchMap$com$byd$bt$model$BtPagerType = iArr2;
            try {
                iArr2[BtPagerType.NO_CONNECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.PAIRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.PAIRE_CODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.PAIRE_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.CONNECTING.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.CONNECT_FAILED.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$byd$bt$model$BtPagerType[BtPagerType.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public class OnPageBtnClickedCallback implements w3.a {
        private final WeakReference<BtSettingsPager> ref;

        public OnPageBtnClickedCallback(BtSettingsPager btSettingsPager) {
            this.ref = new WeakReference<>(btSettingsPager);
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
            WeakReference<BtSettingsPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onPagerBtnClicked(cls, pagerBtnType, obj, obj2);
        }
    }

    public BtSettingsPager(Context context) {
        super(context);
        this.indexClicked = -1;
        this.prePairFailed = 0L;
        this.rcvNoRespond = false;
        this.isBtNameEditable = false;
        this.newDeviceSupportCPAA = false;
        this.enableHfpRunnable = new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.25
            @Override // java.lang.Runnable
            public void run() {
                x3.a.a(BtSettingsPager.TAG_W, "超时时间到,AndroidAutoHfp禁用解除");
                BtSettingsPager.this.enableAndroidAutoHfp(true);
                if (BtSettingsPager.this.newDeviceSupportCPAA) {
                    x3.a.a(BtSettingsPager.TAG_W, "new devices support cpaa");
                    BtSettingsPager.this.newDeviceSupportCPAA = false;
                } else {
                    if (BtSettingsPager.this.getContext() == null) {
                        return;
                    }
                    d.c(BtSettingsPager.this.getContext(), BtSettingsPager.this.getContext().getString(R.string.hint_not_support_pair_on_aa_connected_operation));
                    if (BtSettingsPager.this.newConnectedDevice != null) {
                        ((BtSettingsPresenter) BtSettingsPager.this.getP()).forceDisconnect(BtSettingsPager.this.newConnectedDevice);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeBtState(boolean z7) {
        x3.a.a(TAG_W, "---changeBtState---open: " + z7);
        updateonBtStateChanged(z7);
        this.switchBleState.setInvalid(true);
        this.switchBleState.setClickable(false);
        this.switchBleState.setFocusable(false);
        if (z7) {
            startRefreshAnimation();
        }
    }

    private boolean changeForCpaaDeviceV2(Context context, final BondDevice bondDevice) {
        final AndroidAutoDevice connectedAndroidAutoDevice;
        final boolean zSupportCarPlay = BtSingleConnection.supportCarPlay(bondDevice.getDevice());
        final boolean zSupportAndroidAuto = BtSingleConnection.supportAndroidAuto(bondDevice.getDevice());
        boolean zIsCarPlayConnected = ((BtSettingsPresenter) getP()).isCarPlayConnected();
        boolean zIsAndroidAutoConnected = ((BtSettingsPresenter) getP()).isAndroidAutoConnected();
        if (zIsCarPlayConnected) {
            final CarPlayDevice connectedCarPlayDevice = ((BtSettingsPresenter) getP()).getConnectedCarPlayDevice();
            if (connectedCarPlayDevice == null) {
                return false;
            }
            String btAddr = connectedCarPlayDevice.getBtAddr();
            if (bondDevice.getDevice() == null) {
                return false;
            }
            if (btAddr == null || !btAddr.equals(bondDevice.getDevice().getAddress())) {
                SystemDialog.showChangeCpaaDeviceDialog(bondDevice.getPhoneType() == PhoneType.CAR_PLAY, bondDevice.getPhoneType() == PhoneType.ANDROID_AUTO, context, new w3.a() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.16
                    @Override // w3.a
                    public void onBtnClicked(PagerBtnType pagerBtnType) {
                        x3.a.a(BtSettingsPager.TAG_W, "changeForCpaaDeviceV2", "---onBtnClicked---type: " + pagerBtnType);
                        if (PagerBtnType.BOTTOM_LEFT != pagerBtnType) {
                            BtSettingsPager.this.itemRollback(0, 1000L);
                            return;
                        }
                        CpaaConnectStateCallback cpaaConnectCallback = BtSettingsPager.this.getCpaaConnectCallback(bondDevice);
                        x3.a.a(BtSettingsPager.TAG_W, "changeForCpaaDeviceV2", "--AndroidAuto device, disconnect to connect BT, device: " + bondDevice.getName());
                        if (zSupportCarPlay) {
                            cpaaConnectCallback.setAction(3);
                        } else if (zSupportAndroidAuto) {
                            cpaaConnectCallback.setAction(4);
                        }
                        String btAddr2 = connectedCarPlayDevice.getBtAddr();
                        if (TextUtils.isEmpty(btAddr2)) {
                            btAddr2 = connectedCarPlayDevice.getUsbSerialNumber();
                        }
                        ((BtSettingsPresenter) BtSettingsPager.this.getP()).disconnectCarPlay(btAddr2, cpaaConnectCallback);
                    }

                    @Override // w3.a
                    public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
                        super.onBtnClicked(cls, pagerBtnType);
                    }

                    @Override // w3.a
                    public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
                        super.onBtnClicked(cls, pagerBtnType, obj, obj2);
                    }
                });
                return true;
            }
            ((BtSettingsPresenter) getP()).disconnectCarPlay(btAddr, null);
            return false;
        }
        if (!zIsAndroidAutoConnected || (connectedAndroidAutoDevice = ((BtSettingsPresenter) getP()).getConnectedAndroidAutoDevice()) == null) {
            return false;
        }
        String btAddr2 = connectedAndroidAutoDevice.getBtAddr();
        if (bondDevice.getDevice() == null) {
            return false;
        }
        if (btAddr2 == null || !btAddr2.equals(bondDevice.getDevice().getAddress())) {
            SystemDialog.showChangeCpaaDeviceDialog(bondDevice.getPhoneType() == PhoneType.CAR_PLAY, bondDevice.getPhoneType() == PhoneType.ANDROID_AUTO, context, new w3.a() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.17
                @Override // w3.a
                public void onBtnClicked(PagerBtnType pagerBtnType) {
                    x3.a.a(BtSettingsPager.TAG_W, "changeForCpaaDeviceV2", "---onBtnClicked---type: " + pagerBtnType);
                    if (PagerBtnType.BOTTOM_LEFT != pagerBtnType) {
                        BtSettingsPager.this.itemRollback(0, 1000L);
                        return;
                    }
                    CpaaConnectStateCallback cpaaConnectCallback = BtSettingsPager.this.getCpaaConnectCallback(bondDevice);
                    x3.a.a(BtSettingsPager.TAG_W, "changeForCpaaDeviceV2", "--AndroidAuto device, disconnect to connect BT, device: " + bondDevice.getName());
                    if (zSupportCarPlay) {
                        cpaaConnectCallback.setAction(2);
                    } else if (zSupportAndroidAuto) {
                        cpaaConnectCallback.setAction(1);
                    }
                    String btAddr3 = connectedAndroidAutoDevice.getBtAddr();
                    if (TextUtils.isEmpty(btAddr3)) {
                        btAddr3 = connectedAndroidAutoDevice.getUsbSerialNumber();
                    }
                    BtSingleConnection.enableAndroidAutoHfp(bondDevice.getDevice(), false);
                    ((BtSettingsPresenter) BtSettingsPager.this.getP()).disconnectAndroidAuto(btAddr3, cpaaConnectCallback);
                }

                @Override // w3.a
                public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
                    super.onBtnClicked(cls, pagerBtnType);
                }

                @Override // w3.a
                public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
                    super.onBtnClicked(cls, pagerBtnType, obj, obj2);
                }
            });
            return true;
        }
        x3.a.a(TAG_W, "changeForCpaaDeviceV2", "当前连接AA设备和切换的蓝牙列表设备是同一个");
        return false;
    }

    private void changeVisibility(View view) {
        view.setVisibility(view.getVisibility() == 0 ? 8 : 0);
    }

    private void checkAndShowConnectedPager(BondDevice bondDevice) {
        if (bondDevice == null) {
            return;
        }
        ConnectedBtDevice connectedBtDevice = getConnectedBtDevice(bondDevice.getDevice());
        connectedBtDevice.setDevice(bondDevice);
        showPageOnRight(BtPagerType.CONNECTED, connectedBtDevice);
    }

    private boolean checkAndroidAutoConnectState(Context context) {
        if (!((BtSettingsPresenter) getP()).isAndroidAutoConnected()) {
            return false;
        }
        d.c(getContext(), context.getString(R.string.hint_not_support_pair_on_aa_connected_operation));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkBondDevice(Context context, BondDevice bondDevice) {
        if (bondDevice == null || bondDevice.getDevice() == null) {
            return;
        }
        x3.a.a(TAG_W, "checkBondDevice", "--device: " + bondDevice);
        if (bondDevice.isConnected() && bondDevice.getDevice() != null && bondDevice.getLevel() != BTPowerLevel.UNDEFINED && bondDevice.isHfpConnected()) {
            checkAndShowConnectedPager(bondDevice);
            return;
        }
        if (bondDevice.getConnectState() == 1) {
            x3.a.a(TAG_W, "checkBondDevice", "--name: " + bondDevice.getName() + ",isConnecting return");
            return;
        }
        x3.a.a(TAG_W, "checkBondDevice", "--connecting: " + this.connecting);
        if (this.connecting && this.connectingDevice != null && bondDevice.getMac().equals(this.connectingDevice.getAddress())) {
            x3.a.b(TAG_W, "checkBondDevice", "--This device is connecting, device: " + m4.d.c(this.connectingDevice));
            return;
        }
        if (this.connecting) {
            d.c(context, context.getString(R.string.hint_muti_connect_operation));
            if (this.connectingDevice != null) {
                itemRollback(getBondDeviceIndex(this.connectingDevice.getAddress()), 1000L);
                return;
            }
            return;
        }
        x3.a.a(TAG_W, "checkBondDevice", "---start do check.");
        if (!BtSingleConnection.supportCpaa(bondDevice.getDevice())) {
            x3.a.a(TAG_W, "checkBondDevice", "---It is normal device, d: " + m4.d.c(bondDevice.getDevice()));
            if (checkAndroidAutoConnectState(context)) {
                itemRollback(0, 1000L);
                return;
            } else if (checkCarPlayOnAP24G(context)) {
                itemRollback(0, 1000L);
                return;
            }
        } else if (changeForCpaaDeviceV2(context, bondDevice)) {
            return;
        }
        x3.a.a(TAG_W, "checkBondDevice", "--checkSingleConnection.");
        checkSingleConnection(bondDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkCarPlayOnAP24G(Context context) {
        if (!((BtSettingsPresenter) getP()).isCarPlayConnected() || !((BtSettingsPresenter) getP()).isCurApBand24GHz()) {
            return false;
        }
        d.c(context, context.getString(R.string.hint_not_support_pair_on_cp_connected_operation));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSingleConnection(final BondDevice bondDevice) {
        bondDevice.setConnectState(1);
        notifyBondDevicesDataChanged();
        BtSingleConnection.restoreAndroidAutoHfp(bondDevice);
        x3.a.a(TAG_W, "---checkSingleConnection---d: " + m4.d.c(bondDevice.getDevice()));
        BtSingleConnection.keepSingle(bondDevice.getMac(), new BtSingleConnection.OnDisconnectCompletedCallback() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.20
            @Override // com.byd.systemsettings.btsetting.BtSingleConnection.OnDisconnectCompletedCallback
            public void onDisconnectCompleted() {
                BtSettingsPager.this.doConnectDevice(bondDevice);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSingleConnectionOnMain(final BondDevice bondDevice) {
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.19
            @Override // java.lang.Runnable
            public void run() {
                x3.a.a(BtSettingsPager.TAG_W, "checkSingleConnectionOnMain", "--checkSingleConnection on CarPlay disconnected.");
                BtSettingsPager.this.checkSingleConnection(bondDevice);
            }
        });
    }

    private List<BTDevice> cloneDiscoverDevices(List<BTDevice> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (BTDevice bTDevice : list) {
            BTDevice bTDevice2 = new BTDevice(bTDevice.getDevice());
            bTDevice2.setConnected(bTDevice.isConnected());
            bTDevice2.setRssi(bTDevice.getRssi());
            bTDevice2.setPreConnectedTime(bTDevice.getPreConnectedTime());
            bTDevice2.setPreDisconnectedTime(bTDevice.getPreDisconnectedTime());
            arrayList.add(bTDevice2);
        }
        return arrayList;
    }

    private void connectDevice(PagerBtDevice pagerBtDevice) {
        boolean zConnectDevice = ((BtSettingsPresenter) getP()).connectDevice(pagerBtDevice.getDevice());
        x3.a.e(TAG_W, "---connectDevice---result: " + zConnectDevice + ", d: " + m4.d.c(pagerBtDevice.getDevice()));
        if (zConnectDevice) {
            ((BtSettingsPresenter) getP()).stopScan();
            showPageOnRight(BtPagerType.CONNECTING, pagerBtDevice);
            this.connecting = true;
            this.connectingDevice = pagerBtDevice.getDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dialogToUnpairDevice(Context context) {
        x3.a.a(TAG_W, "dialogToUnpairDevice", ", mUnpairDevice: " + this.mUnpairDevice);
        if (this.mUnpairDevice == null || this.ref.get() == null) {
            return;
        }
        Resources resources = this.ref.get().getResources();
        final BluetoothDevice device = this.mUnpairDevice.getDevice();
        SystemDialog.showIdentification(this.ref.get(), resources.getString(R.string.title_delete_device_bt), resources.getString(R.string.msg_delete_device_bt), resources.getString(R.string.dialog_btn_ok), this.mUnpairDevice, new e4.d() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.22
            @Override // e4.d
            public void onOKClicked() {
                ((BtSettingsPresenter) BtSettingsPager.this.getP()).unpairDeviceDelayCallBack(device);
                BtSettingsPager.this.bondDevicesMap.remove(device.getAddress());
                x3.a.a(BtSettingsPager.TAG_W, "dialogToUnpairDevice", ", connectingDevice: " + m4.d.c(BtSettingsPager.this.connectingDevice));
                if (BtSettingsPager.this.connectingDevice != null && BtSettingsPager.this.connectingDevice.getAddress().equals(device.getAddress())) {
                    x3.a.a(BtSettingsPager.TAG_W, "dialogToUnpairDevice", ", do unpair connecting device, d: " + m4.d.c(BtSettingsPager.this.connectingDevice));
                    BtSettingsPager.this.showPageOnRight(BtPagerType.NO_CONNECTION, null);
                }
                BtSettingsPager.this.sendMessageDelay(33, 50, 1000L, null);
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.23
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                BtSettingsPager.this.mUnpairDevice = null;
                BtSettingsPager.this.unpairDialogShowing = false;
            }
        });
        this.unpairDialogShowing = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableBtNameEdit(boolean z7) {
        this.isBtNameEditable = false;
        x3.a.a(TAG_W, "---disableBtNameEdit---doChange： " + z7);
        this.rlName.setVisibility(8);
        this.tvName.setVisibility(0);
        String strTrim = this.etName.getText().toString().trim();
        String btName = ((BtSettingsPresenter) getP()).getBtName();
        if (!z7 || TextUtils.isEmpty(strTrim) || strTrim.equals(btName)) {
            this.etName.setText("");
            strTrim = btName;
        } else {
            x3.a.a(TAG_W, "---disableBtNameEdit---do set BT name： " + strTrim);
            ((BtSettingsPresenter) getP()).setBleName(strTrim);
            d.b(getContext(), R.string.modify_completed);
        }
        this.tvName.setText(strTrim);
        if (z7) {
            e.c(this.etName, 6);
        } else {
            e.b(this.etName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doConnectDevice(BondDevice bondDevice) {
        BtSingleConnection.keepSingleConnection(bondDevice.getMac());
        x3.a.a(TAG_W, "---doConnectDevice---d: " + m4.d.c(bondDevice.getDevice()));
        PagerBtDevice pagerBtDevice = new PagerBtDevice(bondDevice.getName());
        pagerBtDevice.setDevice(bondDevice.getDevice());
        connectDevice(pagerBtDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice enableAndroidAutoHfp(boolean z7) {
        AndroidAutoDevice androidAutoDevice;
        BondDevice bondDevice;
        if (((BtSettingsPresenter) getP()).isAndroidAutoConnected() && (androidAutoDevice = this.currentAndroidAutoDevice) != null) {
            String btAddr = androidAutoDevice.getBtAddr();
            if (!TextUtils.isEmpty(btAddr) && (bondDevice = this.bondDevicesMap.get(btAddr)) != null && bondDevice.getDevice() != null) {
                x3.a.a(TAG_W, "enableAndroidAutoHfp -> " + z7 + ListUtils.DEFAULT_JOIN_SEPARATOR + bondDevice.getDevice().getName());
                BtSingleConnection.enableAndroidAutoHfp(bondDevice.getDevice(), z7);
                return bondDevice.getDevice();
            }
        }
        return null;
    }

    private void enableBtNameEdit() {
        if (((BtSettingsPresenter) getP()).isEnabled() && this.switchBleState.isChecked()) {
            this.isBtNameEditable = true;
            this.rlName.setVisibility(0);
            this.tvName.setVisibility(8);
            this.etName.setFocusable(true);
            this.etName.setClickable(true);
            this.etName.setText(((BtSettingsPresenter) getP()).getBtName());
            this.etName.requestFocus();
            NoMenuEditText noMenuEditText = this.etName;
            noMenuEditText.setSelection(noMenuEditText.getText().length());
            e.j(this.etName);
        }
    }

    private int getBondDeviceIndex(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (int i8 = 0; i8 < this.bondDevices.size(); i8++) {
            if (this.bondDevices.get(i8).getMac().equals(str)) {
                return i8;
            }
        }
        return -1;
    }

    private BluetoothDevice getConnectedAADevice() {
        AndroidAutoDevice androidAutoDevice;
        if (!((BtSettingsPresenter) getP()).isAndroidAutoConnected() || (androidAutoDevice = this.currentAndroidAutoDevice) == null || androidAutoDevice.getBtAddr() == null) {
            return null;
        }
        BondDevice bondDevice = this.bondDevicesMap.get(this.currentAndroidAutoDevice.getBtAddr());
        if (bondDevice == null || bondDevice.getDevice() == null) {
            return null;
        }
        return bondDevice.getDevice();
    }

    private synchronized ConnectedBtDevice getConnectedBtDevice(BluetoothDevice bluetoothDevice) {
        ConnectedBtDevice connectedBtDevice;
        x3.a.a(TAG_W, "getConnectedBtDevice", ", device: " + m4.d.c(bluetoothDevice));
        BondDevice bondDevice = this.connectedMap.get(bluetoothDevice.getAddress());
        if (bondDevice == null) {
            bondDevice = this.bondDevicesMap.get(bluetoothDevice.getAddress());
            if (bondDevice == null) {
                bondDevice = new BondDevice(new BTDevice(bluetoothDevice));
            }
            this.connectedMap.put(bluetoothDevice.getAddress(), bondDevice);
        }
        connectedBtDevice = new ConnectedBtDevice();
        connectedBtDevice.setDevice(bondDevice);
        return connectedBtDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CpaaConnectStateCallback getCpaaConnectCallback(final BondDevice bondDevice) {
        return new CpaaConnectStateCallback() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.18
            @Override // com.byd.systemsettings.cpaa.callback.CpaaConnectStateCallback
            public void onConnectChanged(boolean z7) {
                x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--onConnectChanged--connected: " + z7);
                if (z7 || bondDevice == null) {
                    return;
                }
                int action = getAction();
                if (action == 0) {
                    x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--ACTION_TO_BT--, device: " + m4.d.c(bondDevice.getDevice()));
                    BtSettingsPager.this.checkSingleConnectionOnMain(bondDevice);
                    return;
                }
                if (action == 1) {
                    x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--ACTION_AA_TO_AA--, device: " + m4.d.c(bondDevice.getDevice()));
                    ((BtSettingsPresenter) BtSettingsPager.this.getP()).connectAndroidAuto(bondDevice);
                    return;
                }
                if (action == 2) {
                    x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--ACTION_AA_TO_CP--, device: " + bondDevice.getName());
                    ((BtSettingsPresenter) BtSettingsPager.this.getP()).connectCarPlay(bondDevice);
                    return;
                }
                if (action == 3) {
                    x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--ACTION_CP_TO_CP--, device: " + bondDevice.getName());
                    ((BtSettingsPresenter) BtSettingsPager.this.getP()).connectCarPlay(bondDevice);
                    return;
                }
                if (action != 4) {
                    return;
                }
                x3.a.a(BtSettingsPager.TAG_W, "getCpaaConnectCallback", "--ACTION_CP_TO_AA--, device: " + bondDevice.getName());
                ((BtSettingsPresenter) BtSettingsPager.this.getP()).connectAndroidAuto(bondDevice);
            }
        };
    }

    private BluetoothDevice getDevice(List<? extends BTDevice> list, String str) {
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            for (BTDevice bTDevice : list) {
                if (str.equals(bTDevice.getMac())) {
                    return bTDevice.getDevice();
                }
            }
        }
        return null;
    }

    private a getPager(BtPagerType btPagerType) {
        a noBtConnectionPager = this.pagerMap.get(btPagerType);
        if (noBtConnectionPager != null) {
            if (noBtConnectionPager.getRootView() != null) {
                return noBtConnectionPager;
            }
            this.pagerMap.remove(btPagerType);
        }
        if (this.mBtnCallback == null) {
            this.mBtnCallback = new OnPageBtnClickedCallback(this);
        }
        switch (AnonymousClass26.$SwitchMap$com$byd$bt$model$BtPagerType[btPagerType.ordinal()]) {
            case 1:
                noBtConnectionPager = new NoBtConnectionPager(getContext());
                break;
            case 2:
                noBtConnectionPager = new BtPairingPager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 3:
                noBtConnectionPager = new BtPaireCodePager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 4:
                noBtConnectionPager = new BtPaireFailedPager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 5:
                noBtConnectionPager = new BtConnectingPager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 6:
                noBtConnectionPager = new BtConnectFailedPager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 7:
                noBtConnectionPager = new BtConnectedPager(getContext());
                noBtConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
        }
        if (noBtConnectionPager != null) {
            this.pagerMap.put(btPagerType, noBtConnectionPager);
        }
        return noBtConnectionPager;
    }

    private void handleWhenAAConnectedOtherDeviceConnect(BluetoothDevice bluetoothDevice, long j8) {
        x3.a.a(TAG_W, "handleWhenAAConnectedOtherDeviceConnect:" + m4.d.f(bluetoothDevice.getName()));
        BluetoothDevice connectedAADevice = getConnectedAADevice();
        if (connectedAADevice == null) {
            x3.a.a(TAG_W, "当前没有连接的AA设备return");
            return;
        }
        if (connectedAADevice.getAddress().equals(bluetoothDevice.getAddress())) {
            x3.a.a(TAG_W, "当前AA连接的蓝牙地址和设备配对的地址是同一个return");
            return;
        }
        if (getP() == null) {
            return;
        }
        BluetoothDevice bluetoothDeviceEnableAndroidAutoHfp = enableAndroidAutoHfp(false);
        if (bluetoothDeviceEnableAndroidAutoHfp != null) {
            ((BtSettingsPresenter) getP()).disconnect(bluetoothDeviceEnableAndroidAutoHfp);
        }
        if (((BtSettingsPresenter) getP()).isAndroidAutoConnected()) {
            this.newConnectedDevice = bluetoothDevice;
            g.a().f(this.enableHfpRunnable);
            g.a().d(this.enableHfpRunnable, j8);
        }
    }

    private boolean isConnectingDevice(String str) {
        return this.connectingDevice == null || (this.connectingDevice != null && this.connectingDevice.getAddress().equals(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void itemRollback(final int i8, long j8) {
        g.a().d(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.21
            @Override // java.lang.Runnable
            public void run() {
                int i9 = i8;
                if (i9 >= 0 && i9 < BtSettingsPager.this.bondAdapter.getItemCount()) {
                    BtSettingsPager.this.bondAdapter.checkItem(i8);
                }
                BtSettingsPager.this.notifyBondDevicesDataChanged();
            }
        }, j8);
    }

    private void loadTitleOnLayoutLocked() {
        this.switchBleState = (BydSwitch) this.mRootView.findViewById(R.id.switch_title_bt_state_bt);
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_local_device_bt_name_bt);
        this.etName = (NoMenuEditText) this.mRootView.findViewById(R.id.et_local_device_bt_name_bt);
        this.rlName = this.mRootView.findViewById(R.id.rl_title_bt_name_bt);
        TintImageView tintImageView = (TintImageView) this.mRootView.findViewById(R.id.iv_right_local_device_bt);
        this.ivRight = tintImageView;
        tintImageView.setOnClickListener(this);
        this.mRootView.findViewById(R.id.iv_back_bt).setOnClickListener(this);
        this.mRootView.findViewById(R.id.iv_close_edit_bt).setOnClickListener(this);
        this.switchBleState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                ((BtSettingsPresenter) BtSettingsPager.this.getP()).enable(z7);
                BtSettingsPager.this.switchBleState.setInvalid(true);
                BtSettingsPager.this.changeBtState(z7);
            }
        });
        this.switchBleState.setOnInterceptTouchListener(new BydSwitch.a() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.4
            @Override // com.byd.base.view.BydSwitch.a
            public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z7) {
                BtSettingsPager.this.switchBleState.playSoundEffect(0);
                if (motionEvent.getAction() != 1) {
                    return;
                }
                if (z7) {
                    BtSettingsPager.this.switchBleState.setChecked(true);
                    return;
                }
                BtSettingsPager.this.connecting = false;
                BtSettingsPager.this.connectingDevice = null;
                BtSettingsPager.this.showPageOnRight(BtPagerType.NO_CONNECTION, null);
                if (AndroidAutoManager.getInstance(BtSettingsPager.this.getContext()).isAndroidAutoConnected()) {
                    o2.c.b().c().jump2OffBtWarningDialog(new b.a() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.4.1
                        @Override // o2.b.a
                        public void onOkResult(Object obj) {
                            ((BtSettingsPresenter) BtSettingsPager.this.getP()).enable(false);
                            BtSettingsPager.this.switchBleState.setInvalid(true);
                        }
                    });
                    return;
                }
                ((BtSettingsPresenter) BtSettingsPager.this.getP()).enable(false);
                BtSettingsPager.this.switchBleState.setInvalid(true);
                BtSettingsPager.this.changeBtState(z7);
            }
        });
        this.etName.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.5
            private long preFocusTime = 0;

            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z7) {
                x3.a.a(BtSettingsPager.TAG_W, "--onFocusChange--hasFocus: " + z7 + ", isBtNameEditable: " + BtSettingsPager.this.isBtNameEditable);
                if (z7) {
                    this.preFocusTime = System.currentTimeMillis();
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis() - this.preFocusTime;
                    x3.a.a(BtSettingsPager.TAG_W, "--onFocusChange--delta: " + jCurrentTimeMillis);
                    if (jCurrentTimeMillis < 200) {
                        return;
                    }
                }
                if (!z7 && BtSettingsPager.this.isBtNameEditable) {
                    g.a().d(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BtSettingsPager.this.disableBtNameEdit(false);
                        }
                    }, 50L);
                }
                if (z7) {
                    return;
                }
                BtSettingsPager btSettingsPager = BtSettingsPager.this;
                btSettingsPager.delayToCloseIme(btSettingsPager.etName);
            }
        });
        String str = BydSystemProperties.get(BydSystemProperties.VERSION_TYPE, null);
        String btName = ((BtSettingsPresenter) getP()).getBtName();
        x3.a.c(TAG_W, "loadTitleOnLayoutLocked, versionType: " + str + ", btName: " + btName);
        if (BydSystemProperties.VERSION_USER_DEBUG.equals(str) && "BYD-discover-test".equals(btName)) {
            this.tvName.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (m4.c.b(view, z3.e.INTERVAL, 10)) {
                        e3.a.d(true);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBondDevicesDataChanged() {
        if (this.bondAdapter == null) {
            return;
        }
        x3.a.a(TAG_W, "---notifyBondDevicesDataChanged---");
        BondBtListAdapter bondBtListAdapter = this.bondAdapter;
        if (bondBtListAdapter != null) {
            bondBtListAdapter.notifyDataSetChanged();
        }
    }

    private void onAANewDevicePair(int i8) {
        g.a().f(this.enableHfpRunnable);
        if (i8 == 0 || i8 == -1) {
            enableAndroidAutoHfp(true);
        }
    }

    private void onCancelOperation() {
        x3.a.a(TAG_W, "-----onCancelOperation----");
        updateBondDeviceListUI();
    }

    private void onConnectFailedPagerClicked(PagerBtnType pagerBtnType, PagerBtDevice pagerBtDevice, Object obj) {
        x3.a.a(TAG_W, "onConnectFailedPagerClicked", ", type = " + pagerBtnType + ", obj1 = " + obj);
        int i8 = AnonymousClass26.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 == 1) {
            connectDevice(pagerBtDevice);
        } else {
            if (i8 != 2) {
                return;
            }
            this.connecting = false;
            onCancelOperation();
        }
    }

    private void onConnectedPagerClicked(PagerBtnType pagerBtnType, ConnectedBtDevice connectedBtDevice, Object obj) {
        x3.a.a(TAG_W, "onConnectedPagerClicked", ", type = " + pagerBtnType + ", device = " + connectedBtDevice);
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null) {
            return;
        }
        int i8 = AnonymousClass26.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 == 1) {
            ((BtSettingsPresenter) getP()).forceDisconnect(connectedBtDevice.getDevice().getDevice());
        } else if (i8 == 2 && !m4.c.a(200L)) {
            this.mUnpairDevice = connectedBtDevice.getDevice();
            dialogToUnpairDevice(this.ref.get());
        }
    }

    private synchronized void onDiscoverDevicesChanged(boolean z7, BTDevice bTDevice) {
        this.discoverDevices.clear();
        if (z7) {
            this.deviceMap.remove(bTDevice.getMac());
        } else {
            this.deviceMap.put(bTDevice.getMac(), bTDevice);
        }
        this.discoverDevices.addAll(this.deviceMap.values());
        Collections.sort(this.discoverDevices);
        this.discoverAdapter.notifyDataSetChanged();
        x3.a.a(TAG_W, "onDiscoverDevicesChanged", ", discoverDevices.size: " + this.discoverDevices.size());
    }

    private void onPairCodePagerClicked(PagerBtnType pagerBtnType, PagerBtDevice pagerBtDevice, Object obj) {
        x3.a.a(TAG_W, "onPairCodePagerClicked", ", type = " + pagerBtnType);
        if (AnonymousClass26.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()] != 3) {
            return;
        }
        if (pagerBtDevice != null && pagerBtDevice.getDevice() != null) {
            ((BtSettingsPresenter) getP()).cancelPairDevice(pagerBtDevice.getDevice());
        }
        this.pairing = false;
        onCancelOperation();
    }

    private void onPaireFailedPagerClicked(PagerBtnType pagerBtnType, PagerBtDevice pagerBtDevice, Object obj) {
        x3.a.a(TAG_W, "onPaireFailedPagerClicked", ", type = " + pagerBtnType + ", obj1 = " + obj);
        int i8 = AnonymousClass26.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 == 1) {
            this.pairing = false;
            pairDevice(pagerBtDevice);
        } else {
            if (i8 != 2) {
                return;
            }
            this.pairing = false;
            onCancelOperation();
        }
    }

    private void onPairingPagerClicked(PagerBtnType pagerBtnType, PagerBtDevice pagerBtDevice, Object obj) {
        x3.a.a(TAG_W, "onPairingPagerClicked", ", type = " + pagerBtnType + ", obj1 = " + obj);
        if (AnonymousClass26.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()] != 3) {
            return;
        }
        if (pagerBtDevice != null && pagerBtDevice.getDevice() != null) {
            ((BtSettingsPresenter) getP()).cancelPairDevice(pagerBtDevice.getDevice());
        }
        this.pairing = false;
        onCancelOperation();
    }

    private void onRecyclerViewNoRespond() {
        x3.a.b(TAG_W, "---onRecyclerViewNoRespond---");
        this.rcvNoRespond = true;
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.24
            @Override // java.lang.Runnable
            public void run() {
                BtSettingsPager.this.updateBondDevices();
                BtSettingsPager.this.rcvNoRespond = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pairDevice(PagerBtDevice pagerBtDevice) {
        this.pairing = true;
        showPageOnRight(BtPagerType.PAIRING, pagerBtDevice);
        ((BtSettingsPresenter) getP()).pairDevice(pagerBtDevice.getDevice());
    }

    private void refresh() {
        x3.a.a(TAG_W, "refresh", ", isScanning: " + this.isScanning);
        disableBtNameEdit(false);
        if (this.isScanning) {
            ((BtSettingsPresenter) getP()).stopScan();
            return;
        }
        this.discoverDevices.clear();
        this.deviceMap.clear();
        this.discoverAdapter.notifyDataSetChanged();
        ((BtSettingsPresenter) getP()).startScan();
    }

    private void resetSwitchState() {
        x3.a.a(TAG_W, "---resetSwitchState---");
        this.switchBleState.setInvalid(false);
        this.switchBleState.setClickable(true);
        this.switchBleState.setFocusable(true);
    }

    private void showConfirmBtDialog(AndroidAutoManager androidAutoManager) {
        Resources resources = getContext().getResources();
        SystemDialog.showBtnLeftHighlight(getContext(), resources.getString(R.string.cp_warning), resources.getString(R.string.bt_confirm_msg_2), resources.getString(R.string.dilink_yes), resources.getString(R.string.dilink_no), new e4.d() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.7
            @Override // e4.d
            public void onOKClicked() {
                ((BtSettingsPresenter) BtSettingsPager.this.getP()).enable(false);
                BtSettingsPager.this.switchBleState.setInvalid(true);
            }
        });
    }

    private void showConnectedPager(BondDevice bondDevice) {
        x3.a.a(TAG_W, "--showConnectedPager--target: " + bondDevice);
        if (((BtSettingsPresenter) getP()).getConnectedDevicesCount() <= 0 || bondDevice == null) {
            return;
        }
        ConnectedBtDevice connectedBtDevice = new ConnectedBtDevice();
        connectedBtDevice.setDevice(bondDevice);
        showPageOnRight(BtPagerType.CONNECTED, connectedBtDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPageOnRight(BtPagerType btPagerType, BydData bydData) {
        x3.a.a(TAG_W, "showPageOnRight", ", type: " + btPagerType + ", data: " + bydData);
        this.curBydData = bydData;
        this.flRight.setVisibility(0);
        a pager = getPager(btPagerType);
        if (pager != null) {
            pager.updateData(bydData);
        }
        if (btPagerType == BtPagerType.CONNECTED && bydData != null && (bydData instanceof ConnectedBtDevice)) {
            int bondDeviceIndex = getBondDeviceIndex(((ConnectedBtDevice) bydData).getDevice().getMac());
            x3.a.a(TAG_W, "showPageOnRight", ", index: " + bondDeviceIndex + ", ConnectedCount:" + ((BtSettingsPresenter) getP()).getConnectedDevicesCount());
            if (bondDeviceIndex >= 0) {
                this.bondAdapter.checkItem(bondDeviceIndex);
                notifyBondDevicesDataChanged();
            }
        } else if (btPagerType == BtPagerType.PAIRING || btPagerType == BtPagerType.PAIRE_FAILED) {
            this.bondAdapter.checkItem(-1);
        } else if (btPagerType == BtPagerType.NO_CONNECTION && !((BtSettingsPresenter) getP()).isConnected()) {
            this.bondAdapter.checkItem(-1);
        }
        if (this.curPagerType != btPagerType) {
            this.prePagerType = this.curPagerType;
            if (this.prePagerType != null) {
                this.pagerMap.get(this.prePagerType).onHide();
            }
            this.curPagerType = btPagerType;
            this.flRight.removeAllViews();
            pager.loadData();
            this.flRight.addView(pager.getRootView());
        }
    }

    private void updateBondDeviceListUI() {
        List<BondDevice> list = this.bondDevices;
        if (list == null || list.size() <= 0) {
            showPageOnRight(BtPagerType.NO_CONNECTION, null);
            return;
        }
        Iterator<BondDevice> it = this.bondDevices.iterator();
        boolean z7 = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BondDevice next = it.next();
            boolean zIsConnected = ((BtSettingsPresenter) getP()).isConnected(next.getDevice());
            next.setPreConnectedTime(((BtSettingsPresenter) getP()).getDevicePreconnectedTime(next.getMac()));
            next.setConnected(zIsConnected);
            next.setConnectState(zIsConnected ? 2 : next.getConnectState());
            if (zIsConnected) {
                z7 = true;
            }
        }
        Collections.sort(this.bondDevices);
        x3.a.a(TAG_W, "updateBondDeviceListUI", ", hasConnected: " + z7 + ", connecting: " + this.connecting);
        if (z7) {
            this.bondAdapter.checkItem(0);
            showConnectedPager(this.bondDevices.get(0));
        } else {
            if (this.connecting) {
                return;
            }
            this.bondAdapter.checkItem(-1);
            BtPagerType btPagerType = this.curPagerType;
            BtPagerType btPagerType2 = BtPagerType.NO_CONNECTION;
            if (btPagerType != btPagerType2) {
                showPageOnRight(btPagerType2, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x019d A[Catch: all -> 0x01aa, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0018, B:8:0x001f, B:10:0x0025, B:12:0x003f, B:14:0x0045, B:15:0x0048, B:17:0x005a, B:16:0x004c, B:18:0x005d, B:19:0x0089, B:21:0x008f, B:23:0x0099, B:25:0x00a7, B:26:0x00ac, B:28:0x00b2, B:29:0x00c4, B:31:0x00f4, B:33:0x00fc, B:35:0x0128, B:37:0x012e, B:40:0x015e, B:42:0x0164, B:46:0x0171, B:50:0x01a5, B:49:0x019d), top: B:56:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void updateBondDevices() {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.btsetting.BtSettingsPager.updateBondDevices():void");
    }

    private void updateBondDevicesOnBond(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "---updateBondDevicesOnBond---deviceBond: " + m4.d.c(bluetoothDevice));
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append("---updateBondDevicesOnBond---pairOob: ");
        sb.append(this.pairOob == null ? " is null." : (String) this.pairOob.first);
        strArr[0] = sb.toString();
        x3.a.a(TAG_W, strArr);
        updateBondDevices();
    }

    private void updateBondDevicesView() {
        int size = this.bondDevices.size();
        x3.a.a(TAG_W, "updateBondDevicesView", ", bondSize: " + size);
        notifyBondDevicesDataChanged();
        if (size > 0) {
            this.llPairedDevices.setVisibility(0);
            if (size > 2) {
                this.llShowMore.setVisibility(0);
            } else {
                this.llShowMore.setVisibility(8);
            }
        } else {
            this.llPairedDevices.setVisibility(8);
        }
        updateBondRecyclerViewHeight();
        if (size <= 0) {
            updateOnConnectedChanged(false, null);
        }
    }

    private void updateBondRecyclerViewHeight() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int size = this.bondDevices.size();
        x3.a.a(TAG_W, "updateBondRecyclerViewHeight", ", isShowMore = " + this.isShowMore);
        this.ivShowMore.setImageResource(this.isShowMore ? R.drawable.icon_public_uparrow_s_24 : R.drawable.icon_public_dowmarrow_s_24);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rcvBond.getLayoutParams();
        layoutParams.width = -1;
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_60);
        int i8 = size >= 2 ? dimension * 2 : dimension;
        if (this.isShowMore) {
            i8 = dimension * size;
        }
        layoutParams.height = i8;
        this.rcvBond.setLayoutParams(layoutParams);
    }

    private void updateDeviceState(BluetoothDevice bluetoothDevice, int i8) {
        if (bluetoothDevice == null) {
            return;
        }
        boolean zIsConnected = ((BtSettingsPresenter) getP()).isConnected(bluetoothDevice);
        for (BondDevice bondDevice : this.bondDevices) {
            if (bluetoothDevice.getAddress().equals(bondDevice.getMac())) {
                bondDevice.setConnectState(i8);
                bondDevice.setConnected(zIsConnected);
            }
        }
        Collections.sort(this.bondDevices);
        notifyBondDevicesDataChanged();
    }

    private void updateDiscoverDevicesOnBond(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "---updateDiscoverDevicesOnBond---deviceBond: " + m4.d.c(bluetoothDevice));
        onDiscoverDevicesChanged(true, new BTDevice(bluetoothDevice));
    }

    private void updateOnConnectedChanged(boolean z7, BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "updateOnConnectedChanged", "connected: " + z7, "connecting: " + this.connecting, "device: " + m4.d.c(bluetoothDevice));
        if (z7) {
            if (bluetoothDevice != null) {
                ConnectedBtDevice connectedBtDevice = getConnectedBtDevice(bluetoothDevice);
                if (connectedBtDevice.getDevice() == null || !bluetoothDevice.getAddress().equals(connectedBtDevice.getDevice().getMac())) {
                    connectedBtDevice.setDevice(new BondDevice(new BTDevice(bluetoothDevice)));
                }
                boolean zIsConnected = ((BtSettingsPresenter) getP()).isConnected(bluetoothDevice);
                x3.a.a(TAG_W, "updateOnConnectedChanged", ", isConnected: " + zIsConnected);
                if (zIsConnected) {
                    showPageOnRight(BtPagerType.CONNECTED, connectedBtDevice);
                    return;
                }
                return;
            }
            return;
        }
        if (this.connecting) {
            return;
        }
        if (bluetoothDevice != null) {
            boolean zIsConnectingDevice = isConnectingDevice(bluetoothDevice.getAddress());
            x3.a.a(TAG_W, "updateOnConnectedChanged", "isTarget: " + zIsConnectingDevice);
            if (!zIsConnectingDevice) {
                return;
            }
        }
        BluetoothDevice connectedDevice = ((BtSettingsPresenter) getP()).getConnectedDevice();
        if (connectedDevice == null) {
            if (this.pairing) {
                return;
            }
            showPageOnRight(BtPagerType.NO_CONNECTION, null);
            return;
        }
        x3.a.a(TAG_W, "updateOnConnectedChanged", ", bd: " + m4.d.c(connectedDevice));
        ConnectedBtDevice connectedBtDevice2 = getConnectedBtDevice(connectedDevice);
        boolean zIsConnected2 = ((BtSettingsPresenter) getP()).isConnected(connectedDevice);
        x3.a.a(TAG_W, "updateOnConnectedChanged", ", cbd: " + connectedBtDevice2);
        if (zIsConnected2 && connectedBtDevice2.getDevice().getMac().equals(connectedDevice.getAddress())) {
            showPageOnRight(BtPagerType.CONNECTED, connectedBtDevice2);
        } else {
            showPageOnRight(BtPagerType.NO_CONNECTION, null);
        }
    }

    private void updateOnOobBonded(Pair<String, String> pair) {
        if (pair != null) {
            x3.a.a(TAG_W, "---updateOnOobBonded---pair: " + ((String) pair.first));
        }
        this.pairOob = pair;
        updateBondDevices();
    }

    private void updateonBtStateChanged(boolean z7) {
        x3.a.a(TAG_W, "updateonBtStateChanged", ", open = " + z7);
        this.isOpen = z7;
        Context context = getContext();
        if (context == null) {
            return;
        }
        this.switchBleState.b(z7, false);
        Resources resources = context.getResources();
        if (z7) {
            updateBondDevices();
            this.llOpen.setVisibility(0);
            this.llClose.setVisibility(8);
            this.tvName.setTextColor(resources.getColor(R.color.text_color_main));
            this.ivRight.b(1);
            return;
        }
        this.connecting = false;
        this.connectingDevice = null;
        showPageOnRight(BtPagerType.NO_CONNECTION, null);
        this.llOpen.setVisibility(8);
        this.llClose.setVisibility(0);
        this.rlName.setVisibility(8);
        this.tvName.setVisibility(0);
        this.tvName.setTextColor(resources.getColor(R.color.text_color_main_gray));
        this.ivRight.b(0);
        List<BTDevice> list = this.discoverDevices;
        if (list != null) {
            list.clear();
            this.deviceMap.clear();
        }
        e.b(this.etName);
        DiscoverBtListAdapter discoverBtListAdapter = this.discoverAdapter;
        if (discoverBtListAdapter != null) {
            discoverBtListAdapter.notifyDataSetChanged();
        }
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
        return R.layout.activity_bt;
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
    public void initData(final Context context) {
        x3.a.a(TAG_W, "initData", "--initData--");
        ArrayList arrayList = new ArrayList();
        this.bondDevices = arrayList;
        BondBtListAdapter bondBtListAdapter = new BondBtListAdapter(arrayList);
        this.bondAdapter = bondBtListAdapter;
        this.rcvBond.setAdapter(bondBtListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.8
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
            public boolean canScrollVertically() {
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.o
            public void onItemsChanged(RecyclerView recyclerView) {
                super.onItemsChanged(recyclerView);
            }
        };
        linearLayoutManager.setOrientation(1);
        this.rcvBond.setLayoutManager(linearLayoutManager);
        this.rcvBond.setItemAnimator(new c1.c());
        this.bondAdapter.setOnItemClickedListener(new d4.d() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.9
            @Override // d4.d
            public void onItemClicked(int i8) {
                x3.a.a(BtSettingsPager.TAG_W, "initData", "--onItemClicked--bond--position = " + i8);
                BtSettingsPager.this.disableBtNameEdit(false);
                BtSettingsPager btSettingsPager = BtSettingsPager.this;
                btSettingsPager.checkBondDevice(context, (BondDevice) btSettingsPager.bondDevices.get(i8));
            }
        });
        this.bondAdapter.setOnItemBtnClickedCallback(new d4.c() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.10
            @Override // d4.c
            public void onItemBtnClicked(int i8, BtnType btnType) {
                if (m4.c.a(200L)) {
                    return;
                }
                BtSettingsPager.this.disableBtNameEdit(false);
                BondDevice bondDevice = (BondDevice) BtSettingsPager.this.bondDevices.get(i8);
                if (bondDevice.isConnected() && bondDevice.getLevel() != BTPowerLevel.UNDEFINED) {
                    x3.a.a(BtSettingsPager.TAG_W, "initData", "设备HFP已连接,点击return");
                    return;
                }
                BtSettingsPager.this.indexClicked = i8;
                BtSettingsPager.this.mUnpairDevice = bondDevice;
                BtSettingsPager btSettingsPager = BtSettingsPager.this;
                btSettingsPager.dialogToUnpairDevice((Context) btSettingsPager.ref.get());
            }
        });
        this.bondDevicesMap = new ConcurrentHashMap<>();
        this.deviceMap = new ConcurrentHashMap<>();
        ArrayList arrayList2 = new ArrayList();
        this.discoverDevices = arrayList2;
        DiscoverBtListAdapter discoverBtListAdapter = new DiscoverBtListAdapter(arrayList2);
        this.discoverAdapter = discoverBtListAdapter;
        this.rcvDiscover.setAdapter(discoverBtListAdapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext()) { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.11
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager2.setOrientation(1);
        this.rcvDiscover.setLayoutManager(linearLayoutManager2);
        this.rcvDiscover.setItemAnimator(new c1.c());
        this.discoverAdapter.setOnItemClickedListener(new d4.d() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.12
            @Override // d4.d
            public void onItemClicked(int i8) {
                x3.a.a(BtSettingsPager.TAG_W, "initData", "--onItemClicked--discover--position = " + i8);
                BtSettingsPager.this.disableBtNameEdit(false);
                if (BtSettingsPager.this.curPagerType == BtPagerType.PAIRING || BtSettingsPager.this.curPagerType == BtPagerType.PAIRE_CODE) {
                    d.c(BtSettingsPager.this.getContext(), context.getString(R.string.hint_muti_pair_operation));
                } else {
                    if (BtSettingsPager.this.checkCarPlayOnAP24G(context)) {
                        return;
                    }
                    BTDevice bTDevice = (BTDevice) BtSettingsPager.this.discoverDevices.get(i8);
                    PagerBtDevice pagerBtDevice = new PagerBtDevice(bTDevice.getName());
                    pagerBtDevice.setDevice(bTDevice.getDevice());
                    BtSettingsPager.this.pairDevice(pagerBtDevice);
                }
            }
        });
        this.connectedMap = new ConcurrentHashMap<>();
    }

    @Override // z3.e
    public boolean initHandler() {
        return true;
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // z3.e
    public void initViews(Context context) {
        if (this.pagerMap == null) {
            this.pagerMap = new ConcurrentHashMap<>();
        }
        ((BtSettingsPresenter) getP()).init(33);
        x3.a.a(TAG_W, "----initViews----");
        this.titleParent = (FrameLayout) this.mRootView.findViewById(R.id.rl_title_bt);
        this.flRight = (FrameLayout) this.mRootView.findViewById(R.id.fl_bt_right);
        this.llOpen = this.mRootView.findViewById(R.id.ll_bt_state_open_bt);
        this.llClose = this.mRootView.findViewById(R.id.ll_bt_state_close_bt);
        View viewFindViewById = this.mRootView.findViewById(R.id.ll_paired_device_list_bt_left);
        this.llPairedDevices = viewFindViewById;
        this.rcvBond = (RecyclerView) viewFindViewById.findViewById(R.id.rcv_bond_device_bt_left);
        this.llShowMore = this.llPairedDevices.findViewById(R.id.ll_show_more_bt_left);
        this.ivShowMore = (ImageView) this.llPairedDevices.findViewById(R.id.iv_show_more_bt_left);
        this.llShowMore.setOnClickListener(this);
        View viewFindViewById2 = this.mRootView.findViewById(R.id.ll_discover_device_list_bt_left);
        this.llDiscoverDevices = viewFindViewById2;
        ImageView imageView = (ImageView) viewFindViewById2.findViewById(R.id.iv_refresh_bt_left);
        this.ivRefresh = imageView;
        imageView.setOnClickListener(this);
        this.rcvDiscover = (RecyclerView) this.llDiscoverDevices.findViewById(R.id.rcv_discovered_device_bt_left);
        this.mLeftView = this.mRootView.findViewById(R.id.rl_left_bt);
        if (AppConfig.a() == CarModel$Brand.DENZA) {
            this.mLeftView.setBackgroundResource(R.drawable.bg_dialog_bt_left_tenz_land);
        }
        j7.c.c().o(this);
        ScrollView scrollView = (ScrollView) this.mRootView.findViewById(R.id.bt_left_sv);
        this.llPairedDevices.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BtSettingsPager.this.etName != null) {
                    BtSettingsPager.this.etName.clearFocus();
                }
            }
        });
        this.llDiscoverDevices.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BtSettingsPager.this.etName != null) {
                    BtSettingsPager.this.etName.clearFocus();
                }
            }
        });
        q2.f.b(scrollView);
    }

    @Override // z3.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    public void onBatteryChanged(BtBatteryParams btBatteryParams) {
        BluetoothDevice bluetoothDevice = btBatteryParams.device;
        BTPowerLevel bTPowerLevel = btBatteryParams.level;
        x3.a.a(TAG_W, "onBatteryChanged", ", level: " + bTPowerLevel + ", device: " + m4.d.c(bluetoothDevice));
        List<BondDevice> list = this.bondDevices;
        if (list == null || list.isEmpty()) {
            return;
        }
        x3.a.a(TAG_W, "onBatteryChanged", "------update powerLevel----");
        for (BondDevice bondDevice : this.bondDevices) {
            if (bondDevice.getMac().equals(bluetoothDevice.getAddress())) {
                bondDevice.setLevel(bTPowerLevel);
                x3.a.a(TAG_W, "onBatteryChanged", ", bd: " + bondDevice);
            }
        }
        notifyBondDevicesDataChanged();
    }

    public void onBtBondFailed(BondFailedParams bondFailedParams) {
        BluetoothDevice bluetoothDevice = bondFailedParams.device;
        int i8 = bondFailedParams.errCode;
        x3.a.a(TAG_W, "onBtBondFailed", ", device = " + m4.d.c(bluetoothDevice));
        this.prePairFailed = System.currentTimeMillis();
        PagerBtDevice pagerBtDevice = new PagerBtDevice(bluetoothDevice.getName());
        pagerBtDevice.setDevice(bluetoothDevice);
        pagerBtDevice.setErrCode(i8);
        showPageOnRight(BtPagerType.PAIRE_FAILED, pagerBtDevice);
    }

    public void onBtNameChanged(BTDevice bTDevice) {
        x3.a.a(TAG_W, "onBtNameChanged", ", device: " + bTDevice);
        updateBondDevices();
    }

    public void onBtStateChanged(boolean z7) {
        x3.a.a(TAG_W, "onBtStateChanged", ", open = " + z7);
        this.switchBleState.setInvalid(false);
        updateonBtStateChanged(z7);
        this.switchBleState.setClickable(true);
        this.switchBleState.setFocusable(true);
    }

    public void onCarPlayStateChanged(CarPlayParams carPlayParams) {
        x3.a.a(TAG_W, "onCarPlayStateChanged", ", params.name: " + carPlayParams.name + ", state: " + carPlayParams.state);
        List<BondDevice> list = this.bondDevices;
        if (list == null || list.isEmpty()) {
            return;
        }
        Map<String, String> carPlayMap = ((BtSettingsPresenter) getP()).getCarPlayMap();
        for (BondDevice bondDevice : this.bondDevices) {
            if (carPlayMap.get(bondDevice.getMac()) != null) {
                bondDevice.setPhoneType(PhoneType.CAR_PLAY);
            }
            if (bondDevice.getPhoneType() == PhoneType.CAR_PLAY && bondDevice.getMac().equals(carPlayParams.btMaC)) {
                CarPlayDevice connectedCarPlayDevice = ((BtSettingsPresenter) getP()).getConnectedCarPlayDevice();
                bondDevice.setCpConnected(connectedCarPlayDevice != null && connectedCarPlayDevice.getBtAddr().equals(carPlayParams.btMaC));
            }
        }
        notifyBondDevicesDataChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (m4.c.c(view, 200L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_back_bt) {
            z3.d dVar = this.mCallback;
            if (dVar != null) {
                dVar.onBackPress();
            }
            finish();
            return;
        }
        if (id == R.id.iv_right_local_device_bt) {
            enableBtNameEdit();
            return;
        }
        if (id == R.id.iv_close_edit_bt) {
            this.etName.setHint(((BtSettingsPresenter) getP()).getBtName());
            this.etName.setText("");
            return;
        }
        if (id == R.id.ll_show_more_bt_left) {
            disableBtNameEdit(false);
            this.isShowMore = !this.isShowMore;
            updateBondRecyclerViewHeight();
        } else if (id == R.id.iv_refresh_bt_left) {
            refresh();
            if (this.isShowMore) {
                this.isShowMore = false;
                updateBondRecyclerViewHeight();
            }
        }
    }

    public void onConnectFailed(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "onConnectFailed", ", device = " + m4.d.c(bluetoothDevice));
        updateDeviceState(bluetoothDevice, 0);
        BondDevice bondDevice = this.mUnpairDevice;
        if (bondDevice != null && bondDevice.getMac() != null && this.mUnpairDevice.getMac().equals(bluetoothDevice.getAddress())) {
            x3.a.a(TAG_W, "onConnectFailed", ", This device is unpaired, d: " + m4.d.c(bluetoothDevice));
            if (isConnectingDevice(bluetoothDevice.getAddress())) {
                this.connecting = false;
                return;
            }
            return;
        }
        x3.a.a(TAG_W, "onConnectFailed", "connecting: " + this.connecting);
        if (!((BtSettingsPresenter) getP()).isConnected() || this.connecting) {
            if (isConnectingDevice(bluetoothDevice.getAddress())) {
                this.connecting = false;
            }
            int bondDeviceIndex = getBondDeviceIndex(bluetoothDevice.getAddress());
            if (!((BtSettingsPresenter) getP()).isConnected(bluetoothDevice)) {
                PagerBtDevice pagerBtDevice = new PagerBtDevice(bluetoothDevice.getName());
                pagerBtDevice.setDevice(bluetoothDevice);
                showPageOnRight(BtPagerType.CONNECT_FAILED, pagerBtDevice);
            } else if (bondDeviceIndex >= 0 && bondDeviceIndex < this.bondDevices.size()) {
                showConnectedPager(this.bondDevices.get(bondDeviceIndex));
            }
            itemRollback(bondDeviceIndex, 10L);
        }
    }

    public void onConnected(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        if (isConnectingDevice(bluetoothDevice.getAddress())) {
            this.connecting = false;
        }
        x3.a.a(TAG_W, "onConnected", "device: " + m4.d.c(bluetoothDevice));
        ConnectedBtDevice connectedBtDevice = getConnectedBtDevice(bluetoothDevice);
        if (connectedBtDevice.getDevice() == null || !bluetoothDevice.getAddress().equals(connectedBtDevice.getDevice().getMac())) {
            connectedBtDevice.setDevice(new BondDevice(new BTDevice(bluetoothDevice)));
        }
        for (BondDevice bondDevice : this.bondDevices) {
            if (bondDevice.getMac().equals(bluetoothDevice.getAddress())) {
                bondDevice.setPreConnectedTime(System.currentTimeMillis());
            }
        }
        if (this.pairing) {
            x3.a.a(TAG_W, "onConnected", "is pairing:" + m4.d.f(bluetoothDevice.getName()));
            return;
        }
        if (((BtSettingsPresenter) getP()).isConnected(bluetoothDevice)) {
            updateOnConnectedChanged(true, bluetoothDevice);
            updateBondDeviceListUI();
            updateDeviceState(bluetoothDevice, 2);
        }
    }

    public void onConnecting(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        x3.a.a(TAG_W, "onConnecting", ", device = " + m4.d.c(bluetoothDevice));
        updateDeviceState(bluetoothDevice, 1);
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        SystemDialog.sendBroadcastBtDialogShow(getContext(), false);
        super.onDestroy();
        j7.c.c().k(new m2.a(230009));
        j7.c.c().q(this);
        enableAndroidAutoHfp(true);
        e3.a.d(false);
        this.mBtnCallback = null;
        List<BondDevice> list = this.bondDevices;
        if (list != null) {
            Iterator<BondDevice> it = list.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.bondDevices.clear();
        }
        List<BTDevice> list2 = this.discoverDevices;
        if (list2 != null) {
            Iterator<BTDevice> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().release();
            }
            this.discoverDevices.clear();
        }
        ConcurrentHashMap<String, BondDevice> concurrentHashMap = this.bondDevicesMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, BTDevice> concurrentHashMap2 = this.deviceMap;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        ConcurrentHashMap<String, BondDevice> concurrentHashMap3 = this.connectedMap;
        if (concurrentHashMap3 != null) {
            concurrentHashMap3.clear();
        }
        ConcurrentHashMap<BtPagerType, a> concurrentHashMap4 = this.pagerMap;
        if (concurrentHashMap4 != null) {
            Iterator<a> it3 = concurrentHashMap4.values().iterator();
            while (it3.hasNext()) {
                it3.next().onDestroy();
            }
        }
        ServiceDialogHolder.dismissAll(getClass().getName());
        SystemDialog.dismissIdentification();
        SystemDialog.dismissChangeCpaaDevicePager();
        e.b(this.etName);
    }

    public void onDeviceBonding(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "---onDeviceBonding---device = " + m4.d.c(bluetoothDevice));
    }

    public void onDeviceDiscovered(BTDevice bTDevice) {
        x3.a.a(TAG_W, "onDeviceDiscovered", ", device: " + bTDevice);
        if (!this.isScanning) {
            startRefreshAnimation();
        }
        BondDevice bondDevice = this.bondDevicesMap.get(bTDevice.getMac());
        if (bondDevice == null) {
            if (this.deviceMap.get(bTDevice.getMac()) != null) {
                return;
            }
            onDiscoverDevicesChanged(false, bTDevice);
        } else {
            bondDevice.setDevice(bTDevice.getDevice());
            bondDevice.setRssi(bTDevice.getRssi());
            bondDevice.setConnected(((BtSettingsPresenter) getP()).isConnected(bondDevice.getDevice()));
            Collections.sort(this.bondDevices);
            notifyBondDevicesDataChanged();
        }
    }

    public void onDisconnected(BluetoothDevice bluetoothDevice) {
        BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(this.prePairFailed));
        if (bluetoothDevice == null || bigDecimalSubtract.longValue() < 1000) {
            x3.a.a(TAG_W, "onDisconnected", ", PAIRE_FAILED just now, time delta = " + bigDecimalSubtract.longValue());
            return;
        }
        x3.a.a(TAG_W, "onDisconnected", ", device = " + m4.d.c(bluetoothDevice));
        updateOnConnectedChanged(false, bluetoothDevice);
        updateBondDeviceListUI();
        this.connectedMap.remove(bluetoothDevice.getAddress());
        updateDeviceState(bluetoothDevice, 0);
        if (isConnectingDevice(bluetoothDevice.getAddress())) {
            this.connecting = false;
        }
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onGetMessage(m2.a aVar) {
        if (aVar.a() == 112) {
            CarPlayParams carPlayParams = (CarPlayParams) aVar.b();
            x3.a.a(TAG_W, "cp_state_changed:" + carPlayParams.toString());
            onCarPlayStateChanged(carPlayParams);
        }
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        x3.a.a(TAG_W, "onHandlerUpdateUI", ", msg.arg1: " + Integer.toHexString(message.arg1));
        int i8 = message.arg1;
        if (i8 == 1) {
            ((BtSettingsPresenter) getP()).onBtStateChanged(((Boolean) message.obj).booleanValue());
        }
        if (i8 == 32) {
            onReceivedPairRequest((RequestPairParams) message.obj);
            return;
        }
        if (i8 == 96) {
            onBatteryChanged((BtBatteryParams) message.obj);
            return;
        }
        if (i8 == 80) {
            onProfileConnectStataChanged((ProfileParams) message.obj);
            return;
        }
        if (i8 == 81) {
            onProfileServiceConnected(((Integer) message.obj).intValue());
            return;
        }
        switch (i8) {
            case 16:
                onDeviceDiscovered((BTDevice) message.obj);
                break;
            case 17:
                onTargetNotFound((BluetoothDevice) message.obj);
                break;
            case 18:
                onScanFinished();
                break;
            case 19:
                onBtNameChanged((BTDevice) message.obj);
                break;
            default:
                switch (i8) {
                    case 48:
                        this.pairing = false;
                        updateBondDevicesOnBond((BluetoothDevice) message.obj);
                        updateDiscoverDevicesOnBond((BluetoothDevice) message.obj);
                        handleWhenAAConnectedOtherDeviceConnect((BluetoothDevice) message.obj, BtSettingsPresenter.TIMEOUT_AUTO_CONNECT);
                        break;
                    case 49:
                        this.pairing = true;
                        break;
                    case 50:
                        this.pairing = false;
                        updateBondDevices();
                        break;
                    case 51:
                        this.pairing = false;
                        onBtBondFailed((BondFailedParams) message.obj);
                        break;
                    case 52:
                        updateOnOobBonded((Pair) message.obj);
                        break;
                    default:
                        switch (i8) {
                            case 64:
                                onConnected((BluetoothDevice) message.obj);
                                break;
                            case 65:
                                onDisconnected((BluetoothDevice) message.obj);
                                break;
                            case 66:
                                onConnectFailed((BluetoothDevice) message.obj);
                                break;
                            case 67:
                                onConnecting((BluetoothDevice) message.obj);
                                break;
                            default:
                                switch (i8) {
                                    case 114:
                                    case 115:
                                        this.newDeviceSupportCPAA = true;
                                        x3.a.a(TAG_W, "newDeviceSupportCPAA");
                                        break;
                                    case 116:
                                        onAANewDevicePair(((Integer) message.obj).intValue());
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
        e.b(this.etName);
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        NoMenuEditText noMenuEditText;
        x3.a.a(TAG_W, "---onKeyboardStateChanged---showing: " + z7);
        if (z7 || (noMenuEditText = this.etName) == null || !noMenuEditText.hasFocus()) {
            return;
        }
        this.etName.clearFocus();
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
        super.onLtrRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_bt_settings, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        super.onLtrRudderR(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_bt_settings_r, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    public void onPagerBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        if (cls == null) {
            return;
        }
        String name = cls.getName();
        if (BtConnectedPager.class.getName().equals(name)) {
            onConnectedPagerClicked(pagerBtnType, (ConnectedBtDevice) obj, obj2);
            return;
        }
        if (BtPairingPager.class.getName().equals(name)) {
            onPairingPagerClicked(pagerBtnType, (PagerBtDevice) obj, obj2);
            return;
        }
        if (BtPaireFailedPager.class.getName().equals(name)) {
            onPaireFailedPagerClicked(pagerBtnType, (PagerBtDevice) obj, obj2);
        } else if (BtConnectFailedPager.class.getName().equals(name)) {
            onConnectFailedPagerClicked(pagerBtnType, (PagerBtDevice) obj, obj2);
        } else if (BtPaireCodePager.class.getName().equals(name)) {
            onPairCodePagerClicked(pagerBtnType, (PagerBtDevice) obj, obj2);
        }
    }

    public void onProfileConnectStataChanged(ProfileParams profileParams) {
        x3.a.a(TAG_W, "onProfileConnectStataChanged", "----params = " + profileParams);
        if (profileParams == null || profileParams.device == null) {
            return;
        }
        int i8 = profileParams.profile;
        if (i8 == 16 || i8 == 11) {
            BondDevice bondDevice = null;
            for (BondDevice bondDevice2 : this.bondDevices) {
                if (bondDevice2.getMac().equals(profileParams.device.getAddress())) {
                    int connectStateInt = ((BtSettingsPresenter) getP()).getConnectStateInt(profileParams.state);
                    ConnectState connectState = profileParams.state;
                    ConnectState connectState2 = ConnectState.CONNECTED;
                    bondDevice2.setHfpConnected(connectState == connectState2);
                    x3.a.a(TAG_W, "onProfileConnectStataChanged", "----bd: " + m4.d.c(bondDevice2.getDevice()) + ", state: " + connectStateInt);
                    if (profileParams.state == connectState2) {
                        bondDevice2.setConnected(true);
                    }
                    bondDevice = bondDevice2;
                }
            }
            boolean zIsConnectingDevice = isConnectingDevice(profileParams.device.getAddress());
            ConnectState connectState3 = profileParams.state;
            ConnectState connectState4 = ConnectState.CONNECTED;
            if (connectState3 == connectState4 && zIsConnectingDevice) {
                this.connecting = false;
            }
            x3.a.a(TAG_W, "onProfileConnectStataChanged", "isConnectingTarget: " + zIsConnectingDevice, "deviceChanged: " + bondDevice);
            if (bondDevice != null) {
                Collections.sort(this.bondDevices);
                notifyBondDevicesDataChanged();
                if (profileParams.state == connectState4) {
                    if (!BtSingleConnection.supportAndroidAuto(profileParams.device)) {
                        this.bondAdapter.checkItem(0);
                        showConnectedPager(bondDevice);
                    } else if (zIsConnectingDevice) {
                        this.bondAdapter.checkItem(0);
                        showConnectedPager(bondDevice);
                    }
                }
            }
        }
    }

    public void onProfileServiceConnected(int i8) {
        x3.a.a(TAG_W, "onProfileServiceConnected", ", profile = " + i8);
        updateBondDevices();
    }

    public void onReceivedPairRequest(RequestPairParams requestPairParams) {
        BluetoothDevice bluetoothDevice = requestPairParams.device;
        int i8 = requestPairParams.pairCode;
        x3.a.a(TAG_W, "onReceivedPairRequest", ", params:" + requestPairParams);
        PagerBtDevice pagerBtDevice = new PagerBtDevice(bluetoothDevice.getName());
        pagerBtDevice.setDevice(bluetoothDevice);
        pagerBtDevice.setPairCode(i8);
        showPageOnRight(BtPagerType.PAIRE_CODE, pagerBtDevice);
        bluetoothDevice.setPairingConfirmation(true);
    }

    @Override // z3.e
    public void onRestoreInstanceState() {
        super.onRestoreInstanceState();
        BtDialogState btDialogState = (BtDialogState) DialogStatesCache.get(DialogState.BT_DIALOG_STATE);
        x3.a.a(TAG_W, "onRestoreInstanceState", "state: " + btDialogState);
        if (btDialogState == null) {
            return;
        }
        List<BTDevice> discoverDevices = btDialogState.getDiscoverDevices();
        if (this.discoverDevices != null && this.deviceMap != null && discoverDevices != null && !discoverDevices.isEmpty()) {
            this.discoverDevices.clear();
            for (BTDevice bTDevice : discoverDevices) {
                this.deviceMap.put(bTDevice.getMac(), bTDevice);
            }
            this.discoverDevices.addAll(this.deviceMap.values());
            Collections.sort(this.discoverDevices);
            this.discoverAdapter.notifyDataSetChanged();
        }
        BtPagerType curPagerType = btDialogState.getCurPagerType();
        x3.a.a(TAG_W, "onRestoreInstanceState", "type: " + curPagerType);
        List<BondDevice> bondedDevices = ((BtSettingsPresenter) getP()).getBondedDevices();
        if (curPagerType != null) {
            BydData curBydData = btDialogState.getCurBydData();
            x3.a.a(TAG_W, "onRestoreInstanceState", "data: " + curBydData);
            if (curBydData != null) {
                if (curBydData instanceof PagerBtDevice) {
                    ((PagerBtDevice) curBydData).setDevice((curPagerType == BtPagerType.PAIRING || curPagerType == BtPagerType.PAIRE_CODE || curPagerType == BtPagerType.PAIRE_FAILED) ? getDevice(discoverDevices, ((PagerBtDevice) curBydData).getDevice().getAddress()) : getDevice(bondedDevices, ((PagerBtDevice) curBydData).getDevice().getAddress()));
                } else if (curBydData instanceof ConnectedBtDevice) {
                    ConnectedBtDevice connectedBtDevice = (ConnectedBtDevice) curBydData;
                    connectedBtDevice.getDevice().setDevice(getDevice(bondedDevices, connectedBtDevice.getDevice().getMac()));
                }
            }
            showPageOnRight(curPagerType, curBydData);
        }
        if (btDialogState.isUnpairDialogShowing()) {
            BondDevice unpairDevice = btDialogState.getUnpairDevice();
            this.mUnpairDevice = unpairDevice;
            if (unpairDevice != null) {
                BluetoothDevice device = getDevice(bondedDevices, unpairDevice.getMac());
                x3.a.a(TAG_W, "onRestoreInstanceState", "unpairDevice: " + m4.d.c(device));
                this.mUnpairDevice.setDevice(device);
                dialogToUnpairDevice(this.ref.get());
            }
        }
        DialogStatesCache.remove(DialogState.BT_DIALOG_STATE);
    }

    @Override // z3.a
    public void onResume() {
        super.onResume();
        this.tvName.setText(((BtSettingsPresenter) getP()).getBtName());
        this.isOpen = ((BtSettingsPresenter) getP()).isEnabled();
        this.switchBleState.b(this.isOpen, !this.isOpen);
        x3.a.a(TAG_W, "onResume", "---onResume---isOpen = " + this.isOpen);
        if (ServiceDialogHolder.isDoingonConfigurationChanged()) {
            if (!this.isOpen) {
                resetSwitchState();
            }
        } else if (this.isOpen) {
            ((BtSettingsPresenter) getP()).startScan();
        } else {
            ((BtSettingsPresenter) getP()).enable(true);
        }
        this.etName.setHint(((BtSettingsPresenter) getP()).getBtName());
        this.etName.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.13
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i8, KeyEvent keyEvent) {
                if (i8 != 6) {
                    return false;
                }
                BtSettingsPager.this.disableBtNameEdit(true);
                return false;
            }
        });
        this.etName.setOnBackEventListener(new NoMenuEditText.b() { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.14
            @Override // com.byd.mvp.ui.widget.NoMenuEditText.b
            public void onBackPress() {
                BtSettingsPager.this.disableBtNameEdit(false);
            }
        });
        NoMenuEditText noMenuEditText = this.etName;
        noMenuEditText.addTextChangedListener(new BydTextWatcher(noMenuEditText, BydTextWatcher.getNameRegex(noMenuEditText.getContext())) { // from class: com.byd.systemsettings.btsetting.BtSettingsPager.15
            @Override // com.byd.systemsettings.ui.widget.BydTextWatcher
            public void onInputFiltered(String str) {
                d.b(BtSettingsPager.this.getContext(), R.string.hint_unsupport_input);
            }
        });
        if (((BtSettingsPresenter) getP()).isEnabled()) {
            updateonBtStateChanged(true);
        } else if (!ServiceDialogHolder.isDoingonConfigurationChanged()) {
            changeBtState(true);
        }
        this.currentAndroidAutoDevice = ((BtSettingsPresenter) getP()).getConnectedAndroidAutoDevice();
        enableAndroidAutoHfp(true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_bt_settings_rtl, (ViewGroup) null));
        loadTitleOnLayoutLocked();
        setViewGroupRtl(this.titleParent, false);
    }

    @Override // z3.e
    public void onSaveInstanceState() {
        super.onSaveInstanceState();
        BtDialogState btDialogState = new BtDialogState(cloneDiscoverDevices(this.discoverDevices), this.curPagerType, this.curBydData);
        btDialogState.setFirstItemIndex(0);
        btDialogState.setUnpairDialogShowing(this.unpairDialogShowing);
        if (this.unpairDialogShowing) {
            btDialogState.setUnpairDevice(this.mUnpairDevice);
        }
        x3.a.a(TAG_W, "onSaveInstanceState", "state: " + btDialogState);
        DialogStatesCache.put(DialogState.BT_DIALOG_STATE, btDialogState);
    }

    public void onScanFinished() {
        x3.a.a(TAG_W, "---onScanFinished---isScanning: " + this.isScanning);
        this.ivRefresh.clearAnimation();
        this.isScanning = false;
    }

    public void onTargetNotFound(BluetoothDevice bluetoothDevice) {
        x3.a.a(TAG_W, "---onTargetNotFound---device = " + m4.d.c(bluetoothDevice));
    }

    public void showPages(int i8) {
    }

    public void startRefreshAnimation() {
        x3.a.a(TAG_W, "---startRefreshAnimation---");
        if (this.isScanning) {
            return;
        }
        this.isScanning = true;
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setStartOffset(0L);
        this.ivRefresh.startAnimation(rotateAnimation);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.g
    public BtSettingsPresenter newP() {
        return new BtSettingsPresenter();
    }
}
