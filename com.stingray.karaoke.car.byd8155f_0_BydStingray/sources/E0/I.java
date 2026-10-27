package E0;

import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;

/* JADX INFO: loaded from: classes.dex */
public abstract class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a1.c f1503a = l9.d.g();

    public static final Owner a(F f10) {
        AndroidComposeView androidComposeView = f10.f1487t;
        if (androidComposeView != null) {
            return androidComposeView;
        }
        l9.d.i0("LayoutNode should be attached to an owner");
        throw null;
    }
}
