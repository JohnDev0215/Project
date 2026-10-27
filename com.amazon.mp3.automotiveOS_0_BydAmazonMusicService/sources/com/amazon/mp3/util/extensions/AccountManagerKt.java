package com.amazon.mp3.util.extensions;

import com.amazon.music.account.AccountManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"userResult", "Lkotlin/Result;", "Lcom/amazon/music/account/User;", "Lcom/amazon/music/account/AccountManager;", "(Lcom/amazon/music/account/AccountManager;)Ljava/lang/Object;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class AccountManagerKt {
    public static final Object userResult(AccountManager accountManager) {
        Intrinsics.checkNotNullParameter(accountManager, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            return Result.m2810constructorimpl(accountManager.getUser());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
    }
}
