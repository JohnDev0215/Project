package com.bydux.api.common.webs.okhttp;

import com.bydux.api.common.webs.okhttp.bean.ResponseInfo;
import com.google.gson.internal.$Gson;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;

/* JADX INFO: loaded from: classes.dex */
public abstract class BydHttpCallBack<T> {
    private Type mType = getSuperclassTypeParameter(getClass());

    static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return $Gson.Types.canonicalize(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public Type getType() {
        return this.mType;
    }

    public abstract void onFailure(Call call, IOException iOException);

    public abstract void onNetworkDisconnect();

    public abstract void onResponse(Call call, ResponseInfo<T> responseInfo);
}
