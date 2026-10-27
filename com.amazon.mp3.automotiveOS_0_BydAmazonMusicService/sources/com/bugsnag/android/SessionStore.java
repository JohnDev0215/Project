package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.FileStore;
import com.bugsnag.android.internal.ImmutableConfig;
import java.io.File;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
class SessionStore extends FileStore {
    static final Comparator<File> SESSION_COMPARATOR = new Comparator<File>() { // from class: com.bugsnag.android.SessionStore.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file == null && file2 == null) {
                return 0;
            }
            if (file == null) {
                return 1;
            }
            if (file2 == null) {
                return -1;
            }
            return file.getName().compareTo(file2.getName());
        }
    };

    SessionStore(@NonNull ImmutableConfig immutableConfig, @NonNull Logger logger, @Nullable FileStore.Delegate delegate) {
        super(new File(immutableConfig.getPersistenceDirectory().getValue(), "bugsnag-sessions"), immutableConfig.getMaxPersistedSessions(), SESSION_COMPARATOR, logger, delegate);
    }

    @Override // com.bugsnag.android.FileStore
    @NonNull
    String getFilename(Object obj) {
        return SessionFilenameInfo.defaultFilename();
    }

    public boolean isTooOld(File file) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -60);
        return SessionFilenameInfo.findTimestampInFilename(file) < calendar.getTimeInMillis();
    }

    public Date getCreationDate(File file) {
        return new Date(SessionFilenameInfo.findTimestampInFilename(file));
    }
}
