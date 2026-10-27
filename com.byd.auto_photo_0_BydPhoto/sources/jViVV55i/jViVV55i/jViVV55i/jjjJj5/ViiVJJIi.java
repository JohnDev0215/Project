package jViVV55i.jViVV55i.jViVV55i.jjjJj5;

import android.content.Context;
import android.os.BootBusinessManager;
import android.os.IBootBusinessListener;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkBootBusinessListener;
import com.byd.dilink.os.IDiLinkBootBusinessManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkBootBusinessManager.class, singleton = true)
public class ViiVJJIi implements IDiLinkBootBusinessManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BootBusinessManager f3074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> f3075b = new a();

    public class a extends DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> {

        /* JADX INFO: renamed from: jViVV55i.jViVV55i.jViVV55i.jjjJj5.ViiVJJIi$a$a, reason: collision with other inner class name */
        public class IBootBusinessListenerStubC0085a extends IBootBusinessListener.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkBootBusinessListener f3077a;

            public IBootBusinessListenerStubC0085a(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
                this.f3077a = iDiLinkBootBusinessListener;
            }

            public void onBusinessChange(String str) {
                this.f3077a.onBusinessChange(str);
            }

            public void onSubBusinessChange(String str) {
                this.f3077a.onSubBusinessChange(str);
            }
        }

        public a() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBootBusinessListener createInnerCallback(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
            return new IBootBusinessListenerStubC0085a(iDiLinkBootBusinessListener);
        }
    }

    public ViiVJJIi(Context context) {
        this.f3074a = (BootBusinessManager) context.getSystemService("bootbusiness");
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void addListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
        DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> diLinkCallbackHolders = this.f3075b;
        final BootBusinessManager bootBusinessManager = this.f3074a;
        Objects.requireNonNull(bootBusinessManager);
        diLinkCallbackHolders.addCallbackIgnoreRemoteException(iDiLinkBootBusinessListener, new IDiLinkCallbackHandler() { // from class: jViVV55i.jViVV55i.jViVV55i.jjjJj5.h
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                bootBusinessManager.addListener((IBootBusinessListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void entryBusiness(String str) {
        this.f3074a.entryBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void entrySubBusiness(String str) {
        this.f3074a.entrySubBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void exitBusiness(String str) {
        this.f3074a.exitBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void exitSubBusiness(String str) {
        this.f3074a.exitSubBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void removeListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
        DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> diLinkCallbackHolders = this.f3075b;
        final BootBusinessManager bootBusinessManager = this.f3074a;
        Objects.requireNonNull(bootBusinessManager);
        diLinkCallbackHolders.removeCallbackIgnoreRemoteException(iDiLinkBootBusinessListener, new IDiLinkCallbackHandler() { // from class: jViVV55i.jViVV55i.jViVV55i.jjjJj5.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                bootBusinessManager.removeListener((IBootBusinessListener) obj);
            }
        });
    }
}
