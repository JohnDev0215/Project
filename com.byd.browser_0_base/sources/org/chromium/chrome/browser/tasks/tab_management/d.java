package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ie0;
import defpackage.n3;
import defpackage.nr;
import defpackage.nw2;
import defpackage.sy0;
import defpackage.t21;
import defpackage.v33;
import defpackage.zj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.a;
import org.chromium.chrome.browser.tasks.tab_management.b;
import org.chromium.chrome.browser.tasks.tab_management.g;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.m1;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MessageCardProviderMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements g.b {
    public final Context a;
    public final nw2<Boolean> b;
    public final LinkedHashMap c = new LinkedHashMap();
    public final LinkedHashMap d = new LinkedHashMap();
    public final MessageCardView.a e;

    /* JADX INFO: compiled from: MessageCardProviderMediator.java */
    public class a {
        public final int a;
        public final PropertyModel b;

        public a(int i, PropertyModel propertyModel) {
            this.a = i;
            this.b = propertyModel;
        }
    }

    public d(Context context, nr nrVar, sy0 sy0Var) {
        this.a = context;
        this.b = nrVar;
        this.e = sy0Var;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.g.b
    public final void a(int i) {
        LinkedHashMap linkedHashMap = this.c;
        if (linkedHashMap.containsKey(Integer.valueOf(i))) {
            linkedHashMap.remove(Integer.valueOf(i));
        }
        if (this.d.containsKey(Integer.valueOf(i))) {
            c(i);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.g.b
    public final void b(int i, g.a aVar) {
        PropertyModel propertyModelA;
        int i2 = 1;
        final Context context = this.a;
        if (i == 1) {
            MessageCardView.a aVar2 = new MessageCardView.a() { // from class: qg1
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
                public final void a(int i3) {
                    this.a.c(i3);
                }
            };
            m1.a aVar3 = (m1.a) aVar;
            v33 v33Var = aVar3.a;
            int i3 = v33Var.b;
            String string = i3 != 0 ? i3 != 1 ? "" : context.getString(R$string.tab_suggestion_close_stale_message) : context.getString(R$string.tab_suggestion_group_tabs_message);
            String str = String.format(Locale.getDefault(), "%d", Integer.valueOf(v33Var.a.size()));
            String string2 = context.getString(R$string.tab_suggestion_review_button);
            String string3 = context.getString(R$string.accessibility_tab_suggestion_dismiss_button);
            PropertyModel.b bVar = new PropertyModel.b(f.w);
            bVar.c(f.a, 1);
            bVar.c(f.b, v33Var.b);
            bVar.e(f.g, new MessageCardView.b() { // from class: r21
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.b
                public final Drawable a() {
                    return null;
                }
            });
            bVar.e(f.i, aVar2);
            bVar.e(f.l, new l1(aVar3));
            bVar.e(f.k, new k1(aVar3));
            bVar.e(f.f, string);
            bVar.e(f.e, str);
            bVar.e(f.c, string2);
            bVar.e(f.m, string3);
            bVar.b(f.p, true);
            bVar.b(f.s, false);
            bVar.c(f.u, 0);
            bVar.c(n0.a.a, 1);
            bVar.f(n0.a.b, 1.0f);
            propertyModelA = bVar.a();
        } else if (i == 2) {
            t21 t21Var = new t21(i2, this);
            b.a aVar4 = (b.a) aVar;
            String string4 = context.getString(R$string.iph_drag_and_drop_introduction);
            String string5 = context.getString(R$string.iph_drag_and_drop_show_me);
            String string6 = context.getString(R$string.accessibility_tab_suggestion_dismiss_button);
            PropertyModel.b bVar2 = new PropertyModel.b(f.w);
            bVar2.c(f.a, 2);
            bVar2.c(f.b, -1);
            bVar2.e(f.g, new MessageCardView.b() { // from class: r21
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.b
                public final Drawable a() {
                    return null;
                }
            });
            bVar2.e(f.i, t21Var);
            bVar2.e(f.l, aVar4.b);
            bVar2.e(f.k, aVar4.a);
            bVar2.e(f.e, string4);
            bVar2.e(f.f, null);
            bVar2.e(f.c, string5);
            bVar2.e(f.m, string6);
            bVar2.b(f.n, true);
            bVar2.b(f.p, false);
            bVar2.b(f.s, false);
            bVar2.c(f.u, 2);
            bVar2.c(n0.a.a, 1);
            bVar2.f(n0.a.b, 1.0f);
            propertyModelA = bVar2.a();
        } else if (i == 3) {
            MessageCardView.a aVar5 = new MessageCardView.a() { // from class: qg1
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.a
                public final void a(int i32) {
                    this.a.c(i32);
                }
            };
            final i.a aVar6 = (i.a) aVar;
            int i4 = aVar6.a;
            boolean z = i4 != 0;
            String string7 = i4 == 0 ? context.getString(R$string.price_drop_spotted_title) : i4 == 1 ? context.getString(R$string.price_drop_alerts_card_title) : null;
            String string8 = i4 == 0 ? context.getString(R$string.price_drop_spotted_content) : i4 == 1 ? n3.B().a() ? context.getString(R$string.price_drop_alerts_card_get_notified_content) : context.getString(R$string.price_drop_alerts_card_go_to_settings_content) : null;
            String string9 = i4 == 0 ? context.getString(R$string.price_drop_spotted_show_me) : i4 == 1 ? n3.B().a() ? context.getString(R$string.price_drop_alerts_card_get_notified) : context.getString(R$string.price_drop_alerts_card_go_to_settings) : null;
            String string10 = context.getString(R$string.accessibility_tab_suggestion_dismiss_button);
            PropertyModel.b bVar3 = new PropertyModel.b(f.w);
            bVar3.c(f.a, 3);
            bVar3.c(f.b, i4);
            bVar3.e(f.i, aVar5);
            bVar3.e(f.l, aVar6.d);
            bVar3.e(f.k, aVar6.c);
            bVar3.e(f.e, string8);
            bVar3.e(f.f, null);
            bVar3.e(f.c, string9);
            bVar3.e(f.m, string10);
            bVar3.b(f.n, false);
            bVar3.b(f.p, z);
            bVar3.b(f.s, false);
            bVar3.c(f.u, 0);
            bVar3.e(f.t, string7);
            bVar3.e(f.v, aVar6.b);
            bVar3.e(f.g, new MessageCardView.b() { // from class: x32
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.b
                public final Drawable a() {
                    if (aVar6.a != 1) {
                        return null;
                    }
                    return AppCompatResources.a(context, R$drawable.ic_price_alert_blue);
                }
            });
            bVar3.c(n0.a.a, 1);
            bVar3.f(n0.a.b, 1.0f);
            propertyModelA = bVar3.a();
        } else if (i != 4) {
            HashMap mapE = PropertyModel.e(f.w);
            PropertyModel.n nVar = f.s;
            int i5 = zj.a;
            PropertyModel.a aVar7 = new PropertyModel.a();
            aVar7.a = false;
            mapE.put(nVar, aVar7);
            propertyModelA = new PropertyModel(mapE);
        } else {
            a.C0114a c0114a = (a.C0114a) aVar;
            String string11 = context.getString(R$string.incognito_reauth_promo_title);
            String string12 = context.getString(R$string.incognito_reauth_promo_description);
            String string13 = context.getString(R$string.incognito_reauth_lock_action_text);
            String string14 = context.getString(R$string.no_thanks);
            PropertyModel.b bVar4 = new PropertyModel.b(f.w);
            PropertyModel.j jVar = f.a;
            bVar4.c(jVar, 4);
            bVar4.c(f.u, 1);
            bVar4.c(f.b, -1);
            bVar4.c(jVar, 4);
            bVar4.e(f.c, string13);
            bVar4.e(f.h, c0114a.a);
            bVar4.e(f.e, string12);
            bVar4.e(f.d, string14);
            bVar4.e(f.j, new ie0(20, c0114a));
            bVar4.b(f.n, false);
            bVar4.c(f.q, context.getResources().getDimensionPixelSize(R$dimen.incognito_reauth_promo_message_icon_width));
            bVar4.c(f.r, context.getResources().getDimensionPixelSize(R$dimen.incognito_reauth_promo_message_icon_height));
            bVar4.b(f.p, true);
            bVar4.b(f.o, false);
            bVar4.b(f.s, true);
            bVar4.e(f.t, string11);
            bVar4.e(f.g, new MessageCardView.b() { // from class: uy0
                @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.b
                public final Drawable a() {
                    return AppCompatResources.a(context, R$drawable.ic_incognito_reauth_promo_icon);
                }
            });
            bVar4.c(n0.a.a, 1);
            bVar4.f(n0.a.b, 1.0f);
            propertyModelA = bVar4.a();
        }
        a aVar8 = new a(i, propertyModelA);
        LinkedHashMap linkedHashMap = this.c;
        if (linkedHashMap.containsKey(Integer.valueOf(i))) {
            ((List) linkedHashMap.get(Integer.valueOf(i))).add(aVar8);
        } else {
            linkedHashMap.put(Integer.valueOf(i), new ArrayList(Arrays.asList(aVar8)));
        }
    }

    public final void c(int i) {
        this.d.remove(Integer.valueOf(i));
        this.e.a(i);
    }
}
