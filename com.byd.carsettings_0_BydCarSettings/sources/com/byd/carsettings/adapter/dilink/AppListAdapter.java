package com.byd.carsettings.adapter.dilink;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.r.i;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppListAdapter extends BydAdapter<BydAppInfo> {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2672a;

        public a(int i) {
            this.f2672a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (AppListAdapter.this.mItemClickListener != null) {
                AppListAdapter.this.mItemClickListener.onItemClicked(this.f2672a);
            }
        }
    }

    public class b extends BydAdapter<BydAppInfo>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2674a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2675b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2676c;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2674a = (ImageView) view.findViewById(R.id.iv_app_icon_item_app_list);
            this.f2675b = (TextView) view.findViewById(R.id.tv_app_name_item_app_list);
            this.f2676c = (TextView) view.findViewById(R.id.tv_app_size_item_app_list);
        }
    }

    public AppListAdapter(List<BydAppInfo> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<BydAppInfo>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        BydAppInfo bydAppInfo = (BydAppInfo) this.data.get(i);
        bVar.f2674a.setImageDrawable(bydAppInfo.getIcon());
        bVar.f2675b.setText(bydAppInfo.getAppName());
        bVar.f2676c.setText(i.c(bydAppInfo.getCodeBytes() + bydAppInfo.getCacheBytes() + bydAppInfo.getDataBytes()));
        viewHolder.itemView.setOnClickListener(new a(i));
        if (b.b.a.i.a.d()) {
            bVar.f2675b.setGravity(21);
            bVar.f2676c.setGravity(21);
        } else {
            bVar.f2675b.setGravity(19);
            bVar.f2676c.setGravity(19);
        }
    }
}
