package c4;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import d4.d;
import d4.e;
import d4.f;
import java.util.List;

/* JADX INFO: compiled from: BydAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a<T> extends RecyclerView.g implements View.OnClickListener {
    public List<T> data;
    public b mBtnCheckedCallback;
    public d4.c mCallback;
    public d4.a mDragItemCallback;
    public d4.b mDragSubitemCallback;
    public Resources mRes;
    public e mSubCallback;
    public c onItemBtnCheckedCallbackV2;
    public d mItemClickListener = null;
    public f mSubitemClickListener = null;
    private a adapter = this;

    /* JADX INFO: renamed from: c4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydAdapter.java */
    public class C0013a extends RecyclerView.c0 {
        public int id;

        public C0013a(View view) {
            super(view);
            view.setTag(this);
        }

        public int getId() {
            return this.id;
        }

        public void setId(int i8) {
            this.id = i8;
        }
    }

    /* JADX INFO: compiled from: BydAdapter.java */
    public interface b {
    }

    /* JADX INFO: compiled from: BydAdapter.java */
    public interface c {
    }

    public a(List<T> list) {
        this.data = list;
    }

    public void addItemByPosition(T t7, int i8) {
        this.data.add(i8, t7);
        notifyItemInserted(i8);
    }

    public abstract void drag(int i8, int i9);

    public void dragCompleted(RecyclerView.c0 c0Var) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.b(c0Var);
        }
    }

    public void dragStart(RecyclerView.c0 c0Var) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.c(c0Var);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<T> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i8) {
        return i8;
    }

    public int getLayoutIdByViewTypes(int i8) {
        return loadLayoutId();
    }

    public List<T> getModel() {
        return this.data;
    }

    public String getStringWithoutNull(String str) {
        return str != null ? str : "";
    }

    public abstract a<T>.C0013a initViewHolder(View view);

    public a<T>.C0013a initViewHolderByViewType(int i8, View view) {
        return initViewHolder(view);
    }

    public abstract int loadLayoutId();

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        d dVar = this.mItemClickListener;
        if (dVar != null) {
            dVar.onItemClicked(((C0013a) view.getTag()).getId());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.c0 onCreateViewHolder(ViewGroup viewGroup, int i8) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutIdByViewTypes(i8), viewGroup, false);
        viewInflate.setOnClickListener(this);
        this.mRes = viewInflate.getResources();
        a<T>.C0013a c0013aInitViewHolderByViewType = initViewHolderByViewType(i8, viewInflate);
        viewInflate.setTag(c0013aInitViewHolderByViewType);
        return c0013aInitViewHolderByViewType;
    }

    public void removeItem(int i8) {
        d4.a aVar = this.mDragItemCallback;
        if (aVar != null) {
            aVar.d(i8);
        }
    }

    public abstract void removeItemByPosition(int i8);

    public void setOnDragItemCallback(d4.a aVar) {
        this.mDragItemCallback = aVar;
    }

    public void setOnDragSubitemCallback(d4.b bVar) {
        this.mDragSubitemCallback = bVar;
    }

    public void setOnItemBtnCheckedCallback(b bVar) {
        this.mBtnCheckedCallback = bVar;
    }

    public void setOnItemBtnCheckedCallbackV2(c cVar) {
        this.onItemBtnCheckedCallbackV2 = cVar;
    }

    public void setOnItemBtnClickedCallback(d4.c cVar) {
        this.mCallback = cVar;
    }

    public void setOnItemClickedListener(d dVar) {
        this.mItemClickListener = dVar;
    }

    public void setOnSubitemBtnClickedCallback(e eVar) {
        this.mSubCallback = eVar;
    }

    public void setOnSubitemClickedListener(f fVar) {
        this.mSubitemClickListener = fVar;
    }
}
