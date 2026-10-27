package j5;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: j5.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class EnumC1550h {
    public static final C1543a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ EnumC1550h[] f17014m;

    static {
        C1543a c1543a = new C1543a();
        l = c1543a;
        f17014m = new EnumC1550h[]{c1543a, new EnumC1550h() { // from class: j5.b
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.c(field.getName());
            }
        }, new EnumC1550h() { // from class: j5.c
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.c(EnumC1550h.a(field.getName(), ' '));
            }
        }, new EnumC1550h() { // from class: j5.d
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.a(field.getName(), '_').toUpperCase(Locale.ENGLISH);
            }
        }, new EnumC1550h() { // from class: j5.e
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.a(field.getName(), '_').toLowerCase(Locale.ENGLISH);
            }
        }, new EnumC1550h() { // from class: j5.f
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.a(field.getName(), '-').toLowerCase(Locale.ENGLISH);
            }
        }, new EnumC1550h() { // from class: j5.g
            @Override // j5.EnumC1550h
            public final String b(Field field) {
                return EnumC1550h.a(field.getName(), '.').toLowerCase(Locale.ENGLISH);
            }
        }};
    }

    public static String a(String str, char c) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(c);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String c(String str) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (Character.isLetter(cCharAt)) {
                if (Character.isUpperCase(cCharAt)) {
                    return str;
                }
                char upperCase = Character.toUpperCase(cCharAt);
                if (i7 == 0) {
                    return upperCase + str.substring(1);
                }
                return str.substring(0, i7) + upperCase + str.substring(i7 + 1);
            }
        }
        return str;
    }

    public static EnumC1550h valueOf(String str) {
        return (EnumC1550h) Enum.valueOf(EnumC1550h.class, str);
    }

    public static EnumC1550h[] values() {
        return (EnumC1550h[]) f17014m.clone();
    }

    public abstract String b(Field field);
}
