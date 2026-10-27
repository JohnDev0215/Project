package com.github.promeg.pinyinhelper;

import java.util.Collection;
import java.util.List;
import org.ahocorasick.trie.Emit;

/* JADX INFO: loaded from: classes2.dex */
interface SegmentationSelector {
    List<Emit> select(Collection<Emit> collection);
}
