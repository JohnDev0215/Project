package com.amazon.avod.content;

import com.amazon.avod.fileio.DiskUtils;
import com.google.common.base.Optional;
import java.io.File;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentStore {
    void deleteAllContent(ContentSessionType contentSessionType, File file);

    void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str);

    void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str, Optional<DiskUtils.DeletionProgressListener> optional);
}
