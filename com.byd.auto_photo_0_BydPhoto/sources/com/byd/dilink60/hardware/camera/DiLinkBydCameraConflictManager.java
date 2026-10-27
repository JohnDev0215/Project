package com.byd.dilink60.hardware.camera;

import android.hardware.BydCameraConflictManager;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager;
import com.byd.dilink.hardware.camera.IDiLinkConflictedCallback;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkBydCameraConflictManager.class, singleton = true)
public class DiLinkBydCameraConflictManager implements IDiLinkBydCameraConflictManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BydCameraConflictManager f1970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink6_0_DiLinkConflictedCallback f1971b = new DiLink6_0_DiLinkConflictedCallback();

    public class DiLink6_0_DiLinkConflictedCallback extends DiLinkCallbackHolders<IDiLinkConflictedCallback, BydCameraConflictManager.IConflictedCallback> {
        public DiLink6_0_DiLinkConflictedCallback() {
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BydCameraConflictManager.IConflictedCallback createInnerCallback(final IDiLinkConflictedCallback iDiLinkConflictedCallback) {
            return new BydCameraConflictManager.IConflictedCallback() { // from class: com.byd.dilink60.hardware.camera.DiLinkBydCameraConflictManager.DiLink6_0_DiLinkConflictedCallback.1
                @Keep
                public void onConflicted(int i, int i2, int i3) {
                    iDiLinkConflictedCallback.onConflicted(i, i2, i3);
                }
            };
        }
    }

    public DiLinkBydCameraConflictManager() {
        if (DiLink.isPlatform("RUI")) {
            this.f1970a = new BydCameraConflictManager();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AtomicBoolean atomicBoolean, int i, BydCameraConflictManager.IConflictedCallback iConflictedCallback) {
        atomicBoolean.set(this.f1970a.addCameraConflictCallback(i, iConflictedCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AtomicBoolean atomicBoolean, int i, BydCameraConflictManager.IConflictedCallback iConflictedCallback) {
        atomicBoolean.set(this.f1970a.removeCameraConflictCallback(i, iConflictedCallback));
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean addCameraConflictCallback(final int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1971b.addCallbackIgnoreRemoteException(iDiLinkConflictedCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.hardware.camera.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1987a.a(atomicBoolean, i, (BydCameraConflictManager.IConflictedCallback) obj);
            }
        });
        return atomicBoolean.get();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkBydCameraConflictManager
    public boolean removeCameraConflictCallback(final int i, @NonNull IDiLinkConflictedCallback iDiLinkConflictedCallback) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.f1971b.removeCallbackIgnoreRemoteException(iDiLinkConflictedCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink60.hardware.camera.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1984a.b(atomicBoolean, i, (BydCameraConflictManager.IConflictedCallback) obj);
            }
        });
        return atomicBoolean.get();
    }
}
