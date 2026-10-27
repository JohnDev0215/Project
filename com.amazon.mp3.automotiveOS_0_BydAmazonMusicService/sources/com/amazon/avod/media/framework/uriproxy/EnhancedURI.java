package com.amazon.avod.media.framework.uriproxy;

import android.text.TextUtils;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.common.base.Preconditions;
import java.net.URI;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class EnhancedURI {

    @Nonnull
    private final String mIdentifier;

    @Nonnull
    private final URI mURI;

    public EnhancedURI(@Nonnull URI uri, @Nullable String str) {
        this.mURI = (URI) Preconditions.checkNotNull(uri, Contexts.OpenSeeMore.uri);
        if (TextUtils.isEmpty(str)) {
            this.mIdentifier = Integer.toString(uri.hashCode());
        } else {
            this.mIdentifier = str;
        }
    }

    @Nonnull
    public URI getURI() {
        return this.mURI;
    }

    @Nonnull
    public String getIdentifier() {
        return this.mIdentifier;
    }

    @Nonnull
    public String toString() {
        return String.format(Locale.ENGLISH, "%s-%s", this.mURI, this.mIdentifier);
    }
}
