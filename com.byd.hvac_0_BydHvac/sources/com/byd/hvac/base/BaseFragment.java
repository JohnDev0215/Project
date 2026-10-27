package com.byd.hvac.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.a.b.a;
import b.a.a.b.b;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydFragment;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragment<T extends a> extends BydFragment implements b {
    public View d;
    public Context e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f985a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f986b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f987c = true;
    public T f = (T) t0();

    public static Class<?> w0(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(w0(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return w0(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + (type == null ? "null" : type.getClass().getName()));
    }

    public abstract void A0();

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public Context getContext() {
        return this.e;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        BydLogHelper.logD("onAttach: " + this);
        super.onAttach(context);
        this.e = context;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        BydLogHelper.logD("onCreate: ");
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        BydLogHelper.logD("onCreateView: ");
        s0();
        if (this.d == null) {
            this.d = layoutInflater.inflate(v0(), viewGroup, false);
        }
        x0(this.d);
        z0();
        return this.d;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BydLogHelper.logD("onDestroy: ");
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        BydLogHelper.logD("onDestroyView: " + this);
        this.f987c = true;
        this.f985a = false;
        this.f986b = false;
        View view = this.d;
        if (view != null && view.getParent() != null) {
            ((ViewGroup) this.d.getParent()).removeView(this.d);
        }
        u0();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f985a = true;
        BydLogHelper.logD("onViewCreated: ");
        y0();
    }

    public final void s0() {
        BydLogHelper.logD("attachView: ");
        T t = this.f;
        if (t != null) {
            t.d(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        BydLogHelper.logD("setUserVisibleHint: isVisibleToUser：" + z);
        if (!z) {
            this.f986b = false;
        } else {
            this.f986b = true;
            y0();
        }
    }

    public final T t0() {
        try {
            return (T) w0(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void u0() {
        BydLogHelper.logD("detachView: ");
        T t = this.f;
        if (t != null) {
            t.a();
            this.f.b();
        }
    }

    public abstract int v0();

    public abstract void x0(View view);

    public final void y0() {
        if (this.f985a && this.f986b && this.f987c) {
            A0();
            this.f987c = false;
        }
    }

    public void z0() {
        T t = this.f;
        if (t != null) {
            t.c();
        }
    }
}
