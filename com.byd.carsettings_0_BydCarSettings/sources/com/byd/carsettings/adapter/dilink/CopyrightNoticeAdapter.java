package com.byd.carsettings.adapter.dilink;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.CopyrightNoticeApp;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CopyrightNoticeAdapter extends BydAdapter<CopyrightNoticeApp> {

    public class a extends BydAdapter<CopyrightNoticeApp>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2704a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ImageView f2705b;

        public a(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2704a = (TextView) view.findViewById(R.id.tv_app_name_copyright_notice);
            this.f2705b = (ImageView) view.findViewById(R.id.iv_copyright_goto);
        }
    }

    public CopyrightNoticeAdapter(List<CopyrightNoticeApp> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<CopyrightNoticeApp>.BaseViewHolder initViewHolder(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_copyright_notice_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        aVar.id = i;
        aVar.f2704a.setText(((CopyrightNoticeApp) this.data.get(i)).appName);
    }
}
