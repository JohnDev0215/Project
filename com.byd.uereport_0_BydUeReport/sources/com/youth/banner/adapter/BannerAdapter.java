package com.youth.banner.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.c0;
import com.youth.banner.R;
import com.youth.banner.holder.IViewHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BannerAdapter<T, VH extends RecyclerView.c0> extends RecyclerView.g<VH> implements IViewHolder<T, VH> {
    public List<T> mDatas = new ArrayList();
    private int mIncreaseCount = 2;
    private OnBannerListener<T> mOnBannerListener;
    private VH mViewHolder;

    public BannerAdapter(List<T> list) {
        setDatas(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a(Object obj, int i8, View view) {
        this.mOnBannerListener.OnBannerClick(obj, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
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
    /* JADX INFO: renamed from: lambda$onCreateViewHolder$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(RecyclerView.c0 c0Var, View view) {
        if (this.mOnBannerListener != null) {
            Object tag = c0Var.itemView.getTag(R.id.banner_data_key);
            this.mOnBannerListener.OnBannerClick((T) tag, ((Integer) c0Var.itemView.getTag(R.id.banner_pos_key)).intValue());
        }
    }

    public T getData(int i8) {
        return this.mDatas.get(i8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + this.mIncreaseCount : getRealCount();
    }

    public int getRealCount() {
        List<T> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getRealData(int i8) {
        return this.mDatas.get(getRealPosition(i8));
    }

    public int getRealPosition(int i8) {
        return BannerUtils.getRealPosition(this.mIncreaseCount == 2, i8, getRealCount());
    }

    public VH getViewHolder() {
        return this.mViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void onBindViewHolder(VH vh, int i8) {
        this.mViewHolder = vh;
        final int realPosition = getRealPosition(i8);
        final T t7 = this.mDatas.get(realPosition);
        vh.itemView.setTag(R.id.banner_data_key, t7);
        vh.itemView.setTag(R.id.banner_pos_key, Integer.valueOf(realPosition));
        onBindView(vh, this.mDatas.get(realPosition), realPosition, getRealCount());
        if (this.mOnBannerListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() { // from class: b7.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f2128d.a(t7, realPosition, view);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public VH onCreateViewHolder(ViewGroup viewGroup, int i8) {
        final VH vhOnCreateHolder = onCreateHolder(viewGroup, i8);
        vhOnCreateHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: b7.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2126d.b(vhOnCreateHolder, view);
            }
        });
        return vhOnCreateHolder;
    }

    public void setDatas(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void setIncreaseCount(int i8) {
        this.mIncreaseCount = i8;
    }

    public void setOnBannerListener(OnBannerListener<T> onBannerListener) {
        this.mOnBannerListener = onBannerListener;
    }
}
