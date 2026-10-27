package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;
import p.yt0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class FieldNamingPolicy implements yt0 {
    public static final FieldNamingPolicy c;
    public static final /* synthetic */ FieldNamingPolicy[] e;

    static {
        FieldNamingPolicy fieldNamingPolicy = new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.1
            @Override // p.yt0
            public final String a(Field field) {
                return field.getName();
            }
        };
        c = fieldNamingPolicy;
        e = new FieldNamingPolicy[]{fieldNamingPolicy, new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.2
            @Override // p.yt0
            public final String a(Field field) {
                return FieldNamingPolicy.c(field.getName());
            }
        }, new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.3
            @Override // p.yt0
            public final String a(Field field) {
                return FieldNamingPolicy.c(FieldNamingPolicy.b(field.getName(), " "));
            }
        }, new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.4
            @Override // p.yt0
            public final String a(Field field) {
                return FieldNamingPolicy.b(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        }, new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.5
            @Override // p.yt0
            public final String a(Field field) {
                return FieldNamingPolicy.b(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        }, new FieldNamingPolicy() { // from class: com.google.gson.FieldNamingPolicy.6
            @Override // p.yt0
            public final String a(Field field) {
                return FieldNamingPolicy.b(field.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        }};
    }

    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String c(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (!Character.isLetter(str.charAt(i)) && i < length) {
            i++;
        }
        char cCharAt = str.charAt(i);
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        if (i == 0) {
            return upperCase + str.substring(1);
        }
        return str.substring(0, i) + upperCase + str.substring(i + 1);
    }

    public static FieldNamingPolicy valueOf(String str) {
        return (FieldNamingPolicy) Enum.valueOf(FieldNamingPolicy.class, str);
    }

    public static FieldNamingPolicy[] values() {
        return (FieldNamingPolicy[]) e.clone();
    }
}
