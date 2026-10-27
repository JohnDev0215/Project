package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.AppPermissionAdapter;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AppPermissionsPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.a<b.b.g.n.j.b> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1566b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f1567c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RecyclerView f1568d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<b.b.g.m.d.c> f1569e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AppPermissionAdapter f1570f;
    public CustomViewDialog g;
    public String h;
    public Activity i;

    /* JADX INFO: compiled from: AppPermissionsPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (d.this.mCallback != null) {
                d.this.mCallback.onBackPress();
            }
            if (d.this.i != null) {
                d.this.i.finish();
            }
            if (d.this.g != null) {
                d.this.g.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: AppPermissionsPager.java */
    public class b implements BydAdapter.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1572a;

        public b(Context context) {
            this.f1572a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            String str = d.this.h;
            b.b.g.m.d.c cVar = (b.b.g.m.d.c) d.this.f1569e.get(i);
            ((b.b.g.n.j.b) d.this.mo7getP()).l(str, cVar.e(), cVar.c(), z);
            b.b.m.c.a.a(d.this.TAG, "result isEnable：" + cVar.f(this.f1572a));
            if (z) {
                return;
            }
            if ("com.neusoft.na.navigation".equals(str) || "com.huawei.maps.auto.app".equals(str) || "com.byd.automap".equals(str) || "net.zmap.znav.byd8155".equals(str) || "com.mappls.auto.bydznav8155F".equals(str)) {
                b.b.g.p.h.d();
            }
        }
    }

    public d(Context context, Activity activity) {
        super(context);
        this.i = activity;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_app_permissions;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        ArrayList arrayList = new ArrayList();
        this.f1569e = arrayList;
        AppPermissionAdapter appPermissionAdapter = new AppPermissionAdapter(arrayList);
        this.f1570f = appPermissionAdapter;
        appPermissionAdapter.setOnItemBtnCheckedCallback(new b(context));
        this.f1568d.setLayoutManager(new LinearLayoutManager(this.mRootView.getContext(), 1, false));
        this.f1568d.setAdapter(this.f1570f);
        b.b.a.h.m.b(this.f1568d);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_544);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        initLoadingView(R.id.ll_status_in_loading);
        this.f1565a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_app_permissions);
        this.f1567c = (ImageView) this.mRootView.findViewById(R.id.iv_icon_dialog_app_permissions);
        this.f1566b = (TextView) this.mRootView.findViewById(R.id.tv_app_name_dialog_app_permissions);
        this.f1568d = (RecyclerView) this.mRootView.findViewById(R.id.rcv_permission_list_app_permissions);
        ((ImageView) this.mRootView.findViewById(R.id.iv_back_dialog_app_permissions)).setOnClickListener(new a());
        this.f1565a.setText(getContext().getString(R.string.title_permission_app_details));
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.b newP() {
        return new b.b.g.n.j.b();
    }

    public void n(Message message) {
        List list = (List) message.obj;
        b.b.m.c.a.a(this.TAG, "---onReceivedAppInfoList---list = " + list);
        this.f1569e.clear();
        if (list != null && !list.isEmpty()) {
            this.f1569e.addAll(list);
        }
        if (this.f1569e.size() <= 0) {
            this.f1568d.setVisibility(8);
            onLoadNodata();
        } else {
            onLoadGetdata();
            this.f1568d.setVisibility(0);
        }
        this.f1570f.notifyDataSetChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        BydAppInfo bydAppInfo;
        int i = message.arg1;
        if (i == 49) {
            n(message);
        } else if (i == 65 && (bydAppInfo = (BydAppInfo) message.obj) != null) {
            this.f1566b.setText(bydAppInfo.getAppName());
            this.f1567c.setImageDrawable(bydAppInfo.getIcon());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void p(String str) {
        this.h = str;
        ((b.b.g.n.j.b) mo7getP()).i(this.h, 33, 65);
        ((b.b.g.n.j.b) mo7getP()).j(this.h, 33, 49);
    }

    public d(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.g = customViewDialog;
        customViewDialog.w();
    }
}
