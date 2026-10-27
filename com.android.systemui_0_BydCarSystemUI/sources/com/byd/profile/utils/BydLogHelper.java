package com.byd.profile.utils;

import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Constants;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BydLogHelper {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    private static final String DEFAULT_TAG = "Profile";
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final int JSON_INDENT = 2;
    private static final int STACK_DEPTH = 6;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    public static void d(String message) {
        Log.println(3, DEFAULT_TAG, getMessage(message));
    }

    public static void d(String tag, String message) {
        Log.println(3, tag, getMessage(message));
    }

    public static void d(Object object) {
        Log.println(3, DEFAULT_TAG, getMessage(toString(object)));
    }

    public static void d(String message, Object... args) {
        Log.println(3, DEFAULT_TAG, getMessage(joinMessage(message, args)));
    }

    public static void d(String message, Throwable throwable) {
        Log.println(3, DEFAULT_TAG, getMessage(message, throwable));
    }

    public static void e(String message) {
        Log.println(6, DEFAULT_TAG, getMessage(message));
    }

    public static void e(String message, Throwable throwable) {
        Log.println(6, DEFAULT_TAG, getMessage(message, throwable));
    }

    public static void w(String message) {
        Log.println(5, DEFAULT_TAG, getMessage(message));
    }

    public static void i(String message) {
        Log.println(4, DEFAULT_TAG, getMessage(message));
    }

    public static void v(String message) {
        Log.println(2, DEFAULT_TAG, getMessage(message));
    }

    public static void json(String json) {
        if (TextUtils.isEmpty(json)) {
            Log.e(DEFAULT_TAG, "Empty/Null json content");
            return;
        }
        try {
            String strTrim = json.trim();
            if (strTrim.startsWith("{")) {
                Log.println(3, DEFAULT_TAG, getMessage(new JSONObject(strTrim).toString(2)));
            } else if (strTrim.startsWith("[")) {
                Log.println(3, DEFAULT_TAG, getMessage(new JSONArray(strTrim).toString(2)));
            } else {
                Log.e(DEFAULT_TAG, "Invalid Json");
            }
        } catch (JSONException e) {
            Log.e(DEFAULT_TAG, "Invalid Json", e);
        }
    }

    public static void xml(String xml) {
        if (TextUtils.isEmpty(xml)) {
            Log.e(DEFAULT_TAG, "Empty/Null xml content");
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(xml));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("indent", "yes");
            transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformerNewTransformer.transform(streamSource, streamResult);
            Log.println(3, DEFAULT_TAG, getMessage(streamResult.getWriter().toString().replaceFirst(">", ">\n")));
        } catch (TransformerException unused) {
            Log.e(DEFAULT_TAG, "Invalid xml");
        }
    }

    private static String getMessage(String message) {
        return getMessage(message, null);
    }

    private static String getMessage(String message, Throwable throwable) {
        if (TextUtils.isEmpty(message)) {
            message = "NULL";
        }
        if (throwable == null) {
            return message + getStackInfo();
        }
        return message + BydAdbHelper.COMMAND_LINE_END + Log.getStackTraceString(throwable);
    }

    private static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (!object.getClass().isArray()) {
            return object.toString();
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        return object instanceof Object[] ? Arrays.deepToString((Object[]) object) : "Couldn't find a correct type for the object";
    }

    private static String joinMessage(String message, Object... args) {
        if (args == null || args.length == 0) {
            return message;
        }
        StringBuilder sbAppend = new StringBuilder(message).append(" : ");
        for (int i = 0; i < args.length; i++) {
            sbAppend.append("arg").append(i).append(" = ").append(toString(args[i]));
            if (i < args.length - 1) {
                sbAppend.append(", ");
            }
        }
        return sbAppend.toString();
    }

    private static String getStackInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return " --- [" + getSimpleClassName(stackTrace[6].getClassName()) + Constants.CHAR_DOT + stackTrace[6].getMethodName() + "() line:" + stackTrace[6].getLineNumber() + "]";
    }

    private static String getSimpleClassName(String name) {
        return name.substring(name.lastIndexOf(Constants.CHAR_DOT) + 1);
    }
}
