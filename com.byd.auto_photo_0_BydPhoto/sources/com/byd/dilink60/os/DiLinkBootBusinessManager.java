package com.byd.dilink60.os;

import android.content.Context;
import android.os.BootBusinessManager;
import android.os.IBootBusinessListener;
import androidx.annotation.Keep;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.os.IDiLinkBootBusinessListener;
import com.byd.dilink.os.IDiLinkBootBusinessManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkBootBusinessManager.class, singleton = true)
public class DiLinkBootBusinessManager implements IDiLinkBootBusinessManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final BootBusinessManager f2054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> f2055b = new DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener>() { // from class: com.byd.dilink60.os.DiLinkBootBusinessManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public IBootBusinessListener createInnerCallback(final IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
            return new IBootBusinessListener.Stub() { // from class: com.byd.dilink60.os.DiLinkBootBusinessManager.1.1
                @Keep
                public void onBusinessChange(String str) {
                    iDiLinkBootBusinessListener.onBusinessChange(str);
                }

                @Keep
                public void onSubBusinessChange(String str) {
                    iDiLinkBootBusinessListener.onSubBusinessChange(str);
                }
            };
        }
    };

    public DiLinkBootBusinessManager(Context context) {
        this.f2054a = (BootBusinessManager) context.getSystemService("bootbusiness");
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void addListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
        DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> diLinkCallbackHolders = this.f2055b;
        final BootBusinessManager bootBusinessManager = this.f2054a;
        Objects.requireNonNull(bootBusinessManager);
        diLinkCallbackHolders.addCallbackIgnoreRemoteException(iDiLinkBootBusinessListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.d0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                bootBusinessManager.addListener((IBootBusinessListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void entryBusiness(String str) {
        this.f2054a.entryBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void entrySubBusiness(String str) {
        this.f2054a.entrySubBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void exitBusiness(String str) {
        this.f2054a.exitBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void exitSubBusiness(String str) {
        this.f2054a.exitSubBusiness(str);
    }

    @Override // com.byd.dilink.os.IDiLinkBootBusinessManager
    public void removeListener(IDiLinkBootBusinessListener iDiLinkBootBusinessListener) {
        DiLinkCallbackHolders<IDiLinkBootBusinessListener, IBootBusinessListener> diLinkCallbackHolders = this.f2055b;
        final BootBusinessManager bootBusinessManager = this.f2054a;
        Objects.requireNonNull(bootBusinessManager);
        diLinkCallbackHolders.removeCallbackIgnoreRemoteException(iDiLinkBootBusinessListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.os.b0
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                bootBusinessManager.removeListener((IBootBusinessListener) obj);
            }
        });
    }
}
