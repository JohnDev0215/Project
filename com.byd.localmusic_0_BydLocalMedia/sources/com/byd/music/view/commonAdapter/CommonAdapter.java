package com.byd.music.view.commonAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    public List<T> data;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private int resource;
    public int selectPosition;

    public interface OnItemClickListener<T> {
        void onItemClick(int i, T t);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(int i, T t);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    protected abstract void onItemNotSelect(RecyclerViewHolder recyclerViewHolder, int i, T t);

    protected abstract void onItemSelect(RecyclerViewHolder recyclerViewHolder, int i, T t);

    protected abstract void setupViewHolder(RecyclerViewHolder recyclerViewHolder, int i, T t);

    public CommonAdapter(int i) {
        this(null, i);
    }

    public CommonAdapter(List<T> list, int i) {
        this.data = list;
        this.resource = i;
    }

    public void init(List<T> list) {
        if (list == null) {
            throw new NullPointerException("data can not be null");
        }
        this.data = list;
        notifyDataSetChanged();
    }

    public void clear() {
        createDataIfNotExits();
        this.data.clear();
        notifyDataSetChanged();
    }

    public void addItems(List<T> list) {
        createDataIfNotExits();
        this.data.addAll(list);
        notifyItemInserted(getItemCount());
        notifyItemChanged(getItemCount());
    }

    public void addItem(T t) {
        createDataIfNotExits();
        this.data.add(t);
        notifyDataSetChanged();
    }

    public T getItemAtPosition(int i) {
        return this.data.get(i);
    }

    public void addItem2First(T t) {
        createDataIfNotExits();
        this.data.add(0, t);
        notifyDataSetChanged();
    }

    public void notifyItemAtPosition(T t, int i) {
        createDataIfNotExits();
        if (this.data.size() < i) {
            throw new RuntimeException("data length is " + this.data.size() + ", notify position is " + i);
        }
        this.data.set(i, t);
        notifyItemChanged(i);
    }

    public void remove(int i) {
        List<T> list = this.data;
        if (list == null || i >= list.size()) {
            return;
        }
        this.data.remove(i);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        List<T> list = this.data;
        if (list == null) {
            return;
        }
        list.remove(t);
        notifyDataSetChanged();
    }

    private void createDataIfNotExits() {
        if (this.data == null) {
            this.data = new ArrayList();
        }
    }

    public List<T> getData() {
        return this.data;
    }

    protected int getItemLayout(int i) {
        return this.resource;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayout(i), viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final RecyclerViewHolder recyclerViewHolder, final int i) {
        setupViewHolder(recyclerViewHolder, i, this.data.get(i));
        if (recyclerViewHolder.itemView.getTag() != null) {
            recyclerViewHolder.itemView.removeOnAttachStateChangeListener((View.OnAttachStateChangeListener) recyclerViewHolder.itemView.getTag());
        }
        View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.byd.music.view.commonAdapter.CommonAdapter.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                if (i == CommonAdapter.this.selectPosition) {
                    CommonAdapter commonAdapter = CommonAdapter.this;
                    commonAdapter.onItemSelect(recyclerViewHolder, i, commonAdapter.data.get(i));
                } else {
                    CommonAdapter commonAdapter2 = CommonAdapter.this;
                    commonAdapter2.onItemNotSelect(recyclerViewHolder, i, commonAdapter2.data.get(i));
                }
            }
        };
        recyclerViewHolder.itemView.addOnAttachStateChangeListener(onAttachStateChangeListener);
        recyclerViewHolder.itemView.setTag(onAttachStateChangeListener);
        recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.commonAdapter.CommonAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommonAdapter.this.onItemClickListener != null) {
                    CommonAdapter.this.onItemClickListener.onItemClick(i, CommonAdapter.this.data.get(i));
                }
            }
        });
        recyclerViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.music.view.commonAdapter.CommonAdapter.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (CommonAdapter.this.onItemLongClickListener == null) {
                    return false;
                }
                CommonAdapter.this.onItemLongClickListener.onItemLongClick(i, CommonAdapter.this.data.get(i));
                return true;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setSelectPosition(int i) {
        this.selectPosition = i;
        notifyDataSetChanged();
    }
}
