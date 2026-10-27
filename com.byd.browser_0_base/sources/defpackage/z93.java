package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import defpackage.zf2;
import java.util.Iterator;
import java.util.function.Consumer;
import org.chromium.base.b;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class z93 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ z93(Object obj, int i, Object obj2, boolean z) {
        this.e = i;
        this.g = obj;
        this.h = obj2;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        boolean z = this.f;
        Object obj = this.h;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                w93 w93Var = (w93) obj;
                aa3 aa3Var = aa3.this;
                ti1 ti1Var = (ti1) aa3Var.d;
                ti1Var.getClass();
                SuggestionsTileView suggestionsTileViewA = ti1Var.a(w93Var.a);
                if (suggestionsTileViewA != null) {
                    suggestionsTileViewA.setIconDrawable(w93Var.e);
                    suggestionsTileViewA.setIconViewLayoutParams(w93Var);
                    suggestionsTileViewA.setIconTint(w93Var.f);
                }
                Runnable runnable = ti1Var.n;
                if (runnable != null) {
                    runnable.run();
                }
                if (z) {
                    aa3Var.d(3);
                }
                break;
            case 1:
                zf2.a aVar = (zf2.a) obj2;
                zf2 zf2Var = (zf2) obj;
                if (z) {
                    SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                    sharedPreferencesManager.o("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME", aVar.a);
                    sharedPreferencesManager.o("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_URL", aVar.b);
                    sharedPreferencesManager.l("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE", aVar.c);
                    sharedPreferencesManager.l("org.chromium.chrome.browser.searchwidget.IS_GOOGLE_LENS_AVAILABLE", aVar.d);
                    sharedPreferencesManager.l("org.chromium.chrome.browser.searchwidget.IS_INCOGNITO_AVAILABLE", aVar.e);
                }
                Iterator<Consumer<zf2.a>> it = zf2Var.e.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it;
                    if (aVar2.hasNext()) {
                        ((Consumer) aVar2.next()).accept(aVar);
                    }
                    break;
                }
                break;
            default:
                DragReorderableListAdapter.b bVar = (DragReorderableListAdapter.b) obj2;
                bVar.getClass();
                ((RecyclerView.a0) obj).e.setBackgroundColor(z ? DragReorderableListAdapter.this.l : 0);
                break;
        }
    }

    public /* synthetic */ z93(boolean z, zf2.a aVar, zf2 zf2Var) {
        this.e = 1;
        this.f = z;
        this.g = aVar;
        this.h = zf2Var;
    }
}
