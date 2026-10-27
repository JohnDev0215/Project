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
public class DoubleDefaultAdapter implements q<Double>, i<Double> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.i
    public Double deserialize(j jVar, Type type, h hVar) {
        try {
            if (jVar.h().equals("") || jVar.h().equals("null")) {
                return Double.valueOf(0.0d);
            }
        } catch (Exception unused) {
        }
        try {
            return Double.valueOf(jVar.b());
        } catch (NumberFormatException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.q
    public j serialize(Double d2, Type type, p pVar) {
        return new o(d2);
    }
}
