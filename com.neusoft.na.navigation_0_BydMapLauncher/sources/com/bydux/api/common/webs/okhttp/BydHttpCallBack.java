package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.google.gson.internal.C$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydHttpCallBack<T> {
    private Type mType = getSuperclassTypeParameter(getClass());

    public abstract void onFailure(Call call, IOException e);

    public abstract void onNetworkDisconnect();

    public abstract void onResponse(Call call, ResponseInfo<T> responseInfo);

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type genericSuperclass = subclass.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public Type getType() {
        return this.mType;
    }
}
