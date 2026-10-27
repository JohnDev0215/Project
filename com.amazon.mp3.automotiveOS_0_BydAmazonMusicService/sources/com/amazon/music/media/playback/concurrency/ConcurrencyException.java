package com.amazon.music.media.playback.concurrency;

import androidx.annotation.NonNull;
import com.amazon.music.media.playback.PlaybackException;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class ConcurrencyException extends PlaybackException {
    private boolean canResolveWithoutInteraction;
    private final List<ConcurrentDeviceInfo> devices;

    public ConcurrencyException(@NonNull List<ConcurrentDeviceInfo> list, boolean z) {
        super(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).description("The user is playing on too many devices. Have the user pick a device and call PlaybackController.resolveMaxConcurrency()."));
        this.devices = (List) Validate.notEmpty(list, "devices cannot be null or empty", new Object[0]);
        this.canResolveWithoutInteraction = z;
    }

    public List<ConcurrentDeviceInfo> getDevices() {
        return this.devices;
    }
}
