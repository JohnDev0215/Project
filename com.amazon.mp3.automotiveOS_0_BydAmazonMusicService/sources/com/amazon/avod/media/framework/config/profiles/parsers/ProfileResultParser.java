package com.amazon.avod.media.framework.config.profiles.parsers;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ProfileResultParser<T> {
    T parse(@Nonnull String str);
}
