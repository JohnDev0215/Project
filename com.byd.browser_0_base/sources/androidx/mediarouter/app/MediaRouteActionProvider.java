package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ActionProvider;
import androidx.mediarouter.media.g;
import androidx.mediarouter.media.h;
import defpackage.pd1;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class MediaRouteActionProvider extends ActionProvider {
    public final h d;
    public final g e;
    public final pd1 f;
    public MediaRouteButton g;

    public MediaRouteActionProvider(Context context) {
        super(context);
        this.e = g.c;
        this.f = pd1.a;
        this.d = h.d(context);
        new WeakReference(this);
    }

    @Override // androidx.core.view.ActionProvider
    public final boolean b() {
        this.d.getClass();
        return h.g(this.e);
    }

    @Override // androidx.core.view.ActionProvider
    public final View c() {
        if (this.g != null) {
            Log.e("MRActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        MediaRouteButton mediaRouteButton = new MediaRouteButton(this.a);
        this.g = mediaRouteButton;
        mediaRouteButton.setCheatSheetEnabled(true);
        this.g.setRouteSelector(this.e);
        this.g.setAlwaysVisible(false);
        this.g.setDialogFactory(this.f);
        this.g.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        return this.g;
    }

    @Override // androidx.core.view.ActionProvider
    public final boolean e() {
        MediaRouteButton mediaRouteButton = this.g;
        if (mediaRouteButton != null) {
            return mediaRouteButton.d();
        }
        return false;
    }
}
