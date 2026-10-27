package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.annotations.DELETE;
import com.spotify.cosmos.cosmonaut.annotations.GET;
import com.spotify.cosmos.cosmonaut.annotations.POST;
import com.spotify.cosmos.cosmonaut.annotations.PUT;
import com.spotify.cosmos.cosmonaut.annotations.Path;
import com.spotify.cosmos.cosmonaut.annotations.SUB;
import com.spotify.cosmos.cosmonautatoms.ReflectionUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.or2;
import p.u4;
import p.us1;
import p.v03;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class PathAtom {
    public static final Companion Companion = new Companion(null);
    private final List<IndexedAnnotation<Path>> namedPathIndex;
    private final String uri;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<IndexedAnnotation<Path>> createNamedPathIndex(Method method) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            v03.i(parameterAnnotations, "parameterAnnotations");
            ArrayList arrayList = new ArrayList();
            int length = parameterAnnotations.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                Annotation[] annotationArr = parameterAnnotations[i];
                ReflectionUtil.Companion companion = ReflectionUtil.Companion;
                v03.i(annotationArr, "parameterAnnotation");
                Path path = (Path) companion.findAnnotation(annotationArr, Path.class);
                IndexedAnnotation indexedAnnotation = path == null ? null : new IndexedAnnotation(path, i2);
                if (indexedAnnotation != null) {
                    arrayList.add(indexedAnnotation);
                }
                i++;
                i2 = i3;
            }
            return arrayList;
        }

        public final String convertPathArg(String str, Object obj) {
            v03.j(str, "name");
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Number) {
                return ((Number) obj).toString();
            }
            if (obj == null) {
                throw new IllegalArgumentException(u4.j("@Path argument ", str, " is null"));
            }
            StringBuilder sbP = u4.p("@Path argument ", str, " has unsupported type: ");
            sbP.append(obj.getClass().getName());
            throw new IllegalArgumentException(sbP.toString());
        }

        public final String parseUri(Annotation annotation) {
            v03.j(annotation, "annot");
            if (annotation instanceof GET) {
                return ((GET) annotation).value();
            }
            if (annotation instanceof POST) {
                return ((POST) annotation).value();
            }
            if (annotation instanceof SUB) {
                return ((SUB) annotation).value();
            }
            if (annotation instanceof DELETE) {
                return ((DELETE) annotation).value();
            }
            if (annotation instanceof PUT) {
                return ((PUT) annotation).value();
            }
            throw new IllegalArgumentException(zu.f0(zu.d0(annotation)).toString());
        }
    }

    public PathAtom(Method method) {
        v03.j(method, "method");
        Companion companion = Companion;
        this.namedPathIndex = companion.createNamedPathIndex(method);
        this.uri = companion.parseUri(ReflectionUtil.Companion.findActionAnnotation(method));
    }

    public final String call(Object[] objArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        us1 us1Var = or2.b;
        for (IndexedAnnotation<Path> indexedAnnotation : this.namedPathIndex) {
            String strValue = ((Path) indexedAnnotation.getAnnot()).value();
            if (objArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            String strConvertPathArg = Companion.convertPathArg(strValue, objArr[indexedAnnotation.getIndex()]);
            if (!((Path) indexedAnnotation.getAnnot()).encoded()) {
                strConvertPathArg = us1Var.b(strConvertPathArg);
            }
            linkedHashMap.put(strValue, strConvertPathArg);
        }
        StringBuilder sb = new StringBuilder(this.uri);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("{" + ((String) entry.getKey()));
            sb2.append('}');
            String string = sb2.toString();
            int iIndexOf = sb.indexOf(string);
            sb.replace(iIndexOf, string.length() + iIndexOf, (String) entry.getValue());
        }
        String string2 = sb.toString();
        v03.i(string2, "builder.toString()");
        return string2;
    }
}
