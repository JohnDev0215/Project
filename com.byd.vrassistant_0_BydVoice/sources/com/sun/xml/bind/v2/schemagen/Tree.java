package com.sun.xml.bind.v2.schemagen;

import com.sun.xml.bind.v2.schemagen.xmlschema.ContentModelContainer;
import com.sun.xml.bind.v2.schemagen.xmlschema.Occurs;
import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;
import com.sun.xml.bind.v2.schemagen.xmlschema.TypeDefParticle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
abstract class Tree {
    boolean canBeTopLevel() {
        return false;
    }

    abstract boolean isNullable();

    protected abstract void write(ContentModelContainer contentModelContainer, boolean z, boolean z2);

    Tree() {
    }

    Tree makeOptional(boolean z) {
        return z ? new Optional() : this;
    }

    Tree makeRepeated(boolean z) {
        return z ? new Repeated() : this;
    }

    static Tree makeGroup(GroupKind groupKind, List<Tree> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Tree tree : list) {
            if (tree instanceof Group) {
                Group group = (Group) tree;
                if (group.kind == groupKind) {
                    arrayList.addAll(Arrays.asList(group.children));
                }
            }
            arrayList.add(tree);
        }
        return new Group(groupKind, (Tree[]) arrayList.toArray(new Tree[arrayList.size()]));
    }

    protected void write(TypeDefParticle typeDefParticle) {
        if (canBeTopLevel()) {
            write((ContentModelContainer) typeDefParticle._cast(ContentModelContainer.class), false, false);
        } else {
            new Group(GroupKind.SEQUENCE, new Tree[]{this}).write(typeDefParticle);
        }
    }

    protected final void writeOccurs(Occurs occurs, boolean z, boolean z2) {
        if (z) {
            occurs.minOccurs(0);
        }
        if (z2) {
            occurs.maxOccurs("unbounded");
        }
    }

    static abstract class Term extends Tree {
        @Override // com.sun.xml.bind.v2.schemagen.Tree
        boolean isNullable() {
            return false;
        }

        Term() {
        }
    }

    private static final class Optional extends Tree {
        private final Tree body;

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        boolean isNullable() {
            return true;
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        Tree makeOptional(boolean z) {
            return this;
        }

        private Optional(Tree tree) {
            this.body = tree;
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
            this.body.write(contentModelContainer, true, z2);
        }
    }

    private static final class Repeated extends Tree {
        private final Tree body;

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        Tree makeRepeated(boolean z) {
            return this;
        }

        private Repeated(Tree tree) {
            this.body = tree;
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        boolean isNullable() {
            return this.body.isNullable();
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
            this.body.write(contentModelContainer, z, true);
        }
    }

    private static final class Group extends Tree {
        private final Tree[] children;
        private final GroupKind kind;

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        boolean canBeTopLevel() {
            return true;
        }

        private Group(GroupKind groupKind, Tree... treeArr) {
            this.kind = groupKind;
            this.children = treeArr;
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        boolean isNullable() {
            if (this.kind == GroupKind.CHOICE) {
                for (Tree tree : this.children) {
                    if (tree.isNullable()) {
                        return true;
                    }
                }
                return false;
            }
            for (Tree tree2 : this.children) {
                if (!tree2.isNullable()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.sun.xml.bind.v2.schemagen.Tree
        protected void write(ContentModelContainer contentModelContainer, boolean z, boolean z2) {
            Particle particleWrite = this.kind.write(contentModelContainer);
            writeOccurs(particleWrite, z, z2);
            for (Tree tree : this.children) {
                tree.write(particleWrite, false, false);
            }
        }
    }
}
