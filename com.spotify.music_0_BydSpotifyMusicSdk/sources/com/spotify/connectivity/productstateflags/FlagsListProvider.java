package com.spotify.connectivity.productstateflags;

import com.spotify.connectivity.flags.Flag;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FlagsListProvider {
    Collection<Flag<?>> getAllFlags();

    List<Flag<?>> getProductStateFlags();
}
