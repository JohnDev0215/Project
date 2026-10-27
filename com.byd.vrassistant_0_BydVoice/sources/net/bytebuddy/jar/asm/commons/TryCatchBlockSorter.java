package net.bytebuddy.jar.asm.commons;

import java.util.Collections;
import java.util.Comparator;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.tree.MethodNode;
import net.bytebuddy.jar.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: classes3.dex */
public class TryCatchBlockSorter extends MethodNode {
    public TryCatchBlockSorter(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr) {
        this(Opcodes.ASM6, methodVisitor, i, str, str2, str3, strArr);
    }

    protected TryCatchBlockSorter(int i, MethodVisitor methodVisitor, int i2, String str, String str2, String str3, String[] strArr) {
        super(i, i2, str, str2, str3, strArr);
        this.mv = methodVisitor;
    }

    public void visitEnd() {
        Collections.sort(this.tryCatchBlocks, new Comparator<TryCatchBlockNode>() { // from class: net.bytebuddy.jar.asm.commons.TryCatchBlockSorter.1
            @Override // java.util.Comparator
            public int compare(TryCatchBlockNode tryCatchBlockNode, TryCatchBlockNode tryCatchBlockNode2) {
                return blockLength(tryCatchBlockNode) - blockLength(tryCatchBlockNode2);
            }

            private int blockLength(TryCatchBlockNode tryCatchBlockNode) {
                return TryCatchBlockSorter.this.instructions.indexOf(tryCatchBlockNode.end) - TryCatchBlockSorter.this.instructions.indexOf(tryCatchBlockNode.start);
            }
        });
        for (int i = 0; i < this.tryCatchBlocks.size(); i++) {
            ((TryCatchBlockNode) this.tryCatchBlocks.get(i)).updateIndex(i);
        }
        if (this.mv != null) {
            accept(this.mv);
        }
    }
}
