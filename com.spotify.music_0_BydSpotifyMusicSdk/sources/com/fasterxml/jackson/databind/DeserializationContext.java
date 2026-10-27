package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat$Value;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.util.Date;
import p.b60;
import p.h71;
import p.q71;
import p.sl;
import p.ts2;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeserializationContext extends b60 implements Serializable {
    private static final long serialVersionUID = 1;

    public abstract void A(h71 h71Var, String str, Object... objArr);

    public abstract void B(h71 h71Var, JsonToken jsonToken, String str, Object... objArr);

    public abstract JavaType c(Class cls);

    public abstract CoercionAction d(LogicalType logicalType, Class cls, CoercionInputShape coercionInputShape);

    public abstract CoercionAction e(LogicalType logicalType, Class cls);

    public abstract h71 f(sl slVar, JavaType javaType);

    public abstract AnnotationIntrospector g();

    public abstract Base64Variant h();

    public abstract DeserializationConfig i();

    public abstract JsonFormat$Value j(Class cls);

    public abstract TypeFactory k();

    public abstract void l(h71 h71Var);

    public abstract Object m(Class cls, ts2 ts2Var, String str, Object... objArr);

    public abstract void n(JavaType javaType, JsonToken jsonToken, q71 q71Var, String str, Object... objArr);

    public abstract void o(q71 q71Var, JavaType javaType);

    public abstract void p(q71 q71Var, Class cls);

    public abstract void q(q71 q71Var, h71 h71Var, Object obj, String str);

    public abstract void r(Class cls, Number number, String str, Object... objArr);

    public abstract void s(Class cls, String str, String str2, Object... objArr);

    public abstract boolean t(int i);

    public abstract boolean u(StreamReadCapability streamReadCapability);

    public abstract boolean v(DeserializationFeature deserializationFeature);

    public abstract boolean w(MapperFeature mapperFeature);

    public abstract Date x(String str);

    public abstract Object y(JavaType javaType, String str);

    public abstract void z(Class cls, String str, Object... objArr);
}
