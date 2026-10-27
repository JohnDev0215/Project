package defpackage;

import J.N;
import aa3.e;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.aa3;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TileRenderer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ka3 {
    public final Context a;
    public final uc2 b;
    public bx0 c;
    public final int d;
    public final int e;
    public final float f;
    public final int g = 1;
    public boolean h;
    public final int i;

    /* JADX INFO: compiled from: TileRenderer.java */
    public class a implements LargeIconBridge.LargeIconCallback {
        public final WeakReference<w93> e;
        public final Runnable f;

        public a(w93 w93Var, z93 z93Var) {
            this.e = new WeakReference<>(w93Var);
            this.f = z93Var;
        }

        @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
        public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
            WeakReference<w93> weakReference = this.e;
            w93 w93Var = weakReference.get();
            if (w93Var != null) {
                w93Var.d = i2;
                ka3 ka3Var = ka3.this;
                if (bitmap == null) {
                    uc2 uc2Var = ka3Var.b;
                    uc2Var.e.setColor(i);
                    w93Var.e = new BitmapDrawable(ka3Var.a.getResources(), uc2Var.c(w93Var.a.b));
                    w93Var.f = null;
                    w93Var.c = z ? 3 : 2;
                } else {
                    ka3Var.getClass();
                    int iRound = Math.round((ka3Var.f * bitmap.getWidth()) / ka3Var.d);
                    qc2 qc2Var = new qc2(ka3Var.a.getResources(), bitmap);
                    qc2Var.b(iRound);
                    qc2Var.d.setAntiAlias(true);
                    qc2Var.invalidateSelf();
                    qc2Var.setFilterBitmap(true);
                    w93Var.e = qc2Var;
                    w93Var.f = null;
                    w93Var.c = 1;
                }
                Runnable runnable = this.f;
                if (runnable != null) {
                    runnable.run();
                }
            }
            weakReference.clear();
        }
    }

    public ka3(Context context, int i, bx0 bx0Var) {
        this.c = bx0Var;
        this.a = context;
        Resources resources = context.getResources();
        context.getTheme();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.tile_view_icon_size);
        this.d = dimensionPixelSize;
        this.f = resources.getDimension(R$dimen.tile_view_icon_corner_radius);
        this.e = Math.min(dimensionPixelSize, resources.getDimensionPixelSize(R$dimen.tile_view_icon_min_size));
        this.i = i != 1 ? i != 2 ? 0 : R$layout.suggestions_tile_view_condensed : R$layout.suggestions_tile_view;
        this.b = new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, context.getColor(R$color.default_favicon_background_color), resources.getDimensionPixelSize(R$dimen.tile_view_icon_text_size));
    }

    public static boolean b(w93 w93Var) {
        TemplateUrlService templateUrlServiceQ;
        sk skVar = bq.a;
        return N.M09VlOh_("OrganicRepeatableQueries") && (templateUrlServiceQ = oj2.q(Profile.d())) != null && templateUrlServiceQ.g(w93Var.a.b);
    }

    public final SuggestionsTileView a(w93 w93Var, ViewGroup viewGroup, aa3.a aVar) {
        SuggestionsTileView suggestionsTileView = (SuggestionsTileView) LayoutInflater.from(viewGroup.getContext()).inflate(this.i, viewGroup, false);
        suggestionsTileView.getClass();
        ao2 ao2Var = w93Var.a;
        String strA0 = n3.A0(ao2Var.a, ao2Var.b);
        boolean z = w93Var.g != null;
        Drawable drawable = w93Var.e;
        suggestionsTileView.setOfflineBadgeVisibility(z);
        suggestionsTileView.setIconDrawable(drawable);
        suggestionsTileView.f.setLines(this.g);
        suggestionsTileView.f.setText(strA0);
        suggestionsTileView.j = ao2Var;
        suggestionsTileView.setIconViewLayoutParams(w93Var);
        if (this.h && aVar != null) {
            d(w93Var, aVar);
            boolean zB = b(w93Var);
            Context context = this.a;
            if (zB) {
                suggestionsTileView.setContentDescription(context.getString(R$string.accessibility_omnibox_most_visited_tile_search, ao2Var.a));
            } else {
                suggestionsTileView.setContentDescription(context.getString(R$string.accessibility_omnibox_most_visited_tile_navigate, ao2Var.a, ao2Var.b.e()));
            }
            aa3.e eVar = aa3.this.new e(ao2Var);
            if (ao2Var.d == 5) {
                eVar.f = new zx1(14, this);
            } else if (b(w93Var)) {
                eVar.f = new o90(11);
                eVar.g = new qe3(18);
            } else {
                eVar.f = new wz(20);
                eVar.g = new gg3(10);
            }
            suggestionsTileView.setOnClickListener(eVar);
            suggestionsTileView.setOnCreateContextMenuListener(eVar);
        }
        return suggestionsTileView;
    }

    public final void c(List list, ViewGroup viewGroup, aa3.a aVar) {
        TraceEvent traceEventH0 = TraceEvent.H0("TileRenderer.renderTileSection", null);
        try {
            HashMap map = new HashMap();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                SuggestionsTileView suggestionsTileView = (SuggestionsTileView) viewGroup.getChildAt(i);
                map.put(suggestionsTileView.getData(), suggestionsTileView);
            }
            viewGroup.removeAllViews();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                w93 w93Var = (w93) it.next();
                SuggestionsTileView suggestionsTileViewA = (SuggestionsTileView) map.get(w93Var.a);
                if (suggestionsTileViewA == null || suggestionsTileViewA.getIconView() == null || suggestionsTileViewA.getIconView().getDrawable() == null) {
                    suggestionsTileViewA = a(w93Var, viewGroup, aVar);
                }
                viewGroup.addView(suggestionsTileViewA);
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public final void d(w93 w93Var, aa3.a aVar) {
        if (b(w93Var)) {
            PostTask.c(rh3.a, new b80(this, 6, w93Var, aVar.a(w93Var)));
            return;
        }
        bx0 bx0Var = this.c;
        if (bx0Var != null) {
            GURL gurl = w93Var.a.b;
            a aVar2 = new a(w93Var, aVar.a(w93Var));
            if (bx0Var.b == null) {
                Object obj = ThreadUtils.a;
                if (d70.b == null) {
                    d70.b = new d70();
                }
                d70.b.getClass();
                bx0Var.b = new LargeIconBridge(bx0Var.a);
            }
            bx0Var.b.b(gurl, this.e, aVar2);
        }
    }
}
