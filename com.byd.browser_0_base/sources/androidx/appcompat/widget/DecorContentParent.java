package androidx.appcompat.widget;

import android.view.Window;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;

/* JADX INFO: loaded from: classes.dex */
public interface DecorContentParent {
    boolean a();

    void b();

    boolean c();

    void d(MenuBuilder menuBuilder, MenuPresenter.Callback callback);

    boolean e();

    boolean f();

    boolean g();

    void h(int i);

    void i();

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
