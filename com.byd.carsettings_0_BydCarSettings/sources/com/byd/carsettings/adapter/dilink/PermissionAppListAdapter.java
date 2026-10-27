package com.byd.carsettings.adapter.dilink;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.m.d.g;
import com.byd.carsettings.R;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionAppListAdapter extends BydAdapter<g> {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2726a;

        public a(int i) {
            this.f2726a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PermissionAppListAdapter.this.mItemClickListener != null) {
                PermissionAppListAdapter.this.mItemClickListener.onItemClicked(this.f2726a);
            }
        }
    }

    public class b extends BydAdapter<g>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2728a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2729b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2730c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2731d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f2732e;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2728a = (ImageView) view.findViewById(R.id.iv_app_icon_item_permission_app_list);
            this.f2729b = (TextView) view.findViewById(R.id.tv_app_name_item_permission_app_list);
            this.f2730c = (TextView) view.findViewById(R.id.tv_permission_info_item_permission_app_list);
            this.f2731d = view.findViewById(R.id.v_item_bg_item_permission_app_list);
            this.f2732e = view.findViewById(R.id.v_line_item_permission_app_list);
        }
    }

    public PermissionAppListAdapter(List<g> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<g>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_permission_app_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String str;
        b bVar = (b) viewHolder;
        g gVar = (g) this.data.get(i);
        bVar.f2728a.setImageDrawable(gVar.d());
        bVar.f2729b.setText(gVar.b());
        Context context = bVar.f2729b.getContext();
        context.getString(R.string.no_permission_permission_app_manage);
        String string = context.getString(R.string.permission_info_permission_app_manage);
        if (gVar.f() != null) {
            str = String.format(string, gVar.c() + "", gVar.f().size() + "");
        } else {
            str = String.format(string, "0", "0");
        }
        bVar.f2730c.setText(str);
        viewHolder.itemView.setOnClickListener(new a(i));
        boolean zG = gVar.g();
        bVar.f2731d.setBackgroundResource(zG ? R.drawable.bg_frame_blue_content_eaf4ff : R.color.transparent);
        int color = context.getResources().getColor(R.color.text_color_main);
        int color2 = context.getResources().getColor(R.color.text_color_main_gray);
        if (zG) {
            int color3 = context.getResources().getColor(R.color.text_color_btn_blue);
            color2 = context.getResources().getColor(R.color.text_color_btn_blue);
            color = color3;
        }
        bVar.f2729b.setTextColor(color);
        bVar.f2730c.setTextColor(color2);
        if (i == this.data.size() - 1) {
            bVar.f2732e.setVisibility(8);
        } else {
            bVar.f2732e.setVisibility(0);
        }
    }
}
