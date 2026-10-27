package com.amazon.music.skyfire.ui.ktx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0006\u001a\u00020\u0002*\u00020\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n\u001a\u0014\u0010\u000b\u001a\u00020\u0002*\u00020\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b\u001a\u0015\u0010\f\u001a\u00020\r*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0086\u0002\u001a\u001b\u0010\f\u001a\u00020\r*\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0086\u0002\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"children", "", "Landroid/view/View;", "Landroid/view/ViewGroup;", "getChildren", "(Landroid/view/ViewGroup;)Ljava/lang/Iterable;", "inflate", "layoutId", "", "attachToRoot", "", "inflateThis", "plusAssign", "", "view", "views", "DMSFCoreAndroidUI_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ViewGroupKt {
    public static final Iterable<View> getChildren(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        return new ViewGroupKt$children$1(viewGroup);
    }

    public static /* synthetic */ View inflate$default(ViewGroup viewGroup, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return inflate(viewGroup, i, z);
    }

    public static final View inflate(ViewGroup viewGroup, @LayoutRes int i, boolean z) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, z);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "from(context).inflate(la…utId, this, attachToRoot)");
        return viewInflate;
    }

    public static final View inflateThis(ViewGroup viewGroup, @LayoutRes int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        View viewInflate = View.inflate(viewGroup.getContext(), i, viewGroup);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(context, layoutId, this)");
        return viewInflate;
    }

    public static final void plusAssign(ViewGroup viewGroup, View view) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        viewGroup.addView(view);
    }

    public static final void plusAssign(ViewGroup viewGroup, Iterable<? extends View> views) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        Intrinsics.checkNotNullParameter(views, "views");
        Iterator<? extends View> it = views.iterator();
        while (it.hasNext()) {
            viewGroup.addView(it.next());
        }
    }
}
