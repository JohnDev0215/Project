package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f457a;
    public List<String> b = new ArrayList();
    public List<String> c = new ArrayList();

    public n5(String str) {
        this.f457a = str;
    }

    public n5 a(String str, String str2) {
        this.b.add(str);
        this.c.add(str + " " + str2);
        return this;
    }

    public String toString() {
        return String.format("CREATE TABLE %s (%s);", this.f457a, e.a(", ", this.c));
    }
}
