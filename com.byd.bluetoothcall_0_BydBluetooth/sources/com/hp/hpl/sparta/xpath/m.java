package com.hp.hpl.sparta.xpath;

import com.hp.hpl.sparta.Sparta;

/* JADX INFO: loaded from: classes.dex */
public class m extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4202a;

    m(String str) {
        this.f4202a = Sparta.a(str);
    }

    @Override // com.hp.hpl.sparta.xpath.o
    public void a(b0 b0Var) {
        b0Var.p(this);
    }

    @Override // com.hp.hpl.sparta.xpath.o
    public boolean b() {
        return false;
    }

    public String c() {
        return this.f4202a;
    }

    public String toString() {
        return this.f4202a;
    }
}
