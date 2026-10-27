package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$menu;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: CustomTabAppMenuPropertiesDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class b30 extends o7 {
    public final List<String> A;
    public final HashMap B;
    public final HashMap C;
    public final il3 t;
    public final int u;
    public final boolean v;
    public final boolean w;
    public final boolean x;
    public final boolean y;
    public final boolean z;

    public b30(Context context, z2 z2Var, uj1 uj1Var, d23 d23Var, g gVar, View view, aq1 aq1Var, il3 il3Var, int i, List list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        super(context, z2Var, uj1Var, d23Var, gVar, view, null, null, aq1Var, null);
        this.B = new HashMap();
        this.C = new HashMap();
        this.t = il3Var;
        this.u = i;
        this.A = list;
        this.x = z;
        this.v = z2;
        this.w = z3;
        this.y = z5;
        this.z = z6;
    }

    @Override // defpackage.o7, defpackage.m7
    public final int a() {
        int i = this.u;
        if (i == 1 || i == 5) {
            return 0;
        }
        return R$layout.powered_by_chrome_footer;
    }

    @Override // defpackage.o7, defpackage.m7
    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new kb0());
        return arrayList;
    }

    @Override // defpackage.o7, defpackage.m7
    public final void d(i7 i7Var, View view) {
        TextView textView = (TextView) view.findViewById(R$id.running_in_chrome_footer_text);
        if (textView != null) {
            textView.setText(view.getResources().getString(R$string.twa_running_in_chrome_template, view.getResources().getString(R$string.app_name)));
        }
    }

    @Override // defpackage.o7, defpackage.m7
    public final boolean f() {
        return this.z;
    }

    @Override // defpackage.o7, defpackage.m7
    public final Bundle g(int i) {
        Bundle bundleG = super.g(i);
        HashMap map = this.C;
        if (!map.containsKey(Integer.valueOf(i))) {
            return bundleG;
        }
        bundleG.putInt("CustomMenuItemId", ((Integer) map.get(Integer.valueOf(i))).intValue());
        return bundleG;
    }

    @Override // defpackage.o7
    public final int h() {
        return R$menu.custom_tabs_menu;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0139 A[LOOP:0: B:59:0x0131->B:61:0x0139, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x016a A[EDGE_INSN: B:67:0x016a->B:62:0x016a BREAK  A[LOOP:0: B:59:0x0131->B:61:0x0139], SYNTHETIC] */
    @Override // defpackage.o7
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(android.view.Menu r11, defpackage.i7 r12) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b30.m(android.view.Menu, i7):void");
    }
}
