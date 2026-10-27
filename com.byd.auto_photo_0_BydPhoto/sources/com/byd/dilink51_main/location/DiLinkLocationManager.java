package com.byd.dilink51_main.location;

import android.content.Context;
import android.location.ILocationEnabledListener;
import android.location.ILocationManager;
import android.location.LocationEnabledListener;
import android.location.LocationManager;
import android.os.ServiceManager;
import android.os.UserHandle;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.location.IDiLinkLocationEnabledListener;
import com.byd.dilink.location.IDiLinkLocationManager;
import com.byd.dilink51_main.location.DiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class DiLinkLocationManager implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final LocationManager f1784a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> f1786c = new DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a>() { // from class: com.byd.dilink51_main.location.DiLinkLocationManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createInnerCallback(final IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
            return new a() { // from class: com.byd.dilink51_main.location.DiLinkLocationManager.1.1
                @Keep
                public void onLocationEnabledChanged(boolean z) {
                    iDiLinkLocationEnabledListener.onLocationEnabledChanged(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ILocationManager f1785b = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));

    public static abstract class a extends ILocationEnabledListener.Stub implements LocationEnabledListener {
    }

    public DiLinkLocationManager(Context context) {
        this.f1784a = (LocationManager) context.getSystemService("location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(a aVar) {
        this.f1784a.addLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2, String str3, a aVar) {
        this.f1785b.addLocationEnabledListener(aVar, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(a aVar) {
        this.f1784a.removeLocationEnabledListener(aVar);
        this.f1785b.removeLocationEnabledListener(aVar);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        return this.f1786c.addCallbackIgnoreRemoteException(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.location.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1790a.a((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, final String str, @Nullable final String str2, final String str3) {
        return this.f1786c.addCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.location.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1792a.b(str, str2, str3, (DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        return this.f1784a.getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        return this.f1785b.getLocationEnabledDeadTimeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        return this.f1784a.getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        return this.f1784a.getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        return this.f1784a.isAutomotiveGnssSuspended();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.f1784a.isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        this.f1786c.removeCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.location.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1791a.c((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        this.f1784a.setAutomotiveGnssSuspended(z);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        this.f1784a.setLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        this.f1785b.setLocationEnabledTypeForUser(i, i2);
    }
}
