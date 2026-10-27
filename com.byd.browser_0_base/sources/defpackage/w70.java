package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import androidx.core.content.FileProvider;
import androidx.fragment.app.b;
import androidx.fragment.app.j;
import defpackage.f93;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class w70 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ w70(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Uri uriB;
        int i = this.e;
        int i2 = 0;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                List list = (List) obj3;
                j.b bVar = (j.b) obj2;
                i21.e(list, "$awaitingContainerChanges");
                i21.e(bVar, "$operation");
                i21.e((b) obj, "this$0");
                if (list.contains(bVar)) {
                    list.remove(bVar);
                    View view = bVar.c.f12J;
                    int i3 = bVar.a;
                    i21.d(view, "view");
                    gq2.a(i3, view);
                }
                break;
            case 1:
                String str = (String) obj2;
                Callback callback = (Callback) obj;
                d.c cVar = d.a;
                File file = new File((String) obj3);
                try {
                    Context context = xz.a;
                    uriB = FileProvider.a(context, context.getPackageName() + ".FileProvider").b(file);
                } catch (Exception unused) {
                    uriB = Uri.parse(str);
                }
                PostTask.c(rh3.a, callback.p1(uriB));
                break;
            case 2:
                hu huVar = (hu) obj3;
                huVar.getClass();
                n3.X0("Omnibox.ClipboardSuggestion.Conceal");
                huVar.j((AutocompleteMatch) obj2, (PropertyModel) obj, false);
                break;
            case 3:
                xl2 xl2Var = (xl2) obj3;
                ByteBuffer byteBuffer = (ByteBuffer) obj2;
                Callback callback2 = (Callback) obj;
                int i4 = xl2.t;
                if (xl2Var != null) {
                    xl2Var.c(byteBuffer);
                }
                PostTask.c(rh3.a, new ul2(callback2, i2, xl2Var));
                break;
            case 4:
                Tab tab = (Tab) obj3;
                ByteBuffer byteBuffer2 = (ByteBuffer) obj2;
                Callback callback3 = (Callback) obj;
                int i5 = xl2.t;
                PostTask.c(d73.k, new w70(tab.isDestroyed() ? null : xl2.n(tab), 3, byteBuffer2, callback3));
                break;
            case 5:
                ((s62) obj2).b((AccountInfo) N.MAwvRw4K(((org.chromium.components.signin.identitymanager.b) obj3).e.a, (String) obj));
                break;
            case 6:
                f93 f93Var = (f93) obj3;
                y82 y82Var = (y82) obj2;
                y82 y82Var2 = (y82) obj;
                f93.j jVar = f93.l;
                f93Var.getClass();
                int i6 = y82Var.a;
                int i7 = y82Var.b;
                int i8 = y82Var2.a;
                int i9 = y82Var2.b;
                ImeAdapterImpl imeAdapterImpl = f93Var.d;
                imeAdapterImpl.f.f(imeAdapterImpl.d(), i6, i7, i8, i9);
                break;
            default:
                tp3 tp3Var = (tp3) obj3;
                tp3Var.e--;
                ((Callback) obj2).onResult((Boolean) obj);
                break;
        }
    }
}
