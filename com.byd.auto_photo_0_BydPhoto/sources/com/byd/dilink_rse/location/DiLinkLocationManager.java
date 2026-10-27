package com.byd.dilink_rse.location;

import android.content.Context;
import android.location.ILocationEnabledListener;
import android.location.ILocationManager;
import android.location.LocationEnabledListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.location.IDiLinkLocationEnabledListener;
import com.byd.dilink.location.IDiLinkLocationManager;
import com.byd.dilink_rse.location.DiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class DiLinkLocationManager implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ILocationManager f2721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> f2722b = new DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a>() { // from class: com.byd.dilink_rse.location.DiLinkLocationManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createInnerCallback(final IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
            return new a() { // from class: com.byd.dilink_rse.location.DiLinkLocationManager.1.1
                @Keep
                public void onLocationEnabledChanged(boolean z) {
                    iDiLinkLocationEnabledListener.onLocationEnabledChanged(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f2723c;

    public static abstract class a extends ILocationEnabledListener.Stub implements LocationEnabledListener {
    }

    public DiLinkLocationManager(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("location");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Location Manager");
        }
        this.f2721a = ILocationManager.Stub.asInterface(service);
        this.f2723c = (LocationManager) context.getSystemService("location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(a aVar) {
        this.f2723c.addLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, a aVar) {
        this.f2721a.addLocationEnabledListener(aVar, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(a aVar) {
        this.f2723c.removeLocationEnabledListener(aVar);
        this.f2721a.removeLocationEnabledListener(aVar);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        return this.f2722b.addCallbackIgnoreRemoteException(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.location.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2730a.a((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, final String str, @Nullable String str2, String str3) {
        return this.f2722b.addCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.location.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2728a.b(str, (DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        return this.f2723c.getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        return this.f2721a.getLocationEnabledDeadTimeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        return this.f2723c.getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        return this.f2721a.getLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        throw new UnsupportedTargetPlatformException("RSE不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.f2723c.isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        this.f2722b.removeCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.location.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2727a.f((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        throw new UnsupportedTargetPlatformException("RSE不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        this.f2723c.setLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        this.f2721a.setLocationEnabledTypeForUser(i, i2);
    }
}
