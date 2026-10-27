package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.he3;
import defpackage.pv0;
import defpackage.wm3;
import java.util.Iterator;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.android.webid.data.IdentityProviderMetadata;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.chrome.features.tasks.TasksView;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;
import org.chromium.components.webapps.WebappsIconUtils;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ee3 implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ ee3(int i) {
        this.a = i;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        View viewFindViewById;
        j.a agVar;
        IdentityProviderMetadata identityProviderMetadata;
        Integer num;
        int i = 2;
        switch (this.a) {
            case 0:
                PropertyModel propertyModel = (PropertyModel) kVar;
                View view = (View) obj;
                if (((v62) obj2) == he3.a.f) {
                    view.setOnClickListener(new cy0(4, propertyModel));
                    ((TextView) view.findViewById(R$id.touch_to_fill_button_title)).setText(R$string.autofill_credit_card_continue_button);
                }
                break;
            case 1:
                View view2 = (View) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel propertyModel2 = (PropertyModel) ((PropertyModel) kVar).j((PropertyModel.r) v62Var);
                if (v62Var == w1.c) {
                    viewFindViewById = view2.findViewById(R$id.header_view_item);
                    agVar = new cg(19);
                } else if (v62Var == w1.a) {
                    viewFindViewById = view2.findViewById(R$id.account_selection_continue_btn);
                    agVar = new ee3(i);
                } else if (v62Var == w1.b) {
                    viewFindViewById = view2.findViewById(R$id.user_data_sharing_consent);
                    agVar = new ag(16);
                }
                if (propertyModel2 != null) {
                    viewFindViewById.setVisibility(0);
                    Iterator it = propertyModel2.b().iterator();
                    while (it.hasNext()) {
                        agVar.b(propertyModel2, viewFindViewById, (v62) it.next());
                    }
                } else {
                    viewFindViewById.setVisibility(8);
                }
                break;
            case 2:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                View view3 = (View) obj;
                v62 v62Var2 = (v62) obj2;
                Context context = view3.getContext();
                ButtonCompat buttonCompat = (ButtonCompat) view3.findViewById(R$id.account_selection_continue_btn);
                PropertyModel.l<IdentityProviderMetadata> lVar = t1.b;
                if (v62Var2 != lVar) {
                    PropertyModel.l<Account> lVar2 = t1.a;
                    if (v62Var2 == lVar2) {
                        Account account = (Account) propertyModel3.j(lVar2);
                        String str = account.d;
                        if (str == null || str.isEmpty()) {
                            str = account.c;
                        }
                        buttonCompat.setText(String.format(context.getString(R$string.account_selection_continue), str));
                    } else if (v62Var2 == t1.c) {
                        buttonCompat.setOnClickListener(new ij3(7, propertyModel3));
                    }
                    break;
                } else if (!z7.I(context) && (num = (identityProviderMetadata = (IdentityProviderMetadata) propertyModel3.j(lVar)).b) != null) {
                    buttonCompat.setButtonColor(ColorStateList.valueOf(num.intValue()));
                    Integer numValueOf = identityProviderMetadata.a;
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(oj2.n(z7.h0(num.intValue()) ? R$attr.colorOnPrimary : R$attr.colorOnSurface, context, "AccountSelectionView"));
                    }
                    buttonCompat.setTextColor(numValueOf.intValue());
                    break;
                }
                break;
            case 3:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                View view4 = (View) obj;
                v62 v62Var3 = (v62) obj2;
                int[] iArr = a.a;
                r7.a(propertyModel4, view4, v62Var3);
                PropertyModel.p pVar = j7.a;
                if (v62Var3 != pVar) {
                    PropertyModel.r<CharSequence> rVar = j7.b;
                    if (v62Var3 != rVar) {
                        PropertyModel.r<CharSequence> rVar2 = j7.c;
                        if (v62Var3 != rVar2) {
                            PropertyModel.n nVar = j7.d;
                            if (v62Var3 != nVar) {
                                PropertyModel.n nVar2 = j7.e;
                                if (v62Var3 != nVar2) {
                                    PropertyModel.r<Drawable> rVar3 = j7.h;
                                    if (v62Var3 == rVar3) {
                                        Drawable drawable = (Drawable) propertyModel4.j(rVar3);
                                        ChromeImageView chromeImageView = (ChromeImageView) view4.findViewById(R$id.menu_item_icon);
                                        chromeImageView.setImageDrawable(drawable);
                                        chromeImageView.setVisibility(drawable != null ? 0 : 8);
                                        int iH = propertyModel4.h(j7.i);
                                        if (iH == 0) {
                                            iH = R$color.default_icon_color_secondary_tint_list;
                                        }
                                        chromeImageView.setImageTintList(bz.b(chromeImageView.getContext(), iH));
                                    } else if (v62Var3 == j7.l) {
                                        view4.setOnClickListener(new ij3(8, propertyModel4));
                                    }
                                } else if (!propertyModel4.k(nVar2)) {
                                    wm3.a(view4);
                                } else {
                                    wm3.b(view4, new wm3.a(2));
                                }
                            } else {
                                view4.setEnabled(propertyModel4.k(nVar));
                            }
                        } else {
                            View viewFindViewById2 = view4.findViewById(R$id.menu_item_text);
                            CharSequence charSequence = (CharSequence) propertyModel4.j(rVar2);
                            if (!TextUtils.isEmpty(charSequence)) {
                                viewFindViewById2.setContentDescription(charSequence);
                            } else {
                                viewFindViewById2.setContentDescription(null);
                            }
                        }
                    } else {
                        ((TextView) view4.findViewById(R$id.menu_item_text)).setText((CharSequence) propertyModel4.j(rVar));
                    }
                } else {
                    view4.setId(propertyModel4.h(pVar));
                }
                break;
            case 4:
                a.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 5:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                View view5 = (View) obj;
                v62 v62Var4 = (v62) obj2;
                PropertyModel.j jVar = zo0.a;
                if (v62Var4 == jVar) {
                    TextView textView = (TextView) view5.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_add_new_item_label);
                    textView.setText(propertyModel5.h(jVar));
                    textView.setContentDescription(view5.getContext().getResources().getString(propertyModel5.h(jVar)) + ", " + view5.getContext().getResources().getString(org.chromium.chrome.browser.ui.fast_checkout.R$string.fast_checkout_detail_screen_non_selected_description));
                } else if (v62Var4 == zo0.b) {
                    view5.setOnClickListener(new cy0(5, propertyModel5));
                }
                break;
            case 6:
                PropertyModel propertyModel6 = (PropertyModel) kVar;
                pv0.a aVar = (pv0.a) obj;
                v62 v62Var5 = (v62) obj2;
                PropertyModel.r<ov0> rVar4 = fk0.g;
                if (v62Var5 == rVar4) {
                    ov0 ov0Var = (ov0) propertyModel6.j(rVar4);
                    aVar.i.setOnClickListener(new gs(13, ov0Var));
                    aVar.j.setOnClickListener(new ie0(24, ov0Var));
                    aVar.k.setOnClickListener(new rf(aVar, 8, ov0Var));
                } else {
                    PropertyModel.r<FastCheckoutAutofillProfile> rVar5 = fk0.c;
                    if (v62Var5 == rVar5) {
                        FastCheckoutAutofillProfile fastCheckoutAutofillProfile = (FastCheckoutAutofillProfile) propertyModel6.j(rVar5);
                        aVar.b.setText(fastCheckoutAutofillProfile.getFullName());
                        StringBuilder sb = new StringBuilder();
                        sb.append(fastCheckoutAutofillProfile.getStreetAddress());
                        if (!fastCheckoutAutofillProfile.getStreetAddress().isEmpty() && !fastCheckoutAutofillProfile.getPostalCode().isEmpty()) {
                            sb.append(", ");
                        }
                        sb.append(fastCheckoutAutofillProfile.getPostalCode());
                        String string = sb.toString();
                        TextView textView2 = aVar.c;
                        textView2.setText(string);
                        String emailAddress = fastCheckoutAutofillProfile.getEmailAddress();
                        TextView textView3 = aVar.e;
                        textView3.setText(emailAddress);
                        String phoneNumber = fastCheckoutAutofillProfile.getPhoneNumber();
                        TextView textView4 = aVar.f;
                        textView4.setText(phoneNumber);
                        pv0.a(aVar.b);
                        pv0.a(textView2);
                        pv0.a(textView3);
                        pv0.a(textView4);
                        aVar.d.setVisibility((fastCheckoutAutofillProfile.getEmailAddress().isEmpty() && fastCheckoutAutofillProfile.getPhoneNumber().isEmpty()) ? 8 : 0);
                    } else {
                        PropertyModel.r<FastCheckoutCreditCard> rVar6 = fk0.e;
                        if (v62Var5 == rVar6) {
                            FastCheckoutCreditCard fastCheckoutCreditCard = (FastCheckoutCreditCard) propertyModel6.j(rVar6);
                            TextView textView5 = aVar.g;
                            ImageView imageView = aVar.h;
                            textView5.setText(fastCheckoutCreditCard.g);
                            try {
                                imageView.setImageDrawable(AppCompatResources.a(aVar.a, fastCheckoutCreditCard.b()));
                            } catch (Resources.NotFoundException unused) {
                                imageView.setImageDrawable(null);
                                return;
                            }
                        }
                    }
                }
                break;
            case 7:
                PropertyModel propertyModel7 = (PropertyModel) kVar;
                tl3 tl3Var = (tl3) obj;
                v62 v62Var6 = (v62) obj2;
                PropertyModel.n nVar3 = sl3.a;
                if (v62Var6 != nVar3) {
                    PropertyModel.r<String> rVar7 = sl3.b;
                    if (v62Var6 != rVar7) {
                        PropertyModel.r<String> rVar8 = sl3.c;
                        if (v62Var6 != rVar8) {
                            PropertyModel.n nVar4 = sl3.d;
                            if (v62Var6 != nVar4) {
                                PropertyModel.r<Runnable> rVar9 = sl3.f;
                                if (v62Var6 != rVar9) {
                                    PropertyModel.r<Runnable> rVar10 = sl3.g;
                                    if (v62Var6 != rVar10) {
                                        PropertyModel.r<AsyncImageView.a> rVar11 = sl3.e;
                                        if (v62Var6 == rVar11) {
                                            ((AsyncImageView) tl3Var.b.findViewById(R$id.thumbnail)).g((AsyncImageView.a) propertyModel7.j(rVar11), null);
                                        }
                                    } else {
                                        tl3Var.b.findViewById(R$id.close_button).setOnClickListener(new rf(tl3Var, 11, (Runnable) propertyModel7.j(rVar10)));
                                    }
                                } else {
                                    tl3Var.b.setOnClickListener(new yy0(3, (Runnable) propertyModel7.j(rVar9)));
                                }
                            } else {
                                ((TextView) tl3Var.b.findViewById(R$id.video_length)).setVisibility(propertyModel7.k(nVar4) ? 0 : 8);
                            }
                        } else {
                            ((TextView) tl3Var.b.findViewById(R$id.video_length)).setText((String) propertyModel7.j(rVar8));
                        }
                    } else {
                        ((TextView) tl3Var.b.findViewById(R$id.title)).setText((String) propertyModel7.j(rVar7));
                    }
                } else {
                    boolean zK = propertyModel7.k(nVar3);
                    if (zK && tl3Var.b == null) {
                        tl3Var.b = tl3Var.a.inflate();
                    }
                    View view6 = tl3Var.b;
                    if (view6 != null) {
                        view6.setVisibility(zK ? 0 : 8);
                    }
                }
                break;
            case 8:
                PropertyModel propertyModel8 = (PropertyModel) kVar;
                v62 v62Var7 = (v62) obj2;
                RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) ((View) obj).findViewById(R$id.language_radio_button);
                PropertyModel.r<String> rVar12 = h51.b;
                if (v62Var7 != rVar12) {
                    PropertyModel.r<String> rVar13 = h51.c;
                    if (v62Var7 != rVar13) {
                        PropertyModel.n nVar5 = h51.d;
                        if (v62Var7 == nVar5) {
                            radioButtonWithDescription.setChecked(propertyModel8.k(nVar5));
                        } else if (v62Var7 == h51.e) {
                            radioButtonWithDescription.setOnCheckedChangeListener(new z70(radioButtonWithDescription, 10, propertyModel8));
                        }
                    } else {
                        radioButtonWithDescription.setDescriptionText((CharSequence) propertyModel8.j(rVar13));
                    }
                } else {
                    radioButtonWithDescription.setPrimaryText((CharSequence) propertyModel8.j(rVar12));
                }
                break;
            case 9:
                p73.a((PropertyModel) kVar, (TasksView) obj, (v62) obj2);
                break;
            case 10:
                PropertyModel propertyModel9 = (PropertyModel) kVar;
                View view7 = (View) obj;
                v62 v62Var8 = (v62) obj2;
                PropertyModel.p pVar2 = gv1.h;
                if (pVar2 != v62Var8) {
                    PropertyModel.p pVar3 = gv1.g;
                    if (pVar3 == v62Var8) {
                        ((SeekBar) view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_slider)).setMax(propertyModel9.h(pVar3));
                    } else if (gv1.a == v62Var8) {
                        view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_decrease_zoom_button).setOnClickListener(new kg(9, propertyModel9));
                    } else if (gv1.b != v62Var8) {
                        PropertyModel.n nVar6 = gv1.e;
                        if (nVar6 != v62Var8) {
                            PropertyModel.n nVar7 = gv1.f;
                            if (nVar7 == v62Var8) {
                                view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_increase_zoom_button).setEnabled(propertyModel9.k(nVar7));
                            } else if (gv1.c == v62Var8) {
                                ((SeekBar) view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_slider)).setOnSeekBarChangeListener(new jv1(propertyModel9));
                            }
                        } else {
                            view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_decrease_zoom_button).setEnabled(propertyModel9.k(nVar6));
                        }
                    } else {
                        view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_increase_zoom_button).setOnClickListener(new rg1(4, propertyModel9));
                    }
                } else {
                    ((SeekBar) view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_slider)).setProgress(propertyModel9.h(pVar2));
                    TextView textView6 = (TextView) view7.findViewById(org.chromium.components.browser_ui.accessibility.R$id.page_zoom_current_zoom_level);
                    long jRound = Math.round(iv1.b(propertyModel9.h(pVar2)) * 100.0d);
                    textView6.setText(view7.getContext().getResources().getString(org.chromium.components.browser_ui.accessibility.R$string.page_zoom_level, Long.valueOf(jRound)));
                    textView6.setContentDescription(view7.getContext().getResources().getString(org.chromium.components.browser_ui.accessibility.R$string.page_zoom_level_label, Long.valueOf(jRound)));
                }
                break;
            default:
                PropertyModel propertyModel10 = (PropertyModel) kVar;
                org.chromium.components.webapps.bottomsheet.a aVar2 = (org.chromium.components.webapps.bottomsheet.a) obj;
                v62 v62Var9 = (v62) obj2;
                PropertyModel.r<String> rVar14 = w3.a;
                if (!v62Var9.equals(rVar14)) {
                    PropertyModel.r<String> rVar15 = w3.b;
                    if (!v62Var9.equals(rVar15)) {
                        PropertyModel.r<String> rVar16 = w3.c;
                        if (!v62Var9.equals(rVar16)) {
                            PropertyModel.r<Pair<Bitmap, Boolean>> rVar17 = w3.d;
                            if (!v62Var9.equals(rVar17)) {
                                PropertyModel.n nVar8 = w3.f;
                                if (!v62Var9.equals(nVar8)) {
                                    PropertyModel.r<View.OnClickListener> rVar18 = w3.g;
                                    if (v62Var9.equals(rVar18)) {
                                        aVar2.setOnClickListener((View.OnClickListener) propertyModel10.j(rVar18));
                                    }
                                } else {
                                    aVar2.b.findViewById(org.chromium.components.webapps.R$id.button_install).setEnabled(propertyModel10.k(nVar8));
                                }
                            } else {
                                Pair pair = (Pair) propertyModel10.j(rVar17);
                                Bitmap bitmap = (Bitmap) pair.first;
                                boolean zBooleanValue = ((Boolean) pair.second).booleanValue();
                                ImageView imageView2 = (ImageView) aVar2.b.findViewById(org.chromium.components.webapps.R$id.app_icon);
                                if (zBooleanValue) {
                                    imageView2.setImageBitmap(WebappsIconUtils.generateAdaptiveIconBitmap(bitmap));
                                } else {
                                    imageView2.setImageBitmap(bitmap);
                                }
                                imageView2.setVisibility(0);
                            }
                        } else {
                            String str2 = (String) propertyModel10.j(rVar16);
                            TextView textView7 = (TextView) aVar2.c.findViewById(org.chromium.components.webapps.R$id.description);
                            textView7.setText(str2);
                            textView7.setVisibility(str2.isEmpty() ? 8 : 0);
                        }
                    } else {
                        ((TextView) aVar2.b.findViewById(org.chromium.components.webapps.R$id.app_origin)).setText((String) propertyModel10.j(rVar15));
                    }
                } else {
                    ((TextView) aVar2.b.findViewById(org.chromium.components.webapps.R$id.app_name)).setText((String) propertyModel10.j(rVar14));
                }
                break;
        }
    }
}
