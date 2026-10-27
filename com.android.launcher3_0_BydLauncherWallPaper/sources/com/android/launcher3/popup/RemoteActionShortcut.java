package com.android.launcher3.popup;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.BaseDraggingActivity;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.R;

/* JADX INFO: loaded from: classes4.dex */
public class RemoteActionShortcut extends SystemShortcut<BaseDraggingActivity> {
    private static final boolean DEBUG = false;
    private static final String TAG = "RemoteActionShortcut";
    private final RemoteAction mAction;

    @Override // com.android.launcher3.popup.SystemShortcut
    public boolean isLeftGroup() {
        return true;
    }

    public RemoteActionShortcut(RemoteAction action) {
        super(action.getIcon(), action.getTitle(), action.getContentDescription(), R.id.action_remote_action_shortcut);
        this.mAction = action;
    }

    @Override // com.android.launcher3.popup.SystemShortcut
    public View.OnClickListener getOnClickListener(final BaseDraggingActivity activity, final ItemInfo itemInfo) {
        return new View.OnClickListener() { // from class: com.android.launcher3.popup.-$$Lambda$RemoteActionShortcut$sQFpNh7emSZCMBnjl5Dw7au11-I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getOnClickListener$1$RemoteActionShortcut(activity, itemInfo, view);
            }
        };
    }

    public /* synthetic */ void lambda$getOnClickListener$1$RemoteActionShortcut(final BaseDraggingActivity baseDraggingActivity, ItemInfo itemInfo, View view) {
        AbstractFloatingView.closeAllOpenViews(baseDraggingActivity);
        final String str = ((Object) this.mAction.getTitle()) + ", " + itemInfo.getTargetComponent().getPackageName();
        try {
            this.mAction.getActionIntent().send(baseDraggingActivity, 0, new Intent().putExtra("android.intent.extra.PACKAGE_NAME", itemInfo.getTargetComponent().getPackageName()), new PendingIntent.OnFinished() { // from class: com.android.launcher3.popup.-$$Lambda$RemoteActionShortcut$sBiqU-2L4pFNtpR_W3gQ115iXbk
                @Override // android.app.PendingIntent.OnFinished
                public final void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str2, Bundle bundle) {
                    RemoteActionShortcut.lambda$getOnClickListener$0(str, baseDraggingActivity, pendingIntent, intent, i, str2, bundle);
                }
            }, new Handler(Looper.getMainLooper()));
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Remote action canceled: " + str, e);
            Toast.makeText(baseDraggingActivity, baseDraggingActivity.getString(R.string.remote_action_failed, this.mAction.getTitle()), 0).show();
        }
        baseDraggingActivity.getUserEventDispatcher().logActionOnControl(0, 17, view);
    }

    static /* synthetic */ void lambda$getOnClickListener$0(String str, BaseDraggingActivity baseDraggingActivity, PendingIntent pendingIntent, Intent intent, int i, String str2, Bundle bundle) {
        if (str2 == null || str2.isEmpty()) {
            return;
        }
        Log.e(TAG, "Remote action returned result: " + str + " : " + str2);
        Toast.makeText(baseDraggingActivity, str2, 0).show();
    }
}
