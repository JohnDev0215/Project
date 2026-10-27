package com.google.common.base;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public final class MoreObjects {
    public static <T> T firstNonNull(@CheckForNull T t, T t2) {
        if (t != null) {
            return t;
        }
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitEmptyValues;
        private boolean omitNullValues;

        private ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.omitEmptyValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public ToStringHelper add(String str, @CheckForNull Object obj) {
            return addHolder(str, obj);
        }

        public ToStringHelper add(String str, boolean z) {
            return addUnconditionalHolder(str, String.valueOf(z));
        }

        public ToStringHelper add(String str, char c) {
            return addUnconditionalHolder(str, String.valueOf(c));
        }

        public ToStringHelper add(String str, double d) {
            return addUnconditionalHolder(str, String.valueOf(d));
        }

        public ToStringHelper add(String str, float f) {
            return addUnconditionalHolder(str, String.valueOf(f));
        }

        public ToStringHelper add(String str, int i) {
            return addUnconditionalHolder(str, String.valueOf(i));
        }

        public ToStringHelper add(String str, long j) {
            return addUnconditionalHolder(str, String.valueOf(j));
        }

        public ToStringHelper addValue(@CheckForNull Object obj) {
            return addHolder(obj);
        }

        public ToStringHelper addValue(boolean z) {
            return addUnconditionalHolder(String.valueOf(z));
        }

        public ToStringHelper addValue(char c) {
            return addUnconditionalHolder(String.valueOf(c));
        }

        public ToStringHelper addValue(double d) {
            return addUnconditionalHolder(String.valueOf(d));
        }

        public ToStringHelper addValue(float f) {
            return addUnconditionalHolder(String.valueOf(f));
        }

        public ToStringHelper addValue(int i) {
            return addUnconditionalHolder(String.valueOf(i));
        }

        public ToStringHelper addValue(long j) {
            return addUnconditionalHolder(String.valueOf(j));
        }

        private static boolean isEmpty(Object obj) {
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() == 0;
            }
            if (obj instanceof Collection) {
                return ((Collection) obj).isEmpty();
            }
            if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            }
            if (obj instanceof Optional) {
                return !((Optional) obj).isPresent();
            }
            return obj.getClass().isArray() && Array.getLength(obj) == 0;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                r8 = this;
                boolean r0 = r8.omitNullValues
                boolean r1 = r8.omitEmptyValues
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = 32
                r2.<init>(r3)
                java.lang.String r3 = r8.className
                java.lang.StringBuilder r2 = r2.append(r3)
                r3 = 123(0x7b, float:1.72E-43)
                java.lang.StringBuilder r2 = r2.append(r3)
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r8.holderHead
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r3.next
                java.lang.String r4 = ""
            L1d:
                if (r3 == 0) goto L6b
                java.lang.Object r5 = r3.value
                boolean r6 = r3 instanceof com.google.common.base.MoreObjects.ToStringHelper.UnconditionalValueHolder
                if (r6 != 0) goto L32
                if (r5 != 0) goto L2a
                if (r0 != 0) goto L68
                goto L32
            L2a:
                if (r1 == 0) goto L32
                boolean r6 = isEmpty(r5)
                if (r6 != 0) goto L68
            L32:
                r2.append(r4)
                java.lang.String r4 = r3.name
                if (r4 == 0) goto L44
                java.lang.String r4 = r3.name
                java.lang.StringBuilder r4 = r2.append(r4)
                r6 = 61
                r4.append(r6)
            L44:
                if (r5 == 0) goto L63
                java.lang.Class r4 = r5.getClass()
                boolean r4 = r4.isArray()
                if (r4 == 0) goto L63
                r4 = 1
                java.lang.Object[] r6 = new java.lang.Object[r4]
                r7 = 0
                r6[r7] = r5
                java.lang.String r5 = java.util.Arrays.deepToString(r6)
                int r6 = r5.length()
                int r6 = r6 - r4
                r2.append(r5, r4, r6)
                goto L66
            L63:
                r2.append(r5)
            L66:
                java.lang.String r4 = ", "
            L68:
                com.google.common.base.MoreObjects$ToStringHelper$ValueHolder r3 = r3.next
                goto L1d
            L6b:
                r0 = 125(0x7d, float:1.75E-43)
                java.lang.StringBuilder r0 = r2.append(r0)
                java.lang.String r0 = r0.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.base.MoreObjects.ToStringHelper.toString():java.lang.String");
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@CheckForNull Object obj) {
            addHolder().value = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, @CheckForNull Object obj) {
            ValueHolder valueHolderAddHolder = addHolder();
            valueHolderAddHolder.value = obj;
            valueHolderAddHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        private UnconditionalValueHolder addUnconditionalHolder() {
            UnconditionalValueHolder unconditionalValueHolder = new UnconditionalValueHolder();
            this.holderTail.next = unconditionalValueHolder;
            this.holderTail = unconditionalValueHolder;
            return unconditionalValueHolder;
        }

        private ToStringHelper addUnconditionalHolder(Object obj) {
            addUnconditionalHolder().value = obj;
            return this;
        }

        private ToStringHelper addUnconditionalHolder(String str, Object obj) {
            UnconditionalValueHolder unconditionalValueHolderAddUnconditionalHolder = addUnconditionalHolder();
            unconditionalValueHolderAddUnconditionalHolder.value = obj;
            unconditionalValueHolderAddUnconditionalHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        private static class ValueHolder {

            @CheckForNull
            String name;

            @CheckForNull
            ValueHolder next;

            @CheckForNull
            Object value;

            private ValueHolder() {
            }
        }

        private static final class UnconditionalValueHolder extends ValueHolder {
            private UnconditionalValueHolder() {
                super();
            }
        }
    }

    private MoreObjects() {
    }
}
