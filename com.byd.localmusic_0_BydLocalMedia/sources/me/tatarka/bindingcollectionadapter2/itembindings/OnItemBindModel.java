package me.tatarka.bindingcollectionadapter2.itembindings;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.itembindings.ItemBindingModel;

/* JADX INFO: loaded from: classes5.dex */
public class OnItemBindModel<T extends ItemBindingModel> implements OnItemBind<T> {
    @Override // me.tatarka.bindingcollectionadapter2.OnItemBind
    public void onItemBind(ItemBinding itemBinding, int i, T t) {
        t.onItemBind(itemBinding);
    }
}
