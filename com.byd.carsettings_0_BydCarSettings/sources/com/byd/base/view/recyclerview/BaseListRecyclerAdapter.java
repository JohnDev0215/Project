package com.byd.base.view.recyclerview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
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
    public b.b.a.j.d.c f2496d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinearLayout f2498f;
    public b.b.a.j.d.b g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2493a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2494b = -2;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<T> f2495c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2497e = true;
    public boolean h = false;

    public static class FooterHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2499a;

        public FooterHolder(View view) {
            super(view);
            this.f2499a = (TextView) view.findViewById(R$id.tv_nomore_des);
        }
    }

    public class a extends GridLayoutManager.SpanSizeLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f2500a;

        public a(GridLayoutManager gridLayoutManager) {
            this.f2500a = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            if (BaseListRecyclerAdapter.this.q(i)) {
                return this.f2500a.getSpanCount();
            }
            return 1;
        }
    }

    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f2502a;

        public b(RecyclerView.ViewHolder viewHolder) {
            this.f2502a = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseListRecyclerAdapter.this.f2496d.a(this.f2502a.itemView, this.f2502a.getLayoutPosition());
        }
    }

    public class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.ViewHolder f2504a;

        public c(RecyclerView.ViewHolder viewHolder) {
            this.f2504a = viewHolder;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            BaseListRecyclerAdapter.this.f2496d.b(this.f2504a.itemView, this.f2504a.getLayoutPosition());
            return false;
        }
    }

    public class d extends RecyclerView.ViewHolder {
        public d(View view) {
            super(view);
        }
    }

    public void a(List<T> list) {
        this.f2495c = list;
        notifyDataSetChanged();
    }

    public void b(View view) {
        d(view, 1);
    }

    public void d(View view, int i) {
        if (this.f2498f == null) {
            LinearLayout linearLayout = new LinearLayout(view.getContext());
            this.f2498f = linearLayout;
            linearLayout.setOrientation(i);
            if (i == 1) {
                this.f2498f.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            } else {
                this.f2498f.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
            }
        }
        this.f2498f.removeAllViews();
        this.f2498f.addView(view);
        notifyItemInserted(0);
    }

    public void e() {
        List<T> list = this.f2495c;
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    public int f() {
        List<T> list = this.f2495c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int g(int i) {
        return 0;
    }

    @Nullable
    public T getItem(int i) {
        int iN;
        if (this.f2495c != null && (iN = i - n()) >= 0 && iN < this.f2495c.size()) {
            return this.f2495c.get(iN);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        int iF = f();
        if (!this.f2497e && p() && iF > 0) {
            iF++;
        }
        return iF + n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (!this.f2497e && p() && i == getItemCount() - 1) {
            return -1;
        }
        if (i != 0 || n() <= 0) {
            return g(i);
        }
        return -2;
    }

    public int i() {
        return R$string.guide_home_room_footer_desc;
    }

    public int j() {
        return Color.rgb(155, 152, 152);
    }

    public int k() {
        return -1;
    }

    public int l() {
        return R$layout.base_item_nomore_footer;
    }

    public int n() {
        LinearLayout linearLayout = this.f2498f;
        return (linearLayout == null || linearLayout.getChildCount() <= 0) ? 0 : 1;
    }

    public b.b.a.j.d.b o() {
        return this.g;
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
            r(viewHolder, i, i - n());
            return;
        }
        FooterHolder footerHolder = (FooterHolder) viewHolder;
        footerHolder.itemView.setBackgroundColor(k());
        footerHolder.f2499a.setTextColor(j());
        footerHolder.f2499a.setText(i());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        return i == -1 ? new FooterHolder(layoutInflaterFrom.inflate(l(), viewGroup, false)) : i == -2 ? new d(this.f2498f) : t(layoutInflaterFrom, viewGroup, i);
    }

    public boolean p() {
        return false;
    }

    public boolean q(int i) {
        return i == 0 && n() > 0;
    }

    public void r(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (this.f2496d == null || i2 == -1) {
            return;
        }
        viewHolder.itemView.setOnClickListener(new b(viewHolder));
        viewHolder.itemView.setOnLongClickListener(new c(viewHolder));
    }

    public void s(RecyclerView.ViewHolder viewHolder, int i, int i2, List<Object> list) {
        r(viewHolder, i, i2);
    }

    public void setItemChildClickListener(b.b.a.j.d.b bVar) {
        this.g = bVar;
    }

    public void setOnItemClickListener(b.b.a.j.d.c cVar) {
        this.f2496d = cVar;
    }

    public RecyclerView.ViewHolder t(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        BydLogHelper.logV("RecyclerView.ViewHolder onCreateViewHolder");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        if (list.isEmpty()) {
            super.onBindViewHolder(viewHolder, i, list);
        } else if (viewHolder instanceof FooterHolder) {
            s(viewHolder, i, -1, list);
        } else {
            s(viewHolder, i, i - n(), list);
        }
    }
}
