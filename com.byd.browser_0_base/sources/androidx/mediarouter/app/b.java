package androidx.mediarouter.app;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import androidx.mediarouter.app.OverlayListView;
import androidx.mediarouter.media.h;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: compiled from: MediaRouteControllerDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ Map e;
    public final /* synthetic */ Map f;
    public final /* synthetic */ MediaRouteControllerDialog g;

    public b(MediaRouteControllerDialog mediaRouteControllerDialog, HashMap map, HashMap map2) {
        this.g = mediaRouteControllerDialog;
        this.e = map;
        this.f = map2;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        Map map;
        Map map2;
        OverlayListView.a aVar;
        Map map3;
        MediaRouteControllerDialog mediaRouteControllerDialog = this.g;
        mediaRouteControllerDialog.I.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        HashSet hashSet = mediaRouteControllerDialog.L;
        if (hashSet == null || mediaRouteControllerDialog.M == null) {
            return;
        }
        int size = hashSet.size() - mediaRouteControllerDialog.M.size();
        c cVar = new c(mediaRouteControllerDialog);
        int firstVisiblePosition = mediaRouteControllerDialog.I.getFirstVisiblePosition();
        int i = 0;
        boolean z = false;
        while (true) {
            int childCount = mediaRouteControllerDialog.I.getChildCount();
            map = this.e;
            map2 = this.f;
            if (i >= childCount) {
                break;
            }
            View childAt = mediaRouteControllerDialog.I.getChildAt(i);
            h.C0020h item = mediaRouteControllerDialog.f14J.getItem(firstVisiblePosition + i);
            Rect rect = (Rect) map.get(item);
            int top = childAt.getTop();
            int i2 = rect != null ? rect.top : (mediaRouteControllerDialog.S * size) + top;
            AnimationSet animationSet = new AnimationSet(true);
            HashSet hashSet2 = mediaRouteControllerDialog.L;
            if (hashSet2 == null || !hashSet2.contains(item)) {
                map3 = map;
            } else {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
                map3 = map;
                alphaAnimation.setDuration(mediaRouteControllerDialog.m0);
                animationSet.addAnimation(alphaAnimation);
                i2 = top;
            }
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, i2 - top, 0.0f);
            translateAnimation.setDuration(mediaRouteControllerDialog.l0);
            animationSet.addAnimation(translateAnimation);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setInterpolator(mediaRouteControllerDialog.o0);
            if (!z) {
                animationSet.setAnimationListener(cVar);
                z = true;
            }
            childAt.clearAnimation();
            childAt.startAnimation(animationSet);
            map3.remove(item);
            map2.remove(item);
            i++;
        }
        for (Map.Entry entry : map2.entrySet()) {
            h.C0020h c0020h = (h.C0020h) entry.getKey();
            BitmapDrawable bitmapDrawable = (BitmapDrawable) entry.getValue();
            Rect rect2 = (Rect) map.get(c0020h);
            if (mediaRouteControllerDialog.M.contains(c0020h)) {
                aVar = new OverlayListView.a(bitmapDrawable, rect2);
                aVar.h = 1.0f;
                aVar.i = 0.0f;
                aVar.e = mediaRouteControllerDialog.n0;
                aVar.d = mediaRouteControllerDialog.o0;
            } else {
                int i3 = mediaRouteControllerDialog.S * size;
                OverlayListView.a aVar2 = new OverlayListView.a(bitmapDrawable, rect2);
                aVar2.g = i3;
                aVar2.e = mediaRouteControllerDialog.l0;
                aVar2.d = mediaRouteControllerDialog.o0;
                aVar2.m = new a(mediaRouteControllerDialog, c0020h);
                mediaRouteControllerDialog.N.add(c0020h);
                aVar = aVar2;
            }
            mediaRouteControllerDialog.I.e.add(aVar);
        }
    }
}
