package org.chromium.content.browser;

import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.MessagePayload;

/* JADX INFO: loaded from: classes3.dex */
public final class MessagePayloadJni {
    @CalledByNative
    public static MessagePayload createFromArrayBuffer(byte[] bArr) {
        return new MessagePayload(bArr);
    }

    @CalledByNative
    public static MessagePayload createFromString(String str) {
        return new MessagePayload(str);
    }

    @CalledByNative
    public static byte[] getAsArrayBuffer(MessagePayload messagePayload) {
        messagePayload.a(1);
        byte[] bArr = messagePayload.c;
        Objects.requireNonNull(bArr, "mArrayBuffer cannot be null.");
        return bArr;
    }

    @CalledByNative
    public static String getAsString(MessagePayload messagePayload) {
        messagePayload.a(0);
        return messagePayload.b;
    }

    @CalledByNative
    public static int getType(MessagePayload messagePayload) {
        return messagePayload.a;
    }
}
