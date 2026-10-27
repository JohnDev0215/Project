package j1;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.autoservice.R;
import com.byd.info.BtCallInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import o1.b;
import ts.car.service.androidauto.IAndroidAutoAppManager;

/* JADX INFO: compiled from: MusicModeWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements View.OnKeyListener, k1.a {
    public h V;
    public int Z;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f3448f;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public f1.b f3459k0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public IAndroidAutoAppManager f3471q0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3440b = "MusicModeWindow";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3442c = "reason";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3444d = "homekey";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3446e = "recentapps";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3450g = 1001;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3452h = 3000;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3454i = 1500;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f3456j = 1002;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f3458k = 1003;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public WindowManager f3460l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public WindowManager.LayoutParams f3462m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3464n = true;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ActivityManager f3466o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List<String> f3468p = new ArrayList();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public WindowManager f3470q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public WindowManager.LayoutParams f3472r = null;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View f3474s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConstraintLayout f3476t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f3478u = 3;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f3480v = 4;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f3482w = 5;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f3483x = 6;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f3484y = 7;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f3485z = 8;
    public final int A = 9;
    public final int B = 10;
    public final int C = 11;
    public final int D = 12;
    public final int E = 13;
    public final int F = 14;
    public final int G = 15;
    public final int H = 16;
    public final int I = 3;
    public int J = 0;
    public int K = -1;
    public int L = -1;
    public int M = 0;
    public final int N = 0;
    public final int O = 1;
    public final int P = 2;
    public final int Q = 3;
    public List<f> R = new ArrayList();
    public List<String> S = null;
    public List<Integer> T = null;
    public List<Integer> U = null;
    public final int W = 100;
    public RecyclerView X = null;
    public g Y = null;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f3439a0 = 2;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f3441b0 = false;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public int f3443c0 = -1;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f3445d0 = 0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final int f3447e0 = 1;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final int f3449f0 = 2;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f3451g0 = 4;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public int f3453h0 = 1;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f3455i0 = false;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public int f3457j0 = 0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public String f3461l0 = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public String f3463m0 = "com.ts.androidauto.app.display.AapActivity";

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public String f3465n0 = "com.ts.carplay.app";

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public String f3467o0 = "com.ts.androidauto.app";

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public String f3469p0 = "com.telenav.scoutivi.spotify.byd";

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f3473r0 = 3;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public boolean f3475s0 = true;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public List<Integer> f3477t0 = Arrays.asList(Integer.valueOf(R.mipmap.icon_public_fm), Integer.valueOf(R.mipmap.icon_public_am), Integer.valueOf(R.mipmap.icon_public_dab), Integer.valueOf(R.mipmap.icon_public_lanya), Integer.valueOf(R.mipmap.icon_public_bendi));

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public List<Integer> f3479u0 = Arrays.asList(Integer.valueOf(R.string.mode_fm_music), Integer.valueOf(R.string.mode_am_music), Integer.valueOf(R.string.mode_dab_music), Integer.valueOf(R.string.mode_bt_music), Integer.valueOf(R.string.mode_local_music));

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    @SuppressLint({"HandlerLeak"})
    public Handler f3481v0 = new c();

    /* JADX INFO: renamed from: j1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class ViewTreeObserverOnGlobalLayoutListenerC0027a implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserverOnGlobalLayoutListenerC0027a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT >= 16) {
                a.this.X.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            a aVar = a.this;
            aVar.Z = aVar.X.getWidth() / 2;
            int dimension = (int) a.this.f3448f.getResources().getDimension(R.dimen.dp_100);
            a aVar2 = a.this;
            aVar2.f3439a0 = ((aVar2.X.getWidth() / dimension) + 1) / 2;
            a.this.x();
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class b implements View.OnTouchListener {
        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            a.this.f3481v0.sendEmptyMessage(1001);
            return false;
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    Log.d("MusicModeWindow", "MSG_HIDE_WINDOW ");
                    f1.b.S0().X0();
                    if (a.this.f3460l != null) {
                        a.this.f3460l.removeView(a.this.f3474s);
                        a.this.f3460l = null;
                        a.this.J = 0;
                        a.this.K = -1;
                        a.this.L = -1;
                    }
                    break;
                case 1002:
                    a aVar = a.this;
                    aVar.E(aVar.f3473r0, false);
                    BydLogHelper.logD("MusicModeWindow", "MSG_SHOW_WINDOW scrollToCenter");
                    break;
                case 1003:
                    a aVar2 = a.this;
                    aVar2.E(aVar2.f3473r0, true);
                    BydLogHelper.logD("MusicModeWindow", "MSG_SELECT_MODE scrollToCenter mModePosition = " + a.this.f3473r0);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class d extends RecyclerView.t {
        public d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i3) {
            super.a(recyclerView, i3);
            if (i3 == 0) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int iZ1 = linearLayoutManager.Z1();
                int iC2 = linearLayoutManager.c2();
                if (a.this.f3441b0) {
                    a.this.f3441b0 = false;
                    int i4 = iZ1 + ((iC2 - iZ1) / 2);
                    a.this.R.clear();
                    if (i4 != 0) {
                        for (int i5 = i4 - 1; i5 < i4 + 2; i5++) {
                            View viewC = recyclerView.getLayoutManager().C(i5);
                            a.this.R.add(new f(i5, Math.abs(a.this.Z - (viewC.getLeft() + (viewC.getWidth() / 2)))));
                        }
                        i4 = a.y(a.this.R).f3491a;
                    }
                    a.this.E(i4, true);
                    BydLogHelper.logD("MusicModeWindow", "findView scrollToCenter");
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i3, int i4) {
            super.b(recyclerView, i3, i4);
            for (int i5 = 0; i5 < recyclerView.getChildCount(); i5++) {
                recyclerView.getChildAt(i5).invalidate();
            }
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class e implements View.OnTouchListener {
        public e() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            a.this.f3441b0 = true;
            a.this.f3481v0.removeMessages(1001);
            a.this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
            return false;
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3491a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3492b;

        public f(int i3, int i4) {
            this.f3491a = i3;
            this.f3492b = i4;
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class g extends RecyclerView.g {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3493c = 3;

        /* JADX INFO: renamed from: j1.a$g$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MusicModeWindow.java */
        public class ViewOnClickListenerC0028a implements View.OnClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f3495b;

            public ViewOnClickListenerC0028a(int i3) {
                this.f3495b = i3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.E(this.f3495b, true);
                BydLogHelper.logD("MusicModeWindow", "onClick scrollToCenter");
            }
        }

        /* JADX INFO: compiled from: MusicModeWindow.java */
        public class b extends RecyclerView.c0 {

            /* JADX INFO: renamed from: t, reason: collision with root package name */
            public TextView f3497t;

            /* JADX INFO: renamed from: u, reason: collision with root package name */
            public ImageView f3498u;

            public b(View view) {
                super(view);
                this.f3498u = (ImageView) view.findViewById(R.id.id_image);
                this.f3497t = (TextView) view.findViewById(R.id.id_tv);
            }
        }

        public g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            return a.this.S.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void j(RecyclerView.c0 c0Var, int i3) {
            b bVar = (b) c0Var;
            if (this.f3493c == i3) {
                bVar.f3497t.setTextColor(a.this.f3448f.getResources().getColor(R.color.mode_text_color_select));
            } else {
                bVar.f3497t.setTextColor(a.this.f3448f.getResources().getColor(R.color.mode_text_color_normal));
            }
            bVar.f3497t.setText((CharSequence) a.this.S.get(i3));
            bVar.f3497t.setSelected(true);
            if (((Integer) a.this.T.get(i3)).intValue() != -1) {
                bVar.f3498u.setBackground(a.this.f3448f.getDrawable(((Integer) a.this.T.get(i3)).intValue()));
            } else {
                bVar.f3498u.setBackground(null);
            }
            bVar.f1474b.setOnClickListener(new ViewOnClickListenerC0028a(i3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.c0 l(ViewGroup viewGroup, int i3) {
            return new b(LayoutInflater.from(a.this.f3448f).inflate(R.layout.mode_item, viewGroup, false));
        }

        public void u(int i3) {
            this.f3493c = i3;
            h();
        }
    }

    /* JADX INFO: compiled from: MusicModeWindow.java */
    public class h extends BroadcastReceiver {
        public h() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                String stringExtra = intent.getStringExtra("reason");
                BydLogHelper.logD("MusicModeWindow", "homeWatcherReceiver resson =" + stringExtra);
                if (!"homekey".equals(stringExtra) || a.this.f3460l == null) {
                    return;
                }
                a.this.f3460l.removeView(a.this.f3474s);
                a.this.f3460l = null;
            }
        }
    }

    public a(Context context, f1.b bVar) {
        this.f3448f = null;
        this.V = null;
        this.f3459k0 = null;
        this.f3471q0 = null;
        this.f3448f = context;
        this.f3459k0 = bVar;
        B();
        this.V = new h();
        context.registerReceiver(this.V, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.f3471q0 = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this.f3448f);
        l1.a.o().q(this);
    }

    public static f y(List<f> list) {
        f fVar = list.get(0);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (list.get(i3).f3492b <= fVar.f3492b) {
                fVar = list.get(i3);
            }
        }
        return fVar;
    }

    public void A() {
        this.f3481v0.sendEmptyMessage(1001);
    }

    public final void B() {
        View viewInflate = LayoutInflater.from(this.f3448f).inflate(R.layout.auto_select_mode, (ViewGroup) null);
        this.f3474s = viewInflate;
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R.id.rv);
        this.X = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f3448f, 0, false));
        g gVar = new g();
        this.Y = gVar;
        this.X.setAdapter(gVar);
        this.X.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0027a());
        this.f3474s.setOnKeyListener(this);
    }

    public final void C(int[] iArr, boolean z3, boolean z4) {
        for (int i3 : iArr) {
            BydLogHelper.logD("mode[i] = " + i3);
        }
        if (this.S == null) {
            this.S = new ArrayList();
        }
        if (this.T == null) {
            this.T = new ArrayList();
        }
        if (this.U == null) {
            this.U = new ArrayList();
        }
        this.S.clear();
        this.T.clear();
        this.U.clear();
        this.f3475s0 = false;
        this.f3445d0 = 0;
        this.f3439a0 = 3;
        if (iArr.length > 0 && iArr[0] == 103) {
            this.f3475s0 = true;
            this.S.add(this.f3448f.getString(R.string.mode_spotify_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_spotify));
            this.U.add(8);
        }
        if (iArr.length > 7 && iArr[7] == 104) {
            this.S.add(this.f3448f.getString(R.string.mode_amazon_music));
            this.T.add(Integer.valueOf(R.mipmap.com_byd_amazon_music));
            this.U.add(10);
        }
        if (iArr.length > 1 && iArr[1] == 11) {
            this.S.add(this.f3448f.getString(R.string.mode_fm_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_fm));
            this.U.add(3);
        }
        if (iArr.length > 2 && iArr[2] == 12) {
            this.S.add(this.f3448f.getString(R.string.mode_am_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_am));
            this.U.add(4);
        }
        if (iArr.length > 3 && iArr[3] == 13) {
            this.S.add(this.f3448f.getString(R.string.mode_dab_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_dab));
            this.U.add(5);
        }
        if (iArr.length > 4 && iArr[4] == 102) {
            this.f3445d0 = 1;
            this.S.add(this.f3448f.getString(R.string.mode_button_carplay));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_carplay));
            this.U.add(9);
        } else if (iArr.length > 4 && iArr[4] == 101) {
            this.f3445d0 = 2;
            this.S.add(this.f3448f.getString(R.string.mode_button_androidauto));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_androidauto));
            this.U.add(9);
        }
        if (iArr.length > 5 && iArr[5] == 8) {
            this.S.add(this.f3448f.getString(R.string.mode_bt_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_lanya));
            this.U.add(6);
        }
        if (iArr.length > 6 && iArr[6] == 1) {
            this.S.add(this.f3448f.getString(R.string.mode_music));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_music));
            this.U.add(7);
        }
        if (z3) {
            this.S.add(this.f3448f.getString(R.string.mode_sd));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_sdcard));
            this.U.add(11);
        }
        if (z4) {
            this.S.add(this.f3448f.getString(R.string.mode_usb));
            this.T.add(Integer.valueOf(R.mipmap.icon_public_usb));
            this.U.add(12);
        }
        if (iArr.length > 12 && iArr[12] == 109) {
            this.S.add(this.f3448f.getString(R.string.mode_deezer));
            this.T.add(Integer.valueOf(R.mipmap.com_byd_deezer_music));
            this.U.add(13);
        }
        if (iArr.length > 18 && iArr[18] == 115) {
            this.S.add(this.f3448f.getString(R.string.mode_tidal));
            this.T.add(Integer.valueOf(R.mipmap.com_byd_tidal_music));
            this.U.add(14);
        }
        if (iArr.length > 15 && iArr[15] == 112) {
            this.S.add(this.f3448f.getString(R.string.mode_melon));
            this.T.add(Integer.valueOf(R.mipmap.com_byd_melon_music));
            this.U.add(15);
        }
        if (iArr.length > 8 && iArr[8] == 105) {
            this.S.add(this.f3448f.getString(R.string.mode_flo));
            this.T.add(Integer.valueOf(R.mipmap.com_byd_flo_music));
            this.U.add(16);
        }
        for (int i4 = 0; i4 < this.f3439a0; i4++) {
            this.S.add(0, "");
            this.T.add(0, -1);
        }
        for (int i5 = 0; i5 < this.f3439a0; i5++) {
            this.S.add("");
            this.T.add(-1);
        }
    }

    public void D() {
        View view;
        WindowManager windowManager = this.f3460l;
        if (windowManager == null || (view = this.f3474s) == null) {
            return;
        }
        windowManager.removeView(view);
        this.f3460l = null;
        this.f3474s = null;
        this.f3448f.unregisterReceiver(this.V);
        this.J = 0;
        this.K = -1;
        this.L = -1;
    }

    public final void E(int i3, boolean z3) {
        BydLogHelper.logD("MusicModeWindow", "scrollToCenter position =" + i3);
        int i4 = this.f3439a0;
        if (i3 < i4) {
            i3 = i4;
        }
        if (i3 >= (this.Y.c() - this.f3439a0) - 1) {
            i3 = (this.Y.c() - this.f3439a0) - 1;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.X.getLayoutManager();
        int iZ1 = linearLayoutManager.Z1();
        int iC2 = linearLayoutManager.c2();
        View viewC = linearLayoutManager.C(i3);
        if (i3 > iC2 && viewC == null) {
            H(iC2, i3);
        } else if (i3 >= iZ1 || viewC != null) {
            viewC = linearLayoutManager.C(i3);
        } else {
            H(iZ1, i3);
        }
        BydLogHelper.logD("MusicModeWindow", "homeWatcherReceiver first =" + iZ1 + ",last =" + iC2 + ",childView =" + viewC + ",position =" + i3);
        if (z3) {
            StringBuilder sb = new StringBuilder();
            sb.append("mMusicMode.get(position-3) =");
            int i5 = i3 - 3;
            sb.append(this.U.get(i5));
            BydLogHelper.logD("MusicModeWindow", sb.toString());
            F(this.U.get(i5).intValue(), this.J);
        }
        if (viewC == null) {
            return;
        }
        this.X.n1((viewC.getLeft() - this.Z) + (viewC.getWidth() / 2), 0, null, 500);
        this.Y.u(i3);
    }

    public final void F(int i3, int i4) {
        Log.d("SDFGHJK", "mode = " + i3 + "= mCurMode = " + this.J);
        switch (i3) {
            case 3:
                this.f3473r0 = this.U.indexOf(3) + 3;
                BydLogHelper.logD("MusicModeWindow", "FM_MUSIC");
                b.c cVar = new b.c();
                cVar.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 11, 19, cVar, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 4:
                this.f3473r0 = this.U.indexOf(4) + 3;
                BydLogHelper.logD("MusicModeWindow", "AM_MUSIC");
                b.c cVar2 = new b.c();
                cVar2.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 12, 19, cVar2, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 5:
                this.f3473r0 = this.U.indexOf(5) + 3;
                BydLogHelper.logD("MusicModeWindow", "DAB_MUSIC");
                b.c cVar3 = new b.c();
                cVar3.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 13, 19, cVar3, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 6:
                this.f3473r0 = this.U.indexOf(6) + 3;
                BydLogHelper.logD("MusicModeWindow", "BLUETOOTH_MUSIC");
                b.c cVar4 = new b.c();
                cVar4.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 8, 19, cVar4, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 7:
                this.f3473r0 = this.U.indexOf(7) + 3;
                BydLogHelper.logD("MusicModeWindow", "LOCAL_MUSIC curMode = " + i4);
                b.c cVar5 = new b.c();
                cVar5.b(BYDAutoMultimediaDevice.PARAM_SOURCE, 0);
                cVar5.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 1, 19, cVar5, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 8:
                this.f3473r0 = this.U.indexOf(8) + 3;
                BydLogHelper.logD("MusicModeWindow", "SPOTIFY_MUSIC");
                b.c cVar6 = new b.c();
                cVar6.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 103, 19, cVar6, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 9:
                this.f3473r0 = this.U.indexOf(9) + 3;
                BydLogHelper.logD("MusicModeWindow", "CARPLAY_ANDROID mCpaaStatus =" + this.f3445d0);
                int i5 = this.f3445d0;
                if (i5 == 1) {
                    b.c cVar7 = new b.c();
                    cVar7.c("withui", o1.b.f4451b);
                    o1.b.c(this.f3448f, 102, 19, cVar7, null);
                } else if (i5 == 2) {
                    b.c cVar8 = new b.c();
                    String str = o1.b.f4451b;
                    cVar8.c("withui", str);
                    cVar8.c("PARAM_ANDROIDAUTO_FROM_MODE", str);
                    o1.b.c(this.f3448f, 101, 19, cVar8, null);
                }
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 10:
                this.f3473r0 = this.U.indexOf(10) + 3;
                BydLogHelper.logD("MusicModeWindow", "AMAZON_MUSIC");
                b.c cVar9 = new b.c();
                cVar9.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 104, 19, cVar9, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 11:
                this.f3473r0 = this.U.indexOf(11) + 3;
                BydLogHelper.logD("MusicModeWindow", "LOCAL_SD_MODE");
                b.c cVar10 = new b.c();
                cVar10.b(BYDAutoMultimediaDevice.PARAM_SOURCE, 2);
                cVar10.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 1, 19, cVar10, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 12:
                this.f3473r0 = this.U.indexOf(12) + 3;
                BydLogHelper.logD("MusicModeWindow", "LOCAL_USB_MODE");
                b.c cVar11 = new b.c();
                cVar11.b(BYDAutoMultimediaDevice.PARAM_SOURCE, 1);
                cVar11.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 1, 19, cVar11, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 13:
                this.f3473r0 = this.U.indexOf(13) + 3;
                BydLogHelper.logD("MusicModeWindow", "DEEZER_MUSIC");
                b.c cVar12 = new b.c();
                cVar12.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 109, 19, cVar12, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 14:
                this.f3473r0 = this.U.indexOf(14) + 3;
                BydLogHelper.logD("MusicModeWindow", "TIDAL_MUSIC");
                b.c cVar13 = new b.c();
                cVar13.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 115, 19, cVar13, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 15:
                this.f3473r0 = this.U.indexOf(15) + 3;
                BydLogHelper.logD("MusicModeWindow", "MELON_MUSIC");
                b.c cVar14 = new b.c();
                cVar14.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 112, 19, cVar14, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
            case 16:
                this.f3473r0 = this.U.indexOf(16) + 3;
                BydLogHelper.logD("MusicModeWindow", "FLO_MUSIC");
                b.c cVar15 = new b.c();
                cVar15.c("withui", o1.b.f4451b);
                o1.b.c(this.f3448f, 105, 19, cVar15, null);
                this.f3481v0.removeMessages(1001);
                this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
                break;
        }
    }

    public final void G(int i3) {
        List<Integer> list = this.U;
        if (list == null || list.size() == 0) {
            return;
        }
        this.f3455i0 = this.f3459k0.P0();
        int iI0 = this.f3459k0.I0();
        this.f3457j0 = iI0;
        if (this.f3455i0 || iI0 == 1) {
            this.f3473r0 = this.U.indexOf(9) + 3;
            return;
        }
        if (i3 == 1) {
            int i4 = this.f3443c0;
            if (i4 == 1) {
                this.f3473r0 = this.U.indexOf(12) + 3;
                return;
            } else if (i4 == 2) {
                this.f3473r0 = this.U.indexOf(11) + 3;
                return;
            } else {
                if (i4 == 0) {
                    this.f3473r0 = this.U.indexOf(7) + 3;
                }
                return;
            }
        }
        if (i3 == 8) {
            this.f3473r0 = this.U.indexOf(6) + 3;
            return;
        }
        if (i3 == 109) {
            this.f3473r0 = this.U.indexOf(13) + 3;
            return;
        }
        if (i3 == 112) {
            this.f3473r0 = this.U.indexOf(15) + 3;
            return;
        }
        if (i3 == 115) {
            this.f3473r0 = this.U.indexOf(14) + 3;
            return;
        }
        switch (i3) {
            case 11:
                this.f3473r0 = this.U.indexOf(3) + 3;
                break;
            case 12:
                this.f3473r0 = this.U.indexOf(4) + 3;
                break;
            case 13:
                this.f3473r0 = this.U.indexOf(5) + 3;
                break;
            default:
                switch (i3) {
                    case 101:
                    case 102:
                        this.f3473r0 = this.U.indexOf(9) + 3;
                        break;
                    case 103:
                        this.f3473r0 = this.U.indexOf(8) + 3;
                        break;
                    case 104:
                        this.f3473r0 = this.U.indexOf(10) + 3;
                        break;
                    case 105:
                        this.f3473r0 = this.U.indexOf(16) + 3;
                        break;
                    default:
                        z();
                        break;
                }
                break;
        }
    }

    public final void H(int i3, int i4) {
        View viewC = ((LinearLayoutManager) this.X.getLayoutManager()).C(i3);
        this.X.n1((viewC.getLeft() - this.Z) + (viewC.getWidth() / 2), 0, null, 0);
        this.Y.u(i3);
        E(i4, false);
        BydLogHelper.logD("MusicModeWindow", "setScrollToPosition scrollToCenter");
        this.f3481v0.removeMessages(1001);
        this.f3481v0.sendEmptyMessageDelayed(1001, 3000L);
    }

    @Override // k1.a
    public void b(List<BtCallInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.f3481v0.sendEmptyMessage(1001);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i3, KeyEvent keyEvent) {
        Log.d("MusicModeWindow", "onKey  keyCode =" + i3);
        return false;
    }

    public void w(boolean z3, int i3, int[] iArr, Boolean bool, Boolean bool2, int i4) {
        if (z3) {
            if (iArr == null) {
                return;
            } else {
                C(iArr, bool2.booleanValue(), bool.booleanValue());
            }
        }
        this.f3443c0 = i4;
        BydLogHelper.logD("MusicModeWindow", "musicSource = " + i3 + ", mode =" + iArr + ", usb = " + bool + ", sd = " + bool2);
        if (this.f3460l == null) {
            BydLogHelper.logD("MusicModeWindow", "createWindowView 1111  musicSource =" + i3);
            C(iArr, bool2.booleanValue(), bool.booleanValue());
            this.f3460l = (WindowManager) this.f3448f.getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 8, -2);
            this.f3462m = layoutParams;
            layoutParams.flags = 1320;
            layoutParams.flags = -2080374784;
            layoutParams.format = 1;
            layoutParams.gravity = 17;
            this.X.setBackground(this.f3448f.getDrawable(R.drawable.img_mode_bg));
            this.f3460l.addView(this.f3474s, this.f3462m);
            G(i3);
            this.f3474s.setOnTouchListener(new b());
            if (i3 == 1) {
                this.J = 0;
            }
            this.K = this.J;
        } else {
            if (this.U.size() <= 0) {
                return;
            }
            if (this.f3473r0 - 3 >= this.U.size()) {
                E(3, false);
                BydLogHelper.logD("MusicModeWindow", "createWindowView scrollToCenter");
            } else {
                Log.d("SDFGHJK", "musicSource = " + i3 + "= selectType = " + i4 + "= mCurMode = " + this.J);
                this.f3473r0 = this.f3473r0 + 1;
                this.K = this.J;
                BydLogHelper.logD("MusicModeWindow", "createWindowView  mLocalChildNum =" + this.M + ", mLastMode = " + this.K);
                if (this.f3473r0 - 3 >= this.U.size()) {
                    this.f3473r0 = 3;
                }
            }
        }
        this.L = i3;
        this.f3481v0.removeMessages(1003);
        this.f3481v0.sendEmptyMessageDelayed(1003, 100L);
    }

    public final void x() {
        this.X.l(new d());
        this.X.setOnTouchListener(new e());
    }

    public final void z() {
    }
}
