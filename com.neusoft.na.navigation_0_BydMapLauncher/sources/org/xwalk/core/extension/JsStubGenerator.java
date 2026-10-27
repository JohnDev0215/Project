package org.xwalk.core.extension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import org.xwalk.core.extension.ReflectionHelper;

/* JADX INFO: loaded from: classes4.dex */
public class JsStubGenerator {
    public static final String MSG_TO_CLASS = "postMessageToClass";
    public static final String MSG_TO_EXTENSION = "postMessageToExtension";
    public static final String MSG_TO_OBJECT = "postMessageToObject";
    public static String TAG = "JsStubGenerator";
    String jsHeader = "var v8tools = requireNative(\"v8tools\");\nvar jsStubModule = requireNative(\"jsStub\");\njsStubModule.init(extension, v8tools);\nvar jsStub = jsStubModule.jsStub;\nvar helper = jsStub.createRootStub(exports);\n";
    ReflectionHelper reflection;

    JsStubGenerator(ReflectionHelper reflectionHelper) {
        this.reflection = reflectionHelper;
    }

    String generate() {
        ReflectionHelper.MemberInfo entryPoint = this.reflection.getEntryPoint();
        String strGenerateEntryPoint = entryPoint != null ? generateEntryPoint(entryPoint) : "";
        if (strGenerateEntryPoint.length() <= 0) {
            strGenerateEntryPoint = this.jsHeader;
        }
        if (this.reflection.getEventList() != null) {
            strGenerateEntryPoint = strGenerateEntryPoint + generateEventTarget(this.reflection);
        }
        Map<String, ReflectionHelper.MemberInfo> members = this.reflection.getMembers();
        Iterator<String> it = members.keySet().iterator();
        while (it.hasNext()) {
            ReflectionHelper.MemberInfo memberInfo = members.get(it.next());
            if (!memberInfo.isEntryPoint) {
                int i = AnonymousClass1.$SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType[memberInfo.type.ordinal()];
                if (i == 1) {
                    strGenerateEntryPoint = strGenerateEntryPoint + generateProperty(MSG_TO_EXTENSION, memberInfo);
                } else if (i == 2) {
                    strGenerateEntryPoint = strGenerateEntryPoint + generateMethod(MSG_TO_EXTENSION, memberInfo, true);
                } else if (i == 3) {
                    strGenerateEntryPoint = strGenerateEntryPoint + generateConstructor(memberInfo, true);
                }
            }
        }
        return strGenerateEntryPoint + "\n";
    }

