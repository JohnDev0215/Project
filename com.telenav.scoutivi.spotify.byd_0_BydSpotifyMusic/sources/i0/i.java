package i0;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MenuItem;

/* JADX INFO: compiled from: MenuItemCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static MenuItem a(MenuItem menuItem, b bVar) {
        if (menuItem instanceof b0.b) {
            return ((b0.b) menuItem).a(bVar);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static void b(MenuItem menuItem, char c7, int i7) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setAlphabeticShortcut(c7, i7);
        } else {
            menuItem.setAlphabeticShortcut(c7, i7);
        }
    }

    public static void c(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setContentDescription(charSequence);
        } else {
            menuItem.setContentDescription(charSequence);
        }
    }

    public static void d(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setIconTintList(colorStateList);
        } else {
            menuItem.setIconTintList(colorStateList);
        }
    }

    public static void e(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setIconTintMode(mode);
        } else {
            menuItem.setIconTintMode(mode);
        }
    }

    public static void f(MenuItem menuItem, char c7, int i7) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setNumericShortcut(c7, i7);
        } else {
            menuItem.setNumericShortcut(c7, i7);
        }
    }

    public static void g(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof b0.b) {
            ((b0.b) menuItem).setTooltipText(charSequence);
        } else {
            menuItem.setTooltipText(charSequence);
        }
    }
}
