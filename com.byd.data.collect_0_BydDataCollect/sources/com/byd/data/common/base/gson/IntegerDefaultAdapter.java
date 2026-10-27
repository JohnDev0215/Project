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
public class IntegerDefaultAdapter implements q<Integer>, i<Integer> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.i
    public Integer deserialize(j jVar, Type type, h hVar) {
        try {
            if (jVar.h().equals("") || jVar.h().equals("null")) {
                return 0;
            }
        } catch (Exception unused) {
        }
        try {
            return Integer.valueOf(jVar.c());
        } catch (NumberFormatException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.q
    public j serialize(Integer num, Type type, p pVar) {
        return new o(num);
    }
}
