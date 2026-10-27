package com.byd.data.common.entiry.gson;

import android.util.Log;
import com.google.gson.h;
import com.google.gson.i;
import com.google.gson.j;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DataTypeAdapter implements i<Number> {
    @Override // com.google.gson.i
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Number deserialize(j jVar, Type type, h hVar) {
        jVar.e();
        Log.i("GsonAdapter", " json =" + jVar + " type =" + type);
        return 0;
    }
}
