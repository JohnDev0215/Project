package com.hp.hpl.sparta;

import com.hp.hpl.sparta.Sparta;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
class n implements Sparta.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Hashtable f4183a = new Hashtable();

    n() {
    }

    @Override // com.hp.hpl.sparta.Sparta.c
    public String a(String str) {
        String str2 = (String) this.f4183a.get(str);
        if (str2 != null) {
            return str2;
        }
        this.f4183a.put(str, str);
        return str;
    }
}
