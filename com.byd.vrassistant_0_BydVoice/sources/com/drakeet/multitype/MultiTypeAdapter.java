package com.drakeet.multitype;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import net.bytebuddy.implementation.MethodDelegation;

/* JADX INFO: compiled from: MultiTypeAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 :2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001:B+\b\u0007\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0015\u001a\u00020\u0007H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u001c\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0002H\u0002J\u001d\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001fJ\u0018\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J&\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00072\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u0018\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0007H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u0010\u0010)\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u0010\u0010*\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J\u0010\u0010+\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016J'\u0010,\u001a\u00020!\"\n\b\u0000\u0010-\u0018\u0001*\u00020\u00052\u0010\u0010.\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030/H\u0086\bJ'\u0010,\u001a\u00020!\"\n\b\u0000\u0010-\u0018\u0001*\u00020\u00052\u0010\u00100\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030\u001bH\u0086\bJ!\u0010,\u001a\u00020!\"\u0004\b\u0000\u0010-2\f\u00101\u001a\b\u0012\u0004\u0012\u0002H-02H\u0000¢\u0006\u0002\b3J\"\u0010,\u001a\b\u0012\u0004\u0012\u0002H-04\"\u0004\b\u0000\u0010-2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-06H\u0007J,\u0010,\u001a\u00020!\"\u0004\b\u0000\u0010-2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-062\u0010\u0010.\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030/J,\u0010,\u001a\u00020!\"\u0004\b\u0000\u0010-2\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-062\u0010\u00100\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030\u001bJ&\u0010,\u001a\b\u0012\u0004\u0012\u0002H-04\"\b\b\u0000\u0010-*\u00020\u00052\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-07H\u0007J5\u0010,\u001a\u00020!\"\n\b\u0000\u0010-\u0018\u0001*\u00020\u00052\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-072\u0010\u0010.\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030/H\u0086\bJ5\u0010,\u001a\u00020!\"\n\b\u0000\u0010-\u0018\u0001*\u00020\u00052\f\u00105\u001a\b\u0012\u0004\u0012\u0002H-072\u0010\u00100\u001a\f\u0012\u0004\u0012\u0002H-\u0012\u0002\b\u00030\u001bH\u0086\bJ\u000e\u00108\u001a\u00020!2\u0006\u0010\b\u001a\u00020\tJ\u0014\u00109\u001a\u00020!2\n\u00105\u001a\u0006\u0012\u0002\b\u000306H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006;"}, d2 = {"Lcom/drakeet/multitype/MultiTypeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "items", "", "", "initialCapacity", "", "types", "Lcom/drakeet/multitype/Types;", "(Ljava/util/List;ILcom/drakeet/multitype/Types;)V", "getInitialCapacity", "()I", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "getTypes", "()Lcom/drakeet/multitype/Types;", "setTypes", "(Lcom/drakeet/multitype/Types;)V", "getItemCount", "getItemId", "", MediaControl.PARAM_POSITION, "getItemViewType", "getOutDelegateByViewHolder", "Lcom/drakeet/multitype/ItemViewDelegate;", "holder", "indexInTypesOf", "item", "indexInTypesOf$multitype", "onBindViewHolder", "", "payloads", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "indexViewType", "onFailedToRecycleView", "", "onViewAttachedToWindow", "onViewDetachedFromWindow", "onViewRecycled", "register", "T", "binder", "Lcom/drakeet/multitype/ItemViewBinder;", MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX, "type", "Lcom/drakeet/multitype/Type;", "register$multitype", "Lcom/drakeet/multitype/OneToManyFlow;", "clazz", "Ljava/lang/Class;", "Lkotlin/reflect/KClass;", "registerAll", "unregisterAllTypesIfNeeded", "Companion", "multitype"}, k = 1, mv = {1, 4, 2})
public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultiTypeAdapter";
    private final int initialCapacity;
    private List<? extends Object> items;
    private Types types;

    public MultiTypeAdapter() {
        this(null, 0, null, 7, null);
    }

    public MultiTypeAdapter(List<? extends Object> list) {
        this(list, 0, null, 6, null);
    }

    public MultiTypeAdapter(List<? extends Object> list, int i) {
        this(list, i, null, 4, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MultiTypeAdapter(List list, int i, MutableTypes mutableTypes, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        list = (i2 & 1) != 0 ? CollectionsKt.emptyList() : list;
        i = (i2 & 2) != 0 ? 0 : i;
        this(list, i, (i2 & 4) != 0 ? new MutableTypes(i, null, 2, null) : mutableTypes);
    }

    public List<Object> getItems() {
        return this.items;
    }

    public void setItems(List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.items = list;
    }

    public int getInitialCapacity() {
        return this.initialCapacity;
    }

    public Types getTypes() {
        return this.types;
    }

    public void setTypes(Types types) {
        Intrinsics.checkNotNullParameter(types, "<set-?>");
        this.types = types;
    }

    public MultiTypeAdapter(List<? extends Object> items, int i, Types types) {
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(types, "types");
        this.items = items;
        this.initialCapacity = i;
        this.types = types;
    }

    public final <T> void register(Class<T> clazz, ItemViewDelegate<T, ?> delegate) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        unregisterAllTypesIfNeeded(clazz);
        register$multitype(new Type<>(clazz, delegate, new DefaultLinker()));
    }

    public final /* synthetic */ <T> void register(ItemViewDelegate<T, ?> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.reifiedOperationMarker(4, "T");
        register(Object.class, delegate);
    }

    public final /* synthetic */ <T> void register(KClass<T> clazz, ItemViewDelegate<T, ?> delegate) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.reifiedOperationMarker(4, "T");
        register(Object.class, delegate);
    }

    public final <T> void register(Class<T> clazz, ItemViewBinder<T, ?> binder) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(binder, "binder");
        register((Class) clazz, (ItemViewDelegate) binder);
    }

    public final /* synthetic */ <T> void register(ItemViewBinder<T, ?> binder) {
        Intrinsics.checkNotNullParameter(binder, "binder");
        Intrinsics.reifiedOperationMarker(4, "T");
        register((Class) Object.class, (ItemViewDelegate) binder);
    }

    public final /* synthetic */ <T> void register(KClass<T> clazz, ItemViewBinder<T, ?> binder) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(binder, "binder");
        Intrinsics.reifiedOperationMarker(4, "T");
        register((Class) Object.class, (ItemViewDelegate) binder);
    }

    public final <T> void register$multitype(Type<T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        getTypes().register(type);
        type.getDelegate().set_adapter$multitype(this);
    }

    public final <T> OneToManyFlow<T> register(Class<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        unregisterAllTypesIfNeeded(clazz);
        return new OneToManyBuilder(this, clazz);
    }

    public final <T> OneToManyFlow<T> register(KClass<T> clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return register(JvmClassMappingKt.getJavaClass((KClass) clazz));
    }

    public final void registerAll(Types types) {
        Intrinsics.checkNotNullParameter(types, "types");
        int size = types.getSize();
        for (int i = 0; i < size; i++) {
            Type type = types.getType(i);
            unregisterAllTypesIfNeeded(type.getClazz());
            register$multitype(type);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return indexInTypesOf$multitype(position, getItems().get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemViewDelegate delegate = getTypes().getType(indexViewType).getDelegate();
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return delegate.onCreateViewHolder(context, parent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        onBindViewHolder(holder, position, CollectionsKt.emptyList());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<? extends Object> payloads) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        getOutDelegateByViewHolder(holder).onBindViewHolder(holder, getItems().get(position), payloads);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getItems().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return getTypes().getType(getItemViewType(position)).getDelegate().getItemId(getItems().get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        getOutDelegateByViewHolder(holder).onViewRecycled(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        return getOutDelegateByViewHolder(holder).onFailedToRecycleView(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        getOutDelegateByViewHolder(holder).onViewAttachedToWindow(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        getOutDelegateByViewHolder(holder).onViewDetachedFromWindow(holder);
    }

    private final ItemViewDelegate<Object, RecyclerView.ViewHolder> getOutDelegateByViewHolder(RecyclerView.ViewHolder holder) {
        ItemViewDelegate<Object, RecyclerView.ViewHolder> delegate = getTypes().getType(holder.getItemViewType()).getDelegate();
        if (delegate != null) {
            return delegate;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.drakeet.multitype.ItemViewDelegate<kotlin.Any, androidx.recyclerview.widget.RecyclerView.ViewHolder>");
    }

    public final int indexInTypesOf$multitype(int position, Object item) throws DelegateNotFoundException {
        Intrinsics.checkNotNullParameter(item, "item");
        int iFirstIndexOf = getTypes().firstIndexOf(item.getClass());
        if (iFirstIndexOf != -1) {
            return iFirstIndexOf + getTypes().getType(iFirstIndexOf).getLinker().index(position, item);
        }
        throw new DelegateNotFoundException(item.getClass());
    }

    private final void unregisterAllTypesIfNeeded(Class<?> clazz) {
        if (getTypes().unregister(clazz)) {
            Log.w(TAG, "The type " + clazz.getSimpleName() + " you originally registered is now overwritten.");
        }
    }
}
