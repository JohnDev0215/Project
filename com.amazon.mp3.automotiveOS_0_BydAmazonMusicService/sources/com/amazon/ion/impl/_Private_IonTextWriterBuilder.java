package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.system.SimpleCatalog;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public class _Private_IonTextWriterBuilder extends IonTextWriterBuilder {
    private static final CharSequence SPACE_CHARACTER = " ";
    public static _Private_IonTextWriterBuilder STANDARD = standard().immutable();
    public boolean _blob_as_string;
    private _Private_CallbackBuilder _callback_builder;
    public boolean _clob_as_string;
    public boolean _decimal_as_float;
    public boolean _float_nan_and_inf_as_null;
    private boolean _pretty_print;
    public boolean _sexp_as_list;
    public boolean _skip_annotations;
    public boolean _string_as_json;
    public boolean _symbol_as_string;
    public boolean _timestamp_as_millis;
    public boolean _timestamp_as_string;
    public boolean _untyped_nulls;

    @Override // com.amazon.ion.system.IonTextWriterBuilder
    public _Private_IonTextWriterBuilder immutable() {
        return this;
    }

    public static _Private_IonTextWriterBuilder standard() {
        return new Mutable();
    }

    private _Private_IonTextWriterBuilder() {
    }

    private _Private_IonTextWriterBuilder(_Private_IonTextWriterBuilder _private_iontextwriterbuilder) {
        super(_private_iontextwriterbuilder);
        this._callback_builder = _private_iontextwriterbuilder._callback_builder;
        this._pretty_print = _private_iontextwriterbuilder._pretty_print;
        this._blob_as_string = _private_iontextwriterbuilder._blob_as_string;
        this._clob_as_string = _private_iontextwriterbuilder._clob_as_string;
        this._decimal_as_float = _private_iontextwriterbuilder._decimal_as_float;
        this._float_nan_and_inf_as_null = _private_iontextwriterbuilder._float_nan_and_inf_as_null;
        this._sexp_as_list = _private_iontextwriterbuilder._sexp_as_list;
        this._skip_annotations = _private_iontextwriterbuilder._skip_annotations;
        this._string_as_json = _private_iontextwriterbuilder._string_as_json;
        this._symbol_as_string = _private_iontextwriterbuilder._symbol_as_string;
        this._timestamp_as_millis = _private_iontextwriterbuilder._timestamp_as_millis;
        this._timestamp_as_string = _private_iontextwriterbuilder._timestamp_as_string;
        this._untyped_nulls = _private_iontextwriterbuilder._untyped_nulls;
    }

    public final _Private_IonTextWriterBuilder copy() {
        return new Mutable();
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder
    public _Private_IonTextWriterBuilder mutable() {
        return copy();
    }

    final boolean isPrettyPrintOn() {
        return this._pretty_print;
    }

    final CharSequence lineSeparator() {
        if (this._pretty_print) {
            return getNewLineType().getCharSequence();
        }
        return SPACE_CHARACTER;
    }

    final CharSequence topLevelSeparator() {
        return getWriteTopLevelValuesOnNewLines() ? getNewLineType().getCharSequence() : lineSeparator();
    }

    private _Private_IonTextWriterBuilder fillDefaults() {
        _Private_IonTextWriterBuilder _private_iontextwriterbuilderCopy = copy();
        if (_private_iontextwriterbuilderCopy.getCatalog() == null) {
            _private_iontextwriterbuilderCopy.setCatalog(new SimpleCatalog());
        }
        if (_private_iontextwriterbuilderCopy.getCharset() == null) {
            _private_iontextwriterbuilderCopy.setCharset(IonTextWriterBuilder.UTF8);
        }
        if (_private_iontextwriterbuilderCopy.getNewLineType() == null) {
            _private_iontextwriterbuilderCopy.setNewLineType(IonTextWriterBuilder.NewLineType.PLATFORM_DEPENDENT);
        }
        return (_Private_IonTextWriterBuilder) _private_iontextwriterbuilderCopy.immutable();
    }

    private IonWriter build(_Private_FastAppendable _private_fastappendable) {
        IonCatalog catalog = getCatalog();
        SymbolTable[] imports = getImports();
        IonSystem ionSystemBuild = IonSystemBuilder.standard().withCatalog(catalog).build();
        SymbolTable systemSymbolTable = ionSystemBuild.getSystemSymbolTable();
        return new IonWriterUser(catalog, ionSystemBuild, getCallbackBuilder() == null ? new IonWriterSystemText(systemSymbolTable, this, _private_fastappendable) : new IonWriterSystemTextMarkup(systemSymbolTable, this, _private_fastappendable), _Private_Utils.initialSymtab(((_Private_ValueFactory) ionSystemBuild).getLstFactory(), systemSymbolTable, imports));
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriter build(OutputStream outputStream) {
        return fillDefaults().build(new OutputStreamFastAppendable(outputStream));
    }

    private static final class Mutable extends _Private_IonTextWriterBuilder {
        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder
        public _Private_IonTextWriterBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.system.IonWriterBuilderBase
        protected void mutationCheck() {
        }

        private Mutable() {
            super();
        }

        private Mutable(_Private_IonTextWriterBuilder _private_iontextwriterbuilder) {
            super();
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder
        public _Private_IonTextWriterBuilder immutable() {
            return new _Private_IonTextWriterBuilder();
        }
    }

    public final _Private_CallbackBuilder getCallbackBuilder() {
        return this._callback_builder;
    }
}
