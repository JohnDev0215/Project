package defpackage;

import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.hu2;
import org.chromium.chrome.browser.content_creation.notes.NoteCreationDialog;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bo1 implements j.a {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ bo1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        int i = this.a;
        Object obj3 = this.b;
        switch (i) {
            case 0:
                int i2 = NoteCreationDialog.z0;
                ((NoteCreationDialog) obj3).Q0((PropertyModel) kVar, (ViewGroup) obj);
                break;
            default:
                PropertyModel propertyModel = (PropertyModel) kVar;
                TextView textView = (TextView) obj;
                v62 v62Var = (v62) obj2;
                ((hu2) obj3).getClass();
                PropertyModel.r<String> rVar = hu2.a.a;
                if (v62Var == rVar) {
                    textView.setText((CharSequence) propertyModel.j(rVar));
                }
                break;
        }
    }
}
