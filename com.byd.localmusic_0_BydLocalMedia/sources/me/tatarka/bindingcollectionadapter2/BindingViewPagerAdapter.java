package me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.PagerAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BindingViewPagerAdapter<T> extends PagerAdapter implements BindingCollectionAdapter<T> {
    private WeakReferenceOnListChangedCallback<T> callback;
    private LayoutInflater inflater;
    private ItemBinding<? super T> itemBinding;
    private List<T> items;
    private LifecycleOwner lifecycleOwner;
    private PageTitles<T> pageTitles;
    private List<View> views = new ArrayList();

    public interface PageTitles<T> {
        CharSequence getPageTitle(int i, T t);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public BindingViewPagerAdapter() {
    }

    public BindingViewPagerAdapter(ItemBinding<? super T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItemBinding(ItemBinding<? super T> itemBinding) {
        this.itemBinding = itemBinding;
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        Iterator<View> it = this.views.iterator();
        while (it.hasNext()) {
            ViewDataBinding binding = DataBindingUtil.getBinding(it.next());
            if (binding != null) {
                binding.setLifecycleOwner(lifecycleOwner);
            }
        }
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public ItemBinding<? super T> getItemBinding() {
        ItemBinding<? super T> itemBinding = this.itemBinding;
        if (itemBinding != null) {
            return itemBinding;
        }
        throw new NullPointerException("itemBinding == null");
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void setItems(List<T> list) {
        List<T> list2 = this.items;
        if (list2 == list) {
            return;
        }
        if (list2 instanceof ObservableList) {
            ((ObservableList) list2).removeOnListChangedCallback(this.callback);
            this.callback = null;
        }
        if (list instanceof ObservableList) {
            ObservableList observableList = (ObservableList) list;
            WeakReferenceOnListChangedCallback<T> weakReferenceOnListChangedCallback = new WeakReferenceOnListChangedCallback<>(this, observableList);
            this.callback = weakReferenceOnListChangedCallback;
            observableList.addOnListChangedCallback(weakReferenceOnListChangedCallback);
        }
        this.items = list;
        notifyDataSetChanged();
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public T getAdapterItem(int i) {
        return this.items.get(i);
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public ViewDataBinding onCreateBinding(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(layoutInflater, i, viewGroup, false);
    }

    @Override // me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter
    public void onBindBinding(ViewDataBinding viewDataBinding, int i, int i2, int i3, T t) {
        if (this.itemBinding.bind(viewDataBinding, t)) {
            viewDataBinding.executePendingBindings();
            LifecycleOwner lifecycleOwner = this.lifecycleOwner;
            if (lifecycleOwner != null) {
                viewDataBinding.setLifecycleOwner(lifecycleOwner);
            }
        }
    }

    public void setPageTitles(PageTitles<T> pageTitles) {
        this.pageTitles = pageTitles;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
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
    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        PageTitles<T> pageTitles = this.pageTitles;
        if (pageTitles == null) {
            return null;
        }
        return pageTitles.getPageTitle(i, this.items.get(i));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        tryGetLifecycleOwner(viewGroup);
        T t = this.items.get(i);
        this.itemBinding.onItemBind(i, t);
        ViewDataBinding viewDataBindingOnCreateBinding = onCreateBinding(this.inflater, this.itemBinding.layoutRes(), viewGroup);
        View root = viewDataBindingOnCreateBinding.getRoot();
        onBindBinding(viewDataBindingOnCreateBinding, this.itemBinding.variableId(), this.itemBinding.layoutRes(), i, t);
        viewGroup.addView(root);
        root.setTag(t);
        this.views.add(root);
        return root;
    }

    private void tryGetLifecycleOwner(View view) {
        LifecycleOwner lifecycleOwner = this.lifecycleOwner;
        if (lifecycleOwner == null || lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            this.lifecycleOwner = Utils.findLifecycleOwner(view);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        this.views.remove(view);
        viewGroup.removeView(view);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        Object tag = ((View) obj).getTag();
        if (this.items == null) {
            return -2;
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (tag == this.items.get(i)) {
                return i;
            }
        }
        return -2;
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingViewPagerAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingViewPagerAdapter<T> bindingViewPagerAdapter, ObservableList<T> observableList) {
            this.adapterRef = AdapterReferenceCollector.createRef(bindingViewPagerAdapter, observableList, this);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            BindingViewPagerAdapter<T> bindingViewPagerAdapter = this.adapterRef.get();
            if (bindingViewPagerAdapter == null) {
                return;
            }
            Utils.ensureChangeOnMainThread();
            bindingViewPagerAdapter.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }
    }
}
