package defpackage;

import android.view.ContextThemeWrapper;
import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: CustomViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public interface m50 extends j.a<PropertyModel, View, v62> {
    int c(int i);

    int d(ContextThemeWrapper contextThemeWrapper);

    int getItemViewType(int i);

    void getViewTypeCount();
}
