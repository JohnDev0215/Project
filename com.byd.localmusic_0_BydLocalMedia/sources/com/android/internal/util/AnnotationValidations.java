package com.android.internal.util;

import android.annotation.AppIdInt;
import android.annotation.ColorInt;
import android.annotation.FloatRange;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.Size;
import android.annotation.UserIdInt;
import android.app.slice.Slice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes4.dex */
public class AnnotationValidations {
    @Deprecated
    public static void validate(Class<? extends Annotation> cls, Annotation annotation, int i, Object... objArr) {
    }

    @Deprecated
    public static void validate(Class<? extends Annotation> cls, Annotation annotation, Object obj) {
    }

    @Deprecated
    public static void validate(Class<? extends Annotation> cls, Annotation annotation, Object obj, Object... objArr) {
    }

    private AnnotationValidations() {
    }

    public static void validate(Class<UserIdInt> cls, UserIdInt userIdInt, int i) {
        if ((i == -10000 || i >= -3) && i <= 21474) {
            return;
        }
        invalid(cls, Integer.valueOf(i));
    }

    public static void validate(Class<AppIdInt> cls, AppIdInt appIdInt, int i) {
        if (i / UserHandle.PER_USER_RANGE != 0 || i < 0) {
            invalid(cls, Integer.valueOf(i));
        }
    }

