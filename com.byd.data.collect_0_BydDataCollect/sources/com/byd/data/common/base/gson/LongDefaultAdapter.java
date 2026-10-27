package com.byd.data.common.base.gson;

import com.google.gson.h;
import com.google.gson.i;
import com.google.gson.j;
import com.google.gson.o;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.r;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public class LongDefaultAdapter implements q<Long>, i<Long> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.i
    public Long deserialize(j jVar, Type type, h hVar) {
        try {
            if (jVar.h().equals("") || jVar.h().equals("null")) {
                return 0L;
            }
        } catch (Exception unused) {
        }
        try {
            return Long.valueOf(jVar.g());
        } catch (NumberFormatException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.q
    public j serialize(Long l, Type type, p pVar) {
        return new o(l);
    }
}
