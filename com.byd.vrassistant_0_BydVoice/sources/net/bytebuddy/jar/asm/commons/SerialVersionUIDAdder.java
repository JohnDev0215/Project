package net.bytebuddy.jar.asm.commons;

import com.android.multidex.ClassPathElement;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class SerialVersionUIDAdder extends ClassVisitor {
    private int access;
    private boolean computeSVUID;
    private boolean hasSVUID;
    private boolean hasStaticInitializer;
    private String[] interfaces;
    private String name;
    private Collection<Item> svuidConstructors;
    private Collection<Item> svuidFields;
    private Collection<Item> svuidMethods;

    public SerialVersionUIDAdder(ClassVisitor classVisitor) {
        this(Opcodes.ASM6, classVisitor);
        if (getClass() != SerialVersionUIDAdder.class) {
            throw new IllegalStateException();
        }
    }

    protected SerialVersionUIDAdder(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
        this.svuidFields = new ArrayList();
        this.svuidConstructors = new ArrayList();
        this.svuidMethods = new ArrayList();
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        boolean z = (i2 & 16384) == 0;
        this.computeSVUID = z;
        if (z) {
            this.name = str;
            this.access = i2;
            String[] strArr2 = new String[strArr.length];
            this.interfaces = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        }
        super.visit(i, i2, str, str2, str3, strArr);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        if (this.computeSVUID) {
            if (MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(str)) {
                this.hasStaticInitializer = true;
            }
            int i2 = i & 3391;
            if ((i & 2) == 0) {
                if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str)) {
                    this.svuidConstructors.add(new Item(str, i2, str2));
                } else if (!MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(str)) {
                    this.svuidMethods.add(new Item(str, i2, str2));
                }
            }
        }
        return super.visitMethod(i, str, str2, str3, strArr);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        if (this.computeSVUID) {
            if ("serialVersionUID".equals(str)) {
                this.computeSVUID = false;
                this.hasSVUID = true;
            }
            if ((i & 2) == 0 || (i & 136) == 0) {
                this.svuidFields.add(new Item(str, i & 223, str2));
            }
        }
        return super.visitField(i, str, str2, str3, obj);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitInnerClass(String str, String str2, String str3, int i) {
        String str4 = this.name;
        if (str4 != null && str4.equals(str)) {
            this.access = i;
        }
        super.visitInnerClass(str, str2, str3, i);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public void visitEnd() {
        if (this.computeSVUID && !this.hasSVUID) {
            try {
                addSVUID(computeSVUID());
            } catch (Throwable th) {
                throw new RuntimeException("Error while computing SVUID for " + this.name, th);
            }
        }
        super.visitEnd();
    }

    public boolean hasSVUID() {
        return this.hasSVUID;
    }

    protected void addSVUID(long j) {
        FieldVisitor fieldVisitorVisitField = super.visitField(24, "serialVersionUID", "J", null, Long.valueOf(j));
        if (fieldVisitorVisitField != null) {
            fieldVisitorVisitField.visitEnd();
        }
    }

    protected long computeSVUID() throws Throwable {
        DataOutputStream dataOutputStream;
        Throwable th;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeUTF(this.name.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
                int i = this.access;
                if ((i & 512) != 0) {
                    i = this.svuidMethods.size() > 0 ? i | 1024 : i & (-1025);
                }
                dataOutputStream.writeInt(i & 1553);
                Arrays.sort(this.interfaces);
                int i2 = 0;
                while (true) {
                    String[] strArr = this.interfaces;
                    if (i2 >= strArr.length) {
                        break;
                    }
                    dataOutputStream.writeUTF(strArr[i2].replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH));
                    i2++;
                }
                writeItems(this.svuidFields, dataOutputStream, false);
                if (this.hasStaticInitializer) {
                    dataOutputStream.writeUTF(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME);
                    dataOutputStream.writeInt(8);
                    dataOutputStream.writeUTF("()V");
                }
                writeItems(this.svuidConstructors, dataOutputStream, true);
                writeItems(this.svuidMethods, dataOutputStream, true);
                dataOutputStream.flush();
                byte[] bArrComputeSHAdigest = computeSHAdigest(byteArrayOutputStream.toByteArray());
                long j = 0;
                for (int iMin = Math.min(bArrComputeSHAdigest.length, 8) - 1; iMin >= 0; iMin--) {
                    j = (j << 8) | ((long) (bArrComputeSHAdigest[iMin] & 255));
                }
                dataOutputStream.close();
                return j;
            } catch (Throwable th2) {
                th = th2;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            dataOutputStream = null;
            th = th3;
        }
    }

    protected byte[] computeSHAdigest(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA").digest(bArr);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e.toString());
        }
    }

    private static void writeItems(Collection<Item> collection, DataOutput dataOutput, boolean z) throws IOException {
        int size = collection.size();
        Item[] itemArr = (Item[]) collection.toArray(new Item[size]);
        Arrays.sort(itemArr);
        for (int i = 0; i < size; i++) {
            dataOutput.writeUTF(itemArr[i].name);
            dataOutput.writeInt(itemArr[i].access);
            dataOutput.writeUTF(z ? itemArr[i].desc.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH) : itemArr[i].desc);
        }
    }

    private static class Item implements Comparable<Item> {
        final int access;
        final String desc;
        final String name;

        Item(String str, int i, String str2) {
            this.name = str;
            this.access = i;
            this.desc = str2;
        }

        @Override // java.lang.Comparable
        public int compareTo(Item item) {
            int iCompareTo = this.name.compareTo(item.name);
            return iCompareTo == 0 ? this.desc.compareTo(item.desc) : iCompareTo;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Item) && compareTo((Item) obj) == 0;
        }

        public int hashCode() {
            return (this.name + this.desc).hashCode();
        }
    }
}
