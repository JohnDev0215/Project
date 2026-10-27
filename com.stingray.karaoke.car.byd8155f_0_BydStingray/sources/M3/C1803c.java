package m3;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: m3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1803c extends View implements H {
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f19009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f19010n;
    public C1804d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f19011p;

    public C1803c(Context context) {
        super(context, null);
        this.l = new ArrayList();
        this.f19009m = Collections.emptyList();
        this.f19010n = 0.0533f;
        this.o = C1804d.f19012g;
        this.f19011p = 0.08f;
    }

    @Override // m3.H
    public final void a(List list, C1804d c1804d, float f10, float f11) {
        this.f19009m = list;
        this.o = c1804d;
        this.f19010n = f10;
        this.f19011p = f11;
        while (true) {
            ArrayList arrayList = this.l;
            if (arrayList.size() >= list.size()) {
                invalidate();
                return;
            }
            arrayList.add(new G(getContext()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:198:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010e  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dispatchDraw(android.graphics.Canvas r40) {
        /*
            Method dump skipped, instruction units count: 1273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m3.C1803c.dispatchDraw(android.graphics.Canvas):void");
    }
}
