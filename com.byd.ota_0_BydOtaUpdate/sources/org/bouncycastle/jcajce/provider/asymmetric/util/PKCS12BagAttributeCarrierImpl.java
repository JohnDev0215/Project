package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.i;
import g.a.a.m;
import g.a.a.p;
import g.a.c.c.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class PKCS12BagAttributeCarrierImpl implements e {
    private Hashtable pkcs12Attributes;
    private Vector pkcs12Ordering;

    public PKCS12BagAttributeCarrierImpl() {
        this(new Hashtable(), new Vector());
    }

    public PKCS12BagAttributeCarrierImpl(Hashtable hashtable, Vector vector) {
        this.pkcs12Attributes = hashtable;
        this.pkcs12Ordering = vector;
    }

    public Hashtable getAttributes() {
        return this.pkcs12Attributes;
    }

    @Override // g.a.c.c.e
    public g.a.a.e getBagAttribute(m mVar) {
        return (g.a.a.e) this.pkcs12Attributes.get(mVar);
    }

    @Override // g.a.c.c.e
    public Enumeration getBagAttributeKeys() {
        return this.pkcs12Ordering.elements();
    }

    public Vector getOrdering() {
        return this.pkcs12Ordering;
    }

    public void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        Object object = objectInputStream.readObject();
        if (object instanceof Hashtable) {
            this.pkcs12Attributes = (Hashtable) object;
            this.pkcs12Ordering = (Vector) objectInputStream.readObject();
        } else {
            i iVar = new i((byte[]) object);
            while (true) {
                m mVar = (m) iVar.M();
                if (mVar == null) {
                    return;
                } else {
                    setBagAttribute(mVar, iVar.M());
                }
            }
        }
    }

    @Override // g.a.c.c.e
    public void setBagAttribute(m mVar, g.a.a.e eVar) {
        if (this.pkcs12Attributes.containsKey(mVar)) {
            this.pkcs12Attributes.put(mVar, eVar);
        } else {
            this.pkcs12Attributes.put(mVar, eVar);
            this.pkcs12Ordering.addElement(mVar);
        }
    }

    public int size() {
        return this.pkcs12Ordering.size();
    }

    public void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        if (this.pkcs12Ordering.size() == 0) {
            objectOutputStream.writeObject(new Hashtable());
            objectOutputStream.writeObject(new Vector());
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        p pVar = new p(byteArrayOutputStream);
        Enumeration bagAttributeKeys = getBagAttributeKeys();
        while (bagAttributeKeys.hasMoreElements()) {
            m mVar = (m) bagAttributeKeys.nextElement();
            pVar.j(mVar);
            pVar.j((g.a.a.e) this.pkcs12Attributes.get(mVar));
        }
        objectOutputStream.writeObject(byteArrayOutputStream.toByteArray());
    }
}
