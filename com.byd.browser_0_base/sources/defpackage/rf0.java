package defpackage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: EditorDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class rf0 implements Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ tf0 e;

    public rf0(tf0 tf0Var) {
        this.e = tf0Var;
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        int i = R$id.delete_menu_id;
        tf0 tf0Var = this.e;
        if (itemId != i) {
            if (menuItem.getItemId() != R$id.help_menu_id) {
                return true;
            }
            fu0 fu0VarA = hu0.a();
            Activity activity = tf0Var.e;
            ((hu0) fu0VarA).c(activity, activity.getString(R$string.help_context_autofill), null, tf0Var.B);
            return true;
        }
        il ilVar = tf0Var.q;
        String str = (String) ilVar.d;
        Object obj = ilVar.e;
        if (str == null && ((String) obj) == null) {
            tf0Var.z.run();
            tf0Var.b();
            return true;
        }
        View viewInflate = LayoutInflater.from(tf0Var.getContext()).inflate(R$layout.confirmation_dialog_view, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.confirmation_dialog_title)).setText(str);
        ((TextView) viewInflate.findViewById(R$id.confirmation_dialog_message)).setText((String) obj);
        AlertDialog.Builder builder = new AlertDialog.Builder(tf0Var.getContext(), R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.a.r = viewInflate;
        int i2 = 0;
        builder.e(R$string.cancel, new nf0(i2, tf0Var));
        builder.f(R$string.delete, new of0(i2, tf0Var));
        builder.a().show();
        return true;
    }
}
