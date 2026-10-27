package com.byd.mvp.ui.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.c.a.c;
import b.b.m.f.c.a.d;
import b.b.m.f.c.a.e;
import b.b.m.f.c.a.f;
import b.b.m.f.c.a.g;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydAdapter<T> extends RecyclerView.Adapter implements View.OnClickListener, b.b.m.f.c.a.a {
    public List<T> data;
    public a mBtnCheckedCallback;
    public d mCallback;
    public b.b.m.f.c.a.b mDragItemCallback;
    public c mDragSubitemCallback;
    public Resources mRes;
    public f mSubCallback;
    public b onItemBtnCheckedCallbackV2;
    public e mItemClickListener = null;
    public g mSubitemClickListener = null;
    private BydAdapter adapter = this;

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public int id;

        public BaseViewHolder(View view) {
            super(view);
            view.setTag(this);
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }
    }

    public interface a {
        void a(int i, boolean z, BtnType btnType);
    }

    public interface b {
        void onItemBtnChecked(Object obj, boolean z, BtnType btnType);
    }

    public BydAdapter(List<T> list) {
        this.data = list;
    }

    public void addItemByPosition(T t, int i) {
        this.data.add(i, t);
        notifyItemInserted(i);
    }

    public void drag(int i, int i2) {
        T t = this.data.get(i);
        this.data.remove(i);
        this.data.add(i2, t);
        notifyItemMoved(i, i2);
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.a(i, i2);
        }
    }

    @Override // b.b.m.f.c.a.a
    public void dragCompleted(RecyclerView.ViewHolder viewHolder) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.b(viewHolder);
        }
    }

    @Override // b.b.m.f.c.a.a
    public void dragStart(RecyclerView.ViewHolder viewHolder) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.c(viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public int getLayoutIdByViewTypes(int i) {
        return loadLayoutId();
    }

    public List<T> getModel() {
        return this.data;
    }

    public String getStringWithoutNull(String str) {
        return str != null ? str : "";
    }

    public abstract BydAdapter<T>.BaseViewHolder initViewHolder(View view);

    public BydAdapter<T>.BaseViewHolder initViewHolderByViewType(int i, View view) {
        return initViewHolder(view);
    }

    public abstract int loadLayoutId();

    public void onClick(View view) {
        e eVar = this.mItemClickListener;
        if (eVar != null) {
            eVar.onItemClicked(((BaseViewHolder) view.getTag()).getId());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutIdByViewTypes(i), viewGroup, false);
        viewInflate.setOnClickListener(this);
        this.mRes = viewInflate.getResources();
        BydAdapter<T>.BaseViewHolder baseViewHolderInitViewHolderByViewType = initViewHolderByViewType(i, viewInflate);
        viewInflate.setTag(baseViewHolderInitViewHolderByViewType);
        return baseViewHolderInitViewHolderByViewType;
    }

    @Override // b.b.m.f.c.a.a
    public void removeItem(int i) {
        b.b.m.f.c.a.b bVar = this.mDragItemCallback;
        if (bVar != null) {
            bVar.d(i);
        }
    }

    public void removeItemByPosition(int i) {
        this.data.remove(i);
        notifyItemRemoved(i);
    }

    public void setOnDragItemCallback(b.b.m.f.c.a.b bVar) {
        this.mDragItemCallback = bVar;
    }

    public void setOnDragSubitemCallback(c cVar) {
        this.mDragSubitemCallback = cVar;
    }

    public void setOnItemBtnCheckedCallback(a aVar) {
        this.mBtnCheckedCallback = aVar;
    }

    public void setOnItemBtnCheckedCallbackV2(b bVar) {
        this.onItemBtnCheckedCallbackV2 = bVar;
    }

    public void setOnItemBtnClickedCallback(d dVar) {
        this.mCallback = dVar;
    }

    public void setOnItemClickedListener(e eVar) {
        this.mItemClickListener = eVar;
    }

    public void setOnSubitemBtnClickedCallback(f fVar) {
        this.mSubCallback = fVar;
    }

    public void setOnSubitemClickedListener(g gVar) {
        this.mSubitemClickListener = gVar;
    }
}
