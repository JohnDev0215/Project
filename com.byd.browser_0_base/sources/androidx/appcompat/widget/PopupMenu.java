package androidx.appcompat.widget;

import android.view.MenuItem;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.ShowableListMenu;

/* JADX INFO: loaded from: classes.dex */
public class PopupMenu {
    public OnMenuItemClickListener a;
    public OnDismissListener b;

    /* JADX INFO: renamed from: androidx.appcompat.widget.PopupMenu$2, reason: invalid class name */
    class AnonymousClass2 implements PopupWindow.OnDismissListener {
        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            throw null;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.PopupMenu$3, reason: invalid class name */
    class AnonymousClass3 extends ForwardingListener {
        @Override // androidx.appcompat.widget.ForwardingListener
        public final ShowableListMenu b() {
            throw null;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public final boolean c() {
            throw null;
        }

        @Override // androidx.appcompat.widget.ForwardingListener
        public final boolean d() {
            throw null;
        }
    }

    public interface OnDismissListener {
    }

    public interface OnMenuItemClickListener {
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.b = onDismissListener;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.a = onMenuItemClickListener;
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.PopupMenu$1, reason: invalid class name */
    class AnonymousClass1 implements MenuBuilder.Callback {
        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            throw null;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
        }
    }
}
