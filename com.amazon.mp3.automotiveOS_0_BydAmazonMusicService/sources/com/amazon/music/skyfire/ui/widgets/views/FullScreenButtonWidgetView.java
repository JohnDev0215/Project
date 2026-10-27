package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.amazon.music.views.library.R$layout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FullScreenButtonWidgetView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/FullScreenButtonWidgetView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FullScreenButtonWidgetView extends RelativeLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullScreenButtonWidgetView(Context context) {
        super(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(context, R$layout.dmmviewlibrary_full_screen_button_widget_layout, this);
    }
}
