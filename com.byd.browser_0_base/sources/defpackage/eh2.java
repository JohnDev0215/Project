package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import defpackage.f81;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SectionTitleViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class eh2 extends i81 {
    public final TextView z;

    public eh2(View view) {
        super(view);
        this.z = (TextView) view.findViewById(R$id.date);
    }

    @Override // defpackage.i81
    public final void u(PropertyModel propertyModel, f81 f81Var) {
        f81.h hVar = (f81.h) f81Var;
        Context context = this.e.getContext();
        int i = hVar.e;
        this.z.setText(i != 0 ? i != 1 ? null : context.getResources().getString(R$string.download_manager_just_now) : ou2.a(hVar.d));
    }
}
