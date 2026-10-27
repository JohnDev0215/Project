package org.chromium.components.messages;

import defpackage.fh1;
import defpackage.g3;
import defpackage.hg1;
import defpackage.im2;
import defpackage.iq;
import defpackage.jb1;
import defpackage.jg1;
import defpackage.lb1;
import defpackage.nw2;
import defpackage.pe2;
import defpackage.wg1;
import defpackage.yq2;
import java.util.HashMap;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MessageDispatcherImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements lb1 {
    public final fh1 e;
    public final MessageContainer f;
    public final nw2<Integer> g;
    public final jg1 h;
    public final hg1 i;
    public final WindowAndroid j;

    public a(MessageContainer messageContainer, yq2 yq2Var, iq iqVar, jb1 jb1Var, g3 g3Var) {
        fh1 fh1Var = new fh1(new hg1(messageContainer, jb1Var));
        this.f = messageContainer;
        this.g = yq2Var;
        this.h = iqVar;
        this.j = g3Var;
        this.e = fh1Var;
        this.i = fh1Var.b;
    }

    @Override // defpackage.vg1
    public final void a(int i, PropertyModel propertyModel) {
        fh1 fh1Var = this.e;
        HashMap map = fh1Var.c;
        fh1.a aVar = (fh1.a) map.get(propertyModel);
        if (aVar == null) {
            return;
        }
        map.remove(propertyModel);
        fh1Var.a(i, aVar);
    }

    @Override // defpackage.vg1
    public final void b(PropertyModel propertyModel, WebContents webContents, int i, boolean z) {
        this.e.b(propertyModel, new b(this.f, propertyModel, new im2(this), this.g, this.h, this.i), new pe2(webContents, i), z);
    }

    @Override // defpackage.vg1
    public final void c(PropertyModel propertyModel, boolean z) {
        this.e.b(propertyModel, new b(this.f, propertyModel, new wg1(this), this.g, this.h, this.i), new pe2(this.j), z);
    }
}
