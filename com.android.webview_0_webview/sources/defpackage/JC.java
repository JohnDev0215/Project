package defpackage;

import android.R;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextClassification;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JC implements HC {
    public final Map a = new HashMap();

    public static View.OnClickListener d(CharSequence charSequence, final PendingIntent pendingIntent) {
        if (TextUtils.isEmpty(charSequence) || pendingIntent == null) {
            return null;
        }
        return new View.OnClickListener(pendingIntent) { // from class: IC
            public final PendingIntent s;

            {
                this.s = pendingIntent;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JC.e(this.s, view);
            }
        };
    }

    public static final /* synthetic */ void e(PendingIntent pendingIntent, View view) {
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Ts.a("MenuItemProvider", "Error creating OnClickListener from PendingIntent", e);
        }
    }

    @Override // defpackage.HC
    public void a(Context context, Menu menu, TextClassification textClassification) {
        if (menu == null || textClassification == null) {
            return;
        }
        int size = textClassification.getActions().size();
        if (size > 0) {
            RemoteAction remoteAction = textClassification.getActions().get(0);
            MenuItem menuItemFindItem = menu.findItem(R.id.textAssist);
            if (remoteAction.shouldShowIcon()) {
                menuItemFindItem.setIcon(remoteAction.getIcon().loadDrawable(context));
            } else {
                menuItemFindItem.setIcon((Drawable) null);
            }
        }
        for (int i = 1; i < size; i++) {
            RemoteAction remoteAction2 = textClassification.getActions().get(i);
            View.OnClickListener onClickListenerD = d(remoteAction2.getTitle(), remoteAction2.getActionIntent());
            if (onClickListenerD != null) {
                MenuItem menuItemAdd = menu.add(R.id.textAssist, 0, i + 50, remoteAction2.getTitle());
                menuItemAdd.setContentDescription(remoteAction2.getContentDescription());
                if (remoteAction2.shouldShowIcon()) {
                    menuItemAdd.setIcon(remoteAction2.getIcon().loadDrawable(context));
                }
                menuItemAdd.setShowAsAction(1);
                this.a.put(menuItemAdd, onClickListenerD);
            }
        }
    }

    @Override // defpackage.HC
    public void b() {
        this.a.clear();
    }

    @Override // defpackage.HC
    public void c(MenuItem menuItem, View view) {
        View.OnClickListener onClickListener = (View.OnClickListener) this.a.get(menuItem);
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }
}
