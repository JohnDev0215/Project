package com.drakeet.multitype;

import kotlin.Metadata;

/* JADX INFO: compiled from: Types.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH&J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00020\u0003H&J\u001c\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\nH&J\u0014\u0010\u0010\u001a\u00020\u00112\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/drakeet/multitype/Types;", "", "size", "", "getSize", "()I", "firstIndexOf", "clazz", "Ljava/lang/Class;", "getType", "Lcom/drakeet/multitype/Type;", "T", "index", "register", "", "type", "unregister", "", "multitype"}, k = 1, mv = {1, 4, 2})
public interface Types {
    int firstIndexOf(Class<?> clazz);

    int getSize();

    <T> Type<T> getType(int index);

    <T> void register(Type<T> type);

    boolean unregister(Class<?> clazz);
}
