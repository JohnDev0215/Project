package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.NotificationAppAdapter;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: compiled from: NotificationAppListPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class v extends b.b.m.e.a<b.b.g.n.j.m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1718a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1719b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1720c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1721d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RecyclerView f1722e;

    /* JADX INFO: compiled from: NotificationAppListPager.java */
    public class a implements BydAdapter.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f1723a;

        public a(List list) {
            this.f1723a = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            b.b.m.c.a.a(v.this.TAG, "--onNotificationOperate--position: " + i + ", checked: " + z);
            ((b.b.g.n.j.m) v.this.mo7getP()).c((b.b.g.p.k.a) this.f1723a.get(i), z);
        }
    }

    public v(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.m newP() {
        return new b.b.g.n.j.m();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.dialog_list_r : R.layout.dialog_list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        List<b.b.g.p.k.a> listD = ((b.b.g.n.j.m) mo7getP()).d();
        NotificationAppAdapter notificationAppAdapter = new NotificationAppAdapter(listD);
        notificationAppAdapter.setOnItemBtnCheckedCallback(new a(listD));
        this.f1722e.setAdapter(notificationAppAdapter);
        if (notificationAppAdapter.getModel() == null || notificationAppAdapter.getModel().size() <= 0) {
            this.f1720c.setVisibility(8);
            this.f1721d.setVisibility(0);
        } else {
            this.f1720c.setVisibility(0);
            this.f1721d.setVisibility(8);
        }
        String string = context.getString(R.string.notification_app_manage);
        String string2 = context.getString(R.string.no_data_notification_dialog);
        this.f1718a.setText(string);
        this.f1719b.setText(string2);
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_544);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.j.m) mo7getP()).e();
        this.f1718a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_list);
        this.f1719b = (TextView) this.mRootView.findViewById(R.id.tv_no_data_dialog_list);
        this.f1720c = this.mRootView.findViewById(R.id.rl_data_show_dialog_list);
        this.f1721d = this.mRootView.findViewById(R.id.rl_no_data_show_dialog_list);
        this.f1722e = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dialog_list);
        this.mRootView.findViewById(R.id.iv_back_dialog_list).setOnClickListener(this);
        this.f1722e.setLayoutManager(new LinearLayoutManager(context, 1, false));
        b.b.a.h.m.b(this.f1722e);
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.e.d dVar;
        if (view.getId() != R.id.iv_back_dialog_list || (dVar = this.mCallback) == null) {
            return;
        }
        dVar.onBackPress();
    }
}
