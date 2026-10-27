package dagger.hilt.android.internal.managers;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;

/* JADX INFO: loaded from: classes.dex */
public final class ViewComponentManager$FragmentContextWrapper extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Fragment f5972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LayoutInflater f5973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f5974c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final m f5975d;

    ViewComponentManager$FragmentContextWrapper(Context context, Fragment fragment) {
        super((Context) o4.d.b(context));
        m mVar = new m() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper.this.f5972a = null;
                    ViewComponentManager$FragmentContextWrapper.this.f5973b = null;
                    ViewComponentManager$FragmentContextWrapper.this.f5974c = null;
                }
            }
        };
        this.f5975d = mVar;
        this.f5973b = null;
        Fragment fragment2 = (Fragment) o4.d.b(fragment);
        this.f5972a = fragment2;
        fragment2.a().a(mVar);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f5974c == null) {
            if (this.f5973b == null) {
                this.f5973b = (LayoutInflater) getBaseContext().getSystemService("layout_inflater");
            }
            this.f5974c = this.f5973b.cloneInContext(this);
        }
        return this.f5974c;
    }

    ViewComponentManager$FragmentContextWrapper(LayoutInflater layoutInflater, Fragment fragment) {
        super((Context) o4.d.b(((LayoutInflater) o4.d.b(layoutInflater)).getContext()));
        m mVar = new m() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper.this.f5972a = null;
                    ViewComponentManager$FragmentContextWrapper.this.f5973b = null;
                    ViewComponentManager$FragmentContextWrapper.this.f5974c = null;
                }
            }
        };
        this.f5975d = mVar;
        this.f5973b = layoutInflater;
        Fragment fragment2 = (Fragment) o4.d.b(fragment);
        this.f5972a = fragment2;
        fragment2.a().a(mVar);
    }
}
