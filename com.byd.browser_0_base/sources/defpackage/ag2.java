package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SearchBoxCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ag2 {
    public final PropertyModel a;
    public final ViewGroup b;
    public final cg2 c;
    public WindowAndroid d;

    public ag2(Context context, ViewGroup viewGroup) {
        PropertyModel propertyModel = new PropertyModel(dg2.v);
        this.a = propertyModel;
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R$id.search_box);
        this.b = viewGroup2;
        this.c = new cg2(context, propertyModel, viewGroup2);
    }

    public void addLensButtonClickListener(View.OnClickListener onClickListener) {
        this.c.addLensButtonClickListener(onClickListener);
    }

    public void addVoiceSearchButtonClickListener(View.OnClickListener onClickListener) {
        this.c.addVoiceSearchButtonClickListener(onClickListener);
    }

    public void setSearchBoxClickListener(View.OnClickListener onClickListener) {
        this.c.setSearchBoxClickListener(onClickListener);
    }
}
