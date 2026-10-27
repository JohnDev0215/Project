package p;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.play.core.assetpacks.ExtractionForegroundService;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class c33 extends l23 {
    @Override // p.l23
    public final boolean g0(int i, Parcel parcel) {
        String[] packagesForUid;
        String[] packagesForUid2;
        e33 e33Var = null;
        if (i == 2) {
            Bundle bundle = (Bundle) n23.a(parcel, Bundle.CREATOR);
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                e33Var = iInterfaceQueryLocalInterface instanceof e33 ? (e33) iInterfaceQueryLocalInterface : new e33(strongBinder);
            }
            j03 j03Var = (j03) this;
            synchronized (j03Var) {
                try {
                    j03Var.b.b("updateServiceState AIDL call", new Object[0]);
                    if (z03.a(j03Var.c) && (packagesForUid = j03Var.c.getPackageManager().getPackagesForUid(Binder.getCallingUid())) != null && Arrays.asList(packagesForUid).contains("com.android.vending")) {
                        int i2 = bundle.getInt("action_type");
                        b13 b13Var = j03Var.f;
                        synchronized (b13Var.b) {
                            b13Var.b.add(e33Var);
                        }
                        if (i2 == 1) {
                            String string = bundle.getString("notification_channel_name");
                            synchronized (j03Var) {
                                if (string == null) {
                                    string = "File downloads by Play";
                                }
                                try {
                                    j03Var.g.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", string, 2));
                                    j03Var.e.a(true);
                                    b13 b13Var2 = j03Var.f;
                                    String string2 = bundle.getString("notification_title");
                                    String string3 = bundle.getString("notification_subtext");
                                    long j = bundle.getLong("notification_timeout", 600000L);
                                    Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
                                    Notification.Builder timeoutAfter = new Notification.Builder(j03Var.c, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j);
                                    if (parcelable instanceof PendingIntent) {
                                        timeoutAfter.setContentIntent((PendingIntent) parcelable);
                                    }
                                    Notification.Builder ongoing = timeoutAfter.setSmallIcon(R.drawable.stat_sys_download).setOngoing(false);
                                    if (string2 == null) {
                                        string2 = "Downloading additional file";
                                    }
                                    Notification.Builder contentTitle = ongoing.setContentTitle(string2);
                                    if (string3 == null) {
                                        string3 = "Transferring";
                                    }
                                    contentTitle.setSubText(string3);
                                    int i3 = bundle.getInt("notification_color");
                                    if (i3 != 0) {
                                        timeoutAfter.setColor(i3).setVisibility(-1);
                                    }
                                    b13Var2.e = timeoutAfter.build();
                                    j03Var.c.bindService(new Intent(j03Var.c, (Class<?>) ExtractionForegroundService.class), j03Var.f, 1);
                                } finally {
                                }
                            }
                        } else if (i2 == 2) {
                            j03Var.e.a(false);
                            b13 b13Var3 = j03Var.f;
                            b13Var3.a.b("Stopping foreground installation service.", new Object[0]);
                            b13Var3.c.unbindService(b13Var3);
                            ExtractionForegroundService extractionForegroundService = b13Var3.d;
                            if (extractionForegroundService != null) {
                                synchronized (extractionForegroundService) {
                                    extractionForegroundService.stopForeground(true);
                                    extractionForegroundService.stopSelf();
                                }
                            }
                            b13Var3.a();
                        } else {
                            j03Var.b.c("Unknown action type received: %d", Integer.valueOf(i2));
                            e33Var.V(new Bundle());
                        }
                    } else {
                        e33Var.V(new Bundle());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else {
            if (i != 3) {
                return false;
            }
            IBinder strongBinder2 = parcel.readStrongBinder();
            if (strongBinder2 != null) {
                IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
                e33Var = iInterfaceQueryLocalInterface2 instanceof e33 ? (e33) iInterfaceQueryLocalInterface2 : new e33(strongBinder2);
            }
            j03 j03Var2 = (j03) this;
            j03Var2.b.b("clearAssetPackStorage AIDL call", new Object[0]);
            Context context = j03Var2.c;
            if (z03.a(context) && (packagesForUid2 = context.getPackageManager().getPackagesForUid(Binder.getCallingUid())) != null && Arrays.asList(packagesForUid2).contains("com.android.vending")) {
                com.google.android.play.core.assetpacks.c.g(j03Var2.d.d());
                Bundle bundle2 = new Bundle();
                Parcel parcelG0 = e33Var.g0();
                parcelG0.writeInt(1);
                bundle2.writeToParcel(parcelG0, 0);
                e33Var.h0(parcelG0, 4);
            } else {
                e33Var.V(new Bundle());
            }
        }
        return true;
    }
}
