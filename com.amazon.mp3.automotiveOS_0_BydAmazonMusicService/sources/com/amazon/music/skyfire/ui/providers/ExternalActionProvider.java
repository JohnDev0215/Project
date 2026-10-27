package com.amazon.music.skyfire.ui.providers;

import android.content.Context;
import android.view.View;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: ExternalActionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH&¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "", "handleAction", "", "context", "Landroid/content/Context;", "name", "", "args", "", "view", "Landroid/view/View;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ExternalActionProvider {
    boolean handleAction(Context context, String name, Map<String, String> args, View view);

    /* JADX INFO: compiled from: ExternalActionProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ boolean handleAction$default(ExternalActionProvider externalActionProvider, Context context, String str, Map map, View view, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleAction");
            }
            if ((i & 8) != 0) {
                view = null;
            }
            return externalActionProvider.handleAction(context, str, map, view);
        }
    }
}
