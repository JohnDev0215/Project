package org.chromium.components.browser_ui.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import android.util.Log;
import defpackage.lp1;
import defpackage.o8;
import defpackage.ro1;
import defpackage.so1;
import java.util.List;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationManagerProxyImpl implements so1 {
    public final ro1 a;

    public NotificationManagerProxyImpl(Context context) {
        this.a = new ro1(context);
    }

    @Override // defpackage.so1
    public final void a(String str) {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.deleteNotificationChannel", null);
        try {
            this.a.b.deleteNotificationChannel(str);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final List<NotificationChannel> b() {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.getNotificationChannels", null);
        try {
            List<NotificationChannel> notificationChannels = this.a.b.getNotificationChannels();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return notificationChannels;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final void c(lp1 lp1Var) {
        Notification notification;
        if (lp1Var == null || (notification = lp1Var.a) == null) {
            Log.e("cr_NotifManagerProxy", "Failed to create notification.");
            return;
        }
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.notify(notification)", null);
        try {
            ro1 ro1Var = this.a;
            o8 o8Var = lp1Var.b;
            ro1Var.b((String) o8Var.c, o8Var.b, notification);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final void d(int i, String str) {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.cancel(tag, id)", null);
        try {
            this.a.b.cancel(str, i);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final void e(NotificationChannel notificationChannel) {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.createNotificationChannel", null);
        try {
            this.a.b.createNotificationChannel(notificationChannel);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final boolean f() {
        return this.a.a();
    }

    @Override // defpackage.so1
    public final NotificationChannel g(String str) {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.getNotificationChannel", null);
        try {
            NotificationChannel notificationChannel = this.a.b.getNotificationChannel(str);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return notificationChannel;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final List<NotificationChannelGroup> h() {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.getNotificationChannelGroups", null);
        try {
            List<NotificationChannelGroup> notificationChannelGroups = this.a.b.getNotificationChannelGroups();
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return notificationChannelGroups;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.so1
    public final void i(NotificationChannelGroup notificationChannelGroup) {
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.createNotificationChannelGroup", null);
        try {
            this.a.b.createNotificationChannelGroup(notificationChannelGroup);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
