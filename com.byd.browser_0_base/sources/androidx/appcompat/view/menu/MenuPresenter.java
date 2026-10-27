package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public interface MenuPresenter {

    public interface Callback {
        void c(MenuBuilder menuBuilder, boolean z);

        boolean d(MenuBuilder menuBuilder);
    }

    void c(MenuBuilder menuBuilder, boolean z);

    void d(boolean z);

    boolean e();

    void f(Callback callback);

    boolean g(MenuItemImpl menuItemImpl);

    int getId();

    void h(Context context, MenuBuilder menuBuilder);

    void i(Parcelable parcelable);

    boolean k(SubMenuBuilder subMenuBuilder);

    Parcelable l();

    boolean m(MenuItemImpl menuItemImpl);
}
