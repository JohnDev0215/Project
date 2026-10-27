package b.b.g.q.f.b;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: compiled from: AccessibilityAppListPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b.b.m.e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1544a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1545b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1546c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RecyclerView f1547d;

    public a(Context context) {
        super(context);
    }

    public void b(BydAdapter bydAdapter) {
        b.b.m.c.a.a(this.TAG, "---setBydAdapter---adapter = " + bydAdapter);
        if (bydAdapter == null) {
            return;
        }
        this.f1547d.setAdapter(bydAdapter);
        if (bydAdapter.getModel() == null || bydAdapter.getModel().size() <= 0) {
            this.f1545b.setVisibility(8);
            this.f1546c.setVisibility(0);
        } else {
            this.f1545b.setVisibility(0);
            this.f1546c.setVisibility(8);
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_accessibility_app_list;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1547d.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.f1544a.setText(context.getString(R.string.accessibility_app_manage));
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1544a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_accessibility_app_list);
        this.f1545b = this.mRootView.findViewById(R.id.rl_data_show_dialog_accessibility_app_list);
        this.f1546c = this.mRootView.findViewById(R.id.rl_no_data_show_dialog_accessibility_app_list);
        this.f1547d = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dialog_accessibility_app_list);
        this.mRootView.findViewById(R.id.iv_back_dialog_accessibility_app_list).setOnClickListener(this);
    }

    @Override // b.b.m.e.h
    public b.b.m.e.g newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back_dialog_accessibility_app_list) {
            finish();
        }
    }

    @Override // b.b.m.e.a
    public void setTitleText(String str) {
        TextView textView = this.f1544a;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
