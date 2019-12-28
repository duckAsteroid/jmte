package com.floreysoft.jmte.message;

import java.util.Map;

import com.floreysoft.jmte.ErrorHandler;
import com.floreysoft.jmte.token.Token;

public class DefaultErrorHandler extends AbstractErrorHandler implements ErrorHandler {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(ErrorMessage errorMessage, Token token,
			Map<String, Object> parameters) throws ParseException {
		Message message = new ResourceBundleMessage(errorMessage.key).withModel(
				parameters).onToken(token);
		logger.severe(message.format(locale));
		throw new ParseException(message);
	}
}
