package com.byd.dilink51.location;

import android.content.Context;
import android.location.BydLocationManager;
import android.location.ILocationEnabledListener;
import android.location.ILocationManager;
import android.location.LocationEnabledListener;
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
import com.byd.dilink51.location.DiLinkLocationManager;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkLocationManager.class, singleton = true)
public class DiLinkLocationManager implements IDiLinkLocationManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> f1576a = new DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a>() { // from class: com.byd.dilink51.location.DiLinkLocationManager.1
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createInnerCallback(final IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
            return new a() { // from class: com.byd.dilink51.location.DiLinkLocationManager.1.1
                @Keep
                public void onLocationEnabledChanged(boolean z) {
                    iDiLinkLocationEnabledListener.onLocationEnabledChanged(z);
                }
            };
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ILocationManager f1577b = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1578c;

    public static abstract class a extends ILocationEnabledListener.Stub implements LocationEnabledListener {
    }

    public DiLinkLocationManager(Context context) {
        this.f1578c = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(a aVar) {
        BydLocationManager.getInstance(this.f1578c).removeLocationEnabledListener(aVar);
        this.f1577b.removeLocationEnabledListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, String str2, String str3, a aVar) {
        this.f1577b.addLocationEnabledListener(aVar, str, str2, str3);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        DiLinkCallbackHolders<IDiLinkLocationEnabledListener, a> diLinkCallbackHolders = this.f1576a;
        final BydLocationManager bydLocationManager = BydLocationManager.getInstance(this.f1578c);
        Objects.requireNonNull(bydLocationManager);
        return diLinkCallbackHolders.addCallbackIgnoreRemoteException(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.location.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                bydLocationManager.addLocationEnabledListener((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, final String str, @Nullable final String str2, final String str3) {
        return this.f1576a.addCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.location.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1584a.b(str, str2, str3, (DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTime() {
        return BydLocationManager.getInstance(this.f1578c).getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public long getLocationEnabledDeadTimeForUser(int i) {
        return BydLocationManager.getInstance(this.f1578c).getLocationEnabledDeadTime();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser() {
        return BydLocationManager.getInstance(this.f1578c).getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public int getLocationEnabledTypeForUser(int i) {
        return BydLocationManager.getInstance(this.f1578c).getLocationEnabledTypeForUser();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isAutomotiveGnssSuspended() {
        return ILocationManager.Stub.asInterface(ServiceManager.getService("location")).isAutomotiveGnssSuspended();
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return BydLocationManager.getInstance(this.f1578c).isLocationEnabledForUser(userHandle);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener) {
        this.f1576a.removeCallback(iDiLinkLocationEnabledListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.location.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1583a.a((DiLinkLocationManager.a) obj);
            }
        });
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setAutomotiveGnssSuspended(boolean z) {
        ILocationManager.Stub.asInterface(ServiceManager.getService("location")).setAutomotiveGnssSuspended(z);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i) {
        BydLocationManager.getInstance(this.f1578c).setLocationEnabledTypeForUser(i);
    }

    @Override // com.byd.dilink.location.IDiLinkLocationManager
    public void setLocationEnabledTypeForUser(int i, int i2) {
        BydLocationManager.getInstance(this.f1578c).setLocationEnabledTypeForUser(i);
    }
}
