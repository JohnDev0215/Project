package com.drakeet.multitype;

import com.byd.mediacenter.api.media.MediaControl;
import kotlin.Metadata;

/* JADX INFO: compiled from: DefaultLinker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/drakeet/multitype/DefaultLinker;", "T", "Lcom/drakeet/multitype/Linker;", "()V", "index", "", MediaControl.PARAM_POSITION, "item", "(ILjava/lang/Object;)I", "multitype"}, k = 1, mv = {1, 4, 2})
public final class DefaultLinker<T> implements Linker<T> {
    @Override // com.drakeet.multitype.Linker
    public int index(int position, T item) {
        return 0;
    }
}
