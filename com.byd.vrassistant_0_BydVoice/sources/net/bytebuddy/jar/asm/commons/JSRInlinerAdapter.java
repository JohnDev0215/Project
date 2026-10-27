package net.bytebuddy.jar.asm.commons;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.tree.InsnList;
import net.bytebuddy.jar.asm.tree.InsnNode;
import net.bytebuddy.jar.asm.tree.JumpInsnNode;
import net.bytebuddy.jar.asm.tree.LabelNode;
import net.bytebuddy.jar.asm.tree.LocalVariableNode;
import net.bytebuddy.jar.asm.tree.MethodNode;
import net.bytebuddy.jar.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: classes3.dex */
public class JSRInlinerAdapter extends MethodNode implements Opcodes {
    private static final boolean LOGGING = false;
    final BitSet dualCitizens;
    private final BitSet mainSubroutine;
    private final Map<LabelNode, BitSet> subroutineHeads;

    public JSRInlinerAdapter(MethodVisitor methodVisitor, int i, String str, String str2, String str3, String[] strArr) {
        this(Opcodes.ASM6, methodVisitor, i, str, str2, str3, strArr);
        if (getClass() != JSRInlinerAdapter.class) {
            throw new IllegalStateException();
        }
    }

    protected JSRInlinerAdapter(int i, MethodVisitor methodVisitor, int i2, String str, String str2, String str3, String[] strArr) {
        super(i, i2, str, str2, str3, strArr);
        this.subroutineHeads = new HashMap();
        this.mainSubroutine = new BitSet();
        this.dualCitizens = new BitSet();
        this.mv = methodVisitor;
    }

    public void visitJumpInsn(int i, Label label) {
        super.visitJumpInsn(i, label);
        LabelNode labelNode = this.instructions.getLast().label;
        if (i != 168 || this.subroutineHeads.containsKey(labelNode)) {
            return;
        }
        this.subroutineHeads.put(labelNode, new BitSet());
    }

    public void visitEnd() {
        if (!this.subroutineHeads.isEmpty()) {
            markSubroutines();
            emitCode();
        }
        if (this.mv != null) {
            accept(this.mv);
        }
    }

    private void markSubroutines() {
        BitSet bitSet = new BitSet();
        markSubroutineWalk(this.mainSubroutine, 0, bitSet);
        for (Map.Entry<LabelNode, BitSet> entry : this.subroutineHeads.entrySet()) {
            markSubroutineWalk(entry.getValue(), this.instructions.indexOf(entry.getKey()), bitSet);
        }
    }

    private void markSubroutineWalk(BitSet bitSet, int i, BitSet bitSet2) {
        boolean z;
        markSubroutineWalkDFS(bitSet, i, bitSet2);
        for (boolean z2 = true; z2; z2 = z) {
            z = false;
            for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
                int iIndexOf = this.instructions.indexOf(tryCatchBlockNode.handler);
                if (!bitSet.get(iIndexOf)) {
                    int iIndexOf2 = this.instructions.indexOf(tryCatchBlockNode.start);
                    int iIndexOf3 = this.instructions.indexOf(tryCatchBlockNode.end);
                    int iNextSetBit = bitSet.nextSetBit(iIndexOf2);
                    if (iNextSetBit != -1 && iNextSetBit < iIndexOf3) {
                        markSubroutineWalkDFS(bitSet, iIndexOf, bitSet2);
                        z = true;
                    }
                }
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:104)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    private void markSubroutineWalkDFS(java.util.BitSet r6, int r7, java.util.BitSet r8) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.commons.JSRInlinerAdapter.markSubroutineWalkDFS(java.util.BitSet, int, java.util.BitSet):void");
    }

