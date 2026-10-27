package org.apache.shiro.crypto.hash.format;

import org.apache.shiro.crypto.hash.Hash;

/* JADX INFO: loaded from: classes.dex */
public interface ParsableHashFormat extends HashFormat {
    Hash parse(String str);
}
