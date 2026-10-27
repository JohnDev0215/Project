package com.neusoft.na.ocservice.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.ocservice.R;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;

/* JADX INFO: compiled from: NotificationHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fJ\u0006\u0010\u0012\u001a\u00020\u0010J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/neusoft/na/ocservice/util/NotificationHelper;", "Landroid/content/ContextWrapper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mgr", "Landroid/app/NotificationManager;", "getMgr", "()Landroid/app/NotificationManager;", "mgr$delegate", "Lkotlin/Lazy;", "create", "Lcom/neusoft/na/ocservice/util/NotificationId;", "channel", "Lcom/neusoft/na/ocservice/util/NotificationHelper$Channel;", "hide", "", "notify", "hideAll", "show", "Channel", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationHelper extends ContextWrapper {

    /* JADX INFO: renamed from: mgr$delegate, reason: from kotlin metadata */
    private final Lazy mgr;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<Channel, String> ChannelMap = MapsKt.mapOf(TuplesKt.to(Channel.OneCore, "OneCoreServiceNotificationChannel"), TuplesKt.to(Channel.DBU, "DbUpdateNotificationChannel"));

    /* JADX INFO: compiled from: NotificationHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/neusoft/na/ocservice/util/NotificationHelper$Channel;", "", "(Ljava/lang/String;I)V", "OneCore", "DBU", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum Channel {
        OneCore,
        DBU
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationHelper(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mgr = LazyKt.lazy(new Function0<NotificationManager>() { // from class: com.neusoft.na.ocservice.util.NotificationHelper$mgr$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final NotificationManager invoke() {
                Object systemService = this.this$0.getSystemService("notification");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                return (NotificationManager) systemService;
            }
        });
        String string = getString(R.string.oneCoreNotificationChannelName);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.oneCoreNotificationChannelName)");
        String string2 = getString(R.string.oneCoreNotificationChannelDescription);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.oneCo…cationChannelDescription)");
        NotificationChannel notificationChannel = new NotificationChannel(ChannelMap.get(Channel.OneCore), string, 2);
        notificationChannel.setDescription(string2);
        notificationChannel.setLockscreenVisibility(1);
        notificationChannel.setLightColor(-16776961);
        getMgr().createNotificationChannel(notificationChannel);
    }

    /* JADX INFO: compiled from: NotificationHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/ocservice/util/NotificationHelper$Companion;", "Lmu/KLogging;", "()V", "ChannelMap", "", "Lcom/neusoft/na/ocservice/util/NotificationHelper$Channel;", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final NotificationManager getMgr() {
        return (NotificationManager) this.mgr.getValue();
    }

    public final NotificationId create(Channel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        String str = ChannelMap.get(channel);
        if (str == null) {
            str = "";
        }
        return new NotificationId(new NotificationCompat.Builder(this, str), ThreadLocalRandom.current().nextInt());
    }

    public final void show(NotificationId notify) {
        Intrinsics.checkNotNullParameter(notify, "notify");
        NotificationCompat.Builder builder = notify.getBuilder();
        int id = notify.getId();
        StatusBarNotification[] activeNotifications = getMgr().getActiveNotifications();
        Intrinsics.checkNotNullExpressionValue(activeNotifications, "mgr.activeNotifications");
        StatusBarNotification[] statusBarNotificationArr = activeNotifications;
        int length = statusBarNotificationArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else {
                if (statusBarNotificationArr[i].getId() == id) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (z) {
            getMgr().notify(id, builder.build());
        } else {
            INSTANCE.getLogger().warn("Notification with id:" + id + " already active.");
        }
    }

    public final void hide(NotificationId notify) {
        Intrinsics.checkNotNullParameter(notify, "notify");
        int id = notify.getId();
        StatusBarNotification[] activeNotifications = getMgr().getActiveNotifications();
        Intrinsics.checkNotNullExpressionValue(activeNotifications, "mgr.activeNotifications");
        StatusBarNotification[] statusBarNotificationArr = activeNotifications;
        int length = statusBarNotificationArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            } else {
                if (statusBarNotificationArr[i].getId() == id) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (z) {
            INSTANCE.getLogger().warn("No notification with id:" + id + " active.");
        } else {
            getMgr().cancel(id);
        }
    }

    public final void hideAll() {
        getMgr().cancelAll();
    }
}
