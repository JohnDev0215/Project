package org.chromium.components.messages;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import defpackage.es3;
import defpackage.jh1;
import defpackage.k81;
import defpackage.l51;
import defpackage.l81;
import defpackage.ng1;
import defpackage.nw2;
import defpackage.o81;
import defpackage.v62;
import defpackage.xa1;
import defpackage.yq2;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.messages.MessageWrapper;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes3.dex */
public final class MessageWrapper implements k81.a {
    public long e;
    public final PropertyModel f;
    public jh1 g;

    /* JADX WARN: Type inference failed for: r4v1, types: [T, yq2] */
    /* JADX WARN: Type inference failed for: r4v2, types: [T, es3] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, l51] */
    public MessageWrapper(int i, long j) {
        this.e = j;
        HashMap mapE = PropertyModel.e(ng1.H);
        PropertyModel.j jVar = ng1.a;
        int i2 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(jVar, dVar);
        PropertyModel.r<nw2<Integer>> rVar = ng1.d;
        ?? yq2Var = new yq2(24, this);
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = yq2Var;
        mapE.put(rVar, gVar);
        PropertyModel.r<Runnable> rVar2 = ng1.e;
        ?? es3Var = new es3(10, this);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = es3Var;
        mapE.put(rVar2, gVar2);
        PropertyModel.r<Callback<Integer>> rVar3 = ng1.x;
        ?? l51Var = new l51(14, this);
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = l51Var;
        mapE.put(rVar3, gVar3);
        this.f = new PropertyModel(mapE);
    }

    @CalledByNative
    public static MessageWrapper create(long j, int i) {
        return new MessageWrapper(i, j);
    }

    @Override // k81.a
    public final void a(PropertyModel propertyModel) {
        N.MSwKRLAp(this.e, propertyModel.h(o81.f));
    }

    @CalledByNative
    public PropertyModel addSecondaryMenuItem(int i, int i2, String str) {
        return addSecondaryMenuItem(i, i2, str, str);
    }

    @CalledByNative
    public void addSecondaryMenuItemDivider() {
        jh1 jh1Var = this.g;
        if (jh1Var == null) {
            return;
        }
        jh1Var.a.t(new xa1(0, new PropertyModel(new v62[0])));
    }

    @CalledByNative
    public void clearNativePtr() {
        this.e = 0L;
    }

    @CalledByNative
    public void clearSecondaryMenuItems() {
        jh1 jh1Var = this.g;
        if (jh1Var == null) {
            return;
        }
        jh1Var.a.clear();
    }

    @CalledByNative
    public void disableIconTint() {
        this.f.n(ng1.p, 0);
    }

    @CalledByNative
    public String getDescription() {
        CharSequence charSequence = (CharSequence) this.f.j(ng1.h);
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }

    @CalledByNative
    public int getDescriptionMaxLines() {
        return this.f.h(ng1.k);
    }

    @CalledByNative
    public Bitmap getIconBitmap() {
        return ((BitmapDrawable) ((Drawable) this.f.j(ng1.l))).getBitmap();
    }

    @CalledByNative
    public int getIconResourceId() {
        return this.f.h(ng1.m);
    }

    @CalledByNative
    public String getPrimaryButtonText() {
        return (String) this.f.j(ng1.c);
    }

    @CalledByNative
    public String getSecondaryButtonMenuText() {
        return (String) this.f.j(ng1.s);
    }

    @CalledByNative
    public int getSecondaryIconResourceId() {
        return this.f.h(ng1.r);
    }

    @CalledByNative
    public String getTitle() {
        return (String) this.f.j(ng1.f);
    }

    @CalledByNative
    public void initializeSecondaryMenu(WindowAndroid windowAndroid, int i) {
        final Context context = (Context) windowAndroid.k().get();
        if (this.g != null) {
            PropertyModel propertyModel = this.f;
            propertyModel.n(ng1.v, i);
            propertyModel.p(ng1.u, new l81() { // from class: lh1
                @Override // defpackage.l81
                public final k81 b() {
                    MessageWrapper messageWrapper = this.e;
                    return new zd(context, messageWrapper.g.a, messageWrapper);
                }
            });
        }
    }

    @CalledByNative
    public boolean isValidIcon() {
        return this.f.j(ng1.l) != null;
    }

    @CalledByNative
    public void setDescription(CharSequence charSequence) {
        this.f.p(ng1.h, charSequence);
    }

    @CalledByNative
    public void setDescriptionMaxLines(int i) {
        this.f.n(ng1.k, i);
    }

    @CalledByNative
    public void setDuration(long j) {
        this.f.o(ng1.w, j);
    }

    @CalledByNative
    public void setIcon(Bitmap bitmap) {
        this.f.p(ng1.l, new BitmapDrawable(bitmap));
    }

    @CalledByNative
    public void setIconResourceId(int i) {
        this.f.n(ng1.m, i);
    }

    @CalledByNative
    public void setIconRoundedCornerRadius(int i) {
        this.f.n(ng1.o, i);
    }

    @CalledByNative
    public void setLargeIcon(boolean z) {
        this.f.l(ng1.n, z);
    }

    @CalledByNative
    public void setPrimaryButtonText(String str) {
        this.f.p(ng1.c, str);
    }

    @CalledByNative
    public void setSecondaryButtonMenuText(String str) {
        this.f.p(ng1.s, str);
    }

    @CalledByNative
    public void setSecondaryIconResourceId(int i) {
        this.f.n(ng1.r, i);
    }

    @CalledByNative
    public void setTitle(String str) {
        this.f.p(ng1.f, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CalledByNative
    public PropertyModel addSecondaryMenuItem(int i, int i2, String str, String str2) {
        if (this.g == null) {
            this.g = new jh1();
        }
        jh1 jh1Var = this.g;
        jh1Var.getClass();
        HashMap mapE = PropertyModel.e(o81.k);
        PropertyModel.p pVar = o81.f;
        int i3 = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(pVar, dVar);
        PropertyModel.p pVar2 = o81.d;
        PropertyModel.d dVar2 = new PropertyModel.d();
        dVar2.a = i2;
        mapE.put(pVar2, dVar2);
        PropertyModel.r<String> rVar = o81.b;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = str;
        mapE.put(rVar, gVar);
        PropertyModel.r<String> rVar2 = o81.c;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = str2;
        mapE.put(rVar2, gVar2);
        PropertyModel.n nVar = o81.g;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = true;
        mapE.put(nVar, aVar);
        PropertyModel propertyModel = new PropertyModel(mapE);
        jh1Var.a.t(new xa1(1, propertyModel));
        return propertyModel;
    }
}
