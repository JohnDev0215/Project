package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.reflect.Accessor;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class Scope<BeanT, PropT, ItemT, PackT> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Accessor<BeanT, PropT> acc;
    private BeanT bean;
    public final UnmarshallingContext context;
    private Lister<BeanT, PropT, ItemT, PackT> lister;
    private PackT pack;

    Scope(UnmarshallingContext unmarshallingContext) {
        this.context = unmarshallingContext;
    }

    public boolean hasStarted() {
        return this.bean != null;
    }

    public void reset() {
        if (this.bean == null) {
            return;
        }
        this.bean = null;
        this.acc = null;
        this.pack = null;
        this.lister = null;
    }

    public void finish() throws AccessorException {
        if (hasStarted()) {
            this.lister.endPacking(this.pack, this.bean, this.acc);
            reset();
        }
    }

    private boolean clean() {
        return this.bean == null && this.acc == null && this.pack == null && this.lister == null;
    }

    public void add(Accessor<BeanT, PropT> accessor, Lister<BeanT, PropT, ItemT, PackT> lister, ItemT itemt) throws SAXException {
        try {
            if (!hasStarted()) {
                BeanT beant = (BeanT) this.context.getCurrentState().getTarget();
                this.bean = beant;
                this.acc = accessor;
                this.lister = lister;
                this.pack = lister.startPacking(beant, accessor);
            }
            lister.addToPack(this.pack, itemt);
        } catch (AccessorException e) {
            Loader.handleGenericException(e, true);
            this.lister = Lister.getErrorInstance();
            this.acc = Accessor.getErrorInstance();
        }
    }

    public void start(Accessor<BeanT, PropT> accessor, Lister<BeanT, PropT, ItemT, PackT> lister) throws SAXException {
        try {
            if (hasStarted()) {
                return;
            }
            BeanT beant = (BeanT) this.context.getCurrentState().getTarget();
            this.bean = beant;
            this.acc = accessor;
            this.lister = lister;
            this.pack = lister.startPacking(beant, accessor);
        } catch (AccessorException e) {
            Loader.handleGenericException(e, true);
            this.lister = Lister.getErrorInstance();
            this.acc = Accessor.getErrorInstance();
        }
    }
}
