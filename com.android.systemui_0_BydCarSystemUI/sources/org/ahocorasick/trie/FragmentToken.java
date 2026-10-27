package org.ahocorasick.trie;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentToken extends Token {
    @Override // org.ahocorasick.trie.Token
    public Emit getEmit() {
        return null;
    }

    @Override // org.ahocorasick.trie.Token
    public boolean isMatch() {
        return false;
    }

    public FragmentToken(String str) {
        super(str);
    }
}
