package s3;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: s3.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2365D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC2367F f22176b;

    public /* synthetic */ C2365D(AbstractC2367F abstractC2367F, int i7) {
        this.f22175a = i7;
        this.f22176b = abstractC2367F;
    }

    public final int a(View view) {
        switch (this.f22175a) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                this.f22176b.getClass();
                return view.getRight() + ((C2368G) view.getLayoutParams()).f22191b.right + ((ViewGroup.MarginLayoutParams) c2368g).rightMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                this.f22176b.getClass();
                return view.getBottom() + ((C2368G) view.getLayoutParams()).f22191b.bottom + ((ViewGroup.MarginLayoutParams) c2368g2).bottomMargin;
        }
    }

    public final int b(View view) {
        switch (this.f22175a) {
            case 0:
                C2368G c2368g = (C2368G) view.getLayoutParams();
                this.f22176b.getClass();
                return (view.getLeft() - ((C2368G) view.getLayoutParams()).f22191b.left) - ((ViewGroup.MarginLayoutParams) c2368g).leftMargin;
            default:
                C2368G c2368g2 = (C2368G) view.getLayoutParams();
                this.f22176b.getClass();
                return (view.getTop() - ((C2368G) view.getLayoutParams()).f22191b.top) - ((ViewGroup.MarginLayoutParams) c2368g2).topMargin;
        }
    }

    public final int c() {
        switch (this.f22175a) {
            case 0:
                AbstractC2367F abstractC2367F = this.f22176b;
                return abstractC2367F.f22189n - abstractC2367F.B();
            default:
                AbstractC2367F abstractC2367F2 = this.f22176b;
                return abstractC2367F2.o - abstractC2367F2.z();
        }
    }

    public final int d() {
        switch (this.f22175a) {
            case 0:
                return this.f22176b.A();
            default:
                return this.f22176b.C();
        }
    }
}
