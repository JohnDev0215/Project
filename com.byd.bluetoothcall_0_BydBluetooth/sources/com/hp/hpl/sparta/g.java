package com.hp.hpl.sparta;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Integer f4172a = new Integer(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Integer f4173b = new Integer(2);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Integer f4174c = new Integer(3);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Integer f4175d = new Integer(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Integer f4176e = new Integer(5);
    private static final Integer f = new Integer(6);
    private static final Integer g = new Integer(7);
    private static final Integer h = new Integer(8);
    private static final Integer i = new Integer(9);
    private static final Integer j = new Integer(10);
    private final Vector k = new Vector();
    private Hashtable l = new Hashtable();

    g() {
    }

    private static Integer c(f fVar) {
        return new Integer(System.identityHashCode(fVar));
    }

    void a(f fVar, int i2) {
        Integer num;
        this.k.addElement(fVar);
        switch (i2) {
            case 1:
                num = f4172a;
                break;
            case 2:
                num = f4173b;
                break;
            case 3:
                num = f4174c;
                break;
            case 4:
                num = f4175d;
                break;
            case 5:
                num = f4176e;
                break;
            case 6:
                num = f;
                break;
            case 7:
                num = g;
                break;
            case 8:
                num = h;
                break;
            case 9:
                num = i;
                break;
            case 10:
                num = j;
                break;
            default:
                num = new Integer(i2);
                break;
        }
        this.l.put(c(fVar), num);
    }

    void b(String str) {
        this.k.addElement(str);
    }

    Enumeration d() {
        return this.k.elements();
    }

    int e(f fVar) {
        return ((Integer) this.l.get(c(fVar))).intValue();
    }

    void f() {
        this.k.removeAllElements();
        this.l.clear();
    }

    public String toString() {
        String string;
        try {
            StringBuffer stringBuffer = new StringBuffer("{ ");
            Enumeration enumerationElements = this.k.elements();
            while (enumerationElements.hasMoreElements()) {
                Object objNextElement = enumerationElements.nextElement();
                if (objNextElement instanceof String) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("String(");
                    stringBuffer2.append(objNextElement);
                    stringBuffer2.append(") ");
                    string = stringBuffer2.toString();
                } else {
                    f fVar = (f) objNextElement;
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Node(");
                    stringBuffer3.append(fVar.m());
                    stringBuffer3.append(")[");
                    stringBuffer3.append(this.l.get(c(fVar)));
                    stringBuffer3.append("] ");
                    string = stringBuffer3.toString();
                }
                stringBuffer.append(string);
            }
            stringBuffer.append("}");
            return stringBuffer.toString();
        } catch (IOException e2) {
            return e2.toString();
        }
    }
}
