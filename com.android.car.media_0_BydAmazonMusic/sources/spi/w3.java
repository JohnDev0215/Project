package spi;

import com.alibaba.android.arouter.utils.Consts;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: FieldNamingPolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class w3 implements x3 {
    public static final a a;
    public static final b b;
    public static final c c;
    public static final d d;
    public static final e e;
    public static final f f;
    public static final /* synthetic */ w3[] g;

    /* JADX INFO: compiled from: FieldNamingPolicy.java */
    public enum a extends w3 {
        public a(String str, int i) {
            super(str, i, null);
        }

        @Override // spi.x3
        public final String a(Field field) {
            return field.getName();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [spi.w3$f] */
    /* JADX WARN: Type inference failed for: r1v1, types: [spi.w3$b] */
    /* JADX WARN: Type inference failed for: r4v1, types: [spi.w3$c] */
    /* JADX WARN: Type inference failed for: r6v1, types: [spi.w3$d] */
    /* JADX WARN: Type inference failed for: r8v1, types: [spi.w3$e] */
    static {
        a aVar = new a("IDENTITY", 0);
        a = aVar;
        ?? r1 = new w3("UPPER_CAMEL_CASE", 1) { // from class: spi.w3.b
            {
                a aVar2 = null;
            }

            @Override // spi.x3
            public final String a(Field field) {
                return w3.c(field.getName());
            }
        };
        b = r1;
        ?? r4 = new w3("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: spi.w3.c
            {
                a aVar2 = null;
            }

            @Override // spi.x3
            public final String a(Field field) {
                return w3.c(w3.b(field.getName(), " "));
            }
        };
        c = r4;
        ?? r6 = new w3("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: spi.w3.d
            {
                a aVar2 = null;
            }

            @Override // spi.x3
            public final String a(Field field) {
                return w3.b(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        d = r6;
        ?? r8 = new w3("LOWER_CASE_WITH_DASHES", 4) { // from class: spi.w3.e
            {
                a aVar2 = null;
            }

            @Override // spi.x3
            public final String a(Field field) {
                return w3.b(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        e = r8;
        ?? r10 = new w3("LOWER_CASE_WITH_DOTS", 5) { // from class: spi.w3.f
            {
                a aVar2 = null;
            }

            @Override // spi.x3
            public final String a(Field field) {
                return w3.b(field.getName(), Consts.DOT).toLowerCase(Locale.ENGLISH);
            }
        };
        f = r10;
        g = new w3[]{aVar, r1, r4, r6, r8, r10};
    }

    private w3(String str, int i) {
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
        String strValueOf;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i++;
            cCharAt = str.charAt(i);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        int i2 = i + 1;
        if (i2 < str.length()) {
            strValueOf = upperCase + str.substring(i2);
        } else {
            strValueOf = String.valueOf(upperCase);
        }
        sb.append(strValueOf);
        return sb.toString();
    }

    public static w3 valueOf(String str) {
        return (w3) Enum.valueOf(w3.class, str);
    }

    public static w3[] values() {
        return (w3[]) g.clone();
    }

    public /* synthetic */ w3(String str, int i, a aVar) {
        this(str, i);
    }
}
