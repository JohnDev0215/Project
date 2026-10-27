package s3;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: s3.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2401u extends T1.f {
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2401u(AbstractC2367F abstractC2367F, int i7) {
        super(abstractC2367F);
        this.d = i7;
    }

    @Override // T1.f
    public final int b(View view) {
        switch (this.d) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                return view.getRight() + ((C2368G) view.getLayoutParams()).f22191b.right + ((ViewGroup.MarginLayoutParams) c2368g).rightMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                return view.getBottom() + ((C2368G) view.getLayoutParams()).f22191b.bottom + ((ViewGroup.MarginLayoutParams) c2368g2).bottomMargin;
        }
    }

    @Override // T1.f
    public final int c(View view) {
        switch (this.d) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                Rect rect = ((C2368G) view.getLayoutParams()).f22191b;
                return view.getMeasuredWidth() + rect.left + rect.right + ((ViewGroup.MarginLayoutParams) c2368g).leftMargin + ((ViewGroup.MarginLayoutParams) c2368g).rightMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                Rect rect2 = ((C2368G) view.getLayoutParams()).f22191b;
                return view.getMeasuredHeight() + rect2.top + rect2.bottom + ((ViewGroup.MarginLayoutParams) c2368g2).topMargin + ((ViewGroup.MarginLayoutParams) c2368g2).bottomMargin;
        }
    }

    @Override // T1.f
    public final int d(View view) {
        switch (this.d) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                Rect rect = ((C2368G) view.getLayoutParams()).f22191b;
                return view.getMeasuredHeight() + rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) c2368g).topMargin + ((ViewGroup.MarginLayoutParams) c2368g).bottomMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                Rect rect2 = ((C2368G) view.getLayoutParams()).f22191b;
                return view.getMeasuredWidth() + rect2.left + rect2.right + ((ViewGroup.MarginLayoutParams) c2368g2).leftMargin + ((ViewGroup.MarginLayoutParams) c2368g2).rightMargin;
        }
    }

    @Override // T1.f
    public final int e(View view) {
        switch (this.d) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                return (view.getLeft() - ((C2368G) view.getLayoutParams()).f22191b.left) - ((ViewGroup.MarginLayoutParams) c2368g).leftMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                ((AbstractC2367F) this.f8493b).getClass();
                return (view.getTop() - ((C2368G) view.getLayoutParams()).f22191b.top) - ((ViewGroup.MarginLayoutParams) c2368g2).topMargin;
        }
    }

    @Override // T1.f
    public final int f() {
        switch (this.d) {
            case 0:
                return ((AbstractC2367F) this.f8493b).f22189n;
            default:
                return ((AbstractC2367F) this.f8493b).o;
        }
    }

    @Override // T1.f
    public final int g() {
        switch (this.d) {
            case 0:
                AbstractC2367F abstractC2367F = (AbstractC2367F) this.f8493b;
                return abstractC2367F.f22189n - abstractC2367F.B();
            default:
                AbstractC2367F abstractC2367F2 = (AbstractC2367F) this.f8493b;
                return abstractC2367F2.o - abstractC2367F2.z();
        }
    }

    @Override // T1.f
    public final int h() {
        switch (this.d) {
            case 0:
                return ((AbstractC2367F) this.f8493b).B();
            default:
                return ((AbstractC2367F) this.f8493b).z();
        }
    }

    @Override // T1.f
    public final int i() {
        switch (this.d) {
            case 0:
                return ((AbstractC2367F) this.f8493b).l;
            default:
                return ((AbstractC2367F) this.f8493b).f22188m;
        }
    }

    @Override // T1.f
    public final int j() {
        switch (this.d) {
            case 0:
                return ((AbstractC2367F) this.f8493b).f22188m;
            default:
                return ((AbstractC2367F) this.f8493b).l;
        }
    }

    @Override // T1.f
    public final int k() {
        switch (this.d) {
            case 0:
                return ((AbstractC2367F) this.f8493b).A();
            default:
                return ((AbstractC2367F) this.f8493b).C();
        }
    }

    @Override // T1.f
    public final int l() {
        switch (this.d) {
            case 0:
                AbstractC2367F abstractC2367F = (AbstractC2367F) this.f8493b;
                return (abstractC2367F.f22189n - abstractC2367F.A()) - abstractC2367F.B();
            default:
                AbstractC2367F abstractC2367F2 = (AbstractC2367F) this.f8493b;
                return (abstractC2367F2.o - abstractC2367F2.C()) - abstractC2367F2.z();
        }
    }

    @Override // T1.f
    public final int n(View view) {
        switch (this.d) {
            case 0:
                AbstractC2367F abstractC2367F = (AbstractC2367F) this.f8493b;
                Rect rect = (Rect) this.c;
                abstractC2367F.G(view, rect);
                return rect.right;
            default:
                AbstractC2367F abstractC2367F2 = (AbstractC2367F) this.f8493b;
                Rect rect2 = (Rect) this.c;
                abstractC2367F2.G(view, rect2);
                return rect2.bottom;
        }
    }

    @Override // T1.f
    public final int o(View view) {
        switch (this.d) {
            case 0:
                AbstractC2367F abstractC2367F = (AbstractC2367F) this.f8493b;
                Rect rect = (Rect) this.c;
                abstractC2367F.G(view, rect);
                return rect.left;
            default:
                AbstractC2367F abstractC2367F2 = (AbstractC2367F) this.f8493b;
                Rect rect2 = (Rect) this.c;
                abstractC2367F2.G(view, rect2);
                return rect2.top;
        }
    }

    @Override // T1.f
    public final void p(int i7) {
        switch (this.d) {
            case 0:
                ((AbstractC2367F) this.f8493b).K(i7);
                break;
            default:
                ((AbstractC2367F) this.f8493b).L(i7);
                break;
        }
    }
}
