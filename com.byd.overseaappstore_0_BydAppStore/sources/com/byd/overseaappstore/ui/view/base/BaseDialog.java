package com.byd.overseaappstore.ui.view.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.R;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.av;
import spi.ct;
import spi.d;
import spi.u4;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/BaseDialog;", "Landroidx/viewbinding/ViewBinding;", "VB", "Landroidx/fragment/app/DialogFragment;", "Landroid/content/DialogInterface$OnKeyListener;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseDialog<VB extends ViewBinding> extends DialogFragment implements DialogInterface.OnKeyListener {

    @Nullable
    public VB a;

    @Nullable
    public Function0<Unit> b;
    public boolean c = true;
    public boolean d = true;

    @NotNull
    public abstract VB b();

    public abstract void c();

    public abstract void d();

    @Override // androidx.fragment.app.DialogFragment
    public final void dismissAllowingStateLoss() {
        if (isAdded()) {
            ct.a(getClass().getSimpleName() + " isAdded: " + isAdded() + " so dismiss it", new Object[0]);
            super.dismissAllowingStateLoss();
            return;
        }
        boolean zIsAdded = isAdded();
        Dialog dialog = getDialog();
        ct.c("dismiss: " + this + " not associated with a fragment manager added:" + zIsAdded + " isShow:" + (dialog != null ? Boolean.valueOf(dialog.isShowing()) : null), new Object[0]);
    }

    public abstract void e(@NotNull Configuration configuration);

    public abstract void f(@NotNull Configuration configuration);

    public abstract void g();

    public final void h(@NotNull FragmentManager manager, @Nullable String str) {
        Object objM63constructorimpl;
        Object objValueOf;
        Intrinsics.checkNotNullParameter(manager, "manager");
        try {
            Result.Companion companion = Result.INSTANCE;
            if (isAdded()) {
                ct.b("DialogFragment showAllowingStateLoss already add class:" + getClass().getSimpleName(), new Object[0]);
                objValueOf = Unit.INSTANCE;
            } else {
                Field declaredField = DialogFragment.class.getDeclaredField("mDismissed");
                Intrinsics.checkNotNullExpressionValue(declaredField, "DialogFragment::class.ja…claredField(\"mDismissed\")");
                declaredField.setAccessible(true);
                declaredField.set(this, Boolean.FALSE);
                Field declaredField2 = DialogFragment.class.getDeclaredField("mShownByMe");
                Intrinsics.checkNotNullExpressionValue(declaredField2, "DialogFragment::class.ja…claredField(\"mShownByMe\")");
                declaredField2.setAccessible(true);
                declaredField2.set(this, Boolean.TRUE);
                FragmentTransaction fragmentTransactionBeginTransaction = manager.beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "manager.beginTransaction()");
                fragmentTransactionBeginTransaction.add(this, str);
                objValueOf = Integer.valueOf(fragmentTransactionBeginTransaction.commitAllowingStateLoss());
            }
            objM63constructorimpl = Result.m63constructorimpl(objValueOf);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM66exceptionOrNullimpl = Result.m66exceptionOrNullimpl(objM63constructorimpl);
        if (thM66exceptionOrNullimpl != null) {
            ct.b(d.g("DialogFragment showAllowingStateLoss error:", thM66exceptionOrNullimpl.getMessage(), " class:", getClass().getSimpleName()), new Object[0]);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        ct.a(d.f("DialogFg lifecycle onConfigurationChanged class:", getClass().getSimpleName()), new Object[0]);
        e(newConfig);
        f(newConfig);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NotNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        ct.a(d.f("DialogFg lifecycle onCreateDialog class:", getClass().getSimpleName()), new Object[0]);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new u4(contextRequireContext, R.style.DefaultStyleDialog);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ct.a(d.f("DialogFg lifecycle onCreateView class:", getClass().getSimpleName()), new Object[0]);
        VB vb = (VB) b();
        this.a = vb;
        if (vb != null) {
            return vb.getRoot();
        }
        return null;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ct.a(d.f("DialogFg lifecycle onDestroyView class:", getClass().getSimpleName()), new Object[0]);
        this.a = null;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(@Nullable DialogInterface dialogInterface, int i, @NotNull KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (i != 4 || event.getAction() != 1) {
            return false;
        }
        Function0<Unit> function0 = this.b;
        if (function0 != null) {
            function0.invoke();
        }
        if (!this.c) {
            return true;
        }
        dismissAllowingStateLoss();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ct.a(d.f("DialogFg lifecycle onViewCreated class:", getClass().getSimpleName()), new Object[0]);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(this.d);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setOnKeyListener(this);
        }
        d();
        c();
        av avVar = av.a;
        getContext();
        boolean zE = avVar.e();
        if (zE) {
            ct.a("dialog checkToUpdateRightRudderView rightRudder:" + zE + " ", new Object[0]);
            g();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(@NotNull FragmentManager manager, @Nullable String str) {
        Object objM63constructorimpl;
        Intrinsics.checkNotNullParameter(manager, "manager");
        try {
            Result.Companion companion = Result.INSTANCE;
            if (isAdded()) {
                ct.b("DialogFragment already add class:" + getClass().getSimpleName(), new Object[0]);
            } else {
                super.show(manager, str);
            }
            objM63constructorimpl = Result.m63constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM63constructorimpl = Result.m63constructorimpl(ResultKt.createFailure(th));
        }
        Throwable thM66exceptionOrNullimpl = Result.m66exceptionOrNullimpl(objM63constructorimpl);
        if (thM66exceptionOrNullimpl != null) {
            ct.b(d.g("DialogFragment show error:", thM66exceptionOrNullimpl.getMessage(), " class:", getClass().getSimpleName()), new Object[0]);
        }
    }
}
