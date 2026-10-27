package org.chromium.components.messages;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import defpackage.a9;
import defpackage.an2;
import defpackage.cx2;
import defpackage.es3;
import defpackage.f52;
import defpackage.gs;
import defpackage.hg1;
import defpackage.hs;
import defpackage.jg1;
import defpackage.kh1;
import defpackage.lg1;
import defpackage.mg1;
import defpackage.ng1;
import defpackage.nw2;
import defpackage.ug1;
import defpackage.ve3;
import defpackage.yr;
import defpackage.zm2;
import java.util.ArrayList;
import org.chromium.components.messages.MessageContainer;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SingleActionMessage.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements kh1, MessageContainer.b {
    public lg1 a;
    public MessageBannerView b;
    public final MessageContainer c;
    public final PropertyModel d;
    public final a e;
    public final zm2 f;
    public final nw2<Integer> g;
    public final cx2 h;
    public boolean i;
    public long j;

    /* JADX INFO: compiled from: SingleActionMessage.java */
    @FunctionalInterface
    public interface a {
        void a(int i, PropertyModel propertyModel);
    }

    public b(MessageContainer messageContainer, PropertyModel propertyModel, a aVar, nw2 nw2Var, jg1 jg1Var, hg1 hg1Var) {
        this.d = propertyModel;
        this.c = messageContainer;
        this.e = aVar;
        this.g = nw2Var;
        this.h = hg1Var;
        ArrayList arrayListB = propertyModel.b();
        PropertyModel.q qVar = ng1.w;
        this.f = new zm2(jg1Var, propertyModel, arrayListB.contains(qVar) ? propertyModel.i(qVar) : 0L);
        propertyModel.p(ng1.F, new gs(19, this));
        propertyModel.p(ng1.G, new f52(27, this));
    }

    public final int a() {
        return Integer.valueOf(this.d.h(ng1.a)).intValue();
    }

    public final AnimatorSet b(int i, boolean z) throws Throwable {
        lg1 lg1Var = this.a;
        an2 an2Var = new an2(0, this);
        MessageBannerView messageBannerView = lg1Var.b;
        messageBannerView.p.b();
        lg1Var.d.a();
        boolean z2 = z && !((Settings.Global.getFloat(messageBannerView.getContext().getContentResolver(), "animator_duration_scale", 1.0f) > 0.0f ? 1 : (Settings.Global.getFloat(messageBannerView.getContext().getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f ? 0 : -1)) == 0);
        a9 a9Var = new a9(lg1Var, 28, an2Var);
        mg1 mg1Var = lg1Var.a;
        Animator animator = mg1Var.n;
        if (animator != null) {
            animator.cancel();
        }
        mg1Var.n = null;
        float f = i == 1 ? 0.0f : -mg1Var.f.get().intValue();
        if (!z2) {
            PropertyModel.o oVar = ng1.C;
            PropertyModel propertyModel = mg1Var.e;
            propertyModel.m(oVar, 0.0f);
            propertyModel.m(ng1.A, f);
            mg1Var.o = 0;
        }
        if (mg1Var.o != 0) {
            return mg1Var.b(true, false, f, false, mg1Var.m, a9Var);
        }
        a9Var.run();
        return null;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final AnimatorSet c(int i, int i2) {
        int titleHeightForAnimation;
        int descriptionHeightForAnimation;
        int width;
        lg1 lg1Var = this.a;
        int iA = 0;
        MessageContainer messageContainer = this.c;
        if (lg1Var == null) {
            MessageBannerView messageBannerView = (MessageBannerView) LayoutInflater.from(messageContainer.getContext()).inflate(R$layout.message_banner_view, (ViewGroup) messageContainer, false);
            this.b = messageBannerView;
            this.a = new lg1(messageBannerView, this.d, this.g, messageContainer.getResources(), new es3(11, this), this.h, this.f, new ve3(17, this));
        }
        this.d.m(ng1.E, this.b.getResources().getDimension(i2 == 1 ? R$dimen.message_banner_elevation : R$dimen.message_banner_back_elevation));
        if (messageContainer.indexOfChild(this.b) == -1) {
            messageContainer.a(this.b);
        }
        if (i2 == 1) {
            messageContainer.setA11yDelegate(this);
        }
        this.j = SystemClock.uptimeMillis();
        lg1 lg1Var2 = this.a;
        hs hsVar = new hs(12, this);
        MessageBannerView messageBannerView2 = lg1Var2.b;
        messageBannerView2.p.b();
        if (i2 == 2) {
            ug1 ug1Var = (ug1) hsVar.get();
            int height = messageBannerView2.getHeight();
            if (!messageBannerView2.isLaidOut()) {
                View view = ug1Var.a;
                if (view.isLaidOut()) {
                    width = view.getWidth();
                } else {
                    if (!ug1Var.b) {
                        ug1Var.b();
                    }
                    width = view.getMeasuredWidth();
                }
                messageBannerView2.measure(View.MeasureSpec.makeMeasureSpec(width, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
                height = messageBannerView2.getMeasuredHeight();
            }
            if (height < ug1Var.a()) {
                iA = ug1Var.a() - height;
            } else if (height > ug1Var.a()) {
                View view2 = ug1Var.a;
                if (view2.isLaidOut()) {
                    titleHeightForAnimation = ((MessageBannerView) view2).getTitleHeightForAnimation();
                } else {
                    if (!ug1Var.b) {
                        ug1Var.b();
                    }
                    titleHeightForAnimation = ((MessageBannerView) view2).getTitleMeasuredHeightForAnimation();
                }
                if (view2.isLaidOut()) {
                    descriptionHeightForAnimation = ((MessageBannerView) view2).getDescriptionHeightForAnimation();
                } else {
                    if (!ug1Var.b) {
                        ug1Var.b();
                    }
                    descriptionHeightForAnimation = ((MessageBannerView) view2).getDescriptionMeasuredHeightForAnimation();
                }
                if (!view2.isLaidOut() && !ug1Var.b) {
                    ug1Var.b();
                }
                int primaryButtonLineCountForAnimation = ((MessageBannerView) view2).getPrimaryButtonLineCountForAnimation();
                if (titleHeightForAnimation != messageBannerView2.k.getMeasuredHeight()) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) messageBannerView2.k.getLayoutParams();
                    layoutParams.height = titleHeightForAnimation;
                    messageBannerView2.k.setLayoutParams(layoutParams);
                }
                if (descriptionHeightForAnimation != messageBannerView2.l.getMeasuredHeight()) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) messageBannerView2.l.getLayoutParams();
                    layoutParams2.height = descriptionHeightForAnimation;
                    messageBannerView2.l.setLayoutParams(layoutParams2);
                }
                messageBannerView2.n.setMaxLines(primaryButtonLineCountForAnimation);
                messageBannerView2.n.setEllipsize(TextUtils.TruncateAt.END);
            }
        } else if (i == 2 && i2 == 1) {
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) messageBannerView2.k.getLayoutParams();
            if (layoutParams3.height != -2) {
                layoutParams3.height = -2;
                messageBannerView2.k.setLayoutParams(layoutParams3);
            }
            LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) messageBannerView2.l.getLayoutParams();
            if (layoutParams4.height != -2) {
                layoutParams4.height = -2;
                messageBannerView2.l.setLayoutParams(layoutParams4);
            }
            messageBannerView2.n.setMaxLines(Integer.MAX_VALUE);
            messageBannerView2.n.setEllipsize(null);
        }
        yr yrVar = new yr(i2, 3, lg1Var2);
        mg1 mg1Var = lg1Var2.a;
        int i3 = mg1Var.o;
        int i4 = mg1Var.m;
        PropertyModel propertyModel = mg1Var.e;
        if (i3 == 0) {
            propertyModel.m(ng1.A, i == 1 ? 0.0f : -mg1Var.f.get().intValue());
            propertyModel.n(ng1.B, i4);
        } else if (i3 == 2 && i2 == 1) {
            PropertyModel.o oVar = ng1.A;
            PropertyModel.p pVar = ng1.B;
            propertyModel.m(oVar, propertyModel.h(pVar) - i4);
            propertyModel.n(pVar, i4);
        }
        Animator animator = mg1Var.n;
        if (animator != null) {
            animator.cancel();
        }
        mg1Var.n = null;
        return mg1Var.b(true, true, 0.0f, false, i2 == 2 ? mg1Var.l + iA : i4, yrVar);
    }
}
