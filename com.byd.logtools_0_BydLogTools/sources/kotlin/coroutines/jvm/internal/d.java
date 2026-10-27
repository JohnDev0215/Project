package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: DebugMetadata.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    private static final void a(int i, int i2) {
        if (i2 <= i) {
            return;
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + i + ", got " + i2 + ". Please update the Kotlin standard library.").toString());
    }

    private static final c b(BaseContinuationImpl baseContinuationImpl) {
        return (c) baseContinuationImpl.getClass().getAnnotation(c.class);
    }

    private static final int c(BaseContinuationImpl baseContinuationImpl) {
        try {
            Field declaredField = baseContinuationImpl.getClass().getDeclaredField("label");
            i.b(declaredField, "field");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(baseContinuationImpl);
            if (!(obj instanceof Integer)) {
                obj = null;
            }
            Integer num = (Integer) obj;
            return (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static final StackTraceElement d(BaseContinuationImpl baseContinuationImpl) {
        String strC;
        i.c(baseContinuationImpl, "$this$getStackTraceElementImpl");
        c cVarB = b(baseContinuationImpl);
        if (cVarB == null) {
            return null;
        }
        a(1, cVarB.v());
        int iC = c(baseContinuationImpl);
        int i = iC < 0 ? -1 : cVarB.l()[iC];
        String strB = f.f5104c.b(baseContinuationImpl);
        if (strB == null) {
            strC = cVarB.c();
        } else {
            strC = strB + '/' + cVarB.c();
        }
        return new StackTraceElement(strC, cVarB.m(), cVarB.f(), i);
    }
}
