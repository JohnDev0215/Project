package org.xwalk.core.extension;

import android.util.Log;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
class ReflectionHelper {
    private static final String TAG = "JsStubReflectHelper";
    static Set<Class<?>> primitives = new HashSet();
    private Class<?> myClass;
    private Map<String, MemberInfo> members = new HashMap();
    private Map<String, String> bindingClasses = new HashMap();
    private Map<String, ReflectionHelper> constructorReflections = new HashMap();
    private String[] eventList = null;
    private MemberInfo entryPoint = null;

    public enum MemberType {
        JS_METHOD,
        JS_PROPERTY,
        JS_CONSTRUCTOR
    }

    public class MemberInfo {
        AccessibleObject accesser;
        boolean isEntryPoint;
        boolean isStatic;
        boolean isWritable;
        String javaName;
        String jsName;
        Class<?> mainClass;
        MemberType type;
        boolean withPromise;
        String wrapArgs = "";
        String wrapReturns = "";

        public MemberInfo() {
        }
    }

    public ReflectionHelper(Class<?> cls) {
        this.myClass = cls;
        init();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void getMemberInfo(java.lang.reflect.AccessibleObject[] r11, org.xwalk.core.extension.ReflectionHelper.MemberType r12) {
        /*
            Method dump skipped, instruction units count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.extension.ReflectionHelper.getMemberInfo(java.lang.reflect.AccessibleObject[], org.xwalk.core.extension.ReflectionHelper$MemberType):void");
    }

    boolean isBindingClass(Class<?> cls) {
        return BindingObject.class.isAssignableFrom(cls);
    }

    void init() {
        primitives.add(Byte.class);
        primitives.add(Integer.class);
        primitives.add(Long.class);
        primitives.add(Double.class);
        primitives.add(Character.class);
        primitives.add(Float.class);
        primitives.add(Boolean.class);
        primitives.add(Short.class);
        getMemberInfo(this.myClass.getDeclaredMethods(), MemberType.JS_METHOD);
        getMemberInfo(this.myClass.getDeclaredFields(), MemberType.JS_PROPERTY);
    }

    public static void registerHandlers(ReflectionHelper reflectionHelper, MessageHandler messageHandler, Object obj) {
        if (reflectionHelper == null || messageHandler == null) {
            return;
        }
        Iterator<String> it = reflectionHelper.getMembers().keySet().iterator();
        while (it.hasNext()) {
            MemberInfo memberInfo = reflectionHelper.getMembers().get(it.next());
            messageHandler.register(memberInfo.jsName, memberInfo.javaName, memberInfo.type, obj, reflectionHelper);
        }
    }

    Map<String, MemberInfo> getMembers() {
        return this.members;
    }

    ReflectionHelper getConstructorReflection(String str) {
        if (this.constructorReflections.containsKey(str)) {
            return this.constructorReflections.get(str);
        }
        return null;
    }

    ReflectionHelper getReflectionByBindingClass(String str) {
        if (this.bindingClasses.containsKey(str)) {
            return getConstructorReflection(this.bindingClasses.get(str));
        }
        return null;
    }

    Boolean hasMethod(String str) {
        if (!this.members.containsKey(str)) {
            return false;
        }
        MemberInfo memberInfo = this.members.get(str);
        return Boolean.valueOf(memberInfo.type == MemberType.JS_METHOD || memberInfo.type == MemberType.JS_CONSTRUCTOR);
    }

    Boolean hasProperty(String str) {
        if (this.members.containsKey(str)) {
            return Boolean.valueOf(this.members.get(str).type == MemberType.JS_PROPERTY);
        }
        return false;
    }

    MemberInfo getMemberInfo(String str) {
        return this.members.get(str);
    }

    Object[] getArgsFromJson(XWalkExternalExtension xWalkExternalExtension, int i, Method method, JSONArray jSONArray) {
        int i2;
        Exception e;
        Class<?> cls;
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        boolean zIsStatic = Modifier.isStatic(method.getModifiers());
        int i3 = 0;
        while (i3 < parameterTypes.length) {
            try {
                cls = parameterTypes[i3];
            } catch (Exception e2) {
                i2 = i3;
                e = e2;
            }
            if (zIsStatic && cls.equals(JsContextInfo.class)) {
                i2 = i3 + 1;
                try {
                    objArr[i3] = new JsContextInfo(i, xWalkExternalExtension, method.getClass(), Integer.toString(0));
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                }
                i3 = i2;
                i3++;
            } else {
                objArr[i3] = jSONArray.get(i3);
                i3++;
            }
        }
        return objArr;
    }

    public static boolean isSerializable(Object obj) {
        Class<?> cls = obj.getClass();
        return cls.isPrimitive() || primitives.contains(cls) || (obj instanceof String) || (obj instanceof Map) || (obj instanceof JSONArray) || (obj instanceof JSONObject);
    }

    public static Object toSerializableObject(Object obj) {
        int i = 0;
        if (obj.getClass().isArray()) {
            JSONArray jSONArray = new JSONArray();
            Object[] objArr = (Object[]) obj;
            while (i < objArr.length) {
                try {
                    jSONArray.put(i, toSerializableObject(objArr[i]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
            return jSONArray;
        }
        if (isSerializable(obj)) {
            return obj;
        }
        try {
            String str = (String) obj.getClass().getMethod("toJSONString", new Class[0]).invoke(obj, new Object[0]);
            if (str.trim().charAt(0) == '[') {
                return new JSONArray(str);
            }
            return new JSONObject(str);
        } catch (Exception unused) {
            Log.w(TAG, "No serialization method: \"toJSONString\", or errors happened.");
            try {
                Class<?> cls = obj.getClass();
                JSONObject jSONObject = new JSONObject();
                Field[] fields = cls.getFields();
                int length = fields.length;
                while (i < length) {
                    Field field = fields[i];
                    jSONObject.put(field.getName(), field.get(obj));
                    i++;
                }
                return jSONObject;
            } catch (Exception e2) {
                Log.e(TAG, "Field to serialize object to JSON.");
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static String objToJSON(Object obj) {
        if (obj == null) {
            return "null";
        }
        Object serializableObject = toSerializableObject(obj);
        boolean z = serializableObject instanceof String;
        String string = serializableObject.toString();
        return z ? JSONObject.quote(string) : string;
    }

    Object invokeMethod(XWalkExternalExtension xWalkExternalExtension, int i, Object obj, String str, JSONArray jSONArray) throws Exception {
        if (!hasMethod(str).booleanValue()) {
            throw new NoSuchMethodException("No such method:" + str);
        }
        if (!getMemberInfo(str).isStatic && !this.myClass.isInstance(obj)) {
            throw new InvocationTargetException(new Exception("Invalid target to set property:" + str));
        }
        Method method = (Method) this.members.get(str).accesser;
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method.invoke(obj, getArgsFromJson(xWalkExternalExtension, i, method, jSONArray));
    }

    Object getProperty(Object obj, String str) throws Exception {
        if (!hasProperty(str).booleanValue()) {
            throw new NoSuchFieldException("No such property:" + str);
        }
        if (!getMemberInfo(str).isStatic && !this.myClass.isInstance(obj)) {
            throw new InvocationTargetException(new Exception("Invalid target to set property:" + str));
        }
        Field field = (Field) this.members.get(str).accesser;
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    void setProperty(Object obj, String str, Object obj2) throws Exception {
        if (!hasProperty(str).booleanValue()) {
            throw new NoSuchFieldException("No such property:" + str);
        }
        if (!getMemberInfo(str).isStatic && !this.myClass.isInstance(obj)) {
            throw new InvocationTargetException(new Exception("Invalid target to set property:" + str));
        }
        Field field = (Field) this.members.get(str).accesser;
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    String[] getEventList() {
        return this.eventList;
    }

    MemberInfo getEntryPoint() {
        return this.entryPoint;
    }

    boolean isEventSupported(String str) {
        if (this.eventList == null) {
            return false;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.eventList;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    boolean isInstance(Object obj) {
        return this.myClass.isInstance(obj);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object handleMessage(org.xwalk.core.extension.MessageInfo r14, java.lang.Object r15) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.extension.ReflectionHelper.handleMessage(org.xwalk.core.extension.MessageInfo, java.lang.Object):java.lang.Object");
    }
}
