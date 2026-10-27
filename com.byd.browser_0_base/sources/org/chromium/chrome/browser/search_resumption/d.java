package org.chromium.chrome.browser.search_resumption;

import android.text.TextUtils;
import defpackage.c30;
import defpackage.pq2;
import java.util.List;
import org.chromium.base.TraceEvent;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SearchResumptionTileBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public a a;

    /* JADX INFO: compiled from: SearchResumptionTileBuilder.java */
    public interface a {
    }

    public d(pq2 pq2Var) {
        this.a = pq2Var;
    }

    public final void a(List<AutocompleteMatch> list, SearchResumptionTileContainerView searchResumptionTileContainerView) {
        TraceEvent traceEventH0 = TraceEvent.H0("SearchSuggestionTileProvider.addTileSection", null);
        try {
            int iMin = Math.min(list.size(), 3);
            int i = 0;
            for (int i2 = 0; i < iMin && i2 < list.size(); i2++) {
                AutocompleteMatch autocompleteMatch = list.get(i2);
                boolean z = true;
                if (!(!TextUtils.isEmpty(autocompleteMatch.d)) || autocompleteMatch.a != 8) {
                    z = false;
                }
                if (z) {
                    searchResumptionTileContainerView.addView(c(autocompleteMatch.d, autocompleteMatch.j, searchResumptionTileContainerView));
                    i++;
                }
            }
            int childCount = searchResumptionTileContainerView.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                ((SearchResumptionTileView) searchResumptionTileContainerView.getChildAt(i3)).a(i3, childCount);
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

    public final void b(String[] strArr, GURL[] gurlArr, SearchResumptionTileContainerView searchResumptionTileContainerView) {
        TraceEvent traceEventH0 = TraceEvent.H0("SearchSuggestionTileProvider.addTileSection", null);
        try {
            int iMin = Math.min(gurlArr.length, 3);
            int i = 0;
            for (int i2 = 0; i < iMin && i2 < gurlArr.length; i2++) {
                if (!TextUtils.isEmpty(strArr[i2])) {
                    searchResumptionTileContainerView.addView(c(strArr[i2], gurlArr[i2], searchResumptionTileContainerView));
                    i++;
                }
            }
            int childCount = searchResumptionTileContainerView.getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                ((SearchResumptionTileView) searchResumptionTileContainerView.getChildAt(i3)).a(i3, childCount);
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

    public final SearchResumptionTileView c(String str, GURL gurl, SearchResumptionTileContainerView searchResumptionTileContainerView) {
        SearchResumptionTileView searchResumptionTileViewA = searchResumptionTileContainerView.a();
        searchResumptionTileViewA.e = gurl;
        searchResumptionTileViewA.f.setText(str);
        searchResumptionTileViewA.setContentDescription(searchResumptionTileViewA.f.getText());
        searchResumptionTileViewA.setOnClickListener(new c30(searchResumptionTileViewA, 5, this.a));
        return searchResumptionTileViewA;
    }
}
