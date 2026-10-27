package com.google.i18n.phonenumbers.prefixmapper;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.SortedMap;
import java.util.TreeSet;

/* JADX INFO: compiled from: PhonePrefixMapStorageStrategy.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f4156a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final TreeSet<Integer> f4157b = new TreeSet<>();

    c() {
    }

    public abstract String a(int i);

    public int b() {
        return this.f4156a;
    }

    public TreeSet<Integer> c() {
        return this.f4157b;
    }

    public abstract int d(int i);

    public abstract void e(ObjectInput objectInput) throws IOException;

    public abstract void f(SortedMap<Integer, String> sortedMap);

    public abstract void g(ObjectOutput objectOutput) throws IOException;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iB = b();
        for (int i = 0; i < iB; i++) {
            sb.append(d(i));
            sb.append("|");
            sb.append(a(i));
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        return sb.toString();
    }
}
