package com.interview.questions.interview.common.rest.support;

import com.interview.questions.interview.common.rest.Quote;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Service providing high-level, management of data access and other
 * {@link Quote} -related operations.
 */
@Service
public class QuoteService {

    private static final Log logger = LogFactory.getLog(QuoteService.class);

    public static final String QUOTES_ENDPOINT = "http://gturnquist-quoters.cfapps.io/api/random";

    private final RestTemplate restTemplate = new RestTemplate();

    public Quote getQuote() {
        Quote quote = null;
        try {
            quote = restTemplate.getForObject(QUOTES_ENDPOINT, Quote.class);
            logger.info(quote.toString());
        } catch (Exception ex) {
            logger.error("getQuote: " + ex.getMessage());
        }
        return quote;
    }
}
