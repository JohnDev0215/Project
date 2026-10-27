package com.hp.hpl.sparta.xpath;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Hashtable f4195a = new Hashtable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Stack f4196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4198d;

    private c0(String str) throws XPathException {
        this(str, new InputStreamReader(new ByteArrayInputStream(str.getBytes())));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004f A[Catch: IOException -> 0x006d, TryCatch #0 {IOException -> 0x006d, blocks: (B:3:0x000a, B:5:0x002d, B:7:0x0035, B:10:0x003d, B:11:0x0044, B:16:0x004f, B:17:0x0058, B:18:0x0059, B:20:0x005f, B:22:0x0065, B:8:0x003a), top: B:27:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0059 A[Catch: IOException -> 0x006d, TryCatch #0 {IOException -> 0x006d, blocks: (B:3:0x000a, B:5:0x002d, B:7:0x0035, B:10:0x003d, B:11:0x0044, B:16:0x004f, B:17:0x0058, B:18:0x0059, B:20:0x005f, B:22:0x0065, B:8:0x003a), top: B:27:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004b A[EDGE_INSN: B:29:0x004b->B:13:0x004b BREAK  A[LOOP:0: B:11:0x0044->B:22:0x0065], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private c0(java.lang.String r6, java.io.Reader r7) throws com.hp.hpl.sparta.xpath.XPathException {
        /*
            r5 = this;
            r5.<init>()
            java.util.Stack r0 = new java.util.Stack
            r0.<init>()
            r5.f4196b = r0
            r5.f4198d = r6     // Catch: java.io.IOException -> L6d
            com.hp.hpl.sparta.xpath.s r6 = new com.hp.hpl.sparta.xpath.s     // Catch: java.io.IOException -> L6d
            r6.<init>(r7)     // Catch: java.io.IOException -> L6d
            r7 = 47
            r6.b(r7)     // Catch: java.io.IOException -> L6d
            r0 = 46
            r6.b(r0)     // Catch: java.io.IOException -> L6d
            r0 = 58
            r6.d(r0, r0)     // Catch: java.io.IOException -> L6d
            r0 = 95
            r6.d(r0, r0)     // Catch: java.io.IOException -> L6d
            int r0 = r6.a()     // Catch: java.io.IOException -> L6d
            r1 = 1
            r2 = 0
            if (r0 != r7) goto L3a
            r5.f4197c = r1     // Catch: java.io.IOException -> L6d
            int r0 = r6.a()     // Catch: java.io.IOException -> L6d
            if (r0 != r7) goto L3c
            r6.a()     // Catch: java.io.IOException -> L6d
            r0 = r1
            goto L3d
        L3a:
            r5.f4197c = r2     // Catch: java.io.IOException -> L6d
        L3c:
            r0 = r2
        L3d:
            java.util.Stack r3 = r5.f4196b     // Catch: java.io.IOException -> L6d
            com.hp.hpl.sparta.xpath.t r4 = new com.hp.hpl.sparta.xpath.t     // Catch: java.io.IOException -> L6d
            r4.<init>(r5, r0, r6)     // Catch: java.io.IOException -> L6d
        L44:
            r3.push(r4)     // Catch: java.io.IOException -> L6d
            int r0 = r6.f4205a     // Catch: java.io.IOException -> L6d
            if (r0 == r7) goto L59
            r7 = -1
            if (r0 != r7) goto L4f
            return
        L4f:
            com.hp.hpl.sparta.xpath.XPathException r7 = new com.hp.hpl.sparta.xpath.XPathException     // Catch: java.io.IOException -> L6d
            java.lang.String r0 = "at end of XPATH expression"
            java.lang.String r1 = "end of expression"
            r7.<init>(r5, r0, r6, r1)     // Catch: java.io.IOException -> L6d
            throw r7     // Catch: java.io.IOException -> L6d
        L59:
            int r0 = r6.a()     // Catch: java.io.IOException -> L6d
            if (r0 != r7) goto L64
            r6.a()     // Catch: java.io.IOException -> L6d
            r0 = r1
            goto L65
        L64:
            r0 = r2
        L65:
            java.util.Stack r3 = r5.f4196b     // Catch: java.io.IOException -> L6d
            com.hp.hpl.sparta.xpath.t r4 = new com.hp.hpl.sparta.xpath.t     // Catch: java.io.IOException -> L6d
            r4.<init>(r5, r0, r6)     // Catch: java.io.IOException -> L6d
            goto L44
        L6d:
            r6 = move-exception
            com.hp.hpl.sparta.xpath.XPathException r7 = new com.hp.hpl.sparta.xpath.XPathException
            r7.<init>(r5, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.xpath.c0.<init>(java.lang.String, java.io.Reader):void");
    }

    private c0(boolean z, t[] tVarArr) {
        this.f4196b = new Stack();
        for (t tVar : tVarArr) {
            this.f4196b.addElement(tVar);
        }
        this.f4197c = z;
        this.f4198d = null;
    }

    private String a() {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration enumerationElements = this.f4196b.elements();
        boolean z = true;
        while (enumerationElements.hasMoreElements()) {
            t tVar = (t) enumerationElements.nextElement();
            if (!z || this.f4197c) {
                stringBuffer.append('/');
                if (tVar.c()) {
                    stringBuffer.append('/');
                }
            }
            stringBuffer.append(tVar.toString());
            z = false;
        }
        return stringBuffer.toString();
    }

    public static c0 b(String str) throws XPathException {
        c0 c0Var;
        synchronized (f4195a) {
            c0Var = (c0) f4195a.get(str);
            if (c0Var == null) {
                c0Var = new c0(str);
                f4195a.put(str, c0Var);
            }
        }
        return c0Var;
    }

    public Enumeration c() {
        return this.f4196b.elements();
    }

    public Object clone() {
        int size = this.f4196b.size();
        t[] tVarArr = new t[size];
        Enumeration enumerationElements = this.f4196b.elements();
        for (int i = 0; i < size; i++) {
            tVarArr[i] = (t) enumerationElements.nextElement();
        }
        return new c0(this.f4197c, tVarArr);
    }

    public boolean d() {
        return this.f4197c;
    }

    public boolean e() {
        return ((t) this.f4196b.peek()).d();
    }

    public String toString() {
        if (this.f4198d == null) {
            this.f4198d = a();
        }
        return this.f4198d;
    }
}
