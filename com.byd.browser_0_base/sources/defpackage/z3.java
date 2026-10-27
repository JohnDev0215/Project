package defpackage;

import android.app.PendingIntent;
import android.util.Log;
import android.view.View;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z3 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ z3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                try {
                    ((PendingIntent) obj).send();
                } catch (PendingIntent.CanceledException e) {
                    Log.e("cr_MenuItemProvider", "Error creating OnClickListener from PendingIntent", e);
                }
                break;
            default:
                ((SelectableListToolbar) obj).g0.setText("");
                break;
        }
    }
}
