package com.android.textclassifier.notification;

import android.app.Notification;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SmartSuggestions {
    private final ImmutableList<Notification.Action> actions;
    private final ImmutableList<CharSequence> replies;

    public SmartSuggestions(List<CharSequence> list, List<Notification.Action> list2) {
        this.replies = ImmutableList.copyOf((Collection) list);
        this.actions = ImmutableList.copyOf((Collection) list2);
    }

    public ImmutableList<CharSequence> getReplies() {
        return this.replies;
    }

    public ImmutableList<Notification.Action> getActions() {
        return this.actions;
    }
}
