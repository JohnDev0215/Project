package OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0OO.OooO0O0.OooO0o;
import android.accessibilityservice.AccessibilityService;
import android.hardware.usb.UsbDevice;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.loostone.libtuning.inf.extern.IBaseKeyService;
import com.loostone.libtuning.inf.extern.IKeyListener;
import com.loostone.libtuning.inf.extern.IMicControl;
import com.loostone.libtuning.inf.extern.IMicInfoListener;
import java.util.LinkedList;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO extends AccessibilityService implements IBaseKeyService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f187b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final kotlin.a<LinkedList<IKeyListener>> f188c = d.b(OooO00o.f190OooO00o);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public UsbDevice f189d;
    public boolean e = true;
    public final kotlin.a f = d.b(new C0013OooO0OO());

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<LinkedList<IKeyListener>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f190OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final LinkedList<IKeyListener> invoke() {
            return new LinkedList<>();
        }
    }

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.OooO0OO$OooO0OO, reason: collision with other inner class name */
    public static final class C0013OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o> {
        public C0013OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o invoke() {
            return new OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o(OooO0OO.this.c());
        }
    }

    public static final class a {
        static {
            j.d(new PropertyReference1Impl(j.a(a.class), "callBackList", "getCallBackList()Ljava/util/LinkedList;"));
        }

        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }

        public final LinkedList<IKeyListener> a() {
            return (LinkedList) OooO0OO.f188c.getValue();
        }
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o b() {
        return (OooO00o.OooO00o.OooO0o0.OooO0O0.OooO0OO.OooO00o) this.f.getValue();
    }

    public final UsbDevice c() {
        return this.f189d;
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public IMicControl getMicControl() {
        return b();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public int getPairState(int i) {
        return IBaseKeyService.DefaultImpls.getPairState(this, i);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        b.f41a.e("MicKeyService", "  onCreate()");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        b.f41a.e("MicKeyService", "  onInterrupt()");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public boolean onKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            return super.onKeyEvent(keyEvent);
        }
        if (!this.e) {
            return super.onKeyEvent(keyEvent);
        }
        try {
            for (IKeyListener iKeyListener : f187b.a()) {
                switch (keyEvent.getKeyCode()) {
                    case 131:
                        if (keyEvent.getAction() == 0) {
                            iKeyListener.onPower(0, true);
                        }
                        break;
                    case 132:
                        if (keyEvent.getAction() == 0) {
                            iKeyListener.onPower(0, false);
                        }
                        break;
                    case 133:
                        if (keyEvent.getAction() == 0) {
                            iKeyListener.onVoice(0, true);
                        } else if (keyEvent.getAction() == 1) {
                            iKeyListener.onVoice(0, false);
                        }
                        break;
                    case 134:
                        if (keyEvent.getAction() == 0) {
                            iKeyListener.onVolumeUp(0, true);
                        } else if (keyEvent.getAction() == 1) {
                            iKeyListener.onVolumeUp(0, false);
                        }
                        break;
                    case 135:
                        if (keyEvent.getAction() == 0) {
                            iKeyListener.onVolumeUp(0, true);
                        } else if (keyEvent.getAction() == 1) {
                            iKeyListener.onVolumeUp(0, false);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onKeyEvent(keyEvent);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void pause() {
        this.e = false;
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void registerKeyListener(IKeyListener iKeyListener) {
        h.d(iKeyListener, "listener");
        f187b.a().add(iKeyListener);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void registerMicInfoListener(IMicInfoListener iMicInfoListener) {
        h.d(iMicInfoListener, "listener");
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void resume() {
        this.e = true;
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void setDevice(UsbDevice usbDevice) {
        this.f189d = usbDevice;
        b().g(usbDevice);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void start() {
        b.f41a.e("MicKeyService", "start()");
        OooO0o.f54a.a().endHid();
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void stop() {
        b.f41a.e("MicKeyService", "stop()");
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void unRegisterKeyListener(IKeyListener iKeyListener) {
        h.d(iKeyListener, "listener");
        f187b.a().remove(iKeyListener);
    }

    @Override // com.loostone.libtuning.inf.extern.IBaseKeyService
    public void unRegisterMicInfoListener(IMicInfoListener iMicInfoListener) {
        h.d(iMicInfoListener, "listener");
    }
}
