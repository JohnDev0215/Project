package com.amazon.avod.playback.renderer.visualon;

import com.amazon.avod.media.framework.error.ErrorEnumUtils;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.error.MediaInternalErrorCode;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VisualOnRendererException extends MediaException {
    private static final long serialVersionUID = 1;

    public VisualOnRendererException(String str, int i) {
        super(fromErrorCode(i, VisualOnRendererError.getErrorMap(), LicenseError.getErrorMap()), str, null);
    }

    private static MediaInternalErrorCode fromErrorCode(int i, @Nonnull Map<Integer, VisualOnRendererError> map, @Nonnull Map<Integer, LicenseError> map2) {
        Preconditions.checkNotNull(map2);
        Preconditions.checkNotNull(map);
        if (map2.containsKey(Integer.valueOf(i))) {
            return map2.get(Integer.valueOf(i));
        }
        if (map.containsKey(Integer.valueOf(i))) {
            return (MediaInternalErrorCode) ErrorEnumUtils.fromErrorCode(i, map);
        }
        return VisualOnRendererError.UNKNOWN;
    }
}
