package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
class CurrentFrame extends Frame {
    CurrentFrame() {
    }

    @Override // net.bytebuddy.jar.asm.Frame
    void execute(int i, int i2, ClassWriter classWriter, Item item) {
        super.execute(i, i2, classWriter, item);
        Frame frame = new Frame();
        merge(classWriter, frame, 0);
        set(frame);
        this.owner.inputStackTop = 0;
    }
}
