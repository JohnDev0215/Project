package android.content.type;

import java.io.InputStream;
import java.util.function.Function;
import libcore.content.type.MimeMap;

/* JADX INFO: loaded from: classes.dex */
public class DefaultMimeMapFactory {
    private DefaultMimeMapFactory() {
    }

    public static MimeMap create() {
        final Class<DefaultMimeMapFactory> cls = DefaultMimeMapFactory.class;
        return create(new Function() { // from class: android.content.type.-$$Lambda$DefaultMimeMapFactory$QwIcc--5O-7OhygVTd7dOIvFzQA
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return cls.getResourceAsStream("/res/" + ((String) obj));
            }
        });
    }

    public static MimeMap create(Function<String, InputStream> function) {
        MimeMap.Builder builder = MimeMap.builder();
        parseTypes(builder, function, "debian.mime.types");
        parseTypes(builder, function, "android.mime.types");
        parseTypes(builder, function, "vendor.mime.types");
        return builder.build();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0074, code lost:
    
        throw new java.lang.IllegalArgumentException("Malformed line: " + r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void parseTypes(libcore.content.type.MimeMap.Builder r7, java.util.function.Function<java.lang.String, java.io.InputStream> r8, java.lang.String r9) {
        /*
            java.lang.Object r8 = r8.apply(r9)     // Catch: java.lang.Throwable -> L94
            java.io.InputStream r8 = (java.io.InputStream) r8     // Catch: java.lang.Throwable -> L94
            java.lang.Object r8 = java.util.Objects.requireNonNull(r8)     // Catch: java.lang.Throwable -> L94
            java.io.InputStream r8 = (java.io.InputStream) r8     // Catch: java.lang.Throwable -> L94
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L88
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L88
            r1.<init>(r8)     // Catch: java.lang.Throwable -> L88
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L88
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L7e
            r2 = 10
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7e
        L1d:
            java.lang.String r2 = r0.readLine()     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L75
            r1.clear()     // Catch: java.lang.Throwable -> L7e
            r3 = 0
            r4 = r3
        L28:
            r5 = 32
            int r5 = r2.indexOf(r5, r4)     // Catch: java.lang.Throwable -> L7e
            if (r5 >= 0) goto L34
            int r5 = r2.length()     // Catch: java.lang.Throwable -> L7e
        L34:
            java.lang.String r4 = r2.substring(r4, r5)     // Catch: java.lang.Throwable -> L7e
            boolean r6 = r4.isEmpty()     // Catch: java.lang.Throwable -> L7e
            if (r6 != 0) goto L5c
            r1.add(r4)     // Catch: java.lang.Throwable -> L7e
            int r4 = r5 + 1
            int r5 = r2.length()     // Catch: java.lang.Throwable -> L7e
            if (r4 < r5) goto L28
            java.lang.Object r2 = r1.get(r3)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L7e
            int r3 = r1.size()     // Catch: java.lang.Throwable -> L7e
            r4 = 1
            java.util.List r3 = r1.subList(r4, r3)     // Catch: java.lang.Throwable -> L7e
            r7.put(r2, r3)     // Catch: java.lang.Throwable -> L7e
            goto L1d
        L5c:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7e
            r1.<init>()     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = "Malformed line: "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> L7e
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L7e
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7e
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L7e
            throw r7     // Catch: java.lang.Throwable -> L7e
        L75:
            r0.close()     // Catch: java.lang.Throwable -> L88
            if (r8 == 0) goto L7d
            r8.close()     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L94
        L7d:
            return
        L7e:
            r7 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L83
            goto L87
        L83:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch: java.lang.Throwable -> L88
        L87:
            throw r7     // Catch: java.lang.Throwable -> L88
        L88:
            r7 = move-exception
            if (r8 == 0) goto L93
            r8.close()     // Catch: java.lang.Throwable -> L8f
            goto L93
        L8f:
            r8 = move-exception
            r7.addSuppressed(r8)     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L94
        L93:
            throw r7     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L94
        L94:
            r7 = move-exception
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to parse "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r9 = r0.append(r9)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.content.type.DefaultMimeMapFactory.parseTypes(libcore.content.type.MimeMap$Builder, java.util.function.Function, java.lang.String):void");
    }
}
