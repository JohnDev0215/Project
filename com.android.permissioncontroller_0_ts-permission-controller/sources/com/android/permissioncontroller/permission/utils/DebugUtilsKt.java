package com.android.permissioncontroller.permission.utils;

import com.android.launcher3.icons.cache.BaseIconCache;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DebugUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001\u001a\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0005\u001a\u0010\u0010\b\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\t"}, d2 = {"permissionsStackTrace", "", "Ljava/lang/StackTraceElement;", "kotlin.jvm.PlatformType", "shortStackTrace", "", "stackTraceWithin", "pkgPrefix", "toShortString", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class DebugUtilsKt {
    public static final String shortStackTrace() {
        return toShortString(permissionsStackTrace());
    }

    public static final List<StackTraceElement> permissionsStackTrace() {
        List<StackTraceElement> listStackTraceWithin = stackTraceWithin("com.android.permissioncontroller");
        if (!listStackTraceWithin.isEmpty()) {
            ListIterator<StackTraceElement> listIterator = listStackTraceWithin.listIterator(listStackTraceWithin.size());
            while (listIterator.hasPrevious()) {
                StackTraceElement it = listIterator.previous();
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                String className = it.getClassName();
                Intrinsics.checkExpressionValueIsNotNull(className, "it.className");
                if (!StringsKt.contains$default((CharSequence) className, (CharSequence) ".DebugUtils", false, 2, (Object) null)) {
                    return CollectionsKt.take(listStackTraceWithin, listIterator.nextIndex() + 1);
                }
            }
        }
        return CollectionsKt.emptyList();
    }

    public static final List<StackTraceElement> stackTraceWithin(String pkgPrefix) {
        Intrinsics.checkParameterIsNotNull(pkgPrefix, "pkgPrefix");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread\n    .currentThread()");
        StackTraceElement[] stackTrace = threadCurrentThread.getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Thread\n    .currentThread()\n    .stackTrace");
        boolean yielding$iv = false;
        ArrayList list$iv = new ArrayList();
        for (StackTraceElement it : stackTrace) {
            if (yielding$iv) {
                list$iv.add(it);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                Intrinsics.checkExpressionValueIsNotNull(it.getClassName(), "it.className");
                if (!(!StringsKt.startsWith$default(r10, pkgPrefix, false, 2, (Object) null))) {
                    list$iv.add(it);
                    yielding$iv = true;
                }
            }
        }
        ArrayList $this$takeWhile$iv = list$iv;
        ArrayList list$iv2 = new ArrayList();
        for (Object item$iv : $this$takeWhile$iv) {
            StackTraceElement it2 = (StackTraceElement) item$iv;
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            String className = it2.getClassName();
            Intrinsics.checkExpressionValueIsNotNull(className, "it.className");
            if (!StringsKt.startsWith$default(className, pkgPrefix, false, 2, (Object) null)) {
                break;
            }
            list$iv2.add(item$iv);
        }
        return list$iv2;
    }

    public static final String toShortString(List<StackTraceElement> toShortString) {
        Intrinsics.checkParameterIsNotNull(toShortString, "$this$toShortString");
        Collections.reverse(toShortString);
        return CollectionsKt.joinToString$default(toShortString, " -> ", null, null, 0, null, new Function1<StackTraceElement, String>() { // from class: com.android.permissioncontroller.permission.utils.DebugUtilsKt.toShortString.1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(StackTraceElement it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                String className = it.getClassName();
                Intrinsics.checkExpressionValueIsNotNull(className, "it.className");
                String fullSimpleClassName = StringsKt.substringAfterLast$default(className, BaseIconCache.EMPTY_CLASS_NAME, (String) null, 2, (Object) null);
                String simpleClassName = StringsKt.substringAfterLast$default(fullSimpleClassName, "$", (String) null, 2, (Object) null);
                if ((simpleClassName.length() > 0) && Character.isDigit(simpleClassName.charAt(0))) {
                    simpleClassName = fullSimpleClassName;
                }
                return simpleClassName + '.' + it.getMethodName() + ':' + it.getLineNumber();
            }
        }, 30, null);
    }
}
