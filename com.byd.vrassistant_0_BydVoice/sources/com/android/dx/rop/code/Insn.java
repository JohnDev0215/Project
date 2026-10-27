package com.android.dx.rop.code;

import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.ToHuman;

/* JADX INFO: loaded from: classes.dex */
public abstract class Insn implements ToHuman {
    private final Rop opcode;
    private final SourcePosition position;
    private final RegisterSpec result;
    private final RegisterSpecList sources;

    public static class BaseVisitor implements Visitor {
        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitFillArrayDataInsn(FillArrayDataInsn fillArrayDataInsn) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn plainCstInsn) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn plainInsn) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn switchInsn) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn throwingCstInsn) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn throwingInsn) {
        }
    }

    public interface Visitor {
        void visitFillArrayDataInsn(FillArrayDataInsn fillArrayDataInsn);

        void visitPlainCstInsn(PlainCstInsn plainCstInsn);

        void visitPlainInsn(PlainInsn plainInsn);

        void visitSwitchInsn(SwitchInsn switchInsn);

        void visitThrowingCstInsn(ThrowingCstInsn throwingCstInsn);

        void visitThrowingInsn(ThrowingInsn throwingInsn);
    }

    public abstract void accept(Visitor visitor);

    public final boolean equals(Object obj) {
        return this == obj;
    }

    public abstract TypeList getCatches();

    public String getInlineString() {
        return null;
    }

    public abstract Insn withAddedCatch(Type type);

    public abstract Insn withNewRegisters(RegisterSpec registerSpec, RegisterSpecList registerSpecList);

    public abstract Insn withRegisterOffset(int i);

    public Insn withSourceLiteral() {
        return this;
    }

    public Insn(Rop rop, SourcePosition sourcePosition, RegisterSpec registerSpec, RegisterSpecList registerSpecList) {
        if (rop == null) {
            throw new NullPointerException("opcode == null");
        }
        if (sourcePosition == null) {
            throw new NullPointerException("position == null");
        }
        if (registerSpecList == null) {
            throw new NullPointerException("sources == null");
        }
        this.opcode = rop;
        this.position = sourcePosition;
        this.result = registerSpec;
        this.sources = registerSpecList;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return toStringWithInline(getInlineString());
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toHumanWithInline(getInlineString());
    }

    public final Rop getOpcode() {
        return this.opcode;
    }

    public final SourcePosition getPosition() {
        return this.position;
    }

    public final RegisterSpec getResult() {
        return this.result;
    }

    public final RegisterSpec getLocalAssignment() {
        RegisterSpec registerSpec;
        if (this.opcode.getOpcode() == 54) {
            registerSpec = this.sources.get(0);
        } else {
            registerSpec = this.result;
        }
        if (registerSpec == null || registerSpec.getLocalItem() == null) {
            return null;
        }
        return registerSpec;
    }

    public final RegisterSpecList getSources() {
        return this.sources;
    }

    public final boolean canThrow() {
        return this.opcode.canThrow();
    }

    public Insn copy() {
        return withRegisterOffset(0);
    }

    private static boolean equalsHandleNulls(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public boolean contentEquals(Insn insn) {
        return this.opcode == insn.getOpcode() && this.position.equals(insn.getPosition()) && getClass() == insn.getClass() && equalsHandleNulls(this.result, insn.getResult()) && equalsHandleNulls(this.sources, insn.getSources()) && StdTypeList.equalContents(getCatches(), insn.getCatches());
    }

    protected final String toStringWithInline(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append("Insn{");
        stringBuffer.append(this.position);
        stringBuffer.append(' ');
        stringBuffer.append(this.opcode);
        if (str != null) {
            stringBuffer.append(' ');
            stringBuffer.append(str);
        }
        stringBuffer.append(" :: ");
        RegisterSpec registerSpec = this.result;
        if (registerSpec != null) {
            stringBuffer.append(registerSpec);
            stringBuffer.append(" <- ");
        }
        stringBuffer.append(this.sources);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    protected final String toHumanWithInline(String str) {
        StringBuffer stringBuffer = new StringBuffer(80);
        stringBuffer.append(this.position);
        stringBuffer.append(": ");
        stringBuffer.append(this.opcode.getNickname());
        if (str != null) {
            stringBuffer.append("(");
            stringBuffer.append(str);
            stringBuffer.append(")");
        }
        if (this.result == null) {
            stringBuffer.append(" .");
        } else {
            stringBuffer.append(" ");
            stringBuffer.append(this.result.toHuman());
        }
        stringBuffer.append(" <-");
        int size = this.sources.size();
        if (size == 0) {
            stringBuffer.append(" .");
        } else {
            for (int i = 0; i < size; i++) {
                stringBuffer.append(" ");
                stringBuffer.append(this.sources.get(i).toHuman());
            }
        }
        return stringBuffer.toString();
    }
}
