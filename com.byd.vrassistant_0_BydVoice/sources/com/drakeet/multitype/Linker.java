package com.drakeet.multitype;

import com.byd.mediacenter.api.media.MediaControl;
import kotlin.Metadata;

/* JADX INFO: compiled from: Linker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00028\u0000H'¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/drakeet/multitype/Linker;", "T", "", "index", "", MediaControl.PARAM_POSITION, "item", "(ILjava/lang/Object;)I", "multitype"}, k = 1, mv = {1, 4, 2})
public interface Linker<T> {
    int index(int position, T item);
}
