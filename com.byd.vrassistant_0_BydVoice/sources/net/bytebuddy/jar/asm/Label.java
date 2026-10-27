package net.bytebuddy.jar.asm;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;

/* JADX INFO: loaded from: classes3.dex */
public class Label {
    static final int DEBUG = 1;
    static final int JSR = 128;
    static final int PUSHED = 8;
    static final int REACHABLE = 64;
    static final int RESIZED = 4;
    static final int RESOLVED = 2;
    static final int RET = 256;
    static final int STORE = 32;
    static final int SUBROUTINE = 512;
    static final int TARGET = 16;
    static final int VISITED = 1024;
    static final int VISITED2 = 2048;
    Frame frame;
    public Object info;
    int inputStackTop;
    int line;
    Label next;
    int outputStackMax;
    int position;
    private int referenceCount;
    private int[] srcAndRefPositions;
    int status;
    Label successor;
    Edge successors;

    public int getOffset() {
        if ((this.status & 2) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.position;
    }

    void put(MethodWriter methodWriter, ByteVector byteVector, int i, boolean z) {
        if ((this.status & 2) != 0) {
            if (z) {
                byteVector.putInt(this.position - i);
                return;
            } else {
                byteVector.putShort(this.position - i);
                return;
            }
        }
        if (z) {
            addReference((-1) - i, byteVector.length);
            byteVector.putInt(-1);
        } else {
            addReference(i, byteVector.length);
            byteVector.putShort(-1);
        }
    }

    private void addReference(int i, int i2) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        int i3 = this.referenceCount;
        int[] iArr = this.srcAndRefPositions;
        if (i3 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.srcAndRefPositions = iArr2;
        }
        int[] iArr3 = this.srcAndRefPositions;
        int i4 = this.referenceCount;
        int i5 = i4 + 1;
        iArr3[i4] = i;
        this.referenceCount = i5 + 1;
        iArr3[i5] = i2;
    }

    boolean resolve(MethodWriter methodWriter, int i, byte[] bArr) {
        this.status |= 2;
        this.position = i;
        int i2 = 0;
        boolean z = false;
        while (i2 < this.referenceCount) {
            int[] iArr = this.srcAndRefPositions;
            int i3 = i2 + 1;
            int i4 = iArr[i2];
            int i5 = i3 + 1;
            int i6 = iArr[i3];
            if (i4 >= 0) {
                int i7 = i - i4;
                if (i7 < -32768 || i7 > 32767) {
                    int i8 = i6 - 1;
                    int i9 = bArr[i8] & 255;
                    if (i9 <= 168) {
                        bArr[i8] = (byte) (i9 + 49);
                    } else {
                        bArr[i8] = (byte) (i9 + 20);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i7 >>> 8);
                bArr[i6 + 1] = (byte) i7;
            } else {
                int i10 = i4 + i + 1;
                int i11 = i6 + 1;
                bArr[i6] = (byte) (i10 >>> 24);
                int i12 = i11 + 1;
                bArr[i11] = (byte) (i10 >>> 16);
                bArr[i12] = (byte) (i10 >>> 8);
                bArr[i12 + 1] = (byte) i10;
            }
            i2 = i5;
        }
        return z;
    }

    Label getFirst() {
        Frame frame = this.frame;
        return frame == null ? this : frame.owner;
    }

    boolean inSubroutine(long j) {
        if ((this.status & 1024) != 0) {
            return (((int) j) & this.srcAndRefPositions[(int) (j >>> 32)]) != 0;
        }
        return false;
    }

    boolean inSameSubroutine(Label label) {
        if ((this.status & 1024) != 0 && (label.status & 1024) != 0) {
            int i = 0;
            while (true) {
                int[] iArr = this.srcAndRefPositions;
                if (i >= iArr.length) {
                    break;
                }
                if ((iArr[i] & label.srcAndRefPositions[i]) != 0) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    void addToSubroutine(long j, int i) {
        int i2 = this.status;
        if ((i2 & 1024) == 0) {
            this.status = i2 | 1024;
            this.srcAndRefPositions = new int[(i / 32) + 1];
        }
        int[] iArr = this.srcAndRefPositions;
        int i3 = (int) (j >>> 32);
        iArr[i3] = ((int) j) | iArr[i3];
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void visitSubroutine(net.bytebuddy.jar.asm.Label r5, long r6, int r8) {
        /*
            r4 = this;
            r0 = r4
        L1:
            if (r0 == 0) goto L5f
            net.bytebuddy.jar.asm.Label r1 = r0.next
            r2 = 0
            r0.next = r2
            if (r5 == 0) goto L35
            int r2 = r0.status
            r3 = r2 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L11
            goto L3b
        L11:
            r2 = r2 | 2048(0x800, float:2.87E-42)
            r0.status = r2
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L40
            boolean r2 = r0.inSameSubroutine(r5)
            if (r2 != 0) goto L40
            net.bytebuddy.jar.asm.Edge r2 = new net.bytebuddy.jar.asm.Edge
            r2.<init>()
            int r3 = r0.inputStackTop
            r2.info = r3
            net.bytebuddy.jar.asm.Edge r3 = r5.successors
            net.bytebuddy.jar.asm.Label r3 = r3.successor
            r2.successor = r3
            net.bytebuddy.jar.asm.Edge r3 = r0.successors
            r2.next = r3
            r0.successors = r2
            goto L40
        L35:
            boolean r2 = r0.inSubroutine(r6)
            if (r2 == 0) goto L3d
        L3b:
            r0 = r1
            goto L1
        L3d:
            r0.addToSubroutine(r6, r8)
        L40:
            net.bytebuddy.jar.asm.Edge r2 = r0.successors
        L42:
            if (r2 == 0) goto L3b
            int r3 = r0.status
            r3 = r3 & 128(0x80, float:1.8E-43)
            if (r3 == 0) goto L50
            net.bytebuddy.jar.asm.Edge r3 = r0.successors
            net.bytebuddy.jar.asm.Edge r3 = r3.next
            if (r2 == r3) goto L5c
        L50:
            net.bytebuddy.jar.asm.Label r3 = r2.successor
            net.bytebuddy.jar.asm.Label r3 = r3.next
            if (r3 != 0) goto L5c
            net.bytebuddy.jar.asm.Label r3 = r2.successor
            r3.next = r1
            net.bytebuddy.jar.asm.Label r1 = r2.successor
        L5c:
            net.bytebuddy.jar.asm.Edge r2 = r2.next
            goto L42
        L5f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Label.visitSubroutine(net.bytebuddy.jar.asm.Label, long, int):void");
    }

    public String toString() {
        return TemperatureUnitEnum.L + System.identityHashCode(this);
    }
}
