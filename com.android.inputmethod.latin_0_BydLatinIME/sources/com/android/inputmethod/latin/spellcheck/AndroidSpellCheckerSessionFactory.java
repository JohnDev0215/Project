package com.android.inputmethod.latin.spellcheck;

import android.service.textservice.SpellCheckerService;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AndroidSpellCheckerSessionFactory {
    public static SpellCheckerService.Session newInstance(AndroidSpellCheckerService service) {
        return new AndroidSpellCheckerSession(service);
    }
}
