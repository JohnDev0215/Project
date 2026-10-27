package b.b.f;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.j;
import b.b.a.h.m;
import b.b.d.b;
import b.b.e.b;
import b.b.g.r.z;
import com.byd.carplay.adapter.CarPlayListAdapter;
import com.byd.carplay.model.CPAADevice;
import com.byd.carplay.model.CarPlayType;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.ui.adapter.BtnType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: CarPlayPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.a<b.b.f.h.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RelativeLayout f675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f676b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f677c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<CPAADevice> f678d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CarPlayListAdapter f679e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.b.m.f.d.b f680f;
    public b.b.m.f.d.b g;
    public View h;
    public h i;
    public String j;
    public boolean k;
    public boolean l;
    public CPAADevice m;
    public boolean n;
    public b.b.n.a o;
    public b.b.f.e q;
    public final ServiceConnection r;

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class a implements b.b.m.f.c.a.e {
        public a() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a(d.this.TAG, ", position = " + i);
            d dVar = d.this;
            dVar.J((CPAADevice) dVar.f678d.get(i));
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class b implements b.b.m.f.c.a.d {
        public b() {
        }

        @Override // b.b.m.f.c.a.d
        public void onItemBtnClicked(int i, BtnType btnType) {
            b.b.m.c.a.a(d.this.TAG, ", position = " + i);
            d dVar = d.this;
            dVar.I((CPAADevice) dVar.f678d.get(i), btnType);
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class c implements b.InterfaceC0032b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CPAADevice f683a;

        public c(CPAADevice cPAADevice) {
            this.f683a = cPAADevice;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.e.b.InterfaceC0032b
        public void a() {
            BluetoothDevice bluetoothDeviceI = b.f.a().i(this.f683a.getBtMac());
            if (bluetoothDeviceI != null) {
                b.b.e.b.l(bluetoothDeviceI, true);
            }
            ((b.b.f.h.a) d.this.mo7getP()).q(this.f683a);
        }
    }

    /* JADX INFO: renamed from: b.b.f.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarPlayPager.java */
    public class C0039d implements b.b.m.b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CPAADevice f685a;

        public C0039d(CPAADevice cPAADevice) {
            this.f685a = cPAADevice;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.b.a
        public void onBtnClicked(PagerBtnType pagerBtnType) {
            if (PagerBtnType.BOTTOM_RIGHT == pagerBtnType) {
                return;
            }
            d.this.l = true;
            d.this.m = this.f685a;
            if (this.f685a.getType() == CarPlayType.ANDROID_AUTO) {
                b.b.e.b.n(this.f685a.getBtMac(), null);
                BluetoothDevice bluetoothDeviceI = b.f.a().i(this.f685a.getBtMac());
                if (bluetoothDeviceI != null) {
                    b.b.e.b.l(bluetoothDeviceI, true);
                }
            }
            CarPlayDevice carPlayDeviceX = ((b.b.f.h.a) d.this.mo7getP()).x();
            if (carPlayDeviceX != null) {
                d dVar = d.this;
                dVar.j = ((b.b.f.h.a) dVar.mo7getP()).s(new CPAADevice(carPlayDeviceX));
                b.b.m.c.a.a(d.this.TAG, "CarPlayDevice ing start disconnect:" + d.this.j);
            }
            CarPlayDevice carPlayDeviceV = ((b.b.f.h.a) d.this.mo7getP()).v();
            if (carPlayDeviceV != null) {
                d dVar2 = d.this;
                dVar2.j = ((b.b.f.h.a) dVar2.mo7getP()).s(new CPAADevice(carPlayDeviceV));
                b.b.m.c.a.a(d.this.TAG, "CarPlayDevice connected start disconnect:" + d.this.j);
            }
            AndroidAutoDevice androidAutoDeviceW = ((b.b.f.h.a) d.this.mo7getP()).w();
            if (androidAutoDeviceW != null) {
                d dVar3 = d.this;
                dVar3.j = ((b.b.f.h.a) dVar3.mo7getP()).s(new CPAADevice(androidAutoDeviceW));
                b.b.m.c.a.a(d.this.TAG, "AndroidAutoDevice ing start disconnect:" + d.this.j);
            }
            AndroidAutoDevice androidAutoDeviceU = ((b.b.f.h.a) d.this.mo7getP()).u();
            if (androidAutoDeviceU != null) {
                d dVar4 = d.this;
                dVar4.j = ((b.b.f.h.a) dVar4.mo7getP()).s(new CPAADevice(androidAutoDeviceU));
                b.b.m.c.a.a(d.this.TAG, "AndroidAutoDevice connected start disconnect:" + d.this.j);
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class e implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CPAADevice f687a;

        /* JADX INFO: compiled from: CarPlayPager.java */
        public class a implements Runnable {
            public a() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                d.this.k = false;
                b.b.m.c.a.a(d.this.TAG, " connected, start delete:" + e.this.f687a);
                ((b.b.f.h.a) d.this.mo7getP()).F(e.this.f687a);
            }
        }

        public e(CPAADevice cPAADevice) {
            this.f687a = cPAADevice;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.m = this.f687a;
            if (this.f687a.isConnected() || this.f687a.deviceStatus == 3) {
                d.this.k = true;
                d dVar = d.this;
                dVar.j = ((b.b.f.h.a) dVar.mo7getP()).s(this.f687a);
                b.b.a.f.h.a().g(new a(), 2000L);
            } else {
                b.b.m.c.a.a(d.this.TAG, " start delete:" + this.f687a);
                d.this.k = false;
                ((b.b.f.h.a) d.this.mo7getP()).F(this.f687a);
            }
            d.this.f678d.remove(this.f687a);
            d.this.g.c();
            if (d.this.f678d.size() == 0) {
                d.this.h.setVisibility(0);
            } else {
                d.this.h.setVisibility(8);
            }
            Collections.sort(d.this.f678d);
            d.this.f679e.notifyDataSetChanged();
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.g.c();
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (d.this.f679e != null) {
                d.this.f679e.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayPager.java */
    public interface h {
        void a();
    }

    public void A(boolean z) {
        if (z) {
            F();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void B() {
        b.b.n.a aVar;
        if (!this.n || (aVar = this.o) == null) {
            Intent intent = new Intent();
            intent.setAction("com.byd.cpaa.action");
            intent.setPackage("com.byd.uereport");
            this.f677c.bindService(intent, this.r, 1);
            return;
        }
        try {
            aVar.T(false);
        } catch (RemoteException e2) {
            e2.printStackTrace();
            ((b.b.f.h.a) mo7getP()).H();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000c, code lost:
    
        if (r6 != 4) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(int r6, ts.car.service.carplay.data.CarPlayDevice r7) {
        /*
            r5 = this;
            r0 = -1
            r1 = 1
            if (r6 == r0) goto Lf
            if (r6 == 0) goto Lf
            if (r6 == r1) goto Lf
            r0 = 2
            if (r6 == r0) goto L54
            r0 = 4
            if (r6 == r0) goto L49
            goto L5b
        Lf:
            if (r7 == 0) goto L49
            java.lang.String r0 = r5.TAG
            java.lang.String[] r1 = new java.lang.String[r1]
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "disconnectDeviceId:"
            r3.append(r4)
            java.lang.String r4 = r5.j
            r3.append(r4)
            java.lang.String r4 = ",connectOp:"
            r3.append(r4)
            boolean r4 = r5.l
            r3.append(r4)
            java.lang.String r4 = ",removeOp:"
            r3.append(r4)
            boolean r4 = r5.k
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1[r2] = r3
            b.b.m.c.a.a(r0, r1)
            java.lang.String r0 = r7.getBtAddr()
            r5.E(r0)
        L49:
            java.lang.String r0 = r5.TAG
            java.lang.String r1 = "---carPlayDeviceStatusChanged----CP_AA_DEVICE_CONNECTED---"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            b.b.m.c.a.a(r0, r1)
        L54:
            b.b.f.d$h r0 = r5.i
            if (r0 == 0) goto L5b
            r0.a()
        L5b:
            if (r7 == 0) goto L6c
            java.lang.String r0 = r7.getBtAddr()
            int r1 = r7.getWirelessAvailableState()
            java.lang.String r7 = r7.getUsbSerialNumber()
            r5.K(r6, r0, r1, r7)
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.f.d.C(int, ts.car.service.carplay.data.CarPlayDevice):void");
    }

    public void D(boolean z) {
        if (z) {
            F();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void E(String str) {
        if (this.j.equals(str)) {
            if (this.m == null) {
                b.b.m.c.a.c(this.TAG, "currentDevice==null return");
                return;
            }
            if (this.l) {
                this.l = false;
                b.b.m.c.a.a(this.TAG, "connectOp");
                ((b.b.f.h.a) mo7getP()).q(this.m);
            } else if (this.k) {
                this.k = false;
                b.b.m.c.a.a(this.TAG, "removeOp");
                ((b.b.f.h.a) mo7getP()).F(this.m);
            }
            this.j = "-1";
        }
    }

    public void F() {
        if (this.l || this.k) {
            b.b.m.c.a.a(this.TAG, "dismissAll-当前存在连接和删除操作,弹框不消失");
            return;
        }
        b.b.m.c.a.a(this.TAG, "---dismissAll---");
        b.b.m.f.d.b bVar = this.f680f;
        if (bVar != null) {
            bVar.c();
        }
        b.b.m.f.d.b bVar2 = this.g;
        if (bVar2 != null) {
            bVar2.c();
        }
        b.b.f.e eVar = this.q;
        if (eVar != null) {
            eVar.finish();
        }
        b.b.m.e.d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void G(CPAADevice cPAADevice) {
        b.b.m.c.a.a(this.TAG, ", doDeviceAvailable, device: " + cPAADevice.getName());
        if (((b.b.f.h.a) mo7getP()).D() || ((b.b.f.h.a) mo7getP()).B() || ((b.b.f.h.a) mo7getP()).E() || ((b.b.f.h.a) mo7getP()).C()) {
            O(cPAADevice);
            return;
        }
        b.b.m.c.a.a(this.TAG, ", doDeviceAvailable, connect device: " + cPAADevice.getName());
        if (cPAADevice.getType() == CarPlayType.CAR_PLAY) {
            ((b.b.f.h.a) mo7getP()).q(cPAADevice);
        } else if (TextUtils.isEmpty(cPAADevice.getUsbSN())) {
            b.b.e.b.n(cPAADevice.getBtMac(), new c(cPAADevice));
        } else {
            ((b.b.f.h.a) mo7getP()).r(cPAADevice);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void H(CPAADevice cPAADevice) {
        b.b.m.c.a.a(this.TAG, ", doDeviceConnected, device: " + cPAADevice.getName());
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            B();
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            ((b.b.f.h.a) mo7getP()).G();
        }
        b.b.m.e.d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
        Intent intent = new Intent("com.byd.card.close.FloatCard");
        intent.putExtra("package", "com.byd.carsettings");
        if (getContext() != null) {
            getContext().sendBroadcast(intent);
        }
    }

    public final void I(CPAADevice cPAADevice, BtnType btnType) {
        b.b.m.c.a.a(this.TAG, ", doOnItemBtClicked, device: " + cPAADevice + ", type: " + btnType);
        P(cPAADevice);
    }

    public final void J(CPAADevice cPAADevice) {
        b.b.m.c.a.a(this.TAG, ", doOnItemClicked, device: " + cPAADevice.getName() + ", " + cPAADevice.getType() + ", status: " + cPAADevice.deviceStatus);
        int i = cPAADevice.deviceStatus;
        if (i == 4) {
            H(cPAADevice);
            return;
        }
        if (i == 1 || i == 0) {
            G(cPAADevice);
            return;
        }
        if (i == 3) {
            z.c(getContext(), this.f677c.getString(R.string.device_connecting));
        } else if (i == 2) {
            z.c(getContext(), this.f677c.getString(R.string.device_disconnecting));
        } else if (i == -1) {
            z.c(getContext(), this.f677c.getString(R.string.device_not_available));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void K(int i, String str, int i2, String str2) {
        List<CPAADevice> list = this.f678d;
        if (list == null || list.size() == 0 || TextUtils.isEmpty(str) || this.mRootView == null) {
            return;
        }
        for (CPAADevice cPAADevice : this.f678d) {
            if (str.equals(cPAADevice.getBtMac()) || (!j.c(str2) && str2.equals(cPAADevice.getUsbSN()))) {
                cPAADevice.deviceStatus = i;
                cPAADevice.mWirelessAvailableState = i2;
                cPAADevice.setConnected(i == 4);
                cPAADevice.setHasBtSignal(((b.b.f.h.a) mo7getP()).y(cPAADevice));
                cPAADevice.setHasWiFiSignal(((b.b.f.h.a) mo7getP()).A(cPAADevice));
                cPAADevice.setHasPhoneSignal(((b.b.f.h.a) mo7getP()).z(cPAADevice));
                b.b.m.c.a.a(this.TAG, "flushDeviceState, device:" + cPAADevice.getName() + ", " + cPAADevice.getType());
            }
        }
        Collections.sort(this.f678d);
        b.b.a.f.h.a().d(new g());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public b.b.f.h.a newP() {
        return new b.b.f.h.a();
    }

    public void M(h hVar) {
        this.i = hVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void N() {
        List<CPAADevice> listT = ((b.b.f.h.a) mo7getP()).t();
        b.b.m.c.a.a(this.TAG, "----onResume----list = " + listT);
        this.f678d.clear();
        if (listT != null && !listT.isEmpty()) {
            this.f678d.addAll(listT);
        }
        if (this.f678d.size() == 0) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
        Collections.sort(this.f678d);
        this.f679e.notifyDataSetChanged();
    }

    public final void O(CPAADevice cPAADevice) {
        this.q = b.b.i.d.C(this.f677c, new C0039d(cPAADevice));
    }

    public final void P(CPAADevice cPAADevice) {
        this.g = new b.b.m.f.d.b();
        View viewInflate = LayoutInflater.from(this.f677c).inflate(R.layout.dialog_btn_left_hight_light_identification, (ViewGroup) null, false);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_title_identification);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_msg_identification);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_type_identification);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_name_identification);
        Button button = (Button) viewInflate.findViewById(R.id.btn_ok_dialog_bottom);
        button.setText(this.f677c.getString(R.string.dilink_yes));
        Button button2 = (Button) viewInflate.findViewById(R.id.btn_cancel_dialog_bottom);
        button2.setText(this.f677c.getString(R.string.dilink_no));
        textView.setText(this.f677c.getString(R.string.cp_warning));
        imageView.setImageResource(R.drawable.icon_light_public_carplay);
        if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            imageView.setImageResource(R.drawable.icon_light_public_android_auto);
        }
        textView2.setText(this.f677c.getString(R.string.cp_warning_des));
        textView3.setText(cPAADevice.getName());
        button.setOnClickListener(new e(cPAADevice));
        button2.setOnClickListener(new f());
        this.g.E(this.f677c, viewInflate, (int) this.f677c.getResources().getDimension(R.dimen.dp_560), (int) this.f677c.getResources().getDimension(R.dimen.dp_312), true);
    }

    @Override // b.b.m.e.a
    public String getDialogTag() {
        return "CarPlayPager";
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.activity_car_play;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        ArrayList arrayList = new ArrayList();
        this.f678d = arrayList;
        CarPlayListAdapter carPlayListAdapter = new CarPlayListAdapter(arrayList);
        this.f679e = carPlayListAdapter;
        this.f676b.setAdapter(carPlayListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.f676b.setLayoutManager(linearLayoutManager);
        m.b(this.f676b);
        this.f676b.setItemAnimator(new DefaultItemAnimator());
        this.f679e.setOnItemClickedListener(new a());
        this.f679e.setOnItemBtnClickedCallback(new b());
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.f.h.a) mo7getP()).init(context);
        this.f676b = (RecyclerView) this.mRootView.findViewById(R.id.rcv_connected_device_list_car_play);
        this.f675a = (RelativeLayout) this.mRootView.findViewById(R.id.rl_title_car_play);
        this.h = this.mRootView.findViewById(R.id.ll_no_data_car_play);
        this.mRootView.findViewById(R.id.iv_back_car_play).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_add_new_device_car_play).setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_add_new_device_car_play) {
            b.b.i.d.y(this.f677c);
            this.l = false;
            this.k = false;
        } else {
            if (id != R.id.iv_back_car_play) {
                return;
            }
            b.b.m.e.d dVar = this.mCallback;
            if (dVar != null) {
                dVar.onBackPress();
            }
            finish();
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        b.b.m.f.d.b bVar = this.g;
        if (bVar != null) {
            bVar.c();
        }
        if (this.n && this.o != null) {
            getContext().unbindService(this.r);
            this.o = null;
        }
        this.i = null;
        EventBus.getDefault().unregister(this);
        b.b.m.c.a.a(this.TAG, "onDestroy");
        b.b.i.d.u(BydApp.getContext(), false);
        super.onDestroy();
        this.l = false;
        this.k = false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 230008) {
            b.b.m.c.a.a(this.TAG, "service connected");
            if (this.f678d.isEmpty()) {
                N();
                return;
            }
            return;
        }
        if (aVar.h() == 230010) {
            b.b.m.c.a.a(this.TAG, "蓝牙设备移除,CPAA列表刷新");
            N();
        }
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        super.onHandlerUpdateUI(message);
        if (message.arg1 == 1) {
            b.b.m.c.a.a(this.TAG, "list_update_msg");
            N();
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        super.onLtrRudderR(z);
        setViewGroupRtl(this.f675a, true);
    }

    @Override // b.b.m.e.a
    public void onResume() {
        super.onResume();
        N();
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        super.onRtlRudderL(z);
        setViewGroupRtl(this.f675a, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(int r8, ts.car.service.androidauto.data.AndroidAutoDevice r9) {
        /*
            r7 = this;
            r0 = 1
            r1 = -1
            if (r8 == r1) goto Lf
            if (r8 == 0) goto Lf
            if (r8 == r0) goto Lf
            r0 = 2
            if (r8 == r0) goto L53
            r0 = 4
            if (r8 == r0) goto L53
            goto L5a
        Lf:
            if (r9 == 0) goto L53
            java.lang.String r2 = r9.getBtAddr()
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L1f
            java.lang.String r2 = r9.getUsbSerialNumber()
        L1f:
            java.lang.String r3 = r7.TAG
            java.lang.String[] r0 = new java.lang.String[r0]
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "disconnectDeviceId:"
            r5.append(r6)
            java.lang.String r6 = r7.j
            r5.append(r6)
            java.lang.String r6 = ",connectOp:"
            r5.append(r6)
            boolean r6 = r7.l
            r5.append(r6)
            java.lang.String r6 = ",removeOp:"
            r5.append(r6)
            boolean r6 = r7.k
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0[r4] = r5
            b.b.m.c.a.a(r3, r0)
            r7.E(r2)
        L53:
            b.b.f.d$h r0 = r7.i
            if (r0 == 0) goto L5a
            r0.a()
        L5a:
            if (r9 == 0) goto L67
            java.lang.String r0 = r9.getBtAddr()
            java.lang.String r9 = r9.getUsbSerialNumber()
            r7.K(r8, r0, r1, r9)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.f.d.z(int, ts.car.service.androidauto.data.AndroidAutoDevice):void");
    }
}
