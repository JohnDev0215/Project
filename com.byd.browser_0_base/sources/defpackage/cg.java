package defpackage;

import J.N;
import android.content.res.Resources;
import android.gesture.GesturePoint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.util.Pair;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.es2;
import defpackage.f81;
import defpackage.le3;
import defpackage.v1;
import java.util.List;
import java.util.Optional;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.creator.CreatorToolbarView;
import org.chromium.chrome.browser.gesturenav.SideSlideLayout;
import org.chromium.chrome.browser.gesturenav.a;
import org.chromium.chrome.browser.history_clusters.d;
import org.chromium.chrome.browser.password_check.CompromisedCredential;
import org.chromium.chrome.browser.password_check.PasswordCheckDeletionDialogFragment;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;
import org.chromium.chrome.browser.password_check.PasswordCheckFragmentView;
import org.chromium.chrome.browser.password_check.PasswordCheckViewDialogFragment;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.password_check.i;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.password_entry_edit.b;
import org.chromium.chrome.browser.password_entry_edit.c;
import org.chromium.chrome.browser.password_entry_edit.e;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.ui.autofill.OtpVerificationDialogView;
import org.chromium.chrome.browser.ui.autofill.e;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.components.browser_ui.widget.ClipDrawableProgressBar;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.components.paintpreview.player.frame.PlayerFrameView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.m;
import org.chromium.ui.modelutil.n;
import org.chromium.ui.resources.ResourceManager;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class cg implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ cg(int i) {
        this.a = i;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        String str;
        PropertyModel.r<CompromisedCredential> rVar;
        PropertyModel.r<PasswordCheckDialogFragment.a> rVar2;
        View view;
        int i;
        String str2;
        int i2 = 7;
        int i3 = 5;
        str = "";
        int i4 = 2;
        int i5 = 3;
        switch (this.a) {
            case 0:
                lg.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 1:
                PropertyModel propertyModel = (PropertyModel) kVar;
                View view2 = (View) obj;
                if (((v62) obj2) == ig.j) {
                    LinearLayout linearLayout = (LinearLayout) view2;
                    linearLayout.setClickable(true);
                    linearLayout.setOnClickListener(new kg(0, propertyModel));
                }
                break;
            case 2:
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                View view3 = (View) obj;
                v62 v62Var = (v62) obj2;
                int i6 = R$id.menu_row_text;
                View viewFindViewById = view3.findViewById(i6);
                PropertyModel.r<CharSequence> rVar3 = qz.a;
                if (v62Var == rVar3) {
                    ((TextView) viewFindViewById).setText((CharSequence) propertyModel2.j(rVar3));
                }
                PropertyModel.r<Drawable> rVar4 = rz.d;
                if (v62Var != rVar4) {
                    PropertyModel.r<CharSequence> rVar5 = rz.e;
                    if (v62Var != rVar5) {
                        PropertyModel.r<View.OnClickListener> rVar6 = rz.g;
                        if (v62Var == rVar6) {
                            view3.findViewById(R$id.menu_row_share_icon).setOnClickListener((View.OnClickListener) propertyModel2.j(rVar6));
                        }
                    } else {
                        ((ImageView) view3.findViewById(R$id.menu_row_share_icon)).setContentDescription(view3.getContext().getString(R$string.accessibility_menu_share_via, propertyModel2.j(rVar5)));
                    }
                } else {
                    Drawable drawable = (Drawable) propertyModel2.j(rVar4);
                    ImageView imageView = (ImageView) view3.findViewById(R$id.menu_row_share_icon);
                    imageView.setImageDrawable(drawable);
                    imageView.setVisibility(drawable != null ? 0 : 8);
                    int dimensionPixelSize = view3.getResources().getDimensionPixelSize(R$dimen.context_menu_list_lateral_padding);
                    view3.findViewById(i6).setPaddingRelative(dimensionPixelSize, 0, drawable != null ? 0 : dimensionPixelSize, 0);
                }
                break;
            case 3:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                CreatorToolbarView creatorToolbarView = (CreatorToolbarView) obj;
                v62 v62Var2 = (v62) obj2;
                PropertyModel.r<String> rVar7 = n10.b;
                if (rVar7 != v62Var2) {
                    PropertyModel.r<Boolean> rVar8 = n10.h;
                    if (rVar8 != v62Var2) {
                        PropertyModel.r<Boolean> rVar9 = n10.e;
                        if (rVar9 != v62Var2) {
                            PropertyModel.r<Runnable> rVar10 = n10.f;
                            if (rVar10 != v62Var2) {
                                PropertyModel.r<Runnable> rVar11 = n10.g;
                                if (rVar11 == v62Var2) {
                                    creatorToolbarView.setFollowingButtonToolbarOnClickListener((Runnable) propertyModel3.j(rVar11));
                                }
                            } else {
                                creatorToolbarView.setFollowButtonToolbarOnClickListener((Runnable) propertyModel3.j(rVar10));
                            }
                        } else {
                            creatorToolbarView.setIsFollowedStatus(((Boolean) propertyModel3.j(rVar9)).booleanValue());
                        }
                    } else {
                        creatorToolbarView.setToolbarVisibility(((Boolean) propertyModel3.j(rVar8)).booleanValue());
                    }
                } else {
                    creatorToolbarView.setTitle((String) propertyModel3.j(rVar7));
                }
                break;
            case 4:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                mc0 mc0Var = (mc0) obj;
                v62 v62Var3 = (v62) obj2;
                PropertyModel.r<OfflineItem> rVar12 = lc0.a;
                if (!v62Var3.equals(rVar12)) {
                    PropertyModel.r<String> rVar13 = lc0.c;
                    if (v62Var3.equals(rVar13)) {
                        mc0Var.b.setText((String) propertyModel4.j(rVar13));
                        break;
                    } else {
                        PropertyModel.n nVar = lc0.d;
                        if (v62Var3.equals(nVar)) {
                            mc0Var.f.setVisibility(propertyModel4.k(nVar) ? 0 : 4);
                            break;
                        } else {
                            PropertyModel.r<String> rVar14 = lc0.e;
                            if (v62Var3.equals(rVar14)) {
                                mc0Var.f.setText((String) propertyModel4.j(rVar14));
                                break;
                            } else {
                                PropertyModel.r<Callback<OfflineItem>> rVar15 = lc0.f;
                                if (v62Var3.equals(rVar15)) {
                                    mc0Var.f.setOnClickListener(new p10(1, ((Callback) propertyModel4.j(rVar15)).p1((OfflineItem) propertyModel4.j(rVar12))));
                                    break;
                                } else {
                                    PropertyModel.n nVar2 = lc0.g;
                                    if (v62Var3.equals(nVar2)) {
                                        mc0Var.g.setVisibility(propertyModel4.k(nVar2) ? 0 : 4);
                                        break;
                                    } else {
                                        PropertyModel.r<String> rVar16 = lc0.h;
                                        if (v62Var3.equals(rVar16)) {
                                            mc0Var.g.setText((String) propertyModel4.j(rVar16));
                                            break;
                                        } else {
                                            PropertyModel.r<Callback<OfflineItem>> rVar17 = lc0.i;
                                            if (v62Var3.equals(rVar17)) {
                                                mc0Var.g.setOnClickListener(new nd3(3, ((Callback) propertyModel4.j(rVar17)).p1((OfflineItem) propertyModel4.j(rVar12))));
                                                break;
                                            } else {
                                                PropertyModel.n nVar3 = lc0.k;
                                                if (v62Var3.equals(nVar3)) {
                                                    boolean zK = propertyModel4.k(nVar3);
                                                    TextView textView = mc0Var.c;
                                                    if (!zK) {
                                                        textView.setVisibility(8);
                                                    } else {
                                                        textView.setVisibility(0);
                                                        mc0Var.e.e.setVisibility(4);
                                                        mc0Var.d.e.setVisibility(4);
                                                        mc0Var.f.setVisibility(8);
                                                        mc0Var.g.setVisibility(8);
                                                    }
                                                    break;
                                                } else {
                                                    PropertyModel.p pVar = lc0.b;
                                                    if (v62Var3.equals(pVar) && propertyModel4.h(pVar) == 3) {
                                                        OfflineItem offlineItem = (OfflineItem) propertyModel4.j(rVar12);
                                                        mc0Var.d.e.setVisibility(8);
                                                        offlineItem.A = 3;
                                                        f81.f fVar = new f81.f(offlineItem);
                                                        rx0 rx0Var = mc0Var.e;
                                                        rx0Var.u(propertyModel4, fVar);
                                                        rx0Var.e.setVisibility(0);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    OfflineItem offlineItem2 = (OfflineItem) propertyModel4.j(rVar12);
                    mc0Var.getClass();
                    if (offlineItem2 != null) {
                        if (offlineItem2.A == 2 && propertyModel4.h(lc0.b) == 3) {
                            ((Callback) propertyModel4.j(t81.s)).onResult(offlineItem2);
                        } else {
                            int i7 = offlineItem2.A;
                            rx0 rx0Var2 = mc0Var.e;
                            gs0 gs0Var = mc0Var.d;
                            if (i7 != 2) {
                                gs0Var.e.setVisibility(8);
                                rx0Var2.e.setVisibility(0);
                                rx0Var2.u(propertyModel4, new f81.f(offlineItem2));
                            } else {
                                rx0Var2.e.setVisibility(8);
                                gs0Var.e.setVisibility(0);
                                gs0Var.u(propertyModel4, new f81.f(offlineItem2));
                            }
                        }
                        break;
                    }
                }
                break;
            case 5:
                break;
            case 6:
                ko.a((PropertyModel) kVar, (ChipView) obj, (v62) obj2);
                break;
            case 7:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                a aVar = (a) obj;
                v62 v62Var4 = (v62) obj2;
                PropertyModel.o oVar = ns0.d;
                if (oVar != v62Var4) {
                    PropertyModel.o oVar2 = ns0.e;
                    if (oVar2 == v62Var4) {
                        aVar.a().e(propertyModel5.g(oVar2));
                        break;
                    } else {
                        PropertyModel.p pVar2 = ns0.a;
                        if (pVar2 == v62Var4) {
                            switch (propertyModel5.h(pVar2)) {
                                case 1:
                                    boolean zK2 = propertyModel5.k(ns0.b);
                                    int iH = propertyModel5.h(ns0.f);
                                    if (aVar.h == null) {
                                        SideSlideLayout sideSlideLayout = new SideSlideLayout(aVar.getContext());
                                        aVar.h = sideSlideLayout;
                                        sideSlideLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                                        SideSlideLayout sideSlideLayout2 = aVar.h;
                                        sideSlideLayout2.setOnNavigationListener(new i40(aVar, sideSlideLayout2));
                                        sideSlideLayout2.setOnResetListener(new du(aVar, sideSlideLayout2));
                                    }
                                    aVar.h.setEnabled(true);
                                    aVar.h.setDirection(zK2);
                                    aVar.h.setCloseIndicator(iH);
                                    l10 l10Var = aVar.k;
                                    if (l10Var != null) {
                                        aVar.h.removeCallbacks(l10Var);
                                        aVar.k = null;
                                    }
                                    SideSlideLayout sideSlideLayout3 = aVar.h;
                                    if (sideSlideLayout3 == null || sideSlideLayout3.getParent() == null) {
                                        aVar.addView(aVar.h);
                                    }
                                    SideSlideLayout sideSlideLayout4 = aVar.h;
                                    if (sideSlideLayout4.isEnabled() && !sideSlideLayout4.k && sideSlideLayout4.i != null) {
                                        sideSlideLayout4.m = 0.0f;
                                        sideSlideLayout4.h = 0.0f;
                                        sideSlideLayout4.n = true;
                                        sideSlideLayout4.w = false;
                                        int width = sideSlideLayout4.u ? ((View) sideSlideLayout4.getParent()).getWidth() : -sideSlideLayout4.p;
                                        sideSlideLayout4.r = width;
                                        sideSlideLayout4.l = width;
                                        sideSlideLayout4.o.a();
                                        break;
                                    }
                                    break;
                                case 2:
                                    GesturePoint gesturePoint = (GesturePoint) propertyModel5.j(ns0.g);
                                    aVar.a().f(gesturePoint.x, gesturePoint.y);
                                    break;
                                case 3:
                                    boolean zK3 = propertyModel5.k(ns0.c);
                                    SideSlideLayout sideSlideLayout5 = aVar.h;
                                    if (sideSlideLayout5 != null) {
                                        zc0 zc0Var = aVar.j;
                                        if (zc0Var != null) {
                                            sideSlideLayout5.removeCallbacks(zc0Var);
                                            aVar.j = null;
                                        }
                                        aVar.h.c(zK3);
                                        break;
                                    }
                                    break;
                                case 4:
                                    aVar.a().h();
                                    break;
                                case 5:
                                    SideSlideLayout sideSlideLayout6 = aVar.h;
                                    if (sideSlideLayout6 != null) {
                                        zc0 zc0Var2 = aVar.j;
                                        if (zc0Var2 != null) {
                                            sideSlideLayout6.removeCallbacks(zc0Var2);
                                            aVar.j = null;
                                        }
                                        aVar.h.d();
                                        break;
                                    }
                                    break;
                                case 6:
                                    aVar.a().j();
                                    break;
                            }
                        }
                    }
                } else {
                    float fG = propertyModel5.g(oVar);
                    SideSlideLayout sideSlideLayout7 = aVar.h;
                    if (sideSlideLayout7 != null) {
                        sideSlideLayout7.b(fG);
                        break;
                    }
                }
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                d.b((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 11:
                d.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 12:
                break;
            case 13:
                ko.a((PropertyModel) kVar, (ChipView) obj, (v62) obj2);
                break;
            case 14:
                PropertyModel propertyModel6 = (PropertyModel) kVar;
                PasswordCheckFragmentView passwordCheckFragmentView = (PasswordCheckFragmentView) obj;
                v62 v62Var5 = (v62) obj2;
                PropertyModel.l<p81<xa1>> lVar = h.a;
                if (v62Var5 == lVar) {
                    passwordCheckFragmentView.b0.setAdapter(new l(new n((p81) propertyModel6.j(lVar), new i83(i2), new gq2()), new wd0(14)));
                    break;
                } else {
                    PropertyModel.r<PasswordCheckDialogFragment.a> rVar18 = h.b;
                    PropertyModel.r<String> rVar19 = h.c;
                    if (v62Var5 != rVar18) {
                        if (v62Var5 != rVar19 && v62Var5 != (rVar = h.d) && v62Var5 == (rVar2 = h.e) && propertyModel6.j(rVar2) != null) {
                            new PasswordCheckViewDialogFragment((PasswordCheckDialogFragment.a) propertyModel6.j(rVar2), (CompromisedCredential) propertyModel6.j(rVar)).M0(passwordCheckFragmentView.M(), null);
                        }
                        break;
                    } else if (propertyModel6.j(rVar18) != null) {
                        new PasswordCheckDeletionDialogFragment((PasswordCheckDialogFragment.a) propertyModel6.j(rVar18), (String) propertyModel6.j(rVar19)).M0(passwordCheckFragmentView.M(), null);
                        break;
                    }
                }
                break;
            case 15:
                PropertyModel propertyModel7 = (PropertyModel) kVar;
                View view4 = (View) obj;
                v62 v62Var6 = (v62) obj2;
                PropertyModel.r<Pair<Integer, Integer>> rVar20 = h.b.a;
                Pair pair = (Pair) propertyModel7.j(rVar20);
                PropertyModel.p pVar3 = h.b.b;
                int iH2 = propertyModel7.h(pVar3);
                PropertyModel.r<Long> rVar21 = h.b.c;
                Long l = (Long) propertyModel7.j(rVar21);
                PropertyModel.r<Integer> rVar22 = h.b.d;
                Integer num = (Integer) propertyModel7.j(rVar22);
                PropertyModel.l<Runnable> lVar2 = h.b.e;
                Runnable runnable = (Runnable) propertyModel7.j(lVar2);
                PropertyModel.n nVar4 = h.b.g;
                boolean zK4 = propertyModel7.k(nVar4);
                if (v62Var6 == rVar20) {
                    i.e(view4, iH2, num, l, pair, runnable);
                } else if (v62Var6 == pVar3) {
                    Runnable runnable2 = (Runnable) propertyModel7.j(h.b.f);
                    ImageButton imageButton = (ImageButton) view4.findViewById(R$id.check_status_restart_button);
                    LinearLayout linearLayout2 = (LinearLayout) view4.findViewById(R$id.check_status_text_layout);
                    boolean z = iH2 == 0 || iH2 == 3 || iH2 == 8;
                    ((LinearLayout.LayoutParams) linearLayout2.getLayoutParams()).setMarginEnd(z ? 0 : view4.getResources().getDimensionPixelSize(R$dimen.check_status_text_margin));
                    imageButton.setVisibility(z ? 0 : 8);
                    imageButton.setOnClickListener(z ? new od3(3, runnable2) : null);
                    imageButton.setClickable(z);
                    i.b(view4, iH2, num);
                    i.c(view4, iH2, num);
                    i.e(view4, iH2, num, l, pair, runnable);
                    i.d(view4, iH2, zK4, num);
                } else if (v62Var6 == rVar21) {
                    i.e(view4, iH2, num, l, pair, runnable);
                } else if (v62Var6 == rVar22) {
                    i.b(view4, iH2, num);
                    i.c(view4, iH2, num);
                    i.e(view4, iH2, num, l, pair, runnable);
                    i.d(view4, iH2, zK4, num);
                } else if (v62Var6 != lVar2 && v62Var6 != h.b.f && v62Var6 == nVar4) {
                    i.d(view4, iH2, zK4, num);
                }
                break;
            case 16:
                PropertyModel propertyModel8 = (PropertyModel) kVar;
                CredentialEditFragmentView credentialEditFragmentView = (CredentialEditFragmentView) obj;
                v62 v62Var7 = (v62) obj2;
                PropertyModel.r<CredentialEntryFragmentViewBase.b> rVar23 = e.a;
                if (v62Var7 == rVar23) {
                    CredentialEntryFragmentViewBase.b bVar = (CredentialEntryFragmentViewBase.b) propertyModel8.j(rVar23);
                    credentialEditFragmentView.i0 = bVar;
                    ((ChromeImageButton) credentialEditFragmentView.f12J.findViewById(R$id.copy_username_button)).setOnClickListener(new wd2(credentialEditFragmentView, 6, bVar));
                    ((ChromeImageButton) credentialEditFragmentView.f12J.findViewById(R$id.copy_password_button)).setOnClickListener(new c30(credentialEditFragmentView, i, bVar));
                    ((ChromeImageButton) credentialEditFragmentView.f12J.findViewById(R$id.password_visibility_button)).setOnClickListener(new ie0(16, bVar));
                    credentialEditFragmentView.f12J.findViewById(R$id.btn_sure).setOnClickListener(new rf(credentialEditFragmentView, i, bVar));
                    credentialEditFragmentView.f12J.findViewById(R$id.btn_cancel).setOnClickListener(new vt(17, credentialEditFragmentView));
                    credentialEditFragmentView.k0.addTextChangedListener(new b(bVar));
                    credentialEditFragmentView.m0.addTextChangedListener(new c(bVar));
                    break;
                } else {
                    PropertyModel.l<String> lVar3 = e.b;
                    if (v62Var7 == lVar3) {
                        String str3 = (String) propertyModel8.j(lVar3);
                        ((TextView) credentialEditFragmentView.f12J.findViewById(R$id.url_or_app)).setText(str3);
                        ((TextView) credentialEditFragmentView.f12J.findViewById(R$id.edit_info)).setText(credentialEditFragmentView.P(R$string.password_edit_hint, str3));
                        break;
                    } else if (v62Var7 != e.h) {
                        PropertyModel.r<String> rVar24 = e.c;
                        if (v62Var7 != rVar24) {
                            PropertyModel.n nVar5 = e.d;
                            if (v62Var7 == nVar5) {
                                boolean zK5 = propertyModel8.k(nVar5);
                                credentialEditFragmentView.j0.setError(zK5 ? credentialEditFragmentView.O(R$string.password_entry_edit_duplicate_username_error) : "");
                                boolean z2 = !zK5;
                                credentialEditFragmentView.n0.setEnabled(z2);
                                credentialEditFragmentView.n0.setClickable(z2);
                                break;
                            } else {
                                PropertyModel.n nVar6 = e.e;
                                if (v62Var7 == nVar6) {
                                    boolean zK6 = propertyModel8.k(nVar6);
                                    if (zK6) {
                                        credentialEditFragmentView.D().getWindow().setFlags(8192, 8192);
                                        credentialEditFragmentView.m0.setInputType(131217);
                                    } else {
                                        credentialEditFragmentView.D().getWindow().clearFlags(8192);
                                        credentialEditFragmentView.m0.setInputType(131201);
                                    }
                                    ChromeImageButton chromeImageButton = (ChromeImageButton) credentialEditFragmentView.f12J.findViewById(R$id.password_visibility_button);
                                    chromeImageButton.setImageResource(zK6 ? R$drawable.ic_visibility_off_black : R$drawable.ic_visibility_black);
                                    chromeImageButton.setContentDescription(zK6 ? credentialEditFragmentView.O(R$string.password_entry_viewer_hide_stored_password) : credentialEditFragmentView.O(R$string.password_entry_viewer_show_stored_password));
                                    break;
                                } else {
                                    PropertyModel.r<String> rVar25 = e.f;
                                    if (v62Var7 != rVar25) {
                                        PropertyModel.n nVar7 = e.g;
                                        if (v62Var7 == nVar7) {
                                            boolean zK7 = propertyModel8.k(nVar7);
                                            credentialEditFragmentView.l0.setError(zK7 ? credentialEditFragmentView.O(R$string.password_entry_edit_empty_password_error) : "");
                                            boolean z3 = !zK7;
                                            credentialEditFragmentView.n0.setEnabled(z3);
                                            credentialEditFragmentView.n0.setClickable(z3);
                                        } else if (v62Var7 == e.i) {
                                            credentialEditFragmentView.M0();
                                        }
                                        break;
                                    } else {
                                        String str4 = (String) propertyModel8.j(rVar25);
                                        if (!credentialEditFragmentView.m0.getText().toString().equals(str4)) {
                                            credentialEditFragmentView.m0.setText(str4);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            String str5 = (String) propertyModel8.j(rVar24);
                            if (!credentialEditFragmentView.k0.getText().toString().equals(str5)) {
                                credentialEditFragmentView.k0.setText(z7.q(str5));
                                break;
                            }
                        }
                    }
                }
                break;
            case 17:
                PropertyModel propertyModel9 = (PropertyModel) kVar;
                vd3 vd3Var = (vd3) obj;
                nw2<ResourceManager> nw2Var = vd3Var.g;
                if (nw2Var.get() != null) {
                    N.MHqlwRYg(vd3Var.f, vd3Var, nw2Var.get(), propertyModel9.h(ud3.c), propertyModel9.h(ud3.e), propertyModel9.h(ud3.g), propertyModel9.h(ud3.f), propertyModel9.g(ud3.i), propertyModel9.g(ud3.j), propertyModel9.k(ud3.d), propertyModel9.k(ud3.h), propertyModel9.k(ud3.a));
                    ClipDrawableProgressBar.a aVar2 = (ClipDrawableProgressBar.a) propertyModel9.j(ud3.b);
                    if (aVar2 != null) {
                        long j = vd3Var.f;
                        Rect rect = aVar2.a;
                        int i8 = rect.left;
                        int i9 = rect.top;
                        int iWidth = rect.width();
                        int iHeight = rect.height();
                        int i10 = aVar2.c;
                        Rect rect2 = aVar2.b;
                        N.MKAQCQU8(j, vd3Var, i8, i9, iWidth, iHeight, i10, rect2.left, rect2.top, rect2.width(), rect2.height(), aVar2.d);
                        break;
                    }
                }
                break;
            case 18:
                PropertyModel propertyModel10 = (PropertyModel) kVar;
                View view5 = (View) obj;
                v62 v62Var8 = (v62) obj2;
                PropertyModel.l<Credential> lVar4 = le3.a.b;
                Credential credential = (Credential) propertyModel10.j(lVar4);
                PropertyModel.r<le3.b> rVar26 = le3.a.a;
                if (v62Var8 == rVar26) {
                    ImageView imageView2 = (ImageView) view5.findViewById(R$id.favicon);
                    le3.b bVar2 = (le3.b) propertyModel10.j(rVar26);
                    imageView2.setImageDrawable(org.chromium.chrome.browser.ui.favicon.a.e(bVar2.b, bVar2.a, bVar2.c, org.chromium.chrome.browser.ui.favicon.a.a(view5.getContext()), view5.getResources(), bVar2.d));
                } else if (v62Var8 != le3.a.e) {
                    PropertyModel.l<String> lVar5 = le3.a.c;
                    if (v62Var8 == lVar5) {
                        TextView textView2 = (TextView) view5.findViewById(R$id.credential_origin);
                        textView2.setText((CharSequence) propertyModel10.j(lVar5));
                        textView2.setVisibility(!credential.e && !credential.f ? 8 : 0);
                    } else if (v62Var8 == lVar4) {
                        TextView textView3 = (TextView) view5.findViewById(R$id.credential_origin);
                        textView3.setText(bk3.p(credential.getOriginUrl()).replaceFirst("/$", ""));
                        textView3.setVisibility(!credential.e && !credential.f ? 8 : 0);
                        TextView textView4 = (TextView) view5.findViewById(R$id.username);
                        String str6 = credential.c;
                        textView4.setText(str6);
                        TextView textView5 = (TextView) view5.findViewById(R$id.password);
                        textView5.setText(credential.getPassword());
                        textView5.setTransformationMethod(new PasswordTransformationMethod());
                        view5.setContentDescription(view5.getContext().getString(R$string.touch_to_fill_password_credential_accessibility_description, str6));
                    }
                } else {
                    view5.setOnClickListener(new rf(propertyModel10, i3, credential));
                }
                break;
            case 19:
                PropertyModel propertyModel11 = (PropertyModel) kVar;
                View view6 = (View) obj;
                v62 v62Var9 = (v62) obj2;
                PropertyModel.l<String> lVar6 = v1.c;
                PropertyModel.l<String> lVar7 = v1.b;
                PropertyModel.l<String> lVar8 = v1.d;
                PropertyModel.l<v1.a> lVar9 = v1.f;
                if (v62Var9 == lVar6 || v62Var9 == lVar8 || v62Var9 == lVar7 || v62Var9 == lVar9) {
                    Resources resources = view6.getResources();
                    TextView textView6 = (TextView) view6.findViewById(R$id.header_title);
                    TextView textView7 = (TextView) view6.findViewById(R$id.header_subtitle);
                    v1.a aVar3 = (v1.a) propertyModel11.j(lVar9);
                    String str7 = (String) propertyModel11.j(lVar6);
                    String str8 = (String) propertyModel11.j(lVar8);
                    v1.a aVar4 = v1.a.VERIFY;
                    v1.a aVar5 = v1.a.VERIFY_AUTO_REAUTHN;
                    if (aVar3 == aVar4 || aVar3 == aVar5 || str8.isEmpty()) {
                        view = view6;
                        i = 0;
                    } else {
                        view = view6;
                        i = 0;
                        str = String.format(resources.getString(R$string.account_selection_sheet_subtitle_explicit), str7);
                    }
                    if (str.isEmpty()) {
                        textView7.setVisibility(8);
                        str2 = (String) propertyModel11.j(lVar6);
                    } else {
                        textView6.setPadding(i, 12, i, i);
                        textView7.setText(str);
                        str2 = (String) propertyModel11.j(lVar8);
                    }
                    String string = aVar3 == aVar4 ? resources.getString(R$string.verify_sheet_title) : aVar3 == aVar5 ? resources.getString(R$string.verify_sheet_title_auto_reauthn) : String.format(resources.getString(R$string.account_selection_sheet_title_explicit), str2, (String) propertyModel11.j(lVar7));
                    textView6.setText(string);
                    if (aVar3 == aVar4 || aVar3 == aVar5) {
                        textView6.setContentDescription(string);
                    } else {
                        StringBuilder sbK = C0173a.k(string, ". ");
                        sbK.append(resources.getString(R$string.bottom_sheet_accessibility_description));
                        textView6.setContentDescription(sbK.toString());
                    }
                    if (v62Var9 == lVar9) {
                        boolean z4 = aVar3 == aVar4 || aVar3 == aVar5;
                        View view7 = view;
                        view7.findViewById(R$id.header_progress_bar).setVisibility(z4 ? 0 : 8);
                        view7.findViewById(R$id.header_divider).setVisibility(!z4 ? 0 : 8);
                    }
                } else {
                    PropertyModel.l<Bitmap> lVar10 = v1.e;
                    if (v62Var9 != lVar10) {
                        PropertyModel.l<Runnable> lVar11 = v1.a;
                        if (v62Var9 == lVar11) {
                            view6.findViewById(R$id.close_button).setOnClickListener(new p10(4, (Runnable) propertyModel11.j(lVar11)));
                        }
                    } else {
                        Bitmap bitmap = (Bitmap) propertyModel11.j(lVar10);
                        if (bitmap != null) {
                            Resources resources2 = view6.getResources();
                            int dimensionPixelSize2 = resources2.getDimensionPixelSize(R$dimen.account_selection_sheet_icon_size);
                            int iFloor = (int) Math.floor(bitmap.getWidth() * 0.8f);
                            int iFloor2 = (int) Math.floor(bitmap.getHeight() * 0.8f);
                            int iFloor3 = (int) Math.floor((bitmap.getWidth() - iFloor) / 2.0f);
                            int iFloor4 = (int) Math.floor((bitmap.getHeight() - iFloor2) / 2.0f);
                            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize2, dimensionPixelSize2, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(bitmapCreateBitmap);
                            canvas.drawColor(0);
                            float f = dimensionPixelSize2 / 2.0f;
                            Paint paint = new Paint();
                            paint.setAntiAlias(true);
                            paint.setColor(-1);
                            canvas.drawCircle(f, f, f, paint);
                            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                            canvas.drawBitmap(bitmap, new Rect(iFloor3, iFloor4, iFloor + iFloor3, iFloor2 + iFloor4), new Rect(0, 0, dimensionPixelSize2, dimensionPixelSize2), paint);
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(resources2, bitmapCreateBitmap);
                            ImageView imageView3 = (ImageView) view6.findViewById(R$id.header_idp_icon);
                            imageView3.setImageDrawable(bitmapDrawable);
                            imageView3.setVisibility(0);
                        }
                    }
                }
                break;
            case 20:
                org.chromium.chrome.browser.ui.appmenu.a.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 21:
                PropertyModel propertyModel12 = (PropertyModel) kVar;
                OtpVerificationDialogView otpVerificationDialogView = (OtpVerificationDialogView) obj;
                v62 v62Var10 = (v62) obj2;
                PropertyModel.r<Optional<CharSequence>> rVar27 = org.chromium.chrome.browser.ui.autofill.e.d;
                if (!v62Var10.equals(rVar27)) {
                    PropertyModel.l<String> lVar12 = org.chromium.chrome.browser.ui.autofill.e.b;
                    if (!v62Var10.equals(lVar12)) {
                        PropertyModel.r<e.a> rVar28 = org.chromium.chrome.browser.ui.autofill.e.c;
                        if (!v62Var10.equals(rVar28)) {
                            PropertyModel.r<Optional<String>> rVar29 = org.chromium.chrome.browser.ui.autofill.e.e;
                            if (!v62Var10.equals(rVar29)) {
                                PropertyModel.r<String> rVar30 = org.chromium.chrome.browser.ui.autofill.e.f;
                                if (!v62Var10.equals(rVar30)) {
                                    PropertyModel.n nVar8 = org.chromium.chrome.browser.ui.autofill.e.g;
                                    if (v62Var10.equals(nVar8)) {
                                        if (!propertyModel12.k(nVar8)) {
                                            otpVerificationDialogView.e.setVisibility(8);
                                            otpVerificationDialogView.e.animate().alpha(0.0f).setDuration(250L);
                                            otpVerificationDialogView.f.animate().alpha(1.0f).setDuration(250L);
                                        } else {
                                            otpVerificationDialogView.e.setVisibility(0);
                                            otpVerificationDialogView.e.setAlpha(0.0f);
                                            otpVerificationDialogView.e.animate().alpha(1.0f).setDuration(250L);
                                            otpVerificationDialogView.f.animate().alpha(0.0f).setDuration(250L);
                                        }
                                    }
                                } else {
                                    String str9 = (String) propertyModel12.j(rVar30);
                                    otpVerificationDialogView.e.findViewById(R$id.progress_bar).setVisibility(8);
                                    otpVerificationDialogView.e.findViewById(R$id.confirmation_icon).setVisibility(0);
                                    ((TextView) otpVerificationDialogView.e.findViewById(R$id.progress_bar_message)).setText(str9);
                                }
                            } else {
                                Optional optional = (Optional) propertyModel12.j(rVar29);
                                if (!optional.isPresent()) {
                                    otpVerificationDialogView.h.setVisibility(8);
                                } else {
                                    otpVerificationDialogView.h.setVisibility(0);
                                    otpVerificationDialogView.h.setText((CharSequence) optional.get());
                                }
                            }
                        } else {
                            otpVerificationDialogView.setViewDelegate((e.a) propertyModel12.j(rVar28));
                        }
                    } else {
                        otpVerificationDialogView.setEditTextHint((String) propertyModel12.j(lVar12));
                    }
                } else if (!((Optional) propertyModel12.j(rVar27)).isPresent()) {
                    otpVerificationDialogView.g.getText().clear();
                }
                break;
            case 22:
                PropertyModel propertyModel13 = (PropertyModel) kVar;
                b90 b90Var = (b90) obj;
                v62 v62Var11 = (v62) obj2;
                int i11 = 9;
                if (v62Var11 == fk0.k) {
                    b90Var.b.setNavigationOnClickListener(new ij3(i11, propertyModel13));
                    break;
                } else {
                    PropertyModel.r<Toolbar.OnMenuItemClickListener> rVar31 = fk0.l;
                    if (v62Var11 == rVar31) {
                        b90Var.b.setOnMenuItemClickListener((Toolbar.OnMenuItemClickListener) propertyModel13.j(rVar31));
                        break;
                    } else {
                        PropertyModel.p pVar4 = fk0.h;
                        if (v62Var11 == pVar4) {
                            b90Var.b.setTitle(propertyModel13.h(pVar4));
                            break;
                        } else {
                            PropertyModel.p pVar5 = fk0.i;
                            if (v62Var11 == pVar5) {
                                b90Var.b.setContentDescription(b90Var.a.getResources().getString(propertyModel13.h(pVar5)));
                                break;
                            } else {
                                PropertyModel.p pVar6 = fk0.j;
                                if (v62Var11 == pVar6) {
                                    b90Var.c.setTitle(propertyModel13.h(pVar6));
                                    break;
                                } else {
                                    PropertyModel.r<ya1> rVar32 = fk0.m;
                                    if (v62Var11 == rVar32) {
                                        m mVar = new m((ya1) propertyModel13.j(rVar32));
                                        mVar.H(2, new ea(i), new n1(i4));
                                        mVar.H(3, new da(i2), new ee3(i3));
                                        mVar.H(1, new ea(i11), new n1(i5));
                                        b90Var.d.setAdapter(mVar);
                                        break;
                                    } else {
                                        PropertyModel.p pVar7 = fk0.b;
                                        if (v62Var11 == pVar7 && propertyModel13.h(pVar7) == 0) {
                                            b90Var.d.suppressLayout(false);
                                            ((LinearLayoutManager) b90Var.d.getLayoutManager()).g1(0, 0);
                                            b90Var.e.f = 0;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 23:
                es2.a((PropertyModel) kVar, (es2.a) obj, (v62) obj2);
                break;
            case 24:
                es2.a((PropertyModel) kVar, (es2.a) obj, (v62) obj2);
                break;
            case 25:
                p73.a((PropertyModel) kVar, (TasksView) obj, (v62) obj2);
                break;
            case 26:
                ko.a((PropertyModel) kVar, (ChipView) obj, (v62) obj2);
                break;
            case 27:
                break;
            case 28:
                PropertyModel propertyModel14 = (PropertyModel) kVar;
                kf2 kf2Var = (kf2) obj;
                v62 v62Var12 = (v62) obj2;
                if (jf2.a != v62Var12 && jf2.b != v62Var12) {
                    PropertyModel.l<View> lVar13 = jf2.c;
                    if (lVar13 == v62Var12) {
                        View view8 = (View) propertyModel14.j(lVar13);
                        uh3.l(kf2Var);
                        if (view8 != null) {
                            boolean zK8 = propertyModel14.k(jf2.d);
                            while (true) {
                                ViewParent parent = view8.getParent();
                                ViewGroup viewGroup = kf2Var.e;
                                if (parent == viewGroup) {
                                    if (zK8) {
                                        uh3.g(kf2Var, view8, viewGroup);
                                    } else {
                                        uh3.h(kf2Var, view8, viewGroup);
                                    }
                                    ((ViewGroup.MarginLayoutParams) kf2Var.getLayoutParams()).topMargin = propertyModel14.h(jf2.a);
                                } else {
                                    view8 = (View) view8.getParent();
                                }
                                break;
                            }
                        }
                    } else if (jf2.d != v62Var12) {
                        PropertyModel.l<Runnable> lVar14 = jf2.f;
                        if (lVar14 == v62Var12) {
                            if (propertyModel14.j(lVar14) != null) {
                                kf2Var.setOnClickListener(new rg1(i3, propertyModel14));
                            }
                            break;
                        } else if (jf2.e != v62Var12) {
                            PropertyModel.o oVar3 = jf2.g;
                            if (oVar3 == v62Var12) {
                                float fG2 = propertyModel14.g(oVar3);
                                kf2Var.setAlpha(fG2);
                                i = fG2 > 0.0f ? 0 : 8;
                                if (kf2Var.getVisibility() != i) {
                                    kf2Var.setVisibility(i);
                                }
                                break;
                            } else {
                                PropertyModel.p pVar8 = jf2.h;
                                if (pVar8 != v62Var12) {
                                    PropertyModel.r<Drawable> rVar33 = jf2.i;
                                    if (rVar33 == v62Var12 && propertyModel14.j(rVar33) != null) {
                                        kf2Var.setBackgroundDrawable((Drawable) propertyModel14.j(rVar33));
                                        break;
                                    }
                                } else if (propertyModel14.j(jf2.i) == null) {
                                    kf2Var.setBackgroundColor(propertyModel14.h(pVar8));
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            default:
                PropertyModel propertyModel15 = (PropertyModel) kVar;
                PlayerFrameView playerFrameView = (PlayerFrameView) obj;
                v62 v62Var13 = (v62) obj2;
                PropertyModel.r<Bitmap[][]> rVar34 = a22.a;
                if (!v62Var13.equals(rVar34)) {
                    PropertyModel.r<Size> rVar35 = a22.b;
                    if (v62Var13.equals(rVar35)) {
                        playerFrameView.e.a = (Size) propertyModel15.j(rVar35);
                        break;
                    } else {
                        PropertyModel.r<Point> rVar36 = a22.c;
                        if (v62Var13.equals(rVar36)) {
                            Point point = (Point) propertyModel15.j(rVar36);
                            playerFrameView.k.setTranslate(point.x, point.y);
                            break;
                        } else {
                            PropertyModel.r<Rect> rVar37 = a22.d;
                            if (v62Var13.equals(rVar37)) {
                                Rect rect3 = (Rect) propertyModel15.j(rVar37);
                                int i12 = rect3.left;
                                int i13 = rect3.top;
                                int i14 = rect3.right;
                                int i15 = rect3.bottom;
                                t12 t12Var = playerFrameView.e;
                                if (!t12Var.h) {
                                    t12Var.c.set(i12, i13, i14, i15);
                                    t12Var.f.run();
                                }
                                playerFrameView.a();
                                break;
                            } else {
                                PropertyModel.r<List<View>> rVar38 = a22.e;
                                if (v62Var13.equals(rVar38)) {
                                    playerFrameView.h = (List) propertyModel15.j(rVar38);
                                    break;
                                } else {
                                    PropertyModel.r<List<Rect>> rVar39 = a22.f;
                                    if (v62Var13.equals(rVar39)) {
                                        playerFrameView.i = (List) propertyModel15.j(rVar39);
                                        break;
                                    } else {
                                        PropertyModel.r<Matrix> rVar40 = a22.g;
                                        if (v62Var13.equals(rVar40)) {
                                            Matrix matrix = (Matrix) propertyModel15.j(rVar40);
                                            playerFrameView.j = matrix;
                                            if (!matrix.isIdentity()) {
                                                playerFrameView.postInvalidate();
                                                playerFrameView.a();
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Bitmap[][] bitmapArr = (Bitmap[][]) propertyModel15.j(rVar34);
                    t12 t12Var2 = playerFrameView.e;
                    if (!t12Var2.h) {
                        t12Var2.b = bitmapArr;
                        t12Var2.f.run();
                        break;
                    }
                }
                break;
        }
    }
}
