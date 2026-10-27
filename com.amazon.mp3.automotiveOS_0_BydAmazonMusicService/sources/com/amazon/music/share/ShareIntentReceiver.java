package com.amazon.music.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;

/* JADX INFO: loaded from: classes4.dex */
public class ShareIntentReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ActionType actionType;
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        Bundle extras = intent.getExtras();
        Object obj = extras.get("android.intent.extra.CHOSEN_COMPONENT");
        if (obj != null) {
            String string = obj.toString();
            string.hashCode();
            if (string.equals("ComponentInfo{com.facebook.katana/com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias}")) {
                actionType = ActionType.SHARE_ON_FACEBOOK;
            } else if (string.equals("ComponentInfo{com.twitter.android/com.twitter.android.composer.ComposerActivity}")) {
                actionType = ActionType.SHARE_ON_TWITTER;
            } else {
                actionType = ActionType.SHARE;
            }
            emitMetrics(actionType, EntityIdType.valueOf(extras.getString(Contexts.OpenShareLink.entityIdType)), EntityType.valueOf(extras.getString(Contexts.OpenShareLink.entityType)), extras.getString(Contexts.OpenShareLink.entityId), extras.getString("REF_MARKER"));
            return;
        }
        if (extras.containsKey("ACTION_TYPE")) {
            emitMetrics(ActionType.valueOf(extras.getString("ACTION_TYPE")), EntityIdType.valueOf(extras.getString(Contexts.OpenShareLink.entityIdType)), EntityType.valueOf(extras.getString(Contexts.OpenShareLink.entityType)), extras.getString(Contexts.OpenShareLink.entityId), extras.getString("REF_MARKER"));
        }
    }

    private void emitMetrics(ActionType actionType, EntityIdType entityIdType, EntityType entityType, String str, String str2) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withEntityIdType(entityIdType).withEntityId(str).withEntityType(entityType).withRefMarker(str2).build());
    }
}
