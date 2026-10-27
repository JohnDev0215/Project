package com.byd.systemsettings.btsetting.view.pager;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothPan;
import android.content.Context;
import android.content.res.Resources;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import b3.i;
import c3.a;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.BondDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.ConnectedBtDevice;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import com.byd.systemsettings.btsetting.BtConnectedPresenter;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.systemsettings.btsetting.model.ProfileParams;
import java.lang.ref.WeakReference;
import v2.d;
import y2.b;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtConnectedPager extends a<BtConnectedPresenter> implements CompoundButton.OnCheckedChangeListener {
    public static final int ARG1_CONTACTS_SYNC_STATE = 81;
    public static final int ARG1_PROFILE_SATE = 80;
    private BluetoothA2dpSink a2dp;
    private volatile int btContactsSyncState;
    private BtCallContactsCallback contactsCallback;
    private ConnectedBtDevice device;
    private BluetoothHeadsetClient hfp;
    private volatile boolean isDestroyed;
    private LinearLayout mediaParent;
    private LinearLayout mediaTitleParent;
    private LinearLayout operationBottom;
    private BluetoothPan pan;
    private LinearLayout panParent;
    private LinearLayout panTitleParent;
    private LinearLayout phoneParent;
    private LinearLayout phoneTitleParent;
    private volatile BtProfileConnectCallback profileStateCallback;
    private StringBuffer sb;
    private volatile boolean showContactsHint;
    private BydSwitch switchMedia;
    private BydSwitch switchNetShare;
    private BydSwitch switchPhone;
    private TextView tvConnectedName;
    private TextView tvNetShare;
    private TextView tvNoContacts;
    private TextView tvPhone;

    public class BtCallContactsCallback implements d {
        private final WeakReference<BtConnectedPager> ref;

        public BtCallContactsCallback(BtConnectedPager btConnectedPager) {
            this.ref = new WeakReference<>(btConnectedPager);
        }

        @Override // v2.d
        public void SyncContactsState(int i8) {
            x3.a.a(BtConnectedPager.this.TAG, "SyncContactsState", ", ----SyncContactsState-----i: " + i8);
            WeakReference<BtConnectedPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().isDestroyed) {
                return;
            }
            this.ref.get().sendUIMessage(81, Integer.valueOf(i8));
        }
    }

    public class BtProfileConnectCallback implements i {
        private final WeakReference<BtConnectedPager> ref;

        public BtProfileConnectCallback(BtConnectedPager btConnectedPager) {
            this.ref = new WeakReference<>(btConnectedPager);
        }

        @Override // b3.i
        public void onConnectedChanged(int i8, BluetoothDevice bluetoothDevice, ConnectState connectState) {
            WeakReference<BtConnectedPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().sendUIMessage(80, new ProfileParams(i8, bluetoothDevice, connectState));
        }

        @Override // b3.i
        public /* bridge */ /* synthetic */ void onServiceConnected(int i8) {
            super.onServiceConnected(i8);
        }

        @Override // b3.i
        public /* bridge */ /* synthetic */ void onServiceDisconnected(int i8) {
            super.onServiceDisconnected(i8);
        }
    }

    public class SpanClickListener extends ClickableSpan {
        private int color;
        private final WeakReference<BtConnectedPager> ref;

        public SpanClickListener(BtConnectedPager btConnectedPager, int i8) {
            this.color = -1;
            this.ref = new WeakReference<>(btConnectedPager);
            this.color = i8;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            x3.a.a(BtConnectedPager.this.TAG, ", SpanClickListener---onClick--");
            WeakReference<BtConnectedPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onSpanClicked(view);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            int i8 = this.color;
            if (i8 != -1) {
                textPaint.setColor(i8);
            } else {
                textPaint.setColor(textPaint.linkColor);
            }
            textPaint.setUnderlineText(false);
        }
    }

    public BtConnectedPager(Context context) {
        super(context);
        this.showContactsHint = false;
        this.isDestroyed = false;
        this.btContactsSyncState = -99;
    }

    private void onBtContactsSyncStateChanged(int i8) {
        x3.a.a(this.TAG, "onBtContactsSyncStateChanged", ", state = " + i8);
        Context context = getContext();
        if (this.btContactsSyncState == i8 || context == null || this.isDestroyed) {
            return;
        }
        this.btContactsSyncState = i8;
        this.showContactsHint = i8 == 3;
        updateContactsHintUI();
    }

    private void onDataChanged() {
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null) {
            return;
        }
        BluetoothDevice device = this.device.getDevice().getDevice();
        String str = this.TAG;
        String[] strArr = new String[2];
        strArr[0] = "onDataChanged";
        StringBuilder sb = new StringBuilder();
        sb.append(", bd: ");
        sb.append(device == null ? "null" : m4.d.f(device.getName()));
        strArr[1] = sb.toString();
        x3.a.a(str, strArr);
        updatePhoneInfo(device);
        updateMediaInfo(device);
        updateNetShareInfo(device);
    }

    private void onMediaAudioOperate(boolean z7) {
        x3.a.a(this.TAG, "onMediaAudioOperate", ", isChecked = " + z7);
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null || this.device.getDevice().getDevice() == null) {
            return;
        }
        BluetoothDevice device = this.device.getDevice().getDevice();
        x3.a.a(this.TAG, "onMediaAudioOperate", ", a2dp: " + this.a2dp + ", d: " + device.getName());
        BluetoothA2dpSink bluetoothA2dpSink = this.a2dp;
        if (bluetoothA2dpSink != null) {
            if (z7) {
                bluetoothA2dpSink.connect(device);
            } else {
                bluetoothA2dpSink.disconnect(device);
            }
            this.switchMedia.setInvalid(true);
        }
        updateMediaInfo(device);
    }

    private void onNetworkShareOperate(boolean z7) {
        x3.a.a(this.TAG, "onNetworkShareOperate", ", isChecked = " + z7);
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null || this.device.getDevice().getDevice() == null) {
        }
    }

    private void onPhoneAudioOperate(boolean z7) {
        x3.a.a(this.TAG, "onPhoneAudioOperate", ", isChecked = " + z7);
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null || this.device.getDevice().getDevice() == null) {
            return;
        }
        BluetoothDevice device = this.device.getDevice().getDevice();
        x3.a.a(this.TAG, "onPhoneAudioOperate", ", hfp: " + this.hfp + ", d: " + device.getName());
        BluetoothHeadsetClient bluetoothHeadsetClient = this.hfp;
        if (bluetoothHeadsetClient != null) {
            if (z7) {
                bluetoothHeadsetClient.setPriority2(device, 100);
                this.hfp.connect(device);
                x3.a.a(this.TAG, "onPhoneAudioOperate", "先解禁在连接hfp");
            } else {
                bluetoothHeadsetClient.disconnect(device);
                BtSingleConnection.disconnectAndroidAutoHfp(device);
            }
            this.switchPhone.setInvalid(true);
        }
        updatePhoneInfo(device);
    }

    private void onProfileConnectedChanged(ProfileParams profileParams) {
        BluetoothDevice bluetoothDevice = profileParams.device;
        x3.a.a(this.TAG, "onProfileConnectedChanged", ", params: " + profileParams);
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null || this.device.getDevice().getDevice() == null || bluetoothDevice == null || bluetoothDevice.getAddress() == null) {
            return;
        }
        BluetoothDevice device = this.device.getDevice().getDevice();
        if (bluetoothDevice.getAddress().equals(device.getAddress())) {
            x3.a.a(this.TAG, "onProfileConnectedChanged", ", check profile state, dd: " + m4.d.c(device));
            int i8 = profileParams.profile;
            if (i8 == 5) {
                updateNetShareInfo(device);
                updateSwitchState(this.switchNetShare, profileParams.state);
            } else if (i8 == 11) {
                updateMediaInfo(device);
                updateSwitchState(this.switchMedia, profileParams.state);
            } else {
                if (i8 != 16) {
                    return;
                }
                updatePhoneInfo(device);
                updateContactsHintUI();
                updateSwitchState(this.switchPhone, profileParams.state);
            }
        }
    }

    private void registerProfileStateCallback() {
        if (this.profileStateCallback == null) {
            this.profileStateCallback = new BtProfileConnectCallback(this);
        }
        b.o.a().z0(getClass().getName(), this.profileStateCallback);
    }

    private void unregisterProfileStateCallback() {
        b.o.a().T0(getClass().getName());
        this.profileStateCallback = null;
    }

    private void updateContactsHintUI() {
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null) {
            return;
        }
        BondDevice device = this.device.getDevice();
        c3.a aVarA = a.c.a();
        x3.a.a(this.TAG, "updateContactsHintUI", ", isHfpProfileConnected: " + aVarA.o(device.getDevice()) + ", showContactsHint: " + this.showContactsHint);
        boolean z7 = aVarA.o(device.getDevice()) && this.showContactsHint;
        TextView textView = this.tvNoContacts;
        if (textView != null) {
            textView.setVisibility(z7 ? 0 : 4);
            this.tvNoContacts.setSingleLine(!z7);
        }
    }

    private void updateMediaInfo(BluetoothDevice bluetoothDevice) {
        boolean zN = a.c.a().n(bluetoothDevice);
        x3.a.a(this.TAG, "updateMediaInfo", ", mediaConnected = " + zN);
        this.switchMedia.b(zN, false);
    }

    private void updateNetShareInfo(BluetoothDevice bluetoothDevice) {
        boolean zQ = a.c.a().q(bluetoothDevice);
        this.switchNetShare.b(zQ, false);
        x3.a.a(this.TAG, "updateNetShareInfo", ", netConnected = " + zQ);
        Resources resources = this.tvNetShare.getResources();
        this.tvNetShare.setText(zQ ? resources.getString(R.string.net_share_description_bt) : resources.getString(R.string.net_share_description_bt_disconected));
    }

    private void updatePhoneInfo(BluetoothDevice bluetoothDevice) {
        boolean zO = a.c.a().o(bluetoothDevice);
        this.switchPhone.b(zO, false);
        x3.a.a(this.TAG, "updatePhoneInfo", ", phoneConnected = " + zO);
        Resources resources = this.tvPhone.getResources();
        this.tvPhone.setText(zO ? resources.getString(R.string.phone_audio_description_bt) : resources.getString(R.string.phone_audio_description_bt_disconected));
    }

    private void updateSwitchState(BydSwitch bydSwitch, ConnectState connectState) {
        if (bydSwitch != null) {
            if (connectState == ConnectState.CONNECTED || connectState == ConnectState.DISCONNECTED) {
                bydSwitch.setInvalid(false);
            }
        }
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.e
    public BydData getCurData() {
        return this.device;
    }

    public ConnectedBtDevice getDevice() {
        return this.device;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_bt_conneted;
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
        String string = context.getString(R.string.tips_bt_settings);
        String string2 = context.getString(R.string.send_bt_request);
        int iIndexOf = string.indexOf(string2);
        int length = string2.length() + iIndexOf;
        SpannableString spannableString = new SpannableString(string);
        int color = context.getColor(R.color.text_color_btn_blue);
        int color2 = context.getColor(R.color.dialog_button_bg_blue_endcolor_press);
        if (iIndexOf >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(color), iIndexOf, length, 33);
            spannableString.setSpan(new SpanClickListener(this, color2), iIndexOf, length, 33);
        }
        this.tvNoContacts.setText(spannableString);
        this.tvNoContacts.setHighlightColor(0);
        this.tvNoContacts.setMovementMethod(LinkMovementMethod.getInstance());
        c3.a aVarA = a.c.a();
        this.hfp = aVarA.l(16);
        this.a2dp = aVarA.l(11);
        this.pan = aVarA.l(5);
        if (this.contactsCallback == null) {
            this.contactsCallback = new BtCallContactsCallback(this);
        }
        w2.a.p().s(this.contactsCallback);
        registerProfileStateCallback();
    }

    @Override // z3.e
    public boolean initHandler() {
        return true;
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.isDestroyed = false;
        this.tvConnectedName = (TextView) this.mRootView.findViewById(R.id.tv_setting_bt_name_bt_connected);
        this.tvNoContacts = (TextView) this.mRootView.findViewById(R.id.tv_tips_no_contacts_bt_connected);
        this.phoneParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_phone_audio_bt_connected);
        this.mediaParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_media_bt_connected);
        this.panParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_net_share_bt_connected);
        this.phoneTitleParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_title_phone_audio_name_bt_connected);
        this.mediaTitleParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_title_media_name_bt_connected);
        this.panTitleParent = (LinearLayout) this.mRootView.findViewById(R.id.ll_title_net_share_name_bt_connected);
        this.switchPhone = (BydSwitch) this.mRootView.findViewById(R.id.switch_phone_audio_bt_connected);
        this.switchMedia = (BydSwitch) this.mRootView.findViewById(R.id.switch_media_bt_connected);
        this.switchNetShare = (BydSwitch) this.mRootView.findViewById(R.id.switch_net_share_bt_connected);
        this.tvPhone = (TextView) this.mRootView.findViewById(R.id.tv_phone_audio_description_bt_connected);
        this.tvNetShare = (TextView) this.mRootView.findViewById(R.id.tv_net_share_description_bt_connected);
        this.operationBottom = (LinearLayout) this.mRootView.findViewById(R.id.ll_operation_bottom_bt_connected);
        this.mRootView.findViewById(R.id.btn_disconnect_bt_connected).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_remove_bt_connected).setOnClickListener(this);
        this.switchPhone.setOnCheckedChangeListener(this);
        this.switchMedia.setOnCheckedChangeListener(this);
        this.panParent.setVisibility(8);
        boolean zHasTextOverView = hasTextOverView(this.operationBottom);
        x3.a.a(this.TAG, "----initViews---hasOver: " + zHasTextOverView);
        if (zHasTextOverView) {
            changeLayoutToVertical(this.operationBottom);
        }
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
        int id = compoundButton.getId();
        if (id == R.id.switch_phone_audio_bt_connected) {
            onPhoneAudioOperate(z7);
        } else if (id == R.id.switch_media_bt_connected) {
            onMediaAudioOperate(z7);
        } else if (id == R.id.switch_net_share_bt_connected) {
            onNetworkShareOperate(z7);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mBtnCallback == null) {
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_disconnect_bt_connected) {
            this.mBtnCallback.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.device, null);
        } else if (id == R.id.btn_remove_bt_connected) {
            this.mBtnCallback.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, this.device, null);
        }
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        super.onDestroy();
        unregisterProfileStateCallback();
        this.isDestroyed = true;
        if (this.contactsCallback != null) {
            w2.a.p().u(this.contactsCallback);
        }
        this.contactsCallback = null;
        this.hfp = null;
        this.a2dp = null;
        this.pan = null;
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        x3.a.a(this.TAG, "---onHandlerUpdateUI--- msg: " + message.arg1);
        int i8 = message.arg1;
        if (i8 == 80) {
            onProfileConnectedChanged((ProfileParams) message.obj);
        } else {
            if (i8 != 81) {
                return;
            }
            onBtContactsSyncStateChanged(((Integer) message.obj).intValue());
        }
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        x3.a.a(this.TAG, "-----onLtrRudderR-----");
        setViewGroupRtl(this.operationBottom, true);
        setViewGroupRtl(this.phoneParent, true);
        setViewGroupRtl(this.mediaParent, true);
        setViewGroupRtl(this.panParent, true);
        setViewGroupRtl(this.phoneTitleParent, false);
        setViewGroupRtl(this.mediaTitleParent, false);
        setViewGroupRtl(this.panTitleParent, false);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        x3.a.a(this.TAG, "-----onRtlRudderL-----");
        setViewGroupRtl(this.phoneParent, false);
        setViewGroupRtl(this.mediaParent, false);
        setViewGroupRtl(this.panParent, false);
        setViewGroupRtl(this.phoneTitleParent, true);
        setViewGroupRtl(this.mediaTitleParent, true);
        setViewGroupRtl(this.panTitleParent, true);
    }

    public void onSpanClicked(View view) {
        if (view.getId() == R.id.tv_tips_no_contacts_bt_connected) {
            x3.a.a(this.TAG, "onSpanClicked", ", no_contacts_bt_connected--");
            w2.a.p().b();
        }
    }

    public void setDevice(ConnectedBtDevice connectedBtDevice) {
        this.device = connectedBtDevice;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        super.updateData(bydData);
        if (bydData == null) {
            return;
        }
        this.device = (ConnectedBtDevice) bydData;
        x3.a.a(this.TAG, "---updateData---device: " + this.device);
        ConnectedBtDevice connectedBtDevice = this.device;
        if (connectedBtDevice == null || connectedBtDevice.getDevice() == null) {
            return;
        }
        BondDevice device = this.device.getDevice();
        Context context = getContext();
        if (context != null) {
            this.tvConnectedName.setText(String.format(context.getString(R.string.setting_bt_name_bt), device.getName()));
        }
        ((BtConnectedPresenter) getP()).syncContactsState(81);
        onDataChanged();
    }

    @Override // z3.g
    public BtConnectedPresenter newP() {
        return new BtConnectedPresenter();
    }
}
