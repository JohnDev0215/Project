package com.byd.carsettings.ui.dialog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import b.b.a.f.h;
import b.b.g.r.i;
import com.byd.base.view.dialog.FrameDialogView;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class AppDetailStorageView extends FrameDialogView implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List<String> f2961e = Arrays.asList("com.byd.vrsettings", "com.telenav.scoutivi.spotify.byd", "com.fce.onlinemedia.deezer", "com.fce.onlinemedia.tidal", "com.fce.onlinemedia.melon", "com.fce.onlinemedia.tunein", "com.fce.onlinemedia.audiothek", "com.fce.onlinemedia.pocketcasts", "com.fce.onlinemedia.iheartradio", "com.fce.onlinemedia.audials", "com.fce.onlinemedia.radioline", "com.fce.onlinemedia.libby", "com.fce.onlinemedia.audible", "com.fce.onlinemedia.musicmate", "com.fce.onlinemedia.bloomberg", "com.android.car.media", "com.byd.music.kr");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CustomViewDialog f2962f;
    public TextView g;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public TextView l;
    public TextView m;
    public long n;
    public long o;
    public long p;
    public String q;
    public String r;

    public class a extends b.b.a.j.d.a {

        /* JADX INFO: renamed from: com.byd.carsettings.ui.dialog.AppDetailStorageView$a$a, reason: collision with other inner class name */
        public class RunnableC0095a implements Runnable {
            public RunnableC0095a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppDetailStorageView.this.m.setClickable(true);
            }
        }

        public a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            AppDetailStorageView.this.m.setClickable(false);
            AppDetailStorageView.this.A();
            h.a().g(new RunnableC0095a(), 500L);
        }
    }

    public class b extends b.b.a.j.d.a {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppDetailStorageView.this.l.setClickable(true);
            }
        }

        public b() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            AppDetailStorageView.this.l.setClickable(false);
            AppDetailStorageView.this.z();
            h.a().g(new a(), 500L);
        }
    }

    public class c extends b.b.g.p.j.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2967a;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppDetailStorageView appDetailStorageView = AppDetailStorageView.this;
                appDetailStorageView.C(appDetailStorageView.p, AppDetailStorageView.this.n, AppDetailStorageView.this.o);
            }
        }

        public c(String str) {
            this.f2967a = str;
        }

        public void onRemoveCompleted(String str, boolean z) throws RemoteException {
            b.b.m.c.a.a(AppDetailStorageView.this.f2486a, "清除缓存结果-----packageName:" + str + ",succeeded:" + z);
            AppDetailStorageView appDetailStorageView = AppDetailStorageView.this;
            appDetailStorageView.o = b.b.g.p.h.i(appDetailStorageView.getContext(), this.f2967a)[1];
            if (AppDetailStorageView.this.f2962f == null || AppDetailStorageView.this.f2962f.getActivity() == null) {
                return;
            }
            AppDetailStorageView.this.f2962f.getActivity().runOnUiThread(new a());
        }
    }

    public class d extends b.b.g.p.j.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2970a;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppDetailStorageView appDetailStorageView = AppDetailStorageView.this;
                appDetailStorageView.C(appDetailStorageView.p, AppDetailStorageView.this.n, AppDetailStorageView.this.o);
            }
        }

        public d(String str) {
            this.f2970a = str;
        }

        public void onRemoveCompleted(String str, boolean z) throws RemoteException {
            b.b.m.c.a.a(AppDetailStorageView.this.f2486a, "清除数据结果-----------packageName:" + str + ",succeeded:" + z);
            long[] jArrI = b.b.g.p.h.i(AppDetailStorageView.this.getContext(), this.f2970a);
            AppDetailStorageView.this.n = jArrI[2];
            AppDetailStorageView.this.o = jArrI[1];
            if (AppDetailStorageView.this.f2962f != null && AppDetailStorageView.this.f2962f.getActivity() != null) {
                AppDetailStorageView.this.f2962f.getActivity().runOnUiThread(new a());
            }
            AppDetailStorageView.this.y(this.f2970a);
            if (z) {
                Intent intent = new Intent("com.byd.system.clearAppData");
                intent.setComponent(new ComponentName("com.android.systemui", "com.byd.card.view.broadcast.CardReceiver"));
                intent.putExtra("package", str);
                AppDetailStorageView.this.getContext().sendBroadcast(intent);
                b.b.m.c.a.a(AppDetailStorageView.this.f2486a, "onRemoveCompleted sendBroadcast com.byd.system.clearAppData");
            }
        }
    }

    public AppDetailStorageView(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.f2962f = customViewDialog;
        customViewDialog.w();
    }

    public final void A() {
        SimpleDialog.z(getResources().getString(R.string.title_clear_app_data), getResources().getString(R.string.msg_clear_app_data), getResources().getString(R.string.dialog_btn_ok), getResources().getString(R.string.dialog_btn_cancel), 102).show(this.f2962f.getFragmentManager(), "SimpleDialog");
    }

    public final void B() {
        String string = getContext().getString(R.string.storage_app_details);
        this.g.setText(this.q + "-" + string);
        b.b.m.c.a.a(this.f2486a, "清除前数据");
        String str = this.r;
        long[] jArrI = b.b.g.p.h.i(getContext(), str);
        C(jArrI[0], jArrI[2], jArrI[1]);
        this.l.setEnabled(!"com.byd.carsettings".equals(str));
        if (f2961e.contains(str)) {
            this.l.setEnabled(false);
            this.m.setEnabled(false);
        }
        if ("com.byd.hvac".equals(str)) {
            this.l.setEnabled(false);
        }
    }

    public final void C(long j, long j2, long j3) {
        this.p = j;
        this.n = j2;
        this.o = j3;
        long j4 = j + j2 + j3;
        this.h.setText(i.c(j4));
        this.i.setText(i.c(j));
        this.j.setText(i.c(j2));
        this.k.setText(i.c(j3));
        if (j2 == 0) {
            this.l.setEnabled(false);
        }
        if (j3 == 0) {
            this.m.setEnabled(false);
        }
        b.b.m.c.a.a(this.f2486a, "总计：" + j4 + ",应用：" + j + ",数据：" + j2 + ",缓存：" + j3);
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public void a(View view) {
        this.g = (TextView) view.findViewById(R.id.tv_title_dialog_app_storage);
        this.h = (TextView) view.findViewById(R.id.tv_total_storage_dialog_app_storage);
        this.i = (TextView) view.findViewById(R.id.tv_code_storage_dialog_app_storage);
        this.j = (TextView) view.findViewById(R.id.tv_data_storage_dialog_app_storage);
        this.k = (TextView) view.findViewById(R.id.tv_cache_storage_dialog_app_storage);
        this.l = (TextView) view.findViewById(R.id.btn_clear_app_data_dialog_app_storage);
        this.m = (TextView) view.findViewById(R.id.btn_clear_app_cache_dialog_app_storage);
        view.findViewById(R.id.iv_back_dialog_app_storage).setOnClickListener(this);
        this.l.setOnClickListener(new a());
        this.m.setOnClickListener(new b());
    }

    @Override // com.byd.base.view.dialog.FrameDialogView
    public int getDialogViewRes() {
        return R.layout.dialog_app_storage;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f2962f.dismiss();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 102) {
            b.b.m.c.a.a(getClass().getName(), "CLEAR_APP_DATA_MSG_ID");
            x(this.r);
        } else if (aVar.h() == 103) {
            b.b.m.c.a.a(getClass().getName(), "CLEAR_APP_CACHE_MSG_ID");
            w(this.r);
        }
    }

    public void setData(String str, String str2) {
        BydAppInfo bydAppInfoK = b.b.g.p.h.k(getContext(), str2);
        this.q = str;
        if (bydAppInfoK != null) {
            this.q = bydAppInfoK.getAppName();
        }
        this.r = str2;
        B();
    }

    public void w(String str) {
        b.b.g.p.h.b(getContext(), str, new c(str));
    }

    public void x(String str) {
        b.b.g.p.h.c(getContext(), str, new d(str));
    }

    public final void y(String str) {
        if ("com.neusoft.na.navigation".equals(str) || "com.huawei.maps.auto.app".equals(str) || "com.byd.automap".equals(str) || "net.zmap.znav.byd8155".equals(str) || "com.mappls.auto.bydznav8155F".equals(str)) {
            b.b.g.p.h.d();
        }
    }

    public final void z() {
        SimpleDialog.z(getResources().getString(R.string.title_clear_app_cache), getResources().getString(R.string.msg_clear_app_cache), getResources().getString(R.string.dialog_btn_ok), getResources().getString(R.string.dialog_btn_cancel), 103).show(this.f2962f.getFragmentManager(), "SimpleDialog");
    }
}
