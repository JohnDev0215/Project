package org.objectweb.asm.tree;

import java.util.Map;
import org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:org/objectweb/asm/tree/MultiANewArrayInsnNode.SCL.lombok */
public class MultiANewArrayInsnNode extends AbstractInsnNode {
    public String desc;
    public int dims;

    public MultiANewArrayInsnNode(String descriptor, int numDimensions) {
        super(197);
        this.desc = descriptor;
        this.dims = numDimensions;
    }

    @Override // org.objectweb.asm.tree.AbstractInsnNode
    public int getType() {
        return 13;
    }

    @Override // org.objectweb.asm.tree.AbstractInsnNode
    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitMultiANewArrayInsn(this.desc, this.dims);
        acceptAnnotations(methodVisitor);
    }

    @Override // org.objectweb.asm.tree.AbstractInsnNode
    public AbstractInsnNode clone(Map<LabelNode, LabelNode> clonedLabels) {
        return new MultiANewArrayInsnNode(this.desc, this.dims).cloneAnnotations(this);
    }
}
