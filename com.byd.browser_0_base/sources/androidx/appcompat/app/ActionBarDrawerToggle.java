package androidx.appcompat.app;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarDrawerToggle implements DrawerLayout.d {

    /* JADX INFO: renamed from: androidx.appcompat.app.ActionBarDrawerToggle$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            throw null;
        }
    }

    public interface Delegate {
    }

    public interface DelegateProvider {
    }

    public static class FrameworkActionBarDelegate implements Delegate {

        public static class Api18Impl {
            private Api18Impl() {
            }
        }
    }

    public static class ToolbarCompatDelegate implements Delegate {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public final void a() {
        throw null;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public final void c() {
        throw null;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public final void d() {
        throw null;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.d
    public final void b() {
    }

    public void setToolbarNavigationClickListener(View.OnClickListener onClickListener) {
    }
}
