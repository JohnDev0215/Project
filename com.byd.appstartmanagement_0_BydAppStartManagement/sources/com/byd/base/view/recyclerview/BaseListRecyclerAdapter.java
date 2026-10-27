package com.byd.base.view.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import com.byd.base.R$string;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseListRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.a.b.e.d.b f575d;
    public LinearLayout f;
    public b.a.b.e.d.a g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f572a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f573b = -2;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<T> f574c = new ArrayList();
    public boolean e = true;
    public boolean h = false;

    public static class FooterHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f576a;

        public FooterHolder(View view) {
            super(view);
            this.f576a = (TextView) view.findViewById(R$id.tv_nomore_des);
        }
    }

    public class a extends GridLayoutManager.SpanSizeLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f577a;

        public a(GridLayoutManager gridLayoutManager) {
            this.f577a = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (BaseListRecyclerAdapter.this.l(i)) {
                return this.f577a.getSpanCount();
            }
            return 1;
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f579b;

        public b(RecyclerView.ViewHolder viewHolder) {
            this.f579b = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseListRecyclerAdapter.this.f575d.a(this.f579b.itemView, this.f579b.getLayoutPosition());
        }
    }

    public class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f581b;

        public c(RecyclerView.ViewHolder viewHolder) {
            this.f581b = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            BaseListRecyclerAdapter.this.f575d.b(this.f581b.itemView, this.f581b.getLayoutPosition());
            return false;
        }
    }

    public class d extends RecyclerView.ViewHolder {
        public d(View view) {
            super(view);
        }
    }

    public int d() {
        List<T> list = this.f574c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int e(int i) {
        return 0;
    }

    public int f() {
        return R$string.guide_home_room_footer_desc;
    }

    public int g() {
        return Color.rgb(155, 152, 152);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        int iD = d();
        if (!this.e && k() && iD > 0) {
            iD++;
        }
        return iD + j();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (!this.e && k() && i == getItemCount() - 1) {
            return -1;
        }
        if (i != 0 || j() <= 0) {
            return e(i);
        }
        return -2;
    }

    public int h() {
        return -1;
    }

    public int i() {
        return R$layout.base_item_nomore_footer;
    }

    public int j() {
        LinearLayout linearLayout = this.f;
        return (linearLayout == null || linearLayout.getChildCount() <= 0) ? 0 : 1;
    }

    public boolean k() {
        return false;
    }

    public boolean l(int i) {
        return i == 0 && j() > 0;
    }

    public void m(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (this.f575d == null || i2 == -1) {
            return;
        }
        viewHolder.itemView.setOnClickListener(new b(viewHolder));
        viewHolder.itemView.setOnLongClickListener(new c(viewHolder));
    }

    public void n(RecyclerView.ViewHolder viewHolder, int i, int i2, List<Object> list) {
        m(viewHolder, i, i2);
    }

    public RecyclerView.ViewHolder o(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        BydLogHelper.logV("RecyclerView.ViewHolder onCreateViewHolder");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new a(gridLayoutManager));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!(viewHolder instanceof FooterHolder)) {
            m(viewHolder, i, i - j());
            return;
        }
        FooterHolder footerHolder = (FooterHolder) viewHolder;
        footerHolder.itemView.setBackgroundColor(h());
        footerHolder.f576a.setTextColor(g());
        footerHolder.f576a.setText(f());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        return i == -1 ? new FooterHolder(layoutInflaterFrom.inflate(i(), viewGroup, false)) : i == -2 ? new d(this.f) : o(layoutInflaterFrom, viewGroup, i);
    }

    public void setItemChildClickListener(b.a.b.e.d.a aVar) {
        this.g = aVar;
    }

    public void setOnItemClickListener(b.a.b.e.d.b bVar) {
        this.f575d = bVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
        } else if (viewHolder instanceof FooterHolder) {
            n(viewHolder, i, -1, list);
        } else {
            n(viewHolder, i, i - j(), list);
        }
    }
}
