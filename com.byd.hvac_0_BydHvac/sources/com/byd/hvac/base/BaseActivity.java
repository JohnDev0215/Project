package com.byd.hvac.base;

import android.os.Bundle;
import b.a.a.b.a;
import b.a.a.b.b;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity<T extends a> extends BydActivity implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f984a = (T) y();

    public static Class<?> M(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(M(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return M(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public final void F() {
        T t = this.f984a;
        if (t != null) {
            t.a();
            this.f984a.b();
        }
    }

    public abstract int G();

    public abstract void V();

    public abstract void f0();

    @Override // com.bydux.api.common.views.window.BydActivity, android.app.Activity
    public void finish() {
        super.finish();
    }

    public void i0() {
        T t = this.f984a;
        if (t != null) {
            t.c();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(201326592);
        p();
        setContentView(G());
        f0();
        V();
        i0();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        F();
        super.onDestroy();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    public final void p() {
        T t = this.f984a;
        if (t != null) {
            t.d(this);
        } else {
            BydLogHelper.logD("attachView: mPresenter = null ");
        }
    }

    public final T y() {
        try {
            return (T) M(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
