package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/* JADX INFO: loaded from: classes3.dex */
final class PrimitiveArrayListerCharacter<BeanT> extends Lister<BeanT, char[], Character, CharacterArrayPack> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ void endPacking(CharacterArrayPack characterArrayPack, Object obj, Accessor accessor) throws AccessorException {
        endPacking2(characterArrayPack, obj, (Accessor<Object, char[]>) accessor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public /* bridge */ /* synthetic */ CharacterArrayPack startPacking(Object obj, Accessor accessor) throws AccessorException {
        return startPacking(obj, (Accessor<Object, char[]>) accessor);
    }

    private PrimitiveArrayListerCharacter() {
    }

    static void register() {
        Lister.primitiveArrayListers.put(Character.TYPE, new PrimitiveArrayListerCharacter());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public ListIterator<Character> iterator(final char[] cArr, XMLSerializer xMLSerializer) {
        return new ListIterator<Character>() { // from class: com.sun.xml.bind.v2.runtime.reflect.PrimitiveArrayListerCharacter.1
            int idx = 0;

            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public boolean hasNext() {
                return this.idx < cArr.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.sun.xml.bind.v2.runtime.reflect.ListIterator
            public Character next() {
                char[] cArr2 = cArr;
                int i = this.idx;
                this.idx = i + 1;
                return Character.valueOf(cArr2[i]);
            }
        };
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public CharacterArrayPack startPacking(BeanT beant, Accessor<BeanT, char[]> accessor) {
        return new CharacterArrayPack();
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void addToPack(CharacterArrayPack characterArrayPack, Character ch) {
        characterArrayPack.add(ch);
    }

    /* JADX INFO: renamed from: endPacking, reason: avoid collision after fix types in other method */
    public void endPacking2(CharacterArrayPack characterArrayPack, BeanT beant, Accessor<BeanT, char[]> accessor) throws AccessorException {
        accessor.set(beant, characterArrayPack.build());
    }

    @Override // com.sun.xml.bind.v2.runtime.reflect.Lister
    public void reset(BeanT beant, Accessor<BeanT, char[]> accessor) throws AccessorException {
        accessor.set(beant, new char[0]);
    }

    static final class CharacterArrayPack {
        char[] buf = new char[16];
        int size;

        CharacterArrayPack() {
        }

        void add(Character ch) {
            char[] cArr = this.buf;
            if (cArr.length == this.size) {
                char[] cArr2 = new char[cArr.length * 2];
                System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                this.buf = cArr2;
            }
            if (ch != null) {
                char[] cArr3 = this.buf;
                int i = this.size;
                this.size = i + 1;
                cArr3[i] = ch.charValue();
            }
        }

        char[] build() {
            char[] cArr = this.buf;
            int length = cArr.length;
            int i = this.size;
            if (length == i) {
                return cArr;
            }
            char[] cArr2 = new char[i];
            System.arraycopy(cArr, 0, cArr2, 0, i);
            return cArr2;
        }
    }
}
