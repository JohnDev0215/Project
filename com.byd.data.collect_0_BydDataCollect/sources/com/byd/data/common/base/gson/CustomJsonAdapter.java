package com.byd.data.common.base.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.w.a;
import com.google.gson.w.c;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class CustomJsonAdapter extends TypeAdapter<Short> {
    @Override // com.google.gson.TypeAdapter
    public Short read(a aVar) {
        String strH0 = aVar.h0();
        return (strH0 == null || !strH0.startsWith("0x")) ? Short.valueOf(Short.parseShort(strH0)) : Short.valueOf(Short.parseShort(strH0.substring(2), 16));
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, Short sh) throws IOException {
        cVar.k0(sh);
    }
}
