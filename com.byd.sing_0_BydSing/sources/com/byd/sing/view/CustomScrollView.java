package com.byd.sing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes.dex */
public class CustomScrollView extends ScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3291b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3292c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f3293d;

    public interface a {
        void a();

        void b();
    }

    public CustomScrollView(Context context) {
        super(context);
        this.f3291b = true;
        this.f3292c = false;
    }

    private void a() {
        a aVar;
        if (this.f3291b) {
            a aVar2 = this.f3293d;
            if (aVar2 != null) {
                aVar2.b();
                return;
            }
            return;
        }
        if (!this.f3292c || (aVar = this.f3293d) == null) {
            return;
        }
        aVar.a();
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        if (i2 <= 0) {
            this.f3291b = z2;
            this.f3292c = false;
        } else {
            this.f3291b = false;
            this.f3292c = z2;
        }
        a();
    }

    public void setScanScrollChangedListener(a aVar) {
        this.f3293d = aVar;
    }

    public CustomScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3291b = true;
        this.f3292c = false;
    }

    public CustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3291b = true;
        this.f3292c = false;
    }
}
