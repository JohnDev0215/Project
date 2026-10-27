package defpackage;

import android.view.View;
import android.widget.ImageView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.tabs.TabLayout;
import defpackage.k81;
import java.util.ArrayList;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.feed.sections.SectionHeaderBadgeDrawable;
import org.chromium.chrome.browser.feed.sections.SectionHeaderView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.c;
import org.chromium.ui.modelutil.d;
import org.chromium.ui.modelutil.h;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: SectionHeaderViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class ch2 implements j.a<PropertyModel, SectionHeaderView, v62>, c.a<h<PropertyModel, v62>, SectionHeaderView, v62> {
    public static void g(h hVar, SectionHeaderView sectionHeaderView, int i, int i2, v62 v62Var) {
        TabLayout.g gVarB;
        SectionHeaderView.b bVar;
        SectionHeaderView.c cVar;
        SectionHeaderBadgeDrawable sectionHeaderBadgeDrawable;
        PropertyModel propertyModel = (PropertyModel) hVar.get(0);
        if (v62Var == null || v62Var == yg2.a || v62Var == yg2.b || v62Var == yg2.e) {
            sectionHeaderView.setHeaderText((String) propertyModel.j(yg2.a));
            for (int i3 = i; i3 < i + i2; i3++) {
                PropertyModel propertyModel2 = (PropertyModel) hVar.get(i3);
                boolean zK = propertyModel2.k(yg2.b);
                String str = (String) propertyModel2.j(yg2.a);
                String str2 = (String) propertyModel2.j(yg2.e);
                boolean zK2 = propertyModel2.k(yg2.f);
                TabLayout.g gVarB2 = sectionHeaderView.b(i3);
                if (gVarB2 != null) {
                    SectionHeaderView.b bVar2 = (SectionHeaderView.b) gVarB2.a;
                    bVar2.d = str;
                    bVar2.a = zK;
                    bVar2.c = str2;
                    bVar2.e = zK2;
                    sectionHeaderView.a(gVarB2);
                }
            }
        }
        if (v62Var == null || v62Var == yg2.f) {
            for (int i4 = i; i4 < i + i2; i4++) {
                if (((PropertyModel) hVar.get(i4)).k(yg2.f) && sectionHeaderView.f != null && (gVarB = sectionHeaderView.b(i4)) != null && (cVar = (bVar = (SectionHeaderView.b) gVarB.a).b) != null && (sectionHeaderBadgeDrawable = cVar.f) != null) {
                    sectionHeaderBadgeDrawable.c();
                    bVar.e = true;
                }
            }
        }
        if (v62Var == null || v62Var == yg2.c) {
            for (int i5 = i; i5 < i + i2; i5++) {
                ho3 ho3Var = (ho3) ((PropertyModel) hVar.get(i5)).j(yg2.c);
                TabLayout.g gVarB3 = sectionHeaderView.b(i5);
                if (gVarB3 != null) {
                    if (ho3Var == ho3.GONE) {
                        gVarB3.i.setPadding(gVarB3.i.getPaddingLeft(), 0, sectionHeaderView.getResources().getDimensionPixelOffset(R$dimen.feed_header_tab_extra_margin_right) + gVarB3.i.getPaddingRight(), 0);
                    }
                    ImageView imageView = (ImageView) gVarB3.i.findViewById(R$id.options_indicator);
                    int iOrdinal = ho3Var.ordinal();
                    imageView.setVisibility(iOrdinal != 0 ? iOrdinal != 1 ? 8 : 4 : 0);
                    if (ho3Var == ho3.VISIBLE) {
                        gVarB3.i.setClickable(true);
                        sectionHeaderView.c(i5, false);
                    } else {
                        gVarB3.i.setOnLongClickListener(null);
                        gVarB3.i.setLongClickable(false);
                        om3.j(gVarB3.i, AccessibilityNodeInfoCompat.a.h, null, null);
                    }
                }
            }
        }
        if (v62Var == null || v62Var == yg2.d) {
            for (int i6 = i; i6 < i + i2; i6++) {
                sectionHeaderView.c(i6, ((PropertyModel) hVar.get(i6)).k(yg2.d));
            }
        }
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final void a(d dVar, int i, int i2, Object obj) {
        h hVar = (h) dVar;
        SectionHeaderView sectionHeaderView = (SectionHeaderView) obj;
        for (int i3 = i; i3 < i2 + i; i3++) {
            TabLayout tabLayout = sectionHeaderView.f;
            if (tabLayout != null) {
                TabLayout.g gVarL = tabLayout.l();
                gVarL.c(R$layout.new_tab_page_section_tab);
                gVarL.a = new SectionHeaderView.b();
                sectionHeaderView.f.a(gVarL);
                gVarL.i.setClipToPadding(false);
                gVarL.i.setClipChildren(false);
            }
        }
        g(hVar, sectionHeaderView, i, i2, null);
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        SectionHeaderView sectionHeaderView = (SectionHeaderView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.n nVar = xg2.a;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            sectionHeaderView.setTextsEnabled(zK);
            if (zK) {
                PropertyModel.p pVar = xg2.c;
                if (propertyModel.h(pVar) <= ((h) propertyModel.j(xg2.b)).size()) {
                    sectionHeaderView.setActiveTab(propertyModel.h(pVar));
                    return;
                }
                return;
            }
            return;
        }
        PropertyModel.p pVar2 = xg2.c;
        if (v62Var == pVar2) {
            if (propertyModel.h(pVar2) <= ((h) propertyModel.j(xg2.b)).size()) {
                sectionHeaderView.setActiveTab(propertyModel.h(pVar2));
                return;
            }
            return;
        }
        PropertyModel.r<is1> rVar = xg2.d;
        if (v62Var == rVar) {
            sectionHeaderView.setTabChangeListener((is1) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<k81.a> rVar2 = xg2.f;
        if (v62Var == rVar2 || v62Var == xg2.e) {
            sectionHeaderView.h.setOnClickListener(new uf(sectionHeaderView, 1, (ya1) propertyModel.j(xg2.e), (k81.a) propertyModel.j(rVar2)));
            return;
        }
        PropertyModel.n nVar2 = xg2.g;
        if (v62Var == nVar2) {
            sectionHeaderView.setTabMode(propertyModel.k(nVar2));
            return;
        }
        PropertyModel.r<ho3> rVar3 = xg2.i;
        if (v62Var == rVar3) {
            sectionHeaderView.setIndicatorVisibility((ho3) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.n nVar3 = xg2.h;
        if (v62Var == nVar3) {
            sectionHeaderView.setIsLogo(propertyModel.k(nVar3));
            return;
        }
        PropertyModel.r<View> rVar4 = xg2.j;
        if (v62Var == rVar4) {
            sectionHeaderView.setOptionsPanel((View) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.r<View> rVar5 = xg2.k;
        if (v62Var == rVar5) {
            sectionHeaderView.setStickyHeaderOptionsPanel((View) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.j jVar = xg2.l;
        if (v62Var == jVar) {
            sectionHeaderView.setToolbarHeight(propertyModel.h(jVar));
            return;
        }
        PropertyModel.n nVar4 = xg2.m;
        if (v62Var == nVar4) {
            sectionHeaderView.setStickyHeaderVisible(propertyModel.k(nVar4));
            return;
        }
        PropertyModel.p pVar3 = xg2.n;
        if (v62Var == pVar3) {
            sectionHeaderView.d(propertyModel.h(pVar3));
        }
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final /* bridge */ /* synthetic */ void e(d dVar, int i, Object obj, Object obj2, int i2) {
        g((h) dVar, (SectionHeaderView) obj, i, i2, (v62) obj2);
    }

    @Override // org.chromium.ui.modelutil.c.a
    public final void f(d dVar, int i, int i2, Object obj) {
        SectionHeaderView sectionHeaderView = (SectionHeaderView) obj;
        if (((h) dVar).size() == 0) {
            TabLayout tabLayout = sectionHeaderView.f;
            if (tabLayout != null) {
                tabLayout.n();
                return;
            }
            return;
        }
        int i3 = i2 + i;
        while (true) {
            i3--;
            if (i3 < i) {
                return;
            }
            TabLayout tabLayout2 = sectionHeaderView.f;
            if (tabLayout2 != null) {
                TabLayout.g gVar = tabLayout2.f;
                int i4 = gVar != null ? gVar.e : 0;
                tabLayout2.o(i3);
                ArrayList<TabLayout.g> arrayList = tabLayout2.e;
                TabLayout.g gVarRemove = arrayList.remove(i3);
                if (gVarRemove != null) {
                    gVarRemove.h = null;
                    gVarRemove.i = null;
                    gVarRemove.a = null;
                    gVarRemove.b = null;
                    gVarRemove.j = -1;
                    gVarRemove.c = null;
                    gVarRemove.d = null;
                    gVarRemove.e = -1;
                    gVarRemove.f = null;
                    TabLayout.W.b(gVarRemove);
                }
                int size = arrayList.size();
                for (int i5 = i3; i5 < size; i5++) {
                    arrayList.get(i5).e = i5;
                }
                if (i4 == i3) {
                    tabLayout2.p(arrayList.isEmpty() ? null : arrayList.get(Math.max(0, i3 - 1)), true);
                }
            }
        }
    }
}
