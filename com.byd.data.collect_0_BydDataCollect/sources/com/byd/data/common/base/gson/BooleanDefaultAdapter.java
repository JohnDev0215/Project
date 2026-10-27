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
public class BooleanDefaultAdapter implements q<Boolean>, i<Boolean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.i
    public Boolean deserialize(j jVar, Type type, h hVar) {
        try {
            try {
                boolean z = true;
                if (1 != jVar.c()) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } catch (Exception unused) {
                return Boolean.valueOf(jVar.a());
            }
        } catch (NumberFormatException e2) {
            throw new r(e2);
        }
    }

    @Override // com.google.gson.q
    public j serialize(Boolean bool, Type type, p pVar) {
        return new o(bool);
    }
}
