package com.bugsnag.android.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: compiled from: StringUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000e\u0010\u0003\u001a\u00020\u0002*\u0004\u0018\u00010\u0001H\u0002J\u000e\u0010\u0004\u001a\u00020\u0002*\u0004\u0018\u00010\u0001H\u0002J\u0016\u0010\t\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007J\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00052\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bJ$\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00052\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f¨\u0006\u0013"}, d2 = {"Lcom/bugsnag/android/internal/StringUtils;", "", "", "isDefinitelyMutableMap", "isDefinitelyMutableList", "", "maxLength", "", "str", "stringTrimmedTo", "maxStringLength", "", "list", "Lcom/bugsnag/android/internal/TrimMetrics;", "trimStringValuesTo", "", "map", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class StringUtils {
    public static final StringUtils INSTANCE = new StringUtils();

    private StringUtils() {
    }

    public final String stringTrimmedTo(int maxLength, String str) {
        int length = str.length() - maxLength;
        if (length < 25) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(0, maxLength);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        sb.append(strSubstring);
        sb.append("***<");
        sb.append(length);
        sb.append("> CHARS TRUNCATED***");
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bugsnag.android.internal.TrimMetrics trimStringValuesTo(int r9, java.util.List<java.lang.Object> r10) {
        /*
            r8 = this;
            int r0 = r10.size()
            r1 = 0
            r2 = r1
            r3 = r2
        L7:
            if (r1 >= r0) goto Lb2
            com.bugsnag.android.internal.StringUtils r4 = com.bugsnag.android.internal.StringUtils.INSTANCE
            java.lang.Object r5 = r10.get(r1)
            boolean r6 = r5 instanceof java.lang.String
            if (r6 == 0) goto L2d
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            int r7 = r6.length()
            if (r7 <= r9) goto L2d
            java.lang.String r4 = r4.stringTrimmedTo(r9, r6)
            int r5 = r6.length()
            int r5 = r5 - r9
            r10.set(r1, r4)
            int r2 = r2 + 1
            int r3 = r3 + r5
            goto Lae
        L2d:
            boolean r6 = access$isDefinitelyMutableMap(r4, r5)
            java.lang.String r7 = "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>"
            if (r6 == 0) goto L53
            if (r5 == 0) goto L4d
            java.util.Map r6 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r5)
            com.bugsnag.android.internal.TrimMetrics r4 = r4.trimStringValuesTo(r9, r6)
            int r6 = r4.getItemsTrimmed()
            int r4 = r4.getDataTrimmed()
        L47:
            r10.set(r1, r5)
            int r2 = r2 + r6
            int r3 = r3 + r4
            goto Lae
        L4d:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            r9.<init>(r7)
            throw r9
        L53:
            boolean r6 = access$isDefinitelyMutableList(r4, r5)
            if (r6 == 0) goto L74
            if (r5 == 0) goto L6c
            java.util.List r6 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r5)
            com.bugsnag.android.internal.TrimMetrics r4 = r4.trimStringValuesTo(r9, r6)
            int r6 = r4.getItemsTrimmed()
            int r4 = r4.getDataTrimmed()
            goto L47
        L6c:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r10 = "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Any?>"
            r9.<init>(r10)
            throw r9
        L74:
            boolean r6 = r5 instanceof java.util.Map
            if (r6 == 0) goto L97
            java.util.Map r5 = (java.util.Map) r5
            java.util.Map r5 = kotlin.collections.MapsKt.toMutableMap(r5)
            if (r5 == 0) goto L91
            java.util.Map r5 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r5)
            com.bugsnag.android.internal.TrimMetrics r4 = r4.trimStringValuesTo(r9, r5)
            int r6 = r4.getItemsTrimmed()
            int r4 = r4.getDataTrimmed()
            goto L47
        L91:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            r9.<init>(r7)
            throw r9
        L97:
            boolean r6 = r5 instanceof java.util.Collection
            if (r6 == 0) goto Lae
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.List r5 = kotlin.collections.CollectionsKt.toMutableList(r5)
            com.bugsnag.android.internal.TrimMetrics r4 = r4.trimStringValuesTo(r9, r5)
            int r6 = r4.getItemsTrimmed()
            int r4 = r4.getDataTrimmed()
            goto L47
        Lae:
            int r1 = r1 + 1
            goto L7
        Lb2:
            com.bugsnag.android.internal.TrimMetrics r9 = new com.bugsnag.android.internal.TrimMetrics
            r9.<init>(r2, r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bugsnag.android.internal.StringUtils.trimStringValuesTo(int, java.util.List):com.bugsnag.android.internal.TrimMetrics");
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final TrimMetrics trimStringValuesTo(int maxStringLength, Map<String, Object> map) {
        int itemsTrimmed;
        int dataTrimmed;
        Object obj;
        Iterator<T> it = map.entrySet().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            StringUtils stringUtils = INSTANCE;
            Object value = entry.getValue();
            if (value instanceof String) {
                String str = (String) value;
                if (str.length() > maxStringLength) {
                    String strStringTrimmedTo = stringUtils.stringTrimmedTo(maxStringLength, str);
                    int length = str.length() - maxStringLength;
                    entry.setValue(strStringTrimmedTo);
                    i++;
                    i2 += length;
                }
            }
            if (stringUtils.isDefinitelyMutableMap(value)) {
                if (value != null) {
                    TrimMetrics trimMetricsTrimStringValuesTo = stringUtils.trimStringValuesTo(maxStringLength, TypeIntrinsics.asMutableMap(value));
                    itemsTrimmed = trimMetricsTrimStringValuesTo.getItemsTrimmed();
                    dataTrimmed = trimMetricsTrimStringValuesTo.getDataTrimmed();
                    obj = value;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>");
                }
            } else if (stringUtils.isDefinitelyMutableList(value)) {
                if (value != null) {
                    TrimMetrics trimMetricsTrimStringValuesTo2 = stringUtils.trimStringValuesTo(maxStringLength, TypeIntrinsics.asMutableList(value));
                    itemsTrimmed = trimMetricsTrimStringValuesTo2.getItemsTrimmed();
                    dataTrimmed = trimMetricsTrimStringValuesTo2.getDataTrimmed();
                    obj = value;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Any?>");
                }
            } else if (value instanceof Map) {
                Map mutableMap = MapsKt__MapsKt.toMutableMap((Map) value);
                if (mutableMap == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>");
                }
                Map<String, Object> mapAsMutableMap = TypeIntrinsics.asMutableMap(mutableMap);
                TrimMetrics trimMetricsTrimStringValuesTo3 = stringUtils.trimStringValuesTo(maxStringLength, mapAsMutableMap);
                itemsTrimmed = trimMetricsTrimStringValuesTo3.getItemsTrimmed();
                dataTrimmed = trimMetricsTrimStringValuesTo3.getDataTrimmed();
                obj = mapAsMutableMap;
            } else if (value instanceof Collection) {
                List<Object> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) value);
                TrimMetrics trimMetricsTrimStringValuesTo4 = stringUtils.trimStringValuesTo(maxStringLength, mutableList);
                itemsTrimmed = trimMetricsTrimStringValuesTo4.getItemsTrimmed();
                dataTrimmed = trimMetricsTrimStringValuesTo4.getDataTrimmed();
                obj = mutableList;
            }
            entry.setValue(obj);
            i += itemsTrimmed;
            i2 += dataTrimmed;
        }
        return new TrimMetrics(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDefinitelyMutableMap(Object obj) {
        return (obj instanceof HashMap) || (obj instanceof TreeMap) || (obj instanceof ConcurrentMap) || (obj instanceof EnumMap) || (obj instanceof Hashtable) || (obj instanceof WeakHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDefinitelyMutableList(Object obj) {
        return (obj instanceof ArrayList) || (obj instanceof LinkedList) || (obj instanceof CopyOnWriteArrayList) || (obj instanceof Vector);
    }
}
