package com.byd.bluetoothcall.b;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: Contacts_info.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Comparable<b> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Collator f3236b = Collator.getInstance(Locale.CHINA);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3237c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3238d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3239e;
    private List<c> f;
    private String g;
    private int h;
    private String[] i;
    private String[] j;
    private String k;
    private CollationKey l;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        if (d().equals(bVar.d())) {
            return this.l.compareTo(bVar.l);
        }
        d().compareTo(bVar.d());
        return 0;
    }

    public String d() {
        return this.f3237c;
    }

    public String e() {
        return this.f3238d;
    }

    public int g() {
        return this.h;
    }

    public String h() {
        return this.k;
    }

    public String i() {
        return this.f3239e;
    }

    public List<c> j() {
        return this.f;
    }

    public String k() {
        return this.g;
    }

    public String[] l() {
        return this.j;
    }

    public String[] m() {
        return this.i;
    }

    public void n(String str) {
        this.f3237c = str;
    }

    public void o(String str) {
        this.f3238d = str;
    }

    public void p(int i) {
        this.h = i;
    }

    public void q(String str) {
        this.l = f3236b.getCollationKey(str);
    }

    public void r(String str) {
        this.k = str;
    }

    public void s(String str) {
        this.f3239e = str;
    }

    public void t(List<c> list) {
        this.f = list;
    }

    public String toString() {
        return "Contacts_info{First_capital_letter='" + this.f3237c + "', First_word='" + this.f3238d + "', Name='" + this.f3239e + "', Numbers=" + this.f + ", PY='" + this.g + "', IconBgIndex=" + this.h + ", t9code=" + Arrays.toString(this.i) + ", pycode=" + Arrays.toString(this.j) + ", keyword='" + this.k + "'}";
    }

    public void u(String str) {
        this.g = str;
    }

    public void v(String[] strArr) {
        this.j = strArr;
    }

    public void w(String[] strArr) {
        this.i = strArr;
    }
}
