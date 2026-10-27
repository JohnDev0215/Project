package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.pager.dialogpager.ImeAdapter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: DefImePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.m.e.a<b.b.g.n.p.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1603b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1604c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<b.b.g.m.d.b> f1605d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<b.b.g.m.d.b> f1606e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.b.g.m.d.b f1607f;
    public RecyclerView g;
    public CustomViewDialog h;

    /* JADX INFO: compiled from: DefImePager.java */
    public class a implements b.b.a.j.d.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImeAdapter f1608a;

        public a(ImeAdapter imeAdapter) {
            this.f1608a = imeAdapter;
        }

        @Override // b.b.a.j.d.c
        public void a(View view, int i) {
            h hVar = h.this;
            hVar.f1607f = (b.b.g.m.d.b) hVar.f1605d.get(i);
            for (int i2 = 0; i2 < h.this.f1605d.size(); i2++) {
                ((b.b.g.m.d.b) h.this.f1605d.get(i2)).g(false);
                if (i2 == i) {
                    ((b.b.g.m.d.b) h.this.f1605d.get(i2)).f(true);
                } else {
                    ((b.b.g.m.d.b) h.this.f1605d.get(i2)).f(false);
                }
            }
            this.f1608a.notifyDataSetChanged();
        }

        @Override // b.b.a.j.d.c
        public void b(View view, int i) {
        }
    }

    /* JADX INFO: compiled from: DefImePager.java */
    public class b implements d.a {
        public b() {
        }

        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            h.this.l();
        }
    }

    /* JADX INFO: compiled from: DefImePager.java */
    public class c implements d.a {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            ((b.b.g.n.p.c) h.this.mo7getP()).g(h.this.f1607f);
        }
    }

    public h(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.h = customViewDialog;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_def_ime_system;
    }

    public final void h() {
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        b.b.g.q.d.u.d.q((Activity) this.ref.get(), new b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        List<b.b.g.m.d.b> listD = ((b.b.g.n.p.c) mo7getP()).d();
        this.f1606e = listD;
        if (listD != null && !listD.isEmpty()) {
            Iterator<b.b.g.m.d.b> it = this.f1606e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b.b.g.m.d.b next = it.next();
                if ("com.iqqijni.car.keyboard".equals(next.a().getPackageName())) {
                    ((b.b.g.n.p.c) mo7getP()).c(next);
                    break;
                }
            }
        }
        this.f1603b.setText(context.getString(R.string.def_input_method));
        l();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.c) mo7getP()).f();
        this.f1603b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.mRootView.findViewById(R.id.iv_back_dialog_common_title).setVisibility(8);
        this.f1604c = this.mRootView.findViewById(R.id.ll_operate_dialog_def_input_system);
        this.f1602a = (ViewGroup) this.mRootView.findViewById(R.id.ll_opertion_dialog_bottom);
        this.g = (RecyclerView) this.mRootView.findViewById(R.id.rv_ime);
        this.mRootView.findViewById(R.id.tv_add_ime_input_system).setOnClickListener(this);
        this.mRootView.findViewById(R.id.tv_remove_ime_input_system).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_ok_dialog_bottom).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom).setOnClickListener(this);
    }

    public final void k() {
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.l(activity, activity.getString(R.string.remove_input_method), activity.getString(R.string.msg_remove_input_method), new c());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void l() {
        List<b.b.g.m.d.b> listE = ((b.b.g.n.p.c) mo7getP()).e();
        this.f1605d = listE;
        if (listE != null && !listE.isEmpty()) {
            RecyclerView recyclerView = this.g;
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            ImeAdapter imeAdapter = new ImeAdapter(this.g.getContext(), this.f1605d);
            imeAdapter.setOnItemClickListener(new a(imeAdapter));
            this.g.setAdapter(imeAdapter);
        }
        this.f1606e = ((b.b.g.n.p.c) mo7getP()).d();
        b.b.m.c.a.a(this.TAG, "--initData---inputList = " + this.f1605d);
        List<b.b.g.m.d.b> list = this.f1606e;
        if (list == null || list.isEmpty()) {
            this.f1604c.setVisibility(8);
        } else {
            this.f1604c.setVisibility(0);
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.p.c newP() {
        return new b.b.g.n.p.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_dialog_bottom /* 2131296463 */:
                CustomViewDialog customViewDialog = this.h;
                if (customViewDialog != null) {
                    customViewDialog.dismiss();
                }
                break;
            case R.id.btn_ok_dialog_bottom /* 2131296518 */:
                if (this.f1607f != null) {
                    ((b.b.g.n.p.c) mo7getP()).h(this.f1607f);
                }
                d.a aVar = this.mOkListener;
                if (aVar != null) {
                    aVar.onOKClicked();
                }
                CustomViewDialog customViewDialog2 = this.h;
                if (customViewDialog2 != null) {
                    customViewDialog2.dismiss();
                }
                EventBus.getDefault().post(new b.b.a.c.a(230005));
                break;
            case R.id.tv_add_ime_input_system /* 2131298361 */:
                h();
                break;
            case R.id.tv_remove_ime_input_system /* 2131298693 */:
                k();
                break;
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1602a, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.f1602a, false);
    }
}
