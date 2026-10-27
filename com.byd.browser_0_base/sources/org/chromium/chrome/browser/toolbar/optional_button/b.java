package org.chromium.chrome.browser.toolbar.optional_button;

import android.view.View;
import android.view.ViewGroup;
import defpackage.bt1;
import defpackage.ct1;
import defpackage.hw0;
import defpackage.on0;
import defpackage.pk3;
import defpackage.xc3;
import defpackage.xe3;
import defpackage.zj;
import java.util.HashMap;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: OptionalButtonCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public final bt1 a;
    public final OptionalButtonView b;
    public final pk3 c;
    public final xe3 d;
    public Callback<Integer> e;
    public hw0 f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, on0] */
    public b(View view, pk3 pk3Var, ViewGroup viewGroup, xc3 xc3Var, xe3 xe3Var) {
        this.c = pk3Var;
        HashMap mapE = PropertyModel.e(ct1.l);
        PropertyModel.r<Callback<Integer>> rVar = ct1.d;
        ?? on0Var = new on0(27, this);
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = on0Var;
        mapE.put(rVar, gVar);
        PropertyModel.r<ViewGroup> rVar2 = ct1.f;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = viewGroup;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<BooleanSupplier> rVar3 = ct1.k;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = xc3Var;
        mapE.put(rVar3, gVar3);
        PropertyModel propertyModel = new PropertyModel(mapE);
        OptionalButtonView optionalButtonView = (OptionalButtonView) view;
        this.b = optionalButtonView;
        j.b(propertyModel, optionalButtonView, new j.a() { // from class: org.chromium.chrome.browser.toolbar.optional_button.a
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0083  */
            @Override // org.chromium.ui.modelutil.j.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void b(org.chromium.ui.modelutil.k r11, java.lang.Object r12, java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 524
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.optional_button.a.b(org.chromium.ui.modelutil.k, java.lang.Object, java.lang.Object):void");
            }
        });
        this.a = new bt1(propertyModel);
        this.d = xe3Var;
    }

    public final ListMenuButton a() {
        return this.b.i;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(defpackage.ak r12) {
        /*
            r11 = this;
            ak$a r0 = r12.b()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L3d
            ak$a r0 = r12.b()
            hw0 r0 = r0.f
            if (r0 == 0) goto L3d
            ak$a r0 = r12.b()
            hw0 r0 = r0.f
            r11.f = r0
            wm3$a r3 = new wm3$a
            r3.<init>(r1)
            at1 r4 = new at1
            r4.<init>(r11)
            r3.d = r4
            nn3 r4 = new nn3
            org.chromium.chrome.browser.toolbar.optional_button.OptionalButtonView r5 = r11.b
            r4.<init>(r5)
            r4.d(r2)
            r3.b = r2
            org.chromium.components.browser_ui.widget.listmenu.ListMenuButton r6 = r5.i
            if (r6 != 0) goto L35
            goto L36
        L35:
            r5 = r6
        L36:
            r0.j = r5
            r0.p = r4
            r0.q = r3
            goto L40
        L3d:
            r0 = 0
            r11.f = r0
        L40:
            ak$a r0 = r12.b()
            int r0 = r0.i
            if (r0 == 0) goto L4a
            r0 = r1
            goto L4b
        L4a:
            r0 = r2
        L4b:
            if (r0 == 0) goto La9
            boolean r0 = defpackage.a8.P()
            if (r0 == 0) goto L76
            ak$a r0 = r12.b()
            int r0 = r0.g
            r3 = 6
            if (r0 == r3) goto L61
            r3 = 7
            if (r0 == r3) goto L61
            r3 = r2
            goto L62
        L61:
            r3 = r1
        L62:
            if (r3 != 0) goto L66
            r0 = r2
            goto L72
        L66:
            java.lang.String r0 = defpackage.n3.X(r0)
            sk r3 = defpackage.bq.a
            java.lang.String r3 = "action_chip"
            boolean r0 = J.N.M6bsIDpc(r0, r3, r2)
        L72:
            if (r0 == 0) goto L76
            r0 = r1
            goto L77
        L76:
            r0 = r2
        L77:
            if (r0 == 0) goto L8a
            xe3 r0 = r11.d
            boolean r3 = r0.isInitialized()
            if (r3 == 0) goto L8a
            java.lang.String r3 = "IPH_ContextualPageActions_ActionChip"
            boolean r0 = r0.shouldTriggerHelpUI(r3)
            if (r0 == 0) goto L8a
            goto L8b
        L8a:
            r1 = r2
        L8b:
            if (r1 != 0) goto La9
            r0 = r12
            bk r0 = (defpackage.bk) r0
            r9 = 0
            ak$a r1 = r0.c
            ak$a r10 = new ak$a
            android.graphics.drawable.Drawable r2 = r1.a
            android.view.View$OnClickListener r3 = r1.b
            android.view.View$OnLongClickListener r4 = r1.c
            java.lang.String r5 = r1.d
            boolean r6 = r1.e
            hw0 r7 = r1.f
            int r8 = r1.g
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.c = r10
        La9:
            bt1 r11 = r11.a
            r11.getClass()
            org.chromium.ui.modelutil.PropertyModel$r<ak> r0 = defpackage.ct1.a
            org.chromium.ui.modelutil.PropertyModel r11 = r11.a
            r11.p(r0, r12)
            org.chromium.ui.modelutil.PropertyModel$n r0 = defpackage.ct1.b
            boolean r12 = r12.isEnabled()
            r11.l(r0, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.optional_button.b.b(ak):void");
    }
}
