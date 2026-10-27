package com.google.gson;

import com.byd.systemsettings.system.BydPowerManagerDevice;
import java.lang.reflect.Field;
import java.util.Locale;
import k6.c;

/* JADX INFO: loaded from: classes2.dex */
public enum FieldNamingPolicy implements c {
    IDENTITY { // from class: com.google.gson.FieldNamingPolicy.1
        @Override // com.google.gson.FieldNamingPolicy, k6.c
        public String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: com.google.gson.FieldNamingPolicy.2
        @Override // com.google.gson.FieldNamingPolicy, k6.c
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: com.google.gson.FieldNamingPolicy.3
        @Override // com.google.gson.FieldNamingPolicy, k6.c
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(FieldNamingPolicy.separateCamelCase(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: com.google.gson.FieldNamingPolicy.4
        @Override // com.google.gson.FieldNamingPolicy, k6.c
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), BydPowerManagerDevice.SEPARATOR_K_V).toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: com.google.gson.FieldNamingPolicy.5
        @Override // com.google.gson.FieldNamingPolicy, k6.c
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    };

    private static String modifyString(char c8, String str, int i8) {
        if (i8 >= str.length()) {
            return String.valueOf(c8);
        }
        return c8 + str.substring(i8);
    }

    public static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String upperCaseFirstLetter(String str) {
        StringBuilder sb = new StringBuilder();
        int i8 = 0;
        char cCharAt = str.charAt(0);
        while (i8 < str.length() - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i8++;
            cCharAt = str.charAt(i8);
        }
        if (i8 == str.length()) {
            return sb.toString();
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        sb.append(modifyString(Character.toUpperCase(cCharAt), str, i8 + 1));
        return sb.toString();
    }

    @Override // k6.c
    public abstract /* synthetic */ String translateName(Field field);
}
