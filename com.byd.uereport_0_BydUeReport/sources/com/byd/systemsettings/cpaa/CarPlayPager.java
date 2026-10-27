package com.byd.systemsettings.cpaa;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.bt.model.BTDevice;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.systemsettings.cpaa.adapter.CarPlayListAdapter;
import com.byd.systemsettings.cpaa.model.CPAADevice;
import com.byd.systemsettings.cpaa.model.CarPlayType;
import com.byd.systemsettings.cpaa.present.CarPlayAAPresenter;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import j7.c;
import j7.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o2.b;
import org.greenrobot.eventbus.ThreadMode;
import p2.g;
import q2.d;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;
import y2.b;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayPager extends a<CarPlayAAPresenter> {
    public static final String NO_SHOW_MAC_LIST_CACHE = "no_show_mac_list_cache";
    private static final int REQUEST_CODE_PERMISSION = 33;
    private static final String TAG_W = "CarPlayPager";
    private static boolean connectOp = false;
    private static CPAADevice currentDevice = null;
    private static String disconnectDeviceId = "-1";
    private static boolean removeOp = false;
    private DeviceStatusChangedListener deviceStatusChangedListener;
    private CarPlayListAdapter mAdapter;
    private final Context mContext;
    private List<CPAADevice> mDeviceList;
    private b mJumpService;
    private View noDateView;
    private RecyclerView rcv;
    private RelativeLayout titleParent;

    public interface DeviceStatusChangedListener {
        void onDeviceStatusChanged();
    }

    public CarPlayPager(Context context) {
        super(context);
        this.mContext = context;
    }

    private void connectOrRemoveAACP(String str) {
        if (disconnectDeviceId.equals(str)) {
            if (currentDevice == null) {
                x3.a.b(this.TAG, "currentDevice==null return");
                return;
            }
            if (connectOp) {
                connectOp = false;
                x3.a.a(this.TAG, "connectOp");
                ((CarPlayAAPresenter) getP()).connect(currentDevice);
            } else if (removeOp) {
                removeOp = false;
                x3.a.a(this.TAG, "removeOp");
                ((CarPlayAAPresenter) getP()).remove(currentDevice);
            }
            disconnectDeviceId = "-1";
            c.c().k(new m2.a(230011));
        }
    }

    private void doDeviceAvailable(final CPAADevice cPAADevice) {
        x3.a.a(this.TAG, ", doDeviceAvailable, device: " + cPAADevice.getName());
        if (((CarPlayAAPresenter) getP()).isCarPlayConnected() || ((CarPlayAAPresenter) getP()).isAndroidAutoConnected()) {
            this.mJumpService.jump2SwitchWarningDialog(cPAADevice.getType() == CarPlayType.CAR_PLAY ? "Apple CarPlay" : "Android Auto", new b.a() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.3
                @Override // o2.b.a
                public void onOkResult(Object obj) {
                    boolean unused = CarPlayPager.connectOp = true;
                    CPAADevice unused2 = CarPlayPager.currentDevice = cPAADevice;
                    if (cPAADevice.getType() == CarPlayType.ANDROID_AUTO) {
                        BtSingleConnection.keepSingle(cPAADevice.getBtMac(), null);
                        BluetoothDevice bluetoothDeviceC = b.o.a().C(cPAADevice.getBtMac());
                        if (bluetoothDeviceC != null) {
                            BtSingleConnection.enableAndroidAutoHfp(bluetoothDeviceC, true);
                        }
                    }
                    CarPlayDevice connectingCarPlayDevice = ((CarPlayAAPresenter) CarPlayPager.this.getP()).getConnectingCarPlayDevice();
                    if (connectingCarPlayDevice != null) {
                        String unused3 = CarPlayPager.disconnectDeviceId = ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(new CPAADevice(connectingCarPlayDevice));
                        x3.a.a(CarPlayPager.this.TAG, "CarPlayDevice ing start disconnect:" + CarPlayPager.disconnectDeviceId);
                    }
                    CarPlayDevice connectedCarPlayDevice = ((CarPlayAAPresenter) CarPlayPager.this.getP()).getConnectedCarPlayDevice();
                    if (connectedCarPlayDevice != null) {
                        String unused4 = CarPlayPager.disconnectDeviceId = ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(new CPAADevice(connectedCarPlayDevice));
                        x3.a.a(CarPlayPager.this.TAG, "CarPlayDevice connected start disconnect:" + CarPlayPager.disconnectDeviceId);
                    }
                    AndroidAutoDevice connectingAndroidAutoDevice = ((CarPlayAAPresenter) CarPlayPager.this.getP()).getConnectingAndroidAutoDevice();
                    if (connectingAndroidAutoDevice != null) {
                        String unused5 = CarPlayPager.disconnectDeviceId = ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(new CPAADevice(connectingAndroidAutoDevice));
                        x3.a.a(CarPlayPager.this.TAG, "AndroidAutoDevice ing start disconnect:" + CarPlayPager.disconnectDeviceId);
                    }
                    AndroidAutoDevice connectedAndroidAutoDevice = ((CarPlayAAPresenter) CarPlayPager.this.getP()).getConnectedAndroidAutoDevice();
                    if (connectedAndroidAutoDevice != null) {
                        String unused6 = CarPlayPager.disconnectDeviceId = ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(new CPAADevice(connectedAndroidAutoDevice));
                        x3.a.a(CarPlayPager.this.TAG, "AndroidAutoDevice connected start disconnect:" + CarPlayPager.disconnectDeviceId);
                    }
                    if (connectingCarPlayDevice == null && connectedCarPlayDevice == null && connectingAndroidAutoDevice == null && connectedAndroidAutoDevice == null) {
                        boolean unused7 = CarPlayPager.connectOp = false;
                        ((CarPlayAAPresenter) CarPlayPager.this.getP()).connect(cPAADevice);
                        x3.a.a(CarPlayPager.this.TAG, "showSwitchDeviceDialog--没有连接的设备,新设备直接发起连接");
                    }
                }
            });
            return;
        }
        if ((((CarPlayAAPresenter) getP()).isCarPlayConnecting() && !isDeviceConnecting(cPAADevice)) || (((CarPlayAAPresenter) getP()).isAndroidAutoConnecting() && !isDeviceConnecting(cPAADevice))) {
            d.c(getContext(), this.mContext.getString(R.string.toast_other_device_connecting));
            return;
        }
        connectOp = false;
        d.a();
        x3.a.a(this.TAG, ", doDeviceAvailable, connect device: " + cPAADevice.getName());
        if (cPAADevice.getType() == CarPlayType.CAR_PLAY) {
            ((CarPlayAAPresenter) getP()).connect(cPAADevice);
        } else if (q2.c.b(cPAADevice.getUsbSN()) || !q2.c.b(cPAADevice.getBtMac())) {
            BtSingleConnection.keepSingle(cPAADevice.getBtMac(), new BtSingleConnection.OnDisconnectCompletedCallback() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.4
                @Override // com.byd.systemsettings.btsetting.BtSingleConnection.OnDisconnectCompletedCallback
                public void onDisconnectCompleted() {
                    BluetoothDevice bluetoothDeviceC = b.o.a().C(cPAADevice.getBtMac());
                    if (bluetoothDeviceC != null) {
                        BtSingleConnection.enableAndroidAutoHfp(bluetoothDeviceC, true);
                    }
                    ((CarPlayAAPresenter) CarPlayPager.this.getP()).connect(cPAADevice);
                }
            });
        } else {
            ((CarPlayAAPresenter) getP()).connectAndroidAuto(cPAADevice);
        }
    }

    private void doDeviceConnected(CPAADevice cPAADevice) {
        x3.a.a(this.TAG, ", doDeviceConnected, device: " + cPAADevice.getName());
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            ((CarPlayAAPresenter) getP()).startCarPlayUI();
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            ((CarPlayAAPresenter) getP()).startAndroidAutoUI();
        }
        z3.d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
        Intent intent = new Intent("com.byd.card.close.FloatCard");
        intent.putExtra("package", "com.byd.carsettings");
        if (getContext() != null) {
            getContext().sendBroadcast(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnItemBtClicked(final CPAADevice cPAADevice, BtnType btnType) {
        x3.a.a(this.TAG, ", doOnItemBtClicked, device: " + cPAADevice + ", type: " + btnType);
        if (BtnType.BTN_4.equals(btnType)) {
            this.mJumpService.jump2DeleteWarningDialog(CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType()) ? "Auto" : "CarPlay", cPAADevice.getName(), new b.a() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.5
                @Override // o2.b.a
                public void onOkResult(Object obj) {
                    CPAADevice unused = CarPlayPager.currentDevice = cPAADevice;
                    if (cPAADevice.isConnected() || cPAADevice.deviceStatus == 3) {
                        boolean unused2 = CarPlayPager.removeOp = true;
                        String unused3 = CarPlayPager.disconnectDeviceId = ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(cPAADevice);
                        g.a().d(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                boolean unused4 = CarPlayPager.removeOp = false;
                                x3.a.a(CarPlayPager.this.TAG, " connected, start delete:" + cPAADevice);
                                ((CarPlayAAPresenter) CarPlayPager.this.getP()).remove(cPAADevice);
                                c.c().k(new m2.a(230011));
                            }
                        }, BTDevice.CONNECTED_TIME_STANDARD);
                    } else {
                        x3.a.a(CarPlayPager.this.TAG, " start delete:" + cPAADevice);
                        boolean unused4 = CarPlayPager.removeOp = false;
                        ((CarPlayAAPresenter) CarPlayPager.this.getP()).remove(cPAADevice);
                    }
                    CarPlayPager.this.mDeviceList.remove(cPAADevice);
                    if (CarPlayPager.this.mDeviceList.size() == 0) {
                        CarPlayPager.this.noDateView.setVisibility(0);
                    } else {
                        CarPlayPager.this.noDateView.setVisibility(8);
                    }
                    Collections.sort(CarPlayPager.this.mDeviceList);
                    CarPlayPager.this.mAdapter.notifyDataSetChanged();
                    c.c().k(new m2.a(230011));
                }
            });
            return;
        }
        if (BtnType.BTN_3.equals(btnType)) {
            String strA = q2.b.a(this.mContext, NO_SHOW_MAC_LIST_CACHE);
            if (q2.c.b(strA) || ((cPAADevice.getBtMac() == null || !strA.contains(cPAADevice.getBtMac())) && (cPAADevice.getUsbSN() == null || !strA.contains(cPAADevice.getUsbSN())))) {
                this.mJumpService.jump2DisconnectWarningDialog(CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType()) ? "Android Auto" : "Apple CarPlay", new b.a() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.6
                    @Override // o2.b.a
                    public void onOkResult(Object obj) {
                        String strA2;
                        ((CarPlayAAPresenter) CarPlayPager.this.getP()).disconnect(cPAADevice);
                        if (!((Boolean) obj).booleanValue() || (strA2 = q2.b.a(CarPlayPager.this.mContext, CarPlayPager.NO_SHOW_MAC_LIST_CACHE)) == null) {
                            return;
                        }
                        if (strA2.contains(cPAADevice.getBtMac()) && strA2.contains(cPAADevice.getUsbSN())) {
                            return;
                        }
                        String usbSN = q2.c.b(cPAADevice.getBtMac()) ? cPAADevice.getUsbSN() : cPAADevice.getBtMac();
                        q2.b.b(CarPlayPager.this.mContext, CarPlayPager.NO_SHOW_MAC_LIST_CACHE, strA2 + " " + usbSN);
                    }
                });
            } else {
                ((CarPlayAAPresenter) getP()).disconnect(cPAADevice);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnItemClicked(CPAADevice cPAADevice) {
        x3.a.a(this.TAG, ", doOnItemClicked, device: " + cPAADevice.getName() + ", " + cPAADevice.getType() + ", status: " + cPAADevice.deviceStatus);
        int i8 = cPAADevice.deviceStatus;
        if (i8 == 4) {
            doDeviceConnected(cPAADevice);
            return;
        }
        if (i8 == 1 || i8 == 0) {
            doDeviceAvailable(cPAADevice);
            return;
        }
        if (i8 == 3) {
            if (cPAADevice.getType() == CarPlayType.ANDROID_AUTO) {
                ((CarPlayAAPresenter) getP()).connectAndroidAuto(cPAADevice);
                return;
            } else {
                if (cPAADevice.getType() == CarPlayType.CAR_PLAY) {
                    ((CarPlayAAPresenter) getP()).connectCarPlay(cPAADevice);
                    return;
                }
                return;
            }
        }
        if (i8 == 2) {
            d.c(getContext(), this.mContext.getString(R.string.device_disconnecting));
        } else if (i8 == -1) {
            d.c(getContext(), this.mContext.getString(R.string.device_not_available));
        }
    }

    private void enableConnectedDevice() {
        AndroidAutoDevice connectedAndroidAutoDevice = ((CarPlayAAPresenter) getP()).getConnectedAndroidAutoDevice();
        if (connectedAndroidAutoDevice == null || q2.c.b(connectedAndroidAutoDevice.getBtAddr())) {
            return;
        }
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        String btAddr = connectedAndroidAutoDevice.getBtAddr();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (btAddr.equals(bluetoothDevice.getAddress())) {
                BtSingleConnection.enableAndroidAutoHfp(bluetoothDevice, true);
                return;
            }
        }
    }

    private boolean isDeviceConnecting(CPAADevice cPAADevice) {
        String btMac = cPAADevice.getBtMac();
        boolean z7 = false;
        if (cPAADevice.getType() == CarPlayType.CAR_PLAY) {
            CarPlayDevice connectingCarPlayDevice = ((CarPlayAAPresenter) getP()).getConnectingCarPlayDevice();
            if (connectingCarPlayDevice != null && !q2.c.b(connectingCarPlayDevice.getBtAddr()) && connectingCarPlayDevice.getBtAddr().equals(btMac)) {
                return true;
            }
        } else {
            AndroidAutoDevice connectingAndroidAutoDevice = ((CarPlayAAPresenter) getP()).getConnectingAndroidAutoDevice();
            if (connectingAndroidAutoDevice != null && !q2.c.b(connectingAndroidAutoDevice.getBtAddr()) && connectingAndroidAutoDevice.getBtAddr().equals(btMac)) {
                z7 = true;
            }
            if (connectingAndroidAutoDevice != null && !q2.c.b(connectingAndroidAutoDevice.getUsbSerialNumber()) && connectingAndroidAutoDevice.getUsbSerialNumber().equals(cPAADevice.getUsbSN())) {
                return true;
            }
        }
        return z7;
    }

    private void showCPAAList() {
        List<CPAADevice> aACPDeviceList = ((CarPlayAAPresenter) getP()).getAACPDeviceList();
        x3.a.a(this.TAG, "----onResume----list = " + aACPDeviceList);
        this.mDeviceList.clear();
        if (aACPDeviceList != null && !aACPDeviceList.isEmpty()) {
            this.mDeviceList.addAll(aACPDeviceList);
        }
        if (this.mDeviceList.size() == 0) {
            this.noDateView.setVisibility(0);
        } else {
            this.noDateView.setVisibility(8);
        }
        Collections.sort(this.mDeviceList);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void androidAutoDeviceStatusChanged(int r8, ts.car.service.androidauto.data.AndroidAutoDevice r9) {
        /*
            r7 = this;
            r0 = -1
            r1 = 1
            if (r8 == r0) goto Lf
            if (r8 == 0) goto Lf
            if (r8 == r1) goto Lf
            r0 = 2
            if (r8 == r0) goto L53
            r0 = 4
            if (r8 == r0) goto L53
            goto L5a
        Lf:
            if (r9 == 0) goto L53
            java.lang.String r0 = r9.getBtAddr()
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L1f
            java.lang.String r0 = r9.getUsbSerialNumber()
        L1f:
            java.lang.String r2 = r7.TAG
            java.lang.String[] r1 = new java.lang.String[r1]
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "disconnectDeviceId:"
            r4.append(r5)
            java.lang.String r5 = com.byd.systemsettings.cpaa.CarPlayPager.disconnectDeviceId
            r4.append(r5)
            java.lang.String r5 = ",connectOp:"
            r4.append(r5)
            boolean r5 = com.byd.systemsettings.cpaa.CarPlayPager.connectOp
            r4.append(r5)
            java.lang.String r5 = ",removeOp:"
            r4.append(r5)
            boolean r5 = com.byd.systemsettings.cpaa.CarPlayPager.removeOp
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r1[r3] = r4
            x3.a.a(r2, r1)
            r7.connectOrRemoveAACP(r0)
        L53:
            com.byd.systemsettings.cpaa.CarPlayPager$DeviceStatusChangedListener r0 = r7.deviceStatusChangedListener
            if (r0 == 0) goto L5a
            r0.onDeviceStatusChanged()
        L5a:
            if (r9 == 0) goto L6e
            java.lang.String r3 = r9.getBtAddr()
            r4 = -1
            java.lang.String r5 = r9.getUsbSerialNumber()
            boolean r6 = r9.isUsbDevice()
            r1 = r7
            r2 = r8
            r1.flushDeviceState(r2, r3, r4, r5, r6)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.cpaa.CarPlayPager.androidAutoDeviceStatusChanged(int, ts.car.service.androidauto.data.AndroidAutoDevice):void");
    }

    public void androidAutoVideoFocusStatusChanged(boolean z7) {
        if (z7) {
            dismissAll();
        }
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000c, code lost:
    
        if (r8 != 4) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void carPlayDeviceStatusChanged(int r8, ts.car.service.carplay.data.CarPlayDevice r9) {
        /*
            r7 = this;
            r0 = -1
            r1 = 1
            if (r8 == r0) goto Lf
            if (r8 == 0) goto Lf
            if (r8 == r1) goto Lf
            r0 = 2
            if (r8 == r0) goto L54
            r0 = 4
            if (r8 == r0) goto L49
            goto L5b
        Lf:
            if (r9 == 0) goto L49
            java.lang.String r0 = r7.TAG
            java.lang.String[] r1 = new java.lang.String[r1]
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "disconnectDeviceId:"
            r3.append(r4)
            java.lang.String r4 = com.byd.systemsettings.cpaa.CarPlayPager.disconnectDeviceId
            r3.append(r4)
            java.lang.String r4 = ",connectOp:"
            r3.append(r4)
            boolean r4 = com.byd.systemsettings.cpaa.CarPlayPager.connectOp
            r3.append(r4)
            java.lang.String r4 = ",removeOp:"
            r3.append(r4)
            boolean r4 = com.byd.systemsettings.cpaa.CarPlayPager.removeOp
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1[r2] = r3
            x3.a.a(r0, r1)
            java.lang.String r0 = r9.getBtAddr()
            r7.connectOrRemoveAACP(r0)
        L49:
            java.lang.String r0 = r7.TAG
            java.lang.String r1 = "---carPlayDeviceStatusChanged----CP_AA_DEVICE_CONNECTED---"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            x3.a.a(r0, r1)
        L54:
            com.byd.systemsettings.cpaa.CarPlayPager$DeviceStatusChangedListener r0 = r7.deviceStatusChangedListener
            if (r0 == 0) goto L5b
            r0.onDeviceStatusChanged()
        L5b:
            if (r9 == 0) goto L72
            java.lang.String r3 = r9.getBtAddr()
            int r4 = r9.getWirelessAvailableState()
            java.lang.String r5 = r9.getUsbSerialNumber()
            boolean r6 = r9.isUsbDevice()
            r1 = r7
            r2 = r8
            r1.flushDeviceState(r2, r3, r4, r5, r6)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.cpaa.CarPlayPager.carPlayDeviceStatusChanged(int, ts.car.service.carplay.data.CarPlayDevice):void");
    }

    public void carPlayDisplayStatusChanged(boolean z7) {
        if (z7) {
            dismissAll();
        }
    }

    public void dismissAll() {
        if (connectOp || removeOp) {
            x3.a.a(this.TAG, "dismissAll-当前存在连接和删除操作,弹框2s后再消失");
            connectOp = false;
            removeOp = false;
            g.a().d(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.7
                @Override // java.lang.Runnable
                public void run() {
                    CarPlayPager.this.dismissAll();
                }
            }, BTDevice.CONNECTED_TIME_STANDARD);
            return;
        }
        x3.a.a(this.TAG, "---dismissAll---");
        z3.d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
    }

    public void flushDeviceState(int i8, final String str, int i9, final String str2, boolean z7) {
        List<CPAADevice> list = this.mDeviceList;
        if (list == null || list.size() == 0 || this.mRootView == null) {
            return;
        }
        for (CPAADevice cPAADevice : this.mDeviceList) {
            if ((!TextUtils.isEmpty(str) && str.equals(cPAADevice.getBtMac())) || (!q2.c.b(str2) && str2.equals(cPAADevice.getUsbSN()))) {
                cPAADevice.deviceStatus = i8;
                cPAADevice.mWirelessAvailableState = i9;
                cPAADevice.connectType = z7 ? 1 : 2;
                cPAADevice.setConnected(i8 == 4);
                cPAADevice.setHasBtSignal(((CarPlayAAPresenter) getP()).getDeviceBtSignal(cPAADevice));
                cPAADevice.setHasWiFiSignal(((CarPlayAAPresenter) getP()).getDeviceWifiSignal(cPAADevice));
                cPAADevice.setHasPhoneSignal(((CarPlayAAPresenter) getP()).getDevicePhoneSignal(cPAADevice));
                x3.a.a(this.TAG, "flushDeviceState, device:" + cPAADevice.getName() + ", " + cPAADevice.getType());
            }
        }
        Collections.sort(this.mDeviceList);
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.8
            @Override // java.lang.Runnable
            public void run() {
                if (CarPlayPager.this.mAdapter != null) {
                    CarPlayPager.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        g.a().d(new Runnable() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.9
            @Override // java.lang.Runnable
            public void run() {
                x3.a.a(CarPlayPager.this.TAG, "---flushDeviceStateDelay---");
                for (CPAADevice cPAADevice2 : CarPlayPager.this.mDeviceList) {
                    if ((!TextUtils.isEmpty(str) && str.equals(cPAADevice2.getBtMac())) || (!q2.c.b(str2) && str2.equals(cPAADevice2.getUsbSN()))) {
                        cPAADevice2.setHasBtSignal(((CarPlayAAPresenter) CarPlayPager.this.getP()).getDeviceBtSignal(cPAADevice2));
                    }
                }
                if (CarPlayPager.this.mAdapter != null) {
                    CarPlayPager.this.mAdapter.notifyDataSetChanged();
                }
            }
        }, 4000L);
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_car_play;
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
        ArrayList arrayList = new ArrayList();
        this.mDeviceList = arrayList;
        CarPlayListAdapter carPlayListAdapter = new CarPlayListAdapter(arrayList);
        this.mAdapter = carPlayListAdapter;
        this.rcv.setAdapter(carPlayListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rcv.setLayoutManager(linearLayoutManager);
        q2.f.a(this.rcv);
        this.rcv.setItemAnimator(new c1.c());
        this.mAdapter.setOnItemClickedListener(new d4.d() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.1
            @Override // d4.d
            public void onItemClicked(int i8) {
                x3.a.a(CarPlayPager.this.TAG, ", position = " + i8);
                CarPlayPager carPlayPager = CarPlayPager.this;
                carPlayPager.doOnItemClicked((CPAADevice) carPlayPager.mDeviceList.get(i8));
            }
        });
        this.mAdapter.setOnItemBtnClickedCallback(new d4.c() { // from class: com.byd.systemsettings.cpaa.CarPlayPager.2
            @Override // d4.c
            public void onItemBtnClicked(int i8, BtnType btnType) {
                x3.a.a(CarPlayPager.this.TAG, ", position = " + i8);
                CarPlayPager carPlayPager = CarPlayPager.this;
                carPlayPager.doOnItemBtClicked((CPAADevice) carPlayPager.mDeviceList.get(i8), btnType);
            }
        });
        this.mJumpService = o2.c.b().c();
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
        ((CarPlayAAPresenter) getP()).init(context);
        this.rcv = (RecyclerView) this.mRootView.findViewById(R.id.rcv_connected_device_list_car_play);
        this.titleParent = (RelativeLayout) this.mRootView.findViewById(R.id.rl_title_car_play);
        this.noDateView = this.mRootView.findViewById(R.id.ll_no_data_car_play);
        this.mRootView.findViewById(R.id.iv_back_car_play).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_add_new_device_car_play).setOnClickListener(this);
        this.mRootView.findViewById(R.id.iv_cp_tips).setOnClickListener(this);
        c.c().o(this);
    }

    @Override // z3.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back_car_play) {
            z3.d dVar = this.mCallback;
            if (dVar != null) {
                dVar.onBackPress();
            }
            finish();
            return;
        }
        if (id == R.id.btn_add_new_device_car_play) {
            SystemDialog.showBtSettings(this.mContext);
            connectOp = false;
            removeOp = false;
        } else if (id == R.id.iv_cp_tips) {
            this.mJumpService.jump2DeviceTipsDialog();
        }
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        this.deviceStatusChangedListener = null;
        c.c().q(this);
        x3.a.a(this.TAG, "onDestroy");
        SystemDialog.sendBroadcastConnectDeviceDialogShow(SystemDialog.getContext(), false);
        super.onDestroy();
        connectOp = false;
        removeOp = false;
        disconnectDeviceId = "-1";
        enableConnectedDevice();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onGetMessage(m2.a aVar) {
        if (aVar.a() == 230008) {
            x3.a.a(this.TAG, "service connected");
            if (this.mDeviceList.isEmpty()) {
                showCPAAList();
                return;
            }
            return;
        }
        if (aVar.a() == 230010 || aVar.a() == 230011) {
            x3.a.a(this.TAG, "蓝牙设备移除,CPAA列表刷新");
            showCPAAList();
        }
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        super.onHandlerUpdateUI(message);
        if (message.arg1 == 1) {
            x3.a.a(this.TAG, "list_update_msg");
            showCPAAList();
        }
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        super.onLtrRudderR(z7);
        setViewGroupRtl(this.titleParent, true);
    }

    @Override // z3.a
    public void onResume() {
        super.onResume();
        showCPAAList();
        enableConnectedDevice();
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        setViewGroupRtl(this.titleParent, false);
    }

    public void setDeviceStatusChanged(DeviceStatusChangedListener deviceStatusChangedListener) {
        this.deviceStatusChangedListener = deviceStatusChangedListener;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.g
    public CarPlayAAPresenter newP() {
        return new CarPlayAAPresenter();
    }
}
