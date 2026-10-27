package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.bean.FileBean;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.fragment.AlertDialogFragment;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.util.n;
import com.byd.util.w;
import com.byd.util.x;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseImageEditActivity extends SubBaseTaskActivity implements View.OnClickListener {
    protected View A;
    protected View B;
    private SimpleDateFormat C;
    private boolean D;
    private Uri E;
    protected TextView F;
    protected TextView G;
    protected TextView H;
    protected TextView I;
    protected TextView J;
    protected TextView K;
    protected TextView L;
    private ProgressDialogFragment M;
    private Thread N;
    private f O;
    private Bitmap r;
    private ImageView s;
    protected String t;
    private String u;
    protected String v;
    private int w;
    private String x;
    private String y;
    private boolean z;

    class a extends com.byd.taskmanager.b.a<Bitmap> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Bitmap doTask() {
            int iP = b.a.h.a.p(BaseImageEditActivity.this.y);
            int i = iP <= 1 ? 0 : BaseImageEditActivity.this.w;
            Bitmap bitmapJ = b.a.h.a.j(BaseImageEditActivity.this.y, i, i, iP, Bitmap.Config.ARGB_8888, false);
            if (bitmapJ != null) {
                w.c("decode ok " + bitmapJ.getWidth() + " " + bitmapJ.getHeight() + " setMaxSize:" + BaseImageEditActivity.this.w);
            }
            return bitmapJ;
        }
    }

    class b extends com.byd.taskmanager.b.c<Bitmap> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Bitmap bitmap) {
            BaseImageEditActivity baseImageEditActivity = BaseImageEditActivity.this;
            if (bitmap == null) {
                w.e(baseImageEditActivity, R.string.load_fail);
                BaseImageEditActivity.this.finish();
            } else {
                baseImageEditActivity.r = bitmap;
                BaseImageEditActivity.this.s.setImageBitmap(BaseImageEditActivity.this.r);
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            w.e(BaseImageEditActivity.this, R.string.file_no_found);
            BaseImageEditActivity.this.finish();
        }
    }

    class d implements AlertDialogFragment.OnAlertDialogButtonClick {
        d() {
        }

        @Override // com.byd.fragment.AlertDialogFragment.OnAlertDialogButtonClick
        public boolean onClick(int i) {
            if (i != 0) {
                BaseImageEditActivity.this.finish();
                return true;
            }
            BaseImageEditActivity.this.s0();
            return true;
        }
    }

    class e extends com.byd.taskmanager.b.a {
        e() {
        }

        @Override // com.byd.taskmanager.b.a
        protected Object doTask() {
            File[] fileArrListFiles = new File(BaseImageEditActivity.this.t).listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return null;
            }
            for (File file : fileArrListFiles) {
                file.delete();
            }
            return null;
        }
    }

    private static class f extends b.a.d<BaseImageEditActivity> {
        public f(BaseImageEditActivity baseImageEditActivity) {
            super(baseImageEditActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(BaseImageEditActivity baseImageEditActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                baseImageEditActivity.p0(message);
            } else {
                if (i != 2) {
                    return;
                }
                baseImageEditActivity.w0();
            }
        }
    }

    private class g extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f1004a;

        g(String str) {
            this.f1004a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            String str = this.f1004a + "/IMG_" + (BaseImageEditActivity.this.C.format(new Date()) + DiLinkFontUtilsConstants.FILE_PNG_SUFFIX);
            w.c("saveAndBackBitmap : " + BaseImageEditActivity.this.u + " " + str + " len:" + new File(BaseImageEditActivity.this.u).length());
            n.a(BaseImageEditActivity.this.u, str, null);
            File file = new File(str);
            n.E(file, System.currentTimeMillis());
            com.byd.util.e.N(BaseImageEditActivity.this, file);
            BaseImageEditActivity.this.O.obtainMessage(1, str).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0(Message message) {
        Intent intent = new Intent();
        intent.putExtra("photo_intent_data", (String) message.obj);
        setResult(-1, intent);
        ProgressDialogFragment progressDialogFragment = this.M;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.M = null;
        }
        a0(R.string.save_ok, true);
        finish();
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0058 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[DONT_GENERATE, FINALLY_INSNS, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean q0(java.io.InputStream r6, java.io.OutputStream r7) {
        /*
            r5 = this;
            r0 = 2048(0x800, float:2.87E-42)
            r1 = 0
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r2 = r1
        L6:
            int r3 = r6.read(r0)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r4 = -1
            if (r3 == r4) goto L12
            r7.write(r0, r1, r3)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            int r2 = r2 + r3
            goto L6
        L12:
            r7.flush()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r0.<init>()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String r3 = "copy count : "
            r0.append(r3)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r0.append(r2)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            com.byd.util.w.c(r0)     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3d
            r7.close()     // Catch: java.io.IOException -> L2d
            goto L31
        L2d:
            r7 = move-exception
            r7.printStackTrace()
        L31:
            r6.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r6 = move-exception
            r6.printStackTrace()
        L39:
            r6 = 1
            return r6
        L3b:
            r0 = move-exception
            goto L56
        L3d:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L3b
            if (r7 == 0) goto L4b
            r7.close()     // Catch: java.io.IOException -> L47
            goto L4b
        L47:
            r7 = move-exception
            r7.printStackTrace()
        L4b:
            if (r6 == 0) goto L55
            r6.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r6 = move-exception
            r6.printStackTrace()
        L55:
            return r1
        L56:
            if (r7 == 0) goto L60
            r7.close()     // Catch: java.io.IOException -> L5c
            goto L60
        L5c:
            r7 = move-exception
            r7.printStackTrace()
        L60:
            if (r6 == 0) goto L6a
            r6.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r6 = move-exception
            r6.printStackTrace()
        L6a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseImageEditActivity.q0(java.io.InputStream, java.io.OutputStream):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0() {
        if (this.D) {
            if (getIntent().getBooleanExtra("return-data", false)) {
                new Intent().putExtra("data", this.r);
            } else {
                w.c("save to content : " + this.E);
                try {
                    this.r.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(getContentResolver().openFileDescriptor(this.E, "rw").getFileDescriptor()));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    w.c("==save Exception==" + e2.getMessage());
                }
            }
            setResult(-1);
            finish();
            return;
        }
        String parent = new File(this.x).getParent();
        if (!new File(parent).exists()) {
            w.c("image edit -> target folder not exists");
            w.e(this, R.string.disk_inexistence);
            return;
        }
        ProgressDialogFragment progressDialogFragment = this.M;
        if (progressDialogFragment == null) {
            this.M = new ProgressDialogFragment(R.string.saving_tips);
        } else if (progressDialogFragment.isShowing()) {
            return;
        }
        this.M.show(getSupportFragmentManager(), "saving");
        Thread thread = this.N;
        if (thread == null || !thread.isAlive()) {
            g gVar = new g(parent);
            this.N = gVar;
            gVar.start();
        }
    }

    private String t0(Uri uri) {
        String str = this.t + "/" + System.currentTimeMillis();
        try {
            if (q0(getContentResolver().openInputStream(uri), new FileOutputStream(str))) {
                return str;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("openInputStream exception : " + e2.getMessage());
            return null;
        }
    }

    private void u0() {
        AlertDialogFragment alertDialogFragmentNewInstance = AlertDialogFragment.newInstance(R.string.give_up_modify, R.string.give_up_current_modify, R.string.save, R.string.give_up);
        alertDialogFragmentNewInstance.setOnAlertDialogButtonClick(new d());
        alertDialogFragmentNewInstance.show(getSupportFragmentManager(), "save_edit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w0() {
        File file = new File(this.u);
        if (!file.exists() || file.length() == 0) {
            w.c("tryDecodeAgainOnActivityResult fail , file not exist");
            return;
        }
        int iP = b.a.h.a.p(this.u);
        int i = iP <= 1 ? 0 : this.w;
        Bitmap bitmapH = b.a.h.a.h(this.u, i, i, iP, Bitmap.Config.ARGB_8888, false);
        this.r = bitmapH;
        this.s.setImageBitmap(bitmapH);
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        setContentView(r0());
        this.s = (ImageView) x(R.id.iv_image);
        View viewFindViewById = findViewById(R.id.btn_cancel);
        this.B = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        View viewFindViewById2 = findViewById(R.id.btn_save);
        this.A = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        this.A.setEnabled(this.z);
        TextView textView = (TextView) x(R.id.btn_crop);
        this.F = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) x(R.id.btn_filter);
        this.G = textView2;
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) x(R.id.btn_scrawl);
        this.H = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) x(R.id.btn_water_mark);
        this.I = textView4;
        textView4.setOnClickListener(this);
        TextView textView5 = (TextView) x(R.id.btn_rotate);
        this.J = textView5;
        textView5.setOnClickListener(this);
        TextView textView6 = (TextView) x(R.id.btn_masc);
        this.K = textView6;
        textView6.setOnClickListener(this);
        TextView textView7 = (TextView) x(R.id.btn_blur);
        this.L = textView7;
        textView7.setOnClickListener(this);
        Bitmap bitmap = this.r;
        if (bitmap != null) {
            this.s.setImageBitmap(bitmap);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void R() {
        if (L(this, x.e((PhotoBaseApp) getApplication()))) {
            S();
        } else {
            w.e(this, R.string.permission_denied_and_finish);
            finish();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        super.S();
        this.C = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        this.t = n.m(this) + "/editcaches";
        w.c("cache path : " + this.t);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        extras.setClassLoader(FileBean.class.getClassLoader());
        boolean zEquals = "com.android.media.action.IMAGE_EDIT".equals(intent.getAction());
        this.D = zEquals;
        if (zEquals) {
            Uri data = intent.getData();
            this.E = (Uri) intent.getParcelableExtra("output");
            String strQ = n.q(this, data);
            if (strQ == null) {
                w.c("edit from third uri : " + data);
                String strT0 = t0(data);
                this.x = strT0;
                this.y = strT0;
                if (strT0 == null) {
                    w.e(this, R.string.load_fail);
                    finish();
                    return;
                }
            } else {
                if (!new File(strQ).exists()) {
                    w.e(this, R.string.file_no_found);
                    finish();
                    return;
                }
                this.x = strQ;
                this.y = strQ;
                w.c("edit from third path : " + strQ);
            }
        } else {
            String path = ((FileBean) extras.getParcelable("photo_intent_data")).getPath();
            this.x = path;
            this.y = path;
        }
        WindowManager windowManager = (WindowManager) getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        this.w = Math.max(point.x, point.y);
        v(0, new a(), new b(), true).w(0);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        w.c("ImageEditActivity onLocaleChanged");
        this.F.setText(R.string.crop);
        this.G.setText(R.string.filter);
        this.H.setText(R.string.scrawl);
        this.I.setText(R.string.water_mark);
        this.J.setText(R.string.rotate);
        this.K.setText(R.string.masc);
        this.L.setText(R.string.blur);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        w.c("ImageEditActivity onNormalConfigurationChanged");
        N();
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        return x.f(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        w.c("ImageEditActivity resultCode : " + i2);
        if (i2 == -1) {
            if (intent != null) {
                this.r = (Bitmap) intent.getParcelableExtra("photo_intent_data");
            } else {
                if (this.v == null) {
                    return;
                }
                File file = new File(this.v);
                String path = file.getPath();
                this.u = path;
                int iP = b.a.h.a.p(path);
                int i3 = iP <= 1 ? 0 : this.w;
                Bitmap bitmapH = b.a.h.a.h(this.u, i3, i3, iP, Bitmap.Config.ARGB_8888, false);
                this.r = bitmapH;
                if (bitmapH == null) {
                    w.c("decode fail , try again");
                    this.O.sendEmptyMessageDelayed(2, 500L);
                }
                this.s.setImageBitmap(this.r);
                this.y = this.u;
                w.c("ImageEditActivity onActivityResult : " + this.u + " " + file.exists() + " " + file.length());
            }
            this.z = true;
            this.A.setEnabled(true);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.z) {
            u0();
        } else {
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Class<?> cls;
        if (com.byd.util.e.y()) {
        }
        int i = 1;
        switch (view.getId()) {
            case R.id.btn_blur /* 2131230793 */:
                cls = BlurActivity.class;
                i = 7;
                v0(cls, i, null);
                break;
            case R.id.btn_cancel /* 2131230794 */:
                if (this.z) {
                    u0();
                }
                finish();
                break;
            case R.id.btn_crop /* 2131230799 */:
                cls = CropActivity.class;
                v0(cls, i, null);
                break;
            case R.id.btn_filter /* 2131230805 */:
                cls = Filter2Activity.class;
                i = 2;
                v0(cls, i, null);
                break;
            case R.id.btn_masc /* 2131230807 */:
                cls = MosicActivity.class;
                i = 6;
                v0(cls, i, null);
                break;
            case R.id.btn_rotate /* 2131230815 */:
                Bundle bundle = new Bundle();
                bundle.putBoolean("just_rotate", true);
                v0(CropActivity.class, 5, bundle);
                break;
            case R.id.btn_save /* 2131230816 */:
                if (this.z) {
                    s0();
                }
                finish();
                break;
            case R.id.btn_scrawl /* 2131230817 */:
                cls = ScrawlActivity.class;
                i = 3;
                v0(cls, i, null);
                break;
            case R.id.btn_water_mark /* 2131230829 */:
                cls = WatermarkActivity.class;
                i = 4;
                v0(cls, i, null);
                break;
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.O = new f(this);
        super.onCreate(bundle);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        com.byd.taskmanager.a.e().d(new e());
        super.onDestroy();
        f fVar = this.O;
        if (fVar != null) {
            fVar.removeCallbacksAndMessages(null);
            this.O.release();
            this.O = null;
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
        String str2 = this.x;
        if (str2 != null && str2.startsWith(str)) {
            runOnUiThread(new c());
        }
    }

    protected int r0() {
        return R.layout.activity_image_edit;
    }

    protected void v0(Class<?> cls, int i, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.setDataAndType(Uri.fromFile(new File(this.y)), "image/*");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        String strO = n.o(this.t);
        this.v = strO;
        intent.putExtra("output", Uri.parse(strO));
        startActivityForResult(intent, i);
    }
}
