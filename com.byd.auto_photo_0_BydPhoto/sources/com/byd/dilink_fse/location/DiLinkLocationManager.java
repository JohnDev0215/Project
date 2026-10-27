package com.byd.dilink_fse.location;

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
import com.byd.dilink_fse.location.DiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class DiLinkLocationManager implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> f2377a = new DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a>() { // from class: com.byd.dilink_fse.location.DiLinkLocationManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createInnerCallback(final IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
            return new a() { // from class: com.byd.dilink_fse.location.DiLinkLocationManager.1.1
                @Keep
                public void onLocationEnabledChanged(boolean z) {
                    iDiLinkLocationEnabledListener.onLocationEnabledChanged(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ILocationManager f2378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f2379c;

    public static abstract class a extends ILocationEnabledListener.Stub implements LocationEnabledListener {
    }

    public DiLinkLocationManager(Context context) throws RemoteException {
        IBinder service = ServiceManager.getService("location");
        if (service == null) {
            throw new RemoteException("Cannot retrieve Location Manager");
        }
        this.f2378b = ILocationManager.Stub.asInterface(service);
        this.f2379c = (LocationManager) context.getSystemService("location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(a aVar) {
        this.f2378b.removeLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2, String str3, a aVar) {
        this.f2378b.addLocationEnabledListener(aVar, str, str2, str3);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        throw new UnsupportedTargetPlatformException("FSE 没有 addLocationEnabledListener 这个函数！");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, final String str, @Nullable final String str2, final String str3) {
        return this.f2377a.addCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.location.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2383a.b(str, str2, str3, (DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        return this.f2379c.getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        return this.f2378b.getLocationEnabledDeadTimeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        return this.f2379c.getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        return this.f2378b.getLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        throw new UnsupportedTargetPlatformException("FSE不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.f2379c.isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        this.f2377a.removeCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_fse.location.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2386a.a((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        throw new UnsupportedTargetPlatformException("FSE不存在此接口");
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        this.f2379c.setLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        this.f2378b.setLocationEnabledTypeForUser(i, i2);
    }
}
