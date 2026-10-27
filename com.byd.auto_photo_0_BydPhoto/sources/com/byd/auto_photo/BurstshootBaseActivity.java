package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.f;
import com.byd.bean.BurstshootItem;
import com.byd.bean.FileBean;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.fragment.AlertDialogFragment;
import com.byd.fragment.BurstshootSaveDialogFragment;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.util.d;
import com.byd.util.w;
import com.byd.view.ListItemDecoration;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BurstshootBaseActivity extends SubBaseTaskActivity implements View.OnClickListener {
    private FileBean A;
    private ProgressDialogFragment B;
    private int C;
    private int D;
    private int G;
    private int H;
    protected int I;
    private int J;
    private BurstshootSaveDialogFragment L;
    private com.byd.util.d M;
    protected TextView r;
    protected TextView s;
    protected TextView t;
    private RecyclerView u;
    private RecyclerView v;
    protected b.a.e.e w;
    protected b.a.e.e x;
    protected List<BurstshootItem> y;
    protected int z;
    protected int E = 16;
    protected int F = 9;
    private RecyclerView.OnScrollListener K = new d();
    private f.c N = new g();

    class a extends com.byd.taskmanager.b.a<List<BurstshootItem>> {

        /* JADX INFO: renamed from: com.byd.auto_photo.BurstshootBaseActivity$a$a, reason: collision with other inner class name */
        class C0028a implements FilenameFilter {
            C0028a() {
            }

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.endsWith(".jpg") || str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX);
            }
        }

        class b implements Comparator<File> {
            b() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(File file, File file2) {
                long jLastModified = file.lastModified() - file2.lastModified();
                if (jLastModified < 0) {
                    return 1;
                }
                return jLastModified > 0 ? -1 : 0;
            }
        }

        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<BurstshootItem> doTask() {
            ArrayList arrayList;
            long jCurrentTimeMillis = System.currentTimeMillis();
            File[] fileArrListFiles = new File(BurstshootBaseActivity.this.A.getPath()).getParentFile().listFiles(new C0028a());
            if (fileArrListFiles != null) {
                Arrays.sort(fileArrListFiles, new b());
                arrayList = new ArrayList(fileArrListFiles.length);
                for (int i = 0; i < fileArrListFiles.length; i++) {
                    arrayList.add(new BurstshootItem(fileArrListFiles[i].getPath()));
                    w.c("burst file name:" + fileArrListFiles[i].getName());
                }
            } else {
                arrayList = null;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            if (jCurrentTimeMillis2 < 200) {
                try {
                    Thread.sleep(200 - jCurrentTimeMillis2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return arrayList;
        }
    }

    class b extends com.byd.taskmanager.b.c<List<BurstshootItem>> {
        b() {
        }

        @Override // com.byd.taskmanager.b.c, com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            BurstshootBaseActivity.this.A0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<BurstshootItem> list) {
            BurstshootBaseActivity burstshootBaseActivity = BurstshootBaseActivity.this;
            burstshootBaseActivity.y = list;
            if (list != null) {
                burstshootBaseActivity.F0(list);
                BurstshootBaseActivity burstshootBaseActivity2 = BurstshootBaseActivity.this;
                burstshootBaseActivity2.w.k(burstshootBaseActivity2.y);
                BurstshootBaseActivity burstshootBaseActivity3 = BurstshootBaseActivity.this;
                burstshootBaseActivity3.x.k(burstshootBaseActivity3.y);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("burst load finish : ");
            sb.append(list != null ? list.size() : 0);
            w.c(sb.toString());
            BurstshootBaseActivity.this.H0();
            BurstshootBaseActivity.this.A0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class c implements ViewTreeObserver.OnPreDrawListener {
        c() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            BurstshootBaseActivity.this.v.getViewTreeObserver().removeOnPreDrawListener(this);
            BurstshootBaseActivity burstshootBaseActivity = BurstshootBaseActivity.this;
            burstshootBaseActivity.C = burstshootBaseActivity.getResources().getDisplayMetrics().widthPixels;
            BurstshootBaseActivity burstshootBaseActivity2 = BurstshootBaseActivity.this;
            burstshootBaseActivity2.D = burstshootBaseActivity2.v.getMeasuredWidth();
            BurstshootBaseActivity.this.u.addOnScrollListener(BurstshootBaseActivity.this.K);
            BurstshootBaseActivity.this.G = 0;
            if (BurstshootBaseActivity.this.H == 0) {
                return true;
            }
            BurstshootBaseActivity.this.v.smoothScrollToPosition(BurstshootBaseActivity.this.H);
            BurstshootBaseActivity burstshootBaseActivity3 = BurstshootBaseActivity.this;
            burstshootBaseActivity3.x.i(burstshootBaseActivity3.H);
            BurstshootBaseActivity.this.u.smoothScrollToPosition(BurstshootBaseActivity.this.H);
            return true;
        }
    }

    class d extends RecyclerView.OnScrollListener {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            BurstshootBaseActivity.u0(BurstshootBaseActivity.this, i);
            float f = BurstshootBaseActivity.this.G / BurstshootBaseActivity.this.J;
            int iRound = Math.round(f);
            w.c(BurstshootBaseActivity.this.G + "  offset : " + f + " " + iRound);
            if (BurstshootBaseActivity.this.C == BurstshootBaseActivity.this.D && BurstshootBaseActivity.this.getResources().getConfiguration().orientation == 2 && iRound == BurstshootBaseActivity.this.w.getItemCount() - 2 && f > iRound) {
                iRound++;
            }
            if (BurstshootBaseActivity.this.H != iRound) {
                BurstshootBaseActivity.this.H = iRound;
                BurstshootBaseActivity.this.x.i(iRound);
                BurstshootBaseActivity.this.v.scrollToPosition(iRound);
            }
        }
    }

    class e implements d.b {

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ boolean f1051a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ArrayList f1052b;

            a(boolean z, ArrayList arrayList) {
                this.f1051a = z;
                this.f1052b = arrayList;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f1051a) {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("save_imgs", this.f1052b);
                    BurstshootBaseActivity.this.setResult(-1, intent);
                    BurstshootBaseActivity.this.a0(R.string.save_ok, true);
                }
                BurstshootBaseActivity.this.finish();
            }
        }

        e() {
        }

        @Override // com.byd.util.d.b
        public void a(ArrayList<String> arrayList, boolean z) {
            w.c("BurstManager onSaveFinish : " + z);
            BurstshootBaseActivity.this.runOnUiThread(new a(z, arrayList));
        }
    }

    class f implements AlertDialogFragment.OnAlertDialogButtonClick {
        f() {
        }

        @Override // com.byd.fragment.AlertDialogFragment.OnAlertDialogButtonClick
        public boolean onClick(int i) {
            if (i != 0) {
                return false;
            }
            BurstshootBaseActivity.this.M.k();
            return true;
        }
    }

    class g implements f.c {
        g() {
        }

        @Override // b.a.e.f.c
        public void a(int i, int i2) {
            if (i == 1) {
                return;
            }
            BurstshootBaseActivity.this.C0(i2);
        }

        @Override // b.a.e.f.c
        public void b(int i, int i2) {
            BurstshootBaseActivity.this.w.h(i2);
            if (i != 1) {
                BurstshootBaseActivity.this.C0(i2);
            } else {
                BurstshootBaseActivity.this.u.smoothScrollToPosition(i2);
                BurstshootBaseActivity.this.v.smoothScrollToPosition(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0() {
        ProgressDialogFragment progressDialogFragment = this.B;
        if (progressDialogFragment != null) {
            if (progressDialogFragment.isShowing()) {
                this.B.dismissAllowingStateLoss();
            }
            this.B = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C0(int i) {
        BurstshootItem burstshootItem = this.y.get(i);
        burstshootItem.setSelect(!burstshootItem.isSelect());
        this.z = burstshootItem.isSelect() ? this.z + 1 : this.z - 1;
        this.w.notifyItemChanged(i);
        this.x.notifyItemChanged(i);
        this.u.smoothScrollToPosition(i);
        this.v.smoothScrollToPosition(i);
        H0();
    }

    private float E0() {
        int i = getResources().getConfiguration().orientation;
        int size = this.y.size();
        if (i == 1) {
            return size == 1 ? 1.0f : 1.2f;
        }
        if (size <= 0 || size > 2) {
            return 1.4f;
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0(List<BurstshootItem> list) {
        int size = list.size();
        float fE0 = E0();
        this.w.j(y0(fE0 == 2.0f ? this.I : fE0 > 2.0f ? this.I * 2 : 0, fE0));
        this.x.j((getResources().getDimensionPixelSize(R.dimen.burst_small_recycler_height) * this.E) / this.F);
        x0(this.w.d(), this.x.d());
        this.J = ((this.w.e() * size) + ((size - 1) * this.I)) / size;
        this.v.getViewTreeObserver().addOnPreDrawListener(new c());
    }

    private void G0() {
        if (this.B == null) {
            this.B = new ProgressDialogFragment(R.string.loading_please_wait);
        }
        this.B.show(getSupportFragmentManager(), "burst_manager");
        v(0, new a(), new b(), true).w(0);
    }

    private void I0() {
        if (this.M == null) {
            com.byd.util.d dVar = new com.byd.util.d();
            this.M = dVar;
            dVar.j(new e());
        }
        if (this.L == null) {
            BurstshootSaveDialogFragment burstshootSaveDialogFragment = new BurstshootSaveDialogFragment();
            this.L = burstshootSaveDialogFragment;
            burstshootSaveDialogFragment.setOnAlertDialogButtonClick(new f());
        }
        File parentFile = new File(this.A.getPath()).getParentFile();
        this.L.setNums(this.z);
        this.M.i(this, this.y, parentFile);
        this.L.show(getSupportFragmentManager(), "save");
    }

    static /* synthetic */ int u0(BurstshootBaseActivity burstshootBaseActivity, int i) {
        int i2 = burstshootBaseActivity.G + i;
        burstshootBaseActivity.G = i2;
        return i2;
    }

    protected void B0() {
        this.u.removeOnScrollListener(this.K);
        N();
        H0();
    }

    protected int D0() {
        return R.layout.activity_burstshoot_manager;
    }

    protected void H0() {
        TextView textView;
        boolean z;
        if (this.z > 0) {
            textView = this.r;
            z = true;
        } else {
            textView = this.r;
            z = false;
        }
        textView.setEnabled(z);
        this.t.setText(R.string.select);
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        setContentView(D0());
        Bundle extras = getIntent().getExtras();
        extras.setClassLoader(FileBean.class.getClassLoader());
        this.A = (FileBean) extras.getParcelable("photo_intent_data");
        TextView textView = (TextView) x(R.id.btn_save);
        this.r = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) x(R.id.btn_cancel);
        this.s = textView2;
        textView2.setOnClickListener(this);
        this.t = (TextView) x(R.id.tv_title);
        RecyclerView recyclerView = (RecyclerView) x(R.id.recycler_big_size);
        this.u = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        RecyclerView recyclerView2 = (RecyclerView) x(R.id.recycler_small_size);
        this.v = recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, 0, false));
        if (this.w == null) {
            b.a.e.e eVar = new b.a.e.e(0, this, this.E, this.F);
            this.w = eVar;
            eVar.l(this.N);
        }
        if (this.x == null) {
            b.a.e.e eVar2 = new b.a.e.e(1, this, this.E, this.F);
            this.x = eVar2;
            eVar2.l(this.N);
        }
        RecyclerView.RecycledViewPool recycledViewPool = this.u.getRecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 8);
        this.u.setRecycledViewPool(recycledViewPool);
        this.u.setHasFixedSize(true);
        new LinearSnapHelper().attachToRecyclerView(this.u);
        this.I = z0(0);
        int iZ0 = z0(1);
        this.u.addItemDecoration(new ListItemDecoration(this.I, true));
        this.v.addItemDecoration(new ListItemDecoration(iZ0, true));
        this.v.setHasFixedSize(true);
        this.u.setAdapter(this.w);
        this.v.setAdapter(this.x);
        if (this.w.getItemCount() == 0) {
            G0();
        } else {
            F0(this.y);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        if (!TextUtils.isEmpty(this.r.getText())) {
            this.r.setText(R.string.save);
        }
        if (!TextUtils.isEmpty(this.s.getText())) {
            this.s.setText(R.string.cancel);
        }
        H0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        B0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
        b0(z, true);
        this.t.setTextColor(com.byd.util.e.l(this, R.attr.text_color));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (com.byd.util.e.y()) {
            return;
        }
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            finish();
        } else {
            if (id != R.id.btn_save) {
                return;
            }
            I0();
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        com.byd.util.d dVar = this.M;
        if (dVar != null) {
            dVar.h();
            this.M = null;
        }
    }

    protected void x0(int i, int i2) {
        this.u.getLayoutParams().height = i;
        this.v.getLayoutParams().height = i2;
    }

    protected int y0(int i, float f2) {
        return (getResources().getConfiguration().orientation != 2 || this.y.size() >= 2) ? (int) ((r0 - i) / f2) : getResources().getDisplayMetrics().widthPixels;
    }

    protected int z0(int i) {
        Resources resources;
        int i2;
        if (i == 0) {
            resources = getResources();
            i2 = R.dimen.dp_15;
        } else {
            resources = getResources();
            i2 = R.dimen.dp_8;
        }
        return resources.getDimensionPixelSize(i2);
    }
}
