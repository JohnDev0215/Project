package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import defpackage.hg1;
import defpackage.jk2;
import defpackage.rq0;
import defpackage.sf3;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class tf3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ tf3(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = true;
        int i = this.e;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                sf3.a aVar = (sf3.a) obj3;
                try {
                    aVar.f((ft1) obj2, new vf3((dg3) ((c91) obj).get()));
                } catch (RemoteException | InterruptedException e) {
                    Log.w("cr_TWAClient", "Failed to execute TWA command.", e);
                    return;
                } catch (SecurityException | ExecutionException e2) {
                    Log.w("cr_TWAClient", "Failed to connect to TWA to execute command", e2);
                    aVar.c();
                    return;
                }
                break;
            case 1:
                qq0 qq0Var = (qq0) obj3;
                sq0 sq0Var = (sq0) obj2;
                Tab tab = (Tab) obj;
                if (!qq0Var.g() || (!Objects.equals(qq0Var.n, sq0Var) && !Objects.equals(qq0Var.q, sq0Var))) {
                    z = false;
                }
                if (!z) {
                    qq0Var.g.r(Boolean.TRUE);
                    if (qq0Var.h.get().booleanValue()) {
                        qq0Var.k(sq0Var, qq0Var.t);
                    } else {
                        qq0Var.q = sq0Var;
                    }
                }
                qq0Var.o(false);
                WebContents webContentsC = tab.c();
                if (webContentsC != null) {
                    SelectionPopupControllerImpl.j(webContentsC).i();
                }
                qq0.n(tab, null);
                Iterator<rq0.a> it = qq0Var.j.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it;
                    if (aVar2.hasNext()) {
                        ((rq0.a) aVar2.next()).a(sq0Var, tab);
                    }
                    break;
                }
                break;
            case 2:
                hu huVar = (hu) obj3;
                AutocompleteMatch autocompleteMatch = (AutocompleteMatch) obj2;
                PropertyModel propertyModel = (PropertyModel) obj;
                huVar.getClass();
                n3.X0("Omnibox.ClipboardSuggestion.Reveal");
                if (!autocompleteMatch.j.k()) {
                    huVar.j(autocompleteMatch, propertyModel, true);
                } else {
                    m91 m91Var = new m91(huVar, 7, autocompleteMatch, propertyModel);
                    long j = autocompleteMatch.w;
                    if (j != 0) {
                        N.MrcKeMB9(j, m91Var);
                    } else {
                        m91Var.run();
                    }
                }
                break;
            case 3:
                Uri uri = (Uri) obj3;
                jk2.e eVar = (jk2.e) obj2;
                if (uri == null) {
                    eVar.b();
                    break;
                } else if (ApplicationStatus.getStateForApplication() != 4) {
                    eVar.a(uri);
                    break;
                }
                break;
            default:
                hg1 hg1Var = (hg1) obj3;
                hg1Var.e.cancel();
                hg1Var.e.removeAllListeners();
                AnimatorSet animatorSet = new AnimatorSet();
                hg1Var.e = animatorSet;
                animatorSet.play((Animator) obj2);
                hg1Var.e.addListener(new hg1.a(new a9(hg1Var, 27, (Runnable) obj)));
                ((jq) hg1Var.d).b();
                hg1Var.i.onResult(hg1Var.e);
                break;
        }
    }
}
