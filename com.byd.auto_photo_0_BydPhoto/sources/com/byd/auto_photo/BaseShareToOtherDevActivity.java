package com.byd.auto_photo;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.h.l;
import com.byd.share.DragToShareContainer;
import com.byd.share.ShareItem;
import com.byd.share.a;
import com.byd.util.k;
import com.byd.util.p;
import com.byd.util.w;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseShareToOtherDevActivity extends BaseDialogActivity implements View.OnClickListener, DragToShareContainer.c {
    private ShareItem g;
    private DragToShareContainer h;
    private com.byd.share.a i;
    private String j;
    private View[] k;
    private Handler l;
    protected TextView m;
    protected TextView n;
    protected TextView o;
    protected TextView p;
    protected View q;
    protected Bitmap r;
    private Thread s;
    private View.OnKeyListener t = new e();
    private ArrayList<Integer> u = new ArrayList<>();

    class a implements ViewTreeObserver.OnPreDrawListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            BaseShareToOtherDevActivity.this.h.l();
            return true;
        }
    }

    class b implements a.f {
        b() {
        }

        @Override // com.byd.share.a.f
        public void a() {
            w.e(BaseShareToOtherDevActivity.this, R.string.share_ivi_dev_busy);
        }

        @Override // com.byd.share.a.f
        public void b() {
            w.c("BaseShareToOtherDevActivity -> dev state change");
            BaseShareToOtherDevActivity.this.s();
        }
    }

    class c extends Thread {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseShareToOtherDevActivity.this.h.setShowBitmap(BaseShareToOtherDevActivity.this.r);
            }
        }

        c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            w.c("bitmap decode thread start : " + BaseShareToOtherDevActivity.this.j);
            try {
                Thread.sleep(0L);
                BaseShareToOtherDevActivity baseShareToOtherDevActivity = BaseShareToOtherDevActivity.this;
                baseShareToOtherDevActivity.r = l.m(baseShareToOtherDevActivity).o(BaseShareToOtherDevActivity.this.j, 500, 500, true, true, true);
                BaseShareToOtherDevActivity.this.runOnUiThread(new a());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (message.what != 1) {
                return;
            }
            BaseShareToOtherDevActivity.this.x(((Integer) message.obj).intValue());
        }
    }

    class e implements View.OnKeyListener {
        e() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66 && i != 23) {
                return false;
            }
            if (keyEvent.getAction() == 1) {
                int id = view.getId();
                w.c("onKey -> share to dev view confirm -> " + id);
                if (BaseShareToOtherDevActivity.this.h.j(id)) {
                    BaseShareToOtherDevActivity.this.x(id);
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0102 A[PHI: r8
  0x0102: PHI (r8v29 android.view.View) = (r8v9 android.view.View), (r8v18 android.view.View), (r8v27 android.view.View), (r8v39 android.view.View) binds: [B:56:0x0100, B:49:0x00d9, B:42:0x00b2, B:35:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0104 A[PHI: r8
  0x0104: PHI (r8v31 android.view.View) = (r8v9 android.view.View), (r8v18 android.view.View), (r8v27 android.view.View), (r8v39 android.view.View) binds: [B:56:0x0100, B:49:0x00d9, B:42:0x00b2, B:35:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void s() {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseShareToOtherDevActivity.s():void");
    }

    private int v() {
        int iP = this.i.p();
        if (iP == 1) {
            return R.id.dev_ivi;
        }
        if (iP == 2) {
            return R.id.dev_fse;
        }
        if (iP == 4) {
            return R.id.dev_left_rse;
        }
        if (iP == 8) {
            return R.id.dev_right_rse;
        }
        if (iP != 16) {
            return 0;
        }
        return R.id.dev_tv;
    }

    private void w(int i) {
        this.u.add(Integer.valueOf(i));
        this.g.setToIdList(this.u);
        this.i.S(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i) {
        int i2;
        this.u.clear();
        switch (i) {
            case R.id.dev_fse /* 2131230862 */:
                w.c("select dev_fse");
                i2 = 2;
                break;
            case R.id.dev_ivi /* 2131230863 */:
                w.c("select dev_ivi");
                i2 = 1;
                break;
            case R.id.dev_left_rse /* 2131230864 */:
                w.c("select dev_left_rse");
                i2 = 4;
                break;
            case R.id.dev_right_rse /* 2131230865 */:
                w.c("select dev_right_rse");
                i2 = 8;
                break;
            case R.id.dev_tv /* 2131230866 */:
                w.c("select tv");
                i2 = 16;
                break;
            default:
                return;
        }
        w(i2);
    }

    private void y() {
        Thread thread = this.s;
        if (thread != null && thread.isAlive()) {
            w.c("bitmap decode thread is running");
            return;
        }
        c cVar = new c();
        this.s = cVar;
        cVar.start();
    }

    private void z() {
        if (this.l == null) {
            HandlerThread handlerThread = new HandlerThread("share_t");
            handlerThread.start();
            this.l = new d(handlerThread.getLooper());
        }
    }

    @Override // com.byd.share.DragToShareContainer.c
    public void a(int i) {
        w.c("on Device View Select : " + i);
        this.l.obtainMessage(1, Integer.valueOf(i)).sendToTarget();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected int e() {
        return getResources().getDimensionPixelSize(R.dimen.dp_360);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected int f() {
        return getResources().getDimensionPixelSize(R.dimen.dp_610);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void h() {
        setContentView(u());
        findViewById(R.id.view_close).setOnClickListener(this);
        this.m = (TextView) findViewById(R.id.tv_title);
        this.n = (TextView) findViewById(R.id.tv_tips_content);
        DragToShareContainer dragToShareContainer = (DragToShareContainer) findViewById(R.id.drag_share_container);
        this.h = dragToShareContainer;
        dragToShareContainer.setDrawFromViewId(v());
        this.h.setOnTargetSelectListener(this);
        this.q = findViewById(R.id.container_guide);
        this.o = (TextView) findViewById(R.id.tv_share_more_dev_guide);
        this.q.setVisibility(p.d(this).h() ? 0 : 8);
        TextView textView = (TextView) findViewById(R.id.btn_guide_read);
        this.p = textView;
        textView.setOnClickListener(this);
        Bitmap bitmap = this.r;
        if (bitmap == null) {
            y();
        } else {
            this.h.setShowBitmap(bitmap);
        }
        this.k = new View[]{findViewById(R.id.dev_ivi), findViewById(R.id.dev_fse), findViewById(R.id.dev_left_rse), findViewById(R.id.dev_right_rse), findViewById(R.id.dev_tv)};
        s();
        this.h.getViewTreeObserver().addOnPreDrawListener(new a());
        w.c("ShareToOtherDev iniUi");
        this.i.R(new b());
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void j(Configuration configuration) {
        this.m.setText(R.string.title_more_dev_share);
        this.n.setText(R.string.tips_more_dev_share);
        if (this.q.getVisibility() == 0) {
            this.o.setText(R.string.share_more_dev_guide);
            this.p.setText(R.string.get_it);
        }
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void k(Configuration configuration) {
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void l(boolean z) {
        LinkedList<Integer> linkedList = new LinkedList();
        View[] viewArr = this.k;
        if (viewArr != null && viewArr.length > 0) {
            for (View view : viewArr) {
                if (view.isSelected()) {
                    linkedList.add(Integer.valueOf(view.getId()));
                }
            }
        }
        int visibility = this.q.getVisibility();
        super.l(z);
        if (linkedList.size() > 0) {
            for (Integer num : linkedList) {
                if (num != null) {
                    ViewGroup viewGroup = (ViewGroup) findViewById(num.intValue());
                    viewGroup.setSelected(true);
                    if (viewGroup.getChildCount() >= 1) {
                        View childAt = viewGroup.getChildAt(0);
                        if (childAt instanceof ImageView) {
                            ((ImageView) childAt).setImageBitmap(this.r);
                        }
                    }
                }
            }
        }
        linkedList.clear();
        this.q.setVisibility(visibility);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_guide_read) {
            this.q.setVisibility(8);
        } else {
            if (id != R.id.view_close) {
                return;
            }
            finish();
        }
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        Intent intent = getIntent();
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("paths");
        int intExtra = intent.getIntExtra("data_type", 0);
        this.j = intent.getStringExtra("cover_path");
        w.c("ShareToOtherDev onCreate coverPath : " + this.j + " type : " + intExtra + " " + hashCode());
        ShareItem shareItem = new ShareItem();
        this.g = shareItem;
        shareItem.setPaths(stringArrayListExtra).setDataType(intExtra).setFromId(com.byd.share.a.s(this).p()).setAppName("photo");
        com.byd.share.a aVarS = com.byd.share.a.s(this);
        this.i = aVarS;
        aVarS.m();
        z();
        super.onCreate(bundle);
        k.c(this).g(intExtra == 2 ? TimeManagerImpl.TIME_FORMAT_TWELVE : "11");
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onDestroy() {
        Handler handler = this.l;
        if (handler != null) {
            handler.getLooper().quit();
            this.l = null;
        }
        Thread thread = this.s;
        if (thread != null) {
            if (thread.isAlive()) {
                this.s.interrupt();
            }
            this.s = null;
        }
        super.onDestroy();
        w.c("ShareToOtherDev OnDestroy " + hashCode());
    }

    protected int u() {
        return R.layout.activity_share_to_other_dev;
    }
}
