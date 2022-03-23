package com.example.iteratiatask.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.iteratiatask.service.CurrencyASPParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Resolves GraphQL query for bank exchange date.
 * Returns String date from bank page.
 */

@Slf4j
@Component
public class DateQueryResolver implements GraphQLQueryResolver {

    private CurrencyASPParser parser;

    public DateQueryResolver(CurrencyASPParser parser) {
        this.parser = parser;
    }

    public String getDate() {
        log.info("Getting bank exchange date");
        return parser.getDate();
    }
}
