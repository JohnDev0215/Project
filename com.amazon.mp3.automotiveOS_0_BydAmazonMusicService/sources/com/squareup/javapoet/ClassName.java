package com.squareup.javapoet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.SimpleElementVisitor8;

/* JADX INFO: loaded from: classes7.dex */
public final class ClassName extends TypeName implements Comparable<ClassName> {
    public static final ClassName OBJECT = get((Class<?>) Object.class);
    final String canonicalName;
    final ClassName enclosingClassName;
    final String packageName;
    final String simpleName;
    private List<String> simpleNames;

    private ClassName(String str, ClassName className, String str2) {
        this(str, className, str2, Collections.emptyList());
    }

    private ClassName(String str, ClassName className, String str2, List<AnnotationSpec> list) {
        super(list);
        Objects.requireNonNull(str, "packageName == null");
        this.packageName = str;
        this.enclosingClassName = className;
        this.simpleName = str2;
        if (className != null) {
            str2 = className.canonicalName + '.' + str2;
        } else if (!str.isEmpty()) {
            str2 = str + '.' + str2;
        }
        this.canonicalName = str2;
    }

    public ClassName annotated(List<AnnotationSpec> list) {
        return new ClassName(this.packageName, this.enclosingClassName, this.simpleName, concatAnnotations(list));
    }

    @Override // com.squareup.javapoet.TypeName
    public boolean isAnnotated() {
        ClassName className;
        return super.isAnnotated() || ((className = this.enclosingClassName) != null && className.isAnnotated());
    }

    public String packageName() {
        return this.packageName;
    }

    public ClassName enclosingClassName() {
        return this.enclosingClassName;
    }

    public ClassName topLevelClassName() {
        ClassName className = this.enclosingClassName;
        return className != null ? className.topLevelClassName() : this;
    }

    public List<String> simpleNames() {
        List<String> list = this.simpleNames;
        if (list != null) {
            return list;
        }
        if (this.enclosingClassName == null) {
            this.simpleNames = Collections.singletonList(this.simpleName);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(enclosingClassName().simpleNames());
            arrayList.add(this.simpleName);
            this.simpleNames = Collections.unmodifiableList(arrayList);
        }
        return this.simpleNames;
    }

    public ClassName nestedClass(String str) {
        return new ClassName(this.packageName, this, str);
    }

    public String simpleName() {
        return this.simpleName;
    }

    public String canonicalName() {
        return this.canonicalName;
    }

    public static ClassName get(Class<?> cls) {
        Util.checkNotNull(cls, "clazz == null", new Object[0]);
        Util.checkArgument(!cls.isPrimitive(), "primitive types cannot be represented as a ClassName", new Object[0]);
        Util.checkArgument(!Void.TYPE.equals(cls), "'void' type cannot be represented as a ClassName", new Object[0]);
        Util.checkArgument(!cls.isArray(), "array types cannot be represented as a ClassName", new Object[0]);
        String str = "";
        while (cls.isAnonymousClass()) {
            str = cls.getName().substring(cls.getName().lastIndexOf(36)) + str;
            cls = cls.getEnclosingClass();
        }
        String str2 = cls.getSimpleName() + str;
        if (cls.getEnclosingClass() == null) {
            int iLastIndexOf = cls.getName().lastIndexOf(46);
            return new ClassName(iLastIndexOf != -1 ? cls.getName().substring(0, iLastIndexOf) : "", null, str2);
        }
        return get(cls.getEnclosingClass()).nestedClass(str2);
    }

    public static ClassName get(String str, String str2, String... strArr) {
        ClassName className = new ClassName(str, null, str2);
        for (String str3 : strArr) {
            className = className.nestedClass(str3);
        }
        return className;
    }

    public static ClassName get(final TypeElement typeElement) {
        Util.checkNotNull(typeElement, "element == null", new Object[0]);
        final String string = typeElement.getSimpleName().toString();
        return (ClassName) typeElement.getEnclosingElement().accept(new SimpleElementVisitor8<ClassName, Void>() { // from class: com.squareup.javapoet.ClassName.1
        }, (Object) null);
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassName className) {
        return this.canonicalName.compareTo(className.canonicalName);
    }

    @Override // com.squareup.javapoet.TypeName
    CodeWriter emit(CodeWriter codeWriter) throws IOException {
        String strLookupName;
        boolean z = false;
        for (ClassName className : enclosingClasses()) {
            if (z) {
                codeWriter.emit(".");
                strLookupName = className.simpleName;
            } else if (className.isAnnotated() || className == this) {
                strLookupName = codeWriter.lookupName(className);
                int iLastIndexOf = strLookupName.lastIndexOf(46);
                if (iLastIndexOf != -1) {
                    int i = iLastIndexOf + 1;
                    codeWriter.emitAndIndent(strLookupName.substring(0, i));
                    strLookupName = strLookupName.substring(i);
                    z = true;
                }
            }
            if (className.isAnnotated()) {
                if (z) {
                    codeWriter.emit(" ");
                }
                className.emitAnnotations(codeWriter);
            }
            codeWriter.emit(strLookupName);
            z = true;
        }
        return codeWriter;
    }

    private List<ClassName> enclosingClasses() {
        ArrayList arrayList = new ArrayList();
        for (ClassName className = this; className != null; className = className.enclosingClassName) {
            arrayList.add(className);
        }
        Collections.reverse(arrayList);
        return arrayList;
    }
}
