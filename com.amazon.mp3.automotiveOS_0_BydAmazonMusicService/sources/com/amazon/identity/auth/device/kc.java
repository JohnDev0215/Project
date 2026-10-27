package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class kc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f409a;
    public String b;
    public String c;
    public int d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public jc l;
    public ob m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public Map<String, String> s;
    public List<qb> t;
    public JSONArray u;
    public Map<String, Map<String, String>> v;
    public String w;
    public boolean x;

    public kc(String str) {
        this.u = new JSONArray();
        this.w = str;
    }

    public kc(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, String str8, String str9, Map<String, String> map, ob obVar, jc jcVar) {
        this.u = new JSONArray();
        this.f409a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.t = new ArrayList();
        this.v = new HashMap();
        this.i = str8;
        this.k = str9;
        this.s = map;
        this.m = obVar;
        this.l = jcVar;
    }
}