    /* JADX INFO: renamed from: org.xwalk.core.extension.JsStubGenerator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType;

        static {
            int[] iArr = new int[ReflectionHelper.MemberType.values().length];
            $SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType = iArr;
            try {
                iArr[ReflectionHelper.MemberType.JS_PROPERTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType[ReflectionHelper.MemberType.JS_METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType[ReflectionHelper.MemberType.JS_CONSTRUCTOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    String generateEntryPoint(ReflectionHelper.MemberInfo memberInfo) {
        if (memberInfo.type == ReflectionHelper.MemberType.JS_PROPERTY) {
            return this.jsHeader + String.format("%s(exports, helper);\n", getPrototypeName(((Field) memberInfo.accesser).getType().getSimpleName()));
        }
        return memberInfo.type == ReflectionHelper.MemberType.JS_METHOD ? String.format("exports = %s;\n %s\n %s", getInternalName(memberInfo.jsName), this.jsHeader, generateMethod(MSG_TO_EXTENSION, memberInfo, false)) : memberInfo.type == ReflectionHelper.MemberType.JS_CONSTRUCTOR ? String.format("exports = %s;\n %s\n %s", memberInfo.jsName, this.jsHeader, generateConstructor(memberInfo, false)) : "";
    }

    String[] classGenerator(ReflectionHelper reflectionHelper) {
        String str;
        String str2;
        String strGenerateProperty;
        if (reflectionHelper.getEventList() != null) {
            String strGenerateEventTarget = generateEventTarget(reflectionHelper);
            str2 = "" + strGenerateEventTarget;
            str = "" + strGenerateEventTarget;
        } else {
            str = "";
            str2 = str;
        }
        Map<String, ReflectionHelper.MemberInfo> members = reflectionHelper.getMembers();
        Iterator<String> it = members.keySet().iterator();
        while (it.hasNext()) {
            ReflectionHelper.MemberInfo memberInfo = members.get(it.next());
            String str3 = memberInfo.isStatic ? MSG_TO_CLASS : MSG_TO_OBJECT;
            int i = AnonymousClass1.$SwitchMap$org$xwalk$core$extension$ReflectionHelper$MemberType[memberInfo.type.ordinal()];
            if (i == 1) {
                strGenerateProperty = generateProperty(str3, memberInfo);
            } else {
                strGenerateProperty = i != 2 ? "" : generateMethod(str3, memberInfo, true);
            }
            if (memberInfo.isStatic) {
                str = str + strGenerateProperty;
            } else {
                str2 = str2 + strGenerateProperty;
            }
        }
        return new String[]{str2, str};
    }

    String destroyBindingObject(ReflectionHelper reflectionHelper) {
        Iterator<String> it = reflectionHelper.getMembers().keySet().iterator();
        String str = "exports.destroy = function() {\n";
        while (it.hasNext()) {
            str = str + "delete exports[\"" + it.next() + "\"];\n";
        }
        return (((str + "helper.destroy();\n") + "delete exports[\"__stubHelper\"];\n") + "delete exports[\"destroy\"];\n") + "};";
    }

    String generateEventTarget(ReflectionHelper reflectionHelper) {
        String[] eventList = reflectionHelper.getEventList();
        if (eventList == null || eventList.length == 0) {
            return "";
        }
        String str = "jsStub.makeEventTarget(exports);\n";
        for (String str2 : eventList) {
            str = str + "helper.addEvent(\"" + str2 + "\");\n";
        }
        return str;
    }

    String generateProperty(String str, ReflectionHelper.MemberInfo memberInfo) {
        return String.format("jsStub.defineProperty(\"%s\", exports, \"%s\", %b);\n", str, memberInfo.jsName, Boolean.valueOf(memberInfo.isWritable));
    }

    String generatePromiseMethod(String str, ReflectionHelper.MemberInfo memberInfo) {
        return String.format("jsStub.addMethodWithPromise(\"%s\", exports, \"%s\", %s, %s);\n", str, memberInfo.jsName, memberInfo.wrapArgs.length() > 0 ? memberInfo.wrapArgs : "null", memberInfo.wrapReturns.length() > 0 ? memberInfo.wrapReturns : "null");
    }

    String getArgString(Method method, boolean z) {
        String str = "";
        if (method == null) {
            return "";
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        method.getParameterAnnotations();
        int length = parameterTypes.length;
        if (z) {
            length--;
        }
        for (int i = 0; i < length; i++) {
            String str2 = "arg" + i + "_" + parameterTypes[i].getSimpleName();
            if (str.length() > 0) {
                str = str + ", ";
            }
            str = str + str2;
        }
        return str;
    }

    String generateMethod(String str, ReflectionHelper.MemberInfo memberInfo, boolean z) {
        if (memberInfo.withPromise) {
            return generatePromiseMethod(str, memberInfo);
        }
        String str2 = memberInfo.jsName;
        Method method = (Method) memberInfo.accesser;
        String internalName = getInternalName(str2);
        method.getParameterAnnotations();
        String argString = getArgString(method, memberInfo.withPromise);
        boolean z2 = !method.getReturnType().equals(Void.TYPE);
        return String.format("function %s(%s) {\n" + (z2 ? "  return " : "  ") + "helper.invokeNative(\"%s\", \"%s\", [%s], %b);\n};\n", internalName, argString, str, str2, argString, Boolean.valueOf(z2)) + (z ? String.format("exports[\"%s\"] = %s;\n", str2, internalName) : "");
    }

    String getInternalName(String str) {
        return "__" + str;
    }

    String getPrototypeName(String str) {
        return "__" + str + "_prototype";
    }

    String generateConstructor(ReflectionHelper.MemberInfo memberInfo, boolean z) {
        String str = memberInfo.jsName;
        String prototypeName = getPrototypeName(str);
        String argString = getArgString((Method) memberInfo.accesser, false);
        ReflectionHelper constructorReflection = this.reflection.getConstructorReflection(str);
        String[] strArrClassGenerator = classGenerator(constructorReflection);
        return String.format("function %s(exports, helper){\n%s\n%s\n}\n", prototypeName, strArrClassGenerator[0], destroyBindingObject(constructorReflection)) + String.format("function %s(%s) {\nvar newObject = this;\nvar objectId =\nNumber(helper.invokeNative(\"%s\", \"+%s\", [%s], true));\nif (!objectId) throw \"Error to create instance for constructor:%s.\";\nvar objectHelper = jsStub.getHelper(newObject, helper);\nobjectHelper.objectId = objectId;\nobjectHelper.constructorJsName = \"%s\";\nobjectHelper.registerLifecycleTracker();%s(newObject, objectHelper);\nhelper.addBindingObject(objectId, newObject);}\nhelper.constructors[\"%s\"] = %s;\n", str, argString, MSG_TO_EXTENSION, str, argString, str, str, prototypeName, str, str) + String.format("(function(exports, helper){\n  helper.constructorJsName = \"%s\";\n%s\n})(%s, jsStub.getHelper(%s, helper));\n", str, strArrClassGenerator[1], str, str) + (z ? String.format("exports[\"%s\"] = %s;\n", str, str) : "");
    }
}
