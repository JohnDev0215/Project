package defpackage;

import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: y0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountPickerBottomSheetProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0221y0 {
    public static final PropertyModel.l<View.OnClickListener> a;
    public static final PropertyModel.r<fb0> b;
    public static final PropertyModel.l<View.OnClickListener> c;
    public static final PropertyModel.l<View.OnClickListener> d;
    public static final PropertyModel.p e;
    public static final PropertyModel.j f;
    public static final PropertyModel.l<InterfaceC0223z0> g;
    public static final v62[] h;

    static {
        PropertyModel.l<View.OnClickListener> lVar = new PropertyModel.l<>("on_selected_account_clicked");
        a = lVar;
        PropertyModel.r<fb0> rVar = new PropertyModel.r<>("selected_account_data", false);
        b = rVar;
        PropertyModel.l<View.OnClickListener> lVar2 = new PropertyModel.l<>("on_continue_as_clicked");
        c = lVar2;
        PropertyModel.l<View.OnClickListener> lVar3 = new PropertyModel.l<>("on_dismiss_clicked");
        d = lVar3;
        PropertyModel.p pVar = new PropertyModel.p("view_state");
        e = pVar;
        PropertyModel.j jVar = new PropertyModel.j("entry_point");
        f = jVar;
        PropertyModel.l<InterfaceC0223z0> lVar4 = new PropertyModel.l<>("bottom_sheet_strings");
        g = lVar4;
        h = new v62[]{lVar, rVar, lVar2, lVar3, pVar, jVar, lVar4};
    }
}
