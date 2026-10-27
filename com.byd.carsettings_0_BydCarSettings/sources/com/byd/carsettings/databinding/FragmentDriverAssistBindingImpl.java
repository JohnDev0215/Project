package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentDriverAssistBindingImpl extends FragmentDriverAssistBinding {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public static final ViewDataBinding.IncludedLayouts f2817c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public static final SparseIntArray f2818d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NonNull
    public final DampingScrollView f2819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NonNull
    public final LinearLayout f2820f;
    public long g;

    public FragmentDriverAssistBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, f2817c, f2818d));
    }

    @Override // com.byd.carsettings.databinding.FragmentDriverAssistBinding
    public void c(@Nullable BaseAdasViewModel baseAdasViewModel) {
        this.f2816b = baseAdasViewModel;
        synchronized (this) {
            this.g |= 1;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.view.ViewGroup, androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.g;
            this.g = 0L;
        }
        float fG = 0.0f;
        BaseAdasViewModel baseAdasViewModel = this.f2816b;
        long j2 = j & 3;
        ?? J = 0;
        J = 0;
        if (j2 != 0) {
            if (baseAdasViewModel != null) {
                fG = baseAdasViewModel.g(false);
                J = baseAdasViewModel.j();
            }
            if (j2 != 0) {
                j |= J != 0 ? 8L : 4L;
            }
        }
        if ((j & 3) != 0) {
            ViewBindingAdapter.setPaddingStart(this.f2820f, fG);
            ViewBindingAdapter.setPaddingEnd(this.f2820f, fG);
            if (ViewDataBinding.getBuildSdkInt() >= 17) {
                this.f2815a.setLayoutDirection(J);
            }
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.g != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.g = 2L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (12 != i) {
            return false;
        }
        c((BaseAdasViewModel) obj);
        return true;
    }

    public FragmentDriverAssistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RecyclerView) objArr[2]);
        this.g = -1L;
        DampingScrollView dampingScrollView = (DampingScrollView) objArr[0];
        this.f2819e = dampingScrollView;
        dampingScrollView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[1];
        this.f2820f = linearLayout;
        linearLayout.setTag(null);
        this.f2815a.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
