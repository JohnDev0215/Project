package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;

/* JADX INFO: loaded from: classes2.dex */
public interface ExceptionCallback {
    void call(MediaException mediaException);
}
