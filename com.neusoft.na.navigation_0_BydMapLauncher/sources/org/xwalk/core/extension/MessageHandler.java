package org.xwalk.core.extension;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.xwalk.core.extension.ReflectionHelper;

/* JADX INFO: loaded from: classes4.dex */
public class MessageHandler {
    private String TAG;
    private Map<String, Handler> mHandlers;

    public class Handler {
        String javaName;
        ReflectionHelper reflection;
        Object targetObject;
        ReflectionHelper.MemberType type;

        public Handler(String str, ReflectionHelper.MemberType memberType, Object obj, ReflectionHelper reflectionHelper) {
            this.type = memberType;
            this.javaName = str;
            this.targetObject = obj;
            this.reflection = reflectionHelper;
        }

        public Handler(MessageHandler messageHandler, String str, ReflectionHelper.MemberType memberType, Object obj) {
            this(str, memberType, obj, null);
        }
    }

    public MessageHandler() {
        this.TAG = "MessageHandler";
        this.mHandlers = new HashMap();
    }

    public MessageHandler(MessageHandler messageHandler) {
        this.TAG = "MessageHandler";
        HashMap map = new HashMap();
        this.mHandlers = map;
        map.putAll(messageHandler.mHandlers);
    }

    public void register(String str, String str2, ReflectionHelper.MemberType memberType, Object obj, ReflectionHelper reflectionHelper) {
        if (this.mHandlers.containsKey(str)) {
            Log.w(this.TAG, "Existing handler for " + str);
        } else {
            this.mHandlers.put(str, new Handler(str2, memberType, obj, reflectionHelper));
        }
    }

    public void register(String str, String str2, ReflectionHelper.MemberType memberType, Object obj) {
        register(str, str2, memberType, obj, null);
    }

    public void register(String str, String str2, Object obj) {
        register(str, str2, ReflectionHelper.MemberType.JS_METHOD, obj, null);
    }

    public void register(String str, Object obj) {
        register(str, str, ReflectionHelper.MemberType.JS_METHOD, obj, null);
    }

    public Object handleMessage(MessageInfo messageInfo) {
        String jsName = messageInfo.getJsName();
        Handler handler = this.mHandlers.get(jsName);
        if (handler == null || handler.targetObject == null) {
            Log.w(this.TAG, "Cannot find handler for method " + jsName);
            return null;
        }
        Object obj = handler.targetObject;
        if (messageInfo.getExtension().isAutoJS() && handler.reflection != null) {
            try {
                return handler.reflection.handleMessage(messageInfo, obj);
            } catch (Exception e) {
                Log.e(this.TAG, e.toString());
                return null;
            }
        }
        try {
            return obj.getClass().getMethod(handler.javaName, MessageInfo.class).invoke(obj, messageInfo);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
            Log.e(this.TAG, e2.toString());
            return null;
        }
    }
}
