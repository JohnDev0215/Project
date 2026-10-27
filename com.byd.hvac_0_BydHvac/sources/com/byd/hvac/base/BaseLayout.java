package com.byd.hvac.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import b.a.a.b.a;
import b.a.a.b.b;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseLayout<T extends a> extends RelativeLayout implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f988a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T f989b;

    public BaseLayout(Context context) {
        super(context);
        this.f989b = (T) y();
        this.f988a = context;
        RelativeLayout.inflate(context, getLayoutId(), this);
        M();
        V();
        f0();
    }

    public static Class<?> G(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(G(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return G(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public final void F() {
        BydLogHelper.logD("detachView: ");
        T t = this.f989b;
        if (t != null) {
            t.a();
            this.f989b.b();
        }
    }

    public abstract void M();

    public void V() {
        T t = this.f989b;
        if (t != null) {
            t.c();
        }
    }

    public abstract void f0();

    public abstract int getLayoutId();

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        BydLogHelper.logD("onAttachedToWindow");
        p();
        f0();
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        BydLogHelper.logD("onDetachedFromWindow");
        F();
        super.onDetachedFromWindow();
    }

    public final void p() {
        BydLogHelper.logD("attachView: ");
        T t = this.f989b;
        if (t != null) {
            t.d(this);
        }
    }

    public final T y() {
        try {
            return (T) G(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BaseLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f989b = (T) y();
        this.f988a = context;
        RelativeLayout.inflate(context, getLayoutId(), this);
        M();
        V();
    }
}
