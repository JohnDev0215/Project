package com.spotify.cosmos.cosmonautatoms;

import com.google.common.base.Optional;
import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.cosmos.cosmonaut.annotations.Query;
import com.spotify.cosmos.cosmonaut.annotations.QueryMap;
import com.spotify.cosmos.cosmonautatoms.ReflectionUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.or2;
import p.u4;
import p.us1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class QueryAtom {
    public static final Companion Companion = new Companion(null);
    private final List<IndexedAnnotation<Query>> namedQueryIndex;
    private final Optional<IndexedAnnotation<QueryMap>> queryMapIndex;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<IndexedAnnotation<Query>> createNamedQueryIndex(Method method) {
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
                Query query = (Query) companion.findAnnotation(annotationArr, Query.class);
                IndexedAnnotation indexedAnnotation = query == null ? null : new IndexedAnnotation(query, i2);
                if (indexedAnnotation != null) {
                    arrayList.add(indexedAnnotation);
                }
                i++;
                i2 = i3;
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Optional<IndexedAnnotation<QueryMap>> findQueryMapIndex(Method method) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            v03.i(parameterAnnotations, "parameterAnnotations");
            int length = parameterAnnotations.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                Annotation[] annotationArr = parameterAnnotations[i];
                ReflectionUtil.Companion companion = ReflectionUtil.Companion;
                v03.i(annotationArr, "parameterAnnotation");
                QueryMap queryMap = (QueryMap) companion.findAnnotation(annotationArr, QueryMap.class);
                if (queryMap != null) {
                    Type type = method.getGenericParameterTypes()[i2];
                    Companion companion2 = QueryAtom.Companion;
                    v03.i(type, RxProductState.Keys.KEY_TYPE);
                    if (companion2.isValidQueryMapArgumentType(type)) {
                        return Optional.e(new IndexedAnnotation(queryMap, i2));
                    }
                    throw new IllegalArgumentException("@QueryMap argument should be Map<String, Object> or Map<String, String>".toString());
                }
                i++;
                i2 = i3;
            }
            Optional<IndexedAnnotation<QueryMap>> optionalA = Optional.a();
            v03.i(optionalA, "absent()");
            return optionalA;
        }

        public final String convertQueryArg(String str, Object obj) {
            v03.j(str, "name");
            v03.j(obj, "arg");
            if (obj instanceof String) {
                return (String) obj;
            }
            if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
                StringBuilder sbP = u4.p("@Query argument ", str, " has unsupported type: ");
                sbP.append(obj.getClass().getName());
                throw new IllegalArgumentException(sbP.toString());
            }
            return obj.toString();
        }

        public final Map<String, Object> convertToTypedMap(Object obj) {
            v03.j(obj, "arg");
            return (Map) obj;
        }

        public final boolean isClassAssignableFromType(Type type, Class<?> cls) {
            v03.j(type, RxProductState.Keys.KEY_TYPE);
            v03.j(cls, "clazz");
            return (type instanceof Class) && cls.isAssignableFrom((Class) type);
        }

        public final boolean isValidQueryMapArgumentType(Type type) {
            v03.j(type, RxProductState.Keys.KEY_TYPE);
            if (!(type instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length != 2) {
                return false;
            }
            v03.i(rawType, "containerType");
            if (!isClassAssignableFromType(rawType, Map.class)) {
                return false;
            }
            Type type2 = actualTypeArguments[0];
            v03.i(type2, "types[0]");
            return isClassAssignableFromType(type2, String.class);
        }
    }

    public QueryAtom(Method method) {
        v03.j(method, "method");
        Companion companion = Companion;
        this.namedQueryIndex = companion.createNamedQueryIndex(method);
        this.queryMapIndex = companion.findQueryMapIndex(method);
    }

    public final Map<String, String> call(Object[] objArr) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        fillQueryArguments(linkedHashMap, objArr);
        fillQueryMapArguments(linkedHashMap, objArr);
        return linkedHashMap;
    }

    public final void fillQueryArguments(Map<String, String> map, Object[] objArr) {
        v03.j(map, "ret");
        us1 us1Var = or2.a;
        for (IndexedAnnotation<Query> indexedAnnotation : this.namedQueryIndex) {
            if (objArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Object obj = objArr[indexedAnnotation.getIndex()];
            if (obj != null) {
                String strValue = ((Query) indexedAnnotation.getAnnot()).value();
                Companion companion = Companion;
                String strConvertQueryArg = companion.convertQueryArg(strValue, obj);
                if (!((Query) indexedAnnotation.getAnnot()).encoded()) {
                    strConvertQueryArg = us1Var.b(strConvertQueryArg);
                }
                map.put(strValue, companion.convertQueryArg(strValue, strConvertQueryArg));
            }
        }
    }

    public final void fillQueryMapArguments(Map<String, String> map, Object[] objArr) {
        v03.j(map, "ret");
        us1 us1Var = or2.a;
        if (this.queryMapIndex.d()) {
            IndexedAnnotation indexedAnnotation = (IndexedAnnotation) this.queryMapIndex.c();
            if (objArr == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Object obj = objArr[indexedAnnotation.getIndex()];
            if (obj instanceof Map) {
                for (Map.Entry<String, Object> entry : Companion.convertToTypedMap(obj).entrySet()) {
                    String strConvertQueryArg = Companion.convertQueryArg(entry.getKey(), entry.getValue());
                    if (!((QueryMap) indexedAnnotation.getAnnot()).encoded()) {
                        strConvertQueryArg = us1Var.b(strConvertQueryArg);
                    }
                    map.put(entry.getKey(), strConvertQueryArg);
                }
            }
        }
    }
}
