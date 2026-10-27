package org.ahocorasick.trie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import org.ahocorasick.interval.IntervalTree;
import org.ahocorasick.trie.handler.DefaultEmitHandler;
import org.ahocorasick.trie.handler.EmitHandler;

/* JADX INFO: loaded from: classes2.dex */
public class Trie {
    private State rootState;
    private TrieConfig trieConfig;

    private Trie(TrieConfig trieConfig) {
        this.trieConfig = trieConfig;
        this.rootState = new State();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addKeyword(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        State stateAddState = this.rootState;
        for (char c : str.toCharArray()) {
            Character chValueOf = Character.valueOf(c);
            if (this.trieConfig.isCaseInsensitive()) {
                chValueOf = Character.valueOf(Character.toLowerCase(chValueOf.charValue()));
            }
            stateAddState = stateAddState.addState(chValueOf);
        }
        if (this.trieConfig.isCaseInsensitive()) {
            str = str.toLowerCase();
        }
        stateAddState.addEmit(str);
    }

    public Collection<Token> tokenize(String str) {
        ArrayList arrayList = new ArrayList();
        int end = -1;
        for (Emit emit : parseText(str)) {
            if (emit.getStart() - end > 1) {
                arrayList.add(createFragment(emit, str, end));
            }
            arrayList.add(createMatch(emit, str));
            end = emit.getEnd();
        }
        if (str.length() - end > 1) {
            arrayList.add(createFragment(null, str, end));
        }
        return arrayList;
    }

    private Token createFragment(Emit emit, String str, int i) {
        return new FragmentToken(str.substring(i + 1, emit == null ? str.length() : emit.getStart()));
    }

    private Token createMatch(Emit emit, String str) {
        return new MatchToken(str.substring(emit.getStart(), emit.getEnd() + 1), emit);
    }

    public Collection<Emit> parseText(CharSequence charSequence) {
        DefaultEmitHandler defaultEmitHandler = new DefaultEmitHandler();
        parseText(charSequence, defaultEmitHandler);
        List<Emit> emits = defaultEmitHandler.getEmits();
        if (this.trieConfig.isOnlyWholeWords()) {
            removePartialMatches(charSequence, emits);
        }
        if (this.trieConfig.isOnlyWholeWordsWhiteSpaceSeparated()) {
            removePartialMatchesWhiteSpaceSeparated(charSequence, emits);
        }
        if (!this.trieConfig.isAllowOverlaps()) {
            new IntervalTree(emits).removeOverlaps(emits);
        }
        return emits;
    }

    public boolean containsMatch(CharSequence charSequence) {
        return firstMatch(charSequence) != null;
    }

    public void parseText(CharSequence charSequence, EmitHandler emitHandler) {
        State state = this.rootState;
        for (int i = 0; i < charSequence.length(); i++) {
            Character chValueOf = Character.valueOf(charSequence.charAt(i));
            if (this.trieConfig.isCaseInsensitive()) {
                chValueOf = Character.valueOf(Character.toLowerCase(chValueOf.charValue()));
            }
            state = getState(state, chValueOf);
            if (storeEmits(i, state, emitHandler) && this.trieConfig.isStopOnHit()) {
                return;
            }
        }
    }

    public Emit firstMatch(CharSequence charSequence) {
        if (!this.trieConfig.isAllowOverlaps()) {
            Collection<Emit> text = parseText(charSequence);
            if (text == null || text.isEmpty()) {
                return null;
            }
            return text.iterator().next();
        }
        State state = this.rootState;
        for (int i = 0; i < charSequence.length(); i++) {
            Character chValueOf = Character.valueOf(charSequence.charAt(i));
            if (this.trieConfig.isCaseInsensitive()) {
                chValueOf = Character.valueOf(Character.toLowerCase(chValueOf.charValue()));
            }
            state = getState(state, chValueOf);
            Collection<String> collectionEmit = state.emit();
            if (collectionEmit != null && !collectionEmit.isEmpty()) {
                for (String str : collectionEmit) {
                    Emit emit = new Emit((i - str.length()) + 1, i, str);
                    if (!this.trieConfig.isOnlyWholeWords() || !isPartialMatch(charSequence, emit)) {
                        return emit;
                    }
                }
            }
        }
        return null;
    }

    private boolean isPartialMatch(CharSequence charSequence, Emit emit) {
        if (emit.getStart() == 0 || !Character.isAlphabetic(charSequence.charAt(emit.getStart() - 1))) {
            return emit.getEnd() + 1 != charSequence.length() && Character.isAlphabetic(charSequence.charAt(emit.getEnd() + 1));
        }
        return true;
    }

    private void removePartialMatches(CharSequence charSequence, List<Emit> list) {
        ArrayList arrayList = new ArrayList();
        for (Emit emit : list) {
            if (isPartialMatch(charSequence, emit)) {
                arrayList.add(emit);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            list.remove((Emit) it.next());
        }
    }

    private void removePartialMatchesWhiteSpaceSeparated(CharSequence charSequence, List<Emit> list) {
        long length = charSequence.length();
        ArrayList arrayList = new ArrayList();
        for (Emit emit : list) {
            if ((emit.getStart() != 0 && !Character.isWhitespace(charSequence.charAt(emit.getStart() - 1))) || (emit.getEnd() + 1 != length && !Character.isWhitespace(charSequence.charAt(emit.getEnd() + 1)))) {
                arrayList.add(emit);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            list.remove((Emit) it.next());
        }
    }

    private State getState(State state, Character ch) {
        State stateNextState = state.nextState(ch);
        while (stateNextState == null) {
            state = state.failure();
            stateNextState = state.nextState(ch);
        }
        return stateNextState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void constructFailureStates() {
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        for (State state : this.rootState.getStates()) {
            state.setFailure(this.rootState);
            linkedBlockingDeque.add(state);
        }
        while (!linkedBlockingDeque.isEmpty()) {
            State state2 = (State) linkedBlockingDeque.remove();
            for (Character ch : state2.getTransitions()) {
                State stateNextState = state2.nextState(ch);
                linkedBlockingDeque.add(stateNextState);
                State stateFailure = state2.failure();
                while (stateFailure.nextState(ch) == null) {
                    stateFailure = stateFailure.failure();
                }
                State stateNextState2 = stateFailure.nextState(ch);
                stateNextState.setFailure(stateNextState2);
                stateNextState.addEmit(stateNextState2.emit());
            }
        }
    }

    private boolean storeEmits(int i, State state, EmitHandler emitHandler) {
        Collection<String> collectionEmit = state.emit();
        boolean z = false;
        if (collectionEmit != null && !collectionEmit.isEmpty()) {
            for (String str : collectionEmit) {
                emitHandler.emit(new Emit((i - str.length()) + 1, i, str));
                z = true;
            }
        }
        return z;
    }

    public static TrieBuilder builder() {
        return new TrieBuilder();
    }

    public static class TrieBuilder {
        private Trie trie;
        private TrieConfig trieConfig;

        private TrieBuilder() {
            this.trieConfig = new TrieConfig();
            this.trie = new Trie(this.trieConfig);
        }

        public TrieBuilder caseInsensitive() {
            this.trieConfig.setCaseInsensitive(true);
            return this;
        }

        public TrieBuilder removeOverlaps() {
            this.trieConfig.setAllowOverlaps(false);
            return this;
        }

        public TrieBuilder onlyWholeWords() {
            this.trieConfig.setOnlyWholeWords(true);
            return this;
        }

        public TrieBuilder onlyWholeWordsWhiteSpaceSeparated() {
            this.trieConfig.setOnlyWholeWordsWhiteSpaceSeparated(true);
            return this;
        }

        public TrieBuilder addKeyword(String str) {
            this.trie.addKeyword(str);
            return this;
        }

        public TrieBuilder stopOnHit() {
            this.trie.trieConfig.setStopOnHit(true);
            return this;
        }

        public Trie build() {
            this.trie.constructFailureStates();
            return this.trie;
        }
    }
}
