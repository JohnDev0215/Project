package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: FieldNamingPolicy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements com.google.gson.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f4423b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f4424c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f4425d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f4426e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c f4427f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f4428g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final /* synthetic */ c[] f4429h;

    /* JADX INFO: compiled from: FieldNamingPolicy.java */
    enum a extends c {
        a(String str, int i2) {
            super(str, i2, null);
        }

        @Override // com.google.gson.d
        public String a(Field field) {
            return field.getName();
        }
    }

    static {
        a aVar = new a("IDENTITY", 0);
        f4423b = aVar;
        c cVar = new c("UPPER_CAMEL_CASE", 1) { // from class: com.google.gson.c.b
            {
                a aVar2 = null;
            }

            @Override // com.google.gson.d
            public String a(Field field) {
                return c.e(field.getName());
            }
        };
        f4424c = cVar;
        c cVar2 = new c("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: com.google.gson.c.c
            {
                a aVar2 = null;
            }

            @Override // com.google.gson.d
            public String a(Field field) {
                return c.e(c.d(field.getName(), " "));
            }
        };
        f4425d = cVar2;
        c cVar3 = new c("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: com.google.gson.c.d
            {
                a aVar2 = null;
            }

            @Override // com.google.gson.d
            public String a(Field field) {
                return c.d(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        f4426e = cVar3;
        c cVar4 = new c("LOWER_CASE_WITH_DASHES", 4) { // from class: com.google.gson.c.e
            {
                a aVar2 = null;
            }

            @Override // com.google.gson.d
            public String a(Field field) {
                return c.d(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        f4427f = cVar4;
        c cVar5 = new c("LOWER_CASE_WITH_DOTS", 5) { // from class: com.google.gson.c.f
            {
                a aVar2 = null;
            }

            @Override // com.google.gson.d
            public String a(Field field) {
                return c.d(field.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        };
        f4428g = cVar5;
        f4429h = new c[]{aVar, cVar, cVar2, cVar3, cVar4, cVar5};
    }

    private c(String str, int i2) {
    }

    private static String b(char c2, String str, int i2) {
        if (i2 >= str.length()) {
            return String.valueOf(c2);
        }
        return c2 + str.substring(i2);
    }

    static String d(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    static String e(String str) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i2 < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i2++;
            cCharAt = str.charAt(i2);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        sb.append(b(Character.toUpperCase(cCharAt), str, i2 + 1));
        return sb.toString();
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f4429h.clone();
    }

    /* synthetic */ c(String str, int i2, a aVar) {
        this(str, i2);
    }
}
