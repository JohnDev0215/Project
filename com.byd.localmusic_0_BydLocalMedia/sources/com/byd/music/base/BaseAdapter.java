package com.byd.music.base;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private final ItemClickListener itemClickListener;
    int itemCount;
    private List<T> list;
    private RecyclerView recyclerView;
    ViewHolder viewHolder;

    public interface ItemClickListener<T> {
        void onClick(T t, int i);
    }

    public abstract void createHolder(ViewHolder viewHolder, T t);

    public abstract int getLayoutId();

    public BaseAdapter(List<T> list, ItemClickListener itemClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    public BaseAdapter(List<T> list, ItemClickListener itemClickListener, RecyclerView recyclerView) {
        this.list = list;
        this.itemClickListener = itemClickListener;
        this.recyclerView = recyclerView;
    }

    public ViewHolder getViewHolder() {
        return this.viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false));
        this.viewHolder = viewHolder;
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        createHolder(viewHolder, this.list.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.base.BaseAdapter.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (BaseAdapter.this.itemClickListener != null) {
                    BaseAdapter.this.itemClickListener.onClick(BaseAdapter.this.list.get(i), i);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.itemCount != 0) {
            int size = this.list.size();
            int i = this.itemCount;
            return size > i ? i : this.list.size();
        }
        List<T> list = this.list;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public int getMyItemCount() {
        return this.itemCount;
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private SparseArray<View> views;

        public ViewHolder(View view) {
            super(view);
            this.views = new SparseArray<>();
            this.rootView = view;
        }

        /* JADX WARN: Incorrect return type in method signature: <T:Landroid/view/View;>(I)TT; */
        public View get(int i) {
            View view = this.views.get(i);
            if (view != null) {
                return view;
            }
            View viewFindViewById = this.rootView.findViewById(i);
            this.views.put(i, viewFindViewById);
            return viewFindViewById;
        }

        public void setText(int i, String str) {
            ((TextView) get(i)).setText(str);
        }

        public void setText(TextView textView, String str) {
            textView.setText(str);
        }

        public View getView(int i) {
            return get(i);
        }

        public void setImage(int i, String str) {
            Glide.with(Utils.getApp()).load(str).into((ImageView) get(i));
        }

        public void setRecycleView(int i, BaseAdapter baseAdapter, LinearLayoutManager linearLayoutManager) {
            RecyclerView recyclerView = (RecyclerView) get(i);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(baseAdapter);
        }
    }
}
