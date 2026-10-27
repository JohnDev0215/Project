package com.byd.dilink30.location;

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
import com.byd.dilink30.location.DiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class DiLinkLocationManager implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ILocationManager f1216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> f1217b = new DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a>() { // from class: com.byd.dilink30.location.DiLinkLocationManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createInnerCallback(final IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
            return new a() { // from class: com.byd.dilink30.location.DiLinkLocationManager.1.1
                @Keep
                public void onLocationEnabledChanged(boolean z) {
                    iDiLinkLocationEnabledListener.onLocationEnabledChanged(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f1218c;

    public static abstract class a extends ILocationEnabledListener.Stub implements LocationEnabledListener {
    }

    public DiLinkLocationManager(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("location");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Location Manager");
        }
        this.f1216a = ILocationManager.Stub.asInterface(service);
        this.f1218c = (LocationManager) context.getSystemService("location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(a aVar) {
        this.f1218c.removeLocationEnabledListener(aVar);
        this.f1216a.removeLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(a aVar) {
        this.f1218c.addLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str, a aVar) {
        this.f1216a.addLocationEnabledListener(aVar, str);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        return this.f1217b.addCallbackIgnoreRemoteException(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.location.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1225a.b((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, final String str, @Nullable String str2, String str3) {
        return this.f1217b.addCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.location.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1222a.c(str, (DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        return this.f1218c.getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        return this.f1216a.getLocationEnabledDeadTimeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        return this.f1218c.getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        return this.f1216a.getLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        throw new UnsupportedTargetPlatformException("Di3.0不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.f1218c.isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        this.f1217b.removeCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink30.location.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1224a.a((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        throw new UnsupportedTargetPlatformException("Di3.0不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        this.f1218c.setLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        this.f1216a.setLocationEnabledTypeForUser(i, i2);
    }
}
