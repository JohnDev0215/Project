package defpackage;

import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PaginationViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class lv1 extends i81 {
    public static final /* synthetic */ int z = 0;

    public lv1(View view) {
        super(view);
    }

    @Override // defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        this.e.setOnClickListener(new kg(1, propertyModel));
    }
}
