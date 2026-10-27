package com.drakeet.multitype;

import com.byd.mediacenter.api.media.MediaControl;
import kotlin.Metadata;

/* JADX INFO: compiled from: JavaClassLinker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J/\u0010\u0003\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/drakeet/multitype/JavaClassLinker;", "T", "", "index", "Ljava/lang/Class;", "Lcom/drakeet/multitype/ItemViewDelegate;", MediaControl.PARAM_POSITION, "", "item", "(ILjava/lang/Object;)Ljava/lang/Class;", "multitype"}, k = 1, mv = {1, 4, 2})
public interface JavaClassLinker<T> {
    Class<? extends ItemViewDelegate<T, ?>> index(int position, T item);
}
