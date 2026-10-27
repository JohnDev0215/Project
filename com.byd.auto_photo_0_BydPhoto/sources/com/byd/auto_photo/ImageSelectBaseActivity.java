package com.byd.auto_photo;

import android.content.ContentUris;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.j;
import b.a.e.k;
import b.a.e.l;
import b.a.k.k;
import com.byd.bean.FileBean;
import com.byd.bean.ImageSelectFolderItem;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.util.g;
import com.byd.util.n;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.GridItemDecoration;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImageSelectBaseActivity extends SubBaseTaskActivity implements View.OnClickListener, PopupWindow.OnDismissListener {
    protected int A = 10;
    private String B;
    private ProgressDialogFragment C;
    private k D;
    protected PopupWindow E;
    protected ListView F;
    private MediaScannerConnection G;
    protected View r;
    protected RecyclerView s;
    private j t;
    protected l u;
    protected TextView v;
    protected TextView w;
    private FileBean x;
    private String y;
    protected View z;

    class a implements k.b {
        a() {
        }

        @Override // b.a.e.k.b
        public void a(FileBean fileBean) {
            ImageSelectBaseActivity.this.x = fileBean;
            if (ImageSelectBaseActivity.this.A0()) {
                Bundle extras = ImageSelectBaseActivity.this.getIntent().getExtras();
                String string = extras != null ? extras.getString("crop") : null;
                if (string != null) {
                    ImageSelectBaseActivity.this.I0(fileBean.getPath(), string.equals("circle"));
                    return;
                }
            }
            ImageSelectBaseActivity.this.o0(fileBean.getPath());
        }
    }

    class b extends com.byd.taskmanager.b.c<Boolean> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Boolean bool) {
            if (ImageSelectBaseActivity.this.D.g() == null || ImageSelectBaseActivity.this.D.g().size() <= 0) {
                w.c("MediaSelector load nothing!!!");
                w.e(ImageSelectBaseActivity.this, R.string.no_data);
                ImageSelectBaseActivity.this.v.setText(R.string.no_data);
            } else {
                w.c("MediaSelector onLoadCompleted");
                ImageSelectBaseActivity.this.t.e(ImageSelectBaseActivity.this.D.g());
                ImageSelectBaseActivity imageSelectBaseActivity = ImageSelectBaseActivity.this;
                imageSelectBaseActivity.u.d(imageSelectBaseActivity.D.i());
                ImageSelectBaseActivity imageSelectBaseActivity2 = ImageSelectBaseActivity.this;
                imageSelectBaseActivity2.v.setText(imageSelectBaseActivity2.u.getItem(0).getName());
                ImageSelectBaseActivity.this.u.g(0);
                ImageSelectBaseActivity imageSelectBaseActivity3 = ImageSelectBaseActivity.this;
                imageSelectBaseActivity3.w.setText(String.valueOf(imageSelectBaseActivity3.u.getItem(0).getChildCount()));
            }
            ImageSelectBaseActivity.this.r0();
        }

        @Override // com.byd.taskmanager.b.c, com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            ImageSelectBaseActivity.this.r0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class c implements AdapterView.OnItemClickListener {
        c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ImageSelectBaseActivity imageSelectBaseActivity = ImageSelectBaseActivity.this;
            if (i == 0) {
                imageSelectBaseActivity.t.e(ImageSelectBaseActivity.this.D.g());
            } else {
                ImageSelectBaseActivity.this.t.e(ImageSelectBaseActivity.this.D.h(imageSelectBaseActivity.u.getItem(i).getPath()));
            }
            ImageSelectBaseActivity.this.u.g(i);
            ImageSelectBaseActivity.this.K0(false);
            ImageSelectFolderItem item = ImageSelectBaseActivity.this.u.getItem(i);
            String name = item.getName();
            if (ImageSelectBaseActivity.this.B.equals(item.getPath())) {
                name = ImageSelectBaseActivity.this.getString(R.string.root_path);
            }
            ImageSelectBaseActivity.this.v.setText(name);
            ImageSelectBaseActivity imageSelectBaseActivity2 = ImageSelectBaseActivity.this;
            imageSelectBaseActivity2.w.setText(String.valueOf(imageSelectBaseActivity2.u.getItem(i).getChildCount()));
        }
    }

    class d implements MediaScannerConnection.MediaScannerConnectionClient {
        d() {
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            ImageSelectBaseActivity.this.G.scanFile(ImageSelectBaseActivity.this.y, null);
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            w.c("ImageSelect onScanCompleted:" + str + " " + uri);
            ImageSelectBaseActivity.this.o0(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A0() {
        String action = getIntent().getAction();
        return "android.intent.action.PICK".equals(action) || "android.intent.action.GET_CONTENT".equals(action);
    }

    private boolean B0(String str) {
        return str.equals("vnd.android.cursor.dir/video") || str.startsWith("video/");
    }

    private void C0() {
        String strResolveType = getIntent().resolveType(this);
        this.D = new b.a.k.k(this, strResolveType == null || !B0(strResolveType));
        G0();
        v(0, this.D, new b(), true).w(0);
    }

    private boolean E0(Uri uri, String str, String str2) {
        Uri uriBuildDocumentUri = DocumentsContract.buildDocumentUri("com.android.providers.media.documents", str2 + ":" + ContentUris.parseId(uri));
        StringBuilder sb = new StringBuilder();
        sb.append("document uri : ");
        sb.append(uriBuildDocumentUri);
        w.c(sb.toString());
        try {
            Intent intent = new Intent();
            intent.setDataAndType(uriBuildDocumentUri, str);
            intent.addFlags(3);
            setResult(-1, intent);
            finish();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            w.c("back document uri fail : " + e.getMessage());
            return false;
        }
    }

    private void G0() {
        if (this.C == null) {
            this.C = new ProgressDialogFragment(R.string.loading_please_wait);
        }
        if (this.C.isShowing()) {
            return;
        }
        this.C.show(getSupportFragmentManager(), "media_select_loading");
    }

    private void H0() {
        if (this.G == null) {
            this.G = new MediaScannerConnection(this, new d());
        }
        if (this.G.isConnected()) {
            this.G.scanFile(this.y, null);
        } else {
            this.G.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0(String str, boolean z) {
        String strO;
        Intent intent = new Intent(this, (Class<?>) CropActivity.class);
        intent.setDataAndType(Uri.fromFile(new File(str)), "image/*");
        String str2 = getExternalCacheDir().getPath() + "/editcaches";
        if (A0()) {
            strO = g.j(this) + "/IMG_" + System.currentTimeMillis() + ".jpg";
        } else {
            strO = n.o(str2);
        }
        this.y = strO;
        intent.putExtra("output", Uri.parse(this.y));
        if (z) {
            intent.putExtra("circleCrop", true);
        }
        startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void o0(java.lang.String r7) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.ImageSelectBaseActivity.o0(java.lang.String):void");
    }

    private boolean q0() {
        PopupWindow popupWindow = this.E;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return false;
        }
        this.E.dismiss();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r0() {
        ProgressDialogFragment progressDialogFragment = this.C;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.C = null;
        }
    }

    private boolean z0(String str) {
        return str.equals("vnd.android.cursor.dir/image") || str.startsWith("image/");
    }

    protected void D0() {
        this.E.showAtLocation(this.z, 80, 0, -this.r.getMeasuredHeight());
    }

    protected void F0() {
        if (this.E == null) {
            y0();
        }
        D0();
    }

    protected void J0() {
        boolean z;
        PopupWindow popupWindow = this.E;
        if (popupWindow == null || !popupWindow.isShowing()) {
            if (this.u.getCount() == 0) {
                this.u.d(this.D.i());
            }
            z = true;
        } else {
            z = false;
        }
        K0(z);
    }

    protected void K0(boolean z) {
        if (z) {
            F0();
        } else {
            q0();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        ImageSelectFolderItem imageSelectFolderItemF;
        setContentView(R.layout.activity_image_select);
        this.z = findViewById(R.id.main_image_select);
        this.v = (TextView) x(R.id.tv_select_folder);
        View viewFindViewById = findViewById(R.id.footer_view);
        this.r = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.w = (TextView) findViewById(R.id.tv_folder_child_count);
        this.s = (RecyclerView) x(R.id.recycler_image);
        int iT0 = t0();
        this.s.setLayoutManager(new GridLayoutManager(this, iT0));
        this.s.addItemDecoration(new GridItemDecoration());
        int iV0 = v0(iT0, this.A);
        if (this.t == null) {
            j jVar = new j(this);
            this.t = jVar;
            jVar.f(new a());
        }
        this.t.d(iV0);
        this.s.setAdapter(this.t);
        if (this.u == null) {
            this.u = p0();
        }
        if (this.t.getItemCount() <= 0 || (imageSelectFolderItemF = this.u.f()) == null) {
            return;
        }
        this.v.setText(imageSelectFolderItemF.getName());
        this.w.setText(String.valueOf(imageSelectFolderItemF.getChildCount()));
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
        this.B = g.i(this, "/");
        super.S();
        C0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        Resources resources;
        int i;
        super.T(configuration);
        b.a.k.k kVar = this.D;
        if (kVar == null || kVar.g() == null || this.D.g().size() <= 0) {
            this.v.setText(R.string.no_data);
            return;
        }
        l lVar = this.u;
        if (lVar != null) {
            ImageSelectFolderItem imageSelectFolderItem = lVar.c().get(0);
            if (this.D.j()) {
                resources = getResources();
                i = R.string.all_images;
            } else {
                resources = getResources();
                i = R.string.videos;
            }
            imageSelectFolderItem.setName(resources.getString(i));
            this.v.setText(this.u.f().getName());
            this.u.notifyDataSetChanged();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        s0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        return x.f(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            Intent intent2 = new Intent();
            if (!A0()) {
                intent2.putExtra("photo_intent_data", i2 == -1 ? this.y : this.x.getPath());
                setResult(-1, intent2);
                finish();
            } else if (i2 == -1) {
                H0();
            } else {
                o0(this.x.getPath());
            }
        }
    }

    public void onClick(View view) {
        if (this.D.g() == null || this.D.g().size() == 0) {
            w.e(this, R.string.no_data);
        } else {
            if (view.getId() != R.id.footer_view) {
                return;
            }
            J0();
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        ProgressDialogFragment progressDialogFragment = this.C;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.C = null;
        }
        b.a.k.k kVar = this.D;
        if (kVar != null) {
            kVar.e();
        }
        super.onDestroy();
        MediaScannerConnection mediaScannerConnection = this.G;
        if (mediaScannerConnection != null) {
            if (mediaScannerConnection.isConnected()) {
                this.G.disconnect();
            }
            this.G = null;
        }
    }

    public void onDismiss() {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && q0()) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    protected l p0() {
        return new l(this);
    }

    protected void s0() {
        PopupWindow popupWindow = this.E;
        boolean z = popupWindow != null && popupWindow.isShowing();
        w.c("ImageSelectActivity doConfigChange : " + z);
        if (z) {
            q0();
        }
        N();
        if (z) {
            K0(true);
        }
    }

    protected int t0() {
        return getResources().getConfiguration().orientation == 1 ? 4 : 6;
    }

    protected int u0() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    protected int v0(int i, int i2) {
        return (u0() - ((i - 1) * i2)) / i;
    }

    protected int w0() {
        return getResources().getDimensionPixelSize(R.dimen.dp_300);
    }

    protected int x0() {
        return -1;
    }

    protected void y0() {
        if (this.F == null) {
            this.F = new ListView(this);
        }
        this.F.setCacheColorHint(0);
        this.F.setAdapter((ListAdapter) this.u);
        this.F.setOnItemClickListener(new c());
        PopupWindow popupWindow = new PopupWindow(this.F, x0(), w0());
        this.E = popupWindow;
        popupWindow.setBackgroundDrawable(new ColorDrawable(-1));
        this.E.setOutsideTouchable(true);
        this.E.setFocusable(true);
        this.E.setOnDismissListener(this);
    }
}
