package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.LocaleList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import defpackage.hg1;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;
import org.chromium.chrome.browser.omnibox.suggestions.h;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class r91 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ r91(Activity activity, BookmarkModel bookmarkModel, ArrayList arrayList, wp2 wp2Var) {
        this.e = 2;
        this.g = activity;
        this.f = bookmarkModel;
        this.h = arrayList;
        this.i = wp2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        tp2 tp2VarA;
        int i = this.e;
        int i2 = 6;
        Object obj = this.g;
        int i3 = 1;
        Object obj2 = this.i;
        Object obj3 = this.h;
        Object obj4 = this.f;
        switch (i) {
            case 0:
                s91 s91Var = (s91) obj4;
                TemplateUrlService templateUrlService = (TemplateUrlService) obj3;
                s91Var.getClass();
                xm1 xm1Var = new xm1(s91Var, i2, (Callback) obj2);
                if (N.MELaF8Vs(templateUrlService.c, templateUrlService) || c6.e()) {
                    xm1Var.onResult(Boolean.TRUE);
                } else {
                    xm1Var.onResult(Boolean.TRUE);
                }
                break;
            case 1:
                l.a aVar = (l.a) obj4;
                View view = (View) obj;
                View view2 = (View) obj3;
                PropertyModel propertyModel = (PropertyModel) obj2;
                SuggestionLayout suggestionLayout = aVar.c;
                if (suggestionLayout.i != null) {
                    LinearLayout linearLayout = suggestionLayout.j;
                    if (linearLayout != null) {
                        linearLayout.setVisibility(0);
                    }
                    h hVar = suggestionLayout.i;
                    ArrayList<AutocompleteMatch> arrayListA = kg2.a();
                    ArrayList arrayList = hVar.i;
                    arrayList.clear();
                    arrayList.addAll(arrayListA);
                    hVar.o();
                }
                boolean zC = pr1.c();
                ViewGroup viewGroup = aVar.a;
                if (zC) {
                    if (view.getParent() == null) {
                        viewGroup.addView(view);
                    }
                    if (view2.getParent() == null) {
                        viewGroup.addView(view2);
                        l.a(aVar, propertyModel);
                    }
                } else {
                    viewGroup.setVisibility(0);
                    if (view2.getParent() == null) {
                        viewGroup.addView(view2);
                    }
                    OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = aVar.b;
                    omniboxSuggestionsDropdown.getClass();
                    if (!pr1.c() && omniboxSuggestionsDropdown.getVisibility() != 0) {
                        omniboxSuggestionsDropdown.setVisibility(0);
                        tr1 tr1Var = omniboxSuggestionsDropdown.T0;
                        if (tr1Var != null && tr1Var.j != 0) {
                            tr1Var.I(-1);
                        }
                        omniboxSuggestionsDropdown.S0.E = true;
                    }
                }
                break;
            case 2:
                Activity activity = (Activity) obj;
                BookmarkModel bookmarkModel = (BookmarkModel) obj4;
                List<Tab> list = (List) obj3;
                wp2 wp2Var = (wp2) obj2;
                int i4 = lh.a;
                if (list.size() == 1) {
                    Tab tab = (Tab) list.get(0);
                    BookmarkId bookmarkIdA = lh.a(activity, bookmarkModel, tab.getTitle(), tab.v(), null, 0);
                    if (bookmarkIdA != null && bookmarkIdA.getType() == 0) {
                        oj2.U(Profile.c(n3.k0(tab.D(), tab.isIncognito())), 6, "Bookmarks.AddedPerProfileType");
                    }
                    if (bookmarkIdA == null) {
                        tp2VarA = tp2.a(activity.getString(R$string.bookmark_page_failed), new hh(), 1, 0);
                        tp2VarA.i = false;
                        n3.X0("EnhancedBookmarks.AddingFailed");
                    } else {
                        BookmarkItem bookmarkItemH = bookmarkModel.h(bookmarkModel.h(bookmarkIdA).e);
                        String str = bookmarkItemH == null ? "" : bookmarkItemH.a;
                        jh jhVar = new jh(activity, bookmarkIdA);
                        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                        if ((!sharedPreferencesManager.contains("enhanced_bookmark_last_used_parent_folder") ? null : BookmarkId.a(sharedPreferencesManager.g("enhanced_bookmark_last_used_parent_folder", null))) == null) {
                            tp2VarA = tp2.a(activity.getString(R$string.bookmark_page_saved_default), jhVar, 0, 0);
                        } else {
                            tp2VarA = tp2.a(str, jhVar, 0, 0);
                            tp2VarA.c = activity.getString(R$string.bookmark_page_saved_folder);
                        }
                        tp2VarA.i = false;
                        tp2VarA.d = activity.getString(R$string.bookmark_item_edit);
                        tp2VarA.e = null;
                    }
                    wp2Var.c(tp2VarA);
                } else {
                    LocaleList locales = activity.getResources().getConfiguration().getLocales();
                    BookmarkId bookmarkIdB = bookmarkModel.b(activity.getString(R$string.tab_selection_editor_add_bookmarks_folder_name, DateFormat.getDateTimeInstance(2, 1, locales.size() > 0 ? locales.get(0) : activity.getResources().getConfiguration().locale).format(new Date(System.currentTimeMillis()))), bookmarkModel.k());
                    int i5 = 0;
                    for (Tab tab2 : list) {
                        if (bookmarkModel.f(lh.a(activity, bookmarkModel, tab2.getTitle(), tab2.v(), bookmarkIdB, 0))) {
                            i5++;
                        }
                    }
                    oj2.Q(i5, "Android.TabMultiSelectV2.BookmarkTabsCount");
                    tp2 tp2VarA2 = tp2.a(activity.getString(R$string.bookmark_page_saved_default), new kh(activity, bookmarkIdB), 0, 0);
                    tp2VarA2.i = false;
                    tp2VarA2.d = activity.getString(R$string.bookmark_item_edit);
                    tp2VarA2.e = null;
                    wp2Var.c(tp2VarA2);
                }
                break;
            default:
                hg1 hg1Var = (hg1) obj4;
                Animator animator = (Animator) obj;
                Animator animator2 = (Animator) obj3;
                Runnable runnable = (Runnable) obj2;
                if (!((hg1Var.f == animator && hg1Var.g == animator2) ? false : true)) {
                    hg1Var.e.cancel();
                    hg1Var.e.removeAllListeners();
                    AnimatorSet animatorSet = new AnimatorSet();
                    hg1Var.e = animatorSet;
                    animatorSet.play(animator);
                    hg1Var.e.play(animator2);
                    hg1Var.e.addListener(new hg1.a(new gg1(hg1Var, runnable, i3)));
                    ((jq) hg1Var.d).b();
                    hg1Var.i.onResult(hg1Var.e);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ r91(Object obj, Object obj2, Object obj3, Object obj4, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
    }
}
