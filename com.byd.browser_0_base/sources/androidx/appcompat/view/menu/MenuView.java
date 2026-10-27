package androidx.appcompat.view.menu;

/* JADX INFO: loaded from: classes.dex */
public interface MenuView {

    public interface ItemView {
        void c(MenuItemImpl menuItemImpl);

        MenuItemImpl getItemData();
    }

    void b(MenuBuilder menuBuilder);
}
