package defpackage;

import android.view.View;
import defpackage.f81;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CardFooterViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class nm extends i81 {
    public static final /* synthetic */ int z = 0;

    public nm(View view) {
        super(view);
    }

    @Override // defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        this.e.setOnClickListener(new c30(propertyModel, 1, (f81.c) f81Var));
    }
}
