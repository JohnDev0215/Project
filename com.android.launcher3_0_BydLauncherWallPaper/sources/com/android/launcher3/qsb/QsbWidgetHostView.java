package com.android.launcher3.qsb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.widget.NavigableAppWidgetHostView;

/* JADX INFO: loaded from: classes4.dex */
public class QsbWidgetHostView extends NavigableAppWidgetHostView {

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mPreviousOrientation;

    @Override // com.android.launcher3.widget.NavigableAppWidgetHostView
    protected boolean shouldAllowDirectClick() {
        return true;
    }

    public QsbWidgetHostView(Context context) {
        super(context);
        setFocusable(true);
        setBackgroundResource(R.drawable.qsb_host_view_focus_bg);
    }

    @Override // android.appwidget.AppWidgetHostView
    public void updateAppWidget(RemoteViews remoteViews) {
        this.mPreviousOrientation = getResources().getConfiguration().orientation;
        super.updateAppWidget(remoteViews);
    }

    public boolean isReinflateRequired(int orientation) {
        return this.mPreviousOrientation != orientation;
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(0, 0, 0, 0);
    }

    @Override // android.appwidget.AppWidgetHostView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        try {
            super.onLayout(changed, left, top, right, bottom);
        } catch (RuntimeException unused) {
            post(new Runnable() { // from class: com.android.launcher3.qsb.-$$Lambda$QsbWidgetHostView$sdn7YE9p3_TebdHSMjfMTFKQxg8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onLayout$0$QsbWidgetHostView();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onLayout$0$QsbWidgetHostView() {
        updateAppWidget(new RemoteViews(getAppWidgetInfo().provider.getPackageName(), 0));
    }

    @Override // android.appwidget.AppWidgetHostView
    protected View getErrorView() {
        return getDefaultView(this);
    }

    @Override // android.appwidget.AppWidgetHostView
    protected View getDefaultView() {
        View defaultView = super.getDefaultView();
        defaultView.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.qsb.-$$Lambda$QsbWidgetHostView$kcDudMhQRTKhKifmIg4llyRWDdU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getDefaultView$1$QsbWidgetHostView(view);
            }
        });
        return defaultView;
    }

    public /* synthetic */ void lambda$getDefaultView$1$QsbWidgetHostView(View view) {
        Launcher.getLauncher(getContext()).startSearch("", false, null, true);
    }

    public static View getDefaultView(ViewGroup parent) {
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.qsb_default_view, parent, false);
        viewInflate.findViewById(R.id.btn_qsb_search).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.qsb.-$$Lambda$QsbWidgetHostView$UOz71DN3v7EvlljLFBdDT0She50
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Launcher.getLauncher(view.getContext()).startSearch("", false, null, true);
            }
        });
        return viewInflate;
    }
}
