package com.android.common.contacts;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DataUsageStatUpdater {
    private static final String TAG = DataUsageStatUpdater.class.getSimpleName();

    public static final class DataUsageFeedback {
        static final Uri FEEDBACK_URI = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, "usagefeedback");
        static final String USAGE_TYPE = "type";
        public static final String USAGE_TYPE_CALL = "call";
        public static final String USAGE_TYPE_LONG_TEXT = "long_text";
        public static final String USAGE_TYPE_SHORT_TEXT = "short_text";
    }

    public DataUsageStatUpdater(Context context) {
    }

    public boolean updateWithRfc822Address(Collection<CharSequence> texts) {
        return false;
    }

    public boolean updateWithAddress(Collection<String> addresses) {
        return false;
    }

    public boolean updateWithPhoneNumber(Collection<String> numbers) {
        return false;
    }
}
