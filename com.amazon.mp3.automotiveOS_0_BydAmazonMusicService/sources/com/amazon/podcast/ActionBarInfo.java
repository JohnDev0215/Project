package com.amazon.podcast;

import androidx.appcompat.app.ActionBar;
import com.amazon.podcast.bootstrap.ActionBarListener;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: compiled from: ActionBarInfo.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016R.\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR&\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000fj\b\u0012\u0004\u0012\u00020\u0004`\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/podcast/ActionBarInfo;", "Lcom/amazon/podcast/bootstrap/ActionBarProvider;", "Landroidx/appcompat/app/ActionBar;", "actionBar", "Lcom/amazon/podcast/bootstrap/ActionBarListener;", "actionBarListener", "", "addActionBarListener", "removeActionBarListener", "value", "Landroidx/appcompat/app/ActionBar;", "getActionBar", "()Landroidx/appcompat/app/ActionBar;", "setActionBar", "(Landroidx/appcompat/app/ActionBar;)V", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "listeners", "Ljava/util/HashSet;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ActionBarInfo extends ActionBarProvider {
    private static ActionBar actionBar;
    public static final ActionBarInfo INSTANCE = new ActionBarInfo();
    private static HashSet<ActionBarListener> listeners = new HashSet<>();

    private ActionBarInfo() {
    }

    public final void setActionBar(ActionBar actionBar2) {
        actionBar = actionBar2;
        if (actionBar2 != null) {
            Iterator<T> it = listeners.iterator();
            while (it.hasNext()) {
                ((ActionBarListener) it.next()).onActionBarUpdated(actionBar2);
            }
        }
    }

    @Override // com.amazon.podcast.bootstrap.ActionBarProvider
    public ActionBar actionBar() {
        return actionBar;
    }

    @Override // com.amazon.podcast.bootstrap.ActionBarProvider
    public void addActionBarListener(ActionBarListener actionBarListener) {
        if (actionBarListener == null) {
            return;
        }
        listeners.add(actionBarListener);
    }

    @Override // com.amazon.podcast.bootstrap.ActionBarProvider
    public void removeActionBarListener(ActionBarListener actionBarListener) {
        listeners.remove(actionBarListener);
    }
}
