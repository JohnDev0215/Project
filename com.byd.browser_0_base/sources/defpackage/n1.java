package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.s1;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.chrome.browser.ui.fast_checkout.R$string;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutAutofillProfile;
import org.chromium.chrome.browser.ui.fast_checkout.data.FastCheckoutCreditCard;
import org.chromium.components.webapps.R$drawable;
import org.chromium.components.webapps.c;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class n1 implements j.a {
    public final /* synthetic */ int a;

    public /* synthetic */ n1(int i) {
        this.a = i;
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        int i = 1;
        int i2 = 2;
        switch (this.a) {
            case 0:
                PropertyModel propertyModel = (PropertyModel) kVar;
                View view = (View) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.l<Account> lVar = s1.b;
                Account account = (Account) propertyModel.j(lVar);
                PropertyModel.r<s1.a> rVar = s1.a;
                if (v62Var != rVar) {
                    PropertyModel.l<Callback<Account>> lVar2 = s1.c;
                    if (v62Var == lVar2) {
                        Callback callback = (Callback) propertyModel.j(lVar2);
                        if (callback != null) {
                            view.setOnClickListener(new n40(callback, 9, account));
                        } else {
                            view.setOnClickListener(null);
                        }
                    } else if (v62Var == lVar) {
                        ((TextView) view.findViewById(R$id.title)).setText(account.c);
                        ((TextView) view.findViewById(R$id.description)).setText(account.b);
                    }
                } else {
                    s1.a aVar = (s1.a) propertyModel.j(rVar);
                    int i3 = aVar.c;
                    Resources resources = view.getContext().getResources();
                    Bitmap bitmapA = aVar.b;
                    if (bitmapA == null) {
                        bitmapA = new uc2(resources, i3, i3, i3 / 2, -7829368, view.getResources().getDimensionPixelSize(R$dimen.account_selection_account_avatar_monogram_text_size)).a(aVar.a);
                    }
                    ((ImageView) view.findViewById(R$id.start_icon)).setImageDrawable(ob.a(resources, bitmapA, i3));
                }
                break;
            case 1:
                a.a((PropertyModel) kVar, (View) obj, (v62) obj2);
                break;
            case 2:
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                View view2 = (View) obj;
                v62 v62Var2 = (v62) obj2;
                PropertyModel.l<FastCheckoutCreditCard> lVar3 = v10.a;
                PropertyModel.n nVar = v10.b;
                if (v62Var2 == lVar3) {
                    FastCheckoutCreditCard fastCheckoutCreditCard = (FastCheckoutCreditCard) propertyModel2.j(lVar3);
                    TextView textView = (TextView) view2.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_credit_card_item_number);
                    textView.setText(fastCheckoutCreditCard.g);
                    TextView textView2 = (TextView) view2.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_credit_card_item_name);
                    textView2.setText(fastCheckoutCreditCard.getName());
                    TextView textView3 = (TextView) view2.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_credit_card_item_expiration_date);
                    textView3.setText(fastCheckoutCreditCard.a(view2.getContext()));
                    ImageView imageView = (ImageView) view2.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_credit_card_icon);
                    textView.setVisibility(textView.length() == 0 ? 8 : 0);
                    textView2.setVisibility(textView2.length() == 0 ? 8 : 0);
                    textView3.setVisibility(textView3.length() == 0 ? 8 : 0);
                    try {
                        imageView.setImageDrawable(AppCompatResources.a(imageView.getContext(), fastCheckoutCreditCard.b()));
                    } catch (Resources.NotFoundException unused) {
                        imageView.setImageDrawable(null);
                    }
                    break;
                } else if (v62Var2 == v10.c) {
                    view2.setOnClickListener(new rg1(i, propertyModel2));
                } else if (v62Var2 == nVar) {
                    view2.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_credit_card_item_selected_icon).setVisibility(propertyModel2.k(nVar) ? 0 : 8);
                }
                FastCheckoutCreditCard fastCheckoutCreditCard2 = (FastCheckoutCreditCard) propertyModel2.j(lVar3);
                boolean zK = propertyModel2.k(nVar);
                StringBuilder sb = new StringBuilder();
                String str = fastCheckoutCreditCard2.g;
                sb.append(!str.isEmpty() ? str.concat(",") : "");
                String name = fastCheckoutCreditCard2.getName();
                sb.append(!name.isEmpty() ? name.concat(",") : "");
                String strA = fastCheckoutCreditCard2.a(view2.getContext());
                if (!strA.isEmpty()) {
                    sb.append(view2.getContext().getResources().getString(R$string.fast_checkout_credit_card_item_expire_description));
                    String strConcat = " ".concat(strA);
                    sb.append(strConcat.isEmpty() ? "" : strConcat.concat(","));
                }
                sb.append(view2.getContext().getResources().getString(zK ? R$string.fast_checkout_detail_screen_selected_description : R$string.fast_checkout_detail_screen_non_selected_description));
                view2.setContentDescription(sb.toString());
                break;
            case 3:
                PropertyModel propertyModel3 = (PropertyModel) kVar;
                View view3 = (View) obj;
                v62 v62Var3 = (v62) obj2;
                PropertyModel.l<FastCheckoutAutofillProfile> lVar4 = gb.a;
                PropertyModel.n nVar2 = gb.b;
                if (v62Var3 == lVar4) {
                    FastCheckoutAutofillProfile fastCheckoutAutofillProfile = (FastCheckoutAutofillProfile) propertyModel3.j(lVar4);
                    TextView textView4 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_name);
                    textView4.setText(fastCheckoutAutofillProfile.getFullName());
                    TextView textView5 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_street_address);
                    textView5.setText(fastCheckoutAutofillProfile.getStreetAddress());
                    TextView textView6 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_city_and_postal_code);
                    textView6.setText(hb.b(fastCheckoutAutofillProfile));
                    TextView textView7 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_country);
                    textView7.setText(fastCheckoutAutofillProfile.m);
                    TextView textView8 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_email);
                    textView8.setText(fastCheckoutAutofillProfile.getEmailAddress());
                    TextView textView9 = (TextView) view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_phone_number);
                    textView9.setText(fastCheckoutAutofillProfile.getPhoneNumber());
                    hb.c(textView4);
                    hb.c(textView5);
                    hb.c(textView6);
                    hb.c(textView7);
                    hb.c(textView8);
                    hb.c(textView9);
                    View viewFindViewById = view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_sub_section);
                    if (fastCheckoutAutofillProfile.getEmailAddress().isEmpty() && fastCheckoutAutofillProfile.getPhoneNumber().isEmpty()) {
                        i = 8;
                    }
                    viewFindViewById.setVisibility(i);
                } else if (v62Var3 == gb.c) {
                    view3.setOnClickListener(new kg(6, propertyModel3));
                } else if (v62Var3 == nVar2) {
                    view3.findViewById(org.chromium.chrome.browser.ui.fast_checkout.R$id.fast_checkout_autofill_profile_item_selected_icon).setVisibility(propertyModel3.k(nVar2) ? 0 : 8);
                }
                FastCheckoutAutofillProfile fastCheckoutAutofillProfile2 = (FastCheckoutAutofillProfile) propertyModel3.j(lVar4);
                boolean zK2 = propertyModel3.k(nVar2);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(hb.a(fastCheckoutAutofillProfile2.getFullName()));
                sb2.append(hb.a(fastCheckoutAutofillProfile2.getStreetAddress()));
                sb2.append(hb.a(hb.b(fastCheckoutAutofillProfile2)));
                sb2.append(hb.a(fastCheckoutAutofillProfile2.m));
                sb2.append(hb.a(fastCheckoutAutofillProfile2.getEmailAddress()));
                sb2.append(hb.a(fastCheckoutAutofillProfile2.getPhoneNumber()));
                sb2.append(view3.getContext().getResources().getString(zK2 ? R$string.fast_checkout_detail_screen_selected_description : R$string.fast_checkout_detail_screen_non_selected_description));
                view3.setContentDescription(sb2.toString());
                break;
            case 4:
                PropertyModel propertyModel4 = (PropertyModel) kVar;
                View view4 = (View) obj;
                v62 v62Var4 = (v62) obj2;
                if (v62Var4 == n51.b) {
                    view4.findViewById(R$id.watch).setOnClickListener(new kg(7, propertyModel4));
                } else if (v62Var4 != n51.a) {
                    PropertyModel.n nVar3 = n51.c;
                    if (v62Var4 == nVar3) {
                        view4.findViewById(R$id.watch).setEnabled(propertyModel4.k(nVar3));
                    }
                } else {
                    view4.findViewById(R$id.close_button).setOnClickListener(new rg1(i2, propertyModel4));
                }
                break;
            case 5:
                PropertyModel propertyModel5 = (PropertyModel) kVar;
                View view5 = (View) obj;
                v62 v62Var5 = (v62) obj2;
                TextView textView10 = (TextView) view5.findViewById(org.chromium.components.browser_ui.widget.R$id.menu_item_text);
                ImageView imageView2 = (ImageView) view5.findViewById(org.chromium.components.browser_ui.widget.R$id.menu_item_icon);
                ImageView imageView3 = (ImageView) view5.findViewById(org.chromium.components.browser_ui.widget.R$id.menu_item_end_icon);
                PropertyModel.p pVar = o81.a;
                if (v62Var5 == pVar) {
                    textView10.setText(propertyModel5.h(pVar));
                    break;
                } else {
                    PropertyModel.r<String> rVar2 = o81.b;
                    if (v62Var5 == rVar2) {
                        textView10.setText((CharSequence) propertyModel5.j(rVar2));
                        break;
                    } else {
                        PropertyModel.r<String> rVar3 = o81.c;
                        if (v62Var5 == rVar3) {
                            textView10.setContentDescription((CharSequence) propertyModel5.j(rVar3));
                            break;
                        } else {
                            PropertyModel.p pVar2 = o81.d;
                            if (v62Var5 == pVar2 || v62Var5 == o81.e) {
                                int iH = propertyModel5.h((PropertyModel.j) v62Var5);
                                Drawable drawableA = iH != 0 ? AppCompatResources.a(view5.getContext(), iH) : null;
                                if (drawableA != null) {
                                    if (v62Var5 != pVar2) {
                                        imageView3.setImageDrawable(drawableA);
                                        imageView2.setVisibility(8);
                                        imageView3.setVisibility(0);
                                    } else {
                                        imageView2.setImageDrawable(drawableA);
                                        textView10.setPaddingRelative(view5.getResources().getDimensionPixelOffset(org.chromium.components.browser_ui.widget.R$dimen.menu_padding_start), textView10.getPaddingTop(), textView10.getPaddingEnd(), textView10.getPaddingBottom());
                                        imageView2.setVisibility(0);
                                        imageView3.setVisibility(8);
                                    }
                                }
                                break;
                            } else if (v62Var5 != o81.f) {
                                PropertyModel.n nVar4 = o81.g;
                                if (v62Var5 != nVar4) {
                                    PropertyModel.p pVar3 = o81.h;
                                    if (v62Var5 != pVar3) {
                                        PropertyModel.j jVar = o81.i;
                                        if (v62Var5 != jVar) {
                                            PropertyModel.h hVar = o81.j;
                                            if (v62Var5 == hVar) {
                                                if (!propertyModel5.k(hVar)) {
                                                    textView10.setEllipsize(null);
                                                } else {
                                                    textView10.setMaxLines(1);
                                                    textView10.setEllipsize(TextUtils.TruncateAt.END);
                                                }
                                            }
                                        } else {
                                            c6.f(textView10, propertyModel5.h(jVar));
                                        }
                                    } else {
                                        imageView2.setImageTintList(bz.b(view5.getContext(), propertyModel5.h(pVar3)));
                                        imageView3.setImageTintList(bz.b(view5.getContext(), propertyModel5.h(pVar3)));
                                    }
                                } else {
                                    textView10.setEnabled(propertyModel5.k(nVar4));
                                    imageView2.setEnabled(propertyModel5.k(nVar4));
                                    imageView3.setEnabled(propertyModel5.k(nVar4));
                                }
                                break;
                            }
                        }
                    }
                }
                break;
            default:
                PropertyModel propertyModel6 = (PropertyModel) kVar;
                c cVar = (c) obj;
                v62 v62Var6 = (v62) obj2;
                PropertyModel.r<String> rVar4 = w3.a;
                if (!v62Var6.equals(rVar4)) {
                    PropertyModel.r<String> rVar5 = w3.b;
                    if (!v62Var6.equals(rVar5)) {
                        PropertyModel.r<Pair<Bitmap, Boolean>> rVar6 = w3.d;
                        if (!v62Var6.equals(rVar6)) {
                            PropertyModel.p pVar4 = w3.e;
                            if (!v62Var6.equals(pVar4)) {
                                PropertyModel.n nVar5 = w3.f;
                                if (!v62Var6.equals(nVar5)) {
                                    PropertyModel.r<String> rVar7 = w3.h;
                                    if (!v62Var6.equals(rVar7)) {
                                        PropertyModel.o oVar = w3.i;
                                        if (v62Var6.equals(oVar)) {
                                            cVar.l.setRating(propertyModel6.g(oVar));
                                            cVar.m.setImageResource(R$drawable.google_play);
                                        }
                                    } else {
                                        String str2 = (String) propertyModel6.j(rVar7);
                                        PropertyModel propertyModel7 = cVar.e;
                                        propertyModel7.p(di1.j, str2);
                                        propertyModel7.p(di1.k, xz.a.getString(org.chromium.components.webapps.R$string.app_banner_view_native_app_install_accessibility, str2));
                                    }
                                } else {
                                    cVar.p = propertyModel6.k(nVar5);
                                    cVar.a();
                                }
                            } else {
                                int iH2 = propertyModel6.h(pVar4);
                                cVar.getClass();
                                cVar.h.setVisibility(iH2 == 2 ? 0 : 8);
                                cVar.i.setVisibility(iH2 != 2 ? 0 : 8);
                                cVar.k.setVisibility(iH2 == 1 ? 0 : 8);
                                cVar.l.setVisibility(iH2 == 0 ? 0 : 8);
                                cVar.m.setVisibility(iH2 != 0 ? 8 : 0);
                            }
                        } else {
                            Pair pair = (Pair) propertyModel6.j(rVar6);
                            Bitmap bitmap = (Bitmap) pair.first;
                            boolean zBooleanValue = ((Boolean) pair.second).booleanValue();
                            ImageView imageView4 = cVar.o;
                            if (zBooleanValue) {
                                imageView4.setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
                            } else {
                                imageView4.setImageBitmap(bitmap);
                            }
                            cVar.n.setVisibility(8);
                            imageView4.setVisibility(0);
                        }
                    } else {
                        cVar.k.setText((String) propertyModel6.j(rVar5));
                    }
                } else {
                    String str3 = (String) propertyModel6.j(rVar4);
                    cVar.j.setText(str3);
                    cVar.h.setText(str3);
                    cVar.o.setContentDescription(str3);
                }
                break;
        }
    }
}
