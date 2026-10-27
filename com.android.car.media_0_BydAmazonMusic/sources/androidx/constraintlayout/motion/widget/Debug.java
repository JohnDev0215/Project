package androidx.constraintlayout.motion.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.library.BuildConfig;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.CharBuffer;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"LogConditional"})
public class Debug {
    public static void dumpLayoutParams(ViewGroup viewGroup, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(") ");
        sbK.append(str);
        sbK.append("  ");
        String string = sbK.toString();
        int childCount = viewGroup.getChildCount();
        System.out.println(str + " children " + childCount);
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            PrintStream printStream = System.out;
            StringBuilder sbN = c.n(string, "     ");
            sbN.append(getName(childAt));
            printStream.println(sbN.toString());
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            for (Field field : layoutParams.getClass().getFields()) {
                try {
                    Object obj = field.get(layoutParams);
                    if (field.getName().contains("To") && !obj.toString().equals("-1")) {
                        System.out.println(string + "       " + field.getName() + " " + obj);
                    }
                } catch (IllegalAccessException unused) {
                }
            }
        }
    }

    public static void dumpPoc(Object obj) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(")");
        String string = sbK.toString();
        Class<?> cls = obj.getClass();
        PrintStream printStream = System.out;
        StringBuilder sbN = c.n(string, "------------- ");
        sbN.append(cls.getName());
        sbN.append(" --------------------");
        printStream.println(sbN.toString());
        for (Field field : cls.getFields()) {
            try {
                Object obj2 = field.get(obj);
                if (field.getName().startsWith("layout_constraint") && ((!(obj2 instanceof Integer) || !obj2.toString().equals("-1")) && ((!(obj2 instanceof Integer) || !obj2.toString().equals("0")) && ((!(obj2 instanceof Float) || !obj2.toString().equals(BuildConfig.VERSION_NAME)) && (!(obj2 instanceof Float) || !obj2.toString().equals("0.5")))))) {
                    System.out.println(string + "    " + field.getName() + " " + obj2);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        PrintStream printStream2 = System.out;
        StringBuilder sbN2 = c.n(string, "------------- ");
        sbN2.append(cls.getSimpleName());
        sbN2.append(" --------------------");
        printStream2.println(sbN2.toString());
    }

    public static String getActionType(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        for (Field field : MotionEvent.class.getFields()) {
            try {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Integer.TYPE) && field.getInt(null) == action) {
                    return field.getName();
                }
            } catch (IllegalAccessException unused) {
            }
        }
        return "---";
    }

    public static String getCallFrom(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i + 2];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(")");
        return sbK.toString();
    }

    public static String getLoc() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(") ");
        sbK.append(stackTraceElement.getMethodName());
        sbK.append("()");
        return sbK.toString();
    }

    public static String getLocation() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(")");
        return sbK.toString();
    }

    public static String getLocation2() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(")");
        return sbK.toString();
    }

    public static String getName(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static String getState(MotionLayout motionLayout, int i) {
        return getState(motionLayout, i, -1);
    }

    public static void logStack(String str, String str2, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i, stackTrace.length - 1);
        String strF = " ";
        for (int i2 = 1; i2 <= iMin; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            StringBuilder sbK = c.k(".(");
            sbK.append(stackTrace[i2].getFileName());
            sbK.append(":");
            sbK.append(stackTrace[i2].getLineNumber());
            sbK.append(") ");
            sbK.append(stackTrace[i2].getMethodName());
            String string = sbK.toString();
            strF = c.f(strF, " ");
            Log.v(str, str2 + strF + string + strF);
        }
    }

    public static void printStack(String str, int i) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int iMin = Math.min(i, stackTrace.length - 1);
        String strF = " ";
        for (int i2 = 1; i2 <= iMin; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            StringBuilder sbK = c.k(".(");
            sbK.append(stackTrace[i2].getFileName());
            sbK.append(":");
            sbK.append(stackTrace[i2].getLineNumber());
            sbK.append(") ");
            String string = sbK.toString();
            strF = c.f(strF, " ");
            System.out.println(str + strF + string + strF);
        }
    }

    public static String getState(MotionLayout motionLayout, int i, int i2) {
        int length;
        if (i == -1) {
            return "UNDEFINED";
        }
        String resourceEntryName = motionLayout.getContext().getResources().getResourceEntryName(i);
        if (i2 == -1) {
            return resourceEntryName;
        }
        if (resourceEntryName.length() > i2) {
            resourceEntryName = resourceEntryName.replaceAll("([^_])[aeiou]+", "$1");
        }
        if (resourceEntryName.length() <= i2 || (length = resourceEntryName.replaceAll("[^_]", "").length()) <= 0) {
            return resourceEntryName;
        }
        return resourceEntryName.replaceAll(CharBuffer.allocate((resourceEntryName.length() - i2) / length).toString().replace((char) 0, '.') + "_", "_");
    }

    public static String getName(Context context, int i) {
        if (i == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return c.d("?", i);
        }
    }

    public static String getName(Context context, int[] iArr) {
        String resourceEntryName;
        try {
            String str = iArr.length + "[";
            int i = 0;
            while (i < iArr.length) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(i == 0 ? "" : " ");
                String string = sb.toString();
                try {
                    resourceEntryName = context.getResources().getResourceEntryName(iArr[i]);
                } catch (Resources.NotFoundException unused) {
                    resourceEntryName = "? " + iArr[i] + " ";
                }
                str = string + resourceEntryName;
                i++;
            }
            return str + "]";
        } catch (Exception e) {
            Log.v("DEBUG", e.toString());
            return "UNKNOWN";
        }
    }

    public static void dumpLayoutParams(ViewGroup.LayoutParams layoutParams, String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(") ");
        sbK.append(str);
        sbK.append("  ");
        String string = sbK.toString();
        PrintStream printStream = System.out;
        StringBuilder sbO = c.o(" >>>>>>>>>>>>>>>>>>. dump ", string, "  ");
        sbO.append(layoutParams.getClass().getName());
        printStream.println(sbO.toString());
        for (Field field : layoutParams.getClass().getFields()) {
            try {
                Object obj = field.get(layoutParams);
                String name = field.getName();
                if (name.contains("To") && !obj.toString().equals("-1")) {
                    System.out.println(string + "       " + name + " " + obj);
                }
            } catch (IllegalAccessException unused) {
            }
        }
        System.out.println(" <<<<<<<<<<<<<<<<< dump " + string);
    }
}
