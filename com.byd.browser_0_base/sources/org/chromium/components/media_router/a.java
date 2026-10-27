package org.chromium.components.media_router;

import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import defpackage.r90;

/* JADX INFO: compiled from: BaseMediaRouteDialogManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public r90 a;

    /* JADX INFO: renamed from: org.chromium.components.media_router.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseMediaRouteDialogManager.java */
    public static class C0132a {
        public int a;
        public boolean b;

        public final void a(FragmentActivity fragmentActivity) {
            if (this.b) {
                FrameLayout frameLayout = (FrameLayout) fragmentActivity.getWindow().getDecorView();
                if ((frameLayout.getSystemUiVisibility() & 1024) == 0) {
                    frameLayout.setSystemUiVisibility(this.a);
                }
            }
        }
    }
}
