package com.byd.carsettings.adapter.dilink;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.m.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.PermissionType;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionListAdapter extends BydAdapter<d> {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2734a;

        public a(int i) {
            this.f2734a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PermissionListAdapter.this.mItemClickListener != null) {
                PermissionListAdapter.this.mItemClickListener.onItemClicked(this.f2734a);
            }
        }
    }

    public class b extends BydAdapter<d>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2736a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2737b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2738c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2739d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f2740e;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2736a = (ImageView) view.findViewById(R.id.iv_app_icon_item_permission_app_list);
            this.f2737b = (TextView) view.findViewById(R.id.tv_app_name_item_permission_app_list);
            this.f2738c = (TextView) view.findViewById(R.id.tv_permission_info_item_permission_app_list);
            this.f2739d = view.findViewById(R.id.v_item_bg_item_permission_app_list);
            this.f2740e = view.findViewById(R.id.v_line_item_permission_app_list);
        }
    }

    public PermissionListAdapter(List<d> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<d>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_permission_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        d dVar = (d) this.data.get(i);
        PermissionType permissionTypeE = dVar.e();
        if (permissionTypeE != null && permissionTypeE.getImgId() != 0) {
            bVar.f2736a.setImageResource(permissionTypeE.getImgId());
        }
        bVar.f2737b.setText(dVar.d());
        Context context = bVar.f2737b.getContext();
        String string = context.getString(R.string.no_app_permission_app_manage);
        if (dVar.b() != null) {
            string = String.format(context.getString(R.string.app_info_permission_app_manage), dVar.c(context) + "", dVar.b().size() + "");
        }
        bVar.f2738c.setText(string);
        viewHolder.itemView.setOnClickListener(new a(i));
        boolean zF = dVar.f();
        bVar.f2739d.setBackgroundResource(zF ? R.drawable.bg_frame_blue_content_eaf4ff : R.color.transparent);
        int color = context.getResources().getColor(R.color.text_color_main);
        int color2 = context.getResources().getColor(R.color.text_color_main_gray);
        if (zF) {
            int color3 = context.getResources().getColor(R.color.text_color_btn_blue);
            color2 = context.getResources().getColor(R.color.text_color_btn_blue);
            color = color3;
        }
        bVar.f2737b.setTextColor(color);
        bVar.f2738c.setTextColor(color2);
        if (i == this.data.size() - 1) {
            bVar.f2740e.setVisibility(8);
        } else {
            bVar.f2740e.setVisibility(0);
        }
    }
}
