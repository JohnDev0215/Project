package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class ResponseHandlerMultimap {
    private final Map<Request<?>, List<ResponseHandler<?>>> mMap = new HashMap();

    public <T> void put(@Nonnull Request<T> request, @Nonnull ResponseHandler<T> responseHandler) {
        List<ResponseHandler<?>> linkedList;
        Preconditions.checkNotNull(request, "key");
        Preconditions.checkNotNull(responseHandler, "value");
        if (!this.mMap.containsKey(request)) {
            linkedList = new LinkedList<>();
            this.mMap.put(request, linkedList);
        } else {
            linkedList = this.mMap.get(request);
        }
        linkedList.add(responseHandler);
    }

    public <T> Collection<ResponseHandler<T>> removeAll(@Nonnull Request<T> request) {
        Preconditions.checkNotNull(request, "key");
        LinkedList linkedList = new LinkedList();
        if (this.mMap.containsKey(request)) {
            Iterator<ResponseHandler<?>> it = this.mMap.remove(request).iterator();
            while (it.hasNext()) {
                linkedList.add(it.next());
            }
        }
        return linkedList;
    }

    public boolean containsKey(@Nonnull Request<?> request) {
        Preconditions.checkNotNull(request, "key");
        if (this.mMap.containsKey(request)) {
            return !this.mMap.get(request).isEmpty();
        }
        return false;
    }
}
