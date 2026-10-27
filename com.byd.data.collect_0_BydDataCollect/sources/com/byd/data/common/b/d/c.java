package com.byd.data.common.b.d;

import android.util.Log;
import com.byd.data.common.entiry.cloud.CloudConfig;
import com.byd.data.common.entity.bms.QueryDataBean;
import com.byd.data.common.entity.bms.QueryResult;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import f.h0;
import f.z;
import h.h;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* JADX INFO: compiled from: MyGsonResponseBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
final class c<T> implements h<h0, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Gson f3300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypeAdapter<T> f3301b;

    c(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f3300a = gson;
        this.f3301b = typeAdapter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T c(String str, T t) {
        if (t instanceof CloudConfig) {
            CloudConfig cloudConfig = (CloudConfig) t;
            cloudConfig.setResult(cloudConfig.canCollectConfig, str);
        } else if (t instanceof QueryResult) {
            QueryResult queryResult = (QueryResult) t;
            queryResult.setResult(new QueryDataBean(queryResult), str);
        }
        return t;
    }

    @Override // h.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public T a(h0 h0Var) throws Exception {
        String strT = h0Var.T();
        if (com.byd.data.common.server.b.e().p()) {
            Log.d("MyGsonConverter", " response =" + strT);
        }
        if (strT == null || strT.length() < 20) {
            return null;
        }
        z zVarY = h0Var.y();
        try {
            return c(strT, this.f3301b.read(this.f3300a.o(new InputStreamReader(new ByteArrayInputStream(strT.getBytes()), zVarY != null ? zVarY.b(StandardCharsets.UTF_8) : StandardCharsets.UTF_8))));
        } finally {
            h0Var.close();
        }
    }
}
