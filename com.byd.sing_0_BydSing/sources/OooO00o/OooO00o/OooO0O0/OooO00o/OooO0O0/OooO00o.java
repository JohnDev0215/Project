package OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0;

import android.hardware.usb.UsbDevice;
import android.text.TextUtils;
import com.loostone.libtuning.data.bean.HidItem;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import com.loostone.libtuning.inf.extern.IMicControl;
import com.loostone.libtuning.inf.extern.IMicInfoListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.k;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o implements IBaseKeyService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public UsbDevice f174b;
    public b e;
    public boolean l;
    public boolean m;
    public boolean o;
    public int q;
    public int r;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f175c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f176d = d.b(new OooOO0O());
    public final kotlin.a f = d.b(OooO.f177OooO00o);
    public final kotlin.a g = d.b(OooOOO.f182OooO00o);
    public final kotlin.a h = d.b(OooOO0.f180OooO00o);
    public final kotlin.a i = d.b(OooO0o.f179OooO00o);
    public final kotlin.a j = d.b(OooOOO0.f183OooO00o);
    public final kotlin.a k = d.b(OooO0OO.f178OooO00o);
    public final List<String> n = k.g("DC2", "DC3");
    public int p = -1;

    public static final class OooO extends Lambda implements kotlin.jvm.b.a<LinkedList<IKeyListener>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO f177OooO00o = new OooO();

        public OooO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final LinkedList<IKeyListener> invoke() {
            return new LinkedList<>();
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<HashSet<Integer>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f178OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final HashSet<Integer> invoke() {
            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.add(0);
            hashSet.add(32);
            hashSet.add(64);
            hashSet.add(66);
            hashSet.add(68);
            hashSet.add(72);
            hashSet.add(96);
            return hashSet;
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<ArrayList<HidItem>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0o f179OooO00o = new OooO0o();

        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ArrayList<HidItem> invoke() {
            ArrayList<HidItem> arrayList = new ArrayList<>();
            arrayList.add(new HidItem(0, 2, -3));
            arrayList.add(new HidItem(0, 4, -4));
            arrayList.add(new HidItem(0, 8, -5));
            arrayList.add(new HidItem(0, 32, -2));
            arrayList.add(new HidItem(0, 64, -1));
            arrayList.add(new HidItem(1, 64, -1));
            arrayList.add(new HidItem(2, 64, -1));
            return arrayList;
        }
    }

    public static final class OooOO0 extends Lambda implements kotlin.jvm.b.a<Boolean> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOO0 f180OooO00o = new OooOO0();

        public OooOO0() {
            super(0);
        }

        public final boolean OooO00o() {
            Integer numA = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a.r().a();
            return (numA == null ? 0 : numA.intValue()) == 1;
        }

        @Override // kotlin.jvm.b.a
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(OooO00o());
        }
    }

    public static final class OooOO0O extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o> {
        public OooOO0O() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o invoke() {
            return new OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o(OooO00o.this.y());
        }
    }

    public static final class OooOOO extends Lambda implements kotlin.jvm.b.a<LinkedList<IMicInfoListener>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOOO f182OooO00o = new OooOOO();

        public OooOOO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final LinkedList<IMicInfoListener> invoke() {
            return new LinkedList<>();
        }
    }

    public static final class OooOOO0 extends Lambda implements kotlin.jvm.b.a<ArrayList<HidItem>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOOO0 f183OooO00o = new OooOOO0();

        public OooOOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ArrayList<HidItem> invoke() {
            ArrayList<HidItem> arrayList = new ArrayList<>();
            arrayList.add(new HidItem(1, 0, -100));
            arrayList.add(new HidItem(2, 0, -100));
            return arrayList;
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public final class b extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f184b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f185c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ OooO00o f186d;

        public b(OooO00o oooO00o) {
            h.d(oooO00o, "this$0");
            this.f186d = oooO00o;
            this.f184b = new Object();
        }

        public final void a() {
            synchronized (this.f184b) {
                try {
                    this.f184b.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public final void b() {
            this.f185c = true;
        }

        public final void c() {
            this.f185c = false;
            synchronized (this.f184b) {
                this.f184b.notifyAll();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.f186d.f175c) {
                try {
                    while (this.f185c) {
                        a();
                    }
                    byte[] bArrO = this.f186d.v().o();
                    OooO00o oooO00o = this.f186d;
                    oooO00o.c(0, (byte) (bArrO[0] | bArrO[1]));
                    oooO00o.c(1, bArrO[0]);
                    oooO00o.c(2, bArrO[1]);
                    Thread.sleep(50L);
                    if (this.f186d.s()) {
                        byte[] bArrH = this.f186d.v().h((byte) 2);
                        OooO00o oooO00o2 = this.f186d;
                        oooO00o2.m(1, bArrH[0]);
                        oooO00o2.m(2, bArrH[1]);
                        Thread.sleep(50L);
                    }
                    if (this.f186d.l) {
                        String interactEffect = this.f186d.v().getInteractEffect();
                        if (interactEffect != null) {
                            OooO00o oooO00o3 = this.f186d;
                            if (!TextUtils.isEmpty(interactEffect)) {
                                oooO00o3.h(interactEffect);
                            }
                        }
                        Thread.sleep(50L);
                        int dongleVolume = this.f186d.v().getDongleVolume();
                        OooO00o oooO00o4 = this.f186d;
                        if (dongleVolume >= 0 && dongleVolume <= 100) {
                            oooO00o4.b(dongleVolume);
                        }
                        Thread.sleep(50L);
                    }
                    if (this.f186d.m) {
                        OooO00o oooO00o5 = this.f186d;
                        oooO00o5.i(oooO00o5.v().getPremiumSpeakerState());
                        Thread.sleep(50L);
                    }
                    byte[] bArrQ = this.f186d.v().q();
                    this.f186d.p(bArrQ[0], bArrQ[1]);
                    Thread.sleep(50L);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.f186d.c(0, 0);
                    return;
                }
            }
        }
    }

    static {
        new a(null);
    }

    public OooO00o(UsbDevice usbDevice) {
        this.f174b = usbDevice;
    }

    public final HashSet<Integer> a() {
        return (HashSet) this.k.getValue();
    }

    public final void b(int i) {
        if (this.p == i) {
            return;
        }
        this.p = i;
        OooO00o.OooO00o.OooO0O0.b.f41a.e("HidService", "dealMicVolume(" + i + ')');
        Iterator<IMicInfoListener> it = x().iterator();
        while (it.hasNext()) {
            try {
                it.next().onInfoEvent(0, 1, Integer.valueOf(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(int i, int i2) {
        if (a().contains(Integer.valueOf(i2))) {
            for (HidItem hidItem : l()) {
                if (hidItem.getMicIndex() == i) {
                    boolean z = (hidItem.getHidCode() & i2) > 0 ? 1 : 0;
                    int i3 = !z;
                    if (hidItem.getLastState() != i3) {
                        try {
                            for (IKeyListener iKeyListener : o()) {
                                int keyCode = hidItem.getKeyCode();
                                if (keyCode == -5) {
                                    iKeyListener.onVolumeDown(i, z);
                                } else if (keyCode == -4) {
                                    iKeyListener.onVolumeUp(i, z);
                                } else if (keyCode == -3) {
                                    iKeyListener.onTVPhoneSwitch(i, z);
                                } else if (keyCode == -2) {
                                    iKeyListener.onVoice(i, z);
                                } else if (keyCode == -1) {
                                    iKeyListener.onPower(i, z);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    hidItem.setLastState(i3);
                }
            }
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public IMicControl getMicControl() {
        return v();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public int getPairState(int i) {
        if (i == 0) {
            return this.q;
        }
        if (i != 1) {
            return -1;
        }
        return this.r;
    }

    public final void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e("HidService", "dealInteractEffect(" + str + ')');
        Iterator<IMicInfoListener> it = x().iterator();
        while (it.hasNext()) {
            try {
                it.next().onInfoEvent(0, 3, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void i(boolean z) {
        if (this.o != z) {
            this.o = z;
            Iterator<IMicInfoListener> it = x().iterator();
            while (it.hasNext()) {
                try {
                    it.next().onInfoEvent(0, 6, Boolean.valueOf(z));
                } catch (Exception e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d("HidService", "dealPremiumSpeakerState error");
                    e.printStackTrace();
                }
            }
        }
    }

    public final ArrayList<HidItem> l() {
        return (ArrayList) this.i.getValue();
    }

    public final void m(int i, int i2) {
        for (HidItem hidItem : u()) {
            if (hidItem.getMicIndex() == i) {
                if (hidItem.getLastState() != i2) {
                    try {
                        for (IMicInfoListener iMicInfoListener : x()) {
                            if (hidItem.getKeyCode() == -100) {
                                iMicInfoListener.onMicBattery(i, i2);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                hidItem.setLastState(i2);
            }
        }
    }

    public final LinkedList<IKeyListener> o() {
        return (LinkedList) this.f.getValue();
    }

    public final void p(int i, int i2) {
        if (this.q != i) {
            this.q = i;
            Iterator<IKeyListener> it = o().iterator();
            while (it.hasNext()) {
                it.next().onPairStateChanged(1, this.q);
            }
        }
        if (this.r != i2) {
            this.r = i2;
            Iterator<IKeyListener> it2 = o().iterator();
            while (it2.hasNext()) {
                it2.next().onPairStateChanged(2, this.r);
            }
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void pause() {
        b bVar = this.e;
        if (bVar == null) {
            return;
        }
        bVar.b();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void registerKeyListener(IKeyListener iKeyListener) {
        h.d(iKeyListener, "listener");
        o().add(iKeyListener);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void registerMicInfoListener(IMicInfoListener iMicInfoListener) {
        h.d(iMicInfoListener, "listener");
        x().add(iMicInfoListener);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void resume() {
        b bVar = this.e;
        if (bVar == null) {
            return;
        }
        bVar.c();
    }

    public final boolean s() {
        return ((Boolean) this.h.getValue()).booleanValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void setDevice(UsbDevice usbDevice) {
        this.f174b = usbDevice;
        v().g(usbDevice);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void start() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b("HidService", "start: ");
        Integer numA = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a.n().a();
        if (numA != null && numA.intValue() == 0) {
            v().a((byte) 8, (byte) 1);
        }
        this.l = false;
        this.m = false;
        OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o oooO00o = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a;
        UsbDevice usbDevice = this.f174b;
        String strL = oooO00o.l(usbDevice == null ? null : usbDevice.getProductName());
        if (StringsKt__StringsKt.o(strL, "DC3", false, 2, null)) {
            this.l = true;
            this.m = true;
        }
        if (!this.m) {
            Iterator<T> it = this.n.iterator();
            while (it.hasNext()) {
                if (StringsKt__StringsKt.o(strL, (String) it.next(), false, 2, null)) {
                    this.m = true;
                }
            }
        }
        this.f175c = true;
        b bVar = new b(this);
        bVar.start();
        this.e = bVar;
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void stop() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b("HidService", "stop: ");
        this.f175c = false;
        this.e = null;
        c(0, 0);
    }

    public final ArrayList<HidItem> u() {
        return (ArrayList) this.j.getValue();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void unRegisterKeyListener(IKeyListener iKeyListener) {
        h.d(iKeyListener, "listener");
        o().remove(iKeyListener);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void unRegisterMicInfoListener(IMicInfoListener iMicInfoListener) {
        h.d(iMicInfoListener, "listener");
        x().remove(iMicInfoListener);
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o v() {
        return (OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o) this.f176d.getValue();
    }

    public final LinkedList<IMicInfoListener> x() {
        return (LinkedList) this.g.getValue();
    }

    public final UsbDevice y() {
        return this.f174b;
    }
}
