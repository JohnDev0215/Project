package com.byd.carsettings.ui.fragment.dilink;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import b.b.g.d.m;
import b.b.g.j.b;
import b.b.g.n.q.p;
import b.b.g.r.k;
import b.b.g.r.x;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.BTDevice;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.carplay.SystemPanelChangeReceiver;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.LinearItem;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.mvp.XFragment;
import com.byd.systemsettings.system.PrivacyPolicyDialog;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class LinkFragment extends XFragment<b.b.g.n.k.e> implements View.OnClickListener {
    public b.b.g.n.k.e A;
    public volatile boolean B = false;
    public ViewGroup m;
    public BydSwitch n;
    public ScrollView o;
    public LinearItem p;
    public LinearItem q;
    public LinearItem r;
    public LinearItem s;
    public View t;
    public ViewGroup u;
    public TextView v;
    public LinearItem w;
    public int x;
    public g y;
    public b.b.f.d z;

    public class a implements CompoundButton.OnCheckedChangeListener {
        public a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            b.b.m.c.a.a(LinkFragment.this.f3715a, "initData", ", ---onCheckedChanged--isChecked: " + z + ", mHandler: " + LinkFragment.this.y);
            ((b.b.g.n.k.e) LinkFragment.this.mo7getP()).O(LinkFragment.this.getContext(), z);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            LinkFragment linkFragment = LinkFragment.this;
            linkFragment.l0(((b.b.g.n.k.e) linkFragment.mo7getP()).L(), ((b.b.g.n.k.e) LinkFragment.this.mo7getP()).J());
        }
    }

    public class c implements Runnable {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            BluetoothDevice bluetoothDeviceG = ((b.b.g.n.k.e) LinkFragment.this.mo7getP()).G();
            if (bluetoothDeviceG != null) {
                String strG = x.g(bluetoothDeviceG.getName());
                LinkFragment linkFragment = LinkFragment.this;
                linkFragment.m0(linkFragment.p, ((b.b.g.n.k.e) LinkFragment.this.mo7getP()).K(), true, strG);
            } else {
                FragmentActivity activity = LinkFragment.this.getActivity();
                if (activity != null) {
                    LinkFragment linkFragment2 = LinkFragment.this;
                    linkFragment2.m0(linkFragment2.p, ((b.b.g.n.k.e) LinkFragment.this.mo7getP()).K(), false, activity.getString(R.string.state_open_not_connected_link));
                }
            }
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LinearItem f3148a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f3149b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f3150c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ String f3151d;

        public d(LinearItem linearItem, boolean z, boolean z2, String str) {
            this.f3148a = linearItem;
            this.f3149b = z;
            this.f3150c = z2;
            this.f3151d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkFragment.this.m0(this.f3148a, this.f3149b, this.f3150c, this.f3151d);
        }
    }

    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3153a;

        public e(boolean z) {
            this.f3153a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkFragment.this.n.setChecked(this.f3153a, false);
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LinkFragment linkFragment = LinkFragment.this;
            linkFragment.R(linkFragment.getActivity());
        }
    }

    public static class g extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<LinkFragment> f3156a;

        public /* synthetic */ g(LinkFragment linkFragment, a aVar) {
            this(linkFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<LinkFragment> weakReference = this.f3156a;
            if (weakReference == null || weakReference.get() == null || message.what != 545) {
                return;
            }
            this.f3156a.get().p0(message);
        }

        public g(LinkFragment linkFragment) {
            this.f3156a = new WeakReference<>(linkFragment);
        }
    }

    public static class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static long f3157a;

        public static boolean a(Context context) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z = new BigDecimal(jCurrentTimeMillis).subtract(new BigDecimal(f3157a)).compareTo(new BigDecimal(300)) == 1;
            f3157a = jCurrentTimeMillis;
            return z;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ((b.b.g.n.k.e) mo7getP()).init(context);
        b.b.m.c.a.a(this.f3715a, "---initViews---");
        this.m = (ViewGroup) this.f3717c.findViewById(R.id.link_parent);
        this.o = (ScrollView) this.f3717c.findViewById(R.id.scv_link_pager);
        this.p = (LinearItem) this.f3717c.findViewById(R.id.item_bt_link);
        this.q = (LinearItem) this.f3717c.findViewById(R.id.item_wireless_net_link);
        this.r = (LinearItem) this.f3717c.findViewById(R.id.item_hotspot_link);
        this.s = (LinearItem) this.f3717c.findViewById(R.id.item_carplay_link);
        this.n = (BydSwitch) this.f3717c.findViewById(R.id.switch_mobile_data_link);
        this.t = this.f3717c.findViewById(R.id.ll_mobile_data_link);
        this.u = (ViewGroup) this.f3717c.findViewById(R.id.ll_title_mobile_data_link);
        TextView textView = (TextView) this.f3717c.findViewById(R.id.tv_title_mobile_data_link);
        this.v = textView;
        textView.setOnClickListener(this);
        this.w = (LinearItem) this.f3717c.findViewById(R.id.item_remote_control);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.w.setOnClickListener(this);
        p.c().e(context);
        EventBus.getDefault().register(this);
        this.p.setLogCipher(true);
        this.q.setLogCipher(true);
        boolean zA = m.a(b.b.f.b.f651a);
        boolean zA2 = m.a(b.b.f.b.f653c);
        b.b.m.c.a.a(this.f3715a, "isSupportCP: " + zA + ",isSupportAA:" + zA2);
        if (zA || zA2) {
            this.s.setVisibility(0);
            b.C0048b.a().F(b.b.g.m.b.a(0, 3));
        } else {
            this.s.setVisibility(8);
            b.C0048b.a().k(b.b.g.m.b.a(0, 3));
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void D(boolean z) {
        super.D(z);
    }

    public final void Q() {
        EventBus.getDefault().unregister(this);
        this.p.setOnClickListener(null);
        this.q.setOnClickListener(null);
        this.r.setOnClickListener(null);
        this.n.setOnCheckedChangeListener(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void R(Context context) {
        this.x = ((b.b.g.n.k.e) mo7getP()).I(context);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.w.setVisibility(0);
        int i = SystemProperties.getInt(PrivacyPolicyDialog.SYS_PRIVACY_STATUS, 3);
        int i2 = this.x;
        if (i2 != 0 && i != 1) {
            this.w.setRightText("");
            return;
        }
        if (i2 == 0) {
            this.w.setVisibility(8);
            return;
        }
        if (i2 == 1 || i2 == 2) {
            this.w.setRightText(activity.getString(R.string.remote_control_not_bind));
        } else {
            if (i2 != 3) {
                return;
            }
            this.w.setRightText(activity.getString(R.string.remote_control_bind_ok));
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.k.e newP() {
        b.b.g.n.k.e eVar = new b.b.g.n.k.e();
        this.A = eVar;
        return eVar;
    }

    public void T(boolean z) {
        l0(false, z);
    }

    public void U() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new f());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void V(BTDevice bTDevice) {
        b.b.m.c.a.a(this.f3715a, "----onBtConnectNameChanged--device: " + bTDevice);
        if (bTDevice == null || !bTDevice.isConnected()) {
            return;
        }
        n0(this.p, ((b.b.g.n.k.e) mo7getP()).K(), true, x.g(bTDevice.getName()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void W(boolean z, String str, boolean z2) {
        b.b.m.c.a.a(this.f3715a, "----onBtConnectSateChanged--isConnected = " + z + ",device:" + b.b.m.g.f.g(str));
        BluetoothDevice bluetoothDeviceG = ((b.b.g.n.k.e) mo7getP()).G();
        b.b.m.c.a.a(this.f3715a, "----onBtConnectSateChanged--d = " + b.b.m.g.f.d(bluetoothDeviceG));
        if (z2 && bluetoothDeviceG != null) {
            str = bluetoothDeviceG.getName();
            z = true;
        }
        o0(z, str);
    }

    public final void X() {
        b.b.m.c.a.a(this.f3715a, "----onBtOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        SystemPanelChangeReceiver.f2564a = 0;
        b.b.i.d.y(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void Y(int i, BluetoothDevice bluetoothDevice, ConnectState connectState) {
        b.b.m.c.a.a(this.f3715a, "----onBtProfileConnectSateChanged--profile: " + i + ",state: " + connectState + ", device: " + b.b.m.g.f.d(bluetoothDevice));
        if (connectState == ConnectState.CONNECTED || connectState == ConnectState.DISCONNECTED) {
            BluetoothDevice bluetoothDeviceG = ((b.b.g.n.k.e) mo7getP()).G();
            b.b.m.c.a.a(this.f3715a, "---onBtProfileConnectSateChanged---d = " + b.b.m.g.f.d(bluetoothDeviceG));
            if (bluetoothDeviceG != null) {
                o0(true, bluetoothDeviceG.getName());
            }
        }
    }

    public void Z(boolean z) {
        b.b.m.c.a.a(this.f3715a, "----onBtStateChanged--isEnable = " + z);
        n0(this.p, z, false, "");
    }

    public void a0(boolean z) {
        l0(z, false);
    }

    public final void b0() {
        b.b.m.c.a.a(this.f3715a, "----onCarPlayOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        SystemPanelChangeReceiver.f2564a = 0;
        b.b.i.d.B(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c0(ConnectState connectState, int i) {
        Resources resources;
        b.b.m.c.a.a(this.f3715a, "----onHotspotConnectStateChanged--state = " + connectState + ", count = " + i);
        if ((connectState == ConnectState.CONNECTED || connectState == ConnectState.DISCONNECTED) && (resources = this.r.getResources()) != null) {
            n0(this.r, ((b.b.g.n.k.e) mo7getP()).isHotspotEnable(), i > 0, String.format(resources.getString(R.string.state_connect_link), Integer.valueOf(i)));
        }
    }

    public final void d0() {
        b.b.m.c.a.a(this.f3715a, "----onHotspotOperate---");
        FragmentActivity activity = getActivity();
        if (activity != null && h.a(getContext())) {
            b.b.i.d.D(activity);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e0(EnableState enableState) {
        b.b.m.c.a.a(this.f3715a, "----onHotspotStateChanged--state: " + enableState);
        EnableState enableState2 = EnableState.ENABLE;
        if (enableState == enableState2 || enableState == EnableState.DISABLE) {
            boolean z = enableState == enableState2;
            int hotspotConnectedCount = ((b.b.g.n.k.e) mo7getP()).getHotspotConnectedCount();
            b.b.m.c.a.a(this.f3715a, "---onHotspotStateChanged---connectedCount: " + hotspotConnectedCount);
            Resources resources = this.r.getResources();
            if (resources == null) {
                return;
            }
            n0(this.r, z, hotspotConnectedCount > 0, String.format(resources.getString(R.string.state_connect_link), Integer.valueOf(hotspotConnectedCount)));
        }
    }

    public void f0(String str) {
        b.b.m.c.a.a(this.f3715a, "----onIccidChanged---:" + b.b.m.g.f.g(str));
        if (TextUtils.isEmpty(str)) {
            this.t.setVisibility(8);
            b.C0048b.a().k(b.b.g.m.b.a(0, 4));
        } else {
            this.t.setVisibility(0);
            b.C0048b.a().F(b.b.g.m.b.a(0, 4));
        }
    }

    public void g0(boolean z) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new e(z));
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_link;
    }

    public final void h0() {
        b.b.m.c.a.a(this.f3715a, "----onRemoteControlOperate---");
        int i = SystemProperties.getInt(PrivacyPolicyDialog.SYS_PRIVACY_STATUS, 3);
        CarModel$Brand carModel$Brand = k.f1817c;
        String string = getString(R.string.nfc_brand_byd);
        if (carModel$Brand == CarModel$Brand.DENZA) {
            string = getString(R.string.nfc_brand_denz);
        }
        if (this.x != 0 && i != 1) {
            SimpleDialog simpleDialogZ = SimpleDialog.z(getResources().getString(R.string.dialog_title_hint), getResources().getString(R.string.privacy_not_allow_tips_2, string), getResources().getString(R.string.dialog_btn_ok), "", 0);
            if (getActivity() != null) {
                simpleDialogZ.show(getActivity().getSupportFragmentManager(), "SimpleDialog");
                return;
            }
            return;
        }
        b.b.m.c.a.a(this.f3715a, "----starUeService---");
        Intent intent = new Intent("android.intent.action.CAR_SETTINGS_REGISTER_REQUEST");
        intent.setPackage("com.byd.uereport");
        if (getActivity() != null) {
            getActivity().sendBroadcast(intent);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i0(ConnectState connectState, NetworkInfo networkInfo) {
        b.b.m.c.a.a(this.f3715a, "----onWifiConnectStateChanged--state: " + connectState + ", info: " + networkInfo);
        ConnectState connectState2 = ConnectState.CONNECTED;
        if (connectState == connectState2 || connectState == ConnectState.DISCONNECTED) {
            n0(this.q, ((b.b.g.n.k.e) mo7getP()).isWifiEnabled(), connectState == connectState2, x.k(((b.b.g.n.k.e) mo7getP()).F()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void j0(EnableState enableState) {
        b.b.m.c.a.a(this.f3715a, "----onWifiStateChanged--state = " + enableState);
        EnableState enableState2 = EnableState.ENABLE;
        if (enableState == enableState2 || enableState == EnableState.DISABLE) {
            n0(this.q, enableState == enableState2, ((b.b.g.n.k.e) mo7getP()).N(), x.k(((b.b.g.n.k.e) mo7getP()).F()));
        }
    }

    public final void k0() {
        b.b.m.c.a.a(this.f3715a, "----onWirelessOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        b.b.i.d.H(activity);
    }

    public final void l0(boolean z, boolean z2) {
        b.b.m.c.a.a(this.f3715a, "isCarPlayConnected:" + z + ",isAndroidAutoConnected:" + z2);
        boolean z3 = z | z2;
        String str = z ? "Apple CarPlay" : "";
        if (z2) {
            str = "Android Auto";
        }
        if (z && z2) {
            str = "Apple CarPlay" + ListUtils.DEFAULT_JOIN_SEPARATOR + "Android Auto";
        }
        m0(this.s, true, z3, str);
    }

    public final void m0(LinearItem linearItem, boolean z, boolean z2, String str) {
        if (linearItem == null || linearItem.getResources() == null) {
            return;
        }
        Resources resources = linearItem.getResources();
        String string = resources.getString(R.string.state_close_link);
        if (!z) {
            str = string;
        } else if (!z2) {
            str = resources.getString(R.string.state_open_not_connected_link);
        }
        if (b.b.a.i.a.d()) {
            linearItem.getTvRight().setGravity(19);
        } else {
            linearItem.getTvRight().setGravity(21);
        }
        if (str == null) {
            str = "";
        }
        linearItem.setRightText(str);
    }

    public final void n0(LinearItem linearItem, boolean z, boolean z2, String str) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new d(linearItem, z, z2, str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o0(boolean z, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strG = x.g(str);
        b.b.m.c.a.a(this.f3715a, "----updateBtItem--isConnected = " + z + ",name:" + b.b.m.g.f.g(strG));
        n0(this.p, ((b.b.g.n.k.e) mo7getP()).K(), z, strG);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f3719e) {
        }
        switch (view.getId()) {
            case R.id.item_bt_link /* 2131297114 */:
                X();
                break;
            case R.id.item_carplay_link /* 2131297115 */:
                b0();
                break;
            case R.id.item_hotspot_link /* 2131297127 */:
                d0();
                break;
            case R.id.item_remote_control /* 2131297136 */:
                h0();
                break;
            case R.id.item_wireless_net_link /* 2131297165 */:
                k0();
                break;
            case R.id.tv_title_mobile_data_link /* 2131298799 */:
                if (b.b.m.g.c.b(view, 5000L, 10)) {
                    b.b.m.g.g.g(getActivity(), "byd_pad://secretcode:9526/AutomaticallyActivity");
                    break;
                }
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.b.m.c.a.a(this.f3715a, "---onDestroyView---");
        b.b.m.f.d.d.c(getClass().getName());
        Q();
        g gVar = this.y;
        if (gVar != null) {
            gVar.removeCallbacksAndMessages(null);
        }
        ((ImageView) this.f3717c.findViewById(R.id.iv_link)).setImageDrawable(null);
        b.b.f.d dVar = this.z;
        if (dVar != null) {
            dVar.M(null);
        }
        this.A.P();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 230008) {
            b.b.m.c.a.a(this.f3715a, "service connected");
            b.b.a.f.h.a().g(new b(), 500L);
        } else if (aVar.h() == 230009) {
            b.b.a.f.h.a().g(new c(), 500L);
        } else if (aVar.h() == 230012) {
            f0(((b.b.g.n.k.e) mo7getP()).H());
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, true);
            setViewGroupRtl(this.p, false);
            setViewGroupRtl(this.q, false);
            setViewGroupRtl(this.r, false);
            setViewGroupRtl(this.s, false);
            setViewGroupRtl(this.w, false);
        }
        b.b.m.c.a.a(this.f3715a, "---onLtrRudderR---land: " + z);
        setViewGroupRtl(this.t, true);
        setViewGroupRtl(this.u, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        b.b.m.c.a.a(this.f3715a, "---onResume---start.");
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        BluetoothDevice bluetoothDeviceG = ((b.b.g.n.k.e) mo7getP()).G();
        b.b.m.c.a.a(this.f3715a, "---onResume---connectDevice： " + b.b.m.g.f.d(bluetoothDeviceG));
        if (bluetoothDeviceG == null) {
            m0(this.p, ((b.b.g.n.k.e) mo7getP()).K(), false, activity.getString(R.string.state_open_not_connected_link));
        } else {
            m0(this.p, ((b.b.g.n.k.e) mo7getP()).K(), true, x.g(bluetoothDeviceG.getName()));
        }
        String strK = x.k(((b.b.g.n.k.e) mo7getP()).F());
        b.b.m.c.a.a(this.f3715a, "---onResume---wifiName: " + b.b.m.g.f.g(strK));
        m0(this.q, ((b.b.g.n.k.e) mo7getP()).isWifiEnabled(), ((b.b.g.n.k.e) mo7getP()).N(), strK);
        int hotspotConnectedCount = ((b.b.g.n.k.e) mo7getP()).getHotspotConnectedCount();
        b.b.m.c.a.a(this.f3715a, "---onResume---connectedCount: " + hotspotConnectedCount);
        m0(this.r, ((b.b.g.n.k.e) mo7getP()).isHotspotEnable(), hotspotConnectedCount > 0, String.format(activity.getString(R.string.state_connect_link), Integer.valueOf(hotspotConnectedCount)));
        l0(((b.b.g.n.k.e) mo7getP()).L(), ((b.b.g.n.k.e) mo7getP()).J());
        boolean zM = ((b.b.g.n.k.e) mo7getP()).M(getContext());
        b.b.m.c.a.a(this.f3715a, "---onResume---isMobileNetEnable: " + zM);
        this.n.setChecked(zM, false);
        f0(((b.b.g.n.k.e) mo7getP()).H());
        R(activity);
        b.b.m.c.a.a(this.f3715a, "---onResume---completed.");
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, false);
            setViewGroupRtl(this.p, true);
            setViewGroupRtl(this.q, true);
            setViewGroupRtl(this.r, true);
            setViewGroupRtl(this.s, true);
            setViewGroupRtl(this.w, true);
        }
        setViewGroupRtl(this.t, false);
        setViewGroupRtl(this.u, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p0(Message message) {
        boolean zM = ((b.b.g.n.k.e) mo7getP()).M(getContext());
        b.b.m.c.a.a(this.f3715a, "updateUIAfterOperate", ", isMobileNetEnable = " + zM);
        this.n.setChecked(zM, false);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume---subindex: " + i);
        H(this.o, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        boolean zM = ((b.b.g.n.k.e) mo7getP()).M(getContext());
        b.b.m.c.a.a(this.f3715a, "initData", ", isMobileNetEnable: " + zM);
        this.n.setChecked(zM, false);
        if (this.y == null) {
            this.y = new g(this, null);
        }
        this.n.setOnCheckedChangeListener(new a());
        ((ImageView) this.f3717c.findViewById(R.id.iv_link)).setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.img_empty_link));
    }
}
