package defpackage;

import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: compiled from: AccountPickerProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h1 {
    public static final PropertyModel.r<fb0> a;
    public static final PropertyModel.l<Callback<fb0>> b;
    public static final v62[] c;

    static {
        PropertyModel.r<fb0> rVar = new PropertyModel.r<>("profile_data", false);
        a = rVar;
        PropertyModel.l<Callback<fb0>> lVar = new PropertyModel.l<>("on_click_listener");
        b = lVar;
        c = new v62[]{rVar, lVar};
    }
}
