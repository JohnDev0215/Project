package com.neusoft.na.ocservice.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.ocservice.R;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.NotificationHelper;
import kotlin.Metadata;

/* JADX INFO: compiled from: WidgetService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\"\u0010\u0013\u001a\u00020\u00142\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0017R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/neusoft/na/ocservice/widget/WidgetService;", "Landroid/app/Service;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "binder", "Lcom/neusoft/na/ocservice/widget/WidgetBinder;", "getBinder", "()Lcom/neusoft/na/ocservice/widget/WidgetBinder;", "setBinder", "(Lcom/neusoft/na/ocservice/widget/WidgetBinder;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onStartCommand", "", "flags", "startId", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class WidgetService extends Service {
    private final String TAG = "XJWidgetService";
    private WidgetBinder binder;

    public final WidgetBinder getBinder() {
        return this.binder;
    }

    public final void setBinder(WidgetBinder widgetBinder) {
        this.binder = widgetBinder;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.i(this.TAG, "onBind");
        if (this.binder == null) {
            this.binder = WidgetBinder.INSTANCE.getInstance(this);
        }
        return this.binder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(this.TAG, "onCreate");
        this.binder = WidgetBinder.INSTANCE.getInstance(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (CheckBoardKt.isGreatwallV61()) {
            return super.onStartCommand(intent, flags, startId);
        }
        WidgetService widgetService = this;
        NotificationCompat.Builder ongoing = new NotificationHelper(widgetService).create(NotificationHelper.Channel.OneCore).getBuilder().setSmallIcon(R.drawable.ic_navigation_white_24dp).setContentTitle(getString(R.string.app_name)).setContentText("Navigation running").setPriority(-1).setOngoing(true);
        Intent intent2 = new Intent();
        intent2.setClassName(getPackageName(), "com.neusoft.na.mots.MainActivity");
        intent2.setFlags(536870912);
        startForeground(2001, ongoing.setContentIntent(PendingIntent.getActivity(widgetService, 0, intent2, 67108864)).build());
        return super.onStartCommand(intent, flags, startId);
    }
}
