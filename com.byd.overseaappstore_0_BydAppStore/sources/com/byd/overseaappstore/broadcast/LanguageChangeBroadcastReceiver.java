package com.byd.overseaappstore.broadcast;

import android.content.Context;
import android.content.Intent;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.d;
import spi.kg;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/broadcast/LanguageChangeBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class LanguageChangeBroadcastReceiver extends Hilt_LanguageChangeBroadcastReceiver {
    public LanguageChangeStateMgr c;

    @Override // com.byd.overseaappstore.broadcast.Hilt_LanguageChangeBroadcastReceiver, android.content.BroadcastReceiver
    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        super.onReceive(context, intent);
        LanguageChangeStateMgr languageChangeStateMgr = null;
        String action = intent != null ? intent.getAction() : null;
        if (intent == null) {
            ct.b("broadcast languageChange intent is null", new Object[0]);
            return;
        }
        if (action == null || action.length() == 0) {
            ct.b("broadcast languageChange action.isNullOrEmpty", new Object[0]);
            return;
        }
        if (!Intrinsics.areEqual(action, "android.intent.action.LOCALE_CHANGED")) {
            ct.a(d.f("broadcast languageChange unknown action no intervene action:", action), new Object[0]);
            return;
        }
        String strE = kg.a.e();
        ct.a(d.g("broadcast languageChange changed:", strE, " action:", action), new Object[0]);
        LanguageChangeStateMgr languageChangeStateMgr2 = this.c;
        if (languageChangeStateMgr2 != null) {
            languageChangeStateMgr = languageChangeStateMgr2;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("languageChangeStateMgr");
        }
        languageChangeStateMgr.notifyNetContextStateData(strE);
    }
}
