package io.sentry.android.core.internal.gestures;

import android.content.res.Resources;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import androidx.core.view.ScrollingView;
import h6.q;
import h6.r;

/* JADX INFO: loaded from: classes.dex */
public final class a implements io.sentry.internal.gestures.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f16178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f16179b = new int[2];

    public a(boolean z6) {
        this.f16178a = z6;
    }

    @Override // io.sentry.internal.gestures.a
    public final io.sentry.internal.gestures.c a(View view, float f10, float f11, io.sentry.internal.gestures.b bVar) {
        int[] iArr = this.f16179b;
        view.getLocationOnScreen(iArr);
        boolean z6 = false;
        int i7 = iArr[0];
        int i10 = iArr[1];
        int width = view.getWidth();
        int height = view.getHeight();
        if (f10 < i7 || f10 > i7 + width || f11 < i10 || f11 > i10 + height) {
            return null;
        }
        if (bVar == io.sentry.internal.gestures.b.CLICKABLE && view.isClickable() && view.getVisibility() == 0) {
            try {
                return new io.sentry.internal.gestures.c(view, r.h(view), q.t(view), null, "old_view_system");
            } catch (Resources.NotFoundException unused) {
                return null;
            }
        }
        if (bVar != io.sentry.internal.gestures.b.SCROLLABLE) {
            return null;
        }
        if (((!this.f16178a ? false : ScrollingView.class.isAssignableFrom(view.getClass())) || AbsListView.class.isAssignableFrom(view.getClass()) || ScrollView.class.isAssignableFrom(view.getClass())) && view.getVisibility() == 0) {
            z6 = true;
        }
        if (!z6) {
            return null;
        }
        try {
            return new io.sentry.internal.gestures.c(view, r.h(view), q.t(view), null, "old_view_system");
        } catch (Resources.NotFoundException unused2) {
            return null;
        }
    }
}
