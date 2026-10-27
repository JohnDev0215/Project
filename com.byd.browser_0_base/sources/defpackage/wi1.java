package defpackage;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.omnibox.suggestions.carousel.BaseCarouselSuggestionProcessor;
import org.chromium.chrome.browser.omnibox.suggestions.d;
import org.chromium.components.browser_ui.styles.R$color;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MostVisitedTilesProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wi1 extends BaseCarouselSuggestionProcessor {
    public final Context c;
    public final cw2 d;
    public final mk0 e;
    public final int f;
    public a g;
    public boolean h;

    /* JADX INFO: compiled from: MostVisitedTilesProcessor.java */
    public class a extends RecyclerView.r {
        public a() {
            d(0, 10);
        }
    }

    public wi1(Context context, d dVar, mk0 mk0Var) {
        super(context);
        this.c = context;
        this.d = dVar;
        this.e = mk0Var;
        this.f = context.getResources().getDimensionPixelSize(R$dimen.tile_view_min_height);
    }

    @Override // defpackage.ee0
    public final void a() {
        sk skVar = bq.a;
        this.h = N.M09VlOh_("OrganicRepeatableQueries");
        sh shVar = pr1.a;
        if (bq.f26J.a()) {
            this.g = new a();
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.carousel.BaseCarouselSuggestionProcessor, defpackage.dw2
    public final void c(final int i, final AutocompleteMatch autocompleteMatch, PropertyModel propertyModel) {
        super.c(i, autocompleteMatch, propertyModel);
        List<AutocompleteMatch.b> list = autocompleteMatch.v;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        int i2 = 0;
        while (i2 < size) {
            PropertyModel propertyModel2 = new PropertyModel(na3.m);
            AutocompleteMatch.b bVar = list.get(i2);
            final String strE = TextUtils.isEmpty(bVar.a) ? bVar.b.e() : bVar.a;
            final GURL gurl = bVar.b;
            boolean z = bVar.c && this.h;
            propertyModel2.p(na3.a, strE);
            propertyModel2.n(na3.b, 1);
            propertyModel2.p(na3.i, new z8(this, 19, gurl));
            final int i3 = i2;
            final boolean z2 = z;
            List<AutocompleteMatch.b> list2 = list;
            propertyModel2.p(na3.j, new View.OnClickListener() { // from class: ui1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    wi1 wi1Var = this.e;
                    wi1Var.getClass();
                    int i4 = z2 ? 2 : 1;
                    oj2.U(i3, 15, "Omnibox.SuggestTiles.SelectedTileIndex");
                    oj2.U(i4, 3, "Omnibox.SuggestTiles.SelectedTileType");
                    ((d) wi1Var.d).h(i, autocompleteMatch, gurl);
                }
            });
            final int i4 = i2;
            propertyModel2.p(na3.k, new View.OnLongClickListener(autocompleteMatch, strE, i, i4) { // from class: vi1
                public final /* synthetic */ AutocompleteMatch f;
                public final /* synthetic */ int g;
                public final /* synthetic */ int h;

                {
                    this.g = i;
                    this.h = i4;
                }

                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    final d dVar = (d) this.e.d;
                    dVar.getClass();
                    final int i5 = this.g;
                    final int i6 = this.h;
                    dVar.m(this.f, new Runnable() { // from class: ma
                        @Override // java.lang.Runnable
                        public final void run() {
                            AutocompleteController autocompleteController = dVar.w;
                            if (autocompleteController.f == 0) {
                                return;
                            }
                            AutocompleteResult autocompleteResult = autocompleteController.g;
                            int i7 = i5;
                            if (autocompleteResult.a(i7, 3)) {
                                N.McljA_bE(autocompleteController.f, i7, i6);
                            }
                        }
                    });
                    return true;
                }
            });
            Context context = this.c;
            if (z) {
                PropertyModel.r<ColorStateList> rVar = na3.d;
                String str = yp.a;
                propertyModel2.p(rVar, bz.b(context, R$color.default_icon_color_secondary_tint_list));
                propertyModel2.p(na3.c, AppCompatResources.a(context, R$drawable.ic_suggestion_magnifier));
                propertyModel2.p(na3.h, context.getString(R$string.accessibility_omnibox_most_visited_tile_search, strE));
            } else {
                propertyModel2.p(na3.d, null);
                propertyModel2.p(na3.h, context.getString(R$string.accessibility_omnibox_most_visited_tile_navigate, strE, gurl.e()));
                propertyModel2.n(na3.f, context.getResources().getDimensionPixelSize(R$dimen.omnibox_carousel_icon_rounding_radius));
                this.e.b(gurl, true, new gx(17, propertyModel2));
            }
            arrayList.add(new xa1(0, propertyModel2));
            i2++;
            list = list2;
        }
        propertyModel.p(dd.a, arrayList);
        propertyModel.l(dd.c, false);
        propertyModel.p(dd.e, this.g);
    }

    @Override // defpackage.dw2
    public final boolean d(AutocompleteMatch autocompleteMatch, int i) {
        return autocompleteMatch.a == 29;
    }

    @Override // org.chromium.chrome.browser.omnibox.suggestions.carousel.BaseCarouselSuggestionProcessor
    public final int e() {
        return this.f;
    }

    @Override // defpackage.ee0
    public final void k(boolean z) {
        a aVar;
        if (z || (aVar = this.g) == null) {
            return;
        }
        aVar.a();
    }

    @Override // defpackage.ee0
    public final int m() {
        return 7;
    }

    @Override // defpackage.ee0
    public final PropertyModel n() {
        return new PropertyModel(dd.g);
    }
}
