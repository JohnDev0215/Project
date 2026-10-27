package com.loostone.libtuning.process.dongle;

import a.a.d.c.a;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import com.loostone.libtuning.inf.extern.IDongleListener;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class LocalDongleManager {
    public static final OooO00o Companion = new OooO00o(null);
    private static final String TAG = "DongleController";
    private final a dongleListeners$delegate;
    private final a mCachedDongleDeviceManager$delegate;
    private final Context mContext;
    private final a mEventManager$delegate;

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<LinkedList<IDongleListener>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0O0 f3892OooO00o = new OooO0O0();

        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final LinkedList<IDongleListener> invoke() {
            return new LinkedList<>();
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0> {
        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0 invoke() {
            return new OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0(LocalDongleManager.this.mContext, LocalDongleManager.this);
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO> {

        public static final class OooO00o implements a.a.d.c.a {

            /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
            public final /* synthetic */ LocalDongleManager f3895OooO00o;

            public OooO00o(LocalDongleManager localDongleManager) {
                this.f3895OooO00o = localDongleManager;
            }

            @Override // a.a.d.c.a
            public void onDeviceAddedWithHid(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
                Iterator it = this.f3895OooO00o.getDongleListeners().iterator();
                while (it.hasNext()) {
                    ((IDongleListener) it.next()).onDongleAttach();
                }
            }

            @Override // a.a.d.c.a
            public void onDeviceDeniedByHid(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
            }

            @Override // a.a.d.c.a
            public void onDeviceRemoved(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
                h.d(oooO00o, "cachedDevice");
                Iterator it = this.f3895OooO00o.getDongleListeners().iterator();
                while (it.hasNext()) {
                    ((IDongleListener) it.next()).onDongleDetach();
                }
            }

            @Override // a.a.d.c.a
            public void onDonglePermissionDenied() {
            }

            @Override // a.a.d.c.a
            public void onDonglePermissionGranted(UsbDevice usbDevice) {
                h.d(usbDevice, "usbDevice");
            }

            @Override // a.a.d.c.a
            public void onMicConnectState(UsbDevice usbDevice, boolean z) {
                h.d(usbDevice, "usbDevice");
                a.C0027a.a(this, usbDevice, z);
                Iterator it = this.f3895OooO00o.getDongleListeners().iterator();
                while (it.hasNext()) {
                    ((IDongleListener) it.next()).onMicConnectState(usbDevice, z);
                }
            }
        }

        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO invoke() {
            OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO oooO0OO = new OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO(LocalDongleManager.this.mContext, LocalDongleManager.this.getMCachedDongleDeviceManager());
            oooO0OO.g(new OooO00o(LocalDongleManager.this));
            return oooO0OO;
        }
    }

    public LocalDongleManager(Context context) {
        h.d(context, "mContext");
        this.mContext = context;
        this.dongleListeners$delegate = d.b(OooO0O0.f3892OooO00o);
        this.mCachedDongleDeviceManager$delegate = d.b(new OooO0OO());
        this.mEventManager$delegate = d.b(new OooO0o());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LinkedList<IDongleListener> getDongleListeners() {
        return (LinkedList) this.dongleListeners$delegate.getValue();
    }

    public final int dongleStatus() {
        return getMCachedDongleDeviceManager().a();
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0 getMCachedDongleDeviceManager() {
        return (OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0O0) this.mCachedDongleDeviceManager$delegate.getValue();
    }

    public final OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO getMEventManager() {
        return (OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO) this.mEventManager$delegate.getValue();
    }

    public final void init() {
        getMEventManager().K();
    }

    public final void registerDongleListener(IDongleListener iDongleListener) {
        h.d(iDongleListener, "listener");
        getDongleListeners().add(iDongleListener);
    }

    public final void release() {
        getMEventManager().O();
        getMCachedDongleDeviceManager().o();
    }

    public final void setScreenLiftState(boolean z) {
        getMCachedDongleDeviceManager().h(z);
    }

    public final void unRegisterDongleListener(IDongleListener iDongleListener) {
        h.d(iDongleListener, "listener");
        getDongleListeners().remove(iDongleListener);
    }
}
