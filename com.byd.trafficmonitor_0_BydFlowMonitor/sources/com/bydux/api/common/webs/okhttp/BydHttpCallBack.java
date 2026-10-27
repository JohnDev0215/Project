package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.google.gson.internal.C$Gson$Types;
import f.e;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public abstract class BydHttpCallBack<T> {
    private Type mType = getSuperclassTypeParameter(getClass());

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public Type getType() {
        return this.mType;
    }

    public abstract void onFailure(e eVar, IOException iOException);

    public abstract void onNetworkDisconnect();

    public abstract void onResponse(e eVar, ResponseInfo<T> responseInfo);
}
