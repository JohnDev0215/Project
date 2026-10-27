package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import defpackage.k81;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xq1 implements View.OnLongClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ xq1(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                ((Callback) ((PropertyModel) obj2).j(t81.w)).onResult((f81) obj);
                break;
            default:
                j3 j3Var = (j3) obj2;
                j3Var.getClass();
                Context context = view.getContext();
                ListMenuButton listMenuButton = (ListMenuButton) view;
                ya1 ya1Var = new ya1();
                ya1Var.t(zd.e(R$string.adaptive_toolbar_menu_edit_shortcut, R$id.customize_adaptive_button_menu_id, 0, true));
                final t02 t02Var = new t02((Callback) obj, 7);
                nn3 nn3VarN0 = n3.n0(listMenuButton);
                j3Var.a = new zd(context, ya1Var, new k81.a() { // from class: h3
                    @Override // k81.a
                    public final void a(PropertyModel propertyModel) {
                        t02Var.onResult(Integer.valueOf(propertyModel.h(o81.f)));
                    }
                });
                int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.adaptive_button_menu_vertical_padding);
                ListView listView = j3Var.a.e;
                listView.setPaddingRelative(listView.getPaddingStart(), dimensionPixelOffset, listView.getPaddingEnd(), dimensionPixelOffset);
                listMenuButton.d(new i3(j3Var, nn3VarN0), false);
                listMenuButton.f();
                n3.X0("MobileAdaptiveMenuShown");
                break;
        }
        return true;
    }
}
