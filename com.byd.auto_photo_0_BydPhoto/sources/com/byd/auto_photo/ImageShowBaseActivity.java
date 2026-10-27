package com.byd.auto_photo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Shader;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import b.a.e.m;
import b.a.e.w;
import com.byd.bean.FileBean;
import com.byd.dilink.provider.DiLinkBluetoothDeviceConstants;
import com.byd.fragment.AlertDialogFragment;
import com.byd.fragment.BaseFileDeleteDialogFragment;
import com.byd.fragment.BaseProgressDialogFragment;
import com.byd.fragment.BaseRenameFileFragment;
import com.byd.fragment.FileDeleteDialogFragment;
import com.byd.fragment.FileDetailDialogFragment;
import com.byd.fragment.ProgressDialogFragment;
import com.byd.fragment.RenameFileFragment;
import com.byd.fragment.ShareDialogFragment;
import com.byd.share.ShareItem;
import com.byd.share.d;
import com.byd.util.l;
import com.byd.util.p;
import com.byd.view.ListItemDecoration;
import com.byd.view.XImageView;
import com.byd.view.XScaleImageViewPager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImageShowBaseActivity extends SubBaseTaskActivity implements View.OnClickListener, DialogInterface.OnDismissListener, BaseProgressDialogFragment.OnDismissListener, d.e, l.i {
    private View A;
    private RenameFileFragment A0;
    protected TextView B;
    private FileDetailDialogFragment B0;
    protected View C;
    private AlertDialogFragment C0;
    protected View D;
    private FileDeleteDialogFragment D0;
    protected View E;
    private com.byd.util.l E0;
    protected TextView F;
    protected z F0;
    protected View I;
    protected int J;
    protected View K;
    private int L;
    protected TextView M;
    protected TextView N;
    protected TextView O;
    protected TextView P;
    protected TextView Q;
    protected boolean R;
    protected boolean S;
    protected boolean T;
    protected boolean U;
    private Handler V;
    private y W;
    private b0 X;
    protected com.byd.util.k Y;
    protected boolean Z;
    protected ShareItem a0;
    private RecyclerView b0;
    protected b.a.e.w c0;
    protected com.byd.share.d d0;
    private Uri e0;
    protected int f0;
    protected boolean g0;
    protected boolean h0;
    protected boolean i0;
    private ViewPager j0;
    private b.a.e.o k0;
    private volatile boolean l0;
    protected boolean m0;
    protected a0 o0;
    private boolean r0;
    private b.a.k.m s;
    protected XScaleImageViewPager t;
    protected b.a.e.n u;
    protected ProgressDialogFragment u0;
    private int v;
    private ShareDialogFragment v0;
    protected TextView w;
    private BitmapRegionDecoder w0;
    private BitmapFactory.Options x0;
    private int y;
    private Rect y0;
    protected TextView z;
    private WallpaperManager z0;
    protected boolean r = true;
    private int x = -1;
    protected int G = 0;
    protected boolean H = true;
    private String[] n0 = {BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON};
    private ViewPager.OnPageChangeListener p0 = new p();
    protected boolean q0 = true;
    protected final Runnable s0 = new q();
    private final Runnable t0 = new r();
    private List<String> G0 = new LinkedList();
    private HashMap<String, String> H0 = new HashMap<>();
    private List<String> I0 = new LinkedList();

    class a implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ XImageView f1067a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ XImageView f1068b;

        a(XImageView xImageView, XImageView xImageView2) {
            this.f1067a = xImageView;
            this.f1068b = xImageView2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.f1067a.getViewTreeObserver().removeOnPreDrawListener(this);
            this.f1067a.updateImageView(this.f1068b.getImageMatrix(), "", false);
            this.f1067a.copyOriginalState(this.f1068b);
            this.f1067a.invalidate();
            return true;
        }
    }

    private class a0 extends ContentObserver {
        public a0(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            b.a.e.n nVar;
            super.onChange(z, uri);
            if (ImageShowBaseActivity.this.r0 || uri == null) {
                return;
            }
            String strQ = com.byd.util.n.q(ImageShowBaseActivity.this, uri);
            com.byd.util.w.c("ImageShow onChange : " + strQ);
            if (strQ != null || (nVar = ImageShowBaseActivity.this.u) == null || nVar.s() || ImageShowBaseActivity.this.u.getCount() <= 0) {
                return;
            }
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            if (!imageShowBaseActivity.Z) {
                if (imageShowBaseActivity.r0) {
                    File file = new File(ImageShowBaseActivity.this.u.b().get(ImageShowBaseActivity.this.t.getCurrentItem()).getPath());
                    com.byd.util.w.c("current show item state : " + file.exists() + " " + file.length());
                    if (!file.exists() || file.length() == 0) {
                        ImageShowBaseActivity.this.F0.obtainMessage(3, file.getPath()).sendToTarget();
                        return;
                    }
                    return;
                }
                return;
            }
            List<String> listB = imageShowBaseActivity.c0.b();
            int size = listB.size();
            boolean z2 = false;
            for (int i = 0; i < size; i++) {
                File file2 = new File(listB.get(i));
                boolean zExists = file2.exists();
                long length = file2.length();
                com.byd.util.w.c(file2.getPath() + " -> exists:" + zExists + " len:" + length);
                if (!zExists || length == 0) {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                com.byd.util.w.e(ImageShowBaseActivity.this, R.string.file_error);
                ImageShowBaseActivity.this.finish();
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageShowBaseActivity.this.F1();
        }
    }

    private class b0 extends BroadcastReceiver {
        private b0() {
        }

        /* synthetic */ b0(ImageShowBaseActivity imageShowBaseActivity, k kVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            if (imageShowBaseActivity.Z) {
                str = "show from other dev";
            } else {
                if (imageShowBaseActivity.r0) {
                    String stringExtra = intent.getStringExtra("opt");
                    com.byd.util.w.c("ModifyReceiver onReceive : " + stringExtra);
                    if ("rename".equals(stringExtra)) {
                        String stringExtra2 = intent.getStringExtra("old");
                        String stringExtra3 = intent.getStringExtra("new");
                        com.byd.util.w.c("modify(rename) " + stringExtra2 + "-> " + stringExtra3 + " on other window : " + hashCode());
                        ImageShowBaseActivity.this.H0.put(stringExtra2, stringExtra3);
                        return;
                    }
                    if ("del".equals(stringExtra)) {
                        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("paths");
                        if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
                            return;
                        }
                        com.byd.util.w.c("modify(del) " + stringArrayListExtra.get(0) + " on other window : " + hashCode());
                        ImageShowBaseActivity.this.G0.addAll(stringArrayListExtra);
                        return;
                    }
                    if ("collect".equals(stringExtra)) {
                        String stringExtra4 = intent.getStringExtra("path");
                        com.byd.util.w.c("collect change : " + stringExtra4);
                        if (ImageShowBaseActivity.this.I0.contains(stringExtra4)) {
                            ImageShowBaseActivity.this.I0.remove(stringExtra4);
                            return;
                        } else {
                            ImageShowBaseActivity.this.I0.add(stringExtra4);
                            return;
                        }
                    }
                    return;
                }
                str = "modify on current window : " + hashCode();
            }
            com.byd.util.w.c(str);
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageShowBaseActivity.this.j0.setVisibility(8);
        }
    }

    private class c0 extends v {
        private c0() {
            super(ImageShowBaseActivity.this, null);
        }

        /* synthetic */ c0(ImageShowBaseActivity imageShowBaseActivity, k kVar) {
            this();
        }

        @Override // com.byd.auto_photo.ImageShowBaseActivity.v, b.a.e.m.e
        public void c(List<FileBean> list, FileBean fileBean) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(fileBean);
            super.c(arrayList, fileBean);
        }
    }

    class d implements BaseRenameFileFragment.OnRenameListener {
        d() {
        }

        @Override // com.byd.fragment.BaseRenameFileFragment.OnRenameListener
        public void onCancel() {
        }

        @Override // com.byd.fragment.BaseRenameFileFragment.OnRenameListener
        public void onRenameOk(File file, File file2) {
            b.a.h.l.m(ImageShowBaseActivity.this).w(file.getPath(), file2.getPath());
            LinkedList linkedList = new LinkedList();
            linkedList.add(file.getPath());
            com.byd.util.n.g(ImageShowBaseActivity.this, linkedList);
            com.byd.util.e.P(ImageShowBaseActivity.this, file2);
            Intent intent = new Intent("com.byd.ACTION_FILE_CHANGE");
            intent.putExtra("path", file2.getPath());
            LocalBroadcastManager.getInstance(ImageShowBaseActivity.this).sendBroadcast(intent);
            ImageShowBaseActivity.this.u.q(file.getPath(), file2.getPath());
            ImageShowBaseActivity.this.u.notifyDataSetChanged();
            if (com.byd.util.f.k(ImageShowBaseActivity.this).o(file.getPath())) {
                com.byd.util.f.k(ImageShowBaseActivity.this).x(file.getPath(), file2.getPath());
            }
            ImageShowBaseActivity.this.a0(R.string.rename_ok, true);
            ImageShowBaseActivity.this.D1(file, file2);
            ImageShowBaseActivity.H1(ImageShowBaseActivity.this, file.getPath(), file2.getPath());
        }
    }

    private class d0 extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1076a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1077b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Intent f1078c;

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ FileBean f1079a;

            a(FileBean fileBean) {
                this.f1079a = fileBean;
            }

            @Override // java.lang.Runnable
            public void run() {
                ImageShowBaseActivity.this.u.notifyDataSetChanged();
                ImageShowBaseActivity.this.K1(false);
                ImageShowBaseActivity.this.J1(this.f1079a);
                ImageShowBaseActivity.this.z.setVisibility(8);
                ImageShowBaseActivity.this.A.setVisibility(8);
                Intent intent = new Intent("com.byd.ACTION_FILE_CHANGE");
                intent.putExtra("path", "db:///collect");
                LocalBroadcastManager.getInstance(ImageShowBaseActivity.this).sendBroadcast(intent);
                LocalBroadcastManager.getInstance(ImageShowBaseActivity.this).sendBroadcast(new Intent("com.byd.ACTION_BURSTSHOOT_CHANGE"));
            }
        }

        class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f1081a;

            b(int i) {
                this.f1081a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                ImageShowBaseActivity.this.t.setCurrentItem(this.f1081a, false);
            }
        }

        d0(int i, int i2, Intent intent) {
            this.f1076a = i;
            this.f1077b = i2;
            this.f1078c = intent;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            p.b bVarE;
            int iR;
            b.a.e.n nVar = ImageShowBaseActivity.this.u;
            if (nVar == null || nVar.b() == null) {
                com.byd.util.w.c("OptResultThread -> not data!");
                return;
            }
            int i = this.f1076a;
            if (i == 1) {
                if (this.f1077b == -1) {
                    ImageShowBaseActivity.this.G1(this.f1078c.getStringExtra("photo_intent_data"));
                    return;
                }
                return;
            }
            if (i == 2) {
                if (this.f1077b == -1) {
                    ArrayList<String> stringArrayListExtra = this.f1078c.getStringArrayListExtra("save_imgs");
                    com.byd.util.w.c("ImageShowActivity onActivityResult:" + stringArrayListExtra.size());
                    int currentItem = ImageShowBaseActivity.this.t.getCurrentItem();
                    FileBean fileBean = ImageShowBaseActivity.this.u.b().get(currentItem);
                    fileBean.setType(1);
                    fileBean.setNum(0);
                    fileBean.setCollected(2);
                    File file = new File(stringArrayListExtra.get(0));
                    fileBean.setName(file.getName());
                    fileBean.setPath(file.getPath());
                    int size = stringArrayListExtra.size();
                    for (int i2 = 1; i2 < size; i2++) {
                        File file2 = new File(stringArrayListExtra.get(i2));
                        ImageShowBaseActivity.this.u.b().add(currentItem, new FileBean(file2.getName(), file2.getPath(), fileBean.getSourceType(), 1, fileBean.getLastModifiedTime(), fileBean.getLat(), fileBean.getLng(), false));
                    }
                    ImageShowBaseActivity.this.runOnUiThread(new a(fileBean));
                    return;
                }
                return;
            }
            if (i == 3 && (bVarE = com.byd.util.p.d(ImageShowBaseActivity.this).e()) != null) {
                int currentItem2 = ImageShowBaseActivity.this.t.getCurrentItem();
                try {
                    FileBean fileBean2 = ImageShowBaseActivity.this.u.b().get(currentItem2);
                    String canonicalPath = new File(bVarE.a()).getCanonicalPath();
                    String canonicalPath2 = new File(fileBean2.getPath()).getCanonicalPath();
                    com.byd.util.w.c("last play : " + bVarE.a() + " current : " + fileBean2.getPath());
                    StringBuilder sb = new StringBuilder();
                    sb.append(canonicalPath);
                    sb.append(" -- ");
                    sb.append(canonicalPath2);
                    com.byd.util.w.c(sb.toString());
                    if (bVarE.a().equals(fileBean2.getPath()) || canonicalPath.equals(canonicalPath2) || (iR = ImageShowBaseActivity.this.u.r(bVarE.a(), currentItem2)) == currentItem2) {
                        return;
                    }
                    ImageShowBaseActivity.this.runOnUiThread(new b(iR));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class e implements AlertDialogFragment.OnAlertDialogButtonClick {
        e() {
        }

        @Override // com.byd.fragment.AlertDialogFragment.OnAlertDialogButtonClick
        public boolean onClick(int i) {
            if (i != 0) {
                return true;
            }
            ImageShowBaseActivity.this.g1();
            return true;
        }
    }

    class f extends Thread {

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f1085a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ FileBean f1086b;

            a(int i, FileBean fileBean) {
                this.f1085a = i;
                this.f1086b = fileBean;
            }

            @Override // java.lang.Runnable
            public void run() {
                ImageShowBaseActivity.this.M1(this.f1085a, this.f1086b);
            }
        }

        f() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FileBean fileBean = ImageShowBaseActivity.this.u.b().get(ImageShowBaseActivity.this.t.getCurrentItem());
            int iL1 = ImageShowBaseActivity.this.L1(fileBean);
            if (iL1 == 1) {
                ImageShowBaseActivity.this.T0();
            }
            ImageShowBaseActivity.this.runOnUiThread(new a(iL1, fileBean));
        }
    }

    class g implements BaseFileDeleteDialogFragment.OnDeleteDialogListener {
        g() {
        }

        @Override // com.byd.fragment.BaseFileDeleteDialogFragment.OnDeleteDialogListener
        public void onSureDelete() {
            ImageShowBaseActivity.this.E0.D();
        }
    }

    class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1089a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ FileBean f1090b;

        h(int i, FileBean fileBean) {
            this.f1089a = i;
            this.f1090b = fileBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageShowBaseActivity.this.u.b().set(this.f1089a, this.f1090b);
            ImageShowBaseActivity.this.u.notifyDataSetChanged();
        }
    }

    class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ FileBean f1092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1093b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ FileBean f1094c;

        i(FileBean fileBean, int i, FileBean fileBean2) {
            this.f1092a = fileBean;
            this.f1093b = i;
            this.f1094c = fileBean2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1092a.getType() == 2) {
                ImageShowBaseActivity.this.a0(R.string.video_edit_export_success, true);
            }
            ImageShowBaseActivity.this.u.a(this.f1093b, this.f1094c);
            ImageShowBaseActivity.this.J1(this.f1094c);
            Object tag = ImageShowBaseActivity.this.w.getTag();
            if (tag == null || Integer.parseInt(tag.toString()) != 1) {
                return;
            }
            ImageShowBaseActivity.this.K1(false);
        }
    }

    class j extends Thread {
        j() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Iterator<FileBean> it = ImageShowBaseActivity.this.u.b().iterator();
                while (it.hasNext()) {
                    b.a.h.l.m(ImageShowBaseActivity.this).j(it.next().getPath());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            com.byd.util.w.c("clear cache end");
        }
    }

    class k extends Thread {
        k() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            com.byd.share.a.s(ImageShowBaseActivity.this).m();
        }
    }

    class l implements Runnable {
        l() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!ImageShowBaseActivity.this.r0) {
                ImageShowBaseActivity.this.Z(R.string.external_file_not_exist);
            }
            ImageShowBaseActivity.this.finish();
        }
    }

    class m implements Runnable {
        m() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageShowBaseActivity.this.i1();
            ImageShowBaseActivity.this.finish();
        }
    }

    class n implements ViewPager.OnPageChangeListener {
        n() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            ImageShowBaseActivity.this.B1(i);
        }
    }

    class o implements w.b {
        o() {
        }

        @Override // b.a.e.w.b
        public void onItemClick(int i) {
            ImageShowBaseActivity.this.y = i;
            ImageShowBaseActivity.this.F0.removeMessages(5);
            boolean z = ImageShowBaseActivity.this.a0.getDataType() != 2 && ImageShowBaseActivity.this.a0.isMoreThanOneItem();
            if (z) {
                ImageShowBaseActivity.G0(ImageShowBaseActivity.this);
            }
            com.byd.util.w.c("thumb item click : " + i + " " + ImageShowBaseActivity.this.y);
            ImageShowBaseActivity.this.t.setLoop(false);
            ImageShowBaseActivity.this.t.stopLoop();
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            imageShowBaseActivity.t.setCurrentItem(imageShowBaseActivity.y, true);
            ImageShowBaseActivity.this.b0.smoothScrollToPosition(i);
            ImageShowBaseActivity imageShowBaseActivity2 = ImageShowBaseActivity.this;
            imageShowBaseActivity2.E1(imageShowBaseActivity2.u.b().get(ImageShowBaseActivity.this.y));
            if (z) {
                ImageShowBaseActivity.this.F0.sendEmptyMessageDelayed(5, 1500L);
            }
        }
    }

    class p implements ViewPager.OnPageChangeListener {
        p() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            com.byd.util.w.c("==onPageScrollStateChanged==" + i);
            ImageShowBaseActivity.this.v = i;
            if (i != 0) {
                ImageShowBaseActivity.this.b1();
            }
            ImageShowBaseActivity.this.C1(i);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (ImageShowBaseActivity.this.y == i) {
                return;
            }
            ImageShowBaseActivity.this.y = i;
            ImageShowBaseActivity.this.F0.removeMessages(6);
            ImageShowBaseActivity.this.F0.sendEmptyMessageDelayed(6, 300L);
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            if (!imageShowBaseActivity.Z) {
                ImageShowBaseActivity.this.M0(imageShowBaseActivity.u.b().get(i));
                return;
            }
            imageShowBaseActivity.E1(imageShowBaseActivity.u.b().get(ImageShowBaseActivity.this.y));
            if (ImageShowBaseActivity.this.c0.getItemCount() < 2) {
                return;
            }
            if (ImageShowBaseActivity.this.a0.getDataType() != 2 && ImageShowBaseActivity.this.a0.isMoreThanOneItem()) {
                if (i == ImageShowBaseActivity.this.u.getCount() - 1) {
                    i = 0;
                } else {
                    if (i == 0) {
                        i = ImageShowBaseActivity.this.c0.getItemCount();
                    }
                    i--;
                }
            }
            if (i < 0 || i >= ImageShowBaseActivity.this.c0.getItemCount()) {
                return;
            }
            ImageShowBaseActivity.this.b0.smoothScrollToPosition(i);
            ImageShowBaseActivity.this.c0.e(i);
        }
    }

    class q implements Runnable {
        q() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"InlinedApi"})
        public void run() {
            ImageShowBaseActivity.this.z1();
        }
    }

    class r implements Runnable {
        r() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ImageShowBaseActivity.this.V1();
        }
    }

    class s extends com.byd.taskmanager.b.c<List<FileBean>> {
        s() {
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<FileBean> list) {
            ImageShowBaseActivity.this.u.h(list);
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            imageShowBaseActivity.c0.f(imageShowBaseActivity.a0.getPaths());
            com.byd.util.w.c("big size : " + ImageShowBaseActivity.this.u.getCount() + " thumb size : " + ImageShowBaseActivity.this.c0.getItemCount());
            ImageShowBaseActivity.this.c1();
            if (ImageShowBaseActivity.this.a0.getDataType() == 2 || !ImageShowBaseActivity.this.a0.isMoreThanOneItem()) {
                ImageShowBaseActivity.this.y = 0;
            } else {
                ImageShowBaseActivity.this.y = 1;
                ImageShowBaseActivity imageShowBaseActivity2 = ImageShowBaseActivity.this;
                imageShowBaseActivity2.t.setCurrentItem(imageShowBaseActivity2.y, false);
                ImageShowBaseActivity.this.t.startLoop();
            }
            ImageShowBaseActivity.this.y1();
            ImageShowBaseActivity imageShowBaseActivity3 = ImageShowBaseActivity.this;
            imageShowBaseActivity3.E1(list.get(imageShowBaseActivity3.y));
            if (ImageShowBaseActivity.this.a0.getDataType() == 2) {
                ImageShowBaseActivity imageShowBaseActivity4 = ImageShowBaseActivity.this;
                if (!imageShowBaseActivity4.H) {
                    com.byd.util.w.c("not support video play!");
                    return;
                }
                Intent intent = new Intent(imageShowBaseActivity4, (Class<?>) VideoPlayActivity.class);
                intent.putExtra("more_dev_share", true);
                intent.putParcelableArrayListExtra("share_items", new ArrayList<>(ImageShowBaseActivity.this.u.b()));
                intent.putExtra("current_index", 0);
                ImageShowBaseActivity.this.startActivityForResult(intent, 3);
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class t extends b.a.k.m {
        final /* synthetic */ String i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        t(Context context, String str, boolean z, String str2) {
            super(context, str, z);
            this.i = str2;
        }

        @Override // b.a.k.m
        protected void d(int i) {
            com.byd.util.w.c("onReceiveMaxCount : " + i);
            if (i > 100) {
                FileBean fileBeanH = com.byd.util.n.h(ImageShowBaseActivity.this, new File(this.i));
                LinkedList linkedList = new LinkedList();
                linkedList.add(null);
                linkedList.add(fileBeanH);
                linkedList.add(null);
                ImageShowBaseActivity.this.F0.obtainMessage(8, linkedList).sendToTarget();
            }
        }
    }

    class u extends com.byd.taskmanager.b.c<List<FileBean>> {
        u() {
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<FileBean> list) {
            if (list == null || list.size() == 0) {
                com.byd.util.w.c("ImageShowActivity load complete , but no data");
                if (ImageShowBaseActivity.this.getResources().getInteger(R.integer.activity_main_enable) == 1) {
                    Intent intent = new Intent(ImageShowBaseActivity.this, (Class<?>) MainActivity.class);
                    intent.putExtra("index", 0);
                    ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
                    if (imageShowBaseActivity.R) {
                        imageShowBaseActivity.U0();
                    }
                    intent.addFlags(67108864);
                    ImageShowBaseActivity.this.startActivity(intent);
                }
                ImageShowBaseActivity.this.finish();
                return;
            }
            ImageShowBaseActivity.this.u.h(list);
            ImageShowBaseActivity imageShowBaseActivity2 = ImageShowBaseActivity.this;
            imageShowBaseActivity2.y = imageShowBaseActivity2.s.c();
            boolean z = ImageShowBaseActivity.this.j0.getVisibility() == 0;
            if (z) {
                int currentItem = ImageShowBaseActivity.this.j0.getCurrentItem();
                if (currentItem != 1) {
                    ImageShowBaseActivity imageShowBaseActivity3 = ImageShowBaseActivity.this;
                    imageShowBaseActivity3.y = currentItem == 0 ? imageShowBaseActivity3.y - 1 : imageShowBaseActivity3.y + 1;
                    if (ImageShowBaseActivity.this.y < 0) {
                        ImageShowBaseActivity imageShowBaseActivity4 = ImageShowBaseActivity.this;
                        imageShowBaseActivity4.y = imageShowBaseActivity4.u.getCount() - 1;
                    } else if (ImageShowBaseActivity.this.y > ImageShowBaseActivity.this.u.getCount() - 1) {
                        ImageShowBaseActivity.this.y = 0;
                    }
                }
                ImageShowBaseActivity imageShowBaseActivity5 = ImageShowBaseActivity.this;
                imageShowBaseActivity5.I.setVisibility(imageShowBaseActivity5.q0 ? 0 : 8);
            }
            ImageShowBaseActivity.this.p1();
            ImageShowBaseActivity.this.c1();
            if (z) {
                ImageShowBaseActivity.this.R0();
                ImageShowBaseActivity.this.j0.setEnabled(false);
                ImageShowBaseActivity.this.F1();
            }
            ImageShowBaseActivity.this.y1();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    private class v implements m.e {
        private v() {
        }

        /* synthetic */ v(ImageShowBaseActivity imageShowBaseActivity, k kVar) {
            this();
        }

        @Override // b.a.e.m.e
        public void a() {
            if (com.byd.util.e.y()) {
                return;
            }
            ImageShowBaseActivity.this.Z1();
        }

        @Override // b.a.e.m.e
        public void b() {
            ImageShowBaseActivity.this.Z(R.string.file_no_found);
        }

        @Override // b.a.e.m.e
        public void c(List<FileBean> list, FileBean fileBean) {
            ImageShowBaseActivity imageShowBaseActivity = ImageShowBaseActivity.this;
            if (!imageShowBaseActivity.H) {
                com.byd.util.w.c("1->not support video play!");
                return;
            }
            imageShowBaseActivity.Y.g("02");
            if (com.byd.util.e.y()) {
                return;
            }
            Intent intent = new Intent(ImageShowBaseActivity.this, (Class<?>) VideoPlayActivity.class);
            if (ImageShowBaseActivity.this.Z) {
                intent.putExtra("more_dev_share", true);
                intent.putParcelableArrayListExtra("share_items", new ArrayList<>(ImageShowBaseActivity.this.u.b()));
                intent.putExtra("current_index", ImageShowBaseActivity.this.y);
            }
            ImageShowBaseActivity.this.startActivityForResult(intent, 3);
            if (ImageShowBaseActivity.this.Z) {
                return;
            }
            com.byd.util.r.h().n(list, fileBean);
        }

        @Override // b.a.e.m.e
        public void d(FileBean fileBean) {
            if (com.byd.util.e.y()) {
                return;
            }
            ImageShowBaseActivity.this.X1(fileBean);
        }

        @Override // b.a.e.m.e
        public void e(FileBean fileBean) {
            if (com.byd.util.e.y()) {
                return;
            }
            ImageShowBaseActivity.this.Z1();
        }
    }

    private class w implements Runnable {
        private w() {
        }

        /* synthetic */ w(ImageShowBaseActivity imageShowBaseActivity, k kVar) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            z zVar;
            LinkedList linkedList = new LinkedList();
            linkedList.addAll(ImageShowBaseActivity.this.u.b());
            int size = ImageShowBaseActivity.this.G0.size();
            if (size > 0) {
                z = false;
                for (int i = 0; i < size; i++) {
                    String str = (String) ImageShowBaseActivity.this.G0.get(i);
                    int size2 = linkedList.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size2) {
                            break;
                        }
                        if (str.equals(((FileBean) linkedList.get(i2)).getPath())) {
                            linkedList.remove(i2);
                            z = true;
                            break;
                        }
                        i2++;
                    }
                }
            } else {
                z = false;
            }
            ImageShowBaseActivity.this.G0.clear();
            if (ImageShowBaseActivity.this.H0.size() > 0 && linkedList.size() > 0) {
                for (Map.Entry entry : ImageShowBaseActivity.this.H0.entrySet()) {
                    int size3 = linkedList.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size3) {
                            break;
                        }
                        if (((String) entry.getKey()).equals(((FileBean) linkedList.get(i3)).getPath())) {
                            File file = new File((String) entry.getValue());
                            FileBean fileBean = (FileBean) linkedList.get(i3);
                            fileBean.setName(file.getName());
                            fileBean.setPath(file.getPath());
                            fileBean.setThumbPath(null);
                            z = true;
                            break;
                        }
                        i3++;
                    }
                }
            }
            ImageShowBaseActivity.this.H0.clear();
            if (ImageShowBaseActivity.this.I0.size() > 0 && linkedList.size() > 0) {
                for (String str2 : ImageShowBaseActivity.this.I0) {
                    Iterator it = linkedList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            FileBean fileBean2 = (FileBean) it.next();
                            if (str2.equals(fileBean2.getPath())) {
                                fileBean2.setCollected(0);
                                z = true;
                                break;
                            }
                        }
                    }
                }
            }
            ImageShowBaseActivity.this.I0.clear();
            int i4 = 0;
            while (i4 < linkedList.size()) {
                if (new File(((FileBean) linkedList.get(i4)).getPath()).exists()) {
                    i4++;
                } else {
                    linkedList.remove(i4);
                    z = true;
                }
            }
            if (z && (zVar = ImageShowBaseActivity.this.F0) != null) {
                zVar.obtainMessage(2, 1, 0, linkedList).sendToTarget();
            }
        }
    }

    private static class x extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<ImageShowBaseActivity> f1108a;

        x(ImageShowBaseActivity imageShowBaseActivity) {
            this.f1108a = new WeakReference<>(imageShowBaseActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            com.byd.util.r.h().k(null);
            WeakReference<ImageShowBaseActivity> weakReference = this.f1108a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1108a.get().O1((List) message.obj);
        }
    }

    private class y extends BroadcastReceiver {
        private y() {
        }

        /* synthetic */ y(ImageShowBaseActivity imageShowBaseActivity, k kVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("reason");
                com.byd.util.w.c("ImageShow receive ACTION_CLOSE_SYSTEM_DIALOGS : " + stringExtra);
                if ("homekey".equals(stringExtra) || "recentapps".equals(stringExtra)) {
                    ImageShowBaseActivity.this.x1(stringExtra);
                }
            }
        }
    }

    private static class z extends b.a.d<ImageShowBaseActivity> {
        public z(ImageShowBaseActivity imageShowBaseActivity) {
            super(imageShowBaseActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(ImageShowBaseActivity imageShowBaseActivity, Message message) {
            int i = message.what;
            if (i == 100) {
                imageShowBaseActivity.n1();
            }
            switch (i) {
                case 1:
                    com.byd.util.w.c("ImageShowActivity auto finish");
                    imageShowBaseActivity.finish();
                    break;
                case 2:
                    imageShowBaseActivity.N0(message);
                    break;
                case 3:
                    imageShowBaseActivity.f1((String) message.obj, false);
                    break;
                case 4:
                    imageShowBaseActivity.P0();
                    break;
                case 5:
                    imageShowBaseActivity.I1();
                    break;
                case 6:
                    imageShowBaseActivity.S0();
                    break;
                case 7:
                    imageShowBaseActivity.e1();
                    break;
                case 8:
                    imageShowBaseActivity.U1((List) message.obj);
                    break;
            }
        }
    }

    static /* synthetic */ int G0(ImageShowBaseActivity imageShowBaseActivity) {
        int i2 = imageShowBaseActivity.y;
        imageShowBaseActivity.y = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G1(String str) {
        Runnable iVar;
        int currentItem = this.t.getCurrentItem();
        File file = new File(str);
        FileBean fileBean = this.u.b().get(currentItem);
        FileBean fileBean2 = new FileBean(file.getName(), str, fileBean.getSourceType(), fileBean.getType(), file.lastModified());
        if (fileBean.getType() == 3) {
            fileBean2.setNum(fileBean.getNum() + 1);
            iVar = new h(currentItem, fileBean2);
        } else {
            fileBean2.setEditAddItem(true);
            iVar = new i(fileBean, currentItem, fileBean2);
        }
        runOnUiThread(iVar);
    }

    public static void H1(Context context, String str, String str2) {
        Intent intent = new Intent("image_show_modify");
        intent.putExtra("opt", "rename");
        intent.putExtra("old", str);
        intent.putExtra("new", str2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        com.byd.util.w.c("ImageShowActivity renameShowItem :" + str + " " + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I1() {
        this.t.setLoop(true);
        this.t.startLoop();
    }

    private Bitmap K0(Bitmap bitmap) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint();
            int[] iArr = {1291845632, 0, 0, 1291845632};
            Palette.Swatch darkMutedSwatch = Palette.from(bitmap).generate().getDarkMutedSwatch();
            if (darkMutedSwatch != null) {
                int rgb = darkMutedSwatch.getRgb();
                int iArgb = Color.argb(77, Color.red(rgb), Color.green(rgb), Color.blue(rgb));
                iArr[3] = iArgb;
                iArr[0] = iArgb;
            }
            paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, height, iArr, new float[]{0.0f, 0.15f, 0.85f, 1.0f}, Shader.TileMode.CLAMP));
            Rect rect = new Rect(0, 0, width, height);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.drawRect(rect, paint);
            bitmap.recycle();
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            com.byd.util.w.c("addLinearShape exception:" + e2.getMessage());
            return bitmap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M0(FileBean fileBean) {
        K1(q1(fileBean));
        J1(fileBean);
        Q0(fileBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N0(Message message) {
        if (message.arg1 == 1) {
            b1();
            List<FileBean> list = (List) message.obj;
            if (list.size() == 0) {
                i1();
                finish();
                return;
            }
            int size = this.y;
            if (size >= list.size()) {
                size = list.size() - 1;
            }
            this.u.h(list);
            if (size != this.y) {
                this.y = size;
                this.t.setCurrentItem(size);
            } else {
                J1(this.u.b().get(this.y));
            }
            K1(q1(this.u.b().get(this.y)));
        }
    }

    private void O0(FileBean fileBean) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O1(List<FileBean> list) {
        this.u.h(list);
        int count = this.u.getCount();
        com.byd.util.w.c("MediaDataChangeUtil handle " + count);
        if (count <= 0) {
            com.byd.util.w.c("->ImageShowBaseActivity no data , finish");
            Y1();
        } else {
            this.y = com.byd.util.r.h().g();
            p1();
            y1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P0() {
        if (!this.f2925c) {
            com.byd.util.w.c("ImageShowBaseActivity no start , can not show share icon");
            return;
        }
        int i2 = this.y;
        if (i2 < 0 || i2 >= this.u.getCount()) {
            return;
        }
        Q0(this.u.b().get(this.y));
    }

    private void P1(FileBean fileBean) {
        if (this.E0 == null) {
            com.byd.util.l lVar = new com.byd.util.l(this, this.J);
            this.E0 = lVar;
            lVar.B(this);
        }
        this.E0.y(new File(fileBean.getPath()).getParent());
        if (this.D0 == null) {
            FileDeleteDialogFragment fileDeleteDialogFragment = new FileDeleteDialogFragment();
            this.D0 = fileDeleteDialogFragment;
            fileDeleteDialogFragment.setUiVisibility(this.J);
            this.D0.setOnDeleteDialogListener(new g());
            this.D0.setOnDismissListener(this);
        }
        int type = fileBean.getType();
        int num = type == 3 ? fileBean.getNum() : 0;
        this.E0.z(fileBean);
        this.D0.setInfo(type, num, false);
        this.D0.show(getSupportFragmentManager(), "delete");
    }

    private void Q1(FileBean fileBean) {
        if (this.B0 == null) {
            FileDetailDialogFragment fileDetailDialogFragment = new FileDetailDialogFragment();
            this.B0 = fileDetailDialogFragment;
            fileDetailDialogFragment.setUiVisibility(this.J);
        }
        this.B0.setFileBean(fileBean);
        this.B0.show(getSupportFragmentManager(), "detail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0() {
        if (this.k0.E()) {
            com.byd.util.w.c("show matrix changed !!!");
            m.f fVar = (m.f) this.u.c(this.y);
            if (fVar == null) {
                com.byd.util.w.c("holder is null");
                return;
            }
            XImageView xImageView = (XImageView) fVar.a().findViewById(R.id.item_thumb);
            XImageView xImageViewD = this.k0.D();
            if (xImageView == null || xImageViewD == null) {
                com.byd.util.w.c("image view or loading image view is null");
            } else {
                xImageView.getViewTreeObserver().addOnPreDrawListener(new a(xImageView, xImageViewD));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S0() {
        b.a.e.n nVar = this.u;
        if (nVar == null) {
            return;
        }
        nVar.p(this.y);
    }

    private void S1(FileBean fileBean) {
        if (this.A0 == null) {
            RenameFileFragment renameFileFragment = new RenameFileFragment();
            this.A0 = renameFileFragment;
            renameFileFragment.setOnRenameListener(new d());
            this.A0.setOnDismissListener(this);
        }
        Y0(this.A0);
        this.A0.init(fileBean.getPath(), fileBean.getType());
        this.A0.show(getSupportFragmentManager(), "rename");
    }

    private void T1(FileBean fileBean) {
        if (this.C0 == null) {
            AlertDialogFragment alertDialogFragmentNewInstance = AlertDialogFragment.newInstance(R.string.set_wall_paper, R.string.set_wall_paper_tips);
            this.C0 = alertDialogFragmentNewInstance;
            alertDialogFragmentNewInstance.setUiVisibility(this.J);
            this.C0.setOnAlertDialogButtonClick(new e());
        }
        if (fileBean.getType() == 2 || fileBean.getName().toLowerCase().endsWith(".gif")) {
            this.C0.configUi(R.string.set_wall_paper, R.string.video_wallpaper_alert_tips, R.string.video_wallpaper_alert_sure, R.string.video_wallpaper_alert_cancel);
        } else {
            this.C0.configUi(R.string.set_wall_paper, R.string.set_wall_paper_tips, 0, 0);
        }
        this.C0.show(getSupportFragmentManager(), "set_wallpaper");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U0() {
        Activity next;
        com.byd.util.w.c("task record : clear old record");
        Iterator<Activity> it = PhotoBaseApp.b().iterator();
        Stack stack = null;
        while (it.hasNext() && (next = it.next()) != this) {
            if (stack == null) {
                if (next instanceof MainActivity) {
                    stack = new Stack();
                }
            }
            stack.push(next);
        }
        if (stack != null) {
            while (!stack.isEmpty()) {
                ((Activity) stack.pop()).finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U1(List<FileBean> list) {
        this.l0 = true;
        b.a.e.o oVar = new b.a.e.o(this, this.L);
        this.k0 = oVar;
        oVar.z(new c0(this, null));
        this.k0.h(list);
        this.j0.setAdapter(this.k0);
        this.j0.setCurrentItem(1);
        this.j0.setVisibility(0);
        M0(list.get(1));
        c1();
    }

    private FileBean V0() {
        FileBean fileBeanJ1 = j1();
        if (fileBeanJ1 == null || r1(fileBeanJ1.getPath())) {
            return null;
        }
        return fileBeanJ1;
    }

    public static void W0(Context context, String str) {
        Intent intent = new Intent("image_show_modify");
        intent.putExtra("opt", "collect");
        intent.putExtra("path", str);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X1(FileBean fileBean) {
        Intent intent = new Intent(this, (Class<?>) BurstshootManagerActivity.class);
        intent.putExtra("photo_intent_data", fileBean);
        intent.setExtrasClassLoader(FileBean.class.getClassLoader());
        startActivityForResult(intent, 2);
    }

    private void Y1() {
        com.byd.util.w.c("ImageShowActivity toMainActivity");
        if (!this.R) {
            L0();
            return;
        }
        if (getResources().getInteger(R.integer.activity_main_enable) == 1) {
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            if (!this.S && this.x == -1) {
                this.x = 1;
            }
            int i2 = this.x;
            if (i2 != -1) {
                intent.putExtra("index", i2);
            }
            FileBean fileBeanJ1 = j1();
            if (fileBeanJ1 != null) {
                intent.putExtra("fullscreen_path", fileBeanJ1.getPath());
            }
            U0();
            intent.addFlags(67108864);
            startActivity(intent);
        }
        finish();
    }

    private void Z0() {
        boolean zS1 = s1();
        this.U = zS1;
        if (zS1) {
            this.g0 = getResources().getInteger(R.integer.video_wallpaper_enable) == 1;
            this.h0 = getResources().getInteger(R.integer.gif_wallpaper_enable) == 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z1() {
        if (this.q0) {
            n1();
        } else {
            N1();
        }
    }

    public static void a1(Context context, ArrayList<String> arrayList) {
        Intent intent = new Intent("image_show_modify");
        intent.putExtra("opt", "del");
        intent.putStringArrayListExtra("paths", arrayList);
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }

    private void d1() {
        com.byd.util.w.c("ImageShowActivity backPressed");
        if (this.Z) {
            finish();
        } else {
            L0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1() {
        R1(R.string.doing_set_wall_paper_tips, "set_wallpaper_progress");
        new f().start();
    }

    private void h1() {
        runOnUiThread(new m());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i1() {
        if (this.r0) {
            return;
        }
        Z(R.string.file_no_found);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.byd.bean.FileBean j1() {
        /*
            r3 = this;
            boolean r0 = r3.l0
            if (r0 == 0) goto L1a
            androidx.viewpager.widget.ViewPager r0 = r3.j0
            int r0 = r0.getCurrentItem()
            r1 = 1
            if (r0 != r1) goto L1a
            b.a.e.o r0 = r3.k0
            java.util.List r0 = r0.b()
        L13:
            java.lang.Object r0 = r0.get(r1)
            com.byd.bean.FileBean r0 = (com.byd.bean.FileBean) r0
            return r0
        L1a:
            b.a.e.n r0 = r3.u
            r1 = 0
            if (r0 == 0) goto L3b
            int r0 = r0.getCount()
            if (r0 <= 0) goto L3b
            com.byd.view.XScaleImageViewPager r2 = r3.t
            int r2 = r2.getCurrentItem()
            if (r2 < r0) goto L2e
            goto L3b
        L2e:
            b.a.e.n r0 = r3.u
            java.util.List r0 = r0.b()
            com.byd.view.XScaleImageViewPager r1 = r3.t
            int r1 = r1.getCurrentItem()
            goto L13
        L3b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.ImageShowBaseActivity.j1():com.byd.bean.FileBean");
    }

    private Bitmap k1(String str, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        float f2 = i2 / i4;
        float f3 = i3 / i5;
        if (f2 > f3) {
            i7 = (i3 * i4) / i5;
            i6 = i3;
        } else {
            i6 = (i2 * i5) / i4;
            i7 = i2;
        }
        int i8 = (i2 - i7) >> 1;
        int i9 = (i3 - i6) >> 1;
        this.y0.set(i8, i9, i8 + i7, i9 + i6);
        com.byd.util.w.c("bitmap : " + i2 + "x" + i3 + " wallpaper real size : " + i7 + "x" + i6 + " rect:" + this.y0.toShortString());
        Bitmap bitmapDecodeRegion = this.w0.decodeRegion(this.y0, this.x0);
        if (bitmapDecodeRegion == null) {
            com.byd.util.w.c("decode bitmap by BitmapFactory");
            bitmapDecodeRegion = b.a.h.a.h(str, i4, i5, b.a.h.a.p(str), Bitmap.Config.ARGB_8888, false);
        }
        return (f2 == 1.0f && f3 == 1.0f) ? K0(bitmapDecodeRegion) : Bitmap.createScaledBitmap(bitmapDecodeRegion, i4, i5, true);
    }

    private int[] l1() {
        int[] iArr = new int[2];
        WindowManager windowManager = (WindowManager) getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        int i2 = point.x;
        int i3 = point.y;
        if (i2 > i3) {
            iArr[0] = i2;
            iArr[1] = i3;
        } else {
            iArr[1] = i2;
            iArr[0] = i3;
        }
        return iArr;
    }

    private void o1() {
        String strQ;
        if (this.Z) {
            v1();
            return;
        }
        Intent intent = getIntent();
        if (intent.getData() != null) {
            Uri data = intent.getData();
            strQ = com.byd.util.n.q(this, data);
            com.byd.util.w.c("ImageShow uri : " + data + " path:" + strQ);
            if (strQ == null) {
                try {
                    InputStream inputStreamOpenInputStream = getContentResolver().openInputStream(data);
                    boolean zEndsWith = data.getPath().toLowerCase().endsWith(".gif");
                    com.byd.util.w.c("ImageShow stream data from : " + data + " is gif:" + zEndsWith);
                    if (zEndsWith) {
                        this.u.B(inputStreamOpenInputStream);
                    } else {
                        this.u.A(b.a.h.a.g(getContentResolver().openInputStream(data), b.a.h.a.o(inputStreamOpenInputStream)));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    com.byd.util.w.f(this, R.string.load_fail, 1);
                }
                this.I.setVisibility(8);
                this.e0 = data;
                y1();
                return;
            }
            if (intent.hasExtra("index")) {
                this.x = intent.getIntExtra("index", 0);
            }
        } else {
            strQ = null;
        }
        if (strQ != null) {
            File file = new File(strQ);
            if (file.exists() && file.length() != 0) {
                u1(strQ);
                return;
            }
            Z(R.string.file_error);
            com.byd.util.w.c("ImageShow file no exists");
            finish();
            return;
        }
        com.byd.util.r.h().k(this.V);
        if (com.byd.util.r.h().i()) {
            com.byd.util.r.h().k(null);
            this.u.h(com.byd.util.r.h().f());
            com.byd.util.w.c("MediaDataChangeUtil isChangeFinish " + this.u.getCount());
            if (this.u.getCount() <= 0) {
                com.byd.util.w.c("ImageShowBaseActivity no data , finish");
                Y1();
            } else {
                this.y = com.byd.util.r.h().g();
                p1();
                y1();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p1() {
        List<FileBean> listB;
        this.t.setCurrentItem(this.y, false);
        int count = this.u.getCount();
        com.byd.util.w.c("showIndex:" + this.y + " total:" + count);
        if (this.y >= count) {
            return;
        }
        this.F0.removeMessages(6);
        this.F0.sendEmptyMessageDelayed(6, 300L);
        if (this.Z || (listB = this.u.b()) == null || this.y >= listB.size()) {
            return;
        }
        FileBean fileBean = listB.get(this.y);
        K1(q1(fileBean));
        J1(fileBean);
        W1();
    }

    private boolean r1(String str) {
        File file = new File(str);
        if (file.exists() && file.length() != 0) {
            return false;
        }
        i1();
        return true;
    }

    private void u1(String str) {
        R1(R.string.loading_please_wait, "img_show_load");
        if (!z(0)) {
            t tVar = new t(this, str, this.R, str);
            this.s = tVar;
            v(0, tVar, new u(), true);
            if (!this.H) {
                this.s.e(1);
            }
        }
        w(0);
    }

    private void v1() {
        R1(R.string.loading_please_wait, "load_img");
        if (!z(1)) {
            v(1, new b.a.k.e(this.a0), new s(), true);
        }
        w(1);
    }

    protected void A1() {
        boolean z2 = false;
        this.G = getResources().getBoolean(R.bool.view_image_edit) ? 0 : 8;
        if (!"852".equals(com.byd.util.e.n()) && getResources().getInteger(R.integer.activity_video_enable) == 1) {
            z2 = true;
        }
        this.H = z2;
    }

    protected void B1(int i2) {
        b.a.e.n nVar = this.u;
        if (nVar != null && nVar.getCount() > 0) {
            this.j0.setEnabled(false);
            this.F0.postDelayed(new b(), 300L);
        } else if (i2 == 0 || i2 == 2) {
            this.I.setVisibility(8);
        } else if (this.q0) {
            this.I.setVisibility(0);
        }
    }

    protected void C1(int i2) {
    }

    protected void D1(File file, File file2) {
    }

    protected void E1(FileBean fileBean) {
    }

    protected void F1() {
        this.l0 = false;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300L);
        this.j0.startAnimation(alphaAnimation);
        this.F0.postDelayed(new c(), 300L);
    }

    protected void J1(FileBean fileBean) {
        View view;
        boolean zEndsWith = fileBean.getName().toLowerCase().endsWith(".gif");
        int i2 = (!this.r || zEndsWith || (fileBean.getType() == 2 && !this.i0)) ? 8 : 0;
        this.F.setVisibility(i2);
        this.D.setVisibility(i2);
        int iM1 = m1(fileBean, zEndsWith);
        this.Q.setVisibility(iM1);
        this.E.setVisibility(iM1);
        if (fileBean.getType() == 3) {
            if (com.byd.auto_photo.a.f1178a.intValue() == 2) {
                this.z.setVisibility(0);
                this.A.setVisibility(0);
            }
            if (fileBean.getType() == 3) {
                this.B.setVisibility(0);
                this.C.setVisibility(0);
                O0(fileBean);
            }
            this.B.setVisibility(8);
            view = this.C;
        } else {
            this.z.setVisibility(8);
            view = this.A;
        }
        view.setVisibility(8);
        O0(fileBean);
    }

    protected void K1(boolean z2) {
        TextView textView;
        int i2;
        if (z2) {
            textView = this.w;
            i2 = 1;
        } else {
            textView = this.w;
            i2 = 0;
        }
        textView.setTag(Integer.valueOf(i2));
    }

    protected void L0() {
        com.byd.util.p.d(this).c();
        Intent intent = new Intent();
        FileBean fileBeanJ1 = j1();
        if (fileBeanJ1 != null) {
            intent.putExtra("photo_intent_data", fileBeanJ1.getPath());
        }
        setResult(-1, intent);
        finish();
    }

    protected int L1(FileBean fileBean) {
        if (this.x0 == null) {
            this.x0 = new BitmapFactory.Options();
            this.y0 = new Rect();
        }
        BitmapRegionDecoder bitmapRegionDecoder = this.w0;
        if (bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled()) {
            this.w0.recycle();
        }
        try {
            this.w0 = BitmapRegionDecoder.newInstance(fileBean.getPath(), false);
        } catch (IOException e2) {
            e2.printStackTrace();
            BitmapRegionDecoder bitmapRegionDecoder2 = this.w0;
            if (bitmapRegionDecoder2 != null) {
                bitmapRegionDecoder2.recycle();
                this.w0 = null;
            }
        }
        int[] iArrL1 = l1();
        if (this.w0 == null) {
            Bitmap bitmapH = b.a.h.a.h(fileBean.getPath(), iArrL1[0], iArrL1[0], 1, Bitmap.Config.ARGB_8888, false);
            return w1(bitmapH, bitmapH);
        }
        this.x0.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileBean.getPath(), this.x0);
        BitmapFactory.Options options = this.x0;
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        options.inJustDecodeBounds = false;
        Bitmap bitmapK1 = k1(fileBean.getPath(), i2, i3, iArrL1[0], iArrL1[1]);
        Bitmap bitmapK12 = k1(fileBean.getPath(), i2, i3, iArrL1[1], iArrL1[0]);
        com.byd.util.w.c("set wallpaper land:" + bitmapK1.getWidth() + "x" + bitmapK1.getHeight() + " port:" + bitmapK12.getWidth() + "x" + bitmapK12.getHeight());
        int iW1 = w1(bitmapK1, bitmapK12);
        StringBuilder sb = new StringBuilder();
        sb.append("===> photo set wallpaper result : ");
        sb.append(iW1);
        com.byd.util.w.c(sb.toString());
        BitmapRegionDecoder bitmapRegionDecoder3 = this.w0;
        if (bitmapRegionDecoder3 != null) {
            if (!bitmapRegionDecoder3.isRecycled()) {
                this.w0.recycle();
            }
            this.w0 = null;
        }
        return iW1;
    }

    protected void M1(int i2, FileBean fileBean) {
        if (i2 == 1) {
            a0(R.string.wall_paper_set_ok, true);
        } else {
            Z(R.string.wall_paper_set_fail);
        }
        c1();
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        ShareItem shareItem;
        setContentView(R.layout.activity_image_show);
        this.K = findViewById(R.id.image_show_content);
        this.t = (XScaleImageViewPager) findViewById(R.id.m_pager);
        this.j0 = (ViewPager) findViewById(R.id.pager_super_much_loading);
        b.a.e.n nVar = this.u;
        if (nVar == null || nVar.getCount() <= 0) {
            this.j0.addOnPageChangeListener(new n());
            b.a.e.o oVar = this.k0;
            if (oVar != null) {
                this.j0.setAdapter(oVar);
            }
        } else {
            this.l0 = false;
            this.j0.setVisibility(8);
        }
        View viewFindViewById = findViewById(R.id.img_show_tool_layout);
        this.I = viewFindViewById;
        if (this.q0 && this.G == 8) {
            viewFindViewById.setVisibility(8);
        }
        TextView textView = (TextView) x(R.id.btn_back);
        this.M = textView;
        textView.setOnClickListener(this);
        if (com.byd.auto_photo.a.f1178a.intValue() != 2) {
            if (this.R && getResources().getInteger(R.integer.activity_main_enable) == 1) {
                this.M.setVisibility(0);
            } else {
                this.M.setVisibility(8);
            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_indicator);
        this.b0 = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false));
        if (this.c0 == null) {
            b.a.e.w wVar = new b.a.e.w(this);
            this.c0 = wVar;
            wVar.g(new o());
        }
        this.b0.setAdapter(this.c0);
        if (this.Z) {
            this.M.setVisibility(8);
            this.I.setVisibility(8);
            ShareItem shareItem2 = this.a0;
            if (shareItem2 != null && shareItem2.isMoreThanOneItem()) {
                new LinearSnapHelper().attachToRecyclerView(this.b0);
                this.b0.addItemDecoration(new ListItemDecoration(20, true));
                this.b0.setVisibility(0);
            }
        }
        TextView textView2 = (TextView) x(R.id.btn_set_wall_paper);
        this.Q = textView2;
        textView2.setOnClickListener(this);
        this.E = findViewById(R.id.space_set_wallpaper);
        TextView textView3 = (TextView) x(R.id.btn_detail);
        this.P = textView3;
        textView3.setOnClickListener(this);
        TextView textView4 = (TextView) x(R.id.btn_share);
        this.N = textView4;
        textView4.setOnClickListener(this);
        this.B = (TextView) x(R.id.btn_rename);
        this.C = findViewById(R.id.space_rename);
        this.B.setOnClickListener(this);
        TextView textView5 = (TextView) x(R.id.btn_collect);
        this.w = textView5;
        textView5.setOnClickListener(this);
        TextView textView6 = (TextView) x(R.id.btn_delete);
        this.O = textView6;
        textView6.setOnClickListener(this);
        this.D = findViewById(R.id.space_edit);
        TextView textView7 = (TextView) x(R.id.btn_edit);
        this.F = textView7;
        textView7.setOnClickListener(this);
        TextView textView8 = (TextView) x(R.id.btn_expand);
        this.z = textView8;
        textView8.setOnClickListener(this);
        this.A = findViewById(R.id.space_expand);
        if (this.u == null) {
            b.a.e.n nVar2 = new b.a.e.n(this, this.L);
            this.u = nVar2;
            nVar2.z(new v(this, null));
        }
        this.t.setAdapter(this.u);
        this.t.setPageMargin(10);
        if (this.Z && (shareItem = this.a0) != null && shareItem.getDataType() != 2 && this.a0.isMoreThanOneItem()) {
            this.u.y(true);
            this.t.setLoop(true);
            if (this.u.getCount() > 1) {
                this.t.startLoop();
            }
        }
        if (com.byd.util.e.E()) {
            return;
        }
        this.t.addOnPageChangeListener(this.p0);
        if (this.J == 0) {
            this.J = this.t.getSystemUiVisibility();
        }
    }

    protected void N1() {
        this.K.setSystemUiVisibility(this.J);
        this.q0 = true;
        this.F0.removeCallbacks(this.s0);
        this.F0.postDelayed(this.t0, 200L);
    }

    protected void Q0(FileBean fileBean) {
        if (fileBean.getType() == 3 || !this.q0) {
            this.d0.l();
        } else {
            this.d0.p(this.f0, 0);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void R() {
        if (L(this, com.byd.util.x.e((PhotoBaseApp) getApplication()))) {
            S();
        } else {
            com.byd.util.w.e(this, R.string.permission_denied_and_finish);
            finish();
        }
    }

    protected void R1(int i2, String str) {
        if (this.m0 && BaseAutoFinishActivity.u()) {
            return;
        }
        if (this.u0 == null) {
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
            this.u0 = progressDialogFragment;
            progressDialogFragment.setUiVisibility(this.J);
            this.u0.setOnDismissListener(this);
        }
        this.u0.setMsgRes(i2);
        this.u0.show(getSupportFragmentManager(), str);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        super.S();
        com.byd.util.w.c("ImageShow onGrantedPermission");
        o1();
        this.o0 = new a0(null);
        getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.o0);
        getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.o0);
        this.Y.g("01");
        if (this.Z) {
            return;
        }
        new k().start();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        b.a.e.n nVar = this.u;
        if (nVar != null) {
            nVar.notifyDataSetChanged();
        }
        this.N.setText(R.string.share);
        this.B.setText(R.string.rename);
        this.z.setText(R.string.expand);
        this.F.setText(R.string.edit);
        this.O.setText(R.string.delete);
        this.P.setText(R.string.detail);
        this.Q.setText(R.string.set_wall_paper);
        this.w.setText(R.string.collect);
    }

    protected void T0() {
        Uri uri = Uri.parse(!this.m0 ? "content://com.byd.multipletheme.ContentProvider/wallpaper" : "content://com.byd.automultipletheme.ContentProvider/wallpaper");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DiLinkBluetoothDeviceConstants._ID, (Integer) 1);
            contentValues.put("wallpaper_id", (Integer) (-1));
            com.byd.util.w.c("clearMultipartThemeRecord result : " + getContentResolver().insert(uri, contentValues));
        } catch (Exception e2) {
            e2.printStackTrace();
            com.byd.util.w.c("clearMultipartThemeRecord exception : " + e2.getMessage());
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        X0(150);
    }

    protected void V1() {
        if (this.u.s()) {
            return;
        }
        if (this.Z) {
            ShareItem shareItem = this.a0;
            if (shareItem == null || !shareItem.isMoreThanOneItem()) {
                return;
            }
            this.b0.setVisibility(0);
            return;
        }
        if (!this.l0 || this.j0.getCurrentItem() == 1) {
            this.I.setVisibility(this.G);
        }
        FileBean fileBeanV0 = V0();
        if (fileBeanV0 == null) {
            return;
        }
        Q0(fileBeanV0);
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        String[] strArrF = com.byd.util.x.f(this);
        int iQ = com.byd.share.a.q(this);
        if (getResources().getInteger(R.integer.support_share_qr_code) != 1 || iQ == 0 || iQ == 1) {
            return strArrF;
        }
        String[] strArr = new String[strArrF.length + this.n0.length];
        System.arraycopy(strArrF, 0, strArr, 0, strArrF.length);
        String[] strArr2 = this.n0;
        System.arraycopy(strArr2, 0, strArr, strArrF.length, strArr2.length);
        return strArr;
    }

    protected void W1() {
        this.F0.removeMessages(4);
        this.F0.sendEmptyMessageDelayed(4, 300L);
    }

    protected void X0(int i2) {
        this.F0.removeMessages(7);
        if (i2 == 0) {
            this.F0.sendEmptyMessage(7);
        } else {
            this.F0.sendEmptyMessageDelayed(7, i2);
        }
    }

    protected void Y0(RenameFileFragment renameFileFragment) {
    }

    protected void b1() {
        DialogFragment dialogFragment;
        com.byd.util.w.c("ImageShow dismissAllDialog");
        ProgressDialogFragment progressDialogFragment = this.u0;
        if (progressDialogFragment != null && progressDialogFragment.isShowing()) {
            this.u0.dismissAllowingStateLoss();
            return;
        }
        RenameFileFragment renameFileFragment = this.A0;
        if (renameFileFragment != null && renameFileFragment.isShowing()) {
            this.A0.dismissAllowingStateLoss();
            return;
        }
        FileDeleteDialogFragment fileDeleteDialogFragment = this.D0;
        if (fileDeleteDialogFragment == null || !fileDeleteDialogFragment.isShowing()) {
            ShareDialogFragment shareDialogFragment = this.v0;
            if (shareDialogFragment != null && shareDialogFragment.isShowing()) {
                this.v0.dismissAllowingStateLoss();
                return;
            }
            AlertDialogFragment alertDialogFragment = this.C0;
            if (alertDialogFragment == null || !alertDialogFragment.isShowing()) {
                FileDetailDialogFragment fileDetailDialogFragment = this.B0;
                if (fileDetailDialogFragment == null || !fileDetailDialogFragment.isShowing()) {
                    return;
                } else {
                    dialogFragment = this.B0;
                }
            } else {
                dialogFragment = this.C0;
            }
        } else {
            dialogFragment = this.D0;
        }
        dialogFragment.dismissAllowingStateLoss();
    }

    protected void c1() {
        ProgressDialogFragment progressDialogFragment = this.u0;
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
            this.u0.setOnDismissListener(null);
            this.u0 = null;
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    @SuppressLint({"RestrictedApi"})
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        com.byd.util.w.c("ImageShowBaseActivity dispatchKeyEvent : " + keyEvent.getAction() + "->" + keyEvent.getKeyCode());
        if (com.byd.util.e.E()) {
            finish();
            return true;
        }
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && keyEvent.getRepeatCount() == 0) {
            d1();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    protected void e1() {
        XScaleImageViewPager xScaleImageViewPager = this.t;
        if (xScaleImageViewPager == null) {
            return;
        }
        xScaleImageViewPager.removeOnPageChangeListener(this.p0);
        if (this.t.isLoop()) {
            this.t.stopLoop();
        }
        com.byd.util.w.c("ImageShow doConfigChange");
        N();
        this.d0.l();
        this.d0.r(this.f0, 0);
        if (this.u.s()) {
            this.I.setVisibility(8);
        } else {
            p1();
        }
        if (this.q0 || this.F0.hasMessages(100)) {
            return;
        }
        this.F0.sendEmptyMessageDelayed(100, 300L);
    }

    protected void f1(String str, boolean z2) {
        List<FileBean> listB = this.u.b();
        int size = listB.size();
        int count = 0;
        while (true) {
            if (count >= size) {
                count = -1;
                break;
            } else {
                if (str.equals(listB.get(count).getPath())) {
                    this.u.g(count);
                    break;
                }
                count++;
            }
        }
        if (count == -1) {
            count = this.t.getCurrentItem();
        }
        if (this.u.getCount() == 0) {
            if (!this.r0) {
                Z(z2 ? R.string.del_all_in_folder : R.string.file_error);
            }
            this.F0.sendEmptyMessageDelayed(1, 300L);
            return;
        }
        if (count > this.u.getCount() - 1) {
            count = this.u.getCount() - 1;
            this.u.notifyDataSetChanged();
            this.t.setCurrentItem(count);
        }
        FileBean fileBean = this.u.b().get(count);
        K1(q1(fileBean));
        J1(fileBean);
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.T) {
            finishAndRemoveTask();
        } else {
            super.finish();
        }
    }

    protected int m1(FileBean fileBean, boolean z2) {
        if (this.U) {
            return z2 ? this.h0 ? 0 : 8 : (fileBean.getType() == 1 || (this.g0 && fileBean.getType() == 2)) ? 0 : 8;
        }
        return 8;
    }

    protected void n1() {
        if (this.u.s()) {
            this.I.setVisibility(8);
        } else if (!this.Z) {
            if (this.I.getVisibility() == 0) {
                this.I.setVisibility(8);
            }
            this.d0.l();
        } else if (this.b0.getVisibility() == 0) {
            this.b0.setVisibility(8);
        }
        this.q0 = false;
        this.F0.removeCallbacks(this.t0);
        this.F0.postDelayed(this.s0, 200L);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        new d0(i2, i3, intent).start();
    }

    public void onClick(View view) {
        com.byd.util.f fVarK;
        String path;
        Intent intent;
        if (com.byd.util.e.y()) {
        }
        if (this.v != 0) {
            com.byd.util.w.c("can not click on pager scrolling : " + this.v);
            return;
        }
        switch (view.getId()) {
            case R.id.btn_back /* 2131230792 */:
                Y1();
                break;
            case R.id.btn_collect /* 2131230797 */:
                FileBean fileBeanV0 = V0();
                if (fileBeanV0 != null) {
                    view.setEnabled(false);
                    Object tag = view.getTag();
                    if (tag == null || Integer.parseInt(tag.toString()) == 0) {
                        long jC = com.byd.util.f.k(this).c(fileBeanV0);
                        if (jC <= 0) {
                            com.byd.util.w.c("add collection fail : " + jC);
                            Z(R.string.collect_fail);
                        } else {
                            fileBeanV0.setCollected(1);
                            K1(true);
                        }
                    } else {
                        if (fileBeanV0.getType() == 3) {
                            fVarK = com.byd.util.f.k(this);
                            path = new File(fileBeanV0.getPath()).getParent();
                        } else {
                            fVarK = com.byd.util.f.k(this);
                            path = fileBeanV0.getPath();
                        }
                        fVarK.u(path);
                        fileBeanV0.setCollected(2);
                        K1(false);
                    }
                    view.setEnabled(true);
                    com.byd.util.k.c(this).g("05");
                    Intent intent2 = new Intent("com.byd.ACTION_FILE_CHANGE");
                    intent2.putExtra("path", "db:///collect");
                    intent2.putExtra("collect_file", fileBeanV0.getPath());
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
                    W0(this, fileBeanV0.getPath());
                    break;
                }
                break;
            case R.id.btn_delete /* 2131230800 */:
                FileBean fileBeanV02 = V0();
                if (fileBeanV02 != null) {
                    P1(fileBeanV02);
                    break;
                }
                break;
            case R.id.btn_detail /* 2131230801 */:
                FileBean fileBeanV03 = V0();
                if (fileBeanV03 != null) {
                    Q1(fileBeanV03);
                    break;
                }
                break;
            case R.id.btn_edit /* 2131230802 */:
                FileBean fileBeanV04 = V0();
                if (fileBeanV04 != null) {
                    this.Y.g("03");
                    if (fileBeanV04.getType() != 2) {
                        intent = new Intent(this, (Class<?>) ImageEditActivity.class);
                        intent.putExtra("photo_intent_data", fileBeanV04);
                        intent.setExtrasClassLoader(FileBean.class.getClassLoader());
                    } else if (this.i0) {
                        intent = new Intent(this, (Class<?>) VideoEditActivity.class);
                        intent.putExtra("photo_intent_data", fileBeanV04.getPath());
                    }
                    startActivityForResult(intent, 1);
                    break;
                }
                break;
            case R.id.btn_expand /* 2131230803 */:
                FileBean fileBeanV05 = V0();
                if (fileBeanV05 != null && fileBeanV05.getType() == 3) {
                    X1(fileBeanV05);
                }
                break;
            case R.id.btn_rename /* 2131230814 */:
                FileBean fileBeanV06 = V0();
                if (fileBeanV06 != null) {
                    if (fileBeanV06.getType() != 3) {
                        S1(fileBeanV06);
                    } else {
                        Z(R.string.can_not_rename_burst);
                    }
                    break;
                }
                break;
            case R.id.btn_set_wall_paper /* 2131230820 */:
                this.Y.g("07");
                FileBean fileBeanV07 = V0();
                if (fileBeanV07 != null) {
                    T1(fileBeanV07);
                    break;
                }
                break;
            case R.id.btn_share /* 2131230821 */:
                FileBean fileBeanV08 = V0();
                if (fileBeanV08 != null) {
                    if (this.v0 == null) {
                        ShareDialogFragment shareDialogFragment = new ShareDialogFragment();
                        this.v0 = shareDialogFragment;
                        shareDialogFragment.setUiVisibility(this.J);
                        this.v0.setOnDismissListener(this);
                    }
                    this.v0.setShareFileBean(fileBeanV08);
                    this.v0.show(getSupportFragmentManager(), "share");
                    break;
                }
                break;
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.f2924b = false;
        Point point = new Point();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getRealSize(point);
        int iMax = Math.max(point.x, point.y);
        this.L = iMax;
        if (iMax < 1920) {
            this.L = 1920;
        }
        this.m0 = com.byd.util.e.G(this);
        this.F0 = new z(this);
        Intent intent = getIntent();
        this.R = intent.getBooleanExtra("from_camera", false);
        this.S = intent.getBooleanExtra("from_main", false);
        this.T = "android.intent.action.VIEW".equals(intent.getAction());
        boolean booleanExtra = intent.getBooleanExtra("more_dev_share", false);
        this.Z = booleanExtra;
        if (booleanExtra) {
            ShareItem shareItem = (ShareItem) intent.getParcelableExtra("share_item");
            this.a0 = shareItem;
            com.byd.util.w.g(this, com.byd.util.x.h(this, shareItem.getFromId(), this.a0.getDataType()));
        }
        Z0();
        this.V = new x(this);
        this.Y = com.byd.util.k.c(this);
        com.byd.share.d dVar = new com.byd.share.d(this);
        this.d0 = dVar;
        dVar.o(this);
        this.f0 = getResources().getDimensionPixelSize(R.dimen.more_dev_share_icon_offset_x);
        this.i0 = getResources().getBoolean(R.bool.video_edit_support);
        A1();
        super.onCreate(bundle);
        k kVar = null;
        y yVar = new y(this, kVar);
        this.W = yVar;
        registerReceiver(yVar, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.X = new b0(this, kVar);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.X, new IntentFilter("image_show_modify"));
    }

    @Override // com.byd.util.l.i
    public void onDeleteCancel() {
    }

    @Override // com.byd.util.l.i
    public void onDeleteComplete(List<String> list) {
        if (list == null || list.size() == 0) {
            com.byd.util.w.c("ImageShowActivity onDeleteComplete exception");
            return;
        }
        if (this.l0) {
            com.byd.util.w.c("onDeleteComplete in much loading show");
            i1();
            this.F0.sendEmptyMessageDelayed(1, 300L);
            return;
        }
        f1(list.get(0), true);
        Intent intent = new Intent("com.byd.ACTION_FILE_CHANGE");
        intent.putExtra("path", this.E0.w());
        intent.putExtra("delete", true);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        if (this.u.getCount() > 0) {
            a0(R.string.delete_finish, true);
        }
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        a1(this, arrayList);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        com.byd.util.w.c("ImageShowActivity onDestroy");
        XScaleImageViewPager xScaleImageViewPager = this.t;
        if (xScaleImageViewPager != null) {
            xScaleImageViewPager.removeOnPageChangeListener(this.p0);
        }
        this.F0.removeCallbacksAndMessages(null);
        com.byd.util.r.h().k(null);
        b1();
        b.a.e.n nVar = this.u;
        if (nVar != null) {
            nVar.f();
            if (this.T && this.u.b() != null && this.u.b().size() > 0) {
                com.byd.util.w.c("clear cache start");
                new j().start();
            }
        }
        ShareDialogFragment shareDialogFragment = this.v0;
        if (shareDialogFragment != null) {
            shareDialogFragment.setOnDismissListener(null);
            this.v0 = null;
        }
        RenameFileFragment renameFileFragment = this.A0;
        if (renameFileFragment != null) {
            renameFileFragment.setOnDismissListener(null);
            this.A0 = null;
        }
        FileDeleteDialogFragment fileDeleteDialogFragment = this.D0;
        if (fileDeleteDialogFragment != null) {
            fileDeleteDialogFragment.setOnDismissListener(null);
            this.D0 = null;
        }
        if (this.B0 != null) {
            this.B0 = null;
        }
        AlertDialogFragment alertDialogFragment = this.C0;
        if (alertDialogFragment != null) {
            alertDialogFragment.setOnAlertDialogButtonClick(null);
            this.C0 = null;
        }
        B();
        ProgressDialogFragment progressDialogFragment = this.u0;
        if (progressDialogFragment != null) {
            progressDialogFragment.setOnDismissListener(null);
            this.u0 = null;
        }
        this.e0 = null;
        y yVar = this.W;
        if (yVar != null) {
            unregisterReceiver(yVar);
            this.W = null;
        }
        if (this.X != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.X);
            this.X = null;
        }
        if (this.o0 != null) {
            getContentResolver().unregisterContentObserver(this.o0);
            this.o0 = null;
        }
        this.d0.l();
        this.d0.o(null);
        this.d0.n();
        this.d0 = null;
        this.F0.release();
        this.F0 = null;
        super.onDestroy();
        com.byd.util.l lVar = this.E0;
        if (lVar != null) {
            lVar.x();
            this.E0 = null;
        }
        if (this.T && this.g.d() && com.byd.share.c.y() && com.byd.share.c.w(this).B()) {
            com.byd.share.c.w(this).F();
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
    }

    @Override // com.byd.fragment.BaseProgressDialogFragment.OnDismissListener
    public void onDismiss(BaseProgressDialogFragment baseProgressDialogFragment) {
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, b.a.k.h.b
    public void onExternalEject(String str, boolean z2) {
        com.byd.util.w.c("ImageShow onExternalEject : " + str + " " + z2);
        b.a.e.n nVar = this.u;
        if (nVar != null && !nVar.s() && this.u.getCount() != 0) {
            if (!new File(this.u.b().get(this.t.getCurrentItem()).getPath()).exists()) {
                h1();
                return;
            } else {
                if (this.u.o(str)) {
                    runOnUiThread(new l());
                    return;
                }
                return;
            }
        }
        Uri uri = this.e0;
        if (uri != null) {
            String path = uri.getPath();
            String str2 = File.separator;
            if (!str.endsWith(str2)) {
                str = str + str2;
            }
            if (path == null || !path.contains(str)) {
                return;
            }
            h1();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        com.byd.util.w.c("ImageShowActivity onNewIntent");
        setIntent(intent);
        boolean booleanExtra = intent.getBooleanExtra("more_dev_share", false);
        this.Z = booleanExtra;
        if (booleanExtra) {
            this.a0 = (ShareItem) intent.getParcelableExtra("share_item");
        }
        o1();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.r0 = true;
        super.onPause();
        com.byd.util.w.c("ImageShowBaseActivity onPause");
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        b.a.e.n nVar;
        super.onResume();
        if (this.r0) {
            this.r0 = false;
            if (!this.q0 && !this.F0.hasMessages(100)) {
                this.F0.sendEmptyMessageDelayed(100, 300L);
            }
            if (!this.Z && (nVar = this.u) != null && !nVar.s() && this.u.getCount() > 0) {
                new Thread(new w(this, null)).start();
            }
        }
        com.byd.util.w.c("ImageShowBaseActivity onResume");
    }

    @Override // com.byd.share.d.e
    public void onShareIconClick() {
        Intent intent = new Intent(this, (Class<?>) ShareToOtherDevActivity.class);
        ArrayList<String> arrayList = new ArrayList<>(1);
        b.a.e.n nVar = this.u;
        if (nVar == null || nVar.b() == null || this.y >= this.u.getCount()) {
            return;
        }
        FileBean fileBean = this.u.b().get(this.y);
        arrayList.add(com.byd.share.a.s(this).o(fileBean.getPath()));
        intent.putStringArrayListExtra("paths", arrayList);
        intent.putExtra("data_type", fileBean.getType());
        intent.putExtra("cover_path", fileBean.getPath());
        startActivity(intent);
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        com.byd.util.w.c("ImageShowBaseActivity onStart");
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        if (this.F0.hasMessages(100)) {
            this.F0.removeMessages(100);
        }
        super.onStop();
        getWindow().getDecorView().clearAnimation();
        com.byd.util.w.c("ImageShowBaseActivity onStop");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        com.byd.util.w.c("ImageShowBaseActivity onWindowFocusChanged : " + z2);
    }

    protected boolean q1(FileBean fileBean) {
        if (fileBean.getCollected() == 0) {
            String path = fileBean.getPath();
            if (fileBean.getType() == 3) {
                path = new File(fileBean.getPath()).getParent();
            }
            fileBean.setCollected(com.byd.util.f.k(this).o(path) ? 1 : 2);
        }
        return fileBean.isCollected();
    }

    protected boolean s1() {
        if (!t1()) {
            com.byd.util.w.c("no theme app found!");
            return false;
        }
        try {
            Class.forName("android.app.WallpaperManager").getDeclaredMethod("setBYDWallpaper", Bitmap.class, Bitmap.class, Bitmap.class, Bitmap.class).setAccessible(true);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    protected boolean t1() {
        return com.byd.util.e.x(this, "com.byd.multipletheme") || com.byd.util.e.x(this, "com.byd.automultipletheme");
    }

    protected int w1(Bitmap bitmap, Bitmap bitmap2) {
        try {
            if (this.z0 == null) {
                this.z0 = WallpaperManager.getInstance(this);
            }
            Method declaredMethod = this.z0.getClass().getDeclaredMethod("setBYDWallpaper", Bitmap.class, Bitmap.class, Bitmap.class, Bitmap.class);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(this.z0, bitmap, bitmap, bitmap2, bitmap2)).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            com.byd.util.w.c("setWallPaper exception : " + e2.getMessage());
            return 0;
        }
    }

    protected void x1(String str) {
    }

    protected void y1() {
    }

    protected void z1() {
        this.K.setSystemUiVisibility(5895);
    }
}
