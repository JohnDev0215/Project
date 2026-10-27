package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import com.bydux.api.common.views.window.DampingScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentDriverAssistBindingLandImpl extends FragmentDriverAssistBinding {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public static final ViewDataBinding.IncludedLayouts f2821c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public static final SparseIntArray f2822d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NonNull
    public final DampingScrollView f2823e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2824f;

    public FragmentDriverAssistBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 2, f2821c, f2822d));
    }

    @Override // com.byd.carsettings.databinding.FragmentDriverAssistBinding
    public void c(@Nullable BaseAdasViewModel baseAdasViewModel) {
        this.f2816b = baseAdasViewModel;
        synchronized (this) {
            this.f2824f |= 1;
        }
        notifyPropertyChanged(12);
        super.requestRebind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [android.view.ViewGroup, androidx.recyclerview.widget.RecyclerView] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [int] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
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
            j = this.f2824f;
            this.f2824f = 0L;
        }
        BaseAdasViewModel baseAdasViewModel = this.f2816b;
        long j2 = j & 3;
        ?? J = 0;
        if (j2 != 0) {
            J = baseAdasViewModel != null ? baseAdasViewModel.j() : 0;
            if (j2 != 0) {
                j |= J != 0 ? 8L : 4L;
            }
        }
        if ((j & 3) == 0 || ViewDataBinding.getBuildSdkInt() < 17) {
            return;
        }
        this.f2815a.setLayoutDirection(J);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.f2824f != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.f2824f = 2L;
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

    public FragmentDriverAssistBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (RecyclerView) objArr[1]);
        this.f2824f = -1L;
        DampingScrollView dampingScrollView = (DampingScrollView) objArr[0];
        this.f2823e = dampingScrollView;
        dampingScrollView.setTag(null);
        this.f2815a.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
