package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.filter2.d;
import com.byd.filter2.h.d;
import com.byd.filter2.h.e;
import com.byd.filter2.h.f;
import com.byd.filter2.h.h;
import com.byd.filter2.h.i;
import com.byd.filter2.h.j;
import com.byd.util.n;
import com.byd.util.w;
import java.io.File;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseVideoEditExportActivity extends BaseDialogActivity implements View.OnClickListener, d, e {
    private boolean A;
    private com.byd.filter2.h.d B;
    private f C = new c();
    private String g;
    private i h;
    private h i;
    private int j;
    private int k;
    private int l;
    private long m;
    private TextView n;
    private int o;
    private String p;
    private int q;
    private Rect r;
    private long s;
    private long t;
    private long u;
    private String v;
    private TextView w;
    private TextView x;
    private TextView y;
    private ProgressBar z;

    class a extends com.byd.taskmanager.b.a {
        a() {
        }

        @Override // com.byd.taskmanager.b.a
        protected Object doTask() {
            File[] fileArrListFiles = new File(BaseVideoEditExportActivity.this.v).listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return null;
            }
            for (File file : fileArrListFiles) {
                file.delete();
            }
            return null;
        }
    }

    class b implements d.InterfaceC0078d {
        b() {
        }

        @Override // com.byd.filter2.h.d.InterfaceC0078d
        public void a(String str) {
            boolean zDelete = new File(BaseVideoEditExportActivity.this.p).delete();
            if (BaseVideoEditExportActivity.this.A) {
                w.c("mix cancel !!!");
                return;
            }
            w.c("onMixFinish : " + zDelete);
            com.byd.util.e.N(BaseVideoEditExportActivity.this, new File(str));
            b.a.f.a.e().f(new com.byd.filter2.h.a(1, str));
            BaseVideoEditExportActivity.this.finish();
        }

        @Override // com.byd.filter2.h.d.InterfaceC0078d
        public void b() {
            w.c("mix error");
            BaseVideoEditExportActivity.this.H();
        }

        @Override // com.byd.filter2.h.d.InterfaceC0078d
        public void c(int i) {
            BaseVideoEditExportActivity baseVideoEditExportActivity = BaseVideoEditExportActivity.this;
            baseVideoEditExportActivity.I(baseVideoEditExportActivity.q + i);
        }
    }

    class c implements f {
        c() {
        }

        @Override // com.byd.filter2.h.f
        public void a(long j) {
            w.c("onFrameAvailableForClip : " + j);
            BaseVideoEditExportActivity.this.i.s(BaseVideoEditExportActivity.this.j, BaseVideoEditExportActivity.this.k, BaseVideoEditExportActivity.this.p, BaseVideoEditExportActivity.this.r);
        }

        @Override // com.byd.filter2.h.f
        public void b(int i, int i2, int i3) {
            BaseVideoEditExportActivity.this.o = i3;
        }

        @Override // com.byd.filter2.h.f
        public void c() {
            w.c("Decode error");
            BaseVideoEditExportActivity.this.H();
        }

        @Override // com.byd.filter2.h.f
        public void d(int i) {
            if (BaseVideoEditExportActivity.this.o != 0) {
                BaseVideoEditExportActivity baseVideoEditExportActivity = BaseVideoEditExportActivity.this;
                baseVideoEditExportActivity.q = (i * 60) / baseVideoEditExportActivity.o;
                BaseVideoEditExportActivity baseVideoEditExportActivity2 = BaseVideoEditExportActivity.this;
                baseVideoEditExportActivity2.I(baseVideoEditExportActivity2.q);
            }
        }

        @Override // com.byd.filter2.h.f
        public void e() {
            BaseVideoEditExportActivity.this.E();
        }
    }

    private void D() {
        File file = new File(this.v);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        if (this.i.k()) {
            this.i.t();
        }
    }

    private void F() {
        j.a aVarH = j.i(this).h();
        if (aVarH != null) {
            if (aVarH.b() != 0 || aVarH.a() != 0 || aVarH.f() != 0) {
                this.i.g(j.i(this).g());
            }
            if (aVarH.e() == null || aVarH.e().i() == 0) {
                return;
            }
            this.i.g(aVarH.e());
        }
    }

    private void G() {
        this.A = true;
        E();
        com.byd.filter2.h.d dVar = this.B;
        if (dVar != null) {
            dVar.v();
            this.B = null;
        }
        b.a.f.a.e().f(new com.byd.filter2.h.a(2));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        E();
        b.a.f.a.e().f(new com.byd.filter2.h.a(0));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(int i) {
        this.n.setText(i + "%");
        this.z.setProgress(i);
    }

    @Override // com.byd.filter2.h.e
    public void b() {
        w.c("===onRecorderStart==");
        this.h.u();
    }

    @Override // com.byd.filter2.h.e
    public void c(String str, int i) {
        if (this.A) {
            w.c("stop export!!!");
            return;
        }
        String str2 = new File(this.g).getParent() + "/MOV_" + System.currentTimeMillis() + ".mp4";
        w.c("temp file : " + str + " export to : " + str2);
        long j = this.u;
        com.byd.filter2.h.d dVar = new com.byd.filter2.h.d(str2, str, j / ((long) i), this.g, this.s, j);
        this.B = dVar;
        dVar.t(40);
        this.B.s(new b());
        this.B.u();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void h() {
        setContentView(R.layout.activity_video_edit_export);
        this.w = (TextView) findViewById(R.id.tv_dialog_title);
        this.x = (TextView) findViewById(R.id.tv_export_tips_content);
        this.n = (TextView) findViewById(R.id.tv_export_progress);
        this.z = (ProgressBar) findViewById(R.id.progress_export);
        TextView textView = (TextView) findViewById(R.id.btn_cancel);
        this.y = textView;
        textView.setOnClickListener(this);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void j(Configuration configuration) {
        this.w.setText(R.string.video_edit_export_title);
        this.x.setText(R.string.video_edit_export_tips);
        this.y.setText(R.string.cancel);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void k(Configuration configuration) {
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        G();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_cancel) {
            return;
        }
        G();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        Intent intent = getIntent();
        this.g = intent.getStringExtra("path");
        this.r = (Rect) intent.getParcelableExtra("crop_rect");
        this.s = intent.getIntExtra("from_pos", 0) * 1000;
        long intExtra = intent.getIntExtra("end_pos", 0) * 1000;
        this.t = intExtra;
        this.u = intExtra - this.s;
        this.j = intent.getIntExtra("video_width", 0);
        this.k = intent.getIntExtra("video_height", 0);
        this.l = intent.getIntExtra("frame_rate", 30);
        this.m = intent.getLongExtra("duration_us", 0L);
        w.c("VideoEditExport onCreate : " + this.s + " " + this.u);
        StringBuilder sb = new StringBuilder();
        sb.append(n.m(this));
        sb.append("/editcaches");
        this.v = sb.toString();
        D();
        this.p = this.v + "/" + System.currentTimeMillis();
        h hVar = new h(this);
        this.i = hVar;
        hVar.q(this);
        this.i.p(this);
        i iVar = new i(this.i);
        this.h = iVar;
        iVar.x(this.C);
        super.onCreate(bundle);
        this.i.r();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onDestroy() {
        this.h.w();
        this.i.l();
        com.byd.taskmanager.a.e().d(new a());
        super.onDestroy();
    }

    @Override // com.byd.filter2.d
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        F();
        this.i.h(this.l);
        this.h.z(this.g, null, new Surface(this.i.j()), this.s, this.u);
    }

    @Override // com.byd.filter2.d
    public void t() {
    }
}
