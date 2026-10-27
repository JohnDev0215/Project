package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: StorageCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class hu2 {
    public final PropertyModel a;
    public final TextView b;

    /* JADX INFO: compiled from: StorageCoordinator.java */
    public static class a {
        public static final PropertyModel.r<String> a;
        public static final v62[] b;

        static {
            PropertyModel.r<String> rVar = new PropertyModel.r<>();
            a = rVar;
            b = new v62[]{rVar};
        }
    }

    public hu2(Context context, lg2 lg2Var) {
        PropertyModel propertyModel = new PropertyModel(a.b);
        this.a = propertyModel;
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R$layout.download_storage_summary, (ViewGroup) null);
        this.b = textView;
        j.b(propertyModel, textView, new bo1(1, this));
        new ju2(context, new pq2(11, this), lg2Var);
    }
}
