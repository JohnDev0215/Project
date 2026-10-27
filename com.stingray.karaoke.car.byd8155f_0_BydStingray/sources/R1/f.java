package R1;

import androidx.datastore.preferences.protobuf.AbstractC0939b;
import androidx.datastore.preferences.protobuf.AbstractC0955s;
import androidx.datastore.preferences.protobuf.AbstractC0957u;
import androidx.datastore.preferences.protobuf.AbstractC0959w;
import androidx.datastore.preferences.protobuf.B;
import androidx.datastore.preferences.protobuf.C0944g;
import androidx.datastore.preferences.protobuf.C0956t;
import androidx.datastore.preferences.protobuf.InterfaceC0958v;
import androidx.datastore.preferences.protobuf.T;
import androidx.datastore.preferences.protobuf.U;
import androidx.datastore.preferences.protobuf.W;
import androidx.datastore.preferences.protobuf.X;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC0957u {
    private static final f DEFAULT_INSTANCE;
    private static volatile T PARSER = null;
    public static final int STRINGS_FIELD_NUMBER = 1;
    private InterfaceC0958v strings_ = W.o;

    static {
        f fVar = new f();
        DEFAULT_INSTANCE = fVar;
        AbstractC0957u.l(f.class, fVar);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void n(f fVar, Iterable iterable) {
        InterfaceC0958v interfaceC0958v = fVar.strings_;
        if (!((AbstractC0939b) interfaceC0958v).l) {
            int size = interfaceC0958v.size();
            fVar.strings_ = interfaceC0958v.h(size == 0 ? 10 : size * 2);
        }
        List list = fVar.strings_;
        Charset charset = AbstractC0959w.f11890a;
        iterable.getClass();
        if (iterable instanceof B) {
            List listC = ((B) iterable).c();
            B b10 = (B) list;
            int size2 = list.size();
            for (Object obj : listC) {
                if (obj == null) {
                    String str = "Element at index " + (b10.size() - size2) + " is null.";
                    for (int size3 = b10.size() - 1; size3 >= size2; size3--) {
                        b10.remove(size3);
                    }
                    throw new NullPointerException(str);
                }
                if (obj instanceof C0944g) {
                    b10.j((C0944g) obj);
                } else {
                    b10.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof U) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size4 = list.size();
        for (Object obj2 : iterable) {
            if (obj2 == null) {
                String str2 = "Element at index " + (list.size() - size4) + " is null.";
                for (int size5 = list.size() - 1; size5 >= size4; size5--) {
                    list.remove(size5);
                }
                throw new NullPointerException(str2);
            }
            list.add(obj2);
        }
    }

    public static f o() {
        return DEFAULT_INSTANCE;
    }

    public static e q() {
        return (e) ((AbstractC0955s) DEFAULT_INSTANCE.e(5));
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0957u
    public final Object e(int i7) {
        switch (AbstractC2641j.c(i7)) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new X(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"strings_"});
            case 3:
                return new f();
            case 4:
                return new e(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                T c0956t = PARSER;
                if (c0956t == null) {
                    synchronized (f.class) {
                        try {
                            c0956t = PARSER;
                            if (c0956t == null) {
                                c0956t = new C0956t();
                                PARSER = c0956t;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return c0956t;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final InterfaceC0958v p() {
        return this.strings_;
    }
}
