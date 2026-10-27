package com.byd.automultipletheme.common;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: LogUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0019\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dJ#\u0010\u001e\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dJ#\u0010\u001f\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dJ\u0010\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0001H\u0002J\u0012\u0010\"\u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u0002J\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020%H\u0002J#\u0010&\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dJ/\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00062\u0010\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001cH\u0002¢\u0006\u0002\u0010*J \u0010+\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006H\u0002J\u001f\u0010-\u001a\u00020\u00062\u0010\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001cH\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002J\b\u00103\u001a\u00020\u0006H\u0002J#\u00104\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dJ#\u00105\u001a\u00020\u001a2\u0016\u0010\u001b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u001c\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \f*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/byd/automultipletheme/common/LogUtils;", "", "()V", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "", "BOTTOM_BORDER", "", "BOTTOM_CORNER", "D", "DEFAULT_TAG", ExifInterface.LONGITUDE_EAST, "FILE_SEP", "kotlin.jvm.PlatformType", "I", "LEFT_BORDER", "LINE_SEP", "MIDDLE_BORDER", "MIDDLE_CORNER", "MIDDLE_DIVIDER", "SIDE_DIVIDER", "STACK_INDEX", "TOP_BORDER", "TOP_CORNER", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "a", "", "contents", "", "([Ljava/lang/Object;)V", "d", "e", "formatArray", "content", "formatObject", "getFileName", "targetElement", "Ljava/lang/StackTraceElement;", "i", "l", IntentString.DATA_TYPE, "tag", "(ILjava/lang/String;[Ljava/lang/Object;)V", "printMessage", "message", "processBody", "([Ljava/lang/Object;)Ljava/lang/String;", "processBorder", "isTop", "", "processTagAndHead", "processTitle", "v", "w", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LogUtils {
    private static final int A = 7;
    private static final String BOTTOM_BORDER = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String BOTTOM_CORNER = "└";
    private static final int D = 3;
    private static final String DEFAULT_TAG = "BydAutoTheme";
    private static final int E = 6;
    private static final int I = 4;
    private static final String LEFT_BORDER = "│ ";
    private static final String MIDDLE_BORDER = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MIDDLE_CORNER = "├";
    private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final int STACK_INDEX = 3;
    private static final String TOP_BORDER = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String TOP_CORNER = "┌";
    private static final int V = 2;
    private static final int W = 5;
    public static final LogUtils INSTANCE = new LogUtils();
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");

    private LogUtils() {
    }

    public final void v(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(2, DEFAULT_TAG, contents);
    }

    public final void d(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(3, DEFAULT_TAG, contents);
    }

    public final void i(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(4, DEFAULT_TAG, contents);
    }

    public final void w(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(5, DEFAULT_TAG, contents);
    }

    public final void e(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(6, DEFAULT_TAG, contents);
    }

    public final void a(Object... contents) {
        Intrinsics.checkNotNullParameter(contents, "contents");
        l(7, DEFAULT_TAG, contents);
    }

    private final void l(int type, String tag, Object[] contents) {
        printMessage(type, tag, processTitle() + processBorder(true) + processTagAndHead(tag) + processBody(contents) + processBorder(false));
    }

    private final String processTitle() {
        StackTraceElement targetElement = new Throwable().getStackTrace()[3];
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNullExpressionValue(targetElement, "targetElement");
        sb.append(getFileName(targetElement));
        sb.append(LINE_SEP);
        return sb.toString();
    }

    private final String processBorder(boolean isTop) {
        if (!isTop) {
            return BOTTOM_BORDER;
        }
        return TOP_BORDER + LINE_SEP;
    }

    private final String processBody(Object[] contents) {
        StringBuilder sb = new StringBuilder();
        int length = contents.length;
        int length2 = contents.length;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            Object obj = contents[i];
            int i3 = i2 + 1;
            sb.append(MIDDLE_CORNER);
            sb.append("args");
            sb.append("[");
            sb.append(i2);
            sb.append("]");
            sb.append(" = ");
            sb.append(INSTANCE.formatObject(obj));
            if (i2 != length - 1) {
                sb.append(LINE_SEP);
            }
            i++;
            i2 = i3;
        }
        return sb.toString() + LINE_SEP;
    }

    private final String formatObject(Object content) {
        if (content == null) {
            return "";
        }
        if (content instanceof String) {
            return (String) content;
        }
        if (content.getClass().isArray()) {
            return INSTANCE.formatArray(content);
        }
        return content.toString();
    }

    private final String formatArray(Object content) {
        if (content instanceof Object[]) {
            return ArraysKt.contentDeepToString((Object[]) content);
        }
        if (content instanceof boolean[]) {
            String string = Arrays.toString((boolean[]) content);
            Intrinsics.checkNotNullExpressionValue(string, "toString(this)");
            return string;
        }
        if (content instanceof byte[]) {
            String string2 = Arrays.toString((byte[]) content);
            Intrinsics.checkNotNullExpressionValue(string2, "toString(this)");
            return string2;
        }
        if (content instanceof char[]) {
            String string3 = Arrays.toString((char[]) content);
            Intrinsics.checkNotNullExpressionValue(string3, "toString(this)");
            return string3;
        }
        if (content instanceof double[]) {
            String string4 = Arrays.toString((double[]) content);
            Intrinsics.checkNotNullExpressionValue(string4, "toString(this)");
            return string4;
        }
        if (content instanceof float[]) {
            String string5 = Arrays.toString((float[]) content);
            Intrinsics.checkNotNullExpressionValue(string5, "toString(this)");
            return string5;
        }
        if (content instanceof int[]) {
            String string6 = Arrays.toString((int[]) content);
            Intrinsics.checkNotNullExpressionValue(string6, "toString(this)");
            return string6;
        }
        if (content instanceof long[]) {
            String string7 = Arrays.toString((long[]) content);
            Intrinsics.checkNotNullExpressionValue(string7, "toString(this)");
            return string7;
        }
        if (content instanceof short[]) {
            String string8 = Arrays.toString((short[]) content);
            Intrinsics.checkNotNullExpressionValue(string8, "toString(this)");
            return string8;
        }
        throw new IllegalArgumentException("Array has incompatible type: " + content.getClass());
    }

    private final String processTagAndHead(String tag) {
        StackTraceElement targetElement = new Throwable().getStackTrace()[3];
        Intrinsics.checkNotNullExpressionValue(targetElement, "targetElement");
        String fileName = getFileName(targetElement);
        String string = new Formatter().format("%s, %s.%s(%s:%d)", Thread.currentThread().getName(), targetElement.getClassName(), targetElement.getMethodName(), fileName, Integer.valueOf(targetElement.getLineNumber())).toString();
        Intrinsics.checkNotNullExpressionValue(string, "Formatter()\n            …)\n            .toString()");
        StringBuilder sb = new StringBuilder();
        sb.append((char) 9500);
        sb.append(string);
        String str = LINE_SEP;
        sb.append(str);
        sb.append(MIDDLE_BORDER);
        sb.append(str);
        return sb.toString();
    }

    private final void printMessage(int type, String tag, String message) {
        Log.println(type, tag, message);
    }

    private final String getFileName(StackTraceElement targetElement) {
        String fileName = targetElement.getFileName();
        if (fileName != null) {
            return fileName;
        }
        String className = targetElement.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "className");
        Object[] array = StringsKt.split$default((CharSequence) className, new String[]{"\\."}, false, 0, 6, (Object) null).toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        if (!(strArr.length == 0)) {
            className = strArr[strArr.length - 1];
        }
        Intrinsics.checkNotNullExpressionValue(className, "className");
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) className, Typography.dollar, 0, false, 6, (Object) null);
        if (iIndexOf$default != -1) {
            Intrinsics.checkNotNullExpressionValue(className, "className");
            className = className.substring(0, iIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(className, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        return className + ".java";
    }
}
