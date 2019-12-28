package com.floreysoft.jmte.token;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.floreysoft.jmte.TemplateContext;
import com.floreysoft.jmte.util.Util;

public class ForEachToken extends ExpressionToken {
	public static final String FOREACH = "foreach";
	public static final String UNDEFINED_VARNAME = "_undefined";

	private final String varName;
	private final String separator;

	private transient Iterator<Object> iterator;
	private transient int index;

	public ForEachToken(String expression, String varName, String separator) {
		super(expression);
		this.varName = varName;
		this.separator = separator != null ? separator : "";
		this.index = -1;
	}

	public ForEachToken(List<String> segments, String expression, String varName, String separator) {
		super(segments, expression);
		this.varName = varName;
		this.separator = separator != null ? separator : "";
		this.index = -1;
	}

	@Override
	public String getText() {
		if (text == null) {
			text = FOREACH + " " + getExpression() + " " + varName
					+ ((separator == null || separator.isEmpty()) ? "" : " " + separator);
		}
		return text;
	}

	@Override
	public Object evaluate(TemplateContext context) {
		Object value = evaluatePlain(context);
        return context.modelAdaptor.getIterable(value);
	}

	public Iterator<Object> iterator() {
		return getIterator();
	}

	public String getVarName() {
		return varName;
	}

	public String getSeparator() {
		return separator;
	}

	public boolean isLast() {
		return !iterator().hasNext();
	}

	public boolean isFirst() {
		return index == 0;
	}

	public int getIndex() {
		return index;
	}

	public void setIterable(Iterable<Object> iterable) {
		this.iterator = iterable.iterator();
	}

	public void setIterator(Iterator<Object> iterator) {
		this.iterator = iterator;
	}

	public Iterator<Object> getIterator() {
		return iterator;
	}
	
	public Object advance() {
		index++;
		return iterator.next();
	}

	public void resetIndex() {
		index = -1;
	}
}
