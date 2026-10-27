package b.b.g.q.f.c;

import android.content.Context;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.g.m.d.c;
import b.b.g.m.d.g;
import b.b.g.p.h;
import b.b.m.e.f;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.AppPermissionAdapter;
import com.byd.carsettings.adapter.dilink.PermissionAppListAdapter;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AppListPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends f<b.b.g.n.p.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView f1728a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1729b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1730c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1731d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<g> f1732e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<c> f1733f;
    public PermissionAppListAdapter g;
    public AppPermissionAdapter h;
    public Context i;
    public g j;
    public int k;
    public b.b.g.q.f.c.b l;
    public String m;

    /* JADX INFO: renamed from: b.b.g.q.f.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppListPager.java */
    public class C0071a implements e {
        public C0071a() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a(a.this.TAG, "---initViews---onItemClicked----position = " + i);
            a.this.n(i);
            a aVar = a.this;
            aVar.m((g) aVar.f1732e.get(i));
        }
    }

    /* JADX INFO: compiled from: AppListPager.java */
    public class b implements BydAdapter.a {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            b.b.m.c.a.a(a.this.TAG, "---initViews---onItemBtnChecked----position: " + i + ", checked: " + z);
            if (a.this.j != null) {
                String str = a.this.j.e().packageName;
                a.this.m = str;
                ((b.b.g.n.p.b) a.this.mo7getP()).j(str, a.this.j.f().get(i), z, 33, 42);
                if (z) {
                    return;
                }
                if ("com.neusoft.na.navigation".equals(str) || "com.huawei.maps.auto.app".equals(str) || "com.byd.automap".equals(str) || "net.zmap.znav.byd8155".equals(str) || "com.mappls.auto.bydznav8155F".equals(str)) {
                    h.d();
                }
            }
        }
    }

    public a(Context context) {
        super(context);
        this.k = 0;
    }

    @Override // b.b.m.e.f
    public Context getContext() {
        return this.i;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_app_list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        ((b.b.g.n.p.b) mo7getP()).i(33, 35);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.i = context;
        this.f1731d = this.mRootView.findViewById(R.id.empty_ll);
        this.f1728a = (RecyclerView) this.mRootView.findViewById(R.id.rcv_left_app_list_pager);
        this.f1729b = (RecyclerView) this.mRootView.findViewById(R.id.rcv_right_app_list_pager);
        this.f1730c = this.mRootView.findViewById(R.id.ll_no_data_app_list_pager);
        ArrayList arrayList = new ArrayList();
        this.f1732e = arrayList;
        PermissionAppListAdapter permissionAppListAdapter = new PermissionAppListAdapter(arrayList);
        this.g = permissionAppListAdapter;
        this.f1728a.setAdapter(permissionAppListAdapter);
        this.f1728a.setLayoutManager(new LinearLayoutManager(this.i, 1, false));
        m.b(this.f1728a);
        this.g.setOnItemClickedListener(new C0071a());
        ArrayList arrayList2 = new ArrayList();
        this.f1733f = arrayList2;
        AppPermissionAdapter appPermissionAdapter = new AppPermissionAdapter(arrayList2);
        this.h = appPermissionAdapter;
        this.f1729b.setAdapter(appPermissionAdapter);
        this.f1729b.setLayoutManager(new LinearLayoutManager(this.i, 1, false));
        m.b(this.f1729b);
        this.h.setOnItemBtnCheckedCallback(new b());
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    public final void m(g gVar) {
        this.j = gVar;
        this.f1733f.clear();
        List<c> listF = gVar.f();
        if (listF != null && listF.size() > 0) {
            this.f1733f.addAll(listF);
        }
        b.b.m.c.a.a(this.TAG, "---changePermissionList--permissions = " + listF);
        this.h.notifyDataSetChanged();
        if (this.f1733f.size() <= 0) {
            this.f1729b.setVisibility(8);
            this.f1730c.setVisibility(0);
        } else {
            this.f1729b.setVisibility(0);
            this.f1730c.setVisibility(8);
        }
    }

    public final void n(int i) {
        this.k = i;
        int i2 = 0;
        while (i2 < this.f1732e.size()) {
            this.f1732e.get(i2).h(i == i2);
            i2++;
        }
        this.g.notifyDataSetChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
        if (i == 35) {
            q(message, false);
            return;
        }
        if (i == 36) {
            q(message, true);
            return;
        }
        if (i != 42) {
            return;
        }
        ((b.b.g.n.p.b) mo7getP()).i(33, 36);
        b.b.g.q.f.c.b bVar = this.l;
        if (bVar != null) {
            bVar.initData(getContext());
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.p.b newP() {
        return new b.b.g.n.p.b();
    }

    public final void q(Message message, boolean z) {
        List list = (List) message.obj;
        b.b.m.c.a.a(this.TAG, "---onReceiveAppList--list = " + list);
        this.f1732e.clear();
        if (list != null && !list.isEmpty()) {
            this.f1732e.addAll(list);
        }
        Collections.sort(this.f1732e);
        if (z) {
            for (g gVar : this.f1732e) {
                if (gVar.e().packageName.equals(this.m)) {
                    gVar.h(true);
                    m(gVar);
                } else {
                    gVar.h(false);
                }
            }
        } else {
            this.f1732e.get(this.k).h(true);
        }
        this.g.notifyDataSetChanged();
        b.b.m.c.a.a(this.TAG, "---onReceiveAppList--mAppList.size = " + this.f1732e.size());
        if (this.f1732e.size() <= 0) {
            this.f1731d.setVisibility(0);
            return;
        }
        if (!z) {
            m(this.f1732e.get(this.k));
        }
        this.f1731d.setVisibility(8);
    }

    public void r(b.b.g.q.f.c.b bVar) {
        this.l = bVar;
    }
}
