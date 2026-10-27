package com.byd.auto_photo;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import b.a.h.l;
import b.a.k.i;
import b.a.k.s;
import com.byd.bean.FileBean;
import com.byd.util.g;
import com.byd.util.n;
import com.byd.util.w;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhotoWidgetService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<FileBean> f1129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1130c;
    private RemoteViews d;
    private AppWidgetManager e;
    private Handler f;
    private b h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1128a = true;
    private com.byd.taskmanager.b.c<List<FileBean>> g = new a();

    class a extends com.byd.taskmanager.b.c<List<FileBean>> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<FileBean> list) throws IOException {
            PhotoWidgetService.this.f1129b = list;
            PhotoWidgetService.this.h();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        /* synthetic */ b(PhotoWidgetService photoWidgetService, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.byd.ACTION_FILE_CHANGE".equals(intent.getAction())) {
                if (g.l(context).equals(intent.getStringExtra("path"))) {
                    PhotoWidgetService.this.f();
                }
            }
        }
    }

    static class c extends com.byd.taskmanager.b.a<List<FileBean>> implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LinkedList<FileBean> f1133a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f1134b;

        c(com.byd.taskmanager.b.b<List<FileBean>> bVar, Context context) {
            super(bVar);
            this.f1134b = context;
            this.f1133a = new LinkedList<>();
        }

        @Override // b.a.k.s
        public void a(FileBean fileBean) {
            this.f1133a.add(fileBean);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<FileBean> doTask() throws InterruptedException {
            this.f1133a.clear();
            n.z(this.f1134b, g.l(this.f1134b), this);
            if (this.f1133a.size() == 0) {
                try {
                    new i(this.f1134b, true, 1).c(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            w.c("PhotoWidgetService loadData:" + this.f1133a.size());
            return this.f1133a;
        }
    }

    private static class d extends b.a.d<PhotoWidgetService> {
        public d(PhotoWidgetService photoWidgetService) {
            super(photoWidgetService);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(PhotoWidgetService photoWidgetService, Message message) throws IOException {
            if (message.what != 1) {
                return;
            }
            photoWidgetService.g();
        }
    }

    private PendingIntent e(int i) {
        Intent intent = new Intent(this, (Class<?>) PhotoWidgetService.class);
        intent.putExtra("opt", i);
        return PendingIntent.getService(this, i, intent, 134217728);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        com.byd.taskmanager.a.e().d(new c(this.g, this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() throws IOException {
        this.f1130c = this.f1130c < this.f1129b.size() + (-1) ? this.f1130c + 1 : 0;
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws IOException {
        if (this.d == null) {
            this.e = AppWidgetManager.getInstance(this);
            RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.photo_widget);
            this.d = remoteViews;
            remoteViews.setOnClickPendingIntent(R.id.iv_pre, e(1));
            this.d.setOnClickPendingIntent(R.id.iv_next, e(2));
            this.d.setOnClickPendingIntent(R.id.iv_play, e(3));
            this.d.setOnClickPendingIntent(R.id.iv_image, e(4));
        }
        Bitmap bitmapO = l.m(this).o(this.f1129b.get(this.f1130c).getPath(), 500, 500, true, true, true);
        if (bitmapO != null) {
            this.d.setImageViewBitmap(R.id.iv_image, bitmapO);
        } else {
            this.d.setImageViewResource(R.id.iv_image, R.drawable.icon_widget_def);
        }
        this.e.updateAppWidget(new ComponentName(getApplication().getPackageName(), PhotoWidget.class.getName()), this.d);
        if (this.f1128a) {
            this.f.sendEmptyMessageDelayed(1, 15000L);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        w.c("PhotoWidgetService onCreate");
        this.f = new d(this);
        this.g.registUiObject(this);
        this.h = new b(this, null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.ACTION_FILE_CHANGE");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h, intentFilter);
        f();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f.removeCallbacksAndMessages(null);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.h);
        super.onDestroy();
        this.g.unregistUiObject();
        this.h = null;
        w.c("PhotoWidgetService onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) throws IOException {
        if (intent == null || !intent.hasExtra("opt") || this.f1129b == null) {
            return 1;
        }
        int intExtra = intent.getIntExtra("opt", 0);
        if (intExtra == 1) {
            this.f.removeMessages(1);
            int size = this.f1130c;
            if (size <= 0) {
                size = this.f1129b.size();
            }
            this.f1130c = size - 1;
            h();
        } else if (intExtra == 2) {
            this.f.removeMessages(1);
            g();
        } else if (intExtra == 3) {
            if (this.f1128a) {
                this.f.removeMessages(1);
                this.f1128a = false;
                this.d.setImageViewResource(R.id.iv_play, R.drawable.icon_start);
            } else {
                this.f1128a = true;
                this.d.setImageViewResource(R.id.iv_play, R.drawable.icon_pause);
                this.f.sendEmptyMessageDelayed(1, 15000L);
            }
            this.e.updateAppWidget(new ComponentName(this, (Class<?>) PhotoWidget.class), this.d);
        } else if (intExtra == 4) {
            Intent intent2 = new Intent(this, (Class<?>) ImageShowActivity.class);
            intent2.setFlags(VehicleAreaDoor.DOOR_HOOD);
            intent2.setData(Uri.parse(this.f1129b.get(this.f1130c).getPath()));
            startActivity(intent2);
        }
        return 1;
    }
}
