package com.byd.data.common.base.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.w.a;
import com.google.gson.w.b;
import com.google.gson.w.c;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class StringNullAdapter extends TypeAdapter<String> {
    @Override // com.google.gson.TypeAdapter
    public String read(a aVar) throws IOException {
        if (aVar.j0() == b.NULL) {
            aVar.f0();
            return "";
        }
        String strH0 = aVar.h0();
        return strH0.equals("null") ? "" : strH0;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, String str) throws IOException {
        if (str == null) {
            cVar.Y();
        } else {
            cVar.l0(str);
        }
    }
}