    private void emitCode() {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Instantiation(null, this.mainSubroutine));
        InsnList insnList = new InsnList();
        List<TryCatchBlockNode> arrayList = new ArrayList<>();
        List<LocalVariableNode> arrayList2 = new ArrayList<>();
        while (!linkedList.isEmpty()) {
            emitSubroutine((Instantiation) linkedList.removeFirst(), linkedList, insnList, arrayList, arrayList2);
        }
        this.instructions = insnList;
        this.tryCatchBlocks = arrayList;
        this.localVariables = arrayList2;
    }

    private void emitSubroutine(Instantiation instantiation, List<Instantiation> list, InsnList insnList, List<TryCatchBlockNode> list2, List<LocalVariableNode> list3) {
        int size = this.instructions.size();
        LabelNode labelNode = null;
        for (int i = 0; i < size; i++) {
            JumpInsnNode jumpInsnNode = this.instructions.get(i);
            Instantiation instantiationFindOwner = instantiation.findOwner(i);
            if (jumpInsnNode.getType() == 8) {
                LabelNode labelNodeRangeLabel = instantiation.rangeLabel((LabelNode) jumpInsnNode);
                if (labelNodeRangeLabel != labelNode) {
                    insnList.add(labelNodeRangeLabel);
                    labelNode = labelNodeRangeLabel;
                }
            } else if (instantiationFindOwner != instantiation) {
                continue;
            } else if (jumpInsnNode.getOpcode() == 169) {
                LabelNode labelNode2 = null;
                for (Instantiation instantiation2 = instantiation; instantiation2 != null; instantiation2 = instantiation2.previous) {
                    if (instantiation2.subroutine.get(i)) {
                        labelNode2 = instantiation2.returnLabel;
                    }
                }
                if (labelNode2 == null) {
                    throw new RuntimeException("Instruction #" + i + " is a RET not owned by any subroutine");
                }
                insnList.add(new JumpInsnNode(167, labelNode2));
            } else if (jumpInsnNode.getOpcode() == 168) {
                LabelNode labelNode3 = jumpInsnNode.label;
                Instantiation instantiation3 = new Instantiation(instantiation, this.subroutineHeads.get(labelNode3));
                LabelNode labelNodeGotoLabel = instantiation3.gotoLabel(labelNode3);
                insnList.add(new InsnNode(1));
                insnList.add(new JumpInsnNode(167, labelNodeGotoLabel));
                insnList.add(instantiation3.returnLabel);
                list.add(instantiation3);
            } else {
                insnList.add(jumpInsnNode.clone(instantiation));
            }
        }
        for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
            LabelNode labelNodeRangeLabel2 = instantiation.rangeLabel(tryCatchBlockNode.start);
            LabelNode labelNodeRangeLabel3 = instantiation.rangeLabel(tryCatchBlockNode.end);
            if (labelNodeRangeLabel2 != labelNodeRangeLabel3) {
                LabelNode labelNodeGotoLabel2 = instantiation.gotoLabel(tryCatchBlockNode.handler);
                if (labelNodeRangeLabel2 == null || labelNodeRangeLabel3 == null || labelNodeGotoLabel2 == null) {
                    throw new RuntimeException("Internal error!");
                }
                list2.add(new TryCatchBlockNode(labelNodeRangeLabel2, labelNodeRangeLabel3, labelNodeGotoLabel2, tryCatchBlockNode.type));
            }
        }
        for (LocalVariableNode localVariableNode : this.localVariables) {
            LabelNode labelNodeRangeLabel4 = instantiation.rangeLabel(localVariableNode.start);
            LabelNode labelNodeRangeLabel5 = instantiation.rangeLabel(localVariableNode.end);
            if (labelNodeRangeLabel4 != labelNodeRangeLabel5) {
                list3.add(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, labelNodeRangeLabel4, labelNodeRangeLabel5, localVariableNode.index));
            }
        }
    }

    private static void log(String str) {
        System.err.println(str);
    }

    private class Instantiation extends AbstractMap<LabelNode, LabelNode> {
        final Instantiation previous;
        public final Map<LabelNode, LabelNode> rangeTable = new HashMap();
        public final LabelNode returnLabel;
        public final BitSet subroutine;

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<LabelNode, LabelNode>> entrySet() {
            return null;
        }

        Instantiation(Instantiation instantiation, BitSet bitSet) {
            this.previous = instantiation;
            this.subroutine = bitSet;
            for (Instantiation instantiation2 = instantiation; instantiation2 != null; instantiation2 = instantiation2.previous) {
                if (instantiation2.subroutine == bitSet) {
                    throw new RuntimeException("Recursive invocation of " + bitSet);
                }
            }
            if (instantiation != null) {
                this.returnLabel = new LabelNode();
            } else {
                this.returnLabel = null;
            }
            int size = JSRInlinerAdapter.this.instructions.size();
            LabelNode labelNode = null;
            for (int i = 0; i < size; i++) {
                LabelNode labelNode2 = JSRInlinerAdapter.this.instructions.get(i);
                if (labelNode2.getType() == 8) {
                    LabelNode labelNode3 = labelNode2;
                    labelNode = labelNode == null ? new LabelNode() : labelNode;
                    this.rangeTable.put(labelNode3, labelNode);
                } else if (findOwner(i) == this) {
                    labelNode = null;
                }
            }
        }

        public Instantiation findOwner(int i) {
            if (!this.subroutine.get(i)) {
                return null;
            }
            if (!JSRInlinerAdapter.this.dualCitizens.get(i)) {
                return this;
            }
            Instantiation instantiation = this;
            for (Instantiation instantiation2 = this.previous; instantiation2 != null; instantiation2 = instantiation2.previous) {
                if (instantiation2.subroutine.get(i)) {
                    instantiation = instantiation2;
                }
            }
            return instantiation;
        }

        public LabelNode gotoLabel(LabelNode labelNode) {
            return findOwner(JSRInlinerAdapter.this.instructions.indexOf(labelNode)).rangeTable.get(labelNode);
        }

        public LabelNode rangeLabel(LabelNode labelNode) {
            return this.rangeTable.get(labelNode);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public LabelNode get(Object obj) {
            return gotoLabel((LabelNode) obj);
        }
    }
}
