package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.transition.TransitionManager;
import android.util.Log;
import android.util.Pair;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.es2;
import defpackage.iq2;
import defpackage.jh2;
import defpackage.jq2;
import defpackage.k4;
import defpackage.le3;
import defpackage.ne3;
import defpackage.u1;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsView;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.j;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout;
import org.chromium.chrome.browser.omnibox.suggestions.e;
import org.chromium.chrome.browser.omnibox.suggestions.i;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.password_entry_edit.FederatedCredentialFragmentView;
import org.chromium.chrome.browser.tasks.tab_management.s;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.chrome.browser.ui.signin.fre.SigninFirstRunView;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.components.browser_ui.widget.tile.TileView;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.payments.R$string;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.n;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.TextViewWithLeading;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ag implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ ag(int i) {
        this.a = i;
    }

    private static void a(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        jh2 jh2Var = (jh2) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.l<String> lVar = ih2.a;
        if (lVar == v62Var) {
            jh2Var.d.setText((CharSequence) propertyModel.j(lVar));
            return;
        }
        PropertyModel.l<Pair<Drawable, Boolean>> lVar2 = ih2.b;
        if (lVar2 == v62Var) {
            Pair pair = (Pair) propertyModel.j(lVar2);
            jh2Var.f.setImageDrawable((Drawable) pair.first);
            if (((Boolean) pair.second).booleanValue()) {
                ImageView imageView = jh2Var.f;
                imageView.getLayoutParams().height = -2;
                imageView.getLayoutParams().width = -2;
                return;
            }
            return;
        }
        PropertyModel.l<CharSequence> lVar3 = ih2.c;
        if (lVar3 == v62Var) {
            jh2Var.e.setText((CharSequence) propertyModel.j(lVar3));
            return;
        }
        PropertyModel.l<CharSequence> lVar4 = ih2.d;
        if (lVar4 == v62Var) {
            jh2Var.g.setText((CharSequence) propertyModel.j(lVar4));
            return;
        }
        PropertyModel.l<CharSequence> lVar5 = ih2.e;
        if (lVar5 == v62Var) {
            jh2Var.h.setText((CharSequence) propertyModel.j(lVar5));
            return;
        }
        PropertyModel.l<jh2.a> lVar6 = ih2.f;
        if (lVar6 == v62Var) {
            jh2.a aVar = (jh2.a) propertyModel.j(lVar6);
            jh2Var.k.setVisibility(aVar.a ? 0 : 8);
            Context context = jh2Var.c;
            jh2Var.k.setText(iq2.a(context.getResources().getString(R$string.secure_payment_confirmation_opt_out_label, context.getResources().getString(DeviceFormFactor.a(context) ? R$string.secure_payment_confirmation_this_tablet_label : R$string.secure_payment_confirmation_this_phone_label), aVar.b), new iq2.a(new un1(context, new l51(20, aVar.c)), "BEGIN_LINK", "END_LINK")));
            return;
        }
        if (ih2.g == v62Var) {
            jh2Var.i.setOnClickListener(new cy0(9, propertyModel));
        } else if (ih2.h == v62Var) {
            jh2Var.j.setOnClickListener(new ij3(12, propertyModel));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        int color;
        qi0 qi0Var;
        RecyclerView recyclerView;
        z = false;
        boolean z = false;
        z = false;
        boolean z2 = false;
        Object[] objArr = 0;
        int i = 7;
        int i2 = 3;
        int i3 = 6;
        int i4 = 8;
        switch (this.a) {
            case 0:
                return;
            case 1:
                lg.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                return;
            case 2:
                return;
            case 3:
                PropertyModel propertyModel = (PropertyModel) kVar;
                FeedOptionsView feedOptionsView = (FeedOptionsView) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.n nVar = el0.a;
                if (v62Var == nVar) {
                    feedOptionsView.setVisibility(propertyModel.k(nVar));
                    return;
                }
                return;
            case 4:
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                k4 k4Var = (k4) obj;
                v62 v62Var2 = (v62) obj2;
                PropertyModel.l<Callback<Integer>> lVar = c.b;
                if (v62Var2 == lVar) {
                    k4Var.f = (Callback) propertyModel2.j(lVar);
                    return;
                }
                PropertyModel.n nVar2 = c.a;
                if (v62Var2 == nVar2) {
                    boolean zK = propertyModel2.k(nVar2);
                    ji jiVar = k4Var.e;
                    if (!zK) {
                        jiVar.c(k4Var, true);
                        return;
                    }
                    k4.a aVar = k4Var.i;
                    jiVar.g(aVar);
                    if (jiVar.d(k4Var, true)) {
                        return;
                    }
                    k4Var.f.onResult(0);
                    jiVar.e(aVar);
                    return;
                }
                PropertyModel.l<String> lVar2 = c.d;
                if (v62Var2 == lVar2) {
                    String str = String.format(k4Var.h.getResources().getString(qx1.c() ? org.chromium.chrome.R$string.all_passwords_bottom_sheet_subtitle : org.chromium.chrome.R$string.all_passwords_bottom_sheet_warning_dialog_message_first), oj2.j(2, new GURL((String) propertyModel2.j(lVar2))));
                    int i5 = R$id.sheet_warning;
                    LinearLayout linearLayout = k4Var.h;
                    ((TextView) linearLayout.findViewById(i5)).setText(str);
                    if (qx1.c()) {
                        ((TextView) linearLayout.findViewById(R$id.sheet_warning_second)).setVisibility(8);
                        return;
                    }
                    return;
                }
                PropertyModel.l<Callback<String>> lVar3 = c.e;
                if (v62Var2 == lVar3) {
                    ((SearchView) k4Var.h.findViewById(R$id.all_passwords_search_view)).setOnQueryTextListener(new l4((Callback) propertyModel2.j(lVar3)));
                    return;
                }
                PropertyModel.l<p81<xa1>> lVar4 = c.c;
                if (v62Var2 == lVar4) {
                    k4Var.g.setAdapter(new l(new n((p81) propertyModel2.j(lVar4), new qh1(i), new wd0(11)), new pd(i4)));
                    return;
                }
                return;
            case 5:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                final UrlBar urlBar = (UrlBar) obj;
                v62 v62Var3 = (v62) obj2;
                PropertyModel.r<ActionMode.Callback> rVar = org.chromium.chrome.browser.omnibox.j.a;
                if (rVar.equals(v62Var3)) {
                    ActionMode.Callback callback = (ActionMode.Callback) propertyModel3.j(rVar);
                    if (callback == null && urlBar.getCustomSelectionActionModeCallback() == null) {
                        return;
                    }
                    urlBar.setCustomSelectionActionModeCallback(callback);
                    return;
                }
                PropertyModel.n nVar3 = org.chromium.chrome.browser.omnibox.j.b;
                if (nVar3.equals(v62Var3)) {
                    urlBar.setAllowFocus(propertyModel3.k(nVar3));
                    return;
                }
                PropertyModel.r<j.a> rVar2 = org.chromium.chrome.browser.omnibox.j.c;
                if (rVar2.equals(v62Var3)) {
                    j.a aVar2 = (j.a) propertyModel3.j(rVar2);
                    if (urlBar.f()) {
                        String str2 = aVar2.a;
                        String str3 = aVar2.b;
                        if (!TextUtils.isEmpty(str3)) {
                            urlBar.q = true;
                        }
                        jq2 jq2Var = urlBar.l;
                        if (jq2Var != null) {
                            jq2Var.h = true;
                            String string = str2.toString();
                            String string2 = str3.toString();
                            int length = string.length();
                            int length2 = string.length();
                            pa paVar = jq2Var.d;
                            paVar.a = string;
                            paVar.b = string2;
                            paVar.c = length;
                            paVar.d = length2;
                            jq2.a aVar3 = jq2Var.f;
                            if (aVar3 != null) {
                                aVar3.d();
                                jq2Var.f.e();
                            }
                            jq2Var.h = false;
                            return;
                        }
                        return;
                    }
                    return;
                }
                PropertyModel.r<UrlBar.c> rVar3 = org.chromium.chrome.browser.omnibox.j.d;
                if (rVar3.equals(v62Var3)) {
                    urlBar.setDelegate((UrlBar.c) propertyModel3.j(rVar3));
                    return;
                }
                PropertyModel.r<Callback<Boolean>> rVar4 = org.chromium.chrome.browser.omnibox.j.e;
                if (rVar4.equals(v62Var3)) {
                    final Callback callback2 = (Callback) propertyModel3.j(rVar4);
                    urlBar.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: tj3
                        @Override // android.view.View.OnFocusChangeListener
                        public final void onFocusChange(View view, boolean z3) {
                            if (z3) {
                                urlBar.setIgnoreTextChangesForAutocomplete(false);
                            }
                            callback2.onResult(Boolean.valueOf(z3));
                        }
                    });
                    return;
                }
                PropertyModel.n nVar4 = org.chromium.chrome.browser.omnibox.j.f;
                if (nVar4.equals(v62Var3)) {
                    urlBar.setCursorVisible(propertyModel3.k(nVar4));
                    return;
                }
                PropertyModel.r<UrlBar.d> rVar5 = org.chromium.chrome.browser.omnibox.j.g;
                if (rVar5.equals(v62Var3)) {
                    urlBar.setTextContextMenuDelegate((UrlBar.d) propertyModel3.j(rVar5));
                    return;
                }
                PropertyModel.r<j.b> rVar6 = org.chromium.chrome.browser.omnibox.j.h;
                if (rVar6.equals(v62Var3)) {
                    j.b bVar = (j.b) propertyModel3.j(rVar6);
                    urlBar.setIgnoreTextChangesForAutocomplete(true);
                    urlBar.setText(bVar.a);
                    urlBar.setTextForAutofillServices(bVar.b);
                    int i6 = bVar.c;
                    if (i6 == 1) {
                        urlBar.Q = bVar.d;
                    } else {
                        urlBar.Q = 0;
                    }
                    urlBar.R = i6;
                    if (urlBar.isLayoutRequested()) {
                        urlBar.F = urlBar.R != 0;
                    } else {
                        urlBar.h(urlBar.R);
                    }
                    urlBar.setIgnoreTextChangesForAutocomplete(false);
                    if (urlBar.hasFocus()) {
                        int i7 = bVar.e;
                        if (i7 == 0) {
                            urlBar.selectAll();
                            return;
                        } else {
                            if (i7 == 1) {
                                urlBar.setSelection(urlBar.getText().length());
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                PropertyModel.p pVar = org.chromium.chrome.browser.omnibox.j.l;
                if (pVar.equals(v62Var3)) {
                    int iH = propertyModel3.h(pVar);
                    int iB = rr1.b(urlBar.getContext(), iH);
                    int iC = rr1.c(urlBar.getContext(), iH);
                    urlBar.setTextColor(iB);
                    urlBar.setHintTextColor(iC);
                    return;
                }
                PropertyModel.n nVar5 = org.chromium.chrome.browser.omnibox.j.m;
                if (!nVar5.equals(v62Var3)) {
                    PropertyModel.r<Callback<Integer>> rVar7 = org.chromium.chrome.browser.omnibox.j.i;
                    if (rVar7.equals(v62Var3)) {
                        urlBar.setUrlDirectionListener((Callback) propertyModel3.j(rVar7));
                        return;
                    }
                    PropertyModel.r<UrlBar.e> rVar8 = org.chromium.chrome.browser.omnibox.j.j;
                    if (rVar8.equals(v62Var3)) {
                        urlBar.setUrlTextChangeListener((UrlBar.e) propertyModel3.j(rVar8));
                        return;
                    }
                    PropertyModel.r<TextWatcher> rVar9 = org.chromium.chrome.browser.omnibox.j.k;
                    if (rVar9.equals(v62Var3)) {
                        urlBar.setTextChangedListener((TextWatcher) propertyModel3.j(rVar9));
                        return;
                    }
                    PropertyModel.r<it3> rVar10 = org.chromium.chrome.browser.omnibox.j.n;
                    if (rVar10.equals(v62Var3)) {
                        urlBar.setWindowDelegate((it3) propertyModel3.j(rVar10));
                        return;
                    }
                    return;
                }
                boolean zK2 = propertyModel3.k(nVar5);
                int i8 = R$id.highlight_color;
                Object tag = urlBar.getTag(i8);
                if (tag == null || !(tag instanceof Integer)) {
                    int highlightColor = urlBar.getHighlightColor();
                    urlBar.setTag(i8, Integer.valueOf(highlightColor));
                    color = highlightColor;
                } else {
                    color = ((Integer) tag).intValue();
                }
                if (zK2) {
                    color = urlBar.getResources().getColor(R$color.text_highlight_color_incognito);
                }
                urlBar.setHighlightColor(color);
                try {
                    Drawable textCursorDrawable = urlBar.getTextCursorDrawable();
                    Drawable textSelectHandle = urlBar.getTextSelectHandle();
                    Drawable textSelectHandleLeft = urlBar.getTextSelectHandleLeft();
                    Drawable textSelectHandleRight = urlBar.getTextSelectHandleRight();
                    int color2 = zK2 ? urlBar.getContext().getColor(R$color.default_control_color_active_dark) : oj2.p(urlBar, R$attr.colorPrimary);
                    textCursorDrawable.mutate().setTint(color2);
                    textSelectHandle.mutate().setTint(color2);
                    textSelectHandleLeft.mutate().setTint(color2);
                    textSelectHandleRight.mutate().setTint(color2);
                    return;
                } catch (Resources.NotFoundException e) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        throw e;
                    }
                    Log.e("cr_UrlBarViewBinder", "Failed to access the cursor or handle drawable, skipped tinting.", e);
                    ((Callback) urlBar.getTag(R$id.report_exception_callback)).onResult(new Throwable("This is not a crash. See https://crbug.com/1263630 for details.", e));
                    return;
                }
            case 6:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                l.a aVar4 = (l.a) obj;
                v62 v62Var4 = (v62) obj2;
                PropertyModel.n nVar6 = i.a;
                if (nVar6.equals(v62Var4)) {
                    boolean zK3 = propertyModel4.k(nVar6);
                    ViewGroup viewGroup = aVar4.b.getViewGroup();
                    SuggestionLayout suggestionLayout = aVar4.c;
                    ViewGroup viewGroup2 = suggestionLayout.getViewGroup();
                    Runnable runnable = aVar4.d;
                    ViewGroup viewGroup3 = aVar4.a;
                    if (runnable != null) {
                        viewGroup3.removeCallbacks(runnable);
                    }
                    if (zK3) {
                        if (aVar4.d == null) {
                            aVar4.d = new r91(aVar4, viewGroup2, viewGroup, propertyModel4, 1);
                        }
                        viewGroup3.post(aVar4.d);
                        return;
                    }
                    uh3.l(viewGroup);
                    uh3.l(viewGroup2);
                    if (pr1.c()) {
                        return;
                    }
                    OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = aVar4.b;
                    omniboxSuggestionsDropdown.getClass();
                    if (!pr1.c() && omniboxSuggestionsDropdown.getVisibility() == 0) {
                        omniboxSuggestionsDropdown.setVisibility(8);
                        omniboxSuggestionsDropdown.getRecycledViewPool().a();
                    }
                    if (!pr1.c() && suggestionLayout.getVisibility() == 0) {
                        suggestionLayout.setVisibility(8);
                    }
                    viewGroup3.setVisibility(4);
                    return;
                }
                PropertyModel.r<e> rVar11 = i.b;
                if (rVar11.equals(v62Var4)) {
                    aVar4.b.setEmbedder((e) propertyModel4.j(rVar11));
                    aVar4.c.setEmbedder((e) propertyModel4.j(rVar11));
                    return;
                }
                PropertyModel.r<OmniboxSuggestionsDropdown.a> rVar12 = i.f;
                if (rVar12.equals(v62Var4)) {
                    aVar4.b.setGestureObserver((OmniboxSuggestionsDropdown.a) propertyModel4.j(rVar12));
                    return;
                }
                PropertyModel.r<Callback<Integer>> rVar13 = i.g;
                if (rVar13.equals(v62Var4)) {
                    aVar4.b.setHeightChangeListener((Callback) propertyModel4.j(rVar13));
                    return;
                }
                PropertyModel.r<Runnable> rVar14 = i.h;
                if (rVar14.equals(v62Var4)) {
                    aVar4.b.setSuggestionDropdownScrollListener((Runnable) propertyModel4.j(rVar14));
                    return;
                }
                PropertyModel.r<Runnable> rVar15 = i.i;
                if (rVar15.equals(v62Var4)) {
                    aVar4.b.setSuggestionDropdownOverscrolledToTopListener((Runnable) propertyModel4.j(rVar15));
                    return;
                }
                PropertyModel.n nVar7 = i.d;
                if (nVar7.equals(v62Var4)) {
                    if (propertyModel4.k(nVar7)) {
                        OmniboxSuggestionsDropdown omniboxSuggestionsDropdown2 = aVar4.b;
                        omniboxSuggestionsDropdown2.getClass();
                        PostTask.b(rh3.a, new ql0(22, omniboxSuggestionsDropdown2), 300L);
                        return;
                    }
                    return;
                }
                PropertyModel.r<ya1> rVar16 = i.c;
                if (rVar16.equals(v62Var4)) {
                    if (pr1.c()) {
                        ((ya1) propertyModel4.j(rVar16)).g(new org.chromium.chrome.browser.omnibox.suggestions.j(aVar4, propertyModel4));
                        return;
                    } else {
                        ((ya1) propertyModel4.j(rVar16)).g(new org.chromium.chrome.browser.omnibox.suggestions.k(aVar4));
                        return;
                    }
                }
                PropertyModel.p pVar2 = i.e;
                if (pVar2.equals(v62Var4)) {
                    OmniboxSuggestionsDropdown omniboxSuggestionsDropdown3 = aVar4.b;
                    int iArgb = propertyModel4.h(pVar2) == 2 ? omniboxSuggestionsDropdown3.Q0 : omniboxSuggestionsDropdown3.P0;
                    if (!omniboxSuggestionsDropdown3.isHardwareAccelerated() && Color.alpha(iArgb) == 255) {
                        iArgb = Color.argb(254, Color.red(iArgb), Color.green(iArgb), Color.blue(iArgb));
                    }
                    omniboxSuggestionsDropdown3.setBackground(new ColorDrawable(iArgb));
                    return;
                }
                return;
            case 7:
                gw2.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                return;
            case 8:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                TileView tileView = (TileView) obj;
                v62 v62Var5 = (v62) obj2;
                PropertyModel.r<String> rVar17 = na3.a;
                if (v62Var5 == rVar17) {
                    ((TextView) tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.tile_view_title)).setText((CharSequence) propertyModel5.j(rVar17));
                    return;
                }
                PropertyModel.p pVar3 = na3.b;
                if (v62Var5 == pVar3) {
                    TextView textView = (TextView) tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.tile_view_title);
                    int iH2 = propertyModel5.h(pVar3);
                    textView.setLines(iH2 > 0 ? iH2 : 1);
                    return;
                }
                PropertyModel.r<Drawable> rVar18 = na3.c;
                if (v62Var5 == rVar18) {
                    ((ImageView) tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.tile_view_icon)).setImageDrawable((Drawable) propertyModel5.j(rVar18));
                    return;
                }
                PropertyModel.r<ColorStateList> rVar19 = na3.d;
                if (v62Var5 == rVar19) {
                    ((ImageView) tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.tile_view_icon)).setImageTintList((ColorStateList) propertyModel5.j(rVar19));
                    return;
                }
                PropertyModel.n nVar8 = na3.g;
                if (v62Var5 == nVar8) {
                    tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.offline_badge).setVisibility(propertyModel5.k(nVar8) ? 0 : 8);
                    return;
                }
                PropertyModel.n nVar9 = na3.e;
                if (v62Var5 == nVar9) {
                    boolean zK4 = propertyModel5.k(nVar9);
                    int dimensionPixelSize = tileView.getResources().getDimensionPixelSize(zK4 ? R$dimen.tile_view_icon_size : R$dimen.tile_view_icon_size_modern);
                    View viewFindViewById = tileView.findViewById(org.chromium.components.browser_ui.widget.R$id.tile_view_icon);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
                    marginLayoutParams.width = dimensionPixelSize;
                    marginLayoutParams.height = dimensionPixelSize;
                    marginLayoutParams.topMargin = tileView.getResources().getDimensionPixelSize(zK4 ? R$dimen.tile_view_icon_background_margin_top_modern : R$dimen.tile_view_icon_margin_top_modern);
                    viewFindViewById.setLayoutParams(marginLayoutParams);
                    tileView.setRoundingRadius(propertyModel5.k(nVar9) ? tileView.getResources().getDimensionPixelSize(R$dimen.tile_view_icon_size) : propertyModel5.h(na3.f));
                    return;
                }
                PropertyModel.p pVar4 = na3.f;
                if (v62Var5 == pVar4) {
                    tileView.setRoundingRadius(propertyModel5.k(nVar9) ? tileView.getResources().getDimensionPixelSize(R$dimen.tile_view_icon_size) : propertyModel5.h(pVar4));
                    return;
                }
                PropertyModel.r<Runnable> rVar20 = na3.i;
                if (v62Var5 == rVar20) {
                    tileView.setOnFocusViaSelectionListener((Runnable) propertyModel5.j(rVar20));
                    return;
                }
                PropertyModel.r<View.OnClickListener> rVar21 = na3.j;
                if (v62Var5 == rVar21) {
                    tileView.setOnClickListener((View.OnClickListener) propertyModel5.j(rVar21));
                    return;
                }
                PropertyModel.r<View.OnLongClickListener> rVar22 = na3.k;
                if (v62Var5 == rVar22) {
                    tileView.setOnLongClickListener((View.OnLongClickListener) propertyModel5.j(rVar22));
                    return;
                }
                PropertyModel.r<View.OnCreateContextMenuListener> rVar23 = na3.l;
                if (v62Var5 == rVar23) {
                    tileView.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) propertyModel5.j(rVar23));
                    return;
                }
                PropertyModel.r<CharSequence> rVar24 = na3.h;
                if (v62Var5 == rVar24) {
                    tileView.setContentDescription((CharSequence) propertyModel5.j(rVar24));
                    return;
                }
                return;
            case 9:
                PropertyModel propertyModel6 = (PropertyModel) kVar;
                FederatedCredentialFragmentView federatedCredentialFragmentView = (FederatedCredentialFragmentView) obj;
                v62 v62Var6 = (v62) obj2;
                PropertyModel.r<CredentialEntryFragmentViewBase.b> rVar25 = org.chromium.chrome.browser.password_entry_edit.e.a;
                if (v62Var6 == rVar25) {
                    CredentialEntryFragmentViewBase.b bVar2 = (CredentialEntryFragmentViewBase.b) propertyModel6.j(rVar25);
                    federatedCredentialFragmentView.i0 = bVar2;
                    ((ChromeImageButton) federatedCredentialFragmentView.f12J.findViewById(R$id.copy_username_button)).setOnClickListener(new n40(federatedCredentialFragmentView, i, bVar2));
                    return;
                }
                PropertyModel.l<String> lVar5 = org.chromium.chrome.browser.password_entry_edit.e.b;
                if (v62Var6 == lVar5) {
                    ((TextView) federatedCredentialFragmentView.f12J.findViewById(R$id.url_or_app)).setText((String) propertyModel6.j(lVar5));
                    return;
                }
                if (v62Var6 == org.chromium.chrome.browser.password_entry_edit.e.i) {
                    federatedCredentialFragmentView.M0();
                    return;
                }
                PropertyModel.r<String> rVar26 = org.chromium.chrome.browser.password_entry_edit.e.c;
                if (v62Var6 == rVar26) {
                    federatedCredentialFragmentView.k0.setText((String) propertyModel6.j(rVar26));
                    return;
                }
                PropertyModel.l<String> lVar6 = org.chromium.chrome.browser.password_entry_edit.e.h;
                if (v62Var6 == lVar6) {
                    ((TextView) federatedCredentialFragmentView.f12J.findViewById(R$id.password)).setText(federatedCredentialFragmentView.P(org.chromium.chrome.R$string.password_via_federation, (String) propertyModel6.j(lVar6)));
                    return;
                }
                return;
            case 10:
                vk2.b((PropertyModel) kVar, (ViewGroup) obj, (v62) obj2);
                return;
            case 11:
                PropertyModel propertyModel7 = (PropertyModel) kVar;
                tt2 tt2Var = (tt2) obj;
                v62 v62Var7 = (v62) obj2;
                PropertyModel.r<String> rVar27 = rt2.a;
                if (rVar27 == v62Var7) {
                    ((TextView) tt2Var.a.findViewById(R$id.status_text)).setText((CharSequence) propertyModel7.j(rVar27));
                    return;
                }
                PropertyModel.r<Drawable> rVar28 = rt2.b;
                if (rVar28 == v62Var7) {
                    ((TextView) tt2Var.a.findViewById(R$id.status_text)).setCompoundDrawablesRelative((Drawable) propertyModel7.j(rVar28), null, null, null);
                    return;
                }
                PropertyModel.n nVar10 = rt2.d;
                if (nVar10 == v62Var7) {
                    tt2Var.b.i = propertyModel7.k(nVar10);
                    return;
                }
                PropertyModel.p pVar5 = rt2.c;
                if (pVar5 == v62Var7) {
                    tt2Var.a.setVisibility(propertyModel7.h(pVar5));
                    return;
                }
                PropertyModel.p pVar6 = rt2.e;
                if (pVar6 == v62Var7) {
                    tt2Var.a.setBackgroundColor(propertyModel7.h(pVar6));
                    return;
                }
                PropertyModel.o oVar = rt2.f;
                if (oVar == v62Var7) {
                    tt2Var.a.findViewById(R$id.status_text).setAlpha(propertyModel7.g(oVar));
                    return;
                }
                PropertyModel.p pVar7 = rt2.g;
                if (pVar7 == v62Var7) {
                    ((TextView) tt2Var.a.findViewById(R$id.status_text)).setTextColor(propertyModel7.h(pVar7));
                    return;
                }
                PropertyModel.p pVar8 = rt2.h;
                if (pVar8 == v62Var7) {
                    ((TextViewWithCompoundDrawables) tt2Var.a.findViewById(R$id.status_text)).setDrawableTintColor(ColorStateList.valueOf(propertyModel7.h(pVar8)));
                    return;
                }
                if (rt2.i == v62Var7) {
                    tt2Var.a.setTranslationY(propertyModel7.h(r10) - tt2Var.a.getHeight());
                    return;
                }
                PropertyModel.n nVar11 = rt2.j;
                if (nVar11 == v62Var7) {
                    tt2Var.a.setImportantForAccessibility(propertyModel7.k(nVar11) ? 4 : 0);
                    return;
                }
                return;
            case 12:
                PropertyModel propertyModel8 = (PropertyModel) kVar;
                ViewGroup viewGroup4 = (ViewGroup) obj;
                v62 v62Var8 = (v62) obj2;
                if (v62Var8 != null) {
                    ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) viewGroup4;
                    s.b(propertyModel8, viewLookupCachingFrameLayout, v62Var8);
                    s.a(propertyModel8, viewLookupCachingFrameLayout, v62Var8);
                    return;
                }
                ViewLookupCachingFrameLayout viewLookupCachingFrameLayout2 = (ViewLookupCachingFrameLayout) viewGroup4;
                for (v62 v62Var9 : u0.C) {
                    s.b(propertyModel8, viewLookupCachingFrameLayout2, v62Var9);
                    s.a(propertyModel8, viewLookupCachingFrameLayout2, v62Var9);
                }
                return;
            case 13:
                return;
            case 14:
                PropertyModel propertyModel9 = (PropertyModel) kVar;
                me3 me3Var = (me3) obj;
                v62 v62Var10 = (v62) obj2;
                PropertyModel.l<Callback<Integer>> lVar7 = le3.c;
                if (v62Var10 == lVar7) {
                    me3Var.h = (Callback) propertyModel9.j(lVar7);
                    return;
                }
                PropertyModel.n nVar12 = le3.a;
                if (v62Var10 != nVar12) {
                    PropertyModel.l<p81<xa1>> lVar8 = le3.b;
                    if (v62Var10 == lVar8) {
                        int i9 = 19;
                        me3Var.g.setAdapter(new org.chromium.ui.modelutil.l(new n((p81) propertyModel9.j(lVar8), new pd(i9), new i83(i9)), new qh1(16)));
                        return;
                    }
                    return;
                }
                boolean zK5 = propertyModel9.k(nVar12);
                ji jiVar2 = me3Var.e;
                if (zK5) {
                    me3Var.B();
                    ne3.a aVar5 = me3Var.i;
                    jiVar2.g(aVar5);
                    if (!jiVar2.d(me3Var, true)) {
                        jiVar2.e(aVar5);
                    }
                    if (objArr == 0 || !propertyModel9.k(nVar12)) {
                        return;
                    }
                    ((Callback) propertyModel9.j(lVar7)).onResult(0);
                    return;
                }
                jiVar2.c(me3Var, true);
                objArr = 1;
                if (objArr == 0) {
                    return;
                } else {
                    return;
                }
            case 15:
                PropertyModel propertyModel10 = (PropertyModel) kVar;
                View view = (View) obj;
                v62 v62Var11 = (v62) obj2;
                if (v62Var11 == le3.a.e) {
                    view.setOnClickListener(new n40(propertyModel10, i4, (Credential) propertyModel10.j(le3.a.b)));
                    return;
                }
                if (v62Var11 == le3.e.d) {
                    view.setOnClickListener(new rf(propertyModel10, i3, (WebAuthnCredential) propertyModel10.j(le3.e.b)));
                    return;
                }
                PropertyModel.l<Boolean> lVar9 = le3.a.d;
                if (v62Var11 == lVar9) {
                    ((TextView) view.findViewById(R$id.touch_to_fill_button_title)).setText(view.getContext().getString(((Boolean) propertyModel10.j(lVar9)).booleanValue() ? org.chromium.chrome.R$string.touch_to_fill_signin : org.chromium.chrome.R$string.touch_to_fill_continue));
                    return;
                }
                PropertyModel.l<Boolean> lVar10 = le3.e.c;
                if (v62Var11 == lVar10) {
                    ((TextView) view.findViewById(R$id.touch_to_fill_button_title)).setText(view.getContext().getString(((Boolean) propertyModel10.j(lVar10)).booleanValue() ? org.chromium.chrome.R$string.touch_to_fill_signin : org.chromium.chrome.R$string.touch_to_fill_continue));
                    return;
                }
                return;
            case 16:
                PropertyModel propertyModel11 = (PropertyModel) kVar;
                View view2 = (View) obj;
                v62 v62Var12 = (v62) obj2;
                PropertyModel.l<u1.a> lVar11 = u1.a;
                if (v62Var12 == lVar11) {
                    u1.a aVar6 = (u1.a) propertyModel11.j(lVar11);
                    Context context = view2.getContext();
                    iq2.a aVarA = x1.a(context, "link_privacy_policy", aVar6.c, aVar6.e);
                    iq2.a aVarA2 = x1.a(context, "link_terms_of_service", aVar6.b, aVar6.d);
                    String str4 = String.format(context.getString((aVarA == null && aVarA2 == null) ? org.chromium.chrome.R$string.account_selection_data_sharing_consent_no_pp_or_tos : aVarA == null ? org.chromium.chrome.R$string.account_selection_data_sharing_consent_no_pp : aVarA2 == null ? org.chromium.chrome.R$string.account_selection_data_sharing_consent_no_tos : org.chromium.chrome.R$string.account_selection_data_sharing_consent), aVar6.a);
                    ArrayList arrayList = new ArrayList();
                    if (aVarA != null) {
                        arrayList.add(aVarA);
                    }
                    if (aVarA2 != null) {
                        arrayList.add(aVarA2);
                    }
                    SpannableString spannableStringA = iq2.a(str4, (iq2.a[]) arrayList.toArray(new iq2.a[0]));
                    TextView textView2 = (TextView) view2.findViewById(R$id.user_data_sharing_consent);
                    textView2.setText(spannableStringA);
                    textView2.setMovementMethod(LinkMovementMethod.getInstance());
                    return;
                }
                return;
            case 17:
                a.b((PropertyModel) kVar, (View) obj, (v62) obj2);
                return;
            case 18:
                PropertyModel propertyModel12 = (PropertyModel) kVar;
                a1 a1Var = (a1) obj;
                v62 v62Var13 = (v62) obj2;
                PropertyModel.l<View.OnClickListener> lVar12 = C0221y0.a;
                if (v62Var13 == lVar12) {
                    a1Var.i.setOnClickListener((View.OnClickListener) propertyModel12.j(lVar12));
                    return;
                }
                PropertyModel.p pVar9 = C0221y0.e;
                if (v62Var13 == pVar9) {
                    int iH3 = propertyModel12.h(pVar9);
                    ViewFlipper viewFlipper = a1Var.g;
                    viewFlipper.setDisplayedChild(iH3);
                    View viewFindViewById2 = viewFlipper.getChildAt(iH3).findViewById(a1.k[iH3]);
                    viewFindViewById2.setFocusable(true);
                    viewFindViewById2.sendAccessibilityEvent(8);
                    return;
                }
                PropertyModel.r<fb0> rVar29 = C0221y0.b;
                if (v62Var13 == rVar29) {
                    fb0 fb0Var = (fb0) propertyModel12.j(rVar29);
                    if (fb0Var != null) {
                        View childAt = a1Var.g.getChildAt(1);
                        ki0.a(fb0Var, a1Var.i);
                        ((ButtonCompat) childAt.findViewById(R$id.account_picker_continue_as_button)).setText(sm2.a(childAt.getContext(), fb0Var));
                        return;
                    }
                    return;
                }
                PropertyModel.l<View.OnClickListener> lVar13 = C0221y0.c;
                if (v62Var13 == lVar13) {
                    a1Var.setOnClickListenerOfContinueButton((View.OnClickListener) propertyModel12.j(lVar13));
                    return;
                }
                PropertyModel.l<View.OnClickListener> lVar14 = C0221y0.d;
                if (v62Var13 == lVar14) {
                    a1Var.j.setOnClickListener((View.OnClickListener) propertyModel12.j(lVar14));
                    return;
                }
                PropertyModel.l<InterfaceC0223z0> lVar15 = C0221y0.g;
                if (v62Var13 == lVar15) {
                    InterfaceC0223z0 interfaceC0223z0 = (InterfaceC0223z0) propertyModel12.j(lVar15);
                    int title = interfaceC0223z0.getTitle();
                    int iB2 = interfaceC0223z0.b();
                    int iA = interfaceC0223z0.a();
                    a1Var.getClass();
                    int[] iArr = {1, 2, 0};
                    for (int i10 = 0; i10 < 3; i10++) {
                        View childAt2 = a1Var.g.getChildAt(iArr[i10]);
                        ((TextView) childAt2.findViewById(R$id.account_picker_header_title)).setText(title);
                        ((TextViewWithLeading) childAt2.findViewById(R$id.account_picker_header_subtitle)).setText(iB2);
                    }
                    a1Var.j.setText(iA);
                    return;
                }
                return;
            case 19:
                PropertyModel propertyModel13 = (PropertyModel) kVar;
                SigninFirstRunView signinFirstRunView = (SigninFirstRunView) obj;
                v62 v62Var14 = (v62) obj2;
                PropertyModel.l<View.OnClickListener> lVar16 = lm2.d;
                if (v62Var14 == lVar16) {
                    signinFirstRunView.getContinueButtonView().setOnClickListener((View.OnClickListener) propertyModel13.j(lVar16));
                    return;
                }
                if (v62Var14 == lm2.e) {
                    return;
                }
                if (v62Var14 == lm2.f) {
                    mm2.b(signinFirstRunView, propertyModel13);
                    return;
                }
                if (v62Var14 == lm2.g) {
                    mm2.b(signinFirstRunView, propertyModel13);
                    return;
                }
                if (v62Var14 == lm2.a) {
                    return;
                }
                if (v62Var14 == lm2.b) {
                    mm2.a(signinFirstRunView, propertyModel13);
                    return;
                }
                PropertyModel.n nVar13 = lm2.c;
                if (v62Var14 == nVar13) {
                    propertyModel13.k(nVar13);
                    mm2.a(signinFirstRunView, propertyModel13);
                    return;
                }
                PropertyModel.n nVar14 = lm2.h;
                if (v62Var14 == nVar14) {
                    boolean zK6 = propertyModel13.k(nVar14);
                    ProgressBar initialLoadProgressSpinnerView = signinFirstRunView.getInitialLoadProgressSpinnerView();
                    if (!zK6) {
                        TransitionManager.beginDelayedTransition(signinFirstRunView);
                        initialLoadProgressSpinnerView.setVisibility(8);
                    }
                    mm2.a(signinFirstRunView, propertyModel13);
                    return;
                }
                if (v62Var14 == lm2.i) {
                    return;
                }
                PropertyModel.n nVar15 = lm2.j;
                if (v62Var14 != nVar15) {
                    if (v62Var14 == lm2.k) {
                        return;
                    }
                    throw new IllegalArgumentException("Unknown property key:" + v62Var14);
                }
                if (propertyModel13.k(nVar15)) {
                    return;
                }
                mm2.a(signinFirstRunView, propertyModel13);
                return;
            case 20:
                PropertyModel propertyModel14 = (PropertyModel) kVar;
                View view3 = (View) obj;
                v62 v62Var15 = (v62) obj2;
                PropertyModel.r<String> rVar30 = og3.a;
                if (v62Var15 == rVar30) {
                    ((TextView) view3.findViewById(R$id.title)).setText((CharSequence) propertyModel14.j(rVar30));
                    return;
                }
                PropertyModel.r<String> rVar31 = og3.b;
                if (v62Var15 == rVar31) {
                    ((TextView) view3.findViewById(R$id.video_length)).setText((CharSequence) propertyModel14.j(rVar31));
                    return;
                }
                if (v62Var15 == og3.c) {
                    view3.setOnClickListener(new cy0(i3, propertyModel14));
                    return;
                }
                PropertyModel.r<AsyncImageView.a> rVar32 = og3.d;
                if (v62Var15 != rVar32) {
                    throw new IllegalArgumentException("Cannot update the view for propertyKey: " + v62Var15);
                }
                ((AsyncImageView) view3.findViewById(R$id.thumbnail)).g((AsyncImageView.a) propertyModel14.j(rVar32), null);
                return;
            case 21:
                PropertyModel propertyModel15 = (PropertyModel) kVar;
                ViewGroup viewGroup5 = (ViewGroup) obj;
                v62 v62Var16 = (v62) obj2;
                PropertyModel.n nVar16 = zr2.c;
                if (v62Var16 == nVar16) {
                    if (propertyModel15.k(nVar16) && propertyModel15.k(zr2.e)) {
                        z = true;
                    }
                    ti0.a(viewGroup5, propertyModel15, z);
                    return;
                }
                PropertyModel.n nVar17 = zr2.e;
                if (v62Var16 == nVar17) {
                    if (propertyModel15.k(nVar16) && propertyModel15.k(nVar17)) {
                        z2 = true;
                    }
                    ti0.a(viewGroup5, propertyModel15, z2);
                    return;
                }
                PropertyModel.p pVar10 = zr2.g;
                if (v62Var16 != pVar10) {
                    if (v62Var16 != zr2.h || (qi0Var = (qi0) propertyModel15.j(zr2.f)) == null || (recyclerView = qi0Var.b.D) == null) {
                        return;
                    }
                    recyclerView.g0(0);
                    return;
                }
                PropertyModel.r<qi0> rVar33 = zr2.f;
                if (propertyModel15.j(rVar33) != null && propertyModel15.k(zr2.b)) {
                    c.a aVar7 = ((qi0) propertyModel15.j(rVar33)).b.q;
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) aVar7.getLayoutParams();
                    if (layoutParams == null) {
                        return;
                    }
                    layoutParams.topMargin = propertyModel15.h(pVar10);
                    aVar7.setLayoutParams(layoutParams);
                    return;
                }
                return;
            case 22:
                PropertyModel propertyModel16 = (PropertyModel) kVar;
                es2.a aVar8 = (es2.a) obj;
                v62 v62Var17 = (v62) obj2;
                PropertyModel.n nVar18 = zr2.d;
                if (nVar18 != v62Var17) {
                    PropertyModel.p pVar11 = zr2.g;
                    if (pVar11 != v62Var17) {
                        if (zr2.e == v62Var17 && propertyModel16.k(nVar18)) {
                            aVar8.b.bringToFront();
                            return;
                        }
                        return;
                    }
                    int iH4 = propertyModel16.h(pVar11);
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) aVar8.b.getLayoutParams();
                    if (marginLayoutParams2 == null) {
                        return;
                    }
                    marginLayoutParams2.topMargin = iH4;
                    aVar8.b.setLayoutParams(marginLayoutParams2);
                    return;
                }
                boolean zK7 = propertyModel16.k(nVar18);
                if (zK7 && aVar8.b.getParent() == null) {
                    ViewGroup viewGroup6 = aVar8.a;
                    View view4 = aVar8.b;
                    viewGroup6.addView(view4);
                    int iH5 = propertyModel16.h(zr2.g);
                    ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) view4.getLayoutParams();
                    if (marginLayoutParams3 != null) {
                        marginLayoutParams3.topMargin = iH5;
                        view4.setLayoutParams(marginLayoutParams3);
                    }
                }
                aVar8.b.getBackground().setAlpha(0);
                int i11 = zK7 ? 0 : 8;
                View view5 = aVar8.b;
                view5.setVisibility(i11);
                if (propertyModel16.k(nVar18)) {
                    view5.bringToFront();
                    return;
                }
                return;
            case 23:
                PropertyModel propertyModel17 = (PropertyModel) kVar;
                MessageBannerView messageBannerView = (MessageBannerView) obj;
                v62 v62Var18 = (v62) obj2;
                PropertyModel.p pVar12 = ng1.b;
                if (v62Var18 == pVar12) {
                    messageBannerView.setPrimaryWidgetAppearance(propertyModel17.h(pVar12));
                    return;
                }
                PropertyModel.r<String> rVar34 = ng1.c;
                if (v62Var18 == rVar34) {
                    messageBannerView.setPrimaryButtonText((String) propertyModel17.j(rVar34));
                    return;
                }
                PropertyModel.r<View.OnClickListener> rVar35 = ng1.F;
                if (v62Var18 == rVar35) {
                    messageBannerView.setPrimaryButtonClickListener((View.OnClickListener) propertyModel17.j(rVar35));
                    return;
                }
                PropertyModel.r<String> rVar36 = ng1.f;
                if (v62Var18 == rVar36) {
                    messageBannerView.setTitle((String) propertyModel17.j(rVar36));
                    return;
                }
                PropertyModel.r<String> rVar37 = ng1.g;
                if (v62Var18 == rVar37) {
                    messageBannerView.setTitleContentDescription((String) propertyModel17.j(rVar37));
                    return;
                }
                PropertyModel.r<CharSequence> rVar38 = ng1.h;
                if (v62Var18 == rVar38) {
                    messageBannerView.setDescriptionText((CharSequence) propertyModel17.j(rVar38));
                    return;
                }
                PropertyModel.r<Drawable> rVar39 = ng1.i;
                if (v62Var18 == rVar39) {
                    messageBannerView.setDescriptionIcon((Drawable) propertyModel17.j(rVar39));
                    messageBannerView.a(propertyModel17.k(ng1.j));
                    return;
                }
                PropertyModel.n nVar19 = ng1.j;
                if (v62Var18 == nVar19) {
                    messageBannerView.a(propertyModel17.k(nVar19));
                    return;
                }
                PropertyModel.p pVar13 = ng1.k;
                if (v62Var18 == pVar13) {
                    messageBannerView.setDescriptionMaxLines(propertyModel17.h(pVar13));
                    return;
                }
                PropertyModel.r<Drawable> rVar40 = ng1.l;
                if (v62Var18 == rVar40) {
                    messageBannerView.setIcon((Drawable) propertyModel17.j(rVar40));
                    return;
                }
                PropertyModel.p pVar14 = ng1.m;
                if (v62Var18 == pVar14) {
                    messageBannerView.setIcon(AppCompatResources.a(messageBannerView.getContext(), propertyModel17.h(pVar14)));
                    return;
                }
                PropertyModel.p pVar15 = ng1.p;
                if (v62Var18 == pVar15) {
                    messageBannerView.setIconTint(propertyModel17.h(pVar15));
                    return;
                }
                PropertyModel.p pVar16 = ng1.o;
                if (v62Var18 == pVar16) {
                    messageBannerView.setIconCornerRadius(propertyModel17.h(pVar16));
                    return;
                }
                PropertyModel.n nVar20 = ng1.n;
                if (v62Var18 == nVar20) {
                    boolean zK8 = propertyModel17.k(nVar20);
                    int dimensionPixelSize2 = messageBannerView.getResources().getDimensionPixelSize(org.chromium.components.messages.R$dimen.message_icon_size);
                    int dimensionPixelSize3 = messageBannerView.getResources().getDimensionPixelSize(org.chromium.components.messages.R$dimen.message_icon_size_large);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) messageBannerView.j.getLayoutParams();
                    if (zK8) {
                        layoutParams2.width = dimensionPixelSize3;
                        layoutParams2.height = dimensionPixelSize3;
                    } else {
                        layoutParams2.width = -2;
                        layoutParams2.height = dimensionPixelSize2;
                    }
                    messageBannerView.j.setLayoutParams(layoutParams2);
                    return;
                }
                PropertyModel.r<Drawable> rVar41 = ng1.q;
                if (v62Var18 == rVar41) {
                    messageBannerView.setSecondaryIcon((Drawable) propertyModel17.j(rVar41));
                    return;
                }
                PropertyModel.p pVar17 = ng1.r;
                if (v62Var18 == pVar17) {
                    messageBannerView.setSecondaryIcon(AppCompatResources.a(messageBannerView.getContext(), propertyModel17.h(pVar17)));
                    return;
                }
                PropertyModel.r<String> rVar42 = ng1.s;
                if (v62Var18 == rVar42) {
                    messageBannerView.setSecondaryButtonMenuText((String) propertyModel17.j(rVar42));
                    return;
                }
                PropertyModel.r<l81> rVar43 = ng1.u;
                if (v62Var18 == rVar43) {
                    messageBannerView.setSecondaryMenuButtonDelegate((l81) propertyModel17.j(rVar43));
                    return;
                }
                PropertyModel.p pVar18 = ng1.v;
                if (v62Var18 == pVar18) {
                    messageBannerView.setSecondaryMenuMaxSize(propertyModel17.h(pVar18));
                    return;
                }
                PropertyModel.r<String> rVar44 = ng1.t;
                if (v62Var18 == rVar44) {
                    messageBannerView.setSecondaryIconContentDescription((String) propertyModel17.j(rVar44));
                    return;
                }
                PropertyModel.r<Runnable> rVar45 = ng1.G;
                if (v62Var18 == rVar45) {
                    messageBannerView.setSecondaryActionCallback((Runnable) propertyModel17.j(rVar45));
                    return;
                }
                PropertyModel.r<Runnable> rVar46 = ng1.D;
                if (v62Var18 == rVar46) {
                    Runnable runnable2 = (Runnable) propertyModel17.j(rVar46);
                    if (runnable2 == null) {
                        messageBannerView.setOnTouchListener(null);
                        return;
                    } else {
                        messageBannerView.setOnTouchListener(new me0(i2, runnable2));
                        return;
                    }
                }
                PropertyModel.o oVar2 = ng1.C;
                if (v62Var18 == oVar2) {
                    messageBannerView.setAlpha(propertyModel17.g(oVar2));
                    return;
                }
                PropertyModel.o oVar3 = ng1.z;
                if (v62Var18 == oVar3) {
                    messageBannerView.setTranslationX(propertyModel17.g(oVar3));
                    return;
                }
                PropertyModel.o oVar4 = ng1.A;
                if (v62Var18 == oVar4) {
                    messageBannerView.setTranslationY(propertyModel17.g(oVar4));
                    return;
                }
                PropertyModel.o oVar5 = ng1.E;
                if (v62Var18 == oVar5) {
                    messageBannerView.setElevation(propertyModel17.g(oVar5));
                    return;
                }
                PropertyModel.p pVar19 = ng1.B;
                if (v62Var18 == pVar19) {
                    messageBannerView.setMarginTop(propertyModel17.h(pVar19));
                    return;
                }
                return;
            default:
                a(kVar, obj, obj2);
                return;
        }
    }
}
