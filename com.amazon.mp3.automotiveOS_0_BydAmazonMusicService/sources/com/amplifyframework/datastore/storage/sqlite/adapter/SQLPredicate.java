package com.amplifyframework.datastore.storage.sqlite.adapter;

import com.amplifyframework.core.model.query.predicate.BeginsWithQueryOperator;
import com.amplifyframework.core.model.query.predicate.BetweenQueryOperator;
import com.amplifyframework.core.model.query.predicate.ContainsQueryOperator;
import com.amplifyframework.core.model.query.predicate.EqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.GreaterOrEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.GreaterThanQueryOperator;
import com.amplifyframework.core.model.query.predicate.LessOrEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.LessThanQueryOperator;
import com.amplifyframework.core.model.query.predicate.NotContainsQueryOperator;
import com.amplifyframework.core.model.query.predicate.NotEqualQueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.sqlite.SQLiteModelFieldTypeConverter;
import com.amplifyframework.datastore.storage.sqlite.SqlKeyword;
import com.amplifyframework.datastore.storage.sqlite.TypeConverter;
import com.amplifyframework.util.GsonFactory;
import com.amplifyframework.util.Immutable;
import com.amplifyframework.util.Wrap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class SQLPredicate {
    private final List<Object> bindings = new LinkedList();
    private final String queryString;

    public SQLPredicate(QueryPredicate queryPredicate) throws DataStoreException {
        this.queryString = parsePredicate(queryPredicate).toString();
    }

    public String toString() {
        return this.queryString;
    }

    public List<Object> getBindings() {
        return Immutable.of(this.bindings);
    }

    private void addBinding(Object obj) {
        this.bindings.add(SQLiteModelFieldTypeConverter.convertRawValueToTarget(obj, TypeConverter.getJavaFieldTypeFromValue(obj), GsonFactory.instance()));
    }

    private StringBuilder parsePredicate(QueryPredicate queryPredicate) throws DataStoreException {
        if (QueryPredicates.all().equals(queryPredicate)) {
            return new StringBuilder("1 = 1");
        }
        if (QueryPredicates.none().equals(queryPredicate)) {
            return new StringBuilder("1 = 0");
        }
        if (queryPredicate instanceof QueryPredicateOperation) {
            return parsePredicateOperation((QueryPredicateOperation) queryPredicate);
        }
        if (queryPredicate instanceof QueryPredicateGroup) {
            return parsePredicateGroup((QueryPredicateGroup) queryPredicate);
        }
        throw new DataStoreException("Tried to parse an unsupported QueryPredicate", "Try changing to one of the supported values: QueryPredicateOperation, QueryPredicateGroup, MatchAllQueryPredicate, or MatchNoneQueryPredicate.");
    }

    private StringBuilder parsePredicateOperation(QueryPredicateOperation<?> queryPredicateOperation) throws DataStoreException {
        String strField;
        StringBuilder sb = new StringBuilder();
        String strInBackticks = Wrap.inBackticks(queryPredicateOperation.modelName());
        String strInBackticks2 = Wrap.inBackticks(queryPredicateOperation.field());
        if (strInBackticks == null) {
            strField = queryPredicateOperation.field();
        } else {
            strField = strInBackticks + "." + strInBackticks2;
        }
        QueryOperator<?> queryOperatorOperator = queryPredicateOperation.operator();
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[queryOperatorOperator.type().ordinal()]) {
            case 1:
                BetweenQueryOperator betweenQueryOperator = (BetweenQueryOperator) queryOperatorOperator;
                addBinding(betweenQueryOperator.start());
                addBinding(betweenQueryOperator.end());
                sb.append(strField);
                SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
                sb.append(sqlKeyword);
                sb.append(SqlKeyword.BETWEEN);
                sb.append(sqlKeyword);
                sb.append("?");
                sb.append(sqlKeyword);
                sb.append(SqlKeyword.AND);
                sb.append(sqlKeyword);
                sb.append("?");
                return sb;
            case 2:
                addBinding(((ContainsQueryOperator) queryOperatorOperator).value());
                sb.append("instr(");
                sb.append(strField);
                sb.append(",");
                sb.append("?");
                sb.append(")");
                SqlKeyword sqlKeyword2 = SqlKeyword.DELIMITER;
                sb.append(sqlKeyword2);
                sb.append(SqlKeyword.fromQueryOperator(QueryOperator.Type.GREATER_THAN));
                sb.append(sqlKeyword2);
                sb.append("0");
                return sb;
            case 3:
                addBinding(((NotContainsQueryOperator) queryOperatorOperator).value());
                sb.append("instr(");
                sb.append(strField);
                sb.append(",");
                sb.append("?");
                sb.append(")");
                SqlKeyword sqlKeyword3 = SqlKeyword.DELIMITER;
                sb.append(sqlKeyword3);
                sb.append(SqlKeyword.fromQueryOperator(QueryOperator.Type.EQUAL));
                sb.append(sqlKeyword3);
                sb.append("0");
                return sb;
            case 4:
                addBinding(((BeginsWithQueryOperator) queryOperatorOperator).value() + "%");
                sb.append(strField);
                SqlKeyword sqlKeyword4 = SqlKeyword.DELIMITER;
                sb.append(sqlKeyword4);
                sb.append(SqlKeyword.LIKE);
                sb.append(sqlKeyword4);
                sb.append("?");
                return sb;
            case 5:
            case 6:
                if (getOperatorValue(queryOperatorOperator) == null) {
                    SqlKeyword sqlKeyword5 = queryOperatorOperator.type() == QueryOperator.Type.EQUAL ? SqlKeyword.IS_NULL : SqlKeyword.IS_NOT_NULL;
                    sb.append(strField);
                    sb.append(SqlKeyword.DELIMITER);
                    sb.append(sqlKeyword5.toString());
                    return sb;
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                break;
            default:
                throw new DataStoreException("Tried to parse an unsupported QueryPredicateOperation", "Try changing to one of the supported values from QueryPredicateOperation.Type enum.");
        }
        addBinding(getOperatorValue(queryOperatorOperator));
        sb.append(strField);
        SqlKeyword sqlKeyword6 = SqlKeyword.DELIMITER;
        sb.append(sqlKeyword6);
        sb.append(SqlKeyword.fromQueryOperator(queryOperatorOperator.type()));
        sb.append(sqlKeyword6);
        sb.append("?");
        return sb;
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.storage.sqlite.adapter.SQLPredicate$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type;
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryPredicateGroup$Type;

        static {
            int[] iArr = new int[QueryPredicateGroup.Type.values().length];
            $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryPredicateGroup$Type = iArr;
            try {
                iArr[QueryPredicateGroup.Type.NOT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryPredicateGroup$Type[QueryPredicateGroup.Type.OR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryPredicateGroup$Type[QueryPredicateGroup.Type.AND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[QueryOperator.Type.values().length];
            $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type = iArr2;
            try {
                iArr2[QueryOperator.Type.BETWEEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.CONTAINS.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.NOT_CONTAINS.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.BEGINS_WITH.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.EQUAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.NOT_EQUAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_THAN.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_THAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.LESS_OR_EQUAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[QueryOperator.Type.GREATER_OR_EQUAL.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private StringBuilder parsePredicateGroup(QueryPredicateGroup queryPredicateGroup) throws DataStoreException {
        StringBuilder sb = new StringBuilder();
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryPredicateGroup$Type[queryPredicateGroup.type().ordinal()];
        if (i == 1) {
            sb.append(SqlKeyword.fromQueryPredicateGroup(queryPredicateGroup.type()));
            sb.append(SqlKeyword.DELIMITER);
            sb.append((CharSequence) parsePredicate(queryPredicateGroup.predicates().get(0)));
            return sb;
        }
        if (i == 2 || i == 3) {
            sb.append("(");
            Iterator<QueryPredicate> it = queryPredicateGroup.predicates().iterator();
            while (it.hasNext()) {
                sb.append((CharSequence) parsePredicate(it.next()));
                if (it.hasNext()) {
                    SqlKeyword sqlKeyword = SqlKeyword.DELIMITER;
                    sb.append(sqlKeyword);
                    sb.append(SqlKeyword.fromQueryPredicateGroup(queryPredicateGroup.type()));
                    sb.append(sqlKeyword);
                }
            }
            sb.append(")");
            return sb;
        }
        throw new DataStoreException("Tried to parse an unsupported QueryPredicateGroup", "Try changing to one of the supported values from QueryPredicateGroup.Type enum.");
    }

    private Object getOperatorValue(QueryOperator<?> queryOperator) throws DataStoreException {
        switch (AnonymousClass1.$SwitchMap$com$amplifyframework$core$model$query$predicate$QueryOperator$Type[queryOperator.type().ordinal()]) {
            case 5:
                return ((EqualQueryOperator) queryOperator).value();
            case 6:
                return ((NotEqualQueryOperator) queryOperator).value();
            case 7:
                return ((LessThanQueryOperator) queryOperator).value();
            case 8:
                return ((GreaterThanQueryOperator) queryOperator).value();
            case 9:
                return ((LessOrEqualQueryOperator) queryOperator).value();
            case 10:
                return ((GreaterOrEqualQueryOperator) queryOperator).value();
            default:
                throw new DataStoreException("Tried to parse an unsupported QueryOperator type", "Check if a new QueryOperator.Type enum has been created which is not supported.");
        }
    }
}
