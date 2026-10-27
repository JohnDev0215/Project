package defpackage;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.he3;
import defpackage.il1;
import defpackage.k81;
import defpackage.le3;
import defpackage.om3;
import defpackage.yw1;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;
import org.chromium.chrome.browser.feed.feedmanagement.FeedManagementItemView;
import org.chromium.chrome.browser.feed.sort_ui.FeedOptionsView;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c;
import org.chromium.chrome.browser.omnibox.suggestions.dividerline.DividerLineView;
import org.chromium.chrome.browser.omnibox.suggestions.header.HeaderView;
import org.chromium.chrome.browser.omnibox.suggestions.tail.TailSuggestionView;
import org.chromium.chrome.browser.password_check.CompromisedCredential;
import org.chromium.chrome.browser.password_check.PasswordCheckCoordinator;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.password_entry_edit.BlockedCredentialFragmentView;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.password_entry_edit.e;
import org.chromium.chrome.browser.password_manager.PasswordManagerDialogView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.tile.MostVisitedTilesCarouselLayout;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;
import org.chromium.chrome.browser.tasks.tab_management.h1;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.t0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.chrome.browser.ui.signin.PersonalizedSigninPromoView;
import org.chromium.components.browser_ui.styles.R$style;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zf implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ zf(int i) {
        this.a = i;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        int iH;
        ColorStateList colorStateList;
        int i = 7;
        int i2 = 2;
        switch (this.a) {
            case 0:
                PropertyModel propertyModel = (PropertyModel) kVar;
                View view = (View) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.r<pg> rVar = ig.a;
                if (v62Var == rVar) {
                    PersonalizedSigninPromoView personalizedSigninPromoView = (PersonalizedSigninPromoView) view.findViewById(R$id.signin_promo_view_container);
                    pg pgVar = (pg) propertyModel.j(rVar);
                    pgVar.getClass();
                    pgVar.j.g(pgVar.i, personalizedSigninPromoView, new pq2(i, pgVar));
                }
                break;
            case 1:
                lg.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 2:
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                zs2 zs2Var = (zs2) obj;
                zs2Var.getClass();
                if (propertyModel2 != null) {
                    N.MatWNfnY(zs2Var.f, zs2Var, propertyModel2.h(m61.e), propertyModel2.k(m61.B), propertyModel2.h(m61.M), propertyModel2.g(m61.j) * m61.c, (propertyModel2.g(m61.k) * m61.c) + propertyModel2.g(m61.Q), propertyModel2.g(m61.x), propertyModel2.g(m61.q), propertyModel2.g(m61.y));
                    break;
                }
                break;
            case 3:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                View view2 = (View) obj;
                v62 v62Var2 = (v62) obj2;
                PropertyModel.r<String> rVar2 = oz.a;
                if (v62Var2 == rVar2) {
                    TextView textView = (TextView) view2.findViewById(R$id.menu_header_title);
                    textView.setText((CharSequence) propertyModel3.j(rVar2));
                    textView.setVisibility(TextUtils.isEmpty((CharSequence) propertyModel3.j(rVar2)) ? 8 : 0);
                    break;
                } else {
                    PropertyModel.p pVar = oz.b;
                    if (v62Var2 == pVar) {
                        int iH2 = propertyModel3.h(pVar);
                        TextView textView2 = (TextView) view2.findViewById(R$id.menu_header_title);
                        textView2.setMaxLines(iH2);
                        if (iH2 != Integer.MAX_VALUE) {
                            textView2.setEllipsize(TextUtils.TruncateAt.END);
                        } else {
                            textView2.setEllipsize(null);
                        }
                        break;
                    } else {
                        PropertyModel.r<CharSequence> rVar3 = oz.c;
                        if (v62Var2 == rVar3) {
                            TextView textView3 = (TextView) view2.findViewById(R$id.menu_header_url);
                            textView3.setText((CharSequence) propertyModel3.j(rVar3));
                            textView3.setVisibility(TextUtils.isEmpty((CharSequence) propertyModel3.j(rVar3)) ? 8 : 0);
                            break;
                        } else {
                            PropertyModel.r<View.OnClickListener> rVar4 = oz.d;
                            if (v62Var2 == rVar4) {
                                view2.findViewById(R$id.title_and_url).setOnClickListener((View.OnClickListener) propertyModel3.j(rVar4));
                                break;
                            } else {
                                PropertyModel.p pVar2 = oz.e;
                                if (v62Var2 == pVar2) {
                                    int iH3 = propertyModel3.h(pVar2);
                                    TextView textView4 = (TextView) view2.findViewById(R$id.menu_header_url);
                                    textView4.setMaxLines(iH3);
                                    if (iH3 != Integer.MAX_VALUE) {
                                        textView4.setEllipsize(TextUtils.TruncateAt.END);
                                    } else {
                                        textView4.setEllipsize(null);
                                    }
                                    break;
                                } else {
                                    PropertyModel.r<Bitmap> rVar5 = oz.f;
                                    if (v62Var2 == rVar5) {
                                        Bitmap bitmap = (Bitmap) propertyModel3.j(rVar5);
                                        if (bitmap != null) {
                                            ((ImageView) view2.findViewById(R$id.menu_header_image)).setImageBitmap(bitmap);
                                        }
                                        break;
                                    } else {
                                        PropertyModel.n nVar = oz.g;
                                        if (v62Var2 == nVar) {
                                            view2.findViewById(R$id.circle_background).setVisibility(propertyModel3.k(nVar) ? 0 : 4);
                                            break;
                                        } else {
                                            PropertyModel.p pVar3 = oz.i;
                                            if (v62Var2 == pVar3) {
                                                int iH4 = propertyModel3.h(pVar3);
                                                if (-1 != iH4) {
                                                    View viewFindViewById = view2.findViewById(R$id.menu_header_image);
                                                    ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
                                                    layoutParams.width = iH4;
                                                    layoutParams.height = iH4;
                                                    viewFindViewById.setLayoutParams(layoutParams);
                                                }
                                                break;
                                            } else {
                                                PropertyModel.p pVar4 = oz.j;
                                                if (v62Var2 == pVar4) {
                                                    int iH5 = propertyModel3.h(pVar4);
                                                    if (-1 != iH5) {
                                                        View viewFindViewById2 = view2.findViewById(R$id.circle_background);
                                                        ViewGroup.LayoutParams layoutParams2 = viewFindViewById2.getLayoutParams();
                                                        layoutParams2.width = iH5;
                                                        layoutParams2.height = iH5;
                                                        viewFindViewById2.setLayoutParams(layoutParams2);
                                                    }
                                                    break;
                                                } else {
                                                    PropertyModel.p pVar5 = oz.k;
                                                    if (v62Var2 == pVar5 && -1 != (iH = propertyModel3.h(pVar5))) {
                                                        View viewFindViewById3 = view2.findViewById(R$id.circle_background);
                                                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById3.getLayoutParams();
                                                        marginLayoutParams.setMargins(iH, iH, iH, iH);
                                                        viewFindViewById3.setLayoutParams(marginLayoutParams);
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
                }
                break;
            case 4:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                DownloadLocationCustomView downloadLocationCustomView = (DownloadLocationCustomView) obj;
                v62 v62Var3 = (v62) obj2;
                PropertyModel.l<CharSequence> lVar = pc0.a;
                if (v62Var3 != lVar) {
                    PropertyModel.l<CharSequence> lVar2 = pc0.b;
                    if (v62Var3 != lVar2) {
                        PropertyModel.h hVar = pc0.c;
                        if (v62Var3 != hVar) {
                            PropertyModel.h hVar2 = pc0.d;
                            if (v62Var3 != hVar2) {
                                PropertyModel.l<CharSequence> lVar3 = pc0.e;
                                if (v62Var3 != lVar3) {
                                    PropertyModel.l<CharSequence> lVar4 = pc0.f;
                                    if (v62Var3 != lVar4) {
                                        PropertyModel.h hVar3 = pc0.g;
                                        if (v62Var3 != hVar3) {
                                            PropertyModel.h hVar4 = pc0.h;
                                            if (v62Var3 != hVar4) {
                                                PropertyModel.h hVar5 = pc0.i;
                                                if (v62Var3 == hVar5) {
                                                    downloadLocationCustomView.m.setVisibility(propertyModel4.k(hVar5) ? 0 : 8);
                                                }
                                            } else {
                                                downloadLocationCustomView.setDontShowAgainCheckbox(propertyModel4.k(hVar4));
                                            }
                                        } else {
                                            downloadLocationCustomView.l.setVisibility(propertyModel4.k(hVar3) ? 0 : 8);
                                        }
                                    } else {
                                        downloadLocationCustomView.setFileSize((CharSequence) propertyModel4.j(lVar4));
                                    }
                                } else {
                                    downloadLocationCustomView.setFileName((CharSequence) propertyModel4.j(lVar3));
                                }
                            } else {
                                downloadLocationCustomView.h.setVisibility(propertyModel4.k(hVar2) ? 0 : 8);
                            }
                        } else {
                            boolean zK = propertyModel4.k(hVar);
                            downloadLocationCustomView.g.setVisibility(zK ? 0 : 8);
                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) downloadLocationCustomView.f.getLayoutParams();
                            marginLayoutParams2.bottomMargin = downloadLocationCustomView.getResources().getDimensionPixelSize(zK ? R$dimen.download_dialog_title_margin_bottom : R$dimen.download_dialog_subtitle_margin_bottom);
                            downloadLocationCustomView.f.setLayoutParams(marginLayoutParams2);
                        }
                    } else {
                        downloadLocationCustomView.setSubtitle((CharSequence) propertyModel4.j(lVar2));
                    }
                } else {
                    downloadLocationCustomView.setTitle((CharSequence) propertyModel4.j(lVar));
                }
                break;
            case 5:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                FeedManagementItemView feedManagementItemView = (FeedManagementItemView) obj;
                v62 v62Var4 = (v62) obj2;
                PropertyModel.r<String> rVar6 = bl0.a;
                if (rVar6 != v62Var4) {
                    PropertyModel.r<String> rVar7 = bl0.b;
                    if (rVar7 != v62Var4) {
                        PropertyModel.r<View.OnClickListener> rVar8 = bl0.c;
                        if (rVar8 == v62Var4) {
                            feedManagementItemView.setOnClickListener((View.OnClickListener) propertyModel5.j(rVar8));
                        }
                    } else {
                        feedManagementItemView.setDescription((String) propertyModel5.j(rVar7));
                    }
                } else {
                    feedManagementItemView.setTitle((String) propertyModel5.j(rVar6));
                }
                break;
            case 6:
                break;
            case 7:
                PropertyModel propertyModel6 = (PropertyModel) kVar;
                FeedOptionsView feedOptionsView = (FeedOptionsView) obj;
                v62 v62Var5 = (v62) obj2;
                PropertyModel.n nVar2 = el0.a;
                if (v62Var5 == nVar2) {
                    feedOptionsView.setVisibility(propertyModel6.k(nVar2));
                }
                break;
            case 8:
                PropertyModel propertyModel7 = (PropertyModel) kVar;
                View view3 = (View) obj;
                v62 v62Var6 = (v62) obj2;
                PropertyModel.r<Drawable> rVar9 = il1.b.a;
                if (rVar9 != v62Var6) {
                    PropertyModel.r<String> rVar10 = il1.b.b;
                    if (rVar10 != v62Var6) {
                        PropertyModel.r<View.OnClickListener> rVar11 = il1.b.c;
                        if (rVar11 == v62Var6) {
                            view3.setOnClickListener((View.OnClickListener) propertyModel7.j(rVar11));
                        }
                    } else {
                        ((TextView) view3.findViewById(R$id.entry_title)).setText((CharSequence) propertyModel7.j(rVar10));
                    }
                } else {
                    ((ImageView) view3.findViewById(R$id.favicon_img)).setImageDrawable((Drawable) propertyModel7.j(rVar9));
                }
                break;
            case 9:
                PropertyModel propertyModel8 = (PropertyModel) kVar;
                View view4 = (View) obj;
                v62 v62Var7 = (v62) obj2;
                PropertyModel.l<s10> lVar5 = c.a.a;
                s10 s10Var = (s10) propertyModel8.j(lVar5);
                ChipView chipView = (ChipView) view4.findViewById(R$id.suggestion_text);
                ChipView chipView2 = (ChipView) view4.findViewById(R$id.password_text);
                PropertyModel.l<Callback<u10>> lVar6 = c.a.b;
                PropertyModel.h hVar6 = c.a.c;
                if (v62Var7 == lVar6 || v62Var7 == hVar6) {
                    boolean zK2 = propertyModel8.k(hVar6);
                    Callback callback = (Callback) propertyModel8.j(lVar6);
                    if (s10Var.a.isEmpty()) {
                        chipView.setOnClickListener(null);
                    } else {
                        chipView.setOnClickListener(new n40(callback, 3, s10Var));
                    }
                    if (zK2) {
                        chipView2.setOnClickListener(new rf(callback, i2, s10Var));
                    } else {
                        chipView2.setOnClickListener(new vt(13, chipView2));
                    }
                    chipView.setEnabled(chipView.hasOnClickListeners());
                    chipView.setClickable(chipView.hasOnClickListeners());
                    chipView2.setEnabled(chipView2.hasOnClickListeners());
                    chipView2.setClickable(chipView2.hasOnClickListeners());
                    break;
                } else if (v62Var7 == lVar5) {
                    TextView textView5 = (TextView) view4.findViewById(R$id.password_info_title);
                    boolean z = s10Var.e;
                    String str = s10Var.f;
                    String str2 = s10Var.d;
                    textView5.setText(z ? str : oj2.j(2, new GURL(str2)));
                    chipView.getPrimaryTextView().setText(z7.q(s10Var.c));
                    String string = s10Var.b;
                    boolean zIsEmpty = string.isEmpty();
                    if (!zIsEmpty) {
                        chipView2.getPrimaryTextView().setTransformationMethod(new PasswordTransformationMethod());
                    }
                    TextView primaryTextView = chipView2.getPrimaryTextView();
                    if (zIsEmpty) {
                        string = view4.getContext().getString(R$string.all_passwords_bottom_sheet_no_password);
                    }
                    primaryTextView.setText(string);
                    Context context = view4.getContext();
                    nk0 nk0Var = new nk0(context);
                    ImageView imageView = (ImageView) view4.findViewById(R$id.favicon);
                    boolean z2 = s10Var.e;
                    Drawable drawableE = a.e(null, z2 ? str : str2, context.getColor(R$color.default_favicon_background_color), nk0Var.b, context.getResources(), nk0Var.c);
                    int dimensionPixelSize = imageView.getContext().getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_suggestion_icon_size);
                    drawableE.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                    imageView.setImageDrawable(drawableE);
                    if (!z2) {
                        td2 td2Var = new td2(i2, imageView);
                        LargeIconBridge largeIconBridge = new LargeIconBridge(Profile.d());
                        GURL gurl = new GURL(str2);
                        if (gurl.b) {
                            largeIconBridge.b(gurl, nk0Var.c, new qk0(nk0Var, i, gurl, td2Var));
                            break;
                        }
                    }
                }
                break;
            case 10:
                i11.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 11:
                gw2.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 12:
                PropertyModel propertyModel9 = (PropertyModel) kVar;
                HeaderView headerView = (HeaderView) obj;
                v62 v62Var8 = (v62) obj2;
                PropertyModel.r<String> rVar12 = eu0.a;
                if (rVar12 != v62Var8) {
                    PropertyModel.p pVar6 = bw2.a;
                    if (v62Var8 != pVar6) {
                        PropertyModel.p pVar7 = bw2.b;
                        if (v62Var8 != pVar7) {
                            PropertyModel.n nVar3 = eu0.b;
                            if (v62Var8 == nVar3) {
                                boolean zK3 = propertyModel9.k(nVar3);
                                Resources resources = headerView.getResources();
                                int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_header_height);
                                int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_header_padding_start);
                                int dimensionPixelSize4 = resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_header_padding_top);
                                int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_header_padding_bottom);
                                if (zK3) {
                                    dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_header_height_modern_phase2);
                                    dimensionPixelSize3 += resources.getDimensionPixelSize(R$dimen.omnibox_suggestion_side_spacing);
                                    dimensionPixelSize5 = 0;
                                }
                                headerView.setMinHeight(dimensionPixelSize2);
                                headerView.setPaddingRelative(dimensionPixelSize3, dimensionPixelSize4, 0, dimensionPixelSize5);
                            }
                        } else {
                            int iH6 = propertyModel9.h(pVar7);
                            WeakHashMap<View, in3> weakHashMap = om3.a;
                            om3.e.h(headerView, iH6);
                        }
                    } else {
                        i = propertyModel9.h(pVar6) != 2 ? 0 : 1;
                        String str3 = yp.a;
                        headerView.setTextAppearance(i != 0 ? R$style.TextAppearance_TextMediumThick_Secondary_Baseline_Light : R$style.TextAppearance_TextMediumThick_Secondary);
                    }
                } else {
                    headerView.setText((CharSequence) propertyModel9.j(rVar12));
                }
                break;
            case 13:
                PropertyModel propertyModel10 = (PropertyModel) kVar;
                DividerLineView dividerLineView = (DividerLineView) obj;
                v62 v62Var9 = (v62) obj2;
                PropertyModel.p pVar8 = bw2.a;
                if (pVar8 == v62Var9) {
                    if (propertyModel10.h(pVar8) != 2) {
                        dividerLineView.getDivider().setBackgroundColor(ii2.e(dividerLineView.getContext()));
                    } else {
                        dividerLineView.getDivider().setBackgroundResource(R$color.divider_line_bg_color_light);
                    }
                }
                break;
            case 14:
                PropertyModel propertyModel11 = (PropertyModel) kVar;
                View view5 = (View) obj;
                v62 v62Var10 = (v62) obj2;
                PropertyModel.r<Spannable> rVar13 = z5.b;
                if (rVar13 != v62Var10) {
                    PropertyModel.r<Spannable> rVar14 = z5.e;
                    if (rVar14 != v62Var10) {
                        PropertyModel.r<String> rVar15 = z5.c;
                        if (rVar15 != v62Var10) {
                            PropertyModel.r<String> rVar16 = z5.f;
                            if (rVar16 != v62Var10) {
                                PropertyModel.p pVar9 = z5.a;
                                if (pVar9 != v62Var10) {
                                    PropertyModel.p pVar10 = z5.d;
                                    if (pVar10 == v62Var10) {
                                        ((TextView) view5.findViewById(R$id.omnibox_answer_line_2)).setMaxLines(propertyModel11.h(pVar10));
                                    }
                                } else {
                                    ((TextView) view5.findViewById(R$id.omnibox_answer_line_1)).setMaxLines(propertyModel11.h(pVar9));
                                }
                            } else {
                                ((TextView) view5.findViewById(R$id.omnibox_answer_line_2)).setContentDescription((CharSequence) propertyModel11.j(rVar16));
                            }
                        } else {
                            ((TextView) view5.findViewById(R$id.omnibox_answer_line_1)).setContentDescription((CharSequence) propertyModel11.j(rVar15));
                        }
                    } else {
                        ((TextView) view5.findViewById(R$id.omnibox_answer_line_2)).setText((CharSequence) propertyModel11.j(rVar14));
                    }
                } else {
                    ((TextView) view5.findViewById(R$id.omnibox_answer_line_1)).setText((CharSequence) propertyModel11.j(rVar13));
                }
                break;
            case 15:
                PropertyModel propertyModel12 = (PropertyModel) kVar;
                TailSuggestionView tailSuggestionView = (TailSuggestionView) obj;
                v62 v62Var11 = (v62) obj2;
                PropertyModel.r<f4> rVar17 = p63.c;
                if (rVar17 != v62Var11) {
                    PropertyModel.r<ew2> rVar18 = p63.a;
                    if (v62Var11 != rVar18) {
                        PropertyModel.r<String> rVar19 = p63.b;
                        if (v62Var11 != rVar19) {
                            PropertyModel.p pVar11 = bw2.a;
                            if (v62Var11 == pVar11) {
                                Context context2 = tailSuggestionView.getContext();
                                tailSuggestionView.setTextColor(propertyModel12.h(pVar11) == 2 ? context2.getColor(R$color.default_text_color_light) : oj2.n(R$attr.colorOnSurface, context2, "OmniboxResourceProvider"));
                            }
                        } else {
                            tailSuggestionView.setFullText((String) propertyModel12.j(rVar19));
                        }
                    } else {
                        tailSuggestionView.setTailText((Spannable) propertyModel12.j(rVar18));
                    }
                } else {
                    tailSuggestionView.setAlignmentManager((f4) propertyModel12.j(rVar17));
                }
                break;
            case 16:
                break;
            case 17:
                gw2.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 18:
                final PropertyModel propertyModel13 = (PropertyModel) kVar;
                final View view6 = (View) obj;
                v62 v62Var12 = (v62) obj2;
                PropertyModel.l<CompromisedCredential> lVar7 = h.a.a;
                CompromisedCredential compromisedCredential = (CompromisedCredential) propertyModel13.j(lVar7);
                if (v62Var12 == lVar7) {
                    ((TextView) view6.findViewById(R$id.credential_origin)).setText(compromisedCredential.h);
                    ((TextView) view6.findViewById(R$id.compromised_username)).setText(compromisedCredential.i);
                    ((TextView) view6.findViewById(R$id.compromised_reason)).setText(!compromisedCredential.p ? R$string.password_check_credential_row_reason_leaked : !compromisedCredential.o ? R$string.password_check_credential_row_reason_phished : R$string.password_check_credential_row_reason_leaked_and_phished);
                    ((ListMenuButton) view6.findViewById(R$id.credential_menu_button)).setDelegate(new l81() { // from class: ax1
                        @Override // defpackage.l81
                        public final k81 b() {
                            final Context context3 = view6.getContext();
                            PropertyModel.l<CompromisedCredential> lVar8 = h.a.a;
                            PropertyModel propertyModel14 = propertyModel13;
                            final CompromisedCredential compromisedCredential2 = (CompromisedCredential) propertyModel14.j(lVar8);
                            final PasswordCheckCoordinator.a aVar = (PasswordCheckCoordinator.a) propertyModel14.j(h.a.b);
                            ya1 ya1Var = new ya1();
                            ya1Var.t(zd.e(R$string.password_check_credential_menu_item_view_button_caption, 0, 0, true));
                            ya1Var.t(zd.e(R$string.password_check_credential_menu_item_edit_button_caption, 0, 0, true));
                            ya1Var.t(zd.e(R$string.password_check_credential_menu_item_remove_button_caption, 0, 0, true));
                            return new zd(context3, ya1Var, new k81.a() { // from class: bx1
                                @Override // k81.a
                                public final void a(PropertyModel propertyModel15) {
                                    int iH7 = propertyModel15.h(o81.a);
                                    int i3 = R$string.password_check_credential_menu_item_view_button_caption;
                                    PasswordCheckCoordinator.a aVar2 = aVar;
                                    CompromisedCredential compromisedCredential3 = compromisedCredential2;
                                    if (iH7 == i3) {
                                        aVar2.g(compromisedCredential3);
                                    } else if (iH7 == R$string.password_check_credential_menu_item_edit_button_caption) {
                                        aVar2.a(compromisedCredential3, context3);
                                    } else if (iH7 == R$string.password_check_credential_menu_item_remove_button_caption) {
                                        aVar2.d(compromisedCredential3);
                                    }
                                }
                            });
                        }
                    });
                    ButtonCompat buttonCompat = (ButtonCompat) view6.findViewById(R$id.credential_change_button);
                    buttonCompat.setOnClickListener(new n40(propertyModel13, 5, compromisedCredential));
                    Drawable[] compoundDrawablesRelative = buttonCompat.getCompoundDrawablesRelative();
                    Context context3 = view6.getContext();
                    int i3 = R$color.default_text_color_on_accent1_list;
                    int length = compoundDrawablesRelative.length;
                    while (i < length) {
                        Drawable drawable = compoundDrawablesRelative[i];
                        if (drawable != null) {
                            drawable.setTintList(bz.b(context3, i3));
                        }
                        i++;
                    }
                    break;
                } else if (v62Var12 != h.a.b) {
                    PropertyModel.h hVar7 = h.a.c;
                    if (v62Var12 != hVar7) {
                        PropertyModel.r<yw1.a> rVar20 = h.a.d;
                        if (v62Var12 == rVar20) {
                            ImageView imageView2 = (ImageView) view6.findViewById(R$id.credential_favicon);
                            yw1.a aVar = (yw1.a) propertyModel13.j(rVar20);
                            Resources resources2 = view6.getResources();
                            Context context4 = view6.getContext();
                            imageView2.setImageDrawable(a.e(aVar.b, aVar.a, aVar.d ? context4.getColor(org.chromium.chrome.browser.ui.favicon.R$color.default_favicon_background_color) : aVar.c, a.a(context4), resources2, resources2.getDimensionPixelSize(org.chromium.chrome.browser.ui.favicon.R$dimen.default_favicon_size)));
                        }
                    } else {
                        ((ButtonCompat) view6.findViewById(R$id.credential_change_button)).setVisibility(propertyModel13.k(hVar7) ? 0 : 8);
                        ((TextView) view6.findViewById(R$id.credential_change_hint)).setVisibility(propertyModel13.k(hVar7) ? 8 : 0);
                    }
                    break;
                }
                break;
            case 19:
                PropertyModel propertyModel14 = (PropertyModel) kVar;
                BlockedCredentialFragmentView blockedCredentialFragmentView = (BlockedCredentialFragmentView) obj;
                v62 v62Var13 = (v62) obj2;
                PropertyModel.r<CredentialEntryFragmentViewBase.b> rVar21 = e.a;
                if (v62Var13 != rVar21) {
                    PropertyModel.l<String> lVar8 = e.b;
                    if (v62Var13 == lVar8) {
                        ((TextView) blockedCredentialFragmentView.f12J.findViewById(R$id.url_or_app)).setText((String) propertyModel14.j(lVar8));
                    } else if (v62Var13 == e.i) {
                        blockedCredentialFragmentView.M0();
                    }
                } else {
                    blockedCredentialFragmentView.i0 = (CredentialEntryFragmentViewBase.b) propertyModel14.j(rVar21);
                }
                break;
            case 20:
                PropertyModel propertyModel15 = (PropertyModel) kVar;
                v62 v62Var14 = (v62) obj2;
                PasswordManagerDialogView passwordManagerDialogView = (PasswordManagerDialogView) ((View) obj);
                PropertyModel.l<Runnable> lVar9 = nx1.a;
                if (lVar9 != v62Var14) {
                    PropertyModel.j jVar = nx1.b;
                    if (jVar != v62Var14) {
                        PropertyModel.n nVar4 = nx1.c;
                        if (nVar4 != v62Var14) {
                            PropertyModel.l<String> lVar10 = nx1.d;
                            if (lVar10 != v62Var14) {
                                PropertyModel.l<CharSequence> lVar11 = nx1.e;
                                if (lVar11 == v62Var14) {
                                    passwordManagerDialogView.setDetails((CharSequence) propertyModel15.j(lVar11));
                                }
                            } else {
                                passwordManagerDialogView.setTitle((String) propertyModel15.j(lVar10));
                            }
                        } else {
                            if (propertyModel15.k(nVar4)) {
                                passwordManagerDialogView.g.setVisibility(0);
                            } else {
                                passwordManagerDialogView.g.setVisibility(8);
                            }
                            boolean z3 = !propertyModel15.k(nVar4);
                            if (passwordManagerDialogView.e != null) {
                                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) passwordManagerDialogView.h.getLayoutParams();
                                layoutParams3.setMarginEnd(passwordManagerDialogView.getResources().getDimensionPixelSize(z3 ? R$dimen.password_manager_dialog_title_compact_margin : R$dimen.password_manager_dialog_text_margin));
                                passwordManagerDialogView.h.setLayoutParams(layoutParams3);
                                passwordManagerDialogView.e.setVisibility(z3 ? 8 : 0);
                                passwordManagerDialogView.f.setVisibility(z3 ? 0 : 8);
                            }
                            if (!qx1.c()) {
                                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(passwordManagerDialogView.g.getLayoutParams());
                                Resources resources3 = passwordManagerDialogView.getContext().getResources();
                                int i4 = R$dimen.password_manager_dialog_text_margin;
                                layoutParams4.setMarginStart(resources3.getDimensionPixelSize(i4));
                                layoutParams4.setMarginEnd(passwordManagerDialogView.getContext().getResources().getDimensionPixelSize(i4));
                                passwordManagerDialogView.g.setLayoutParams(layoutParams4);
                                passwordManagerDialogView.g.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                        }
                    } else {
                        passwordManagerDialogView.setIllustration(propertyModel15.h(jVar));
                    }
                    break;
                } else {
                    Runnable runnable = (Runnable) propertyModel15.j(lVar9);
                    ChromeImageButton chromeImageButton = passwordManagerDialogView.e;
                    if (chromeImageButton != null) {
                        chromeImageButton.setOnClickListener(new nd3(7, runnable));
                        passwordManagerDialogView.f.setOnClickListener(new od3(4, runnable));
                        passwordManagerDialogView.e.setVisibility(0);
                        break;
                    }
                }
                break;
            case 21:
                PropertyModel propertyModel16 = (PropertyModel) kVar;
                iz1 iz1Var = (iz1) obj;
                v62 v62Var15 = (v62) obj2;
                PropertyModel.p pVar12 = dz1.a;
                if (pVar12 != v62Var15) {
                    PropertyModel.r<Runnable> rVar22 = dz1.b;
                    if (rVar22 == v62Var15) {
                        iz1Var.j = (Runnable) propertyModel16.j(rVar22);
                    }
                } else {
                    int iH7 = propertyModel16.h(pVar12);
                    View view7 = iz1Var.g;
                    ViewGroup.LayoutParams layoutParams5 = view7.getLayoutParams();
                    layoutParams5.height = Math.max(0, iH7);
                    view7.setLayoutParams(layoutParams5);
                }
                break;
            case 22:
                PropertyModel propertyModel17 = (PropertyModel) kVar;
                yi1 yi1Var = (yi1) obj;
                v62 v62Var16 = (v62) obj2;
                PropertyModel.n nVar5 = xi1.a;
                if (nVar5 == v62Var16) {
                    yi1Var.a.setVisibility(propertyModel17.k(nVar5) ? 0 : 8);
                    break;
                } else {
                    PropertyModel.n nVar6 = xi1.b;
                    if (nVar6 != v62Var16) {
                        PropertyModel.r<Integer> rVar23 = xi1.d;
                        if (rVar23 != v62Var16) {
                            PropertyModel.r<Integer> rVar24 = xi1.e;
                            if (rVar24 == v62Var16) {
                                ((MostVisitedTilesCarouselLayout) yi1Var.b).setEdgePaddings(((Integer) propertyModel17.j(rVar24)).intValue());
                            }
                        } else {
                            ((MostVisitedTilesCarouselLayout) yi1Var.b).setIntervalPaddings(((Integer) propertyModel17.j(rVar23)).intValue());
                        }
                        break;
                    } else {
                        yi1Var.b.setVisibility(propertyModel17.k(nVar6) ? 0 : 8);
                        PropertyModel.r<View> rVar25 = xi1.c;
                        if (propertyModel17.j(rVar25) != null) {
                            ((View) propertyModel17.j(rVar25)).setVisibility(propertyModel17.k(nVar6) ? 8 : 0);
                            break;
                        }
                    }
                }
                break;
            case 23:
                PropertyModel propertyModel18 = (PropertyModel) kVar;
                p03 p03Var = (p03) obj;
                v62 v62Var17 = (v62) obj2;
                PropertyModel.r<View.OnClickListener> rVar26 = n03.a;
                if (rVar26 != v62Var17) {
                    PropertyModel.r<View.OnClickListener> rVar27 = n03.b;
                    if (rVar27 != v62Var17) {
                        PropertyModel.n nVar7 = n03.c;
                        if (nVar7 != v62Var17) {
                            PropertyModel.n nVar8 = n03.d;
                            if (nVar8 != v62Var17) {
                                PropertyModel.p pVar13 = n03.e;
                                if (pVar13 != v62Var17) {
                                    PropertyModel.r<Integer> rVar28 = n03.f;
                                    if (rVar28 != v62Var17) {
                                        PropertyModel.r<String> rVar29 = n03.g;
                                        if (rVar29 != v62Var17) {
                                            PropertyModel.r<String> rVar30 = n03.h;
                                            if (rVar30 == v62Var17) {
                                                p03Var.a.setRightButtonContentDescription((String) propertyModel18.j(rVar30));
                                            }
                                        } else {
                                            p03Var.a.setLeftButtonContentDescription((String) propertyModel18.j(rVar29));
                                        }
                                    } else {
                                        int iIntValue = ((Integer) propertyModel18.j(rVar28)).intValue();
                                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) p03Var.b.getLayoutManager();
                                        linearLayoutManager.g1(iIntValue - ((linearLayoutManager.d() - linearLayoutManager.b()) / 2), 0);
                                    }
                                } else {
                                    p03Var.a.setLeftButtonDrawableId(propertyModel18.h(pVar13));
                                }
                            } else {
                                p03Var.a.setIsIncognito(propertyModel18.k(nVar8));
                            }
                        } else {
                            p03Var.a.setMainContentVisibility(propertyModel18.k(nVar7));
                        }
                    } else {
                        p03Var.a.setRightButtonOnClickListener((View.OnClickListener) propertyModel18.j(rVar27));
                    }
                } else {
                    p03Var.a.setLeftButtonOnClickListener((View.OnClickListener) propertyModel18.j(rVar26));
                }
                break;
            case 24:
                final PropertyModel propertyModel19 = (PropertyModel) kVar;
                ViewGroup viewGroup = (ViewGroup) obj;
                v62 v62Var18 = (v62) obj2;
                t0.a(propertyModel19, viewGroup, v62Var18);
                final int iH8 = propertyModel19.h(u0.a);
                int integer = viewGroup.getResources().getInteger(R$integer.list_item_level_default);
                int integer2 = viewGroup.getResources().getInteger(R$integer.list_item_level_selected);
                final SelectableTabGridView selectableTabGridView = (SelectableTabGridView) viewGroup.findViewById(R$id.content_view);
                if (u0.o != v62Var18) {
                    PropertyModel.r<org.chromium.components.browser_ui.widget.selectable_list.a<Integer>> rVar31 = u0.p;
                    if (rVar31 != v62Var18) {
                        PropertyModel.n nVar9 = u0.k;
                        if (nVar9 == v62Var18) {
                            boolean zK4 = propertyModel19.k(nVar9);
                            ImageView imageView3 = (ImageView) viewGroup.findViewById(R$id.end_button);
                            Drawable background = imageView3.getBackground();
                            if (zK4) {
                                integer = integer2;
                            }
                            background.setLevel(integer);
                            imageView3.getBackground().mutate().setTintList(zK4 ? (ColorStateList) propertyModel19.j(u0.u) : (ColorStateList) propertyModel19.j(u0.t));
                            imageView3.getDrawable().setAlpha(zK4 ? 255 : 0);
                            imageView3.setImageTintList(zK4 ? (ColorStateList) propertyModel19.j(u0.l) : null);
                            if (zK4) {
                                ((p5) imageView3.getDrawable()).start();
                            }
                        }
                    } else {
                        selectableTabGridView.setSelectionDelegate((org.chromium.components.browser_ui.widget.selectable_list.a) propertyModel19.j(rVar31));
                        selectableTabGridView.setItem(Integer.valueOf(iH8));
                    }
                } else {
                    View.OnClickListener onClickListener = new View.OnClickListener() { // from class: h13
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view8) {
                            ((j0.l) propertyModel19.j(u0.o)).a(iH8);
                            selectableTabGridView.m();
                        }
                    };
                    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() { // from class: i13
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
                        @Override // android.view.View.OnLongClickListener
                        public final boolean onLongClick(View view8) {
                            ((j0.l) propertyModel19.j(u0.o)).a(iH8);
                            SelectableTabGridView selectableTabGridView2 = selectableTabGridView;
                            return selectableTabGridView2.onLongClick(selectableTabGridView2);
                        }
                    };
                    selectableTabGridView.setOnClickListener(onClickListener);
                    selectableTabGridView.setOnLongClickListener(onLongClickListener);
                    ImageView imageView4 = (ImageView) selectableTabGridView.findViewById(R$id.end_button);
                    imageView4.setOnClickListener(onClickListener);
                    imageView4.setOnLongClickListener(onLongClickListener);
                }
                break;
            case 25:
                h1.a((PropertyModel) kVar, (ViewGroup) obj, (v62) obj2);
                break;
            case 26:
                PropertyModel propertyModel20 = (PropertyModel) kVar;
                View view8 = (View) obj;
                v62 v62Var19 = (v62) obj2;
                TextView textView6 = (TextView) view8.findViewById(R$id.menu_item_text);
                ImageView imageView5 = (ImageView) view8.findViewById(R$id.menu_item_icon);
                ImageView imageView6 = (ImageView) view8.findViewById(R$id.menu_item_end_icon);
                PropertyModel.r<String> rVar32 = e33.h;
                if (v62Var19 == rVar32) {
                    textView6.setText((CharSequence) propertyModel20.j(rVar32));
                    break;
                } else {
                    PropertyModel.r<Drawable> rVar33 = e33.j;
                    if (v62Var19 == rVar33) {
                        imageView5.setImageDrawable((Drawable) propertyModel20.j(rVar33));
                        textView6.setPaddingRelative(view8.getResources().getDimensionPixelOffset(R$dimen.menu_padding_start), textView6.getPaddingTop(), textView6.getPaddingEnd(), textView6.getPaddingBottom());
                        imageView5.setVisibility(0);
                        imageView6.setVisibility(8);
                        break;
                    } else {
                        PropertyModel.n nVar10 = e33.k;
                        if (v62Var19 == nVar10 || v62Var19 == e33.i) {
                            boolean zK5 = propertyModel20.k(nVar10);
                            view8.setEnabled(zK5);
                            textView6.setEnabled(zK5);
                            imageView5.setEnabled(zK5);
                            imageView6.setEnabled(zK5);
                            if (!zK5) {
                                textView6.setContentDescription(null);
                            } else {
                                textView6.setContentDescription((CharSequence) propertyModel20.j(e33.i));
                            }
                            break;
                        } else {
                            PropertyModel.r<ColorStateList> rVar34 = e33.n;
                            if (v62Var19 == rVar34 && (colorStateList = (ColorStateList) propertyModel20.j(rVar34)) != null) {
                                imageView5.setImageTintList(colorStateList);
                                break;
                            }
                        }
                    }
                }
                break;
            case 27:
                PropertyModel propertyModel21 = (PropertyModel) kVar;
                di diVar = (di) obj;
                v62 v62Var20 = (v62) obj2;
                PropertyModel.p pVar14 = ci.a;
                if (pVar14 == v62Var20) {
                    diVar.a.findViewById(R$id.bottom_controls_wrapper).getLayoutParams().height = propertyModel21.h(pVar14);
                    break;
                } else {
                    PropertyModel.p pVar15 = ci.b;
                    if (pVar15 == v62Var20) {
                        diVar.b.i = propertyModel21.h(pVar15);
                        break;
                    } else {
                        PropertyModel.n nVar11 = ci.c;
                        PropertyModel.n nVar12 = ci.d;
                        if (nVar11 != v62Var20 && nVar12 != v62Var20) {
                            PropertyModel.n nVar13 = ci.e;
                            if (nVar13 == v62Var20) {
                                diVar.a.setImportantForAccessibility(propertyModel21.k(nVar13) ? 4 : 0);
                            }
                            break;
                        } else {
                            boolean zK6 = propertyModel21.k(nVar11);
                            boolean zK7 = propertyModel21.k(nVar12);
                            diVar.a.setVisibility(zK6 ? 0 : 4);
                            diVar.b.j = zK7;
                            if (!zK6 && !zK7) {
                                diVar.a.getResourceAdapter().i.b();
                                break;
                            }
                        }
                    }
                }
                break;
            case 28:
                PropertyModel propertyModel22 = (PropertyModel) kVar;
                View view9 = (View) obj;
                v62 v62Var21 = (v62) obj2;
                PropertyModel.l<WebAuthnCredential> lVar12 = le3.e.b;
                WebAuthnCredential webAuthnCredential = (WebAuthnCredential) propertyModel22.j(lVar12);
                if (v62Var21 != le3.e.d) {
                    PropertyModel.r<le3.b> rVar35 = le3.e.a;
                    if (v62Var21 == rVar35) {
                        ImageView imageView7 = (ImageView) view9.findViewById(R$id.favicon);
                        le3.b bVar = (le3.b) propertyModel22.j(rVar35);
                        imageView7.setImageDrawable(a.e(bVar.b, bVar.a, bVar.c, a.a(view9.getContext()), view9.getResources(), bVar.d));
                    } else if (v62Var21 == lVar12) {
                        ((TextView) view9.findViewById(R$id.username)).setText(webAuthnCredential.getUsername());
                        ((TextView) view9.findViewById(R$id.webauthn_credential_context)).setText(R$string.touch_to_fill_sheet_webauthn_credential_context);
                        view9.setContentDescription(view9.getContext().getString(R$string.touch_to_fill_passkey_credential_accessibility_description, webAuthnCredential.getUsername()));
                    }
                } else {
                    view9.setOnClickListener(new wd2(propertyModel22, i, webAuthnCredential));
                }
                break;
            default:
                PropertyModel propertyModel23 = (PropertyModel) kVar;
                View view10 = (View) obj;
                v62 v62Var22 = (v62) obj2;
                PropertyModel.j jVar2 = he3.a.a;
                if (v62Var22 != jVar2) {
                    PropertyModel.l<String> lVar13 = he3.a.b;
                    if (v62Var22 != lVar13) {
                        PropertyModel.l<String> lVar14 = he3.a.c;
                        if (v62Var22 != lVar14) {
                            PropertyModel.l<String> lVar15 = he3.a.d;
                            if (v62Var22 != lVar15) {
                                PropertyModel.l<String> lVar16 = he3.a.e;
                                if (v62Var22 == lVar16) {
                                    ((TextView) view10.findViewById(R$id.description_line_2)).setText((CharSequence) propertyModel23.j(lVar16));
                                } else if (v62Var22 == he3.a.f) {
                                    view10.setOnClickListener(new ij3(6, propertyModel23));
                                }
                            } else {
                                ((TextView) view10.findViewById(R$id.description_line_2)).setText((CharSequence) propertyModel23.j(lVar15));
                            }
                        } else {
                            ((TextView) view10.findViewById(R$id.card_number)).setText((CharSequence) propertyModel23.j(lVar14));
                        }
                    } else {
                        ((TextView) view10.findViewById(R$id.card_name)).setText((CharSequence) propertyModel23.j(lVar13));
                    }
                } else {
                    ImageView imageView8 = (ImageView) view10.findViewById(R$id.favicon);
                    int iH9 = propertyModel23.h(jVar2);
                    imageView8.setImageDrawable(iH9 != 0 ? AppCompatResources.a(view10.getContext(), iH9) : null);
                }
                break;
        }
    }
}
