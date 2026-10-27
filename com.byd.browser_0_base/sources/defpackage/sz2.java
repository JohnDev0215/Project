package defpackage;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabGridDialogMenuCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sz2 {
    public final Context a;
    public final a b;
    public final Callback<Integer> c;
    public p4 d;

    public sz2(Context context, View view, Callback<Integer> callback) {
        this.a = context;
        this.c = callback;
        a aVar = new a();
        this.b = aVar;
        context.registerComponentCallbacks(aVar);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.tab_switcher_action_menu_layout, (ViewGroup) null);
        ListView listView = (ListView) viewInflate.findViewById(R$id.tab_switcher_action_menu_list);
        ya1 ya1Var = new ya1();
        if (oj2.E(context)) {
            ya1Var.t(new xa1(0, a(context, R$string.menu_select_tabs, R$id.select_tabs)));
        } else {
            ya1Var.t(new xa1(0, a(context, R$string.tab_grid_dialog_toolbar_remove_from_group, R$id.ungroup_tab)));
        }
        if (oj2.C(context)) {
            ya1Var.t(new xa1(0, a(context, R$string.tab_grid_dialog_toolbar_edit_group_name, R$id.edit_group_name)));
        }
        tz2 tz2Var = new tz2(ya1Var);
        listView.setAdapter((ListAdapter) tz2Var);
        tz2Var.a(0, new o61(R$layout.list_menu_item), new bg(21));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: qz2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view2, int i, long j) {
                sz2 sz2Var = this.e;
                Callback<Integer> callback2 = sz2Var.c;
                if (callback2 != null) {
                    callback2.onResult(Integer.valueOf((int) j));
                }
                sz2Var.d.a();
            }
        });
        p4 p4Var = new p4(context, ((Activity) viewInflate.getContext()).getWindow().getDecorView(), AppCompatResources.a(context, R$drawable.menu_bg_tinted), viewInflate, new nn3(view));
        this.d = p4Var;
        p4Var.j.setFocusable(true);
        p4 p4Var2 = this.d;
        p4Var2.x = true;
        p4Var2.w = true;
        int i = R$style.EndIconMenuAnim;
        p4Var2.A = i;
        p4Var2.j.setAnimationStyle(i);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.menu_width);
        p4 p4Var3 = this.d;
        p4Var3.r = dimensionPixelSize;
        p4Var3.addOnDismissListener(new PopupWindow.OnDismissListener() { // from class: rz2
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                sz2 sz2Var = this.e;
                sz2Var.a.unregisterComponentCallbacks(sz2Var.b);
            }
        });
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
    public static PropertyModel a(Context context, int i, int i2) {
        HashMap mapE = PropertyModel.e(uz2.c);
        PropertyModel.r<String> rVar = uz2.a;
        ?? string = context.getString(i);
        int i3 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = string;
        mapE.put(rVar, gVar);
        PropertyModel.p pVar = uz2.b;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i2;
        mapE.put(pVar, dVar);
        return new PropertyModel(mapE);
    }

    /* JADX INFO: compiled from: TabGridDialogMenuCoordinator.java */
    public class a implements ComponentCallbacks {
        public a() {
        }

        @Override // android.content.ComponentCallbacks
        public final void onConfigurationChanged(Configuration configuration) {
            sz2 sz2Var = sz2.this;
            p4 p4Var = sz2Var.d;
            if (p4Var == null || !p4Var.c()) {
                return;
            }
            sz2Var.d.a();
        }

        @Override // android.content.ComponentCallbacks
        public final void onLowMemory() {
        }
    }
}
