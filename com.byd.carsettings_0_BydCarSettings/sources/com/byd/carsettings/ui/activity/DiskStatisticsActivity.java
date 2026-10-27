package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.j;
import b.b.a.h.m;
import b.b.g.r.i;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.DiskSize;
import com.byd.carsettings.system.BydStorageManager;
import com.byd.carsettings.system.model.DiskType;
import com.byd.carsettings.system.model.UFlashInfo;
import com.byd.carsettings.ui.activity.ExternalStorageAdapter;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.mvp.ui.widget.DiskStatisticsView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DiskStatisticsActivity extends DialogActivity<b.b.g.n.b> {
    public TextView A;
    public TextView B;
    public TextView D;
    public TextView E;
    public TextView F;
    public long M;
    public List<UFlashInfo> N;
    public RecyclerView O;
    public ExternalStorageAdapter P;
    public UFlashInfo Q;
    public ScrollView R;
    public DiskStatisticsView u;
    public TextView v;
    public TextView w;
    public TextView x;
    public TextView y;
    public TextView z;
    public long G = -1;
    public long H = -1;
    public long I = -1;
    public long J = -1;
    public long K = -1;
    public long L = -1;
    public boolean S = false;

    public class a extends b.b.a.j.d.a {
        public a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            DiskStatisticsActivity.this.X();
        }
    }

    public class b implements ExternalStorageAdapter.c {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.carsettings.ui.activity.ExternalStorageAdapter.c
        public void a(UFlashInfo uFlashInfo) {
            b.b.m.c.a.a(DiskStatisticsActivity.this.f3705c, "---removeFlashDisk--run--flashInfo: " + uFlashInfo);
            DiskStatisticsActivity.this.S = true;
            ((b.b.g.n.b) DiskStatisticsActivity.this.mo7getP()).W(uFlashInfo.getVolId());
            uFlashInfo.diskState = 1;
            DiskStatisticsActivity.this.Q = uFlashInfo;
            DiskStatisticsActivity.this.P.notifyDataSetChanged();
        }

        @Override // com.byd.carsettings.ui.activity.ExternalStorageAdapter.c
        public void b(UFlashInfo uFlashInfo) {
            if (uFlashInfo.diskState == 2) {
                DiskStatisticsActivity.this.Q = uFlashInfo;
                SimpleDialog.z("", DiskStatisticsActivity.this.getString(uFlashInfo.getType() == DiskType.USB ? R.string.dilink_remount_msg : R.string.dilink_remount_sd_msg, new Object[]{uFlashInfo.getName(), uFlashInfo.getName()}), DiskStatisticsActivity.this.getString(R.string.dilink_usb_mount), DiskStatisticsActivity.this.getString(R.string.dialog_btn_cancel), 101).show(DiskStatisticsActivity.this.getSupportFragmentManager(), "SimpleDialog");
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DiskStatisticsActivity.this.R.fullScroll(130);
        }
    }

    public class d implements Runnable {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            DiskStatisticsActivity.this.F(33, 260, Long.valueOf(BydStorageManager.k(DiskStatisticsActivity.this)));
            ((b.b.g.n.b) DiskStatisticsActivity.this.mo7getP()).M(33, 257);
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.activity_disk_statistics_r : R.layout.activity_disk_statistics;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
        ((b.b.g.n.b) mo7getP()).init(context);
        this.u = (DiskStatisticsView) findViewById(R.id.dsv_disk_statistics);
        this.v = (TextView) findViewById(R.id.tv_size_used_disk_statistics);
        this.w = (TextView) findViewById(R.id.tv_size_not_used_disk_statistics);
        this.x = (TextView) findViewById(R.id.tv_total_size_disk_statistics);
        this.y = (TextView) findViewById(R.id.tv_available_size_disk_statistics);
        this.z = (TextView) findViewById(R.id.tv_app_size_disk_statistics);
        this.A = (TextView) findViewById(R.id.tv_media_size_disk_statistics);
        this.B = (TextView) findViewById(R.id.tv_download_size_disk_statistics);
        this.D = (TextView) findViewById(R.id.tv_cache_size_disk_statistics);
        this.E = (TextView) findViewById(R.id.tv_other_size_disk_statistics);
        this.F = (TextView) findViewById(R.id.tv_sys_size_disk_statistics);
        findViewById(R.id.iv_back_disk_statistics).setOnClickListener(this);
        findViewById(R.id.btn_clear_cache_disk_statistics).setOnClickListener(new a());
        this.O = (RecyclerView) findViewById(R.id.rv_external_storage);
        Y();
        EventBus.getDefault().register(this);
    }

    public final void W(long j, long j2, long j3, long j4, long j5, long j6) {
        if (j == -1 || j2 == -1 || j3 == -1 || j4 == -1 || j5 == -1 || j6 == -1) {
            b.b.m.c.a.a(this.f3705c, "---calculateOtherSize--- invalid size: return!!");
            return;
        }
        long j7 = (((((j - j2) - j3) - j4) - j5) - j6) - this.M;
        b.b.m.c.a.a(this.f3705c, "---calculateOtherSize--- total: " + j + ", " + j2);
        long j8 = j3 + j4 + j5 + j6 + this.M;
        long j9 = this.G;
        if (j8 > j9) {
            this.u.setTotal(j8 + this.H);
        } else {
            this.u.setTotal(j9);
        }
        DiskStatisticsView.b bVarD = this.u.d(4);
        if (j7 <= 0) {
            j7 = 0;
        }
        bVarD.f3797a = j7;
        this.E.setText(i.c(j7));
        this.u.requestLayout();
    }

    public final void X() {
        b.b.m.c.a.a(this.f3705c, "---clearCache---");
        SimpleDialog.z(getString(R.string.title_clear_app_cache), getString(R.string.msg_clear_app_cache), getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel), 108).show(getSupportFragmentManager(), "SimpleDialog");
    }

    public final void Y() {
        ScrollView scrollView = (ScrollView) findViewById(R.id.disk_sv);
        this.R = scrollView;
        m.c(scrollView);
        this.O.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        this.N = new ArrayList();
        ExternalStorageAdapter externalStorageAdapter = new ExternalStorageAdapter(this.O.getContext(), this.N);
        this.P = externalStorageAdapter;
        this.O.setAdapter(externalStorageAdapter);
        this.P.f(new b());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.b newP() {
        return new b.b.g.n.b();
    }

    @RequiresApi(api = 29)
    public final void a0() {
        b.b.m.c.a.a(this.f3705c, "---onClearCacheCompleted---");
        b.b.m.f.d.d.c(getClass().getName());
        b.b.m.g.b.a(new d());
    }

    public final void b0(Message message) {
        Long l = (Long) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedAppDataSize---size: " + l);
        if (l != null) {
            this.I = l.longValue();
            DiskStatisticsView.b bVarD = this.u.d(0);
            bVarD.f3797a = l.longValue();
            this.u.requestLayout();
            this.z.setText(i.c(bVarD.f3797a));
        }
    }

    public final void c0(Message message) {
        Long l = (Long) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedCacheSize---size: " + l);
        if (l != null) {
            this.L = l.longValue();
            DiskStatisticsView.b bVarD = this.u.d(3);
            long jLongValue = l.longValue();
            bVarD.f3797a = jLongValue;
            this.D.setText(i.c(jLongValue));
            this.u.requestLayout();
        }
    }

    public final void d0(Message message) {
        Long l = (Long) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedDownloadSize---size: " + l);
        if (l != null) {
            this.K = l.longValue();
            DiskStatisticsView.b bVarD = this.u.d(2);
            long jLongValue = l.longValue();
            bVarD.f3797a = jLongValue;
            this.B.setText(i.c(jLongValue));
            this.u.requestLayout();
        }
    }

    public final void e0(Message message) {
        Long l = (Long) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedMediaSize---size: " + l);
        if (l != null) {
            this.J = l.longValue();
            DiskStatisticsView.b bVarD = this.u.d(1);
            long jLongValue = l.longValue();
            bVarD.f3797a = jLongValue;
            this.A.setText(i.c(jLongValue));
            this.u.requestLayout();
        }
    }

    public final void f0(Message message) {
        if (this.S) {
            this.S = false;
            b.b.m.c.a.a(this.f3705c, "---当前在执行unmount,onReceivedMounted不执行挂载数据刷新---");
            return;
        }
        this.N = (List) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedMounted--- flashList: " + this.N);
        ExternalStorageAdapter externalStorageAdapter = this.P;
        if (externalStorageAdapter != null) {
            externalStorageAdapter.g(this.N);
        }
        this.R.post(new c());
    }

    public final void g0(Message message) {
        DiskSize diskSize = (DiskSize) message.obj;
        this.G = diskSize.getTotalSzie();
        this.H = diskSize.getAvailableSize();
        b.b.m.c.a.a(this.f3705c, "---onReceivedTotalSize---total = " + i.c(this.G) + ", availableSize = " + i.c(this.H));
        this.u.setTotal(this.G);
        this.x.setText(i.c(this.G));
        this.v.setText(i.c(this.G - this.H));
        this.w.setText(i.c(this.H));
        long realTotalSize = this.G - diskSize.getRealTotalSize();
        this.M = realTotalSize;
        this.F.setText(i.c(realTotalSize));
        this.y.setText(i.c(this.H));
        DiskStatisticsView.b bVarD = this.u.d(5);
        long j = this.M;
        if (j <= 0) {
            j = 0;
        }
        bVarD.f3797a = j;
        this.u.requestLayout();
    }

    public final void h0(Message message) {
        this.N = (List) message.obj;
        b.b.m.c.a.a(this.f3705c, "---onReceivedUFlashInfo--- flashList: " + this.N);
        ExternalStorageAdapter externalStorageAdapter = this.P;
        if (externalStorageAdapter != null) {
            externalStorageAdapter.g(this.N);
        }
        this.R.scrollTo(0, 0);
    }

    public final void i0() {
        b.b.m.c.a.a(this.f3705c, "---onStartClearCache---");
        b.b.g.q.d.u.b.o(this, getString(R.string.msg_clearing_cache_app_manage));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void j0(Uri uri) {
        b.b.m.c.a.a(this.f3705c, "---onUFlashMounted---");
        UFlashInfo uFlashInfo = this.Q;
        if (uFlashInfo != null) {
            uFlashInfo.diskState = 0;
            this.P.notifyDataSetChanged();
        }
        ((b.b.g.n.b) mo7getP()).R(33, 264);
        this.Q = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        ((b.b.g.n.b) mo7getP()).R(33, 262);
        ((b.b.g.n.b) mo7getP()).Q(33, 256);
        DiskStatisticsView.b bVar = new DiskStatisticsView.b();
        bVar.f3798b = getColor(R.color.app_color_strip_storage);
        this.u.a(bVar);
        ((b.b.g.n.b) mo7getP()).M(33, 257);
        DiskStatisticsView.b bVar2 = new DiskStatisticsView.b();
        bVar2.f3798b = getColor(R.color.media_color_strip_storage);
        this.u.a(bVar2);
        ((b.b.g.n.b) mo7getP()).P(33, 258);
        DiskStatisticsView.b bVar3 = new DiskStatisticsView.b();
        bVar3.f3798b = getColor(R.color.download_color_strip_storage);
        this.u.a(bVar3);
        ((b.b.g.n.b) mo7getP()).O(33, 259);
        DiskStatisticsView.b bVar4 = new DiskStatisticsView.b();
        bVar4.f3798b = getColor(R.color.cache_color_strip_storage);
        this.u.a(bVar4);
        ((b.b.g.n.b) mo7getP()).N(33, 260);
        DiskStatisticsView.b bVar5 = new DiskStatisticsView.b();
        bVar5.f3798b = getColor(R.color.other_color_strip_storage);
        this.u.a(bVar5);
        DiskStatisticsView.b bVar6 = new DiskStatisticsView.b();
        bVar6.f3798b = getColor(R.color.sys_color_strip_storage);
        this.u.a(bVar6);
    }

    public void k0(Uri uri) {
        if (uri == null) {
            b.b.m.c.a.a(this.f3705c, "uri ==null");
            return;
        }
        UFlashInfo uFlashInfo = null;
        Iterator<UFlashInfo> it = this.N.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            UFlashInfo next = it.next();
            b.b.m.c.a.a(this.f3705c, "---onUFlashRemoved---info:" + next);
            if (next != null && !j.c(next.getPath()) && uri.toString().contains(next.getPath())) {
                uFlashInfo = next;
                break;
            }
        }
        if (uFlashInfo != null && !this.S) {
            this.P.e(uFlashInfo);
        }
        this.S = false;
    }

    public void l0(Uri uri) {
        ExternalStorageAdapter externalStorageAdapter;
        b.b.m.c.a.a(this.f3705c, "---onUFlashUnmounted---uri:" + uri + ",thread:" + Thread.currentThread());
        if (uri == null || (externalStorageAdapter = this.P) == null) {
            return;
        }
        UFlashInfo uFlashInfo = this.Q;
        if (uFlashInfo == null) {
            Iterator<UFlashInfo> it = this.N.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UFlashInfo next = it.next();
                b.b.m.c.a.a(this.f3705c, "---onUFlashUnmounted---info:" + next);
                if (next != null && !j.c(next.getPath()) && uri.toString().contains(next.getPath())) {
                    this.Q = next;
                    next.diskState = 2;
                    this.P.notifyDataSetChanged();
                    break;
                }
            }
        } else {
            uFlashInfo.diskState = 2;
            externalStorageAdapter.notifyDataSetChanged();
        }
        this.Q = null;
        this.S = false;
    }

    @Override // com.byd.mvp.mvp.XActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() != R.id.iv_back_disk_statistics) {
            return;
        }
        finish();
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b.b.m.f.d.d.c(getClass().getName());
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() != 101) {
            if (aVar.h() == 108) {
                b.b.m.c.a.a(this.f3705c, "---clearCache--onOKClicked---");
                ((b.b.g.n.b) mo7getP()).L(33, 17, 18);
                return;
            }
            return;
        }
        b.b.m.c.a.a(getClass().getName(), "已弹出外设，重新挂载");
        UFlashInfo uFlashInfo = this.Q;
        if (uFlashInfo == null || uFlashInfo.diskState != 2) {
            return;
        }
        uFlashInfo.diskState = 3;
        this.P.notifyDataSetChanged();
        b.b.m.c.a.a(this.f3705c, "---mountFlashDisk--run--flashInfo: " + this.Q);
        ((b.b.g.n.b) mo7getP()).S(this.Q.getVolId());
    }

    @Override // com.byd.mvp.mvp.XActivity, android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.Q = (UFlashInfo) bundle.getSerializable("UFlashInfo");
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        UFlashInfo uFlashInfo = this.Q;
        if (uFlashInfo != null) {
            bundle.putSerializable("UFlashInfo", uFlashInfo);
        }
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void w(Message message) {
        int i = message.arg1;
        if (i == 17) {
            i0();
        }
        if (i == 18) {
            a0();
            return;
        }
        switch (i) {
            case 256:
                g0(message);
                break;
            case 257:
                b0(message);
                break;
            case 258:
                e0(message);
                break;
            case 259:
                d0(message);
                break;
            case 260:
                c0(message);
                break;
            default:
                switch (i) {
                    case 262:
                        h0(message);
                        break;
                    case 263:
                        W(this.G, this.H, this.I, this.J, this.K, this.L);
                        break;
                    case 264:
                        f0(message);
                        break;
                }
                break;
        }
    }
}
