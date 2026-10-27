package com.amazon.music.find.service;

import android.content.Context;
import com.amazon.music.find.data.database.AppDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/service/FindService;", "", "()V", "TAG", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "clearAppState", "", "context", "Landroid/content/Context;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindService {
    private static final String TAG = "SPL";
    public static final FindService INSTANCE = new FindService();
    private static final Logger logger = LoggerFactory.getLogger(FindService.class.getName());

    private FindService() {
    }

    public final void clearAppState(Context context) {
        if (context == null) {
            return;
        }
        try {
            Logger logger2 = logger;
            logger2.debug("SPL::Clearing FIND module data");
            AppDatabase database = AppDatabase.INSTANCE.getDatabase(context);
            try {
                database.clearAllTables();
                logger2.debug("SPL::Clearing FIND module data completed");
                database.close();
                logger2.debug("SPL::Closing FIND db completed");
            } catch (Throwable th) {
                database.close();
                logger.debug("SPL::Closing FIND db completed");
                throw th;
            }
        } catch (Exception e) {
            logger.debug(Intrinsics.stringPlus("SPL::Unhandled exception occured during clearing FIND module data ", e.getMessage()));
        }
    }
}
