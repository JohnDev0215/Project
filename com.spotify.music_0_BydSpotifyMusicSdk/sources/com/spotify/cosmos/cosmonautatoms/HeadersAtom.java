package com.spotify.cosmos.cosmonautatoms;

import com.spotify.cosmos.cosmonaut.annotations.Header;
import com.spotify.cosmos.cosmonaut.annotations.HeaderMap;
import com.spotify.cosmos.cosmonaut.annotations.Headers;
import com.spotify.cosmos.cosmonautatoms.ReflectionUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.c;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.b;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class HeadersAtom {
    public static final Companion Companion = new Companion(null);
    private final List<IndexedAnnotation<HeaderMap>> dynamicHeaderMapsIndex;
    private final List<IndexedAnnotation<Header>> dynamicHeadersIndex;
    private final Map<String, String> staticHeaders;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<IndexedAnnotation<HeaderMap>> createDynamicHeaderMapsIndex(Method method) {
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
                HeaderMap headerMap = (HeaderMap) companion.findAnnotation(annotationArr, HeaderMap.class);
                IndexedAnnotation indexedAnnotation = headerMap == null ? null : new IndexedAnnotation(headerMap, i2);
                if (indexedAnnotation != null) {
                    arrayList.add(indexedAnnotation);
                }
                i++;
                i2 = i3;
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<IndexedAnnotation<Header>> createDynamicHeadersIndex(Method method) {
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
                Header header = (Header) companion.findAnnotation(annotationArr, Header.class);
                IndexedAnnotation indexedAnnotation = header == null ? null : new IndexedAnnotation(header, i2);
                if (indexedAnnotation != null) {
                    arrayList.add(indexedAnnotation);
                }
                i++;
                i2 = i3;
            }
            return arrayList;
        }

        public final String convertHeaderArg(String str, Object obj) {
            v03.j(str, "name");
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Number) {
                return ((Number) obj).toString();
            }
            if (obj == null) {
                throw new IllegalArgumentException(u4.j("@Header argument ", str, " is null"));
            }
            StringBuilder sbP = u4.p("@Header argument ", str, " has unsupported type: ");
            sbP.append(obj.getClass().getName());
            throw new IllegalArgumentException(sbP.toString());
        }

        public final Map<String, String> createHeaders(Method method) {
            v03.j(method, "method");
            Headers headers = (Headers) method.getAnnotation(Headers.class);
            if (headers == null) {
                return c.h0();
            }
            String[] strArrValue = headers.value();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : strArrValue) {
                int iN0 = b.n0(str, ':', 0, false, 6);
                if (iN0 == -1 || iN0 == 0 || iN0 == str.length() - 1) {
                    throw new IllegalArgumentException("\"".concat(str) + "\" is not in the form \"Name: Value\"");
                }
                String strSubstring = str.substring(0, iN0);
                v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String strSubstring2 = str.substring(iN0 + 1);
                v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                String string = b.G0(strSubstring2).toString();
                if (string.length() == 0) {
                    throw new IllegalArgumentException("\"".concat(strSubstring) + "\" valuer is empty");
                }
                linkedHashMap.put(strSubstring, string);
            }
            return linkedHashMap;
        }
    }

    public HeadersAtom(Method method) {
        v03.j(method, "method");
        Companion companion = Companion;
        this.staticHeaders = companion.createHeaders(method);
        this.dynamicHeadersIndex = companion.createDynamicHeadersIndex(method);
        this.dynamicHeaderMapsIndex = companion.createDynamicHeaderMapsIndex(method);
    }

    public final Map<String, String> call(Object[] objArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.staticHeaders);
        for (IndexedAnnotation<Header> indexedAnnotation : this.dynamicHeadersIndex) {
            if (objArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Object obj = objArr[indexedAnnotation.getIndex()];
            String strValue = ((Header) indexedAnnotation.getAnnot()).value();
            linkedHashMap.put(strValue, Companion.convertHeaderArg(strValue, obj));
        }
        for (IndexedAnnotation<HeaderMap> indexedAnnotation2 : this.dynamicHeaderMapsIndex) {
            if (objArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Object obj2 = objArr[indexedAnnotation2.getIndex()];
            if (obj2 == null) {
                throw new IllegalArgumentException("@HeaderMap argument is null".toString());
            }
            if (!(obj2 instanceof Map)) {
                throw new IllegalArgumentException("@HeaderMap argument is not a java.util.Map".toString());
            }
            for (Map.Entry entry : ((Map) obj2).entrySet()) {
                String strValueOf = String.valueOf(entry.getKey());
                linkedHashMap.put(strValueOf, Companion.convertHeaderArg(strValueOf, entry.getValue()));
            }
        }
        return linkedHashMap;
    }
}
