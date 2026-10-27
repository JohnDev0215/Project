package b.b.g.q.f.c;

import android.content.Context;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.m;
import b.b.g.m.d.i;
import b.b.g.n.p.d;
import b.b.g.p.h;
import b.b.m.e.f;
import b.b.m.f.c.a.e;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.PermissionListAdapter;
import com.byd.carsettings.adapter.dilink.SubPermissionAppListAdapter;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.mvp.utils.AppConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: PermissionPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends f<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RecyclerView f1736a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f1737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1738c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1739d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f1740e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<b.b.g.m.d.d> f1741f;
    public List<i> g;
    public PermissionListAdapter h;
    public SubPermissionAppListAdapter i;
    public int j;
    public b.b.g.q.f.c.a k;

    /* JADX INFO: compiled from: PermissionPager.java */
    public class a implements e {
        public a() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            b.b.m.c.a.a(b.this.TAG, "---initViews---onItemClicked----position = " + i);
            b.this.p(i);
            b bVar = b.this;
            bVar.n((b.b.g.m.d.d) bVar.f1741f.get(i));
        }
    }

    /* JADX INFO: renamed from: b.b.g.q.f.c.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PermissionPager.java */
    public class C0072b implements BydAdapter.a {
        public C0072b() {
        }

        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            b.b.m.c.a.a(b.this.TAG, "---initViews---onItemBtnChecked----position = " + i + ", checked = " + z);
            b bVar = b.this;
            bVar.m((i) bVar.g.get(i), z);
        }
    }

    public b(Context context) {
        super(context);
        this.j = 0;
    }

    @Override // b.b.m.e.f
    public Context getContext() {
        return this.f1740e;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_permission;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        ((d) mo7getP()).i(33, 40);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1740e = context;
        this.f1739d = this.mRootView.findViewById(R.id.empty_ll);
        this.f1736a = (RecyclerView) this.mRootView.findViewById(R.id.rcv_left_permission_pager);
        this.f1737b = (RecyclerView) this.mRootView.findViewById(R.id.rcv_right_permission_pager);
        this.f1738c = this.mRootView.findViewById(R.id.ll_no_data_permission_pager);
        ArrayList arrayList = new ArrayList();
        this.f1741f = arrayList;
        PermissionListAdapter permissionListAdapter = new PermissionListAdapter(arrayList);
        this.h = permissionListAdapter;
        this.f1736a.setAdapter(permissionListAdapter);
        this.f1736a.setLayoutManager(new LinearLayoutManager(this.f1740e, 1, false));
        m.b(this.f1736a);
        this.h.setOnItemClickedListener(new a());
        ArrayList arrayList2 = new ArrayList();
        this.g = arrayList2;
        SubPermissionAppListAdapter subPermissionAppListAdapter = new SubPermissionAppListAdapter(arrayList2);
        this.i = subPermissionAppListAdapter;
        this.f1737b.setAdapter(subPermissionAppListAdapter);
        this.f1737b.setLayoutManager(new LinearLayoutManager(this.f1740e, 1, false));
        m.b(this.f1737b);
        this.i.setOnItemBtnCheckedCallback(new C0072b());
        if (AppConfig.a() == CarModel$Brand.DENZA) {
            this.f1736a.setBackgroundResource(R.drawable.bg_dialog_bt_left_tenz_land);
        }
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void m(i iVar, boolean z) {
        b.b.m.c.a.a(this.TAG, "---changePermission-----subApp = " + iVar + ", checked = " + z);
        ((d) mo7getP()).j(iVar, z, 33, 42);
        if (z || iVar == null) {
            return;
        }
        if ("com.neusoft.na.navigation".equals(iVar.d()) || "com.huawei.maps.auto.app".equals(iVar.d()) || "com.byd.automap".equals(iVar.d()) || "net.zmap.znav.byd8155".equals(iVar.d()) || "com.mappls.auto.bydznav8155F".equals(iVar.d())) {
            h.d();
        }
    }

    public final void n(b.b.g.m.d.d dVar) {
        this.g.clear();
        List listB = dVar.b();
        if (listB != null && listB.size() > 0) {
            this.g.addAll(listB);
        }
        b.b.m.c.a.a(this.TAG, "---changePermissionList--mSubAppList = " + this.g);
        this.i.notifyDataSetChanged();
        if (this.g.size() <= 0) {
            this.f1737b.setVisibility(8);
            this.f1738c.setVisibility(0);
        } else {
            this.f1737b.setVisibility(0);
            this.f1738c.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
        if (i == 40) {
            r(message);
            return;
        }
        if (i != 42) {
            return;
        }
        ((d) mo7getP()).i(33, 40);
        b.b.g.q.f.c.a aVar = this.k;
        if (aVar != null) {
            aVar.initData(getContext());
        }
    }

    public final void p(int i) {
        this.j = i;
        int i2 = 0;
        while (i2 < this.f1741f.size()) {
            this.f1741f.get(i2).h(i == i2);
            i2++;
        }
        this.h.notifyDataSetChanged();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public d newP() {
        return new d();
    }

    public final void r(Message message) {
        List list = (List) message.obj;
        b.b.m.c.a.a(this.TAG, "---onReceivePermissionList--list = " + list);
        this.f1741f.clear();
        if (list != null) {
            this.f1741f.addAll(list);
        }
        Collections.sort(this.f1741f);
        this.f1741f.get(this.j).h(true);
        this.h.notifyDataSetChanged();
        if (this.f1741f.size() <= 0) {
            this.f1739d.setVisibility(0);
        } else {
            n(this.f1741f.get(this.j));
            this.f1739d.setVisibility(8);
        }
    }

    public void s(b.b.g.q.f.c.a aVar) {
        this.k = aVar;
    }
}