    public static void validate(Class<IntRange> cls, IntRange intRange, int i, String str, long j, String str2, long j2) {
        validate(cls, intRange, i, str, j);
        validate(cls, intRange, i, str2, j2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void validate(java.lang.Class<android.annotation.IntRange> r2, android.annotation.IntRange r3, int r4, java.lang.String r5, long r6) {
        /*
            int r3 = r5.hashCode()
            r0 = 3707(0xe7b, float:5.195E-42)
            r1 = 1
            if (r3 == r0) goto L19
            r0 = 3151786(0x3017aa, float:4.416593E-39)
            if (r3 == r0) goto Lf
            goto L24
        Lf:
            java.lang.String r3 = "from"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L24
            r3 = 0
            goto L25
        L19:
            java.lang.String r3 = "to"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L24
            r3 = r1
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 == 0) goto L3b
            if (r3 == r1) goto L2a
            goto L4b
        L2a:
            long r0 = (long) r4
            int r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r3 <= 0) goto L4b
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            invalid(r2, r3, r5, r4)
            goto L4b
        L3b:
            long r0 = (long) r4
            int r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r3 >= 0) goto L4b
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            invalid(r2, r3, r5, r4)
        L4b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.util.AnnotationValidations.validate(java.lang.Class, android.annotation.IntRange, int, java.lang.String, long):void");
    }

    public static void validate(Class<IntRange> cls, IntRange intRange, long j, String str, long j2, String str2, long j3) {
        validate(cls, intRange, j, str, j2);
        validate(cls, intRange, j, str2, j3);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void validate(java.lang.Class<android.annotation.IntRange> r2, android.annotation.IntRange r3, long r4, java.lang.String r6, long r7) {
        /*
            int r3 = r6.hashCode()
            r0 = 3707(0xe7b, float:5.195E-42)
            r1 = 1
            if (r3 == r0) goto L19
            r0 = 3151786(0x3017aa, float:4.416593E-39)
            if (r3 == r0) goto Lf
            goto L24
        Lf:
            java.lang.String r3 = "from"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L24
            r3 = 0
            goto L25
        L19:
            java.lang.String r3 = "to"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L24
            r3 = r1
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 == 0) goto L3a
            if (r3 == r1) goto L2a
            goto L49
        L2a:
            int r3 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r3 <= 0) goto L49
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r7)
            invalid(r2, r3, r6, r4)
            goto L49
        L3a:
            int r3 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r3 >= 0) goto L49
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r7)
            invalid(r2, r3, r6, r4)
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.util.AnnotationValidations.validate(java.lang.Class, android.annotation.IntRange, long, java.lang.String, long):void");
    }

    public static void validate(Class<FloatRange> cls, FloatRange floatRange, float f, String str, float f2, String str2, float f3) {
        validate(cls, floatRange, f, str, f2);
        validate(cls, floatRange, f, str2, f3);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void validate(java.lang.Class<android.annotation.FloatRange> r2, android.annotation.FloatRange r3, float r4, java.lang.String r5, float r6) {
        /*
            int r3 = r5.hashCode()
            r0 = 3707(0xe7b, float:5.195E-42)
            r1 = 1
            if (r3 == r0) goto L19
            r0 = 3151786(0x3017aa, float:4.416593E-39)
            if (r3 == r0) goto Lf
            goto L24
        Lf:
            java.lang.String r3 = "from"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L24
            r3 = 0
            goto L25
        L19:
            java.lang.String r3 = "to"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L24
            r3 = r1
            goto L25
        L24:
            r3 = -1
        L25:
            if (r3 == 0) goto L3a
            if (r3 == r1) goto L2a
            goto L49
        L2a:
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 <= 0) goto L49
            java.lang.Float r3 = java.lang.Float.valueOf(r4)
            java.lang.Float r4 = java.lang.Float.valueOf(r6)
            invalid(r2, r3, r5, r4)
            goto L49
        L3a:
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 >= 0) goto L49
            java.lang.Float r3 = java.lang.Float.valueOf(r4)
            java.lang.Float r4 = java.lang.Float.valueOf(r6)
            invalid(r2, r3, r5, r4)
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.util.AnnotationValidations.validate(java.lang.Class, android.annotation.FloatRange, float, java.lang.String, float):void");
    }

    public static void validate(Class<NonNull> cls, NonNull nonNull, Object obj) {
        if (obj == null) {
            throw null;
        }
    }

    public static void validate(Class<Size> cls, Size size, int i, String str, int i2, String str2, int i3) {
        validate(cls, size, i, str, i2);
        validate(cls, size, i, str2, i3);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void validate(Class<Size> cls, Size size, int i, String str, int i2) {
        byte b;
        switch (str.hashCode()) {
            case 107876:
                b = !str.equals(Slice.SUBTYPE_MAX) ? (byte) -1 : (byte) 2;
                break;
            case 108114:
                b = !str.equals("min") ? (byte) -1 : (byte) 1;
                break;
            case 111972721:
                b = !str.equals("value") ? (byte) -1 : (byte) 0;
                break;
            case 653829648:
                b = !str.equals("multiple") ? (byte) -1 : (byte) 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            if (i2 == -1 || i == i2) {
                return;
            }
            invalid(cls, Integer.valueOf(i), str, Integer.valueOf(i2));
            return;
        }
        if (b == 1) {
            if (i < i2) {
                invalid(cls, Integer.valueOf(i), str, Integer.valueOf(i2));
            }
        } else if (b == 2) {
            if (i > i2) {
                invalid(cls, Integer.valueOf(i), str, Integer.valueOf(i2));
            }
        } else if (b == 3 && i % i2 != 0) {
            invalid(cls, Integer.valueOf(i), str, Integer.valueOf(i2));
        }
    }

    public static void validate(Class<PackageManager.PermissionResult> cls, PackageManager.PermissionResult permissionResult, int i) {
        validateIntEnum(cls, i, 0);
    }

    public static void validate(Class<PackageManager.PackageInfoFlags> cls, PackageManager.PackageInfoFlags packageInfoFlags, int i) {
        validateIntFlags(cls, i, BitUtils.flagsUpTo(536870912));
    }

    public static void validate(Class<Intent.Flags> cls, Intent.Flags flags, int i) {
        validateIntFlags(cls, i, BitUtils.flagsUpTo(Integer.MIN_VALUE));
    }

    public static void validate(Class<? extends Annotation> cls, Annotation annotation, int i) {
        if (!(("android.annotation".equals(cls.getPackageName$()) && cls.getSimpleName().endsWith("Res")) || ColorInt.class.equals(cls)) || i >= 0) {
            return;
        }
        invalid(cls, Integer.valueOf(i));
    }

    public static void validate(Class<? extends Annotation> cls, Annotation annotation, long j) {
        if ("android.annotation".equals(cls.getPackageName$()) && cls.getSimpleName().endsWith("Long") && j < 0) {
            invalid(cls, Long.valueOf(j));
        }
    }

    private static void validateIntEnum(Class<? extends Annotation> cls, int i, int i2) {
        if (i > i2) {
            invalid(cls, Integer.valueOf(i));
        }
    }

    private static void validateIntFlags(Class<? extends Annotation> cls, int i, int i2) {
        if ((i2 & i) != i2) {
            invalid(cls, "0x" + Integer.toHexString(i));
        }
    }

    private static void invalid(Class<? extends Annotation> cls, Object obj) {
        invalid("@" + cls.getSimpleName(), obj);
    }

    private static void invalid(Class<? extends Annotation> cls, Object obj, String str, Object obj2) {
        String str2;
        if ("value".equals(str)) {
            str2 = "";
        } else {
            str2 = str + " = ";
        }
        invalid("@" + cls.getSimpleName() + "(" + str2 + obj2 + ")", obj);
    }

    private static void invalid(String str, Object obj) {
        throw new IllegalStateException("Invalid " + str + ": " + obj);
    }
}
