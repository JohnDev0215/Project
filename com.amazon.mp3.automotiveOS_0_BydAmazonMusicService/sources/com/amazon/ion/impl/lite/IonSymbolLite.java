package com.amazon.ion.impl.lite;

import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonSymbol;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl.lite.IonValueLite;
import java.io.IOException;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
final class IonSymbolLite extends IonTextLite implements _Private_IonSymbol {
    private static final int HASH_SIGNATURE = IonType.SYMBOL.toString().hashCode();
    private int _sid;

    IonSymbolLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
        this._sid = -1;
    }

    IonSymbolLite(IonSymbolLite ionSymbolLite, IonContext ionContext) throws UnknownSymbolException {
        super(ionSymbolLite, ionContext);
        this._sid = -1;
    }

    IonSymbolLite(ContainerlessContext containerlessContext, SymbolToken symbolToken) {
        super(containerlessContext, symbolToken == null);
        this._sid = -1;
        if (symbolToken != null) {
            String text = symbolToken.getText();
            int sid = symbolToken.getSid();
            if (text != null) {
                super.setValue(text);
            } else {
                this._sid = sid;
                _isSymbolIdPresent(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonSymbolLite clone(IonContext ionContext) {
        IonSymbolLite ionSymbolLite = new IonSymbolLite(this, ionContext);
        if (this._sid == 0) {
            ionSymbolLite._sid = 0;
        }
        return ionSymbolLite;
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonSymbolLite mo103clone() throws UnknownSymbolException {
        int i;
        if (!isNullValue() && (i = this._sid) != -1 && i != 0 && _stringValue() == null) {
            throw new UnknownSymbolException(this._sid);
        }
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode;
        int i = HASH_SIGNATURE;
        if (!isNullValue()) {
            SymbolToken symbolTokenSymbolValue = symbolValue(symbolTableProvider);
            String text = symbolTokenSymbolValue.getText();
            if (text == null) {
                iHashCode = symbolTokenSymbolValue.getSid() * WorkQueueKt.MASK;
            } else {
                iHashCode = text.hashCode() * 31;
            }
            i ^= iHashCode ^ ((iHashCode << 29) ^ (iHashCode >> 3));
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.SYMBOL;
    }

    @Override // com.amazon.ion.impl._Private_IonSymbol, com.amazon.ion.IonSymbol
    @Deprecated
    public int getSymbolId() throws NullValueException {
        return getSymbolId(null);
    }

    private int getSymbolId(_Private_IonValue.SymbolTableProvider symbolTableProvider) throws NullValueException {
        SymbolTable symbolTable;
        validateThisNotNull();
        if (this._sid != -1 || isReadOnly()) {
            return this._sid;
        }
        if (symbolTableProvider != null) {
            symbolTable = symbolTableProvider.getSymbolTable();
        } else {
            symbolTable = getSymbolTable();
        }
        if (symbolTable == null) {
            symbolTable = getSystem().getSystemSymbolTable();
        }
        String str_get_value = _get_value();
        if (!symbolTable.isLocalTable()) {
            setSID(symbolTable.findSymbol(str_get_value));
            if (this._sid > 0 || isReadOnly()) {
                return this._sid;
            }
        }
        SymbolToken symbolTokenFind = symbolTable.find(str_get_value);
        if (symbolTokenFind != null) {
            setSID(symbolTokenFind.getSid());
            _set_value(symbolTokenFind.getText());
        }
        return this._sid;
    }

    private void setSID(int i) {
        this._sid = i;
        if (i > 0) {
            cascadeSIDPresentToContextRoot();
        }
    }

    private String _stringValue() {
        return _stringValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    private String _stringValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        String str_get_value = _get_value();
        if (str_get_value == null && (str_get_value = symbolTableProvider.getSymbolTable().findKnownSymbol(this._sid)) != null && !_isLocked()) {
            _set_value(str_get_value);
        }
        return str_get_value;
    }

    @Override // com.amazon.ion.IonSymbol
    public SymbolToken symbolValue() {
        return symbolValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    @Override // com.amazon.ion.impl._Private_IonSymbol
    public SymbolToken symbolValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        if (isNullValue()) {
            return null;
        }
        return _Private_Utils.newSymbolToken(_stringValue(symbolTableProvider), getSymbolId(symbolTableProvider));
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite
    public void setValue(String str) {
        super.setValue(str);
        this._sid = -1;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    boolean attemptClearSymbolIDValues() {
        boolean zAttemptClearSymbolIDValues = super.attemptClearSymbolIDValues();
        if (isNullValue() || this._sid == -1) {
            return zAttemptClearSymbolIDValues;
        }
        if (_stringValue() == null) {
            return false;
        }
        this._sid = -1;
        return zAttemptClearSymbolIDValues;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeSymbolToken(symbolValue(symbolTableProvider));
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.IonText
    public String stringValue() throws UnknownSymbolException {
        return stringValue(new IonValueLite.LazySymbolTableProvider(this));
    }

    private String stringValue(_Private_IonValue.SymbolTableProvider symbolTableProvider) throws UnknownSymbolException {
        if (isNullValue()) {
            return null;
        }
        String str_stringValue = _stringValue(symbolTableProvider);
        if (str_stringValue != null) {
            return str_stringValue;
        }
        throw new UnknownSymbolException(this._sid);
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
