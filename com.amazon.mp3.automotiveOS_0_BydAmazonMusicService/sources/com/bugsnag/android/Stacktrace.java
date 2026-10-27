package com.bugsnag.android;

import com.amazon.alexa.comms.mediaInsights.common.TracePublisherServiceMessageType;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: Stacktrace.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0015\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005B+\b\u0016\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002¢\u0006\u0002\u0010\u0013J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J(\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/bugsnag/android/Stacktrace;", "Lcom/bugsnag/android/JsonStream$Streamable;", "frames", "", "Lcom/bugsnag/android/Stackframe;", "(Ljava/util/List;)V", "stacktrace", "", "Ljava/lang/StackTraceElement;", "projectPackages", "", "", "logger", "Lcom/bugsnag/android/Logger;", "([Ljava/lang/StackTraceElement;Ljava/util/Collection;Lcom/bugsnag/android/Logger;)V", TracePublisherServiceMessageType.TRACE_MESSAGE_KEY, "getTrace", "()Ljava/util/List;", "limitTraceLength", "([Ljava/lang/StackTraceElement;)[Ljava/lang/StackTraceElement;", "serializeStackframe", "el", "toStream", "", "writer", "Lcom/bugsnag/android/JsonStream;", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class Stacktrace implements JsonStream.Streamable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<Stackframe> trace;

    /* JADX INFO: compiled from: Stacktrace.kt */
    @kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/bugsnag/android/Stacktrace$Companion;", "", "()V", "STACKTRACE_TRIM_LENGTH", "", "inProject", "", "className", "", "projectPackages", "", "(Ljava/lang/String;Ljava/util/Collection;)Ljava/lang/Boolean;", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Boolean inProject(String className, Collection<String> projectPackages) {
            boolean z = false;
            if (!projectPackages.isEmpty()) {
                Iterator<T> it = projectPackages.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (StringsKt__StringsJVMKt.startsWith$default(className, (String) it.next(), false, 2, null)) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                return Boolean.TRUE;
            }
            return null;
        }
    }

    public final List<Stackframe> getTrace() {
        return this.trace;
    }

    public Stacktrace(List<Stackframe> list) {
        this.trace = limitTraceLength(list);
    }

    public Stacktrace(StackTraceElement[] stackTraceElementArr, Collection<String> collection, Logger logger) {
        StackTraceElement[] stackTraceElementArrLimitTraceLength = limitTraceLength(stackTraceElementArr);
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : stackTraceElementArrLimitTraceLength) {
            Stackframe stackframeSerializeStackframe = serializeStackframe(stackTraceElement, collection, logger);
            if (stackframeSerializeStackframe != null) {
                arrayList.add(stackframeSerializeStackframe);
            }
        }
        this.trace = arrayList;
    }

    private final StackTraceElement[] limitTraceLength(StackTraceElement[] frames) {
        return frames.length >= 200 ? (StackTraceElement[]) ArraysKt___ArraysKt.sliceArray(frames, RangesKt___RangesKt.until(0, 200)) : frames;
    }

    private final List<Stackframe> limitTraceLength(List<Stackframe> frames) {
        return frames.size() >= 200 ? frames.subList(0, 200) : frames;
    }

    private final Stackframe serializeStackframe(StackTraceElement el, Collection<String> projectPackages, Logger logger) {
        String methodName;
        try {
            String className = el.getClassName();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(className, "className");
            if (className.length() > 0) {
                methodName = className + "." + el.getMethodName();
            } else {
                methodName = el.getMethodName();
            }
            String str = methodName;
            String fileName = el.getFileName();
            if (fileName == null) {
                fileName = "Unknown";
            }
            return new Stackframe(str, fileName, Integer.valueOf(el.getLineNumber()), INSTANCE.inProject(className, projectPackages), null, null, 48, null);
        } catch (Exception e) {
            logger.w("Failed to serialize stacktrace", e);
            return null;
        }
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginArray();
        Iterator<T> it = this.trace.iterator();
        while (it.hasNext()) {
            writer.value((Stackframe) it.next());
        }
        writer.endArray();
    }
}
