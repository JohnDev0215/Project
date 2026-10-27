package b.b.g.q.f.d;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import b.b.g.o.c;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.LinearItem;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: BSPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends i implements c.f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinearItem f1744f;
    public LinearItem g;
    public ViewGroup h;
    public ViewGroup i;
    public ViewGroup j;
    public ImageView k;
    public SegmentedButtonGroup l;
    public String[] m;
    public String[] n;

    /* JADX INFO: renamed from: b.b.g.q.f.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BSPager.java */
    public class C0073a extends b.b.a.j.d.a {
        public C0073a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            a.this.v();
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class b extends b.b.a.j.d.a {
        public b() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            a.this.t();
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class c extends b.b.a.j.d.a {
        public c() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            a.this.s();
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class d implements SegmentedButtonGroup.f {
        public d() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            a.this.u(i);
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.g.setClickable(true);
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.f1744f.setClickable(true);
        }
    }

    /* JADX INFO: compiled from: BSPager.java */
    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.c.a f1751a;

        public g(b.b.a.c.a aVar) {
            this.f1751a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            switch (this.f1751a.h()) {
                case 230006:
                    String strG = this.f1751a.g();
                    a.this.f1744f.setRightText(strG != null ? strG : "");
                    break;
                case 230007:
                    String strG2 = this.f1751a.g();
                    a.this.g.setRightText(strG2 != null ? strG2 : "");
                    break;
            }
        }
    }

    public a(Context context, Fragment fragment) {
        super(context);
    }

    @Override // b.b.g.o.c.f
    public void a() {
        loadData();
    }

    @Override // b.b.g.q.f.d.i
    public void b(int i) {
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_sound_bs;
    }

    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.e) mo7getP()).w();
        this.f1744f = (LinearItem) this.mRootView.findViewById(R.id.item_sound_field_focusing_sound_bs);
        this.g = (LinearItem) this.mRootView.findViewById(R.id.item_sound_charact_sound_bs);
        this.h = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_sound_all_dimensional_sound_bs);
        this.i = (ViewGroup) this.mRootView.findViewById(R.id.ll_sound_all_dimensional_sound_bs);
        this.j = (ViewGroup) this.mRootView.findViewById(R.id.ll_sound_regulate_sound_bs);
        this.k = (ImageView) this.mRootView.findViewById(R.id.iv_sound_regulate_detail_sound_bs);
        this.l = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_sound_regulate_sound_bs);
        this.k.setOnClickListener(new C0073a());
        this.f1744f.setOnClickListener(new b());
        this.g.setOnClickListener(new c());
        this.l.setOnPositionChangedListener(new d());
        this.i.setVisibility(8);
        c.d.a().X(this);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        if (this.mRootView == null) {
            return;
        }
        this.m = this.mRootView.getResources().getStringArray(R.array.ziyan_focus_array);
        this.f1744f.setRightText(this.m[((b.b.g.n.p.e) mo7getP()).m()]);
        this.n = this.mRootView.getResources().getStringArray(R.array.ziyan_eq_array);
        this.g.setRightText(this.n[((b.b.g.n.p.e) mo7getP()).l()]);
        int iT = ((b.b.g.n.p.e) mo7getP()).t();
        b.b.m.c.a.a(this.TAG, "loadData", "gear: " + iT);
        if (iT < 0 || iT >= this.l.getButtonChildCount()) {
            return;
        }
        this.l.setPosition(iT);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        this.k.setOnClickListener(null);
        this.f1744f.setOnClickListener(null);
        this.g.setOnClickListener(null);
        k(null);
        setOnItemClickedListener(null);
        b.b.m.f.d.d.c(a.class.getName());
        c.d.a().X(null);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        b.b.a.f.h.a().g(new g(aVar), 500L);
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.f1744f, false);
            setViewGroupRtl(this.g, false);
            setViewGroupRtl(this.j, false);
        }
        setViewGroupRtl(this.i, true);
        setViewGroupRtl(this.h, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.f1744f, true);
            setViewGroupRtl(this.g, true);
            setViewGroupRtl(this.j, true);
        }
        setViewGroupRtl(this.i, false);
        setViewGroupRtl(this.h, true);
    }

    public final void s() {
        b.b.m.c.a.a(this.TAG, "----onSoundCharactOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        this.f1744f.setClickable(false);
        CustomViewDialog.y(6).show(((FragmentActivity) this.ref.get()).getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new f(), 200L);
    }

    public final void t() {
        b.b.m.c.a.a(this.TAG, "----onSoundFieldOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        this.g.setClickable(false);
        CustomViewDialog.y(5).show(((FragmentActivity) this.ref.get()).getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new e(), 200L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void u(int i) {
        b.b.m.c.a.a(this.TAG, "onSoundRegulateChanged", "position: " + i);
        ((b.b.g.n.p.e) mo7getP()).z(i);
    }

    public final void v() {
        b.b.m.c.a.a(this.TAG, "----onSoundRegulateDetailOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        FragmentActivity fragmentActivity = (FragmentActivity) this.ref.get();
        SimpleDialog.z(fragmentActivity.getString(R.string.title_dialog_sound_regulate_dynaudio_sound), fragmentActivity.getString(R.string.msg_dialog_sound_regulate_bs_sound), fragmentActivity.getString(R.string.dialog_btn_ok), "", 0).show(fragmentActivity.getSupportFragmentManager(), "SimpleDialog");
    }
}
