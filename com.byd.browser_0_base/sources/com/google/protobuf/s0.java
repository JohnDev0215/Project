package com.google.protobuf;

import defpackage.d83;
import defpackage.lk;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: MessageLiteToString.java */
/* JADX INFO: loaded from: classes.dex */
public final class s0 {
    public static final String a(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    public static final void b(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                b(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                b(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            lk.f fVar = lk.f;
            sb.append(d83.a(new lk.f(((String) obj).getBytes(c0.a))));
            sb.append('\"');
            return;
        }
        if (obj instanceof lk) {
            sb.append(": \"");
            sb.append(d83.a((lk) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof z) {
            sb.append(" {");
            c((z) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        b(sb, i4, "key", entry.getKey());
        b(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x020c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(com.google.protobuf.r0 r13, java.lang.StringBuilder r14, int r15) {
        /*
            Method dump skipped, instruction units count: 627
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.s0.c(com.google.protobuf.r0, java.lang.StringBuilder, int):void");
    }
}
