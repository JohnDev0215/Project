package com.byd.systemsettings.ui.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.systemsettings.R;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class ItemListPager extends a {
    private RecyclerView rcv;
    private View rlDataShow;
    private View rlNoDataShow;
    private TextView tvNoData;
    private TextView tvTitle;

    public ItemListPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_list;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
        this.rcv.setLayoutManager(new LinearLayoutManager(context, 1, false));
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_list);
        this.tvNoData = (TextView) this.mRootView.findViewById(R.id.tv_no_data_dialog_list);
        this.rlDataShow = this.mRootView.findViewById(R.id.rl_data_show_dialog_list);
        this.rlNoDataShow = this.mRootView.findViewById(R.id.rl_no_data_show_dialog_list);
        this.rcv = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dialog_list);
        this.mRootView.findViewById(R.id.iv_back_dialog_list).setOnClickListener(this);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back_dialog_list) {
            finish();
        }
    }

    public void setBydAdapter(c4.a aVar) {
        x3.a.a(this.TAG, "---setBydAdapter---adapter = " + aVar);
        if (aVar == null) {
            return;
        }
        this.rcv.setAdapter(aVar);
        if (aVar.getModel() == null || aVar.getModel().size() <= 0) {
            this.rlDataShow.setVisibility(8);
            this.rlNoDataShow.setVisibility(0);
        } else {
            this.rlDataShow.setVisibility(0);
            this.rlNoDataShow.setVisibility(8);
        }
    }

    public void setNoDataText(String str) {
        TextView textView = this.tvNoData;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }

    @Override // z3.a
    public void setTitleText(String str) {
        TextView textView = this.tvTitle;
        if (textView != null) {
            if (str == null) {
                str = "";
            }
            textView.setText(str);
        }
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }
}
